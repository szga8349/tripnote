<template>
<div>
	<div class="title">
		<div class="titShow">
			<h4>
				<span>元数据变更</span>
				<span class="path" v-for="(item, index) in pathShow">
					<a @click="setRouter(item)" href="javascript:;" v-if="index == 0"> > {{item}}</a>
					<span v-if="!tableSummary && index != 0"> > {{item}}</span>
				<!-- 	<a @click="setRouter(item)" href="javascript:;" v-if="!tableSummary && index != 0"> > {{item}}</a> -->
				</span>
			</h4>
		</div>
	</div>
	<div class="tabs" id="TblTab">
		<ul>
			<li v-for="(value, key) in tabList"
				@click="tabChange(key)"
       			:class="{active: activeTab == key}"
			>{{value}}</li>
		</ul>
	</div>
	<div class="tabCon">
		<div class="loading" v-if="tableConLoading"></div>

		<div class="boxCommon" :class="{hidden: activeTab != 'tableList'}" v-if="{active: activeTab == 'tableList'}">
			<div class="con">
				<div class="tableTitle">
					<!-- <div class="name" v-if="tableSummary">变更记录</div> -->
					<!-- <div class="tit" v-if="!tableSummary">
						<a href="javascript:;" @click="tableSummary = true">返回</a>
					</div> -->

					<!-- <a href="/api/v1/exportDatasetChangeReportAbout" style="float: right" v-if="tableSummary">下载Excel</a>

					<a href="javascript:;" @click="downloadDetail()" style="float: right" v-if="!tableSummary">下载Excel</a> -->

				</div>
				<div class="tit" v-if="!tableSummary">
						<!-- <a href="/api/v1/exportDatasetChangeReportAbout"  @click="downloadDetail()"></a> -->
					<a href="javascript:;"  @click="downloadDetail()" >
						<span><img src="../assets/images/icon_download.png"></span>
						变更记录报表下载
					</a>
				</div>
				<table class="table table-bordered tableList" v-if="tableSummary">
					<thead>
						<tr>
							<th>数据源类型</th>
							<th>数据源名称</th>
							<th>新增总数</th>
							<th>修改总数</th>
							<th>删除总数</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(value, key) in tableDetail">
							<td>{{value.source}}</td>
							<td>{{value.sourceName}}</td>
							<td><a href="javascript:;" @click="detailInfo(value.source, value.sourceName, 0)">{{value.addNum}}</a></td>
							<td><a href="javascript:;" @click="detailInfo(value.source, value.sourceName, 1)">{{value.modifiedNum}}</a></td>
							<td><a href="javascript:;" @click="detailInfo(value.source, value.sourceName, 2)">{{value.deleteNum}}</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered tableList" v-if="!tableSummary">
					<thead>
						<tr>
							<th>数据源类型</th>
							<th>数据源名称</th>
							<th>变更类型</th>
							<th>变更元数据</th>
							<th>变更时间</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(value, key) in tableSourceDetail">
							<td>{{value.source}}</td>
							<td>{{value.sourceName}}</td>
							<td>{{value.type}}</td>
							<td>{{value.name}}</td>
							<td>{{value.dateStr}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="boxCommon" :class="{hidden: activeTab != 'chart'}" v-if="{active: activeTab == 'chart'}">
			<div class="highchartCon">
				<highcharts :options="hightchartsOptions"></highcharts>
				<!-- <highcharts :chartType="chartType" :series="chartSeries" :date="chartDate"></highcharts> -->
			</div>
		</div>
	</div>

</div>
</div>

</template>

<script>
// import Highcharts from './Highcharts'
export default {
	components: {
		// Highcharts
	},
	data() {
		return {
			tableConLoading: false,
			tabList: {
				'tableList': '元数据变更统计表',
				'chart': '元数据变更统计图'
			},
			tableId: '',
			activeTab: 'tableList',
			tableDetail: {},
			tableSourceDetail: {},

			chartType: 'column',
			chartSeries: [],
			chartDate: [],

			tableTitle: '',
			tableSummary: true,

			detailSource: '',
			detailSourceName: '',
			detailType: '',

			hightchartsOptions: {}

		}
	},
	props: {
	    name: String
	},
	created() {
		this.fetchData()
	},
	watch: {
		'$route': 'fetchData'
	},
	computed: {
		pathShow(){
			let activeTab = this.tabList[this.activeTab];
			let tableTitle = this.tableTitle || "";
			let pathArr = [];
			if(tableTitle == ""){
				pathArr = [activeTab];
			}else{
				pathArr = [activeTab, tableTitle];
			}
			return pathArr;
		}
	},
	methods: {
		setRouter(title){
			if(title === '元数据变更统计表'){
				this.tableSummary = true;
			}else{
				this.tableSummary = false;
			}
		},
		nameChange(name){
			if(this.nameObj[name]){
				return this.nameObj[name]
			}else{
				return name
			}
		},
		tabChange: function(type) {
			this.activeTab = type;
			this.tableTitle = "";
			this.tableSummary = true;
			this.fetchData();
		},
		schemaView(type){
			if(type == 'table'){
				this.tableVisiable = true;
				this.jsonVisiable = false;

				this.jsonviewVisiable = false

			}else{
				this.tableVisiable = false;
				this.jsonVisiable = true;
				this.jsonviewVisiable = true
			}
		},
		fetchData() {
			this.tableConLoading = true

			let _type = this.activeTab || 'tableList';

			// if(_type == 'tableList'){
				this.$http.get('api/v1/getDatasetChangeReportAbout')
				.then(function(ret) {
					if(ret.body.return_code == 407){
						this.$router.push({ name: 'Login'})
						return
					}

					this.tableDetail = ret.body.data;

					let _add = []
					let _modified = []
					let _del = []


					for (var i = 0; i < ret.body.data.length; i++) {
						this.chartDate.push(ret.body.data[i].sourceName + '(' + ret.body.data[i].source + ')')

						_add.push(ret.body.data[i].addNum)
						_modified.push(ret.body.data[i].modifiedNum)
						_del.push(ret.body.data[i].deleteNum)
					}

					this.chartSeries = [
						{
							name: '新增总数',
							data: _add,
							maxPointWidth: 10,
							color: '#91c7af'
						},
						{
							name: '修改总数',
							data: _modified,
							maxPointWidth: 10,
							color: '#61a0a7'
						},
						{
							name: '删除总数',
							data: _del,
							maxPointWidth: 10,
							color: '#ca8623'
						}
					]

					this.hightchartsOptions = {
						chart: {
							type: 'column',
							height: 300,
							spacingRight: 20
						},
						title: {
							text: ''
						},
						subtitle: {
							text: ''
						},
						// colors: ['#059EFE'],
						plotOptions: {
							line: {
								lineWidth: 1.5,
								marker: {
									enabled: true,
									radius: 2.5,
									states: {
										hover: {
											enabled: true,
											radius: 5
										}
									}
								},
								shadow: false
							},
							area: {
								fillColor: '#7e9df2',
								lineColor: '#7e9df2',
								marker:{
									fillColor: '#3BABF3',
									lineColor: '#3BABF3',
									lineWidth: 1,
									radius: 6,
									states: {
										hover: {
											lineWidthPlus: 6,
											lineColor: '#3BABF3',
											fillColor: '#fff'
										}
									}
								}
							},
						},
						xAxis: {
							categories: this.date,
							tickmarkPlacement: 'on',
							title:{
								// text:'时间',
								align:'high',
								style: {
									fontSize: '14px'
								}
							}
						},
						yAxis: {
							title: {
								text: '修改次数',
								style: {
									fontSize: '14px'
								}
							},
							endOnTick: false,
							lineWidth:1,
							minTickInterval: 1,
							allowDecimals: false,
							plotLines: [{
								value: 0,
								width: 1,
								color: '#808080'
							}]
						},
						tooltip: {
							valueSuffix: '',
							crosshairs: {
								width: 1,
								color: '#eee',
								dashStyle: 'solid'
							}
						},
						credits: {
							enabled: false
						},
						legend: {
							enabled: false
						},
						series: this.chartSeries
					}

					this.tableConLoading = false
				})
			// }
		},

		detailInfo(source, sourceName, type){
			this.tableSummary = false

			this.detailSource = source
			this.detailSourceName = sourceName
			this.detailType = type

			let _typeName
			if(type == 0){
				_typeName = '新增'
			}else if(type == 1){
				_typeName = '修改'
			}else if(type == 2){
				_typeName = '删除'
			}
			this.tableTitle = sourceName + '(' + source + ')' + '--' + _typeName

			this.$http.get('api/v1/getDatasetChangeReportDetails?type=' + this.detailType + '&source=' + this.detailSource + '&sourceName=' + this.detailSourceName)
			.then(function(ret) {

				this.tableSourceDetail = ret.body.data;
			})

		},

		downloadDetail(){
			window.location.href = '/api/v1/exportDatasetChangeReportDetails?type=' + this.detailType + '&source=' + this.detailSource + '&sourceName=' + this.detailSourceName
		},


		handleSizeChange(val) {
			console.log(`每页 ${val} 条`);
		},
		handleCurrentChange(page) {
			this.fetchCommentData(page)
		}
	}
}
</script>
<style>
	.highchartCon{
		border:1px solid #eef0f1;padding: 20px 0;
	}
</style>
