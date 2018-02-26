package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BPoiMapper;
import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.vo.BPoiResultVo;
import com.lenovo.tripnote.entity.vo.BPoiSearchVo;
import com.lenovo.tripnote.service.BPoiService;
@Service
public class BPoiServiceImpl implements BPoiService{
	@Resource
	private BPoiMapper bPoiMapper;
	

	@Override
	public int insert(BPoi t) {
		// TODO Auto-generated method stub
		return bPoiMapper.insert(t);
	}

	@Override
	public BPoi update(BPoi t) {
		 bPoiMapper.updateByPrimaryKeySelective(t);
		 return t;
	}

	@Override
	public BPoi getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bPoiMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bPoiMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BPoi t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BPoiResultVo> selectAndPage(BPoiSearchVo bpoiSearch, RowBounds rowBounds) {
		
		return this.bPoiMapper.selectAndPage(bpoiSearch,rowBounds);
	}

	@Override
	public Integer searchCount(BPoiSearchVo bpoiSearch) {
		// TODO Auto-generated method stub
		return this.bPoiMapper.searchCount(bpoiSearch);
	}

}
