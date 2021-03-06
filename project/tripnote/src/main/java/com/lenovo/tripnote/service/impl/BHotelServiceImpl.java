package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BHotelMapper;
import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BHotelExample;
import com.lenovo.tripnote.entity.BHotelExample.Criteria;
import com.lenovo.tripnote.entity.vo.BHotelSearchVo;
import com.lenovo.tripnote.service.BHotelService;

@Service
public class BHotelServiceImpl implements BHotelService {
	@Resource
	private BHotelMapper bHotelMapper;

	@Override
	public int insert(BHotel t) {
		// TODO Auto-generated method stub
		return bHotelMapper.insertSelective(t);
	}

	@Override
	public BHotel update(BHotel t) {
		bHotelMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BHotel getByKey(Integer id) {
		// TODO Auto-generated method stub
		return bHotelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return bHotelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BHotel t) {
		BHotelExample example = new BHotelExample();
		Criteria cri = example.createCriteria();
		cri.andIdEqualTo(t.getId());
		cri.andCreateUserIdEqualTo(t.getCreateUserId());
		return bHotelMapper.deleteByExample(example);
	}

	@Override
	public List<BHotel> searchHotels(BHotelSearchVo vo) {
		BHotelExample example = new BHotelExample();
		Criteria c = example.createCriteria();
		if (vo.getNameCn() != null) {
			c.andNameCnLike("%" + vo.getNameCn() + "%");
		}
		if (vo.getCityId() != null) {
			c.andCityIdEqualTo(vo.getCityId());
		}
		if (vo.getSupplier() != null) {
			c.andSupplierEqualTo(vo.getSupplier());
		}
		Integer offset = (vo.getPageNo() - 1 < 0 ? 0 : (vo.getPageNo() - 1)) * vo.getPageSize();
		RowBounds rowRounds = new RowBounds(offset, vo.getPageSize());
		return bHotelMapper.selectByExampleAndPage(example, rowRounds);
	}
	
	@Override
	public Integer searchHotelCount(BHotelSearchVo vo) {
		BHotelExample example = new BHotelExample();
		Criteria c = example.createCriteria();
		if (vo.getNameCn() != null) {
			c.andNameCnLike("%" + vo.getNameCn() + "%");
		}
		if (vo.getCityId() != null) {
			c.andCityIdEqualTo(vo.getCityId());
		}
		if (vo.getSupplier() != null) {
			c.andSupplierEqualTo(vo.getSupplier());
		}
		return bHotelMapper.countByExample(example);
	}

}
