<template>
<div>
	我的
</div>
</template>

<script>
export default {
	data() {
		return {
			datasetType: []
		}
	},
	created() {
		// this.$store.commit('setMenuType', 'dataset')
		this.fetchData()
	},
	computed: {
		menuType () {
			return this.$store.state.menuType
		}
	},
	watch: {
		'$route': 'fetchData'
	},
	methods: {
		fetchData() {
			this.$http.get('static/data/sources.json')
			.then(function(ret) {
				this.datasetType = ret.body.sources;
			})
			// .then(function(err) {
			// 	console.log(err)
			// })
		}
	}
}
</script>

<style>
</style>