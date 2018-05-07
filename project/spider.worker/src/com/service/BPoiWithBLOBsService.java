package com.service;

import com.lenovo.tripnote.dao.BPoiWithBLOBsMapper;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;
import com.lenovo.tripnote.entity.BPoiWithBLOBsExample;
import com.lenovo.tripnote.entity.PageBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BPoiWithBLOBsService {
    @Autowired
    private BPoiWithBLOBsMapper bPoiWithBLOBsMapper;

    public void addBPoiWithBLOBs(BPoiWithBLOBs record) {
        bPoiWithBLOBsMapper.insert(record);
    }

    public PageBean queryByPage(Integer page, Integer size, BPoiWithBLOBsExample example) {
        //record sum
        int sum = bPoiWithBLOBsMapper.countByExample(example);
        //page count
        int count = sum%size==0 ? sum/size : sum/size+1;
        //check page
        page = page<1 ? 1 : ((page>count)? count : page);
        //query
        List list = bPoiWithBLOBsMapper.selectByExampleAndPage(example, new RowBounds((page-1)*size, size));
        //save to PageBean 
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageCount(count);
        pageBean.setRecordCount(sum);
        pageBean.setResultList(list);
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(size);
        return pageBean;
    }
}