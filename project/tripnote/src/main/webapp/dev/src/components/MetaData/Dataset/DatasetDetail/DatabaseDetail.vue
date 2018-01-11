<template>
	<div class="databaseDetail">
	 	 <div class="tabCon">
	 	 	<div class="boxCommon" v-if="loading">
	 	 		<loading :isloading="loading"></loading>
	 	 	</div>
			<div class="boxCommon" v-else>
				<div class="table-title">
					<div class="table-title__text">
						<span>{{title}}</span>
						<span class="tit-detail">
							(
							<span v-for="item in tableInfo" v-if="item[1] || item[1] == 0">
								{{item[0]}}:
								<span class="subTitContent">{{item[1]}}</span>
								<span class="division-flag">;&nbsp;</span>
							</span>
							)
						</span>
					</div>
				</div>
				<table class="table table-bordered tableList">
				  	<table-header
				    	:tableHeader = "tableHeader"
				    	:sort = "sort"
				    	:order = "order"
				    	@tableSort="tableSort">
							<th width="100">{{$t('content.dataset.datasetDetail.cz')}}</th>
			    	</table-header>
				    <tbody v-if="databaseList.totalRows>0">
			  		     <item v-for="item in databaseList.records"
			  		  		@refresh="fetchData"
			  		  		:params="{
								params:{
								    id: item.id,
									path:`${isActive.path}/${item.datasetName}`,
									},
									isfolder:false
								}"
							:names = "item.datasetName"
							:view="false"
							:datasetId="item.id">
    		  		  		<td>{{formatSize(item.size)}}</td>
    						<td><div v-ellipsis="formatTime(item.lastModifyTime)"></div></td>
    						<td><div v-ellipsis="formatTime(item.createdTime)"></div></td>
								<!-- <td>{{switchLifeType({lifeDay:item.lifeDay,lifeType:item.lifeType})}}</td> -->
								<td><div v-ellipsis="item.description"></div></td>
    						<td>{{formatSize(item.change_size)}}</td>
                            <td class="table-button-parent">
                                <div class="personal-control">
                                    <a href="javascript:;"
                                    @click="setDialogShow(item.id)">
                                    {{$t('content.common.ckgd')}}
                                    </a>
                                </div>
                            </td>
			  		  </item>
				  </tbody>
				</table>
				<div class="tablePages" v-if="databaseList.totalRows !== 0">
					<el-pagination background
					layout="total, prev, pager, next"
					@size-change="handleSizeChange"
					@current-change="handleCurrentChange"
					:current-page="databaseList.currentPage"
					:page-size="databaseList.pageSize"
					:total="databaseList.totalRows"
					v-if="databaseList.totalRows>databaseList.pageSize">
				</el-pagination>
				</div>
				<no-data v-else></no-data>
			</div>
		</div>
		<el-dialog
			:title="$t('content.common.xq')"
			:visible.sync="dialogVisible"
			:close-on-click-modal="false"
            @close = "closeAll"
			custom-class="dialogComment"
			size="large">
			<div class="date-picker-container" style="display: flex;">
				<span>{{$t('content.dataset.datasetDetail.xzsj')}}</span>
				<date-picker @fectchDate="fectchDate" ref="datePicker"></date-picker>
			</div>
			<div class="chart-container">
				<highcharts
				 v-if="chartMessage.length>0"
				 :options="createdTableOptions"></highcharts>
				<no-data v-else></no-data>
			</div>
		</el-dialog>
	</div>
</template>
<script>
	import { FormatTime,FormatSize,FormatLifeDay } from 'mixins/common';
	import item from 'components/common/TableItemJump';
	import DatePicker from 'components/common/DatePicker';
	import { mapState } from 'vuex'
	export default{
		name:"DatabaseDetail",
		mixins: [ FormatTime,FormatSize,FormatLifeDay],
		components:{
			item,
			DatePicker,
		},
		data(){
			return {
				tableHeader:[{
					name:this.$t('content.dataset.datasetDetail.bm'),//表名
					type:"datasetName",
					hasSort:true
				},
				{
					name:this.$t('content.dataset.datasetDetail.dx'),//大小
					type:"size",
					hasSort:true
				},
				{
					name:this.$t('content.dataset.businessDetail.zhsjsj'),//修改时间
					type:"lastModifyTime",
					hasSort:true
				},
				{
					name:this.$t('content.dataset.datasetDetail.cjsj'),//创建时间
					type:"createdTime",
					hasSort:true
				},
				// {
				// 	name: this.$t('content.dataset.datasetDetail.smzq'),//生命周期
				// 	type:"lifeDay",
				// 	hasSort:false
				// },
				{
					name: this.$t('content.dataset.datasetDetail.ms'),//描述
					type:"datasetName",
					hasSort:false
				},
				{
					name:this.$t('content.dataset.datasetDetail.xzdx'),//修改时间
					type:"change_size",
					hasSort:true
				}],
				databaseList:{},
				sort:"datasetName",
				order:"asc",
				loading:true,
				dialogVisible: false,
				createdTableOptions: {},
				chartMessage: [],
				begin_date: '',
				end_date: '',
				categories: new Set(),
				series: new Set(),
				datasetId: '',
				isClear: true,
				message: {},
				records: [],
				totalRows: 0
			}
		},
		computed:{
			...mapState({
	            tableTypeOpts: 'tableTypeOpts',
	          }),
			isActive(){
				return this.$store.state.treeItemActive;
			},
			title(){
				return this.$route.query.title;
			},
			path(){
				return this.$route.query.path;
			},
			tableInfo(){
				let tableInfo = new Map();
				tableInfo.set(this.$t('content.dataset.datasetDetail.scxm'), this.message.name);
				tableInfo.set(this.$t('content.dataset.datasetDetail.xmgly'), this.message.manager);

				if(this.tableTypeOpts == '0,1,2'){
					tableInfo.set(this.$t('content.dataset.datasetDetail.bzs'), this.databaseList.totalRows + ', ' + this.$t('content.dataset.datasetDetail.qzbysc',[this.message.deletedCount]));
				}else{
					tableInfo.set(this.$t('content.dataset.datasetDetail.bzs'), this.databaseList.totalRows);
				}

				tableInfo.set(this.$t('content.dataset.datasetDetail.syzccdx'), this.formatSize(this.message.total_size));

				// tableInfo.set(this.$t('content.dataset.datasetDetail.xzdx'), this.formatSize(this.message.change_size));

				return [...tableInfo];
			}
		},
		created(){
			this.fetchAll()
		},
		mounted(){
			this.$watch('dialogVisible',(val)=>{
				val && this.$refs.datePicker.handleClickIcon(true)
			});
		},
		watch: {
			'$route': function(val){
				if(val.query.path){
					this.fetchAll()
				}
			}
		},
		methods:{
			setDialogShow(id){
				this.datasetId = id;
				this.dialogVisible = true;
			},
            closeAll(){
                this.$refs.datePicker.$refs.date.hidePicker()  //日期选择控件关闭函数
            },
			fetchAll(val=1){
				this.fetchData(val)
				this.$nextTick(()=>{
					this.fetchTopInfo()
				})
			},
			async fetchTopInfo(){
				let {refId, title} = this.$route.query
				if(refId){
					let url = ''
					if(this.tableTypeOpts == '0,1,2'){
						url = `/datasets/getDatasetCount/${refId}/${title}`
					}else{
						url = `/datasets/getDatasetCount/${refId}/${title}?status=${this.tableTypeOpts}`
					}
					let res = await this.$http.get(url)
					let {message, statusCode} = res.body
					this.message = statusCode == 200 ? message : {}
				}
			},
			fectchDate(arr){
				let [begin_date, end_date] = arr
				Object.assign(this.$data, {begin_date, end_date})
				this.showChart()
			},
			fetchData(page = 1){
				this.loading = true;
				if(this.path){
					let _url = "";
						_url = `/datasets/${this.path}?sort=${this.sort}&order=${this.order}&rows=10&page=${page}`;
                    if(this.tableTypeOpts != '0,1,2'){
                        _url += `&status=${this.tableTypeOpts}`
                    }
					this.$http.get(_url).then(res=>{
			    		this.loading = false;
			    		if(res.body.statusCode == 200 ){
								this.totalRows = res.body.totalRows
			    			this.databaseList = res.body.message
			    		}else{
			    			this.records = []
			    		}
					})
				}
			},
			tableSort({sort,order}){
				this.sort = sort;
				this.order = order;
				this.fetchAll();
			},
			async showChart(){
				this.dialogVisible = true;
				let _url = this.begin_date ? `/datasets/getDatasetChangeDetail?dataset_id=${this.datasetId}&begin_date=${this.begin_date}&end_date=${this.end_date}` : `/datasets/getDatasetChangeDetail?dataset_id=${this.datasetId}`;
				const res = await this.$http.get(_url);
				const {statusCode} = res.body;
				statusCode==200?this.chartMessage = res.body.message : [];
				this.resolveData();
			},
			resolveData(){
				const vm = this;
				this.categories.clear();
				this.series.clear();
				let itemSeries = {name: this.$t('content.dataset.datasetDetail.xzb'), data: []};
				for(let [index, item] of this.chartMessage.entries()){
					this.categories.add(item.create_date);
					this.activeChart == 'createdTable' ? itemSeries.data.push(item.change_num) : itemSeries.data.push(item.change_size);
				}
				this.series.add(itemSeries);

				this.createdTableOptions = {
					chart: {
						height: 300,
						type: 'line'
					},
					title: {
				    text: this.$t('content.dataset.datasetDetail.bsjxzxq'),
						style: {
							fontSize: '14px'
						}
				  },
				  xAxis: {
						title: {
							text: this.$t('content.source.sj'),
							align: 'high'
						},
				    categories: [...this.categories]
				  },
				  yAxis: {
				    title: {
				      text: this.$t('content.dataset.datasetDetail.dx'),
							align: 'high',
							rotation: 0
				    },
				    plotLines: [{
				      value: 0,
				      width: 1,
				      color: '#808080'
				    }],
						lineWidth: 1,
						lineColor: '#d6d9dc',
						labels: {
							formatter(){
								return vm.formatSize(this.value)
							}
						}
				  },
					tooltip: {
                        backgroundColor:'#ab9fff',
                        borderColor:'#ab9fff',
                        borderRadius: 0,
                        style: {
                            color: '#fff',
                        },
						pointFormatter(){
							return `<div>\
                                    ${vm.$t('content.dataset.datasetDetail.bsjxz')}: ${vm.formatSize(this.y)}
							         </div>`
						}
				  },
					legend: {
						enabled: false
					},
					credits: {
						enabled: false
					},
				  series: [...this.series]
				};
			},
		handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
	    },
	  	handleCurrentChange(val) {
	    	this.fetchAll(val)
	    	console.log(`当前页: ${val}`);
	  	}
	}
}
</script>
<style media="screen">
	.width-control-description{
		width: 200px;
	}
	.databaseDetail .dialogComment{
		width: 798px !important;
	}
</style>
