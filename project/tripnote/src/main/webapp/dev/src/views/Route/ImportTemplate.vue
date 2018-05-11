<template>
<div>
    <div class="routeTemplateBg"></div>
    <div class="routeTemplate">
        <div class="header">
            <div class="tit">导入模板</div>
            <a href="javascript:;" class="closeBtn" @click="closeTemplate">
                <i class="iconfont icon-guanbi"></i>
            </a>
        </div>

        <div class="templateBox">
            <div class="tabs">
                <ul>
                    <li :class="{active: tabActive=='my'}" @click="tabActive='my'"><a href="javascript:;">我的模板</a></li>
                    <li :class="{active: tabActive=='system'}" @click="tabActive='system'"><a href="javascript:;">系统模板</a></li>
                </ul>
            </div>
            <div class="filterBox">
                <div class="filterKeywords">
                    <input type="text" placeholder="搜索关键字" v-model="filterTitle" @keyup.enter="getTemplateList">
                    <a href="javascript:;" class="btnSubmit" @click="getTemplateList"></a>
                </div>

                <div class="dayFilter">
                    <label>行程天数：</label>
                    <Select 
                        :opts="filterDayTypeOpts" 
                        :sel="filterDayTypeName" 
                        @changeOpt="changeFilterType" 
                        nameAlias="label"
                        idAlias="value"
                        align="right">
                    </Select>
                </div>
            </div>

            <div class="templateCon">
                <div class="routeNav">
                    <ul>
                        <li v-for="(item, index) in templateList" :class="{'active': templateSelId == item.id}" @click="getTemplateDayList(item.id)">
                            <div class="tit">{{item.title}}</div>
                            <!-- <div class="checkboxC" @click.stop="templateSelId = item.id" :class="{'active': templateSelId == item.id}"></div> -->
                        </li>
                    </ul>
                </div>
                <div class="routeDayDetail">
                    <div class="routeDetailHeader">
                        <div class="tit">选择导入内容</div>
                        <div class="checkbox" v-if="templateDayList.length > 0">
                            <label>全选</label>
                            <div class="checkboxC" :class="{active: selectAllDay}" v-model="selectAllDay" @click="changeSelectAllDay"></div>
                        </div>
                    </div>

                    <div class="templateDayList">
                        <ul v-if="templateDayList.length > 0">
                            <li v-for="(item, index) in templateDayList">
                                <div class="checkboxC" :class="{active: templateSelDay.indexOf(item.id) > -1}" @click="addToSel(item)"></div>
                                <div class="tit">D{{item.indexdate}}</div>
                                <div class="txt" v-html="item.introduction">
                                </div>
                            </li>
                        </ul>

                        <div class="noDataTip" v-if="templateDayList.length == 0">
                            <i class="iconfont icon-point-out"></i>请从左侧选择要导入的模板。
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer">
            <div class="btns">
                <el-button type="primary" @click="submitTemplate">导入</el-button>
                <el-button @click="closeTemplate">取消</el-button>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import Bus from 'utils/bus'
export default {
    data() {
        return {
            tabActive: 'my',

            filterDayTypeOpts: [{
                value: 1,
                label: '全部'
            }, {
                value: 2,
                label: '1-3天'
            }, {
                value: 3,
                label: '4-8天'
            }, {
                value: 4,
                label: '9-12天'
            }, {
                value: 5,
                label: '13-19天'
            }, {
                value: 6,
                label: '20天及以上'
            }],
            filterDayType: 1,
            filterDayTypeName: '全部',

            filterTitle: '',

            checked: false,
            
            dialogAddVisible: false,
            templateName: '',

            templateList: [],
            templateDayList: [],
            templateSelId: '',
            templateSelDay: [],
            selectAllDay: false
        }
    },
    created(){

    },
    computed: {
        routeId(){
            return this.$route.params.routeId
        },

    },
    watch: {
        selectAllDay(val){
            if(val){
                var _sel = []
                for (var i = 0; i < this.templateDayList.length; i++) {
                    _sel.push(this.templateDayList[i].id)
                }
                this.templateSelDay = _sel
            }else{
                this.templateSelDay = []
            }
        },
        tabActive(){
            this.getTemplateList()
            this.templateDayList = []
        }
    },

    created(){
        this.getTemplateList()
    },
    methods: {
        closeTemplate(){
            this.$router.push({path: '/route/' + this.routeId})
        },

        changeSelectAllDay(){
            this.selectAllDay = !this.selectAllDay
        },

        addToSel(item){
            if(this.templateSelDay.indexOf(item.id) == -1){
                this.templateSelDay.push(item.id)
            }else{
                this.templateSelDay.splice(this.templateSelDay.indexOf(item.id), 1);
            }
        },

        submitTemplate(){
            var vm = this

            var _data = {
                tripnoteId: this.routeId,
                scheduleIds: this.templateSelDay
            }
            $.ajax({
                url: '/tripnote/template/doImport/' + vm.templateSelId,
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
                            message: '导入成功！',
                            type: 'success',
                            duration: 2000
                        });
                        Bus.$emit('refreshDay', true)
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

        // sendToParentSure(){
        //     this.$emit('callbackSure', {
        //         templateType: this.templateTypeSel,
        //         templateName: this.templateName
        //     })
        // },
        // sendToParentCancel(){
        //     this.$emit('callbackCancel')
        // }
    },
}
</script>
<style lang="less" scope>
.routeTemplateBg{
    position: fixed;
    z-index: 3;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    opacity: 0.5;
    background: #000;
}
.routeTemplate{
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
    .filterBox{
        height: 59px;
        padding: 12px 0;
        border-bottom: 1px solid #EAEDEE;
        .filterKeywords{
            float: left;
            width: 215px;
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
        .dayFilter{
            float: right;
            padding: 0 15px;
            font-size: 14px;
            .selectBox{
                display: inline-block;
                vertical-align: middle;
                .list{
                    li{
                        padding: 5px 10px;
                        line-height: 22px;
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
        width: 215px;
        height: 500px;
        overflow-y: auto;
        background: #fff;
        border-right: 1px solid #EBEEF2;
        li{
            position: relative;
            height: 72px;
            padding: 12px 40px 12px 10px;
            border-bottom: 1px solid #EBEEF2;
            .tit{
                font-size: 14px;
            }
            .checkboxC{
                cursor: pointer;
                display: none;
                position: absolute;
                right: 16px;
                top: 16px;
                &.active{
                    display: block;
                }
            }
            &.active{
                background: #F7F8F9;
            }
            &:hover{
                background: #F7F8F9;
                .checkboxC{
                    display: block;
                }
            }
        }
    }
    .routeDayDetail{
        height: 500px;
        margin-left: 230px;
        overflow-y: auto;
        background: #fff;
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
                    font-size: 14px;
                }
                .checkboxC{
                    display: inline-block;
                    vertical-align: middle;
                }
            }
        }
        .templateDayList{
            li{
                position: relative;
                padding: 10px 65px 15px 0;
                border-bottom: 1px solid #EBEEF2;
                .checkboxC{
                    position: absolute;
                    right: 15px;
                    top: 15px;
                }
                .tit{
                    font-size: 16px;
                    span{
                        margin-left: 5px;
                        font-size: 14px;
                    }
                }
                .txt{
                    margin-top: 10px;
                    line-height: 22px;
                    font-size: 13px;
                    color: #828d94;
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
</style>