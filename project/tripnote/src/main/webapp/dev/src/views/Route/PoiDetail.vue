<template>
    <div>
        <div class="poiDetailBg"></div>
        <div class="poiDetail">
            <div class="header" v-if="!editStatus">
                <ul class="tabs">
                    <li>
                        <a href="javascript:;" :class="{active: tabActive=='base'}" @click="tabActive='base'">基本信息</a>
                    </li>
                    <li class="line"></li>
                    <li>
                        <a href="javascript:;" :class="{active: tabActive=='demo'}" @click="tabActive='demo'">模板展示</a>
                    </li>
                </ul>
                <a href="javascript:;" class="closeBtn" @click="closeBtn">
                    <i class="iconfont icon-guanbi"></i>
                </a>
                <div class="btns">
                    <el-button icon="el-icon-check" v-if="poiSelected">已添加</el-button>
                    <el-button icon="el-icon-plus" v-if="!poiSelected" @click="addToSchedule">添加到行程</el-button>
                </div>
            </div>

            <div class="header" v-if="editStatus">
                <div class="tit">编辑POI__{{nameCn}}</div>
                <a href="javascript:;" class="closeBtn" @click="closeBtn">
                    <i class="iconfont icon-guanbi"></i>
                </a>
            </div>

            <div class="con baseInfo" v-if="!editStatus && tabActive=='base'">
                <div class="title">
                    <h2>{{nameCn}}</h2>
                    <a href="javascript:;" class="edit" @click="changeEditStatus"><i class="iconfont icon-bianji1"></i>编辑产品</a>
                    <div class="subTit"><label>英文名</label>{{nameEn}}</div>
                </div>
                <div class="detail">
                    <div class="detailList">
                        <ul>
                            <li>
                                <label>POI类型</label>
                                <div class="txt" v-html="typeFormat(type)"></div>
                            </li>
                            <li>
                                <label>电话</label>
                                <div class="txt">{{phone}}</div>
                            </li>
                            <li>
                                <label>网址</label>
                                <div class="txt">{{phone}}</div>
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
                            <li>
                                <label>地点简介</label>
                                <div class="txt">{{addressInstrations}}</div>
                            </li>
                        </ul>
                    </div>
                    <div class="mapInfo">
                        <div class="tit">地理位置</div>
                        <div class="mapWrap">
                            <div id="MapPoi" class="mapPoi"></div>
                            <a href="javascript:;" class="openBtn" @click="showBigMap">点击展开地图查看</a>
                        </div>
                        <div class="address">{{address}}</div>
                    </div>
                </div>
            </div>

            <div class="con demoInfo" v-if="!editStatus && tabActive=='demo'">
                <div class="pic" :style="{backgroundImage: `url(${formatImg(imageurl)})`}"></div>
                <div class="title">
                    <h2>{{nameCn}}</h2>
                </div>
                <div class="des">
                    {{addressInstrations}}
                </div>
                <div class="detail">
                    <ul>
                        <li>
                            <label>POI类型</label>
                            <div class="txt" v-html="typeFormat(type)"></div>
                        </li>
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

            <div class="con editPoi" v-if="editStatus">
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
                <div class="btns" v-if="!editStatus">
                    <el-button @click="closeBtn">取消</el-button>
                </div>

                <div class="btns" v-if="editStatus">
                    <el-button type="primary" @click="savePoi">保存</el-button>
                    <el-button @click="editStatus=false">取消</el-button>
                </div>
            </div>
        </div>

        <div class="bigMap">
            <div class="bigMapBg" :class="{show: bigMapVisible==true}" @click="bigMapVisible=false"></div>
            <div class="mapWrap" :class="{show: bigMapVisible==true}">
                <div class="map" id="MapPoiBig"></div>
                <a href="javascript:;" class="closeBigMap" @click="bigMapVisible=false">
                    <i class="iconfont icon-close"></i>
                </a>
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

            mapInit: false,

            bigMapVisible: false,

            poiSelected: false
        }
    },
    
    watch: {
        tabActive(val){
            // if(val){

            // }
        },
        dayInfo(val){
            if(val){
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
        }
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

        showBigMap(){
            this.bigMapVisible = true
            var vm = this
            // setTimeout(function(){
                vm.initBigMap(vm.lat, vm.lon)
            // }, 50)
        },
        initMap(lat, lng){
            if(this.mapInit){
                return
            }else{
                this.mapInit = true
            }
            var vm = this
            this.map = new google.maps.Map(document.getElementById('MapPoi'), {
                center: {lat: lat, lng: lng},
                zoom: 15
            })

            var marker = new google.maps.Marker({
                map: this.map,
                position: new google.maps.LatLng(lat, lng),
                draggable: false,
                flat: true,
                anchor: RichMarkerPosition.MIDDLE
            });
        },

        initBigMap(lat, lng){
            var vm = this
            this.mapBig = new google.maps.Map(document.getElementById('MapPoiBig'), {
                center: {lat: lat, lng: lng},
                zoom: 15
            })

            var marker = new google.maps.Marker({
                map: this.mapBig,
                position: new google.maps.LatLng(lat, lng),
                
                // draggable: false,
                // flat: true,
                // anchor: RichMarkerPosition.MIDDLE,
                // content: '<div style="width: 10px; height: 10px; background: red"></div>'
            });

            // google.maps.event.addListener(marker1, 'click', function() {
                var infoBubble = new InfoBubble({
                    borderWidth: 0,
                    borderRadius: 0,
                    backgroundColor: '#fff',
                    disableAutoPan: true,
                    hideCloseButton: true,
                    arrowSize: 8,
                    minWidth: 180,
                    maxWidth: 250,
                    minHeight: 38,
                    // maxHeight: 38,
                    padding: 0,
                    content: '<div class="mapPoiMarker"><p class="tit">'+this.nameCn+'</p><p>' + this.address + '</p></div>',
                })

                infoBubble.open(this.mapBig, marker);
            // })

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

        changeEditStatus(){
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

                    this.initMap(this.lat, this.lon)
                    // this.initBigMap(this.lat, this.lon)
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

        changeTabs(tab, event) {
            // this.activeName = tab.name
        },
        closeBox(){
            this.$store.dispatch('setDayScheduleActive', false)
            this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId})
        },

        setSchedule(){
            this.$store.dispatch('setDayScheduleActive', !this.setDayScheduleActive)
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
        &.baseInfo{
            .title{
                border-bottom: 1px solid #e2e2e2;
                h2{
                    float: left;
                    font-weight: normal;
                    font-size: 20px;
                    color: #23a16d;
                }
                .edit{
                    float: right;
                    color: #23a16d;
                    font-size: 14px;
                    i{
                        margin-right: 5px;
                        font-size: 13px;
                    }
                }
                .subTit{
                    clear: both;
                    overflow: hidden;
                    padding: 15px 0;
                    label{
                        float: left;
                        width: 85px;
                        color: #999;
                    }
                }
            }
            .detail{
                overflow: hidden;
                .detailList{
                    float: left;
                    width: 605px;
                    padding-right: 30px;
                    border-right: 1px solid #e2e2e2;
                    li{
                        padding: 15px 0;
                        overflow: hidden;
                        border-bottom: 1px solid #e2e2e2;
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
            .mapInfo{
                float: right;
                width: 275px;
                .tit{
                    margin-top: 15px;
                    margin-bottom: 5px;
                    line-height: 24px;
                    font-size: 14px;
                }
                .mapWrap{
                    position: relative;
                    width: 277px;
                    height: 277px;
                    border: 1px solid #e2e2e2;
                    .mapPoi{
                        width: 275px;
                        height: 275px;
                    }
                    .openBtn{
                        position: absolute;
                        bottom: 0;
                        left: 0;
                        width: 100%;
                        height: 34px;
                        background: rgba(0,0,0,.25);
                        text-align: center;
                        line-height: 34px;
                        color: #fff;
                        &:hover{
                            background: rgba(0,0,0,.4);
                        }
                    }
                }
                .address{
                    margin-top: 10px;
                    color: #999;
                }
            }
        }

        &.demoInfo{
            .pic{
                width: 100%;
                height: 360px;
                text-align: center;
                background-size: cover;
                background-position: center;
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


.bigMap{
    .bigMapBg{
        display: none;
        position: fixed;
        z-index: 9999;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        opacity: 0.3;
        background: #000;
        &.show{
            display: block;
        }
    }
    .mapWrap{
        position: absolute;
        z-index: 10000;
        left: 0;
        top: 0;
        bottom: 50px;
        width: 100%;
        max-height: 590px;
        -webkit-trafficion: transform .2s;
        -moz-trafficion: transform .2s;
        -o-trafficion: transform .2s;
        trafficion: transform .2s;
        -webkit-transform: translateY(-120%);
        -ms-transform: translateY(-120%);
        transform: translateY(-120%);
        -moz-transform: translateY(-120%);

        &.show{
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0);
            -moz-transform: translateY(0);
        }
        .map{
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
        }
        .closeBigMap{
            display: block;
            position: absolute;
            z-index: 10001;
            left: 50%;
            bottom: -40px;
            margin-left: -40px;
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background: #fff;
            box-sizing: border-box;
            text-align: center;
            line-height: 85px;
            box-shadow: rgba(0,0,0,.2) 0 1px 2px 0;
            &:hover{
                background: #f1f1f1;
            }
            i{
                font-size: 30px;
                color: #999;
            }
        }
    }
}
.mapPoiMarker{
    padding: 10px;
    font-size: 14px;
    .tit{
        padding-bottom: 5px;
        font-weight: bold;
    }
}
</style>