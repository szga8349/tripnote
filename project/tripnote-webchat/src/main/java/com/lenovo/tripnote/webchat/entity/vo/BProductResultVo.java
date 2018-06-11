package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.entity.BProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BProductResultVo extends BProduct{
     /**
     * 已售数
     */
    public Integer soldCount;
    
    /**
     * 拼团中的 该属性只有是价格类型为拼团的时候 才会有值 其它情况下都是0
     */
    public Integer collageCount;
    
    
}
