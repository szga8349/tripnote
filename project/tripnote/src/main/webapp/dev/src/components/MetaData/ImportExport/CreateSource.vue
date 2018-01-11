<template>
  <el-dialog
        :title="dialogTitle"
        :visible.sync="dialogVisible"
        @close="closeDialog()"
        :close-on-click-modal="false"
        class="source-link-dialog">
        <div v-show="false">
          {{reRuleForm}}
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="configuration-form">
          <el-form-item :label="$t('content.import.sjymc')+':'" prop="businessName">
            <el-input
              :disabled="params"
              v-model="ruleForm.businessName">
            </el-input>
          </el-form-item>
          <el-form-item :label="$t('content.dataset.importExport.sjyms')+':'" prop="description" class="description-item">
            <el-input type="textarea" :rows="3" v-model="ruleForm.description"></el-input>
          </el-form-item>
          <el-form-item class="cotrol-width" :label="$t('content.dataset.importExport.sjylx')+':'" prop="dataSourceType">
            <el-select v-model="ruleForm.dataSourceType" :disabled="params">
              <el-option label="Hive" value="HIVE"></el-option>
              <el-option label="MySQL" value="MYSQL"></el-option>
            </el-select>
          </el-form-item>
          <!-- <el-form-item :label="$t('content.dataset.importExport.hivelx')+':'" prop="metastoreType">
            <el-select v-model="ruleForm.metastoreType" :disabled="params">
              <el-option label="mysql" value="mysql"></el-option>
            </el-select>
          </el-form-item> -->
          <el-row class="from-inline">
            <el-col :span="12">
              <el-form-item :label="$t('content.dataset.importExport.ipdz')+':'" prop="address">
                <el-input v-model="ruleForm.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('content.import.dkh')+':'" prop="port">
                <el-input v-model="ruleForm.port"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item
            v-if="ruleForm.dataSourceType == 'HIVE'"
            :label="$t('content.dataset.datasetDetail.km')+':'"
            prop="baseName">
            <el-input v-model="ruleForm.baseName"></el-input>
          </el-form-item>
          <el-row class="from-inline">
            <el-col :span="12">
              <el-form-item :label="$t('content.import.yhm')+':'" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('content.import.mm')+':'" prop="password">
                <el-input v-model="ruleForm.password" type="password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="$parent.dialogVisible=false">{{$t('content.common.qx')}}</el-button>
          <el-button @click="testValidate('submit')" type="primary">{{$t('content.common.qd')}}</el-button>
          <el-button @click="testValidate('link')" type="primary" class="test-link">{{$t('content.dataset.importExport.csltx')}}</el-button>
        </div>
      </el-dialog>
</template>
<script>
  import { Message } from 'mixins/common'
  export default{
    name: 'CreateSource',
    props: {
      dialogVisible: Boolean,
      dialogTitle: String,
      params: Object
    },
    mixins: [ Message ],
    data(){
      return {
        driverName: 'com.mysql.jdbc.Driver',
        id: '',
        ruleForm: {
          businessName: '',
          description: '',
          dataSourceType: '',
          metastoreType: 'mysql',
          address: '',
          port: '',
          baseName: '',
          username: '',
          password: '',
        },
        rules: {
          businessName: [
            {required: true, message: this.$t('content.dataset.importExport.qsrsjymc'), trigger: 'blur'},
            {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
          ],
          description: [
            {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
          ],
          dataSourceType: [
            {required: true, message: this.$t('content.dataset.importExport.qxzsjylx'), trigger: 'change'},
          ],
          // metastoreType: [
          //   {required: true, message: this.$t('content.dataset.importExport.qxzhivermetas'), trigger: 'change'},
          // ],
          address: [
            {required: true, message: this.$t('content.dataset.importExport.qsripdz'), trigger: 'blur'},
          ],
          port: [
            {required: true, message: this.$t('content.dataset.importExport.qsrdkh'), trigger: 'blur'},
          ],
          baseName:[
            {required: true, message: this.$t('content.dataset.importExport.qsrkm'), trigger: 'blur'},
          ],
          username: [
            {required: true, message: this.$t('content.dataset.importExport.qsryhm'), trigger: 'blur'},
          ],
          password: [
            {required: true, message: this.$t('content.dataset.importExport.qsrmm'), trigger: 'blur'},
          ]
        }
      }
    },
    watch: {
      dialogVisible(val){
        !val && this.clearFormValue()
        this.rules.businessName = [
            {required: true, message: this.$t('content.dataset.importExport.qsrsjymc'), trigger: 'blur'},
            {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
          ]
        if(val && this.params){
          Object.assign(this.ruleForm, this.params)
          this.id = this.params ? this.params.id : ''
          this.ruleForm.address = this.params.url.split('//')[1].split(':')[0]
          this.ruleForm.port = this.params.url.split('//')[1].split(':')[1].split('/')[0]
          this.ruleForm.baseName = this.params.url.split('//')[1].split(':')[1].split('/')[1]
          this.ruleForm.metastoreType = 'mysql'
        }else{
          Object.keys(this.ruleForm).forEach(key=>{
            this.ruleForm[key] = ''
          })
        }
      }
    },
    computed: {
      reRuleForm(){
        Object.keys(this.ruleForm).forEach(key => {
          this.ruleForm[key] = typeof this.ruleForm[key]=='string' ? this.ruleForm[key].trim() : this.ruleForm[key]
        })
        return this.ruleForm
      },
      url(){
        let reUrl = `jdbc:mysql://${(this.ruleForm.address).trim()}:${(this.ruleForm.port).trim()}/${(this.ruleForm.baseName).trim()}`
        return reUrl
      }
    },
    methods: {
      closeDialog(){
        this.$parent.dialogVisible = false
      },
      async submitForm(){
        this.trimString()
        const { businessName,
                description,
                dataSourceType,
                username,
                password } = this.ruleForm
        const {driverName} = this.$data
        const url = this.url
        const params = {businessName, description, dataSourceType, driverName, url, username, password, id: this.params ? this.params.id : ''}
        let reqUrl = this.params ? '/datasource/update' : '/datasource/create'
        const res = await this.$http.post(reqUrl, params)
        const {statusCode} = res.body
        if(statusCode == 200){
          this.dialogVisible = false
          this.$store.dispatch('fetchDataSourceLink', true)
        }else{
          const code = res.body.message
          switch (code){
            case '10001':
              this.message(this.$t('content.dataset.importExport.sjymccf'), 'error')
              break;
            case '20006':
              this.message(this.$t('content.dataset.importExport.sjyljsb'), 'error')
              break;
          }
        }
      },
      trimString(){
        const vm = this
        Object.keys(this.ruleForm).forEach((key)=>{
          vm.ruleForm[key] = vm.ruleForm[key]+''
          vm.ruleForm[key].trim()
        })
      },
      async testLink(){
        const { username, password } = this.ruleForm
        const {driverName} = this.$data
        const url = this.url
        const params = {driverName, url, username, password}
        const res = await this.$http.post('/datasource/connect', params)
        const {statusCode} = res.body
        if(statusCode == 200){
          const { message } = res.body
          const messageContent = message ? this.$t('content.dataset.importExport.csljcg') : this.$t('content.dataset.importExport.csljsb')
          const state = message ? 'success' : 'error'
          this.message(messageContent, state)
        }
      },
      testValidate(type){
        if(type == 'submit'){
          this.rules.businessName = [
            {required: true, message: this.$t('content.dataset.importExport.qsrsjymc'), trigger: 'blur'},
            {max: 50, message: this.$t('content.validator.cdbdcg', [50]), trigger: 'blur'}
          ]
        }else{
          this.rules.businessName = []
        }
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            if(type == 'submit'){
              this.submitForm()
            }else{
              this.testLink()
            }
          } else {
            return false;
          }
        });
      },
      clearFormValue(){
        this.$refs['ruleForm'].resetFields()
       },
    }
  }
</script>
<style>
  .cotrol-width .el-form-item__content{
    width: 160px;
  }
</style>

