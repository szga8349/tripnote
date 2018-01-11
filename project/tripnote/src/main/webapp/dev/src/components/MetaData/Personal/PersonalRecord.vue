<template>
  <div class="boxCommon">
    <div class="table-title">
      <div class="table-title__text">
        <span>{{$t('content.personal.sqjllb')}}</span>
      </div>
      <div class="table-title__btns">
        <Search
            :placeholder="$t('content.validator.qsrbm')"
            emitName="recordSearch"
            @recordSearch="fetchData">
        </Search>
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
              <th width="100">{{$t('content.dataset.datasetDetail.cz')}}</th>
            </table-header>
            <tbody v-if="records.length !==0 ">
               <item v-for="(data, index) in formatStatus"
                :params="{
                  params:{
                    path:data.path,
                    level:3
                  },
                  disabled: data.disable==1,
                  isfolder: false,
                }"
                :names = "emphasizeName(data.tableName,key)"
                :datasetId="data.datasetId"
                :view="false"
                :indexKey="index"
                :isPrivate="data.isPrivate"
                :isDeleted = "data.isDeleted">
                <td>
                    <div v-ellipsis="data.path.split('/')[data.path.split('/').length - 2]"></div>
                </td>
                <td><div v-ellipsis="formatTime(data.create_time*1000)"></div></td>
                <td><div v-ellipsis="data.ownedProject"></div></td>
                <td><div v-ellipsis="data.usedProject"></div></td>
                <td><div v-ellipsis="data.md_approver"></div></td>
                <td v-html="data.status_mark"></td>
                <td class="table-button-parent">
                  <div class="personal-control">
                    <a href="javascript:;"
                      @click="viewDetails({id,tableName,opinion,md_msg} = data)">
                      {{$t('content.common.ckxq')}}
                    </a>
                  </div>
                </td>
               </item>
            </tbody>
      </table>
      <div class="tablePages" v-if="records.length !==0 ">
        <el-pagination background
        layout="total, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="totalRows"
        v-if="totalRows>pageSize"
        >
        </el-pagination>
      </div>
      <no-data v-else></no-data>
    </template>
      <el-dialog
        :title="$t('content.dataset.datasetDetail.sqqx')"
        :visible.sync="dialogVisible"
        :close-on-click-modal="false"
        custom-class="dialogComment"
          @click.native.stop>
          <el-form
            label-width="160px">
            <el-form-item
              :label="item.label"
              v-for="item in permissionsDetail"
              v-if="!(item.field ==='opinion'&&!item.content)">
               <el-col :span="20">
                 <div class="grid-content detailCon">{{item.content}}</div>
              </el-col>
            </el-form-item>
            <el-form-item
              v-if="md_msg"
              label="$t('content.personal.jjly'):">
                 <div class="grid-content" v-ellipsis="md_msg"></div>
            </el-form-item>
          </el-form>
          <loading :isloading="elLoading" isTree="true"/>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">{{$t('content.common.qd')}}</el-button>
        </div>
      </el-dialog>
  </div>
</template>
<script>
  import item from 'common/TableItemJump'
  import Search from 'common/Search'
  import {FormatTime,EmphasizeName} from 'mixins/common'

  export default{
    name:"PersonalRecord",
    mixins:[FormatTime,EmphasizeName],
    components: {
      item,
      Search,
    },
    data(){
      return {
        tableHeader:[{
          name:this.$t('content.dataset.datasetDetail.bm'),//表名
          type:"tableName",
          hasSort:true,
        },{
            name:this.$t('content.dataset.datasetDetail.km'),
            type:"path",
            hasSort:false,
        },{
          name:this.$t('content.personal.sqsj'),
          type:"create_time",
          hasSort:true,
        },{
          name:this.$t('content.personal.ssxm'),
          type:"ownedProject",
          hasSort:true,
        },{
          name:this.$t('content.personal.qxssxm'),
          type:"pusedProject",
          hasSort:false,
        },{
          name:this.$t('content.personal.shzh'),
          type:"audit_account",
          hasSort:false,
        },{
          name:this.$t('content.common.zhuangt'),
          type:"status",
          hasSort:false,
        }],
        sort: "create_time",
        order: "desc",
        records:[],
        currentPage:1,//当前页
        pageSize:0,//每页记录数
        totalRows:0,//总记录数
        dialogVisible:false,
        formParamsArr:[
            {field:"table_name",label: this.$t('content.personal.zzsqdbw'),content:""},
            {field:"type",label:this.$t('content.dataset.datasetDetail.qxlx'),content:""},
            {field:"project_name",label:this.$t('content.dataset.datasetDetail.sygbqxdxm'),content:""},
            {field:"reason",label:this.$t('content.dataset.datasetDetail.sqly')+':',content:""},
            {field:"final_approver",label:this.$t('content.personal.shr')+':',content:""},
            {field:"md_msg",label:this.$t('content.personal.jjyy'),content:""},
        ],
        details:{},
        loading:true,
        key:"",
        elLoading: true   //Element组件中的Loading
      }
    },
    computed:{
      formatStatus: function (value) {
        if (!value) return ''
          let status_mark= "";
          this.records.forEach(val=>{
            switch(val.md_result){
               case 0:
                  val.status_mark = `<span style="color: orange;">${this.$t('content.personal.dsh')}</span>`
                  break;
              case 1:
                  val.status_mark = `<span style="color: green;">${this.$t('content.personal.shtg')}</span>`
                  break;
              case 11:
                  val.status_mark = `<span style="color: orange;">${this.$t('content.personal.shz')}</span>`
                  break;
              case 2:
                  val.status_mark = `<span style="color: red;">${this.$t('content.personal.shbtg')}</span>`
                  break;
              }
          })
          return this.records;
      },
      permissionsDetail(){
        function isEmptyObject(obj){
           for(var n in obj){return false}
           return true;
        }
        const _isEmptyObject = isEmptyObject.call(null,this.details);
        if(_isEmptyObject){
          return this.formParamsArr
        }else{
          const formParamsArr = this.formParamsArr;
          const details = this.details;
          const {type} = details;
          details.type = Array.from(type,(val)=> val === "r" ? this.$t('content.authority.r') : this.$t('content.authority.w')).join(",");
          return formParamsArr.map((val)=>{
            const field = val.field;
            if(field && Object.prototype.hasOwnProperty.call(details, field)){
              if((field == 'md_msg' || field == 'final_approver') && details[field] == undefined || details[field] == ''){
                  return ''
              }

              this.$set(val,"content",details[field])
              return val;
            }
          })
        }
      },//详情数据
    },
    methods:{
      reasonTxt(reason){
          if(reason == '当前用户没有在项目里面分配组，无法提交资源权限请求。'){
              return this.$t('content.personal.myqxz')
          }else if(reason == '当前用户已经拥有该资源权限，不需要提交请求。'){
              return this.$t('content.personal.yyqx')
          }else if(reason == 'get wrong message when approvling,please try again later'){
              return this.$t('content.personal.leapidspsb')
          }else if(reason == 'approvle failed'){
              return this.$t('content.personal.leapidspsb')
          }else{
              return reason
          }
      },
      tableSort({sort,order}){
        this.sort = sort;
        this.order = order;
        this.fetchData({
            key: this.key
          });
      },
      async fetchData({key = "",currentPage = 1} = {}){
        this.loading = true;
        this.key = key;
        const url = `/privilege/getPrivilegeApplys?keyword=${escape(key)}&sort=${this.sort}&order=${this.order}&page=${currentPage}&rows=10`;
        const res = await this.$http.get(url);
        const {statusCode} = res.body;
         this.loading = false;
         statusCode === 200 ? Object.assign(this.$data,res.body.message) :  this.records=[]
        },
      async viewDetails({id, tableName, opinion, md_msg, final_approver}){
        this.dialogVisible = true;
        this.elLoading = true;
        const url = `/privilege/${id}/getPrivilegeApplyInfo`;
        const res = await this.$http.get(url);
        let {statusCode, message} = res.body;
        opinion = opinion === "null" ? '' : opinion;
        final_approver = final_approver === "null" ? '' : final_approver;
        md_msg = md_msg === "null" ? '' : md_msg;

        md_msg = this.reasonTxt(md_msg)

        let table_name = tableName;
        statusCode === 200 ? Object.assign(message, {table_name, opinion, md_msg, final_approver}) : message = {};

        this.details = message;
        this.elLoading = false;   //请求结束，model已经有数据了，关闭loading层
      },
        handleSizeChange(val) {
          console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
          const params = {
            currentPage: val,
            key: this.key
          }
          this.fetchData(params)
        }
    },
    created(){
      this.fetchData()

    }
  }
</script>
<style>
.detailCon{
  margin-top: 7px;
  line-height: 20px;
  word-break: break-all;
}
  .el-tooltip__popper.newLight {
    line-height: 24px;
    padding: 5px;
    color:#666;
    box-shadow: 1px 1px 2px 2px #ddd;
    border: 1px solid #ccc;
  }
  .el-tooltip__popper.newLight[x-placement^=right] .popper__arrow{
    border-right-color:#ccc;
  }
  .el-dialog__body {
    position: relative;
  }
  .el-dialog__body .loading-container {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    /*background-color: #f3f3f3;*/
  }
  .el-form-item{
    margin-bottom: 0;
  }
  .loading-container img {
    transform: translate(-50%,-50%);
    position: absolute;
    top: 50%;
    left: 50%;
  }
</style>
