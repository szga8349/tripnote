package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BHotelSearchVo extends PageInfoVo{
	 /**
     * b_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String nameCn;
    
    
    private Integer cityId;
}
