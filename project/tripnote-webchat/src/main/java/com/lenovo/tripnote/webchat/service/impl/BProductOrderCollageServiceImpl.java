package com.lenovo.tripnote.webchat.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductCollage;
import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageFinishVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollagedPartakeVo;
import com.lenovo.tripnote.webchat.mapper.BAccountMapper;
import com.lenovo.tripnote.webchat.mapper.BProductCollageMapper;
import com.lenovo.tripnote.webchat.mapper.BProductOrderCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductOrderCollageService;
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
    
	@Override
	@Transactional
	public int insert(BProductOrderCollage t) {
		return bProductOrderCollageMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductOrderCollage update(BProductOrderCollage t) {
		this.bProductOrderCollageMapper.updateByPrimaryKeySelective(t);
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
		if(collage.getPeopleNum()<=oldUsers.size()+1){//参团已满
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
		
		return bProductOrderCollageMapper.searchFormIds(valueOf);
	}

}
