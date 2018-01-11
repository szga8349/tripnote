<template>
<div class="datasetCon">
	<div class="tableDetail">
		<div class="tabs" id="TblTab" >
			<ul>
				<!-- hasPrivilege -->
				<li v-for="item in reTabList"
						v-if="item[0]!='permission' || hasPrivilege"
						@click="tabChange(item[0])"
						:class="{active: activeTab == item[0],
						hidden: (pathType == 'hdfs' && item[0] == 'sampleData') ||
        						(pathType == 'hdfs' && item[0] == 'history') ||
        						(pathType == 'mysql' && item[0] == 'lineage') ||
        						(pathType == 'sqlserver' && item[0] == 'lineage') ||
        						(pathType == 'oracle' && item[0] == 'lineage') ||
        						(pathType == 'postgresql' && item[0] == 'lineage') ||
								(dataStatus == 2 && item[0] == 'sampleData') ||
								((dataStatus == 1 || dataStatus == 2) && item[0] == 'permission') ||
								((dataStatus == 1 || dataStatus == 2) && item[0] == 'lineage') ||
								(dataStatus == 2 && item[0] == 'history') ||
								(dataStatus == 2 && item[0] == 'comments') ||
								(getSample == 'false' && item[0] == 'sampleData') ||
								(!hasApplyAuthority && (item[0] == 'permission' || item[0] == 'lineage') )
  					}"
				>
					{{item[1]}}
				</li>
			</ul>
		</div>

		<div class="tabCon">
			<!-- 属性信息 -->
            <properties :tableId = "tableId" v-if="activeTab == 'properties'"></properties>
            <!-- 字段信息 -->
            <schema :tableId="tableId" v-if="activeTab == 'dataStructure'"></schema>
			<!-- 注释信息 -->
			<comments :tableId = "tableId" v-if="activeTab == 'comments'"></comments>
			<!-- 负责人 -->
			<div class="boxCommon" v-if="activeTab == 'ownership'">
				<div class="con">
					<table class="table table-bordered tableList" v-if="tableOwners.length > 0 ">
						<thead>
							<tr>
								<th>User Name</th>
								<th>Full Name</th>
								<th>Owner ID Type</th>
								<th>Owner Source</th>
								<th>Owner Type</th>
								<th>Owner Sub Type</th>
								<th>Confirmed</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(item, index) in tableOwners">
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 变更记录 -->
			<div class="boxCommon" v-if="activeTab == 'history'">
				<data-history :tableId = "tableId"></data-history>
			</div>
		  	<!-- 样本数据 -->
			<sampleData class="boxCommon" :tableId="tableId" v-if="activeTab == 'sampleData'"></sampleData>
			<!-- 权限信息 -->
			<data-permission :tableId = "tableId" v-if="activeTab == 'permission'"></data-permission>
		  	<!-- 血缘关系 -->
			<div class="iframe" v-if="activeTab == 'lineage'">
				<iframe :src="lineageIframeHref" style="border:0; height: 900px; width:100%" id="LineageIframe"></iframe>
			</div>
		</div>
	</div>
</div>

</template>

<script>

import Bus from 'utils/bus'
import Properties from './Properties'
import Comments from './Comments'
import Schema from './Schema'
import SampleData from './SampleData'

import DataHistory from './DataHistory'
import DataPermission from './DataPermission'
import {testPrivilege} from 'mixins/common' //时间格式化
import {mapState} from 'vuex'

export default {
	components: {
		Properties,//属性信息
		Comments,//注释信息
		Schema,//数据结构
		SampleData,//样本数据
		DataHistory,//变更记录
		DataPermission,//权限信息

	},
	mixins: [testPrivilege],
	data() {
		return {
			tabList: new Map([
				['properties', this.$t('content.dataset.datasetDetail.sxxx')],
                ['dataStructure', this.$t('content.dataset.datasetDetail.sjjg')],
				['sampleData', this.$t('content.dataset.datasetDetail.ybsj')],
				['history', this.$t('content.dataset.datasetDetail.bgls')],
				['permission', this.$t('content.dataset.datasetDetail.qxxx')],
				['lineage', this.$t('content.dataset.datasetDetail.xygx')],
				['comments', this.$t('content.dataset.datasetDetail.zsxx')]
			]),//tab
			activeTab: 'properties',//当前选择tab,
			tableOwners:[],
			//血缘关系
			lineageIframeHref: '',
            initStatus: true,
            hasApplyAuthority: false
		}
	},
	mounted() {
        this.fetchAll();
	    
        Bus.$on('hasApplyAuthority', state=>{
            this.hasApplyAuthority = state == 0 ? false : true;
        })
	},
	watch: {
		'$route'(val){
            if(val.query.path){
                this.fetchAll();
                this.activeTab = 'properties';
            }
		}
	},
	computed: {
		reTabList(){
			return [...this.tabList];
		},
		tableId (){
			let id = this.$route.query.id ? this.$route.query.id : this.$store.state.fetchRouter.datasetId;
			// let id = this.$route.query.id;
			if(!id){
				return
			}
			return id;
		},
		path(){
			let _path =  this.$route.query.path;
			if(_path){
				let path = _path.replace('://','/');
				return path;
			}
		},
		pathType (){
			let _path = this.path;
			if(!_path){
				return
			}
			let _type =_path.split("/")[0];
			return _type;
		},
	    ...mapState({
			dataStatus: 'dataStatus',
			getSample: 'getSample'
	    })
	},
	methods: {
		tabChange: function(type) {
			this.activeTab = type;
			this.fetchData();
		},//tab切换

		async fetchAll(){
			if(this.tableId){
				await this.testPrivilege(`/privilege/getAuthorizedAccounts`,'get',`datasetId=${this.tableId}`)

				this.$store.dispatch('propEditPrivilege', this.hasPrivilege)
				this.fetchData();
			}
		},
		fetchData() {
			let _type = this.activeTab || 'properties';
			if(_type == 'ownership'){
				this.$http.get('/api/v1/datasets/'+this.tableId+'/owners')
				.then(function(ret) {
					this.tableOwners = ret.body.owners;
					this.tableConLoading = false
				})
			}else if(_type == 'sampleData'){
				if (this.pathType == 'hdfs'){  // 如果选择hive， 自动跳转到属性信息
					this.activeTab = 'properties';
					return;
				}
			}else if(_type == 'lineage'){
				this.lineageIframeHref = '/static/lineage.html?type=dataset&id=' + this.tableId
				this.tableConLoading = false
			}
		},
	}
}
</script>