import Vue from 'vue';
import Router from 'vue-router';

import Login from 'views/Login/Login'

import Main from 'views/Main/Main'
import MainRouteList from 'views/Main/RouteList'
import MainPoiList from 'views/Main/PoiList'
import MainTemplateList from 'views/Main/TemplateList'

import Route from 'views/Route/Route'
import RouteIntro from 'views/Route/RouteIntro'
import RouteRemarks from 'views/Route/RouteRemarks'
import DayDetail from 'views/Route/DayDetail'
import DaySchedule from 'views/Route/DaySchedule'


Vue.use(Router)

let routes = [
    // {path: '', redirect: '/'},
    {
        path: '/',
        redirect: '/main/route'
    }, 
    {
        path: '/main',
        name: 'Main',
        component: Main,
        children: [
            {path: '', redirect: { name: 'route' }},
            {path: 'route', name: 'route', component: MainRouteList},
            {path: 'poi', name: 'poi', component: MainPoiList},
            {path: 'template', name: 'template', component: MainTemplateList},
        ]
    },

    {
        path: '/route/:id',
        name: 'Route',
        component: Route,
        children: [
            {path: '', redirect: { name: 'RouteIntro'}},
            {path: 'intro', name: 'RouteIntro', component: RouteIntro},
            {path: 'remarks', name: 'RouteRemarks', component: RouteRemarks},
            {path: 'day/:dayId', name: 'DayDetail', component: DayDetail,
                children: [
                    {path: 'schedule', name: 'schedule', component: DaySchedule},
                ]
            },
            // {path: 'template', name: 'template', component: MainTemplateList},

            // {path: '',  redirect: 'MetaData'},
            // {path: 'MetaData', name: 'MetaData', component: MetaData,
            // children: [
            //   {path: '', redirect: {name: 'home'}},
            //   {path: 'home', name: 'home', component: Home},
            // ]
        ]
    },
    // {path: '/Main', name: 'Login', component: Login}
    {
        path: '/login',
        name: 'Login',
        component: Login
    }
];


export default new Router({
    linkExactActiveClass: 'active',
    mode: 'history',
    routes,
    // scrollBehavior(to, from, savedPosition){
    //   console.log(savedPosition)
    //   return {x: 0, y: 0}
    // }
});