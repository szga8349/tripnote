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
public class TTripNoteTemplate implements Serializable {
    private Integer id;

    private String title;

    private Integer type;

    private Integer days;

    private String destination;

    private Integer status;

    private Integer createUserId;

    private Date createTime;

    private String context;

}