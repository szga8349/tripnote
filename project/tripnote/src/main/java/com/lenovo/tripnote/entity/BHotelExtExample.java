package com.lenovo.tripnote.entity;

import java.util.ArrayList;
import java.util.List;

public class BHotelExtExample {
    protected String pk_name;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BHotelExtExample() {
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

        public Criteria andHotelIdIsNull() {
            addCriterion("hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNotNull() {
            addCriterion("hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotelIdEqualTo(Integer value) {
            addCriterion("hotel_id =", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotEqualTo(Integer value) {
            addCriterion("hotel_id <>", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThan(Integer value) {
            addCriterion("hotel_id >", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hotel_id >=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThan(Integer value) {
            addCriterion("hotel_id <", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThanOrEqualTo(Integer value) {
            addCriterion("hotel_id <=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIn(List<Integer> values) {
            addCriterion("hotel_id in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotIn(List<Integer> values) {
            addCriterion("hotel_id not in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdBetween(Integer value1, Integer value2) {
            addCriterion("hotel_id between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hotel_id not between", value1, value2, "hotelId");
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

        public Criteria andAirconditionerIsNull() {
            addCriterion("airConditioner is null");
            return (Criteria) this;
        }

        public Criteria andAirconditionerIsNotNull() {
            addCriterion("airConditioner is not null");
            return (Criteria) this;
        }

        public Criteria andAirconditionerEqualTo(Integer value) {
            addCriterion("airConditioner =", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerNotEqualTo(Integer value) {
            addCriterion("airConditioner <>", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerGreaterThan(Integer value) {
            addCriterion("airConditioner >", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerGreaterThanOrEqualTo(Integer value) {
            addCriterion("airConditioner >=", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerLessThan(Integer value) {
            addCriterion("airConditioner <", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerLessThanOrEqualTo(Integer value) {
            addCriterion("airConditioner <=", value, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerIn(List<Integer> values) {
            addCriterion("airConditioner in", values, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerNotIn(List<Integer> values) {
            addCriterion("airConditioner not in", values, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerBetween(Integer value1, Integer value2) {
            addCriterion("airConditioner between", value1, value2, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andAirconditionerNotBetween(Integer value1, Integer value2) {
            addCriterion("airConditioner not between", value1, value2, "airconditioner");
            return (Criteria) this;
        }

        public Criteria andPoolIsNull() {
            addCriterion("pool is null");
            return (Criteria) this;
        }

        public Criteria andPoolIsNotNull() {
            addCriterion("pool is not null");
            return (Criteria) this;
        }

        public Criteria andPoolEqualTo(Integer value) {
            addCriterion("pool =", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolNotEqualTo(Integer value) {
            addCriterion("pool <>", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolGreaterThan(Integer value) {
            addCriterion("pool >", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolGreaterThanOrEqualTo(Integer value) {
            addCriterion("pool >=", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolLessThan(Integer value) {
            addCriterion("pool <", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolLessThanOrEqualTo(Integer value) {
            addCriterion("pool <=", value, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolIn(List<Integer> values) {
            addCriterion("pool in", values, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolNotIn(List<Integer> values) {
            addCriterion("pool not in", values, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolBetween(Integer value1, Integer value2) {
            addCriterion("pool between", value1, value2, "pool");
            return (Criteria) this;
        }

        public Criteria andPoolNotBetween(Integer value1, Integer value2) {
            addCriterion("pool not between", value1, value2, "pool");
            return (Criteria) this;
        }

        public Criteria andSmokingIsNull() {
            addCriterion("smoking is null");
            return (Criteria) this;
        }

        public Criteria andSmokingIsNotNull() {
            addCriterion("smoking is not null");
            return (Criteria) this;
        }

        public Criteria andSmokingEqualTo(Integer value) {
            addCriterion("smoking =", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingNotEqualTo(Integer value) {
            addCriterion("smoking <>", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingGreaterThan(Integer value) {
            addCriterion("smoking >", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingGreaterThanOrEqualTo(Integer value) {
            addCriterion("smoking >=", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingLessThan(Integer value) {
            addCriterion("smoking <", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingLessThanOrEqualTo(Integer value) {
            addCriterion("smoking <=", value, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingIn(List<Integer> values) {
            addCriterion("smoking in", values, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingNotIn(List<Integer> values) {
            addCriterion("smoking not in", values, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingBetween(Integer value1, Integer value2) {
            addCriterion("smoking between", value1, value2, "smoking");
            return (Criteria) this;
        }

        public Criteria andSmokingNotBetween(Integer value1, Integer value2) {
            addCriterion("smoking not between", value1, value2, "smoking");
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

        public Criteria andRoomserviceIsNull() {
            addCriterion("roomService is null");
            return (Criteria) this;
        }

        public Criteria andRoomserviceIsNotNull() {
            addCriterion("roomService is not null");
            return (Criteria) this;
        }

        public Criteria andRoomserviceEqualTo(Integer value) {
            addCriterion("roomService =", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceNotEqualTo(Integer value) {
            addCriterion("roomService <>", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceGreaterThan(Integer value) {
            addCriterion("roomService >", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceGreaterThanOrEqualTo(Integer value) {
            addCriterion("roomService >=", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceLessThan(Integer value) {
            addCriterion("roomService <", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceLessThanOrEqualTo(Integer value) {
            addCriterion("roomService <=", value, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceIn(List<Integer> values) {
            addCriterion("roomService in", values, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceNotIn(List<Integer> values) {
            addCriterion("roomService not in", values, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceBetween(Integer value1, Integer value2) {
            addCriterion("roomService between", value1, value2, "roomservice");
            return (Criteria) this;
        }

        public Criteria andRoomserviceNotBetween(Integer value1, Integer value2) {
            addCriterion("roomService not between", value1, value2, "roomservice");
            return (Criteria) this;
        }

        public Criteria andBarlobbyIsNull() {
            addCriterion("barlobby is null");
            return (Criteria) this;
        }

        public Criteria andBarlobbyIsNotNull() {
            addCriterion("barlobby is not null");
            return (Criteria) this;
        }

        public Criteria andBarlobbyEqualTo(Integer value) {
            addCriterion("barlobby =", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyNotEqualTo(Integer value) {
            addCriterion("barlobby <>", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyGreaterThan(Integer value) {
            addCriterion("barlobby >", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyGreaterThanOrEqualTo(Integer value) {
            addCriterion("barlobby >=", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyLessThan(Integer value) {
            addCriterion("barlobby <", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyLessThanOrEqualTo(Integer value) {
            addCriterion("barlobby <=", value, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyIn(List<Integer> values) {
            addCriterion("barlobby in", values, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyNotIn(List<Integer> values) {
            addCriterion("barlobby not in", values, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyBetween(Integer value1, Integer value2) {
            addCriterion("barlobby between", value1, value2, "barlobby");
            return (Criteria) this;
        }

        public Criteria andBarlobbyNotBetween(Integer value1, Integer value2) {
            addCriterion("barlobby not between", value1, value2, "barlobby");
            return (Criteria) this;
        }

        public Criteria andSpaIsNull() {
            addCriterion("spa is null");
            return (Criteria) this;
        }

        public Criteria andSpaIsNotNull() {
            addCriterion("spa is not null");
            return (Criteria) this;
        }

        public Criteria andSpaEqualTo(Integer value) {
            addCriterion("spa =", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaNotEqualTo(Integer value) {
            addCriterion("spa <>", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaGreaterThan(Integer value) {
            addCriterion("spa >", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaGreaterThanOrEqualTo(Integer value) {
            addCriterion("spa >=", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaLessThan(Integer value) {
            addCriterion("spa <", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaLessThanOrEqualTo(Integer value) {
            addCriterion("spa <=", value, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaIn(List<Integer> values) {
            addCriterion("spa in", values, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaNotIn(List<Integer> values) {
            addCriterion("spa not in", values, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaBetween(Integer value1, Integer value2) {
            addCriterion("spa between", value1, value2, "spa");
            return (Criteria) this;
        }

        public Criteria andSpaNotBetween(Integer value1, Integer value2) {
            addCriterion("spa not between", value1, value2, "spa");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterIsNull() {
            addCriterion("fitnessCenter is null");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterIsNotNull() {
            addCriterion("fitnessCenter is not null");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterEqualTo(Integer value) {
            addCriterion("fitnessCenter =", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterNotEqualTo(Integer value) {
            addCriterion("fitnessCenter <>", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterGreaterThan(Integer value) {
            addCriterion("fitnessCenter >", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterGreaterThanOrEqualTo(Integer value) {
            addCriterion("fitnessCenter >=", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterLessThan(Integer value) {
            addCriterion("fitnessCenter <", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterLessThanOrEqualTo(Integer value) {
            addCriterion("fitnessCenter <=", value, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterIn(List<Integer> values) {
            addCriterion("fitnessCenter in", values, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterNotIn(List<Integer> values) {
            addCriterion("fitnessCenter not in", values, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterBetween(Integer value1, Integer value2) {
            addCriterion("fitnessCenter between", value1, value2, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andFitnesscenterNotBetween(Integer value1, Integer value2) {
            addCriterion("fitnessCenter not between", value1, value2, "fitnesscenter");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleIsNull() {
            addCriterion("airportShuttle is null");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleIsNotNull() {
            addCriterion("airportShuttle is not null");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleEqualTo(Integer value) {
            addCriterion("airportShuttle =", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleNotEqualTo(Integer value) {
            addCriterion("airportShuttle <>", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleGreaterThan(Integer value) {
            addCriterion("airportShuttle >", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleGreaterThanOrEqualTo(Integer value) {
            addCriterion("airportShuttle >=", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleLessThan(Integer value) {
            addCriterion("airportShuttle <", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleLessThanOrEqualTo(Integer value) {
            addCriterion("airportShuttle <=", value, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleIn(List<Integer> values) {
            addCriterion("airportShuttle in", values, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleNotIn(List<Integer> values) {
            addCriterion("airportShuttle not in", values, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleBetween(Integer value1, Integer value2) {
            addCriterion("airportShuttle between", value1, value2, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andAirportshuttleNotBetween(Integer value1, Integer value2) {
            addCriterion("airportShuttle not between", value1, value2, "airportshuttle");
            return (Criteria) this;
        }

        public Criteria andConferenceroomIsNull() {
            addCriterion("conferenceRoom is null");
            return (Criteria) this;
        }

        public Criteria andConferenceroomIsNotNull() {
            addCriterion("conferenceRoom is not null");
            return (Criteria) this;
        }

        public Criteria andConferenceroomEqualTo(Integer value) {
            addCriterion("conferenceRoom =", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomNotEqualTo(Integer value) {
            addCriterion("conferenceRoom <>", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomGreaterThan(Integer value) {
            addCriterion("conferenceRoom >", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomGreaterThanOrEqualTo(Integer value) {
            addCriterion("conferenceRoom >=", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomLessThan(Integer value) {
            addCriterion("conferenceRoom <", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomLessThanOrEqualTo(Integer value) {
            addCriterion("conferenceRoom <=", value, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomIn(List<Integer> values) {
            addCriterion("conferenceRoom in", values, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomNotIn(List<Integer> values) {
            addCriterion("conferenceRoom not in", values, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomBetween(Integer value1, Integer value2) {
            addCriterion("conferenceRoom between", value1, value2, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andConferenceroomNotBetween(Integer value1, Integer value2) {
            addCriterion("conferenceRoom not between", value1, value2, "conferenceroom");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceIsNull() {
            addCriterion("laundryService is null");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceIsNotNull() {
            addCriterion("laundryService is not null");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceEqualTo(Integer value) {
            addCriterion("laundryService =", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceNotEqualTo(Integer value) {
            addCriterion("laundryService <>", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceGreaterThan(Integer value) {
            addCriterion("laundryService >", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceGreaterThanOrEqualTo(Integer value) {
            addCriterion("laundryService >=", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceLessThan(Integer value) {
            addCriterion("laundryService <", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceLessThanOrEqualTo(Integer value) {
            addCriterion("laundryService <=", value, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceIn(List<Integer> values) {
            addCriterion("laundryService in", values, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceNotIn(List<Integer> values) {
            addCriterion("laundryService not in", values, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceBetween(Integer value1, Integer value2) {
            addCriterion("laundryService between", value1, value2, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andLaundryserviceNotBetween(Integer value1, Integer value2) {
            addCriterion("laundryService not between", value1, value2, "laundryservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceIsNull() {
            addCriterion("conciergeService is null");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceIsNotNull() {
            addCriterion("conciergeService is not null");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceEqualTo(Integer value) {
            addCriterion("conciergeService =", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceNotEqualTo(Integer value) {
            addCriterion("conciergeService <>", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceGreaterThan(Integer value) {
            addCriterion("conciergeService >", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceGreaterThanOrEqualTo(Integer value) {
            addCriterion("conciergeService >=", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceLessThan(Integer value) {
            addCriterion("conciergeService <", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceLessThanOrEqualTo(Integer value) {
            addCriterion("conciergeService <=", value, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceIn(List<Integer> values) {
            addCriterion("conciergeService in", values, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceNotIn(List<Integer> values) {
            addCriterion("conciergeService not in", values, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceBetween(Integer value1, Integer value2) {
            addCriterion("conciergeService between", value1, value2, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andConciergeserviceNotBetween(Integer value1, Integer value2) {
            addCriterion("conciergeService not between", value1, value2, "conciergeservice");
            return (Criteria) this;
        }

        public Criteria andBanquethallIsNull() {
            addCriterion("banquetHall is null");
            return (Criteria) this;
        }

        public Criteria andBanquethallIsNotNull() {
            addCriterion("banquetHall is not null");
            return (Criteria) this;
        }

        public Criteria andBanquethallEqualTo(Integer value) {
            addCriterion("banquetHall =", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallNotEqualTo(Integer value) {
            addCriterion("banquetHall <>", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallGreaterThan(Integer value) {
            addCriterion("banquetHall >", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallGreaterThanOrEqualTo(Integer value) {
            addCriterion("banquetHall >=", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallLessThan(Integer value) {
            addCriterion("banquetHall <", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallLessThanOrEqualTo(Integer value) {
            addCriterion("banquetHall <=", value, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallIn(List<Integer> values) {
            addCriterion("banquetHall in", values, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallNotIn(List<Integer> values) {
            addCriterion("banquetHall not in", values, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallBetween(Integer value1, Integer value2) {
            addCriterion("banquetHall between", value1, value2, "banquethall");
            return (Criteria) this;
        }

        public Criteria andBanquethallNotBetween(Integer value1, Integer value2) {
            addCriterion("banquetHall not between", value1, value2, "banquethall");
            return (Criteria) this;
        }

        public Criteria andMultilingualIsNull() {
            addCriterion("multilingual is null");
            return (Criteria) this;
        }

        public Criteria andMultilingualIsNotNull() {
            addCriterion("multilingual is not null");
            return (Criteria) this;
        }

        public Criteria andMultilingualEqualTo(Integer value) {
            addCriterion("multilingual =", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualNotEqualTo(Integer value) {
            addCriterion("multilingual <>", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualGreaterThan(Integer value) {
            addCriterion("multilingual >", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualGreaterThanOrEqualTo(Integer value) {
            addCriterion("multilingual >=", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualLessThan(Integer value) {
            addCriterion("multilingual <", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualLessThanOrEqualTo(Integer value) {
            addCriterion("multilingual <=", value, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualIn(List<Integer> values) {
            addCriterion("multilingual in", values, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualNotIn(List<Integer> values) {
            addCriterion("multilingual not in", values, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualBetween(Integer value1, Integer value2) {
            addCriterion("multilingual between", value1, value2, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMultilingualNotBetween(Integer value1, Integer value2) {
            addCriterion("multilingual not between", value1, value2, "multilingual");
            return (Criteria) this;
        }

        public Criteria andMinibarIsNull() {
            addCriterion("miniBar is null");
            return (Criteria) this;
        }

        public Criteria andMinibarIsNotNull() {
            addCriterion("miniBar is not null");
            return (Criteria) this;
        }

        public Criteria andMinibarEqualTo(Integer value) {
            addCriterion("miniBar =", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarNotEqualTo(Integer value) {
            addCriterion("miniBar <>", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarGreaterThan(Integer value) {
            addCriterion("miniBar >", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarGreaterThanOrEqualTo(Integer value) {
            addCriterion("miniBar >=", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarLessThan(Integer value) {
            addCriterion("miniBar <", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarLessThanOrEqualTo(Integer value) {
            addCriterion("miniBar <=", value, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarIn(List<Integer> values) {
            addCriterion("miniBar in", values, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarNotIn(List<Integer> values) {
            addCriterion("miniBar not in", values, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarBetween(Integer value1, Integer value2) {
            addCriterion("miniBar between", value1, value2, "minibar");
            return (Criteria) this;
        }

        public Criteria andMinibarNotBetween(Integer value1, Integer value2) {
            addCriterion("miniBar not between", value1, value2, "minibar");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolIsNull() {
            addCriterion("indoorPool is null");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolIsNotNull() {
            addCriterion("indoorPool is not null");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolEqualTo(Integer value) {
            addCriterion("indoorPool =", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolNotEqualTo(Integer value) {
            addCriterion("indoorPool <>", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolGreaterThan(Integer value) {
            addCriterion("indoorPool >", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolGreaterThanOrEqualTo(Integer value) {
            addCriterion("indoorPool >=", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolLessThan(Integer value) {
            addCriterion("indoorPool <", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolLessThanOrEqualTo(Integer value) {
            addCriterion("indoorPool <=", value, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolIn(List<Integer> values) {
            addCriterion("indoorPool in", values, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolNotIn(List<Integer> values) {
            addCriterion("indoorPool not in", values, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolBetween(Integer value1, Integer value2) {
            addCriterion("indoorPool between", value1, value2, "indoorpool");
            return (Criteria) this;
        }

        public Criteria andIndoorpoolNotBetween(Integer value1, Integer value2) {
            addCriterion("indoorPool not between", value1, value2, "indoorpool");
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