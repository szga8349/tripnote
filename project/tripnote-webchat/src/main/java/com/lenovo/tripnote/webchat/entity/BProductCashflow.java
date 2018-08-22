package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductCashflow  {
    /**
     * b_product_cashflow.id
     * @ibatorgenerated 2018-08-21 19:20:23
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product_cashflow.type (流水类型：1入账-1出账)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private Integer type;

    /**
     * b_product_cashflow.money (金额(出账为负数))
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private Float money;

    /**
     * b_product_cashflow.flow_time (流水时间)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private Date flowTime;

    /**
     * b_product_cashflow.flow_user_id (流水归属用户ID)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    @IDEncryption
    private Integer flowUserId;

    /**
     * b_product_cashflow.flow_user_name (流水归属用户登录名)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private String flowUserName;

    /**
     * b_product_cashflow.flow_code (流水编号)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private String flowCode;

    /**
     * b_product_cashflow.remarks (备注:分享的佣金的时候 可以设置备注分享哪个产品)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private String remarks;

    /**
     * b_product_cashflow.product_id (分享的产品ID,出账时设置-1)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    @IDEncryption
    private Integer productId;

    /**
     * b_product_cashflow.profit_type (收益类型,1分享收益,2订单收益,-1出账)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    private Integer profitType;
}