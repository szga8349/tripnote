<template>
    <div class="selectBox" @mouseover="optListVisible=true">
        <span>{{sel}}</span>
        <i class="el-icon-caret-bottom"></i>
        <ul class="list" v-if="optListVisible" :class="{'ulLeft': !align, 'ulRight': align=='right'}">
            <li v-for="(item,index) in opts" @click.stop="changeOpt(item)" :class="{active:item[nameAlias]==sel}">
                {{item[nameAlias]}}
            </li>
        </ul>
    </div>
</template>
<script>
	export default{
		name: "Select",
		data(){
			return {
                optListVisible: false
				// imgSrc: require('assets/images/loading.gif')
			}
		},
		props:{
			opts: {
				type: Array,
				default: []
			},
			sel: {
				type: String,
				default: ''
			},
            nameAlias: {
                type: String,
                default: 'name'
            },
            idAlias: {
                type: String,
                default: 'id'
            },
            align: {
                type: String,
                default: 'left'
            }
			// isTree: {
			// 	default: false
			// }
		},
        methods: {
            changeOpt(item){
                this.optListVisible = false
                this.$emit('changeOpt', item);
            }
        }
		// render(h){
		// 	if(this.isloading){
		// 		return <div class="loadingBox">
		// 					<i class="el-icon-loading"></i>
		// 			 </div>
		// 	}else{
		// 		return
		// 	}
		// }
	}
</script>
<style lang="less">
.selectBox{
    position: relative;
    z-index: 100;
    span{
        padding-left: 10px;
        line-height: 32px;
    }
    .list{
        display: none;
        position: absolute;
        top: 32px;
        background: #fff;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        border: 1px solid #e2e2e2;
        &.ulLeft{
            left: 0;
        }
        &.ulRight{
            right: 0;
        }
        li{
            min-width: 120px;
            padding: 5px 10px;
            line-height: 22px;
            cursor: pointer;
            
            &.active{
                background: #E3EBED;
                &:hover{
                    background: #E3EBED;
                }
            }
            &:hover{
                background: #F5F7FA;
            }
        }
    }
    &:hover{
        .list{
            display: block;
        }
    }
}
</style>
