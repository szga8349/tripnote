<template>
<div>
    <div class="routeExportTemplateBg"></div>
    <div class="routeExportTemplate">
        <div class="header">
            <div class="tit">导出模板</div>
            <a href="javascript:;" class="closeBtn" @click="closeTemplate">
                <i class="iconfont icon-guanbi"></i>
            </a>
        </div>

        <div class="templateBox">
            <div class="templateCon">
                <div class="routeNav">
                    <div class="routeNavHeader">
                        <div class="tit">选择导出内容</div>
                        <div class="checkbox">
                            <label>全选</label>
                            <div class="checkboxC" :class="{active: selectAllDay}" v-model="selectAllDay" @click="changeSelectAllDay"></div>
                        </div>
                    </div>
                    <ul>
                        <li v-for="(value, index) in routeInfo.dayInfo" :class="{active: daySel == value.id}" @click="changeDay(value)">
                            <div class="day">D{{value.indexdate}}</div>
                            <div class="cityList">
                                <div class="item" v-for="(item, index) in value.citys">
                                    <span class="cityName" :class="{init: !item.scheduleId}">
                                        {{item.nameCn}}
                                    </span>
                                    <span class="arrow">
                                        <i class="iconfont icon-jiantou02"></i>
                                    </span>
                                </div>
                            </div>
                            <div class="checkboxC" @click.stop="addToSel(value)" :class="{'active': templateSelDay.indexOf(value.id) > -1}"></div>
                        </li>
                    </ul>
                </div>
                <div class="routeDayDetail" v-html="intro"></div>
            </div>
        </div>

        <div class="footer">
            <div class="btns">
                <el-button type="primary" @click="exportTemplateSubmit">导出</el-button>
                <el-button @click="closeTemplate">取消</el-button>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import {mapState} from 'vuex'
export default {
    data() {
        return {
            templateList: [],
            templateDayList: [],
            templateSelId: '',
            templateSelDay: [],
            selectAllDay: false,
            intro: '',
            daySel: ''
        }
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
        }),
        routeId(){
            return this.$route.params.routeId
        },
    },
    watch: {
        selectAllDay(val){
            if(val){
                var _sel = []
                for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
                    _sel.push(this.routeInfo.dayInfo[i].id)
                }
                this.templateSelDay = _sel
            }else{
                this.templateSelDay = []
            }
        },
    },

    created(){
        this.daySel = this.routeInfo.dayInfo[0].id
        this.changeDay(this.routeInfo.dayInfo[0])
    },
    methods: {
        changeDay(day){
            this.daySel = day.id
            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doDeail/' + day.id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.intro = res.data.data.introduction
                }
            })
        },

        changeSelectAllDay(){
            this.selectAllDay = !this.selectAllDay
        },

        closeTemplate(){
            this.$router.push({path: '/route/' + this.routeId})
        },

        addToSel(item){
            if(this.templateSelDay.indexOf(item.id) == -1){
                this.templateSelDay.push(item.id)
            }else{
                this.templateSelDay.splice(this.templateSelDay.indexOf(item.id), 1);
            }

            console.log(this.templateSelDay)
        },

        exportTemplateSubmit(){
            var vm = this

            var _data = {
                scheduleIds: this.templateSelDay
            }
            $.ajax({
                url: '/tripnote/doExport/' + vm.routeId,
                type: 'post',
                data: JSON.stringify(_data),
                contentType: 'application/json',
                success: function(res){
                    if(res.code == -1){
                        vm.$message({
                            message: res.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        vm.$message({
                            message: '导出成功！',
                            type: 'success',
                            duration: 2000
                        });
                        vm.$router.push({path: '/route/' + vm.routeId})
                    }
                }
            })

            // this.$http({
            //     method: 'post',
            //     url: '/tripnote/template/doImport/' + this.routeId,
            //     data: {
            //         tripnoteIds: JSON.stringify(this.templateSelDay)
            //     }
            // })
            // .then((res)=>{
            //     if(res.data.code == -1){
            //         this.$message({
            //             message: res.data.message,
            //             type: 'error',
            //             duration: 2000
            //         })
            //     }else{
            //         this.$router.push({path: '/route/' + this.routeId})
            //     }
            // })
        },

        changeFilterType(item){
            this.filterDayType = item.value
            this.filterDayTypeName = item.label

            this.getTemplateList()
        },

        getTemplateList(){
            var _data = {
                isAll: this.tabActive == 'system' ? true : false
            }
            if(this.filterDayType != 1){
                if(this.filterDayType == 2){
                    _data['dateStart'] = 1
                    _data['dateEnd'] = 3
                }else if(this.filterDayType == 3){
                    _data['dateStart'] = 4
                    _data['dateEnd'] = 8
                }else if(this.filterDayType == 4){
                    _data['dateStart'] = 9
                    _data['dateEnd'] = 12
                }else if(this.filterDayType == 5){
                    _data['dateStart'] = 13
                    _data['dateEnd'] = 19
                }else if(this.filterDayType == 6){
                    _data['dateStart'] = 20
                }
            }
            this.$http({
                method: 'post',
                url: '/tripnote/template/doSearch',
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
                    this.templateList = res.data.data.data
                }
            })
        },

        getTemplateDayList(id){
            this.templateSelId = id
            this.$http({
                method: 'post',
                url: '/tripnote/doDetail/' + id,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.templateDayList = res.data.data.ttripNoteSchedules
                    console.log(res.data.data.ttripNoteSchedules)
                }
            })
        },

    },
}
</script>
<style lang="less" scope>
.routeExportTemplateBg{
    position: fixed;
    z-index: 3;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    opacity: 0.5;
    background: #000;
}
.routeExportTemplate{
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


    .templateBox{
        // width: 1200px;
        margin: 0 20px;
        background: #fff;
        .tabs{
            height: 53px;
            border-bottom: 1px solid #EAEDEE;
            li{
                float: left;
                padding: 15px 0 12px;
                a{
                    display: block;
                    padding: 0 24px;
                    border-right: 1px solid #EAEDEE;
                    line-height: 24px;
                    font-size: 14px;
                    color: #555;
                }
                &.active{
                    border-bottom: 2px solid #23a16d;
                    a{
                        color: #23a16d;
                    }
                }
                &:last-child{
                    a{
                        border-right: 0;
                    }
                }
            }
        }
    }
    .templateCon{
        background: #fff;
    }
    .routeNav{
        float: left;
        width: 280px;
        height: 500px;
        overflow-y: auto;
        background: #fff;
        border-right: 1px solid #EBEEF2;
        .routeNavHeader{
            height: 50px;
            border-bottom: 1px solid #EBEEF2;
            .tit{
                float: left;
                padding-left: 10px;
                line-height: 50px;
                color: #bac3c9;
                font-size: 14px;
            }
            .checkbox{
                float: right;
                margin-right: 15px;
                margin-top: 12px;
                label{
                    display: inline-block;
                    margin-right: 10px;
                    font-size: 14px;
                }
                .checkboxC{
                    display: inline-block;
                    vertical-align: middle;
                }
            }
        }
        li{
            position: relative;
            min-height: 72px;
            padding: 12px 40px 12px 10px;
            border-bottom: 1px solid #EBEEF2;
            cursor: pointer;
            .day{
                float: left;
                line-height: 36px;
                font-size: 32px;
                color: #C8D0D5;
            }
            .cityList{
                clear: both;
                overflow: hidden;
                padding-top: 5px;
                font-size: 14px;
                .item{
                    float: left;
                    .cityName{
                        float: left;
                        position: relative;
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
            .checkboxC{
                cursor: pointer;
                position: absolute;
                right: 16px;
                top: 16px;
                &.active{
                    display: block;
                }
            }
            &.active{
                background: #F7F8F9;
                &:hover{
                    background: #F7F8F9;
                }
                .day{
                    color: #666;
                }
            }
            &:hover{
                background: #fdfdfd;
                .checkboxC{
                    display: block;
                }
            }
        }
    }
    .routeDayDetail{
        height: 500px;
        padding-top: 15px;
        margin-left: 300px;
        overflow-y: auto;
        background: #fff;
        line-height: 22px;
        font-size: 13px;
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
</style>