<template>
  <div class="searchCon searchCon__search">
    <div class="search-line">
      <div class="search-container">
        <div class="data-search" :class="{'has-flex-grow': hasImport}">
          <div class="searchWrap" v-if="searchVisible">
            <input type="text"
            :placeholder="$t('content.common.ssgjz')"
            :title="treeSearchKeywords"
            v-model="treeSearchKeywords"
            @keyup.enter="treeSearch">
            <span v-if="treeSearchKeywords.length>0"
              class="tree-search-input-delete el-icon-circle-close"
              @click="resetTreeData"
              ></span>
            <a href="javascript:;" class="tree-control-icon" @click="treeSearch"></a>
          </div>
          <h4 v-else class="dataset-tree-title">{{getIsBusiness ? $t('content.header.sjj') : $t('content.header.sjj')}}
            <i class="iconfont icon-shaixuan" @click="filterOptsConToggle"></i>
          </h4>
        </div>
        <div class="data-controler">
          <i v-if="!searchVisible"
          @click="showSearchInput"
          class="tree-control-icon icon1"
          :title="$t('content.header.sscx')"></i>
          <i class="tree-control-icon icon3"
            @click.stop="dataExport"
            v-if="hasImport"
            :title="$t('content.header.daoc')"
          ></i>
          <i class="tree-control-icon icon2"
            @click.stop="pushToImport"
            v-if="hasImport && hasPrivilege"
            :title="$t('content.header.daor')"
          ></i>
        </div>

        <div class="filterOpts" v-if="filterOptsConVisible">
            <ul>
              <li>
                <el-checkbox class="cb" v-model="filterOptsActive">
                  {{$t('content.tree.xsactivedysj')}}
                </el-checkbox>
              </li>
              <li>
                <el-checkbox class="cb" v-model="filterOptsDeleted">
                  {{$t('content.tree.xsdleteddysj')}}
                </el-checkbox>
              </li>
              <li>
                <el-checkbox class="cb" v-model="filterOptsUnknown">
                  {{$t('content.tree.xsunkown')}}
                </el-checkbox>
              </li>
            </ul>
            <el-button class="btnSubmit" :disabled="isDisable" type="primary" 
             @click.native="filterOptsSubmit()">{{$t("content.common.qd")}}</el-button>

            <el-button class="btnSubmit" :disabled="isDisable" type="default" 
             @click.native="filterOptsConHide">{{$t("content.common.qx")}}</el-button>
          </div>
      </div>
    </div>

      
    <el-dialog
      :title="$t('content.dataset.datasetDetail.xzdcdysj')"
      width="540px"
      :visible.sync="dialogVisible"
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
          <loading :isloading="loadingVisible"></loading>
          <div style="position: relative;">
            <div v-if="treeBusinessLoading" class="tree-dialog-loading-container">
              <more-loading :isTree="true" :isloading="treeBusinessLoading"></more-loading>
            </div>
            <el-checkbox-group v-model="exportList" v-if="!loadingVisible">
              <no-data :noDataVisible="noDataVisible"></no-data>
              <physical-export-tree
                :model="treeData"
                v-if="!noDataVisible">
              </physical-export-tree>
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
  </div>
</template>
<script>
  import PhysicalExportTree from './PhysicalExportTree/PhysicalExportTree'
  import { mapState } from 'vuex'
  import { testPrivilege, reStructureTreeData, Message } from 'mixins/common'
  import Bus from 'utils/bus'
  import FileSaver from 'file-saver'

  export default{
    name: 'TreeHeader',
    mixins: [testPrivilege, reStructureTreeData, Message],
    components: {
      PhysicalExportTree
    },
    data(){
      return {
        fullscreenLoading: false,
        treeSearchKeywords: '',//搜索关键字
        dialogVisible: false,
        treeData: [],
        searchVisible: false,
        hasImport: false,
        validatorShow: false,
        searchContent: '',
        loadingVisible: true,
        noDataVisible: false,

        filterOpts: [],
        filterOptsActive: true,
        filterOptsDeleted: true,
        filterOptsUnknown: true,
        filterOptsConVisible: false,
        thisRouter: '',
        filterTimer: ''
      }
    },
    // treeSearchKeywords
    async created(){
      this.hasImport = /Physical/.test(this.$route.path) ? true : false
      this.testPrivilege('/excelMetadataInfo/import')
      this.filterOptsActive = true
      this.filterOptsDeleted = true
      this.filterOptsUnknown = true
      this.changeFilterOpts()
      this.thisRouter = this.$route.name

      // var vm = this;

      // console.log($('#SearchCon'))

      // $('#SearchCon').click(function(event) {
      //   alert(123)
      //   vm.filterOptsConVisible = false;
      // });
      // this.filterOptsActive = this.tableTypeOpts.indexOf(0) > -1 ? true : false;
      // this.filterOptsDeleted = this.tableTypeOpts.indexOf(1) > -1 ? true : false;
      // this.filterOptsUnknown = this.tableTypeOpts.indexOf(2) > -1 ? true : false;
    },
    mounted(){
      this.$nextTick(()=>{
        const vm = this
        document.onclick = function (e) {
          let target = $(e.target).hasClass('searchWrap') || $(e.target).parents().hasClass('searchWrap') ||$(e.target).hasClass('icon1')
          if (!target) {
            vm.searchVisible = false
          }
        }
      })
    },
    watch: {
      tableTypeOpts(newVal, oldVal){
        var _filterOpts = newVal.split(',')

        this.filterOptsActive = _filterOpts.indexOf('0') > -1 ? true : false
        this.filterOptsDeleted = _filterOpts.indexOf('1') > -1 ? true : false
        this.filterOptsUnknown = _filterOpts.indexOf('2') > -1 ? true : false
      },
      getIsBusiness(newData, oldData){
        this.clearSearch()
        this.filterOptsActive = true
        this.filterOptsDeleted = true
        this.filterOptsUnknown = true

        var _filterOpts = []
        if(this.filterOptsActive){
          _filterOpts.push(0)
        }
        if(this.filterOptsDeleted){
          _filterOpts.push(1)
        }
        if(this.filterOptsUnknown){
          _filterOpts.push(2)
        }
        this.$store.dispatch('changeTableTypeOpts', _filterOpts.join(','))
      },
      dialogVisible(val){
        this.validatorShow = val ? false : this.validatorShow
        if(val){
          this.$store.dispatch('saveExportSearch', '')
          this.searchContent = ''
          this.noDataVisible = false
        }
      },
      filterOptsActive(val){
        if(!this.filterOptsDeleted && !this.filterOptsUnknown){
          this.filterOptsActive = true
          return;
        }
      },
      filterOptsDeleted(){
        if(!this.filterOptsActive && !this.filterOptsUnknown){
          this.filterOptsDeleted = true
          return;
        }
      },
      filterOptsUnknown(){
        if(!this.filterOptsDeleted && !this.filterOptsActive){
          this.filterOptsUnknown = true
          return;
        }
      },
      '$route'(val){
        this.hasImport = /Physical/.test(val.path) ? true : false
      },
    },
    computed: {
      ...mapState({
        tableTypeOpts: 'tableTypeOpts',
        exportList: 'exportList',
        exportDBList: 'exportDBList',
        getIsBusiness: 'isBusiness',
        treeBusinessLoading: 'treeBusinessLoading'
  		}),
    },
    methods: {
      filterOptsConToggle(){
        if(this.filterOptsConVisible){
          this.filterOptsActive = this.filterOpts[0]
          this.filterOptsDeleted = this.filterOpts[1]
          this.filterOptsUnknown = this.filterOpts[2]

          this.filterOptsConVisible = false
        }else{
          this.filterOpts = [this.filterOptsActive, this.filterOptsDeleted, this.filterOptsUnknown]
          this.filterOptsConVisible = true
        }
      },
      filterOptsConHide(){
        this.filterOptsActive = this.filterOpts[0]
        this.filterOptsDeleted = this.filterOpts[1]
        this.filterOptsUnknown = this.filterOpts[2]

        this.filterOptsConVisible = false
      },
      filterOptsSubmit(){
        this.changeFilterOpts()
        this.filterOptsConVisible = false;
      },
      changeFilterOpts(){
        var _filterOpts = []
        if(this.filterOptsActive){
          _filterOpts.push(0)
        }
        if(this.filterOptsDeleted){
          _filterOpts.push(1)
        }
        if(this.filterOptsUnknown){
          _filterOpts.push(2)
        }
        this.$store.dispatch('changeTableTypeOpts', _filterOpts.join(','))
        Bus.$emit('initTree')
      },
      async searchClick(){
        this.loadingVisible = true
        const vm = this;
        this.treeData = []
				this.loading = true;
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
				let headerData = {contentType: 'application/json', dataType: 'json'};
				const res = await this.$http.post(url,params,headerData);
				const {statusCode,message} = res.body;
        const {records} = message
				if(statusCode == 200){
          this.loadingVisible = false
					this.loading = false
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
      resetTreeData(){
        this.clearSearch()
        this.treeSearch()
      },
      showSearchInput(){
        this.searchVisible = true
        // this.clearSearch()
      },
      clearSearch(){
        this.treeSearchKeywords = ''
      },
      pushToImport(){
        this.$store.dispatch('saveRouter', 'PhysicalDetail')
        this.$router.push({name: 'DataImport'})
      },
      treeSearch(){
  			this.$store.dispatch('saveDatasetKeyword', this.treeSearchKeywords)
      },
      dataExport(){
        this.fetchTreeData()
        this.$store.dispatch('clearExportCheckList', [])
        this.dialogVisible = true
      },
      async fetchTreeData(){
        const vm = this;
				vm.loadingVisible = true;
				const targetPath = this.$route.query.path;
				let url = '/excelMetadataInfo/treeSelect';
				let params = {
					targetTopicPath: targetPath,
					level:0,
					path:"",
          page: 1,
          rows: 20
				}
				let headerData = {contentType: 'application/json', dataType: 'json'};
				const res = await this.$http.post(url ,params,headerData);
				const {statusCode, message} = res.body;
        const {records} = message
				if(statusCode === 200){
					vm.loadingVisible = false;
          vm.setOpen(records);
          vm.treeData = records;
          this.reStructureTreeData()
				}
      },
      setOpen(value){
				const vm = this;
				if(!value.length){
					return;
				}
				value.map(val=>{
					if(val.children){
						vm.$set(val, 'open', true);
						vm.setOpen(val.children.records);
					}else{
						vm.$set(val, 'open', false);
					}
				})
			},
      async submitData(){
        if(this.exportList.length == 0 && this.exportDBList.length == 0){
          this.validatorShow = true
          return;
        }else{
          this.validatorShow = false
          let newExportList = []
          this.exportList.forEach(val=>{
            typeof val == 'number' && newExportList.push(val)
          })
          if(newExportList.length > 200){
            this.message(this.$t('content.dataset.datasetDetail.mczddr200'),"error");
            return;
          }else{
            // this.fullscreenLoading = true

            let datasetIds = newExportList && newExportList.join(',')
            let urn = this.exportDBList && this.exportDBList.join(',')
            let ignoreWarn = 'true'
            const lang = localStorage.getItem('lang')
            this.$http.post('/excelMetadataInfo/export', {urn, datasetIds, lang, ignoreWarn, responseparameterType: 'arraybuffer'}, {responseType: 'arraybuffer'})
            .then(res=>{
              // if(res.errorCode && res.errorCode == 20012){

              // }else{
                // this.fullscreenLoading = false
                this.dialogVisible = false
                this.downFile(res.body)
              // }
            })
          }

        }

      },
      downFile(data){
        let blob = new Blob([data], {type: 'application/vnd.ms-excel'})
        let date = new Date()
        let fileName = `${this.$t('content.dataset.datasetDetail.dcysj')}${date.getFullYear()}-${date.getMonth()<9?('0'+(date.getMonth()+1)): (date.getMonth()+1)}-${date.getDate()<10?('0'+date.getDate()) : date.getDate()}-${date.getHours()}-${date.getMinutes()}.xlsx`

        FileSaver.saveAs(blob, fileName);

        // if (window.navigator.msSaveOrOpenBlob) {
        //   navigator.msSaveBlob(blob, fileName);
        // } else {
        //     var link = document.createElement('a');
        //     link.style.display = 'inherit'
        //     link.href = window.URL.createObjectURL(blob)
        //     link.download = fileName
        //     $('.searchCon').append(link)
        //     link.click()
        //     window.URL.revokeObjectURL(link.href)
        // }
        this.$store.dispatch('clearExportCheckList', [])
      }
    }
  }
</script>
<style lang="less">
  .filterOpts{
    z-index: 1;
    position: absolute;
    top: 45px;
    left: 0;
    width: 255px;
    background: #fff;
    box-shadow: 0 8px 8px rgba(0,0,0,.2);
    
    .btnSubmit{
      float: right;
      height: 28px;
      margin: 20px 20px 20px -10px;
      padding: 0 16px !important;
      line-height: 26px;
      font-size: 13px;
    }
    ul{
      padding: 10px 20px;
      border-top: 1px solid #ddd;
      border-bottom: 1px solid #ddd;
    }
    li{
      padding: 5px 0
    }
    .cb{
      .el-checkbox__input{
        margin-top: -2px;
      }
      .el-checkbox__label{
        font-size: 12px;
        padding-left: 3px;
      }
      .el-checkbox__inner{
        border-radius: 3px;
        zoom: 0.8
      }
    }
  }
  .search-container{
    margin-bottom: 5px;
    display: flex;
    justify-content: space-between;
  }
  .has-flex-grow{
    margin-right: 15px;
  }
  .data-search{
    flex-grow: 1;
  }
  .data-controler{
    display: flex;
    align-items: center;
    i{
      margin-right: 20px;
      &:last-child{
        margin-right: 0;
      }
    }
  }
  .searchCon__search{
    .dbList{
      margin: 0;
    }
    .el-row{
      margin: 0;
    }
  }
</style>
