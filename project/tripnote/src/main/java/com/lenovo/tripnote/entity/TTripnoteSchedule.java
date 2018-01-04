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
public class TTripnoteSchedule implements Serializable {
    private Integer id;

    private Integer tripnoteId;

    private String title;

    private Integer indexdate;

    private Date createtime;

    private Integer createUserid;

    private String createuserName;

    private String introduction;

}