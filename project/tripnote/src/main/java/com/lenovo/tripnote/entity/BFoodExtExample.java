package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.List;

public class BFoodExtExample {
    protected String pk_name;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BFoodExtExample() {
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

        public Criteria andFoodIdIsNull() {
            addCriterion("food_id is null");
            return (Criteria) this;
        }

        public Criteria andFoodIdIsNotNull() {
            addCriterion("food_id is not null");
            return (Criteria) this;
        }

        public Criteria andFoodIdEqualTo(Integer value) {
            addCriterion("food_id =", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdNotEqualTo(Integer value) {
            addCriterion("food_id <>", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdGreaterThan(Integer value) {
            addCriterion("food_id >", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_id >=", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdLessThan(Integer value) {
            addCriterion("food_id <", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdLessThanOrEqualTo(Integer value) {
            addCriterion("food_id <=", value, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdIn(List<Integer> values) {
            addCriterion("food_id in", values, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdNotIn(List<Integer> values) {
            addCriterion("food_id not in", values, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdBetween(Integer value1, Integer value2) {
            addCriterion("food_id between", value1, value2, "foodId");
            return (Criteria) this;
        }

        public Criteria andFoodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("food_id not between", value1, value2, "foodId");
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

        public Criteria andGooutIsNull() {
            addCriterion("goOut is null");
            return (Criteria) this;
        }

        public Criteria andGooutIsNotNull() {
            addCriterion("goOut is not null");
            return (Criteria) this;
        }

        public Criteria andGooutEqualTo(Integer value) {
            addCriterion("goOut =", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutNotEqualTo(Integer value) {
            addCriterion("goOut <>", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutGreaterThan(Integer value) {
            addCriterion("goOut >", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutGreaterThanOrEqualTo(Integer value) {
            addCriterion("goOut >=", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutLessThan(Integer value) {
            addCriterion("goOut <", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutLessThanOrEqualTo(Integer value) {
            addCriterion("goOut <=", value, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutIn(List<Integer> values) {
            addCriterion("goOut in", values, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutNotIn(List<Integer> values) {
            addCriterion("goOut not in", values, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutBetween(Integer value1, Integer value2) {
            addCriterion("goOut between", value1, value2, "goout");
            return (Criteria) this;
        }

        public Criteria andGooutNotBetween(Integer value1, Integer value2) {
            addCriterion("goOut not between", value1, value2, "goout");
            return (Criteria) this;
        }

        public Criteria andDeliveryIsNull() {
            addCriterion("delivery is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIsNotNull() {
            addCriterion("delivery is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryEqualTo(Integer value) {
            addCriterion("delivery =", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotEqualTo(Integer value) {
            addCriterion("delivery <>", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryGreaterThan(Integer value) {
            addCriterion("delivery >", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery >=", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryLessThan(Integer value) {
            addCriterion("delivery <", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryLessThanOrEqualTo(Integer value) {
            addCriterion("delivery <=", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryIn(List<Integer> values) {
            addCriterion("delivery in", values, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotIn(List<Integer> values) {
            addCriterion("delivery not in", values, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryBetween(Integer value1, Integer value2) {
            addCriterion("delivery between", value1, value2, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery not between", value1, value2, "delivery");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningIsNull() {
            addCriterion("outdoorDining is null");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningIsNotNull() {
            addCriterion("outdoorDining is not null");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningEqualTo(Integer value) {
            addCriterion("outdoorDining =", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningNotEqualTo(Integer value) {
            addCriterion("outdoorDining <>", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningGreaterThan(Integer value) {
            addCriterion("outdoorDining >", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningGreaterThanOrEqualTo(Integer value) {
            addCriterion("outdoorDining >=", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningLessThan(Integer value) {
            addCriterion("outdoorDining <", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningLessThanOrEqualTo(Integer value) {
            addCriterion("outdoorDining <=", value, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningIn(List<Integer> values) {
            addCriterion("outdoorDining in", values, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningNotIn(List<Integer> values) {
            addCriterion("outdoorDining not in", values, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningBetween(Integer value1, Integer value2) {
            addCriterion("outdoorDining between", value1, value2, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andOutdoordiningNotBetween(Integer value1, Integer value2) {
            addCriterion("outdoorDining not between", value1, value2, "outdoordining");
            return (Criteria) this;
        }

        public Criteria andAcceptcardIsNull() {
            addCriterion("acceptCard is null");
            return (Criteria) this;
        }

        public Criteria andAcceptcardIsNotNull() {
            addCriterion("acceptCard is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptcardEqualTo(Integer value) {
            addCriterion("acceptCard =", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardNotEqualTo(Integer value) {
            addCriterion("acceptCard <>", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardGreaterThan(Integer value) {
            addCriterion("acceptCard >", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardGreaterThanOrEqualTo(Integer value) {
            addCriterion("acceptCard >=", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardLessThan(Integer value) {
            addCriterion("acceptCard <", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardLessThanOrEqualTo(Integer value) {
            addCriterion("acceptCard <=", value, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardIn(List<Integer> values) {
            addCriterion("acceptCard in", values, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardNotIn(List<Integer> values) {
            addCriterion("acceptCard not in", values, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardBetween(Integer value1, Integer value2) {
            addCriterion("acceptCard between", value1, value2, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andAcceptcardNotBetween(Integer value1, Integer value2) {
            addCriterion("acceptCard not between", value1, value2, "acceptcard");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkIsNull() {
            addCriterion("provideDrink is null");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkIsNotNull() {
            addCriterion("provideDrink is not null");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkEqualTo(Integer value) {
            addCriterion("provideDrink =", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkNotEqualTo(Integer value) {
            addCriterion("provideDrink <>", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkGreaterThan(Integer value) {
            addCriterion("provideDrink >", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkGreaterThanOrEqualTo(Integer value) {
            addCriterion("provideDrink >=", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkLessThan(Integer value) {
            addCriterion("provideDrink <", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkLessThanOrEqualTo(Integer value) {
            addCriterion("provideDrink <=", value, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkIn(List<Integer> values) {
            addCriterion("provideDrink in", values, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkNotIn(List<Integer> values) {
            addCriterion("provideDrink not in", values, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkBetween(Integer value1, Integer value2) {
            addCriterion("provideDrink between", value1, value2, "providedrink");
            return (Criteria) this;
        }

        public Criteria andProvidedrinkNotBetween(Integer value1, Integer value2) {
            addCriterion("provideDrink not between", value1, value2, "providedrink");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelIsNull() {
            addCriterion("disabilityChannel is null");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelIsNotNull() {
            addCriterion("disabilityChannel is not null");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelEqualTo(Integer value) {
            addCriterion("disabilityChannel =", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelNotEqualTo(Integer value) {
            addCriterion("disabilityChannel <>", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelGreaterThan(Integer value) {
            addCriterion("disabilityChannel >", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("disabilityChannel >=", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelLessThan(Integer value) {
            addCriterion("disabilityChannel <", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelLessThanOrEqualTo(Integer value) {
            addCriterion("disabilityChannel <=", value, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelIn(List<Integer> values) {
            addCriterion("disabilityChannel in", values, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelNotIn(List<Integer> values) {
            addCriterion("disabilityChannel not in", values, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelBetween(Integer value1, Integer value2) {
            addCriterion("disabilityChannel between", value1, value2, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andDisabilitychannelNotBetween(Integer value1, Integer value2) {
            addCriterion("disabilityChannel not between", value1, value2, "disabilitychannel");
            return (Criteria) this;
        }

        public Criteria andTelevisionIsNull() {
            addCriterion("television is null");
            return (Criteria) this;
        }

        public Criteria andTelevisionIsNotNull() {
            addCriterion("television is not null");
            return (Criteria) this;
        }

        public Criteria andTelevisionEqualTo(Integer value) {
            addCriterion("television =", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionNotEqualTo(Integer value) {
            addCriterion("television <>", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionGreaterThan(Integer value) {
            addCriterion("television >", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionGreaterThanOrEqualTo(Integer value) {
            addCriterion("television >=", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionLessThan(Integer value) {
            addCriterion("television <", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionLessThanOrEqualTo(Integer value) {
            addCriterion("television <=", value, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionIn(List<Integer> values) {
            addCriterion("television in", values, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionNotIn(List<Integer> values) {
            addCriterion("television not in", values, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionBetween(Integer value1, Integer value2) {
            addCriterion("television between", value1, value2, "television");
            return (Criteria) this;
        }

        public Criteria andTelevisionNotBetween(Integer value1, Integer value2) {
            addCriterion("television not between", value1, value2, "television");
            return (Criteria) this;
        }

        public Criteria andBuffetIsNull() {
            addCriterion("buffet is null");
            return (Criteria) this;
        }

        public Criteria andBuffetIsNotNull() {
            addCriterion("buffet is not null");
            return (Criteria) this;
        }

        public Criteria andBuffetEqualTo(Integer value) {
            addCriterion("buffet =", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetNotEqualTo(Integer value) {
            addCriterion("buffet <>", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetGreaterThan(Integer value) {
            addCriterion("buffet >", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetGreaterThanOrEqualTo(Integer value) {
            addCriterion("buffet >=", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetLessThan(Integer value) {
            addCriterion("buffet <", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetLessThanOrEqualTo(Integer value) {
            addCriterion("buffet <=", value, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetIn(List<Integer> values) {
            addCriterion("buffet in", values, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetNotIn(List<Integer> values) {
            addCriterion("buffet not in", values, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetBetween(Integer value1, Integer value2) {
            addCriterion("buffet between", value1, value2, "buffet");
            return (Criteria) this;
        }

        public Criteria andBuffetNotBetween(Integer value1, Integer value2) {
            addCriterion("buffet not between", value1, value2, "buffet");
            return (Criteria) this;
        }

        public Criteria andLoungeIsNull() {
            addCriterion("lounge is null");
            return (Criteria) this;
        }

        public Criteria andLoungeIsNotNull() {
            addCriterion("lounge is not null");
            return (Criteria) this;
        }

        public Criteria andLoungeEqualTo(Integer value) {
            addCriterion("lounge =", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeNotEqualTo(Integer value) {
            addCriterion("lounge <>", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeGreaterThan(Integer value) {
            addCriterion("lounge >", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lounge >=", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeLessThan(Integer value) {
            addCriterion("lounge <", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeLessThanOrEqualTo(Integer value) {
            addCriterion("lounge <=", value, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeIn(List<Integer> values) {
            addCriterion("lounge in", values, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeNotIn(List<Integer> values) {
            addCriterion("lounge not in", values, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeBetween(Integer value1, Integer value2) {
            addCriterion("lounge between", value1, value2, "lounge");
            return (Criteria) this;
        }

        public Criteria andLoungeNotBetween(Integer value1, Integer value2) {
            addCriterion("lounge not between", value1, value2, "lounge");
            return (Criteria) this;
        }

        public Criteria andBookIsNull() {
            addCriterion("book is null");
            return (Criteria) this;
        }

        public Criteria andBookIsNotNull() {
            addCriterion("book is not null");
            return (Criteria) this;
        }

        public Criteria andBookEqualTo(Integer value) {
            addCriterion("book =", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookNotEqualTo(Integer value) {
            addCriterion("book <>", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookGreaterThan(Integer value) {
            addCriterion("book >", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookGreaterThanOrEqualTo(Integer value) {
            addCriterion("book >=", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookLessThan(Integer value) {
            addCriterion("book <", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookLessThanOrEqualTo(Integer value) {
            addCriterion("book <=", value, "book");
            return (Criteria) this;
        }

        public Criteria andBookIn(List<Integer> values) {
            addCriterion("book in", values, "book");
            return (Criteria) this;
        }

        public Criteria andBookNotIn(List<Integer> values) {
            addCriterion("book not in", values, "book");
            return (Criteria) this;
        }

        public Criteria andBookBetween(Integer value1, Integer value2) {
            addCriterion("book between", value1, value2, "book");
            return (Criteria) this;
        }

        public Criteria andBookNotBetween(Integer value1, Integer value2) {
            addCriterion("book not between", value1, value2, "book");
            return (Criteria) this;
        }

        public Criteria andChildrenIsNull() {
            addCriterion("children is null");
            return (Criteria) this;
        }

        public Criteria andChildrenIsNotNull() {
            addCriterion("children is not null");
            return (Criteria) this;
        }

        public Criteria andChildrenEqualTo(Integer value) {
            addCriterion("children =", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenNotEqualTo(Integer value) {
            addCriterion("children <>", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenGreaterThan(Integer value) {
            addCriterion("children >", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenGreaterThanOrEqualTo(Integer value) {
            addCriterion("children >=", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenLessThan(Integer value) {
            addCriterion("children <", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenLessThanOrEqualTo(Integer value) {
            addCriterion("children <=", value, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenIn(List<Integer> values) {
            addCriterion("children in", values, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenNotIn(List<Integer> values) {
            addCriterion("children not in", values, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenBetween(Integer value1, Integer value2) {
            addCriterion("children between", value1, value2, "children");
            return (Criteria) this;
        }

        public Criteria andChildrenNotBetween(Integer value1, Integer value2) {
            addCriterion("children not between", value1, value2, "children");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingIsNull() {
            addCriterion("businessMeeting is null");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingIsNotNull() {
            addCriterion("businessMeeting is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingEqualTo(Integer value) {
            addCriterion("businessMeeting =", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingNotEqualTo(Integer value) {
            addCriterion("businessMeeting <>", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingGreaterThan(Integer value) {
            addCriterion("businessMeeting >", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingGreaterThanOrEqualTo(Integer value) {
            addCriterion("businessMeeting >=", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingLessThan(Integer value) {
            addCriterion("businessMeeting <", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingLessThanOrEqualTo(Integer value) {
            addCriterion("businessMeeting <=", value, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingIn(List<Integer> values) {
            addCriterion("businessMeeting in", values, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingNotIn(List<Integer> values) {
            addCriterion("businessMeeting not in", values, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingBetween(Integer value1, Integer value2) {
            addCriterion("businessMeeting between", value1, value2, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andBusinessmeetingNotBetween(Integer value1, Integer value2) {
            addCriterion("businessMeeting not between", value1, value2, "businessmeeting");
            return (Criteria) this;
        }

        public Criteria andGroupdiningIsNull() {
            addCriterion("groupDining is null");
            return (Criteria) this;
        }

        public Criteria andGroupdiningIsNotNull() {
            addCriterion("groupDining is not null");
            return (Criteria) this;
        }

        public Criteria andGroupdiningEqualTo(Integer value) {
            addCriterion("groupDining =", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningNotEqualTo(Integer value) {
            addCriterion("groupDining <>", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningGreaterThan(Integer value) {
            addCriterion("groupDining >", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupDining >=", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningLessThan(Integer value) {
            addCriterion("groupDining <", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningLessThanOrEqualTo(Integer value) {
            addCriterion("groupDining <=", value, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningIn(List<Integer> values) {
            addCriterion("groupDining in", values, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningNotIn(List<Integer> values) {
            addCriterion("groupDining not in", values, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningBetween(Integer value1, Integer value2) {
            addCriterion("groupDining between", value1, value2, "groupdining");
            return (Criteria) this;
        }

        public Criteria andGroupdiningNotBetween(Integer value1, Integer value2) {
            addCriterion("groupDining not between", value1, value2, "groupdining");
            return (Criteria) this;
        }

        public Criteria andLocalfoodIsNull() {
            addCriterion("localFood is null");
            return (Criteria) this;
        }

        public Criteria andLocalfoodIsNotNull() {
            addCriterion("localFood is not null");
            return (Criteria) this;
        }

        public Criteria andLocalfoodEqualTo(Integer value) {
            addCriterion("localFood =", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodNotEqualTo(Integer value) {
            addCriterion("localFood <>", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodGreaterThan(Integer value) {
            addCriterion("localFood >", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("localFood >=", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodLessThan(Integer value) {
            addCriterion("localFood <", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodLessThanOrEqualTo(Integer value) {
            addCriterion("localFood <=", value, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodIn(List<Integer> values) {
            addCriterion("localFood in", values, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodNotIn(List<Integer> values) {
            addCriterion("localFood not in", values, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodBetween(Integer value1, Integer value2) {
            addCriterion("localFood between", value1, value2, "localfood");
            return (Criteria) this;
        }

        public Criteria andLocalfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("localFood not between", value1, value2, "localfood");
            return (Criteria) this;
        }

        public Criteria andDinnerIsNull() {
            addCriterion("dinner is null");
            return (Criteria) this;
        }

        public Criteria andDinnerIsNotNull() {
            addCriterion("dinner is not null");
            return (Criteria) this;
        }

        public Criteria andDinnerEqualTo(Integer value) {
            addCriterion("dinner =", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotEqualTo(Integer value) {
            addCriterion("dinner <>", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerGreaterThan(Integer value) {
            addCriterion("dinner >", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("dinner >=", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerLessThan(Integer value) {
            addCriterion("dinner <", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerLessThanOrEqualTo(Integer value) {
            addCriterion("dinner <=", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerIn(List<Integer> values) {
            addCriterion("dinner in", values, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotIn(List<Integer> values) {
            addCriterion("dinner not in", values, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerBetween(Integer value1, Integer value2) {
            addCriterion("dinner between", value1, value2, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotBetween(Integer value1, Integer value2) {
            addCriterion("dinner not between", value1, value2, "dinner");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantIsNull() {
            addCriterion("landscapeRestaurant is null");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantIsNotNull() {
            addCriterion("landscapeRestaurant is not null");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantEqualTo(Integer value) {
            addCriterion("landscapeRestaurant =", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantNotEqualTo(Integer value) {
            addCriterion("landscapeRestaurant <>", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantGreaterThan(Integer value) {
            addCriterion("landscapeRestaurant >", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantGreaterThanOrEqualTo(Integer value) {
            addCriterion("landscapeRestaurant >=", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantLessThan(Integer value) {
            addCriterion("landscapeRestaurant <", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantLessThanOrEqualTo(Integer value) {
            addCriterion("landscapeRestaurant <=", value, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantIn(List<Integer> values) {
            addCriterion("landscapeRestaurant in", values, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantNotIn(List<Integer> values) {
            addCriterion("landscapeRestaurant not in", values, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantBetween(Integer value1, Integer value2) {
            addCriterion("landscapeRestaurant between", value1, value2, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andLandscaperestaurantNotBetween(Integer value1, Integer value2) {
            addCriterion("landscapeRestaurant not between", value1, value2, "landscaperestaurant");
            return (Criteria) this;
        }

        public Criteria andRomanticIsNull() {
            addCriterion("romantic is null");
            return (Criteria) this;
        }

        public Criteria andRomanticIsNotNull() {
            addCriterion("romantic is not null");
            return (Criteria) this;
        }

        public Criteria andRomanticEqualTo(Integer value) {
            addCriterion("romantic =", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticNotEqualTo(Integer value) {
            addCriterion("romantic <>", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticGreaterThan(Integer value) {
            addCriterion("romantic >", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticGreaterThanOrEqualTo(Integer value) {
            addCriterion("romantic >=", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticLessThan(Integer value) {
            addCriterion("romantic <", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticLessThanOrEqualTo(Integer value) {
            addCriterion("romantic <=", value, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticIn(List<Integer> values) {
            addCriterion("romantic in", values, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticNotIn(List<Integer> values) {
            addCriterion("romantic not in", values, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticBetween(Integer value1, Integer value2) {
            addCriterion("romantic between", value1, value2, "romantic");
            return (Criteria) this;
        }

        public Criteria andRomanticNotBetween(Integer value1, Integer value2) {
            addCriterion("romantic not between", value1, value2, "romantic");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionIsNull() {
            addCriterion("specialOccasion is null");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionIsNotNull() {
            addCriterion("specialOccasion is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionEqualTo(Integer value) {
            addCriterion("specialOccasion =", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionNotEqualTo(Integer value) {
            addCriterion("specialOccasion <>", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionGreaterThan(Integer value) {
            addCriterion("specialOccasion >", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionGreaterThanOrEqualTo(Integer value) {
            addCriterion("specialOccasion >=", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionLessThan(Integer value) {
            addCriterion("specialOccasion <", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionLessThanOrEqualTo(Integer value) {
            addCriterion("specialOccasion <=", value, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionIn(List<Integer> values) {
            addCriterion("specialOccasion in", values, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionNotIn(List<Integer> values) {
            addCriterion("specialOccasion not in", values, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionBetween(Integer value1, Integer value2) {
            addCriterion("specialOccasion between", value1, value2, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andSpecialoccasionNotBetween(Integer value1, Integer value2) {
            addCriterion("specialOccasion not between", value1, value2, "specialoccasion");
            return (Criteria) this;
        }

        public Criteria andMealIsNull() {
            addCriterion("meal is null");
            return (Criteria) this;
        }

        public Criteria andMealIsNotNull() {
            addCriterion("meal is not null");
            return (Criteria) this;
        }

        public Criteria andMealEqualTo(Integer value) {
            addCriterion("meal =", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealNotEqualTo(Integer value) {
            addCriterion("meal <>", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealGreaterThan(Integer value) {
            addCriterion("meal >", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealGreaterThanOrEqualTo(Integer value) {
            addCriterion("meal >=", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealLessThan(Integer value) {
            addCriterion("meal <", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealLessThanOrEqualTo(Integer value) {
            addCriterion("meal <=", value, "meal");
            return (Criteria) this;
        }

        public Criteria andMealIn(List<Integer> values) {
            addCriterion("meal in", values, "meal");
            return (Criteria) this;
        }

        public Criteria andMealNotIn(List<Integer> values) {
            addCriterion("meal not in", values, "meal");
            return (Criteria) this;
        }

        public Criteria andMealBetween(Integer value1, Integer value2) {
            addCriterion("meal between", value1, value2, "meal");
            return (Criteria) this;
        }

        public Criteria andMealNotBetween(Integer value1, Integer value2) {
            addCriterion("meal not between", value1, value2, "meal");
            return (Criteria) this;
        }

        public Criteria andFlatfoodIsNull() {
            addCriterion("flatFood is null");
            return (Criteria) this;
        }

        public Criteria andFlatfoodIsNotNull() {
            addCriterion("flatFood is not null");
            return (Criteria) this;
        }

        public Criteria andFlatfoodEqualTo(Integer value) {
            addCriterion("flatFood =", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodNotEqualTo(Integer value) {
            addCriterion("flatFood <>", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodGreaterThan(Integer value) {
            addCriterion("flatFood >", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("flatFood >=", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodLessThan(Integer value) {
            addCriterion("flatFood <", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodLessThanOrEqualTo(Integer value) {
            addCriterion("flatFood <=", value, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodIn(List<Integer> values) {
            addCriterion("flatFood in", values, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodNotIn(List<Integer> values) {
            addCriterion("flatFood not in", values, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodBetween(Integer value1, Integer value2) {
            addCriterion("flatFood between", value1, value2, "flatfood");
            return (Criteria) this;
        }

        public Criteria andFlatfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("flatFood not between", value1, value2, "flatfood");
            return (Criteria) this;
        }

        public Criteria andMediumpriceIsNull() {
            addCriterion("mediumPrice is null");
            return (Criteria) this;
        }

        public Criteria andMediumpriceIsNotNull() {
            addCriterion("mediumPrice is not null");
            return (Criteria) this;
        }

        public Criteria andMediumpriceEqualTo(Integer value) {
            addCriterion("mediumPrice =", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceNotEqualTo(Integer value) {
            addCriterion("mediumPrice <>", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceGreaterThan(Integer value) {
            addCriterion("mediumPrice >", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("mediumPrice >=", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceLessThan(Integer value) {
            addCriterion("mediumPrice <", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceLessThanOrEqualTo(Integer value) {
            addCriterion("mediumPrice <=", value, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceIn(List<Integer> values) {
            addCriterion("mediumPrice in", values, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceNotIn(List<Integer> values) {
            addCriterion("mediumPrice not in", values, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceBetween(Integer value1, Integer value2) {
            addCriterion("mediumPrice between", value1, value2, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andMediumpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("mediumPrice not between", value1, value2, "mediumprice");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodIsNull() {
            addCriterion("upscaleFood is null");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodIsNotNull() {
            addCriterion("upscaleFood is not null");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodEqualTo(Integer value) {
            addCriterion("upscaleFood =", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodNotEqualTo(Integer value) {
            addCriterion("upscaleFood <>", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodGreaterThan(Integer value) {
            addCriterion("upscaleFood >", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("upscaleFood >=", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodLessThan(Integer value) {
            addCriterion("upscaleFood <", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodLessThanOrEqualTo(Integer value) {
            addCriterion("upscaleFood <=", value, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodIn(List<Integer> values) {
            addCriterion("upscaleFood in", values, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodNotIn(List<Integer> values) {
            addCriterion("upscaleFood not in", values, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodBetween(Integer value1, Integer value2) {
            addCriterion("upscaleFood between", value1, value2, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andUpscalefoodNotBetween(Integer value1, Integer value2) {
            addCriterion("upscaleFood not between", value1, value2, "upscalefood");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNull() {
            addCriterion("breakfast is null");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNotNull() {
            addCriterion("breakfast is not null");
            return (Criteria) this;
        }

        public Criteria andBreakfastEqualTo(Integer value) {
            addCriterion("breakfast =", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotEqualTo(Integer value) {
            addCriterion("breakfast <>", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThan(Integer value) {
            addCriterion("breakfast >", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThanOrEqualTo(Integer value) {
            addCriterion("breakfast >=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThan(Integer value) {
            addCriterion("breakfast <", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThanOrEqualTo(Integer value) {
            addCriterion("breakfast <=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastIn(List<Integer> values) {
            addCriterion("breakfast in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotIn(List<Integer> values) {
            addCriterion("breakfast not in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastBetween(Integer value1, Integer value2) {
            addCriterion("breakfast between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotBetween(Integer value1, Integer value2) {
            addCriterion("breakfast not between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBrunchIsNull() {
            addCriterion("brunch is null");
            return (Criteria) this;
        }

        public Criteria andBrunchIsNotNull() {
            addCriterion("brunch is not null");
            return (Criteria) this;
        }

        public Criteria andBrunchEqualTo(Integer value) {
            addCriterion("brunch =", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchNotEqualTo(Integer value) {
            addCriterion("brunch <>", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchGreaterThan(Integer value) {
            addCriterion("brunch >", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchGreaterThanOrEqualTo(Integer value) {
            addCriterion("brunch >=", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchLessThan(Integer value) {
            addCriterion("brunch <", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchLessThanOrEqualTo(Integer value) {
            addCriterion("brunch <=", value, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchIn(List<Integer> values) {
            addCriterion("brunch in", values, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchNotIn(List<Integer> values) {
            addCriterion("brunch not in", values, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchBetween(Integer value1, Integer value2) {
            addCriterion("brunch between", value1, value2, "brunch");
            return (Criteria) this;
        }

        public Criteria andBrunchNotBetween(Integer value1, Integer value2) {
            addCriterion("brunch not between", value1, value2, "brunch");
            return (Criteria) this;
        }

        public Criteria andLunchIsNull() {
            addCriterion("lunch is null");
            return (Criteria) this;
        }

        public Criteria andLunchIsNotNull() {
            addCriterion("lunch is not null");
            return (Criteria) this;
        }

        public Criteria andLunchEqualTo(Integer value) {
            addCriterion("lunch =", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotEqualTo(Integer value) {
            addCriterion("lunch <>", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchGreaterThan(Integer value) {
            addCriterion("lunch >", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchGreaterThanOrEqualTo(Integer value) {
            addCriterion("lunch >=", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchLessThan(Integer value) {
            addCriterion("lunch <", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchLessThanOrEqualTo(Integer value) {
            addCriterion("lunch <=", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchIn(List<Integer> values) {
            addCriterion("lunch in", values, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotIn(List<Integer> values) {
            addCriterion("lunch not in", values, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchBetween(Integer value1, Integer value2) {
            addCriterion("lunch between", value1, value2, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotBetween(Integer value1, Integer value2) {
            addCriterion("lunch not between", value1, value2, "lunch");
            return (Criteria) this;
        }

        public Criteria andDinnertimeIsNull() {
            addCriterion("dinnerTime is null");
            return (Criteria) this;
        }

        public Criteria andDinnertimeIsNotNull() {
            addCriterion("dinnerTime is not null");
            return (Criteria) this;
        }

        public Criteria andDinnertimeEqualTo(Integer value) {
            addCriterion("dinnerTime =", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeNotEqualTo(Integer value) {
            addCriterion("dinnerTime <>", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeGreaterThan(Integer value) {
            addCriterion("dinnerTime >", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dinnerTime >=", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeLessThan(Integer value) {
            addCriterion("dinnerTime <", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeLessThanOrEqualTo(Integer value) {
            addCriterion("dinnerTime <=", value, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeIn(List<Integer> values) {
            addCriterion("dinnerTime in", values, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeNotIn(List<Integer> values) {
            addCriterion("dinnerTime not in", values, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeBetween(Integer value1, Integer value2) {
            addCriterion("dinnerTime between", value1, value2, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andDinnertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("dinnerTime not between", value1, value2, "dinnertime");
            return (Criteria) this;
        }

        public Criteria andVegetariansIsNull() {
            addCriterion("vegetarians is null");
            return (Criteria) this;
        }

        public Criteria andVegetariansIsNotNull() {
            addCriterion("vegetarians is not null");
            return (Criteria) this;
        }

        public Criteria andVegetariansEqualTo(Integer value) {
            addCriterion("vegetarians =", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansNotEqualTo(Integer value) {
            addCriterion("vegetarians <>", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansGreaterThan(Integer value) {
            addCriterion("vegetarians >", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansGreaterThanOrEqualTo(Integer value) {
            addCriterion("vegetarians >=", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansLessThan(Integer value) {
            addCriterion("vegetarians <", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansLessThanOrEqualTo(Integer value) {
            addCriterion("vegetarians <=", value, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansIn(List<Integer> values) {
            addCriterion("vegetarians in", values, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansNotIn(List<Integer> values) {
            addCriterion("vegetarians not in", values, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansBetween(Integer value1, Integer value2) {
            addCriterion("vegetarians between", value1, value2, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andVegetariansNotBetween(Integer value1, Integer value2) {
            addCriterion("vegetarians not between", value1, value2, "vegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansIsNull() {
            addCriterion("strictVegetarians is null");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansIsNotNull() {
            addCriterion("strictVegetarians is not null");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansEqualTo(Integer value) {
            addCriterion("strictVegetarians =", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansNotEqualTo(Integer value) {
            addCriterion("strictVegetarians <>", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansGreaterThan(Integer value) {
            addCriterion("strictVegetarians >", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansGreaterThanOrEqualTo(Integer value) {
            addCriterion("strictVegetarians >=", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansLessThan(Integer value) {
            addCriterion("strictVegetarians <", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansLessThanOrEqualTo(Integer value) {
            addCriterion("strictVegetarians <=", value, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansIn(List<Integer> values) {
            addCriterion("strictVegetarians in", values, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansNotIn(List<Integer> values) {
            addCriterion("strictVegetarians not in", values, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansBetween(Integer value1, Integer value2) {
            addCriterion("strictVegetarians between", value1, value2, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andStrictvegetariansNotBetween(Integer value1, Integer value2) {
            addCriterion("strictVegetarians not between", value1, value2, "strictvegetarians");
            return (Criteria) this;
        }

        public Criteria andGlutenIsNull() {
            addCriterion("gluten is null");
            return (Criteria) this;
        }

        public Criteria andGlutenIsNotNull() {
            addCriterion("gluten is not null");
            return (Criteria) this;
        }

        public Criteria andGlutenEqualTo(Integer value) {
            addCriterion("gluten =", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenNotEqualTo(Integer value) {
            addCriterion("gluten <>", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenGreaterThan(Integer value) {
            addCriterion("gluten >", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenGreaterThanOrEqualTo(Integer value) {
            addCriterion("gluten >=", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenLessThan(Integer value) {
            addCriterion("gluten <", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenLessThanOrEqualTo(Integer value) {
            addCriterion("gluten <=", value, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenIn(List<Integer> values) {
            addCriterion("gluten in", values, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenNotIn(List<Integer> values) {
            addCriterion("gluten not in", values, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenBetween(Integer value1, Integer value2) {
            addCriterion("gluten between", value1, value2, "gluten");
            return (Criteria) this;
        }

        public Criteria andGlutenNotBetween(Integer value1, Integer value2) {
            addCriterion("gluten not between", value1, value2, "gluten");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineIsNull() {
            addCriterion("shanghaiCuisine is null");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineIsNotNull() {
            addCriterion("shanghaiCuisine is not null");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineEqualTo(Integer value) {
            addCriterion("shanghaiCuisine =", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineNotEqualTo(Integer value) {
            addCriterion("shanghaiCuisine <>", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineGreaterThan(Integer value) {
            addCriterion("shanghaiCuisine >", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineGreaterThanOrEqualTo(Integer value) {
            addCriterion("shanghaiCuisine >=", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineLessThan(Integer value) {
            addCriterion("shanghaiCuisine <", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineLessThanOrEqualTo(Integer value) {
            addCriterion("shanghaiCuisine <=", value, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineIn(List<Integer> values) {
            addCriterion("shanghaiCuisine in", values, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineNotIn(List<Integer> values) {
            addCriterion("shanghaiCuisine not in", values, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineBetween(Integer value1, Integer value2) {
            addCriterion("shanghaiCuisine between", value1, value2, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andShanghaicuisineNotBetween(Integer value1, Integer value2) {
            addCriterion("shanghaiCuisine not between", value1, value2, "shanghaicuisine");
            return (Criteria) this;
        }

        public Criteria andChinesefoodIsNull() {
            addCriterion("chineseFood is null");
            return (Criteria) this;
        }

        public Criteria andChinesefoodIsNotNull() {
            addCriterion("chineseFood is not null");
            return (Criteria) this;
        }

        public Criteria andChinesefoodEqualTo(Integer value) {
            addCriterion("chineseFood =", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodNotEqualTo(Integer value) {
            addCriterion("chineseFood <>", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodGreaterThan(Integer value) {
            addCriterion("chineseFood >", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("chineseFood >=", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodLessThan(Integer value) {
            addCriterion("chineseFood <", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodLessThanOrEqualTo(Integer value) {
            addCriterion("chineseFood <=", value, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodIn(List<Integer> values) {
            addCriterion("chineseFood in", values, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodNotIn(List<Integer> values) {
            addCriterion("chineseFood not in", values, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodBetween(Integer value1, Integer value2) {
            addCriterion("chineseFood between", value1, value2, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andChinesefoodNotBetween(Integer value1, Integer value2) {
            addCriterion("chineseFood not between", value1, value2, "chinesefood");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineIsNull() {
            addCriterion("asianCuisine is null");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineIsNotNull() {
            addCriterion("asianCuisine is not null");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineEqualTo(Integer value) {
            addCriterion("asianCuisine =", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineNotEqualTo(Integer value) {
            addCriterion("asianCuisine <>", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineGreaterThan(Integer value) {
            addCriterion("asianCuisine >", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineGreaterThanOrEqualTo(Integer value) {
            addCriterion("asianCuisine >=", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineLessThan(Integer value) {
            addCriterion("asianCuisine <", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineLessThanOrEqualTo(Integer value) {
            addCriterion("asianCuisine <=", value, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineIn(List<Integer> values) {
            addCriterion("asianCuisine in", values, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineNotIn(List<Integer> values) {
            addCriterion("asianCuisine not in", values, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineBetween(Integer value1, Integer value2) {
            addCriterion("asianCuisine between", value1, value2, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andAsiancuisineNotBetween(Integer value1, Integer value2) {
            addCriterion("asianCuisine not between", value1, value2, "asiancuisine");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodIsNull() {
            addCriterion("healthyFood is null");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodIsNotNull() {
            addCriterion("healthyFood is not null");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodEqualTo(Integer value) {
            addCriterion("healthyFood =", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodNotEqualTo(Integer value) {
            addCriterion("healthyFood <>", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodGreaterThan(Integer value) {
            addCriterion("healthyFood >", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("healthyFood >=", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodLessThan(Integer value) {
            addCriterion("healthyFood <", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodLessThanOrEqualTo(Integer value) {
            addCriterion("healthyFood <=", value, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodIn(List<Integer> values) {
            addCriterion("healthyFood in", values, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodNotIn(List<Integer> values) {
            addCriterion("healthyFood not in", values, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodBetween(Integer value1, Integer value2) {
            addCriterion("healthyFood between", value1, value2, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andHealthyfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("healthyFood not between", value1, value2, "healthyfood");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineIsNull() {
            addCriterion("indiaCuisine is null");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineIsNotNull() {
            addCriterion("indiaCuisine is not null");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineEqualTo(Integer value) {
            addCriterion("indiaCuisine =", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineNotEqualTo(Integer value) {
            addCriterion("indiaCuisine <>", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineGreaterThan(Integer value) {
            addCriterion("indiaCuisine >", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineGreaterThanOrEqualTo(Integer value) {
            addCriterion("indiaCuisine >=", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineLessThan(Integer value) {
            addCriterion("indiaCuisine <", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineLessThanOrEqualTo(Integer value) {
            addCriterion("indiaCuisine <=", value, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineIn(List<Integer> values) {
            addCriterion("indiaCuisine in", values, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineNotIn(List<Integer> values) {
            addCriterion("indiaCuisine not in", values, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineBetween(Integer value1, Integer value2) {
            addCriterion("indiaCuisine between", value1, value2, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andIndiacuisineNotBetween(Integer value1, Integer value2) {
            addCriterion("indiaCuisine not between", value1, value2, "indiacuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineIsNull() {
            addCriterion("nationalCuisine is null");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineIsNotNull() {
            addCriterion("nationalCuisine is not null");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineEqualTo(Integer value) {
            addCriterion("nationalCuisine =", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineNotEqualTo(Integer value) {
            addCriterion("nationalCuisine <>", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineGreaterThan(Integer value) {
            addCriterion("nationalCuisine >", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineGreaterThanOrEqualTo(Integer value) {
            addCriterion("nationalCuisine >=", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineLessThan(Integer value) {
            addCriterion("nationalCuisine <", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineLessThanOrEqualTo(Integer value) {
            addCriterion("nationalCuisine <=", value, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineIn(List<Integer> values) {
            addCriterion("nationalCuisine in", values, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineNotIn(List<Integer> values) {
            addCriterion("nationalCuisine not in", values, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineBetween(Integer value1, Integer value2) {
            addCriterion("nationalCuisine between", value1, value2, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andNationalcuisineNotBetween(Integer value1, Integer value2) {
            addCriterion("nationalCuisine not between", value1, value2, "nationalcuisine");
            return (Criteria) this;
        }

        public Criteria andCafeIsNull() {
            addCriterion("cafe is null");
            return (Criteria) this;
        }

        public Criteria andCafeIsNotNull() {
            addCriterion("cafe is not null");
            return (Criteria) this;
        }

        public Criteria andCafeEqualTo(Integer value) {
            addCriterion("cafe =", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeNotEqualTo(Integer value) {
            addCriterion("cafe <>", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeGreaterThan(Integer value) {
            addCriterion("cafe >", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cafe >=", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeLessThan(Integer value) {
            addCriterion("cafe <", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeLessThanOrEqualTo(Integer value) {
            addCriterion("cafe <=", value, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeIn(List<Integer> values) {
            addCriterion("cafe in", values, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeNotIn(List<Integer> values) {
            addCriterion("cafe not in", values, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeBetween(Integer value1, Integer value2) {
            addCriterion("cafe between", value1, value2, "cafe");
            return (Criteria) this;
        }

        public Criteria andCafeNotBetween(Integer value1, Integer value2) {
            addCriterion("cafe not between", value1, value2, "cafe");
            return (Criteria) this;
        }

        public Criteria andFastfoodIsNull() {
            addCriterion("fastFood is null");
            return (Criteria) this;
        }

        public Criteria andFastfoodIsNotNull() {
            addCriterion("fastFood is not null");
            return (Criteria) this;
        }

        public Criteria andFastfoodEqualTo(Integer value) {
            addCriterion("fastFood =", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodNotEqualTo(Integer value) {
            addCriterion("fastFood <>", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodGreaterThan(Integer value) {
            addCriterion("fastFood >", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("fastFood >=", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodLessThan(Integer value) {
            addCriterion("fastFood <", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodLessThanOrEqualTo(Integer value) {
            addCriterion("fastFood <=", value, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodIn(List<Integer> values) {
            addCriterion("fastFood in", values, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodNotIn(List<Integer> values) {
            addCriterion("fastFood not in", values, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodBetween(Integer value1, Integer value2) {
            addCriterion("fastFood between", value1, value2, "fastfood");
            return (Criteria) this;
        }

        public Criteria andFastfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("fastFood not between", value1, value2, "fastfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodIsNull() {
            addCriterion("ItalianFood is null");
            return (Criteria) this;
        }

        public Criteria andItalianfoodIsNotNull() {
            addCriterion("ItalianFood is not null");
            return (Criteria) this;
        }

        public Criteria andItalianfoodEqualTo(Integer value) {
            addCriterion("ItalianFood =", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodNotEqualTo(Integer value) {
            addCriterion("ItalianFood <>", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodGreaterThan(Integer value) {
            addCriterion("ItalianFood >", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("ItalianFood >=", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodLessThan(Integer value) {
            addCriterion("ItalianFood <", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodLessThanOrEqualTo(Integer value) {
            addCriterion("ItalianFood <=", value, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodIn(List<Integer> values) {
            addCriterion("ItalianFood in", values, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodNotIn(List<Integer> values) {
            addCriterion("ItalianFood not in", values, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodBetween(Integer value1, Integer value2) {
            addCriterion("ItalianFood between", value1, value2, "italianfood");
            return (Criteria) this;
        }

        public Criteria andItalianfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("ItalianFood not between", value1, value2, "italianfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodIsNull() {
            addCriterion("europeanFood is null");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodIsNotNull() {
            addCriterion("europeanFood is not null");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodEqualTo(Integer value) {
            addCriterion("europeanFood =", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodNotEqualTo(Integer value) {
            addCriterion("europeanFood <>", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodGreaterThan(Integer value) {
            addCriterion("europeanFood >", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("europeanFood >=", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodLessThan(Integer value) {
            addCriterion("europeanFood <", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodLessThanOrEqualTo(Integer value) {
            addCriterion("europeanFood <=", value, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodIn(List<Integer> values) {
            addCriterion("europeanFood in", values, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodNotIn(List<Integer> values) {
            addCriterion("europeanFood not in", values, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodBetween(Integer value1, Integer value2) {
            addCriterion("europeanFood between", value1, value2, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andEuropeanfoodNotBetween(Integer value1, Integer value2) {
            addCriterion("europeanFood not between", value1, value2, "europeanfood");
            return (Criteria) this;
        }

        public Criteria andSoupIsNull() {
            addCriterion("soup is null");
            return (Criteria) this;
        }

        public Criteria andSoupIsNotNull() {
            addCriterion("soup is not null");
            return (Criteria) this;
        }

        public Criteria andSoupEqualTo(Integer value) {
            addCriterion("soup =", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupNotEqualTo(Integer value) {
            addCriterion("soup <>", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupGreaterThan(Integer value) {
            addCriterion("soup >", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupGreaterThanOrEqualTo(Integer value) {
            addCriterion("soup >=", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupLessThan(Integer value) {
            addCriterion("soup <", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupLessThanOrEqualTo(Integer value) {
            addCriterion("soup <=", value, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupIn(List<Integer> values) {
            addCriterion("soup in", values, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupNotIn(List<Integer> values) {
            addCriterion("soup not in", values, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupBetween(Integer value1, Integer value2) {
            addCriterion("soup between", value1, value2, "soup");
            return (Criteria) this;
        }

        public Criteria andSoupNotBetween(Integer value1, Integer value2) {
            addCriterion("soup not between", value1, value2, "soup");
            return (Criteria) this;
        }

        public Criteria andMixeddishIsNull() {
            addCriterion("mixedDish is null");
            return (Criteria) this;
        }

        public Criteria andMixeddishIsNotNull() {
            addCriterion("mixedDish is not null");
            return (Criteria) this;
        }

        public Criteria andMixeddishEqualTo(Integer value) {
            addCriterion("mixedDish =", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishNotEqualTo(Integer value) {
            addCriterion("mixedDish <>", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishGreaterThan(Integer value) {
            addCriterion("mixedDish >", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishGreaterThanOrEqualTo(Integer value) {
            addCriterion("mixedDish >=", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishLessThan(Integer value) {
            addCriterion("mixedDish <", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishLessThanOrEqualTo(Integer value) {
            addCriterion("mixedDish <=", value, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishIn(List<Integer> values) {
            addCriterion("mixedDish in", values, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishNotIn(List<Integer> values) {
            addCriterion("mixedDish not in", values, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishBetween(Integer value1, Integer value2) {
            addCriterion("mixedDish between", value1, value2, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andMixeddishNotBetween(Integer value1, Integer value2) {
            addCriterion("mixedDish not between", value1, value2, "mixeddish");
            return (Criteria) this;
        }

        public Criteria andAmericancookingIsNull() {
            addCriterion("americanCooking is null");
            return (Criteria) this;
        }

        public Criteria andAmericancookingIsNotNull() {
            addCriterion("americanCooking is not null");
            return (Criteria) this;
        }

        public Criteria andAmericancookingEqualTo(Integer value) {
            addCriterion("americanCooking =", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingNotEqualTo(Integer value) {
            addCriterion("americanCooking <>", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingGreaterThan(Integer value) {
            addCriterion("americanCooking >", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingGreaterThanOrEqualTo(Integer value) {
            addCriterion("americanCooking >=", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingLessThan(Integer value) {
            addCriterion("americanCooking <", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingLessThanOrEqualTo(Integer value) {
            addCriterion("americanCooking <=", value, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingIn(List<Integer> values) {
            addCriterion("americanCooking in", values, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingNotIn(List<Integer> values) {
            addCriterion("americanCooking not in", values, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingBetween(Integer value1, Integer value2) {
            addCriterion("americanCooking between", value1, value2, "americancooking");
            return (Criteria) this;
        }

        public Criteria andAmericancookingNotBetween(Integer value1, Integer value2) {
            addCriterion("americanCooking not between", value1, value2, "americancooking");
            return (Criteria) this;
        }

        public Criteria andOnlinebookIsNull() {
            addCriterion("onlineBook is null");
            return (Criteria) this;
        }

        public Criteria andOnlinebookIsNotNull() {
            addCriterion("onlineBook is not null");
            return (Criteria) this;
        }

        public Criteria andOnlinebookEqualTo(Integer value) {
            addCriterion("onlineBook =", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookNotEqualTo(Integer value) {
            addCriterion("onlineBook <>", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookGreaterThan(Integer value) {
            addCriterion("onlineBook >", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookGreaterThanOrEqualTo(Integer value) {
            addCriterion("onlineBook >=", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookLessThan(Integer value) {
            addCriterion("onlineBook <", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookLessThanOrEqualTo(Integer value) {
            addCriterion("onlineBook <=", value, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookIn(List<Integer> values) {
            addCriterion("onlineBook in", values, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookNotIn(List<Integer> values) {
            addCriterion("onlineBook not in", values, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookBetween(Integer value1, Integer value2) {
            addCriterion("onlineBook between", value1, value2, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andOnlinebookNotBetween(Integer value1, Integer value2) {
            addCriterion("onlineBook not between", value1, value2, "onlinebook");
            return (Criteria) this;
        }

        public Criteria andRestaurantIsNull() {
            addCriterion("restaurant is null");
            return (Criteria) this;
        }

        public Criteria andRestaurantIsNotNull() {
            addCriterion("restaurant is not null");
            return (Criteria) this;
        }

        public Criteria andRestaurantEqualTo(Integer value) {
            addCriterion("restaurant =", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantNotEqualTo(Integer value) {
            addCriterion("restaurant <>", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantGreaterThan(Integer value) {
            addCriterion("restaurant >", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantGreaterThanOrEqualTo(Integer value) {
            addCriterion("restaurant >=", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantLessThan(Integer value) {
            addCriterion("restaurant <", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantLessThanOrEqualTo(Integer value) {
            addCriterion("restaurant <=", value, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantIn(List<Integer> values) {
            addCriterion("restaurant in", values, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantNotIn(List<Integer> values) {
            addCriterion("restaurant not in", values, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantBetween(Integer value1, Integer value2) {
            addCriterion("restaurant between", value1, value2, "restaurant");
            return (Criteria) this;
        }

        public Criteria andRestaurantNotBetween(Integer value1, Integer value2) {
            addCriterion("restaurant not between", value1, value2, "restaurant");
            return (Criteria) this;
        }

        public Criteria andDessertIsNull() {
            addCriterion("dessert is null");
            return (Criteria) this;
        }

        public Criteria andDessertIsNotNull() {
            addCriterion("dessert is not null");
            return (Criteria) this;
        }

        public Criteria andDessertEqualTo(Integer value) {
            addCriterion("dessert =", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertNotEqualTo(Integer value) {
            addCriterion("dessert <>", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertGreaterThan(Integer value) {
            addCriterion("dessert >", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertGreaterThanOrEqualTo(Integer value) {
            addCriterion("dessert >=", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertLessThan(Integer value) {
            addCriterion("dessert <", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertLessThanOrEqualTo(Integer value) {
            addCriterion("dessert <=", value, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertIn(List<Integer> values) {
            addCriterion("dessert in", values, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertNotIn(List<Integer> values) {
            addCriterion("dessert not in", values, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertBetween(Integer value1, Integer value2) {
            addCriterion("dessert between", value1, value2, "dessert");
            return (Criteria) this;
        }

        public Criteria andDessertNotBetween(Integer value1, Integer value2) {
            addCriterion("dessert not between", value1, value2, "dessert");
            return (Criteria) this;
        }

        public Criteria andCafeteaIsNull() {
            addCriterion("cafeTea is null");
            return (Criteria) this;
        }

        public Criteria andCafeteaIsNotNull() {
            addCriterion("cafeTea is not null");
            return (Criteria) this;
        }

        public Criteria andCafeteaEqualTo(Integer value) {
            addCriterion("cafeTea =", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaNotEqualTo(Integer value) {
            addCriterion("cafeTea <>", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaGreaterThan(Integer value) {
            addCriterion("cafeTea >", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaGreaterThanOrEqualTo(Integer value) {
            addCriterion("cafeTea >=", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaLessThan(Integer value) {
            addCriterion("cafeTea <", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaLessThanOrEqualTo(Integer value) {
            addCriterion("cafeTea <=", value, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaIn(List<Integer> values) {
            addCriterion("cafeTea in", values, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaNotIn(List<Integer> values) {
            addCriterion("cafeTea not in", values, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaBetween(Integer value1, Integer value2) {
            addCriterion("cafeTea between", value1, value2, "cafetea");
            return (Criteria) this;
        }

        public Criteria andCafeteaNotBetween(Integer value1, Integer value2) {
            addCriterion("cafeTea not between", value1, value2, "cafetea");
            return (Criteria) this;
        }

        public Criteria andBakeriesIsNull() {
            addCriterion("bakeries is null");
            return (Criteria) this;
        }

        public Criteria andBakeriesIsNotNull() {
            addCriterion("bakeries is not null");
            return (Criteria) this;
        }

        public Criteria andBakeriesEqualTo(Integer value) {
            addCriterion("bakeries =", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesNotEqualTo(Integer value) {
            addCriterion("bakeries <>", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesGreaterThan(Integer value) {
            addCriterion("bakeries >", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesGreaterThanOrEqualTo(Integer value) {
            addCriterion("bakeries >=", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesLessThan(Integer value) {
            addCriterion("bakeries <", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesLessThanOrEqualTo(Integer value) {
            addCriterion("bakeries <=", value, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesIn(List<Integer> values) {
            addCriterion("bakeries in", values, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesNotIn(List<Integer> values) {
            addCriterion("bakeries not in", values, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesBetween(Integer value1, Integer value2) {
            addCriterion("bakeries between", value1, value2, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBakeriesNotBetween(Integer value1, Integer value2) {
            addCriterion("bakeries not between", value1, value2, "bakeries");
            return (Criteria) this;
        }

        public Criteria andBarIsNull() {
            addCriterion("bar is null");
            return (Criteria) this;
        }

        public Criteria andBarIsNotNull() {
            addCriterion("bar is not null");
            return (Criteria) this;
        }

        public Criteria andBarEqualTo(Integer value) {
            addCriterion("bar =", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotEqualTo(Integer value) {
            addCriterion("bar <>", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarGreaterThan(Integer value) {
            addCriterion("bar >", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarGreaterThanOrEqualTo(Integer value) {
            addCriterion("bar >=", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarLessThan(Integer value) {
            addCriterion("bar <", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarLessThanOrEqualTo(Integer value) {
            addCriterion("bar <=", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarIn(List<Integer> values) {
            addCriterion("bar in", values, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotIn(List<Integer> values) {
            addCriterion("bar not in", values, "bar");
            return (Criteria) this;
        }

        public Criteria andBarBetween(Integer value1, Integer value2) {
            addCriterion("bar between", value1, value2, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotBetween(Integer value1, Integer value2) {
            addCriterion("bar not between", value1, value2, "bar");
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