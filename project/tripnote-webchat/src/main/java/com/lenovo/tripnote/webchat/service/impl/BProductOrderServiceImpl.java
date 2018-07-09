package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.BProductOrderExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductCustomerOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.mapper.BProductOrderMapper;
import com.lenovo.tripnote.webchat.service.BProductOrderService;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
@Service
public class BProductOrderServiceImpl implements BProductOrderService{
    @Resource
    private BProductOrderMapper bProductOrderMapper;
	@Override
	@Transactional
	public int insert(BProductOrder t) {
		return bProductOrderMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductOrder update(BProductOrder t) {
		this.bProductOrderMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductOrder getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ResultPageInfo search(BProductOrderSearchVo search) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(search.getPageNum());
		pageInfo.setPageSize(search.getPageSize());
		Page<BProductOrderResultVo> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		List<BProductOrderResultVo> list = bProductOrderMapper.search(search);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

	@Override
	public ResultPageInfo searchCustomer(BProductOrderSearchVo search) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(search.getPageNum());
		pageInfo.setPageSize(search.getPageSize());
		Page<BProductCustomerOrderResultVo> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		List<BProductCustomerOrderResultVo> list = bProductOrderMapper.searchCustomer(search);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

	@Override
	public ResultPageInfo listByProduct(BProductOrderSearchVo search) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(search.getPageNum());
		pageInfo.setPageSize(search.getPageSize());
		Page<BProductOrder> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		BProductOrderExample example = new BProductOrderExample();
		example.createCriteria().andProductIdEqualTo(search.getProductId());
		List<BProductOrder> list = bProductOrderMapper.selectByExample(example);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

}
