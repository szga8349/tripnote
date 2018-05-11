<template>
    <div class="orderPage">
        <div class="topBar">
            <router-link :to="{path: '/route/' + routeId}" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">支付页 > {{orderStepName}}</div>
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
                <el-button type="primary" @click="orderListSubmit" v-if="orderStep=='orderList'">提交订单</el-button>
                
                <div v-if="orderStep=='orderConfirm'">
                    <el-checkbox v-model="agreeProtocol">我同意<a href="">《TRIP NOTE预订协议》</a></el-checkbox>
                    <el-button type="primary" @click="orderConfirmSubmit">提交订单</el-button>
                </div>

                <el-button type="primary" @click="orderPaymentSubmit" v-if="orderStep=='orderPayment'">去支付</el-button>

                <el-button type="primary" @click="backHome" v-if="orderStep=='orderSuccess'">去首页</el-button>
            </div>
        </div>

        <div class="commonBox orderListBox" v-if="orderStep=='orderList'">
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
                        全部费用：<strong>{{calculatePriceTotal()}}</strong>人民币
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
                                ¥{{val.price || 0}}
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
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.type < 5">
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


                        <div class="header">
                            <div class="tit">
                                <strong>产品</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('product')}})</span></span>
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
                            <!-- <div class="opts">
                                全选 
                                <el-checkbox v-model="selectAllProduct"></el-checkbox>
                            </div> -->
                        </div>
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.type == 5">
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

                        <div class="header">
                            <div class="tit">
                                <strong>住宿</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('hotel')}})</span></span>
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
                            <!-- <div class="opts">
                                全选 
                                <el-checkbox v-model="selectAllHotel"></el-checkbox>
                            </div> -->
                        </div>
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.type == 6">
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

        <div class="orderConfirm" v-if="orderStep=='orderConfirm'">
            <div class="commonBox rightBox">
                <div class="commonTit">
                    <h2>产品清单</h2>
                    <div class="filterBtns">
                        <el-button-group>
                            <el-button type="primary" icon="el-icon-date" :class="{active: filterType=='date'}"
                            @click="changeFilterType('date')"
                            >按时间</el-button>
                            <el-button type="primary" icon="el-icon-menu" :class="{active: filterType=='type'}"
                            @click="changeFilterType('type')"
                            >按类型</el-button>
                        </el-button-group>
                    </div>
                </div>

                <div class="commonCon orderListAll">
                    <div class="orderList" v-for="(item, index) in schedules" v-if="filterType == 'date'">
                        <div class="header">
                            <div class="tit">
                                <strong>第{{index+1}}天</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceDay(item.scheduletrips)}})</span></span>
                            </div>
                        </div>

                        <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.sel">
                            <div class="tit">
                                <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                            </div>
                            <div>
                                <span class="date">使用日期：2018-01-02</span>
                                <span class="price">¥{{val.price}}</span>
                            </div>
                            <div class="num">
                                x {{val.num}}
                            </div>
                        </div>
                    </div>

                    <div class="orderList" v-if="filterType == 'type'">
                        <div class="header">
                            <div class="tit">
                                <strong>行程POI</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('poi')}})</span></span>
                            </div>
                        </div>
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.sel && val.type < 5">
                                <div class="tit">
                                    <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                                </div>
                                <div>
                                    <span class="date">使用日期：2018-01-02</span>
                                    <span class="price">¥{{val.price}}</span>
                                </div>
                                <div class="num">
                                    x {{val.num}}
                                </div>
                            </div>
                        </div>

                        <div class="header">
                            <div class="tit">
                                <strong>产品</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('product')}})</span></span>
                            </div>
                        </div>
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.sel && val.type == 5">
                                <div class="tit">
                                    <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                                </div>
                                <div>
                                    <span class="date">使用日期：2018-01-02</span>
                                    <span class="price">¥{{val.price}}</span>
                                </div>
                                <div class="num">
                                    x {{val.num}}
                                </div>
                            </div>
                        </div>

                        <div class="header">
                            <div class="tit">
                                <strong>住宿</strong>
                                <span>(总计：<span class="totalPrice">¥{{calculatePriceType('hotel')}})</span></span>
                            </div>
                        </div>
                        
                        <div v-for="(item, index) in schedules">
                            <div class="item" v-for="(val, i) in item.scheduletrips" v-if="val.sel && val.type == 6">
                                <div class="tit">
                                    <span class="icon" v-html="poiTypeFormat(val.type)"></span>{{val.nameCn}}
                                </div>
                                <div>
                                    <span class="date">使用日期：2018-01-02</span>
                                    <span class="price">¥{{val.price}}</span>
                                </div>
                                <div class="num">
                                    x {{val.num}}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="totalAll">
                    <div class="totalNum"><span>{{schedulesNum}}</span>个产品，总计金额</div>
                    <div class="totalPrice">¥{{calculatePriceTotal()}}</div>
                </div>
            </div>


            <div class="commonBox leftBox">
                <div class="commonTit">
                    <h2>预订人信息</h2>
                </div>

                <div class="commonCon">
                    <div class="customerList">
                        <ul>
                            <li v-for="(item, index) in customers">
                                <div class="no">NO.{{index+1}}</div>
                                <dl>
                                    <dt>姓名</dt>
                                    <dd>
                                        <el-input v-model="item.name" placeholder="请与登记证件姓名保持一致"></el-input>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>身份证</dt>
                                    <dd>
                                        <el-input v-model="item.idcard" placeholder="请输入身份证号码"></el-input>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>手机号</dt>
                                    <dd>
                                        <el-input v-model="item.phone" placeholder="手机号码(选填)"></el-input>
                                    </dd>
                                </dl>

                                <div class="del" @click="delCustomer(item)"><i class="iconfont icon-cha"></i></div>
                            </li>
                        </ul>

                        <el-button type="default" class="btnBorder addNewCustomer" icon="el-icon-plus" @click="addNewCustomer">添加联系人</el-button>
                    </div>
                

                    <div class="commonTit">
                        <h2>联系人信息</h2>
                    </div>

                    <div class="contactInfo">
                        <ul>
                            <li>
                                <label>手机号</label>
                                <div class="con">
                                    <el-input v-model="contactPhone" placeholder="请填写正确的手机号码，以便接收信息"></el-input>
                                </div>
                            </li>
                            <li>
                                <label>邮箱地址</label>
                                <div class="con">
                                    <el-input v-model="contactPhone" placeholder="Email接收航变信息"></el-input>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="orderPayment" v-if="orderStep=='orderPayment'">
            <div class="info">
                <div class="tit">
                    <i class="iconfont icon-queren"></i>
                    订单提交成功！请尽快付款！（该订单会为您保留24小时，过后系统将自动取消订单。）
                </div>
                <div class="orderNo">订单号：2018252525663</div>
                <div class="price">
                    ¥1280.00(USD 252.00)
                </div>
            </div>

            <div class="payment">
                <ul>
                    <li :class="{active: paymentType=='gsyh'}" @click="paymentType='gsyh'">
                        <img src="../../assets/images/icon_payment_gh.png">
                    </li>
                    <li :class="{active: paymentType=='nyyh'}" @click="paymentType='nyyh'">
                        <img src="../../assets/images/icon_payment_nh.png">
                    </li>
                    <li :class="{active: paymentType=='zgyh'}" @click="paymentType='zgyh'">
                        <img src="../../assets/images/icon_payment_zh.png">
                    </li>
                    <li :class="{active: paymentType=='jsyh'}" @click="paymentType='jsyh'">
                        <img src="../../assets/images/icon_payment_jh.png">
                    </li>
                    <li :class="{active: paymentType=='zsyh'}" @click="paymentType='zsyh'">
                        <img src="../../assets/images/icon_payment_zsh.png">
                    </li>
                    <li :class="{active: paymentType=='yzyh'}" @click="paymentType='yzyh'">
                        <img src="../../assets/images/icon_payment_yz.png">
                    </li>
                    <li :class="{active: paymentType=='yl'}" @click="paymentType='yl'">
                        <img src="../../assets/images/icon_payment_yl.png">
                    </li>
                </ul>

                <ul>
                    <li :class="{active: paymentType=='wx'}" @click="paymentType='wx'">
                        <img src="../../assets/images/icon_payment_wx.png">
                    </li>
                    <li :class="{active: paymentType=='zfb'}" @click="paymentType='zfb'">
                        <img src="../../assets/images/icon_payment_zfb.png">
                    </li>
                </ul>
            </div>
        </div>

        <div class="orderPayment" v-if="orderStep=='orderSuccess'">
            <div class="orderSuccess">
                <div class="icon">
                    <img src="../../assets/images/icon_success.png">
                </div>
                <p>订单已支付完成！</p>
                <div class="btns">
                    <el-button type="default" class="btnBorder" @click="backHome">返回首页</el-button>
                    <el-button type="default" class="btnBorder" @click="goOrderHistory">查看订单</el-button>
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
            orderStep: 'orderList',
            dialogAddVisible: false,
            customPrice: '',
            customers: [],
            schedules: '',
            filterType: 'date',
            selectAll: true,
            // selectAllType: true,

            // selectAllPoi: true,
            // selectAllProduct: true,
            // selectAllHotel: true,



            schedulesType: '',
            paymentType: '',

            agreeProtocol: true
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
        },

        schedulesNum(){
            var _total = 0;
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    if(this.schedules[i].scheduletrips[j].sel){
                        _total++
                    }
                }
            }
            return _total
        }
    },
    created(){
        if(this.$route.query['type']){
            this.orderStep = this.$route.query['type']
        }

        this.getRouteInfo()
        // var vm = this
        // Bus.$on('changeOrderStep', function(val){
        //     vm.orderStep = val
        // })
    },

    watch: {
        selectAll(){
            this.changeAllSelected()
        },
        // selectAllType(){
        //     this.changeAllSelectedType()
        // },
        // selectAllProduct(){
        //     this.changeAllSelectedTypeProduct()
        // },
        // selectAllHotel(){
        //     this.changeAllSelectedTypeHotel()
        // },

    },

    methods: {
        orderListSubmit(){
            var vm = this

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
                    if(res.code == -1){
                        vm.$message({
                            message: res.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        vm.orderStep = 'orderConfirm'

                        vm.$router.push({query: {'type': 'orderConfirm'}})
                    }
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

        orderConfirmSubmit(){
            this.orderStep = 'orderPayment'
            this.$router.push({query: {'type': 'orderPayment'}})
        },

        orderPaymentSubmit(){
            this.orderStep = 'orderSuccess'
            this.$router.push({query: {'type': 'orderSuccess'}})
        },



        changeFilterType(type){
            this.filterType = type
            // if(type ==){

            // }
        },

        calculatePriceType(type){
            var _type
            if(type == 'poi'){
                _type = [1,2,3,4]
            }else if(type == 'product'){
                _type = [5]
            }else if(type == 'hotel'){
                _type = [6]
            }

            var _total = 0;
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    if(_type.indexOf(this.schedules[i].scheduletrips[j].type) > -1 && this.schedules[i].scheduletrips[j].sel){
                        _total += this.schedules[i].scheduletrips[j].price * this.schedules[i].scheduletrips[j].num
                    }
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
            }else if(type == 5){
                return '<i class="iconfont icon-flag"></i>'
            }else if(type == 6){
                return '<i class="iconfont icon-chuangwei"></i>'
            }
        },

        calculatePriceDay(schedules){
            var _total = 0;
            for (var i = 0; i < schedules.length; i++) {
                if(schedules[i].sel){
                    _total += schedules[i].price*schedules[i].num
                }
            }
            return _total
        },

        changeAllSelected(){
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    this.schedules[i].scheduletrips[j].sel = this.selectAll
                }
            }
            this.calculatePriceTotal()
        },


        // changeAllSelectedTypePoi(){
        //     for (var i = 0; i < this.schedules.length; i++) {
        //         for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
        //             this.schedules[i].scheduletrips[j].sel = this.selectAllPoi
        //         }
        //     }

        //     this.calculatePriceTotal()
        // },

        // changeAllSelectedTypeProduct(){
        //     for (var i = 0; i < this.schedules.length; i++) {
        //         for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
        //             this.schedules[i].scheduletrips[j].sel = this.selectAllProduct
        //         }
        //     }

        //     this.calculatePriceTotal()
        // },

        // changeAllSelectedTypeHotel(){
        //     for (var i = 0; i < this.schedules.length; i++) {
        //         for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
        //             this.schedules[i].scheduletrips[j].sel = this.selectAllHotel
        //         }
        //     }

        //     this.calculatePriceTotal()
        // },


        calculatePriceTotal(){
            var _total = 0;
            for (var i = 0; i < this.schedules.length; i++) {
                for (var j = 0; j < this.schedules[i].scheduletrips.length; j++) {
                    if(this.schedules[i].scheduletrips[j].sel){
                        _total += this.schedules[i].scheduletrips[j].price * this.schedules[i].scheduletrips[j].num
                    }
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
                        _data[i].scheduletrips = _data[i].scheduletrips.concat(_data[i].scheduleHotels)
                    }

                    for (var i = 0; i < _data.length; i++) {
                        for (var j = 0; j < _data[i].scheduletrips.length; j++) {
                            _data[i].scheduletrips[j]['num'] = 1
                            _data[i].scheduletrips[j]['sel'] = true
                        }
                    }

                    // var _dataHotel = res.data.data.ttripNoteSchedules
                    // for (var i = 0; i < _dataHotel.length; i++) {
                    //     for (var j = 0; j < _dataHotel[i].scheduletrips.length; j++) {
                    //         _dataHotel[i].scheduletrips[j]['num'] = 1
                    //         _dataHotel[i].scheduletrips[j]['sel'] = true
                    //     }
                    // }


                    this.schedules = _data
                }
            })
        },

        addNewCustomer(){
            this.customers.push({
                name: '',
                phone: '',
                idcard: ''
            })
        },

        delCustomer(index){
            this.customers.splice(index, 1)
        },

        backHome(){
            this.$router.push({path: '/main/route'})
        },
        goOrderHistory(){
            this.$router.push({path: '/orderHistory'})
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
            width: 480px;
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
                width: 120px;
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
            a{
                color: #23a16d;
            }
        }
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

    .commonBox{
        &.orderListBox{
            width: 1200px;
            margin: 20px auto;
        }
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
                width: 30%;
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
                width: 20%;
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
                width: 15%;
            }
            .opts{
                float: left;
                width: 10%;
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

    .orderConfirm{
        position: absolute;
        left: 50%;
        bottom: 20px;
        top: 80px;
        width: 1200px;
        margin-left: -600px;
        .commonCon{
            position: absolute;
            top: 46px;
            bottom: 0;
            left: 0;
            right: 0;
            overflow-y: auto;
            margin: 0;
            padding: 0 20px;
            &.orderListAll{
                bottom: 70px;
            }
        }
        .commonTit{
            height: 36px;
            border-bottom: 1px solid #eee;
        }
        .leftBox{
            position: absolute;
            right: 400px;
            left: 0;
            margin: 0;
            bottom: 0;
            top: 0;

            .customerList{
                margin-bottom: 30px;
                li{
                    overflow: hidden;
                    position: relative;
                    margin-top: 15px;
                    padding: 10px 0;
                    border: 1px solid #EAEDF1;
                    .no{
                        position: absolute;
                        left: -25px;
                        top: 10px;
                        height: 24px;
                        width: 90px;
                        transform: rotate(-45deg);
                        background: #DAE5E6;
                        line-height: 24px;
                        color: #fff;
                        text-align: center;
                    }
                    dl{
                        padding: 8px 0;
                        overflow: hidden;
                        dt{
                            float: left;
                            width: 120px;
                            line-height: 32px;
                            text-align: right;
                        }
                        dd{
                            width: 400px;
                            margin-left: 140px;
                        }
                    }
                    .del{
                        display: none;
                        position: absolute;
                        right: 20px;
                        top: 50%;
                        width: 20px;
                        height: 20px;
                        margin-top: -10px;
                        color: #D7DEE2;
                        cursor: pointer;
                        line-height: 20px;
                        i{
                            font-size: 20px;
                        }
                        &:hover{
                            color: #F56C6C;
                        }
                    }
                    &:hover{
                        .del{
                            display: block;
                        }
                    }
                }
                .addNewCustomer{
                    margin-top: 20px;
                }
            }

            .contactInfo{
                padding: 20px 0 40px;
                li{
                    padding: 12px 0;
                    label{
                        float: left;
                        width: 120px;
                        line-height: 32px;
                        text-align: right;
                    }
                    .con{
                        width: 400px;
                        margin-left: 140px;
                    }
                }
            }
        }
        .rightBox{
            position: absolute;
            right: 0;
            bottom: 0;
            top: 0;
            width: 380px;
            margin: 0;

            .filterBtns{
                float: right;
                .el-button-group{
                    margin-right: 0;
                }
            }

            .orderList{
                margin-bottom: 15px;
                .header{
                    overflow: hidden;
                    line-height: 40px;
                    border-bottom: 1px dashed #e2e2e2;
                    color: #999;
                }
                .tit{
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
                .date{
                    float: left;
                    color: #a0abb3;
                }
                .price{
                    float: right;
                    color: #23a16d;
                }
                .num{
                    clear: both;
                    text-align: right;
                    color: #a0abb3;
                }
                .total{
                    float: left;
                    width: 20%;
                }
                
                .item{
                    margin-bottom: 8px;
                    padding: 8px 0;
                    line-height: 24px;
                    border-bottom: 1px dashed #e2e2e2;
                    font-size: 13px;
                    .tit{
                        font-size: 14px;
                    }
                }
            }

            .totalAll{
                position: absolute;
                left: 20px;
                right: 20px;
                bottom: 20px;
                height: 50px;
                border-bottom: 1px solid #23a16d;
                line-height: 50px;
                .totalNum{
                    float: left;
                    span{
                        color: #23a16d;
                    }
                }
                .totalPrice{
                    float: right;
                    font-size: 16px;
                    font-weight: bold;
                    color: #23a16d;
                }
            }
        }
    }


    .orderPayment{
        width: 1200px;
        margin: 20px auto;
        padding: 20px;
        background: #fff;
        .info{
            border: 1px solid #DAE9E6;
            background: #F7FFFD;
            padding: 0 20px 10px;
            .tit{
                color: #23a16d;
                line-height: 54px;
                border-bottom: 1px solid #E1EEEB;
                font-size: 14px;
                i{
                    font-size: 16px;
                }
            }
            .orderNo{
                margin-top: 10px;
                line-height: 34px;
            }
            .price{
                line-height: 30px;
                color: #23a16d;
                font-size: 16px;
            }
        }
        .payment{
            margin-top: 20px;
            border: 1px solid #E0E6EA;
            ul{
                overflow: hidden;
                width: 1128px;
                margin: 0 40px;
                padding-bottom: 40px;
                border-bottom: 1px dashed #DBE2E6;
                &:last-child{
                    border: 0;
                }
                li{
                    float: left;
                    position: relative;
                    width: 202px;
                    height: 62px;
                    margin: 40px 30px 0;
                    border: 1px solid #E0E6EA;
                    cursor: pointer;
                    &.active{
                        position: relative;
                        overflow: hidden;
                        border-color: #23a16d;
                        &:after{
                            content: "\E611";
                            position: absolute;
                            bottom: 0;
                            right: 0;
                            color: #fff;
                            width: 15px;
                            font-weight: bold;
                            line-height: 16px;
                            text-align: center;
                            font-family: element-icons!important;
                        }
                        &:before{
                            content: '';
                            position: absolute;
                            bottom: -30px;
                            right: -30px;
                            height: 30px;
                            width: 100px;
                            transform: rotate(-45deg);
                            background: #23a16d;
                        }
                    }
                }
            }
        }

        .orderSuccess{
            height: 470px;
            border: 1px solid #DAE9E6;
            background: #F7FFFD;
            .icon{
                width: 75px;
                height: 75px;
                margin: 95px auto 0;
            }
            p{
                margin: 30px 0;
                text-align: center;
                font-size: 16px;
                color: #23a16d;
            }
            .btns{
                text-align: center;
                .el-button{
                    width: 115px;
                    height: 36px;
                    margin: 0 12px;
                    background: #F7FFFD;
                    &:hover{
                        background: #23a16d;
                        border-color: green;
                        color: #fff;
                    }
                }
            }
        }
    }



}
</style>