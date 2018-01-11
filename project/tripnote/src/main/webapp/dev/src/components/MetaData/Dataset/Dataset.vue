<template>
  <div class="box">
    <div id="MainCon">
      <div class="datasetList">
        <split-controler
          :isOpenTree="isOpenTree"
          :controlerLeft="controlerLeft"
          @setTreeControler="treeControl">
        </split-controler>
        <tree-header></tree-header>
        <loading :isloading="loading" isTree="true"></loading>
        <div style="position: relative;">
          <div class="tree-loading-container" v-if="treeLoading">
            <more-loading :isTree="true" :isloading="treeLoading"></more-loading>
          </div>
          <div v-if="!loading">
            <div class="dbList" v-if="treeData.length && treeDataIsNotNull">
              <tree-view
                :model="treeData"
                :isBusiness="getIsBusiness"
                :hasPrivilege="hasPrivilege">
              </tree-view>
            </div>
            <template v-if="treeData.length === 0 || !treeDataIsNotNull">
              <div class="addTheme__con"
                  @click="addNewTheme"
                   v-if="businessVisible && isShowAdd &&hasPrivilege">
                  <img :src="icon_addTheme" alt="">
                  <p class="addTheme__txt">
                    {{$t('content.common.qdj')}}
                    <a href="javascript:;">{{$t('content.tree.xjywzt')}}</a>
                  </p>
              </div>
              <no-data v-else></no-data>
            </template>
            <div class="addThemeBotoom__con" @click="addNewTheme" v-if="businessVisible && hasPrivilege">
                <!-- <a href="javascript:;" style="border: 1px solid red;"> -->
                    <i class="el-icon-plus"></i>
                    <span>{{$t('content.tree.xjywzt')}}</span>
                <!-- </a> -->
            </div>
          </div>
        </div>
        <el-dialog
          :title="$t('content.dataset.businessDetail.xjzt')"
          :visible.sync="dialogFormVisible"
          width="385px"
          :close-on-click-modal="false"
          @click.native.stop>
          <el-form :model="addFileForm" :rules="addFileRules" ref="addFileForm">
            <el-row :gutter="24">
              <el-col :span="20" :offset="2">
                <el-form-item prop="name">
                  <el-input v-model="addFileForm.name"
                    autofocus
                    :placeholder="$t('content.tree.qsrztm')"
                    @keyup.native.enter="addFileCommit('addFileForm')"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">{{$t('content.common.qx')}}</el-button>
              <el-button type="primary" @click="addFileCommit('addFileForm')" :disabled="buttonDisabled">
                  {{$t('content.common.qd')}}
              </el-button>
          </div>
        </el-dialog>
      </div>
      <div class="content" id="scrollContainer">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </div>
    </div>
  </div>
</template>

<script>
    import TreeView from './DatasetTree'
    import TreeHeader from './DatasetTreeHeader'
    import SplitControler from 'components/common/SplitControler'
    import 'assets/libs/splitter/jquery.splitter.js';
    import 'assets/libs/splitter/jquery.splitter.css'
    import {Message, resetValidators, windowSplitter, testPrivilege, reStructureTreeData} from 'mixins/common'
    import { themeNameValidate } from 'utils/validator'
    import Bus from 'utils/bus'
    import {mapState} from 'vuex'

    export default {
        components: {
            TreeView,
            SplitControler,
            TreeHeader
        },
        mixins: [Message, resetValidators, testPrivilege, windowSplitter, reStructureTreeData],
        data() {
          return {
            icon_addTheme: require('assets/images/icon_addTheme.png'),
            icon_add: require('assets/images/icon_add.png'),
            loading: true,
            isOpenTree: true,
            treeData: [],
            dialogFormVisible: false,
            addFileForm: {
                name: '',
            },
            addFileRules: {
              name: [
                {required: true, message: this.$t('content.tree.qsrztm'), trigger: 'blur'},
                {min: 1, max: 20, message: this.$t('content.validator.cdbdcg', [20]), trigger: 'blur'},
                {validator: themeNameValidate, trigger: 'blur'},
              ],
            },
            isShowAdd: false,
            hasPrivilege: true,
            controlerLeft: 310,
            buttonDisabled: false,
            treeDataIsNotNull: false
          }
        },
        computed: {
          ...mapState({
            tableTypeOpts: 'tableTypeOpts',
            keyword: 'datasetKeyword',
            getIsBusiness: 'isBusiness',
            treeLoading: 'treeLoading',
          }),
          path(){
            return this.$route.query.path;
          },
          url(){
            return this.getIsBusiness ? '/topic/tree' : '/datasets/tree';
          },
          businessVisible(){
            return this.getIsBusiness;
          },
        },
        watch: {
          keyword(val){
            val = val.replace(/_/g, '\\_')
            val = val.replace(/%/g, '\\%')
            val = val.replace(/'/g, '\\\'')
            val = val.replace(/"/g, '\\"')
            let url = this.url;
            let data = null
            if(this.tableTypeOpts == '0,1,2'){
              data = {
                level: 0,
                key: val,
                rows: 20,
                path: '',
                page: 1,
              }
            }else{
              data = {
                level: 0,
                key: val,
                rows: 20,
                path: '',
                page: 1,
                status: this.tableTypeOpts
              }
            }
            this.loading = true;
            this.$store.dispatch('beginRequest');
            this.$http.post(url, JSON.stringify(data),
                    {
                      contentType: 'application/json',
                      dataType: 'json'
                    }).then((res) => {
              this.loading = false
              this.$store.dispatch('treeLoading', false)
              const {statusCode, message} = res.body
              const {records} = message

              this.isNotNull(records)
              this.$store.dispatch('setTreeData', Array.isArray(records) && records.length !== 0 && this.treeDataIsNotNull)

              if (statusCode == 200) {
                this.setOpen(records)
                this.setFirst(records)
                this.isShowAdd = false
                this.treeData = records

                if(this.getIsBusiness){
                  this.reStructureBusinessData()
                  this.$router.push({name: 'DatasetDetail'})
                }else{
                  this.reStructureTreeData()
                  this.$router.push({name: 'PhysicalDetail'})
                }
              } else {
                this.treeData = []
              }
            })
          },
          getIsBusiness(val){
            // const vm = this;
            // this.$store.dispatch('beginRequest');
            // vm.businessVisible = vm.getIsBusiness;
            // vm.destroyed()
            // vm.fetchTreeData().then((data) => {
            //   this.treeData = data
            //   if( this.getIsBusiness){
            //     this.reStructureBusinessData()
            //   }else{
            //     this.reStructureTreeData()
            //   }
            //   this.setOpen(this.treeData)
            //   this.setFirst(this.treeData)
            // })
          },
          '$route'(val, oldVal){
            this.resetPosition()
          }
        },
        async mounted() {
            let self = this;
            const splitter = self.treeControlInit();
            $(window).scroll(function () {
                const scrollLeft = $(window).scrollLeft();
                let splitterPosition;
                if (self.isOpenTree) {
                    splitterPosition = 310;
                } else {
                    splitterPosition = 60;
                }
                self.controlerLeft = splitterPosition - scrollLeft;
            });

            Bus.$off('refreshTree').$on('refreshTree', async function (activeParams) {
                self.$store.dispatch('beginRequest');
                await self.keyTreeSearch({path: activeParams.path, key: self.$store.state.datasetKeyword})
                self.resetPosition()
                if (activeParams.path === '' && self.treeData.length > 0) {  //为空的时候，当前为根目录，需要选中首条数据
                  self.setFirst(self.treeData)
                } else {
                  self.$store.dispatch('fetchRouter', activeParams)
                }
            })
            Bus.$on('resetPosition', () => {
                this.resetPosition()
            });
        },
        async created(){
          //先off再on，防止请求两次
          Bus.$off('scrollTop').$on('scrollTop', ()=>{
            document.querySelector('#scrollContainer').scrollTop = 0
          })
          Bus.$off('initTree').$on('initTree', async() => {
            await this.fetchAllData()
          })
        },
        methods: {
            isNotNull(records){
              if(this.getIsBusiness){
                this.treeDataIsNotNull = true
              }else{
                this.treeDataIsNotNull = false
                for (var i = 0; i < records.length; i++) {
                  if(records[i].tableNum != null){
                    this.treeDataIsNotNull = true
                  }
                }
              }
            },
            destroyed(){
                this.treeData = []
                this.$store.dispatch('saveDatasetKeyword', "")
                this.$store.dispatch('saveTreeItemActive', "")
                this.$store.dispatch('saveViewData', "clear")
            },
            async fetchAllData(){
              this.testPrivilege("/topic/delete");//是否有权限操作
              if (this.$store.state.tableName) {
                  await this.keyTreeSearch({
                      path: this.path,
                      key: ''
                  });
                  this.$store.dispatch('saveTableName', "")
              } else {
                this.treeData = await this.fetchTreeData()

                if(this.getIsBusiness){
                  this.reStructureBusinessData()
                }else{
                  this.reStructureTreeData()
                }
                this.setOpen(this.treeData)
                this.setFirst(this.treeData)
              }
            },
            reStructureBusinessData(){
              let hasNextPage = true
              let path = ''
              function checkChildren(data){
                data && data.forEach((val, index)=>{
                  let arrPath = val.path.split('/')
                  arrPath.pop()
                  let newPath = arrPath.join('/')
                  if(val.children){
                    if(val.children.hasNextPage){
                      const {path, level} = val
                      val.children.records.push({type: 'more', children:[], path, level})
                    }
                    checkChildren(val.children.records)
                  }
                })
              }
              checkChildren(this.treeData)
            },
            resetPosition(){
                let leftPosition = this.splitter.position();
                // [].forEach.call(this.$el.querySelectorAll('.addThemeBotoom__con'), div => {
                //     div.style.width = `${leftPosition}px`
                // })
                // [].forEach.call(this.$el.querySelectorAll('.foldToggle'), div => {
                //     div.style.right = `${1000 - leftPosition + 20}px`
                // });
                // [].forEach.call(this.$el.querySelectorAll('.setting-container'), div => {
                //     div.style.right = `${1000 - leftPosition + 40}px`
                // });
                // [].forEach.call(this.$el.querySelectorAll('.folder-table-num'), div => {
                //     div.style.right = `${1000 - leftPosition + 30}px`
                // });
                this.controlerLeft = leftPosition + 60;
            },
            fetchTreeData(){ //获取树状
                this.loading = true
                this.$store.dispatch('fullTreeLoading', true)
                let postData = null
                if(this.tableTypeOpts == '0,1,2'){
                  postData = {
                    level: 0,
                    path: "",
                    page: 1,
                    rows: 20
                  }
                }else{
                  postData = {
                    level: 0,
                    path: "",
                    page: 1,
                    rows: 20,
                    status: this.tableTypeOpts
                  }
                }
                return new Promise((resolve, reject) => {
                    const vm = this
                    const url = this.url
                    vm.treeData = []
                    vm.$http.post(url, JSON.stringify(postData),
                            {
                              contentType: 'application/json',
                              dataType: 'json'
                            }).then((res) => {
                        vm.loading = false
                        this.$store.dispatch('treeLoading', false)
                        vm.isShowAdd = true
                        if (res.body.statusCode === 200) {
                          this.$store.dispatch('fullTreeLoading', false)
                          const {records} = res.body.message

                          this.isNotNull(records)
                          this.$store.dispatch('setTreeData', Array.isArray(records) && records.length !== 0 && this.treeDataIsNotNull)
                          if(this.getIsBusiness){
                            this.$router.push({name: 'DatasetDetail'})
                          }else{
                            this.$router.push({name: 'PhysicalDetail'})
                          }
                          resolve(records)
                        } else {
                          this.treeData = []
                        }
                    })
                })
            },
            //搜索结果跳过来的精确搜索
            keyTreeSearch({path = this.path, key = ""}){
              const modelOpen = this.$store.state.modelOpen
              const vm = this
              let postData = null
              let targetPath = modelOpen ? (path || this.$store.state.fetchRouter.path) + '/' : (path || this.$store.state.fetchRouter.path)

                if(this.tableTypeOpts == '0,1,2'){
                  postData = {
                    level: 0,
                    key,
                    page: 1,
                    rows: 20,
                    targetPath    //url地址没有path参数，就从全局存储状态里面获取
                  }
                }else{
                  postData = {
                    level: 0,
                    key,
                    page: 1,
                    rows: 20,
                    targetPath,    //url地址没有path参数，就从全局存储状态里面获取
                    status: this.tableTypeOpts
                  }
                }
                return new Promise((resolve, reject) => {
                    let url = vm.url;
                    vm.loading = true;
                    vm.$http.post(url, JSON.stringify(postData),
                            {
                                contentType: 'application/json',
                                dataType: 'json'
                            }).then((res) => {
                        vm.loading = false
                        vm.treeData = []
                        vm.isShowAdd = true
                        const {statusCode, message} = res.body
                        const {records} = message
                        if (statusCode == 200) {
                            this.isNotNull(records)
                            this.$store.dispatch('setTreeData', Array.isArray(records) && records.length !== 0 && this.treeDataIsNotNull)
                            this.treeData = records
                            vm.setOpen(records)
                            if( this.getIsBusiness){
                              this.reStructureBusinessData()
                            }else{
                              this.reStructureTreeData()
                            }
                            resolve()
                        } else {
                            vm.treeData = []
                            reject(new Error(res.body.statusCode))
                        }
                    })
                })
            },
            setOpen(value){
              const vm = this;
              if (!value) {
                  return
              }
              value.map(val => {
                  if (val.children) {
                      vm.$set(val, 'open', true)
                      vm.setOpen(val.children.records)
                  } else {
                      vm.$set(val, 'open', false)
                  }
              })
            },
            setFirst(data){
              const vm = this
              const datafirst = data[0]
              if (!datafirst) {
                let path = ''
                let datasetId = 0
                this.$store.dispatch('fetchRouter', {datasetId, path})
              } else {
                let {path, folder, datasetId} = datafirst
                if (datafirst.hasChild && datafirst.children != null) {
                  vm.setFirst(datafirst.children.records)
                } else {
                  if (this.getIsBusiness && datafirst.folder) {
                    const {title, path} = datafirst
                    this.$store.dispatch('saveBusinessState', {title, path})
                  } else {
                    this.$store.dispatch('saveBusinessState', {title: '', path: ''})
                  }
                  this.$store.dispatch('fetchRouter', {datasetId, path})
                }
              }
            },
            addNewTheme(){
              this.dialogFormVisible = true;
              this.resetValidators('addFileForm');
            },
            addFileCommit(formName){

                const title = this.addFileForm.name.trim();
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            path: "",
                            title
                        }
                        let _url = `/topic/create`;
                        this.buttonDisabled = true;
                        this.$http.post(_url, params)
                                .then((res) => {
                                    const code = res.body.statusCode;
                                    if (code == 200) {
                                        this.dialogFormVisible = false;
                                        this.message(`${this.$t('content.common.chuangj')}${this.$t('content.common.chengg')}!`, "success");

                                        Bus.$emit('refreshTree', {path: title, datasetId: 0});
                                    } else if (code == 201) {
                                        this.message(`${this.$t('content.tree.cztycz')}!`, "warning");
                                    } else {
                                        this.message(`${this.$t('content.common.chuangj')}${this.$t('content.common.shib')}!`, "error");
                                    }
                                    this.buttonDisabled = false;
                                })
                    } else {
                        return false;
                    }
                });
            },
            treeControl(){
                this.isOpenTree = !this.isOpenTree;
                let _left = this.isOpenTree ? "310" : '60';
                $('#MainCon').split().destroy();
                this.controlerLeft = _left - $(window).scrollLeft()
                this.treeControlInit(_left - 60);
            },
        }
    }
</script>
<style scoped>
    .dbList {
        position: relative;
        margin-bottom: 180px;
        /*width: 1000px;*/
    }
    .MainCon {
        height: 100%;
    }
</style>
