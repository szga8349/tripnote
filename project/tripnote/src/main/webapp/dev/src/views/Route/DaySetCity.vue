<template>
    <div class="columnBox setCity">
        <div class="header">
            <div class="searchBox">
                <div class="icon"><i class="iconfont icon-sousuo1"></i></div>
                <input type="text" class="keywords" placeholder="添加目标城市" v-model="keywords" >
                <div class="clearKeywords" v-if="keywords != ''">
                    <i class="iconfont icon-tianjia"></i>
                </div>
            </div>
            <div class="opts">
                <a href="javascript:;" @click="closeMap">
                    <i class="iconfont icon-guanbi"></i>
                </a>
            </div>
        </div>

        <div class="selectCity">
            <div class="tit">
                <div v-if="countryName == ''">选择目的地</div>
                <div v-if="countryName != ''">
                    <a href="javascript:;" class="back" @click="backCountryList">
                        <i class="iconfont icon-back01"></i>
                    </a>{{countryName}}
                </div>
            </div>
            <div class="list">
                <ul>
                    <li v-for="(item, index) in countryList" @click="getCityList(item.nameCn)">
                        <div class="icon">
                            <img src="../../assets/images/flag_china.png">
                        </div>
                        <div class="name">{{item.nameCn}}</div>
                        <div class="moreLink">
                            <i class="iconfont icon-fanhui"></i>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="map" id="map">
            
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    data() {
        return {
            countryList: [],
            cityList: [],
            countryName: '',
        }
    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
        }),
    },
    created(){
        this.getCountryList()
    },
    methods: {
        backCountryList(){
            this.countryName = ''
            this.getCountryList()
        },
        getCountryList(){
            this.$http({
                method: 'get',
                url: '/tripnote/country/doSearch',
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    console.log(res.data.data)
                    this.countryList = res.data.data
                }
            })
        },

        getCityList(nameCn){
            this.countryName = nameCn

            this.$http({
                method: 'get',
                url: '/tripnote/city/doSearch',
                data:{
                    countryId: 1,
                    pageSize: 100
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.countryList = res.data.data
                }
            })
        },

        closeMap(){
            this.$store.dispatch('setRouteLineActive', false)
        },

        editOverview(){
            
        }
        // sendToParentSure(){
        //     this.$emit('callbackSure', {
        //         templateType: this.templateTypeSel,
        //         templateName: this.templateName
        //     })
        // },
        // sendToParentCancel(){
        //     this.$emit('callbackCancel')
        // }
    },
}
</script>
<style lang="less" scope>
.setCity{
    left: 300px;
    right: 20px;
    top: 80px;
    bottom: 20px;
    .header{
        .searchBox{
            float: left;
            width: 290px;
            height: 54px;
            margin-left: -5px;
            border-right: 1px solid #EAEDF1;
            .icon{
                float: left;
                width: 25px;
                line-height: 54px;
                text-align: left;
                i{
                    font-size: 18px;
                    color: #aaa;
                }
            }
            .keywords{
                float: left;
                width: 215px;
                height: 54px;
                line-height: 54px;
                border: 0;
                // background: #ddd;
                font-size: 14px;
            }
            .clearKeywords{
                float: right;
                width: 40px;
                line-height: 54px;
                text-align: center;
                i{
                    font-size: 20px;
                }
            }
        }
        .opts{
            height: 54px;
            margin-right: -15px;
            border-left: 1px solid #EAEDF1;
            &:hover{
                background: #f1f1f1;
            }
            a{
                display: block;
                width: 54px;
                line-height: 54px;
                text-align: center;
                i{
                    font-size: 20px;
                    font-weight: bold;
                    color: #A1ACB3;
                }
            }
            
        }
    }

    .selectCity{
        width: 300px;
        .tit{
            height: 40px;
            padding-top: 10px;
            line-height: 30px;
            font-size: 16px;
            padding-left: 35px;
            border-right: 1px solid #EAEDF1;
            // font-weight: bold;
            .back{
                float: left;
                width: 30px;
                margin-left: -30px;
                text-align: center;
                i{
                    font-size: 20px;
                }
            }
        }
        .list{
            position: absolute;
            top: 95px;
            left: 0;
            bottom: 0;
            width: 300px;
            overflow-y: auto;
            border-right: 1px solid #EAEDF1;
            li{
                overflow: hidden;
                line-height: 34px;
                padding-left: 35px;
                font-size: 14px;
                &:hover{
                    background: #EBEDF1;
                }
                .icon{
                    float: left;
                    width: 22px;
                    height: 14px;
                    margin: 10px 6px 0 0;
                    img{
                        width: 100%;
                        height: 100%;
                        display: block;
                    }
                }
                .name{
                    float: left;
                }
                .moreLink{
                    display: none;
                    float: right;
                    width: 30px;
                    text-align: center;
                    font-size: 12px;
                    cursor: pointer;
                    font-weight: bold;
                }
                &:hover{
                    .moreLink{
                        display: block;
                    }
                }
            }
        }
    }
    
    .content{
        padding: 10px 15px;
        line-height: 24px;
        font-size: 14px;
    }
}
</style>