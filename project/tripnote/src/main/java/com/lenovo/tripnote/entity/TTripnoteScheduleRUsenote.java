package com.lenovo.tripnote.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleRUsenote implements Serializable {
    private Integer tripnoteScheduleId;

    private Integer userTripnoteId;

}