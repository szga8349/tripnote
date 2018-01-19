<!-- <template>
	<div class="loading-container" style="border: 1px solid blue;">
		<img style="border: 1px solid blue;" src={this.imgSrc} />
  </div>
</template> -->
<script>
	export default{
		name: "Loading",
		data(){
			return {
				// imgSrc: require('assets/images/loading.gif')
			}
		},
		props:{
			isloading:{
				type: Boolean,
				default: false
			},
			// isTree: {
			// 	default: false
			// }
		},
		render(h){
			if(this.isloading){
				return <div class="loadingBox">
							<i class="el-icon-loading"></i>
					 </div>
			}else{
				return
			}
		}
	}
</script>
<style media="screen" scope>
	.loadingBox{
		margin-top: 120px;
		min-height: 60px;
		text-align: center;
		font-size: 30px;
	}
</style>
