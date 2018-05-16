<template>
    <div class="orderHistory">
        <div class="topBar">
            <router-link :to="{path: '/main'}" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">订单中心</div>
            </div>


            <div class="optBtns">
                <el-button type="primary" @click="backHome">去首页</el-button>
            </div>
        </div>

        <div class="commonBox">
            <div class="commonTit">
                <h2>我的订单</h2>
                <div class="opts">
                    <label>筛选：</label>
                    <Select :opts="filterOptsList" :sel="filterOptsSel" nameAlias="name" idAlias="id" align="right"></Select>
                </div>
            </div>

            <div class="commonCon">
                <div class="orderHeader">
                    <div class="detailCol">订单详情</div>
                    <div class="personCol">预订人</div>
                    <div class="priceCol">金额</div>
                    <div class="statusCol">订单状态</div>
                    <div class="optCol">操作</div>
                </div>

                <div class="orderList">
                    <ul>
                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="success">支付成功</span>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="success">支付成功</span>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="failed">支付失败</span>
                                    <a href="javascript:;" class="detailLink">查看</a>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="waitPay">等待支付</span>
                                    <a href="javascript:;" class="detailLink">去支付</a>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="success">支付成功</span>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="success">支付成功</span>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="failed">支付失败</span>
                                    <a href="javascript:;" class="detailLink">查看</a>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="head">
                                <div class="date">2018-02-02 10:15:47</div>
                                <div class="no"><label>订单号：</label>252844524521</div>
                            </div>
                            <div class="con">
                                <div class="detailCol">
                                    <div class="pic" style="background-image: url(&quot;http://127.0.0.1:8081/static/route/5/ce3f40f8-fc25-40c0-89d6-b508a89fcbcf.jpg&quot;);"></div>
                                    <h3>0124_成都 北京456</h3>
                                    <div class="cityList">
                                        <div title="广州 / 广州 / 广州 / 广州 / 广州">
                                            广州 / 广州 / 广州 / 广州 / 广州
                                        </div>
                                    </div>
                                </div>
                                <div class="personCol">张三丰</div>
                                <div class="priceCol">
                                    <div class="price">总额：¥1680</div>
                                    <div class="paytype">微信在线支付</div>
                                </div>
                                <div class="statusCol">
                                    <span class="waitPay">等待支付</span>
                                    <a href="javascript:;" class="detailLink">去支付</a>
                                </div>
                                <div class="optCol">
                                    <el-button>再次预定行程</el-button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            filterOptsList: [{
                id: 1,
                name: '最近三个月订单'
            },{
                id: 2,
                name: '近半年订单'
            },{
                id: 3,
                name: '2017年订单'
            },{
                id: 4,
                name: '2016年以前订单'
            }],
            filterOptsSel: '最近三个月订单'
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

        orderStepName(){
            if(this.orderStep == 'orderList'){
                return '行程报价'
            }else if(this.orderStep == 'orderConfirm'){
                return '填写信息'
            }else if(this.orderStep == 'orderPayment'){
                return '支付'
            }else if(this.orderStep == 'orderSuccess'){
                return '完成'
            }
        }
    },
    created(){
        // this.getRouteInfo()
        // var vm = this
        // Bus.$on('changeOrderStep', function(val){
        //     vm.orderStep = val
        // })
    },

    watch: {
    },

    methods: {
        backHome(){
            this.$router.push({path: '/main/route'})
        }
    },

    destroyed(){
       
    }
}
</script>
<style lang="less" scope>
.orderHistory{
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
            a{
                color: #23a16d;
            }
        }
    }

    .commonBox{
        width: 1200px;
        margin: 20px auto;
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
            
            .total{
                float: right;
                font-size: 14px;
                strong{
                    color: #23a16d;
                    font-size: 18px;
                }
            }
        }
        .commonTit{
            .opts{
                float: right;
                label{
                    float: left;
                    line-height: 32px;
                }
                .selectBox{
                    float: right;
                }
            }
        }

        .orderHeader{
            height: 36px;
            padding-left: 15px;
            background: #F2F4F8;
            line-height: 36px;
        }

        .orderList{
            li{
                margin-top: 16px;
                border: 1px solid #E0E6EA;
                .head{
                    overflow: hidden;
                    height: 36px;
                    padding-left: 15px;
                    background: #F2F4F8;
                    line-height: 36px;
                    color: #a0abb3;
                    font-size: 14px;
                    .date{
                        float: left;
                    }
                    .no{
                        float: left;
                        margin-left: 50px;
                        color: #444;
                        label{
                            color: #a0abb3;
                        }
                    }
                }
                .con{
                    overflow: hidden;
                    padding-left: 15px;
                    .detailCol,
                    .personCol,
                    .priceCol,
                    .statusCol{
                        height: 95px;
                        border-right: 1px solid #E0E6EA;
                    }

                    .detailCol{
                        padding-top: 12px;
                    }
                    .personCol{
                        line-height: 95px;
                        color: #a0abb3;
                        font-size: 14px;
                    }
                    .statusCol{
                        color: #a0abb3;
                        font-size: 14px;
                    }
                    .optCol{
                        margin-top: 32px;
                    }
                }
            }
        }

        .detailCol{
            float: left;
            width: 550px;
            .pic{
                float: left;
                width: 90px;
                height: 70px;
                background-size: cover;
                background-position: center;
            }
            h3{
                padding-top: 5px;
                margin-left: 100px;
                font-size: 16px;
                font-weight: normal;
            }
            .cityList{
                margin-top: 20px;
                margin-left: 100px;
                color: #a0abb3;
                div{
                    width: 100%;
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    font-size: 14px;
                }
            }
        }
        .personCol{
            float: left;
            width: 120px;
            text-align: center;
        }
        .priceCol{
            float: left;
            width: 175px;
            text-align: center;
            font-size: 14px;
            color: #a0abb3;
            line-height: 24px;
            .price{
                margin-top: 25px;
                display: inline-block;
                border-bottom: 1px solid #e2e2e2;
            }
            // .priceCol{
            //     line-height: 
            // }
        }
        .statusCol{
            float: left;
            width: 120px;
            text-align: center;
            .success{
                line-height: 95px;
                color: #23a16d;
            }
            .failed{
                display: block;
                margin-top: 25px;
                line-height: 24px;
                color: #F56C6C;
            }
            .waitPay{
                display: block;
                margin-top: 25px;
                line-height: 24px;
                color: #409EFF;
            }

            .detailLink{
                display: inline-block;
                line-height: 22px;
                color: #a0abb3;
                text-decoration: underline;
            }
        }
        .optCol{
            float: left;
            width: 175px;
            text-align: center;
        }
    }




}
</style>