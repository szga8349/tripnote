package com.test;

import com.lenovo.tripnote.dao.BPoiWithBLOBsMapper;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;
import com.lenovo.tripnote.entity.BPoiWithBLOBsExample;
import com.lenovo.tripnote.entity.PageBean;
import com.service.BPoiWithBLOBsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*

With spring by page query

Example parameter:
     ShopExample example = new ShopExample();
     Criteria c1 = example.createCriteria(); 
     Criteria c2 = example.createCriteria(); 
     example.or(c2); 
     c1.andSidBetween(1, 100); 
     c1.andSnameLike("my%"); 
     c2.andSidIsNotNull(); 
     example.setOrderByClause("sprice desc"); //sort field		

Page query
     List list = service.selectByExampleAndPage(example, new RowBounds(0, 3));
     

*/
public class TestDAOWithSpring {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Get service
        BPoiWithBLOBsService service = (BPoiWithBLOBsService)context.getBean("bPoiWithBLOBsService");
        // Invoke Service
        BPoiWithBLOBsExample example = new BPoiWithBLOBsExample();
        PageBean pageBean = service.queryByPage(1,3,example);
        System.out.println("record count:"+pageBean.getRecordCount());
        System.out.println("page count:"+pageBean.getPageCount());
        System.out.println("page size:"+pageBean.getPageSize());
        System.out.println("current page:"+pageBean.getCurrentPage());
        List<BPoiWithBLOBs> list = pageBean.getResultList();
        for (BPoiWithBLOBs obj : list ){
            System.out.println(obj);
        }
    }
}