package com.lenovo.tripnote.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripNoteMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleOfferMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripnoteScheduleOffer;
import com.lenovo.tripnote.entity.vo.TCustemVo;
import com.lenovo.tripnote.entity.vo.TTravelVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferSearchVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferVo;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.service.TTripnoteScheduleOfferService;
@Service
public class TTripnoteScheduleOfferServiceImpl implements TTripnoteScheduleOfferService {
	@Resource
	private TTripnoteScheduleOfferMapper tTripnoteScheduleOfferMapper;
	@Resource
	private TTripNoteMapper tTripNoteMapper;
	@Resource
	private TCustomerService tCustomerService;
	@Override
	public int insert(TTripnoteScheduleOffer t) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleOfferMapper.insertSelective(t);
	}

	@Override
	public TTripnoteScheduleOffer update(TTripnoteScheduleOffer t) {
		tTripnoteScheduleOfferMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public TTripnoteScheduleOffer getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.tTripnoteScheduleOfferMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.tTripnoteScheduleOfferMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleOffer t) {

		return 0;
	}

	@Override
	public List<Integer> batchAdd(TTravelVo vo, BAccount account) {
		if (vo.getTotalPrice() != null) {// 更新定制的客户报价
			TTripNote record = new TTripNote();
			record.setId(vo.getTripnoteId());
			record.setPrice(vo.getTotalPrice());
			tTripNoteMapper.updateByPrimaryKeySelective(record);
		}
		List<Integer> returnList = new ArrayList<>();
		if(vo.getScheduleOffers()!=null){
			for(TTripnoteScheduleOfferVo offervo : vo.getScheduleOffers()){
				TTripnoteScheduleOffer offer = new TTripnoteScheduleOffer();
				try {
					BeanUtils.copyProperties(offer, offervo);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				offer.setCreateUserId(account.getId());
				offer.setTripnoteId(vo.getTripnoteId());
				if(offer.getId()!=null){//返回的值不为空 直接修改
					tTripnoteScheduleOfferMapper.updateByPrimaryKeySelective(offer);
				}else{
					tTripnoteScheduleOfferMapper.insert(offer);
				}
				returnList.add(offer.getId());
			}
		}
		return returnList;
	}

	@Override
	public List<TTripnoteScheduleOfferResultVo> search(TTripnoteScheduleOfferSearchVo offer) {	
		return this.tTripnoteScheduleOfferMapper.search(offer);
	}

	@Override
	public Integer addCustem(TCustemVo vo, BAccount account) {
		TCustomer search = new TCustomer();
		search.setName(vo.getName());
		search.setCreateUserId(account.getId());
		search.setPhone1(vo.getPhone());
		List<TCustomer> oldCustomer = tCustomerService.search(search, account, true);
		if(oldCustomer!=null && !oldCustomer.isEmpty()){//原有客户存在
			//设置定制与客户的关联关系
			search.setId(oldCustomer.get(0).getId());
		}else{
			//如果不存在该客户 创建该客户并关联到当前登录人上
			search.setStatus(1);
			tCustomerService.insert(search);
		}
		tCustomerService.insertTripnoteRCustomer(vo.getId(),search.getId());
		return search.getId();
	}

	@Override
	public int deleteTripnoteRCustomer(Integer tripnoteId, Integer customerId) {
		return tCustomerService.deleteTripnoteRCustomer(tripnoteId, customerId);
	}

}
