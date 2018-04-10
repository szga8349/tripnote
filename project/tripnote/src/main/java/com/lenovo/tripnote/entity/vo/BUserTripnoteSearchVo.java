package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BUserTripnoteSearchVo extends PageInfoVo{
     private String title;
     private Integer type;
     private Integer userId;
}
