<template>
<div id="MainCon">
	<!-- <div class="datasetList" style="border: 1px solid red;">
		<div class="subNav">
			<ul>
				<router-link to="/DataChange/DataChangeRecord" tag="li">
					元数据变更记录
				</router-link>
			</ul>
		</div>
	</div> -->

	<div class="content">
		<router-view></router-view>
	</div>
</div>
</template>

<script>
require('../assets/libs/splitter/jquery.splitter.js');
import '../assets/libs/splitter/jquery.splitter.css'

export default {
	components: {
	},
	data() {
		return {
			treeDataInit: false,
			datasetLoading: false
		}
	},
	created() {
		this.$router.push({ name: 'DataChangeRecord', append: true});
	},
	computed: {
	},
	watch: {
	},
	methods: {

	}
}
</script>
<style>
	.content{
		margin-left: 0;
	}
</style>
