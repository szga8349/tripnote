<template>
  <div class="config-blacklist">
    <section class="headerCon">
      <div class="leftBox">
        <label>{{$t('content.dataset.importExport.sxpzlx')}}:</label>
        <el-radio v-model="useWhiteList" label="false">{{$t('content.dataset.importExport.pzhmd')}}</el-radio>
        <el-radio v-model="useWhiteList" label="true">{{$t('content.dataset.importExport.pzbmd')}}</el-radio>
      </div>
      <div class="rightBox">
        {{wordingContent}}
      </div>
    </section>

    <el-form>
      <div class="black-list-container">
        <section class="black-list-left">
          <!-- 搜索暂时屏蔽 -->
          <!-- <div class="tree-search">
            <el-input
            v-model="keyword"
            placeholder="请输入要查找的表名"></el-input>
            <i class="el-icon-search" @click="serchDataTable"></i>
          </div> -->
          <div class="black-tree-container dbList">
            <loading :isloading="selectVisible"></loading>
            <el-checkbox-group
              v-model="checkListCo"
              v-if="!selectVisible">
              <tree-view :model="treeData"></tree-view>
            </el-checkbox-group>
          </div>
        </section>
        <section>
          <i class="el-icon-arrow-right"></i>
          <i class="el-icon-arrow-left"></i>
        </section>

        <section class="black-list-right black-tree-container">
          <ul class="black-select-container dbList">
            <black-select
              v-for="item in reCheckList"
              :model="item"></black-select>
          </ul>
        </section>
      </div>
      <footer>
        <el-button @click="prevStep">{{$t('content.common.syb')}}</el-button>
        <el-button @click="saveBlackList" type="primary">{{$t('content.common.bc')}}</el-button>
      </footer>
    </el-form>

    <el-dialog
        :title="$t('content.common.tis')"
        width="385px"
        :visible.sync="dialogVisible">
          <div class="dialogTips">
            {{$t('content.dataset.importExport.qrxghbmd')}}？
          </div>
          <span slot="footer" class="dialog-footer">
             <el-button @click="dialogVisible=false">{{$t('content.common.qx')}}</el-button>
             <el-button type="primary" @click="submitBlackList">{{$t('content.common.qd')}}</el-button>
          </span>
      </el-dialog>
    <el-dialog
        :title="$t('content.common.warn')"
        width="385px"
        :visible.sync="warnDialog">
          <div class="dialogTips">
            {{$t('content.dataset.importExport.blackWhiteWarn',[(eval(oritionWhiteList)?$t('content.dataset.importExport.white'):$t('content.dataset.importExport.black')), (!eval(oritionWhiteList)?$t('content.dataset.importExport.white'):$t('content.dataset.importExport.black'))])}}
          </div>
          <span slot="footer" class="dialog-footer">
             <el-button @click="cancleChange">{{$t('content.common.qx')}}</el-button>
             <el-button type="primary" @click="confirmChange">{{$t('content.common.qd')}}</el-button>
          </span>
      </el-dialog>
  </div>
</template>
<script>
  import Bus from 'utils/bus'
  import { Message } from 'mixins/common'
  import TreeView from './BlackListTree'
  import BlackSelect from './BlackListSelect'
  import Vue from 'vue'
  export default{
    name: 'BlackList',
    components: {
      TreeView,
      BlackSelect
    },
    mixins: [ Message ],
    data(){
      return {
        confirmFlag: null,
        useWhiteList: 'false',
        oritionWhiteList: this.useWhiteList,
        warnDialog: false,
        treeData: [],
        checkList: [],
        // leftTreeLoading: true,
        blackSelectData: [
          {folder: true, name: 'ccsd'},
          {folder: true, name: 'cctest'},
          {folder: false, name: 'a'},
          {folder: false, name: 'b'},
          {folder: true, name: 'haha'},
        ],
        dbs: [],
        tables: [],
        keyword: '',
        wordingContent: this.$t('content.dataset.importExport.pzbxyh'),
        selectVisible: true,
        listFlag: false,
        checkListCo: [],
        isChange: false,
        checkListState: [],
        useWhiteListState: '',
        dialogVisible: false,
        params: null,
        flag: false,
        lang: this.$store.state.language
        // zh-CN
      }
    },
    async created(){
      await this.fetchTreeData()
      //将checkListCo copy给checkListState
      this.checkListState = this.checkListCo.slice(0)
      //将黑白名单的选项copy给useWhiteListState
      this.useWhiteListState = this.useWhiteList

      Bus.$on('deleteItem', item=>{
        const itemValue = item.tableName ? item.tableName : item
        if(typeof item == 'object'){
          this.checkListCo && this.checkListCo.forEach((val, index)=>{
            val = val.includes('{') ? JSON.parse(val) : val
            if(val.tableName == itemValue){
              this.checkListCo.splice(index, 1)
            }
          })
        }else{
          this.checkListCo && this.checkListCo.forEach((val, index)=>{
            if(val == itemValue){
              this.checkListCo.splice(index, 1)
            }
          })
        }
      })

      document.querySelectorAll('.el-dialog__close')[1].addEventListener('click', ()=>{
        console.log(this.cancleChange())
      })
    },
    computed: {
      selectTables(){
        this.$store.states.selectTables
      },
      firstStepData(){
        return this.$store.state.createTask
      },
      reCheckList(){
        const selectArr = []
        const tableDatabaseMock = []
        const tableDataArr = []
        this.checkListCo && this.checkListCo.forEach(val=>{
          val = val.includes('{')?JSON.parse(val):val
          if(val.tableName){
            // 先将选中的表所在的库加入数组
            if(!tableDatabaseMock.includes(val.databaseName)){
              tableDatabaseMock.push(val.databaseName)
            }
          }else{
            //直接将库添加到列表
            selectArr.push(val)
          }
        })

        let tableObj = Object.create(null)
        tableDatabaseMock.forEach(val=>{
          let singleArr = []
          this.checkListCo.forEach(item=>{
            item = item.includes('{')?JSON.parse(item):item
            if(val == item.databaseName){
              singleArr.push(item)
            }
          })
          tableObj[val] = singleArr
        })

        Object.keys(tableObj).forEach(key=>{
          pushObjToArr(key)
        })
        //将每一个选有表的数据拆分成单独的的对象分隔开存入数组
        function pushObjToArr(key){
          let singleObj = {[key]: tableObj[key]}
          selectArr.push(singleObj)
        }
        this.$store.dispatch('saveSelectList', selectArr)
        return selectArr
      }
    },
    watch: {
      checkListCo (val) {
        let tables = []
        let databases = []
        val.forEach(item => {
          if(item.includes('tableName')){
            tables.push(JSON.parse(item))
          }else{
            databases.push(item)
          }
        })
        tables.forEach(table => {
          databases.forEach(database => {
            if(table.databaseName == database){
              val.forEach((item, index) => {
                if(item == JSON.stringify(table)){
                  val.splice(index, 1)
                }
              })
            }
          })
        })
      },
      '$parent.activeTab'(val){
        if(val == this.$t('content.dataset.importExport.hqdkbhmdsz') && this.$parent.sourchChange){
          this.checkListCo = []
          // this.selectVisible = true
          this.fetchTreeData()
        }
        if(this.$t('content.dataset.importExport.hqdkbhmdsz')){
          this.listFlag = false
        }
      },
      useWhiteList(val){
        this.wordingContent = eval(val) ? this.$t('content.dataset.importExport.pzbxyb') : this.$t('content.dataset.importExport.pzbxyh')
        if(this.$route.params.id && this.useWhiteList != this.oritionWhiteList){
          this.warnDialog = true
        }
      },
      checkList(val){
        let selectDatabase
        const selectItem = val[val.length-1]
        if(val.length>0){
          selectDatabase = selectItem.tableName?'':selectItem.databaseName
        }

        let dataBase = []
        val.forEach((val, index)=>{
          if(!val.tableName){
            dataBase.push(val.databaseName)
          }
        })
        let resultBase = new Set(dataBase)
        resultBase = [...resultBase]

        val.forEach((item, index)=>{
          if(item.tableName && resultBase.includes(item.databaseName)){
            val.splice(index, 1)
          }
        })
        let tablaArr = []
        this.checkListCo = val.map(item=>{
          if(item.tableName){
            let single = JSON.stringify({databaseName: item.databaseName, tableName: item.tableName})
            tablaArr.push(single)
            return single
          }else{
            return item.databaseName
          }
        })
        this.$store.dispatch('saveBlackList', tablaArr)
      },
    },
    methods: {
      eval(val){
        return eval(val)
      },
      cancleChange(){
        // this.confirmFlag = false
        this.useWhiteList = this.oritionWhiteList
        this.warnDialog = false
      },
      confirmChange(){
        this.oritionWhiteList = this.useWhiteList
        // this.confirmFlag = true
        this.warnDialog = false
      },
      saveBlackList(){
        this.flag = this.checkListCo.every((val, index) => val == this.checkListState[index]) && this.useWhiteListState == this.useWhiteList

        const vm = this

        this.dbs = []
        this.tables = []
        let id = this.$route.params.id ? this.$route.params.id : ''
        const {
          taskName,
          taskDesc,
          refId,
          cron,
          getSample,
          serverUrl,
          serverUsername,
          serverPassword,
          useKerberos,
          principal,
          keytab,
          properties
        } = this.firstStepData
        function removeRepeat(){
            //database
            let newCheckList = []
            let newDatabaseName = []
            //table
            let newTableList = []
            let newTableName = []
            vm.checkListCo.forEach(val=>{
              val = val.includes('{')?JSON.parse(val):val
                if(!val.tableName && !newDatabaseName.includes(val)){
                    newDatabaseName.push(val)
                    newCheckList.push(val)
                }else if(val.tableName && !newTableName.includes(val)){
                    newTableName.push(val.tableName)
                    newCheckList.push(val)
                }
            })
            newCheckList.forEach(val=>{
              if(!val.tableName){
                vm.dbs.push({databaseName: val})
              }else{
                vm.tables.push(val)
              }
            })
        }
        //去重一下
        removeRepeat()
        const params = {
            taskName,
            taskDesc,
            refId,
            cron,
            getSample: eval(getSample),
            serverUrl,
            serverUsername,
            serverPassword,
            useKerberos: eval(useKerberos),
            principal,
            keytab,
            useWhiteList: eval(this.useWhiteList),
            dbs: this.dbs,
            tables: this.tables,
            isActive: 'Y',
            id,
            properties
        }
        this.params = params
        if(!this.flag){
          this.dialogVisible = true
        }else{
          this.submitBlackList()
        }

      },
      async submitBlackList(){
        const params = this.params
        const flag = this.flag
        this.$store.dispatch('fullLoading', true)

        let url = this.$route.params.id ? '/systemImport/update' : '/systemImport/create'
        const res = await this.$http.post(url, params)
        const {message} = res.body
        switch(message){
          case '20010':{
            this.message(`${this.$t('content.dataset.importExport.rwyjcz')}!`, 'error')
            this.$store.dispatch('fullLoading', false)
            break;
          }
          case '20011':{
            this.message(`${this.$('content.dataset.importExport.ybsjdfwq')}！`, 'error')
            this.$store.dispatch('fullLoading', false)
            break;
          }
        }
        let content = this.$route.params.id ? this.$t('content.common.bj') : this.$t('content.dataset.importExport.xjrw')
        this.$store.dispatch('fullLoading', false)
        if(res.body.statusCode == 200){
          // `${content} ${this.$t('content.common.chengg')}`
          if(!flag){
            this.message(this.$t('content.dataset.importExport.hbmdsx'), 'success')
          }
          this.$router.push({name: 'ConfigurationList'})
        }else{
          this.message(`${content} ${this.$t('content.common.shib')}`, 'error')
        }
      },
      prevStep(){
        this.$parent.activeTab = this.$t('content.dataset.importExport.rwxx')
      },
      async fetchTreeData(){
        this.checkListCo = []
        const {refId } = this.firstStepData
        const url = '/systemImport/getSampleDbAndTable'
        const params = {
          refId
        }
        const res = await this.$http.post(url, params)
        const { statusCode } = res.body
        this.selectVisible = false
        if(statusCode == 200){
          this.treeData = JSON.parse(res.body.message)
          if(this.$route.params.id){
            this.checkList = this.firstStepData.dbs.concat(this.firstStepData.tables)
            this.oritionWhiteList = this.useWhiteList = this.firstStepData.useWhiteList + ''
          }else{
            this.oritionWhiteList = this.useWhiteList = false + ''
          }
        }else{
            this.message(this.$t('content.dataset.importExport.hivemetastore'),'error')
            this.prevStep()
        }
      }
    }
  }
</script>
<style lang="less">
  .config-blacklist{
    width: 850px;
    margin: 0 auto;
    .headerCon{
      display: flex;
      justify-content: space-between;
      >.leftBox{
        width: 50%;
      }
      .rightBox{
        width: 60%;
        text-align: right;
      }
      margin: 30px 0;
      .el-radio__label{
        display: inline;
        color: #666;
        font-size: 12px;
      }
      >div:last-child{
        color: #ccc;
      }
    }
    .black-list-container{
      display: flex;
      justify-content: space-between;
      align-items: center;
      >section:nth-of-type(2n-1){
        border: 1px solid #dadada;
        width: 339px;
        // height: 631px;
      }
      >section:nth-of-type(2){
        display: flex;
        flex-direction: column;
        align-items: center;
        i{
          display: block;
          padding: 3px;
          border: 1px solid #ccc;
        }
        i:first-child{
          margin-bottom: 8px;
        }
      }
    }
    footer{
      display: flex;
      justify-content: flex-end;
      margin-top: 15px;
    }
  }
  .tree-search{
    border: 1px solid #dadada;
    display: flex;
    justify-content: space-between;
    align-items: center;
    input{
      border: 0;
    }
    input::-webkit-input-placeholder{
      color: #ccc;
    }
    input::-moz-placeholder{
      color: #ccc;
    }
    input::-ms-input-placeholder{
      color: #ccc;
    }
    i{
      font-size: 18px;
      margin-right: 10px;
      color: #4087f6;
    }
  }
  .black-tree-container{
    height: 590px;
    overflow-y: auto;
  }
  .black-list-left{
    position: relative;
  }
  .black-list-left .dbList{
    margin: 0;
  }
</style>
