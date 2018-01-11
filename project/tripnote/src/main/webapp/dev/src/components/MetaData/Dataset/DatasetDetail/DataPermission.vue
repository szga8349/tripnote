<template>
  <div class="boxCommon">
   <loading :isloading="loading"/>
   <template v-if="!loading">
      <template v-if="records.length !== 0">
        <span class="permission__title">{{extraParam}} {{$t('content.dataset.datasetDetail.ydxqx')}}</span>
        <permission-item
        v-for="item in grouping"
        :project="item.project"
        :model="item.val"></permission-item>
        <div class="tablePages">
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
      </template>
      <no-data v-else></no-data>
   </template>
  </div>
</template>
<script>
  import permissionItem from './DataPermissionItem'
  export default{
    name: "DataPermission",
    components:{permissionItem},
    data(){
      return{
        loading:true,
        currentPage:1,
        pageSize:20,
        totalRows:0,
        extraParam:0,//账号数
        records:[],
      }
    },
    computed:{
      grouping(){
        const records = this.records;
        const groupArr = [];
        const newMap = records.reduce((pre,cur)=>{
          const id = cur.usedProjectId;
          let arr = [];
          arr.push(cur);
          if(pre.has(id)){
            arr = pre.get(id).concat(arr);
          }
          pre.set(id,arr)
          return pre;
        },new Map());
         for (let [key,val] of newMap){
          const obj={
            id:key,
            project:val[0].usedProject,
            val
          }
          groupArr.push(obj)
         }
         return groupArr
      }
    },
    props:{
      tableId:{
        require:true
      }
    },
    watch: {
      '$route': function(val){
        if(val.query.path){
          this.fetchData();
        }
      },
    },
    methods:{
      async fetchData(page = 1){
        this.loading = true;
        const url = `/privilege/getAuthorizedAccounts?page=${page}&datasetId=${this.tableId}&rows=20&sort=usedProject&order=asc`;
        const res = await this.$http.get(url);
        this.loading = false;
        const {statusCode,message} = res.body;
        statusCode === 200 && Object.assign(this.$data,message)
      },
      handleCurrentChange(val) {
        this.fetchData(val)
      }
    },
    created(){
      this.fetchData();
    }
  }
</script>
<style>
  .table-container{
    margin-bottom: 20px;
  }
  .permission__title{
    display:block;
    color:#a5aaac;
    margin: 15px 0;
  }
  .permission__user{
    float: left;
  }
  .permission__user-small{color: #a5aaac;}
  .permission__authority{
    float: right;
  }
  .permission__icon{
    display: inline-block;
    font-style: normal;
    width: 20px;
    height: 20px;
    background: #dadada;
    color: #fff;
    text-align: center;
    line-height: 20px;
    margin-right: 8px;
  }
  .permission__icon-r{
      background: #22b66f;
    }
  .permission__icon-w{
      background: #277dea;
    }
</style>
