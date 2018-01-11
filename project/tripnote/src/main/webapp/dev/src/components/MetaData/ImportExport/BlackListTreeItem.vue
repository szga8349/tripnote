<template>
  <div class="black-list-item">
    <section class="icon-DB black-list-title"
             @click="requestChild(folder)">
      <span class="foldCon">
        <i class="folder"
          :class="{'baseIcon': !folder.tableName}"></i>
      </span>
      <span>
        {{folder.databaseName}}
      </span>
      <span class="black-list-check" >
        <el-checkbox
          :label="folder.databaseName"
          @click.native.stop
        ></el-checkbox>
      </span>
    </section>
    <!-- 表 -->
    <ul v-if="treeData.length">
      <li v-for="item in reTreeData" v-if="!item.more">
        <div class="black-list-item">
          <section class="icon-DB black-list-title">
            <span class="foldCon">
              <i class="folder"></i>
            </span>
            <span  v-ellipsis="item.tableName" class="black-list-tableName"></span>
            <span class="black-list-check">
              <!-- @click.native.stop="addToList(item)" -->
              <el-checkbox
                :label="JSON.stringify(item)"
                :checked="item.isChecked"
                :disabled="saveSelectList.includes(item.databaseName)"
                @click.native.stop>
              </el-checkbox>
            </span>
          </section>
        </div>
      </li>
      <li v-else>
        <div class="load-more-item" @click="requestMore(folder)">
          <a>{{$t('content.common.jzgd')}}</a>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
  import Bus from 'utils/bus'
  export default{
    name: 'blackListTreeItem',
    props: {
      folder: Object,
      isOpen: Boolean
    },
    data(){
      return {
        treeData: [],
        isOpen: false,
        dataBase: [],
        tableList: [],
        currentPage: 1
      }
    },
    computed: {
      firstStepData(){
        return this.$store.state.createTask
      },
      saveBlackList(){
        let arr = this.$store.state.testBlackList
        let list = []
        arr.forEach(val=>{
          list.push(val.databaseName)
        })
        return list
      },
      saveSelectList(){
        return this.$store.state.saveSelectList
      },
      saveTableList(){
        this.tableList = []
        const list = this.$store.state.saveBlackList
          list.forEach((val, index)=>{
            if(val.tableName){
              this.tableList.push(val)
            }
          })
          // let resultBase = new Set(this.tableList)
          return this.tableList
      },
      isChecked(){
        if(!this.folder.tableName && this.saveBlackList.includes(this.folder.databaseName)){
          return true
        }else{
          return false
        }
      },
      reTreeData(){
        const vm = this

        this.treeData.forEach((val, index)=>{
          vm.saveTableList.forEach(item=>{

            if(item.tableName == val.tableName && item.databaseName == val.databaseName){
              this.$set(val, 'isChecked', true)
            }
          })
        })
        return this.treeData
      }
    },
    methods: {
      async requestChild(folder){
        if(this.isOpen){
          this.treeData = []
          this.isOpen = false
        }else{
          this.currentPage = 1
          const params = {
            refId: this.firstStepData.refId,
            database: this.folder.databaseName,
            page: this.currentPage,
            rows: 10,
            // tableName
          }
          const res = await this.$http.post('/systemImport/getSampleDbAndTable', params)
          const { statusCode } = res.body
          if(statusCode == 200){
            const message = JSON.parse(res.body.message)
            const {records} = message
            if(message.hasNextPage){
              this.treeData = records.concat({more: true})
            }else{
              this.treeData = records
            }
            this.isOpen = true
          }
        }
      },
      async requestMore(folder){
        const params = {
            refId: this.firstStepData.refId,
            database: this.folder.databaseName,
            page: ++this.currentPage,
            rows: 10,
          }
        const res = await this.$http.post('/systemImport/getSampleDbAndTable', params)
        const { statusCode } = res.body
        if(statusCode == 200){
          const message = JSON.parse(res.body.message)
          const {records} = message
          if(message.hasNextPage){
            this.treeData.splice((this.currentPage-1)*10,1)
            this.treeData = this.treeData.concat(records, [{more: true}])
          }else{
            this.treeData.splice((this.currentPage-1)*10, 1)
            this.treeData = this.treeData.concat(records)
          }

        }
      },
    }
  }
</script>
<style lang="less">
  .black-list-item{
    .black-list-tableName{
      max-width: 235px !important;
    }
    >.black-list-title{
      margin-left: -280px;
      padding-left: 280px;
      border-bottom: 1px solid #e2e2e2;
    }
    >.icon-DB{
      height: 36px;
      line-height: 36px;
      .baseIcon{
        height: 17px;
        background-position: 0 0;
      }
    }
    >.icon-DB:hover{
      cursor: pointer;
      background: #f3f3f5;
    }
    .black-list-check{
      float: right;
      margin-right: 10px;
      height: 100%;
      .el-checkbox__label{
        display: none;
      }
    }
    .load-more-item{
       margin-left: -30px;
       text-align: center;
       border-bottom: 1px solid #e2e2e2;
       cursor: pointer;
    }
  }
</style>

