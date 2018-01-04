package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.List;

public class TTripnoteScheduleRUsenoteExample {
    protected String pk_name;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TTripnoteScheduleRUsenoteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setPk_name(String pk_name) {
        this.pk_name = pk_name;
    }

    public String getPk_name() {
        return pk_name;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

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

        public Criteria andTripnoteScheduleIdIsNull() {
            addCriterion("tripnote_schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdIsNotNull() {
            addCriterion("tripnote_schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdEqualTo(Integer value) {
            addCriterion("tripnote_schedule_id =", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdNotEqualTo(Integer value) {
            addCriterion("tripnote_schedule_id <>", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdGreaterThan(Integer value) {
            addCriterion("tripnote_schedule_id >", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tripnote_schedule_id >=", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdLessThan(Integer value) {
            addCriterion("tripnote_schedule_id <", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdLessThanOrEqualTo(Integer value) {
            addCriterion("tripnote_schedule_id <=", value, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdIn(List<Integer> values) {
            addCriterion("tripnote_schedule_id in", values, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdNotIn(List<Integer> values) {
            addCriterion("tripnote_schedule_id not in", values, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdBetween(Integer value1, Integer value2) {
            addCriterion("tripnote_schedule_id between", value1, value2, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andTripnoteScheduleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tripnote_schedule_id not between", value1, value2, "tripnoteScheduleId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdIsNull() {
            addCriterion("user_tripnote_id is null");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdIsNotNull() {
            addCriterion("user_tripnote_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdEqualTo(Integer value) {
            addCriterion("user_tripnote_id =", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdNotEqualTo(Integer value) {
            addCriterion("user_tripnote_id <>", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdGreaterThan(Integer value) {
            addCriterion("user_tripnote_id >", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_tripnote_id >=", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdLessThan(Integer value) {
            addCriterion("user_tripnote_id <", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_tripnote_id <=", value, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdIn(List<Integer> values) {
            addCriterion("user_tripnote_id in", values, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdNotIn(List<Integer> values) {
            addCriterion("user_tripnote_id not in", values, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdBetween(Integer value1, Integer value2) {
            addCriterion("user_tripnote_id between", value1, value2, "userTripnoteId");
            return (Criteria) this;
        }

        public Criteria andUserTripnoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_tripnote_id not between", value1, value2, "userTripnoteId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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