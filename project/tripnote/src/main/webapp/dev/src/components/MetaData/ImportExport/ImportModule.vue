<template>
  <div>
    <el-row type="flex" justify="space-between" class="import-module-header">
      <el-col :span="12" @click.native="toImportRoute">
        <import-header-item
          :title="$t('content.dataset.importExport.dcwj')"
          :content="$t('content.dataset.importExport.scybjhdysj')"
          id="2"
        ></import-header-item>
      </el-col>
      <el-col :span="12" @click.native="downloadTemplate">
        <import-header-item
          justify="flex-end"
          :title="$t('content.dataset.importExport.xzmb')"
          :content="$t('content.dataset.importExport.xzysjdrmb')"
          id="1"
        ></import-header-item>
      </el-col>
    </el-row>
    <div class="boxCommon">
      <div class="tableMulti">
        <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.importExport.drjl')}}</span>
          </div>
        </div>
      </div>

      <table v-if="!loading" class="table table-bordered tableList">
          <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort">
          </table-header>
        <tbody v-if="records.length>0">
          <tr v-for="item in records">
            <td>{{formatTime(item.oper_time)}}</td>
            <td>{{item.operator}}</td>
            <td>
              <div v-ellipsis="item.file_name"></div>
            </td>
            <td>
              <!-- @click="exportSuccess(item.success_detail)" -->
              <span
                class="export-success"
                v-if="item.successCount>0">
                {{$t('content.dataset.importExport.drcgt', [item.successCount])}}
              </span>
              <span v-if="item.successCount>0&&item.failCount>0">,</span>
              <!-- @click="exportFail(item.fail_detail)" -->
              <span
                class="export-fail"
                v-if="item.failCount>0">
                {{$t('content.dataset.importExport.drsbt', [item.failCount])}}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tablePages" v-if="records.length !== 0 && !loading">
        <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="10"
        :total="totalRows"
        v-if="totalRows>10">
        </el-pagination>
      </div>

      <loading :isloading="loading"></loading>
      <no-data v-if="!loading && records.length == 0"></no-data>
    </div>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      custom-class="import-export-dialog">
      <ul>
        <li v-for="item in showList">
          {{item.name ? item.name : ''}},
          {{item.state ? item.state : ''}}
          <span v-if="item.reason">,</span>
          {{item.reason ? item.reason : ''}}
        </li>
      </ul>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible=false">确 认</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import importHeaderItem from './importHeaderItem'
  import { FormatTime } from 'mixins/common'
  import FileSaver from 'file-saver'
  export default{
    components: {
      importHeaderItem
    },
    mixins: [ FormatTime ],
    data(){
      return {
        dialogTitle: '',
        dialogVisible: false,
        tableHeader:[{
          name: this.$t('content.dataset.importExport.drsj'),
          type: 'oper_time',
          hasSort:true
        },
        {
          name: this.$t('content.dataset.importExport.czr'),
          type: "operator",
          hasSort: false
        },
        {
          name: this.$t('content.dataset.importExport.wjm'),
          type: "fileName",
          hasSort: false
        },
        {
          name: this.$t('content.dataset.importExport.drjg'),
          type: "result",
          hasSort: false
        }],
        sort:"oper_time",
        order:"desc",
        showList: [],
        records: [],
        currentPage: 1,
				totalRows: 1,
        loading: true,
      }
    },
    created(){
      this.fetchImportData()
    },
    methods: {
      toImportRoute(){
        this.$store.dispatch('saveRouter', 'ImportModule')
        this.$router.push({name: 'DataImport'})
      },
      tableSort({sort,order}){
        this.sort = sort;
				this.order = order;
				this.fetchImportData();
      },
      exportSuccess(detail){
        const vm = this
        this.showList = []
        this.dialogVisible = true
        this.dialogTitle = this.$t('content.dataset.importExport.drcgt', [detail.length])
        detail.forEach(val=>{
          this.showList.push({name: val.tableName, state: vm.$t('content.dataset.import.drcg')})
        })
      },
      exportFail(detail){
        const vm = this
        this.showList = []
        this.dialogVisible = true
        this.dialogTitle = `导入失败${detail.length}条`
        detail.forEach(val=>{
          const reason = vm.errorCodeTranslate(val.errorCode)
          this.showList.push({name: val.tableName, state: vm.$t('content.dataset.import.drsb'), reason})
        })
      },
      errorCodeTranslate(code){
        const vm = this
        let reason = ''
        switch(code){
          case '20001':
            reason = vm.$t('content.dataset.import.mbcw')
            break;
          case '20002':
            reason = vm.$t('content.dataset.import.myqx')
            break;
          case '20003':
            reason = vm.$t('content.dataset.import.kbbcz')
            break;
          case '20004':
            reason = vm.$t('content.dataset.import.sjkpzmzd')
            break;
          case '80001':
            reason = vm.$t('content.dataset.datasetDetail.xtyc')
            break;
        }
        return reason;
      },
      async downloadTemplate(){
        const lang = window.localStorage.getItem('lang')
        const res = await this.$http.post(`/excelMetadataInfo/exportTemplate/${lang}`,
          {responseparameterType: 'arraybuffer'}, {responseType: 'arraybuffer'})
        if(res.status == 200){
          var blob = new Blob([res.body], {type: 'application/vnd.ms-excel'})
          FileSaver.saveAs(blob, this.$t('content.dataset.import.dcwjm')+".xlsx");

          // const data = res.body
          // const blob = new Blob([data], {type: 'application/vnd.ms-excel'})
          // const fileName = 'Metadata.xlsx'
          // if (window.navigator.msSaveOrOpenBlob) {
          //   navigator.msSaveBlob(blob, fileName)
          // } else {
          //   const link = document.createElement('a')
          //   link.style.display = 'inherit'
          //   link.href = window.URL.createObjectURL(blob)
          //   link.download = fileName;
          //   $('.searchCon').append(link)
          //   link.click()

          //   // window.location = link.href;
          //   window.URL.revokeObjectURL(link.href)
          // }
        }
      },
      async fetchImportData(page=1){
        this.loading = true
        const res = await this.$http.post(`/excelMetadataInfo/getImportLog?sort=${this.sort}&order=${this.order}&page=${page}&rows=10`)
        this.loading = false
        const {statusCode} = res.body
        statusCode == 200 && Object.assign(this.$data, res.body.message)
      },
      handleCurrentChange(val) {
	    	this.fetchImportData(val)
	    	console.log(`当前页: ${val}`);
	  	},
    }
  }
</script>
