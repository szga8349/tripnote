package com.lenovo.tripnote.webchat.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductCollage;
import com.lenovo.tripnote.webchat.entity.BProductImage;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollageVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductStatusVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductUpdateStatusVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductVo;
import com.lenovo.tripnote.webchat.mapper.BProductCollageMapper;
import com.lenovo.tripnote.webchat.mapper.BProductImageMapper;
import com.lenovo.tripnote.webchat.mapper.BProductMapper;
import com.lenovo.tripnote.webchat.mapper.BProductOrderCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.vo.BatchVo;
import com.lenovo.tripnote.webchat.vo.IDVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;

@Log4j
@Service()
public class BProductServiceImpl implements BProductService {
	@Resource
	private BProductMapper bProductMapper;
	@Resource
    private BProductImageMapper bProductImageMapper;
	@Resource
    private BProductCollageMapper bProductCollageMapper;
	@Resource
	private BProductOrderCollageMapper bProductOrderCollageMapper;
	@Override
	@Transactional
	public int insert(BProduct t) {
		return bProductMapper.insert(t);
	}
	@Transactional
	@Override
	public BProduct update(BProduct t) {
		bProductMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProduct getByKey(Integer id) {
		
		return this.bProductMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int addProduct(BProductVo vo,TokenVo token) {
		BProduct product = new BProduct();
		try {
			BeanUtils.copyProperties(product, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());
		}
		product.setCreateTime(new Date());
		product.setCreateUserId(token.getUserId());
		product.setCreateUserName(token.getUserLoginName());
		bProductMapper.insertSelective(product);
		if(vo.getImageUrls()!=null){//产品关联的图片
			for(String imageurl:vo.getImageUrls()){
				BProductImage image = new BProductImage();
				image.setUrl(imageurl);
				image.setProductId(product.getId());
				bProductImageMapper.insertSelective(image);
			}
		}
		if(vo.getProductCollages()!=null){
			for(BProductCollageVo collageVo:vo.getProductCollages()){
				BProductCollage collage = new BProductCollage();
				collage.setCollageLimitTime(collageVo.getCollageLimitTime());
				collage.setCollagePrice(collageVo.getCollagePrice());
				collage.setPeopleNum(collageVo.getPeopleNum());
				collage.setProductId(product.getId());
				bProductCollageMapper.insertSelective(collage);
			}
		}
		return product.getId();
	}

	@Override
	public ResultPageInfo searchProduct(BProductSearchVo searchVo) {
		ResultPageInfo pageInfo = new ResultPageInfo();
		pageInfo.setPageNum(searchVo.getPageNum());
		pageInfo.setPageSize(searchVo.getPageSize());
		Page<BProductResultVo> page = PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<BProductResultVo> list = bProductMapper.searchProduct(searchVo);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setData(list);
		return pageInfo;
	}

	@Override
	@Transactional
	public void deleteBatch(BatchVo batchVo) {
		if(batchVo.getIds()!=null){
			for(IDVo id:batchVo.getIds()){
				bProductMapper.deleteByPrimaryKey(id.getId());
			}
		}
	}
	@Override
	public BProductDetailVo detail(Integer valueOf) {
		BProductDetailVo detail =  bProductMapper.detail(valueOf);
		if(detail!=null){
		    detail.setCollages(bProductOrderCollageMapper.detail(valueOf));
		}
		return detail;
	}
	@Override
	@Transactional
	public void updateStatusBatch(BProductUpdateStatusVo statuses) {
		if(statuses!=null && statuses.getStatuses()!=null){
			for(BProductStatusVo status:statuses.getStatuses()){
				BProduct product =new BProduct();
				product.setId(status.getId());
				product.setStatus(status.getStatus());
				product.setLastUpdateTime(new Date());
				bProductMapper.updateByPrimaryKeySelective(product);
			}
		}
	}
	
}
