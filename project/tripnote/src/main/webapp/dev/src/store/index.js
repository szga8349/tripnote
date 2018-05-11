import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations';
import actions from './actions';

Vue.use(Vuex);
let view = window.sessionStorage.getItem('viewActive');

const state = {
    loginSilderInit: false, // 登录页面slider初始化
    routeInfo: {}, // 行程详情
    setRouteLineActive: false, // 安排行程激活状态
    setDayScheduleActive: false, // 设置日程激活状态
    setDayPoiEditActive: false, // 设置交通激活状态
    dayInfo: {}, 
}

export default new Vuex.Store({
    state,
    mutations,
    actions
});
