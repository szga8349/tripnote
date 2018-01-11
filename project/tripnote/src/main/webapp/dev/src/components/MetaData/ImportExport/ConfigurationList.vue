<template>
  <div class="boxCommon configuration-list">
    <div class="tableMulti">
        <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.importExport.rwlb')}}</span>
          </div>
          <div class="configuration-list-header">
            <el-button
              type="primary"
              @click="createTask">
                {{$t('content.dataset.importExport.xjrw')}}
            </el-button>
            <Search
              :placeholder="$t('content.dataset.importExport.qsrrwmc')"
              emitName="taskSearch"
              @taskSearch="fetchData"></Search>
          </div>
        </div>
      </div>
      <table class="table table-bordered tableList">
          <table-header
            :tableHeader = "tableHeader"
            @tableSort="tableSort">
            <th>{{$t('content.dataset.datasetDetail.cz')}}</th>
          </table-header>
        <tbody v-if="!loadingVisible">
          <tr v-for="(item, index) in records">
            <td>
              <div class="table-search-item" :title="item.taskName" v-html="searchKey ? item.taskName.replace(eval('/('+searchKey+')/ig'), '<b>'+'$1'+'</b>'):item.taskName">
              </div>
            </td>
            <td>
              <div v-ellipsis="item.taskDesc"></div>
            </td>
            <td>{{item.dataSourceType}}</td>
            <td>
              <div v-ellipsis="item.dataSourceName"></div>
            </td>
            <td>
              <div v-ellipsis="formatTime(item.createDate)"></div>
            </td>
            <td>{{item.creator}}</td>
            <td>{{cronTypeDesc(item.cronExpr)}}</td>
            <td>
              <span class="task-status" :class="{taskStatusActive: item.isActive == 'Y'}">
                {{item.isActive == 'Y' ? $t('content.dataset.datasetDetail.zhengc') : $t('content.dataset.importExport.tingy')}}
              </span>
            </td>
            <td>
              <div v-if="item.isActive == 'Y'" v-ellipsis="formatTime(item.nextRun*1000)"></div>
            </td>
            <td>
              <div class="configuration-operate">
                <a href="javascript:;"
                  class="taskStart"
                  v-if="item.isActive=='N'"
                  @click="operate('start', item.id)">
                  {{$t('content.dataset.importExport.qy')}}
                </a>
                <a href="javascript:;"
                  class="taskStop"
                  v-else
                  @click="operate('pending', item.id)">
                  {{$t('content.dataset.importExport.tingy')}}
                </a>
                <a
                  href="javascript:;"
                  @click="modifyTask(item.id)"
                  v-if="item.status != 'REQUESTED' && item.status != 'STARTED' && item.status != 'PROCESSING'"
                >{{$t('content.common.xiug')}}</a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <no-data v-if="!loadingVisible && records.length == 0"></no-data>
      <loading :isloading="loadingVisible"></loading>
      <div class="tablePages" v-if="!loadingVisible && totalRows>10">
        <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="10"
        :total="totalRows">
        </el-pagination>
      </div>

      <!-- ȷ�Ͽ� -->
      <el-dialog
        :title="$t('content.common.tis')"
        :visible.sync="confirmDialogVisible"
        width="385px"
        :close-on-click-modal="false"
        @click.native.stop>
        <div class="del-content">
          {{confirmMessage}}?
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button
            @click="confirmDialogVisible = false">
            {{$t('content.common.qx')}}
          </el-button>
          <el-button
            type="primary" @click="operatePost()">
            {{$t('content.common.qd')}}
          </el-button>
        </div>
      </el-dialog>

  </div>
</template>
<script>
  import Search from 'common/Search'
  import { FormatTime, Message } from 'mixins/common';
  import Bus from 'utils/bus'
  import { httpRequest } from 'utils'
  export default{
    data(){
      return {
        taskName: '',
        currentPage: 1,
        totalRows: 0,
        records: [],
        loadingVisible: true,
        sort: 'createDate',
        order: 'asc',
        searchKey: '',
        tableHeader:[
          {
            name: this.$t('content.dataset.import.rwmc')
          },
          {
            name: this.$t('content.dataset.importExport.rwms')
          },
          {
            name: this.$t('content.dataset.importExport.sjylx')
          },
          {
            name: this.$t('content.import.sjymc'),
          },
          {
            name: this.$t('content.dataset.businessDetail.cjsj'),
            hasSort: true,
            type: 'createDate',
          },
          {
            name: this.$t('content.dataset.importExport.cjr')
          },
          {
            name: this.$t('content.dataset.importExport.ddlx')
          },
          {
            name: this.$t('content.source.rwzt')
          },
          {
            name: this.$t('content.dataset.importExport.xczxsj'),
          },
        ],
        taskId: '',
        taskType: '',
        confirmDialogVisible: false,
        confirmMessage: ''
      }
    },
    mixins: [ FormatTime, Message ],
    components: {
      Search
    },
    created(){
      Bus.$emit('routerTitle', this.$route.title)
      this.fetchData()
    },
    methods: {
      eval(str){
        return eval(str)
      },
      modifyTask(id){
        this.$router.push({name: 'Configuration', params: {id}})
      },
      cronTypeDesc(type){
        switch(type) {
          case '0 0 0 1 1 ? 2017':
            return this.$t('content.dataset.importExport.ycx')
            break;
          case '0 0 0/1 * * ?':
            return this.$t('content.dataset.importExport.mxs')
            break;
          case '0 0 0 1/1 * ?':
            return this.$t('content.dataset.importExport.meit')
            break;
          case '0 0 0 ? * MON':
            return this.$t('content.dataset.importExport.meiz')
            break;
          case '0 0 0 1 * ?':
            return this.$t('content.dataset.importExport.meiy')
            break;
          default:
            return '一次性'
        }
      },
      operate(type, id){
        this.taskType = type
        this.taskId = id
        this.confirmMessage = type == "start" ? this.$t('content.dataset.importExport.qdqy') : this.$t('content.dataset.importExport.qdty')
        this.confirmDialogVisible = true
      },

      async operatePost(){
        var type = this.taskType
        var id = this.taskId

        let active = type=="start" ? 'Y' : 'N'
        const res = await this.$http.post(`/systemImport/updateActive/${id}/${active}`)
        const {statusCode} = res.body
        if(statusCode == 200){
          let message = active == 'Y' ? this.$t('content.dataset.importExport.qy') : this.$t('content.dataset.importExport.tingy')
          this.message(`${message}${window.localStorage.lang == 'en'? ' ':''}${this.$t('content.common.chengg')}!`, 'success')
          this.confirmDialogVisible = false

          this.fetchData({page:this.currentPage, key:this.searchKey})
        }
      },
      tableSort({sort, order}){
        Object.assign(this.$data, {sort, order})
        const params = {
          key: this.searchKey
        }
        this.fetchData(params)
      },
      createTask(){
        const ruleForm = {
          taskName:'',
          taskDesc: '',
          refId: '',
          cron: '',
          getSample: 'false',
          serverUrl: '',
          useKerberos: 'false',
          principal: '',
          keytab: '',
          serverUsername: '',
          serverPassword: '',
        }
        this.$router.push('Configuration')
        this.$store.dispatch('createTask', ruleForm)
      },
      async fetchData({page=1, key=''}={}){
        this.searchKey = key
        this.loadingVisible = true
        const res = await this.$http.get(`/systemImport/getTask?page=${page}&rows=10&taskName=${key}&sort=${this.sort}&order=${this.order}`)
        const {statusCode} = res.body
        if(statusCode == 200){
          this.loadingVisible = false
          Object.assign(this.$data, res.body.message)
        }
      },
      handleCurrentChange(val) {
	    	this.fetchData({page: val, key: this.searchKey})
      },
    }
  }
</script>
<style lang="less">
  .configuration-list{
    .taskStatusActive{
      color: green;
    }
    .taskStop{
      color: red;
    }
    .table-title{
      a{
        font-size: 12px;
      }
      margin-bottom: 10px;
    }

    .configuration-operate{
      a{
        text-decoration: none;
      }
      .task-running{
        padding: 3px 5px;
        background: #5ab85a;
        color: #fff;
        .el-icon-caret-right{
          font-size: 10px;
        }
      }
      .task-pending{
        padding: 3px 5px;
        border: 1px solid #fe9a02;
        color: #fe9a02;
        .task-pending-icon{
          display: inline-block;
          width: 6px;
          height: 10px;
          border-right: 2px solid #fe9a02;
          border-left: 2px solid #fe9a02;
        }
      }
    }
  }
</style>
