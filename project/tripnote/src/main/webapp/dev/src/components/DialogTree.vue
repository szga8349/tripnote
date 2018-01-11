<template>
	<li>
		<div class="datasetMoveTo__tit"
			 v-if="model.folder"
			 :class="classType"
			 @click="toggle(model)">
			<el-row>
			  <el-col :span="20">
			    <span class="foldCon">
      				<i class="fold" :class="{open: model.open}"></i>
      			</span>
  				<span
  					class="datasetMoveTo__txt"
      				v-ellipsis:240="model.title">
      			</span>
			  </el-col>
			  <el-col :span="4" class="datasetMoveTo__radio">
			     <el-radio class="radio" :label="model.path"></el-radio>
			  </el-col>
			</el-row>
		</div>
		<ul v-if="model.open">
		  <item v-for="model in treeChildren" :model="model"></item>
		</ul>
	</li>
</template>

<script>
export default {
	name:'item',
	props: {
		model: Array
	},
  data() {
        return {
          active:"",
          treeChildren:[],
          radioActive:''
        };
  },
	computed:{
		classType(){
			const _level = this.model.level;
			const _folder = this.model.folder;
			let _classType = "";
			if(!_folder){
				_classType = "icon-table";
			}else{
				if(_level == 1){
					_classType = "icon-type"
				}else{
					_classType = "icon_folder";
				}
			}
			return _classType;
		},
		hasChildren(){
		  let children = !!this.model.children.length;
		},
		path(){
			return this.model.path;
		},
	},
	methods:{
		toggle(model){
			model.open = !model.open;
			const _path = model.path;
			this.$store.dispatch('setSelectItem', _path);
			if(model.open){
				this.fetchChildren(model);
			}
		},
    fetchChildren(model){
      let _url='/topic/tree';
      let level = model.level;
      let path = model.path;
			let data = {
				level,
				path
			};
      this.$http.post(_url,data,{
                        contentType: 'application/json',
                        dataType: 'json'
       }).then((res)=>{
      	res.body.message.map(item=>item['open']=false)
          this.treeChildren = res.body.message;
      })
    },
  }
}
</script>
<style scoped>
	.dbList .datasetMoveTo__tit{height: 38px;border-bottom: 1px solid #ccc;padding: 0 10px 0 20px;cursor: pointer;margin-left: -250px;padding-left: 250px;}
	.datasetMoveTo__txt{margin:10px;}
	.datasetMoveTo__radio{text-align: right;}
</style>
