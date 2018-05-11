<template>
    <div class="routeCreate">
        <div class="topBar">
            <router-link to="/main/route" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">{{routeName}}</div>
            </div>

            <div class="optBtns">
                <ul>
                    <!-- <li>
                        <i class="icon0"></i>
                        <p>线路模板</p>
                        <div class="list">
                            <div class="item" @click="goImport">导入线路模板</div>
                            <div class="item" @click="goExport">导出线路模板</div>
                        </div>
                    </li> -->

                    <li @click="showBaseInfo">
                        <i class="icon1"></i>
                        <p>定制信息</p>
                    </li>
                    <router-link :to="{path: '/order/' + routeId}" tag="li">
                        <i class="icon2"></i>
                        <p>行程报价</p>
                    </router-link>
                </ul>
            </div>
        </div>

        <div class="columnBox dayList" :class="{setDayScheduleActive: setDayScheduleActive, setDayPoiEditActive: setDayPoiEditActive}">
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
                    <router-link :to="{path: '/template/' + routeId + '/intro'}" tag="li" class="summary" v-if="!setRouteLineActive && !setDayScheduleActive">
                        线路总览
                    </router-link>

                    <li v-for="(value, index) in scheduleInfo" @click="toDayDetail(value)" :class="{active: daySel==value.id}">
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
                    </li>

                    <li class="addDay" v-if="setRouteLineActive" @click="addRouteDay">
                        <i class="el-icon-circle-plus-outline"></i>
                        添加一天
                    </li>

                    <router-link :to="{path: '/template/' + routeId + '/remarks'}" tag="li" class="summary" v-if="!setRouteLineActive && !setDayScheduleActive">
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

        <el-dialog title="完善定制信息" :visible.sync="dialogBaseInfoVisible" width="600px">
            <el-form ref="form" :model="form" label-width="70px" class="formAdd">
                <el-form-item label="名称:">
                    <el-input v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item label="行程封面:">
                    <div class="routePic" :style="{backgroundImage: `url(${routeImgFormat(form.imageurl)})`}"></div>
                    <el-upload
                        class="uploadRoutePic"
                        action="/tripnote/common/upload/route/image"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        >
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                    </el-upload>

                </el-form-item>

              <el-form-item label="行程时段:">
                <el-col :span="10">
                    <el-date-picker
                        :picker-options="pickerOptions"
                        class="dateRange"
                        v-model="form.dateRange"
                        type="daterange"
                        range-separator="至"
                        :clearable="false"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-col>
                <el-col class="line" :span="4">
                    <span class="label">行程天数:</span>
                </el-col>
                <el-col :span="10">
                  <el-input v-model="form.days" :disabled="true"></el-input>
                </el-col>
              </el-form-item>

                  <el-form-item label="出发城市:">
                    <el-col :span="10">
                      <el-input v-model="form.startCity"></el-input>
                    </el-col>
                    <el-col class="line" :span="4">
                        <span class="label">返回城市:</span>
                    </el-col>
                    <el-col :span="10">
                      <el-input v-model="form.destination"></el-input>
                    </el-col>
                  </el-form-item>

                <el-form-item label="客人信息:">
                    <div class="personList">
                        <ul>
                            <li v-for="(item, index) in form.personList">
                                <div class="done" v-if="item.type == 'done'">
                                    {{item.name}}&nbsp;&nbsp;{{item.phone}} 
                                    <a href="javascript:;" class="iconEdit" @click="item.type = 'edit'"></a>
                                </div>
                                <div v-if="item.type == 'edit'">
                                    <el-input placeholder="客人姓名" v-model="item.name" class="inputText"></el-input>
                                    <el-input placeholder="联系方式" v-model="item.phone" class="inputText"></el-input>
                                    <el-button icon="el-icon-check" class="btn btnAdd" @click="addPersonItem(item)"></el-button>
                                    <el-button icon="el-icon-close" class="btn btnDel" @click="delPersonItem(index)"></el-button>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="addPerson" @click="newPersonItem">
                        <i class="el-icon-circle-plus-outline"></i>
                        添加客户信息
                    </div>
                </el-form-item>

                <el-form-item label="备注:">
                    <el-input
                        type="textarea"
                        :rows="3"
                        placeholder="添加备注信息（限200个中文字符）"
                        v-model="form.remarks">
                    </el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogBaseInfoVisible = false">取 消</el-button>
                <el-button :disabled="isDisable" type="primary" @click.native="saveRouteInfo()">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'
import moment from 'moment'

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

            dialogBaseInfoVisible: false,
            dialogImportVisible: false,
            dialogExportVisible: false,

            form: {
                title: '',
                days: '',
                imageurl: '',
                destination: '',
                dateRange: '',
                remark: '',
                personList: [],
                startCity: ''
            },

            // daySel: ''
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
        dayId(){
            // if(this.routeInfo.daySel){
            //     return this.routeInfo.daySel
            // }else{
            //     var _path = this.$route.path

            //     return _path.split('/')[4] ? parseInt(_path.split('/')[4]) : -1
            // }
            return this.$route.params.dayId ? parseInt(this.$route.params.dayId) : -1
        },

        daySel(){
            return this.$route.params.dayId
        }
    },
    created(){
        this.getRouteInfo()

        if(this.$route.params.dayId){
            console.log('dayId', this.$route.params.dayId)
            // this.daySel = this.$route.params.dayId
        }

            
        var vm = this
        Bus.$on('refreshDay', function(){
            vm.getRouteInfo()
        })

        Bus.$on('addCityToDayInit', function(item){
            vm.addCityToDayInit(item)
        })

        Bus.$on('addToDaySubmit', function(item){
            vm.addToDaySubmit(item)
        })

        Bus.$on('addCityToDayAdd', function(item){
            vm.addCityToDayAdd(item)
        })

        Bus.$on('addCityToDayReduce', function(item){
            vm.addCityToDayReduce(item)
        })
    },

    watch: {
        'form.dateRange'(val){
            if(val != ''){
                this.form.days = (new Date(val[1]).getTime() - new Date(val[0]).getTime())/(24*60*60*1000) + 1
            }
        }
    },

    methods: {
        goImport(){
            this.$router.push({path: '/route/' + this.routeId + '/importTemplate'})
        },

        goExport(){
            this.$router.push({path: '/route/' + this.routeId + '/exportTemplate'})
        },

        routeImgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        saveRouteInfo(){
            var _custom = []
            for (var i = 0; i < this.form.personList.length; i++) {
                if(this.form.personList[i].type == 'done'){
                    _custom.push({
                        name: this.form.personList[i].name,
                        phone: this.form.personList[i].phone,
                    })
                }
            }

            this.$http({
                method: 'POST',
                url: '/tripnote/doUpdate/' + this.routeId,
                data: {
                    title: this.form.title,
                    imageurl: this.form.imageurl,
                    days: this.form.days,
                    startDate: moment(this.form.dateRange[0]).format('YYYY-MM-DD'),
                    endDate: moment(this.form.dateRange[1]).format('YYYY-MM-DD'),
                    remarks: this.form.remarks,
                    startCity: this.form.startCity,
                    destination: this.form.destination,
                    customers: JSON.stringify({"customer":_custom}),
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
                    this.$message({
                        message: '修改成功！',
                        type: 'success',
                        duration: 2000
                    });
                    this.dialogBaseInfoVisible = false
                }
            })
        },

        showBaseInfo(){
            this.dialogBaseInfoVisible = true
        },

        newPersonItem(){
            this.form.personList.push({
                name: '',
                phone: '',
                type: 'edit'
            })
        },
        addPersonItem(item){
            item.type = 'done'
        },
        delPersonItem(index){
            this.form.personList.splice(index, 1)
        },


        toDayDetail(item){
            // this.daySel = item.id

            if(this.$route.name == 'schedule'){
                this.$router.push({path: '/template/' + this.routeId + '/day/' + item.id + '/schedule'})
            }else{
                this.$router.push({path: '/template/' + this.routeId + '/day/' + item.id})
            }
            
        },

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
            // console.log(item)

            // console.log('addDayPrevCity', this.addDayPrevCity)
            // console.log(this.addDayInsertDay)
            // console.log(this.addDayUpdateIndexList)

            var res0 = {data:{code:1}}
            var res1
            if(this.addDayPrevCity != ''){
                res0 = await this.$http({
                    method: 'post',
                    url: '/tripnote/schedulecity/doAdd',
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
                        url: '/tripnote/schedulecity/doAdd',
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
                    url: '/tripnote/schedulecity/doAdd',
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
                url: '/tripnote/schedule/doAddIndexdate/' + this.routeId,
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
                    url: '/tripnote/schedulecity/doAddFrist',
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
                url: '/tripnote/schedule/doUpdateIndexDate',
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
                url: '/tripnote/doDetail/' + _routeId,
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

                    Object.assign(this.$data.form, res.data.data)

                    this.form.dateRange = [new Date(this.form.startDate), new Date(this.form.endDate)]

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
                url: '/tripnote/schedule/doDelete/' + this.delRouteDayId,
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
                        url: '/tripnote/schedule/doUpdateIndexDate',
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
                url: '/tripnote/schedule/doAdd',
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
                url: '/tripnote/schedule/doUpdateIndexDate',
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
                        url: '/tripnote/schedule/doAdd',
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
                url: '/tripnote/schedulecity/doDelete/' + id,
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

        handleAvatarSuccess(res, file) {
            this.form.imageurl = res.link
        },
    },

    destroyed(){
        Bus.$off('refreshDay')

        Bus.$off('addCityToDayInit')

        Bus.$off('addToDaySubmit')

        Bus.$off('addCityToDayAdd')

        Bus.$off('addCityToDayReduce')


        this.$store.dispatch('setRouteLineActive', false)
        this.$store.dispatch('setDayScheduleActive', false)
        this.$store.dispatch('setDayPoiEditActive', false)
    }
}
</script>
<style lang="less" scope>
.uploadRoutePic{
    margin-left: 180px;
}
</style>