package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleOfferResultVo {
	
	    /**
	     * 定制ID
	     */
	    private Integer tripnoteId;
        /**
         * 定制客户报价
         */
        private Double price;
        
        private List<TTripnoteScheduleOfferVo> scheduleOffers;
}
