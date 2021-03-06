package com.lenovo.tripnote.webchat.entity;

import java.util.ArrayList;
import java.util.List;

public class BProductCollageExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    protected List<Criteria> oredCriteria;

    public BProductCollageExample() {
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
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-06-06 14:07:51
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
     * @ibatorgenerated 2018-06-06 14:07:51
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
     * @ibatorgenerated 2018-06-06 14:07:51
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

        public Criteria andPeopleNumIsNull() {
            addCriterion("people_num is null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumIsNotNull() {
            addCriterion("people_num is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumEqualTo(Integer value) {
            addCriterion("people_num =", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotEqualTo(Integer value) {
            addCriterion("people_num <>", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumGreaterThan(Integer value) {
            addCriterion("people_num >", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("people_num >=", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumLessThan(Integer value) {
            addCriterion("people_num <", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumLessThanOrEqualTo(Integer value) {
            addCriterion("people_num <=", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumIn(List<Integer> values) {
            addCriterion("people_num in", values, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotIn(List<Integer> values) {
            addCriterion("people_num not in", values, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumBetween(Integer value1, Integer value2) {
            addCriterion("people_num between", value1, value2, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("people_num not between", value1, value2, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andCollagePriceIsNull() {
            addCriterion("collage_price is null");
            return (Criteria) this;
        }

        public Criteria andCollagePriceIsNotNull() {
            addCriterion("collage_price is not null");
            return (Criteria) this;
        }

        public Criteria andCollagePriceEqualTo(Float value) {
            addCriterion("collage_price =", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceNotEqualTo(Float value) {
            addCriterion("collage_price <>", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceGreaterThan(Float value) {
            addCriterion("collage_price >", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceGreaterThanOrEqualTo(Float value) {
            addCriterion("collage_price >=", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceLessThan(Float value) {
            addCriterion("collage_price <", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceLessThanOrEqualTo(Float value) {
            addCriterion("collage_price <=", value, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceIn(List<Float> values) {
            addCriterion("collage_price in", values, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceNotIn(List<Float> values) {
            addCriterion("collage_price not in", values, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceBetween(Float value1, Float value2) {
            addCriterion("collage_price between", value1, value2, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollagePriceNotBetween(Float value1, Float value2) {
            addCriterion("collage_price not between", value1, value2, "collagePrice");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeIsNull() {
            addCriterion("collage_limit_time is null");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeIsNotNull() {
            addCriterion("collage_limit_time is not null");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeEqualTo(Integer value) {
            addCriterion("collage_limit_time =", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeNotEqualTo(Integer value) {
            addCriterion("collage_limit_time <>", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeGreaterThan(Integer value) {
            addCriterion("collage_limit_time >", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("collage_limit_time >=", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeLessThan(Integer value) {
            addCriterion("collage_limit_time <", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("collage_limit_time <=", value, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeIn(List<Integer> values) {
            addCriterion("collage_limit_time in", values, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeNotIn(List<Integer> values) {
            addCriterion("collage_limit_time not in", values, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeBetween(Integer value1, Integer value2) {
            addCriterion("collage_limit_time between", value1, value2, "collageLimitTime");
            return (Criteria) this;
        }

        public Criteria andCollageLimitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("collage_limit_time not between", value1, value2, "collageLimitTime");
            return (Criteria) this;
        }
    }

    /**
     * b_product_collage
     * @ibatorgenerated do_not_delete_during_merge 2018-06-06 14:07:51
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-06-06 14:07:51
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