<template>
	<div class="storage">
		<loading :isloading="loading"></loading>
		<div class="con" v-if="!loading">
			<div class="tableMulti">
				<div class="table-title">
					<div class="table-title__text">
						<span>{{$t('content.dataset.datasetDetail.ccxx')}}</span>
					</div>
				</div>
				<table class="table table-bordered tableList">
					<thead>
						 <tr>
					      <th  width="30%">{{$t('content.dataset.datasetDetail.mc')}}</th>
					      <th>{{$t('content.dataset.datasetDetail.sx')}}</th>
					    </tr>
					</thead>
					<tbody>
						<!-- <tr v-if="isPartitioned != $t('content.common.fou') && $parent.status == 0">
							<td>{{$t('content.dataset.datasetDetail.smzq')}}</td>
							<td class="life-cycle-edit">
									<div>
											{{showRecordList}}
									</div>
							</td>
						</tr> -->
						<tr>
							<td>{{$t('content.dataset.datasetDetail.sfwfqb')}}</td>
							<td>{{isPartitioned}}</td>
						</tr>
						<tr v-if="storage">
							<td>{{$t('content.dataset.datasetDetail.dx')}}</td>
							<td>{{storage}}</td>
						</tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.cjsj')}}</td>
              <td>{{formatTime(create_time*1000)}}</td>
            </tr>
            <!-- <tr v-if="lastUpdate">
              <td>{{$t('content.dataset.datasetDetail.zhgxsj')}}</td>
              <td>{{lastUpdate}}</td>
            </tr> -->
            <tr v-if="lastStructurUpdate">
              <td>{{$t('content.dataset.datasetDetail.sjjgzhgxsj')}}</td>
              <td>{{lastStructurUpdate}}</td>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.cclj')}}</td>
              <td>{{location}}</td>
            </tr>
						<template  v-if="tableDetailType == 1">
              <template v-if="showMore">
                <tr v-for="(value, key) in tableDetail"
  							v-if="key !== 'partition_column'">
  								<td width="30%">
										<!-- <span style="border: 1px solid red;">
											{{key}}
										</span> -->
  									{{nameObj[key]}}
  								</td>
  								<td>
  									<div v-if="typeof value == 'object'">
  										<div class="tableMulti" v-for="(v, k) in value">
  											<table class="table table-bordered table-striped">
  												<tbody>
  													<tr v-for="(_val, _key) in v">
  														<td width="30%">
  														{{nameObj[_key]}}
  														</td>
  														<td>
  															{{_val}}
  														</td>
  													</tr>
  												</tbody>
  											</table>
  										</div>
  									</div>
  									<div v-else v-ellipsis:600="value"></div>
  								</td>
  							</tr>
              </template>
              <template v-else>
                <tr v-for="(value, key) in tableDetail" v-if="key != 'partition_column' && key != 'serialization_format' && key != 'tbl_type' && key != 'bolckSize' && key != 'input_format' && key != 'output_format' && key != 'is_compressed' && key != 'is_storedassubdirectories'">
  								<td width="30%">
  									{{nameObj[key]}}
  								</td>
  								<td>
  									<div v-if="typeof value == 'object'">
  										<div class="tableMulti" v-for="(v, k) in value">
  											<table class="table table-bordered table-striped">
  												<tbody>
  													<tr v-for="(_val, _key) in v">
  														<td width="30%">
																{{nameObj[_key]}}
  														</td>
  														<td>
  															{{_val}}
  														</td>
  													</tr>
  												</tbody>
  											</table>
  										</div>
  									</div>
  									<div v-else v-ellipsis:600="value"></div>
  								</td>
  							</tr>
              </template>

						</template>
					</tbody>
				</table>
        <div class="storage-show-more" @click="showMore = !showMore">
          <span v-if="!showMore">{{$t('content.common.ckgd')}}</span>
          <span v-else>{{$t('content.common.shouq')}}</span>
        </div>
			</div>
		</div>
	</div>
</template>
<script>
import {
	FormatTime,
	FormatSize,
	Message,
	testPrivilege,
	resetValidators,
	FormatLifeDay
	 } from 'mixins/common'
import _ from 'lodash'
import dialogItemTitle from 'common/dialogItemTitle'

export default{
	mixins: [ FormatTime,FormatSize,Message, testPrivilege, resetValidators,FormatLifeDay],
	name: "properties",
	components: {
		dialogItemTitle,
	},
	props:{
		tableId:{
			require:true
		},
		storageData: Object,
		lastUpdate: String,
		lastStructurUpdate: String,
		flag: String
	},
	data(){
		const vm = this
		return {
			oldLifeSet:{
				life_day: '',
				type: '',
			},
			deadline: 1,
			showPlaceholder: false,
			loading: false,
			tableDetail: {},
			tableDetailType: 1,
			warnVisible: false,
			lifeCycle: this.$t('content.common.yongj'),
			storage:"",
			life_day: '',
			type: 'D',
			tag_type: '',
			tag_column: '',
			create_time: '',

			isPartitioned: this.$t('content.common.fou'),
			change:false, //输入是否改变
			nameObj: {
				'location': this.$t('content.dataset.datasetDetail.cclj'),
				'DB_ID': this.$t('content.dataset.datasetDetail.sjkbh'),
				'TBL_ID': this.$t('content.dataset.datasetDetail.bgbh'),
				'create_time': this.$t('content.dataset.datasetDetail.cjsj'),
                'lastUpdate': this.$t('content.dataset.datasetDetail.zhgxsj'),
                'lastStructurUpdate': this.$t('content.dataset.datasetDetail.sjjgzhgxsj'),
				'etl_source': 'ETL源',
				'input_format': this.$t('content.dataset.datasetDetail.srgs'),
				'output_format': this.$t('content.dataset.datasetDetail.scgs'),
				'is_compressed': this.$t('content.dataset.datasetDetail.sfys'),
				'is_storedassubdirectories': this.$t('content.dataset.datasetDetail.sfcwzml'),

				'serialization_format': this.$t('content.dataset.datasetDetail.xlhgs'),
				'tbl_type': this.$t('content.dataset.datasetDetail.bglx'),

				'length': '长度',
				'replication': '副本数',
				'bolckSize': '块大小',
				'modificationTime': '修改时间',
				'permission': '权限',
				'owner': '负责人',
				'group': '权限组',

				'is_primary_key':'是否主键',
				'is_unique': '是否唯一',
				'type': '类型',
 				'index_columns': '索引列',
				'constraint_type': '约束类型',
				'name': '名称',
				'num_of_columns': '列数',
				'constraint_name': '约束名'
			},
            showMore: false,
            location: '',
		}
	},
	async created(){
		const self = this;
		if(this.tableId){
			await this.fetchStorageData()
			await this.fetchIsPartitioned()
		}
	},
	updated(){
		const {life_day, type} = this.$data;
		Object.assign(this.oldLifeSet, {life_day, type});
	},
	watch: {
		// '$route': function(val, oldVal){
		// 	this.loading = true;
		// 	// this.fetchStorageData()
		// 	this.fetchIsPartitioned()
		// 	this.basis = {
		// 		'0': '文件时间',
		// 		'1': '字段时间'
		// 	}
		// 	this.showMore = false
		// },
		// '$parent.storageMessage'(val){
		// 	this.storageData = val
		// 	this.fetchStorageData()
		// 	this.isPartitioned = val.isPartitioned == 'N' ? '否' : '是'
		// }
	},
	computed: {
		// 覆盖mixins中的showRecordList
		showRecordList(){
			let lifeDay = this.life_day
			let lifeType = this.type
			return this.switchLifeType({lifeDay,lifeType}, {lang: this.language})
		},
	},
	methods: {
		fetchIsPartitioned(){
			this.isPartitioned = this.storageData.isPartitioned === 'N' ? this.$t('content.common.fou') : this.$t('content.common.shi');

            if (this.storageData.isPartitioned !== 'N') {
                this.fetchLifeCycleData();
            }else{
			    this.loading = false;
            }
		},
		async fetchLifeCycleData(){
			const self = this;
			this.$http.get(`/lifecycle/${this.tableId}`)
			.then((res)=>{
				self.loading = false;
				if(res.status == 200){
					if(res.body.data.length == 0){
						for(let key in self.recordForm){
							self.recordForm[key] = '';
						}
						self.type = 'F'
						self.showPlaceholder = true;
					}else{
						self.showPlaceholder = false;
						Object.assign(self.$data, res.body.data[0])
						//转字符
						self.tag_type = self.tag_type + '';
						self.tag_column = self.tag_column + '';
					}
				}
			})
		},
		fetchStorageData(){
			if(this.storageData.datasetStorageInfo){
				const _size = this.storageData.datasetStorageInfo.size;
				this.storage = this.formatSize(_size);
            }
			this.tableDetail = this.storageData.properties;
			this.create_time = this.tableDetail.create_time;
			this.location = this.tableDetail.location;
			['last_modify_time', 'structurModifyTime', 'data_scope', 'modify_time', 'create_time', 'location'].map(v => {
				Reflect.deleteProperty(this.tableDetail, v)
			})
			if ("indexes" in this.tableDetail) {
				this.tableDetailType = 2;//数据库
			} else {
				this.tableDetailType = 1;//hive 或者 hdfs
				if ("DB_ID" in this.tableDetail) { //hive
					// 过滤 参数
					['DB_ID', 'TBL_ID', 'etl_source', 'SD_ID'].map((v) => {
						Reflect.deleteProperty(this.tableDetail, v)
					});
					this.tableDetail.is_compressed = this.tableDetail.is_compressed ? this.$t('content.common.shi') : this.$t('content.common.fou');
					this.tableDetail.is_storedassubdirectories = this.tableDetail.is_storedassubdirectories ? this.$t('content.common.shi') : this.$t('content.common.fou');
				} else {
					this.tableDetail.bolckSize = this.tableDetail.bolckSize ? this.formatSize(this.tableDetail.bolckSize) : '';
					Reflect.deleteProperty(this.tableDetail, 'length')
				}
			}
		},
	}
}

</script>
<style lang="less">
@import '../../../../vars.less';
.storage{
  .tableList{
    margin-bottom: 0;
  }
}
.storage	.titIndex span{
		float: left;
	}
.storage	.edit-container{
		float: right;
		margin-bottom: 10px;
	}
.storage	.el-input.is-disabled .el-input__inner{
		    background-color: transparent;
		    border-color: #fff;
		    cursor: text;
				color: #333;
				padding-left: 0;
	}
.storage	.el-form-item__error{
		left: 0;
		top: 33px;
	}
.storage	.el-form-item{
		margin-bottom:0px;
	}
.storage	.el-form-item__content{
		line-height: 26px;
	}
.storage	.el-input__inner,
.storage .el-select{
		height: 28px;
		width: 400px;
	}
.storageContent{
	width: 600px;
	word-break: break-all;
	font-size: 13px;
}
.propertiew-authority span{
	background: #dbdbdb;
	color: #fff;
}
.propertiew-authority .propperiesWritable{
	background: #267ceb;
}
.propertiew-authority .properiesReadable{
	background: #3cbf80;
}
.life-cycle-edit .el-form-item__error{
	width: 250px;
	left:273px;
	top:5px;
}
.life-cycle-edit .el-select-dropdown{
	min-width: 80px !important;
}
.life-cycle-edit .el-select,.life-cycle-edit .el-select .el-input__inner{
	width: 80px;
}
.life-cycle-edit .el-select-dropdown__item{
	width: 80px;
}
.life-cycle-edit .el-input__inner{
	width: 185px;
}
.life-cycle-edit{
	.el-row{
		margin-bottom: 0;
	}
}
.ail-txt {
  line-height: 30px;
}
.warn-box{
	width: 345px;
	line-height: 27px;
	padding-left: 20px;
	border: 1px solid #eee7d1;
	background: #f5f2e8;
	color: #cda068;
}
.warn-box i{
	display: inline-block;
	width: 6px;
	height: 15px;
	background: url("../../@{imagesUrl}/lifecycle_warn_icon.png");
	vertical-align: middle;
}
.fade-enter-active, .fade-leave-active {
  trafficion: opacity .5s
}
.fade-enter, .fade-leave-to, .fade-leave-active /* .fade-leave-active in <2.1.8 */ {
  opacity: 0
}
.ali-placeholder{
	color: #ccc;
}
.ail-normal{
	color: #000;
}
.lifecycleCon{position: relative;display: inline-block;}
.storage-dialog{
	width: 534px;
}
.storage-dialog .el-input__inner,.storage-dialog .el-select{
	width: 100%;
}
.storage-dialog .el-radio__label{
	display: inline-block;
}
.storage-dialog .el-form-item__content{
	line-height: 35px;
}
.storage-dialog .el-dialog__body{
	padding: 30px 40px;
  min-height: 100px;
}
.boxCommon.storage .el-form-item__error{
	width: 300px;
}
.storage-show-more{
  line-height: 30px;
  text-align: center;
  color: #014b70;
  cursor: pointer;
  width: 60px;
  margin: 0 auto;
}
.storage-show-more:hover{
  color: #2ebff1;
}
</style>
