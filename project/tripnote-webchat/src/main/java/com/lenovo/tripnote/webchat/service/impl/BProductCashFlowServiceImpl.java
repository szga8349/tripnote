package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.BProductCashflowExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowTotalVo;
import com.lenovo.tripnote.webchat.mapper.BProductCashflowMapper;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
@Service()
public class BProductCashFlowServiceImpl implements BProductCashFlowService{
    @Resource
    private BProductCashflowMapper bProductCashflowMapper;

	@Override
	public int insert(BProductCashflow t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BProductCashflow update(BProductCashflow t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BProductCashflow getByKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultPageInfo searchShareProfit(BProductCashFlowSearchVo searchVo) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(searchVo.getPageNum());
		pageInfo.setPageSize(searchVo.getPageSize());
		Page<BProductCashFlowResultVo> page = PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<BProductCashFlowResultVo> list =  this.bProductCashflowMapper.searchBProductCashflow(searchVo);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

	@Override
	public ResultPageInfo searchProductProfit(BProductCashFlowSearchVo searchVo) {
	
		return searchShareProfit(searchVo);
	}

	@Override
	public Float searchBalance(Integer userId) {
		
		return bProductCashflowMapper.searchBalance(userId);
	}

	@Override
	public ResultPageInfo searchCashFlow(BProductCashFlowSearchVo searchVo) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(searchVo.getPageNum());
		pageInfo.setPageSize(searchVo.getPageSize());
		BProductCashflowExample example = new BProductCashflowExample();
		example.createCriteria().andFlowUserIdEqualTo(searchVo.getUserId());
		int offset =  (searchVo.getPageNum()-1)*searchVo.getPageSize();
		RowBounds rowBound = new RowBounds(offset,searchVo.getPageSize());
		int count = this.bProductCashflowMapper.countByExample(example);
		pageInfo.setTotal(Long.valueOf(count));
		if(count>0)//当查询总数大于0时 再去查询数据
			pageInfo.setData(this.bProductCashflowMapper.selectByExampleAndPage(example, rowBound));
		return pageInfo;
	}

	@Override
	public BProductCashFlowTotalVo searchTotal(BProductCashFlowSearchVo searchVo) {

		return bProductCashflowMapper.searchTotal(searchVo);
	}
	
}
