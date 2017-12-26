package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BHotelExample {
    protected String pk_name = "id";

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BHotelExample() {
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("`location` is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("`location` is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("`location` =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("`location` <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("`location` >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("`location` >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("`location` <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("`location` <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("`location` like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("`location` not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("`location` in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("`location` not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("`location` between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("`location` not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNull() {
            addCriterion("star_level is null");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNotNull() {
            addCriterion("star_level is not null");
            return (Criteria) this;
        }

        public Criteria andStarLevelEqualTo(Integer value) {
            addCriterion("star_level =", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotEqualTo(Integer value) {
            addCriterion("star_level <>", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThan(Integer value) {
            addCriterion("star_level >", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("star_level >=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThan(Integer value) {
            addCriterion("star_level <", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThanOrEqualTo(Integer value) {
            addCriterion("star_level <=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelIn(List<Integer> values) {
            addCriterion("star_level in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotIn(List<Integer> values) {
            addCriterion("star_level not in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelBetween(Integer value1, Integer value2) {
            addCriterion("star_level between", value1, value2, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("star_level not between", value1, value2, "starLevel");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIsNull() {
            addCriterion("check_in_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIsNotNull() {
            addCriterion("check_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeEqualTo(String value) {
            addCriterion("check_in_time =", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotEqualTo(String value) {
            addCriterion("check_in_time <>", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeGreaterThan(String value) {
            addCriterion("check_in_time >", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeGreaterThanOrEqualTo(String value) {
            addCriterion("check_in_time >=", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeLessThan(String value) {
            addCriterion("check_in_time <", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeLessThanOrEqualTo(String value) {
            addCriterion("check_in_time <=", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeLike(String value) {
            addCriterion("check_in_time like", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotLike(String value) {
            addCriterion("check_in_time not like", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIn(List<String> values) {
            addCriterion("check_in_time in", values, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotIn(List<String> values) {
            addCriterion("check_in_time not in", values, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeBetween(String value1, String value2) {
            addCriterion("check_in_time between", value1, value2, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotBetween(String value1, String value2) {
            addCriterion("check_in_time not between", value1, value2, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeIsNull() {
            addCriterion("check_ou_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeIsNotNull() {
            addCriterion("check_ou_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeEqualTo(String value) {
            addCriterion("check_ou_time =", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeNotEqualTo(String value) {
            addCriterion("check_ou_time <>", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeGreaterThan(String value) {
            addCriterion("check_ou_time >", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeGreaterThanOrEqualTo(String value) {
            addCriterion("check_ou_time >=", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeLessThan(String value) {
            addCriterion("check_ou_time <", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeLessThanOrEqualTo(String value) {
            addCriterion("check_ou_time <=", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeLike(String value) {
            addCriterion("check_ou_time like", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeNotLike(String value) {
            addCriterion("check_ou_time not like", value, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeIn(List<String> values) {
            addCriterion("check_ou_time in", values, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeNotIn(List<String> values) {
            addCriterion("check_ou_time not in", values, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeBetween(String value1, String value2) {
            addCriterion("check_ou_time between", value1, value2, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andCheckOuTimeNotBetween(String value1, String value2) {
            addCriterion("check_ou_time not between", value1, value2, "checkOuTime");
            return (Criteria) this;
        }

        public Criteria andBuiltInIsNull() {
            addCriterion("built_in is null");
            return (Criteria) this;
        }

        public Criteria andBuiltInIsNotNull() {
            addCriterion("built_in is not null");
            return (Criteria) this;
        }

        public Criteria andBuiltInEqualTo(String value) {
            addCriterion("built_in =", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInNotEqualTo(String value) {
            addCriterion("built_in <>", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInGreaterThan(String value) {
            addCriterion("built_in >", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInGreaterThanOrEqualTo(String value) {
            addCriterion("built_in >=", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInLessThan(String value) {
            addCriterion("built_in <", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInLessThanOrEqualTo(String value) {
            addCriterion("built_in <=", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInLike(String value) {
            addCriterion("built_in like", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInNotLike(String value) {
            addCriterion("built_in not like", value, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInIn(List<String> values) {
            addCriterion("built_in in", values, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInNotIn(List<String> values) {
            addCriterion("built_in not in", values, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInBetween(String value1, String value2) {
            addCriterion("built_in between", value1, value2, "builtIn");
            return (Criteria) this;
        }

        public Criteria andBuiltInNotBetween(String value1, String value2) {
            addCriterion("built_in not between", value1, value2, "builtIn");
            return (Criteria) this;
        }

        public Criteria andRepairTimeIsNull() {
            addCriterion("repair_time is null");
            return (Criteria) this;
        }

        public Criteria andRepairTimeIsNotNull() {
            addCriterion("repair_time is not null");
            return (Criteria) this;
        }

        public Criteria andRepairTimeEqualTo(String value) {
            addCriterion("repair_time =", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeNotEqualTo(String value) {
            addCriterion("repair_time <>", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeGreaterThan(String value) {
            addCriterion("repair_time >", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeGreaterThanOrEqualTo(String value) {
            addCriterion("repair_time >=", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeLessThan(String value) {
            addCriterion("repair_time <", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeLessThanOrEqualTo(String value) {
            addCriterion("repair_time <=", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeLike(String value) {
            addCriterion("repair_time like", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeNotLike(String value) {
            addCriterion("repair_time not like", value, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeIn(List<String> values) {
            addCriterion("repair_time in", values, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeNotIn(List<String> values) {
            addCriterion("repair_time not in", values, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeBetween(String value1, String value2) {
            addCriterion("repair_time between", value1, value2, "repairTime");
            return (Criteria) this;
        }

        public Criteria andRepairTimeNotBetween(String value1, String value2) {
            addCriterion("repair_time not between", value1, value2, "repairTime");
            return (Criteria) this;
        }

        public Criteria andFloorNumberIsNull() {
            addCriterion("floor_number is null");
            return (Criteria) this;
        }

        public Criteria andFloorNumberIsNotNull() {
            addCriterion("floor_number is not null");
            return (Criteria) this;
        }

        public Criteria andFloorNumberEqualTo(Integer value) {
            addCriterion("floor_number =", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberNotEqualTo(Integer value) {
            addCriterion("floor_number <>", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberGreaterThan(Integer value) {
            addCriterion("floor_number >", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_number >=", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberLessThan(Integer value) {
            addCriterion("floor_number <", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberLessThanOrEqualTo(Integer value) {
            addCriterion("floor_number <=", value, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberIn(List<Integer> values) {
            addCriterion("floor_number in", values, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberNotIn(List<Integer> values) {
            addCriterion("floor_number not in", values, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberBetween(Integer value1, Integer value2) {
            addCriterion("floor_number between", value1, value2, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andFloorNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_number not between", value1, value2, "floorNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIsNull() {
            addCriterion("room_number is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIsNotNull() {
            addCriterion("room_number is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberEqualTo(Integer value) {
            addCriterion("room_number =", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotEqualTo(Integer value) {
            addCriterion("room_number <>", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThan(Integer value) {
            addCriterion("room_number >", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_number >=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThan(Integer value) {
            addCriterion("room_number <", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThanOrEqualTo(Integer value) {
            addCriterion("room_number <=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIn(List<Integer> values) {
            addCriterion("room_number in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotIn(List<Integer> values) {
            addCriterion("room_number not in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberBetween(Integer value1, Integer value2) {
            addCriterion("room_number between", value1, value2, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("room_number not between", value1, value2, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andWifiIsNull() {
            addCriterion("wifi is null");
            return (Criteria) this;
        }

        public Criteria andWifiIsNotNull() {
            addCriterion("wifi is not null");
            return (Criteria) this;
        }

        public Criteria andWifiEqualTo(Integer value) {
            addCriterion("wifi =", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiNotEqualTo(Integer value) {
            addCriterion("wifi <>", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiGreaterThan(Integer value) {
            addCriterion("wifi >", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiGreaterThanOrEqualTo(Integer value) {
            addCriterion("wifi >=", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiLessThan(Integer value) {
            addCriterion("wifi <", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiLessThanOrEqualTo(Integer value) {
            addCriterion("wifi <=", value, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiIn(List<Integer> values) {
            addCriterion("wifi in", values, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiNotIn(List<Integer> values) {
            addCriterion("wifi not in", values, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiBetween(Integer value1, Integer value2) {
            addCriterion("wifi between", value1, value2, "wifi");
            return (Criteria) this;
        }

        public Criteria andWifiNotBetween(Integer value1, Integer value2) {
            addCriterion("wifi not between", value1, value2, "wifi");
            return (Criteria) this;
        }

        public Criteria andParkIsNull() {
            addCriterion("park is null");
            return (Criteria) this;
        }

        public Criteria andParkIsNotNull() {
            addCriterion("park is not null");
            return (Criteria) this;
        }

        public Criteria andParkEqualTo(Integer value) {
            addCriterion("park =", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkNotEqualTo(Integer value) {
            addCriterion("park <>", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkGreaterThan(Integer value) {
            addCriterion("park >", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkGreaterThanOrEqualTo(Integer value) {
            addCriterion("park >=", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkLessThan(Integer value) {
            addCriterion("park <", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkLessThanOrEqualTo(Integer value) {
            addCriterion("park <=", value, "park");
            return (Criteria) this;
        }

        public Criteria andParkIn(List<Integer> values) {
            addCriterion("park in", values, "park");
            return (Criteria) this;
        }

        public Criteria andParkNotIn(List<Integer> values) {
            addCriterion("park not in", values, "park");
            return (Criteria) this;
        }

        public Criteria andParkBetween(Integer value1, Integer value2) {
            addCriterion("park between", value1, value2, "park");
            return (Criteria) this;
        }

        public Criteria andParkNotBetween(Integer value1, Integer value2) {
            addCriterion("park not between", value1, value2, "park");
            return (Criteria) this;
        }

        public Criteria andElevatorIsNull() {
            addCriterion("elevator is null");
            return (Criteria) this;
        }

        public Criteria andElevatorIsNotNull() {
            addCriterion("elevator is not null");
            return (Criteria) this;
        }

        public Criteria andElevatorEqualTo(Integer value) {
            addCriterion("elevator =", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorNotEqualTo(Integer value) {
            addCriterion("elevator <>", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorGreaterThan(Integer value) {
            addCriterion("elevator >", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("elevator >=", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorLessThan(Integer value) {
            addCriterion("elevator <", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorLessThanOrEqualTo(Integer value) {
            addCriterion("elevator <=", value, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorIn(List<Integer> values) {
            addCriterion("elevator in", values, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorNotIn(List<Integer> values) {
            addCriterion("elevator not in", values, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorBetween(Integer value1, Integer value2) {
            addCriterion("elevator between", value1, value2, "elevator");
            return (Criteria) this;
        }

        public Criteria andElevatorNotBetween(Integer value1, Integer value2) {
            addCriterion("elevator not between", value1, value2, "elevator");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageIsNull() {
            addCriterion("left_luggage is null");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageIsNotNull() {
            addCriterion("left_luggage is not null");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageEqualTo(Integer value) {
            addCriterion("left_luggage =", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageNotEqualTo(Integer value) {
            addCriterion("left_luggage <>", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageGreaterThan(Integer value) {
            addCriterion("left_luggage >", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageGreaterThanOrEqualTo(Integer value) {
            addCriterion("left_luggage >=", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageLessThan(Integer value) {
            addCriterion("left_luggage <", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageLessThanOrEqualTo(Integer value) {
            addCriterion("left_luggage <=", value, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageIn(List<Integer> values) {
            addCriterion("left_luggage in", values, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageNotIn(List<Integer> values) {
            addCriterion("left_luggage not in", values, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageBetween(Integer value1, Integer value2) {
            addCriterion("left_luggage between", value1, value2, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andLeftLuggageNotBetween(Integer value1, Integer value2) {
            addCriterion("left_luggage not between", value1, value2, "leftLuggage");
            return (Criteria) this;
        }

        public Criteria andA24HourServicIsNull() {
            addCriterion("a24_hour_servic is null");
            return (Criteria) this;
        }

        public Criteria andA24HourServicIsNotNull() {
            addCriterion("a24_hour_servic is not null");
            return (Criteria) this;
        }

        public Criteria andA24HourServicEqualTo(Integer value) {
            addCriterion("a24_hour_servic =", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicNotEqualTo(Integer value) {
            addCriterion("a24_hour_servic <>", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicGreaterThan(Integer value) {
            addCriterion("a24_hour_servic >", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicGreaterThanOrEqualTo(Integer value) {
            addCriterion("a24_hour_servic >=", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicLessThan(Integer value) {
            addCriterion("a24_hour_servic <", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicLessThanOrEqualTo(Integer value) {
            addCriterion("a24_hour_servic <=", value, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicIn(List<Integer> values) {
            addCriterion("a24_hour_servic in", values, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicNotIn(List<Integer> values) {
            addCriterion("a24_hour_servic not in", values, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicBetween(Integer value1, Integer value2) {
            addCriterion("a24_hour_servic between", value1, value2, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andA24HourServicNotBetween(Integer value1, Integer value2) {
            addCriterion("a24_hour_servic not between", value1, value2, "a24HourServic");
            return (Criteria) this;
        }

        public Criteria andKettleIsNull() {
            addCriterion("kettle is null");
            return (Criteria) this;
        }

        public Criteria andKettleIsNotNull() {
            addCriterion("kettle is not null");
            return (Criteria) this;
        }

        public Criteria andKettleEqualTo(Integer value) {
            addCriterion("kettle =", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleNotEqualTo(Integer value) {
            addCriterion("kettle <>", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleGreaterThan(Integer value) {
            addCriterion("kettle >", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleGreaterThanOrEqualTo(Integer value) {
            addCriterion("kettle >=", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleLessThan(Integer value) {
            addCriterion("kettle <", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleLessThanOrEqualTo(Integer value) {
            addCriterion("kettle <=", value, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleIn(List<Integer> values) {
            addCriterion("kettle in", values, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleNotIn(List<Integer> values) {
            addCriterion("kettle not in", values, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleBetween(Integer value1, Integer value2) {
            addCriterion("kettle between", value1, value2, "kettle");
            return (Criteria) this;
        }

        public Criteria andKettleNotBetween(Integer value1, Integer value2) {
            addCriterion("kettle not between", value1, value2, "kettle");
            return (Criteria) this;
        }

        public Criteria andHairDrierIsNull() {
            addCriterion("hair_drier is null");
            return (Criteria) this;
        }

        public Criteria andHairDrierIsNotNull() {
            addCriterion("hair_drier is not null");
            return (Criteria) this;
        }

        public Criteria andHairDrierEqualTo(Integer value) {
            addCriterion("hair_drier =", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierNotEqualTo(Integer value) {
            addCriterion("hair_drier <>", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierGreaterThan(Integer value) {
            addCriterion("hair_drier >", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierGreaterThanOrEqualTo(Integer value) {
            addCriterion("hair_drier >=", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierLessThan(Integer value) {
            addCriterion("hair_drier <", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierLessThanOrEqualTo(Integer value) {
            addCriterion("hair_drier <=", value, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierIn(List<Integer> values) {
            addCriterion("hair_drier in", values, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierNotIn(List<Integer> values) {
            addCriterion("hair_drier not in", values, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierBetween(Integer value1, Integer value2) {
            addCriterion("hair_drier between", value1, value2, "hairDrier");
            return (Criteria) this;
        }

        public Criteria andHairDrierNotBetween(Integer value1, Integer value2) {
            addCriterion("hair_drier not between", value1, value2, "hairDrier");
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