<template>
	<div class="business-detail-container">
    <loading :isloading="loading"></loading>
    <no-data v-if="!loading && dataList.length == 0 && (!path || !hasPrivilege)"></no-data>
    <div
      v-else-if="!loading && dataList.length == 0 && hasPrivilege"
      class="no-data"
      @click="addData">
      <span v-html="$t('content.dataset.businessDetail.dqztmysjbqdjtj')"></span>
    </div>
		<div class="tabCon" v-else-if="!loading && dataList.length">
			<div class="boxCommon" v-if="allNoData">
				<no-data :noDataVisible="allNoData"></no-data>
			</div>
			<div class="boxCommon" v-else>
				<div class="table-title">
					<div class="table-title__text">
						<span>{{title}}一{{$t('content.dataset.businessDetail.ztlb')}}</span>
					</div>
					<div class="table-title__btns" v-if="hasPrivilege">
						<el-button type="primary" @click.native="addData">
							{{$t('content.dataset.businessDetail.tjbdgzt')}}
						</el-button>
					</div>
				</div>
				<loading :isloading="loading"></loading>
					<table class="table table-bordered tableList" v-if="!loading">
					  	<table-header
						    	:tableHeader = "tableHeader"
						    	:sort = "sort"
						    	:order = "order"
						    	@tableSort="tableSort">
					  	</table-header>
					  <tbody v-if="dataList.length !== 0">
	  	  		        <item v-for="item in dataList"
			  	  		  		:params="{
					  						params:{
					  						  id: item.datasetId,
					  							path:item.path,
					  						},
				  							isfolder:false
			  						}"
			  					:names = "[item.datasetName, item.businessName]"
			  					:view="true"
			  					:datasetId="item.datasetId">
                            <td><div v-ellipsis="item.datasourceName"></div></td>
								<td><div v-ellipsis="item.physicalPath.split('/')[item.physicalPath.split('/').length - 2]"></div></td>
								<td><div v-ellipsis="item.path"></div></td>
								<td><div v-ellipsis="item.description"></div></td>
								<td>{{item.size}}</td>
								<td><div v-ellipsis="item.createdTime"></div></td>
								<td><div v-ellipsis="item.lastModifyTime"></div></td>
	  	  		  </item>
					  </tbody>
					</table>
					<div class="tablePages" v-if="dataList.length !== 0">
						<el-pagination background
						layout="total, prev, pager, next"
						@size-change="handleSizeChange"
						@current-change="handleCurrentChange"
						:current-page="currentPage"
						:page-size="pageSize"
						:total="totalRows"
						v-if="totalRows>pageSize">
						</el-pagination>
					</div>
			</div>
		</div>
		<el-dialog
		:visible.sync="dialogVisible"
		:title='`${$t("content.dataset.businessDetail.tjsjd")}-"${title}"${$t("content.dataset.businessDetail.zhut")}`'
		:close-on-click-modal="closeModal"
		custom-class="dialogComment">
			<div class="business-list-container">
				<div class="dbList businessCheckBox">
					<h4 class="business-select-title">{{$t('content.dataset.businessDetail.qxzb')}}：</h4>
					<el-form class="check-group-container"
									:model="businessTree"
									:rules="businessTreeRule"
									ref="businessTree"
                  style="background: #fff;">
					 <el-form-item prop="checkList">
						<div>
							<div>
								<div class="addtheme-searchWrap">
									<input type="text"
										:placeholder="$t('content.dataset.businessDetail.qsrnyssdnr')"
										v-model="treeSearchKeywords"
										@keyup.enter="treeSearch">
										<input type="text" style="display: none;" />
									<a href="javascript:;"
										 class="el-icon-search"
										 @click="treeSearch"></a>
								</div>
								<el-checkbox-group
                  v-model="businessTree.checkList"
                  style="min-height: 250px;">
                  <loading :isloading="dialogLoading"></loading>
                  <div style="position: relative; height: 100%;">
                    <more-loading :isTree="true" :isloading="treeBusinessLoading"></more-loading>
  									<tree-view
                      :keyword="treeSearchKeywords"
                      :model="treeData"
                      v-if="treeData.length !== 0 && !dialogLoading"
  									></tree-view>
                    <no-data :noDataVisible="treeData.length == 0 && !dialogLoading && !treeBusinessLoading"></no-data>
                  </div>
								</el-checkbox-group>
							</div>
						</div>
						</el-form-item>
					</el-form>
					<div class="businessTrrError" v-if="businessTreeError">
						{{`${$t('content.dataset.datasetDetail.qzsxzygxm')}!`}}
					</div>
				</div>
				<div class="select-center">
					<img :src="imgUrl">
				</div>
				<div class="selected-list-box">
					<h4 class="business-select-title">{{$t('content.dataset.businessDetail.yxb')}}：({{checkList.length-1}})</h4>
					<div class="check-group-container selected-check-list">
						<ul>
							<li
								:key="item[0]"
								v-for="item in selectedList"
								:title="item[1].path">
								<div class="selected-list">
									<i class="list-top-icon"></i>
									<span class="show-title" v-ellipsis="item[1].selectTitle"></span>
									<span v-if="item[1].businessName" v-ellipsis:190="`(${item[1].businessName})`"></span>
								</div>
								<i class="el-icon-close" @click="delSelectItem(item[1].selectTitle)"></i>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button @click.native="dialogVisible = false">{{$t('content.common.qx')}}</el-button>
				<el-button type="primary" @click.native="postPath('businessTree')">{{$t('content.common.qd')}}</el-button>
      </span>
		</el-dialog>
	</div>
</template>
<script>
import { FormatTime, FormatSize, testPrivilege, Message, reStructureTreeData} from 'mixins/common';
import TreeView from './BusinessTree.vue'
import item from 'components/common/TableItemJump'
import {mapState} from 'vuex'
import Bus from 'utils/bus'
	export default{
		name:'businessDetail',

		mixins: [ FormatTime,FormatSize,Message,testPrivilege,reStructureTreeData],
		components: {
			TreeView,
			item,
		},
		data(){
      let err
			let businessTreeValid = (rule, value, callback)=>{
				if(value.length == 1){
					err = true;
				}else{
					err = false;
					callback()
				}
			}
			return {
				businessTreeError: err,
				businessTreeRule: {
					checkList: [
						{validator:businessTreeValid}
					]
				},
				businessTree: {
					checkList: []
				},
				tableHeader:[
        		{
                    name:this.$t('content.dataset.businessDetail.biaom'),
                    type:"datasetName",
                    hasSort:true
                },
                {
                    name:this.$t('content.dataset.businessDetail.biem'),
                    type:"businessName",
                    hasSort:false,
                },
                {
                  name: this.$t('content.import.sjy'),
                  type: "datasourceName",
                  hasSort: true,
                },
                {
                    name: this.$t('content.dataset.datasetDetail.km'),
                    type: "physicalPath",
                    hasSort: false,
                },

				// {
				// 	name:this.$t('content.dataset.businessDetail.wlstlj'),
				// 	type:"physicalPath",
				// 	hasSort:true,
				// },
				{
					name:this.$t('content.dataset.businessDetail.ywstlj'),
					type:"path",
					hasSort:true
				},
				{
					name:this.$t('content.dataset.businessDetail.miaos'),
					type:"description",
					hasSort:true,
				},{
					name:this.$t('content.dataset.datasetDetail.dx'),
					type:"size",
					hasSort:true,
				},{
					name:this.$t('content.dataset.businessDetail.cjsj'),
					type:"createdTime",
					hasSort:true,
				},{
					name:this.$t('content.dataset.businessDetail.zhsjsj'),
					type:"lastModifyTime",
					hasSort:true,
				}],
				loading: true,
				dialogLoading: true,
				dataList:[],
				fields:[],
				closeModal: false,
				dialogVisible: false,
				treeData:[],
				sort:"businessName",
				order:"asc",
				currentPage: 1,
				totalRows: 1,
				pageSize: 10,
				allNoData: false,
				imgUrl: require('assets/images/icon_select.png'),
				treeSearchKeywords: '',
				idNum: 0,
			}
		},
		created(){
			this.fetchData();
			this.privilege();
		},
		computed:{
			...mapState({
				tableTypeOpts: 'tableTypeOpts',
			}),
			treeBusinessLoading: {
					get(){
							return this.$store.state.treeBusinessLoading
					},
					set(val){
							this.$store.dispatch('treeBusinessLoading', val)
					}
			},
			checkList(){
				let newList = new Set();
				this.$store.state.themeCheckList.forEach(v=>{
					newList.add(v.selectPath);
				})
				newList.add('disabled');
				this.businessTree.checkList = [...newList]
				return this.businessTree.checkList;
			},
			selectedList(){
				const newList = new Map();
				this.$store.state.themeCheckList.forEach((v,i)=>{
					newList.set(i, v);
				})
				return [...newList];
			},
			path(){
				let path = this.$route.query.path ? this.$route.query.path: this.$store.state.businessState.path
				return path;
			},
			title(){
				let title = this.$route.query.title ? this.$route.query.title : this.$store.state.businessState.title
				return title;
			},
		},
		watch:{
            dialogVisible(val){
                this.businessTreeError = false
            },
            '$route'(val){
                if(val.query.path){
                    this.fetchData()
                }
            },
		},
		methods:{
			privilege(){//测试权限
				const _url = `/topic/delete`;
				this.testPrivilege(_url);
			},
			async getExitData(){
				const vm = this;
                vm.businessTreeError = false
				vm.dialogLoading = true
				const targetPath = this.$route.query.path;
				let url = `/datasets/treeSelect`;
				let params = {
					targetTopicPath: targetPath,
					level:0,
					path:"",
					page: 1,
					rows: 20,
				}
				let headerData = {contentType: 'application/json', dataType: 'json'};
				const res = await this.$http.post(url ,params,headerData);
				const {statusCode, message} = res.body;
				if(statusCode === 200){
					vm.dialogLoading = false
                    vm.$store.dispatch('treeBusinessLoading', false)
                    const {records} = message
                    records.length>0 && vm.setOpen(records)
                    vm.treeData = records
                    vm.reStructureTreeData()
				}
			},
			isNodata(){
				const routerQuery = this.$route.query;
				const {path} = routerQuery;
				if(path == ''){
					this.allNoData = true;
					return;
				}else{
					this.allNoData = false;
				}
			},
			fetchData(page=1){
				this.isNodata();
				const vm = this;
				const url = `/topicDataset/getDatasetInfo?path=${encodeURIComponent(this.path)}&status=${this.tableTypeOpts}&sort=${this.sort}&order=${this.order}&page=${page}&rows=10`;
				vm.loading = true;
				vm.$http.get(url,{contentType: 'application/json',
				    dataType: 'json'}).then(res=>{
				    	vm.loading = false;
							const {statusCode, message} = res.body;
				    	if(statusCode == 200){
				    		// let data = message;
								Object.assign(this.$data, message)
				    		let _data = [];

				    		message.records.forEach((val)=>{
				    			let {businessName,datasetName, datasourceName, description,size,createdTime,lastModifyTime,datasetId,path, physicalPath} = val;
				    			let _obj = {
				    				businessName,
				    				datasetName,
                                    datasourceName,
				    				description,
				    				datasetId,
									path,
									physicalPath,
				    				size:this.formatSize(size),
				    				createdTime:this.formatTime(createdTime),
				    				lastModifyTime:this.formatTime(lastModifyTime),
				    			}
				    			_data.push(_obj);
				    		})
				    		this.dataList = _data;
				    	}else{
				    		this.dataList = [];
				    	}

				    })
			},
			tableSort({sort,order}){
				this.sort = sort;
				this.order = order;
				this.fetchData();
			},
			addData(){
				this.treeSearchKeywords = ''
				this.dialogVisible = true
                this.dialogLoading = true
                this.treeBusinessLoading = false
				this.treeData = []
				this.businessTreeError = false
				this.$nextTick(()=>{
					this.$refs['businessTree'].resetFields();
				})
				this.$store.dispatch('addThemeCheckList');
				this.getExitData();
			},
			async postPath(formName){
				// this.$refs[formName].validate(async valid=>{
					// if(valid){
						let checkData = this.checkList;
                        if((checkData.length-1)==0){
                          this.businessTreeError = true
                          return
                        }else{
                          this.businessTreeError = false
                        }
						const path = this.$route.query.path;
						const folder = true;
						const disabledIndex = checkData.findIndex((v)=>{
							return v == 'disabled';
						});
						checkData.splice(disabledIndex, 1);
						const datasetIdList = checkData.join(',');
						const data = {
							path,
							datasetIdList
						}
						let res = await this.$http.post('/topicDataset/addTopic', JSON.stringify(data),{contentType: 'application/json', dataType: 'json'});
						this.message(this.$t('content.dataset.businessDetail.tjcg'),"success");
						this.dialogVisible = false;
						this.fetchData();
						Bus.$emit('refreshTree',{path,datasetId:0});
					// }else{
						// return false;
					// }
				// })
			},
			handleSizeChange(val) {
		        console.log(`每页 ${val} 条`);
	    },
	  	handleCurrentChange(val) {
	    	this.fetchData(val)
    	    	console.log(`当前页: ${val}`);
    	  	},
			delSelectItem(item){
				this.$store.dispatch('delThemeCheckList', item);
			},
			async treeSearch(){
				const vm = this;
				vm.dialogLoading = true;
				const targetPath = this.$route.query.path;
				let url = `/datasets/treeSelect`;
				let params = {
					targetTopicPath: targetPath,
					level:0,
					path:"",
					key:vm.treeSearchKeywords,
                    page: 1,
                    rows: 20
				}
				let headerData = {contentType: 'application/json', dataType: 'json'}
				const res = await this.$http.post(url,params,headerData)
				const {statusCode,message} = res.body
                const {records} = message
				if(statusCode == 200){
                    this.treeData = []
                    this.$store.dispatch('treeBusinessLoading', false)
                    this.dialogLoading = false
                    vm.setOpen(records)
                    vm.treeData = records
                    vm.reStructureTreeData()
				}else{
					vm.treeData = []
				}
			},
			setOpen(value){
				const vm = this
				if(!value.length){
					return;
				}
				value.map(val=>{
					if(val.children){
						vm.$set(val, 'open', true);
						vm.setOpen(val.children.records)
					}else{
						vm.$set(val, 'open', false)
					}
				})
			},
		}
	}
</script>
<style lang="less">
  @import '../../../../vars.less';

  .datasetCon {
    width: 100%;
    height: 100%;
  }

  .datasetCon .business-detail-container {
    position: relative;
    width: 100%;
    height: calc(~"100% - 131px");
  }

  .no-data {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 175px;
    height: 218px;
    background: url("../../@{imagesUrl}/metaData/add_table.png") 0 0 no-repeat;
    cursor: pointer;
    span {
      display: block;
      margin-top: 155px;
      line-height: 25px;
      text-align: center;
    }
  }

  .no-data:hover {
    background-image: url("../../@{imagesUrl}/metaData/add_table_hover.png");
  }
</style>
