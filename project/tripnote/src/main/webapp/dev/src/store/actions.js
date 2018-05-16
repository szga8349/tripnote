import * as types from './mutation-types';

const actions = {
    setLoginSliderInit({commit}, playload){
        commit(types.SET_LOGIN_SLIDER_INIT, playload)
    },

    setRouteInfo({commit}, playload){
        commit(types.SET_ROUTE_INFO, playload)
    },


    setRouteLineActive({commit}, playload){
        commit(types.SET_ROUTE_LINE_ACTIVE, playload)
    },

    setDayScheduleActive({commit}, playload){
        commit(types.SET_DAY_SCHEDULE_ACTIVE, playload)
    },

    setDayInfo({commit}, playload){
        commit(types.SET_DAY_INFO, playload)
    },

    setDayPoiEditActive({commit}, playload){
        commit(types.SET_DAY_POI_EDIT_ACTIVE, playload)
    },

}
export default actions;
