package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.vo.BPoiSearchVo;
import com.lenovo.tripnote.entity.vo.BPoiVo;
import com.lenovo.tripnote.service.BPoiService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**POI数据操作
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/poi")
public class BPoiController {
	@Resource
	private BPoiService bPoiService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo add(BPoiVo bpoiVo) throws IllegalAccessException, InvocationTargetException {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BPoi t = new BPoi();
		BeanUtils.copyProperties(t, bpoiVo);
		BAccount account = (BAccount) subject.getPrincipal();
		t.setCreateUserId(account.getId());
		bPoiService.insert(t);
		vo.setData(t.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bPoiService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	/**根据区域ID 国家ID搜索城市  如果查询条件为空 返回所有国家信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BPoiSearchVo bpoiSearch) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		//设置用户ID
		if(bpoiSearch.getName()!=null){
			bpoiSearch.setName("%"+bpoiSearch.getName()+"%");
		}
		bpoiSearch.setUserId(account.getId());
		Integer offset = (bpoiSearch.getPageNo()-1<0?0:(bpoiSearch.getPageNo()-1))*bpoiSearch.getPageSize();
		RowBounds rowBounds = new RowBounds(offset,bpoiSearch.getPageSize());
		vo.setData(this.bPoiService.selectAndPage(bpoiSearch,rowBounds));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(BPoiVo bpoiVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BPoi t = new BPoi();
		BeanUtils.copyProperties(t, bpoiVo);
		t.setId(Integer.valueOf(id));
		bPoiService.update(t);
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo getDetail(@PathVariable String id) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bPoiService.getByKey(Integer.valueOf(id)));
		return vo;
	}
}
