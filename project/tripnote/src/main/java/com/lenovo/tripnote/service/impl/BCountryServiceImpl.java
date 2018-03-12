package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BCountryMapper;
import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.BCountryExample;
import com.lenovo.tripnote.entity.BCountryExample.Criteria;
import com.lenovo.tripnote.entity.vo.BCountryVo;
import com.lenovo.tripnote.service.BCountryService;

@Service
public class BCountryServiceImpl implements BCountryService {
	@Resource
	private BCountryMapper bCountryMapper;

	@Override
	public int insert(BCountry t) {

		return bCountryMapper.insertSelective(t);
	}

	@Override
	public BCountry update(BCountry t) {
		this.bCountryMapper.updateByPrimaryKeySelective(t);
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

	/**获取搜索城市的条件
	 * @param t
	 * @return
	 */
	private BCountryExample getSearchCondition(BCountryVo t) {
		BCountryExample example = new BCountryExample();
		Criteria criteria = example.createCriteria();
		if (t.getRegionId() != null) {
			criteria.andRegionIdEqualTo(t.getRegionId());
		}
		if (t.getNameCn() != null) {
			criteria.andNameCnLike("%"+t.getNameCn()+"%");
		}
		if (t.getNameEn() != null) {
			criteria.andNameCnLike("%"+t.getNameEn()+"%");
		}
		return example;
	}

	@Override
	public List<BCountry> select(BCountryVo t) {

		return this.bCountryMapper.selectByExample(getSearchCondition(t));
	}

	@Override
	public List<BCountry> selectAndPage(BCountryVo t, RowBounds rowBound) {
		return this.bCountryMapper.selectByExampleAndPage(getSearchCondition(t), rowBound);
	}
}
