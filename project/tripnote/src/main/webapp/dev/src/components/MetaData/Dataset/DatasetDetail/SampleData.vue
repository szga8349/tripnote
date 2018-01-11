<template>
	<div class="boxCommon sample-data">
		<loading :isloading="loading"></loading>
		<!-- <div class="table-nodata-container" v-if="tableSample.length === 0 &&!loading">
			<div class="noData">
	      <p>暂无变更记录</p>
	    </div>
		</div> -->
		<div class="con" style="overflow:auto" v-if="!loading">
			<div style=" overflow: auto; margin-top: 13px;">
				<table class="table table-bordered tableList">
					<thead>
						<tr>
							<th v-for="value in tableSampleKey">
								<div v-ellipsis:200="value"></div>
							</th>
						</tr>
					</thead>
					<tbody v-if="tableSample.length > 0">
						<tr v-for="(value, key) in tableSample">
							<td v-for="item in tableSampleKey">
								<div v-ellipsis="value[item]"></div>
							</td>
						</tr>
					</tbody>
				</table>
				<no-data :noDataVisible="tableSample.length === 0"></no-data>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				loading:true,
				tableSampleKey: {},
				tableSample: [],
			}
		},
		props:['tableId'],
		watch:{
			'$route': function(val){
				if(val.query.path){
					this.fetchData();
				}
			},
		},
    	created(){
    		this.fetchData();
    	},
    	methods:{
    		fetchData(){
	    		let _url = '/datasets/'+this.tableId+'/sample';
	    		this.loading = true;
	    		this.$http.get(_url).then(res=>{
	    			this.loading = false;
	    			if(res.body.statusCode === 200 && res.body.message){
	    				this.tableSample = res.body.message.sample;
							this.tableSampleKey = res.body.message.columns;
							this.tableSample.forEach((v)=>{
								for(var key in v){
									if(key === 'P1'){
										let valueArr = v[key].split('.');
										if(valueArr.length>1 && valueArr[1] == 0){
											v[key] = valueArr[0];
										}
									}
								}
							})

	    			}else{
						this.tableSample = [];
	    				// this.tableSampleKey = this.tableSample[0];
	    			}
	    		})
    	}
    }
}
</script>
<style scope>
	.sample-data .tableList{
		table-layout: auto;
	}
</style>
