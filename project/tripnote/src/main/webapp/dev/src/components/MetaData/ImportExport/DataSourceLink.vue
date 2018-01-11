<template>
  <div class="boxCommon">
    <div class="tableMulti">
      <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.datasetDetail.sjylb')}}</span>
          </div>
          <div class="configuration-list-header">
            <el-button
                type="primary"
                @click="openDialog()">
                  {{$t('content.dataset.importExport.xjsjy')}}
              </el-button>
              <Search
                :placeholder="$t('content.dataset.importExport.qsrsjymc')"
                emitName="sourceSearch"
                @sourceSearch="fetchData"></Search>

          </div>
        </div>
    </div>
    <table class="table table-bordered tableList" style=" margin-top: 10px;">
          <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort">
            <th>{{$t('content.dataset.datasetDetail.cz')}}</th>
          </table-header>
        <tbody v-if="!loadingVisible">
          <tr v-for="(item, index) in records">
            <td>
              <div class="table-search-item" :title="item.businessName" v-html="keyWord ? item.businessName.replace(eval('/('+keyWord+')/ig'), '<b>'+'$1'+'</b>'):item.businessName"></div>
            </td>
            <td>
              <div v-ellipsis="item.dataSourceType"></div>
            </td>
            <td>
              <div v-ellipsis="item.url"></div>
            </td>
            <td>
              <div v-ellipsis="item.creator"></div>
            </td>
            <td>
              <div v-ellipsis="formatTime(item.createDate)"></div>
            </td>
            <td>
              <div v-ellipsis="item.description"></div>
            </td>
            <td>
              <div class="configuration-operate">
                <a href="javascript:;"
                  @click="openDialog(item)">{{$t('content.common.bj')}}</a>
                <a href="javascript:;" v-if="item.isUsing == 0"
                  @click="deleteConfirm(item.businessName, item.id)">{{$t('content.common.sc')}}</a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tablePages" v-if="!loadingVisible && totalRows>10">
        <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="10"
        :total="totalRows">
        </el-pagination>
      </div>
      <loading :isloading="loadingVisible"></loading>
      <no-data v-if="!loadingVisible && records.length == 0"></no-data>
      <create-source
        :dialogVisible="dialogVisible"
        :dialogTitle="dialogTitle"
        :params="sourceParams">
      </create-source>


      <el-dialog
        :title="$t('content.common.tis')"
        width="385px"
        :visible.sync="dialogTipsVisible">
          <div class="dialogTips">
            {{dialogTipsTxt}}
            "{{showBusinessName && showBusinessName}}"
          </div>
          <span slot="footer" class="dialog-footer">
             <el-button @click="dialogTipsVisible=false">{{$t('content.common.qx')}}</el-button>
             <el-button type="primary" @click="deletItem">{{$t('content.common.qd')}}</el-button>
          </span>
      </el-dialog>
  </div>
</template>
<script>
  import CreateSource from './CreateSource'
  import Search from 'common/Search'
  import Bus from 'utils/bus'
  import { Message, FormatTime } from 'mixins/common'
  export default{
    name: 'DataSourceLink',
    mixins: [ Message, FormatTime ],
    components: {
      CreateSource,
      Search
    },
    data(){
      return {
        tableHeader: [
          {name: this.$t('content.import.sjymc')},
          {name: this.$t('content.dataset.importExport.sjylx')},
          {name: this.$t('content.dataset.importExport.ljxx')},
          {name: this.$t('content.dataset.importExport.cjr')},
          {
              name: this.$t('content.dataset.businessDetail.cjsj'),
              type:"createDate",
              hasSort:true,
          },
          {name: this.$t('content.dataset.importExport.sjyms')},
        ],
        currentPage: 1,
        totalRows: 0,
        dialogTitle: '',
        dialogVisible:  false,
        records: [],
        sourceParams: {},
        loadingVisible: true,
        keyWord: '',
        loadingVisible: true,
        dialogTipsVisible: false,
        dialogTipsTxt: this.$t('content.dataset.importExport.qrsc'),
        showBusinessName: '',
        id: '',
        sort: "createDate",
        order: "asc",
      }
    },
    created(){
      Bus.$emit('routerTitle', this.$route.title)
      this.fetchData()
    },
    computed: {
      setSourceLink(){
        return this.$store.state.setSourceLink
      }
    },
    watch: {
      setSourceLink(val){
        if(val){
          let content;
          if(this.dialogTitle == this.$t('content.dataset.importExport.xgsjyxx')) content = this.$t('content.dataset.importExport.bjcg')
          if(this.dialogTitle == this.$t('content.dataset.importExport.xjsjy')) content = this.$t('content.dataset.importExport.xjsjycg')
          content && this.message(content, 'success')
          this.fetchData()
        }
      }
    },
    methods: {
      tableSort({sort,order}){
            this.sort = sort;
            this.order = order;
            const params = {
                key: this.key
            }
            this.fetchData(params);
      },
      eval(str){
          return eval(str)
      },
      openDialog(params){
        this.dialogTitle = params?this.$t('content.dataset.importExport.xgsjyxx') : this.$t('content.dataset.importExport.xjsjy')
        this.sourceParams = params
        this.dialogVisible = true
      },
      async fetchData({page=1, key=''}={}){
        this.keyWord = key
        this.dialogVisible = false
        this.loadingVisible = true
        const res = await this.$http.get(`/datasource/getDatasource?page=${page}&rows=10&businessName=${key}&sort=${this.sort}&order=${this.order}`)
        const {statusCode} = res.body

        if(statusCode == 200){

          this.loadingVisible = false
          Object.assign(this.$data, res.body.message)
          this.$store.dispatch('fetchDataSourceLink', false)
        }else{
          content && this.message(content, 'error')
        }
      },
      handleCurrentChange(val) {
	    	this.fetchData({page: val, key: this.keyWord})
      },
      deleteConfirm(businessName, id){
        this.showBusinessName = businessName
        this.id = id
        this.dialogTipsVisible = true
      },
      async deletItem(){
        this.dialogTitle = ''
        const res = await this.$http.post(`/datasource/delete/${this.id}`)
        const {statusCode} = res.body
        if(statusCode == 200){
          this.message(this.$t('content.common.sccg'), 'success')
          this.fetchData()
        }else{
          this.message(this.$t('content.dataset.importExport.sjyljyjb')+'!', 'error')
        }
        this.dialogTipsVisible = false
      }
    }
  }
  // width: 440px;
</script>
<style lang="less">
  .configuration-form{
    .el-form-item{
      margin-bottom: 15px;
    }
    .el-input,.el-textarea{
      width: 100%;
    }
    .el-select .el-input{
      max-width: 150px;
    }
  }

.el-dialog__wrapper .el-dialog__footer .test-link{
  width: 130px;
}
.source-link-dialog {
  .el-row{
    margin: 0;
  }
  .el-form-item__content{
    margin-left: 156px !important;
  }
  .from-inline{

    .el-input{
      width: 100%;
    }
    .el-col:nth-of-type(2){
      .el-form-item__label{
        width: 125px !important;
      }
      .el-form-item__content{
        margin-left: 127px !important;
      }
    }
  }
  .el-dialog--small{
    width: 55%;
  }
  .el-form-item__label{
    width: 155px !important;
  }
}
</style>
