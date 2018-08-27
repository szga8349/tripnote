package com.lenovo.tripnote.webchat.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.BProductCollage;
import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageFinishVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollagedPartakeVo;
import com.lenovo.tripnote.webchat.mapper.BAccountMapper;
import com.lenovo.tripnote.webchat.mapper.BProductCashflowMapper;
import com.lenovo.tripnote.webchat.mapper.BProductCollageMapper;
import com.lenovo.tripnote.webchat.mapper.BProductMapper;
import com.lenovo.tripnote.webchat.mapper.BProductOrderCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.service.BProductOrderCollageService;
import com.lenovo.tripnote.webchat.vo.ProfitTypeVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class BProductOrderCollageServiceImpl implements BProductOrderCollageService{
    @Resource
    private BProductOrderCollageMapper bProductOrderCollageMapper;
    @Resource
    private BAccountMapper bAccountMapper;
    @Resource
    private BProductCollageMapper bProductCollageMapper;
    
    @Resource
    private BProductCashflowMapper bProductCashflowMapper;
    @Resource
    private BProductMapper bProductMapper;
    @Resource
    private BProductCashFlowService bProductCashFlowService;
    
	@Override
	@Transactional
	public int insert(BProductOrderCollage t) {
		//后台根据产品的拼团类型ID获取产品折扣价 作为订单的价格
		BProductCollage product = bProductCollageMapper.selectByPrimaryKey(t.getProductCollageId());
	    t.setPrice(product.getCollagePrice());
		return bProductOrderCollageMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductOrderCollage update(BProductOrderCollage t) {
		this.bProductOrderCollageMapper.updateByPrimaryKeySelective(t);
		//设置支付状态为完成时 增加流水信息
	    if(t.getPayStatus()==2){//成功支付
			t = this.bProductOrderCollageMapper.selectByPrimaryKey(t.getId());
	    	BProductCashflow record = new BProductCashflow();
			Date date = new Date();
			record.setFlowTime(date);
			//查询产品设置价格
			BProduct product = bProductMapper.selectByPrimaryKey(t.getProductId());
			if(product==null)
				throw new RuntimeException("产品ID:["+t.getProductId()+"]的产品已经不存在请联系管理人员");
			record.setFlowUserId(product.getCreateUserId());
			record.setFlowUserName(product.getCreateUserName());
			record.setMoney(product.getRawPrice());
			record.setProductId(product.getId());
			record.setMoney(t.getPrice());
			record.setProductId(t.getProductId());
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
	public BProductOrderCollage getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderCollageMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderCollageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertCollage(BProductOrderCollagedPartakeVo vo, TokenVo token) {
	
		BProductOrderCollage old = bProductOrderCollageMapper.selectByPrimaryKey(vo.getOrderCollageId());
		
		List<BProductOrderCollageDetailVo> oldUsers = listUser(vo.getOrderCollageId());
		//根据团购类型ID查询团购类型信息
		BProductCollage collage = bProductCollageMapper.selectByPrimaryKey(old.getProductCollageId());
		if(collage.getPeopleNum()<oldUsers.size()+1){//参团已满
		    return -1;
		}
		BProductOrderCollage bProduct = new BProductOrderCollage();
		try {
			BeanUtils.copyProperties(bProduct, old);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setDeposit(vo.getDeposit());
		//把创建者作为拼团人
		bProduct.setCollageUserId(token.getUserId());
		bProduct.setCollageUserName(token.getUserLoginName());
		bProduct.setCollageId(old.getId());
		bProduct.setCreateTime(new Date());
		bProductOrderCollageMapper.insert(bProduct);
		return bProduct.getId();
	}

	@Override
	public List<BProductOrderCollageDetailVo> listUser(Integer valueOf) {
		
		return bProductOrderCollageMapper.listUser(valueOf);
	}

	@Override
	public List<BProductOrderCollageDetailVo> listCollage(Integer valueOf) {
	
		return bProductOrderCollageMapper.listCollage(valueOf);
	}

	@Override
	@Transactional
	public List<BProductOrderCollageFinishVo> updateFinish(Integer valueOf) {
		 //设置拼团状态为结束
		 BProductOrderCollage record = new BProductOrderCollage();
		 record.setId(valueOf);
		 record.setCollageStatus(1);
		 bProductOrderCollageMapper.updateByPrimaryKeySelective(record);
		 BProductOrderCollageFinishVo finish = new BProductOrderCollageFinishVo();
		 List<BProductOrderCollageFinishVo> rlist = new ArrayList<BProductOrderCollageFinishVo>();
		 //设置拼团下的拼单为结束
		 List<BProductOrderCollageDetailVo> list = listUser(valueOf);
		 for(BProductOrderCollageDetailVo vo:list){
			 record = new BProductOrderCollage();
			 record.setId(vo.getId());
			 record.setCollageStatus(1);
			 bProductOrderCollageMapper.updateByPrimaryKeySelective(record);
			 finish = new BProductOrderCollageFinishVo();
			 finish.setFormId(vo.getFormId());
			 finish.setLoginToken(vo.getLoginToken());
			 finish.setId(vo.getId());
			 rlist.add(finish);
		 }
		return rlist;
	}

	@Override
	public List<BProductOrderCollageFinishVo> searchFormIds(Integer valueOf) {
		bProductOrderCollageMapper.updateFinishStatus(valueOf);
		return bProductOrderCollageMapper.searchFormIds(valueOf);
	}

}
