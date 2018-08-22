package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BNumberGeneration {
    /**
     * b_number_generation.current_time (当前日期)
     * @ibatorgenerated 2018-08-22 14:44:35
     */
    private Date currentTime;

    /**
     * b_number_generation.number (当前日期下对应的数字信息)
     * @ibatorgenerated 2018-08-22 14:44:35
     */
    private Integer number;

}