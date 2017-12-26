package com.lenovo.tripnote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCityKey {
    private Integer id;

    private String nameCn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }
}