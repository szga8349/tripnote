<template>
    <div class="orderList">
        <div class="commonBox">
            <div class="commonTit">
                <h2>行程报价</h2>
            </div>

            <div class="commonCon">
                <div class="title">
                    <div class="tit">客户报价</div>
                </div>
                <div class="customPrice">
                    <label>总 价：</label>
                    <div class="con">
                        <div class="priceWrap">
                            <input type="text" class="price" placeholder="请输入总的报价" v-model="customPrice">
                            <el-select v-model="value" placeholder="请选择">
                                <el-option
                                    key="1"
                                    label="人民币"
                                    value="1">
                                </el-option>
                                <el-option
                                    key="2"
                                    label="美元"
                                    value="3">
                                </el-option>
                            </el-select>
                        </div>
                        <el-checkbox v-model="checked">选择后您可以将填写的报价并与行程一并发给客人</el-checkbox>
                    </div>
                </div>

                <div class="title">
                    <div class="tit">成本费用</div>
                    <div class="filterBtns">
                        <el-button-group>
                            <el-button type="primary" icon="el-icon-date" :class="{active: filterType=='date'}"
                            @click="changeFilterType('date')"
                            >按时间</el-button>
                            <el-button type="primary" icon="el-icon-menu" :class="{active: filterType=='type'}"
                            @click="changeFilterType('type')"
                            >按类型</el-button>
                        </el-button-group>
                        <el-button type="primary" icon="el-icon-refresh">刷新价格</el-button>
                    </div>

                    <div class="total">
                        全部费用：<strong>{{calculatePriceTotal(schedules)}}</strong>人民币
                    </div>
                </div>

                <div class="schedules">
                    <div class="daysList" v-for="(item, index) in schedules" v-if="filterType == 'date'">
                        <div class="header">
                            <div class="tit">
                                <strong>第{{index+1}}天</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceDay(item.scheduletrips)}})</span></span>
                            </div>
                            <div class="price">
                                单价
                            </div>
                            <div class="num">
                                数量
                            </div>
                            <div class="total">
                                总计
                            </div>
                            <div class="opts" v-if="index==0">
                                全选 
                                <el-checkbox v-model="selectAll"></el-checkbox>
                            </div>
                        </div>

                        <div class="item" v-for="(val, i) in item.scheduletrips">
                            <div class="tit">
                                <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                            </div>
                            <div class="price">
                                ¥{{val.price}}
                            </div>
                            <div class="num">
                                <el-input-number size="small" :min="1" v-model="val.num"></el-input-number>
                            </div>
                            <div class="total">
                                ¥{{val.price*val.num}}
                            </div>

                            <div class="opts">
                                <!-- <input type="checkbox" v-model="val.sel">--{{val.sel}} -->
                                <el-checkbox v-model="val.sel"></el-checkbox>
                            </div>
                        </div>
                    </div>



                    <div class="daysList" v-if="filterType == 'type'">
                        <div class="header">
                            <div class="tit">
                                <strong>行程POI</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('poi')}})</span></span>
                            </div>
                            <div class="price">
                                单价
                            </div>
                            <div class="num">
                                数量
                            </div>
                            <div class="total">
                                总计
                            </div>
                            <div class="opts">
                                全选 
                                <el-checkbox v-model="selectAll"></el-checkbox>
                            </div>
                        </div>
                        
                        <div v-for="(item, index) in schedules" :key="Math.random()">
                            <div class="item" v-for="(val, i) in item.scheduletrips" :key="Math.random()">
                                <div class="tit">
                                    <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                                </div>
                                <div class="price">
                                    ¥{{val.price}}
                                </div>
                                <div class="num">
                                    <el-input-number size="small" :min="1" v-model="val.num"></el-input-number>
                                </div>
                                <div class="total">
                                    ¥{{val.price*val.num}}
                                </div>
                                <div class="opts">
                                    <el-checkbox v-model="val.sel"></el-checkbox>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'
import moment from 'moment'


export default {
    data() {
        return {
            dialogAddVisible: false,
            customPrice: '',
            customers: 1,
            schedules: '',
            filterType: 'date',
            selectAll: true,

            schedulesType: '',
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
        this.getRouteInfo()
    },

    watch: {
        selectAll(){
            this.changeAllSelected()
        }
    },

    methods: {
        submitOrder(){
            var _items = []
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    var _item = this.schedules[i].scheduletrips[j]
                    if(_item.sel){
                        _items.push({
                            "id": _item.id,
                            "nameCn": _item.nameCn,
                            "num": _item.num,
                            "price": _item.price,
                            "supplier": _item.supplier,
                            "type": _item.type,
                            "indexdate": _item.indexdate,
                        })
                    }
                }
            }



            var _data = {
                "totalPrice": this.customPrice,
                "tripnoteId": this.routeId,
                "scheduleOffers": _items
            }

            // console.log(JSON.stringify(_data))

            $.ajax({
                url: '/travel/price/doAdd',
                type: 'post',
                data: JSON.stringify(_data),
                contentType: 'application/json',
                success: function(res){
                    console.log(_res)
                }
            })

            // this.$http({
            //     method: 'post',
            //     url: '/travel/price/doAdd',
            //     data: _data
            // }).then((res)=>{
            //     if(res.data.code == -1){
            //         this.$message({
            //             message: res.data.message,
            //             type: 'error',
            //             duration: 2000
            //         });
            //     }else{
            //         this.customers = res.data.data.custems

            //         var _data = res.data.data.ttripNoteSchedules

            //         for (var i = 0; i < _data.length; i++) {
            //             for (var j = 0; j < _data[i].scheduletrips.length; j++) {
            //                 _data[i].scheduletrips[j]['num'] = 1
            //                 _data[i].scheduletrips[j]['sel'] = false
            //             }
            //         }

            //         this.schedules = _data

            //     }
            // })
        },

        changeFilterType(type){
            this.filterType = type
            // if(type ==){

            // }
        },

        calculatePriceType(type){
            var _total = 0;
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    _total += this.schedules[i].scheduletrips[j].price * this.schedules[i].scheduletrips[j].num
                }
            }
            return _total
        },

        poiTypeFormat(type){
            if(type == 1){
                return '<i class="iconfont icon-canyin"></i>'
            }else if(type == 2){
                return '<i class="iconfont icon-travel"></i>'
            }else if(type == 3){
                return '<i class="iconfont icon-gouwu"></i>'
            }else if(type == 4){
                return '<i class="iconfont icon-kuaiyule"></i>'
            }
        },

        calculatePriceDay(schedules){
            var _total = 0;
            for (var i = 0; i < schedules.length; i++) {
                _total += schedules[i].price*schedules[i].num
            }
            return _total
        },

        changeAllSelected(){
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    this.schedules[i].scheduletrips[j].sel = this.selectAll
                }
            }
        },

        calculatePriceTotal(schedules){
            var _total = 0;
            for (var i = 0; i < schedules.length; i++) {
                for (var j = 0; j < schedules[i].scheduletrips.length; j++) {
                    _total += schedules[i].scheduletrips[j].price * schedules[i].scheduletrips[j].num
                }
            }
            return _total
        },

        getRouteInfo(){
            this.$http({
                method: 'get',
                url: '/travel/price/doDetail/' + this.routeId,
            }).then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.customers = res.data.data.custems

                    var _data = res.data.data.ttripNoteSchedules

                    for (var i = 0; i < _data.length; i++) {
                        for (var j = 0; j < _data[i].scheduletrips.length; j++) {
                            _data[i].scheduletrips[j]['num'] = 1
                            _data[i].scheduletrips[j]['sel'] = true
                        }
                    }

                    this.schedules = _data

                }
            })
        },
    },

    destroyed(){
       
    }
}
</script>
<style lang="less" scope>
.orderList{
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