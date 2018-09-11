package com.lenovo.tripnote.webchat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.BProductShare;
import com.lenovo.tripnote.webchat.entity.vo.BProductCustomerOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.mapper.BProductCashflowMapper;
import com.lenovo.tripnote.webchat.mapper.BProductMapper;
import com.lenovo.tripnote.webchat.mapper.BProductOrderMapper;
import com.lenovo.tripnote.webchat.mapper.BProductShareMapper;
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
    @Resource
    private BProductShareMapper bProductShareMapper;
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
	public BProductOrder update(BProductOrder update) {
		this.bProductOrderMapper.updateByPrimaryKeySelective(update);
		//设置支付状态为完成时 增加流水信息
		if(update.getStatus()==1){//成功支付
			BProductOrder productOrder = this.bProductOrderMapper.selectByPrimaryKey(update.getId());
			BProductCashflow record = new BProductCashflow();
			Date date = new Date();
			record.setFlowTime(date);
			//查询产品设置价格
			BProduct product = bProductMapper.selectByPrimaryKey(productOrder.getProductId());
			if(product==null)
				throw new RuntimeException("产品ID:["+productOrder.getProductId()+"]的产品已经不存在请联系管理人员");
			//设置流水归属人为 产品创建人
			record.setFlowUserId(product.getCreateUserId());
			record.setFlowUserName(product.getCreateUserName());
			record.setMoney(productOrder.getPrice());
			record.setProductId(product.getId());
			//设置流水付款人为支付钱的用户 也即是订单创建人
			record.setPayUserId(productOrder.getCreateUserId());
			//设置流水号
			record.setFlowCode(bProductCashFlowService.generationNumber(date));
			//设置入账
			record.setType(1);
			//设置订单收益
			record.setProfitType(ProfitTypeVo.ORDER.index());
			bProductCashflowMapper.insertSelective(record);
			if(productOrder.getShareId()!=-1){//有分享 设置分享收益
				List<BProductShare> shares = bProductShareMapper.selectParentListByShareID(productOrder.getShareId());
				Stream.iterate(0, i -> i + 1).limit(shares.size()).forEach(i -> {
			            BProductShare share = shares.get(i); 
			          //分享收益
						float shareMoney = 0.0f;
						//产品的10%作为佣金 
						float commission = product.getDiscountPrice()*0.1f;
						switch (i) {
						case 0:
						case 1://一二级分享的 获得10%的佣金奖励
							shareMoney = commission * 0.1f;
							break;
						case 2:
						case 3:
						case 4://3,4,5级分享的 获得5%的佣金奖励
							shareMoney = commission * 0.05f;
							break;
						case 5:
						case 6:
						case 7://6,7,8级分享的 获得4%的佣金奖励
							shareMoney = commission * 0.04f;
							break;
						case 8:
						case 9://9,10级分享的 获得1.5%的佣金奖励
							shareMoney = commission * 0.015f;
							break;
						default:
							break;
						}
						BProductCashflow shareCashFlow = new BProductCashflow();
						//设置分享收益钱
						shareCashFlow.setMoney(shareMoney);
						shareCashFlow.setProductId(share.getProductId());
						//设置分享人为收益人
						shareCashFlow.setFlowUserId(share.getShareUserId());
						shareCashFlow.setFlowUserName(share.getShareUserName());
						//设置收益类型为分享
						shareCashFlow.setProfitType(ProfitTypeVo.SHARE.index());
						//设置为入账
						shareCashFlow.setType(1);
						//设置支付人 订单创建人
						shareCashFlow.setPayUserId(productOrder.getCreateUserId());
						shareCashFlow.setFlowCode(bProductCashFlowService.generationNumber(date));
						shareCashFlow.setFlowTime(date);
						bProductCashflowMapper.insertSelective(shareCashFlow);
			        });
			}
		}
		return update;
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
