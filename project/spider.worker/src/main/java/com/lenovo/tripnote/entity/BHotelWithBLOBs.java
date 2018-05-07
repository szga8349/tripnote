package com.lenovo.tripnote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
public class BHotelWithBLOBs extends BHotel {
    /**
     * b_hotel.introduction (酒店介绍)
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    private String introduction;

    /**
     * b_hotel.strategy (酒店攻略)
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    private String strategy;


}