package com.lenovo.tripnote.webchat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BProductOrderCollageExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    protected List<Criteria> oredCriteria;

    public BProductOrderCollageExample() {
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
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-08-29 10:41:45
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
     * @ibatorgenerated 2018-08-29 10:41:45
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
     * @ibatorgenerated 2018-08-29 10:41:45
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andCollageUserIdIsNull() {
            addCriterion("collage_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdIsNotNull() {
            addCriterion("collage_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdEqualTo(Integer value) {
            addCriterion("collage_user_id =", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdNotEqualTo(Integer value) {
            addCriterion("collage_user_id <>", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdGreaterThan(Integer value) {
            addCriterion("collage_user_id >", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collage_user_id >=", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdLessThan(Integer value) {
            addCriterion("collage_user_id <", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("collage_user_id <=", value, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdIn(List<Integer> values) {
            addCriterion("collage_user_id in", values, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdNotIn(List<Integer> values) {
            addCriterion("collage_user_id not in", values, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdBetween(Integer value1, Integer value2) {
            addCriterion("collage_user_id between", value1, value2, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collage_user_id not between", value1, value2, "collageUserId");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameIsNull() {
            addCriterion("collage_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameIsNotNull() {
            addCriterion("collage_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameEqualTo(String value) {
            addCriterion("collage_user_name =", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameNotEqualTo(String value) {
            addCriterion("collage_user_name <>", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameGreaterThan(String value) {
            addCriterion("collage_user_name >", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("collage_user_name >=", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameLessThan(String value) {
            addCriterion("collage_user_name <", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameLessThanOrEqualTo(String value) {
            addCriterion("collage_user_name <=", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameLike(String value) {
            addCriterion("collage_user_name like", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameNotLike(String value) {
            addCriterion("collage_user_name not like", value, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameIn(List<String> values) {
            addCriterion("collage_user_name in", values, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameNotIn(List<String> values) {
            addCriterion("collage_user_name not in", values, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameBetween(String value1, String value2) {
            addCriterion("collage_user_name between", value1, value2, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andCollageUserNameNotBetween(String value1, String value2) {
            addCriterion("collage_user_name not between", value1, value2, "collageUserName");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdIsNull() {
            addCriterion("product_collage_id is null");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdIsNotNull() {
            addCriterion("product_collage_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdEqualTo(Integer value) {
            addCriterion("product_collage_id =", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdNotEqualTo(Integer value) {
            addCriterion("product_collage_id <>", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdGreaterThan(Integer value) {
            addCriterion("product_collage_id >", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_collage_id >=", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdLessThan(Integer value) {
            addCriterion("product_collage_id <", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_collage_id <=", value, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdIn(List<Integer> values) {
            addCriterion("product_collage_id in", values, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdNotIn(List<Integer> values) {
            addCriterion("product_collage_id not in", values, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdBetween(Integer value1, Integer value2) {
            addCriterion("product_collage_id between", value1, value2, "productCollageId");
            return (Criteria) this;
        }

        public Criteria andProductCollageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_collage_id not between", value1, value2, "productCollageId");
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

        public Criteria andCollageStatusIsNull() {
            addCriterion("collage_status is null");
            return (Criteria) this;
        }

        public Criteria andCollageStatusIsNotNull() {
            addCriterion("collage_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollageStatusEqualTo(Integer value) {
            addCriterion("collage_status =", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusNotEqualTo(Integer value) {
            addCriterion("collage_status <>", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusGreaterThan(Integer value) {
            addCriterion("collage_status >", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("collage_status >=", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusLessThan(Integer value) {
            addCriterion("collage_status <", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusLessThanOrEqualTo(Integer value) {
            addCriterion("collage_status <=", value, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusIn(List<Integer> values) {
            addCriterion("collage_status in", values, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusNotIn(List<Integer> values) {
            addCriterion("collage_status not in", values, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusBetween(Integer value1, Integer value2) {
            addCriterion("collage_status between", value1, value2, "collageStatus");
            return (Criteria) this;
        }

        public Criteria andCollageStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("collage_status not between", value1, value2, "collageStatus");
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

        public Criteria andCreateUserNameIsNull() {
            addCriterion("create_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNotNull() {
            addCriterion("create_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameEqualTo(String value) {
            addCriterion("create_user_name =", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotEqualTo(String value) {
            addCriterion("create_user_name <>", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThan(String value) {
            addCriterion("create_user_name >", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_name >=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThan(String value) {
            addCriterion("create_user_name <", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThanOrEqualTo(String value) {
            addCriterion("create_user_name <=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLike(String value) {
            addCriterion("create_user_name like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotLike(String value) {
            addCriterion("create_user_name not like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIn(List<String> values) {
            addCriterion("create_user_name in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotIn(List<String> values) {
            addCriterion("create_user_name not in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameBetween(String value1, String value2) {
            addCriterion("create_user_name between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotBetween(String value1, String value2) {
            addCriterion("create_user_name not between", value1, value2, "createUserName");
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

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andCollageIdIsNull() {
            addCriterion("collage_id is null");
            return (Criteria) this;
        }

        public Criteria andCollageIdIsNotNull() {
            addCriterion("collage_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollageIdEqualTo(Integer value) {
            addCriterion("collage_id =", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdNotEqualTo(Integer value) {
            addCriterion("collage_id <>", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdGreaterThan(Integer value) {
            addCriterion("collage_id >", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collage_id >=", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdLessThan(Integer value) {
            addCriterion("collage_id <", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdLessThanOrEqualTo(Integer value) {
            addCriterion("collage_id <=", value, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdIn(List<Integer> values) {
            addCriterion("collage_id in", values, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdNotIn(List<Integer> values) {
            addCriterion("collage_id not in", values, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdBetween(Integer value1, Integer value2) {
            addCriterion("collage_id between", value1, value2, "collageId");
            return (Criteria) this;
        }

        public Criteria andCollageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collage_id not between", value1, value2, "collageId");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameIsNull() {
            addCriterion("order_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameIsNotNull() {
            addCriterion("order_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameEqualTo(String value) {
            addCriterion("order_user_name =", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameNotEqualTo(String value) {
            addCriterion("order_user_name <>", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameGreaterThan(String value) {
            addCriterion("order_user_name >", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_user_name >=", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameLessThan(String value) {
            addCriterion("order_user_name <", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameLessThanOrEqualTo(String value) {
            addCriterion("order_user_name <=", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameLike(String value) {
            addCriterion("order_user_name like", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameNotLike(String value) {
            addCriterion("order_user_name not like", value, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameIn(List<String> values) {
            addCriterion("order_user_name in", values, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameNotIn(List<String> values) {
            addCriterion("order_user_name not in", values, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameBetween(String value1, String value2) {
            addCriterion("order_user_name between", value1, value2, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserNameNotBetween(String value1, String value2) {
            addCriterion("order_user_name not between", value1, value2, "orderUserName");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneIsNull() {
            addCriterion("order_user_phone is null");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneIsNotNull() {
            addCriterion("order_user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneEqualTo(String value) {
            addCriterion("order_user_phone =", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneNotEqualTo(String value) {
            addCriterion("order_user_phone <>", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneGreaterThan(String value) {
            addCriterion("order_user_phone >", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("order_user_phone >=", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneLessThan(String value) {
            addCriterion("order_user_phone <", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("order_user_phone <=", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneLike(String value) {
            addCriterion("order_user_phone like", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneNotLike(String value) {
            addCriterion("order_user_phone not like", value, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneIn(List<String> values) {
            addCriterion("order_user_phone in", values, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneNotIn(List<String> values) {
            addCriterion("order_user_phone not in", values, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneBetween(String value1, String value2) {
            addCriterion("order_user_phone between", value1, value2, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andOrderUserPhoneNotBetween(String value1, String value2) {
            addCriterion("order_user_phone not between", value1, value2, "orderUserPhone");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNull() {
            addCriterion("place is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNotNull() {
            addCriterion("place is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceEqualTo(String value) {
            addCriterion("place =", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotEqualTo(String value) {
            addCriterion("place <>", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThan(String value) {
            addCriterion("place >", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("place >=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThan(String value) {
            addCriterion("place <", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThanOrEqualTo(String value) {
            addCriterion("place <=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLike(String value) {
            addCriterion("place like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotLike(String value) {
            addCriterion("place not like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceIn(List<String> values) {
            addCriterion("place in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotIn(List<String> values) {
            addCriterion("place not in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceBetween(String value1, String value2) {
            addCriterion("place between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotBetween(String value1, String value2) {
            addCriterion("place not between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("card_id like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("card_id not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andUseDateIsNull() {
            addCriterion("use_date is null");
            return (Criteria) this;
        }

        public Criteria andUseDateIsNotNull() {
            addCriterion("use_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseDateEqualTo(Date value) {
            addCriterionForJDBCDate("use_date =", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("use_date <>", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateGreaterThan(Date value) {
            addCriterionForJDBCDate("use_date >", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("use_date >=", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateLessThan(Date value) {
            addCriterionForJDBCDate("use_date <", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("use_date <=", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateIn(List<Date> values) {
            addCriterionForJDBCDate("use_date in", values, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("use_date not in", values, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("use_date between", value1, value2, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("use_date not between", value1, value2, "useDate");
            return (Criteria) this;
        }

        public Criteria andOtherInfoIsNull() {
            addCriterion("other_info is null");
            return (Criteria) this;
        }

        public Criteria andOtherInfoIsNotNull() {
            addCriterion("other_info is not null");
            return (Criteria) this;
        }

        public Criteria andOtherInfoEqualTo(String value) {
            addCriterion("other_info =", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoNotEqualTo(String value) {
            addCriterion("other_info <>", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoGreaterThan(String value) {
            addCriterion("other_info >", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoGreaterThanOrEqualTo(String value) {
            addCriterion("other_info >=", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoLessThan(String value) {
            addCriterion("other_info <", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoLessThanOrEqualTo(String value) {
            addCriterion("other_info <=", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoLike(String value) {
            addCriterion("other_info like", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoNotLike(String value) {
            addCriterion("other_info not like", value, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoIn(List<String> values) {
            addCriterion("other_info in", values, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoNotIn(List<String> values) {
            addCriterion("other_info not in", values, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoBetween(String value1, String value2) {
            addCriterion("other_info between", value1, value2, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andOtherInfoNotBetween(String value1, String value2) {
            addCriterion("other_info not between", value1, value2, "otherInfo");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Float value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Float value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Float value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Float value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Float value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Float value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Float> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Float> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Float value1, Float value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Float value1, Float value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(String value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(String value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(String value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(String value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(String value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLike(String value) {
            addCriterion("form_id like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotLike(String value) {
            addCriterion("form_id not like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<String> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<String> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(String value1, String value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(String value1, String value2) {
            addCriterion("form_id not between", value1, value2, "formId");
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

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andShareIdIsNull() {
            addCriterion("share_id is null");
            return (Criteria) this;
        }

        public Criteria andShareIdIsNotNull() {
            addCriterion("share_id is not null");
            return (Criteria) this;
        }

        public Criteria andShareIdEqualTo(Integer value) {
            addCriterion("share_id =", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdNotEqualTo(Integer value) {
            addCriterion("share_id <>", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdGreaterThan(Integer value) {
            addCriterion("share_id >", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_id >=", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdLessThan(Integer value) {
            addCriterion("share_id <", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdLessThanOrEqualTo(Integer value) {
            addCriterion("share_id <=", value, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdIn(List<Integer> values) {
            addCriterion("share_id in", values, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdNotIn(List<Integer> values) {
            addCriterion("share_id not in", values, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdBetween(Integer value1, Integer value2) {
            addCriterion("share_id between", value1, value2, "shareId");
            return (Criteria) this;
        }

        public Criteria andShareIdNotBetween(Integer value1, Integer value2) {
            addCriterion("share_id not between", value1, value2, "shareId");
            return (Criteria) this;
        }
    }

    /**
     * b_product_order_collage
     * @ibatorgenerated do_not_delete_during_merge 2018-08-29 10:41:45
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-08-29 10:41:45
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