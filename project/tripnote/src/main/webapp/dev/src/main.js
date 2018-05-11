import Vue from 'vue';
import App from './App';

import axios from 'axios'

// http请求拦截器
import qs from 'qs'
axios.interceptors.request.use(config => {
    if (config.method == 'GET') {
        config.url = config.url.indexOf('?') > 0 ? config.url + '&clearCache=' + new Date().valueOf() : config.url + '?clearCache=' + new Date().valueOf()
    }
    if (config.method == "post") {
        if (!config.headers['Content-Type']) {
            config.data = qs.stringify(config.data);
            config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        }
    }
    return config
}, error => {
    return Promise.reject(error)
})

// http响应拦截器
axios.interceptors.response.use(data => {
    if (data.data['code'] && data.data['code'] == -2) {
        window.location.href = '/login'
    }
    return data
}, err => {
    if (err && err.response) {
        switch (err.response.status) {
            case 400:
                err.message = '请求错误'
                break

            case 401:
                err.message = '未授权，请登录'
                break

            case 403:
                err.message = '拒绝访问'
                break

            case 404:
                err.message = `请求地址出错: ${err.response.config.url}`
                break

            case 408:
                err.message = '请求超时'
                break

            case 500:
                err.message = '服务器内部错误'
                break

            case 501:
                err.message = '服务未实现'
                break

            case 502:
                err.message = '网关错误'
                break

            case 503:
                err.message = '服务不可用'
                break

            case 504:
                err.message = '网关超时'
                break

            case 505:
                err.message = 'HTTP版本不受支持'
                break

            default:
        }
    }
    return Promise.reject(err)
})

Vue.prototype.$http = axios



import VueI18n from 'vue-i18n'
import 'babel-polyfill'

import store from './store'

import Element from 'element-ui'

// Element国际化
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

import components from 'components/common';
// import Plugins from './plugins'

import {
    cnContent,
    enContent
} from 'locale/international.js'


// Require Froala Editor js file.
require('froala-editor/js/froala_editor.pkgd.min')

// Require Froala Editor css files.
require('froala-editor/css/froala_editor.pkgd.min.css')
require('font-awesome/css/font-awesome.css')
require('froala-editor/css/froala_style.min.css')

// Import and use Vue Froala lib.
import VueFroala from 'vue-froala-wysiwyg'
Vue.use(VueFroala)

Vue.use(directive)
Vue.use(VueI18n)
Vue.use(components)

import router from './router'
// import Setting from './components/Setting';
// import My from './components/My';

window.onbeforeunload = function() {
    window.sessionStorage.clear();
}; //页面刷新


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

Vue.use(Element, {
    locale: langZh
})

var vm = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#App');