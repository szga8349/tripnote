<template>
    <div>
        <div class="columnBox daySchedule" :class="{active: setDayScheduleActive}">
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
                            <span class="delCity" @click="delDayCity(item.id)">
                                <i class="iconfont icon-shanchu1"></i>
                            </span>
                        </span>
                        <span class="arrow">
                            <i class="iconfont icon-jiantou02"></i>
                        </span>
                    </div>
                </div>

                <div class="scheduleList">
                    <div class="scheduleCon">
                        <div class="line"></div>
                        <div class="item poi">
                            <div class="no">1</div>
                            <div class="con">
                                <p><label>景点：</label>乌鲁木齐博物馆</p>
                                <p><label>门票：</label>30元</p>
                                <p><label>景点：</label>3小时30分钟</p>
                            </div>
                            <div class="del"><i class="iconfont icon-cha"></i></div>
                        </div>

                        <div class="item traffic">
                            <i class="el-icon-plus"></i>添加交通信息
                        </div>

                        <div class="item poi">
                            <div class="no">2</div>
                            <div class="con">
                                <p><label>景点：</label>乌鲁木齐博物馆</p>
                                <p><label>门票：</label>30元</p>
                                <p><label>景点：</label>3小时30分钟</p>
                            </div>
                            <div class="del"><i class="iconfont icon-cha"></i></div>
                        </div>

                        <div class="item traffic">
                            <i class="el-icon-plus"></i>添加交通信息
                        </div>

                        <div class="item poi">
                            <div class="no">3</div>
                            <div class="con">
                                <p><label>景点：</label>乌鲁木齐博物馆</p>
                                <p><label>门票：</label>30元</p>
                                <p><label>景点：</label>3小时30分钟</p>
                            </div>
                            <div class="del"><i class="iconfont icon-cha"></i></div>
                        </div>

                        <div class="item traffic">
                            <i class="el-icon-plus"></i>添加交通信息
                        </div>

                        <div class="item poi">
                            <div class="no">4</div>
                            <div class="con">
                                <p><label>景点：</label>乌鲁木齐博物馆</p>
                                <p><label>门票：</label>30元</p>
                                <p><label>景点：</label>3小时30分钟</p>
                            </div>
                            <div class="del"><i class="iconfont icon-cha"></i></div>
                        </div>
                    </div>


                    <div class="nullTip">
                        <div class="icon"></div>
                        <p>请先选择这天要游览的城市</p>
                        <el-button size="small" round icon="el-icon-plus">添加地点或产品</el-button>
                    </div>
                </div>
            </div>
        </div>

        <div class="columnWrap notes day" v-if="!setDayScheduleActive">
            <div class="columnBox">
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
                <div v-if="isEdit">
                    <froala :tag="'textarea'" :config="config" v-model="dayInfo.introduction"></froala>
                </div>
            </div>

            <div class="columnBox">
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
            </div>
        </div>

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
                        
                    },
                    'froalaEditor.image.error': function (e, editor, error, response){
                        console.log(error)
                    },
                },
                heightMin: 300,
                heightMax: 500,
                placeholderText: '请输入内容',
                toolbarButtons: ['bold', 'italic', 'underline', 'fontSize', 'color', '|', 'paragraphFormat', 'align', 'formatOL', 'outdent', 'indent', '|', 'quote', 'insertLink', 'insertImage', 'insertHR'],
                imageUploadURL: '/tripnote/common/upload/schedule/image'
            },
            isEdit: false,
            dayInfo: {
                indexdate: '',
                introduction: '',
                scheduletrips: '',
                title: '',
                traffics: '',
                tripnoteId: '',
                usertripnotes: '',
            }
        }
    },
    
    watch: {
        dayId(val){
            this.$store.dispatch('setDayScheduleActive', false)
            this.getDayDetail()
        },
    },
    created(){
        this.getDayDetail()
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive',
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
        }
    },
    methods: {
        setSchedule(){
            this.$store.dispatch('setDayScheduleActive', true)

            this.$router.push({ name: 'schedule'})
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
.daySchedule{
    width: 300px;
    top: 80px;
    bottom: 20px;
    left: 300px;
    transition: all .3s ease;
    &.active{
        left: 180px;
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
        .line{
            position: absolute;
            top: 45px;
            bottom: 70px;
            left: 33px;
            width: 0;
            border-left: 1px dashed #DBE0E3;
        }
        .item{
            position: relative;
            padding: 10px 15px;
            overflow: hidden;
            border-bottom: 1px solid #EAEDF1;
            &:hover{
                background: #F7F8F9;
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
            .con{
                margin-left: 48px;
                line-height: 28px;
                label{
                    color: #B0B7BD;
                }
            }
            .del{
                position: absolute;
                top: 50%;
                right: 15px;
                width: 20px;
                height: 20px;
                margin-top: -10px;
                line-height: 20px;
                font-size: 20px;
                color: #D7DEE2;
                cursor: pointer;
                &:hover{
                    color: #F56C6C;
                }
            }
            &.traffic{
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
        }
    }
}




.notes{
    &.day{
       left: 615px; 
    }
}

</style>