<template>
    <div class="columnBox setCity" :class="{overview : dayId == -1}">
        <div class="header">
            <div class="searchBox">
                <div class="icon"><i class="iconfont icon-sousuo1"></i></div>
                <input type="text" class="keywords" placeholder="添加目标城市" v-model="keywords" @keyup.enter="getCityList">
                <div class="clearKeywords" v-if="keywords != ''" @click="keywords='';countryName=''">
                    <i class="iconfont icon-tianjia"></i>
                </div>
            </div>
            <div class="opts">
                <a href="javascript:;" class="closeBox" @click="closeMap">
                    <i class="iconfont icon-guanbi"></i>
                </a>
            </div>
        </div>

        <div class="selectCity">
            <loading :isloading="listLoading"></loading>

            <div class="tit" v-if="keywords=='' && !listLoading">
                <div v-if="countryName == ''">选择目的地</div>
                <div v-if="countryName != ''">
                    <a href="javascript:;" class="back" @click="backCountryList">
                        <i class="iconfont icon-back01"></i>
                    </a>{{countryName}}
                </div>
            </div>
            <div class="list" :class="{keywords: keywords!=''}" v-if="!listLoading">
                <ul class="cityList">
                    <li v-for="(item, index) in countryList" @click="showCityList(item.id, item.nameCn, item.lat, item.lon)" v-if="countryName==''">
                        <div class="con">
                            <div class="icon">
                                <img :src="getFlag(item.code)">
                            </div>
                            <div class="name" :title="item.nameCn">{{item.nameCn}}</div>
                            <div class="moreLink">
                                <i class="iconfont icon-fanhui"></i>
                            </div>
                            <div class="sel" v-if="countrySel.indexOf(item.id) > -1">
                                <i class="iconfont icon-queren1"></i>
                            </div>
                        </div>
                    </li>

                    <li v-for="(item, index) in cityList" v-if="countryName!=''" @click="addCity(item)" class="city" 
                    :class="{open: item.addVisible}">
                        <div class="con">
                            <div class="name" :title="item.nameCn">{{item.nameCn}}</div>
                            <div class="moreLink">
                                <i class="iconfont icon-add"></i>
                            </div>
                            <div class="sel" v-if="citySel.indexOf(item.id) > -1">
                                <i class="iconfont icon-queren1"></i>
                            </div>
                        </div>

                        <div class="addToDay" v-if="item.addVisible">
                            <div class="reduceNum" @click.stop="reduceNum(item)" :class="{disabled : item.num == 0}"><i class="iconfont icon-jian"></i></div>
                            <div class="num">{{item.num == 0 ? '当天' : item.num}}</div>
                            <div class="addNum" @click.stop="addNum(item)" :class="{disabled : item.num == 20}"><i class="iconfont icon-tianjia2"></i></div>
                            <div class="btns">
                                <el-button size="medium" @click.stop="closeAddToDay(item)">取消</el-button>
                                <el-button size="medium" type="primary" @click.stop="addToDaySubmit(item)">确定</el-button>
                            </div>
                        </div>
                    </li>
                </ul>
                <el-pagination
                    v-if="countryName!='' && keywords == ''"
                    @current-change="handleCurrentChange"
                    :page-size="25"
                    class="cityPager"
                    layout="prev, pager, next"
                    :total="1000">
                </el-pagination>
            </div>
        </div>
        <div class="googleMap" id="map">
            
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import Bus from 'utils/bus'
export default {
    
    props: {
        dayId: {
            type: Number,
            required: true
        },
        cityInfo: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            map: '',
            countryList: [],
            cityList: [],
            countrySel: [],
            citySel: [],
            countryId: '',
            countryName: '',
            countryLat: '',
            countryLon: '',
            pageNo: 1,
            cityChain: [],
            keywords: '',

            chainMarker: [],
            chainPloyline: '',
            cityMarker: [],
            listLoading: true
        }
    },
    watch: {
        cityInfo(){
            var _countryId = []
            var _cityId = []
            this.cityChain = []

            for (var i = 0; i < this.cityInfo.length; i++) {
                _countryId.push(this.cityInfo[i].countryId)
                _cityId.push(this.cityInfo[i].cityId)
            }
            this.countrySel = Array.from(new Set(_countryId));
            this.citySel = Array.from(new Set(_cityId));

            this.mapCityChain()
        }

    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
        }),
    },
    created(){
        this.getCountryList()
        
    },
    mounted(){
        this.$nextTick(() => {
            this.initMap()
        })
    },
    methods: {
        initMap(){
            var vm = this
            this.map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 39.920000, lng: 116.460000},
                zoom: 2
            })

            google.maps.event.addListener(this.map, "click",function(event){
                // console.log('触发点击事件');
            })

            google.maps.event.addListener(this.map, "bounds_changed",function(event){
                // console.log('边界改变')

                var mapLatLngBounds = vm.map.getBounds();

                var maxX = mapLatLngBounds.getNorthEast().lng();
                var maxY = mapLatLngBounds.getNorthEast().lat();
                var minX = mapLatLngBounds.getSouthWest().lng();
                var minY = mapLatLngBounds.getSouthWest().lat();

                // console.log(maxX)
                // console.log(maxY)
                // console.log(minX)
                // console.log(minY)
            })

            this.mapCityChain()
        },

        mapCityChain(){
            this.chainPloyline && this.chainPloyline.setMap(null);

            var flightPlanCoordinates = []

            for (var i = 0; i < this.cityInfo.length; i++) {
                flightPlanCoordinates.push({
                    lat: parseFloat(this.cityInfo[i].lat.toFixed(2)),
                    lng: parseFloat(this.cityInfo[i].lon.toFixed(2))
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

            this.mapCityMarker(this.cityInfo, 'chain')
        },

        gotoCountryMap(){
            this.map.setCenter({lat: this.cityList[0].lat, lng: this.cityList[0].lon})
            this.map.setZoom(4)

            this.mapCityMarker(this.cityList, 'city')
        },


        mapCityMarker(data, type){
            var vm = this
            if(type == 'chain'){
                for (var i = 0; i < this.chainMarker.length; i++) {
                    this.chainMarker[i].setMap(null);
                }
            }else{
                for (var i = 0; i < this.cityMarker.length; i++) {
                    this.cityMarker[i].setMap(null);
                }
            }

            var infoBubble
            var infoTimer
            var _txtArr = []

            var _infoBubble = {}

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement('DIV');
                var _class = type == "chain" ? "chain" : ""
                div.innerHTML = '<div class="mapMarker ' + _class + '"></div>';

                var marker = new RichMarker({
                    map: this.map,
                    position: new google.maps.LatLng(data[i].lat, data[i].lon),
                    draggable: false,
                    flat: true,
                    anchor: RichMarkerPosition.MIDDLE,
                    content: div
                });


                if(type == 'chain'){
                    this.chainMarker.push(marker)
                }else{
                    this.cityMarker.push(marker)
                }
                _txtArr.push(data[i].nameCn)

                _infoBubble[i] = {
                    infobox: '',
                    infotimer: '',
                    has: false
                }

                var _infoBubble
                var _infoBubbleTimer

                // _infoBubbleArr.push(_infoBubble)
                // _infoBubbleTimerArr.push(_infoBubbleTimer)

                // if(type == 'chain'){
                //     google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                //         return function() {
                //             clearTimeout(_infoBubble[i].infotimer)

                //             if(!_infoBubble[i].has){
                //                 _infoBubble[i].infobox = new InfoBubble({
                //                     borderWidth: 0,
                //                     borderRadius: 0,
                //                     backgroundColor: '#22A98E',
                //                     disableAutoPan: true,
                //                     hideCloseButton: true,
                //                     arrowSize: 8,
                //                     minWidth: 180,
                //                     maxWidth: 180,
                //                     minHeight: 38,
                //                     // maxHeight: 38,
                //                     padding: 0,
                //                     content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div></div>',
                //                 })

                //                 _infoBubble[i].infobox.open(this.map, marker);
                //                 _infoBubble[i].has = true

                //             }else{
                //                 _infoBubble[i].infobox.close()
                //                 _infoBubble[i].has = false
                //             }
                //         }
                //     })(marker, i));

                //     google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
                //         return function() {
                //             _infoBubble[i].infobox.close()
                //         }
                //     })(marker, i));
                // }else{
                    google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function() {
                            clearTimeout(_infoBubble[i].infotimer)

                            window.addNumCon = function(dom, infobox){
                                $(dom).parent().append('<div class="addToDay" v-if="item.addVisible">'
                                    +'<div class="reduceNum" onclick="reduceNum(this)"><i class="iconfont icon-jian"></i></div>'
                                    +'<div class="num">当天</div>'
                                    +'<div class="addNum" onclick="addNum(this)"><i class="iconfont icon-tianjia2"></i></div>'
                                    +'<div class="btns">'
                                        +'<button type="button" class="el-button el-button--default el-button--medium" onclick="closeAddNum(this)"><span>取消</span></button>'
                                        +'<button type="button" class="el-button el-button--primary el-button--medium" onclick="addNumSubmit(this)"><span>确定</span></button>'
                                    +'</div>'
                                +'</div>')
                                _infoBubble[i].infobox.setMinHeight(128);

                                data[i]['num'] = 0

                                Bus.$emit('addCityToDayInit', data[i])
                            }
                            window.num = 0
                            window.reduceNum = function(dom){
                                if(window.num > 0){
                                    window.num--
                                }
                                var _txt = window.num == 0 ? '当天' : window.num
                                $(dom).parent().find('.num').html(_txt)

                                data[i].num = window.num

                                Bus.$emit('addCityToDayAdd', data[i])
                            }
                            window.addNum = function(dom){
                                if(window.num < 20){
                                    window.num++
                                }
                                $(dom).parent().find('.num').html(window.num)

                                data[i].num = window.num

                                Bus.$emit('addCityToDayAdd', data[i])
                            }

                            window.closeAddNum = function(dom){
                                $(dom).parents('.addToDay').remove()
                                _infoBubble[i].infobox.setMinHeight(38);

                                Bus.$emit('refreshDay', true)
                            }

                            window.addNumSubmit = function(dom){
                                $(dom).parents('.addToDay').remove()
                                _infoBubble[i].infobox.setMinHeight(38);
                                _infoBubble[i].infobox.close()

                                data[i].num = window.num
                                Bus.$emit('addToDaySubmit', data[i])
                            }

                            if(!_infoBubble[i].has){
                                _infoBubble[i].infobox = new InfoBubble({
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
                                    content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div><a class="addBtn" onclick="addNumCon(this)"><i class="iconfont icon-lnicon34"></i></a></div>',
                                })

                                _infoBubble[i].infobox.open(this.map, marker);
                                _infoBubble[i].has = true

                            }else{
                                _infoBubble[i].infobox.close()
                                _infoBubble[i].has = false
                            }
                        }
                    })(marker, i));
                // }
            }

            google.maps.event.addListener(this.map, 'click', function() {
                for (var i in _infoBubble) {
                    if(_infoBubble[i].infobox){
                        _infoBubble[i].infobox.close()
                        _infoBubble[i].has = false
                    }
                }
            });
        },


        getFlag(val){
            if(['XK', 'BQ', 'BL', 'JE', 'GG'].indexOf(val) > -1){
                val = 'CN'
            }

            return require('../../assets/images/flag/' + val.toLowerCase() + '.png')
        },
        backCountryList(){
            this.countryName = ''
            for (var i = 0; i < this.cityMarker.length; i++) {
                this.cityMarker[i].setMap(null);
            }
            this.getCountryList()
        },
        getCountryList(){
            this.listLoading = true
            this.$http({
                method: 'get',
                url: '/tripnote/country/doSearch',
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    var _countryId = []
                    for (var i = 0; i < this.cityInfo.length; i++) {
                        _countryId.push(this.cityInfo[i].countryId)
                    }
                    this.countrySel = Array.from(new Set(_countryId));

                    this.countryList = res.data.data

                    this.listLoading = false
                }
            })
        },

        showCityList(countryId, nameCn, lat, lon){
            this.countryId = countryId
            this.countryName = nameCn
            this.countryLat = lat
            this.countryLon = lon

            // this.gotoCountryMap(lat, lon)

            this.getCityList()
        },

        getCityList(){
            this.listLoading = true
            var _data = {
                countryId: this.countryId,
                pageSize: 25,
                pageNo: this.pageNo
            }
            if(this.keywords != ''){
                this.countryName = '-'
                _data['nameCn'] = this.keywords
                _data.pageSize = 1000
            }
            this.$http({
                method: 'post',
                url: '/tripnote/city/doSearch',
                data: _data
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    var _countryId = []
                    for (var i = 0; i < this.cityInfo.length; i++) {
                        _countryId.push(this.cityInfo[i].cityId)
                    }
                    this.citySel = Array.from(new Set(_countryId));

                    var _data = res.data.data
                    for (var i = 0; i < _data.length; i++) {
                        _data[i]['addVisible'] = false
                        _data[i]['num'] = 0
                    }
                    this.cityList = _data

                    if(this.cityList.length > 0){
                        this.gotoCountryMap()
                    }
                    this.listLoading = false

                }
            })
        },

        handleCurrentChange(val){
            this.pageNo = val
            this.getCityList()
        },

        addCity(item){
            if(!item['addVisible']){
                item['addVisible'] = true
                item['num'] = 0

                Bus.$emit('addCityToDayInit', item)
            }
        },

        addToDaySubmit(item){
            Bus.$emit('addToDaySubmit', item)
            item.addVisible = false
        },

        closeMap(){
            this.$store.dispatch('setRouteLineActive', false)
        },

        reduceNum(item){
            if(item.num > 0){
                item.num--
                Bus.$emit('addCityToDayReduce', item)
            }
        },

        addNum(item){
            if(item.num < 20){
                item.num = item.num + 1
                Bus.$emit('addCityToDayAdd', item)
            }
        },

        closeAddToDay(item){
            item.addVisible = false
            Bus.$emit('refreshDay', true)
        },

        addToDaySumit(item){
            console.log(item)
        },

    },
}
</script>
<style lang="less" scope>
.cityPager{
    padding: 10px 0;
    text-align: center;
    .el-pager {
        li.number{
            display: none;
        }
        li.number.active{
            display: block;
        }
    }
    .el-pager li.btn-quicknext, .el-pager li.btn-quickprev{
        display: none;
    }
    .el-icon{
        font-size: 14px !important;
    }
    li{
        font-size: 14px !important;
    }
}
.setCity{
    z-index: 1;
    left: 300px;
    right: 20px;
    top: 80px;
    bottom: 20px;
    .header{
        .searchBox{
            float: left;
            width: 290px;
            height: 54px;
            margin-left: -5px;
            border-right: 1px solid #EAEDF1;
            .icon{
                float: left;
                width: 25px;
                line-height: 54px;
                text-align: left;
                i{
                    font-size: 18px;
                    color: #aaa;
                }
            }
            .keywords{
                float: left;
                width: 215px;
                height: 54px;
                line-height: 54px;
                border: 0;
                // background: #ddd;
                font-size: 14px;
            }
            .clearKeywords{
                float: right;
                width: 40px;
                line-height: 54px;
                text-align: center;
                i{
                    font-size: 20px;
                }
            }
        }
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

    .selectCity{
        width: 300px;
        .tit{
            height: 40px;
            padding-top: 10px;
            line-height: 30px;
            font-size: 16px;
            padding-left: 35px;
            border-right: 1px solid #EAEDF1;
            // font-weight: bold;
            .back{
                float: left;
                width: 30px;
                margin-left: -30px;
                text-align: center;
                i{
                    font-size: 20px;
                }
            }
        }
        .list{
            position: absolute;
            top: 95px;
            left: 0;
            bottom: 0;
            width: 300px;
            overflow-y: auto;
            border-right: 1px solid #EAEDF1;
            &.keywords{
                top: 55px;
            }
            .cityList{
                li{
                    line-height: 36px;
                    font-size: 14px;
                    cursor: pointer;
                    &:hover{
                        background: #EBEDF1;
                    }
                    .con{
                        overflow: hidden;
                    }
                    .icon{
                        float: left;
                        width: 22px;
                        height: 14px;
                        margin: 10px 6px 0 35px;
                        img{
                            width: 100%;
                            height: 100%;
                            display: block;
                        }
                    }
                    .name{
                        float: left;
                        width: 190px;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                    }
                    &.city{
                        .name{
                            margin-left: 35px;
                        }
                    }
                    &.open{
                        background: #EBEDF1;
                        .moreLink,
                        .sel{
                            display: none;
                        }
                    }
                    .moreLink{
                        display: none;
                        float: right;
                        width: 30px;
                        height: 36px;
                        overflow: hidden;
                        margin-right: 5px;
                        text-align: center;
                        font-size: 12px;
                        cursor: pointer;
                        font-weight: bold;
                        .icon-add{
                            font-size: 18px;
                            color: #23a16d;
                        }
                    }
                    .sel{
                        float: right;
                        width: 30px;
                        height: 36px;
                        overflow: hidden;
                        margin-right: 5px;
                        text-align: center;
                        font-size: 12px;
                        cursor: pointer;
                        font-weight: bold;
                        color: #23a16d;
                    }
                    .addToDay{
                        clear: both;
                        background: #fff;
                        padding: 10px 0;
                        box-shadow: 0 10px 10px 0 rgba(0, 0, 0, 0.1);
                        text-align: center;
                        .reduceNum,
                        .num,
                        .addNum{
                            width: 50px;
                            display: inline-block;
                            color: #92959A;
                            text-align: center;
                            &.disabled{
                                color: #bbb;
                                cursor: default;
                            }
                        }
                    }
                    &:hover{
                        .moreLink{
                            display: block;
                        }
                        .sel{
                            display: none;
                        }
                    }

                }
            }
            
        }
    }
    
    .content{
        padding: 10px 15px;
        line-height: 24px;
        font-size: 14px;
    }
    .googleMap{
        position: absolute;
        top: 55px;
        left: 300px;
        right: 0;
        bottom: 0;
    }

    &.overview{
        .header{
            .searchBox{
                display: none;
            }
        }
        .selectCity{
            display: none;
        }
        .googleMap{
            left: 0;
        }
    }
}
.mapMarker{
    width: 10px;
    height: 10px;
    background: #777;
    border-radius: 100%;
    opacity: 0.75;
    &:hover{
        width: 12px;
        height: 12px;
        margin: -1px 0 0 -1px;
        opacity: 1;
    }
    &.chain{
        z-index: 1;
        opacity: 1;
        background: red;
    }
    cursor: pointer;
}

.mapMarkerTxt {
    line-height: 36px;
    font-size: 14px;
    color: #fff;
    .tit{
        float: left;
        margin-left: 10px;
    }
    .addBtn{
        float: right;
        width: 36px;
        height: 36px;
        text-align: center;
        line-height: 36px;
        font-size: 20px;
        color: #fff;
        &:hover{
            background: rgba(0,0,0,0.1);
        }
    }
    .addToDay{
        clear: both;
        background: #fff;
        padding: 10px 0;
        box-shadow: 0 10px 10px 0 rgba(0, 0, 0, 0.1);
        text-align: center;
        .reduceNum,
        .num,
        .addNum{
            width: 50px;
            display: inline-block;
            color: #92959A;
            text-align: center;
            cursor: pointer;
            &.disabled{
                color: #bbb;
                cursor: default;
            }
        }
    }
}
</style>