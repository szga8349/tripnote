package com.lenovo.tripnote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
public class BPoiWithBLOBs extends BPoi {
    /**
     * b_poi.consum_description (消费说明)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String consumDescription;

    /**
     * b_poi.traffic_instructions (交通说明)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String trafficInstructions;

    /**
     * b_poi.address_instrations (地点简介)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String addressInstrations;

    /**
     * b_poi.guide (实用指南)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String guide;

 
}