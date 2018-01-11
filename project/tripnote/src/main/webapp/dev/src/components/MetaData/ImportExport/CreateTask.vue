<template>
<div style="min-height: 500px;">
  <loading v-if="loadingVisible" :isloading="loadingVisible"></loading>
  <div v-else class="create-task">
    <div class="create-task">
      <div v-show="false">
        {{reFuleForm}}
      </div>
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="170px"
        class="task-form">
        <!-- <el-select v-model="value2" placeholder="请选择">
          <el-option
            v-for="item in options2"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled">
          </el-option>
        </el-select> -->
        <el-form-item :label="$t('content.dataset.import.rwmc')+':'" prop="taskName">
          <el-input
            v-model="ruleForm.taskName"
            :disabled="$route.params.id"></el-input>
        </el-form-item>
        <el-form-item :label="$t('content.dataset.importExport.rwms')+':'" prop="taskDesc" class="description-item">
          <el-input type="textarea" :rows="3" v-model="ruleForm.taskDesc"></el-input>
        </el-form-item>
        <div
          class="el-form-item datasource-editor"
          v-if="$route.params.id">
            <label for="" class="el-form-item__label">
              {{$t('content.import.sjy')}}:
            </label>
            <div class="task-data-source">
              <div>
                <span v-ellipsis="ruleForm.businessName"></span>
              </div>
              <div v-if="ruleForm.serverUrl">
                {{ruleForm.properties['hive.metastore.jdbc.url']}}
              </div>
            </div>
          </div>
          <el-row
            type="flex"
            justify="space-between"
            style="margin-bottom: 0"
            v-else>
            <el-col :span="17">
              <el-form-item :label="$t('content.import.sjy')+':'" prop="refId">
                <el-select
                  @change="$parent.sourchChange = true"
                  v-model="ruleForm.refId">
                  <el-option
                    v-for="item in refIdList"
                    :label="item.businessName.length > 20 ? `${item.businessName.slice(0, 16)}...` : item.businessName"
                    :value="item.refId + ''"
                    :title="item.businessName"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-button
              style="float: right; height: 30px; margin-top: 3px"
              @click="createSource">{{$t('content.dataset.importExport.xjsjy')}}</el-button>
            </el-col>
          </el-row>

        <!-- <el-form-item label="调度类型" prop="cron"> -->
        <el-form-item label="调度:">
          <el-input
            type="textarea"
            v-model="showCron"
            @focus="$store.dispatch('closeDialog', true)"
            placeholder="请选择">
          </el-input>
        </el-form-item>
        <!-- <el-form-item>
            <div class="show-cron-item">{{showCron}}</div>
        </el-form-item> -->

        <el-form-item :label="$t('content.dataset.importExport.sfhqybsj')+':'" prop="getSample">
          <el-select v-model="ruleForm.getSample">
            <el-option :label="$t('content.common.shi')" value="true"></el-option>
            <el-option :label="$t('content.common.fou')" value="false"></el-option>
          </el-select>
        </el-form-item>
        <template v-if="ruleForm.getSample=='true'">
          <el-form-item :label="$t('content.dataset.importExport.ybsjhqlj')+':'" prop="serverUrl">
            <el-input v-model="ruleForm.serverUrl"></el-input>
          </el-form-item>
          <el-row class="from-inline">
            <el-col :span="12" class="hive-server-container">
              <el-form-item :label="$t('content.dataset.importExport.hiveserver')+':'" prop="serverUsername">
                <el-input v-model="ruleForm.serverUsername"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" class="hive-server-container">
              <el-form-item :label="$t('content.dataset.importExport.hiveservermm')+':'" prop="serverPassword">
                <el-input v-model="ruleForm.serverPassword" type="password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item :label="$t('content.dataset.importExport.sfqykerb')+':'" prop="useKerberos">
            <el-select v-model="ruleForm.useKerberos">
              <el-option :label="$t('content.common.shi')" value="true"></el-option>
              <el-option :label="$t('content.common.fou')" value="false"></el-option>
            </el-select>
          </el-form-item>
          <template v-if="ruleForm.useKerberos == 'true'">
            <el-form-item label="kerberos principal:" class="principal-input">
              <template v-for="(item, index) in principalIpList">
                <label :for="item.ip">{{item.ip}}:</label>
                <el-input
                  :name="item.ip"
                  v-model="item.principal"></el-input>
              </template>
            </el-form-item>
            <el-form-item label="kerberos keytab:" prop="keytab">
              <el-input v-model="ruleForm.keytab"></el-input>
            </el-form-item>
          </template>
        </template>
        <div class="task-create-footer">
          <!-- <el-button
            v-if="ruleForm.getSample == 'true'"
            @click="testValidate('link')"
            class="test-link">测试连通性</el-button> -->
          <el-button type="primary" @click="testValidate">{{$t('content.common.xyb')}}</el-button>
          <el-button @click="$router.push({name: 'ConfigurationList'})">{{$t('content.common.qx')}}</el-button>
        </div>
      </el-form>
      <create-source
        :dialogVisible="dialogVisible"
        :dialogTitle="dialogTitle">
      </create-source>
        <!-- :show-close="false" -->
      <el-dialog
        :visible.sync="cronDialogVisible"
        title="调度设置"
        class="cron-dialog">
        <Cron></Cron>
      </el-dialog>
    </div>
  </div>
</div>
</template>
<script>
  import CreateSource from './CreateSource'
  import { Message } from 'mixins/common'
  import Bus from 'utils/bus'
  import Cron from 'common/Cron'
  export default{
    name: 'ConfigurationForm',
    components: {
      CreateSource,
      Cron
    },
    mixins: [ Message ],
    data(){
      return {
      	loadingVisible: false,
        cronDialogVisible: false,
        dialogVisible: false,
        dialogTitle: this.$t('content.dataset.importExport.xjsjy'),
        refIdList: [],
        businessName: '',
        ipList: [],
        principalIpList: [],
        cornList: [
          {label: this.$t('content.dataset.importExport.ycx'), value: '0 0 0 1 1 ? 2017'},
          {label: this.$t('content.dataset.importExport.mxs'), value: '0 0 0/1 * * ?'},
          {label: this.$t('content.dataset.importExport.meit'), value: '0 0 0 1/1 * ?'},
          {label: this.$t('content.dataset.importExport.meiz'), value: '0 0 0 ? * MON'},
          {label: this.$t('content.dataset.importExport.meiy'), value: '0 0 0 1 * ?'},
        ],
        ruleForm: {
          taskName:'',
          taskDesc: '',
          refId: '',
          cron: '',
          getSample: 'false',
          serverUrl: '',
          useKerberos: 'false',
          principal: '',
          principalIpList: this.principalIpList,
          keytab: '',
          serverUsername: '',
          serverPassword: '',
          dataSourceType: '',
        },
        rules: {
            taskName: [
              {required: true, message: this.$t('content.dataset.importExport.qsrrwmc'), trigger: 'blur'},
              {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
            ],
            taskDesc: [
              {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
            ],
            refId: [
              {required: true, message: this.$t('content.import.qxzsjy'), trigger: 'change'},
            ],
            cron: [
              {required: true, message: this.$t('content.dataset.importExport.qxzrwlx'), trigger: 'change'},
            ],
            // getSample: [
            //   {required: true, message: '请选择是否获取样本数据', trigger: 'blur'},
            // ],
            serverUrl: [
              {required: true, message: this.$t('content.dataset.importExport.qtxybsjhqlj'), trigger: 'blur'},
            ],
            serverUsername: [
              {required: true, message: this.$t('content.dataset.importExport.qtxhive'), trigger: 'blur'},
            ],
            serverPassword: [
              {required: true, message: this.$t('content.dataset.importExport.qtxhivemm'), trigger: 'blur'},
            ],
            useKerberos: [
              {required: true, message: this.$t('content.dataset.importExport.qxzsfqyker'), trigger: 'blur'},
            ],
            principal: [
              {required: true, message: this.$t('content.dataset.importExport.qtxkerberos'), trigger: 'blur'},
            ],
            keytab: [
              {required: true, message: this.$t('content.dataset.importExport.qtxkerberos'), trigger: 'blur'},
            ],
        }
      }
    },
    async created(){
      const id = this.$route.params.id
      if(id){
        const res = await this.$http.get(`/systemImport/get/${id}`)
        const {statusCode} = res.body
        if(statusCode == 200){
          Object.assign(this.ruleForm, res.body.message)
          this.$store.dispatch('cronValue', this.ruleForm.cron)
          Bus.$emit('taskName',this.ruleForm.taskName)
        }
      }else{
        if(Object.keys(this.firstStepData).length!=0){
          this.$store.dispatch('cronValue', '* * * * * *')
          this.ruleForm = this.firstStepData
        }
      }
      this.fetchIPData()
      this.fetchSourceData()
    },
    watch: {
      closeDialog(val){
        this.cronDialogVisible = val
      },
      cronDialogVisible(val){
        !val && this.$store.dispatch('closeDialog', false)
      },
      principalIpList(val){
        this.ruleForm.principalIpList = val
      },
      dialogVisible(val){
        !val && this.fetchSourceData()
      }
    },
    computed: {
      cronValue(){
        return this.$store.state.cronValue
      },
      closeDialog(){
        return this.$store.state.closeDialog
      },
      showCron(){
        return this.$store.state.showCron
      },
      firstStepData(){
        return this.$store.state.createTask
      },
      reFuleForm(){
        Object.keys(this.ruleForm).forEach(key => {
          this.ruleForm[key] = typeof this.ruleForm[key]=='string' ? this.ruleForm[key].trim() : this.ruleForm[key]
        })
        return this.ruleForm
      }
    },
    methods: {
    	async fetchIPData(){
        const res = await this.$http.get('/systemImport/getBackEndHosts')
        const {statusCode, message} = res.body
        if(statusCode == 200){
          message.forEach((val, index)=>{
            const object = Object.create(null)
            this.$set(this.ipList, index, val)
            let principals = this.ruleForm.principal.split(';')
            principals.forEach(item => {object[item.split('=')[0]]=item.split('=')[1]})
            this.$set(this.principalIpList, index, {
              ip: val,
              principal: object[val]
            })
          })
        }
      },
      async fetchSourceData(){
        const res = await this.$http.get('/systemImport/getDatasource')
        const {statusCode} = res.body
        if(statusCode == 200){
          this.refIdList = res.body.message
        }
      },
      testValidate(type){
        const vm = this
        if(type=='link'){
          ['taskName', 'refId', 'cron'].forEach(key=>{
            this.rules[key] = []
          })
        }else{
          this.rules.taskName = [{required: true, message: '请输入任务名称', trigger: 'blur'},
                                {max: 50, message: '长度在50个字以内', trigger: 'blur'}]
          this.rules.taskDesc =  [{max: 50, message: '长度在50个字以内', trigger: 'blur'}]
          this.rules.refId = [{required: true, message: '请选择数据源', trigger: 'change'}]
          this.rules.cron = [{required: true, message: '请选择任务类型', trigger: 'change'}]
        }
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            if(this.ruleForm.getSample == 'true'){
              this.testLink()
            }else{
              this.nextStep()
            }
          } else {
            return false
          }
        })
      },
      async testLink(){
        let {serverUrl,serverUsername,serverPassword,useKerberos,principal,keytab,principalIpList} = this.ruleForm
        function stringifyPrincipal(arr){
          let principalShow = ''
          arr.forEach(item=>{
            principalShow += `${item.ip}=${item.principal};`
          })
          return principalShow
        }
        principal = principalIpList && stringifyPrincipal(principalIpList)
        this.ruleForm.principal = principal
        const params = {serverUrl,serverUsername,serverPassword,useKerberos:eval(useKerberos),principal,keytab}
        this.loadingVisible = true
        const res = await this.$http.post('/systemImport/connect', params)
        const {statusCode, message} = res.body
        if(statusCode == 200){
            if(message){
              this.loadingVisible = false
              // this.message(this.$t('content.dataset.importExport.csljcg'), 'success')
              this.nextStep()
            }else{
              this.message(this.$t('content.dataset.importExport.csljsb'), 'error')
            }
        }
      },
      async nextStep(){
        this.ruleForm.cron = this.cronValue
        this.$store.dispatch('createTask', this.ruleForm)
        this.firstStepData = this.ruleForm
        this.$parent.activeTab = this.$t('content.dataset.importExport.hqdkbhmdsz')
      },
      createSource(){
        this.dialogVisible = true
      }
    }
  }
</script>
<style lang="less">
  .create-task{
    width: 550px;
    margin: 20px 30px;
    .el-form-item{
      margin-bottom: 15px;
      .el-select,.el-input{
        width: 100%;
      }
      .el-row{
        margin-bottom: 0;
      }
      .el-select-dropdown__item{
        max-width: 100%;
      }
    }
    .task-create-footer{
      display: flex;
      justify-content: flex-end;
    }
  }
  .el-select-dropdown__item{
    max-width: 100%;
  }
  .el-dialog__wrapper .el-dialog__footer{
    .test-link{
      float: left;
    }
  }
  .datasource-editor label{
    width: 170px;
  }
  .el-form-item__content .show-cron-item{
    font-size: 12px;
    line-height: 25px;
  }
  .from-inline .el-form-item{
      margin-bottom: 0;
  }
  .from-inline.el-row{
    margin-bottom: 15px;
  }
  .hive-server-container{
    .el-form-item__error{
      width: 206px;
    }
  }
  .principal-input .el-input{
    width: 250px !important;
  }
  .task-data-source{
    margin-left: 170px;
    padding: 5px;
    border: 1px solid #e0e1e2;
    background: #f0f1f2;
    >div:last-child{
      color: #ccc;
      word-break: break-all;
    }
  }
</style>
