package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProductShare;
import com.lenovo.tripnote.webchat.entity.vo.BProductShareResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductShareSearchVo;
import com.lenovo.tripnote.webchat.mapper.BProductShareMapper;
import com.lenovo.tripnote.webchat.service.BProductShareService;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

@Service
public class BProductShareServiceImpl implements BProductShareService{
    @Resource
    private BProductShareMapper bProductShareMapper;
	@Override
	public int insert(BProductShare t) {
		// TODO Auto-generated method stub
		return bProductShareMapper.insertSelective(t);
	}

	@Override
	public BProductShare update(BProductShare t) {
		bProductShareMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductShare getByKey(Integer id) {
		// TODO Auto-generated method stub
		return bProductShareMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return bProductShareMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ResultPageInfo search(BProductShareSearchVo search) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(search.getPageNum());
		pageInfo.setPageSize(search.getPageSize());
		Page<BProductShareResultVo> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		List<BProductShareResultVo> list =  this.bProductShareMapper.search(search);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

}
