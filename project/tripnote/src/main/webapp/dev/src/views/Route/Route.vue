<template>
    <div class="routeCreate">
        <div class="topBar">
            <router-link to="/main/route" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">{{routeName}}</div>
            </div>

            <div class="btns">
                <el-button>取 消</el-button>
                <el-button type="primary" >发 布</el-button>
            </div>
        </div>

        <div class="columnBox dayList" :class="{setDayScheduleActive: setDayScheduleActive}">
            <div class="header">
                <div class="tit"><i class="iconfont icon-luxian"></i>行程路线</div>
                <div class="opts">
                    <a href="javascript:;" @click="setRouteLine" v-if="!setRouteLineActive" class="actionBtn">
                        <i class="iconfont icon-bianji-blue"></i>
                        <span>安排路线</span>
                    </a>
                </div>
                <!-- <a href="javascript:;" class="setRoute" >安排路线</a> -->
            </div>
            
            <div class="content">
                <ul>
                    <router-link :to="{path: '/route/' + routeId + '/intro'}" tag="li" class="summary" v-if="!setRouteLineActive">
                        线路总览
                    </router-link>

                    <router-link tag="li" v-for="(value, index) in scheduleInfo" :to="{path: '/route/' + routeId + '/day/' + value.id}">
                        <div class="day" :class="{noCity: value.citys.length==0}">D{{value.indexdate}}</div>
                        <div class="date" v-if="!setRouteLineActive">{{formatDateWeek(startDate, index)}}</div>
                        <div class="cityList">
                            <div class="item" v-for="(item, index) in value.citys">
                                <span class="cityName" :class="{init: !item.scheduleId}">
                                    {{item.nameCn}}
                                    <span class="delCity" @click="delDayCity(item.id)" v-if="item.scheduleId">
                                        <i class="iconfont icon-shanchu1"></i>
                                    </span>
                                </span>
                                <span class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </span>
                            </div>
                        </div>
                        <div class="del" @click.stop="delRouteDayConfirm(value.id, value.indexdate)" v-if="setRouteLineActive">
                            <i class="iconfont icon-cha"></i>
                        </div>
                        <div class="insertDay" v-if="setRouteLineActive">
                            <el-button size="mini" round icon="el-icon-plus" @click.stop="insertRouteDay(value.id,value.indexdate)">插入一天</el-button>
                        </div>
                    </router-link>

                    <li class="addDay" v-if="setRouteLineActive" @click="addRouteDay">
                        <i class="el-icon-circle-plus-outline"></i>
                        添加一天
                    </li>

                    <router-link :to="{path: '/route/' + routeId + '/remarks'}" tag="li" class="summary" v-if="!setRouteLineActive">
                        行程备注
                    </router-link>
                </ul>
            </div>
        </div>

        <router-view></router-view>

        <set-city 
            :dayId="dayId"
            :cityInfo="cityInfo"
            v-if="setRouteLineActive"
        ></set-city>

        <el-dialog title="提示" :visible.sync="dialogDelDayTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除这一天吗？</p>
                <p class="colorRed">您安排的这天所有的行程都会删除！</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelDayTip = false">取消</el-button>
                <el-button type="primary" @click.native="delRouteDay">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'

import SetCity from './RouteSetCity'

export default {
    components: {
        SetCity
    },
    mixins: [ FormatDateWeek ],
    data() {
        return {
            dialogAddVisible: false,
            routeName: '',
            scheduleInfo: [],
            startDate: '',
            dialogDelDayTip: false,
            delRouteDayId: '',
            delRouteDayIndex: '',
            cityInfo: [],

            addDayPrevCity: '',
            addDayIndexdate: '',
            addDayNextDayId: '',
            addDayInsertDay: '',
            addDayUpdateIndexList: [],

        }
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setDayScheduleActive: 'setDayScheduleActive',
            setRouteLineActive: 'setRouteLineActive'
        }),
        routeId(){
            return this.$route.params.id
        },
        dayId(){
            // if(this.routeInfo.daySel){
            //     return this.routeInfo.daySel
            // }else{
            //     var _path = this.$route.path

            //     return _path.split('/')[4] ? parseInt(_path.split('/')[4]) : -1
            // }
            console.log(this.$route.params.dayId)
            return this.$route.params.dayId ? parseInt(this.$route.params.dayId) : -1
        }
    },
    created(){
        this.getRouteInfo()
        var vm = this
        Bus.$on('refreshDay', function(){
            vm.getRouteInfo()
        })

        Bus.$on('addCityToDayInit', function(item){
            vm.addCityToDayInit(item)
        })

        Bus.$on('addToDaySubmit', function(item){
            console.log(123)
            vm.addToDaySubmit(item)
        })

        Bus.$on('addCityToDayAdd', function(item){
            vm.addCityToDayAdd(item)
        })

        Bus.$on('addCityToDayReduce', function(item){
            vm.addCityToDayReduce(item)
        })
    },

    // watch: {
    //     setRouteLineActive(value){
    //         if(value){
    //             this.initMap()
    //         }
    //     }
    // },

    methods: {
        addCityToDayInit(item){
            this.addDayPrevCity = ''
            this.addDayIndexdate = ''
            this.addDayNextDayId = ''
            this.addDayInsertDay = ''
            this.addDayUpdateIndexList = []

            var _indexdate;
            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(this.scheduleInfo[i].id == this.dayId){
                    if(i > 0 && this.scheduleInfo[i].citys.length == 0 && this.scheduleInfo[i-1].citys.length > 0){
                        var _prevCity = this.scheduleInfo[i-1].citys
                        _prevCity = _.clone(_prevCity[_prevCity.length - 1])

                        if(_prevCity.nameCn != item.nameCn){
                            delete _prevCity['scheduleId']

                            this.scheduleInfo[i].citys.push(_prevCity)
                            this.addDayPrevCity = _prevCity.cityId
                        }else{
                            this.addDayPrevCity = ''
                        }
                        
                    }else{
                        this.addDayPrevCity = ''
                    }

                    this.scheduleInfo[i].citys.push(item)
                    _indexdate = this.scheduleInfo[i].indexdate 
                    this.addDayIndexdate = _indexdate
                }
            }

            // this.scheduleInfo.insert(_indexdate, {
            //     citys: [item]
            //     id: -1,
            //     indexdate: 2,
            //     introduction: null,
            //     scheduletrips: null,
            //     title: null,
            //     traffics: null,
            //     tripnoteId: 72,
            //     usertripnotes: null
            // });


            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(this.scheduleInfo[i].indexdate == _indexdate + 1){
                    if(this.scheduleInfo[i].citys.length > 0){
                        this.scheduleInfo[i].citys = [item].concat(this.scheduleInfo[i].citys)
                        this.addDayNextDayId = this.scheduleInfo[i].id
                    }else{
                        this.addDayNextDayId = ''
                    }
                }
            }
        },

        // 添加一天
        addCityToDayAdd(item){
            // 插入数组元素
            Array.prototype.insert = function (index, item) {  
                this.splice(index, 0, item);  
            }

            this.scheduleInfo.insert(this.addDayIndexdate, {
                citys: [item],
                id: -1,
                indexdate: this.addDayIndexdate + 1,
                introduction: null,
                scheduletrips: null,
                title: '添加一天',
                traffics: null,
                tripnoteId: null,
                usertripnotes: null
            });

            this.addDayInsertDay = item.num

            this.addDayUpdateIndexList = []
            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(i > this.addDayIndexdate){
                    this.scheduleInfo[i].indexdate = this.scheduleInfo[i].indexdate + 1

                    if(this.scheduleInfo[i].id != -1){
                        this.addDayUpdateIndexList.push({
                            scheduleid: this.scheduleInfo[i].id,
                            indexdate: this.scheduleInfo[i].indexdate
                        })
                    }
                }
            }
        },

        // 减少一天
        addCityToDayReduce(item){
            // 插入数组元素
            Array.prototype.insert = function (index, item) {  
                this.splice(index, 0, item);  
            }

            this.scheduleInfo.splice(this.addDayIndexdate, 1)

            this.addDayInsertDay = item.num

            this.addDayUpdateIndexList = []
            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(i >= this.addDayIndexdate){
                    this.scheduleInfo[i].indexdate = this.scheduleInfo[i].indexdate - 1

                    if(this.scheduleInfo[i].id != -1){
                        this.addDayUpdateIndexList.push({
                            scheduleid: this.scheduleInfo[i].id,
                            indexdate: this.scheduleInfo[i].indexdate
                        })
                    }
                }
            }
        },

        // 添加当天的城市
        async addToDaySubmit(item){
            console.log(item)

            console.log('addDayPrevCity', this.addDayPrevCity)
            console.log(this.addDayInsertDay)
            console.log(this.addDayUpdateIndexList)

            var res0 = {data:{code:1}}
            var res1
            if(this.addDayPrevCity != ''){
                res0 = await this.$http({
                    method: 'post',
                    url: '/tripnote/tripnote/schedulecity/doAdd',
                    data:{
                        cityId: this.addDayPrevCity,
                        scheduleId: this.dayId
                    }
                })
                if(res0.data.code == -1){
                    this.$message({
                        message: res0.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    // 添加当天的城市
                    res1 = await this.$http({
                        method: 'post',
                        url: '/tripnote/tripnote/schedulecity/doAdd',
                        data:{
                            cityId: item.id,
                            scheduleId: this.dayId
                        }
                    })
                    if(res1.data.code == -1){
                        this.$message({
                            message: res1.data.message,
                            type: 'error',
                            duration: 2000
                        });
                    }
                }
            }else{
                // 添加当天的城市
                res1 = await this.$http({
                    method: 'post',
                    url: '/tripnote/tripnote/schedulecity/doAdd',
                    data:{
                        cityId: item.id,
                        scheduleId: this.dayId
                    }
                })
                if(res1.data.code == -1){
                    this.$message({
                        message: res1.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }
            }

            // 添加插入的天及城市
            var _data = []
            for (var i = 0; i < this.addDayInsertDay; i++) {
                _data.push({
                    indexdate: this.addDayIndexdate + i + 1,
                    cityId: item.id
                })
            }

            const res2 = await this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doAddIndexdate/' + this.routeId,
                data:{
                    indexdates: JSON.stringify({"indexdates":_data}),
                }
            })
            if(res2.data.code == -1){
                this.$message({
                    message: res2.data.message,
                    type: 'error',
                    duration: 2000
                });
            }

            var res3 = {data:{code:1}}
            if(this.addDayNextDayId != ''){
                // 添加下一天的城市
                res3 = await this.$http({
                    method: 'post',
                    url: '/tripnote/tripnote/schedulecity/doAddFrist',
                    data:{
                        cityId: item.id,
                        scheduleId: this.addDayNextDayId
                    }
                })
                if(res3.data.code == -1){
                    this.$message({
                        message: res3.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }
            }
            

            // 更新nextday的indexdate
            var _data = []
            for (var i = 0; i < this.addDayUpdateIndexList.length; i++) {
                this.addDayUpdateIndexList[i]
            }
            const res4 = await this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doUpdateIndexDate',
                data: {
                    indexdates: JSON.stringify({"indexdates": this.addDayUpdateIndexList}),
                }
            })
            if(res4.data.code == -1){
                this.$message({
                    message: res4.data.message,
                    type: 'error',
                    duration: 2000
                });
            }

            if(res0.data.code && res1.data.code && res2.data.code && res3.data.code && res4.data.code){
                console.log('done')
                this.getRouteInfo()
            }
        },


        setRouteLine(){
            // this.setRouteLineActive = !this.setRouteLineActive
            this.$store.dispatch('setRouteLineActive', !this.setRouteLineActive)
        },

        getRouteInfo(){
            var _routeId = this.routeId
            this.$http({
                method: 'get',
                url: '/tripnote/tripnote/doDetail/' + _routeId,
            }).then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.routeName = res.data.data.title
                    this.scheduleInfo = res.data.data.ttripNoteSchedules

                    this.startDate = res.data.data.startDate

                    var _routeInfo = $.extend({}, this.routeInfo, {
                        intro: res.data.data.introduction,
                        remarks: res.data.data.remarks,
                        dayInfo: this.scheduleInfo
                    })

                    this.$store.dispatch('setRouteInfo', _routeInfo)

                    var _cityList = []

                    for (var i = 0; i < this.scheduleInfo.length; i++) {
                        _cityList = _cityList.concat(this.scheduleInfo[i].citys)
                    }

                    this.cityInfo = _cityList


                    // Bus.$emit('refreshDayCity', true)

                }
            })
        },

        delRouteDayConfirm(id, index){
            this.delRouteDayId = id
            this.delRouteDayIndex = index
            this.dialogDelDayTip = true
        },

        delRouteDay(){
            var vm = this
            this.$http({
                method: 'get',
                url: '/tripnote/tripnote/schedule/doDelete/' + this.delRouteDayId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    vm.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    vm.dialogDelDayTip = false
                    vm.$message({
                        message: '删除成功！',
                        type: 'success',
                        duration: 2000
                    })

                    var _data = []
                    for (var i = 0; i < vm.scheduleInfo.length; i++) {
                        if(vm.scheduleInfo[i].indexdate > vm.delRouteDayIndex){
                            var _index = vm.scheduleInfo[i].indexdate - 1
                            _data.push({
                                'indexdate': _index,
                                'scheduleid': vm.scheduleInfo[i].id
                            })
                        }
                    }
                    vm.$http({
                        method: 'post',
                        url: '/tripnote/tripnote/schedule/doUpdateIndexDate',
                        data: {
                            indexdates: JSON.stringify({"indexdates":_data}),
                        }
                    })
                    .then((res)=>{
                        if(res.data.code == -1){
                            vm.$message({
                                message: res.data.message,
                                type: 'error',
                                duration: 2000
                            });
                        }else{
                            vm.getRouteInfo()
                        }
                    })
                }
            })
        },
        
        addRouteDay(){
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doAdd',
                data: {
                    indexdate: this.scheduleInfo.length + 1,
                    tripnoteId: this.routeId
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
                    this.getRouteInfo()
                }
            })
        },


        insertRouteDay(id, indexdate){
            var _data = []
            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(this.scheduleInfo[i].indexdate >= indexdate){
                    var _index = this.scheduleInfo[i].indexdate + 1
                    _data.push({
                        'indexdate': _index,
                        'scheduleid': this.scheduleInfo[i].id
                    })
                }
            }
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doUpdateIndexDate',
                data: {
                    indexdates: JSON.stringify({"indexdates":_data}),
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
                    this.$http({
                        method: 'post',
                        url: '/tripnote/tripnote/schedule/doAdd',
                        data: {
                            indexdate: indexdate,
                            tripnoteId: this.routeId
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
                            this.getRouteInfo()
                        }
                    })
                }
            })
        },

        delDayCity(id){
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedulecity/doDelete/' + id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.getRouteInfo()
                }
            })
        }
    },

    destroyed(){
        Bus.$off('refreshDay')

        Bus.$off('addCityToDayInit')

        Bus.$off('addToDaySubmit')

        Bus.$off('addCityToDayAdd')

        Bus.$off('addCityToDayReduce')


        this.$store.dispatch('setRouteLineActive', false)
    }
}
</script>
<style lang="less" scope>
.routeCreate{
    .topBar{
        height: 60px;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #DADEE5;
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
            }
        }
        .btns{
            float: right;
            margin: 12px 20px;
            .el-button{
                height: 36px;
                padding: 5px 35px;
            }
        }
    }

    .detailBox{
        // width: 1200px;
        margin: 20px;
        background: #fff;
        box-shadow: 0 0 10px rgba(0,0,0,.1);
    }
    .detailHeader{
        height: 54px;
        border-bottom: 1px solid #eaedf1;
        .tit{
            float: left;
            margin-left: 20px;
            padding-left: 22px;
            background: url(../../assets/images/icon_map_point.png) left center no-repeat;
            line-height: 56px;
            font-size: 16px;
        }
        .filterBox{
            float: right;
            margin: 10px 5px 0 0;
            li{
                float: left;
                padding: 0 15px;
                border-left: 1px solid #EBEEF2;
                font-size: 14px;

                .selType{
                    border: 0;
                    .el-input{
                        width: 100px;
                    }
                    .el-input__inner{
                        border: 0;
                        font-size: 14px;
                    }
                    .el-icon-arrow-up:before{
                        content: "\E60B"
                    }
                    .el-input .el-select__caret{
                        transform: none;
                    }
                }
                .filterKeywords{
                    width: 200px;
                    height: 34px;
                    border-radius: 34px;
                    background: #EDF1F2;
                    input{
                        height: 34px;
                        padding-left: 17px;
                        line-height: 34px;
                        background: none;
                        border: 0;
                    }
                    .btnSubmit{
                        float: right;
                        width: 45px;
                        height: 34px;
                        background: url(../../assets/images/icon_search.png) center center no-repeat;
                    }
                    &:active,
                    &:hover{
                        background: #eee;
                    }
                }
            }
        }
    }
    .detailCon{
        background: #fff;
    }
    .dayList{
        top: 80px;
        bottom: 20px;
        left: 20px;
        width: 260px;
        transition: all .3s ease;
        &.setDayScheduleActive{
            width: 140px;
            .header{
                .opts{
                    display: none;
                }
            }
            .content{
                li{
                    .date{
                        width: 35px;
                    }
                }
            }
        }
        .header{
            .tit{
                i{
                    display: inline-block;
                    font-size: 24px;
                    vertical-align: middle;
                    margin-top: -3px;
                }
            }
        }
        .content{
            li{
                position: relative;
                min-height: 100px;
                padding: 12px 15px;
                border-bottom: 1px solid #EBEEF2;
                color: #666;
                cursor: pointer;
                .day{
                    float: left;
                    line-height: 36px;
                    font-size: 36px;
                    &.noCity{
                        color: #C8D0D5;
                    }
                }
                .date{
                    float: right;
                    font-size: 12px;
                    color: #A1ACB3;
                }
                .cityList{
                    clear: both;
                    overflow: hidden;
                    padding-top: 15px;
                    font-size: 14px;
                    .item{
                        float: left;
                        .cityName{
                            float: left;
                            position: relative;
                            .delCity{
                                display: none;
                                position: absolute;
                                right: -5px;
                                top: -12px;
                                i{
                                    font-size: 14px;
                                    color: #F56C6C;
                                    &:hover{
                                        color: red;
                                    }
                                }
                            }
                            &.init{
                                padding: 0 5px;
                                border: 1px dashed #ccc;
                            }
                            &:hover{
                                .delCity{
                                    display: block;
                                }
                            }
                        }
                        .arrow{
                            float: left;
                            padding: 0 5px;
                        }
                        &:last-child{
                            .arrow{
                                display: none;
                            }
                        }
                    }
                }
                .del{
                    position: absolute;
                    top: 12px;
                    right: 15px;
                    i{
                        font-size: 20px;
                        color: #D7DEE2;
                        &:hover{
                            color: #F56C6C;
                        }
                    }
                }
                .insertDay{
                    position: absolute;
                    width: 97px;
                    height: 22px;
                    left: 80px;
                    top: -11px;
                    text-align: center;
                    .el-button{
                        visibility: hidden;
                        height: 22px;
                        margin: 0 auto;
                        padding: 0 15px;
                        line-height: 20px;
                        &:hover{
                            visibility: visible;
                        }
                    }
                    &:hover{
                        .el-button{
                            visibility: visible;
                        }
                    }
                }
                &.summary{
                    min-height: 55px;
                    height: 55px;
                    line-height: 32px;
                    font-size: 16px;
                }
                &.active{
                    background: #F7F8F9;
                    .day{
                        color: #23a16d;
                    }
                }
                &:hover{
                    background: #F7F8F9;
                }
                &.addDay{
                    min-height: 55px;
                    height: 55px;
                    line-height: 32px;
                    font-size: 16px;
                    text-align: center;
                    i{
                        margin-right: 3px;
                        vertical-align: middle;
                        margin-top: -3px;
                        font-size: 20px;
                        color: #23a16d;
                    }
                }
            }
        }
    }
    .routeDetail{
        position: absolute;
        padding: 0 20px;
        top: 134px;
        bottom: 20px;
        left: 341px;
        right: 20px;
        overflow-y: auto;
        background: #fff;
        border-left: 1px solid #EBEEF2;
        box-shadow: 0 3px 10px rgba(0,0,0,.1);
        .routeDetailHeader{
            height: 50px;
            border-bottom: 1px solid #EBEEF2;
            line-height: 50px;
            .tit{
                float: left;
                color: #bac3c9;
                font-size: 14px;
            }
            .checkbox{
                float: right;
                margin-right: 15px;
                margin-top: -2px;
                label{
                    display: inline-block;
                    margin-right: 10px;
                    font-size: 16px;
                }
                .checkboxC{
                    display: inline-block;
                    vertical-align: middle;
                }
            }
        }
        
    }

}
</style>