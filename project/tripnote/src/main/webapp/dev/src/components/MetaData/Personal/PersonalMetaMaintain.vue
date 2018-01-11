<template>
    <div>
        <table class="table table-bordered tableList">
            <table-header :tableHeader = "tableHeader"></table-header>
            <tbody>
                <tr v-for="data in records">
                    <td>
                        <div class="dataset-complete">
                            <a href="javascript:;"
                               v-ellipsis="data.datasetName"
                               @click="link(data)"
                               class="maintain-datasetName"
                               :class="{disabled: data.disabled}">
                            </a>
                            <!-- <span v-ellipsis="data.datasetName"></span> -->
                            <i>{{data.rate}}%</i>
                        </div>
                    </td>
                    <td>{{data.datasetType}}</td>
                    <td><div v-ellipsis="data.projectName"></div></td>
                    <td><div v-ellipsis="formatTime(data.createdTime)"></div></td>
                    <!-- <td>{{data.showLifeDay}}</td> -->
                    <td>{{formatSize(data.size)}}</td>
                    <td>{{data.owner}}</td>
                    <td>
                        <div class="owner-operator">
                            <el-tooltip
                                effect="dark"
                                content="维护元数据"
                                placement="top">
                                <span @click="showMetaInfo(data.id, data.datasetName)">
                                    <i class="iconfont icon-weihu icon-s"></i>
                                </span>
                            </el-tooltip>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <no-data :noDataVisible="records.length == 0"></no-data>
        <div class="tablePages">
            <el-pagination background
            layout="total, prev, pager, next"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            :total="totalRows"
            v-if="totalRows>10"
            >
            </el-pagination>
        </div>

        <el-dialog
            title="编辑元信息"
            :visible.sync="dialogVisible"
            custom-class="edit-meta-info"
            :close-on-click-modal="false">
            <h4>{{datasetName}} 的业务元信息</h4>
            <div style="min-height: 250px; background: #fff;">
                <table class="table table-bordered tableList">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>属性</th>
                        </tr>
                    </thead>
                    <tbody v-if="!propertieLoading">
                        <tr>
                            <td>别名</td>
                            <td>
                                <el-input
                                    v-model="properties.businessName"
                                    placeholder="请输入别名"
                                ></el-input>
                            </td>
                        </tr>
                        <tr>
                            <td>数据描述</td>
                            <td>
                                <el-input
                                    v-model="properties.description"
                                    placeholder="请输入数据描述"
                                ></el-input>
                            </td>
                        </tr>
                        <tr>
                            <td>业务意义</td>
                            <td>
                                <el-input
                                    v-model="properties.bizMeaning"
                                    placeholder="请输入业务意义"
                                ></el-input>
                            </td>
                        </tr>
                        <tr>
                            <td>是否敏感数据</td>
                            <td>
                                <el-select
                                    v-model="properties.sensitiveData"
                                    placeholder="请选择"
                                >
                                    <el-option value="Y" :label="$t('content.common.shi')"></el-option>
                                    <el-option value="N" :label="$t('content.common.fou')"></el-option>
                                </el-select>
                            </td>
                        </tr>
                        <tr>
                            <td>数据来源</td>
                            <td>
                                <el-input
                                    v-model="properties.source"
                                    placeholder="请输入数据来源"
                                ></el-input>
                            </td>
                        </tr>
                        <tr>
                            <td>数据更新频率</td>
                            <td>
                                <el-input
                                    v-model="properties.updateInterval"
                                    placeholder="请输入数据更新频率"
                                ></el-input>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <loading :isloading="propertieLoading"></loading>
            </div>
            <h4>{{datasetName}} 的字段信息</h4>
            <div>
                <table class="table table-bordered tableList">
                    <thead>
                        <tr>
                            <th>字段</th>
                            <th>数据类型</th>
                            <th>字段描述</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(data, index) in commentList">
                            <td>{{data.fieldName}}</td>
                            <td>{{data.dataType}}</td>
                            <td>
                                <el-input v-model='commentTable[index].comment'></el-input>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <loading :isloading="schemaLoading"></loading>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">{{$t('content.common.qx')}}</el-button>
                <el-button type="primary" @click="submit">{{$t('content.common.qd')}}</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import Bus from 'utils/bus'
    import PropertyItem from '../Dataset/DatasetDetail/PropertyItem'
    import {FormatTime, FormatSize, Message, FormatLifeDay} from 'mixins/common'
    export default{
        name: 'PersonalMetaMaintain',
        mixins: [FormatTime, FormatSize, Message, FormatLifeDay],
        data(){
            return {
                tableHeader: [
                    {name: '表名', hasSort: false},
                    {name: '表类型', hasSort: false},
                    {name: '所属项目', hasSort: false},
                    {name: '创建时间', hasSort: false},
                    // {name: '生命周期', hasSort: false},
                    {name: '存储大小', hasSort: false},
                    {name: '业务负责人', hasSort: false},
                    {name: '操作', hasSort: false},
                ],
                dialogVisible: false,
                noDataVisible: false,
                editedItem: null,
                records: [],
                currentPage: 1,
                totalRows: 1,

                tableId: '',
                properties:{
                    businessName: '',
                    description: '',
                    bizMeaning: '',
                    sensitiveData: '',
                    source: '',
                    updateInterval: '',

                    generateMethod: '',
                    sourceCategory: '',
                    sourceSystem: '',
                    extendedField: ''
                },
                propertiesInit: {
                    businessName: '',
                    description: '',
                    bizMeaning: '',
                    sensitiveData: '',
                    source: '',
                    updateInterval: '',

                    generateMethod: '',
                    sourceCategory: '',
                    sourceSystem: '',
                    extendedField: ''
                },
                propertieLoading: true,
                schemaLoading: true,
                commentList: [],
                commentTable: [],
                datasetName: ''
            }
        },
        components: {
            PropertyItem
        },
        created(){
            this.fetchData()
        },
        watch: {
            '$parent.key'(val){
                this.fetchData({key: val, currentPage: 1})
            }
        },
        methods: {
            link(queryParams){
                const path = queryParams.path
                const datasetId = queryParams.id
                this.$store.dispatch('fetchRouter', {path,datasetId})

                const name = queryParams.datasetName;
                this.$store.dispatch('saveTableName', name)
                this.$router.push({name: 'Physical'})
                this.$store.dispatch('setBusiness', false)

                this.$store.dispatch('saveNavData', true)
            },
            compareProperties(){
                var _editedField = []
                for(var key in this.properties){
                    if(this.properties[key] != this.propertiesInit[key]){
                        _editedField.push(key)
                    }
                }
                return _editedField.join(',')
            },
            async submit(){
                //属性信息保存
                let propertyPromise = null
                var _data = $.extend(true, {}, this.properties)
                var _editedField = this.compareProperties()
                if(_editedField != ''){
                    _data['field'] = _editedField
                    propertyPromise = new Promise((resolve, reject)=>{
                        this.$http.post(`/datasets/${this.tableId}/commoninfo`, _data)
                        .then(res=>{
                            if(res.body.statusCode == 200){
                                resolve(true)
                            }else{
                                resolve(false)
                            }
                        })
                        .catch(err=>console.log(err))
                    })
                }

                //字段信息保存
                let str = ''
                this.commentTable.forEach((val, index)=>{
                    str += `${val.commentId?val.commentId:0}:${this.commentList[index].id}:${val.comment};`
                })

                const fieldParams = { fieldData: str }
                const fieldPromise = new Promise((resolve, reject)=>{
                    this.$http.post(`/datasets/${this.tableId}/columns/comments`,
                        { fieldData: str },
                        {
                            emulateJSON: true
                        }
                    )
                    .then(res=>{
                            if(res.body.statusCode == 200){
                                resolve(true)
                            }else{
                                resolve(false)
                            }
                        })
                        .catch(err=>console.log(err))
                })

                Promise.all([propertyPromise, fieldPromise])
                       .then(res=>{
                            if(res.every(val=>val)){
                                this.message('编辑成功!', "success")
                                this.dialogVisible = false
                                this.fetchData()
                            }else{
                                this.message('编辑失败', 'error')
                            }
                       })

            },
            async fetchData({currentPage=1, key=''}={}){
                Bus.$emit('loadingVisible', true)
                const res = await this.$http.get(`/datasets/ownedDatasetWithoutCommonInfo?rows=10&page=${currentPage}&keyword=${key}`)
                if(res.body.statusCode == 200){
                    Object.assign(this.$data, res.body.message)
                }
                Bus.$emit('loadingVisible', false)
            },
            showMetaInfo(id, datasetName){
                const vm = this
                this.clearFormValue()
                this.propertieLoading = true
                this.schemaLoading = true
                this.datasetName = datasetName

                this.tableId = id
                this.dialogVisible = true
                this.$http.get(`/datasets/${id}/columns?page=1&rows=10`)
                          .then(res=>{
                              this.schemaLoading = false
                              if(res.body.statusCode == 200){
                                  this.commentList = res.body.message.records
                                  this.commentList.forEach((val, index)=>{
                                      vm.$set(vm.commentTable, index, {comment: val.comment ? val.comment : '', commentId: val.commentId})
                                  })
                              }
                          })
                this.$http.get(`/datasets/${id}/commoninfo`)
                          .then(res=>{
                            this.propertieLoading = false
                            if(res.body.statusCode == 200){
                                const data = res.body.message.datasetCommonInfo
                                Object.assign(this.properties, data)
                                Object.assign(this.propertiesInit, data)
                            }
                          })
            },
            clearFormValue(){
                for(const key in this.properties){
                    this.properties[key] = ''
                }
                this.commentList = []
            },
            handleCurrentChange(val){
                const params = {
                    currentPage: val,
                    key: this.key
                }
                this.fetchData(params);
            },
        }
    }
</script>
<style lang="less">

    .edit-meta-info{
        .el-dialog__body{
            padding: 10px 30px !important;
        }
        h4{
            font-size: 14px;
            margin-bottom: 10px;
            color: #999;
        }
        table{
            margin-bottom: 15px;
        }
    }
    .dataset-complete{
        display: flex;
        justify-content: space-between;
         .maintain-datasetName{
            display: inline-block;
        }
        i{
            line-height: 24px;
            font-style: normal;
            color: #feb155;
        }
    }
    .maintain-pagenation{
        display: flex;
        justify-content: space-between;
        margin-top: 15px;
        .tablePages{
            margin: 0;
        }
        .maintain-page-total{
            color: #999;
            line-height: 30px;
        }
    }
</style>

