package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BTrafficExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    protected List<Criteria> oredCriteria;

    public BTrafficExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setPk_name(String pk_name) {
        this.pk_name = pk_name;
    }

    public String getPk_name() {
        return pk_name;
    }

    /**
     * 排序字段
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 条件查询要先创建Criteria
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameCnIsNull() {
            addCriterion("name_cn is null");
            return (Criteria) this;
        }

        public Criteria andNameCnIsNotNull() {
            addCriterion("name_cn is not null");
            return (Criteria) this;
        }

        public Criteria andNameCnEqualTo(String value) {
            addCriterion("name_cn =", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotEqualTo(String value) {
            addCriterion("name_cn <>", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnGreaterThan(String value) {
            addCriterion("name_cn >", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnGreaterThanOrEqualTo(String value) {
            addCriterion("name_cn >=", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLessThan(String value) {
            addCriterion("name_cn <", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLessThanOrEqualTo(String value) {
            addCriterion("name_cn <=", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLike(String value) {
            addCriterion("name_cn like", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotLike(String value) {
            addCriterion("name_cn not like", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnIn(List<String> values) {
            addCriterion("name_cn in", values, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotIn(List<String> values) {
            addCriterion("name_cn not in", values, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnBetween(String value1, String value2) {
            addCriterion("name_cn between", value1, value2, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotBetween(String value1, String value2) {
            addCriterion("name_cn not between", value1, value2, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameEnIsNull() {
            addCriterion("name_en is null");
            return (Criteria) this;
        }

        public Criteria andNameEnIsNotNull() {
            addCriterion("name_en is not null");
            return (Criteria) this;
        }

        public Criteria andNameEnEqualTo(String value) {
            addCriterion("name_en =", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotEqualTo(String value) {
            addCriterion("name_en <>", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnGreaterThan(String value) {
            addCriterion("name_en >", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("name_en >=", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLessThan(String value) {
            addCriterion("name_en <", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLessThanOrEqualTo(String value) {
            addCriterion("name_en <=", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLike(String value) {
            addCriterion("name_en like", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotLike(String value) {
            addCriterion("name_en not like", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnIn(List<String> values) {
            addCriterion("name_en in", values, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotIn(List<String> values) {
            addCriterion("name_en not in", values, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnBetween(String value1, String value2) {
            addCriterion("name_en between", value1, value2, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotBetween(String value1, String value2) {
            addCriterion("name_en not between", value1, value2, "nameEn");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEndCityidIsNull() {
            addCriterion("end_cityid is null");
            return (Criteria) this;
        }

        public Criteria andEndCityidIsNotNull() {
            addCriterion("end_cityid is not null");
            return (Criteria) this;
        }

        public Criteria andEndCityidEqualTo(Integer value) {
            addCriterion("end_cityid =", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidNotEqualTo(Integer value) {
            addCriterion("end_cityid <>", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidGreaterThan(Integer value) {
            addCriterion("end_cityid >", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_cityid >=", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidLessThan(Integer value) {
            addCriterion("end_cityid <", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidLessThanOrEqualTo(Integer value) {
            addCriterion("end_cityid <=", value, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidIn(List<Integer> values) {
            addCriterion("end_cityid in", values, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidNotIn(List<Integer> values) {
            addCriterion("end_cityid not in", values, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidBetween(Integer value1, Integer value2) {
            addCriterion("end_cityid between", value1, value2, "endCityid");
            return (Criteria) this;
        }

        public Criteria andEndCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("end_cityid not between", value1, value2, "endCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidIsNull() {
            addCriterion("start_cityid is null");
            return (Criteria) this;
        }

        public Criteria andStartCityidIsNotNull() {
            addCriterion("start_cityid is not null");
            return (Criteria) this;
        }

        public Criteria andStartCityidEqualTo(Integer value) {
            addCriterion("start_cityid =", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidNotEqualTo(Integer value) {
            addCriterion("start_cityid <>", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidGreaterThan(Integer value) {
            addCriterion("start_cityid >", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_cityid >=", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidLessThan(Integer value) {
            addCriterion("start_cityid <", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidLessThanOrEqualTo(Integer value) {
            addCriterion("start_cityid <=", value, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidIn(List<Integer> values) {
            addCriterion("start_cityid in", values, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidNotIn(List<Integer> values) {
            addCriterion("start_cityid not in", values, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidBetween(Integer value1, Integer value2) {
            addCriterion("start_cityid between", value1, value2, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("start_cityid not between", value1, value2, "startCityid");
            return (Criteria) this;
        }

        public Criteria andStartNameIsNull() {
            addCriterion("start_name is null");
            return (Criteria) this;
        }

        public Criteria andStartNameIsNotNull() {
            addCriterion("start_name is not null");
            return (Criteria) this;
        }

        public Criteria andStartNameEqualTo(String value) {
            addCriterion("start_name =", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotEqualTo(String value) {
            addCriterion("start_name <>", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameGreaterThan(String value) {
            addCriterion("start_name >", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_name >=", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLessThan(String value) {
            addCriterion("start_name <", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLessThanOrEqualTo(String value) {
            addCriterion("start_name <=", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLike(String value) {
            addCriterion("start_name like", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotLike(String value) {
            addCriterion("start_name not like", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameIn(List<String> values) {
            addCriterion("start_name in", values, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotIn(List<String> values) {
            addCriterion("start_name not in", values, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameBetween(String value1, String value2) {
            addCriterion("start_name between", value1, value2, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotBetween(String value1, String value2) {
            addCriterion("start_name not between", value1, value2, "startName");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNull() {
            addCriterion("end_name is null");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNotNull() {
            addCriterion("end_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndNameEqualTo(String value) {
            addCriterion("end_name =", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotEqualTo(String value) {
            addCriterion("end_name <>", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThan(String value) {
            addCriterion("end_name >", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_name >=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThan(String value) {
            addCriterion("end_name <", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThanOrEqualTo(String value) {
            addCriterion("end_name <=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLike(String value) {
            addCriterion("end_name like", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotLike(String value) {
            addCriterion("end_name not like", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameIn(List<String> values) {
            addCriterion("end_name in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotIn(List<String> values) {
            addCriterion("end_name not in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameBetween(String value1, String value2) {
            addCriterion("end_name between", value1, value2, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotBetween(String value1, String value2) {
            addCriterion("end_name not between", value1, value2, "endName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andStartLonIsNull() {
            addCriterion("start_lon is null");
            return (Criteria) this;
        }

        public Criteria andStartLonIsNotNull() {
            addCriterion("start_lon is not null");
            return (Criteria) this;
        }

        public Criteria andStartLonEqualTo(Double value) {
            addCriterion("start_lon =", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonNotEqualTo(Double value) {
            addCriterion("start_lon <>", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonGreaterThan(Double value) {
            addCriterion("start_lon >", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonGreaterThanOrEqualTo(Double value) {
            addCriterion("start_lon >=", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonLessThan(Double value) {
            addCriterion("start_lon <", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonLessThanOrEqualTo(Double value) {
            addCriterion("start_lon <=", value, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonIn(List<Double> values) {
            addCriterion("start_lon in", values, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonNotIn(List<Double> values) {
            addCriterion("start_lon not in", values, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonBetween(Double value1, Double value2) {
            addCriterion("start_lon between", value1, value2, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLonNotBetween(Double value1, Double value2) {
            addCriterion("start_lon not between", value1, value2, "startLon");
            return (Criteria) this;
        }

        public Criteria andStartLatIsNull() {
            addCriterion("start_lat is null");
            return (Criteria) this;
        }

        public Criteria andStartLatIsNotNull() {
            addCriterion("start_lat is not null");
            return (Criteria) this;
        }

        public Criteria andStartLatEqualTo(Double value) {
            addCriterion("start_lat =", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotEqualTo(Double value) {
            addCriterion("start_lat <>", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatGreaterThan(Double value) {
            addCriterion("start_lat >", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatGreaterThanOrEqualTo(Double value) {
            addCriterion("start_lat >=", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatLessThan(Double value) {
            addCriterion("start_lat <", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatLessThanOrEqualTo(Double value) {
            addCriterion("start_lat <=", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatIn(List<Double> values) {
            addCriterion("start_lat in", values, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotIn(List<Double> values) {
            addCriterion("start_lat not in", values, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatBetween(Double value1, Double value2) {
            addCriterion("start_lat between", value1, value2, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotBetween(Double value1, Double value2) {
            addCriterion("start_lat not between", value1, value2, "startLat");
            return (Criteria) this;
        }

        public Criteria andEndLonIsNull() {
            addCriterion("end_lon is null");
            return (Criteria) this;
        }

        public Criteria andEndLonIsNotNull() {
            addCriterion("end_lon is not null");
            return (Criteria) this;
        }

        public Criteria andEndLonEqualTo(Double value) {
            addCriterion("end_lon =", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonNotEqualTo(Double value) {
            addCriterion("end_lon <>", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonGreaterThan(Double value) {
            addCriterion("end_lon >", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonGreaterThanOrEqualTo(Double value) {
            addCriterion("end_lon >=", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonLessThan(Double value) {
            addCriterion("end_lon <", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonLessThanOrEqualTo(Double value) {
            addCriterion("end_lon <=", value, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonIn(List<Double> values) {
            addCriterion("end_lon in", values, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonNotIn(List<Double> values) {
            addCriterion("end_lon not in", values, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonBetween(Double value1, Double value2) {
            addCriterion("end_lon between", value1, value2, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLonNotBetween(Double value1, Double value2) {
            addCriterion("end_lon not between", value1, value2, "endLon");
            return (Criteria) this;
        }

        public Criteria andEndLatIsNull() {
            addCriterion("end_lat is null");
            return (Criteria) this;
        }

        public Criteria andEndLatIsNotNull() {
            addCriterion("end_lat is not null");
            return (Criteria) this;
        }

        public Criteria andEndLatEqualTo(Double value) {
            addCriterion("end_lat =", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotEqualTo(Double value) {
            addCriterion("end_lat <>", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatGreaterThan(Double value) {
            addCriterion("end_lat >", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatGreaterThanOrEqualTo(Double value) {
            addCriterion("end_lat >=", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatLessThan(Double value) {
            addCriterion("end_lat <", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatLessThanOrEqualTo(Double value) {
            addCriterion("end_lat <=", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatIn(List<Double> values) {
            addCriterion("end_lat in", values, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotIn(List<Double> values) {
            addCriterion("end_lat not in", values, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatBetween(Double value1, Double value2) {
            addCriterion("end_lat between", value1, value2, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotBetween(Double value1, Double value2) {
            addCriterion("end_lat not between", value1, value2, "endLat");
            return (Criteria) this;
        }
    }

    /**
     * b_traffic
     * @ibatorgenerated do_not_delete_during_merge 2018-03-09 14:13:21
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}