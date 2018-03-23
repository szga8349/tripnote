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
public class TCustomer implements Serializable {
    /**
     * t_customer.id
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer id;

    /**
     * t_customer.name
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String name;

    /**
     * t_customer.importance (1-重要客户 -1 默认普通客户)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer importance;

    /**
     * t_customer.status
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer status;

    /**
     * t_customer.sex (1-男，0-女)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer sex;

    /**
     * t_customer.address (客户住址)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String address;

    /**
     * t_customer.birthday (生日)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Date birthday;

    /**
     * t_customer.idcard (身份证信息)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String idcard;

    /**
     * t_customer.constellation (星座)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String constellation;

    /**
     * t_customer.phone1
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String phone1;

    /**
     * t_customer.phone2
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String phone2;

    /**
     * t_customer.phone3
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String phone3;

    /**
     * t_customer.blood_type (血型,1-B型 2-A型 3-AB型 4-O型)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer bloodType;

    /**
     * t_customer.occupation (职业)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String occupation;

    /**
     * t_customer.email
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String email;

    /**
     * t_customer.qq
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String qq;

    /**
     * t_customer.acceptprice (可接受价格)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String acceptprice;

    /**
     * t_customer.arrive_space (曾去过的地方)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String arriveSpace;

    /**
     * t_customer.interest_space (感兴趣的地方)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String interestSpace;

    /**
     * t_customer.weixin
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String weixin;

    /**
     * t_customer.catogry_id (分组ID,-1默认分租)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer catogryId;

    /**
     * t_customer.portraiturl (头像)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String portraiturl;

    /**
     * t_customer.remark (备注)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String remark;

    /**
     * t_customer.create_user_id (定制师id)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Integer createUserId;

    /**
     * t_customer.create_time
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private Date createTime;

    /**
     * t_customer.interest_time (喜欢旅游的时段)
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String interestTime;

    /**
     * t_customer.family (家庭成员：json数据[{“relation”:"父亲","name":"王XX"},{“relation”:"母亲","name":"李XX"}])
     * @ibatorgenerated 2018-03-23 17:00:11
     */
    private String family;

}