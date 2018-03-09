package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.BTraffic;
import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripAddVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripHotelAddVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripVo;
import com.lenovo.tripnote.service.BHotelService;
import com.lenovo.tripnote.service.BPoiService;
import com.lenovo.tripnote.service.BRentService;
import com.lenovo.tripnote.service.BTrafficService;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;
import com.lenovo.tripnote.service.TTripnoteScheduleTripService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**日程行程 
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/scheduletrip")
public class TTripnoteScheduleTripController {
	
	//private List<String> model =  (List<String>) ImmutableList.of("poi","hotel","traffic","rent");
	
	@Resource
	private TTripnoteScheduleTripService tTripnoteScheduleTripService;
	@Resource
	private BPoiService bPoiService;
	@Resource
	private BHotelService bHotelService;
	@Resource
	private TTripnoteScheduleHotelService tTripnoteScheduleHotelService;
	@Resource
	private BRentService  bRentService;
	@Resource
	private BTrafficService  bTrafficService;
	
	@RequestMapping(value = "/poi/doAdd")
	public @ResponseBody ResultVo addScheduletrip(TTripnoteScheduleTripAddVo addVo){
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BPoi bpoi = bPoiService.getByKey(addVo.getSourceId());
		TTripnoteScheduleTrip trip = new TTripnoteScheduleTrip();
		try {
			BeanUtils.copyProperties(trip, bpoi);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		trip.setScheduleId(addVo.getScheduleId());
		trip.setPoiId(bpoi.getId());
		//设置排序字段值
		trip.setSort(addVo.getSort());
		trip.setId(null);
		//重新设置创建人和创建时间
		trip.setCreateUserId(account.getId());
		trip.setCreateTime(new Date());
		tTripnoteScheduleTripService.insert(trip);
		vo.setData(trip.getId());
		return vo;
	}
	@RequestMapping(value = "/hotel/doAdd")
	public @ResponseBody ResultVo addScheduletripHotel(@RequestBody TTripnoteScheduleTripHotelAddVo addVo){
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteScheduleHotelService.batchInsert(addVo,account));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateScheduletrip(TTripnoteScheduleTripVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleTrip schedule = new TTripnoteScheduleTrip();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateUserId(account.getId());
		tTripnoteScheduleTripService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteScheduletrip(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleTripService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo detailScheduletrip(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteScheduleTripService.getByKey(Integer.valueOf(id)));
		return vo;
	}
	
	/**增加租车关联关系
	 * @param addVo
	 * @return
	 */
	@RequestMapping(value = "/rent/doAdd")
	public @ResponseBody ResultVo addRentScheduletrip(TTripnoteScheduleTripAddVo addVo){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bRentService.insertRentToSchedule(addVo.getSourceId(),addVo.getScheduleId());
		return vo;
	}
	/**增加城际交通关联关系
	 * @param addVo
	 * @return
	 */
	@RequestMapping(value = "/traffic/doAdd")
	public @ResponseBody ResultVo addTrafficScheduletrip(TTripnoteScheduleTripAddVo addVo){
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BTraffic bpoi = bTrafficService.getByKey(addVo.getSourceId());
		TTripnoteScheduleTrip trip = new TTripnoteScheduleTrip();
		try {
			BeanUtils.copyProperties(trip, bpoi);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//设置排序字段值
		trip.setSort(addVo.getSort());
		//设置成城际交通类型
		trip.setType(7);
		trip.setScheduleId(addVo.getScheduleId());
		trip.setPoiId(bpoi.getId());
		trip.setId(null);
		//重新设置创建人和创建时间
		trip.setCreateUserId(account.getId());
		trip.setCreateTime(new Date());
		tTripnoteScheduleTripService.insert(trip);
		vo.setData(trip.getId());
		return vo;
	}
	
}
