package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class BNumberGeneration implements Serializable {
    /**
     * b_number_generation.current_time (当前日期)
     * @ibatorgenerated 2018-04-13 11:04:19
     */
    private Date currentTime;

    /**
     * b_number_generation.number (当前日期下对应的数字信息)
     * @ibatorgenerated 2018-04-13 11:04:19
     */
    private Integer number;
}