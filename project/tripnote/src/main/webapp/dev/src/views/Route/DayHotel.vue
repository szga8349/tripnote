<template>
    <div>
        <div class="columnBox trafficBox" :class="{setDayPoiEditActive: setDayPoiEditActive}">
            <div class="header">
                <div class="tit">酒店详情</div>
                <div class="opts">
                    <a href="javascript:;" @click="editPoi" class="actionBtn" v-if="type<5">
                        <i class="iconfont icon-bianji-blue"></i>
                        <span>编辑</span>
                    </a>
                    <a href="javascript:;" @click="backDayDetail" class="closeBtn">
                        <i class="iconfont icon-fanhuidingbu"></i>
                    </a>
                </div>
            </div>
            
            <div class="content demoInfo">
                <div class="pic" :style="{backgroundImage: `url(${formatImg(imageurl)})`}"></div>
                <div class="title">
                    <h2>{{nameCn}}</h2>
                </div>
                <div class="hotelCheckTime">
                    <label>入住时间：</label>{{checkInTime}}
                    <label>退房时间：</label>{{checkOuTime}}
                </div>

                <div class="des">
                    {{addressInstrations}}
                </div>
                <div class="detail">
                    <ul>
                        <li>
                            <label>电话</label>
                            <div class="txt">{{phone}}</div>
                        </li>
                        <li>
                            <label>网址</label>
                            <div class="txt">{{url}}</div>
                        </li>
                        <li>
                            <label>开放时间</label>
                            <div class="txt">{{openTime}}</div>
                        </li>
                        <li>
                            <label>消费</label>
                            <div class="txt">{{consumDescription}}</div>
                        </li>
                        <li>
                            <label>用时参考</label>
                            <div class="txt">{{timeReference}}</div>
                        </li>
                    </ul>
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

            <div class="googleMap" id="map"></div>
        </div>

        <div class="editPoi" v-if="editStatus">
            <div class="poiDetailBg"></div>
            <div class="poiDetail">
                <div class="header" v-if="editStatus">
                    <div class="tit">编辑酒店__{{nameCn}}</div>
                    <a href="javascript:;" class="closeBtn" @click="editStatus=false">
                        <i class="iconfont icon-guanbi"></i>
                    </a>
                </div>

                <div class="con editPoi">
                    <el-form ref="form" :model="form" label-width="70px" class="formAdd">
                        <el-form-item label="中文名称:">
                            <el-input v-model="form.nameCn"></el-input>
                        </el-form-item>
                        <el-form-item label="英文名称:">
                            <el-input v-model="form.nameEn"></el-input>
                        </el-form-item>
                        <el-form-item label="POI类型:">
                            <el-select v-model="form.type" placeholder="请选择" class="templateType">
                                <el-option
                                    v-for="item in poiTypeOpts"
                                    :key="item.value"
                                    :label="item.name"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="参考价:">
                            <el-input v-model="form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="电话:">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item label="网址:">
                            <el-input v-model="form.url"></el-input>
                        </el-form-item>
                        <el-form-item label="开放时间:">
                            <el-input v-model="form.openTime"></el-input>
                        </el-form-item>
                        <el-form-item label="消费:">
                            <el-input type="textarea" :rows="3" v-model="form.consumDescription"></el-input>
                        </el-form-item>
                        <el-form-item label="用时参考:">
                            <el-input type="textarea" :rows="3" v-model="form.timeReference"></el-input>
                        </el-form-item>
                    </el-form>
                </div>

                <div class="footer">
                    <div class="btns">
                        <el-button type="primary" @click="savePoi">保存</el-button>
                        <el-button @click="editStatus=false">取消</el-button>
                    </div>
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
            map: '',

            startPoint: '',
            endPoint: '',
            startPointInfo: {},
            endPointInfo: {},
            trafficType: 'TRANSIT',
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
            checkInTime: '',
            checkOuTime: '',

            poiTypeOpts:[
                {
                    value: 1,
                    name: '餐饮'
                },
                {
                    value: 2,
                    name: '游览'
                },
                {
                    value: 3,
                    name: '购物'
                },
                {
                    value: 4,
                    name: '娱乐'
                }
            ],

            form: {
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
            },

        }
    },
    
    watch: {
        dayInfo(val){
            // if(val){
            //     for (var i = 0; i < val.scheduletrips.length; i++) {
            //         if(val.scheduletrips[i].id == this.startId){
            //             this.startPointInfo = val.scheduletrips[i]
            //         }
            //         if(val.scheduletrips[i].id == this.endId){
            //             this.endPointInfo = val.scheduletrips[i]
            //         }
            //     }
            //     if(this.trafficId){
            //         for (var i = 0; i < val.traffics.length; i++) {
            //             if(val.traffics[i].id == this.trafficId){
            //                 this.trafficType = this.trafficsTypeToNameForamt(val.traffics[i].trafficType)
            //                 this.editStatus = false
            //             }
            //         }
            //     }

            //     this.initMap()
            //     this.getRouteInfo()
            // }
        },

        trafficId(val){
            // for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
            //     if(this.dayInfo.scheduletrips[i].id == this.startId){
            //         this.startPointInfo = this.dayInfo.scheduletrips[i]
            //     }
            //     if(this.dayInfo.scheduletrips[i].id == this.endId){
            //         this.endPointInfo = this.dayInfo.scheduletrips[i]
            //     }
            // }

            // if(this.trafficId){
            //     for (var i = 0; i < this.dayInfo.traffics.length; i++) {
            //         if(this.dayInfo.traffics[i].id == val){
            //             this.trafficType = this.trafficsTypeToNameForamt(this.dayInfo.traffics[i].trafficType)

            //             this.editStatus = false
            //         }
            //     }
            // }

            // if(val){
            //     this.editStatus = false
            // }else{
            //     this.editStatus = true
            // }

            // this.initMap()

            // this.getRouteInfo()
        },

        poiId(val){
            this.getDetail()
        }
    },
    created(){
        this.getDetail()
        // if(this.dayInfo['scheduletrips']){
        //     for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
        //         if(this.dayInfo.scheduletrips[i].id == this.startId){
        //             this.startPointInfo = this.dayInfo.scheduletrips[i]
        //         }
        //         if(this.dayInfo.scheduletrips[i].id == this.endId){
        //             this.endPointInfo = this.dayInfo.scheduletrips[i]
        //         }
        //     }

        //     if(this.trafficId){
        //         for (var i = 0; i < this.dayInfo.traffics.length; i++) {
        //             if(this.dayInfo.traffics[i].id == this.trafficId){
        //                 this.trafficType = this.trafficsTypeToNameForamt(this.dayInfo.traffics[i].trafficType)

        //                 this.editStatus = false
        //             }
        //         }
        //     }



        //     // this.initMap()

        //     this.getRouteInfo()
        // }

        // if(this.trafficId){
        //     this.editStatus = false
        // }else{
        //     this.editStatus = true
        // }
    },

    mounted(){
        if(this.setDayScheduleActive && this.dayInfo['scheduletrips']){
            this.initMap()
        }
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
            return this.$route.params.poiStartId
        },

        endId(){
            return this.$route.params.poiEndId
        },

        trafficId(){
            return this.$route.params.trafficId
        },

        dayId(){
            return this.$route.params.dayId
        },

        scheduleId(){
            return this.$route.params.scheduleId
        },

        poiId(){
            return this.$route.params.poiId
        },

        trafficTypeName(){
            if(this.trafficType == 'TRANSIT'){
                return '公交方案'
            }else if(this.trafficType == 'DRIVING'){
                return  '驾车方案'
            }else if(this.trafficType == 'WALKING'){
                return  '步行方案'
            }
        }

    },
    methods: {
        editPoi(){
            this.editStatus = true
            this.form = {
                nameCn: this.nameCn,
                nameEn: this.nameEn,
                address: this.address,
                lon: this.lon,
                lat: this.lat,
                cityId: this.cityId,
                cityname: this.cityname,
                type: this.type,
                permissionsType: this.permissionsType,
                phone: this.phone,
                openTime: this.openTime,
                consumDescription: this.consumDescription,
                trafficInstructions: this.trafficInstructions,
                timeReference: this.timeReference,
                price: this.price,
                addressInstrations: this.addressInstrations,
                supplier: this.supplier,
                url: this.url,
                createTime: this.createTime,
                createUserId: this.createUserId,
                guide: this.guide,
                imageurl: this.imageurl
            }
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
                url: '/tripnote/scheduletrip/doUpdate/' + this.scheduleId,
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

                    Bus.$emit('refreshSchedule', true)
                }
            })
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
            }else if(type == 5){
                return '<i class="iconfont icon-flag"></i>产品'
            }else if(type == 6){
                return '<i class="iconfont icon-chuangwei"></i>酒店'
            }
        },

        formatImg(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        getDetail(){
            this.$http({
                method: 'post',
                url: '/tripnote/schedulehotel/doDetail/' + this.scheduleId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    // this.editPoi()

                    Object.assign(this.$data, res.data.data)

                    if(this.setDayScheduleActive){
                        this.map.setCenter({lat: this.lat, lng: this.lon})
                        this.map.setZoom(16)
                    }
                }
            })
        },

        initMap(){
            var _pointList = this.dayInfo.scheduletrips

            this.map = new google.maps.Map(document.getElementById('map'), {
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
                strokeColor: '#4F5A61',
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
            if(type == 'TRANSIT'){
                return '<i class="iconfont icon-gongjiaoche"></i>'
            }else if(type == 'DRIVING'){
                return '<i class="iconfont icon-qiche"></i>'
            }else if(type == 'WALKING'){
                return '<i class="iconfont icon-buhang"></i>'
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
            return ['TRANSIT', 'DRIVING', 'WALKING'].indexOf(type) + 1
        },

        changeTrafficType(type){
            this.trafficType = type
            this.getRouteInfo()
        },

        getRouteInfo(){
            var vm = this
            var directionsService = new google.maps.DirectionsService;

            directionsService.route({
                origin: new google.maps.LatLng(this.startPointInfo.lat, this.startPointInfo.lon),
                destination: new google.maps.LatLng(this.endPointInfo.lat, this.endPointInfo.lon),
                travelMode: this.trafficType
            }, function(response, status) {
                vm.distance = response.routes[0].legs[0].distance.value
                vm.distanceText = response.routes[0].legs[0].distance.text

                vm.duration = response.routes[0].legs[0].duration.value
                vm.durationText = response.routes[0].legs[0].duration.text

                vm.routeSteps = response.routes[0].legs[0].steps

                // Route the directions and pass the response to a function to create
                // markers for each step.
                // if (status === 'OK') {
                //     document.getElementById('warnings-panel').innerHTML =
                //         '<b>' + response.routes[0].warnings + '</b>';
                //     directionsDisplay.setDirections(response);
                //     showSteps(response, markerArray, stepDisplay, map);
                // } else {
                //     window.alert('Directions request failed due to ' + status);
                // }
            })
        },

        addToSchedule(){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletraffic/doAdd',
                data: {
                    scheduleId: this.dayId,
                    startScheduleTrip: this.startId,
                    endScheduleTrip: this.endId,
                    distance: this.distance,
                    spendTime: this.duration,
                    trafficType: this.trafficTypeFormat(this.trafficType)
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
        }
    },
}
</script>
<style lang="less" scope>
.trafficBox{
    left: 620px;
    top: 80px;
    bottom: 20px;
    width: 400px;
    transition: all .2s ease;
    &.setDayPoiEditActive{
        left: 340px;
    }
    .header{
        padding-right: 0;
        .tit{
            font-size: 14px;
        }
        .actionBtn{
            float: left;
            margin-right: 15px;
        }
        .closeBtn{
            float: left;
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


    .demoInfo{
        padding: 15px;
        .pic{
            width: 100%;
            height: 160px;
            text-align: center;
            background-size: cover;
            background-position: center;
        }
        .hotelCheckTime{
            margin: 10px 0;
            label{
                color: #a0abb3;
                &:last-child{
                    margin-left: 50px;
                }
            }
        }
        .title{
            margin-top: 10px;
            h2{
                font-weight: normal;
                font-size: 20px;
                color: #23a16d;
            }
        }
        .des{
            margin-top: 5px;
            line-height: 24px;
        }
        .detail{
            margin-top: 15px;
            padding-top: 10px;
            border-top: 1px solid #e2e2e2;
            li{
                overflow: hidden;
                padding: 8px 0;
                label{
                    float: left;
                    width: 85px;
                    color: #999;
                }
                .txt{
                    margin-left: 85px;
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
    width: 20px;
    height: 20px;
    background: #23a16d;
    border-radius: 100%;
    color: #fff;
    text-align: center;
    line-height: 20px;
    font-size: 14px;
}
</style>