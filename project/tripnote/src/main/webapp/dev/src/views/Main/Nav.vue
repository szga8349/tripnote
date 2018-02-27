<template>
    <div class="mainNav">
        <div class="logo"></div>
        <div class="user">
            <div class="avatar">
                <img src="../../assets/images/avatar.png">
            </div>
            <p class="name">Danior</p>
            <p class="phone">13800138000</p>
        </div>

        <ul>
            <router-link tag="li" v-for="value in mainNav" :to="value.route">
                <i :class="`icon ${value.icon}`"></i>
                <span class="name">{{value.name}}</span>
                <span class="num" v-if="typeof value.count != 'undefined'">{{value.count}}</span>
            </router-link>
        </ul>
    </div>
</template>
<script>
import Vue from 'vue'
export default{
    name: 'MainNav',
    avatarImg: './images/avatar.png',
    data(){
        return {
            mainNav: [
                {
                    name: '我的定制',
                    icon: 'icon1',
                    route: {name: 'route'}
                },
                {
                    name: '我的模板',
                    icon: 'icon2',
                    route: {name: 'template'}
                },
                {
                    name: 'POI库',
                    icon: 'icon3',
                    route: {name: 'poi'},
                    count: 2
                },
                {
                    name: '素材库',
                    icon: 'icon4',
                    route: {name: 'res'},
                    count: 16
                },
                {
                    name: '客户管理库',
                    icon: 'icon5',
                    route: {name: 'user'}
                },
                {
                    name: '我的收藏库',
                    icon: 'icon6',
                    route: {name: 'fav'}
                },
            ]
        }
    },
    computed: {
      
    },
    watch: {
      
    },
    created(){
      // this.testSourceAuthority()
    },
    methods: {
      // async testSourceAuthority(){
      //   const res = await this.$http.get('/space/init');
      //   const {statusCode, message} = res.body;
      //   if (statusCode === 200) {
      //     if (message.roleLevel !== 0 || message.projectList.length !== 0) {
      //       this.mainNav.push({
      //         name: this.$t("content.nav.cckj"),
      //         icon: 'icon8',
      //         route: {name: 'Source'}
      //       });
      //       message.roleLevel === 1 && message.projectList.unshift({id: '', name: this.$t('content.header.qb')});
      //       this.$store.dispatch('sourceUserMessage', message);
      //     }
      //   }
      // }
    }
  }
</script>
<style lang="less" scope>
.mainNav{
    float: left;
    width: 220px;
    .logo{
        width: 220px;
        height: 60px;
        background: url(../../assets/images/logo.png) 15px 10px no-repeat #16232c;
    }
    .user{
        margin-top: 45px;
        text-align: center;
        .avatar{
            width: 98px;
            height: 98px;
            margin: 0 auto;
            border-radius: 100%;
        }
        .name{
            margin-top: 5px;
            font-size: 16px;
            color: #c1cfe0;
            font-weight: bold;
        }
        .phone{
            color: #576773;
        }
    }
    ul{
        margin-top: 12px;
        padding: 10px 0 12px;
        border-top: 1px solid #161E25;
        border-bottom: 1px solid #161E25;
        li{
            height: 50px;
            margin-top: 2px;
            line-height: 50px;
            font-size: 14px;
            color: #c1cfe0;
            cursor: pointer;
            &:active,
            &:hover{
                background: #101c24;
            }
            i{
                float: left;
                width: 20px;
                height: 20px;
                margin: 15px 15px 15px 20px;
                background: url(../../assets/images/icon_main_nav.png);
            }
            .icon1{
                background-position: 0 0;
            }
            .icon2{
                background-position: 0 -20px;
            }
            .icon3{
                background-position: 0 -40px;
            }
            .icon4{
                background-position: 0 -60px;
            }
            .icon5{
                background-position: 0 -80px;
            }
            .icon6{
                background-position: 0 -100px;
            }
            .num{ 
                float: right;
                height: 16px;
                margin-top: 17px;
                margin-right: 16px;
                padding: 0 5px;
                background: #49ABD6;
                border-radius: 8px;
                line-height: 16px;
                font-size: 12px;
                color: #fff;
            }
            &:nth-child(4){
                .num{
                    background: #26C24C;
                }
            } 
            &.active{
                background: #101c24;
            }
        }
    }
    
}
</style>
