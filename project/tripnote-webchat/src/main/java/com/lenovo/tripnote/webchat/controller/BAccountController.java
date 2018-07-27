package com.lenovo.tripnote.webchat.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.lenovo.tripnote.webchat.entity.BAccount;
import com.lenovo.tripnote.webchat.entity.BLogin;
import com.lenovo.tripnote.webchat.entity.vo.BAccountVo;
import com.lenovo.tripnote.webchat.entity.vo.BAutoResultVo;
import com.lenovo.tripnote.webchat.entity.vo.PhoneLoginVo;
import com.lenovo.tripnote.webchat.service.BAccountService;
import com.lenovo.tripnote.webchat.sms.ISmsSender;
import com.lenovo.tripnote.webchat.utils.RandomUtils;
import com.lenovo.tripnote.webchat.vo.AutoLoginInfo;
import com.lenovo.tripnote.webchat.vo.LoginInfoVo;
import com.lenovo.tripnote.webchat.vo.RegisterVo;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.SmsCodeVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Controller
@RequestMapping()
@PropertySource(value="classpath:conf/common.properties",encoding="UTF-8")
public class BAccountController {

    @Autowired
    private BAccountService bAccountService;
    
    @Autowired
    private ISmsSender smsSender;
    
    @Value("${token.expiration}")
    //过期时间 单位是秒 默认1个小时
	private Integer expiration = 60*60;

    @ResponseBody
    @RequestMapping(value = "/doLogin")
    public ResultVo doLogin(HttpServletRequest request,LoginInfoVo info){
    	ResultVo vo = new ResultVo();
    	BAccount account = bAccountService.getByUsernameOrPhone(info.getLoginName());
    	if(account==null){
    		vo.setCode(Result.FAUL);
    		vo.setMessage("登录名不存在");
    		return vo;
    	}else if(!com.alibaba.druid.util.StringUtils.equalsIgnoreCase(info.getLoginPasswd(), account.getLoginPassword())){//验证密码
    		vo.setCode(Result.FAUL);
    		vo.setMessage("密码不正确");
    		return vo;
    	}
    	BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getId()));
		if (oldLogin != null) {// 在其它客户端已经登录 返回状态给正在登录的客户端
			vo.setCode(Result.REPEAT);
			vo.setMessage(oldLogin.getDevice());
			oldLogin.setDevice(info.getDevice());
			oldLogin.setLogintime(new Date());
			oldLogin.setUserid(account.getId());
			oldLogin.setLoginname(account.getLoginName());
			oldLogin.setLoginip(request.getRemoteHost());
			bAccountService.update(oldLogin);
		} else {
			BLogin record = new BLogin();
			record.setDevice(info.getDevice());
			record.setLogintime(new Date());
			record.setUserid(account.getId());
			record.setLoginname(account.getLoginName());
			record.setLoginip(request.getRemoteHost());
			record.setStatus(1);
			vo.setCode(Result.SUCESSFUL);
			bAccountService.insert(record);
			System.out.println(record);
		}
		Claims arg0 = new DefaultClaims();
	    arg0.put(Result.USERID, account.getId());
	    arg0.put(Result.USER_LOGINNAME, info.getLoginName());
		// Create Twt token
	    Date date = new Date();
	    Date ex = (Date) date.clone();
	    ex.setTime(date.getTime()+expiration*1000);
        String jwtToken = Jwts.builder().setClaims(arg0).setIssuedAt(date).setExpiration(ex)
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        vo.setData(jwtToken);
        return vo;
    }
    @ResponseBody
    @RequestMapping(value = "/auto/doLogin")
    public ResultVo doAutoLogin(HttpServletRequest request,@RequestBody AutoLoginInfo info){
    	ResultVo vo = new ResultVo();
    	BAccount account = bAccountService.getAutoToken(info.getLoginToken(), info.getAutoLogin());
    	if(account==null){
    		//vo.setCode(Result.FAUL);
    		vo.setMessage("登录名不存在");
    		BAccount newAccount = new BAccount();
    		try {
				BeanUtils.copyProperties(newAccount, info);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
    		bAccountService.insert(newAccount);
    		account = newAccount;
    	}
    	BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getId()));
		if (oldLogin != null) {// 在其它客户端已经登录 返回状态给正在登录的客户端
			vo.setCode(Result.REPEAT);
			vo.setMessage(oldLogin.getDevice());
			oldLogin.setDevice(info.getDevice());
			oldLogin.setLogintime(new Date());
			oldLogin.setUserid(account.getId());
			oldLogin.setLoginname(account.getLoginName());
			oldLogin.setLoginip(request.getRemoteHost());
			bAccountService.update(oldLogin);
		} else {
			BLogin record = new BLogin();
			record.setDevice(info.getDevice());
			record.setLogintime(new Date());
			record.setUserid(account.getId());
			record.setLoginname(account.getLoginName());
			record.setLoginip(request.getRemoteHost());
			record.setStatus(1);
			vo.setCode(Result.SUCESSFUL);
			bAccountService.insert(record);
			System.out.println(record);
		}
		Claims arg0 = new DefaultClaims();
	    arg0.put(Result.USERID, account.getId());
	    arg0.put(Result.USER_LOGINNAME, info.getLoginName());
		// Create Twt token
	    Date date = new Date();
	    Date ex = (Date) date.clone();
	    ex.setTime(date.getTime()+expiration*1000);
        String jwtToken = Jwts.builder().setClaims(arg0).setIssuedAt(date).setExpiration(ex)
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        BAutoResultVo resultVo = new BAutoResultVo();
        try {
			BeanUtils.copyProperties(resultVo, account);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
        resultVo.setToken(jwtToken);
        vo.setData(resultVo);
        return vo;
    }
    
    @ResponseBody
    @RequestMapping(value = "/phone/doLogin")
    public ResultVo doPhoneLogin(HttpServletRequest request,@RequestBody PhoneLoginVo info){
    	ResultVo vo = new ResultVo();
    	SmsCodeVo smsCode = (SmsCodeVo) request.getSession().getAttribute("smscode");
		if (smsCode != null && StringUtils.equals(info.getSmsCode(), smsCode.getSmsCode())) {// 验证码相同
			BAccount account = bAccountService.getByUsernameOrPhone(info.getPhone());
	    	if(account==null){
	    		vo.setCode(Result.FAUL);
	    		vo.setMessage("登录名不存在");
	    		return vo;
	    	}
	    	BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getId()));
			if (oldLogin != null) {// 在其它客户端已经登录 返回状态给正在登录的客户端
				vo.setCode(Result.REPEAT);
				vo.setMessage(oldLogin.getDevice());
				oldLogin.setDevice(info.getDevice());
				oldLogin.setLogintime(new Date());
				oldLogin.setUserid(account.getId());
				oldLogin.setLoginname(account.getLoginName());
				oldLogin.setLoginip(request.getRemoteHost());
				bAccountService.update(oldLogin);
			} else {
				BLogin record = new BLogin();
				record.setDevice(info.getDevice());
				record.setLogintime(new Date());
				record.setUserid(account.getId());
				record.setLoginname(account.getLoginName());
				record.setLoginip(request.getRemoteHost());
				record.setStatus(1);
				vo.setCode(Result.SUCESSFUL);
				bAccountService.insert(record);
			}
			Claims arg0 = new DefaultClaims();
		    arg0.put(Result.USERID, account.getId());
		    arg0.put(Result.USER_LOGINNAME, info.getPhone());
			// Create Twt token
		    Date date = new Date();
		    Date ex = (Date) date.clone();
		    ex.setTime(date.getTime()+expiration*1000);
	        String jwtToken = Jwts.builder().setClaims(arg0).setIssuedAt(date).setExpiration(ex)
	                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	        BAutoResultVo resultVo = new BAutoResultVo();
	        try {
				BeanUtils.copyProperties(resultVo, account);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
	        resultVo.setToken(jwtToken);
	        vo.setData(resultVo);
		}else{
			vo.setCode(Result.FAUL);
			vo.setMessage("验证码错误或过时");
		}
        return vo;
    }
    
    @RequestMapping(value = "/sendRegisterCode")
	public @ResponseBody ResultVo doSendRegisterSMS(HttpServletRequest request, String phoneNo) {
		SmsCodeVo smsCodeVO = new SmsCodeVo();
		String code = RandomUtils.createRandomVcode();
		smsCodeVO.setSendTime(new Date().getTime());
		smsCodeVO.setSmsCode(code);
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		//vo.setData(code);
		try {
			if (smsSender.sendRegisterCode(phoneNo, code))// 发送验证码成功
				request.getSession().setAttribute("smscode", smsCodeVO);
		} catch (ClientException | IOException e) {
			vo.setCode(Result.FAUL);
			vo.setData(e.getMessage());
		}
		return vo;
	}

	@RequestMapping(value = "/token/logout")
	public @ResponseBody ResultVo doLogut(HttpServletRequest request, Model model) {
		TokenVo account = (TokenVo)request.getAttribute(Result.TOKEN);
		ResultVo vo = new ResultVo();
		BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getUserId()));
		if (oldLogin != null) {
			oldLogin.setLoginouttime(new Date());
			oldLogin.setStatus(-1);
			bAccountService.update(oldLogin);
		} else {
			vo.setCode(Result.FAUL);
		}
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}

	@RequestMapping(value = "/register")
	public @ResponseBody ResultVo doRegister(HttpServletRequest request, RegisterVo register) {
		SmsCodeVo smsCode = (SmsCodeVo) request.getSession().getAttribute("smscode");
		ResultVo vo = new ResultVo();
		if (smsCode != null && StringUtils.equals(register.getSmsCode(), smsCode.getSmsCode())) {// 验证码相同
			BAccount account = bAccountService.getByUsernameOrPhone(register.getLoginName());
			if (account != null) {
				vo.setCode(Result.FAUL);
				vo.setMessage("用户名或手机号已经存在");
				return vo;
			}
			BAccount bacount = new BAccount();
			bacount.setPhone(register.getLoginName());
			bacount.setLoginName(register.getLoginName());
			bacount.setLoginPassword(register.getLoginPasswd());
			bAccountService.insert(bacount);
			vo.setCode(Result.SUCESSFUL);
		} else {
			vo.setCode(Result.FAUL);
			vo.setMessage("验证码错误或过时");
		}
		return vo;
	}

	@RequestMapping(value = "/register/check")
	public @ResponseBody ResultVo doCheck(HttpServletRequest request, RegisterVo register) {
		ResultVo vo = new ResultVo();
		BAccount account = bAccountService.getByUsernameOrPhone(register.getLoginName());
		if (account != null) {
			vo.setCode(Result.FAUL);
			vo.setMessage("用户名或手机号已经存在");
			return vo;
		}
		vo.setCode(Result.SUCESSFUL);
		return vo;

	}

	@RequestMapping(value = "/resetPasswd")
	public @ResponseBody ResultVo doResetPasswd(HttpServletRequest request, RegisterVo register) {
		ResultVo vo = new ResultVo();
		SmsCodeVo smsCode = (SmsCodeVo) request.getSession().getAttribute("smscode");
		if (smsCode != null && StringUtils.equals(register.getSmsCode(), smsCode.getSmsCode())) {// 验证码相同
			BAccount account = bAccountService.getByUsernameOrPhone(register.getLoginName());
			if (account == null) {
				vo.setCode(Result.FAUL);
				vo.setMessage("用户名或手机号不存在");
				return vo;
			}
			account.setLoginPassword(register.getLoginPasswd());
			bAccountService.update(account);
			vo.setCode(Result.SUCESSFUL);
		} else {
			vo.setCode(Result.FAUL);
			vo.setMessage("验证码错误或过时");
		}
		return vo;

	}
	
	@RequestMapping(value = "/code/check")
	public @ResponseBody ResultVo doCodeCheck(HttpServletRequest request, RegisterVo register) {
		ResultVo vo = new ResultVo();
		SmsCodeVo smsCode = (SmsCodeVo) request.getSession().getAttribute("smscode");
		if (smsCode != null && StringUtils.equals(register.getSmsCode(), smsCode.getSmsCode())) {// 验证码相同
			vo.setCode(Result.SUCESSFUL);
		} else {
			vo.setCode(Result.FAUL);
			vo.setMessage("验证码错误或过时");
		}
		return vo;
	}
	@RequestMapping(value = "/sendLoginCode")
	public @ResponseBody ResultVo doSendLoginSMS(HttpServletRequest request, String phoneNo) {
		SmsCodeVo smsCodeVO = new SmsCodeVo();
		String code = RandomUtils.createRandomVcode();
		smsCodeVO.setSendTime(new Date().getTime());
		smsCodeVO.setSmsCode(code);
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		//vo.setData(code);
		try {
			if (smsSender.sendLoginCode(phoneNo, code))// 发送验证码成功
				request.getSession().setAttribute("smscode", smsCodeVO);
		} catch (ClientException | IOException e) {
			vo.setCode(Result.FAUL);
			vo.setData(e.getMessage());
		}
		return vo;
	}
	@RequestMapping(value = "/token/user/doUpdate")
	public @ResponseBody ResultVo update(HttpServletRequest request,@RequestBody BAccountVo bpoiVo)
			throws IllegalAccessException, InvocationTargetException {
		TokenVo token = (TokenVo)request.getAttribute(Result.TOKEN);
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BAccount t = new BAccount();
		BeanUtils.copyProperties(t, bpoiVo);
		t.setId(token.getUserId());
		bAccountService.update(t);
		return vo;
	}
	@RequestMapping(value = "/token/user/doDetail")
	public @ResponseBody ResultVo detail(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		TokenVo token = (TokenVo)request.getAttribute(Result.TOKEN);
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bAccountService.getDetail(token.getUserId()));
		return vo;
	}

}