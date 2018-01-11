<template>
    <div class="routeCreate">
        <div class="topBar">
            <router-link to="/main/route" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">{{routeName}}</div>
            </div>

            <div class="btns">
                <el-button>取 消</el-button>
                <el-button type="primary" >发 布</el-button>
            </div>
        </div>

        <div class="dayList">
            <div class="header">
                <div class="tit">行程路线</div>
                <div class="opts">
                    <el-button size="small" type="primary" round @click="setRouteLine" v-if="!setRouteLineActive">
                        <i class="iconfont icon-bianji-blue"></i>
                        安排路线
                    </el-button>
                </div>
                <!-- <a href="javascript:;" class="setRoute" >安排路线</a> -->
            </div>
            
            <div class="content">
                <ul>
                    <router-link :to="{path: '/route/' + routeId + '/overview'}" tag="li" class="summary" v-if="!setRouteLineActive">
                        线路总览
                    </router-link>

                    <router-link tag="li" v-for="(value, index) in scheduleInfo" :to="{path: '/route/' + routeId + '/day/' + value.id}">
                        <div class="day">D{{value.indexdate}}</div>
                        <div class="date" v-if="!setRouteLineActive">{{formatDateWeek(startDate, index)}}</div>
                        <div class="site">{{value.title}}</div>
                        <div class="del" @click.stop="delRouteDayConfirm(value.id)" v-if="setRouteLineActive">
                            <i class="iconfont icon-cha"></i>
                        </div>
                        <div class="addDay" v-if="setRouteLineActive">
                            <el-button size="mini" round icon="el-icon-plus" @click.stop="addRouteDay(value.id,value.indexdate)">插入一天</el-button>
                        </div>
                    </router-link>

                    <router-link :to="{path: '/route/' + routeId + '/remarks'}" tag="li" class="summary" v-if="!setRouteLineActive">
                        行程备注
                    </router-link>
                </ul>
            </div>
        </div>

        <router-view></router-view>


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
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'

export default {
    mixins: [ FormatDateWeek ],
    data() {
        return {
            dialogAddVisible: false,
            routeName: '',
            scheduleInfo: [],
            startDate: '',
            dialogDelDayTip: false,
            delRouteDayId: '',
        }
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive'
        }),
        routeId(){
            if(this.routeInfo.id){
                return this.routeInfo.id
            }else{
                var _path = this.$route.path
                return _path.split('/')[2]
            }
        }
    },
    created(){
        this.getRouteInfo()
    },

    // watch: {
    //     setRouteLineActive(value){
    //         if(value){
    //             this.initMap()
    //         }
    //     }
    // },

    methods: {
        setRouteLine(){
            // this.setRouteLineActive = !this.setRouteLineActive
            this.$store.dispatch('setRouteLineActive', !this.setRouteLineActive)
        },

        getRouteInfo(){
            var _routeId = this.routeId
            this.$http({
                method: 'get',
                url: '/tripnote/tripnote/doDetail/' + _routeId,
            })
            .then((res)=>{
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

                    this.$store.dispatch('setRouteInfo', {routeOverview: res.data.data.introduction})
                }
            })
        },

        delRouteDayConfirm(id){
            this.delRouteDayId = id
            this.dialogDelDayTip = true
        },

        delRouteDay(){
            this.$http({
                method: 'get',
                url: '/tripnote/tripnote/schedule/doDelete/' + this.delRouteDayId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.dialogDelDayTip = false
                    this.$message({
                        message: '删除成功！',
                        type: 'success',
                        duration: 2000
                    })

                    this.getRouteInfo()
                }
            })
        },


        addRouteDay(id, indexdate){

        }




        
    },
}
</script>
<style lang="less" scope>
.routeCreate{
    .topBar{
        height: 60px;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #DADEE5;
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
            }
        }
        .btns{
            float: right;
            margin: 12px 20px;
            .el-button{
                height: 36px;
                padding: 5px 35px;
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
        position: absolute;
        top: 80px;
        bottom: 20px;
        left: 20px;
        width: 260px;
        .header{
            height: 55px;
            border-bottom: 1px solid #EAEDF1;
            background: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,.1);
            .tit{
                float: left;
                height: 55px;
                padding-left: 38px;
                line-height: 55px;
                font-size: 16px;
                background: url(../../assets/images/icon_map_point.png) 15px center no-repeat;
            }
            .opts{
                float: right;
                margin-right: 15px;
                padding-top: 12px;
                .el-button.is-round{
                    height: 30px;
                    padding: 0 12px;
                    line-height: 28px;
                    font-size: 12px;
                    i{
                        float: left;
                        margin-right: 5px;
                        font-size: 12px;
                    }
                }
            }
        }
        .content{
            z-index: 1;
            position: absolute;
            top: 55px;
            left: 0;
            bottom: 0;
            width: 260px;
            overflow-y: auto;
            background: #fff;
            box-shadow: 0 3px 10px rgba(0,0,0,.1);

            li{
                position: relative;
                height: 100px;
                padding: 12px 15px;
                border-bottom: 1px solid #EBEEF2;
                color: #666;
                cursor: pointer;
                .day{
                    float: left;
                    line-height: 36px;
                    font-size: 36px;
                }
                .date{
                    float: right;
                    font-size: 12px;
                    color: #A1ACB3;
                }
                .site{
                    clear: both;
                    padding-top: 12px;
                    font-size: 16px;
                }
                .del{
                    position: absolute;
                    top: 10px;
                    right: 15px;
                    i{
                        font-size: 24px;
                        color: #D7DEE2;
                        &:hover{
                            color: #F56C6C;
                        }
                    }
                }
                .addDay{
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
            }

            li.summary{
                height: 55px;
                line-height: 32px;
                font-size: 16px;
            }
            li.active{
                background: #F7F8F9;
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
</style>