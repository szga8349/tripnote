package com.lenovo.tripnote.webchat.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageDetailVo;
import com.lenovo.tripnote.webchat.mapper.BProductOrderCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductOrderCollageService;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class BProductOrderCollageServiceImpl implements BProductOrderCollageService{
    @Resource
    private BProductOrderCollageMapper bProductOrderCollageMapper;
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
	@Transactional
	public int insertCollage(Integer id, TokenVo token) {
		BProductOrderCollage old = bProductOrderCollageMapper.selectByPrimaryKey(Integer.valueOf(id));
		BProductOrderCollage bProduct = new BProductOrderCollage();
		try {
			BeanUtils.copyProperties(bProduct, old);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		//把创建者作为拼团人
		bProduct.setCollageUserId(token.getUserId());
		bProduct.setCollageUserName(token.getUserLoginName());
		bProduct.setCollageId(old.getCollageId());
		bProduct.setCreateTime(new Date());
		return 0;
	}

	@Override
	public List<BProductOrderCollageDetailVo> listUser(Integer valueOf) {
		
		return bProductOrderCollageMapper.listUser(valueOf);
	}

	@Override
	public List<BProductOrderCollageDetailVo> listCollage(Integer valueOf) {
	
		return bProductOrderCollageMapper.listCollage(valueOf);
	}

}
