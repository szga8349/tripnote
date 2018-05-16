<template>
    <div>
        <div class="commonBox">
            <div class="commonTit">
                <h2>客户管理库</h2>
            </div>

            <div class="commonCon customerBox">
                <div class="leftBox">
                    <ul>
                        <li @click="getGroupFav()" :class="{active: groupId == 'fav'}">
                            重要客户（{{favCount}}）
                            <div class="opts">
                                <i class="iconfont icon-shoucang"></i>
                            </div>
                        </li>
                    </ul>
                    <ul class="groupList">
                        <li @click="getGroupItem('')" :class="{active: groupId == ''}">
                            全部客户（{{groupCount}}）
                            <div class="opts openBtn" @click="toggleGroupOpen=!toggleGroupOpen" :class="{groupClose: toggleGroupOpen}">
                                <i class="el-icon-d-arrow-left"></i>
                            </div>
                        </li>
                        <li v-for="(item, index) in groupList" v-if="toggleGroupOpen" @click="getGroupItem(item.id)" :class="{active: item.id == groupId}">
                            <div v-if="item.editable == 'done'">
                                {{item.name}}（{{item.count ? item.count : 0}}）
                                <div class="opts otherOpts">
                                    <a href="javascript:;" @click.stop="item.editable='edit'" class="edit"><i class="iconfont icon-bianji-blue"></i></a>
                                    <a href="javascript:;" @click.stop="delGroupConfirm(item.id)"><i class="iconfont icon-wechaticon13"></i></a>
                                </div>
                            </div>
                            <div v-if="item.editable == 'edit'">
                                <el-input placeholder="请输入分组名" v-model="item.nameEdit" class="input-with-select editInput">
                                    <el-button slot="append" icon="el-icon-check" @click.stop="editGroup(item)"></el-button>
                                    <el-button slot="append" icon="el-icon-close" @click.stop="item.editable='done'"></el-button>
                                </el-input>
                            </div>
                        </li>
                    </ul>
                    <ul style="margin: 5px 0;">
                        <li v-if="addGroupActive">
                            <el-input placeholder="请输入分组名" v-model="addGroupName" class="input-with-select editInput">
                                <el-button slot="append" icon="el-icon-check" @click="addGroup"></el-button>
                                <el-button slot="append" icon="el-icon-close" @click="addGroupActive=false"></el-button>
                            </el-input>
                        </li>
                        <li class="newGroupLink">
                            <i class="el-icon-plus"></i>
                            <div class="opts" @click="addGroupActive=true">新建分组</div>
                        </li>
                    </ul>
                </div>
                <div class="rightBox">
                    <div class="optBtns">
                        <el-button-group class="btns">
                            <el-button type="default" icon="el-icon-plus" @click="newCustomer">新建客户</el-button>
                            <el-button type="default" icon="el-icon-delete" @click="delCustomer">删除</el-button>

                            <el-select class="selectGroup" value="添加到组" @change="changeGroup">
                                <el-option
                                    v-for="item in groupList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>

                            <!-- <el-select v-model="select" placeholder="添加到组" icon="el-icon-delete">
                                <el-option label="餐厅名" value="1"></el-option>
                                <el-option label="订单号" value="2"></el-option>
                                <el-option label="用户电话" value="3"></el-option>
                            </el-select> -->
                        </el-button-group>

                        <!-- <el-input
                            class="searchBox"
                            placeholder="输入关键字"
                            v-model="searchKeyword">
                            <el-button slot="append" icon="el-icon-search" @click="getCustomerList"></el-button>
                        </el-input> -->
                    </div>

                    <!-- <div class="letterFilter">
                        <a href="javascript:;" v-for="(item, index) in letterList">{{item}}</a>
                    </div> -->

                    <el-table
                        v-loading="tableDataLoading"
                        :data="tableData"
                        style="width: 100%"
                        :default-sort = "{prop: 'date', order: 'descending'}"
                        @row-dblclick="rowClick"
                        height="560"
                        @selection-change="handleSelectionChange"
                    >
                        <el-table-column
                            type="selection"
                            width="55">
                        </el-table-column>
                        <el-table-column
                            prop="name"
                            label="姓名"
                            width="110"
                            >
                            <template slot-scope="scope">
                                <a href="javascript:;" @click="rowClick(scope.row)">{{ scope.row.name }}</a>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="phone1"
                            label="电话"
                            width="110"
                            >
                        </el-table-column>
                        <!-- <el-table-column
                            prop="address"
                            label="住址"
                           >
                        </el-table-column> -->
                        <el-table-column
                            prop="birthday"
                            label="年龄"
                            >
                            <template slot-scope="scope">
                                {{ formatTime(scope.row.birthday) }}
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="sex"
                            label="性别"
                            >
                            <template slot-scope="scope">
                                {{ scope.row.sex == 1 ? '男' : '女' }}
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="xxx"
                            label="提醒"
                            width="50"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="remark"
                            label="备注"
                            >
                        </el-table-column>
                        
                        <el-table-column
                            prop="opts"
                            label=""
                            width="50"
                            >
                                <template slot-scope="scope">
                                    <el-tooltip placement="top" v-if="scope.row.importance == 1">
                                        <div slot="content">取消收藏</div>
                                        <a href="javascript:;" @click.stop="addFav(scope.row)" class="tableDataDel"><i class="iconfont icon-shoucang iconFav"></i></a>
                                    </el-tooltip>
                                    <el-tooltip placement="top" v-if="scope.row.importance != 1">
                                        <div slot="content">添加收藏</div>
                                        <a href="javascript:;" @click.stop="addFav(scope.row)" class="tableDataDel"><i class="iconfont icon-icon_collect"></i></a>
                                    </el-tooltip>
                                </template>
                        </el-table-column>
                    </el-table>

              <!--       <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="pageNo"
                        :page-size="5"
                        layout="total, prev, pager, next"
                        :total="total">
                    </el-pagination> -->


                    <div v-if="!tableDataLoading && groupCount == 0" class="addCustomerTip">
                        <div class="icon"></div>
                        <div class="txt">您还没有客户联系人，赶快添加吧！</div>
                        <el-button type="primary" class="newBtn" icon="el-icon-plus" @click="newCustomer">新建用户</el-button>
                    </div>

                    <div class="noDataTip" v-if="!tableDataLoading && tableData.length == 0">
                        <p>
                            <i class="iconfont icon-point-out"></i>没有相关数据！
                            <a href="javascript:;" @click="newCustomer"><i class="iconfont icon-tianjia2"></i>新建用户</a>
                        </p>
                    </div>
                </div>
            </div>

        </div>

        <el-dialog title="提示" :visible.sync="dialogDelTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除该分组吗？</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelTip = false">取消</el-button>
                <el-button type="primary" @click.native="delGroup">确定</el-button>
            </div>
        </el-dialog>

    </div>
</template>
<script>
// import RouteTemplate from './RouteTemplate'
import moment from 'moment'
import { FormatTime } from 'mixins/common'
import Bus from 'utils/bus'

import draggable from 'vuedraggable'

export default {
    mixins: [ FormatTime ],
    components: {
        // RouteTemplate,
        draggable,
    },
    watch: {
        
    },

    data() {
        return {
            multipleSelection: [],
            dialogDelTip: false,
            delGroupId: '',
            addGroupActive: false,
            letterList: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
            groupList: [],
            addGroupName: '',
            tableData: [],
            tableDataLoading: true,
            pageNo: 1,
            pageSize: 5,
            total: 0,
            toggleGroupOpen: true,
            searchKeyword: '',
            groupCount: -1,
            groupId: '',
            favCount: ''
        }
    },
    created(){
        this.getGroupList()
        this.getCustomerList()

        Bus.$emit('setMainRoutePage', false)


        var vm = this

        Bus.$on('headerSearchCustomer', function(keywords){
            vm.searchKeyword = keywords
            
            vm.getCustomerList()
        })


    },
    methods: {
        newCustomer(){
            this.$router.push({path: '/main/customerNew'})
        },

        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        addGroup(){
            if(this.addGroupName == ''){
                return;
            }
            var vm = this
            this.$http({
                method: 'POST',
                url: '/customer/catogry/doAdd',
                data: {
                    name: this.addGroupName
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
                    this.$message({
                        message: '添加成功！',
                        type: 'success',
                        duration: 2000
                    });
                    this.getGroupList()
                    this.addGroupName = ''
                    this.addGroupActive = false
                }
            })
        },

        delCustomer(){
            var _id = []
            for (var i = 0; i < this.multipleSelection.length; i++) {
                _id.push(this.multipleSelection[i].id)
            }
            var vm = this

            if(_id.length == 0){
                return;
            }

            $.ajax({
                url: '/customer/batch/doDelete',
                data: JSON.stringify({
                    ids: _id
                }),
                type: 'post',
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
                            message: '删除成功！',
                            type: 'success',
                            duration: 2000
                        });
                        vm.getCustomerList()
                    }
                }
            })
        },

        rowClick(row, column, cell, event){
            console.log('row', row)
            this.$router.push({path: '/main/customerEdit/' + row.id})
        },

        formatCityList(ttripNoteSchedules){
            var _cityList = []
            for (var i = 0; i < ttripNoteSchedules.length; i++) {
                for (var j = 0; j < ttripNoteSchedules[i].citys.length; j++) {
                    _cityList.push(ttripNoteSchedules[i].citys[j].nameCn)
                }
            }
            _cityList = Array.from(new Set(_cityList))
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

        // 获取列表
        getCustomerList(type){
            var vm = this
            this.tableDataLoading = true
            var _data = {}
            if(type){
                _data['importance'] = 1
            }else{
                if(this.groupId != ''){
                    _data['catogryId'] = this.groupId
                }
            }
            
            if(this.searchKeyword != ''){
                _data['name'] = this.searchKeyword
            }
            this.$http({
                method: 'POST',
                url: '/customer/doSearch',
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
                    this.tableData = res.data.data
                    if(this.groupCount == -1){
                        this.groupCount = res.data.data.length

                        var _favCount = 0
                        for (var i = 0; i < this.tableData.length; i++) {
                            if(this.tableData[i].importance == 1){
                                _favCount++
                            }
                        }
                        this.favCount = _favCount
                    }
                    this.total = res.data.data.total
                }
            })
        },

        // 获取行程列表
        getGroupList(){
            this.$http({
                method: 'POST',
                url: '/customer/catogry/doSearch',
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    var _groupList = res.data.data
                    this.$http({
                        method: 'POST',
                        url: '/customer/catogry/doStatistic',
                    })
                    .then((res)=>{
                        if(res.data.code == -1){
                            this.$message({
                                message: res.data.message,
                                type: 'error',
                                duration: 2000
                            });
                        }else{
                            var _idNum = {}
                            for (var i = 0; i < res.data.data.length; i++) {
                                _idNum[res.data.data[i].id] = res.data.data[i].count
                            }
                            var _countAll = 0
                            for (var i = 0; i < _groupList.length; i++) {
                                _groupList[i]['editable'] = 'done'
                                _groupList[i]['nameEdit'] = _groupList[i].name
                                if(_idNum[_groupList[i].id]){
                                    _groupList[i]['count'] = _idNum[_groupList[i].id]
                                    _countAll += _idNum[_groupList[i].id]
                                }
                            }
                            this.groupList = _groupList
                        }
                    })
                }
            })
        },

        handleCurrentChange(val){
            this.pageNo = val
            this.getCustomerList()
        },

        formatter(row, column) {
            return row.address;
        },

        delGroupConfirm(id){
            this.dialogDelTip = true
            this.delGroupId = id
        },

        delGroup(){
            this.$http({
                method: 'POST',
                url: '/customer/catogry/doDelete/' + this.delGroupId,
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
                    this.getGroupList()
                }
            })
        },

        changeGroup(item){
            var _id = []
            for (var i = 0; i < this.multipleSelection.length; i++) {
                _id.push(this.multipleSelection[i].id)
            }
            var vm = this
            if(_id.length == 0){
                return;
            }
            $.ajax({
                url: '/customer/batch/doAdd/' + item,
                data: JSON.stringify({
                    ids: _id
                }),
                type: 'post',
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
                            message: '修改成功！',
                            type: 'success',
                            duration: 2000
                        });
                        vm.getGroupList()
                        vm.getCustomerList()
                    }
                }
            })
        },

        getGroupItem(id){
            this.groupId = id
            this.getCustomerList()
        },

        getGroupFav(){
            this.groupId = 'fav'
            this.getCustomerList('fav')
        },

        editGroup(item){
            this.$http({
                method: 'POST',
                url: '/customer/catogry/doUpdate/' + item.id,
                data: {
                    name: item.nameEdit
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
                        message: '修改成功！',
                        type: 'success',
                        duration: 2000
                    })
                    this.getGroupList()
                }
            })
        },
        addFav(item){
            this.$http({
                method: 'POST',
                url: '/customer/doUpdate/' + item.id,
                data: {
                    id: item.id,
                    importance: item.importance == 1 ? -1 : 1
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
                        message: item.importance == 1 ? '取消成功！' : '添加成功！',
                        type: 'success',
                        duration: 2000
                    })
                    this.getGroupList()
                    this.getCustomerList()
                }
            })
        },
    },

    destroyed(){
        Bus.$off('headerSearchCustomer')
    }

}
</script>
<style lang="less">
.customerBox{
    position: relative;
    height: 600px;
    .leftBox{
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
        width: 220px;
        padding: 5px 10px;
        background: #F7FAFB;
        border: 1px solid #E1E5ED;
        ul{
            &.groupList{
                li{
                    cursor: pointer;
                    &:hover,
                    &.active{
                        background: #EBF0F2;
                    }
                }
            }
            border-bottom: 1px dotted #B6BDC6;
            li{
                padding: 0 5px;
                height: 36px;
                line-height: 35px;
                cursor: pointer;
                &:hover{
                    background: #EBF0F2;
                }
                &.newGroupLink{
                    cursor: pointer;
                    &:hover{
                        color: #0079f5;
                    }
                }
                .opts{
                    float: right;
                    a{
                        color: #95A7B0;
                        &.edit{
                            display: inline-block;
                            vertical-align: top;
                            margin-right: 5px;
                            .icon-bianji-blue{
                                font-size: 12px;
                            }
                        }
                        &:hover{
                            color: #0079f5;
                        }
                    }
                    .icon-shoucang{
                        color: #FF6767;
                    }
                }
                .otherOpts{
                    display: none;
                }
                &:hover{
                    .otherOpts{
                        display: block;
                    }
                }
                .openBtn{
                    transform: rotate(-90deg);
                    cursor: pointer;
                    &.groupClose{
                        transform: rotate(90deg);
                    }

                }
                .editInput{
                    .el-input__inner{
                        border-right: 0;
                        &:focus,
                        &:hover{
                            border-color: #e2e2e2;
                        }
                    }
                    .el-input-group__append{
                        background: #fff !important;
                        .el-button{
                            padding: 0 10px;
                            font-size: 14px;
                            &:hover{
                                color: #0079f5;
                            }
                        }
                    }
                }
            }
        }
    }
    .rightBox{
        position: relative;
        margin-left: 240px;
        .optBtns{
            margin-bottom: 10px;
            overflow: hidden;
            .btns{
                float: left;
                .el-button{
                    width: 100px;
                }
                .selectGroup{
                    .el-input__inner{
                        width: 100px;
                        padding-left: 15px;
                    }
                    .el-icon-arrow-up:before{
                        content: "\E60C";
                        color: #606266;
                    }
                }
            }
            .searchBox{
                float: right;
                width: 210px;
                .el-input__inner{
                    padding-left: 15px;
                    border-radius: 16px 0 0 16px;
                    border-right: 0;
                }
                .el-input-group__append{
                    border-radius: 0 16px 16px 0;
                    background: none;
                }
            }
        }
        .addCustomerTip{
            position: absolute;
            top: 120px;
            left: 0;
            width: 100%;
            text-align: center;
            .icon{
                width: 175px;
                height: 175px;
                margin: 0 auto;
                background: url(../../assets/images/icon_no_customer.png);
            }
            .txt{
                margin: 15px 0;
                color: #ddd;
            }
        }
        .noDataTip{
            position: absolute;
            top: 120px;
            left: 0;
            width: 100%;
            text-align: center;
            a{
                color: #0079f5;
                i{
                    font-size: 12px;
                }
            }
        }
        
    }
}
.iconFav{
    color: #FF6767;
}
</style>