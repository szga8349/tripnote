<template>
<section class="loginWrap">
    <div class="logo">
        <img src="../../assets/images/login/login_logo.png">
    </div>
    <div class="tabs">
        <div v-if="type != 'findPwd'">
            <a href="javascript:;" :class="{active : type == 'reg'}" @click="type='reg'">注册</a>
            <a href="javascript:;" :class="{active : type == 'login'}" @click="type='login'">登录</a>
        </div>
        <a href="javascript:;" class="active single" v-if="type == 'findPwd'">找回密码</a>
    </div>

    <div class="con" v-if="type == 'reg' || type == 'findPwd'">
        <h2><span>使用手机号注册</span></h2>
        <ul>
            <li>
                <input type="text" placeholder="手机号" :class="{'error': regPhoneError}" v-model="regPhone" @blur="checkRegPhone" @keyup="clearRegPhoneError">
                <div class="errorInfo" v-if="regPhoneError0Visible">
                    手机号码不能为空！
                </div>
                <div class="errorInfo" v-if="regPhoneError1Visible">
                    手机号码格式不正确！
                </div>
                <div class="errorInfo" v-if="regPhoneError2Visible">
                    手机号码已经注册，<a href="javascript:;" @click="type='login'">去登录</a>
                </div>
            </li>
            <li>
                <div class="clearfix">
                    <input type="text" placeholder="验证码" class="smsCode" :class="{'error': regSmsCodeError}" v-model="regSmsCode" @blur="checkSmsCode" @keyup="clearSmsCodeError">
                    <a href="javascript:;" class="btnGetSmsCode" :class="{'disabled': getSmsCodeTxt != '获取验证码'}" @click="getSmsCode">{{getSmsCodeTxt}}</a>
                </div>
                <div class="errorInfo" v-if="regSmsCodeErrorVisible">
                    {{regSmsCodeErrorInfo}}
                </div>
            </li>
            <li>
                <input type="password" placeholder="设置8-16位密码" :class="{'error': regPwdError}" v-model="regPwd" @blur="checkRegPwd" @keyup="clearRegPwdError">
                <div class="errorInfo" v-if="regPwdErrorVisible">
                    {{regPwdErrorInfo}}
                </div>
            </li>
        </ul>
        <a href="javascript:;" class="btnSubmit" @click="reg" v-if="type == 'reg'">注 册</a>
        <a href="javascript:;" class="btnSubmit" @click="findPwd" v-if="type == 'findPwd'">确 定</a>

        <div class="otherOpts" v-if="type == 'findPwd'">
            <a href="javascript:;" @click="type = 'reg'">注册</a> 或  <a href="javascript:;" @click="type = 'login'">登录</a>
        </div>
    </div>

    <div class="con" v-if="type == 'login'">
        <h2><span>使用手机号登录</span></h2>
        <ul>
            <li>
                <input type="text" placeholder="手机号" v-model="loginPhone" :class="{'error': loginPhoneError}" @blur="checkLoginPhone" @keyup="clearLoginPhoneError">
                <div class="errorInfo" v-if="loginPhoneErrorVisible">
                    {{loginPhoneErrorInfo}}
                </div>
            </li>
            <li>
                <input type="password" placeholder="密 码" v-model="loginPwd" :class="{'error': loginPwdError}" @blur="checkLoginPwd" @keyup="clearLoginPwdError" @keyup.enter="login">
                <div class="errorInfo" v-if="loginPwdErrorVisible">
                    {{loginPwdErrorInfo}}
                </div>
            </li>
        </ul>
        <a href="javascript:;" class="btnSubmit" @click="login">登 录</a>
        <div class="otherOpts">
            <label :class="{active: rememberPwd}" @click="rememberPwd = rememberPwd ? false : true">记住密码</label>
            <a href="javascript:;" class="forgetPwd" @click="type = 'findPwd'">忘记密码</a>
        </div>
    </div>
</section>
</template>

<script>
import '../../assets/css/login.css'
import '../../assets/libs/supersized/supersized.3.2.7.min.js'

import {mapState} from 'vuex'

export default {
	data() {
		return {
            type: 'login',
            bg_1: require('assets/images/login/bg_login_1.jpg'),
            bg_2: require('assets/images/login/bg_login_2.jpg'),
            bg_3: require('assets/images/login/bg_login_3.jpg'),
            regUsername: '',
            regPhone: '',
            regSmsCode: '',
            regSmsCode1: '',
            regPwd: '',
            loginUsername: '',
            loginPhone: '',
            loginPwd: '',

            regPhoneError: false,
            regPhoneError0Visible: false,
            regPhoneError1Visible: false,
            regPhoneError2Visible: false,

            regSmsCodeError: false,
            regSmsCodeErrorVisible: false,
            regSmsCodeErrorInfo: false,

            regPwdError: false,
            regPwdErrorVisible: false,
            regPwdErrorInfo: false,


            loginPhoneError: false,
            loginPhoneErrorVisible: false,
            loginPhoneErrorInfo: false,

            loginPwdError: false,
            loginPwdErrorVisible: false,
            loginPwdErrorInfo: false,

            getSmsCodeTxt: '获取验证码',
            getSmsCodeAbled: true,

            rememberPwd: false,

            silderInit: false,

            submitAble: true
        }
    },
    computed: {
        ...mapState({
            loginSilderInit: 'loginSilderInit',
        }),
    },
    created(){
    },
	mounted() {
        var img1 = this.bg_1
        var img2 = this.bg_2
        var img3 = this.bg_3

        var vm = this

        this.$nextTick(() => {
            $('#supersized').show()
            if(!this.loginSilderInit){
                $(function(){
                    $.supersized({
                        // 功能
                        slide_interval     : 4000,    // 转换之间的长度
                        transition         : 1,    // 0 - 无，1 - 淡入淡出，2 - 滑动顶，3 - 滑动向右，4 - 滑底，5 - 滑块向左，6 - 旋转木马右键，7 - 左旋转木马
                        transition_speed   : 1500,    // 转型速度
                        performance        : 1,    // 0 - 正常，1 - 混合速度/质量，2 - 更优的图像质量，三优的转换速度//（仅适用于火狐/ IE浏览器，而不是Webkit的）

                        // 大小和位置
                        min_width          : 0,    // 最小允许宽度（以像素为单位）
                        min_height         : 0,    // 最小允许高度（以像素为单位）
                        vertical_center    : 1,    // 垂直居中背景
                        horizontal_center  : 1,    // 水平中心的背景
                        fit_always         : 0,    // 图像绝不会超过浏览器的宽度或高度（忽略分钟。尺寸）
                        fit_portrait       : 1,    // 纵向图像将不超过浏览器高度
                        fit_landscape      : 0,    // 景观的图像将不超过宽度的浏览器

                        // 组件
                        slide_links        : 'blank',    // 个别环节为每张幻灯片（选项：假的，'民'，'名'，'空'）
                        slides             : [    // 幻灯片影像
                            {image : img1},
                            {image : img2},
                            {image : img3},
                        ]
                    });
                    vm.$store.dispatch('setLoginSliderInit', true)
                })
            }
        })
	},
	methods: {
        clearRegPhoneError(){
            this.regPhoneError = false
            this.regPhoneError0Visible = false
            this.regPhoneError1Visible = false
            this.regPhoneError2Visible = false
        },
        async checkRegPhone(){
            this.regPhoneError = false
            this.regPhoneError0Visible = false
            this.regPhoneError1Visible = false
            this.regPhoneError2Visible = false

            var vm = this;
            if(this.regPhone == ''){
                this.regPhoneError = true
                this.regPhoneError0Visible = true
                return false; 
            } 

            if(!(/^1[3|4|5|7|8][0-9]{9}$/.test(this.regPhone))){
                this.regPhoneError = true
                this.regPhoneError1Visible = true
                return false; 
            }
            if(this.type == 'reg'){
                const res = await this.$http({
                    method: 'POST',
                    url: '/tripnote/login/register/check',
                    data: {
                        loginName: this.regPhone
                    }
                })

                if(res.data.code != 1){
                    this.regPhoneError = true
                    this.regPhoneError2Visible = true
                    return false
                }else{
                    return true
                }
            }
        },

        checkLoginPhone(){
            this.loginPhoneError = false
            this.loginPhoneErrorVisible = false

            var vm = this;
            if(this.loginPhone == ''){
                this.loginPhoneError = true
                this.loginPhoneErrorVisible = true
                this.loginPhoneErrorInfo = '手机号码不能为空！'
                return false; 
            } 

            if(!(/^1[3|4|5|7|8][0-9]{9}$/.test(this.loginPhone))){
                this.loginPhoneError = true
                this.loginPhoneErrorVisible = true
                this.loginPhoneErrorInfo = '手机号码格式不正确！'
                return false
            }
            return true
        },

        clearLoginPhoneError(){
            this.loginPhoneError = false
            this.loginPhoneErrorVisible = false
        },

        checkLoginPwd(){
            this.loginPwdError = false
            this.loginPwdErrorVisible = false

            if(this.loginPwd == ''){
                this.loginPwdError = true
                this.loginPwdErrorVisible = true
                this.loginPwdErrorInfo = '密码不能为空'
                return false
            }
            return true
        },

        clearLoginPwdError(){
            this.loginPwdError = false
            this.loginPwdErrorVisible = false
        },

        countdown(){
            var _time = 60
            var vm = this
            var _countdownTimer;
            vm.getSmsCodeTxt = _time-- + 's'
            _countdownTimer = setInterval(function(){
                vm.getSmsCodeTxt = _time-- + 's'
                if(_time == 0){
                    clearInterval(_countdownTimer)
                    vm.getSmsCodeTxt = '获取验证码'
                    vm.getSmsCodeAbled = true
                }
            }, 1000)
        },

        getSmsCode(){
            if(!this.getSmsCodeAbled){
                return;
            }
            this.getSmsCodeAbled = false
            if(this.regPhone == ''){
                this.regPhoneError = true
                this.regPhoneError0Visible = true
                return false; 
            } 

            if(!(/^1[3|4|5|7|8][0-9]{9}$/.test(this.regPhone))){
                this.regPhoneError = true
                this.regPhoneError1Visible = true
                return false; 
            }

            var vm = this;

            if(this.type == 'reg'){
                this.$http({
                    method: 'POST',
                    url: '/tripnote/login/register/check',
                    data: {
                        loginName: this.regPhone
                    }
                })
                .then((res)=>{
                    if(res.data.code != 1){
                        this.regPhoneError = true
                        this.regPhoneError2Visible = true
                        this.getSmsCodeAbled = true
                    }else{
                         this.$http({
                            method: 'POST',
                            url: '/tripnote/login/sendSmsCode',
                            data: {
                                loginName: this.regPhone,
                            }
                        })
                        .then((res)=>{
                            if(res.data.code == 1){
                                vm.regSmsCode1 = res.data.data
                                vm.getSmsCodeAbled = false
                                this.countdown()
                            }
                        })
                    }
                })
            }else{
                this.$http({
                    method: 'POST',
                    url: '/tripnote/login/sendSmsCode',
                    data: {
                        loginName: this.regPhone,
                    }
                })
                .then((res)=>{
                    if(res.data.code == 1){
                        vm.regSmsCode1 = res.data.data
                        vm.getSmsCodeAbled = false
                        this.countdown()
                    }
                })
            }
        },

        checkSmsCode(){
            this.regSmsCodeError = false
            this.regSmsCodeErrorVisible = false
            if(this.regSmsCode == ''){
                this.regSmsCodeError = true
                this.regSmsCodeErrorVisible = true
                this.regSmsCodeErrorInfo = '验证码不能为空'
                return false; 
            }else{
                return true
            }
        },

        clearSmsCodeError(){
            this.regSmsCodeError = false
            this.regSmsCodeErrorVisible = false
        },

        checkRegPwd(){
            this.regPwdError = false
            this.regPwdErrorVisible = false

            if(this.regPwd == ''){
                this.regPwdError = true
                this.regPwdErrorVisible = true
                this.regPwdErrorInfo = '密码不能为空'
                return false
            }else if(this.regPwd.length < 8 || this.regPwd.length > 16){
                this.regPwdError = true
                this.regPwdErrorVisible = true
                this.regPwdErrorInfo = '请设置8-16位密码'
                return false
            }else{
                return true
            }
        },

        clearRegPwdError(){
            this.regPwdError = false
            this.regPwdErrorVisible = false
        },

        reg(){
            if(!this.submitAble){
                return
            }
            const _checkPhone = this.checkRegPhone()
            const _checkSmsCode = this.checkSmsCode()
            const _checkPwd = this.checkRegPwd()

            if(!_checkPhone || !_checkSmsCode || !_checkPwd){
                return
            }

            var vm = this;
            this.submitAble = false
            this.$http({
                method: 'POST',
                url: '/tripnote/login/register',
                data: {
                    loginName: this.regPhone,
                    loginPasswd: this.regPwd,
                    smsCode: this.regSmsCode1,
                }
            })
            .then((res)=>{
                vm.submitAble = true
                if(res.data.code == 1){
                    vm.$message({
                        message: '恭喜你，注册成功！',
                        type: 'success',
                        duration: 2000
                    });
                    vm.type = 'login'
                }else{
                    vm.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }
            })
        },

        findPwd(){
            if(!this.submitAble){
                return
            }
            const _checkPhone = this.checkRegPhone()
            const _checkSmsCode = this.checkSmsCode()
            const _checkPwd = this.checkRegPwd()

            if(!_checkPhone || !_checkSmsCode || !_checkPwd){
                return
            }

            var vm = this;
            this.submitAble = false
            this.$http({
                method: 'POST',
                url: '/tripnote/login/resetPasswd',
                data: {
                    loginName: this.regPhone,
                    loginPasswd: this.regPwd,
                    smsCode: this.regSmsCode1,
                }
            })
            .then((res)=>{
                vm.submitAble = true
                if(res.data.code == 1){
                    vm.$message({
                        message: '密码重置成功！',
                        type: 'success',
                        duration: 2000
                    });
                    vm.type = 'login'
                }else{
                    vm.$message({
                        message: res.data.message,
                        type: 'error'
                    });
                }
            })
        },

        login(){
            if(!this.submitAble){
                return
            }
            const _checkPhone = this.checkLoginPhone()
            const _checkPwd = this.checkLoginPwd()

            if(!_checkPhone || !_checkPwd){
                return
            }

            var vm = this;
            this.submitAble = false

           /* $.ajax({
                method: 'POST',
                url: '/tripnote/login/doLogin',
                data: {
                    loginName: this.loginPhone,
                    loginPasswd: this.loginPwd
                },
            })*/

            // return;

            this.$http({
                method: 'POST',
                url: '/tripnote/login/doLogin',
                data: {
                    loginName: this.loginPhone,
                    loginPasswd: this.loginPwd
                }
            })
            .then((res)=>{
                vm.submitAble = true
                
                if(res.data.code == 2){
                    vm.$message({
                        message: '恭喜你，登录成功！',
                        type: 'success',
                        duration: 2000
                    });
                    vm.$router.push({path: 'main'})
                    // vm.type = 'login'
                }else{
                    vm.$message({
                        message: res.data.message,
                        type: 'error'
                    });
                }
            })
        },
	},
    destroyed(){
        $('#supersized').hide()
    }
}
</script>
