package com.lenovo.tripnote.entity.vo;

import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.BPoi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class BPoiResultVo extends BPoi{
    private BCity city;
}
