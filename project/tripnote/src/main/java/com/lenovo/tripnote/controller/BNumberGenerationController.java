package com.lenovo.tripnote.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.dao.BNumberGenerationMapper;
import com.lenovo.tripnote.entity.vo.BRentSearchVo;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

@Controller
@RequestMapping(value = "/user/number")
public class BNumberGenerationController {
	@Resource
	private BNumberGenerationMapper mapper;
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BRentSearchVo bpoiSearch) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		int max = mapper.selectMaxNumber(new Date());
		vo.setData(max);
		return vo;
	}
}
