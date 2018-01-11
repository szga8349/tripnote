<template>
<div class="content">
	<div class="header clearfix">
	    <div class="logo pull-left"><img :src="logoSrc" alt=""/></div>
	    <nav class="menu clearfix">
	        <ul class="menu-nav pull-left clearfix">
						<li v-for="[key, url] in computedNavList"
								v-if="url"
								:class="{active: $route.path.includes(url)}"
								:key="Math.random()"
                @click="pushRouter(url)">
							<span class="split-line">
								<i v-if="key == $t('content.entry.ysjgl')">{{key}}</i>
								<a :href="url" v-else>
									{{key}}
								</a>
							</span>
						</li>
						<li style="visibility: hidden;">3.4.4.2</li>
	        </ul>
	        <ul class="user-menu pull-right clearfix">
						<li>
							<a href="javascript:;" @click="changeLanguage">
								{{selectLanguage=='en' ? '中文' : 'English'}}
							</a>
						</li>
            <li>
							<a :href="authenticateUrl" v-if="authenticateUrl">{{$t('content.entry.qxgl')}}</a>
						</li>
            <li class="user">
              <a href="javascript:;"><img :src="userSrc"/></a>
              <div class="userInfo">
                <span class="arrow-up"></span>
                  <p class="user_name">{{username}}</p>
                  <p class="modify_psw">
										<a href="javascript:;" @click="pushModifyRouter">
											{{$t('content.entry.xgmm')}}
										</a>
									</p>
                  <p class="logo_out">
										<a href="javascript:;" :title="$t('content.entry.tc')" @click="logout()">{{$t('content.entry.tc')}}</a>
									</p>
                </div>
            </li>
	        </ul>
	    </nav>
	</div>
</div>

</div>
</template>

<script>
import Vue from 'vue'

export default {
	name: 'Header',
	data() {
		return {
			logoSrc:require('assets/images/logo.png'),
			userSrc:require('assets/images/user.png'),
			userInfo: false,
			slideSelect:true,
			isFull: false,
			fullScreenIcon: 'url(../../static/assets/images/full_screen.png) center center no-repeat',
			isFullTitle: '最大化',
			message: null,
			authenticateUrl: '',
			selectLanguage: window.localStorage.getItem('lang'),
		}
	},
	beforeRouteEnter(to, from, next){
		next()
	},
	async created() {
		this.fetchLanguage()
		this.fetchData()
	},
	computed: {
		username(){
			return this.$store.state.username || window.sessionStorage.getItem('username')
		},
		slideType(){
			let type = this.slideSelect;
			let slideParams = {};
			if(!type){
				slideParams['slideIcon'] = "iconSlideDown";
				slideParams['slideText'] = "显示功能区";
			}else{
				slideParams['slideIcon'] = "iconSlideUp";
				slideParams['slideText'] = "隐藏功能区";
			}
			return slideParams;
		},
		computedNavList(){
			if(this.message == null)return;
			let navMap = new Map();
			navMap.set(this.$t("content.entry.zl"), `${this.message['sqlEntry']}/overview.jsp`);
			navMap.set(this.$t("content.entry.cxfxq"), this.message['sqlEntry']);
			navMap.set(this.$t("content.entry.rwdd"), this.message['procEntry']);
			navMap.set(this.$t("content.entry.etlkf"), this.message['dataxEntry']);
			navMap.set(this.$t("content.entry.sjjc"), this.message['dhubEntry']);
			navMap.set(this.$t("content.entry.ysjgl"), 'Main');
			this.authenticateUrl = this.message['leapidEntry'];
			this.$store.dispatch('addHeaderNav', [...navMap]);
			return [...navMap];
		}
	},
	methods: {
		fetchLanguage(){
			let lang = window.localStorage.getItem('lang')
			let resLang = /^zh/.test(lang) ? 'zh-CN' : 'en'
			this.selectLanguage = Vue.config.lang = resLang
			this.$store.dispatch('setLanguage', resLang)
      this.$store.dispatch('fullLoading', false)
		},
		//判断浏览器语言
		changeLanguage(){
			this.selectLanguage = /^zh/.test(this.selectLanguage) ? 'en' : 'zh-CN'
      this.$store.dispatch('fullLoading', true)
			this.setLanguage(this.selectLanguage, true)
		},
		async setLanguage(lang, isSwitch = false){
			let reLang = lang.split('-')[0]
			const res = await this.$http.post(`/lang/${reLang}`)
			const {statusCode} = res.body
			statusCode && isSwitch && window.location.reload()
		},
		pushModifyRouter(){
			window.location.href = "/changepw";
		},
		async fetchData(){
			const res = await this.$http.get('/appEntry');
			let {statusCode, message} = res.body;
			message.zl = "http://demo12.test.com:9796/overview.jsp"
			this.message = statusCode == 200 ? message : {};
		},
		pushRouter(url){
			if(this.$route.path.includes(url)){
				this.$router.go(0);
			}
		},
		logout(){
				this.$store.dispatch('setUsername', '');
				window.sessionStorage.removeItem('username');
				window.location.href = "/logout";
		},
		userInfoShow(){
			this.userInfo = true
		},
		userInfoHide(){
			this.userInfo = false
		},
		slideToggle(){
			this.slideSelect = !this.slideSelect;
			if(this.slideSelect){
				$(".navBox").slideDown();
			}else{
				$(".navBox").slideUp();
			}
		},
	}
}
</script>

<style scope lang="less">
@import '../assets/less/header.less';
</style>
