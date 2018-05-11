<template>
    <div>
        <div class="poiDetailBg"></div>
        <div class="poiDetail">
            <div class="header">
                <div class="tit">查看产品详情</div>
                <a href="javascript:;" class="closeBtn" @click="closeBtn">
                    <i class="iconfont icon-guanbi"></i>
                </a>
            </div>

            <div class="con productInfo">
                <div class="baseInfo">
                    <div class="mainPic" :style="{backgroundImage: `url(${formatImg(imageurl)})`}"></div>
                    <div class="info">
                        <div class="tit">{{nameCn}}</div>
                        <div class="subTit">{{nameEn}}</div>
                        <div class="infoList">
                            <ul>
                                <li>城市：成都</li>
                                <li>语言：汉语</li>
                                <li>类型：{{typeFormat(type)}}</li>
                                <li>产品编号：{{id}}</li>
                            </ul>
                        </div>

                        <div class="selectOpts">
                            <ul>
                                <li class="clearfix">
                                    <label>套餐类型：</label>
                                    <div class="opts">
                                        <div class="selItem active">
                                            2日1园
                                        </div>
                                        <div class="selItem">
                                            3日1园
                                        </div>
                                        <div class="selItem">
                                            4日1园
                                        </div>
                                        <div class="selItem">
                                            5日1园
                                        </div>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <label>出行类型：</label>
                                    <div class="opts">
                                        <div class="selItem active">
                                            成人
                                        </div>
                                        <div class="selItem">
                                            儿童(3-9岁)
                                        </div>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <label>数量：</label>
                                    <div class="opts">
                                        <el-input-number size="small" v-model="num6"></el-input-number>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <label>使用日期：</label>
                                    <div class="opts">
                                        <el-date-picker
                                            v-model="value1"
                                            type="date"
                                            placeholder="请选择使用日期">
                                        </el-date-picker>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <label>总价：</label>
                                    <div class="opts">
                                        <span class="price">¥<strong>{{price}}</strong></span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="detailInfo">
                    <div class="tabs">
                        <ul>
                            <li :class="{active: detailType==1}" @click="detailType=1">产品介绍</li>
                            <li :class="{active: detailType==2}" @click="detailType=2">费用说明</li>
                            <li :class="{active: detailType==3}" @click="detailType=3">预定须知</li>
                            <li :class="{active: detailType==4}" @click="detailType=4">实用指南</li>
                            <li :class="{active: detailType==5}" @click="detailType=5">交通信息</li>
                            <li :class="{active: detailType==6}" @click="detailType=6">用户评价(24)</li>
                        </ul>
                    </div>

                    <div class="detailCon">
                        <div class="detailTit">
                            {{detailTypeName}}
                        </div>
                        <div v-if="detailType==1">
                            {{consumDescription}}
                        </div>

                        <div v-if="detailType==2">
                            {{openTime}}
                        </div>

                        <div v-if="detailType==3">
                            <!-- {{guide}} -->
                        </div>

                        <div v-if="detailType==4">
                            {{guide}}
                        </div>

                        <div v-if="detailType==5">
                            {{trafficInstructions}}
                        </div>

                        <div v-if="detailType==6">
                            
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer">
                <div class="btns">
                    <el-button type="primary" @click="addToSchedule">添加</el-button>
                    <el-button @click="closeBtn">取消</el-button>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import {mapState} from 'vuex'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            editStatus: false,
            map: '',
            mapBig: '',
            tabActive: 'base',

            poiTypeOpts: [{
                value: 1,
                name: '餐饮'
            },{
                value: 2,
                name: '游览'
            },{
                value: 3,
                name: '购物'
            },{
                value: 4,
                name: '娱乐'
            }],

            id: '',
            nameCn: '',
            nameEn: '',
            address: '',
            lon: '',
            lat: '',
            cityId: '',
            cityname: '',
            type: '',
            permissionsType: '',
            phone: '',
            openTime: '',
            consumDescription: '',
            trafficInstructions: '',
            timeReference: '',
            price: '',
            addressInstrations: '',
            supplier: '',
            url: '',
            createTime: '',
            createUserId: '',
            guide: '',
            imageurl: '',

            mapInit: false,

            bigMapVisible: false,

            poiSelected: false,

            detailType: 1,
        }
    },
    
    watch: {
    },
    created(){
        this.getDetail()

        if(this.dayInfo){
            var _poiSel = []
            for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
                _poiSel.push(this.dayInfo.scheduletrips[i].poiId)
            }

            if(_poiSel.indexOf(parseInt(this.poiId)) > -1){
                this.poiSelected = true
            }else{
                this.poiSelected = false
            }
        }
    },

    mounted(){
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            dayInfo: 'dayInfo',
            setRouteLineActive: 'setRouteLineActive',
            setDayScheduleActive: 'setDayScheduleActive'
        }),
        poiId(){
            return this.$route.params.poiId
        },
        dayId(){
            return this.$route.params.dayId
        },
        detailTypeName(){
            var type = this.detailType
            if(type == 1){
                return '产品介绍'
            }else if(type == 2){
                return '费用说明'
            }else if(type == 3){
                return '预定须知'
            }else if(type == 4){
                return '实用指南'
            }else if(type == 5){
                return '交通信息'
            }else if(type == 6){
                return '用户评价'
            }
        }
    },
    methods: {
        formatImg(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        typeFormat(type){
            if(type == 1){
                return '<i class="iconfont icon-canyin"></i>餐饮'
            }else if(type == 2){
                return '<i class="iconfont icon-travel"></i>游览'
            }else if(type == 3){
                return '<i class="iconfont icon-gouwu"></i>购物'
            }else if(type == 4){
                return '<i class="iconfont icon-kuaiyule"></i>娱乐'
            }
        },

        getDetail(){
            this.$http({
                method: 'post',
                url: '/poi/doDetail/' + this.poiId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    Object.assign(this.$data, res.data.data)
                }
            })
        },

        savePoi(){
            var _data = {}
            for(var k in this.form){
                if(this.form[k]){
                    _data[k] = this.form[k]
                }
            }
            this.$http({
                method: 'post',
                url: '/poi/doUpdate/' + this.poiId,
                data: _data
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.getDetail()
                    this.editStatus = false
                }
            })
        },

        closeBtn(){
            this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/schedule'})
        },

        addToSchedule(id){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletrip/poi/doAdd',
                data: {
                    scheduleId: this.dayId,
                    sourceId: this.poiId,
                    sort: this.dayInfo.scheduletrips.length + 1
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/schedule'})
                    Bus.$emit('refreshSchedule', true)
                }
            })
        },
    },
}
</script>
<style lang="less" scope>
.poiDetailBg{
    position: fixed;
    z-index: 3;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    opacity: 0.5;
    background: #000;
}
.poiDetail{
    position: absolute;
    top: 20px;
    left: 50%;
    z-index: 2000;
    width: 960px;
    margin-left: -480px;
    margin-bottom: 30px;
    background: #fff;
    .header{
        height: 51px;
        border-bottom: 1px solid #e2e2e2;
        .tit{
            float: left;
            margin-left: 15px;
            line-height: 50px;
            font-size: 16px;
        }
        .tabs{
            float: left;
            li{
                float: left;
                margin-left: 30px;
                &.line{
                    float: left;
                    width: 0;
                    height: 28px;
                    margin-top: 11px;
                    border-right: 1px solid #e2e2e2;
                }
                &:last-child{
                    a{
                        border-right: 0;
                    }
                }
                a{
                    display: block;
                    height: 40px;
                    margin-top: 11px;
                    line-height: 28px;
                    font-size: 16px;
                    color: #8F9BA2;
                    &.active{
                        border-bottom: 2px solid #23a16d;
                        color: #444;
                    }
                }
            }
        }
        .btns{
            float: right;
            margin: 8px 20px 0 0;
            .el-button{
                border-color: #23a16d;
                color: #23a16d;
            }
        }
        .closeBtn{
            float: right;
            width: 50px;
            height: 50px;
            border-left: 1px solid #e2e2e2;
            line-height: 50px;
            text-align: center;
            i{
                font-size: 20px;
                font-weight: bold;
                color: #A1ACB3;
            }
            &:hover{
                background: #f1f1f1;
            }
        }
    }
    .con{
        padding: 20px 30px;
        &.productInfo{
            .baseInfo{
                overflow: hidden;
                .mainPic{
                    float: left;
                    width: 380px;
                    height: 360px;
                    text-align: center;
                    background-size: cover;
                    background-position: center;
                }
                .info{
                    margin-left: 400px;
                    .tit{
                        font-size: 18px;
                        font-weight: bold;
                        color: #23a16d;
                    }
                    .subTit{
                        height: 24px;
                        line-height: 24px;
                        color: #999;
                    }
                    .infoList{
                        overflow: hidden;
                        padding: 8px 0 10px;
                        border-bottom: 1px solid #e2e2e2;
                        li{
                            float: left;
                            margin-right: 20px;
                            padding-right: 15px;
                            border-right: 1px solid #e2e2e2;
                            line-height: 18px;
                            font-size: 14px;
                            &:last-child{
                                border-right: 0;
                            }
                        }
                    }
                    .selectOpts{
                        margin-top: 10px;
                        overflow: hidden;
                        li{
                            padding: 10px 0;
                            label{
                                float: left;
                                width: 75px;
                                line-height: 32px;
                                color: #999;
                                font-size: 14px;
                            }
                            .opts{
                                margin-left: 80px;
                                .selItem{
                                    float: left;
                                    height: 32px;
                                    margin-right: 10px;
                                    padding: 0 15px;
                                    border: 1px solid #DEE3E6;
                                    line-height: 30px;
                                    overflow: hidden;
                                    &.active{
                                        position: relative;
                                        border: 2px solid #23a16d;
                                        line-height: 28px;
                                        &:after{
                                            font-family: element-icons!important;
                                            content: "\E611";
                                            position: absolute;
                                            right: 0;
                                            bottom: 0;
                                            color: #fff;
                                            font-size: 12px;
                                            line-height: 8px;
                                        }
                                        &:before{
                                            content: "";
                                            position: absolute;
                                            right: -6px;
                                            bottom: -12px;
                                            width: 15px;
                                            height: 35px;
                                            background: #23a16d;
                                            transform: rotate(50deg);
                                        }
                                    }
                                }
                                .price{
                                    line-height: 32px;
                                    font-size: 14px;
                                    font-weight: bold;
                                    color: #23a16d;
                                    strong{
                                        font-size: 20px;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            .detailInfo{
                margin-top: 40px;
                .tabs{
                    height: 45px;
                    background: #EEF1F3;
                    border-bottom: 1px solid #E5EAEE;
                    li{
                        float: left;
                        margin-right: 60px;
                        padding: 0 10px;
                        line-height: 43px;
                        font-size: 14px;
                        cursor: pointer;
                        &.active{
                            border-bottom: 2px solid #23a16d;
                            color: #23a16d;
                        }
                    }
                }
                .detailCon{
                    line-height: 24px;
                    font-size: 14px;
                    .detailTit{
                        margin: 20px 0 10px;
                        padding-left: 8px;
                        line-height: 14px;
                        font-size: 14px;
                        border-left: 4px solid #23a16d;
                    }
                }
            }
        }
    }
    .footer{
        height: 60px;
        background: #E2E2E2;
        .btns{
            float: right;
            margin: 14px 30px 0 0;
            .el-button{
                width: 70px;
                margin-left: 5px;
            }
        }
    }
}

</style>