<template>
  <div class="boxCommon personal-collect">
    <div class="table-title">
      <div class="table-title__text">
          <span>{{$t('content.personal.scdsjlb')}}</span>
      </div>
      <div class="table-title__btns">
         <Search
           :placeholder="$t('content.validator.qsrbm')"
           emitName="collectSearch"
           @collectSearch="fetchData"
         ></Search>
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
          <th width="120">{{$t('content.dataset.datasetDetail.cz')}}</th>
        </table-header>
        <tbody v-if="!noDataVisible">
           <item v-for="(data, index) in showRecordList"
            :params="{
              params:{
                title: data.datasetName,
                path: data.path,
                level:3,
              },
              isfolder: false
            }"
            :indexKey="index"
            :names = "emphasizeName(data.datasetName,key)"
            :view="false"
            :datasetId="data.id">
            <td><div v-ellipsis="data.datasourceName"></div></td>
            <td>
                <div v-ellipsis="data.path.split('/')[data.path.split('/').length - 2]"></div>
            </td>
             <td><div v-ellipsis="formatTime(data.createdTime)"></div></td>
             <td>{{data.datasetType}}</td>
             <td>{{formatSize(data.size)}}</td>
             <!-- <td>{{data.showLifeDay}}</td> -->
             <td class="table-button-parent">
               <div class="personal-control">
                <a @click="confirmCancel(data.id)"
                  href="javascript:;">
                  {{$t('content.common.qxsc')}}
                </a>
               </div>
             </td>
           </item>
        </tbody>
      </table>
      <div class="tablePages" v-if="!noDataVisible">
        <el-pagination background
        layout="total, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size=10
        :total="totalRows"
        v-if="totalRows>10"
        >
        </el-pagination>
      </div>
      <no-data :noDataVisible="noDataVisible"></no-data>
    </template>
  </div>
</template>
<script>
  import item from 'common/TableItemJump'
  import Search from 'common/Search'
  import Bus from 'utils/bus'
  import {FormatTime, FormatSize, EmphasizeName, FormatLifeDay, Message} from 'mixins/common'
  export default{
    mixins:[FormatTime, FormatSize,EmphasizeName, FormatLifeDay, Message],
    data(){
      return {
        isClear: false,
        records: [],
        loading: true,
        currentPage: 1,
        pageSize: 10,
        totalRows: 1,
        noDataVisible: false,
        cancelId: '',
        key: '',
        lifeType: '',
        tableHeader: [
          {
            name: this.$t('content.dataset.businessDetail.biaom'),
            type:"datasetName",
            hasSort:true,
          },
          {
              name: this.$t('content.import.sjy'),
              type: "datasourceName",
              hasSort: true,
          },
          {
            name:this.$t('content.dataset.datasetDetail.km'),
            type:"path",
            hasSort:false,
          },
          {
            name:this.$t('content.personal.scsj'),
            type:"createdTime",
            hasSort:true,
          },
          {
            name: this.$t('content.dataset.datasetDetail.sjlx'),
            type:"datasetType",
            hasSort:true,
          },
          {
            name: this.$t('content.dataset.datasetDetail.ccdx'),
            type:"size",
            hasSort:true,
          },
          // {
          //   name: this.$t('content.dataset.datasetDetail.smzq'),
          //   type:"day_num",
          //   hasSort:false,
          // },
        ],
        sort:"createdTime",
        order:"desc",
        index: 0
      }
    },
    components: {
      item,
      Search,
    },
    created(){
      this.fetchData();
    },
    mounted(){
      Bus.$on('confirmCancel', this.confirmCancel);
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
      async fetchData({key='', currentPage=1} = {}){
        this.loading = true;
        this.key = key;
        let url = `datasets/favorites?keyword=${escape(key)}&rows=10&page=${currentPage}&sort=${this.sort}&order=${this.order}`;
        let self = this;
        const res = await self.$http.get(url);
        this.loading = false;
        if(res.body.statusCode == 200){
          Object.assign(this.$data, res.body.message);
          self.noDataVisible = this.records.length==0 ? true : false;
        }
      },
      confirmCancel(id){
        this.cancelId = id;
        this.index++;
        let self = this;
        const cancelUrl = `datasets/${this.cancelId}/favorite`;
        this.$http.delete(cancelUrl)
        .then(res=>{
          if(res.body.statusCode === 200){
            // this.isClear = true;
            self.fetchData({key: this.key, currentPage: this.currentPage})
          }
        })
        .then(()=>{
          this.message(`${this.$t('content.common.qxsc')}${this.$t('content.common.chengg')}!`, 'success')
        })
        .then(()=>{
          this.fetchCount()
        })
      },
      fetchCount(){
        this.$http.get('/datasets/getPersonalDataCount').then(res=>{
          const {statusCode, message} = res.body
          statusCode == 200 && this.$store.dispatch('saveCollectCount', message.Collect)
        })
      },
      handleCurrentChange(val){
        const params = {
          currentPage: val,
          key: this.key
        }
        this.fetchData(params);
      }
    },
  }
</script>
