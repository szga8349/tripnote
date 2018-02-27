<template>
    <div>
        <div class="columnBox daySchedule" :class="{active: setDayScheduleActive, setDayPoiEditActive: setDayPoiEditActive}">
            <div class="header">
                <div class="tit"><i class="iconfont icon-rili"></i>日程安排</div>
                <div class="opts">
                    <a href="javascript:;" @click="setSchedule" class="actionBtn" v-if="!setDayScheduleActive">
                        <i class="iconfont icon-bianji-blue"></i>
                        <span>安排日程</span>
                    </a>
                </div>
            </div>

            <div class="content">
                <div class="dayCityList">
                    <div class="item" v-for="(item, index) in dayInfo.citys">
                        <span class="cityName">
                            {{item.nameCn}}
                            <!-- <span class="delCity" @click="delDayCity(item.id)">
                                <i class="iconfont icon-shanchu1"></i>
                            </span> -->
                        </span>
                        <span class="arrow">
                            <i class="iconfont icon-jiantou02"></i>
                        </span>
                    </div>
                </div>

                <div class="scheduleList">
                    <div class="scheduleCon" :class="{singleItem: (dayInfo.scheduleHotels.length)+(dayInfo.scheduletrips.length) == 1}">
                        <div class="itemWrap" v-for="(item, index) in dayInfo.scheduleHotels" v-if="item.checkInType == 0">
                            <div class="item poi" @click="poiDetail(item)">
                                <div class="no noHotel">
                                    <i class="iconfont icon-sun"></i>
                                </div>
                                <div class="con">
                                    <div class="tit">
                                        <i class="iconfont icon-chuangwei"></i>
                                        {{item.nameCn}}
                                    </div>
                                    <div class="titEn">{{item.nameEn}}</div>
                                    <div class="price" v-if="item.price">￥{{item.price}}</div>
                                </div>
                                <div class="del" @click.stop="delScheduleHotel(item)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>
                            
                            <div class="item traffic" :class="{active:trafficId && traffics[item.id].id == trafficId}"
                                @click="goTrafficDetail('start', traffics[item.id].id)" v-if="traffics[item.id]"
                            >
                                <span v-html="trafficsTypeForamt(traffics[item.id].trafficType)"></span>
                                <span v-if="traffics[item.id].trafficType<4">{{trafficsTimeFormat(traffics[item.id].spendTime)}}</span>
                                <span v-if="traffics[item.id].trafficType>=4">{{trafficsNameFormat(traffics[item.id].trafficType)}} {{traffics[item.id].shift}}</span>


                                <div class="del" @click.stop="delScheduleTraffic(traffics[item.id].id)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>

                            <div class="item traffic"
                                v-if="setDayScheduleActive && !traffics[item.id]"
                                :class="{active:poiStartId && poiStartId == item.id + '_' + item.type}"
                                @click="addTraffic(item, 'start')"
                            >
                                <i class="el-icon-plus"></i>添加交通信息
                            </div>
                        </div>

                        <div class="itemWrap" v-for="(item, index) in dayInfo.scheduletrips">
                            <div class="item poi" :class="{active:poiId==item.poiId, product:item.type==5, hotel:item.type==6}" @click="poiDetail(item)">
                                <div class="no">{{(index+1)}}</div>
                                <div class="con">
                                    <div class="tit">
                                        <span v-html="poiTypeFormat(item.type)"></span>
                                        {{item.nameCn}}
                                    </div>
                                    <div class="titEn">{{item.nameEn}}</div>
                                    <div class="price" v-if="item.price">￥{{item.price}}</div>
                                </div>
                                <div class="del" @click.stop="delSchedule(item)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>
                            
                            <div class="item traffic" :class="{active:trafficId && traffics[item.id].id == trafficId}"
                                @click="goTrafficDetail(index, traffics[item.id].id)" v-if="traffics[item.id]"
                            >
                                <span v-html="trafficsTypeForamt(traffics[item.id].trafficType)"></span>
                                <span v-if="traffics[item.id].trafficType<4">{{trafficsTimeFormat(traffics[item.id].spendTime)}}</span>
                                <span v-if="traffics[item.id].trafficType>=4">{{trafficsNameFormat(traffics[item.id].trafficType)}} {{traffics[item.id].shift}}</span>

                                <div class="del" @click.stop="delScheduleTraffic(traffics[item.id].id)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>

                            <div class="item traffic"
                                v-if="setDayScheduleActive && !traffics[item.id]"
                                :class="{active:poiStartId && poiStartId == item.id + '_' + item.type}"
                                @click="addTraffic(index)"
                            >
                                <i class="el-icon-plus"></i>添加交通信息
                            </div>
                        </div>

                        <div class="itemWrap" v-for="(item, index) in dayInfo.scheduleHotels" v-if="item.checkInType == 1">
                            <div class="item poi" @click="poiDetail(item)">
                                <div class="no noHotel">
                                    <i class="iconfont icon-wanshangchangci"></i>
                                </div>
                                <div class="con">
                                    <div class="tit">
                                        <i class="iconfont icon-chuangwei"></i>
                                        {{item.nameCn}}
                                    </div>
                                    <div class="titEn">{{item.nameEn}}</div>
                                    <div class="price" v-if="item.price">￥{{item.price}}</div>
                                </div>
                                <div class="del" @click.stop="delScheduleHotel(item)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>
                            
                            <div class="item traffic" :class="{active:trafficId && traffics[item.id].id == trafficId}"
                                @click="goTrafficDetail(index, traffics[item.id].id)" v-if="traffics[item.id]"
                            >
                                <span v-html="trafficsTypeForamt(traffics[item.id].trafficType)"></span>
                                <span v-if="traffics[item.id].trafficType<4">{{trafficsTimeFormat(traffics[item.id].spendTime)}}</span>
                                <span v-if="traffics[item.id].trafficType>=4">{{trafficsNameFormat(traffics[item.id].trafficType)}} {{traffics[item.id].shift}}</span>


                                <div class="del" @click.stop="delScheduleTraffic(traffics[item.id].id)" v-if="setDayScheduleActive">
                                    <i class="iconfont icon-cha"></i>
                                </div>
                            </div>

                            <div class="item traffic"
                                v-if="setDayScheduleActive && !traffics[item.id]"
                                :class="{active:poiStartId && poiStartId == item.id}"
                                @click="addTraffic(index, 'end')"
                            >
                                <i class="el-icon-plus"></i>添加交通信息
                            </div>
                        </div>
                    </div>

                    <div class="nullTip" v-if="dayInfo.scheduletrips.length == 0 && dayInfo.scheduleHotels.length == 0">
                        <div class="icon"></div>
                        <p v-if="dayInfo.citys.length == 0">请先选择这天要游览的城市</p>
                        <p v-if="dayInfo.citys.length > 0">暂时还没有安排，开始添加吧</p>
                        <el-button size="small" round icon="el-icon-plus" @click="setSchedule" v-if="dayInfo.citys.length > 0 && !setDayScheduleActive">添加产品</el-button>
                    </div>
                </div>
            </div>
        </div>

        <!-- <div class="columnWrap notes day" > -->
            <div class="columnBox notes day" v-if="!setDayScheduleActive" :class="{poiActive: poiId || trafficId}">
                <div class="header">
                    <div class="tit"><i class="iconfont icon-wenjian"></i>行程介绍</div>
                    <div class="opts">
                        <a href="javascript:;" @click="isEdit=true" class="actionBtn" v-if="!isEdit">
                            <i class="iconfont icon-bianji-blue"></i>
                            <span>编辑</span>
                        </a>
                        <div class="moreBtn" v-if="isEdit">
                            <el-button round size="medium" @click="isEdit=false">取消</el-button>
                            <el-button type="primary" round size="medium" @click="saveDayIntro">保存</el-button>
                        </div>
                    </div>
                </div>

                <div class="content" v-if="!isEdit" v-html="dayInfo.introduction"></div>
                <div class="content" v-if="isEdit">
                    <froala :tag="'textarea'" :config="config" v-model="dayInfo.introduction"></froala>
                </div>
            </div>

            <!-- <div class="columnBox">
                <div class="header">
                    <div class="tit"><i class="iconfont icon-bianji"></i>定制师笔记</div>
                    <div class="opts">
                        <a href="javascript:;" class="actionBtn">
                            <i class="iconfont icon-tianjia2"></i>
                            <span>添加</span>
                        </a>
                    </div>
                </div>

                <div class="content">
                </div>
            </div> -->
        <!-- </div> -->

        <router-view></router-view>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            config: {
                events: {
                    'froalaEditor.initialized': function() {
                        // var temp = document.getElementsByTagName("a");
                        // var i = 0;
                        // for (i = 0; i < temp.length; i++) {
                        //     if (temp[i].href == "https://www.froala.com/wysiwyg-editor?k=u") {
                        //         $(temp[i]).parent().remove()
                        //         // temp[i].parentNode.removeChild(temp[i].parentNode.childNodes[0]);
                        //     }
                        // }
                    },
                    'froalaEditor.image.error': function (e, editor, error, response){
                        console.log(error)
                    },
                },
                // heightMin: 300,
                // heightMax: 500,
                charCounterCount: false,
                quickInsertTags: [],
                placeholderText: '请输入内容',
                toolbarButtons: ['bold', 'italic', 'underline', 'fontSize', 'color', '|', 'paragraphFormat', 'align', 'formatOL', 'outdent', 'indent', '|', 'quote', 'insertLink', 'insertImage', 'insertHR'],
                imageUploadURL: '/tripnote/common/upload/schedule/image'
            },
            isEdit: false,
            dayInfo: {
                indexdate: '',
                introduction: '',
                scheduletrips: [],
                scheduleHotels: [],
                title: '',
                traffics: {},
                tripnoteId: '',
                usertripnotes: '',
                citys: [],
            },

            traffics: {}
        }
    },
    
    watch: {
        dayId(val){
            // this.$store.dispatch('setDayScheduleActive', false)
            this.getDayDetail()
        },
    },
    created(){
        this.getDayDetail()

        var vm = this
        Bus.$on('refreshSchedule', function(item){
            vm.getDayDetail()
        })
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive',
            setDayPoiEditActive: 'setDayPoiEditActive',
            setDayScheduleActive: 'setDayScheduleActive'
        }),
        dayId(){
            return this.$route.params.dayId
            // if(this.routeInfo.daySel){
            //     return this.routeInfo.daySel
            // }else{
            //     var _path = this.$route.path

            //     console.log(this.$route)

            //     return _path.split('/')[4] ? parseInt(_path.split('/')[4]) : -1
            // }
        },
        trafficId(){
            return this.$route.params.trafficId
        },

        poiStartId(){
            return this.$route.params.poiStartId
        },

        poiId(){
            return this.$route.params.poiId
        }

    },
    methods: {
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
        trafficsTimeFormat(time){
            return parseInt(time/60) + 'm'
        },

        trafficsNameFormat(type){
            if(type == 4){
                return '飞机'
            }else if(type == 5){
                return '火车'
            }else if(type == 6){
                return '轮渡'
            }else if(type == 7){
                return '巴士'
            }else if(type == 8){
                return '地铁'
            }
        },

        poiDetail(item){
            if(this.setDayScheduleActive){
                this.$store.dispatch('setDayPoiEditActive', true)
            }

            if(item.type == 6){
                this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/hotel/' + item.id + '/' + item.hotelId})
            }else{
                this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/poi/' + item.id + '/' + item.poiId})
            }
        },

        addTraffic(index, type){
            this.$store.dispatch('setDayPoiEditActive', true)

            var _start, _end;
            if(type){
                if(type == 'start'){
                    _start = index.id + '_' + index.type
                    _end = this.dayInfo.scheduletrips[0].id + '_' + this.dayInfo.scheduletrips[0].type
                }
            }else{
                if(index == this.dayInfo.scheduletrips.length - 1){
                    var _index = this.dayInfo.scheduletrips.length - 1
                    _start = this.dayInfo.scheduletrips[_index].id + '_' + this.dayInfo.scheduletrips[_index].type
                    _end = this.dayInfo.scheduleHotels[this.dayInfo.scheduleHotels.length - 1].id + '_' + this.dayInfo.scheduleHotels[this.dayInfo.scheduleHotels.length - 1].type
                }else{
                    _start = this.dayInfo.scheduletrips[index].id + '_' + this.dayInfo.scheduletrips[index].type
                    _end = this.dayInfo.scheduletrips[index+1].id + '_' + this.dayInfo.scheduletrips[index+1].type
                }
            }

            this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/traffic/' + _start + '/' + _end})
        },

        goTrafficDetail(index, id){
            if(this.setDayScheduleActive){
                this.$store.dispatch('setDayPoiEditActive', true)
            }

            var _start, _end;

            if(index == 'start'){
                _start = this.dayInfo.scheduleHotels[0].id + '_' + this.dayInfo.scheduleHotels[0].type
                _end = this.dayInfo.scheduletrips[0].id + '_' + this.dayInfo.scheduletrips[0].type
            }else {
                if(index == this.dayInfo.scheduletrips.length - 1){
                    _start = this.dayInfo.scheduletrips[index].id + '_' + this.dayInfo.scheduletrips[index].type
                    var _hl = this.dayInfo.scheduleHotels.length
                    _end = this.dayInfo.scheduleHotels[_hl - 1].id + '_' + this.dayInfo.scheduleHotels[_hl - 1].type
                }else{
                    _start = this.dayInfo.scheduletrips[index].id + '_' + this.dayInfo.scheduletrips[index].type
                    _end = this.dayInfo.scheduletrips[index+1].id + '_' + this.dayInfo.scheduletrips[index+1].type
                }
            }

            this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId + '/traffic/' + _start + '/' + _end + '/' + id})
        },

        setSchedule(){
            this.$store.dispatch('setDayScheduleActive', true)

            this.$router.push({ name: 'schedule'})
        },

        getDayDetail(){
            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doDeail/' + this.dayId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.dayInfo = res.data.data

                    var _traffics = res.data.data.traffics

                    var _trafficsObj = {}

                    for (var i = 0; i < _traffics.length; i++) {
                        _trafficsObj[_traffics[i].startScheduleTrip] = _traffics[i]
                    }
                    this.traffics = _trafficsObj

                    this.$store.dispatch('setDayInfo', res.data.data)
                }
            })
        },

        saveDayIntro(){
            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doUpdate/' + this.dayId,
                data: {
                    introduction: this.dayInfo.introduction
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
                    this.$message({
                        message: '保存成功！',
                        type: 'success',
                        duration: 2000
                    })
                    this.isEdit = false
                }
            })
        },

        delDayCity(){

        },




        delSchedule(item){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletrip/doDelete/' + item.id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.getDayDetail()
                }
            })
        },

        delScheduleHotel(item){
            this.$http({
                method: 'post',
                url: '/tripnote/schedulehotel/doDelete/' + item.id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.getDayDetail()
                }
            })
        },


        delScheduleTraffic(id){

            this.$http({
                method: 'post',
                url: '/tripnote/scheduletraffic/doDelete/' + id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.getDayDetail()

                }
            })
        }
    },
}
</script>
<style lang="less" scope>
.daySchedule{
    width: 300px;
    top: 80px;
    bottom: 20px;
    left: 300px;
    transition: all .3s ease;
    &.active{
        left: 180px;
    }
    &.setDayPoiEditActive{
        left: 20px;
    }
    .nullTip{
        padding-bottom: 30px;
        text-align: center;
        .icon{
            width: 75px;
            height: 75px;
            margin: 100px auto 20px;
            background: url(../../assets/images/icon_point.png);
        }
        p{
            margin-bottom: 40px;
            color: #DADFE3;
        }
        .el-button{
            width: 150px;
            border-color: #23a16d;
            color: #23a16d;
            i{
                font-weight: bold;
            }
        }
    }
}

.dayCityList{
    min-height: 51px;
    padding: 0 15px;
    border-bottom: 1px solid #EAEDF1;
    line-height: 50px;
    .item{
        float: left;
        .cityName{
            float: left;
            position: relative;
            font-size: 14px;
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
.scheduleList{
    position: absolute;
    top: 51px;
    bottom: 0;
    left: 0;
    right: 0;
    overflow-y: auto;
    .scheduleCon{
        position: relative;
        &.singleItem{
            .itemWrap{
                .item{
                    &:before{
                        display: none;
                    }
                }
            }
        }
        .itemWrap{
            &:first-child{
                .item{
                    &:before{
                        top: 45px;
                    }
                }
            }
            &:last-child{
                .item{
                    &:before{
                        height: 20px;
                    }
                    &.traffic{
                        display: none;
                    }
                    &.product{
                        .no{
                            background: #AFA37A;
                        }
                        .con{
                            .tit{
                                i{
                                    color: #AFA37A;
                                }
                            }
                        }
                    }
                    &.hotel{
                        .no{
                            background: blue;
                        }
                    }
                }
            }
            .item{
                position: relative;
                padding: 10px 15px;
                overflow: hidden;
                border-bottom: 1px solid #EAEDF1;
                &:before{
                    content: '';
                    position: absolute;
                    left: 33px;
                    top: 0;
                    width: 0;
                    height: 100%;
                    border-left: 1px dashed #DBE0E3;
                }
                &:hover{
                    background: #F7F8F9;
                    .del{
                        display: block;
                    }
                }
                .no{
                    position: absolute;
                    top: 18px;
                    left: 18px;
                    width: 30px;
                    height: 30px;
                    border-radius: 15px;
                    background: #23a16d;
                    line-height: 30px;
                    color: #fff;
                    text-align: center;
                    font-size: 16px;
                }
                .noHotel{
                    background: none;
                    color: #23a16d;
                    i{
                        font-size: 24px;
                    }
                }
                .con{
                    margin-left: 48px;
                    cursor: pointer;
                    .tit{
                        padding: 5px 0;
                        i{
                            color: #23a16d;
                        }
                    }
                    .titEn{
                        padding: 5px 0;
                        color: #777;
                    }
                    .price{
                        padding: 5px 0;
                    }
                }
                .del{
                    display: none;
                    position: absolute;
                    top: 50%;
                    right: 15px;
                    width: 20px;
                    height: 20px;
                    margin-top: -10px;
                    line-height: 20px;
                    color: #D7DEE2;
                    cursor: pointer;
                    i{
                        font-size: 22px;
                    }
                    &:hover{
                        color: #F56C6C;
                    }
                }
                
                &.traffic{
                    &:before{
                        display: none;
                    }
                    z-index: 1;
                    background: #fff;
                    padding-left: 27px;
                    cursor: pointer;
                    i{
                        margin-right: 5px;
                    }
                    &:hover{
                        background: #F7F8F9;
                    }
                }
                &.active{
                    background: #F7F8F9;
                }
            }
        }
        
    }
}

.notes{
    &.day{
        left: 620px;
        &.poiActive{
            &:before{
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(0,0,0,.02);
            }
        }
        
    }
}

</style>