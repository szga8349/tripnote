<template>
  <div class="import-module-container">
    <el-row type="flex" justify="space-between" class="import-module-header">
      <el-col :span="12" @click.native="dataExport">
        <import-header-item
          :title="$t('content.dataset.datasetDetail.dcysj')"
          :content="$t('content.dataset.importExport.dcxtyydysj')"
          ></import-header-item>
      </el-col>
    </el-row>
    <div class="boxCommon">
      <div class="tableMulti">
        <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.importExport.dcjl')}}</span>
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
        <tbody v-if="records.length > 0">
          <tr v-for="item in records">
            <td>{{formatTime(item.oper_time)}}</td>
            <td>{{item.operator}}</td>
            <td>
              <!-- @click="exportSuccess(item.success_detail)" -->
              <span
                class="export-success"
                v-if="item.successCount>0"
                >
                {{$t('content.dataset.importExport.dccgt', [item.successCount])}}
              </span>
              <span v-if="item.successCount>0&&item.failCount>0">,</span>
              <!-- @click="exportFail(item.fail_detail)" -->
              <span
                class="export-fail"
                v-if="item.failCount>0">
                {{$t('content.dataset.importExport.dcsbt', [item.failCount])}}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tablePages" v-if="records.length > 0 && !loading">
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
      :title="$t('content.dataset.datasetDetail.xzdcdysj')"
      :visible.sync="dialogVisible"
      width="540px"
      :close-on-click-modal="false">
      <div class="dbList businessCheckBox" >
        <el-form class="check-group-container"
        :model="exportTree"
        ref="exportTree">
          <div class="addtheme-searchWrap">
            <input type="text"
              :placeholder="$t('content.dataset.businessDetail.qsrnyssdnr')"
              v-model="searchContent"
              @keyup.enter="searchClick">
              <input type="text" style="display: none;" />
            <a href="javascript:;"
               class="el-icon-search"
               @click="searchClick"></a>
          </div>
          <loading :isloading="treeLoading"></loading>
          <div style="position: relative;">
            <div v-if="treeMoreLoading" class="tree-dialog-loading-container">
              <more-loading :isTree="true" :isloading="treeMoreLoading"></more-loading>
            </div>
            <no-data v-if="!treeLoading && treeData.length == 0 && !treeMoreLoading"></no-data>
            <el-checkbox-group v-model="exportList" v-if="!treeLoading && treeData.length != 0">
              <physical-export-tree :model="treeData"></physical-export-tree>
            </el-checkbox-group>
          </div>
        </el-form>
        <div class="import-validator" v-if="validatorShow">
          {{$t('content.validator.zsxzyx')}}
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">{{$t('content.common.qx')}}</el-button>
		    <el-button
          type="primary"
          @click="submitData"
          v-loading.fullscreen.lock="fullscreenLoading">
          {{$t('content.common.qd')}}
        </el-button>
		  </div>
    </el-dialog>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogDetailVisible"
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
        <el-button type="primary" @click="dialogDetailVisible=false">确 认</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import importHeaderItem from './importHeaderItem'
  import PhysicalExportTree from '../Dataset/PhysicalExportTree/PhysicalExportTree'
  import { mapState } from 'vuex'
  import { FormatTime, reStructureTreeData } from 'mixins/common'
  import FileSaver from 'file-saver'

  export default{
    components: {
      importHeaderItem,
      PhysicalExportTree
    },
    mixins: [ FormatTime, reStructureTreeData ],
    data(){
      const vm = this
      return {
        dialogTitle: '',
        dialogVisible: false,
        tableHeader:[{
          name: vm.$t('content.dataset.importExport.dcsj'),
          type: 'oper_time',
          hasSort: true
        },
        {
          name: vm.$t('content.dataset.importExport.czr'),
          type: "operator",
          hasSort: false
        },
        {
          name: vm.$t('content.dataset.importExport.dcjg'),
          type: "result",
          hasSort: false
        }],
        sort:"",
        order:"asc",
        treeData: [],
        showList: [],
        sort:"oper_time",
        order:"desc",
        records: [],
        currentPage: 1,
        totalRows: 0,
				totalRows: 1,
        dialogDetailVisible: false,
        dialogTitle: '',
        loading: true,
        treeLoading: true,
        validatorShow: false,
        fullscreenLoading: false
      }
    },
    created(){
      this.fetchExportData()
    },
    computed: {
      ...mapState({
        exportList: 'exportList',
  			exportDBList: 'exportDBList',
        treeMoreLoading: 'treeBusinessLoading'
  		}),
    },
    watch: {
      dialogVisible(val){
        this.validatorShow = val ? false : this.validatorShow
      },
    },
    methods: {
      async searchClick(){
        const vm = this;
        this.treeData = []
        this.treeLoading = true
        this.$store.dispatch('saveExportSearch', this.searchContent)
				const targetPath = this.$route.query.path;
				let url = `/excelMetadataInfo/treeSelect`;
				let params = {
					targetTopicPath: targetPath,
					level:0,
					path:"",
					key:vm.searchContent,
          page: 1,
          rows: 20
				}
				let headerData = {contentType: 'application/json', dataType: 'json'}
				const res = await this.$http.post(url,params,headerData)
				const {statusCode,message} = res.body
        const {records} = message
				if(statusCode == 200){
					this.treeLoading = false
					this.setOpen(records)
					this.treeData = records
          this.noDataVisible = this.treeData.length == 0 ? true : false
          if(this.$store.state.getIsBusiness){
            vm.reStructureBusinessData()
          }else{
            vm.reStructureTreeData()
          }
				}else{
					vm.treeData = []
          this.noDataVisible = true
				}
      },
      reStructureBusinessData(){
        let hasNextPage = true
        let path = ''
        function checkChildren(data){
          data.forEach((val, index)=>{
            let arrPath = val.path.split('/')
            arrPath.pop()
            let newPath = arrPath.join('/')
            if(val.children){
              if(val.children.hasNextPage){
                const {path, level} = val
                val.children.records.push({type: 'more', children:[], path, level})
              }else{
                checkChildren(val.children.records)
              }
            }
          })
        }
        checkChildren(this.treeData)
      },
      tableSort({sort,order}){
        this.sort = sort;
				this.order = order;
				this.fetchExportData();
      },
      dataExport(){
        this.$store.dispatch('clearExportCheckList', [])
        this.searchContent = ''
        this.$store.dispatch('saveExportSearch', '')
        this.dialogVisible = true
        this.fetchTreeData()
      },
      async fetchExportData(page=1){
        this.loading = true
        const res = await this.$http.post(`/excelMetadataInfo/getExportLog?sort=${this.sort}&order=${this.order}&page=${page}&rows=10`)
        const {statusCode, message} = res.body
        this.loading = false
        statusCode == 200 && Object.assign(this.$data, res.body.message)
      },
      async fetchTreeData(){
        const vm = this
				vm.treeLoading = true
				const targetPath = this.$route.query.path
        let url = '/excelMetadataInfo/treeSelect'
				let params = {
					targetTopicPath: targetPath,
					level:0,
					path:"",
          page: 1,
          rows: 20
				}
				let headerData = {contentType: 'application/json', dataType: 'json'}
				const res = await this.$http.post(url ,params,headerData)
				const {statusCode, message} = res.body
        vm.treeLoading = false
				if(statusCode === 200){
          const {records} = message
          records.length>0 && vm.setOpen(records)
					vm.treeData = records
          vm.reStructureTreeData()
				}
      },
      setOpen(value){
				const vm = this
				if(!value.length){
					return;
				}
				value.map(val=>{
					if(val.children){
						vm.$set(val, 'open', true);
						vm.setOpen(val.children.records)
					}else{
						vm.$set(val, 'open', false)
					}
				})
			},
      async submitData(){
        if(this.exportList.length==0 && this.exportDBList.length == 0){
          this.validatorShow = true
          return;
        }else{
          this.validatorShow = false
          let newExportList = []
          this.exportList.forEach(val=>{
            typeof val == 'number' && newExportList.push(val)
          })
          const datasetIds = newExportList && newExportList.join(',')
          let urn = this.exportDBList && this.exportDBList.join(',')
          const lang = window.localStorage.getItem('lang')

          let ignoreWarn = 'true'

          // this.fullscreenLoading = true
          this.$http.post('/excelMetadataInfo/export', {urn, datasetIds,lang, ignoreWarn, responseparameterType: 'arraybuffer'}, {responseType: 'arraybuffer'})
                    .then(res=>{
                      // this.fullscreenLoading = false
                      this.dialogVisible = false
                      this.downFile(res.body)
                    })
        }

      },
      async downFile(data){
        let blob = new Blob([data], {type: 'application/vnd.ms-excel'})
        let date = new Date()
        let fileName = `${this.$t('content.dataset.datasetDetail.dcysj')}${date.getFullYear()}-${date.getMonth()<9?('0'+(date.getMonth()+1)): (date.getMonth()+1)}-${date.getDate()<10?('0'+date.getDate()) : date.getDate()}-${date.getHours()}-${date.getMinutes()}.xlsx`

        await FileSaver.saveAs(blob, fileName);


        // if (window.navigator.msSaveOrOpenBlob) {
        //   await navigator.msSaveBlob(blob, fileName);
        // } else {
        //     let link = document.createElement('a');
        //     link.style.display = 'inherit'
        //     link.href = window.URL.createObjectURL(blob);
        //     link.download = fileName;
        //     $('.searchCon').append(link)
        //     link.click()
        //     await window.URL.revokeObjectURL(link.href);
        // }
        this.fetchExportData()
        this.$store.dispatch('clearExportCheckList', [])
      },
      exportSuccess(detail){
        const vm = this
        this.showList = []
        this.dialogDetailVisible = true
        this.dialogTitle = this.$t('content.dataset.importExport.dccgt', [detail.length])
        detail.forEach(val=>{
          this.showList.push({name: val.tableName, state: vm.$t('content.dataset.importExport.dccg')})
        })
      },
      exportFail(detail){
        const vm = this
        this.showList = []
        this.dialogDetailVisible = true
        this.dialogTitle = this.$t('content.dataset.importExport.dcsbt', [detail.length])
        detail.forEach(val=>{
          const reason = vm.errorCodeTranslate(val.errorCode)
          this.showList.push({name: val.tableName, state: vm.$t('content.dataset.importExport.dcsb'), reason})
        })
      },
      handleCurrentChange(val) {
	    	this.fetchExportData(val)
	  	},
    }
  }
</script>
<style lang="less">
.import-module-container{
  .el-dialog--small{
    width: 540px;
  }
}
</style>
