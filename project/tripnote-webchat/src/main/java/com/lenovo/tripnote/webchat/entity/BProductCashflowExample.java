package com.lenovo.tripnote.webchat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BProductCashflowExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    protected List<Criteria> oredCriteria;

    public BProductCashflowExample() {
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
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-08-21 19:20:23
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
     * @ibatorgenerated 2018-08-21 19:20:23
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
     * @ibatorgenerated 2018-08-21 19:20:23
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Float value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Float value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Float value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Float value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Float value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Float> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Float> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Float value1, Float value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Float value1, Float value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIsNull() {
            addCriterion("flow_time is null");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIsNotNull() {
            addCriterion("flow_time is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTimeEqualTo(Date value) {
            addCriterion("flow_time =", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotEqualTo(Date value) {
            addCriterion("flow_time <>", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeGreaterThan(Date value) {
            addCriterion("flow_time >", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("flow_time >=", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeLessThan(Date value) {
            addCriterion("flow_time <", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeLessThanOrEqualTo(Date value) {
            addCriterion("flow_time <=", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIn(List<Date> values) {
            addCriterion("flow_time in", values, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotIn(List<Date> values) {
            addCriterion("flow_time not in", values, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeBetween(Date value1, Date value2) {
            addCriterion("flow_time between", value1, value2, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotBetween(Date value1, Date value2) {
            addCriterion("flow_time not between", value1, value2, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIsNull() {
            addCriterion("flow_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIsNotNull() {
            addCriterion("flow_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdEqualTo(Integer value) {
            addCriterion("flow_user_id =", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotEqualTo(Integer value) {
            addCriterion("flow_user_id <>", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdGreaterThan(Integer value) {
            addCriterion("flow_user_id >", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flow_user_id >=", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdLessThan(Integer value) {
            addCriterion("flow_user_id <", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("flow_user_id <=", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIn(List<Integer> values) {
            addCriterion("flow_user_id in", values, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotIn(List<Integer> values) {
            addCriterion("flow_user_id not in", values, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdBetween(Integer value1, Integer value2) {
            addCriterion("flow_user_id between", value1, value2, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flow_user_id not between", value1, value2, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameIsNull() {
            addCriterion("flow_user_name is null");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameIsNotNull() {
            addCriterion("flow_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameEqualTo(String value) {
            addCriterion("flow_user_name =", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameNotEqualTo(String value) {
            addCriterion("flow_user_name <>", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameGreaterThan(String value) {
            addCriterion("flow_user_name >", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("flow_user_name >=", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameLessThan(String value) {
            addCriterion("flow_user_name <", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameLessThanOrEqualTo(String value) {
            addCriterion("flow_user_name <=", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameLike(String value) {
            addCriterion("flow_user_name like", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameNotLike(String value) {
            addCriterion("flow_user_name not like", value, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameIn(List<String> values) {
            addCriterion("flow_user_name in", values, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameNotIn(List<String> values) {
            addCriterion("flow_user_name not in", values, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameBetween(String value1, String value2) {
            addCriterion("flow_user_name between", value1, value2, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowUserNameNotBetween(String value1, String value2) {
            addCriterion("flow_user_name not between", value1, value2, "flowUserName");
            return (Criteria) this;
        }

        public Criteria andFlowCodeIsNull() {
            addCriterion("flow_code is null");
            return (Criteria) this;
        }

        public Criteria andFlowCodeIsNotNull() {
            addCriterion("flow_code is not null");
            return (Criteria) this;
        }

        public Criteria andFlowCodeEqualTo(String value) {
            addCriterion("flow_code =", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeNotEqualTo(String value) {
            addCriterion("flow_code <>", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeGreaterThan(String value) {
            addCriterion("flow_code >", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeGreaterThanOrEqualTo(String value) {
            addCriterion("flow_code >=", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeLessThan(String value) {
            addCriterion("flow_code <", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeLessThanOrEqualTo(String value) {
            addCriterion("flow_code <=", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeLike(String value) {
            addCriterion("flow_code like", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeNotLike(String value) {
            addCriterion("flow_code not like", value, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeIn(List<String> values) {
            addCriterion("flow_code in", values, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeNotIn(List<String> values) {
            addCriterion("flow_code not in", values, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeBetween(String value1, String value2) {
            addCriterion("flow_code between", value1, value2, "flowCode");
            return (Criteria) this;
        }

        public Criteria andFlowCodeNotBetween(String value1, String value2) {
            addCriterion("flow_code not between", value1, value2, "flowCode");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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

        public Criteria andProfitTypeIsNull() {
            addCriterion("profit_type is null");
            return (Criteria) this;
        }

        public Criteria andProfitTypeIsNotNull() {
            addCriterion("profit_type is not null");
            return (Criteria) this;
        }

        public Criteria andProfitTypeEqualTo(Integer value) {
            addCriterion("profit_type =", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeNotEqualTo(Integer value) {
            addCriterion("profit_type <>", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeGreaterThan(Integer value) {
            addCriterion("profit_type >", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_type >=", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeLessThan(Integer value) {
            addCriterion("profit_type <", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeLessThanOrEqualTo(Integer value) {
            addCriterion("profit_type <=", value, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeIn(List<Integer> values) {
            addCriterion("profit_type in", values, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeNotIn(List<Integer> values) {
            addCriterion("profit_type not in", values, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeBetween(Integer value1, Integer value2) {
            addCriterion("profit_type between", value1, value2, "profitType");
            return (Criteria) this;
        }

        public Criteria andProfitTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_type not between", value1, value2, "profitType");
            return (Criteria) this;
        }
    }

    /**
     * b_product_cashflow
     * @ibatorgenerated do_not_delete_during_merge 2018-08-21 19:20:23
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-08-21 19:20:23
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