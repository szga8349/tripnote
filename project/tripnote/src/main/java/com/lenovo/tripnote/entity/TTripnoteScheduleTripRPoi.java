package com.lenovo.tripnote.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripRPoi implements Serializable {
    /**
     * t_tripnote_schedule_trip_r_poi.schedule_trip_id
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer scheduleTripId;

    /**
     * t_tripnote_schedule_trip_r_poi.poi_id
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer poiId;
}