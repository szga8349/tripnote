package com.lenovo.tripnote.webchat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BProductCollectionExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    protected List<Criteria> oredCriteria;

    public BProductCollectionExample() {
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
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-06-15 09:12:43
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
     * @ibatorgenerated 2018-06-15 09:12:43
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
     * @ibatorgenerated 2018-06-15 09:12:43
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

        public Criteria andCollectionProductIdIsNull() {
            addCriterion("collection_product_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdIsNotNull() {
            addCriterion("collection_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdEqualTo(Integer value) {
            addCriterion("collection_product_id =", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdNotEqualTo(Integer value) {
            addCriterion("collection_product_id <>", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdGreaterThan(Integer value) {
            addCriterion("collection_product_id >", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_product_id >=", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdLessThan(Integer value) {
            addCriterion("collection_product_id <", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("collection_product_id <=", value, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdIn(List<Integer> values) {
            addCriterion("collection_product_id in", values, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdNotIn(List<Integer> values) {
            addCriterion("collection_product_id not in", values, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdBetween(Integer value1, Integer value2) {
            addCriterion("collection_product_id between", value1, value2, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_product_id not between", value1, value2, "collectionProductId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdIsNull() {
            addCriterion("collection_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdIsNotNull() {
            addCriterion("collection_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdEqualTo(Integer value) {
            addCriterion("collection_user_id =", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdNotEqualTo(Integer value) {
            addCriterion("collection_user_id <>", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdGreaterThan(Integer value) {
            addCriterion("collection_user_id >", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_user_id >=", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdLessThan(Integer value) {
            addCriterion("collection_user_id <", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("collection_user_id <=", value, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdIn(List<Integer> values) {
            addCriterion("collection_user_id in", values, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdNotIn(List<Integer> values) {
            addCriterion("collection_user_id not in", values, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdBetween(Integer value1, Integer value2) {
            addCriterion("collection_user_id between", value1, value2, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_user_id not between", value1, value2, "collectionUserId");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameIsNull() {
            addCriterion("collection_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameIsNotNull() {
            addCriterion("collection_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameEqualTo(String value) {
            addCriterion("collection_user_name =", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameNotEqualTo(String value) {
            addCriterion("collection_user_name <>", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameGreaterThan(String value) {
            addCriterion("collection_user_name >", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("collection_user_name >=", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameLessThan(String value) {
            addCriterion("collection_user_name <", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameLessThanOrEqualTo(String value) {
            addCriterion("collection_user_name <=", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameLike(String value) {
            addCriterion("collection_user_name like", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameNotLike(String value) {
            addCriterion("collection_user_name not like", value, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameIn(List<String> values) {
            addCriterion("collection_user_name in", values, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameNotIn(List<String> values) {
            addCriterion("collection_user_name not in", values, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameBetween(String value1, String value2) {
            addCriterion("collection_user_name between", value1, value2, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionUserNameNotBetween(String value1, String value2) {
            addCriterion("collection_user_name not between", value1, value2, "collectionUserName");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeIsNull() {
            addCriterion("collection_create_time is null");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeIsNotNull() {
            addCriterion("collection_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeEqualTo(Date value) {
            addCriterion("collection_create_time =", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeNotEqualTo(Date value) {
            addCriterion("collection_create_time <>", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeGreaterThan(Date value) {
            addCriterion("collection_create_time >", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("collection_create_time >=", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeLessThan(Date value) {
            addCriterion("collection_create_time <", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("collection_create_time <=", value, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeIn(List<Date> values) {
            addCriterion("collection_create_time in", values, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeNotIn(List<Date> values) {
            addCriterion("collection_create_time not in", values, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeBetween(Date value1, Date value2) {
            addCriterion("collection_create_time between", value1, value2, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("collection_create_time not between", value1, value2, "collectionCreateTime");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIsNull() {
            addCriterion("collection_remark is null");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIsNotNull() {
            addCriterion("collection_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkEqualTo(String value) {
            addCriterion("collection_remark =", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotEqualTo(String value) {
            addCriterion("collection_remark <>", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkGreaterThan(String value) {
            addCriterion("collection_remark >", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("collection_remark >=", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLessThan(String value) {
            addCriterion("collection_remark <", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLessThanOrEqualTo(String value) {
            addCriterion("collection_remark <=", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLike(String value) {
            addCriterion("collection_remark like", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotLike(String value) {
            addCriterion("collection_remark not like", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIn(List<String> values) {
            addCriterion("collection_remark in", values, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotIn(List<String> values) {
            addCriterion("collection_remark not in", values, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkBetween(String value1, String value2) {
            addCriterion("collection_remark between", value1, value2, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotBetween(String value1, String value2) {
            addCriterion("collection_remark not between", value1, value2, "collectionRemark");
            return (Criteria) this;
        }
    }

    /**
     * b_product_collection
     * @ibatorgenerated do_not_delete_during_merge 2018-06-15 09:12:43
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-06-15 09:12:43
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