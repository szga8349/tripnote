package com.lenovo.tripnote.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BLogin;
import com.lenovo.tripnote.service.BAccountService;
import com.lenovo.tripnote.util.RandomUtils;
import com.lenovo.tripnote.vo.LoginInfoVo;
import com.lenovo.tripnote.vo.RegisterVo;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;
import com.lenovo.tripnote.vo.SmsCodeVo;
import com.lenovo.tripnote.vo.UserToken;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@Resource
	private BAccountService bAccountService;

	@RequestMapping(value = "/doLogin")
	public @ResponseBody ResultVo doLogin(HttpServletRequest request, Model model, LoginInfoVo info) {
		String msg = "";
		ResultVo vo = new ResultVo();
		// String userName = request.getParameter("userName");
		// String password = request.getParameter("password");

		UserToken token = new UserToken(info.getLoginName(), info.getLoginPasswd(), info.getDevice());
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				vo.setCode(Result.SUCESSFUL);
				BAccount account = (BAccount) subject.getPrincipal();
				BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getId()));
				if (oldLogin != null) {//在其它客户端已经登录 返回状态给正在登录的客户端
					vo.setCode(Result.REPEAT);
					vo.setMessage(oldLogin.getDevice());
					oldLogin.setDevice(info.getDevice());
					oldLogin.setLogintime(new Date());
					oldLogin.setUserid(Long.valueOf(account.getId()));
					oldLogin.setLoginname(account.getLoginName());
					oldLogin.setLoginip(request.getRemoteHost());
					bAccountService.update(oldLogin);
				}else{
					BLogin record = new BLogin();
					record.setDevice(info.getDevice());
					record.setLogintime(new Date());
					record.setUserid(Long.valueOf(account.getId()));
					record.setLoginname(account.getLoginName());
					record.setLoginip(request.getRemoteHost());
					record.setStatus(1);
					bAccountService.insert(record);
					System.out.println(record);
				}
				return vo;
			} else {
				vo.setCode(Result.FAUL);
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！" + e.getMessage();
			model.addAttribute("message", msg);
			vo.setCode(Result.FAUL);
			vo.setMessage(msg);
		}
		return vo;
	}

	@RequestMapping(value = "/sendSmsCode")
	public @ResponseBody ResultVo doSendSMS(HttpServletRequest request, String phoneNo) {
		SmsCodeVo smsCodeVO = new SmsCodeVo();
		String code = RandomUtils.createRandomVcode();
		smsCodeVO.setSendTime(new Date().getTime());
		smsCodeVO.setSmsCode(code);
		request.getSession().setAttribute("smscode", smsCodeVO);
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(code);
		return vo;
	}

	@RequestMapping(value = "/logut")
	public String doLogut(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		BLogin oldLogin = bAccountService.getByAccountID(Long.valueOf(account.getId()));
		if (oldLogin != null) {
			oldLogin.setLoginouttime(new Date());
			oldLogin.setStatus(-1);
			bAccountService.update(oldLogin);
		}
		subject.logout();
		return "redirect:/index.jsp";
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
		BAccount account = bAccountService.getByUsernameOrPhone(register.getLoginName());
		if (account == null) {
			vo.setCode(Result.FAUL);
			vo.setMessage("用户名或手机号不存在");
			return vo;
		}
		account.setLoginPassword(register.getLoginPasswd());
		bAccountService.update(account);
		vo.setCode(Result.SUCESSFUL);
		return vo;

	}
}
