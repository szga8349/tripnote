<template>
	<div class="message-container">
		<el-dialog :title="$t('content.common.tis')" :visible.sync="dialogVisible" width="385px">
		  <slot name="content"></slot>
		  <span slot="footer" class="dialog-footer">
		    <el-button type="primary" @click="visible">确 定</el-button>
		  </span>
		</el-dialog>
	</div>
</template>


<script>
  export default {
	  data(){
	  	return{
	  		dialogVisible:true
	  	}
	  },
	  watch:{
	  	'dialogVisible':function(newValue){
	  		if(!newValue){
	  			this.$emit('visible');
	  		}
	  	}
	  },
	  methods:{
  		visible(){
  			this.dialogVisible = false;
  		}
	  }
  };
</script>
