package com.lenovo.tripnote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripWithBLOBs extends TTripnoteScheduleTrip {
    /**
     * t_tripnote_schedule_trip.address_instrations (地点简介)
     * @ibatorgenerated 2018-03-09 15:43:38
     */
    private String addressInstrations;

    /**
     * t_tripnote_schedule_trip.guide (实用指南)
     * @ibatorgenerated 2018-03-09 15:43:38
     */
    private String guide;

}