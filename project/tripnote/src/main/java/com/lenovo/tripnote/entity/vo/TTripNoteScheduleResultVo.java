package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripNoteScheduleResultVo extends TTripNoteScheduleVo{
    private Integer id;
    /**
     * 日程关联的交通信息
     */
    private List<TTripnoteScheduleTrafficResultVo> traffics;
    /**
     * 日程关联的当日行程信息
     */
    private List<TTripnoteScheduleTrafficResultVo> scheduletrips;
    /**
     * 日程关联的定制师笔记信息
     */
    private List<BUserTripnoteResultVo> usertripnotes;
}