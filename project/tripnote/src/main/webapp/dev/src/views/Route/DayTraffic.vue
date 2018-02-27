<template>
    <div>
        <div class="columnBox trafficBox" :class="{setDayPoiEditActive: setDayPoiEditActive}">
            <div class="header">
                <div class="tit">{{startPointInfo.nameCn}} <i class="iconfont icon-jiantou02"></i> {{endPointInfo.nameCn}}</div>
                <div class="opts">
                    <a href="javascript:;" @click="backDayDetail" class="closeBtn">
                        <i class="iconfont icon-fanhuidingbu"></i>
                    </a>
                </div>
            </div>
            
            <div class="content typeSel" v-if="editStatus && routeSummary">
                <div class="tit">选择交通方式</div>

                <div class="typeList">
                    <ul>
                        <li @click="changeTrafficType(1)" :class="{active:trafficType==1}">
                            <i class="iconfont icon-gongjiaoche"></i>
                            <span>公共交通</span>
                        </li>
                        <li @click="changeTrafficType(2)" :class="{active:trafficType==2}">
                            <i class="iconfont icon-qiche"></i>
                            <span>汽车</span>
                        </li>
                        <li @click="changeTrafficType(3)" :class="{active:trafficType==3}">
                            <i class="iconfont icon-buhang"></i>
                            <span>步行</span>
                        </li>
                        <li @click="changeTrafficType('other')" :class="{active:trafficType=='other'}">
                            <i class="iconfont icon-qita"></i>
                            <span>其它</span>
                        </li>
                    </ul>
                </div>

                <div class="summaryInfo" @click="showRouteDetail" v-if="trafficType != 'other'">
                    <h3>{{trafficTypeName}}</h3>
                    <p>
                        <span class="distance">{{distanceText}}</span>
                        <span class="duration">{{durationText}}</span>
                    </p>
                    <a href="javascript:;" class="addToSchedule" v-if="addToScheduleAble" @click.stop="addToSchedule">
                        <i class="el-icon-plus"></i>
                    </a>
                </div>

                <div class="addOtherType" v-if="trafficType == 'other'">
                    <el-button size="small" round icon="el-icon-plus" @click="dialogAddOtherVisible=true">添加交通信息</el-button>
                </div>
            </div>

            <div class="content trafficDetail" v-if="editStatus && !routeSummary">
                <div class="tit">
                    <a href="javascript:;" class="back" @click="routeSummary=true">
                        <i class="iconfont icon-back01"></i>
                    </a>
                    <div class="summary">
                        <span v-html="trafficsTypeForamt(trafficType)"></span>
                        {{distanceText}} {{durationText}} 
                    </div>
                    <a href="javascript:;" class="add" v-if="addToScheduleAble" @click="addToSchedule">
                        <i class="el-icon-plus"></i>
                    </a>
                </div>

                <div class="detail">
                    <div class="startPoint">
                        <i class="iconfont icon-biaoqianlan_icon_yuanquan_moren"></i>
                        {{startPointInfo.nameCn}}
                    </div>
                    
                    <ul>
                        <li v-for="(item, index) in routeSteps">
                            <div v-if="item.travel_mode == 'WALKING'">
                                <i class="iconfont icon-buhang"></i>
                                <div class="title" v-html="item.instructions"></div>
                                <div class="des">{{item.distance.text}} {{item.duration.text}}</div>
                            </div>

                            <div v-if="item.travel_mode == 'TRANSIT'">
                                <div class="start">
                                    <i class="iconfont icon-gongjiaoche"></i>
                                    <div class="title">{{item.transit.departure_stop.name}} 上车</div>
                                    <div class="des">
                                        <p>{{item.transit.line.short_name}}</p>
                                        <p>{{item.distance.text}} {{item.duration.text}}</p>
                                    </div>
                                </div>
                                <div class="end">
                                    <i class="iconfont icon-gongjiaoche"></i>
                                    <div class="title">{{item.transit.arrival_stop.name}} 下车</div>
                                </div>
                            </div>

                            <div v-if="item.travel_mode == 'DRIVING'">
                                <i class="iconfont icon-qiche"></i>
                                <div class="title" v-html="item.instructions"></div>
                            </div>
                        </li>
                    </ul>

                    <div class="endPoint">
                        <i class="iconfont icon-biaoqianlan_icon_yuanquan_moren"></i>
                        {{endPointInfo.nameCn}}
                    </div>
                </div>
            </div>

            <div class="content trafficDetail" v-if="!editStatus">
                <h3>交通信息</h3>
                <div class="summaryInfo">
                    <div class="icon" v-html="trafficsTypeForamt(trafficType)"></div>
                    <div class="info">{{trafficTypeName}}</div>
                    <p v-if="trafficType<4">{{distanceText}} {{durationText}}</p>
                    <p v-if="trafficType>=4">出发：{{startTime.substring(0,5)}} &nbsp;&nbsp;&nbsp;&nbsp;到达：{{arrivalTime.substring(0,5)}}</p>
                </div>
                
                <div v-if="trafficType<4">
                    <h3>交通指引</h3>

                    <div class="detail">
                        <div class="startPoint">
                            <i class="iconfont icon-biaoqianlan_icon_yuanquan_moren"></i>
                            {{startPointInfo.nameCn}}
                        </div>
                        
                        <ul>
                            <li v-for="(item, index) in routeSteps">
                                <div v-if="item.travel_mode == 'WALKING'">
                                    <i class="iconfont icon-buhang"></i>
                                    <div class="title" v-html="item.instructions"></div>
                                    <div class="des">{{item.distance.text}} {{item.duration.text}}</div>
                                </div>

                                <div v-if="item.travel_mode == 'TRANSIT'">
                                    <div class="start">
                                        <i class="iconfont icon-gongjiaoche"></i>
                                        <div class="title">{{item.transit.departure_stop.name}} 上车</div>
                                        <div class="des">
                                            <p>{{item.transit.line.short_name}}</p>
                                            <p>{{item.distance.text}} {{item.duration.text}}</p>
                                        </div>
                                    </div>
                                    <div class="end">
                                        <i class="iconfont icon-gongjiaoche"></i>
                                        <div class="title">{{item.transit.arrival_stop.name}} 下车</div>
                                    </div>
                                </div>

                                <div v-if="item.travel_mode == 'DRIVING'">
                                    <i class="iconfont icon-qiche"></i>
                                    <div class="title" v-html="item.instructions"></div>
                                </div>
                            </li>
                        </ul>

                        <div class="endPoint">
                            <i class="iconfont icon-biaoqianlan_icon_yuanquan_moren"></i>
                            {{endPointInfo.nameCn}}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="columnBox trafficMap" v-if="setDayPoiEditActive">
            <div class="header">
                <div class="opts">
                    <a href="javascript:;" class="closeBox" @click="closeMap">
                        <i class="iconfont icon-guanbi"></i>
                    </a>
                </div>
            </div>

            <div class="googleMap" id="TrafficMap"></div>
        </div>

        <el-dialog title="添加交通信息" :visible.sync="dialogAddOtherVisible" width="560px">
            <el-form ref="form" label-width="95px">
                <el-form-item label="选择交通方式:">
                    <el-select v-model="trafficType" placeholder="请选择" class="templateType">
                        <el-option
                            v-for="item in otherTypeOpts"
                            :key="item.value"
                            :label="item.name"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="班次号:">
                    <el-input v-model="shift" style="width: 80px;"></el-input>
                    <span class="label" style="padding-left: 10px;">出发：</span>
                    <el-time-picker
                        v-model="startTime"
                        class="trafficTimer"
                        format="HH:mm"
                        value-format="HH:mm"
                        :picker-options="{
                            selectableRange: '00:00:00 - 23:59:59'
                        }"
                        placeholder="出发时间">
                    </el-time-picker>
                    <span class="label" style="padding-left: 10px;">到达：</span>
                    <el-time-picker
                        v-model="arrivalTime"
                        class="trafficTimer"
                        format="HH:mm"
                        value-format="HH:mm"
                        :picker-options="{
                            selectableRange: '00:00:00 - 23:59:59'
                        }"
                        placeholder="到达时间">
                    </el-time-picker>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click.native="dialogAddOtherVisible = false">取 消</el-button>
                &nbsp;
                <el-button type="primary" @click.native="addOtherTypeToSchedule">保 存</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            map: '',

            startPoint: '',
            endPoint: '',
            startPointInfo: {},
            endPointInfo: {},
            trafficType: 1,
            // trafficTypeName: '公交方案',

            distance: '',
            distanceText: '',
            duration: '',
            durationText: '',

            routeSteps: [],

            routeSummary: true,

            editStatus: false,


            chainMarker: [],
            chainPloyline: '',
            cityMarker: [],

            dialogAddOtherVisible: false,

            otherType: '',
            shift: '',
            startTime: '',
            arrivalTime: '',

            otherTypeOpts: [
                {
                    value: 'other',
                    name: '请选择'
                },
                {
                    value: 4,
                    name: '飞机'
                },
                {
                    value: 5,
                    name: '火车'
                },
                {
                    value: 6,
                    name: '轮渡'
                },
                {
                    value: 7,
                    name: '巴士'
                },
                {
                    value: 8,
                    name: '地铁'
                }
            ],

            markerArray: [],
            directionsDisplay: '',

            addToScheduleAble: true

        }
    },
    
    watch: {
        dayInfo(val){
            if(val){
                for (var i = 0; i < val.scheduletrips.length; i++) {
                    if(val.scheduletrips[i].id == this.startId){
                        this.startPointInfo = val.scheduletrips[i]
                    }
                    if(val.scheduletrips[i].id == this.endId){
                        this.endPointInfo = val.scheduletrips[i]
                    }
                }
                if(this.trafficId){
                    for (var i = 0; i < val.traffics.length; i++) {
                        if(val.traffics[i].id == this.trafficId){
                            this.trafficType = val.traffics[i].trafficType
                            this.startTime = val.traffics[i].startTime
                            this.arrivalTime = val.traffics[i].arrivalTime
                            this.editStatus = false
                        }
                    }
                }

                if(this.setDayScheduleActive){
                    this.initMap()
                }
                this.getRouteInfo()
            }
        },

        // trafficId(val){
        //     var _allSchedule = this.dayInfo.scheduletrips.concat(this.dayInfo.scheduleHotels)
        //     for (var i = 0; i < _allSchedule.length; i++) {
        //         if(_allSchedule[i].id == this.startId){
        //             this.startPointInfo = _allSchedule[i]
        //         }
        //         if(_allSchedule[i].id == this.endId){
        //             this.endPointInfo = _allSchedule[i]
        //         }
        //     }

        //     if(this.trafficId){
        //         for (var i = 0; i < this.dayInfo.traffics.length; i++) {
        //             if(this.dayInfo.traffics[i].id == val){
        //                 this.trafficType = this.dayInfo.traffics[i].trafficType
        //                 this.startTime = this.dayInfo.traffics[i].startTime
        //                 this.arrivalTime = this.dayInfo.traffics[i].arrivalTime

        //                 this.editStatus = false
        //             }
        //         }
        //     }

        //     if(val){
        //         this.editStatus = false
        //     }else{
        //         this.editStatus = true
        //     }

        //     if(this.setDayScheduleActive){
        //         this.initMap()
        //     }

        //     this.getRouteInfo()
        // },

        startId(){
            var val = this.trafficId
            var _allSchedule = this.dayInfo.scheduletrips.concat(this.dayInfo.scheduleHotels)
            for (var i = 0; i < _allSchedule.length; i++) {
                if(_allSchedule[i].id == this.startId){
                    this.startPointInfo = _allSchedule[i]
                }
                if(_allSchedule[i].id == this.endId){
                    this.endPointInfo = _allSchedule[i]
                }
            }

            if(this.trafficId){
                for (var i = 0; i < this.dayInfo.traffics.length; i++) {
                    if(this.dayInfo.traffics[i].id == val){
                        this.trafficType = this.dayInfo.traffics[i].trafficType
                        this.startTime = this.dayInfo.traffics[i].startTime
                        this.arrivalTime = this.dayInfo.traffics[i].arrivalTime

                        this.editStatus = false
                    }
                }
            }

            if(val){
                this.editStatus = false
            }else{
                this.editStatus = true
            }

            if(this.setDayScheduleActive){
                this.initMap()
            }

            this.getRouteInfo()
        }
    },
    created(){
        if(this.dayInfo['scheduletrips']){
            var _allSchedule = this.dayInfo.scheduletrips.concat(this.dayInfo.scheduleHotels)
            for (var i = 0; i < _allSchedule.length; i++) {
                if(_allSchedule[i].id == this.startId){
                    this.startPointInfo = _allSchedule[i]
                }
                if(_allSchedule[i].id == this.endId){
                    this.endPointInfo = _allSchedule[i]
                }
            }

            if(this.trafficId){
                for (var i = 0; i < this.dayInfo.traffics.length; i++) {
                    if(this.dayInfo.traffics[i].id == this.trafficId){
                        this.trafficType = this.dayInfo.traffics[i].trafficType
                        this.startTime = this.dayInfo.traffics[i].startTime
                        this.arrivalTime = this.dayInfo.traffics[i].arrivalTime

                        this.editStatus = false
                    }
                }
            }
        }

        if(this.trafficId){
            this.editStatus = false
        }else{
            this.editStatus = true
        }
    },

    mounted(){
        if(this.setDayScheduleActive){
            this.initMap()
        }
        this.getRouteInfo()
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            dayInfo: 'dayInfo',
            setRouteLineActive: 'setRouteLineActive',
            setDayPoiEditActive: 'setDayPoiEditActive',
            setDayScheduleActive: 'setDayScheduleActive'
        }),

        dayId(){
            return this.$route.params.dayId
        },
        routeId(){
            return this.$route.params.routeId
        },

        startId(){
            return this.$route.params.poiStartId.split('_')[0]
        },

        startType(){
            return this.$route.params.poiStartId.split('_')[1]
        },

        endId(){
            return this.$route.params.poiEndId.split('_')[0]
        },

        endType(){
            return this.$route.params.poiEndId.split('_')[1]
        },

        trafficId(){
            return this.$route.params.trafficId
        },

        dayId(){
            return this.$route.params.dayId
        },

        trafficTypeName(){
            if(this.trafficType == 1){
                return '公交方案'
            }else if(this.trafficType == 2){
                return  '驾车方案'
            }else if(this.trafficType == 3){
                return  '步行方案'
            }else if(this.trafficType == 4){
                return  '飞机'
            }else if(this.trafficType == 5){
                return  '火车'
            }else if(this.trafficType == 6){
                return  '轮渡'
            }else if(this.trafficType == 7){
                return  '巴士'
            }else if(this.trafficType == 8){
                return  '地铁'
            }
        }
    },
    methods: {
        showRouteDetail(){
            if(this.addToScheduleAble){
                this.routeSummary = false
            }
        },
        initMap(){
            var _pointList = this.dayInfo.scheduletrips

            this.map = new google.maps.Map(document.getElementById('TrafficMap'), {
                center: {lat: _pointList[0].lat, lng: _pointList[0].lon},
                zoom: 12
            })

            this.chainPloyline && this.chainPloyline.setMap(null);

            var flightPlanCoordinates = []

            for (var i = 0; i < _pointList.length; i++) {
                flightPlanCoordinates.push({
                    lat: _pointList[i].lat,
                    lng: _pointList[i].lon
                })
            }
            this.chainPloyline = new google.maps.Polyline({
                path: flightPlanCoordinates,
                geodesic: true,
                strokeColor: '#23a16d',
                strokeOpacity: 1.0,
                strokeWeight: 1.5
            });

            this.chainPloyline.setMap(this.map);

            this.mapTrafficMarker(_pointList)
        },

        mapTrafficMarker(data){
            var vm = this
            for (var i = 0; i < this.cityMarker.length; i++) {
                this.cityMarker[i].setMap(null);
            }
            
            var _txtArr = []

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement('DIV');
                div.innerHTML = '<div class="mapTrafficMarker">' + (i+1) + '</div>';

                var marker = new RichMarker({
                    map: this.map,
                    position: new google.maps.LatLng(data[i].lat, data[i].lon),
                    draggable: false,
                    flat: true,
                    anchor: RichMarkerPosition.MIDDLE,
                    content: div
                });

                var _infobox

                _txtArr.push(data[i].nameCn)

                google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                    return function() {
                        _infobox = new InfoBubble({
                            borderWidth: 0,
                            borderRadius: 0,
                            backgroundColor: '#22A98E',
                            disableAutoPan: true,
                            hideCloseButton: true,
                            arrowSize: 8,
                            minWidth: 180,
                            maxWidth: 180,
                            minHeight: 38,
                            // maxHeight: 38,
                            padding: 0,
                            content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div></div>',
                        })

                        _infobox.open(this.map, marker);
                    }
                })(marker, i));

                google.maps.event.addListener(marker, 'mouseout', function() {
                    _infobox.close()
                })
            }

        },

        closeMap(){
            this.$store.dispatch('setDayPoiEditActive', false)
            this.$store.dispatch('setDayScheduleActive', false)

            this.$router.push({path: '/route/' + this.routeId + '/day/' + this.dayId})
        },

        trafficsTypeForamt(type){
            if(type == 1){
                return '<i class="iconfont icon-gongjiaoche"></i>'
            }else if(type == 2){
                return '<i class="iconfont icon-qiche"></i>'
            }else if(type == 3){
                return '<i class="iconfont icon-buhang"></i>'
            }else if(type == 4){
                return '<i class="iconfont icon-feiji-big"></i>'
            }else if(type == 5){
                return '<i class="iconfont icon-huoche"></i>'
            }else if(type == 6){
                return '<i class="iconfont icon-lunchuang"></i>'
            }else if(type == 7){
                return '<i class="iconfont icon-gongjiaoche"></i>'
            }else if(type == 8){
                return '<i class="iconfont icon-ditie"></i>'
            }
        },

        trafficsTypeToNameForamt(type){
            if(type == 1){
                return 'TRANSIT'
            }else if(type == 2){
                return 'DRIVING'
            }else if(type == 3){
                return 'WALKING'
            }
        },

        backDayDetail(){
            this.$store.dispatch('setDayPoiEditActive', false)

            if(this.setDayScheduleActive){
                this.$router.push({path: '/route/' + this.routeId + '/day/' + this.dayId + '/schedule'})
            }else{
                this.$router.push({path: '/route/' + this.routeId + '/day/' + this.dayId})
            }
        },

        trafficTypeFormat(type){
            return ['TRANSIT', 'DRIVING', 'WALKING'][type-1]
        },

        changeTrafficType(type){
            this.trafficType = type

            if(type != 'other'){
                this.getRouteInfo()
            }else{
                this.clearRouteLine()
            }
        },

        clearRouteLine(){
            if(this.setDayScheduleActive){
                if(this.directionsDisplay != ''){
                    this.directionsDisplay.setMap(null);
                    this.directionsDisplay.setPanel(null);
                }
                for (var i = 0; i < this.markerArray.length; i++) {
                    this.markerArray[i].setMap(null)
                }
            }
        },

        getRouteInfo(){
            var vm = this
            if(this.trafficType >= 4){
                return
            }

            var directionsService = new google.maps.DirectionsService;
            
            if(vm.setDayScheduleActive){
                for (var i = 0; i < this.markerArray.length; i++) {
                    this.markerArray[i].setMap(null)
                }
                this.markerArray = [];

                var stepDisplay = new google.maps.InfoWindow;

                var markerArray = this.markerArray
                var map = this.map

                var pOptions = {
                    map: map,
                    strokeColor: '#FA574B',
                    strokeOpacity: 1,
                    strokeWeight: 2
                };

                //directionsRenderer options
                var mDirectionsRendererOptions = {
                    map: map,
                    suppressMarkers: true,
                    suppressInfoWindows: true,
                    polylineOptions: pOptions
                };

                if(this.directionsDisplay != ''){
                    this.directionsDisplay.setMap(null);
                    this.directionsDisplay.setPanel(null);
                }
                this.directionsDisplay = new google.maps.DirectionsRenderer(mDirectionsRendererOptions);
            }

            function showSteps(directionResult, markerArray, stepDisplay, map) {
                // For each step, place a marker, and add the text to the marker's infowindow.
                // Also attach the marker to an array so we can keep track of it and remove it
                // when calculating new routes.
                var myRoute = directionResult.routes[0].legs[0];

                for (var i = 0; i < myRoute.steps.length; i++) {
                    var marker = new google.maps.Marker;
                    markerArray.push(marker)

                    marker.setMap(map);
                    marker.setPosition(myRoute.steps[i].start_location);

                    attachInstructionText(stepDisplay, marker, myRoute.steps[i].instructions, map);
                }
            }

            function attachInstructionText(stepDisplay, marker, text, map) {
                google.maps.event.addListener(marker, 'click', function() {
                    // Open an info window when the marker is clicked on, containing the text
                    // of the step.
                    stepDisplay.setContent(text);
                    stepDisplay.open(map, marker);
                });
            }

            console.log('startPointInfo', this.startPointInfo)
            console.log('endPointInfo', this.endPointInfo)

            directionsService.route({
                origin: new google.maps.LatLng(this.startPointInfo.lat, this.startPointInfo.lon),
                destination: new google.maps.LatLng(this.endPointInfo.lat, this.endPointInfo.lon),
                travelMode: vm.trafficTypeFormat(this.trafficType)
            }, function(response, status) {
                if(response.routes[0]){
                    vm.distance = response.routes[0].legs[0].distance.value
                    vm.distanceText = response.routes[0].legs[0].distance.text

                    vm.duration = response.routes[0].legs[0].duration.value
                    vm.durationText = response.routes[0].legs[0].duration.text

                    vm.routeSteps = response.routes[0].legs[0].steps

                    if(vm.setDayScheduleActive){
                        vm.directionsDisplay.setDirections(response);
                        // showSteps(response, markerArray, stepDisplay, map);
                    }
                    vm.addToScheduleAble = true
                }else{
                    vm.distance = 0
                    vm.distanceText = '没有找到交通路线'

                    vm.duration = 0
                    vm.durationText = ''

                    vm.addToScheduleAble = false
                }
                
            })
        },

        addToSchedule(){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletraffic/doAdd',
                data: {
                    scheduleId: this.dayId,
                    startScheduleTrip: this.startId,
                    startScheduleType: this.startType,
                    endScheduleTrip: this.endId,
                    endScheduleType: this.endType,
                    distance: this.distance,
                    spendTime: this.duration,
                    trafficType: this.trafficType
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
                    this.editStatus = false
                    Bus.$emit('refreshSchedule', true)
                }
            })
        },

        addOtherTypeToSchedule(){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletraffic/doAdd',
                data: {
                    scheduleId: this.dayId,
                    startScheduleTrip: this.startId,
                    endScheduleTrip: this.endId,
                    trafficType: this.trafficType,
                    shift: this.shift,
                    startTime: this.startTime,
                    arrivalTime: this.arrivalTime,
                    distance: 0,
                    spendTime: 0,
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
                    this.editStatus = false
                    this.dialogAddOtherVisible=false
                    Bus.$emit('refreshSchedule', true)
                }
            })
        }


    },
}
</script>
<style lang="less" scope>
.trafficBox{
    left: 620px;
    top: 80px;
    bottom: 20px;
    width: 365px;
    transition: all .2s ease;
    &.setDayPoiEditActive{
        left: 340px;
    }
    .header{
        padding-right: 0;
        .tit{
            font-size: 14px;
        }
        .closeBtn{
            display: block;
            width: 54px;
            height: 54px;
            line-height: 54px;
            border-top: 1px solid #e2e2e2;
            transform: rotate(-90deg);
            text-align: center;
            color: #999;
            &:hover{
                background: #f1f1f1;
            }
            i{
                font-size: 26px;
            }
        }
    }
    .typeSel{
        .tit{
            padding: 10px 15px;
            line-height: 30px;
            font-size: 14px;
            color: #999;
        }
        .typeList{
            padding: 0 15px;
            overflow: hidden;
            border-bottom: 1px solid #e2e2e2;
            li{
                float: left;
                width: 25%;
                padding: 10px 0 20px;
                text-align: center;
                color: #A7AFB4;
                cursor: pointer;
                &.active,
                &:hover{
                    color: #23a16d;
                }
                i{
                    font-size: 24px;
                }
                span{
                    line-height: 24px;
                    font-size: 14px;
                    display: block;
                }
            }
        }

        .summaryInfo{
            position: relative;
            margin: 20px 15px;
            padding: 15px;
            border: 1px solid #e2e2e2;
            cursor: pointer;
            &:hover{
                background: #F7F8F9;
            }
            h3{
                font-size: 16px;
            }
            p{
                margin-top: 5px;
                font-size: 14px;
                span{
                    margin-right: 5px;
                }
            }
            .addToSchedule{
                position: absolute;
                top: 50%;
                margin-top: -15px;
                right: 15px;
                height: 30px;
                width: 30px;
                border: 2px solid #23a16d;
                color: #23a16d;
                line-height: 30px;
                border-radius: 100%;
                text-align: center;
                &:hover{
                    background: #23a16d;;
                    color: #fff;
                }
                i{
                    font-size: 18px;
                    font-weight: bold;
                }
            }
        }
    }

    .trafficDetail{
        h3{
            padding: 10px 15px 0;
            line-height: 32px;
            color: #999;
            font-weight: normal;
        }
        .summaryInfo{
            padding: 10px 15px 15px;
            overflow: hidden;
            border-bottom: 1px solid #EAEDF1;
            .icon{
                float: left;
                i{
                    font-size: 26px;
                    color: #23a16d;
                }
            }
            .info{
                margin-left: 35px;
                font-size: 14px;
            }
            p{
                margin-left: 35px;
                font-size: 13px;
                color: #999;
            }
        }
        .tit{
            height: 51px;
            padding: 0 15px;
            line-height: 50px;
            border-bottom: 1px solid #EAEDF1;
            font-size: 14px;
            .back{
                float: left;
                i{
                    font-size: 22px;
                }
            }
            .summary{
                float: left;
                margin-left: 10px;
                i{
                    color: #23a16d; 
                }
            }
            .add{
                float: right;
                height: 30px;
                width: 30px;
                margin-top: 10px;
                border: 2px solid #23a16d;
                color: #23a16d;
                line-height: 30px;
                border-radius: 100%;
                text-align: center;
                &:hover{
                    background: #23a16d;;
                    color: #fff;
                }
                i{
                    font-size: 18px;
                    font-weight: bold;
                }
            }
        }
        .detail{
            position: relative;
            padding: 10px 15px 15px;
            font-size: 14px;
            &:before{
                content: '';
                position: absolute;
                left: 27px;
                top: 31px;
                bottom: 36px;
                width: 0;
                border-right: 1px dashed #A7AFB4;
            }
            .startPoint{
                position: relative;
                line-height: 30px;
                i{
                    vertical-align: middle;
                    color: #23a16d;
                    font-size: 24px;
                }
            }
            .endPoint{
                position: relative;
                line-height: 30px;
                i{
                    vertical-align: middle;
                    font-size: 24px;
                    color: #FB5252;
                }
            }
            li{
                position: relative;
                padding: 10px 0;
                i{
                    float: left;
                    margin-left: 4px;
                    background: #fff;
                    font-size: 18px;
                }
                .title{
                    margin-left: 30px;
                }
                .des{
                    margin-left: 30px;
                    padding: 8px 0;
                    color: #999;
                    font-size: 13px;
                }
            }
        }
    }
}

.trafficMap{
    z-index: 1;
    left: 725px;
    right: 20px;
    top: 80px;
    bottom: 20px;
    .header{
        .opts{
            height: 54px;
            margin-right: -15px;
            border-left: 1px solid #EAEDF1;
            &:hover{
                background: #f1f1f1;
            }
            .closeBox{
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
    
    .googleMap{
        position: absolute;
        top: 55px;
        left: 0;
        right: 0;
        bottom: 0;
    }
}
.mapTrafficMarker{
    z-index: 100;
    width: 28px;
    height: 41px;
    margin-top: -20px;
    background: url(../../assets/images/poi_marker.png);
    text-align: center;
    line-height: 24px;
    font-size: 14px;
    color: #000;
    text-align: center;
}
.trafficTimer{
    width: 100px !important;
    .el-input__inner{
        padding-right: 0 !important;
    }
}
.addOtherType{
    margin: 100px auto 0;
    text-align: center;
    .el-button{
        border-color: #23a16d;
        color: #23a16d;
    }
}
</style>