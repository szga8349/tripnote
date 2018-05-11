import Vue from 'vue';
import Router from 'vue-router';

import Login from 'views/Login/Login'

import Main from 'views/Main/Main'
    import MainRouteList from 'views/Main/RouteList'
    import MainPoiList from 'views/Main/PoiList'
    import MainTemplateList from 'views/Main/TemplateList'
    import CustomerList from 'views/Main/CustomerList'
        import CustomerInfo from 'views/Main/CustomerInfo'

    import MainResList from 'views/Main/ResList'
    import MainCollectionList from 'views/Main/CollectionList'

import Route from 'views/Route/Route'
import RouteTemplate from 'views/Route/RouteTemplate'
    import RouteIntro from 'views/Route/RouteIntro'
    import ImportTemplate from 'views/Route/ImportTemplate'
    import ExportTemplate from 'views/Route/ExportTemplate'

    import RouteRemarks from 'views/Route/RouteRemarks'
    import DayDetail from 'views/Route/DayDetail'
    
    import DayPoi from 'views/Route/DayPoi'
    import DayHotel from 'views/Route/DayHotel'
    import DayTraffic from 'views/Route/DayTraffic'
    import DaySchedule from 'views/Route/DaySchedule'
        import PoiDetail from 'views/Route/PoiDetail'
        import ProductDetail from 'views/Route/ProductDetail'
        import HotelDetail from 'views/Route/HotelDetail'


import Order from 'views/Order/Order'
    import OrderList from 'views/Order/OrderList'
    import OrderConfirm from 'views/Order/OrderConfirm'
    import OrderPayment from 'views/Order/OrderPayment'
    import OrderSuccess from 'views/Order/OrderSuccess'

import Publish from 'views/Publish/Publish'

import OrderHistory from 'views/Order/OrderHistory'

import User from 'views/User/Index'
    import UserInfo from 'views/User/Info'
    import ChangePwd from 'views/User/ChangePwd'



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
            {path: 'res', name: 'res', component: MainResList},
            {path: 'collection', name: 'collection', component: MainCollectionList},
            {path: 'template', name: 'template', component: MainTemplateList},
            {path: 'customer', name: 'customer', component: CustomerList},
            {path: 'customerNew', name: 'customerInfo', component: CustomerInfo},
            {path: 'customerNew/:groupId', name: 'customerInfoGroup', component: CustomerInfo},
            {path: 'customerEdit/:customerId', name: 'customerEdit', component: CustomerInfo},
        ]
    },

    {
        path: '/route/:routeId',
        name: 'Route',
        component: Route,
        children: [
            {path: '', redirect: { name: 'RouteIntro'}},
            {path: 'intro', name: 'RouteIntro', component: RouteIntro},
            {path: 'importTemplate', name: 'ImportTemplate', component: ImportTemplate},
            {path: 'exportTemplate', name: 'ExportTemplate', component: ExportTemplate},
            {path: 'remarks', name: 'RouteRemarks', component: RouteRemarks},
            {path: 'day/:dayId', name: 'DayDetail', component: DayDetail,
                children: [
                    {path: 'schedule', name: 'schedule', component: DaySchedule,
                        children: [
                            {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail},
                            {path: 'product/:poiId', name: 'ProductDetail', component: ProductDetail},
                            {path: 'hotel/:poiId', name: 'HotelDetail', component: HotelDetail},
                        ]
                    },
                    {path: 'poi/:scheduleId/:poiId', name: 'poi', component: DayPoi,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'hotel/:scheduleId/:hotelId', name: 'hotel', component: DayHotel,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'traffic/:poiStartId/:poiEndId/:trafficId', name: 'traffic', component: DayTraffic,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'traffic/:poiStartId/:poiEndId', name: 'traffic', component: DayTraffic,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
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

    {
        path: '/template/:routeId',
        name: 'RouteTemplate',
        component: RouteTemplate,
        children: [
            {path: '', redirect: { name: 'RouteIntroTemplate'}},
            {path: 'intro', name: 'RouteIntroTemplate', component: RouteIntro},
            {path: 'remarks', name: 'RouteRemarksTemplate', component: RouteRemarks},
            {path: 'day/:dayId', name: 'DayDetailTemplate', component: DayDetail,
                children: [
                    {path: 'schedule', name: 'scheduleTemplate', component: DaySchedule,
                        children: [
                            {path: 'poi/:poiId', name: 'poiDetailTemplate', component: PoiDetail},
                            {path: 'product/:poiId', name: 'ProductDetailTemplate', component: ProductDetail},
                            {path: 'hotel/:poiId', name: 'HotelDetailTemplate', component: HotelDetail},
                        ]
                    },
                    {path: 'poi/:scheduleId/:poiId', name: 'poiTemplate', component: DayPoi,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'hotel/:scheduleId/:hotelId', name: 'hotelTemplate', component: DayHotel,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'traffic/:poiStartId/:poiEndId/:trafficId', name: 'trafficTemplate', component: DayTraffic,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
                    {path: 'traffic/:poiStartId/:poiEndId', name: 'trafficTemplate', component: DayTraffic,
                        // children: [
                        //     {path: 'poi/:poiId', name: 'poiDetail', component: PoiDetail}
                        // ]
                    },
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

    // {path: '/routeTemplate/:routeId/:typeId', name: 'RouteTemplate', component: RouteTemplate,
    //     // children: [
    //     //     {path: '', redirect: { name: 'orderList' }},
    //     //     {path: '/orderList', name: 'orderList', component: OrderList},
    //     //     {path: '/orderConfirm', name: 'orderConfirm', component: OrderConfirm},
    //     //     {path: '/orderPayment', name: 'orderPayment', component: OrderPayment},
    //     //     {path: '/orderSuccess', name: 'orderSuccess', component: OrderSuccess},
    //     // ]
    // },

    {path: '/order/:routeId', name: 'Order', component: Order,
        // children: [
        //     {path: '', redirect: { name: 'orderList' }},
        //     {path: '/orderList', name: 'orderList', component: OrderList},
        //     {path: '/orderConfirm', name: 'orderConfirm', component: OrderConfirm},
        //     {path: '/orderPayment', name: 'orderPayment', component: OrderPayment},
        //     {path: '/orderSuccess', name: 'orderSuccess', component: OrderSuccess},
        // ]
    },

    {path: '/publish/:routeId', name: 'Publish', component: Publish,
        // children: [
        //     {path: '', redirect: { name: 'orderList' }},
        //     {path: '/orderList', name: 'orderList', component: OrderList},
        //     {path: '/orderConfirm', name: 'orderConfirm', component: OrderConfirm},
        //     {path: '/orderPayment', name: 'orderPayment', component: OrderPayment},
        //     {path: '/orderSuccess', name: 'orderSuccess', component: OrderSuccess},
        // ]
    },

    {path: '/orderHistory', name: 'orderHistory', component: OrderHistory,
        // children: [
        //     {path: '', redirect: { name: 'orderList' }},
        //     {path: '/orderList', name: 'orderList', component: OrderList},
        //     {path: '/orderConfirm', name: 'orderConfirm', component: OrderConfirm},
        //     {path: '/orderPayment', name: 'orderPayment', component: OrderPayment},
        //     {path: '/orderSuccess', name: 'orderSuccess', component: OrderSuccess},
        // ]
    },

    {
        path: '/login',
        name: 'Login',
        component: Login
    },

    {path: '/setting', name: 'user', component: User,
        children: [
            {path: '', redirect: { name: 'userInfo' }},
            {path: 'info', name: 'userInfo', component: UserInfo},
            {path: 'changePwd', name: 'changePwd', component: ChangePwd},
        ]
    },

    


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