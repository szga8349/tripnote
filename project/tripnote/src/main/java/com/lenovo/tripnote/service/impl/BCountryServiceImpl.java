package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BCountryMapper;
import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.BCountryExample;
import com.lenovo.tripnote.entity.vo.BCountryVo;
import com.lenovo.tripnote.service.BCountryService;

@Service
public class BCountryServiceImpl implements BCountryService{
    @Resource
    private BCountryMapper bCountryMapper;
	@Override
	public int insert(BCountry t) {
		
		return bCountryMapper.insert(t);
	}

	@Override
	public BCountry update(BCountry t) {
		this.bCountryMapper.updateByPrimaryKey(t);
		return t;
	}

	@Override
	public BCountry getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bCountryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bCountryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BCountry t) {
		
		return 0;
	}

	@Override
	public List<BCountry> select(BCountryVo t) {
		BCountryExample example = new BCountryExample();
		if(t.getRegionId()!=null){
			example.createCriteria().andRegionIdEqualTo(t.getRegionId().toString());
		}
		if(t.getNameCn()!=null){
			example.createCriteria().andNameCnLike(t.getNameCn());
		}
		if(t.getNameEn()!=null){
			example.createCriteria().andNameCnLike(t.getNameEn());
		}
		return this.bCountryMapper.selectByExample(example );
	}

	@Override
	public List<BCountry> selectAndPage(BCountryVo t) {
		// TODO Auto-generated method stub
		return null;
	}
}
