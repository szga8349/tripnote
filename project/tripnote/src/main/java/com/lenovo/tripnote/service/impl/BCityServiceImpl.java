package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BCityMapper;
import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.BCityExample;
import com.lenovo.tripnote.entity.BCityExample.Criteria;
import com.lenovo.tripnote.entity.vo.BCityVo;
import com.lenovo.tripnote.service.BCityService;
@Service
public class BCityServiceImpl implements BCityService{
    @Resource
    private BCityMapper bCityMapper;
	@Override
	public int insert(BCity t) {
		// TODO Auto-generated method stub
		return bCityMapper.insert(t);
	}

	@Override
	public BCity update(BCity t) {
		this.bCityMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BCity getByKey(Integer id) {

		return this.bCityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bCityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BCity t) {
		return 0;
	}

	@Override
	public List<BCity> select(BCityVo t) {
		return this.bCityMapper.selectByExample(getBCitySearchCondition(t));
	}
	/**获取搜索条件对象
	 * @param t
	 * @return
	 */
	private BCityExample getBCitySearchCondition (BCityVo t){
		BCityExample example = new BCityExample();
	     Criteria criteria  = example.createCriteria();
		if(t.getRegionId()!=null){
			criteria.andRegionIdEqualTo(t.getRegionId());
		}
		if(t.getCountryId()!=null){
			criteria.andCountryIdEqualTo(t.getCountryId());
		}
		if(t.getIsHot()!=null){
			criteria.andIsHotEqualTo(t.getIsHot());
		}
		if(t.getNameCn()!=null){
			criteria.andNameCnLike(t.getNameCn());
		}
		if(t.getNameEn()!=null){
			criteria.andNameCnLike(t.getNameEn());
		}
		return example;
	}

	@Override
	public List<BCity> selectAndPage(BCityVo t, RowBounds rowBound) {

		return this.bCityMapper.selectByExampleAndPage(getBCitySearchCondition(t), rowBound);
	}

}
