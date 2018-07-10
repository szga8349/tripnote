package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.entity.BProductOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductOrderDetailVo extends BProductOrder{
	   
    /**
     * 用户头像信息
     */
    private String userImageUrl;
}
