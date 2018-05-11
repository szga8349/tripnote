<template>
    <div class="routeTemplate">
        <div class="header">
            <div class="typeSel">
                <label>选择模板：</label>
                <!-- <el-select v-model="templateTypeSel" placeholder="请选择" class="templateType">
                    <el-option
                        v-for="item in tempateTypeOpts"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select> -->

                <Select 
                    :opts="tempateTypeOpts" 
                    :sel="templateTypeSel" 
                    @changeOpt="changeTemplateType" 
                    nameAlias="name" 
                    idAlias="id">
                </Select>

                <div class="templateName">{{templateName}}</div>
            </div>

            <div class="btns">
                <el-button @click="cancelTemplate">取 消</el-button>
                <el-button type="primary" @click="submitTemplate">确 定</el-button>
            </div>
        </div>

        <div class="detailBox">
            <div class="detailHeader">
                <div class="tit">
                    我定制过的行程
                </div>
                <div class="filterBox">
                    <ul>
                        <li>
                            <label>行程天数：</label>
                            <Select 
                                :opts="filterDayTypeOpts" 
                                :sel="filterDayType" 
                                @changeOpt="changeFilterType" 
                                nameAlias="label" 
                                idAlias="value">
                            </Select>
                        </li>

                        <li>
                            <div class="filterKeywords">
                                <input type="text" placeholder="搜索关键字">
                                <a href="javascript:;" class="btnSubmit"></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="detailCon">
                <div class="routeNav">
                    <ul>
                        <li v-for="(item, index) in templateList" :class="{'active': templateSelId == item.id}" @click="getTemplateDayList(item.id)">
                            <div class="tit">{{item.title}}</div>
                            <!-- <div class="checkboxC" @click.stop="templateSelId = item.id" :class="{'active': templateSelId == item.id}"></div> -->
                        </li>
                    </ul>
                </div>
                <div class="routeDetail">
                    <div class="routeDetailHeader">
                        <div class="tit">选择导出内容</div>
                        <div class="checkbox">
                            <label>全选</label>
                            <div class="checkboxC" :class="{active: selectAllDay}" v-model="selectAllDay" @click="changeSelectAllDay"></div>
                        </div>
                    </div>

                    <div class="dayList">
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
    </div>
</template>
<script>
export default {
    data() {
        return {
            tempateTypeOpts: [{
                id: 1,
                name: '我的模板'
            },{
                id: 2,
                name: '系统模板'
            }],

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
            filterDayType: '全部',

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
        this.templateTypeSel = this.templateType
    },
    computed: {
        routeId(){
            return this.$route.params.routeId
        },

        templateTypeSel(){
            if(this.$route.params.typeId == 1){
                return '我的模板'
            }else{
                return '系统模板'
            }
        }
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
    },

    created(){
        this.getTemplateList()
    },
    methods: {
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

        cancelTemplate(){
            this.$router.push({path: '/route/' + this.routeId})
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

        changeTemplateType(item){
            this.templateTypeSel = item.name
        },

        changeFilterType(item){
            this.templateTypeSel = item.name
        },

        getTemplateList(){
            this.$http({
                method: 'post',
                url: '/tripnote/template/doSearch',
                data: {
                    isAll: true
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
.routeTemplate2{
    z-index: 2001;
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: #F2F4F8;

    .header{
        height: 60px;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #4a5e6d;
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
            .selectBox{
                float: left;
                span{
                    font-size: 14px;
                }
            }
            .templateName{
                float: left;
                margin-left: 10px;
                font-size: 16px;
                color: #cde0ed;
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
                .filterKeywords{
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
            }
        }
    }
    .detailCon{
        background: #fff;
    }
    .routeNav{
        position: absolute;
        top: 134px;
        bottom: 20px;
        width: 320px;
        overflow-y: auto;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.1);
        li{
            position: relative;
            height: 100px;
            padding: 12px 40px 12px 20px;
            border-bottom: 1px solid #EBEEF2;
            .tit{
                font-size: 16px;
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
        .dayList{
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
}
</style>