package com.lenovo.tripnote.webchat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BProductShareExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    protected List<Criteria> oredCriteria;

    public BProductShareExample() {
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
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-06-14 18:44:28
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
     * @ibatorgenerated 2018-06-14 18:44:28
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
     * @ibatorgenerated 2018-06-14 18:44:28
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

        public Criteria andShareUserIdIsNull() {
            addCriterion("share_user_id is null");
            return (Criteria) this;
        }

        public Criteria andShareUserIdIsNotNull() {
            addCriterion("share_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andShareUserIdEqualTo(Integer value) {
            addCriterion("share_user_id =", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdNotEqualTo(Integer value) {
            addCriterion("share_user_id <>", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdGreaterThan(Integer value) {
            addCriterion("share_user_id >", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_user_id >=", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdLessThan(Integer value) {
            addCriterion("share_user_id <", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("share_user_id <=", value, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdIn(List<Integer> values) {
            addCriterion("share_user_id in", values, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdNotIn(List<Integer> values) {
            addCriterion("share_user_id not in", values, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdBetween(Integer value1, Integer value2) {
            addCriterion("share_user_id between", value1, value2, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("share_user_id not between", value1, value2, "shareUserId");
            return (Criteria) this;
        }

        public Criteria andShareUserNameIsNull() {
            addCriterion("share_user_name is null");
            return (Criteria) this;
        }

        public Criteria andShareUserNameIsNotNull() {
            addCriterion("share_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andShareUserNameEqualTo(String value) {
            addCriterion("share_user_name =", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameNotEqualTo(String value) {
            addCriterion("share_user_name <>", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameGreaterThan(String value) {
            addCriterion("share_user_name >", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("share_user_name >=", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameLessThan(String value) {
            addCriterion("share_user_name <", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameLessThanOrEqualTo(String value) {
            addCriterion("share_user_name <=", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameLike(String value) {
            addCriterion("share_user_name like", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameNotLike(String value) {
            addCriterion("share_user_name not like", value, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameIn(List<String> values) {
            addCriterion("share_user_name in", values, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameNotIn(List<String> values) {
            addCriterion("share_user_name not in", values, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameBetween(String value1, String value2) {
            addCriterion("share_user_name between", value1, value2, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareUserNameNotBetween(String value1, String value2) {
            addCriterion("share_user_name not between", value1, value2, "shareUserName");
            return (Criteria) this;
        }

        public Criteria andShareTimeIsNull() {
            addCriterion("share_time is null");
            return (Criteria) this;
        }

        public Criteria andShareTimeIsNotNull() {
            addCriterion("share_time is not null");
            return (Criteria) this;
        }

        public Criteria andShareTimeEqualTo(Date value) {
            addCriterion("share_time =", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeNotEqualTo(Date value) {
            addCriterion("share_time <>", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeGreaterThan(Date value) {
            addCriterion("share_time >", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("share_time >=", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeLessThan(Date value) {
            addCriterion("share_time <", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeLessThanOrEqualTo(Date value) {
            addCriterion("share_time <=", value, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeIn(List<Date> values) {
            addCriterion("share_time in", values, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeNotIn(List<Date> values) {
            addCriterion("share_time not in", values, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeBetween(Date value1, Date value2) {
            addCriterion("share_time between", value1, value2, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareTimeNotBetween(Date value1, Date value2) {
            addCriterion("share_time not between", value1, value2, "shareTime");
            return (Criteria) this;
        }

        public Criteria andShareLevelIsNull() {
            addCriterion("share_level is null");
            return (Criteria) this;
        }

        public Criteria andShareLevelIsNotNull() {
            addCriterion("share_level is not null");
            return (Criteria) this;
        }

        public Criteria andShareLevelEqualTo(Integer value) {
            addCriterion("share_level =", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelNotEqualTo(Integer value) {
            addCriterion("share_level <>", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelGreaterThan(Integer value) {
            addCriterion("share_level >", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_level >=", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelLessThan(Integer value) {
            addCriterion("share_level <", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelLessThanOrEqualTo(Integer value) {
            addCriterion("share_level <=", value, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelIn(List<Integer> values) {
            addCriterion("share_level in", values, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelNotIn(List<Integer> values) {
            addCriterion("share_level not in", values, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelBetween(Integer value1, Integer value2) {
            addCriterion("share_level between", value1, value2, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andShareLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("share_level not between", value1, value2, "shareLevel");
            return (Criteria) this;
        }

        public Criteria andParentShareIdIsNull() {
            addCriterion("parent_share_id is null");
            return (Criteria) this;
        }

        public Criteria andParentShareIdIsNotNull() {
            addCriterion("parent_share_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentShareIdEqualTo(Integer value) {
            addCriterion("parent_share_id =", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdNotEqualTo(Integer value) {
            addCriterion("parent_share_id <>", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdGreaterThan(Integer value) {
            addCriterion("parent_share_id >", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_share_id >=", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdLessThan(Integer value) {
            addCriterion("parent_share_id <", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_share_id <=", value, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdIn(List<Integer> values) {
            addCriterion("parent_share_id in", values, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdNotIn(List<Integer> values) {
            addCriterion("parent_share_id not in", values, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_share_id between", value1, value2, "parentShareId");
            return (Criteria) this;
        }

        public Criteria andParentShareIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_share_id not between", value1, value2, "parentShareId");
            return (Criteria) this;
        }
    }

    /**
     * b_product_share
     * @ibatorgenerated do_not_delete_during_merge 2018-06-14 18:44:28
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-06-14 18:44:28
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