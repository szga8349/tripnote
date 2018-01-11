<template>
<div class="boxCommon">
  <loading :isloading="loadingVisible"></loading>
	<div class="con" v-if="!loadingVisible">
		<div class="table-title">
			<div class="table-title__text">
				<span>{{$t('content.dataset.datasetDetail.zdxx')}}</span>
			</div>
		</div>
		<div class="schema">
			<div v-if="tableSchema.length > 0">
				<div id="TableView" class="con" v-if="tableVisiable">
          <loading :isloading="fieldLoading"></loading>
					<table v-if="!fieldLoading" class="table table-bordered tableList">
						<thead>
							<tr>
								<th width="20%">{{$t('content.dataset.datasetDetail.zid')}}</th>
								<th width="20%">{{$t("content.dataset.datasetDetail.sjlx")}}</th>
								<th class="pl40" width="60%">{{$t("content.dataset.datasetDetail.zdms")}}</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(item, index) in tableSchema">
								<td>
									<div v-ellipsis="item.fieldName"></div>
								</td>
								<td>
	                <div v-ellipsis="item.dataType"></div>
	              </td>
                <property-item
                  :value="item.comment"
                  :itemName="item"
                  :parentLabel="editedItem"
                  :placeholder="$t('content.dataset.datasetDetail.sjsrzdms')"
                  @emitDoneEdit="doneEdit"></property-item>
							</tr>
						</tbody>
					</table>
          <div class="tablePages" v-if="tableSchema.length !== 0 && totalRows>pageSize && !fieldLoading">
						<el-pagination background
						layout="total, prev, pager, next"
						@current-change="handleCurrentChange"
						:current-page="currentPage"
						:page-size="pageSize"
						:total="totalRows"
						>
						</el-pagination>
					</div>
				</div>
        <div class="con" v-if="partition">
          <div class="tableMulti">
            <div>
              <div class="table-title">
                <div class="table-title__text">
                  <span>{{$t('content.dataset.datasetDetail.fqxx')}}</span>
                </div>
              </div>
              <table class="table table-bordered tableList">
                <thead>
                <tr>
                  <th width="30%">{{$t('content.dataset.datasetDetail.mc')}}</th>
                  <th>{{$t('content.dataset.datasetDetail.sx')}}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>{{$t('content.dataset.datasetDetail.fqzd')}}</td>
                  <td>
                    {{partition}}
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
			</div>
			<no-data :noDataVisible="tableSchema.length === 0"></no-data>
		</div>
	</div>
</div>
</template>
<script>
// import JsonView from '../JsonView'
import Bus from 'utils/bus'
import { Message,testPrivilege } from 'mixins/common' //公用方法
import PropertyItem from './PropertyItem'

	export default{
		components:{
			// JsonView,
      PropertyItem,
		},
		mixins: [Message,testPrivilege],
		data(){
			return {
        editedItem: null,
        errors: null,
				tableSchema:[],
				tableVisiable:true,// 显示表格和JSON视图
				columnId:0,
				columnCommentId: '',
				clickDisabled:false,
        partition: null,   //分区信息
        loadingVisible: true,
        hasErrors: false,
        currentPage: 1,
        totalRows: 1,
				pageSize: 10,
        fieldLoading: false
			}
		},
		props:["tableId"],
		created(){
      Bus.$on('setEditLabel', label=>{
        this.editedItem = label
      })
			this.privilege()
			this.fetchData()
      this.fetchPartitionData()
		},
		watch:{
			'$route': function(val) {
        if(val.query.path){
  				this.privilege();
  				this.fetchData();
        }
			},
		},
    directives: {
      'focus': function (el, binding) {
        if (binding.value) {
          el.focus();
        }
      }
    },
		methods:{
      handleCurrentChange(val) {
	    	this.fetchData(val)
	    	console.log(`当前页: ${val}`);
	  	},
      editItem: function (item) {
        this.beforeEditCache = item.comment
        this.editedItem = item
      },
      validator(type, value){
        let _reg = /^[A-Za-z\d\_\-\u4e00-\u9fa5]*$/;
        this.errors = '';
        if (_reg.test(value)) {
          if (value.length <= 20) {
            return true;
          } else {
            this.message(this.$t('content.dataset.datasetDetail.zdsr20zf'), "error");
            return false;
          }
        } else {
          this.message(this.$t('content.validator.hzywszxhxhx'), "error");
          return false;
        }
      },
      doneEdit: function (item) {
        if (!this.editedItem) {
          return;
        }

        if (!item.comment) {
          item.comment= '';
        }

        item.comment = item.comment.trim()

        if (item.comment.length <= 20) {
          this.hasErrors = false
          this.editedItem = null
          if (!item.comment) {
            item.comment = ''
          }
          if (item.comment !== this.beforeEditCache) {
            this.CommentSubmit(item)
          }
        } else {
          this.hasErrors = true
          this.message(this.$t('content.dataset.datasetDetail.zdsr20zf'), "error")
          return;
        }
      },
      cancelEdit: function (item) {
        this.editedItem = null;
        item.comment = this.beforeEditCache;
      },
      CommentSubmit(item){
          let _param = {
            text: item.comment,
            id: item.commentId
          };
          this.columnId = item.id;
          this.$http({
            method: 'POST',
            url: '/datasets/'+this.tableId+'/columns/' + this.columnId + '/comments',
            params: _param,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
          }).then(function(res) {
            if(res.body.statusCode === 200){
              //this.message("修改成功!","success");
            }else{
              this.message("修改失败!","error");
            }
          })
      },
			privilege(){
				const _editUrl = `/datasets/${this.tableId}/columns/${this.columnId}/comments`;
				this.testPrivilege(_editUrl);
			},
			schemaView(tab){
				if(tab == "table"){
					this.tableVisiable = true
				}else if(tab == "json"){
					this.tableVisiable = false
				}
			},
			fetchData(page=1){
        this.fieldLoading = true
				let _url = `/datasets/${this.tableId}/columns?page=${page}&rows=10`
				this.$http.get(_url).then(res=>{
          this.fieldLoading = false
					if(res.body.statusCode !== 200){
						this.tableSchema = [];
					}else{
            this.loadingVisible = false
            Object.assign(this.$data, res.body.message)
						this.tableSchema = res.body.message.records
					}
					this.noDataVisible = this.tableSchema.length
				})
			},
      async fetchPartitionData(){
        const res = await this.$http.get(`/datasets/${this.tableId}/commoninfo`)
        if(res.body.statusCode == 200){
          if(res.body.message.datasetInfo.properties){
            this.partition = res.body.message.datasetInfo.properties.partition_column
          }else{
            this.partition = null
          }
        }
      }
		}
	}
</script>
<style>
	.properties .schema .el-form-item__error{
		left: 0;
		top: 100%;
	}
</style>
