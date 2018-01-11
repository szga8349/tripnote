<template>
    <div class="personal-approval">
        <div class="personal-approval-content">
            <div class="boxCommon">
                <div class="table-title">
                    <div class="table-title__text">
                        <span>{{$parent.approvalsActiveTab.title}}</span>
                    </div>
                    <div class="table-title__btns">
                        <Search
                        :placeholder="$t('content.validator.qsrbm')"
                        emitName="approvalSearch"
                        @approvalSearch="fetchData"></Search>
                    </div>
                </div>

                <div class="con">
                    <div class="tableMulti">
                        <loading :isloading="loadingVisible"/>
                        <table class="table table-bordered tableList" v-if="!loadingVisible">
                            <table-header
                                    :tableHeader = "tableHeader"
                                    :sort = "sort"
                                    :order = "order"
                                    @tableSort="tableSort">
                            </table-header>
                            <tbody>
                                <tr v-for="item in reApprovalList">
                                    <td>{{item.creator}}</td>
                                    <td>
                                        <div v-ellipsis="emphasizeName(item.dbAndTable, key)"></div>
                                    </td>
                                    <td>
                                        <div>
                                            <i v-for="type in Array.from(item.type)"
                                                class="permission__icon"
                                                :class="{'permission__icon-r': type=='r',
                                                         'permission__icon-w': type=='w'
                                                        }">
                                                {{type == 'r' ? $t('content.authority.r') : $t('content.authority.w')}}
                                            </i>
                                        </div>
                                    </td>
                                    <td>
                                        <div v-ellipsis="item.ownerProject"></div>
                                    </td>
                                    <td>
                                        <div v-ellipsis="item.project_name"></div>
                                    </td>
                                    <td>
                                        <div v-ellipsis="item.reason"></div>
                                    </td>
                                    <td>
                                        <span style="color: orange;" v-if="item.md_result == 0">{{$t('content.personal.dsh')}}
                                        </span>
                                        <span style="color: green;" v-if="item.md_result == 1">{{$t('content.personal.shtg')}}
                                        </span>
                                        <el-tooltip class="item" effect="dark" placement="top">
                                            <div slot="content" v-html="reasonTxt(item.md_msg)"></div>
                                            <span style="color: red; border-bottom: 1px dashed red;"
                                                v-if="item.md_result == 2">
                                                {{$t('content.personal.shbtg')}}
                                            </span>
                                        </el-tooltip>
                                    </td>
                                    <td>
                                        <div v-ellipsis="formatTime(item.create_time * 1000)"></div>
                                    </td>
                                    <td>
                                        <div class="owner-operator" v-if="item.md_result == 0">
                                            <el-tooltip effect="dark" :content="$t('content.personal.shtg')" placement="top">
                                                <span
                                                    @click="agreeApprove(1, item.id)"
                                                ><i class="iconfont icon-cf-c100"></i></span>
                                            </el-tooltip>
                                            <el-tooltip effect="dark" :content="$t('content.personal.shbtg')" placement="top">
                                                <span
                                                    @click="agreeApprove(2, item.id)"
                                                ><i class="iconfont icon-weitongguo"></i></span>
                                            </el-tooltip>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <no-data :noDataVisible="reApprovalList.length == 0 && !loadingVisible"></no-data>
                        <div class="tablePages" v-if="!noDataVisible && !loadingVisible">
                            <el-pagination background
                                layout="total, prev, pager, next"
                                @current-change="handleCurrentChange"
                                :current-page="currentPage"
                                :page-size="pageSize"
                                :total="totalRows"
                                v-if="totalRows>pageSize"
                            >
                            </el-pagination>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <el-dialog
            :title="$t('content.personal.jjly')"
            :visible.sync="reasonDialogVisible"
            width="385px"
            :close-on-click-modal="false"
            >
            <el-input
                type="textarea"
                v-model="reason"
                autofocus
                :placeholder="$t('content.personal.qsrly')"
                @keyup.native.enter="disagree"></el-input>
            <div class="refuse-warn" v-if="refuseValidator">{{refuseContent}}</div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="reasonDialogVisible = false">{{$t('content.common.qx')}}</el-button>
                <el-button type="primary" @click="disagree">{{$t('content.common.qd')}}</el-button>
            </div>
        </el-dialog>

        <el-dialog
            :title="$t('content.common.tis')"
            :visible.sync="loadingDialogVisible"
            width="385px"
            :close-on-click-modal="false"
            >
            <div class="del-content">
                {{$t('content.personal.shz')}}...
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="loadingDialogVisible = false">{{$t('content.common.qd')}}</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import Search from 'common/Search'
    import { FormatTime, Message, FormatSize, EmphasizeName } from 'mixins/common';
    export default{
        mixins: [ FormatTime, Message, EmphasizeName ],
        data(){
            return {
                key: '',
                approvalList: [],
                order: 'asc',
                reasonDialogVisible: false,
                reason: '',
                id: '',
                url: '/privilege/approvalApplyForPrivliege',
                contentMessage: '',
                currentPage: 1,
                pageSize: 10,
                totalRows: 1,
                loadingVisible: true,
                sort: 'md_result',
                order: "asc",
                refuseValidator: false,
                refuseContent: '',
                tableHeader: [
                    {
                        name: this.$t('content.personal.sqr'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.personal.sqkb'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.personal.quanx'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.personal.ssxm'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.personal.syqxxm'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.dataset.datasetDetail.sqly'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.common.zhuangt'),
                        hasSort: true,
                        type: "md_result",
                    },
                    {
                        name: this.$t('content.personal.sqsj'),
                        hasSort: false
                    },
                    {
                        name: this.$t('content.personal.spcz'),
                        hasSort: false
                    },
                ],
                agreeArr: [],
                disAgreeOpt: false,
                loadingDialogVisible: false
            }
        },
        components: {
            Search
        },
        watch: {
            '$parent.approvalsActiveTab.component'(val){
                this.loadingVisible = true
                this.fetchData()
            },
        },
        created(){
            this.fetchData()
        },
        computed: {
            reApprovalList(){
                this.approvalList.forEach(val=>{
                    switch(val.md_result){
                        case 0:
                            val.status_mark = `<span style="color: orange;">${this.$t('content.personal.dsh')}</span>`
                            break;
                        case 1:
                            val.status_mark = `<span style="color: green;">${this.$t('content.personal.shtg')}</span>`
                            break;
                        case 2:
                            val.status_mark = `<span style="color: red;">${this.$t('content.personal.shbtg')}</span>`
                            break;
                        }
                })
                return this.approvalList
            }
        },
        methods: {
            reasonTxt(reason){
                if(reason == '当前用户没有在项目里面分配组，无法提交资源权限请求。'){
                    return this.$t('content.personal.myqxz')
                }else if(reason == '当前用户已经拥有该资源权限，不需要提交请求。'){
                    return this.$t('content.personal.yyqx')
                }else if(reason == 'get wrong message when approvling,please try again later'){
                    return this.$t('content.personal.leapidspsb')
                }else if(reason == 'approvle failed'){
                    return this.$t('content.personal.leapidspsb')
                }else if(reason == '你提交的资源请求正在等待审批，请无重复提交。'){
                    return this.$t('content.personal.qwcftj')
                }else{
                    let showReason = reason ? reason.replace(/(.{30})/g,'$1<br/>') : ''
                    return showReason
                }
            },
            closeResult(){
                this.fetchData()
            },
            disagree(){
                const params = {
                    id: this.id,
                    result: 2,
                    reason: this.reason
                }
                if(this.reason.length==0){
                    this.refuseValidator = true
                    this.refuseContent = this.$t('content.validator.qsrjjly')
                }else if(this.reason.length>200){
                    this.refuseValidator = true
                    this.refuseContent = this.$t('content.validator.cdbdcg', 200)
                }else{
                    this.refuseValidator = false

                    if(this.disAgreeOpt){
                        return;
                    }

                    this.disAgreeOpt = true;
                    this.postIsAgree(params)
                }

            },
            async agreeApprove(result, id){
                this.id = id
                let params = null
                if(result == 1){
                    if(this.agreeArr.indexOf(id) > -1){
                        this.loadingDialogVisible = true
                        return;
                    }

                    this.agreeArr.push(id)

                    params = {
                        id,
                        result,
                        reason: ''
                    }
                    this.postIsAgree(params)
                }else{
                    if(this.agreeArr.indexOf(id) > -1){
                        return;
                    }
                    this.disAgreeOpt = false;
                    this.reasonDialogVisible = true
                    this.reason = ''
                }
            },
            async postIsAgree(params){
                this.loadingDialogVisible = true;
                const res = await this.$http.post(this.url, params)
                if(res.body.statusCode == 200){
                    const {result, reason} = res.body.message
                    this.reasonDialogVisible = false
                    if(result == 1){
                        this.contentMessage = this.$t('content.personal.spcg')
                        this.message(`${this.contentMessage}`, "success")
                    }else{
                        this.contentMessage = this.reasonTxt(reason)
                        this.message(`${this.contentMessage}`, "error")
                    }
                    this.fetchData()
                }
                this.loadingDialogVisible = false;
                this.agreeArr.splice(this.agreeArr.indexOf(params.id), 1);
            },
            async fetchData({key='', page=1} = {}){
                this.key = key
                const vm = this
                this.loadingVisible = true

                var _approvalStatus;

                if(this.$parent.approvalsActiveTab.component == 'approvalsPending'){
                    _approvalStatus = 0
                }else{
                    _approvalStatus = 1
                }

                let url = `privilege/getApprovalList?keyword=${escape(key)}&rows=10&page=${page}&sort=${this.sort}&order=${vm.order}&approvalStatus=${_approvalStatus}`
                const res = await this.$http.get(url)
                if(res.body.statusCode == 200){
                    this.loadingVisible = false
                    this.approvalList = res.body.message.records

                    Object.assign(this.$data, res.body.message);
                }
            },
            tableSort({sort,order}){
                this.sort = sort;
                this.order = order;
                const params = {
                    key: this.key
                }
                this.fetchData(params);
            },
            handleCurrentChange(val){
                const params = {
                    page: val,
                    key: this.key
                }
                this.fetchData(params)
            },
        }
    }
</script>
<style lang="less">
    .owner-operator{
        .icon-cf-c100{
            color: #5bb65f;
        }
        .icon-weitongguo{
            color: #f64e43;
        }
    }
    .personal-approval{
         margin-left:-20px;
         margin-top: -20px;
         margin-right: -20px;
         a{
             color: #00699d;
             text-decoration: none;
         }
         textarea{
             height: 100px;
         }
    }
    .personal-approval-content{
        padding: 20px;
        .person-approval-waiting{
            background: #fff;
        }
    }
    .approval-content{
        text-align: center;
    }
</style>
