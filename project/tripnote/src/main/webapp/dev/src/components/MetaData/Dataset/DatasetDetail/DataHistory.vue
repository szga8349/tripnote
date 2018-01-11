<template>
<div>
  <loading :isloading="loading"></loading>
  <div class="con clearfix" v-if="!loading" style="padding-top: 10px;">
    <el-button-group style="margin-bottom: 12px;">
      <el-button type="primary" v-if="activeTab=='database'">{{$t("content.dataset.datasetDetail.sjjgbg")}}</el-button>
      <el-button v-if="activeTab=='metadata'" @click="changeTab('database')">{{$t("content.dataset.datasetDetail.sjjgbg")}}</el-button>

      <el-button type="primary" v-if="activeTab=='metadata'">{{$t("content.dataset.datasetDetail.ywysjbg")}}</el-button>
      <el-button v-if="activeTab=='database'" @click="changeTab('metadata')">{{$t("content.dataset.datasetDetail.ywysjbg")}}</el-button>
    </el-button-group>

    <div v-if="activeTab == 'database'">
      <table class="table table-bordered tableList">
        <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort">
        </table-header>
        <tbody v-if="dataList.length != 0">
          <tr v-for="item in databaseList">
            <!-- <td>{{item.index}}</td> -->
            <td>{{item.changeDate}}</td>
            <td>{{item.userName}}</td>
            <td :class="{
              addParam: item.type == 'ADD',
              delParam: item.type == 'DELETE',
              modifyParam: item.type == 'MODIFY',
            }"
              >{{item.changeContent}}</td>
            <td>{{item.changeDetail}}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-if="activeTab == 'metadata'">
      <table class="table table-bordered tableList">
        <thead>
          <tr>
            <!-- <th>{{$t("content.dataset.datasetDetail.xh")}}</th> -->
            <th>{{$tc("content.dataset.datasetDetail.bg", 0)}}</th>
            <th>{{$tc("content.dataset.datasetDetail.bg", 1)}}</th>
            <th>{{$tc("content.dataset.datasetDetail.bgzd")}}</th>
            <th>{{$tc("content.dataset.datasetDetail.bgxq")}}</th>
          </tr>
        </thead>
        <tbody v-if="dataList.length != 0">
          <tr v-for="item in metadataList">
            <!-- <td>{{item.index}}</td> -->
            <td>{{formatTime(item.modified_date*1000)}}</td>
            <td>{{item.operator}}</td>
            <td>{{item.field}}</td>
            <td>{{item.old_value}} {{$tc("content.common.bw")}} {{item.new_value}}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <no-data :noDataVisible="dataList.length == 0"></no-data>
  </div>
</div>
</template>
<script>
  import { FormatTime } from 'mixins/common'
  export default{
    name: 'DataHistory',
    props: ['tableId'],
    mixins: [ FormatTime ],
    data(){
      return {
        dataList: [],
        totalPage: 1,
        currentPage: 1,
        loading:true,
        activeTab: 'database',
        tableHeader: [
          { name: this.$t("content.dataset.datasetDetail.xh"), hasSort:true, type: 'changeDate' },
          { name: this.$tc("content.dataset.datasetDetail.bg", 0) },
          { name: this.$tc("content.dataset.datasetDetail.bg", 1) },
          { name: this.$tc("content.dataset.datasetDetail.bg", 2) },
          { name: this.$t("content.dataset.datasetDetail.bgxq") },
        ],
        sort:"changeDate",
				order:"asc",
      }
    },
    created(){
      this.fetchBasicData();
    },
    watch: {
      '$route': function(val){
        if(val.query.path){
          this.fetchBasicData();
        }
      }
    },
    methods: {
      changeTab(tab){
        this.activeTab = tab;
        this.fetchBasicData()
      },
      fetchBasicData(){
        if(this.activeTab == 'metadata'){
          this.fetchMetadataData()
        }else{
          this.fetchDatasetData()
        }
      },
      fetchMetadataData(){
        const id = this.tableId;
        this.loading = true;
        this.$http.get(`/changereport/metadata/${id}`)
        .then((res)=>{
          this.loading = false;
          if(res.body.message.length>0){
            this.dataList = res.body.message;
          }else{
            this.dataList = [];
          }
        })
      },

      fetchDatasetData(){
        const id = this.tableId;
        this.loading = true;
        this.$http.get(`/changereport/dataset/${id}`)
        .then((res)=>{
          this.loading = false;
          if(res.body.message.length>0){
            this.dataList = res.body.message;
          }else{
            this.dataList = [];
          }
        })
      },
      changePage(val){
        console.log(val)
      }
    },
    computed: {
      metadataList(){
        // let list  = this.dataList;
        // let newBasicList = [];
        // let index = 0;
        // list.forEach((v, k) => {
        //   index++;
        //   v.index = index;
        //   v.changeDate = this.formatTime(v.changeDate);
        //   if(v.newName && !v.oldName){
        //     v.changeContent = this.$t('content.dataset.datasetDetail.xzzd');
        //     v.changeDetail = `${this.$t('content.common.xz')} ${v.newName}(${v.newType})`;
        //   }else if(!v.newName && v.oldName){
        //     v.changeContent = this.$t('content.dataset.datasetDetail.sczd');
        //     v.changeDetail = `${this.$t('content.common.sc')} ${v.oldName}(${v.oldType})`;
        //   }else if(v.newName && v.oldName){
        //     v.changeContent = this.$t('content.dataset.datasetDetail.zdlxbg');
        //     v.changeDetail = `${v.oldName}(${v.oldType}) ${this.$t('content.common.bw')} ${v.newName}(${v.newType})`;
        //   }
        //   newBasicList.push(v);
        // })
        return this.dataList;
      },

      databaseList(){
        let list = this.dataList;
        let newBasicList = [];
        let index = 0;
        list.forEach((v, k) => {
          index++;
          v.index = index;
          v.changeDate = this.formatTime(v.changeDate);

          if(v.changeCategory == 'DESC'){
            if(v.type == 'ADD'){
              v.changeContent = this.$t('content.dataset.datasetDetail.xzms');
              v.changeDetail = `${this.$t('content.common.xz')} ${v.newDesc}`;
            }else if(v.type == 'MODIFY'){
              v.changeContent = this.$t('content.dataset.datasetDetail.xgms');
              v.changeDetail = `${v.oldDesc} ${this.$t('content.common.bw')} ${v.newDesc}`;
            }else{
              v.changeContent = this.$t('content.dataset.datasetDetail.scms');
              v.changeDetail = `${this.$t('content.common.sc')} ${v.oldDesc}`;
            }
          }else{
            if(v.type == 'ADD'){
              v.changeContent = this.$t('content.dataset.datasetDetail.xzzd');
              v.changeDetail = `${this.$t('content.common.xz')} ${v.newName}(${v.newType})`;
            }else if(v.type == 'MODIFY'){
              v.changeContent = this.$t('content.dataset.datasetDetail.zdlxbg');
              v.changeDetail = `${v.oldName}(${v.oldType}) ${this.$t('content.common.bw')} ${v.newName}(${v.newType})`;
            }else{
              v.changeContent = this.$t('content.dataset.datasetDetail.sczd');
              v.changeDetail = `${this.$t('content.common.sc')} ${v.oldName}(${v.oldType})`;
            }
          }

          newBasicList.push(v);
        })
        return newBasicList;
      }
    }
  }
</script>
<style>
  .addParam{
    color: #6dc18a;
  }
  .delParam{
    color: red;
  }
  .modifyParam{
    color: #6690c6;
  }
</style>
