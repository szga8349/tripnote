package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BTrafficMapper;
import com.lenovo.tripnote.entity.BTraffic;
import com.lenovo.tripnote.entity.BTrafficExample;
import com.lenovo.tripnote.entity.vo.BTrafficSearchVo;
import com.lenovo.tripnote.service.BTrafficService;
@Service
public class BTrafficServiceImpl implements BTrafficService {
    @Resource
    private BTrafficMapper bTrafficMapper;
	@Override
	public int insert(BTraffic t) {
		// TODO Auto-generated method stub
		return bTrafficMapper.insertSelective(t);
	}

	@Override
	public BTraffic update(BTraffic t) {
		bTrafficMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BTraffic getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bTrafficMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bTrafficMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BTraffic t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer searchCount(BTrafficSearchVo vo) {
		//暂时不加条件进行全搜索
		BTrafficExample example = new BTrafficExample();
		return this.bTrafficMapper.countByExample(example);
	}

	@Override
	public List<BTraffic> selectAndPage(BTrafficSearchVo vo, RowBounds row) {
		BTrafficExample example = new BTrafficExample();
		// TODO Auto-generated method stubBTrafficExample
		return this.bTrafficMapper.selectByExampleAndPage(example, row);
	}

}
