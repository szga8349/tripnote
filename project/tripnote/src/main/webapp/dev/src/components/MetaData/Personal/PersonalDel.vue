    <template>
    <div class="personal-approval">
        <div class="tabs">
            <ul>
                <li v-for="item in reTabList"
                    @click="tabChange(item[0])"
                    :class="{active: activeTab == item[0]}">
                    {{item[1]}}
                </li>
            </ul>
    	</div>
        <div class="personal-approval-content">
            <div class="boxCommon">
                <div class="con">
                    <div class="tableMulti">
                        <div class="table-title">
                          <div class="table-title__text">
                            <span>{{tabList.get(activeTab)}}</span>
                          </div>
                        </div>
                        <loading :isloading="loadingVisible"></loading>
                        <template  v-if="!loadingVisible">
                            <table class="table table-bordered tableList">
                                <!-- <thead>
                                    <tr>
                                        <th>{{activeTab == 'delDataTable' ? $t('content.dataset.businessDetail.biaom') : $t('content.dataset.datasetDetail.km')}}</th>
                                        <th v-if="this.activeTab == 'delDataTable'">{{$t('content.dataset.datasetDetail.km')}}</th>
                                        <th>{{$t('content.personal.ysjly')}}</th>
                                        <th>{{$t('content.source.czzh')}}</th>
                                        <th>{{$t('content.source.czsj')}}</th>
                                        <th>{{$t('content.dataset.datasetDetail.cz')}}</th>
                                    </tr>
                                </thead> -->
                                <table-header
                                    :tableHeader = "tableHeader"
                                    :sort = "sort"
                                    :order = "order"
                                    @tableSort="tableSort">
                                </table-header>
                                <tbody>
                                    <tr v-for="item in records">
                                        <td><div v-ellipsis="item.sourceName"></div></td>
                                        <td><div v-ellipsis="item.refName"></div></td>
                                        <td v-if="activeTab == 'delDataTable'"><div v-ellipsis="item.parentName"></div></td>
                                        <!-- <td><div v-ellipsis="item.refName"></div></td> -->
                                        <td><div v-ellipsis="item.operator"></div></td>
                                        <td><div v-ellipsis="formatTime(item.operateTime*1000)"></div></td>
                                        <td>
                                            <a href="javascript:;"
                                                @click="recover ({
                                                    deleteKey: item.deleteKey,
                                                    sourceType: item.sourceType,
                                                    refId: item.refId,
                                                    refIdType: item.refIdType,
                                                    sourceName: item.sourceName
                                                    })">
                                                {{$t('content.personal.huif')}}
                                            </a>
                                            <a href="javascript:;"
                                                @click="physicallyDelete ({
                                                    deleteKey: item.deleteKey,
                                                    sourceType: item.sourceType,
                                                    refId: item.refId,
                                                    refIdType: item.refIdType,
                                                    sourceName: item.sourceName
                                                })">
                                                {{$t('content.common.sc')}}
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="tablePages" v-if="totalRows>pageSize">
                                <el-pagination background
                                    layout="total, prev, pager, next"
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    :current-page="currentPage"
                                    :page-size="pageSize"
                                    :total="totalRows">
                                </el-pagination>
                            </div>

                            <no-data v-if="records.length == 0"></no-data>
                        </template>
                    </div>
                </div>
            </div>
        </div>
        <!-- 确认框 -->
        <el-dialog
            :title="$t('content.common.tis')"
            :visible.sync="dialogVisible"
            width="385px"
            :close-on-click-modal="false"
            @click.native.stop>
            <div class="del-content">
                {{confirmMessage}}?
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button
                @click="dialogVisible = false">
                {{$t('content.common.qx')}}
                </el-button>
                <el-button
                type="primary" @click="operatePost">
                {{$t('content.common.qd')}}
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import Bus from 'utils/bus'
    import { FormatTime, Message } from 'mixins/common'
    export default{
        mixins: [ FormatTime, Message ],
        data(){
            return {
                tabList: new Map([
                    ['delDataTable', this.$t('content.personal.yscb')],
                    // ['delDataBase', this.$t('content.personal.ysck')],
                ]),
                sort: 'operateTime',
                order: "desc",
                activeTab: 'delDataTable',
                tableHeader: [],
                records: [],
                messageObj: {},
                loadingVisible: true,
                dialogVisible: false,
                confirmMessage: '',
                deleteKey: '',
                sourceType: '',
                refId: '',
                refIdType: '',
                sourceName: '',
                isSystemAdmin: 0,
                currentPage: 1,
                pageSize: 10,
                totalRows: 1,
            }
        },
        async created(){
            this.tableHeader = [
                {
                    name: this.activeTab == 'delDataTable' ? this.$t('content.dataset.businessDetail.biaom') : this.$t('content.dataset.datasetDetail.km'),
                    hasSort: true,
                    type: "sourceName",
                },
                {
                      name: this.$t('content.import.sjy'),
                      type: "refName",
                      hasSort: true,
                  },
                {
                    name: this.$t('content.dataset.datasetDetail.km'),
                    hasSort: true,
                    type: "parentName",
                },
                // {
                //     name: this.$t('content.personal.ysjly'),
                //     hasSort: true,
                //     type: "refName"
                // },
                {
                    name: this.$t('content.source.czzh'),
                    hasSort: true,
                    type: "operator"
                },
                {
                    name: this.$t('content.source.czsj'),
                    hasSort: true,
                    type: "operateTime"
                },
                {
                    name: this.$t('content.dataset.datasetDetail.cz'),
                    hasSort: false
                },
            ],
            this.activeTab = 'delDataTable'
            await this.fetchData()
            if(this.isSystemAdmin == 1){
                this.tabList = new Map([
                    ['delDataTable', this.$t('content.personal.yscb')],
                    ['delDataBase', this.$t('content.personal.ysck')]
                ])
            } else {
                this.tabList = new Map([
                    ['delDataTable', this.$t('content.personal.yscb')]
                ])
            }
        },
        computed: {
            reTabList(){
                return [...this.tabList]
            }
        },
        methods: {
            tableSort({sort,order}){
                this.sort = sort;
                this.order = order;
                
                this.fetchData();
            },
            tabChange(activeTab){
                this.activeTab = activeTab
                if(activeTab == 'delDataTable'){
                    this.tableHeader = [
                        {
                            name: this.activeTab == 'delDataTable' ? this.$t('content.dataset.businessDetail.biaom') : this.$t('content.dataset.datasetDetail.km'),
                            hasSort: true,
                            type: "sourceName",
                        },
                        {
                              name: this.$t('content.import.sjy'),
                              type: "refName",
                              hasSort: true,
                          },
                        {
                            name: this.$t('content.dataset.datasetDetail.km'),
                            hasSort: true,
                            type: "parentName",
                        },
                        // {
                        //     name: this.$t('content.personal.ysjly'),
                        //     hasSort: true,
                        //     type: "refName"
                        // },
                        {
                            name: this.$t('content.source.czzh'),
                            hasSort: true,
                            type: "operator"
                        },
                        {
                            name: this.$t('content.source.czsj'),
                            hasSort: true,
                            type: "operateTime"
                        },
                        {
                            name: this.$t('content.dataset.datasetDetail.cz'),
                            hasSort: false
                        },
                    ]
                }else{
                    this.tableHeader = [
                        {
                            name: this.activeTab == 'delDataTable' ? this.$t('content.dataset.businessDetail.biaom') : this.$t('content.dataset.datasetDetail.km'),
                            hasSort: true,
                            type: "sourceName",
                        },
                        {
                          name: this.$t('content.import.sjy'),
                          type: "refName",
                          hasSort: true,
                      },
                        // {
                        //     name: this.$t('content.personal.ysjly'),
                        //     hasSort: true,
                        //     type: "refName"
                        // },
                        {
                            name: this.$t('content.source.czzh'),
                            hasSort: true,
                            type: "operator"
                        },
                        {
                            name: this.$t('content.source.czsj'),
                            hasSort: true,
                            type: "operateTime"
                        },
                        {
                            name: this.$t('content.dataset.datasetDetail.cz'),
                            hasSort: false
                        },
                    ]
                }
                this.fetchData()
            },
            async fetchData({key='', currentPage = 1}={}){
                this.loadingVisible = true
                const type = this.activeTab == 'delDataTable' ? 'Table' : 'Database'
                const url = `/datasets/deletedDataset?sourceType=${type}&rows=10&page=${currentPage}&sort=${this.sort}&order=${this.order}`;
                const res = await this.$http.get(url)
                this.loadingVisible = false
                this.records = res.body.statusCode == 200 ? res.body.message.records : []


                Object.assign(this.$data, res.body.message);


                this.messageObj = res.body.message
                if(res.body.statusCode == 200){
                    this.isSystemAdmin = this.messageObj.extraParam.isSystemAdmin
                }
            },
            recover(params){
                this.dialogVisible = true
                Object.assign(this.$data, params)
                this.confirmMessage = this.sourceType == 'Table' ? this.$t('content.personal.qrhfb', [this.sourceName]) : this.$t('content.personal.qrhfk', [this.sourceName])
            },
            physicallyDelete(params){
                // this.operatePhysicallyDelete(params)

                this.dialogVisible = true
                Object.assign(this.$data, params)
                this.confirmMessage = this.sourceType == 'Table' ? this.$t('content.personal.qrcdscb', [this.sourceName]) : this.$t('content.personal.qrcdsck', [this.sourceName])
            },

            operatePost(){
                if(this.operatorType == 'recover'){
                    this.recoverPost()
                }else{
                    this.operatePhysicallyDelete()
                }
            },

            async recoverPost(){
                const vm = this
                this.dialogVisible = false
                const { deleteKey, sourceType, refId, refIdType } = this.$data
                const params = { deleteKey, sourceType, refId, refIdType }
                const res = await this.$http.post('/datasets/recoverDeletedDataset', params)
                const { message, statusCode } = res.body
                if(statusCode == 200){
                    switch(message){
                        case '20005':{
                            vm.message(`${vm.$t('content.personal.sjkhcy')}!`, 'error')
                            break;
                        }
                        case '':{
                            vm.message(`${vm.$t('content.personal.hfcg')}!`, "success")
                            break;
                        }
                    }
                    await this.fetchData()
                    Bus.$emit('fetchCount')
                }else{
                    this.message(`${vm.$t('content.personal.hfsb')}!`, "error")
                }
            },
            async operatePhysicallyDelete(){
                const vm = this
                this.dialogVisible = false

                const { deleteKey, sourceType, refId, refIdType, sourceName } = this.$data
                const params = { deleteKey, sourceType, refId, refIdType, sourceName }

                var res;
                if(this.activeTab == 'delDataTable'){
                    res = await this.$http.post('/datasets/'+params.deleteKey+'/deleteDatasetFromDb', {})
                }else{
                    res = await this.$http.post('/datasets/deleteDbFromDB/'+params.refId+'/'+params.sourceName, {
                        ref_id: params.refId,
                        dbName: params.sourceName
                    })
                }
                const { message, statusCode } = res.body

                if(statusCode == 200){
                    vm.message(`${vm.$t('content.personal.cdsccg')}!`, "success")
                    await this.fetchData()
                    Bus.$emit('fetchCount')
                }else{
                    vm.message(`${vm.$t('content.personal.cdscsb')}!`, "error")
                }
            },

            handleCurrentChange(val) {
                const params = {
                    currentPage: val,
                    key: this.key
                }
                this.fetchData(params);
            },
        },
    }
</script>
