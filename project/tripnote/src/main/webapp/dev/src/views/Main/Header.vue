<template>
    <div class="mainHeader">
        <!-- <div class="toggle"></div> -->
        <div class="searchBox" :class="{mainRoutePage: !mainRoutePage}">
            <i class="iconMapPoint"></i>
            <el-autocomplete
                class="cityKeywords"
                v-model="headerSearchCityName"
                :fetch-suggestions="queryCitySearch"
                placeholder="相关目的地"
                :trigger-on-focus="false"
                @select="geoCitySelect"
            ></el-autocomplete>

            <i class="el-icon-search"></i>
            <input type="text" class="keywords" placeholder="搜索关键字" v-model="headerSearchKeyword">

            <a href="javascript:;" class="btnSearch" @click="headerSearchSubmit"></a>
        </div>
        
        <div class="optsBox">
            <!-- <div class="changeLanguage">
                English
                <i class="el-icon-caret-bottom"></i>
            </div> -->

            <div class="msg">
                <span class="num">3</span>
            </div>

            <div class="user">
                <div class="name">
                    <span class="tit">{{user.realName ? user.realName : user.loginName}}</span>
                    <i class="el-icon-caret-bottom"></i>
                    <div class="menu">
                        <ul>
                            <router-link :to="{path: '/setting/info'}" tag="li">
                                <i class="iconfont icon-zhanghaoshezhi"></i>账号设置
                            </router-link>
                            <li @click="logout"><i class="iconfont icon-tuichu"></i>退出账号</li>
                        </ul>
                    </div>
                </div>
                <div class="avatar">
                    <div class="pic" :style="{backgroundImage: `url(${imgFormat(user.imageurl)})`}"></div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  import Vue from 'vue'
  import {mapState} from 'vuex'
  import Bus from 'utils/bus'
  
  export default{
    name: 'MainHeader',
    props: {
        user: Object
    },
    data(){
        return {
            mainRoutePage: false,
            headerSearchKeyword: '',
            headerSearchCityName: '',
            headerSearchCity: '',
        }
    },
    computed: {
      
    },
    watch: {
      // 如果路由有变化，会再次执行该方法
      "$route": "clearSearchKeyword"
    },
    created(){
        var vm = this
        Bus.$on('setMainRoutePage', function(item){
            vm.mainRoutePage = item
        })
    },
    computed: {
    },
    methods: {
        clearSearchKeyword(){
            this.headerSearchKeyword = ''
            this.headerSearchCityName = ''
            this.headerSearchCity = ''
        },

        headerSearchSubmit(){
            var _routeName = this.$route.name
            if(_routeName == 'route'){
                Bus.$emit('headerSearchRoute', this.headerSearchKeyword, this.headerSearchCity)
            }else if(_routeName == 'template'){
                Bus.$emit('headerSearchTemplate', this.headerSearchKeyword, this.headerSearchCity)
            }else if(_routeName == 'poi'){
                Bus.$emit('headerSearchPoi', this.headerSearchKeyword)
            }else if(_routeName == 'res'){
                Bus.$emit('headerSearchRes', this.headerSearchKeyword)
            }else if(_routeName == 'customer'){
                Bus.$emit('headerSearchCustomer', this.headerSearchKeyword)
            }else if(_routeName == 'collection'){
                Bus.$emit('headerSearchCollection', this.headerSearchKeyword)
            }
        },

        logout(){
            this.$http({
                method: 'POST',
                url: '/userLogin/logout'
            })
            .then((res)=>{
                if(res.data.code != 1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.$router.push({name: 'Login'})
                }
            })
        },

        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/avatar.png')
            }
        },


        queryCitySearch(queryString, cb) {
            var vm = this
            this.tableDataLoading = true
            this.$http({
                method: 'POST',
                url: '/city/doSearch',
                data: {
                    pageNo: 1,
                    pageSize: 100,
                    nameCn: queryString
                }
            })
            .then((res)=>{
                vm.tableDataLoading = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    var _data = []

                    for (var i = 0; i < res.data.data.length; i++) {
                        var _item = res.data.data[i]
                        _item.value = res.data.data[i].nameCn
                        _data.push(_item)
                    }

                    cb(_data)
                }
            })
        },

        geoCitySelect(item){
            this.headerSearchCity = item.id
            this.headerSearchCityName = item.nameCn
            // this.form.lat = item.lat
            // this.form.lon = item.lon
        },
    },

    destroyed(){
        Bus.$off('setMainRoutePage')
    }
  }
</script>
<style lang="less" scope>
.mainHeader{
    height: 60px;
    background: #fff;
    box-shadow: 0 3px 10px rgba(0,0,0,.04);
    .toggle{
        float: left;
        width: 70px;
        height: 60px;
        background: url(../../assets/images/icon_main_nav_toggle.png) center center no-repeat;
        border-right: 1px solid #ebebeb;
        cursor: pointer;
    }
    .searchBox{
        float: left;
        width: 370px;
        height: 36px;
        margin-top: 12px;
        margin-left: 18px;
        background: #EDF1F2;
        border-radius: 18px;
        &.mainRoutePage{
            width: 250px;
            .iconMapPoint,
            .cityKeywords{
                display: none;
            }
            .keywords{
                border-left: 0;
            }
        }
        .iconMapPoint{
            float: left;
            width: 16px;
            height: 18px;
            margin: 9px 0 0 15px;
            background: url(../../assets/images/icon_map_point.png);
        }

        .el-icon-search{
            display: none;
            float: left;
            margin: 10px 0 0 5px;
            color: #9FA5B0;
            font-size: 16px;
        }

        .cityKeywords{ 
            float: left;
            margin-left: 10px;
            .el-input__inner{
                width: 100px;
                height: 36px;
                padding: 0;
                background: none;
                border: 0;
                line-height: 36px;
                font-size: 13px;
            }
        }
        .keywords{ 
            float: left;
            width: 175px;
            height: 24px;
            margin-left: 10px;
            background: none;
            border: 0;
            margin-top: 6px;
            padding-left: 8px;
            border-left: 1px solid #ddd;
            line-height: 24px;
            font-size: 13px;
        }
        .btnSearch{
            float: right;
            width: 42px;
            height: 36px;
            background: url(../../assets/images/icon_search.png) 10px center no-repeat;
        }
    }
    .optsBox{
        float: right;
    }
    .changeLanguage{
        float: left;
        padding: 0 22px;
        line-height: 60px;
    }
    .msg{
        float: left;
        width: 90px;
        height: 60px;
        background: url(../../assets/images/icon_msg.png) center center no-repeat;
        .num{
            float: left;
            height: 16px;
            margin-left: 44px;
            margin-top: 14px;
            padding: 0 6px;
            line-height: 16px;
            background: #F83B3B;
            border-radius: 16px;
            color: #fff;
        }
    }
    .user{
        float: left;
        padding: 0 22px;
        line-height: 60px;
        font-size: 14px;
        .name{
            position: relative;
            z-index: 100000000;
            float: left;
            .menu{
                display: none;
                position: absolute;
                z-index: 100;
                top: 50px;
                right: 0;
                width: 135px;
                padding: 12px 0;
                background: #fff;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                li{
                    overflow: hidden;
                    line-height: 36px;
                    padding-left: 18px;
                    cursor: pointer;
                    i{
                        display: inline-block;
                        vertical-align: middle;
                        margin-top: -3px;
                        margin-right: 12px;
                    }
                    &:hover{
                        background: #E9F1F1;
                    }
                }
            }
            &:hover{
                .menu{
                    display: block;
                }
            }
        }
        .avatar{
            float: left;
            height: 42px;
            margin-left: 10px;
            margin-top: 9px;
            .pic{
                width: 45px;
                height: 45px;
                background-size: cover;
                background-position: center;
                border-radius: 100%;
            }
        }
    }

}
</style>
