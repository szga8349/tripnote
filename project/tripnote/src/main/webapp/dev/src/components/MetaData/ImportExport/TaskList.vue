<template>
  <div class="boxCommon task-list-container">
    <div class="tableMulti">
      <div class="table-title" style="height: 40px;">
        <div class="table-title__text">
          <span>{{$t('content.dataset.importExport.rwjllb')}}</span>
        </div>
        <div style="float: right; margin-top: 10px;">
          <div class="date-picker-container" style="float: left; margin: -3px 10px 0 0; display: flex;">
            <span>{{$t('content.dataset.datasetDetail.xzsj')}}</span>
            <date-picker ref="datePicker" :direction="'right'" @fectchDate="getDate"></date-picker>
          </div>
          <Search
            :placeholder="$t('content.dataset.importExport.qsrrwmc')"
            emitName="taskListSearch"
            @taskListSearch="fetchData"></Search>
        </div>
      </div>

    </div>
    <table class="table table-bordered tableList">
          <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort"
          >
            <th>{{$t('content.dataset.datasetDetail.cz')}}</th>
          </table-header>
        <tbody v-if="!loadingVisible">
          <tr v-for="item in reRecords">
            <td>
              <div v-ellipsis="item.taskName"></div>
            </td>
            <td>
              <div v-ellipsis="item.businessName"></div>
            </td>
            <td>
              <span class="task-status"
                :class="{
                    statusSuccee: item.status == 'SUCCEEDED',
                    statusError: item.status == 'ERROR'
                  }">
                {{item.statusContent}}
              </span>
            </td>
            <td>
              <div v-ellipsis="formatTime(item.startTime*1000)"></div>
            </td>
            <td>
              <div v-ellipsis="formatTime(item.endTime*1000)"></div>
            </td>
            <td>
              <div class="configuration-operate"
                   v-if="item.statusContent != $t('content.common.zxz')">
                <a href="javascript:;"
                  @click="openDialog(item.message)">{{$t('content.dataset.importExport.ckrz')}}</a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <no-data :noDataVisible="records.length == 0 && !loadingVisible"></no-data>
      <div class="tablePages" v-if="!loadingVisible&&records.length != 0">
        <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="10"
        :total="totalRows">
        </el-pagination>
      </div>
      <loading :isloading="loadingVisible"></loading>
      <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogTitle"
      :close-on-click-modal="false">
        <div class="task-list-log">
          <div class="logMessage" v-html="logMessage"></div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible=false" type="primary">
            {{$t('content.common.qd')}}
          </el-button>
        </div>
      </el-dialog>
  </div>
</template>
<script>
import { FormatTime } from 'mixins/common'
import DatePicker from 'components/common/DatePicker'
import Search from 'common/Search'
  export default{
    name: 'TaskList',
    data(){
      return {
        tableHeader: [
          {name: this.$t('content.dataset.import.rwmc')},
          {name: this.$t('content.import.sjymc')},
          {name: this.$t('content.source.rwzt')},
          {
            name: this.$t('content.dataset.importExport.kssj'),
            type:"startTime",
            hasSort:true,
          },
          {
            name: this.$t('content.dataset.importExport.jssj'),
            type:"endTime",
            hasSort:true,
          },
        ],
        currentPage: 1,
        totalRows: 0,
        dialogVisible: false,
        dialogTitle: this.$t('content.dataset.importExport.rizhi'),
        currentPage: 1,
        totalRows: 0,
        records: [],
        sort:"startTime",
        order:"desc",
        loadingVisible: true,
        logMessage: '',
        startTime: '',
        endTime: '',
        searchKey: ''
      }
    },
    computed: {
      reRecords(){
        this.records.forEach(val=>{
          switch(val.status){
            case 'SUCCEEDED':
              val.statusContent = this.$t('content.common.chengg')
              break;
            case 'ERROR':
              val.statusContent = this.$t('content.common.shib')
              break;
            default:
               val.statusContent = this.$t('content.common.zxz')
          }
        })
        return this.records
      }
    },
    components:{
      DatePicker,
      Search
    },
    mixins: [FormatTime],
    created(){
      this.fetchData()
    },
    methods: {
      getDate(arr){
        let [begin_date, end_date] = arr
        this.startTime = Date.parse(new Date(`${begin_date} 00:00:00`))
        this.endTime = Date.parse(new Date(`${end_date} 23:59:59`))
        this.fetchData()
      },
      tableSort({sort,order}){
        this.sort = sort;
        this.order = order;
        const params = {
          key: this.searchKey
        }
        this.fetchData(params);
      },
      async fetchData({key=this.searchKey, page=1} = {}){
        this.searchKey = key
        this.loadingVisible = true
        const params = {
          page,
          rows: 10,
          sort: this.sort,
          order: this.order,
          taskName:  (key),
          startTime: this.startTime/1000,
          endTime: this.endTime/1000
        }
        // if(key != ''){
        //   Object.assign({taskName:  (key)}, params);
        // }
        const res = await this.$http.post('/systemImport/getTaskLog',
                                          JSON.stringify(params),
                                          {
                                            contentType: 'application/json',
                                            dataType: 'json'
                                          })
        const{ statusCode } = res.body
        if(statusCode == 200){
          this.loadingVisible = false
          Object.assign(this.$data, res.body.message)
        }
      },
      openDialog(message){
        this.dialogVisible = true
        message = message.replace(/\r/g,"<br/>");
        message = message.replace(/\n/g,"<br/>");
        message = message.replace(/\r\n/g,"<br/>");
        this.logMessage = message

        setTimeout(function(){
          $('.logMessage').scrollTop(0)
        }, 100)
      },
      handleCurrentChange(val) {
	    	this.fetchData({page: val, key: this.searchKey})
	  	},
    }
  }
</script>
<style lang="less">
  .task-list-container {
    .statusSuccee{
      color: rgb(34, 182, 111);
    }
    .statusError{
      color: red;
    }
  }
  .task-status{
    color: orange;
  }
  .task-list-log{
    word-break: break-all;
  }
  .logMessage{
    max-height: 500px;
    font-size: 12px;
    line-height: 24px;
    overflow-y: auto;
  }
</style>

