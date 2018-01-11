<template>
  <div class="boxCommon properties">
    <loading :isloading="loadingVisible"></loading>
    <div class="con" style="margin-top:0;" v-if="!loadingVisible">
      <!-- 基本信息 -->
      <div class="tableMulti">
        <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.datasetDetail.jbxx')}}</span>
          </div>
        </div>
        <table class="table table-bordered tableList">
            <thead>
            <tr>
              <th width="30%">
                {{$t('content.dataset.datasetDetail.mc')}}
              </th>
              <th class="pl40">{{$t('content.dataset.datasetDetail.sx')}}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>{{$t('content.dataset.businessDetail.biaom')}}</td>
              <td>{{tableName}}</td>
            </tr>
            <tr>
              <td>{{$t('content.dataset.businessDetail.biem')}}<!--别名--></td>
              <property-item
                :value="businessName"
                itemName="businessName"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.qsrgbdbm')"
                @emitDoneEdit="doneEdit"></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.sjms')}}<!--数据描述--></td>
              <property-item
                :value="description"
                itemName="description"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.nrjytdmsxx')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.ywyy')}}<!--业务意义--></td>
              <property-item
                :value="bizMeaning"
                itemName="bizMeaning"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjsrgbdywhy')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.sjfzr')}}<!--数据负责人--></td>
              <td>{{owner}}</td>
             <!--  <property-item
                :value="owner"
                itemName="owner"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjszgbdyw')"
                @emithandleCommand="changeOwner"
              ></property-item> -->
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.mgsj')}}<!--敏感数据--></td>
              <property-item
                :value="sensitiveData"
                itemName="sensitiveData"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjsrshf')"
                :isSelect="true"
                :selectList="sensitiveSelect"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.ztlj')}}<!--主题路径--></td>
              <td>
                <div class="view" v-ellipsis="topicList.join(';')" :title="topicList.join(';')"></div>
              </td>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.sjly')}}<!--数据来源--></td>
              <property-item
                :value="source"
                itemName="source"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjsrsjly')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.xyxt')}}<!--下游系统--></td>
              <property-item
                :value="sourceCategory"
                itemName="sourceCategory"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjsrxyxt')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.lybgnmk')}}<!--来源表/功能模块--></td>
              <property-item
                :value="sourceSystem"
                itemName="sourceSystem"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.sjsrlybgnmk')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr v-if="hasApplyAuthority && status == 0">
              <td>{{$t('content.dataset.datasetDetail.wdqx')}}</td>
              <td>
                <div class="propertiesContent">
                  <div class="propertiew-authority">
                    <span :class="{properiesReadable: isReadable}">{{$t('content.authority.r')}}</span>
                    <span :class="{propperiesWritable: isWritable}">{{$t('content.authority.w')}}</span>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.gxzq')}}</td>
              <property-item
                :value="updateInterval"
                itemName="updateInterval"
                :parentLabel="editedLabel"
                :placeholder="$t('content.dataset.datasetDetail.qxzgxzq')"
                @emitDoneEdit="doneEdit"
              ></property-item>
            </tr>
            <tr>
              <td>{{$t('content.dataset.datasetDetail.sjzt')}}</td>
              <td>
                <span style="color: #22b66f" v-if="status == 0">{{$t('content.dataset.datasetDetail.zhengc')}}</span>
                <span style="color: red;" v-if="status == 1">{{$t('content.dataset.datasetDetail.ysc')}}</span>
                <span style="color: #ffbb56;" v-if="status == 2">{{$t('content.dataset.datasetDetail.weiz')}}</span>
              </td>
            </tr>
            </tbody>
          </table>
      </div>
      <!-- 存储信息 -->
      <storage
        v-if="!loadingVisible && status != 2"
        :tableId = "tableId"
        :storageData = "storage"
        :lastUpdate="formatTime(lastUpdate * 1000)"
        :lastStructurUpdate="formatTime(lastStructurUpdate * 1000)"
        ></storage>
        <div class="tableMulti"
            v-if="Object.keys(reExtendedField)!= 0"
        >
        <div class="table-title">
          <div class="table-title__text">
            <span>{{$t('content.dataset.datasetDetail.lyxx')}}</span>
          </div>
        </div>
        <!-- <div class="add-extended-field" @click="addExtend">
            <a href="javascript:;">新增</a>
        </div> -->
        <table class="table table-bordered tableList">
          <thead>
          <tr>
            <th width="30%">
              {{$t('content.dataset.datasetDetail.mc')}}
            </th>
            <th>{{$t('content.dataset.datasetDetail.sx')}}</th>
          </tr>
          </thead>
          <tbody>
              <!-- :placeholder="$t('content.dataset.datasetDetail.qsrgbdbm')" -->

            <tr v-for="(val,key) in reExtendedField">
              <td>
                  <div>{{key}}</div>
              </td>
              <property-item
                :value="val"
                :itemName="`extendedField_${key}`"
                :parentLabel="editedLabel"
                :placeholder="请输入自定义字段属性"
                @emitDoneEdit="extendedDoneEdit"></property-item>
              <!-- <td><div>{{val}}</div></td> -->
            </tr>
          </tbody>
        </table>
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
    SelectRender
  } from 'mixins/common'
  import Bus from 'utils/bus'
  import Storage from './Storage'
  import PropertyItem from './PropertyItem'
  import {mapState} from 'vuex'

  export default{
    mixins: [FormatTime, FormatSize, Message, testPrivilege, SelectRender],
    name: "properties",
    props: {
      tableId: {
        require: true
      }
    },
    components: {
      Storage,//存储信息
      PropertyItem
    },
    data(){
      return {
        hasErrors: false,
        editedLabel: '',
        businessName: '',
        description: '',
        source: '',
        tableName: '',
        generateMethod: '',
        updateInterval: '',
        topicList: [], //主题路径属性
        owner: "",  //数据负责人属性
        // OwnerOption: [],
        isReadable: false,
        isWritable: false,
        lastUpdate: '--',
        lastStructurUpdate: '--',
        dataLimite: '',
        loadingVisible: true,
        dialogFormVisible: false,
        storage: {extendedField:''},
        bizMeaning: '', //业务意义
        sensitiveData: '', //敏感数据
        sourceCategory: '', //数据来源类型
        sourceSystem: '', //数据来源系统
        projectId: '',
        getSample: false,
        sensitiveSelect: [
          [this.$t('content.common.shi'), 'Y'],
          [this.$t('content.common.fou'), 'N'],
        ],
        options:[
					[this.$t('content.common.ant'), 'day'],
					[this.$t('content.common.anz'), 'week'],
					[this.$t('content.common.any'), 'month'],
					[this.$t('content.common.anj'), 'quarter'],
					[this.$t('content.common.ann'), 'year'],
				],
        extendFieldLang: {},
        status: 0,
        extendedField: {},
        hasApplyAuthority: true
      }
    },
    created(){
    //   await this.testPrivilege(`/datasets/${this.tableId}/commoninfo`)
    //   this.$store.dispatch('')
      this.getAllData()
      Bus.$on('setEditLabel', label=>{
        this.editedLabel = label
      })
      Bus.$on('cancelEdit', (value, label)=>{
        this.editedLabel = null
        this[label] = value
      })
    },
    watch: {
      '$route': function (val) {
        if(val.query.path){
          this.loadingVisible = true
          this.editedLabel = null
          this.isReadable = false
          this.isWritable = false
          // this.testPrivilege(`/datasets/${this.tableId}/commoninfo`)
          this.getAllData()
          Bus.$emit('scrollTop')
        }
      },
    },
    methods: {
        // addExtend(){
        //     let extendedField = JSON.parse(this.extendedField)
        //     const extendedMap = new Map([['', '']])
        //     Object.keys(extendedField).forEach(val=>{
        //         extendedMap.set(val, extendedField[val])
        //     })
        //     let newExtendedObj = Object.create(null)
        //     for(let [k,v] of extendedMap){
        //         newExtendedObj[k] = v
        //     }
        //     this.extendedField = JSON.stringify(newExtendedObj)
        // },
        doneEdit(params){
            const vm = this
            const label = params.editedLabel
            this.editedLabel = label
            vm[label] = params.value
            if (!vm[label]) {
                vm[label] = ''
            }
            vm[label] = vm[label].trim()
            if (vm.validator(label, this[label])) {
                vm.editedLabel = null
                this.hasErrors = false
                this[label] !== params.beforeEditCache && this.submit(params.editedLabel)
            } else {
                this.hasErrors = true
            }
        },
        extendedDoneEdit(params){
            const key = params.editedLabel.split('_')[1]
            this.editedLabel = params.editedLabel
            let extendedField = JSON.parse(this.extendedField)
            if(this.validator('extendedField', params.value)){
                Object.keys(extendedField).forEach(val=>{
                    if(val == key){
                        extendedField[key] = params.value.trim()
                    }
                })
                this.extendedField = JSON.stringify(extendedField)
                this.editedLabel = null
                this.hasErrors = false

                params.value !== params.beforeEditCache && this.submit('extendedField.' + params.editedLabel)
            } else {
                this.hasErrors = true
            }
        },
      submit(editedField){
        let params = {
          businessName: this.businessName,
          description: this.description,
          source: this.source,
          generateMethod: this.generateMethod,
          updateInterval: this.updateInterval,
          bizMeaning: this.bizMeaning,
          sensitiveData: this.sensitiveData,
          sourceCategory: this.sourceCategory,
          sourceSystem: this.sourceSystem,
          extendedField: this.extendedField,
          field: editedField
        }
        let cacheData = new Date().valueOf()
        this.$http.post(`/datasets/${this.tableId}/commoninfo`, params)
          .then(function (res) {
            let body = res.body;
            if (body.result && body.statusCode === 200) {
              //this.message("编辑成功!", "success");
            } else {
              this.message("编辑失败!", "error");
            }
          })
      },
      validator(type, value){
        this.errors = ''
        if (type === 'businessName') {
            if(value.length >= 20){
              this.message(this.$t('content.dataset.datasetDetail.zdsr20zf'), "error")
              return false
            }else if(/\//.test(value)){
              // this.message('不能输入"/"', "error")
              this.message(this.$t('content.dataset.datasetDetail.bnsrxx'), "error")
            }else{
              return true
            }
        }else if(type === 'updateInterval'){
          if (value.length <= 50) {
            return true;
          } else {
            this.message(this.$t('content.dataset.datasetDetail.zdsrgzf',[50]),"error")
            return false;
          }
        }else if(type === 'extendedField'){
            if(value.length <= 200){
                return true
            } else {
                this.message(this.$t('content.dataset.datasetDetail.zdsrgzf',[200]),"error")
                return false
            }
        } else {
          if (value.length <= 200) {
            return true;
          } else {
            this.message(this.$t('content.dataset.datasetDetail.zdsr200gzf'), "error");
            return false;
          }
        }
      },
      setIsPrivate(isPrivate){
          this.$store.dispatch('setPrivate', isPrivate)
      },
      getAllData(){
        if(this.tableId){
          this.$http.get(`/datasets/${this.tableId}/commoninfo`)
            .then((response) => {
              if (response.body.statusCode === 200) {
                this.loadingVisible = false;
                let data = response.body.message
                let datasetInfo = data.datasetInfo
                data =  data.datasetCommonInfo
                this.setIsPrivate(data.isPrivate)
                Object.assign(this.$data, data)
                this.$store.dispatch('setDataStatus', this.status)
                this.$store.dispatch('getSample', this.getSample)
                this.extendedField = data.extendedField
                Bus.$emit('hasApplyAuthority', data.isDefault);

                this.hasApplyAuthority = data.isDefault == 0 ? false : true

                /*
                * 权限申请
                * */
                if (data.privilege.resourcePrivilege === 'r'){
                  this.isReadable = true
                } else if (data.privilege.resourcePrivilege === 'rw'){
                  this.isReadable = true
                  this.isWritable = true
                }

                if(this.status != 2){
                  this.storage = datasetInfo

                  this.lastUpdate = datasetInfo.properties.last_modify_time
                  this.lastStructurUpdate = datasetInfo.properties.structurModifyTime

                  this.dataLimite = datasetInfo.properties.data_scope
                }
                Bus.$emit('IncreaseCollection', datasetInfo.favorite > 0);  //触发收藏功能，非父子间组件通信
              }
            });
          }
        // this.fetchOwnerOption();
      },
      changeOwner(command) {
        this.editedLabel = null
        this.$http.get(`/privilege/${this.tableId}/updateDatesetOwner?userName=${command}`, {})
          .then(function (res) {
            let body = res.body;
            if (body.result && body.statusCode === 200) {
              this.owner = command;
            } else {
              this.message("编辑失败!", "error");
            }
          })
      },
      // fetchOwnerOption (){
      //   this.$http.get(`/project/getProjectMember/${this.projectId}`)
      //     .then(response => {
      //       if (response.body.statusCode === 200) {
      //         let option = new Map();
      //         [].forEach.call(response.body.message, val=>{
      //           option.set(val.username, val.username)
      //         })
      //         this.OwnerOption = [...option]
      //       }
      //     })
      // }
    },
    computed: {
      isBusiness(){
        return this.$store.state.isBusiness
      },
      computedLimitDate(){
        let newDateArr = this.dataLimite
        if (!newDateArr)
          return;
        newDateArr = newDateArr.split(':');
        const startDate = newDateArr[0] * 1;
        const endDate = newDateArr[1] * 1;
        const newDateLimit = `${this.formatTime(startDate * 1000)}--${this.formatTime(endDate * 1000)}`;
        return newDateLimit;
      },
      reExtendedField(){
        let extentedField = (this.extendedField && JSON.stringify(this.extendedField) != '{}') ? JSON.parse(this.extendedField) : {}
        return extentedField
  		},
      ...mapState({
        dataStatus: 'dataStatus',
      }),
    },
  }

</script>
<style lang="less">
  .storage{
    margin-top: 10px;
  }
  .properties{
    padding-bottom: 20px;
  }
  .properties .titIndex span {
    float: left;
  }

  .properties .edit-container {
    float: right;
    margin-bottom: 10px;
  }

  .properties .el-input.is-disabled .el-input__inner {
    background-color: transparent;
    border-color: #fff;
    cursor: text;
    color: #333;
    padding-left: 0;
  }

  .properties .el-form-item__error {
    left: 420px;
    top: 5px;
  }

  .properties .el-form-item {
    margin-bottom: 0px;
  }

  .properties .el-form-item__content {
    line-height: 26px;
  }

  .properties .el-input__inner,
  .properties .el-select {
    height: 32px;
    width: 400px;
  }

  .propertiesContent {
    width: 100%;
    word-break: break-all;
    font-size: 13px;
  }

  .propertiew-authority span {
    display: inline-block;
    width: 20px;
    height: 20px;
    line-height: 20px;
    text-align: center;
    margin-top: 2px;
    margin-right: 3px;
    background: #dbdbdb;
    color: #fff;
  }

  .propertiew-authority .propperiesWritable {
    background: #267ceb;
  }

  .propertiew-authority .properiesReadable {
    background: #3cbf80;
  }

  .pl0 {
    padding-left: 0 !important;
  }
  .path-list {
    border-bottom: 1px solid #d9d9d9;
    padding-left: 30px;
    height: 29px;
    line-height: 23px;
  }
  .path-list:last-child {
    border-bottom: none;
    line-height: 34px;
  }
  .ali-placeholder {
    color: #ccc;
  }

  label {
    margin-bottom: 0;
  }

  .edit {
    display: none;
  }

  .view {
    label {
      overflow: hidden;
      text-overflow: ellipsis;
      width: 100%;
      white-space: nowrap;
    }
    height: 23px;
    line-height: 23px;
  }
  .error {
    display: none;
  }
  .editing{
    .edit{
      display: inline-block;
    }
    .error{
      display: inline;
      color: #f00;
    }
    .view{
      display: none;
    }
  }

  .edit {
    border: 1px solid #bfcbd9;
    padding-left: 5px;
    width: 400px;
    height: 30px;
    line-height: 30px;
    background-color: #fff;
  }
    .add-extended-field{
        display: flex;
        justify-content: flex-end;
    }
</style>
