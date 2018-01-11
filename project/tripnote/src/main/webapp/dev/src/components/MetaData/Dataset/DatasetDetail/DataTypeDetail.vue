<template>
	<div class="dataTypeDetail">
		<div class="tabCon">
			<div class="boxCommon" v-if="loading">
				<loading :isloading="loading"></loading>
			</div>
			<div class="boxCommon" v-else>
				<div class="table-title">
					<div class="table-title__text">
						<span>{{title}} {{$t('content.dataset.datasetDetail.sjylb')}}</span>
					</div>
				</div>
				<table class="table table-bordered tableList">
			    	<table-header
				    	:tableHeader = "tableHeader"
				    	:sort = "sort"
				    	:order = "order"
				    	@tableSort="tableSort"
			    	></table-header>
				  <tbody v-if="dataTypeList.totalRows>0">
					  <item v-for="item in dataTypeList.records"
					  		:params="{
								params:{
									title:item.dataSourceName,
									path:`${isActive.path}/${item.dataSourceName}`,
									level:2,
								},
								isfolder:true
							}"
							:names = "item.dataSourceName"
							:view="false">
					  	<td>{{item.ip}}</td>
				  		<td>{{item.port}}</td>
				  		<td>{{item.userName}}</td>
					  </item>
				  </tbody>
				</table>
				<no-data v-if="!dataTypeList.totalRows"></no-data>
				<div class="tablePages" v-else>
					<el-pagination background
					layout="total, prev, pager, next"
					@size-change="handleSizeChange"
					@current-change="handleCurrentChange"
					:current-page="dataTypeList.currentPage"
					:page-size="dataTypeList.pageSize"
					:total="dataTypeList.totalRows"
					v-if="dataTypeList.totalRows>dataTypeList.pageSize">
					</el-pagination>
				</div>

			</div>
		</div>
	</div>
</template>

<script>
import { FormatSize } from 'mixins/common'
import item from 'components/common/TableItemJump'
export default {
	mixins: [ FormatSize ],
	components:{
		item,
	},
	data() {
		return {
			tableHeader:[{
				name: this.$t('content.import.sjymc'),
				type:"dataSourceName",
				hasSort:true,
			},{
				name:"IP",
				type:"ip",
				hasSort:false,
			},{
				name: this.$t('content.import.dkh'),
				type:"port",
				hasSort:false,
			},{
				name:this.$t('content.import.yhm'),
				type:"userName",
				hasSort:false,
			}],
			dataTypeList:{},
			loading: false,
			isEdit:false,
			editModel:'',
			sort:"dataSourceName",
			order:"asc",
		}
	},
	computed:{
		isActive(){
			return this.$store.state.treeItemActive;
		},
		title(){
			return this.$route.query.title;
		},
		path(){
			return this.$route.query.path;
		},
	},
	created() {
		this.fetchData()
	},
	watch: {
		'$route': function(val){
			if(val.query.path){
				this.fetchData()
			}
		},
	},
	methods: {
		fetchData(page = 1) {
			if(this.path){
				this.loading = true;
		    	let _url = `/datasets/${this.path}?sort=${this.sort}&order=${this.order}&rows=10&page=${page}`;
		    	this.$http.get(_url).then(res=>{
		   		    this.loading = false;
		    		if(res.body.statusCode == 200 ){
		    			const data = res.body.message;
		    			this.fetchIp(data)
		    		}else{
		    			this.dataTypeList.records = []
		    		}
		    	})
			}
		},
		fetchIp(data){
			let records = data.records;
			records.map((val)=>{
				if(!val.url){
					return
				};
				const _reg = /\d{2,3}([.]\d{1,3}){3}:\d{1,5}/;//正则获取IP，感觉有点问题，之后修改
				const _url = val.url;
				let _result = _url.match(_reg)[0];
				let _IP = _result.split(":");
				val.ip = _IP[0];
				val.port = _IP[1];
			})
			this.dataTypeList = data;
		},
		tableSort({sort,order}){
			this.sort = sort;
			this.order = order;
			this.fetchData();
		},
		handleSizeChange(val) {
	        console.log(`每页 ${val} 条`);
      	},
      	handleCurrentChange(val) {
        	this.fetchData(val)
        	console.log(`当前页: ${val}`);
      	}
	}
}
</script>
