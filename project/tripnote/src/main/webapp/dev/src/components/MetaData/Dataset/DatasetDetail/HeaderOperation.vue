 <template>
    <section class="headerOperation">
        <h6 class="grid-content titShow__iconCon"
			v-if="dataStatus==0">
			<!-- 申请 -->
			<template v-if="hasApplyAuthority">
				<el-popover
					ref="popover1"
					placement="top"
					width="20"
					trigger="hover"
					:content="$t('content.dataset.datasetDetail.sqqx')">
				</el-popover>
				<span v-popover:popover1>
					<i @click="permission"

					class="titShow__icon titShow__authority"></i>
				</span>
			</template>
			<!-- 收藏 -->
			<el-popover
				ref="popover2"
				placement="top"
				width="20"
				trigger="hover"
				:content="collectParams.text">
			</el-popover>
			<span v-popover:popover2>
				<i @click="collectToggle"
					class="titShow__icon titShow__collect"
					:class="{active:collectActive}"></i>
			</span>
			<!-- 隐藏 -->
			<template v-if="propEditPrivilege">
				<el-popover
					ref="popover3"
					placement="top"
					width="20"
					trigger="hover"
					:content="isPrivate==0?$t('content.common.yc'):$t('content.common.qxyc')">
				</el-popover>
				<span v-popover:popover3>
					<i class="titShow__icon titShow__private"
					:class="{privateActive:isPrivate==0 ? true : false}"

					@click="privateToggle(isPrivate)"></i>
				</span>
			</template>
			<!-- 分享 -->
			<template v-if="propEditPrivilege && isPrivate==0">
				<el-popover
					ref="popover4"
					placement="top"
					width="20"
					trigger="hover"
					content="分享">
				</el-popover>
				<span v-popover:popover4>
					<i @click="shareTable()"
						class="iconfont icon-youjian"></i>
				</span>
			</template>
        </h6>
        <el-dialog
        	:title="$t('content.dataset.datasetDetail.sqqx')"
            width="713px"
        	:visible.sync="dialogFormVisible"
        	:close-on-click-modal="false"
         	@click.native.stop>
        <div class="permission-header-tip">
            {{$t('content.dataset.datasetDetail.zawbsqdxqx', [permissionParams.tableName])}}
        </div>
            <el-form
                ref="permissionsForm"
                :model="permissionsForm"
                label-width="170px"
                :rules="permissionsRules">
                <div class="permission-form-label">
                    {{$t('content.dataset.datasetDetail.xasqqxdxm')}}
                </div>
                <el-row type="flex" justify="space-between" class="permission-container">
                    <el-col :span="11">
                        <label class="permission-label">{{$t('content.common.qxz')}}</label>
                        <permission-list
                            :permissionData="reResMessage"
                            direction="Left"
                        ></permission-list>
                    </el-col>
                    <el-col :span="2">
                        <div class="grid-content select__imgCon">
                            <img :src="imgSrc" alt="选择到">
                        </div>
                    </el-col>
                    <el-col :span="11">
                        <label class="permission-label">
                            {{$t('content.dataset.datasetDetail.yxz')}}
                            <span class="selectNum">
                                ({{selectItems?selectItems.length:0}})
                            </span>
                        </label>
                        <permission-list
                            :permissionData="selectItems"
                            direction="Right"
                            :hasDelete="true"
                        ></permission-list>
                    </el-col>
                </el-row>
                <div v-if="itemValidator" class="item-validator">
                    {{$t('content.dataset.datasetDetail.qzsxzygxm')}}
                </div>
                <el-row class="permission-container" style="margin-top: 10px;">
                    <el-col :span="24">
                    <label class="permission-label">
                        {{$t('content.dataset.datasetDetail.sqly')}}:
                    </label>
                    <el-input
                        type="textarea"
                        :rows="4"
                        :placeholder="$t('content.dataset.datasetDetail.qsrsqly')"
                        v-model="permissionsForm.reason">
                    </el-input>
                    </el-col>
                </el-row>
            <div class="item-validator" v-if="reasonValidator">
                {{$t('content.dataset.datasetDetail.qsrsqly')}}
            </div>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    {{$t('content.common.qx')}}
                </el-button>
            <el-button type="primary"
				@click="permissionsCommit('permissionsForm')">
                {{$t('content.common.qd')}}
            </el-button>
            </div>
        </el-dialog>
    	<!-- 申请详情 -->
        <el-dialog
			:title="$t('content.common.tis')"
			:visible.sync="resultVisible">
            <el-row type="flex" justify="center" class="resultIcon">
                <el-col :span="6">
                    <div class="grid-content">
                        <img :src="applyReturn.passSrc" alt="">
                        <h5>{{applyReturn.title}}</h5>
                    </div>
                </el-col>
            </el-row>
            <el-row class="resultContent">
                <el-col :span="20" :offset="2">
                    <ul class="grid-content">
                        <li @click="jumpToRecord($event)" v-for="item in applyReturn.resultArr" v-html="item.content"></li>
                    </ul>
                </el-col>
            </el-row>
          <span slot="footer" class="dialog-footer">
          	 <el-button type="primary" @click="resultVisible = false">{{$t('content.common.qd')}}</el-button>
          </span>
        </el-dialog>

        <!-- 重复提交提示框 -->
        <el-dialog
          :title="$t('content.common.tis')"
          :visible.sync="warnVisible"
          width="385px"
          :close-on-click-modal="false"
          @click.native.stop>
          <div class="tipContent" v-for="(data, index) in messageContent">
            <p :class="data.indexOf('failed') > -1 ? 'failed' : 'success' ">
                {{tipText(data)}}
            </p>
          </div>

          <div slot="footer" class="dialog-footer">
            <el-button type="primary"
              @click="warnVisible = false">
              {{$t('content.common.qd')}}
            </el-button>
          </div>
        </el-dialog>

        <!-- 邮件分享 -->
        <el-dialog
          title="分享"
          :visible.sync="shareDialogVisble"
          width="385px"
          :close-on-click-modal="false"
          @click.native.stop>
          <div class="tipContent">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入Email地址，多个请用,隔开。"
              v-model="emails">
            </el-input>
          </div>

          <div slot="footer" class="dialog-footer">
            <el-button
              @click="shareDialogVisble = false">
              {{$t('content.common.qx')}}
            </el-button>
            <el-button type="primary"
              @click="shareSubmit()">
              {{$t('content.common.qd')}}
            </el-button>
          </div>
        </el-dialog>
    </section>
</template>
<script>
import { Message,resetValidators } from 'mixins/common'
import permissionList from './PermissionList'
import Vue from 'vue'
import bus from 'utils/bus'
import { strMapToObj } from 'utils'
import {mapState} from 'vuex'

	export default{
		name:"permissionsDialog",
		props:{
			queryParams:Object
		},
		components: {
			permissionList
		},
		mixins: [ Message, resetValidators],
		data(){
			return{
                hasApplyAuthority: false,
				imgSrc: require('assets/images/icon_select.png'),
				collectActive:false,
				dialogFormVisible:false,
				permissionsForm:{
					type:['r'],
					project_arr:[],//已选择的数组
					reason:null,
				},
				permissionsRules:{
					type:[{ type: 'array', required: true,}],
					project_arr:[
						{ type: 'array', required: true, message: this.$t('content.dataset.datasetDetail.qzsxzygxm')}],
					 reason: [
			            { required: true, message: this.$t('content.dataset.datasetDetail.qsrsqly'), trigger: 'blur' },
			            { min: 1, max: 200, message: this.$t('content.dataset.datasetDetail.sqlybdcg'), trigger: 'blur' }
			          ],
				},
				resMessage:[],//返回的数据
				selectedMap:new Map(),//已选择的值
				filterSelectedItems:[],
				resultVisible:false,
				applyReturnMsg:null,//申请成功后的参数
				itemValidator: false,
				reasonValidator: false,
                messageContent: '',
                warnVisible: false,
                shareDialogVisble: false,
                emails: ''
			}
		},
        computed:{
            selectItems:{
                get(){
                  return this.$store.state.selectItems
                },
            },
			permissionParams(){
				const {id,path} = this.queryParams;
				let _path = path.split("/");
				return{
					datasetId:Number(id),
					dbAndTable:_path.slice(-2).join('.'),
					tableName:_path.slice(-1).toString()
				}
			},
			collectParams(){
				const map = new Map([
					[false, {type:"post",text:this.$t('content.common.shouc')}],
					[true, {type:"delete",text:this.$t('content.common.qxsc')}]
				]);
				return map.get(this.collectActive);
			},
			filterSelectedLen(){
				return this.filterSelectedItems.length;
			},
			formParams(){
				const {reason} = this.permissionsForm;
				const {datasetId} = this.permissionParams;
				let project_arr = []
				this.selectItems.forEach(val=>{
					let single = new Map()
					single.set('id', val.id)
					single.set('name', val.name)
					single.set('type', val.reType)
					let obj = strMapToObj(single)
					project_arr.push(obj)
				})
				return {
					dataset_id:datasetId,
					project_arr,
					reason
				}
			},//请求参数
				applyReturn(){
					const vm = this
					const {applyReturnMsg , permissionParams : { dbAndTable }} = this;
					const title = this.$t('content.dataset.datasetDetail.xmdbqxsqtj', [this.permissionParams.dbAndTable]);
					if(!applyReturnMsg) return {title:"",classifyArr:[]};
					this.utils = {
						title,
						applyReturnLen:applyReturnMsg.length,
						grouping(arr,key,fetchType="entries"){
							const grouping = arr.reduce((pre,cur)=>{
								const val = cur[key];
								let arr = [];
								arr.push(cur);
								if(pre.has(val)){
									arr = pre.get(val).concat(arr);
								}
								pre.set(val,arr)
								return pre;
							},new Map());
							return [...grouping[fetchType]()];
						},
						concatArr(arr,key){
							return arr.reduce((pre,cur)=>pre.concat(cur[key]),[]);
						},
						concatName(arr,code){
							const len = arr.length;
							const concatArr = this.concatArr(arr,'project');
							let content = "";
							if(code === 0){
								len > 2 ? content = concatArr.slice(0,2) + "等" : content = concatArr.join("、")
							}else{
								content = `<b style="color:#eb8a83">${concatArr.join("、")}</b>`;
							}
							return content;
						},
						fetchText(code,reason = ""){
								let text = this.title
							switch(code)
								{
								case 0:
								    return text += `${vm.$t('content.common.chengg')}: ${vm.$t('content.dataset.datasetDetail.qd')}"<a style="color:#85878d;text-decoration: underline;">${vm.$t('content.dataset.datasetDetail.grsjsqjl')}</a>"${vm.$t('content.dataset.datasetDetail.ckshzt')}`;
								break;
								case 1:
									return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.xtyc'))
								break;
								case 2:
								    return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.gqxzzsp'))
								break;
								case 3:
								    return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.wxcfsq', [reason]))
								break;
								case 4:
								    return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.qlxxmgly'))
								break;
								case 5:
					               return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.yygzyqx'))
								break;
								default:
								return text += (vm.$t('content.common.shib')+':'+vm.$t('content.dataset.datasetDetail.xtyc'))
								}
						},
						concatObj(val,key){
							let obj = {};
							let concatName = this.concatName(val,key);
							let fetchText = this.fetchText(key,val[0].reason);
							obj.key = key;
							obj.size = val.length;
							obj.content = `${concatName}${fetchText}`;
							return obj
						},
						fetchTitle(arr){
							const totalSize = this.applyReturnLen;
							const success = arr.find((val)=> !val.key);//找到成功的key = 0
							let title = "";
							let passSrc = require('assets/images/icon_noPass.png');
							let size = 0;
								!!success ? size = success.size : size = 0;
								if(totalSize === size){
									size === 1 ? title = vm.$t('content.dataset.datasetDetail.tjcg') : title = vm.$t('content.dataset.datasetDetail.qbtjcg');
									passSrc = require('assets/images/icon_pass.png');
								}else if(size === 0 ){
									totalSize === 1 ? title = vm.$t('content.dataset.datasetDetail.tjsb') : title = vm.$t('content.dataset.datasetDetail.qbtjsb');
								}else{
									title = vm.$t('content.dataset.datasetDetail.bftjsb');
								}
							return {
								title,
								passSrc
							}
						},
						main(){
							const grouping = this.grouping.call(this,applyReturnMsg,"result");
							const map = new Map(grouping);
							let resultArr=[];
							if(map.has(3)){//返回的code为3时候，特殊处理
								const subGrouping = map.get(3);
								const subGroupingResult = this.grouping.call(this,subGrouping,"reason","values")
								map.set(3,subGroupingResult)
							}
							for (let [key,val] of map){
								if(key===3){
									val.forEach((item)=>resultArr.push(this.concatObj(item,key)))
								}else{
									resultArr.push(this.concatObj(val,key))
								}
							}
							const fetchTitle = this.fetchTitle(resultArr);
							return Object.assign(fetchTitle,{resultArr})
						}
					}
					return this.utils.main();
				},//返回显示
      reResMessage: {
        get: function(){
          this.resMessage.forEach(val=>{
            let singleObj = {}
            if(val.type){
              this.$set(val, 'hasDisabled', true)
              this.$set(val, 'initW', val.type.includes('w')?true:false)
              this.$set(val, 'initR', val.type.includes('r')?true:false)
            }else{
              this.$set(val, 'hasDisabled', false)
              this.$set(val, 'initW', false)
              this.$set(val, 'initR', false)
            }
            this.$set(val, 'reType', null)
          })
          return this.resMessage
        }
      },
      ...mapState({
        dataStatus: 'dataStatus',
        isPrivate: 'isPrivate',
		propEditPrivilege: 'propEditPrivilege'
      }),
		},
		watch: {
			language(val){
				Vue.config.lang = val;
				this.title = this.$t('content.dataset.datasetDetail.xmdbqxsqtj', [this.dbAndTable]);
			},
		},
		methods:{
            tipText(txt){
                if( txt.indexOf('failed') >= 0){
                    return '"' + txt.replace(/ failed for applying repeatedly/g, '" '+this.$t('content.dataset.businessDetail.cftj'))
                }else{
                    return '"' + txt.replace(/ apply sucessfully/g, '" '+this.$t('content.dataset.datasetDetail.tjcg'))
                }
            },
            privateToggle(isPrivate){
                let url = isPrivate == 0 ? `/private/privateDataset/${this.queryParams.id}` : `/private/publicDataset/${this.queryParams.id}`
                this.$http.post(url).then(res=>{
                    if(res.body.statusCode == 200){
                        let privateState = isPrivate == 0 ? 1 : 0
						let messageContent = isPrivate == 0 ? this.$t('content.common.yccg') : this.$t('content.common.qxyccg')
                        this.$store.dispatch('setPrivate', privateState)
                        this.message(messageContent , 'success')
                    }else{
                        this.message('操作失败', 'error')
                    }
                })
            },
			collectToggle: _.debounce(async function () {
				const vm = this;

				const url = `/datasets/${this.queryParams.id}/favorite`;
				const {type, text} = this.collectParams;
				const res = await vm.$http[type](url);
				const status = res.body.statusCode === 200;
				if (status) {
				vm.collectActive = !vm.collectActive
				Vue.config.lang == 'zh-CN' ? vm.message(`${text}${this.$t('content.common.chengg')}!`, "success") : vm.message(`${text}${this.$t('content.common.chengg')}!`, "success");
				} else {
							Vue.config.lang == 'zh-CN' ? vm.message(`${text}${this.$t('content.common.shib')}!`, "error") :
				vm.message(`${text}${this.$t('content.common.shib')}!`, "error");
				}
			}, 500),
			resetForm(){
				this.$store.dispatch('selectItems', '')
				this.resMessage = []
				this.permissionsForm.reason = ''
			},
			permission: _.debounce(function () {
				this.resetForm()
				this.dialogFormVisible = true;
				this.fetchData()
			}, 500),
			async fetchData(){
				this.resetValidators('permissionsForm');//重置表单
				this.selectedMap.clear();
				this.permissionsForm.type = ['r'];
				const url = "/project/getProjectInfo?datasetId=" + this.queryParams.id;
				const res = await this.$http.get(url);
				const {statusCode,message} = res.body;
				statusCode === 200 && Object.assign(this.$data,{resMessage:message})
			},
			selectItem(val){
				const {id,name} = val;
				const set = new Set(this.permissionsForm.project_arr);
				if(set.has(id)){
					this.delSelectedItem(val);
				}else{
					this.selectedMap.set(id,name);
					this.permissionsForm.project_arr = [...this.selectedMap.keys()];
				}
			},
			delSelectedItem(val){
				this.selectedMap.delete(val.id);
				this.permissionsForm.project_arr = [...this.selectedMap.keys()]
			},
			async permissionsCommit(formName){
			    var vm = this;
				if(this.selectItems.length==0){
					this.itemValidator = true
					return;
				}else{
					this.itemValidator = false
				}

				if(this.permissionsForm.reason.length==0 || this.permissionsForm.reason.length>200){
					this.reasonValidator = true
					return;
				}else{
					this.reasonValidator = false
				}
				const url = "/privilege/applyPrivilege";
				const params = vm.formParams;
				const res = await vm.$http.post(url,params);
				const {statusCode,message} = res.body;
				this.dialogFormVisible = false;
				if(statusCode === 200){
                    if(message.join('').indexOf('failed') >= 0){
                        vm.warnVisible = true
                        vm.messageContent = message

                        // vm.message(message.join(', ').replace(/failed for applying repeatedly/g, this.$t('content.dataset.businessDetail.cftj')))
                    }else{
                        vm.message(this.$t('content.dataset.datasetDetail.tjcg'), 'success')
                    }
					// Object.assign(this.$data,{resultVisible : true, applyReturnMsg:message })
				}else{
					vm.message(this.$t('content.dataset.datasetDetail.sqtjsb'),"error")
				}
						if(this.permissionsForm.reason.length==0 || this.permissionsForm.reason.length>200){
							this.reasonValidator = true
							return;
						}else{
							this.reasonValidator = false
						}
					// const url = "/privilege/applyPrivilege";
					// const params = vm.formParams;
					// const res = await vm.$http.post(url,params);
					// const {statusCode,message} = res.body;
					// this.dialogFormVisible = false;
					// if(statusCode === 200){
                    //     if(message.join('').indexOf('failed') >= 0){
                    //         vm.warnVisible = true
                    //         vm.messageContent = message
                    //     }else{
                    //         vm.message(this.$t('content.dataset.datasetDetail.tjcg'), 'success')
                    //     }
					// 	// Object.assign(this.$data,{resultVisible : true, applyReturnMsg:message })
					// }else{
					// 	vm.message(this.$t('content.dataset.datasetDetail.sqtjsb'),"error")
					// }
			},
			jumpToRecord(e){
				e = window.event || e;
				if(e.target.nodeName === 'A' ){
				 this.$router.push({ name:'Record'})
				}
				return false;
			},
            shareTable(){
                this.shareDialogVisble = true;
            },
            shareSubmit(){
				var vm = this
                this.$http.post(
					'/datasets/shareDataset',
					{
						datasetId: this.queryParams.id,
						targetUsers: this.emails,
					},
					{ emulateJSON: true }
                )
                .then((res)=>{
					const {statusCode, message} = res.body
					if(statusCode == 200){
						vm.message('分享成功！', 'success')
						this.shareDialogVisble = false;
					}else{
						vm.message('分享失败！', 'error')
					}
                })
            }
		},
    mounted(){
      let self = this;
      bus.$on('IncreaseCollection', state=>{
        self.collectActive = state;
      })

      bus.$on('hasApplyAuthority', state=>{
        self.hasApplyAuthority = state == 0 ? false : true;
      })
	}
}
</script>
<style lang="less">
	@import '../../../../vars.less';
    .tipContent{
        text-align: left;
        p{
            line-height: 24px;
        }
        .failed{
            color: red;
        }
        .success{
            color: green
        }
    }
	.headerOperation{
		.select__item{
			display: flex;
			justify-content:space-between;
	  		align-items:baseline;
	  		.el-checkbox__label{font-size: 0;}
		}
		.el-dialog{
			width: 713px;
		}
		.el-form-item{margin-bottom:13px;}
		.titShow__iconCon{color: #a5aaac;text-align: right;margin-right: 20px;}
        .titShow__iconCon span{cursor: pointer; margin-right: 10px;}
		.titShow__iconCon .iconfont{ vertical-align: middle; font-size: 18px; color:#0F81DB;}
		.titShow__icon{
			display: inline-block;
			height: 16px;
			width: 16px;
			margin-right: 1px;
			overflow: hidden;
			background: url("../../@{imagesUrl}/dataset_operate.png") no-repeat;
			vertical-align: text-bottom;
		}
		.titShow__collect{
		    background-position:  -48px -0px;
        }
		.titShow__collect.active{
		    background-position:  -64px -0px;
        }
		.titShow__authority{
		    background-position:  -32px -0px;
        }
        .titShow__private{
            background-position: -16px 0;
        }
        .privateActive{
            background-position: 0 0;
        }
		.select{height: 210px;border: 1px solid #bfcbd9; background: #fff; width: 220px; overflow-y: auto;}
		.select li{height: 36px;border-bottom: 1px solid #dfe2e3;cursor: pointer;padding: 0px 8px;position: relative;}
		.select li:hover{background: #d8eff5;}
		.select__imgCon{text-align: center;}
		.select__imgCon img{display: inline-block;margin-top: 100px}
		.resultIcon{margin-bottom:30px;text-align:center}
		.resultIcon img{margin:10px auto;}
		.resultIcon h5{font-size:18px}
		.resultContent ul li {list-style-type: disc;margin-bottom: 1em;color:#85878d}
	}
  .selectNum{
    color: #999;
  }
	.select__dest li:hover .select__del{display: inline-block;}
	.resultIcon{margin-bottom:30px;text-align:center}
	.resultIcon img{margin:10px auto;}
	.resultIcon h5{font-size:18px}
	.resultContent ul li {list-style-type: disc;margin-bottom: 1em;color:#85878d}
	.permission-header-tip{
		height: 36px;
		line-height: 36px;
		background: #f4f0e5;
		border: 1px solid #e7dfcf;
		color: #c69864;
		padding: 0 15px;
		font-size: 12px;
	}
	.headerOperation .permission-container{
		font-size: 12px;
		margin-bottom: 0;
	}
	.item-validator{
		color: red;
		font-size: 12px;
	}
	.permission-form-label{
		margin: 15px 0;
		color: #b3b8bf;
		font-size: 12px;
	}
	.permission-label{
		margin-bottom: 5px;
	}
</style>
