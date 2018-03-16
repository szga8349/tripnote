package com.lenovo.tripnote.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripNoteMapper;
import com.lenovo.tripnote.dao.TTripnoteRCustomerMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleHotelMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleRCityMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleTrafficMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleTripMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.TTripNoteExample.Criteria;
import com.lenovo.tripnote.entity.TTripnoteRCustomerExample;
import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.TTripnoteScheduleExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotelExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCity;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCityExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleTraffic;
import com.lenovo.tripnote.entity.TTripnoteScheduleTrafficExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripWithBLOBs;
import com.lenovo.tripnote.entity.vo.BExportVo;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteTravelResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.util.TimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service()
public class TripnoteServiceImpl implements TTripnoteService{
	@Resource
	private TCustomerService tCustomerService;
	@Resource
	private TTripNoteMapper tTripNoteMapper;
	@Resource
	private TTripnoteScheduleMapper tTripnoteScheduleMapper;
	@Resource
	private TTripnoteRCustomerMapper tTripnoteRCustomerMapper;
	@Resource
	private TTripnoteScheduleTripMapper tTripnoteScheduleTripMapper;
	@Resource
	private TTripnoteScheduleHotelMapper tTripnoteScheduleHotelMapper;
	@Resource
	private TTripnoteScheduleTrafficMapper tTripnoteScheduleTrafficMapper;
	@Resource
	private TTripnoteScheduleRCityMapper tTripnoteScheduleRCityMapper;
	@Override
	public int insert(TTripNote t) {
		return tTripNoteMapper.insertSelective(t);
	}

	@Override
	public TTripNote update(TTripNote t) {
		 tTripNoteMapper.updateByPrimaryKeySelective(t);
		 return t;
	}

	@Override
	public TTripNote getByKey(Integer id) {
		return tTripNoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		//需要删除想关联的表信息
		return tTripNoteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripNote t) {
		//删除关联的客户关系
		TTripnoteRCustomerExample rexample = new TTripnoteRCustomerExample();
		com.lenovo.tripnote.entity.TTripnoteRCustomerExample.Criteria cr = rexample.createCriteria();
		cr.andTripnoteIdEqualTo(t.getId());
		tTripnoteRCustomerMapper.deleteByExample(rexample);
		//删除定制下关联所有日程对应的交通信息
		tTripNoteMapper.deleteTraffic(t);
		//删除定制下关联所有日程对应的定制师笔记关联信息
	    tTripNoteMapper.deleteScheduleRUsenote(t);
		//删除定制下关联所有日程对应的目的城际交通
	    tTripNoteMapper.deleteScheduleTraffic(t);
		//删除定制下关联所有日程对应的日程行程信息
		tTripNoteMapper.deleteScheduleTrip(t);
		//删除定制下关联所有日程对应的目的地关联信息
		tTripNoteMapper.deleteScheduleRCity(t);
		//删除定制下关联所有日程对应的关联的酒店信息
		tTripNoteMapper.deleteScheduleHotel(t);
		//删除关联的日程信息
		TTripnoteScheduleExample sexample = new TTripnoteScheduleExample();
		com.lenovo.tripnote.entity.TTripnoteScheduleExample.Criteria criteria1 = sexample.createCriteria();
		criteria1.andCreateUseridEqualTo(t.getCreateUserId());
		criteria1.andTripnoteIdEqualTo(t.getId());
		tTripnoteScheduleMapper.deleteByExample(sexample);
		//删除定制信息
		TTripNoteExample example = new TTripNoteExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(t.getId());
		criteria.andCreateUserIdEqualTo(t.getCreateUserId());
		return tTripNoteMapper.deleteByExample(example );
	}

	@Override
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t) {
		
		return tTripNoteMapper.queryCondition(t);
	}

	@Override
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t, RowBounds rowBound) {
	
		return tTripNoteMapper.queryConditionAndPage(t, rowBound);
	}
	public TTripNote insertTripNote(TTripNoteVo tripnoteVo,BAccount account){
		TTripNote t = new TTripNote();
		try {
			BeanUtils.copyProperties(t, tripnoteVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		t.setCreateUserId(account.getCreateUserId());
		t.setCreateTime(new Date());
		t.setCreateUserId(account.getId());
		this.insert(t);
	    if(tripnoteVo.getCustomers()!=null){//关联客户信息
	    	JSONObject json = JSONObject.fromObject(tripnoteVo.getCustomers());
	    	JSONArray arrary = json.getJSONArray("customer");
	    	int size = arrary.size();
	    	System.out.println(json);
	    	for(int i=0;i<size;i++){
	    		JSONObject custer = arrary.getJSONObject(i);
	    		TCustomer t1 = new TCustomer();
		    	t1.setName(custer.getString("name"));
		    	t1.setPhone1(custer.getString("phone"));
		    	List<TCustomer> customer = tCustomerService.search(t1,account,true); 
		    	if(customer!=null && !customer.isEmpty()){
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), customer.get(0).getId());
		    	}else{
		    		t1.setStatus(1);
		    		t1.setCreateUserId(account.getId());
		    		tCustomerService.insert(t1);
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), t1.getId());
		    	}
	    	}
		}
	    return t;
	}

	@Override
	public TTripNote update(TTripNoteVo tripnoteVo, BAccount account, Integer id) {
		TTripNote t = new TTripNote();
		try {
			BeanUtils.copyProperties(t, tripnoteVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		t.setCreateUserId(account.getCreateUserId());
		t.setCreateTime(new Date());
		t.setCreateUserId(account.getId());
		t.setId(id);
		if(t.getStatus()==1){//发布状态
			t.setPublishTime(new Date());
		}
		this.update(t);
	    if(tripnoteVo.getCustomers()!=null){//关联客户信息
	    	JSONObject json = JSONObject.fromObject(tripnoteVo.getCustomers());
	    	JSONArray arrary = json.getJSONArray("customer");
	    	int size = arrary.size();
	    	for(int i=0;i<size;i++){
	    		JSONObject custer = arrary.getJSONObject(i);
	    		TCustomer t1 = new TCustomer();
		    	t1.setName(custer.getString("name"));
		    	t1.setPhone1(custer.getString("phone"));
		    	List<TCustomer> customer = tCustomerService.search(t1,account,true); 
		    	if(customer!=null && !customer.isEmpty()){
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), customer.get(0).getId());
		    	}else{
		    		t1.setStatus(1);
		    		t1.setCreateUserId(account.getId());
		    		tCustomerService.insert(t1);
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), t1.getId());
		    	}
	    	}
		}
	    return t;
	}

	@Override
	public TTripNoteDetailResultVo getDetailByKey(Integer id) {
		
		return tTripNoteMapper.getDetailByKey(id);
	}
	
	@Override
	public TTripNoteDetailResultVo getPdfDetailByKey(Integer id) {
		
		return tTripNoteMapper.getPdfDetailByKey(id);
	}

	@Override
	public int queryCountCondition(TTripNoteSearchVo t) {
	
		return this.tTripNoteMapper.queryCount(t);
	}

	@Override
	public TTripNoteDetailResultVo getTravelQuotationByKey(Integer id) {
		// TODO Auto-generated method stub
		return tTripNoteMapper.getTravelQuotationByKey(id);
	}

	@Override
	public TTripNoteTravelResultVo getTravelQuotationGroupByTypeByKey(Integer id) {
		// TODO 暂时未做实现
		return this.tTripNoteMapper.getTravelQuotationGroupByTypeByKey(id);
	}
	@Override
	public void insertToTemplate(Integer tripnoteId,BExportVo exportVo){
		TTripNote tripNote = this.tTripNoteMapper.selectByPrimaryKey(tripnoteId);
		//重新计算天数
		int offset = exportVo.getScheduleIds().size();
		tripNote.setId(null);
		tripNote.setDays(offset);
		tripNote.setCreateUserId(exportVo.getCreateUserId());
		tripNote.setType(2);
		//重新设置最后时间
		tripNote.setEndDate(TimeUtils.getAfterDay(tripNote.getStartDate(),offset));
		tripNote.setCreateTime(new Date());
		//新建成模板数据 
		this.tTripNoteMapper.insertSelective(tripNote);
		
		copy(0,tripnoteId,tripNote,exportVo);
	}
	private void copy(int offset,Integer tripnoteId,TTripNote tripNote,BExportVo exportVo){
		//重新计算天数
		int size = exportVo.getScheduleIds().size();
		// 模板导出定制或定制导入模板时 不做客户端的拷贝
		/*TTripnoteRCustomerExample customerexample = new TTripnoteRCustomerExample();
		customerexample.createCriteria().andTripnoteIdEqualTo(tripnoteId);
		List<TTripnoteRCustomer> customers = tTripnoteRCustomerMapper.selectByExample(customerexample );
		if(customers!=null)
			for (TTripnoteRCustomer trip : customers) {
				trip.setTripnoteId(tripNote.getId());
				try{
				tTripnoteRCustomerMapper.insertSelective(trip);
				}catch (Exception e) {
					log.error(e, e.fillInStackTrace());
				}
		}*/
		for(int i=0;i<size;i++){
			Integer schduleId = exportVo.getScheduleIds().get(i);
			TTripnoteSchedule schedule = tTripnoteScheduleMapper.selectByPrimaryKey(schduleId);
			//重新设置indexdate
			schedule.setIndexdate(offset+i+1);
			schedule.setTripnoteId(tripNote.getId());
			schedule.setId(null);
			schedule.setCreatetime(new Date());
			//重新生成schedule
			tTripnoteScheduleMapper.insertSelective(schedule);
		
			{//重新生成关联的城市信息
				TTripnoteScheduleRCityExample cityexample = new TTripnoteScheduleRCityExample();
				cityexample.createCriteria().andScheduleIdEqualTo(schduleId);
				List<TTripnoteScheduleRCity> rcitys = tTripnoteScheduleRCityMapper.selectByExample(cityexample);
				if (rcitys != null)
					for (TTripnoteScheduleRCity trip : rcitys) {
						trip.setScheduleId(schedule.getId());
						trip.setId(null);
						tTripnoteScheduleRCityMapper.insertSelective(trip);
					}
			}
			// key值 以type+ID作为唯一标识 设置原ID与新ID的对应关系 在关联交通的时候使用
			Map<String, Integer> convert = new HashMap<String, Integer>();
			
			{ // 重新生成关联的scheduletrip数据 
				TTripnoteScheduleTripExample example = new TTripnoteScheduleTripExample();
				example.createCriteria().andScheduleIdEqualTo(schduleId);
				List<TTripnoteScheduleTripWithBLOBs> scheduleTrips = tTripnoteScheduleTripMapper
						.selectByExampleWithBLOBs(example);
				if (scheduleTrips != null)
					for (TTripnoteScheduleTripWithBLOBs trip : scheduleTrips) {
						trip.setCreateTime(new Date());
						Integer oldTripId = trip.getId();
						// 设置成新建日程的主键ID
						trip.setScheduleId(schedule.getId());
						// 重新生成scheduletrip
						trip.setId(null);
						tTripnoteScheduleTripMapper.insertSelective(trip);
						convert.put(trip.getType() + "" + oldTripId, trip.getId());
					}
			}
		
			{	//重新生成关联的酒店信息
				TTripnoteScheduleHotelExample hotelexample = new TTripnoteScheduleHotelExample();
				hotelexample.createCriteria().andScheduleIdEqualTo(schduleId);
				List<TTripnoteScheduleHotel> schedulehotels = tTripnoteScheduleHotelMapper
						.selectByExampleWithBLOBs(hotelexample);
				if (schedulehotels != null)
					for (TTripnoteScheduleHotel trip : schedulehotels) {
						trip.setCreateTime(new Date());
						Integer oldTripId = trip.getId();
						// 设置成新建日程的主键ID
						trip.setScheduleId(schedule.getId());
						// 重新生成scheduletrip
						trip.setId(null);
						tTripnoteScheduleHotelMapper.insertSelective(trip);
						convert.put(trip.getType() + "" + oldTripId, trip.getId());
					}
			}
			{//重新生成关联的交通信息
				TTripnoteScheduleTrafficExample trafficExample = new TTripnoteScheduleTrafficExample();
				trafficExample.createCriteria().andScheduleIdEqualTo(schduleId);
				List<TTripnoteScheduleTraffic> traffics = tTripnoteScheduleTrafficMapper
						.selectByExample(trafficExample);
				if (traffics != null)
					for (TTripnoteScheduleTraffic trip : traffics) {
						// 设置成新建日程的主键ID
						trip.setScheduleId(schedule.getId());
						// 程序设置修改后的日程安排信息主键值
						trip.setStartScheduleTrip(
								convert.get(trip.getStartScheduleType() + "" + trip.getStartScheduleTrip()));
						trip.setEndScheduleTrip(
								convert.get(trip.getEndScheduleType() + "" + trip.getEndScheduleTrip()));
						trip.setCreatetime(new Date());
						// 重新生成scheduletrip
						trip.setId(null);
						tTripnoteScheduleTrafficMapper.insertSelective(trip);
					}

			}
		}
	}

	@Override
	public void insertToTripnote(Integer tripnoteId, BExportVo exportVo) {
		TTripNote templet = this.tTripNoteMapper.selectByPrimaryKey(tripnoteId);
		TTripNote tripNote = this.tTripNoteMapper.selectByPrimaryKey(exportVo.getTripnoteId());
		int oldDate = tripNote.getDays();
		int dates =  oldDate+exportVo.getScheduleIds().size();

		tripNote.setDays(dates);
		//重新设置最后时间
	    tripNote.setEndDate(TimeUtils.getAfterDay(tripNote.getStartDate(),dates));
	   //新建成模板数据 
	    this.tTripNoteMapper.updateByPrimaryKeySelective(tripNote);
	    
	    copy(oldDate,templet.getId(),tripNote,exportVo);
	}
	

	
}
