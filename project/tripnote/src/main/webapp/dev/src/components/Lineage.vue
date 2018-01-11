<template>
<div id="mainContent">
	<div id="mainSplitter" ng-app="myModule" ng-controller="treeController">
		<div id="graphSplitter" class="splitter-panel">
			<div style="height:100%; width:100%;position:relative;min-height:800px; overflow:hidden;">
				
				<button id="chainselectorbtn" title="select the chains" click="toggleChains()" style="display:none;background-color: transparent;opacity: 0.5;" class="btn pull-left"><i class="fa fa-random"></i></button>
				<button id="rotationgraphbtn" title="rotate the graph" style="background-color: transparent;opacity: 0.5;" class="btn pull-left hidden"><i class="fa fa-repeat"></i></button>
				<h4 id="title"></h4>
				<input id="lineageType" style="display: none;" value=azkaban>
				<input id="lineageID" style="display: none;" value=0>
				<input id="application" style="display: none;" value=AZKABAN-TEST>
				<input id="project" style="display: none;" value=move_data>
				<input id="flow" style="display: none;" value=3>
				<div id="controls" class="search">
					<input id="searchfield" title="Search" tabindex="1" class="search-field" type="text">
					<i class="search-icon fa fa-search"></i>
					<i id="search-clear" title="Clear search" tabindex="1" class="search-clear">✕<span class="description">Clear search</span></i>
					<i title="Submit search" tabindex="1" style="display:none;" class="search-submit fa fa-arrow-right"><span class="description">Submit search</span></i>
				</div>
				<div id="loading" style="position:absolute;top:12px;left:500px;display: none;"><i class="fa fa-spinner spinning fa-4x"></i></div>
				
				<div id="canvas" tabindex="0" style="width: 800px">
				<svg id="svg-canvas" width="1024"></svg>
			</div>
		</div>
	</div>
	<div id="nodeInfoSplitter">
		<ul id="nodeInfoTab" class="nav nav-tabs">
			<li id="datatabpage"><a id="datatablink" data-toggle="tab" href="#datanodestab">Data</a></li>
			<li id="jobtabpage"><a id="jobtablink" data-toggle="tab" href="#jobnodestab">Job</a></li>
		</ul>
		<div class="tab-content">
			<div id="datanodestab" class="tab-pane">
				<table id="lineagedatatable" class="table table-bordered">
				</table>
			</div>
			<div id="jobnodestab" class="tab-pane">
				<table id="lineagejobtable" class="table table-bordered">
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</template>

<script>

// 引入折现图
let echarts = require('assets/vendors/jquery-ui-1.11.0/jquery-ui.js');


export default {
	components: {
		
	},
	data() {
		return {
			datasetType: [],
			treeData: [],
			treeDataInit: false
		}
	},
	created() {
		// this.$store.commit('setMenuType', 'dataset')
		this.fetchTreeData()
	},
	computed: {
		menuType () {
			return this.$store.state.menuType
		}
	},
	watch: {
		'$route': 'fetchTreeData'
	},
	methods: {
		setFirst(data){
			data[0]['open'] = true;
			if(data[0].children && data[0].children.length > 0){
				this.setFirst(data[0].children)
			}else{
				window.location.href = '/#/Dataset/TableDetail?id='+data[0].id+'&path='+data[0].path
			}
		},
		fetchTreeData() {
			if(this.treeDataInit){
				return;
			}

			this.$http.get('tree/datasets').then((response) => {
				let _data = response.body.children;
				this.setFirst(_data)

				this.treeData = _data;

				this.treeDataInit = true;
			}, (response) => {
				// error callback
			});
		}
	}
}
</script>

<style>
</style>