<template>
    <div class="routeCreate">
        <div class="topBar">
            <a href="javascript:;" class="backBtn" @click="backList"></a>
            <div class="typeSel">
                <div class="templateName">{{routeName}}</div>
            </div>

            <div class="optBtns">
                <ul>
                    <li>
                        <i class="icon0"></i>
                        <p>线路模板<span class="el-icon-caret-bottom"></span></p>
                        <div class="list">
                            <div class="item" @click="goImport">导入线路模板</div>
                            <div class="item" @click="goExport">导出线路模板</div>
                        </div>
                    </li>

                    <li @click="showBaseInfo">
                        <i class="icon1"></i>
                        <p>定制信息</p>
                    </li>
                    <router-link :to="{path: '/order/' + routeId}" tag="li">
                        <i class="icon2"></i>
                        <p>行程报价</p>
                    </router-link>
                    <router-link :to="{path: '/publish/' + routeId}" tag="li">
                        <i class="icon3"></i>
                        <p>发布</p>
                    </router-link>
                </ul>
            </div>
        </div>

        <div class="columnBox dayList" @mouseenter="setBtnActive=true" @mouseleave="setBtnActive=false" :class="{setDayScheduleActive: setDayScheduleActive, setDayPoiEditActive: setDayPoiEditActive}">
            <div class="header">
                <div class="tit"><i class="iconfont icon-luxian"></i>行程路线</div>
                <div class="opts">
                    <a href="javascript:;" @click="setRouteLine" v-if="!setRouteLineActive" class="actionBtn" :class="{active: setBtnActive}">
                        <i class="iconfont icon-bianji-blue"></i>
                        <span>安排路线</span>
                    </a>
                </div>
                <!-- <a href="javascript:;" class="setRoute" >安排路线</a> -->
            </div>
            
            <div class="content">
                <ul>
                    <router-link :to="{path: '/route/' + routeId + '/intro'}" tag="li" class="summary" v-if="!setRouteLineActive && !setDayScheduleActive">
                        线路总览
                    </router-link>
                </ul>
                
                <draggable element="ul" :list="scheduleInfo" :options="{dragClass: 'dayDragging'}" @end="dragEnd">
                    <!-- <li v-for="(element, index) in list1"  >
                        {{element.name}} {{index}}
                    </li> -->
                    <li v-for="(value, index) in scheduleInfo" @click="toDayDetail(value)" :class="{active: daySel==value.id}">
                        <div class="day" :class="{noCity: value.citys.length==0}">D{{value.indexdate}}</div>
                        <div class="date" v-if="!setRouteLineActive">{{formatDateWeek(startDate, index)}}</div>
                        <div class="cityList">
                            <div class="item" v-for="(item, index) in value.citys">
                                <span class="cityName" :class="{init: !item.scheduleId}">
                                    {{item.nameCn}}
                                    <span class="delCity" @click="delDayCity(item.id)" v-if="setRouteLineActive && item.scheduleId">
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
                </draggable>
                
                <ul>
                    <router-link :to="{path: '/route/' + routeId + '/remarks'}" tag="li" class="summary" v-if="!setRouteLineActive && !setDayScheduleActive">
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

        <!-- <el-dialog title="提示" :visible.sync="dialogChangeInfoTip" width="450px">
            <div class="delTipCon">
                <p>当前选择的天数小于之前设定的天数</p>
                <p class="colorRed">您安排的这天所有的行程都会删除！</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelDayTip = false">取消</el-button>
                <el-button type="primary" @click.native="delRouteDay">确定</el-button>
            </div>
        </el-dialog> -->

        <el-dialog title="提示" :visible.sync="backListConfirmVisible" width="450px">
            <div class="delTipCon">
                <p>您确定要退出编辑吗？</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="backListConfirmVisible = false">取消</el-button>
                <el-button type="primary" @click.native="backListConfirmSubmit">确定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="完善行程信息" :visible.sync="dialogBaseInfoVisible" width="600px">
            <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="formAdd">
                <el-form-item label="名称:" required prop="title">
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

              <el-form-item label="行程时段:" required prop="dateRange">
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
                      <el-autocomplete
                            v-model="form.startCity"
                            class="citySel"
                            prefix-icon="el-icon-search"
                            :fetch-suggestions="queryCitySearch"
                            placeholder=""
                            :trigger-on-focus="false"
                            @select="cityStartSelect"
                        ></el-autocomplete>
                    </el-col>
                    <el-col class="line" :span="4">
                        <span class="label">返回城市:</span>
                    </el-col>
                    <el-col :span="10">
                      <el-autocomplete
                            v-model="form.destination"
                            class="citySel"
                            prefix-icon="el-icon-search"
                            :fetch-suggestions="queryCitySearch"
                            placeholder=""
                            :trigger-on-focus="false"
                            @select="cityEndSelect"
                        ></el-autocomplete>
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
                <el-button :disabled="isDisable" type="primary" @click.native="saveRouteInfo">保 存</el-button>
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

import draggable from 'vuedraggable'

export default {
    components: {
        SetCity,
        draggable
    },
    mixins: [ FormatDateWeek ],
    data() {
        return {
            setBtnActive: false,
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
            backListConfirmVisible: false

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

        Bus.$on('addCityToDayOnce', function(item){
            vm.addCityToDayOnce(item)
        })

        Bus.$on('delDayCity', function(cityId){
            vm.delDayCity(cityId)
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

        backList(){
            this.backListConfirmVisible = true
        },

        backListConfirmSubmit(){
            this.$router.push({path: '/main/route'})
        },

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
                // if(this.form.personList[i].type == 'done'){
                if(this.form.personList[i].name != '' && this.form.personList[i].phone != ''){
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

                    if(this.routeInfo.days < this.form.days){
                        this.addSchedule()
                    }else{
                        this.removeSchedule()
                    }
                }
            })
        },

        addSchedule(){
            var _data = []

            for (var i = 0; i < this.form.days - this.routeInfo.days; i++) {
                _data.push({
                    indexdate: this.routeInfo.days + i + 1
                })
            }

            this.$http({
                method: 'POST',
                url: '/tripnote/schedule/doAddIndexdate/' + this.routeId,
                data:{
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
                    var _scheduleId = res.data.data[res.data.data.length - 1]
                    this.addDayCity(_scheduleId)
                }
            })
        },

        addDayCity(scheduleId){
            if(
                this.form.startCity != '' && 
                (
                    this.routeInfo.dayInfo[0].citys.length == 0 ||
                    (this.routeInfo.dayInfo[0].citys.length > 0 && this.routeInfo.dayInfo[0].citys[0].nameCn != this.form.startCity) 
                )

            ){
                var _url = '/tripnote/schedulecity/doAdd'

                if(this.routeInfo.dayInfo[0].citys.length > 0){
                    _url = '/tripnote/schedulecity/doAddFrist'
                }

                this.$http({
                    method: 'POST',
                    url: _url,
                    data:{
                        cityId: this.form.cityStartId,
                        scheduleId: this.routeInfo.dayInfo[0].id
                    }
                })
                .then((res)=>{
                    if(res.data.code == -1){
                        this.$message({
                            message: res.data.message,
                            type: 'error',
                            duration: 2000
                        });
                    }
                })
            }

            if(this.form.destination != '' && scheduleId){
                this.$http({
                    method: 'POST',
                    url: '/tripnote/schedulecity/doAdd',
                    data:{
                        cityId: this.form.cityEndId,
                        scheduleId: scheduleId
                    }
                })
                .then((res)=>{
                    if(res.data.code == -1){
                        this.$message({
                            message: res.data.message,
                            type: 'error',
                            duration: 2000
                        });
                    }
                })
            }

            // var vm  = this
            // setTimeout(function(){
            //     vm.$router.go({path: '/route/' + vm.routeId})
            // }, 1000)

        },

        removeSchedule(){
            var _diff = this.routeInfo.days - this.form.days
            var _ids = []
            var _len = this.routeInfo.dayInfo.length

            for (var i = 0; i < _diff; i++) {
                _ids.push(this.routeInfo.dayInfo[_len-i-1].id)
            }


            var vm = this
            $.ajax({
                url: '/tripnote/schedule/batch/doDelete',
                data: JSON.stringify({
                    ids: _ids
                }),
                type: 'post',
                contentType: 'application/json',
                success: function(res){
                    if(res.code == -1){
                        vm.$message({
                            message: res.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        var _scheduleLast = vm.routeInfo.dayInfo[vm.routeInfo.dayInfo.length - 1 - _diff]

                        if(_scheduleLast.citys.length > 0 && _scheduleLast.citys[_scheduleLast.citys.length - 1].nameName != vm.form.destination){
                            vm.addDayCity(_scheduleLast.id)
                        }else{
                            vm.addDayCity()
                        }
                    }
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
                type: 'edit',

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
                this.$router.push({path: '/route/' + this.routeId + '/day/' + item.id + '/schedule'})
            }else{
                this.$router.push({path: '/route/' + this.routeId + '/day/' + item.id})
            }
            
        },

        addCityToDayOnce(item){
            var _nextScheduleId = ''
            var _thisDayIndex = ''
            var _nextCitys = []

            for (var i = 0; i < this.scheduleInfo.length; i++) {
                if(this.scheduleInfo[i].id == this.dayId){
                    _thisDayIndex = this.scheduleInfo[i].indexdate
                }
            }
            if(_thisDayIndex + 1 <= this.scheduleInfo.length){
                // if(this.scheduleInfo[_thisDayIndex].citys.length > 0){
                    _nextScheduleId = this.scheduleInfo[_thisDayIndex].id
                    _nextCitys = this.scheduleInfo[_thisDayIndex].citys
                // }
            }


            this.$http({
                method: 'post',
                url: '/tripnote/schedulecity/doAdd',
                data:{
                    cityId: item.id,
                    scheduleId: this.dayId
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
                    if(_nextScheduleId != ''){
                        this.$http({
                            method: 'post',
                            url: '/tripnote/schedulecity/doAddFrist',
                            data:{
                                cityId: item.id,
                                scheduleId: _nextScheduleId
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
                                this.getRouteInfo()
                            }
                        })

                        if(_nextCitys.length == 1){
                            this.$http({
                                method: 'post',
                                url: '/tripnote/schedule/doDeail/' + _nextScheduleId,
                            })
                            .then((res)=>{
                                if(res.data.code == -1){
                                    this.$message({
                                        message: res.data.message,
                                        type: 'error',
                                        duration: 2000
                                    })
                                }else{
                                    if(res.data.data.rents.length == 0 && res.data.data.scheduleHotels.length == 0 && res.data.data.scheduletrips.length == 0){
                                        
                                        this.$http({
                                            method: 'post',
                                            url: '/tripnote/schedulecity/doDelete/' + _nextCitys[0].id,
                                        })
                                        .then((res)=>{
                                            if(res.data.code == -1){
                                                this.$message({
                                                    message: res.data.message,
                                                    type: 'error',
                                                    duration: 2000
                                                });
                                            }
                                        })
                                    }
                                }
                            })
                        }



                        
                    }else{
                        this.getRouteInfo()
                    }
                }
            })
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

        cityStartSelect(item){
            this.form.cityStartId = item.id
            this.form.startCity = item.nameCn
        },

        cityEndSelect(item){
            this.form.cityEndId = item.id
            this.form.destination = item.nameCn
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

        dragEnd(data){
            var _newIndex = data.newIndex
            var _oldIndex = data.oldIndex

            console.log('new', _newIndex)
            console.log('old', _oldIndex)

            var _changeSort = []

            for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
                if(this.routeInfo.dayInfo[i].indexdate != i+1){
                    _changeSort.push({
                        scheduleid: this.routeInfo.dayInfo[i].id,
                        indexdate: i+1
                    })
                }
            }

            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doUpdateIndexDate',
                data: {
                    indexdates: JSON.stringify({"indexdates":_changeSort}),
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
                    this.getRouteInfo()
                }
            })
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
                    var _days = res.data.data.days

                    var _routeInfo = $.extend({}, this.routeInfo, {
                        intro: res.data.data.introduction,
                        remarks: res.data.data.remarks,
                        dayInfo: this.scheduleInfo,
                        startDate: this.startDate,
                        days: _days
                    })

                    Object.assign(this.$data.form, res.data.data)

                    this.form.dateRange = [new Date(this.form.startDate), new Date(this.form.endDate)]

                    this.$store.dispatch('setRouteInfo', _routeInfo)

                    Bus.$emit('refreshSchedule', true)

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

        Bus.$off('addCityToDayOnce')
        
        Bus.$off('delDayCity')


        this.$store.dispatch('setRouteLineActive', false)
        this.$store.dispatch('setDayScheduleActive', false)
        this.$store.dispatch('setDayPoiEditActive', false)
    }
}
</script>
<style lang="less" scope>
.routeCreate{
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
            li{
                position: relative;
                z-index: 103;
                float: left;
                width: 90px;
                height: 60px;
                border-left: 1px solid #334959;
                cursor: pointer;
                &:hover{
                    background: #101c24;
                    .list{
                        display: block;
                    }
                    // p{
                    //     color: #23a16d;
                    // }
                    i{
                        &.icon0{
                            background-position: 0px -90px;
                        }
                        &.icon1{
                            background-position: 0px 0;
                        }
                        &.icon2{
                            background-position: 0px -30px;
                        }
                        &.icon3{
                            background-position: 0px -60px;
                        }
                    }
                }
                .list{
                    display: none;
                    position: absolute;
                    top: 60px;
                    right: 0;
                    width: 112px;
                    padding: 12px 0;
                    background: #fff;
                    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);
                    .item{
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
                i{
                    display: block;
                    margin: 5px auto 0;
                    width: 30px;
                    height: 30px;
                    background: url(../../assets/images/icon_route_opts.png);
                    &.icon0{
                        background-position: -30px -90px;
                    }
                    &.icon1{
                        background-position: -30px 0;
                    }
                    &.icon2{
                        background-position: -30px -30px;
                    }
                    &.icon3{
                        background-position: -30px -60px;
                    }
                }

                p{
                    color: #a9d1e4;
                    text-align: center;
                }
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
        &.setDayPoiEditActive{
            left: -280px;
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
                &.dayDragging{
                    background: red;
                }
                &.sortable-ghost{
                    background: #23a16d;
                }
                // .day{
                //     float: left;
                //     line-height: 36px;
                //     font-size: 36px;

                //     &.noCity{
                //         color: #C8D0D5;
                //     }
                // }
                .day{
                    float: left;
                    width: 36px;
                    height: 36px;
                    line-height: 36px;
                    font-size: 20px;
                    text-align: center;
                    background: #C3D1DD;
                    border-radius: 18px;
                    color: #fff;
                    
                    &.noCity{
                        // background: #C3D1DD;
                        // color: #C8D0D5;
                        // opacity: 0.6;
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
                                right: -7px;
                                top: -14px;
                                i{
                                    font-size: 16px;
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
                    min-height: 50px;
                    height: 50px;
                    line-height: 26px;
                    font-size: 16px;
                }
                &.active{
                    background: #F7F8F9;
                    .day{
                        background: #23a16d;
                        color: #fff;
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
.routePic{
    float: left;
    width: 160px;
    height: 90px;
    background-size: cover;
    background-position: center;
    border: 1px solid #e2e2e2;
}
.uploadRoutePic{
    margin-left: 175px;
}
</style>