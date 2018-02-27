<template>
    <div>
        <div class="mainTopTit"></div>
        <div class="commonBox">
            <div class="commonTit">
                <h2>我的POI库</h2>
                <div class="rightOpts">
                    <Select class="poiTypeFilter" :opts="filterOptsList" :sel="filterOptsSel" nameAlias="name" idAlias="id" align="right"></Select>
                    <el-button type="primary" icon="el-icon-plus" @click="newPoi">新建POI</el-button>
                </div>
            </div>

            <div class="commonCon">
                <div class="mainPoiList">
                    <ul class="tableList">
                        <li v-for="(item, index) in poiList">
                            <div class="inside">
                                <div class="pic">
                                    <div class="img" :style="{backgroundImage: `url(${imgFormat(item.imageurl)})`}"></div>
                                </div>
                                <div class="info">
                                    <div class="tit">
                                        <span v-html="poiTypeFormat(item.type)"></span>
                                        {{item.nameCn}}
                                    </div>
                                    <div class="subTit">{{item.nameEn}}</div>
                                    <el-tooltip placement="bottom">
                                        <div slot="content">删除</div>
                                        <a href="javascript:;" @click.stop="delConfirm(item)" class="del"><i class="el-icon-delete"></i></a>
                                    </el-tooltip>
                                </div>
                            </div>
                        </li>
                    </ul>

                    <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="pageNo"
                        :page-size="8"
                        layout="total, prev, pager, next"
                        :total="total">
                    </el-pagination>
                </div>

                <div v-if="!tableDataLoading && poiList.length == 0" class="addNewRouteTip">
                    <div class="pic" @click="dialogAddVisible = true">
                        <div class="icon"></div>
                        <div class="txt">新建定制</div>
                    </div>
                    <p>您还没有POI，请新建POI</p>
                </div>
            </div>

            <el-dialog title="新建POI" :visible.sync="dialogAddVisible" width="600px">
                <el-form ref="form" :model="form" label-width="70px" class="formAdd">
                    <el-form-item label="中文名称:">
                        <el-input v-model="form.nameCn"></el-input>
                    </el-form-item>

                    <el-form-item label="英文名称:">
                        <el-input v-model="form.nameEn"></el-input>
                    </el-form-item>

                    <el-form-item label="图片:">
                        <div class="routePic" :style="{backgroundImage: `url(${routeImgFormat(form.imageurl)})`}"></div>
                        <el-upload
                            class="uploadRoutePic"
                            action="/tripnote/common/upload/poi/image"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            >
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="类型:">
                        <el-select v-model="form.type" placeholder="请选择">
                            <el-option
                                v-for="item in poiType"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>


                    <el-form-item label="其他语言:">
                        <el-input v-model="form.lang"></el-input>
                    </el-form-item>

                    <el-form-item label="地理位置:">
                        <div class="geoInfo">
                           <!--  <el-input
                                placeholder="请输入地点名称"
                                prefix-icon="el-icon-search"
                                v-model="geoKeyword">
                            </el-input> -->

                            <el-autocomplete
                                class="geoKeyword"
                                prefix-icon="el-icon-search"
                                v-model="geoKeyword"
                                :fetch-suggestions="queryGeoSearch"
                                placeholder="请输入地点名称"
                                :trigger-on-focus="false"
                                @select="geoSelect"
                            ></el-autocomplete>
                            <div class="geoMap" id="GeoMap"></div>
                        </div>
                    </el-form-item>

                    <el-form-item label="地址:">
                        <el-input v-model="form.address"></el-input>
                    </el-form-item>

                    <el-form-item label="所在城市:">
                        <el-autocomplete
                            style="width: 100%"
                            v-model="form.cityName"
                            prefix-icon="el-icon-search"
                            :fetch-suggestions="queryCitySearch"
                            placeholder="请输入所在城市"
                            :trigger-on-focus="false"
                            @select="geoCitySelect"
                        ></el-autocomplete>
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

                    <el-form-item label="用时参考:">
                        <el-input v-model="form.timeReference"></el-input>
                    </el-form-item>

                    <el-form-item label="交通指引:">
                        <el-input
                            type="textarea"
                            :rows="3"
                            v-model="form.trafficInstructions">
                        </el-input>
                    </el-form-item>

                    <el-form-item label="地点简介:">
                        <el-input
                            type="textarea"
                            :rows="3"
                            v-model="form.guide">
                        </el-input>
                    </el-form-item>

                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="dialogAddVisible = false">取 消</el-button>
                    <el-button :disabled="isDisable" type="primary" @click.native="addPoiSubmit">新 建</el-button>
                </div>
            </el-dialog>
        </div>

        <el-dialog title="提示" :visible.sync="dialogDelTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除该POI吗？</p>
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
import { FormatTime } from 'mixins/common'

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
                name: '餐饮'
            },{
                id: 2,
                name: '游览'
            },{
                id: 3,
                name: '购物'
            },{
                id: 4,
                name: '娱乐'
            }],

            filterOptsSel: '全部',

            tableData: [],
            tableDataLoading: true,
            pageNo: 1,
            pageSize: 8,
            total: 0,

            cityName: '',

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
                cityName: '',
                price: '',
                phone: '',
                url: '',
                imageurl: '',
                openTime: '',
                phone: '',
                timeReference: '',
                trafficInstructions: '',
                guide: '',
            },
            dialogAddVisible: false,
            templateVisible: false,
            routeId: '',
            delPOIId: '',
            sortField: 'create_time',
            sortType: -1,

            poiList: []
        }
    },
    created(){
        this.getPoiList()
    },
    methods: {
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
                        console.log(" 地址为：" + results[0].formatted_address)
                        console.log(" id为：" + results[0].place_id)
                        console.log(" id为：" + results[0].geometry.location)
                        console.log(results[0].geometry.location)

                        vm.map.setCenter(results[0].geometry.location)
                        vm.map.setZoom(15)

                        vm.form.address = results[0].formatted_address

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
                                        console.log(" 地址为：" + results[0].formatted_address)

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

                        console.log(results)
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
            this.form.cityName = item.nameCn
            this.form.lat = item.lat
            this.form.lon = item.lon

            console.log(item)
        },


        geoSelect(item) {
            console.log(item);
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
                cityName: '',
                price: '',
                phone: '',
                url: '',
                imageurl: '',
                openTime: '',
                phone: '',
                timeReference: '',
                trafficInstructions: '',
                guide: '',
            }
            this.mapInit()
        },


        sortChange(column, prop, order){
            if(column.prop){
                this.sortField = column.prop
                this.sortType = column.order == 'ascending' ? 1 : -1
                this.getRouteList()
            }
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
        getPoiList(){
            var vm = this
            this.tableDataLoading = true
            this.$http({
                method: 'POST',
                url: '/poi/doSearch',
                data: {
                    pageNo: this.pageNo,
                    pageSize: this.pageSize
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
                    this.poiList = res.data.data.data
                    this.total = res.data.data.total
                }
            })
        },

        handleCurrentChange(val){
            this.pageNo = val
            this.getPoiList()
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
            this.$http({
                method: 'POST',
                url: '/poi/doAdd',
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
                    this.getPoiList()
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
                url: '/poi/doDelete/' + this.delPOIId,
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
                    this.getRouteList()
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
}
</script>
<style lang="less" scope>
.poiTypeFilter{
    float: left;
    margin-right: 30px;
}

.mainPoiList{

    .tableList{
        overflow: hidden;
        margin-right: -30px;
        li{
            float: left;
            width: 25%;
            margin-bottom: 30px;
            .inside{
                margin-right: 30px;
                .pic{
                    overflow: hidden;
                    .img{
                        width: 100%;
                        height: 100px;
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
                    padding: 10px;
                    border: 1px solid #e2e2e2;
                    border-top: 0;
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
</style>