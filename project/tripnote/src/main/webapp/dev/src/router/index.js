import Vue from 'vue';
import Router from 'vue-router';

import Login from 'views/Login/Login'

import Main from 'views/Main/Main'
import MainRouteList from 'views/Main/RouteList'
import MainTemplateList from 'views/Main/TemplateList'

import Route from 'views/Route/Route'
import RouteOverview from 'views/Route/RouteOverview'
import RouteRemarks from 'views/Route/RouteRemarks'
import DayDetail from 'views/Route/DayDetail'


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
            {path: 'template', name: 'template', component: MainTemplateList},
        ]
    },

    {
        path: '/route/:id',
        name: 'Route',
        component: Route,
        children: [
            {path: '', redirect: { name: 'RouteOverview' }},
            {path: 'overview', name: 'RouteOverview', component: RouteOverview},
            {path: 'remarks', name: 'RouteRemarks', component: RouteRemarks},
            {path: 'day/:id', name: 'DayDetail', component: DayDetail},
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