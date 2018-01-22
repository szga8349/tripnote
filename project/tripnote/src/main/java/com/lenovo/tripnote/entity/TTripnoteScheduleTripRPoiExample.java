package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.List;

public class TTripnoteScheduleTripRPoiExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    protected String pk_name;

    /**
     * 排序字段
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    protected List<Criteria> oredCriteria;

    public TTripnoteScheduleTripRPoiExample() {
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
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-01-22 16:27:38
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
     * @ibatorgenerated 2018-01-22 16:27:38
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
     * @ibatorgenerated 2018-01-22 16:27:38
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

        public Criteria andScheduleTripIdIsNull() {
            addCriterion("schedule_trip_id is null");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdIsNotNull() {
            addCriterion("schedule_trip_id is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdEqualTo(Integer value) {
            addCriterion("schedule_trip_id =", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdNotEqualTo(Integer value) {
            addCriterion("schedule_trip_id <>", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdGreaterThan(Integer value) {
            addCriterion("schedule_trip_id >", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_trip_id >=", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdLessThan(Integer value) {
            addCriterion("schedule_trip_id <", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_trip_id <=", value, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdIn(List<Integer> values) {
            addCriterion("schedule_trip_id in", values, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdNotIn(List<Integer> values) {
            addCriterion("schedule_trip_id not in", values, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdBetween(Integer value1, Integer value2) {
            addCriterion("schedule_trip_id between", value1, value2, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andScheduleTripIdNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_trip_id not between", value1, value2, "scheduleTripId");
            return (Criteria) this;
        }

        public Criteria andPoiIdIsNull() {
            addCriterion("poi_id is null");
            return (Criteria) this;
        }

        public Criteria andPoiIdIsNotNull() {
            addCriterion("poi_id is not null");
            return (Criteria) this;
        }

        public Criteria andPoiIdEqualTo(Integer value) {
            addCriterion("poi_id =", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdNotEqualTo(Integer value) {
            addCriterion("poi_id <>", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdGreaterThan(Integer value) {
            addCriterion("poi_id >", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("poi_id >=", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdLessThan(Integer value) {
            addCriterion("poi_id <", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdLessThanOrEqualTo(Integer value) {
            addCriterion("poi_id <=", value, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdIn(List<Integer> values) {
            addCriterion("poi_id in", values, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdNotIn(List<Integer> values) {
            addCriterion("poi_id not in", values, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdBetween(Integer value1, Integer value2) {
            addCriterion("poi_id between", value1, value2, "poiId");
            return (Criteria) this;
        }

        public Criteria andPoiIdNotBetween(Integer value1, Integer value2) {
            addCriterion("poi_id not between", value1, value2, "poiId");
            return (Criteria) this;
        }
    }

    /**
     * t_tripnote_schedule_trip_r_poi
     * @ibatorgenerated do_not_delete_during_merge 2018-01-22 16:27:38
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-01-22 16:27:38
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