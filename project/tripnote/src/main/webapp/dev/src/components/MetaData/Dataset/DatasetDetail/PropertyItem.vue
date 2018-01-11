<template>
  <td :class="{editing: itemName === parentLabel}">
    <div class="view">
      <label
        @dblclick="editItem"
        class="ali-placeholder"
        v-if="!value">
        {{$parent.$parent.hasPrivilege?placeholder:$t('content.common.zwsj')}}
        <i @click="editItem" v-if="$parent.$parent.hasPrivilege" class="edit-tip el-icon-edit"></i>
      </label>
      <label v-else>
        <div
            @dblclick="editItem"
            v-ellipsis="selectLabel"
            style="float: left;"></div>
        <div style="float: right;">
            <!-- 删除自定义属性 -->
            <!-- <i @click="delItem"
                v-if="/extendedField/.test(itemName)"
                class="edit-tip el-icon-delete"></i> -->
            <i @click="editItem"
                v-if="$parent.$parent.hasPrivilege"
                class="edit-tip el-icon-edit"></i>
        </div>
      </label>
    </div>
    <textarea
      v-if="!isSelect && itemName == 'description'"
      class="edit edit-textarea"
      type="text"
      v-model="itemValue"
      @blur="doneEdit"
      @keyup.enter="doneEdit"
      @keyup.esc="cancelEdit"
      v-focus="parentLabel === itemName"
    ></textarea>
    <!-- v-if="!isSelect && itemName != 'description'" -->
    <input
      v-if="!isSelect && itemName != 'description'"
      class="edit"
      type="text"
      v-model="itemValue"
      @blur="doneEdit"
      @keyup.enter="doneEdit"
      @keyup.esc="cancelEdit"
      v-focus="parentLabel === itemName"/>
    <el-select
      v-model="itemValue"
      v-if="isSelect"
      class="edit"
      @visible-change="selectVisibleChange">
      <el-option
        v-for="item in selectList"
        :label="item[0]"
        :value="item[1]">
      </el-option>
    </el-select>
  </td>
</template>
<script>
  import Bus from 'utils/bus'
  export default{
    name: 'PropertyItem',
    props: {
      className: String,
      itemName: [String, Object],
      value: String,
      parentLabel: String,
      placeholder: String,
      isSelect: {
        default: false,
        type: Boolean
      },
      selectList: Array
    },
    data(){
      const vm = this
      return {
        editedLabel: '',
        itemValue: '',
      }
    },
    watch: {
      value(val){
        this.itemValue = val
      }
    },
    created(){
      this.editedLabel = this.parentLabel
      this.$nextTick(()=>{
        this.itemValue = this.value
      })

    },
    directives: {
      'focus': function (el, binding) {
        if (binding.value) {
          el.focus();
        }
      }
    },
    computed: {
      selectLabel(){
        const vm = this
        let showLabel = ''
        // con.warn(this.itemValue)
        if(this.isSelect && this.selectList.length > 0 && this.itemName != 'owner'){

          let find = this.selectList.find(val=>val[1]==vm.itemValue)
          showLabel = find ? find[0] : ''
        }else{
          showLabel = this.itemValue
        }
        return showLabel;
      }
    },
    methods: {
      editItem(){
        //下拉框模拟失去焦点
        const vm = this
        window.onclick = function(e){
          if(vm.itemName == 'sensitiveData' || vm.itemName == 'owner'){
            const target = $(e.target).hasClass('el-select') || $(e.target).parents().hasClass('el-select')
            if(!target){
              vm.doneEdit()
            }
          }
        }
        if(!this.$parent.$parent.hasPrivilege || this.$parent.hasErrors){
          return;
        }else{
          Bus.$emit('setEditLabel', this.itemName)
        }
      },
    //   delItem(){
    //       if(/extendedField/.test(this.itemName)){
    //           const params = {
    //               isDel: true,
    //
    //           }
    //       }
    //   },
      async doneEdit(){
        if (!this.parentLabel) {
          return;
        }
        const editedLabel = this.itemName
        let beforeEditCache = this.value
        if(this.parentLabel === 'owner'){
          if(this.itemValue !== beforeEditCache){
            this.$emit('emithandleCommand', this.itemValue)
          }else{
            this.cancelEdit()
          }
        }else if(typeof this.itemName == 'string'){
          const params = {
            value: this.itemValue,
            editedLabel,
            beforeEditCache
          }
          this.$emit('emitDoneEdit', params)
        }else{
          const params = this.itemName
          params.comment = this.itemValue
          this.$emit('emitDoneEdit', params)
        }
      },
      cancelEdit() {
        Bus.$emit('cancelEdit', this.value, this.itemName)
      },
      selectVisibleChange(val){
        !val && this.doneEdit()
      }
    }
  }

</script>
<style lang="less">
  .editing{
    .edit{
      display: inline-block;
      width: 100%;
      outline: none;
    }
    textarea.edit{
      height: 30px;
    }
    input.edit{
      height: 30px;
    }
    .error{
      display: inline;
      margin-left: 10px;
      color: #f00;
    }
    .view{
      height: 30px;
      line-height: 30px;
      display: none;
    }
    .el-select{
      border:0;
      padding-left: 0;
      height: auto !important;
      width: 160px;
      .el-input__inner{
        width: 160px;
        height: 27px !important;
      }
    }
  }
  .edit-tip{
    display: none;
  }
  .view label{
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .view label:hover .edit-tip{
    display: inline-block;
  }
  .edit-textarea{
    padding-top: 3px;
    padding-bottom: 3px;
    line-height: 20px;
  }
</style>
