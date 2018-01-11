<template>
  <div class="boxCommon">
    <div class="table-title">
      <div class="table-title__text">
         <span>{{$t('content.personal.yqxdsjlb')}}</span>
      </div>
      <div class="table-title__btns">
       <Search
        :placeholder="$t('content.validator.qsrbm')"
        emitName="authoritySearch"
        @authoritySearch="fetchData"></Search>
      </div>
    </div>
    <loading :isloading="loading"/>
    <template v-if="!loading">
      <table class="table table-bordered tableList">
        <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort"
          >
        </table-header>
        <tbody v-if="!noDataVisible">
           <item v-for="(data, index) in showRecordList"
            :params="{
              params:{
                path: data.path,
                level:3,
              },
              isfolder: false
            }"
            :names = "emphasizeName(data.resourceName.split('.')[1],key)"
            :view="false"
            :datasetId="data.datasetId"
            :indexKey="index">
              <!-- <td><div v-ellipsis="data.datasourceName"></div></td> -->
             <td><div v-ellipsis="data.resourceName.split('.')[0]"></div></td>
             <td><div v-ellipsis="data.ownedProject"></div></td>
             <!-- <td>
               <div>
                 <i v-for="item in data.type"
                    :class="[item.val ? item.icon : '']"
                    class="permission__icon">
                   {{item.text}}
                 </i>
               </div>
             </td> -->
             <td><div v-ellipsis="data.usedProject"></div></td>
             <td>{{formatSize(data.size)}}</td>
             <!-- <td>{{data.lifeCycle}}</td> -->
             <td>
                <div class="owner-operator">
                    <a
                        href="javascript:;"
                        @click="showTableDetail(data.tableName, data.resourceName)">
                        {{$t('content.common.ckxq')}}
                    </a>
                </div>
            </td>
           </item>
        </tbody>
      </table>
      <div class="tablePages" v-if="!noDataVisible">
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
      <no-data :noDataVisible="noDataVisible"></no-data>
    </template>

    <el-dialog
      :title="dialogTableDetailTitle"
      :visible.sync="dialogTableDetailVisible"
      :close-on-click-modal="false"
      custom-class="detailDialog">

      <loading :isloading="detailLoading"/>

      <template v-if="!detailLoading">
      <table class="table table-bordered tableList">
        <table-header
            :tableHeader = "detailTableHeader"
          >
        </table-header>
        <tbody v-if="!detailNoDataVisible">
           <item v-for="(data, index) in detailRecordList"
            :params="{
              params:{
                path: data.path,
                level:3,
              },
              isfolder: false
            }"
            :view="false"
            :datasetId="data.datasetId"
            :indexKey="index">
              <td><div v-ellipsis="data.usedProject"></div></td>
             <td>
               <div>
                 <i v-for="item in data.type"
                    :class="[item.val ? item.icon : '']"
                    class="permission__icon">
                   {{item.text}}
                 </i>
               </div>
             </td>
           </item>
        </tbody>
      </table>
      <no-data :noDataVisible="detailNoDataVisible"></no-data>
    </template>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogTableDetailVisible=false">{{$t('content.common.qd')}}</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import item from 'common/TableItemJump'
  import Search from 'common/Search'
  import permissionItem from '../Dataset/DatasetDetail/DataPermissionItem'
  import {FormatTime, FormatSize,EmphasizeName,FormatLifeDay} from 'mixins/common'
  export default{
    mixins:[FormatTime, FormatSize,EmphasizeName,FormatLifeDay],
    data(){
      return {
        loading: true,
        noDataVisible: false,
        detailLoading: true,
        detailNoDataVisible: false,
        records: [],
        detailRecords: [],
        detailList: [
          {project: '项目1', authority: '读&写'},
          {project: '项目2', authority: '读&写'},
          {project: '项目3', authority: '读&写'},
          {project: '项目4', authority: '读&写'},
        ],
        currentPage: 1,
        pageSize: 10,
        totalRows: 1,
        tableHeader: [
          {
            name:this.$t('content.dataset.businessDetail.biaom'),
            type:"tableName",
            hasSort:true,
          },
          // {
          //     name: this.$t('content.import.sjy'),
          //     type: "datasourceName",
          //     hasSort: true,
          // },
          {
            name:this.$t('content.dataset.datasetDetail.km'),
            type: "ownedProject",
            hasSort:false,
          },
          // {
          //   name:"获取权限日期",
          //   type:"created_time",
          //   hasSort:true,
          // },
          {
            name:this.$t('content.personal.ssxm'),
            type:"ownedProject",
            hasSort:true,
          },
          // {
          //   name:this.$t('content.dataset.datasetDetail.dxqx'),
          //   type:"type",
          //   hasSort:true,
          // },
          {
            name:this.$t('content.personal.qxssxm'),
            type:"usedProject",
            hasSort:true,
          },
          {
            name:this.$t('content.dataset.datasetDetail.ccdx'),
            type:"size",
            hasSort:true,
          },
          // {
          //   name:this.$t('content.dataset.datasetDetail.smzq'),
          //   type:"dayNum",
          //   hasSort:false,
          // },
          {
            name: this.$t('content.personal.qxxq'),
            hasSort:false,
          },
        ],

        detailTableHeader: [
          {
            name:this.$t('content.personal.qxssxm'),
            type:"usedProject",
            hasSort:true,
          },
          {
            name:this.$t('content.dataset.datasetDetail.dxqx'),
            type:"type",
            hasSort:true,
          }
        ],
        sort:"tableName",
        order:"asc",
        dialogTableDetailTitle: '',
        dialogTableDetailVisible: false
      };
    },
    components: {
      item,
      Search,
      permissionItem
    },
    created(){
      this.fetchData();
    },
    computed: {
      showRecordList(){
        const vm = this
        function type(type = ""){
          let typeArr = [
            {text:vm.$t('content.authority.r'),icon:'permission__icon-r',val:false},
            {text:vm.$t('content.authority.w'),icon:'permission__icon-w',val:false}
                       ];
            if(!!type){
                Array.from(type,(val,index)=>{
                    typeArr[index]['val'] = !!val
                })
              }
            return typeArr
        }
        const records = this.records;

        records.map((val)=>{
            val.type = type(val.type);//读写权限
            val.lifeCycle = this.switchLifeType({lifeDay:val.lifeDay,lifeType:val.lifeType})//生命周期
        })
        return records;
      },

      detailRecordList(){
        const vm = this
        function type(type = ""){
          let typeArr = [
            {text:vm.$t('content.authority.r'),icon:'permission__icon-r',val:false},
            {text:vm.$t('content.authority.w'),icon:'permission__icon-w',val:false}
                       ];
            if(!!type){
                Array.from(type,(val,index)=>{
                    typeArr[index]['val'] = !!val
                })
              }
            return typeArr
        }
        const detailRecords = this.detailRecords;
        detailRecords.map((val)=>{
            val.type = type(val.type);//读写权限
            val.lifeCycle = this.switchLifeType({lifeDay:val.lifeDay,lifeType:val.lifeType})//生命周期
        })
        return detailRecords;
      }
    },
    methods: {
      async fetchData({key='', currentPage=1} = {}){
        this.loading = true;
        this.key = key;
        const self = this;
        let url = `/privilege/getOwnedPrivileges?keyword=${escape(key)}&rows=10&page=${currentPage}&sort=${this.sort}&order=${this.order}`
        const res = await this.$http.get(url);
        this.loading = false;
        if(res.body.statusCode == 200){
          this.noDataVisible = res.body.message.records.length == 0 ? true : false;
          Object.assign(this.$data, res.body.message)
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
      handleSizeChange(){},
      handleCurrentChange(val){
        const params = {
          currentPage: val,
          key: this.key
        }
        this.fetchData(params);
      },

      async showTableDetail(tableName, resourceName){
        this.detailLoading = true;
        this.dialogTableDetailTitle = `"${tableName}" ${this.$t('content.personal.qxxq')}`;
        this.dialogTableDetailVisible = true;

        let url = `/privilege/getOwnedPrivilegesDetail?resourceName=${resourceName}&rows=1000&page=1`;
        const res = await this.$http.get(url);
        this.detailLoading = false;
        if(res.body.statusCode == 200){
          this.detailNoDataVisible = res.body.message.records.length == 0 ? true : false;
          this.detailRecords = res.body.message.records;
        }
      }
    },
  }
</script>
<style lang="less">
  .popover-container{
    .el-button{
      border: 0;
      padding: 0;
      color: #169bd5;
    }
  }
  .detailDialog{
    .el-dialog__body{
      min-height: 150px;
      max-height: 400px;
      overflow-y: auto;
    }
  }
</style>
