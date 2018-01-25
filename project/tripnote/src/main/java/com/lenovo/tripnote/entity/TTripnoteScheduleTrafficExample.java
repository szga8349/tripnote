package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTripnoteScheduleTrafficExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    protected List<Criteria> oredCriteria;

    public TTripnoteScheduleTrafficExample() {
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
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-01-25 16:25:06
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
     * @ibatorgenerated 2018-01-25 16:25:06
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
     * @ibatorgenerated 2018-01-25 16:25:06
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

        public Criteria andScheduleIdIsNull() {
            addCriterion("schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIsNotNull() {
            addCriterion("schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdEqualTo(Integer value) {
            addCriterion("schedule_id =", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotEqualTo(Integer value) {
            addCriterion("schedule_id <>", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThan(Integer value) {
            addCriterion("schedule_id >", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_id >=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThan(Integer value) {
            addCriterion("schedule_id <", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_id <=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIn(List<Integer> values) {
            addCriterion("schedule_id in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotIn(List<Integer> values) {
            addCriterion("schedule_id not in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id not between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripIsNull() {
            addCriterion("start_schedule_trip is null");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripIsNotNull() {
            addCriterion("start_schedule_trip is not null");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripEqualTo(Integer value) {
            addCriterion("start_schedule_trip =", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripNotEqualTo(Integer value) {
            addCriterion("start_schedule_trip <>", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripGreaterThan(Integer value) {
            addCriterion("start_schedule_trip >", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_schedule_trip >=", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripLessThan(Integer value) {
            addCriterion("start_schedule_trip <", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripLessThanOrEqualTo(Integer value) {
            addCriterion("start_schedule_trip <=", value, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripIn(List<Integer> values) {
            addCriterion("start_schedule_trip in", values, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripNotIn(List<Integer> values) {
            addCriterion("start_schedule_trip not in", values, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripBetween(Integer value1, Integer value2) {
            addCriterion("start_schedule_trip between", value1, value2, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andStartScheduleTripNotBetween(Integer value1, Integer value2) {
            addCriterion("start_schedule_trip not between", value1, value2, "startScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripIsNull() {
            addCriterion("end_schedule_trip is null");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripIsNotNull() {
            addCriterion("end_schedule_trip is not null");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripEqualTo(Integer value) {
            addCriterion("end_schedule_trip =", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripNotEqualTo(Integer value) {
            addCriterion("end_schedule_trip <>", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripGreaterThan(Integer value) {
            addCriterion("end_schedule_trip >", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_schedule_trip >=", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripLessThan(Integer value) {
            addCriterion("end_schedule_trip <", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripLessThanOrEqualTo(Integer value) {
            addCriterion("end_schedule_trip <=", value, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripIn(List<Integer> values) {
            addCriterion("end_schedule_trip in", values, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripNotIn(List<Integer> values) {
            addCriterion("end_schedule_trip not in", values, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripBetween(Integer value1, Integer value2) {
            addCriterion("end_schedule_trip between", value1, value2, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andEndScheduleTripNotBetween(Integer value1, Integer value2) {
            addCriterion("end_schedule_trip not between", value1, value2, "endScheduleTrip");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Float value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Float value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Float value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Float value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Float value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Float value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Float> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Float> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Float value1, Float value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Float value1, Float value2) {
            addCriterion("distance not between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andSpendTimeIsNull() {
            addCriterion("spend_time is null");
            return (Criteria) this;
        }

        public Criteria andSpendTimeIsNotNull() {
            addCriterion("spend_time is not null");
            return (Criteria) this;
        }

        public Criteria andSpendTimeEqualTo(Long value) {
            addCriterion("spend_time =", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotEqualTo(Long value) {
            addCriterion("spend_time <>", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeGreaterThan(Long value) {
            addCriterion("spend_time >", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("spend_time >=", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeLessThan(Long value) {
            addCriterion("spend_time <", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeLessThanOrEqualTo(Long value) {
            addCriterion("spend_time <=", value, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeIn(List<Long> values) {
            addCriterion("spend_time in", values, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotIn(List<Long> values) {
            addCriterion("spend_time not in", values, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeBetween(Long value1, Long value2) {
            addCriterion("spend_time between", value1, value2, "spendTime");
            return (Criteria) this;
        }

        public Criteria andSpendTimeNotBetween(Long value1, Long value2) {
            addCriterion("spend_time not between", value1, value2, "spendTime");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeIsNull() {
            addCriterion("traffic_type is null");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeIsNotNull() {
            addCriterion("traffic_type is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeEqualTo(Integer value) {
            addCriterion("traffic_type =", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeNotEqualTo(Integer value) {
            addCriterion("traffic_type <>", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeGreaterThan(Integer value) {
            addCriterion("traffic_type >", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("traffic_type >=", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeLessThan(Integer value) {
            addCriterion("traffic_type <", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeLessThanOrEqualTo(Integer value) {
            addCriterion("traffic_type <=", value, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeIn(List<Integer> values) {
            addCriterion("traffic_type in", values, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeNotIn(List<Integer> values) {
            addCriterion("traffic_type not in", values, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeBetween(Integer value1, Integer value2) {
            addCriterion("traffic_type between", value1, value2, "trafficType");
            return (Criteria) this;
        }

        public Criteria andTrafficTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("traffic_type not between", value1, value2, "trafficType");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIsNull() {
            addCriterion("create_userid is null");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIsNotNull() {
            addCriterion("create_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUseridEqualTo(Integer value) {
            addCriterion("create_userid =", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotEqualTo(Integer value) {
            addCriterion("create_userid <>", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridGreaterThan(Integer value) {
            addCriterion("create_userid >", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_userid >=", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridLessThan(Integer value) {
            addCriterion("create_userid <", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridLessThanOrEqualTo(Integer value) {
            addCriterion("create_userid <=", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIn(List<Integer> values) {
            addCriterion("create_userid in", values, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotIn(List<Integer> values) {
            addCriterion("create_userid not in", values, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridBetween(Integer value1, Integer value2) {
            addCriterion("create_userid between", value1, value2, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("create_userid not between", value1, value2, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameIsNull() {
            addCriterion("createuser_name is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameIsNotNull() {
            addCriterion("createuser_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameEqualTo(String value) {
            addCriterion("createuser_name =", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameNotEqualTo(String value) {
            addCriterion("createuser_name <>", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameGreaterThan(String value) {
            addCriterion("createuser_name >", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameGreaterThanOrEqualTo(String value) {
            addCriterion("createuser_name >=", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameLessThan(String value) {
            addCriterion("createuser_name <", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameLessThanOrEqualTo(String value) {
            addCriterion("createuser_name <=", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameLike(String value) {
            addCriterion("createuser_name like", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameNotLike(String value) {
            addCriterion("createuser_name not like", value, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameIn(List<String> values) {
            addCriterion("createuser_name in", values, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameNotIn(List<String> values) {
            addCriterion("createuser_name not in", values, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameBetween(String value1, String value2) {
            addCriterion("createuser_name between", value1, value2, "createuserName");
            return (Criteria) this;
        }

        public Criteria andCreateuserNameNotBetween(String value1, String value2) {
            addCriterion("createuser_name not between", value1, value2, "createuserName");
            return (Criteria) this;
        }
    }

    /**
     * t_tripnote_schedule_traffic
     * @ibatorgenerated do_not_delete_during_merge 2018-01-25 16:25:06
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-01-25 16:25:06
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