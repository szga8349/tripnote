<template>
    <div class="orderPage">
        <div class="topBar">
            <router-link :to="{path: '/route/' + routeId}" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">支付页 > 行程报价</div>
            </div>

            <div class="stepInfo">
                <ul>
                    <li :class="{active: orderStep=='orderList'}">
                        <span>1</span>
                        <p>行程报价</p>
                    </li>
                    <li :class="{active: orderStep=='orderConfirm'}">
                        <span>2</span>
                        <p>填写信息</p>
                    </li>
                    <li :class="{active: orderStep=='orderPayment'}">
                        <span>3</span>
                        <p>支付</p>
                    </li>
                    <li :class="{active: orderStep=='orderSuccess'}">
                        <span>4</span>
                        <p>完成</p>
                    </li>
                </ul>
            </div>

            <div class="optBtns">
                <el-button type="primary" @click="submitOrder">去支付</el-button>
            </div>
        </div>

        <router-view></router-view>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            orderStep: 'orderList'
        }
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setDayScheduleActive: 'setDayScheduleActive',
            setDayPoiEditActive: 'setDayPoiEditActive',
            setRouteLineActive: 'setRouteLineActive'
        }),
        
        routeId(){
            return this.$route.params.routeId
        },
    },
    created(){
        var vm = this
        Bus.$on('changeOrderStep', function(val){
            vm.orderStep = val
        })
    },

    watch: {
    },

    methods: {
        submitOrder(){
            Bus.$on('orderList', function(val){
                vm.orderStep = val
            })
        }
    },

    destroyed(){
       
    }
}
</script>
<style lang="less" scope>
.orderPage{
    .topBar{
        height: 60px;
        background: #253744;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #4a5e6d;
        }
        .typeSel{
            float: left;
            margin-top: 14px;
            margin-left: 20px;
            line-height: 32px;
            label{
                float: left;
                font-size: 16px;
            }
            .templateType{
                float: left;
                border: 0;
                .el-input{
                    width: 155px;
                }
                .el-input__inner{
                    border: 0;
                    font-size: 16px;
                }
                .el-icon-arrow-up:before{
                    content: "\E60B"
                }
                .el-input .el-select__caret{
                    transform: none;
                }
            }
            .templateName{
                float: left;
                margin-left: 10px;
                font-size: 16px;
                color: #cde0ed;
            }
        }
        .stepInfo{
            position: absolute;
            width: 600px;
            left: 50%;
            margin-left: -300px;
            margin-top: 10px;
            line-height: 32px;
            li{
                &:first-child{
                    &:before{
                        border-radius: 2px 0 0 2px;
                    }
                }
                &:last-child{
                    &:before{
                        border-radius: 0 2px 2px 0;
                    }
                }
                &.active{
                    &:before{
                        background: #23a16d;
                    }
                    span{
                        background: #23a16d;
                    }
                    p{
                        color: #23a16d;
                    }
                }
                position: relative;
                float: left;
                width: 150px;
                &:before{
                    content: '';
                    position: absolute;
                    left: 0;
                    top: 10px;
                    width: 150px;
                    height: 5px;
                    background: #DAE5E6;
                }
                span{
                    display: block;
                    position: relative;
                    z-index: 1;
                    width: 22px;
                    height: 22px;
                    margin: 0 auto;
                    background: #DAE5E6;
                    border-radius: 100%;
                    line-height: 22px;
                    text-align: center;
                    color: #fff;
                }
                p{
                    position: relative;
                    z-index: 1;
                    line-height: 24px;
                    text-align: center;
                    color: #DAE5E6;
                }
            }
        }
        .optBtns{
            float: right;
            height: 60px;
            padding: 0 35px;
            border-left: 1px solid #DADEE5;
            .el-button{
                height: 36px;
                margin-top: 12px;
                padding: 0 30px;
            }
        }
    }

    .commonBox{
        .title{
            overflow: hidden;
            position: relative;
            margin-top: -5px;
            padding-top: 10px;
            border-top: 1px solid #EAEDF1;
            &:before{
                content: '';
                position: absolute;
                width: 5px;
                height: 5px;
                top: 22px;
                background: #23a16d;
                border-radius: 100%;
            }
            .tit{
                float: left;
                margin-left: 10px;
                line-height: 28px;
                font-size: 14px;
            }
            .filterBtns{
                float: left;
                margin-left: 15px;
                .el-button-group{
                    margin-right: 10px;
                    vertical-align: top;
                }
                .el-button{
                    height: 28px;
                    padding: 0 10px;
                    line-height: 26px;
                    background: #fff;
                    border-color: #E0E4EB;
                    color: #555;
                    &.active{
                        z-index: 1;
                        color: #23a16d;
                        // border-color: #23a16d;
                        // border-right: 1px solid #23a16d;
                    }
                    &:hover{
                        color: #23a16d;
                    }
                }
            }
            .total{
                float: right;
                font-size: 14px;
                strong{
                    color: #23a16d;
                    font-size: 18px;
                }
            }
        }
        .customPrice{
            overflow: hidden;
            padding: 15px 0;
            label{
                float: left;
                width: 85px;
                padding-right: 10px;
                line-height: 36px;
                font-size: 14px;
                text-align: right;
                color: #999;
            }
            .con{
                margin-left: 85px;
                .priceWrap{
                    height: 36px;
                    width: 300px;
                    border: 1px solid #E0E4EB;
                    .price{
                        float: left;
                        width: 210px;
                        height: 34px;
                        padding-left: 8px;
                        line-height: 34px;
                        border: 0;
                        // background: red;
                    }
                    .el-select{
                        float: right;
                        border: 0;
                        width: 80px;
                        border: 0;
                        .el-input__inner{
                            border: 0;
                        }
                        input{
                            height: 34px;
                        }
                    }
                }
                .el-checkbox{
                    margin-top: 5px;
                    color: #999;
                    .el-checkbox__label{
                        color: #999;
                        font-size: 12px;
                    }
                }
            }
        }
    }

    .schedules{
        margin-top: 15px;
        .daysList{
            margin-top: 10px;
            .header{
                overflow: hidden;
                line-height: 40px;
                color: #999;
            }
            .tit{
                float: left;
                width: 25%;
                strong{
                    font-size: 16px;
                    font-weight: normal;
                    color: #555;
                }
                .totalPrice{
                    color: #23a16d;
                }
                .icon{
                    margin-right: 3px;
                    color: #23a16d;
                }
            }
            .price{
                float: left;
                width: 25%;
            }
            .num{
                float: left;
                width: 25%;
                .el-input-number{
                    width: 110px;
                    margin-left: -10px;
                    .el-input-number__increase,
                    .el-input-number__decrease{
                        background: none;
                        border: 0;
                    }
                    .el-input{
                        .el-input__inner{
                            width: 30px;
                            height: 22px;
                            margin-left: 40px;
                            padding: 0;
                        }
                    }
                }
            }
            .total{
                float: left;
                width: 20%;
            }
            .opts{
                float: left;
                width: 5%;
                padding-right: 15px;
                text-align: right;
                .el-checkbox__inner{
                    border-radius: 100%;
                    width: 16px;
                    height: 16px;
                    border-color: #23a16d;
                    &::after{
                        width: 4px;
                        height: 8px;
                        left: 4px;
                        top: 1px;
                    }
                }
            }
            .item{
                height: 36px;
                margin-bottom: 8px;
                background: #F1F4F7;
                border-left: 3px solid #23a16d;
                line-height: 36px;
                .tit{
                    padding-left: 10px;
                }
            }
        }
    }



}
</style>