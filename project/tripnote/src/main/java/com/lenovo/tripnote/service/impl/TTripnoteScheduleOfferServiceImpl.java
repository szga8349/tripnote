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
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripnoteScheduleOffer;
import com.lenovo.tripnote.entity.TTripnoteScheduleOfferExample;
import com.lenovo.tripnote.entity.vo.TTravelVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferVo;
import com.lenovo.tripnote.service.TTripnoteScheduleOfferService;
@Service
public class TTripnoteScheduleOfferServiceImpl implements TTripnoteScheduleOfferService {
	@Resource
	private TTripnoteScheduleOfferMapper tTripnoteScheduleOfferMapper;
	@Resource
	private TTripNoteMapper tTripNoteMapper;

	@Override
	public int insert(TTripnoteScheduleOffer t) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleOfferMapper.insert(t);
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
	public List<TTripnoteScheduleOffer> search(TTripnoteScheduleOfferExample offer) {	
		return this.tTripnoteScheduleOfferMapper.selectByExample(offer);
	}

}
