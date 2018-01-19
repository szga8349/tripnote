<template>
    <div>
        <div class="columnBox dayScheduleSet">
            <div class="header">
                <el-tabs class="tabs" v-model="activeName" @tab-click="changeTabs">
                    <el-tab-pane label="POI" name="first">POI</el-tab-pane>
                    <el-tab-pane label="产品" name="second">产品</el-tab-pane>
                    <el-tab-pane label="酒店" name="third">酒店</el-tab-pane>
                    <el-tab-pane label="交通" name="fourth">交通</el-tab-pane>
                </el-tabs>

                <div class="opts">
                    <a href="javascript:;" class="closeBox" @click="closeBox">
                        <i class="iconfont icon-guanbi"></i>
                    </a>
                </div>
            </div>

            <div class="content">
                <div class="poiDetail">
                    <div class="head">
                        <div class="tit">乌鲁木齐</div>
                        <el-input
                            placeholder="搜索POI"
                            prefix-icon="el-icon-search"
                            >
                        </el-input>
                    </div>
                    <div class="poiList">
                        <ul>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                            <li>
                                <div class="pic"><img src="../../assets/images/poi_pic.png"></div>
                                <div class="detail">
                                    <h3>乌鲁木齐博物馆</h3>
                                    <div class="subTit">The Ulumuqi xxx</div>
                                    <div class="price">￥60</div>
                                </div>
                                <div class="add">
                                    <i class="iconfont icon-add"></i>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="googleMap" id="map"></div>
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
            activeName: 'second'
        }
    },
    
    watch: {
        // dayId(val){
        //     for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
        //         if(this.routeInfo.dayInfo[i].id == this.dayId){
        //             this.cityInfo = this.routeInfo.dayInfo[i].citys
        //         }
        //     }
        // },
    },
    created(){
        // this.getDayDetail()
        if(!this.setDayScheduleActive){
            this.$store.dispatch('setDayScheduleActive', !this.setDayScheduleActive)
        }
    },
    mounted(){
        this.$nextTick(() => {
            this.initMap()
        })
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive',
            setDayScheduleActive: 'setDayScheduleActive'
        }),
        dayId(){
            // if(this.routeInfo.daySel){
            //     return this.routeInfo.daySel
            // }else{

                return this.$route.params.dayId
            // }
        }
    },
    methods: {
        initMap(){
            this.map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 39.920000, lng: 116.460000},
                zoom: 4
            })

        },

        changeTabs(tab, event) {
            console.log(tab.name)
            // this.activeName = tab.name
        },
        closeBox(){
            this.$store.dispatch('setDayScheduleActive', false)
            this.$router.push({ path: '/route/' + this.$route.params.id + '/day/' + this.$route.params.dayId})
        },

        setSchedule(){
            this.$store.dispatch('setDayScheduleActive', !this.setDayScheduleActive)
        },

        getDayDetail(){
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doDeail/' + this.dayId,
                data: {
                    introduction: this.routeInfo.intro
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
                    this.dayInfo = res.data.data

                    console.log(this.dayInfo)
                }
            })
        },

        saveDayIntro(){
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/schedule/doUpdate/' + this.dayId,
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

        }
    },
}
</script>
<style lang="less" scope>
.dayScheduleSet{
    top: 80px;
    bottom: 20px;
    left: 500px;
    right: 20px;
    transition: all .3s ease;
    &.active{
        left: 180px;
    }
    .header{
        .tabs{
            float: left;
            margin-left: -15px;
            .el-tabs__nav-wrap::after{
                display: none;
            }
            .el-tabs__content{
                display: none;
            }
            .el-tabs__item{
                height: 55px;
                padding: 0 25px;
                font-size: 16px;
                line-height: 54px;
                &.is-active{
                    color: #23a16d;
                }
            }
            .el-tabs__active-bar{
                padding: 0 25px;
                margin-left: -25px;
                box-sizing: content-box;
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
    

    .poiDetail{
        width: 300px;
        .head{
            height: 50px;
            line-height: 50px;
            padding: 0 15px;
            border-bottom: 1px solid #EAEDF1;
            .tit{
                float: left;
                font-size: 14px;
            }
            .el-input{
                float: right;
                width: 130px;
            }
        }
        .poiList{
            position: absolute;
            top: 51px;
            bottom: 0;
            left: 0;
            width: 300px;
            overflow-y: auto;
            li{
                position: relative;
                overflow: hidden;
                margin: 15px;
                border: 1px solid #EAEDF1;
                transition: all .2s ease;
                &:hover{
                    -webkit-box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    -moz-box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    transform: scale(1.03);
                    -ms-transform: scale(1.03);
                    -moz-transform: scale(1.03);
                    -webkit-transform: scale(1.03);
                    -o-transform: scale(1.03);
                }
                .pic{
                    float: left;
                    width: 88px;
                    height: 88px;
                    img{
                        display: block;
                        width: 88px;
                        height: 88px;
                    }
                }
                .detail{
                    float: left;
                    width: 130px;
                    margin-left: 10px;
                    h3{
                        margin-top: 5px;
                        line-height: 20px;
                        font-size: 14px;
                    }
                    .subTit{
                        font-size: 12px;
                        color: #9CA7AF;
                    }
                    .price{
                        margin-top: 15px;
                        font-size: 14px;
                        color: #23a16d;
                    }
                }
                .add{
                    position: absolute;
                    top: 50%;
                    right: 15px;
                    margin-top: -15px;
                    width: 28px;
                    height: 28px;
                    line-height: 30px;
                    cursor: pointer;
                    i{
                        opacity: 0.8;
                        font-size: 28px;
                        color: #23a16d;
                    }
                    &:hover{
                        i{
                            opacity: 1;
                        }
                    }

                }
            }
        }
    }

    .googleMap{
        position: absolute;
        top: 0;
        left: 300px;
        right: 0;
        bottom: 0;
        border-left: 1px solid #EAEDF1;
    }
    
}


</style>