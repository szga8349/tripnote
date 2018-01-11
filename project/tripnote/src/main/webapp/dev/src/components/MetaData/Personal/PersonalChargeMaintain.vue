<template>
    <div>
        <table class="table table-bordered tableList">
            <table-header :tableHeader = "tableHeader"></table-header>
            <tbody>
                <item v-for="(data, index) in showRecordList"
                :params="{
                  params:{
                    path:data.path,
                    level:3,
                  },
                  isfolder: false
                }"
                :names = "emphasizeName(data.datasetName,key)"
                :view = "false"
                :datasetId = "data.id"
                :indexKey = "index"
                :isPrivate = "data.isPrivate"
                >
                <!-- <tr v-for="data of showRecordList"> -->
                    <td>{{data.datasetType}}</td>
                    <td><div v-ellipsis="data.projectName"></div></td>
                    <td>
                        <div v-ellipsis="formatTime(data.createdTime)"></div>
                    </td>
                    <!-- <td>{{data.showLifeDay}}</td> -->
                    <td>{{formatSize(data.size)}}</td>
                    <td>{{data.owner}}</td>
                    <td>
                        <div class="owner-operator">
                            <el-tooltip effect="dark" :content="$t('content.personal.xgowner')" placement="top">
                                <span @click="showOwnerInfo(data.id, data.datasetName, data.owner)">
                                    <i class="iconfont icon-yonghushezhi icon-s"></i>
                                </span>
                            </el-tooltip>
                        </div>
                    </td>
                <!-- </tr> -->
                </item>
            </tbody>
        </table>
        <div class="tablePages" v-if="totalRows>10">
            <el-pagination background
                layout="total, prev, pager, next"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="totalRows"
            >
            </el-pagination>
        </div>
        <no-data v-if="totalRows == 0"></no-data>
        <!-- 修改owner -->
        <el-dialog :title="ownerTableName" :visible.sync="dialogOwnerVisible" custom-class="changeOwner">
            <div class="ownerCon">
                <label>请输入Owner：</label>
                <div class="txt">
                    <el-select
                        v-model="newOwner"
                        filterable
                        remote
                        placeholder="请输入关键词"
                        :remote-method="searchUserList"
                        :loading="loading">
                        <el-option
                            v-for="item in userList"
                            :key="item"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogOwnerVisible = false">{{$t('content.common.qx')}}</el-button>
                <el-button type="primary" @click="changeOwner()">{{$t('content.common.qd')}}</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    import Bus from 'utils/bus'
    import item from 'common/TableItemJump'
    import { FormatTime, FormatSize, FormatLifeDay, Message, EmphasizeName } from 'mixins/common'
    export default{
        name: 'PersonalChargeMaintain',
        mixins:[FormatTime, FormatSize, FormatLifeDay, Message, EmphasizeName],
        data(){
            return {
                tableHeader: [
                    {name: '表名', hasSort: false},
                    {name: '表类型', hasSort: false},
                    {name: '所属项目', hasSort: false},
                    {name: '创建时间', hasSort: false},
                    // {name: '生命周期', hasSort: false},
                    {name: '存储大小', hasSort: false},
                    {name: '业务负责人', hasSort: false},
                    {name: '操作', hasSort: false},
                ],
                records: [],
                ownerTableId: '', // 当前表id
                ownerTableName: '', // 当前表名
                tableOwnerActive: '', // 当前用户
                dialogOwnerVisible: false,
                ownerHistory: [], // owner历史
                noDataVisible: false,

                currentPage: 1,
                pageSize: 10,
                totalRows: 1,
                loading: false,
                userList: [],
                newOwner: '',
                key: ''
            }
        },
        created(){
            this.fetchData()
        },
        components: {
            item,
        },
        watch: {
            '$parent.key'(val){
                this.fetchData({key: val, currentPage: 1})
            }
        },
        methods: {
            // 修改owner
            async changeOwner(){
                if(this.newOwner != ''){
                    let self = this;
                    let url = `privilege/${this.ownerTableId}/updateDatesetOwner?userName=${this.newOwner}`;
                    const res = await self.$http.get(url);
                    if(res.body.statusCode == 200){
                        this.message(`${this.$t('content.personal.xgcg')}!`, 'success');
                        this.newOwner = '';
                        this.dialogOwnerVisible = false;
                        this.fetchData({key: this.key, currentPage: this.currentPage});
                    }
                }else{
                    this.dialogOwnerVisible = false;
                }
            },
            searchUserList(keyword) {
                if (keyword !== '') {
                    this.loading = true;
                    this.$http.get(`/privilege/getLeapUsers?kw=${keyword}`)
                    .then((res)=>{
                        if(res.status == 200){
                            this.loading = false;
                            this.userList = res.body.message;
                        }
                    })
                } else {
                    this.options4 = [];
                }
            },
            async fetchData({currentPage=1, key=''}={}){
                this.key = key
                Bus.$emit('loadingVisible', true)
                const res = await this.$http.get(`/datasets/ownedDatasetWithoutOwner?keyword=${escape(key)}&rows=10&page=${currentPage}`)
                if(res.body.statusCode == 200){
                    Object.assign(this.$data, res.body.message)
                    Bus.$emit('loadingVisible', false)
                }
            },
            // 显示ower信息窗口
            async showOwnerInfo(id, datasetName, owner){
                let self = this;
                this.ownerTableId = id;
                this.ownerTableName = '修改表' + datasetName + '的owner';
                this.tableOwnerActive = owner;
                this.dialogOwnerVisible = true;
                let url = `privilege/${this.ownerTableId}/getOwnerHistory`;
                const res = await self.$http.get(url);
                if(res.body.statusCode == 200){
                    this.ownerHistory = res.body.message;
                    this.ownerHistory.shift();
                }
            },
            handleCurrentChange(val){
                const params = {
                    currentPage: val,
                    key: this.key
                }
                this.fetchData(params);
            },
        }
    }
</script>
<style>

</style>

