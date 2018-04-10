package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BUserTripnoteExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    protected String pk_name = "id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    protected List<Criteria> oredCriteria;

    public BUserTripnoteExample() {
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
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置去重复
     * @ibatorgenerated 2018-04-10 16:48:42
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
     * @ibatorgenerated 2018-04-10 16:48:42
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
     * @ibatorgenerated 2018-04-10 16:48:42
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
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

        public Criteria andRelationCityIdIsNull() {
            addCriterion("relation_city_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdIsNotNull() {
            addCriterion("relation_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdEqualTo(Integer value) {
            addCriterion("relation_city_id =", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdNotEqualTo(Integer value) {
            addCriterion("relation_city_id <>", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdGreaterThan(Integer value) {
            addCriterion("relation_city_id >", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_city_id >=", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdLessThan(Integer value) {
            addCriterion("relation_city_id <", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_city_id <=", value, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdIn(List<Integer> values) {
            addCriterion("relation_city_id in", values, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdNotIn(List<Integer> values) {
            addCriterion("relation_city_id not in", values, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_city_id between", value1, value2, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_city_id not between", value1, value2, "relationCityId");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameIsNull() {
            addCriterion("relation_city_name is null");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameIsNotNull() {
            addCriterion("relation_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameEqualTo(String value) {
            addCriterion("relation_city_name =", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameNotEqualTo(String value) {
            addCriterion("relation_city_name <>", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameGreaterThan(String value) {
            addCriterion("relation_city_name >", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("relation_city_name >=", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameLessThan(String value) {
            addCriterion("relation_city_name <", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameLessThanOrEqualTo(String value) {
            addCriterion("relation_city_name <=", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameLike(String value) {
            addCriterion("relation_city_name like", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameNotLike(String value) {
            addCriterion("relation_city_name not like", value, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameIn(List<String> values) {
            addCriterion("relation_city_name in", values, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameNotIn(List<String> values) {
            addCriterion("relation_city_name not in", values, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameBetween(String value1, String value2) {
            addCriterion("relation_city_name between", value1, value2, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationCityNameNotBetween(String value1, String value2) {
            addCriterion("relation_city_name not between", value1, value2, "relationCityName");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdIsNull() {
            addCriterion("relation_poi_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdIsNotNull() {
            addCriterion("relation_poi_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdEqualTo(Integer value) {
            addCriterion("relation_poi_id =", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdNotEqualTo(Integer value) {
            addCriterion("relation_poi_id <>", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdGreaterThan(Integer value) {
            addCriterion("relation_poi_id >", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_poi_id >=", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdLessThan(Integer value) {
            addCriterion("relation_poi_id <", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_poi_id <=", value, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdIn(List<Integer> values) {
            addCriterion("relation_poi_id in", values, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdNotIn(List<Integer> values) {
            addCriterion("relation_poi_id not in", values, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_poi_id between", value1, value2, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRelationPoiIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_poi_id not between", value1, value2, "relationPoiId");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameIsNull() {
            addCriterion("ralation_poi_name is null");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameIsNotNull() {
            addCriterion("ralation_poi_name is not null");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameEqualTo(String value) {
            addCriterion("ralation_poi_name =", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameNotEqualTo(String value) {
            addCriterion("ralation_poi_name <>", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameGreaterThan(String value) {
            addCriterion("ralation_poi_name >", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameGreaterThanOrEqualTo(String value) {
            addCriterion("ralation_poi_name >=", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameLessThan(String value) {
            addCriterion("ralation_poi_name <", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameLessThanOrEqualTo(String value) {
            addCriterion("ralation_poi_name <=", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameLike(String value) {
            addCriterion("ralation_poi_name like", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameNotLike(String value) {
            addCriterion("ralation_poi_name not like", value, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameIn(List<String> values) {
            addCriterion("ralation_poi_name in", values, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameNotIn(List<String> values) {
            addCriterion("ralation_poi_name not in", values, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameBetween(String value1, String value2) {
            addCriterion("ralation_poi_name between", value1, value2, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andRalationPoiNameNotBetween(String value1, String value2) {
            addCriterion("ralation_poi_name not between", value1, value2, "ralationPoiName");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("`label` is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("`label` is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("`label` =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("`label` <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("`label` >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("`label` >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("`label` <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("`label` <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("`label` like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("`label` not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("`label` in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("`label` not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("`label` between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("`label` not between", value1, value2, "label");
            return (Criteria) this;
        }
    }

    /**
     * b_user_tripnote
     * @ibatorgenerated do_not_delete_during_merge 2018-04-10 16:48:42
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 内类部，系统内部调用1
     * @ibatorgenerated 2018-04-10 16:48:42
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