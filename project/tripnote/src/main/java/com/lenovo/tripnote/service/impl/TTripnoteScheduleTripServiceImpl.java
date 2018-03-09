package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleTripMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripWithBLOBs;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripSort;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripSortVo;
import com.lenovo.tripnote.service.TTripnoteScheduleTripService;
@Service()
public class TTripnoteScheduleTripServiceImpl implements TTripnoteScheduleTripService{
	
   @Resource
    private TTripnoteScheduleTripMapper tTripnoteScheduleTripMapper;
	@Override
	public int insert(TTripnoteScheduleTrip t) {
		return tTripnoteScheduleTripMapper.insertSelective(t);
	}

	@Override
	public TTripnoteScheduleTrip update(TTripnoteScheduleTrip t) {
		 this.tTripnoteScheduleTripMapper.updateByPrimaryKeySelective(t);
		 return t;
	}

	@Override
	public TTripnoteScheduleTrip getByKey(Integer id) {
		return this.tTripnoteScheduleTripMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.tTripnoteScheduleTripMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleTrip t) {
		return 0;
	}

	@Override
	public int bachUpdateSort(TTripnoteScheduleTripSortVo vo) {
		int count = 0;
		if(vo.getSorts()!=null){
			for(TTripnoteScheduleTripSort sort:vo.getSorts()){
				 TTripnoteScheduleTripWithBLOBs t = new TTripnoteScheduleTripWithBLOBs();
				 t.setId(sort.getScheduletripId());
				 t.setSort(sort.getSort());
				 count+=this.tTripnoteScheduleTripMapper.updateByPrimaryKeySelective(t);
			}
		}
		return count;
	}

}
