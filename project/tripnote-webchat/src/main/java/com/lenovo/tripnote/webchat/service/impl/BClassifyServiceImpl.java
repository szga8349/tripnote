package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BClassify;
import com.lenovo.tripnote.webchat.entity.BClassifyExample;
import com.lenovo.tripnote.webchat.mapper.BClassifyMapper;
import com.lenovo.tripnote.webchat.service.BClassifyService;
@Service()
public class BClassifyServiceImpl implements BClassifyService{
    @Resource
    private BClassifyMapper bClassifyMapper;
	@Override
	public int insert(BClassify t) {
		bClassifyMapper.insertSelective(t);
		return t.getId();
	}

	@Override
	public BClassify update(BClassify t) {
		bClassifyMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BClassify getByKey(Integer id) {

		return this.bClassifyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bClassifyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BClassify> searchBClassify(Integer parentId) {
		BClassifyExample example = new BClassifyExample();
		if(parentId!=null){
			example.createCriteria().andParentClassifyIdEqualTo(parentId);
		}
		example.setOrderByClause(" id asc");
		return bClassifyMapper.selectByExample(example);
	}

}
