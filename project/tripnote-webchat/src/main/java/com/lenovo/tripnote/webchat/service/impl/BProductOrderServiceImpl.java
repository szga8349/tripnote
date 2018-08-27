package com.lenovo.tripnote.webchat.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.vo.BProductCustomerOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.mapper.BProductCashflowMapper;
import com.lenovo.tripnote.webchat.mapper.BProductMapper;
import com.lenovo.tripnote.webchat.mapper.BProductOrderMapper;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.service.BProductOrderService;
import com.lenovo.tripnote.webchat.vo.ProfitTypeVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
@Service
public class BProductOrderServiceImpl implements BProductOrderService{
    @Resource
    private BProductOrderMapper bProductOrderMapper;
    @Resource
    private BProductCashflowMapper bProductCashflowMapper;
    @Resource
    private BProductMapper bProductMapper;
    @Resource
    private BProductCashFlowService bProductCashFlowService;
	@Override
	@Transactional
	public int insert(BProductOrder t) {
		//后台根据产品ID获取产品折扣价 作为订单的价格
		BProduct product = bProductMapper.selectByPrimaryKey(t.getProductId());
		t.setPrice(product.getDiscountPrice());
		return bProductOrderMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductOrder update(BProductOrder t) {
		this.bProductOrderMapper.updateByPrimaryKeySelective(t);
		//设置支付状态为完成时 增加流水信息
		if(t.getStatus()==1){//成功支付
			t = this.bProductOrderMapper.selectByPrimaryKey(t.getId());
			BProductCashflow record = new BProductCashflow();
			Date date = new Date();
			record.setFlowTime(date);
			//查询产品设置价格
			BProduct product = bProductMapper.selectByPrimaryKey(t.getProductId());
			if(product==null)
				throw new RuntimeException("产品ID:["+t.getProductId()+"]的产品已经不存在请联系管理人员");
			record.setFlowUserId(product.getCreateUserId());
			record.setFlowUserName(product.getCreateUserName());
			record.setMoney(t.getPrice());
			record.setProductId(product.getId());
			//设置流水号
			record.setFlowCode(bProductCashFlowService.generationNumber(date));
			//设置入账
			record.setType(1);
			//设置订单收益
			record.setProfitType(ProfitTypeVo.ORDER.index());
			bProductCashflowMapper.insertSelective(record);
		}
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
		//Page<BProductCustomerOrderResultVo> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		Long total = bProductOrderMapper.searchCustomerTotal(search);
		List<BProductCustomerOrderResultVo> list = bProductOrderMapper.searchCustomer(search);
		pageInfo.setTotal(total);
		pageInfo.setData(list);
		return pageInfo;
	}

	@Override
	public ResultPageInfo listByProduct(BProductOrderSearchVo search) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(search.getPageNum());
		pageInfo.setPageSize(search.getPageSize());
		Page<BProductOrderDetailVo> page = PageHelper.startPage(search.getPageNum(), search.getPageSize());
		List<BProductOrderDetailVo> list = bProductOrderMapper.listByProduct(search);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

}
