<template>
	<div class="table-container">
	    <div class="table-title" style="overflow: hidden;">
	    	<div class="table-title__text">
	    		<span>{{$t('content.common.xiangm')}}:{{project}}</span>
	    	</div>
	    </div>
			<!-- <div class="item-container">
				<ul v-for="item in rowsArr">
					<li>{{item}}</li>
				</ul>
			</div> -->
	    <table class="table table-bordered tableList" :style="{width: tableWidth + '%'}">
	      <tbody>
	       	 <permission-tr v-for="item in rowsArr" :model="item"></permission-tr>
	      </tbody>
	    </table>
	</div>
</template>
<script>
	//每一列
	const permissionTd = {
	  name: 'permissionTd',
	  template: `<td>
                	<span class="permission__user">{{ tdParams.username}}
                		<small
                		   class="permission__user-small"
                		   v-if="tdParams.account">
                		   (<span v-ellipsis:160="tdParams.account"></span>)
                		</small>
                	</span>

	                <div class="permission__authority">
	                  <i :class="item.classObj" v-for="item in tdParams.typeArr">{{item.text}}</i>
	                </div>
	              </td>`,
	 data(){
	 	return {
	 		classObject:{
	 			'permission__icon':true,
	 		}
	 	}
	 },
	 props:{
	 	model:Object
	 },
	 computed:{
	 	tdParams(){
	 		const {username,account,type} = this.model;
	 		const arr = ['r','w'];
	 		const typeArr = arr.map((val)=>{
	 			let obj = null;
	 			if(type.includes(val)){
	 				obj = {
						 ['permission__icon-'+val]: true
					};
		 		}
		 		let text = val === 'r' ? this.$t('content.authority.r') :this.$t('content.authority.w');
		 		return {classObj:{...obj,...this.classObject},text}
	 		})
	 		return {
	 			username,
	 			account,
	 			typeArr,
	 		}
	 	}
	 },
	};
	//每一行
	const permissionTr ={
		name:"permissionTr",
		template:`<tr>
					<permission-td v-for="item in model" :model="item"></permission-td>
				  </tr>`,
		components:{permissionTd},
		props:{model:Array},
	};
	//表格
	export default{
		 name: "DataPermissionItem",
    	 components:{permissionTr},
    	 props:{
    	 	project:{
    	 		type:String,
    	 		require:true
    	 	},
    	 	model:Array
    	 },
    	computed:{
    		rowsArr(){
    			const model = this.model;
    			const rows = [];
    			let oddRows = [];
    			model.forEach((val,index,model)=>{
    				oddRows.push(val);
    				if(index % 2 !== 0 ||index === model.length-1){
    					rows.push(oddRows)
    					oddRows = []
    				}
    			})
    			return rows
    		},
    		tableWidth(){
    			const len = this.rowsArr[0].length;
    			if(len && len === 1){
    				return 50
    			}else{
    				return 100
    			}
    		}
    	}
	}
</script>
