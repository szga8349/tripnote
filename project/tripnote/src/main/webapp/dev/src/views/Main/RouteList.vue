<template>
    <div>
        <div class="commonBox">
            <div class="commonTit">
                <h2>我的定制</h2>
                <div class="rightOpts">
                    <el-button type="primary" icon="el-icon-plus" @click="newRoute">新建定制</el-button>
                </div>
            </div>

            <div class="commonCon">
                <el-table
                    v-loading="tableDataLoading"
                    :data="tableData"
                    style="width: 100%"
                    class="routeListTable"
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    @sort-change="sortChange"
                    @row-click="rowClick"
                >
                    <el-table-column
                        prop="title"
                        label="标题"
                        sortable
                        >
                        <template slot-scope="scope">
                            <div class="routeTit">
                                <div class="avatar" :style="{backgroundImage: `url(${imgFormat(scope.row.imageurl)})`}"></div>
                                <h3>{{ scope.row.title }}</h3>
                                <div class="cityList">
                                    <div :title="formatCityList(scope.row.ttripNoteSchedules)">
                                        {{formatCityList(scope.row.ttripNoteSchedules)}}
                                    </div>
                                </div>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="code"
                        label="编号"
                        sortable
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="custems"
                        label="客人名"
                        width="110">
                        <template slot-scope="scope">
                            <div v-html="formatCustomer(scope.row.custems)"></div>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="start_date"
                        label="出发时间"
                        sortable
                        width="100">
                        <template slot-scope="scope">
                            {{ formatTime(scope.row.startDate) }}
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="days"
                        label="行程天数"
                        sortable
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="publish_time"
                        label="发布时间"
                        sortable
                        width="100">
                        <template slot-scope="scope">
                            {{ formatTime(scope.row.createTime) }}
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="opts"
                        label="操作"
                        width="80">
                            <template slot-scope="scope">
                                <el-tooltip placement="top">
                                    <div slot="content">删除</div>
                                    <a href="javascript:;" @click.stop="delRouteConfirm(scope.row.id)" class="tableDataDel"><i class="el-icon-delete"></i></a>
                                </el-tooltip>
                                
                            </template>
                    </el-table-column>
                </el-table>

                <el-pagination
                    background
                    @current-change="handleCurrentChange"
                    :current-page.sync="pageNo"
                    :page-size="5"
                    layout="total, prev, pager, next"
                    :total="total">
                </el-pagination>


                <div v-if="!tableDataLoading && tableData.length == 0" class="addNewRouteTip">
                    <div class="pic" @click="dialogAddVisible = true">
                        <div class="icon"></div>
                        <div class="txt">新建定制</div>
                    </div>
                    <p>您还没有行程，请新建行程</p>
                </div>
            </div>

            <el-dialog title="新建定制" :visible.sync="dialogAddVisible" width="600px">
                <el-form ref="form" :model="form" label-width="70px" class="formAdd">
                      <el-form-item label="名称:">
                        <el-input v-model="form.title"></el-input>
                      </el-form-item>
                      <el-form-item label="行程时段:">
                        <el-col :span="10">
                            <el-date-picker
                                :picker-options="pickerOptions"
                                class="dateRange"
                                v-model="form.dateRange"
                                type="daterange"
                                range-separator="至"
                                :clearable="false"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                            </el-date-picker>
                        </el-col>
                        <el-col class="line" :span="4">
                            <span class="label">行程天数:</span>
                        </el-col>
                        <el-col :span="10">
                          <el-input v-model="form.days" :disabled="true"></el-input>
                        </el-col>
                      </el-form-item>

                      <el-form-item label="出发城市:">
                        <el-col :span="10">
                          <el-input v-model="form.startCity"></el-input>
                        </el-col>
                        <el-col class="line" :span="4">
                            <span class="label">返回城市:</span>
                        </el-col>
                        <el-col :span="10">
                          <el-input v-model="form.destination"></el-input>
                        </el-col>
                      </el-form-item>

                    <el-form-item label="客人信息:">
                        <div class="personList">
                            <ul>
                                <li v-for="(item, index) in form.personList">
                                    <div class="done" v-if="item.type == 'done'">
                                        {{item.name}}&nbsp;&nbsp;{{item.phone}} 
                                        <a href="javascript:;" class="iconEdit" @click="item.type = 'edit'"></a>
                                    </div>
                                    <div v-if="item.type == 'edit'">
                                        <el-input placeholder="客人姓名" v-model="item.name" class="inputText"></el-input>
                                        <el-input placeholder="联系方式" v-model="item.phone" class="inputText"></el-input>
                                        <el-button icon="el-icon-check" class="btn btnAdd" @click="addPersonItem(item)"></el-button>
                                        <el-button icon="el-icon-close" class="btn btnDel" @click="delPersonItem(index)"></el-button>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="addPerson" @click="newPersonItem">
                            <i class="el-icon-circle-plus-outline"></i>
                            添加客户信息
                        </div>
                    </el-form-item>

                    <el-form-item label="备注:">
                        <el-input
                            type="textarea"
                            :rows="3"
                            placeholder="添加备注信息（限200个中文字符）"
                            v-model="form.remarks">
                        </el-input>
                    </el-form-item>

                   <!--  <el-form-item label="选择模板:">
                        <el-select v-model="form.templateType" placeholder="请选择" class="templateType">
                            <el-option
                                v-for="item in tempateTypeOpts"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item> -->
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="dialogAddVisible = false">取 消</el-button>
                    <el-button :disabled="isDisable" type="primary" @click.native="addRouteSubmit()">新 建</el-button>
                </div>
            </el-dialog>
        </div>

        <el-dialog title="提示" :visible.sync="dialogDelTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除这一天吗？</p>
                <p class="colorRed">安排这天所有的行程都会删除！</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelTip = false">取消</el-button>
                <el-button type="primary" @click.native="delRoute">确定</el-button>
            </div>
        </el-dialog>

        <route-template 
            v-if="templateVisible" 
            :templateType="form.templateType" 
            @callbackCancel="templateVisible = false"
            @callbackSure="templateCallback"
        ></route-template>
    </div>
</template>
<script>
import RouteTemplate from './RouteTemplate'
import moment from 'moment'
import { FormatTime } from 'mixins/common'

export default {
    mixins: [ FormatTime ],
    components: {
        RouteTemplate
    },
    watch: {
        'form.dateRange'(val){
            if(val != ''){
                this.form.days = (new Date(val[1]).getTime() - new Date(val[0]).getTime())/(24*60*60*1000) + 1
            }
        }
    },

    data() {
        return {
            dialogDelTip: false,

            pickerOptions:{
                disabledDate(time) {
                    return time.getTime() < Date.now();
                },
            },

            tableData: [],
            tableDataLoading: true,
            pageNo: 1,
            pageSize: 5,
            total: 0,
            form: {
                title: '',
                days: '',
                destination: '',
                dateRange: '',
                remark: '',
                personList: [],
                templateType: 0,
                startCity: ''
            },
            tempateTypeOpts: [{
                value: 0,
                label: '不使用模板'
            }, {
                value: 1,
                label: '我的模板'
            }, {
                value: 2,
                label: '系统模板'
            }
            // , {
            //     value: 3,
            //     label: '系统模板'
            // }
            ],
            dialogAddVisible: false,
            templateVisible: false,
            routeId: '',
            delRouteId: '',
            sortField: 'create_time',
            sortType: -1
        }
    },
    created(){
        this.getRouteList()
    },
    methods: {
        newRoute(){
            this.dialogAddVisible = true
            this.form = {
                title: '',
                days: '',
                destination: '',
                dateRange: '',
                remark: '',
                personList: [],
                templateType: 0,
                startCity: ''
            }
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

        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        formatCustomer(customers){
            var _personList = [];
            for (var i = 0; i < customers.length; i++) {
                _personList.push(customers[i].name)
            }
            return _personList.join('<br/>')
        },

        // 获取行程列表
        getRouteList(){
            var vm = this
            this.tableDataLoading = true
            this.$http({
                method: 'POST',
                url: '/tripnote/doSearch',
                data: {
                    pageNo: this.pageNo,
                    pageSize: this.pageSize,
                    sortField: this.sortField,
                    ascOrDes: this.sortType,
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
                    this.tableData = res.data.data.data
                    this.total = res.data.data.total
                }
            })
        },

        handleCurrentChange(val){
            this.pageNo = val
            this.getRouteList()
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

        addRouteSubmit(){
            var _custom = []
            for (var i = 0; i < this.form.personList.length; i++) {
                if(this.form.personList[i].type == 'done'){
                    _custom.push({
                        name: this.form.personList[i].name,
                        phone: this.form.personList[i].phone,
                    })
                }
            }

            this.$http({
                method: 'POST',
                url: '/tripnote/doAdd',
                data: {
                    title: this.form.title,
                    days: this.form.days,
                    startDate: moment(this.form.dateRange[0]).format('YYYY-MM-DD'),
                    endDate: moment(this.form.dateRange[1]).format('YYYY-MM-DD'),
                    remarks: this.form.remarks,
                    startCity: this.form.startCity,
                    destination: this.form.destination,
                    customers: JSON.stringify({"customer":_custom}),
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.routeId = res.data.data
                    this.form['id'] = this.routeId
                    this.$store.dispatch('setRouteInfo', this.form)
                    this.addSchedule()
                }
            })
        },

        addSchedule(){
            this.$http({
                method: 'POST',
                url: '/tripnote/schedule/doAdd/' + this.routeId,
                data: {
                    indexdate: this.form.days,
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    // this.addDayCity(res.data.data)

                    if(this.form.templateType == 0){
                        this.$router.push({path: '/route/' + this.routeId})
                    }else{
                        this.$router.push({path: '/routeTemplate/' + this.routeId + '/' + this.form.templateType})
                    }
                }
            })
        },

        // async addDayCity(cityList){
        //     const res1 = await this.$http({
        //         method: 'POST',
        //         url: '/tripnote/schedulecity/doAdd',
        //         data:{
        //             cityId: item.id,
        //             scheduleId: this.dayId
        //         }
        //     })
            
        // },

        templateCallback(data){
            this.templateVisible = false
            this.templateType = data.templateType
        },

        delRouteConfirm(id){
            this.dialogDelTip = true
            this.delRouteId = id
        },

        delRoute(){
            this.$http({
                method: 'POST',
                url: '/tripnote/doDelete/' + this.delRouteId,
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
        }
    },
}
</script>
<style lang="less" scope>
.delTipCon{
    padding: 25px 0;
    text-align: center;
    p{
        line-height: 24px;
    }
    .colorRed{
        color: #F56C6C;
    }
}
.routeListTable{
    td{
        padding: 20px 0;
        color: #9FA5B0;
        cursor: pointer;
    }
}
.tableDataDel{
    font-size: 18px;
    color: #9FA5B0;
}
.routeTit{
    .avatar{
        float: left;
        width: 90px;
        height: 70px;
        background-size: cover;
        background-position: center;
    }
    h3{
        padding-top: 5px;
        margin-left: 105px;
        font-size: 16px;
        font-weight: normal;
        color: #444;
    }
    .cityList{
        margin-top: 20px;
        margin-left: 105px;
        div{
            width: 100%;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    }
}
.addNewRouteTip{
    width: 170px;
    margin: 55px auto;
    text-align: center;
    font-size: 14px;
    .pic{
        width: 170px;
        height: 215px;
        border: 1px dashed #e3e3e3;
        cursor: pointer;
        &:hover{
            background: #F9FAFA;
        }
        .icon{
            width: 68px;
            height: 91px;
            margin: 47px auto 0;
            background: url(../../assets/images/icon_add.png);
        }
        .txt{
            line-height: 40px;
        }
    }
    p{
        margin-top: 15px;
        color: #C7CBD1;
    }
}
.formAdd{
    margin-bottom: 15px;
    .label{
        display: block;
        padding-right: 12px;
        font-size: 13px;
        text-align: right;
        color: #474747;
    }
    .dateRange{
        width: 195px;
        padding: 0 5px;
    }
    .el-range__icon{
        line-height: 24px;
    }
    .el-range-separator{
        width: 10%;
        padding: 0 2px;
        line-height: 30px;
        vertical-align: top;
        font-size: 12px;
    }
    .el-range__close-icon{
        display: none;
    }
    .el-range-input{
        width: 38%;
        vertical-align: middle;
    }
    .addPerson{
        height: 34px;
        padding-left: 12px;
        background: #fafafa;
        line-height: 34px;
        color: #999;
        cursor: pointer;
        i{
            font-size: 14px;
        }
        &:hover{
            background: #fff; 
        }
    }
    .personList{
        li{
            margin-top: 4px;
            .done{
                line-height: 24px;
            }
            .iconEdit{
                display: inline-block;
                vertical-align: middle;
                width: 24px;
                height: 24px;
                margin-top: -6px;
                background: url(../../assets/images/icon_edit.png) center center no-repeat;
            }
            .inputText{
                width: 185px;
                margin-right: 10px;
            }
            .btn{
                float: right;
                width: 32px;
                height: 32px;
                margin-left: 5px;
                padding: 5px;
                background: #e2e2e2;
                border: 0;
                font-size: 20px;
                &:hover{
                    background: #ddd;
                }
            }
            .btnDel:hover{
                color: #F56C6C;
            }
            .btnAdd{
                color: #23a16d;
            }
        }
    }
    .templateType{
        width: 100%;
    }
}
</style>