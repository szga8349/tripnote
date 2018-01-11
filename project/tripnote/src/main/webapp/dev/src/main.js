import Vue from 'vue';
import App from './App';


import axios from 'axios'

axios.defaults.transformRequest = [function (data) {
    // Do whatever you want to transform the data
    let ret = ''
    for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    }
    return ret
}];

// http请求拦截器
axios.interceptors.request.use(config => {
    if(config.method == 'GET'){
        config.url = config.url.indexOf('?') > 0 ? config.url + '&clearCache=' + new Date().valueOf() : config.url + '?clearCache=' + new Date().valueOf()
    }
    return config
}, error => {
    return Promise.reject(error)
})

// http响应拦截器
axios.interceptors.response.use(data => {
    if(data.data['code'] && data.data['code'] == -2){
        window.location.href = '/login'
    }
    return data
}, error => {
    return Promise.reject(error)
})

Vue.prototype.$http = axios


import VueI18n from 'vue-i18n'
import 'babel-polyfill'

import store from './store'

import Element from 'element-ui'

//Element国际化
import langZh from 'element-ui/lib/locale/lang/zh-CN'
import langEn from 'element-ui/lib/locale/lang/en'

import 'assets/element-theme/element-variables.scss';
// import 'element-ui/lib/theme-chalk/index';

// import 'assets/less/source';

import 'assets/css/common.less'
import 'assets/css/element.less'
import 'assets/font/iconfont.css'


import directive from 'directives';

// import 'http://ditu.google.cn/maps/api/js?language=zh-CN&libraries=places&key=AIzaSyAVzkS9fJtCinveTrOC1YM7_4BJ4f86R1o'

import './directives/hiddenElement'

// import commonComponents from 'components/common';
// import Plugins from './plugins'

import {
  cnContent,
  enContent
} from 'locale/international.js'


// Vue.use(VueResource)
// Vue.use(VueHighcharts, { Highcharts })

Vue.use(directive)
Vue.use(VueI18n)
// Vue.use(commonComponents)
// Vue.use(Plugins)

import router from './router'
// import Setting from './components/Setting';
// import My from './components/My';

window.onbeforeunload = function() {
  window.sessionStorage.clear();
}; //页面刷新

// router.beforeEach((to, from, next) => {
//   const user = window.sessionStorage.getItem('username');
//   window.scrollTo(0, 0);
//   if (!user) {
//     Vue.http.post('/authenticate', {}, {
//       emulateJSON: true
//     }).then(function(res) {
//       if (res.body.statusCode == 200) {
//         const _user = res.body.message;
//         store.dispatch('setUsername', _user.userName);
//         window.sessionStorage.setItem('username', _user.userName);

//         store.dispatch('setUserInfo', _user)
//         window.sessionStorage.setItem('userInfo', _user)
//       } else {
//         store.dispatch('setUsername', "");
//         window.sessionStorage.removeItem('username');
//         window.sessionStorage.setItem('userInfo', {})
//         window.location.href = "/login";
//       }
//     })
//     next();
//   } else {
//     next();
//   }
// });

// Vue.http.post('/authenticate', {}, {
//   emulateJSON: true
// }).then(function(res) {
//   if (res.body.statusCode == 200) {
//     const _user = res.body.message;
//     store.dispatch('setUsername', _user.userName);
//     window.sessionStorage.setItem('username', _user.userName);

//     store.dispatch('setUserInfo', _user)
//     window.sessionStorage.setItem('userInfo', _user)
//   } else {
//     store.dispatch('setUsername', "");
//     window.sessionStorage.removeItem('username');
//     window.sessionStorage.setItem('userInfo')
//     window.location.href = "/login";
//   }
// })





// Vue.http.interceptors.push((request, next) => {
//   if(request.method == 'GET'){
//     request.url = request.url.indexOf('?') > 0 ? request.url + '&clearCache=' + new Date().valueOf() : request.url + '?clearCache=' + new Date().valueOf()
//   }

//   next((response) => {
//     const returnResponse = response.body;
//     const code = returnResponse.statusCode || returnResponse.return_code;
//     if (code && code === 407) {
//       store.dispatch('setUsername', "");
//       window.sessionStorage.removeItem('username');
//       window.location.href = "/login";
//       return response;
//     }
//   });
// });

//international
const locales = {
  en: {
    content: enContent.content
  },
  'zh-CN': {
    content: cnContent.content
  }
};
// set locales


Vue.config.lang = 'zh-CN'
Object.keys(locales).forEach(function(lang) {
  Vue.locale(lang, locales[lang])
})

// const cookie = document.cookie.split(';')
// let lang
// cookie.forEach(val=>{
//   let arr = val.split('=')
//   let name = arr[0].replace(/^\s/, '')
//   if(name === 'lang'){
//     lang = arr[1]
//   }
// })

//Vue.config.silent = true;
// Vue.http.get('lang?'+new Date().getTime())
//         .then(res=>{
//           return res.body.message
//         })
//         .then(message=>{
//           if(message){
//             lang = /zh/i.test(message.lang) ? 'zh' : 'en'
//           }else{
//             lang = navigator.language || navigator.browserLanguage
//           }
//           /^zh/.test(lang) ? Vue.use(Element, {locale: langZh}) : Vue.use(Element, {locale: langEn})
//           return lang
//         })
//         .then(lang=>{
//           window.localStorage.setItem('lang', lang)
//           var vm = new Vue({
//             router,
//             store,
//             render: h => h(App)
//           }).$mount('#App');
//         })
//         .catch(err=>{
//           console.error(err);
//           return Promise.reject()
//         })

window.localStorage.setItem('lang', 'zh')
Vue.use(Element, {locale: langZh})
var vm = new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#App');