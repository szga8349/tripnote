package com.lenovo.tripnote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripWithBLOBs extends TTripnoteScheduleTrip {
    private String briefly;

    private String context;
}