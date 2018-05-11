import * as types from './mutation-types';

const mutations = {
    [types.SET_LOGIN_SLIDER_INIT](state, playload) {
        state.loginSilderInit = playload;
    },

    [types.SET_ROUTE_INFO](state, playload) {
        state.routeInfo = playload;
    },

    [types.SET_ROUTE_LINE_ACTIVE](state, playload) {
        state.setRouteLineActive = playload;
    },

    [types.SET_DAY_SCHEDULE_ACTIVE](state, playload) {
        state.setDayScheduleActive = playload;
    },

    [types.SET_DAY_INFO](state, playload) {
        state.dayInfo = playload;
    },

    [types.SET_DAY_POI_EDIT_ACTIVE](state, playload) {
        state.setDayPoiEditActive = playload;
    },
}
export default mutations;
