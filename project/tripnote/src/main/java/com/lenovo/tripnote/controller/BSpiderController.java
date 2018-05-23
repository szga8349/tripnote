package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.vo.BHotelSearchVo;
import com.lenovo.tripnote.entity.vo.BHotelVo;
import com.lenovo.tripnote.entity.vo.BPoiSearchVo;
import com.lenovo.tripnote.entity.vo.BPoiVo;
import com.lenovo.tripnote.entity.vo.PageResultVo;
import com.lenovo.tripnote.service.BHotelService;
import com.lenovo.tripnote.service.BPoiService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/spider")
public class BSpiderController {
	@Resource
	private BHotelService bHotelService;
	@Resource
	private BPoiService bPoiService;
	@RequestMapping(value = "/hotel/doSearch")
	public @ResponseBody ResultVo searchHotel(BHotelSearchVo search) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		Integer count = bHotelService.searchHotelCount(search);
		List<BHotel> result = bHotelService.searchHotels(search);
		PageResultVo pageResultVo = new PageResultVo();
		pageResultVo.setData(result);
		pageResultVo.setTotal(count);
		pageResultVo.setPageNo(search.getPageNo());
		pageResultVo.setPageSize(search.getPageSize());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(pageResultVo);
		return vo;
	}
	@RequestMapping(value = "/poi/doSearch")
	public @ResponseBody ResultVo searchPoi(BPoiSearchVo bpoiSearch) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		//设置用户ID
		if(bpoiSearch.getName()!=null){
			bpoiSearch.setName("%"+bpoiSearch.getName()+"%");
		}
		Integer offset = (bpoiSearch.getPageNo()-1<0?0:(bpoiSearch.getPageNo()-1))*bpoiSearch.getPageSize();
		RowBounds rowBounds = new RowBounds(offset,bpoiSearch.getPageSize());
		
		PageResultVo result = new PageResultVo();
		try {
			BeanUtils.copyProperties(result, bpoiSearch);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		result.setTotal(this.bPoiService.searchCount(bpoiSearch));
		result.setData(this.bPoiService.selectAndPage(bpoiSearch,rowBounds));
		vo.setData(result);
		return vo;
	}
	
	@RequestMapping(value = "/hotel/doUpdate/{id}")
	public @ResponseBody ResultVo updateHotel(@PathVariable String id,BHotelVo tripnoteScheduleVo) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		BHotel t = new BHotel();
		BeanUtils.copyProperties(t, tripnoteScheduleVo);
		t.setId(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		bHotelService.update(t);
		return vo;
	}
	@RequestMapping(value = "/poi/doUpdate/{id}")
	public @ResponseBody ResultVo updatePoi(BPoiVo bpoiVo, @PathVariable String id) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BPoi t = new BPoi();
		BeanUtils.copyProperties(t, bpoiVo);
		t.setId(Integer.valueOf(id));
		bPoiService.update(t);
		return vo;
	}

}
