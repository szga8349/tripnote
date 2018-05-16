<template>
    <div>
        <div class="mainTopTit"></div>
        <div class="commonBox">
            <div class="commonTit">
                <h2>我的收藏库</h2>
                <div class="rightOpts">
                    <Select class="poiTypeFilter" @changeOpt="changeFilterType" :opts="filterOptsList" :sel="filterOptsSel" nameAlias="name" idAlias="id" align="right"></Select>
                </div>
            </div>

            <div class="commonCon">
                <div class="mainCollectList">
                    <ul class="tableList">
                        <li v-for="(item, index) in poiList">
                            <div class="inside" @click="collectDetail(item)">
                                <div class="pic">
                                    <div class="img" :style="{backgroundImage: `url(${imgFormat(item.imageUrl)})`}"></div>
                                </div>
                                <div class="info">
                                    <div class="tit">
                                        <span v-html="collectionTypeFormat(item.type)"></span>
                                        {{item.name}}
                                    </div>
                                    <el-tooltip placement="bottom">
                                        <div slot="content">删除</div>
                                        <a href="javascript:;" @click.stop="delConfirm(item.id)" class="del"><i class="el-icon-delete"></i></a>
                                    </el-tooltip>
                                </div>
                            </div>
                        </li>
                    </ul>

                    <el-pagination
                        v-if="poiList.length > 0"
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="pageNo"
                        :page-size="8"
                        layout="total, prev, pager, next"
                        :total="total">
                    </el-pagination>
                </div>

                <div class="noDataTip" v-if="!tableDataLoading && poiList.length == 0">
                    <i class="iconfont icon-point-out"></i>没有相关数据！
                </div>
            </div>
        </div>

        <el-dialog title="资源详情" :visible.sync="dialogDetailVisible" width="800px" top="20px">
            <div class="poiDetailImg" :style="{backgroundImage: `url(${imgFormat(form.imageurl)})`}"></div>
            <el-form ref="form" :model="form" label-width="70px" class="poiDetailForm" v-if="detailType==1">
                <el-form-item label="中文名称:">
                    {{form.nameCn}}
                </el-form-item>

                <el-form-item label="英文名称:">
                    {{form.nameEn}}
                </el-form-item>

                <el-form-item label="类型:">
                    <span class="poiType" v-html="poiTypeFormat(form.type)"></span>
                </el-form-item>

                <el-form-item label="其他语言:">
                    {{form.lang}}
                </el-form-item>

                <el-form-item label="地址:">
                    {{form.address}}
                </el-form-item>

                <el-form-item label="所在城市:">
                    {{form.cityname}}
                </el-form-item>

                <el-form-item label="供应商:">
                    {{form.supplier}}
                </el-form-item>

                <el-form-item label="电话:">
                    {{form.phone}}
                </el-form-item>

                <el-form-item label="网址:">
                    {{form.url}}
                </el-form-item>

                <el-form-item label="开放时间:">
                    {{form.openTime}}
                </el-form-item>

                <el-form-item label="用时参考:">
                    {{form.timeReference}}
                </el-form-item>

                <el-form-item label="交通指引:">
                    {{form.trafficInstructions}}
                </el-form-item>

                <el-form-item label="实用指南:">
                    {{form.guide}}
                </el-form-item>

                <el-form-item label="地点简介:">
                    {{form.addressInstrations}}
                </el-form-item>
            </el-form>

            <el-form ref="form" :model="form" label-width="70px" class="poiDetailForm" v-if="detailType==2">
                <el-form-item label="标题:">
                    {{form.title}}
                </el-form-item>
                <el-form-item label="内容:" v-if="form.type == 1">
                    {{form.context}}
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDetailVisible = false">关闭</el-button>
            </div>
        </el-dialog>

        <el-dialog title="提示" :visible.sync="dialogDelTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除该收藏吗？</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelTip = false">取消</el-button>
                <el-button type="primary" @click.native="delSubmit">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import moment from 'moment'
import {mapState} from 'vuex'
import { FormatTime } from 'mixins/common'
import Bus from 'utils/bus'

export default {
    mixins: [ FormatTime ],
    watch: {
        'form.dateRange'(val){
            if(val != ''){
                this.form.days = (new Date(val[1]).getTime() - new Date(val[0]).getTime())/(24*60*60*1000) + 1
            }
        }
    },

    data() {
        return {
            map: '',
            dialogDelTip: false,

            pickerOptions:{
                disabledDate(time) {
                    return time.getTime() < Date.now();
                },
            },

            filterOptsList: [{
                id: -1,
                name: '全部'
            },{
                id: 1,
                name: 'POI'
            },{
                id: 2,
                name: '素材'
            }],

            filterOptsSel: '全部',
            filterOptsSelId: -1,

            tableData: [],
            tableDataLoading: true,
            pageNo: 1,
            pageSize: 8,
            total: 0,
            poiList: [],

            filterOptsSelSys: '全部',
            filterOptsSelIdSys: -1,

            tableDataSys: [],
            tableDataLoadingSys: true,
            pageNoSys: 1,
            pageSizeSys: 8,
            totalSys: 0,

            poiListSys: [],

            poiType: [{
                label: '餐饮',
                value: 1
            },{
                label: '游览',
                value: 2
            },{
                label: '购物',
                value: 3
            },{
                label: '娱乐',
                value: 4
            }],

            form: {
                nameCn: '',
                nameEn: '',
                type: '',
                lat: '',
                lon: '',
                address: '',
                cityId: [],
                cityname: '',
                price: '',
                phone: '',
                url: '',
                imageurl: '',
                openTime: '',
                phone: '',
                timeReference: '',
                trafficInstructions: '',
                addressInstrations: '',
                supplier: '',
                guide: '',
            },
            dialogAddVisible: false,
            dialogDetailVisible: false,
            templateVisible: false,
            routeId: '',
            delPOIId: '',
            sortField: 'create_time',
            sortType: -1,

            poiStatus: 'add',
            detailType: ''
        }
    },
    created(){
        this.getCollectionList()
        // this.getCollectionListSys()

        Bus.$emit('setMainRoutePage', false)

        var vm = this

        Bus.$on('headerSearchCollection', function(keywords){
            vm.searchKeyword = keywords
            
            vm.getCollectionList()
        })

    },
    methods: {
        dialogPoiTit(){
            if(this.poiStatus == 'add'){
                return '添加素材'
            }else if(this.poiStatus == 'edit'){
                return '编辑素材'
            }else if(this.poiStatus == 'sys'){
                return '素材详情'
            }
        },

        collectDetail(item, type){
            // if(type == 'sys'){
                this.poiStatus = 'sys'
                this.dialogDetailVisible = true
            // }else{
            //     this.poiStatus = 'edit'
            //     this.dialogAddVisible = true
            // }



            this.detailType = item.type

            if(item.type == 1){
                this.$http({
                    method: 'POST',
                    url: '/poi/doDetail/' + item.cid,
                })
                .then((res)=>{
                    if(res.data.code == -1){
                        this.$message({
                            message: res.data.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        this.form = res.data.data
                    }
                })
            }else{
                this.$http({
                    method: 'POST',
                    url: '/user/tripnote/doDetail/' + item.cid,
                })
                .then((res)=>{
                    if(res.data.code == -1){
                        this.$message({
                            message: res.data.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        this.form['imageurl'] = res.data.data.imageUrl
                        this.form['title'] = res.data.data.title
                        this.form['type'] = res.data.data.type
                        this.form['context'] = res.data.data.context
                    }
                })
            }

            
        },

        changeFilterType(item){
            this.filterOptsSel = item.name
            this.filterOptsSelId = item.id

            this.getCollectionList()
        },

        changeFilterTypeSys(item){
            this.filterOptsSelSys = item.name
            this.filterOptsSelIdSys = item.id

            this.getCollectionListSys()
        },


        mapInit(){
            var vm = this
            if(this.map == ''){
                setTimeout(function(){
                    vm.map = new google.maps.Map(document.getElementById('GeoMap'), {
                        center: {lat: 39.920000, lng: 116.460000},
                        zoom: 3,
                        disableDefaultUI: true
                    })
                }, 300)
                
            }            
        },

        queryGeoSearch(queryString, cb) {
            var vm = this
            var geocoder = new google.maps.Geocoder()

            geocoder.geocode({'address': this.geoKeyword}, function(results, status) {  
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results.length == 1) {
                        console.log('one')
                        vm.map.setCenter(results[0].geometry.location)
                        vm.map.setZoom(15)

                        vm.form.lat = results[0].geometry.location.lat()
                        vm.form.lon = results[0].geometry.location.lng()
                        vm.form.address = results[0].formatted_address

                        console.log('lat', vm.form.lat)
                        console.log('lon', vm.form.lon)

                        var _marker = new google.maps.Marker({
                            map: vm.map,
                            // icon: icon,
                            // title: place.name,
                            position: results[0].geometry.location,
                            draggable: true,
                        })

                        google.maps.event.addListener(_marker, 'dragend', function(event) {
                            geocoder.geocode({'location': event.latLng}, function(results, status) {  
                                if (status == google.maps.GeocoderStatus.OK) {
                                    if (results[0]) {
                                        vm.form.lat = results[0].geometry.location.lat()
                                        vm.form.lon = results[0].geometry.location.lng()

                                        vm.form.address = results[0].formatted_address
                                    }
                                }
                            })
                            vm.map.panTo(event.latLng)
                        })

                        cb([])
                    } else {
                        for (var i = 0; i < results.length; i++) {
                            results[i].value = results[i].formatted_address
                        }
                        cb(results)
                    }
                } else {
                    
                }
            });
            
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

        geoCitySelect(item){
            this.form.cityId = item.id
            this.form.cityname = item.nameCn
            // this.form.lat = item.lat
            // this.form.lon = item.lon
        },


        geoSelect(item) {
            var vm = this
            var geocoder = new google.maps.Geocoder()
            geocoder.geocode({'placeId': item.place_id}, function(results, status) {  
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results.length == 1) {
                        console.log('two')
                        vm.map.setCenter(results[0].geometry.location)
                        vm.map.setZoom(15)

                        vm.form.lat = results[0].geometry.location.lat()
                        vm.form.lon = results[0].geometry.location.lng()
                        vm.form.address = results[0].formatted_address

                        console.log(vm.form.lat)

                        var _marker = new google.maps.Marker({
                            map: vm.map,
                            // icon: icon,
                            // title: place.name,
                            position: results[0].geometry.location,
                            draggable: true,
                        })

                        google.maps.event.addListener(_marker, 'dragend', function(event) {
                            geocoder.geocode({'location': event.latLng}, function(results, status) {  
                                if (status == google.maps.GeocoderStatus.OK) {
                                    if (results[0]) {
                                        vm.form.lat = results[0].geometry.location.lat()
                                        vm.form.lon = results[0].geometry.location.lng()

                                        vm.form.address = results[0].formatted_address
                                    }
                                }
                            })
                            vm.map.panTo(event.latLng)
                        })
                    }
                } else {
                    
                }
            });
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

        collectionTypeFormat(type){
            if(type == 1){
                return '<i class="iconPoi"></i>'
            }else if(type == 2){
                return '<i class="iconRes"></i>'
            }
        },

        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        newPoi(){
            this.dialogAddVisible = true
            this.form = {
                nameCn: '',
                nameEn: '',
                type: '',
                lat: '',
                lon: '',
                address: '',
                cityId: [],
                cityname: '',
                price: '',
                phone: '',
                url: '',
                imageurl: '',
                openTime: '',
                phone: '',
                timeReference: '',
                trafficInstructions: '',
                addressInstrations: '',
                supplier: '',
                guide: '',
            }
            this.mapInit()
        },

        rowClick(row, event, column){
            this.$router.push({path: '/route/' + row.id})
        },

        formatCityList(ttripNoteSchedules){
            var _cityList = []
            for (var i = 0; i < ttripNoteSchedules.length; i++) {
                for (var j = 0; j < ttripNoteSchedules[i].citys.length; j++) {
                    _cityList.push(ttripNoteSchedules[i].citys[j].nameCn)
                }
            }
            return _cityList.join(' / ')
        },

        formatCustomer(customers){
            var _personList = [];
            for (var i = 0; i < customers.length; i++) {
                _personList.push(customers[i].name)
            }
            return _personList.join('<br/>')
        },

        // 获取POI列表
        getCollectionList(){
            var vm = this
            this.tableDataLoading = true

            var _data = {
                pageNo: this.pageNo,
                pageSize: this.pageSize,
                isMy: true
            }

            if(this.filterOptsSelId != -1){
                _data['type'] = this.filterOptsSelId
            }

            this.$http({
                method: 'POST',
                url: '/user/collection/doSearch',
                data: _data
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
                    this.poiList = res.data.data.data
                    this.total = res.data.data.total
                }
            })
        },

        // 获取POI列表
        getCollectionListSys(){
            var vm = this
            this.tableDataLoadingSys = true

            var _data = {
                pageNo: this.pageNoSys,
                pageSize: this.pageSizeSys,
                isMy: false
            }

            if(this.filterOptsSelIdSys != -1){
                _data['type'] = this.filterOptsSelIdSys
            }

            this.$http({
                method: 'POST',
                url: '/poi/doSearch',
                data: _data
            })
            .then((res)=>{
                vm.tableDataLoadingSys = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.poiListSys = res.data.data.data
                    this.totalSys = res.data.data.total
                }
            })
        },

        handleCurrentChange(val){
            this.pageNo = val
            this.getCollectionList()
        },

        handleCurrentChangeSys(val){
            this.pageNoSys = val
            this.getCollectionListSys()
        },

        formatter(row, column) {
            return row.address;
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

        addPoiSubmit(){
            var _url = this.poiStatus == 'add' ? '/poi/doAdd' : '/poi/doUpdate/' + this.form.id

            delete this.form.price

            this.$http({
                method: 'POST',
                url: _url,
                data: this.form
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.dialogAddVisible = false
                    this.getCollectionList()
                }
            })
        },

        delConfirm(id){
            this.dialogDelTip = true
            this.delPOIId = id
        },

        delSubmit(){
            this.$http({
                method: 'POST',
                url: '/user/collection/doDelete/' + this.delPOIId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.dialogDelTip = false
                    this.$message({
                        message: '删除成功！',
                        type: 'success',
                        duration: 2000
                    })
                    this.getCollectionList()
                }
            })
        },

        handleAvatarSuccess(res, file) {
            this.form.imageurl = res.link
        },

        routeImgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },
    },

    destroyed(){
        Bus.$off('headerSearchCollection')
    }
}
</script>
<style lang="less" scope>
.poiTypeFilter{
    float: left;
    margin-right: 30px;
}

.mainCollectList{
    .tableList{
        overflow: hidden;
        margin-right: -30px;
        li{
            float: left;
            width: 25%;
            margin-bottom: 30px;
            .inside{
                cursor: pointer;
                margin-right: 30px;
                border: 1px solid #eee;
                &:hover{
                    border-color: #e2e2e2;
                }
                .pic{
                    overflow: hidden;
                    .img{
                        width: 100%;
                        height: 0;
                        padding-bottom: 56.25%;
                        background-size: cover;
                        background-position: center;
                        transition: all 0.3s;
                        &:hover{
                            transform: scale(1.2);
                        }
                    }
                }
                .info{
                    position: relative;
                    height: 64px;
                    padding: 10px;
                    .tit{
                        width: 100%;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                        font-size: 16px;
                        span{
                            color: #23a16d;
                        }
                    }
                    .subTit{
                        // margin-left: 22px;
                        width: 100%;
                        padding-right: 20px;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                        color: #a0abb3;
                    }
                    .del{
                        position: absolute;
                        bottom: 10px;
                        right: 10px;
                        color: #a0abb3;
                        i{
                            font-size: 16px;
                        }
                        &:hover{
                            color: #555;
                        }
                    }
                }
                
            }
        }
    }
    
    .el-pagination{
        padding-top: 0;
    }
    .iconPoi{
        display: inline-block;
        width: 20px;
        height: 20px;
        vertical-align: middle;
        margin-top: -3px;
        background: url(../../assets/images/icon_main_nav.png) 0 -40px
    }
    .iconRes{
        display: inline-block;
        width: 20px;
        height: 20px;
        vertical-align: middle;
        margin-top: -3px;
        background: url(../../assets/images/icon_main_nav.png) 0 -60px
    }
}
.geoInfo{
    height: 300px;
    border: 1px solid #e2e2e2;
    .geoKeyword{
        position: absolute;
        z-index: 100;
        top: 12px;
        left: 12px;
        width: 300px;
    }
    .geoMap{
        width: 100%;
        height: 300px;
    }
}
.poiDetailImg{
    width: 100%;
    height: 0;
    padding-bottom: 56.25%;
    background-size: cover;
    background-position: center;
    border: 1px solid #ddd;
}
.poiDetailForm{
    margin-top: 20px;
    .poiType{
        color: #23a16d;
    }
    .el-form-item__label{
        color: #999;
    }
    .el-form-item{
        margin-bottom: 8px;
    }
}
</style>