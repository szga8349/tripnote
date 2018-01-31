package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTravelVo {
	
    /**
     * 客户报价
     */
    private Double totalPrice;
    /**
     * t_tripnote_schedule_offer.tripnote_id
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer tripnoteId;
    
    
    private List<TTripnoteScheduleOfferVo> scheduleOffers;
    
}
