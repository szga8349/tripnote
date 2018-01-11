<template>
	<div class="datasetDetail datasourceDetail">
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
							<span v-for="(item, index) in tableInfo">
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
			  	    	@tableSort="tableSort"
			      	>
							<th width="100">{{$t('content.dataset.datasetDetail.cz')}}</th>
					</table-header>
				    <tbody v-if="databaseList.totalRows>0">
							<item v-for="item in databaseList.records"
								:params="{
									params:{
										title:item.databaseName,
										path:`${isActive.path}/${item.databaseName}`,
										level:3,
									},
									isfolder:true
								}"
								:names = "item.databaseName"
								:view="false">
							<td>{{item.tableCount}}</td>
							<td>{{formatSize(item.size)}}</td>
							<td>{{item.change_num}}</td>
							<td>{{item.deletedCount}}</td>
							<td>{{formatSize(item.change_size)}}</td>
							<td class="table-button-parent" style="padding: 0; text-align: center;">
								<a @click="setDialogShow(item.databaseName)" href="javascript:;">{{$t('content.common.ckgd')}}</a>
								<a
									@click="delBase({databaseName:item.databaseName, refId: item.refId, refIdType:item.refIdType})"
									href="javascript:;"
									v-if="item.isSystemAdmin == 1">
										{{$t('content.common.sc')}}
								</a>
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
      		@close = "closeAll"
			:close-on-click-modal="false"
			custom-class="dialogComment"
			size="large">
			<div class="chart-header">
				<el-tabs v-model="activeChart" type="card" @tab-click="resolveData">
						<el-tab-pane :label="$t('content.dataset.datasetDetail.bxzsl')" name="createdTable"></el-tab-pane>
						<el-tab-pane :label="$t('content.dataset.datasetDetail.xzccqk')" name="createdStorage"></el-tab-pane>
				</el-tabs>
				<div class="date-picker-container" style="display: flex;">
					<span>{{$t('content.dataset.datasetDetail.xzsj')}}</span>
					<date-picker ref="datePicker" :direction="'right'" @fectchDate="fectchDate"></date-picker>
				</div>
			</div>
			<div class="chart-container" v-if="activeChart=='createdTable'">
				<!-- {{createdTableOptions}} -->
				<highcharts v-if="chartMessage.length>0" :options="createdTableOptions"></highcharts>
				<no-data v-else></no-data>
			</div>
			<div class="chart-container" v-else>
				<highcharts v-if="chartMessage.length>0" :options="createdStorageOptions"></highcharts>
				<no-data v-else></no-data>
			</div>
		</el-dialog>

        <!-- 删除确认框 -->
        <el-dialog
          :title="$t('content.common.tis')"
          :visible.sync="delDialogVisible"
          width="385px"
          :close-on-click-modal="false"
          @click.native.stop>
          <div class="del-content">
              {{$t('content.personal.qrsck')}} "{{databaseName}}"?
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button
              @click="delDialogVisible = false">
              {{$t('content.common.qx')}}
            </el-button>
            <el-button
              type="primary" @click="delDatasetFetch()">
              {{$t('content.common.qd')}}
            </el-button>
          </div>
        </el-dialog>
	</div>
</template>

<script>
import { FormatSize, Message } from 'mixins/common'
import item from 'components/common/TableItemJump'
import DatePicker from 'components/common/DatePicker'
import Bus from 'utils/bus'
import {mapState} from 'vuex'
export default {
	mixins: [ FormatSize, Message ],
	components:{
		item,
		DatePicker
	},
	data() {
		return {
			tableHeader:[{
				name:this.$t('content.dataset.datasetDetail.km'),
				type:"databaseName",
				hasSort:true,
			},
			{
				name: this.$t('content.dataset.datasetDetail.bsl'),
				type:"tableCount",
				hasSort:true,
			},
			{
				name: this.$t('content.dataset.datasetDetail.ccdx'),
				type:"size",
				hasSort:true,
			},
			{
				name: this.$t('content.dataset.datasetDetail.bxzsl'),
				type:"change_num",
				hasSort:true,
			},
			{
				name: this.$t('content.dataset.datasetDetail.scdbsl'),
				type:"deletedCount",
				hasSort:true,
			},
			{
				name: this.$t('content.dataset.datasetDetail.xzccdx'),
				type:"change_size",
				hasSort:true,
			},

			// {
			// 	name:"表新增数量",
			// 	type:"createdTable",
			// 	hasSort:true,
			// },
			// {
			// 	name:"新增存储大小",
			// 	type:"createdStorage",
			// 	hasSort:true,
			// },
			// {
			// 	name:"所属项目",
			// 	type:"projectName",
			// 	hasSort:true,
			// },
			// {
			// 	name:"项目管理员",
			// 	type:"manager",
			// 	hasSort:true,
			// },
			],
            delDialogVisible: false,
            databaseName: '',
            refId: '',
            refIdType: '',
			databaseList:{},
			loading:true,
			sort:"databaseName",
			order:"asc",
			dialogVisible: false,
			activeChart: 'createdTable',
			createdTableOptions: {},
			createdStorageOptions: {},
			chartMessage: [],
			categories: new Set(),
			series: new Set(),
			db_name: '',
			begin_date: '',
			end_date: '',
			isClear: false,
			messageTable: {},
		}
	},
	computed:{
		...mapState({
			tableTypeOpts: 'tableTypeOpts',
			isActive: 'treeItemActive'
		}),
		// isActive(){
		// 	return this.$store.state.treeItemActive;
		// },
		title(){
			return this.$route.query.title;
		},
		path(){
			return this.$route.query.path;
		},
		tableInfo(){
			let tableInfo = new Map();
			tableInfo.set(this.$t('content.dataset.datasetDetail.sjzs'), this.messageTable.total_num);
			tableInfo.set(this.$t('content.dataset.datasetDetail.sjkzdx'), this.formatSize(this.messageTable.total_size));
			// tableInfo.set(this.$t('content.dataset.datasetDetail.zxzdx'), this.formatSize(this.messageTable.total_change));
			return [...tableInfo]
		}
	},
	created() {
		this.fetchAll()
	},
	mounted(){
		this.$watch('dialogVisible', val=>{
			this.isClear = val ? true : false;
			this.activeChart = val ? this.activeChart : 'createdTable';
			val && this.$refs.datePicker.handleClickIcon(true);
		})
	},
	watch: {
		'$route': function(val){
			if(val.query.path){
				this.fetchAll()
			}
		},
	},
	methods: {
        delBase(Params = {}){
            Object.assign(this.$data, Params)
            this.delDialogVisible = true
        },
        async delDatasetFetch(){
            this.delDialogVisible = false
            const res = await this.$http.post(`/datasets/deleteDataset/${this.databaseName}/${this.refId}/${this.refIdType}`)
            const {statusCode} = res.body
            if(statusCode == 200){
                this.message(`${this.$t('content.common.sccg')}!`, "success");
				this.fetchData()
				this.fetchTopInfo()
				let open = this.$store.state.modelOpen
				// Bus.$emit('deletSource', {path: this.path, datasetId: 0, open})

				var _p = this.path;

				if(this.databaseList.totalRows == 1){
					_p = _p.substring(0, _p.lastIndexOf('/'))
				}

                Bus.$emit('refreshTree', {path: _p, datasetId: 0});
            }else{
                this.message(`${this.$t('content.personal.scsb')}!`, "error");
            }
        },
		setDialogShow(db_name){
			this.db_name = db_name
			this.dialogVisible = true
		},
		closeAll(){
			this.$refs.datePicker.$refs.date.hidePicker();  //日期选择控件关闭函数
		},
		fetchAll(val=1){
			this.fetchData(val)
			this.fetchTopInfo()
		},
		async fetchTopInfo(){
			let ref_id = this.$route.query.refId;
			if(ref_id){
				let res = await this.$http.get(`/datasets/getDbCount/${ref_id}?status=${this.tableTypeOpts}`)
				let {message, statusCode} = res.body;
				this.messageTable = statusCode == 200 ? message : {};
			}

		},
		fetchData(page = 1) {
			if(this.path){
				this.loading = true;
		    	let _url = `/datasets/${this.path}?sort=${this.sort}&order=${this.order}&rows=10&page=${page}&status=${this.tableTypeOpts}`;
		    	this.$http.get(_url).then(res=>{
		   		    this.loading = false;
		    		if(res.body.statusCode == 200 ){
		    			this.databaseList = res.body.message
		    		}else{
		    			this.databaseList.records = []
		    		}
		    	})
			}

		},
		tableSort({sort,order}){
			this.sort = sort
			this.order = order
			this.fetchAll()
		},
		handleSizeChange(val) {
	        console.log(`每页 ${val} 条`);
      	},
      	handleCurrentChange(val) {
    			this.fetchAll(val)
        	console.log(`当前页: ${val}`);
      	},
		fectchDate(arr){
			let [begin_date, end_date] = arr;
			Object.assign(this.$data, {begin_date, end_date});
			this.fetchChartData();
		},
        async fetchChartData(){
            let ref_id = this.$route.query.refId;
            let _url = `/datasets/getDbChangeDetail?ref_id=${ref_id}&db_name=${this.db_name}` + (this.begin_date ? `&begin_date=${this.begin_date}&end_date=${this.end_date}` : '');
            const res = await this.$http.get(_url);
            const {statusCode} = res.body;
            statusCode === 200 ? this.chartMessage = res.body.message : [];
            this.resolveData();
		},
		resolveData(){
			let vm = this;
			this.categories.clear();
			this.series.clear();
			let max = 0
			let itemSeries = {name: this.$t('content.dataset.datasetDetail.xzb'), data: []};
			for(let [index, item] of this.chartMessage.entries()){
				this.categories.add(item.create_date);
				max = item.change_num>max ? item.change_num : max
				this.activeChart == 'createdTable' ? itemSeries.data.push(item.change_num) : itemSeries.data.push(item.change_size);
			}
			this.series.add(itemSeries);
			this.createdTableOptions = {
        lang: {
          thousandsSep: ','
        },
				chart: {
					height: 300,
					type: 'line'
				},
				title: {
					text: this.$t('content.dataset.datasetDetail.sjkxbxzqk', [vm.db_name]),
					style: {
						fontSize: '14px'
					}
			  },
				tooltip: {
					backgroundColor:'#ab9fff',
					borderColor:'#ab9fff',
					borderRadius: 0,
					style: {
							color: '#fff',
					},
				},
			  xAxis: {
					title: {
						text: this.$t('content.source.sj'),
						align: 'high'
					},
			    categories: [...this.categories]
			  },
			  yAxis: [{
			    title: {
			      text: this.$t('content.dataset.datasetDetail.bsl'),
						align: 'high',
						rotation: 0
			    },
			    plotLines: [{
			      value: 0,
			      width: 1,
			      color: '#808080'
			    }],
					type : 'vaule',
					minInterval: 1,
					lineWidth: 1,
					lineColor: '#d6d9dc',
					allowDecimals:false
			  }],
				legend: {
					enabled: false
				},
				credits: {
					enabled: false
				},
			  series: [...this.series]
			};


			this.createdStorageOptions = {
				chart: {
					height: 300,
					type: 'line'
				},
				title: {
			    text: this.$t('content.dataset.datasetDetail.sjkxccxzqk', [vm.db_name]),
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
					lineColor: '#d6d9dc',
					labels: {
						formatter(){
							return vm.formatSize(this.value)
						}
					},
					lineWidth: 1,
					lineColor: '#d6d9dc',
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
											${vm.$t('content.dataset.datasetDetail.ccxz')}: ${vm.formatSize(this.y)}
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
			}
		},
	}
}
</script>
<style lang="less">
    .datasourceDetail{
        .dialogComment{
            width: 798px !important;
        }
    }
</style>
