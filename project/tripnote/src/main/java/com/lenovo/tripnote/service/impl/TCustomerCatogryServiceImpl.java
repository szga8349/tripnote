package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TCustomerCatogryMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomerCatogry;
import com.lenovo.tripnote.entity.TCustomerCatogryExample;
import com.lenovo.tripnote.entity.TCustomerCatogryExample.Criteria;
import com.lenovo.tripnote.entity.vo.TCustomerCatogryVo;
import com.lenovo.tripnote.service.TCustomerCatogryService;
@Service
public class TCustomerCatogryServiceImpl implements TCustomerCatogryService{
	@Resource
	private TCustomerCatogryMapper tCustomerCatogryMapper;

	@Override
	public int insert(TCustomerCatogry t) {
		// TODO Auto-generated method stub
		return tCustomerCatogryMapper.insert(t);
	}

	@Override
	public TCustomerCatogry update(TCustomerCatogry t) {
		tCustomerCatogryMapper.updateByPrimaryKeySelective(t);
		return null;
	}

	@Override
	public TCustomerCatogry getByKey(Integer id) {
		
		return this.tCustomerCatogryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.tCustomerCatogryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TCustomerCatogry t) {
		TCustomerCatogryExample example = new TCustomerCatogryExample();
		Criteria cri = example.createCriteria();
		cri.andIdEqualTo(t.getId());
		cri.andCreateUserIdEqualTo(t.getCreateUserId());
		return tCustomerCatogryMapper.deleteByExample(example);
	}
	@Override
	public List<TCustomerCatogry> search(TCustomerCatogryVo t,BAccount account,boolean isexsit) {
		Integer offset = (t.getPageNo()-1<0?0:(t.getPageNo()-1))*t.getPageSize();
		RowBounds rowBound = new RowBounds(offset, t.getPageSize());
		TCustomerCatogryExample example = new TCustomerCatogryExample();
		Criteria cri = example.createCriteria();
		//设置查询当前用户的客户组
		cri.andCreateUserIdEqualTo(account.getId());
		if(t.getName()!=null && !isexsit){//搜索的情况下 使用like
			cri.andNameLike("%"+t.getName()+"%");
		}else if(t.getName()!=null && isexsit){//判断名称是否存在的情况下 只能使用相等进行查询
			cri.andNameEqualTo(t.getName());
		}
		if(t.getId()!=null){//修改时候判断 名称不重复时调用
			cri.andIdNotEqualTo(t.getId());
		}
		return this.tCustomerCatogryMapper.selectByExampleAndPage(example, rowBound);
	}
}
