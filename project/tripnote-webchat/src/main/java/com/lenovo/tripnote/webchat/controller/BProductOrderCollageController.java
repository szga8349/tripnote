package com.lenovo.tripnote.webchat.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollagedPartakeVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderVo;
import com.lenovo.tripnote.webchat.service.BProductOrderCollageService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequestMapping(value = "/token/product/order/collage")
public class BProductOrderCollageController {
	@Resource
	private BProductOrderCollageService bProductOrderCollageService;

	/**创建一个拼团
	 * @param request
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BProductOrderCollageVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BProductOrderCollage bProduct = new BProductOrderCollage();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		//把创建者作为拼团人
		bProduct.setCollageUserId(token.getUserId());
		bProduct.setCollageUserName(token.getUserLoginName());
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateUserName(token.getUserLoginName());
		bProduct.setCreateTime(new Date());
		bProduct.setCollageId(0);//默认创建拼团的主键值为0
		vo.setCode(Result.SUCESSFUL);
		bProductOrderCollageService.insert(bProduct);
		vo.setData(bProduct.getId());
		return vo;
	}

	/**参加拼团
	 * @param request
	 * @param id:参与拼团的记录ID
	 * @return
	 */
	@RequestMapping(value = "/{id}/doAdd")
	@ResponseBody
	public ResultVo doParticipateAdd(HttpServletRequest request,@PathVariable String id,@RequestBody(required=false) BProductOrderCollagedPartakeVo partakVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		vo.setCode(Result.SUCESSFUL);
		if(partakVo==null){
			partakVo = new BProductOrderCollagedPartakeVo();
		}
		partakVo.setOrderCollageId(Integer.valueOf(id));
		vo.setData(bProductOrderCollageService.insertCollage(partakVo,token));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BProductOrderVo info) {
		ResultVo vo = new ResultVo();
		BProductOrderCollage bProduct = new BProductOrderCollage();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bProductOrderCollageService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bProductOrderCollageService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	
	@RequestMapping(value = "/doListUser/{id}")
	@ResponseBody
	public ResultVo doDetail(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setData(bProductOrderCollageService.listUser(Integer.valueOf(id)));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	
	/**一键拼团成功
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doFinish/{id}")
	@ResponseBody
	public ResultVo doFinishOrderCollage(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setData(bProductOrderCollageService.updateFinish(Integer.valueOf(id)));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	
	@RequestMapping(value = "/doFormId/{id}")
	@ResponseBody
	public ResultVo doFormIds(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setData(bProductOrderCollageService.searchFormIds(Integer.valueOf(id)));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}

}
