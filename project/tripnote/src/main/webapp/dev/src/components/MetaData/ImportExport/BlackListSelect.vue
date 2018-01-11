<template>
  <li class="black-list-item">
    <section class="icon-DB black-list-title">
      <span class="foldCon">
        <i class="selectFolder folder baseIcon"></i>
      </span>
      <span>{{typeof model == 'string' ? model : Object.keys(model)[0]}}</span>
      <span
        class="black-list-check"
        v-if="typeof model == 'string'"
        @click="deleteItem(model)">
        <i class="el-icon-circle-close"></i>
      </span>
    </section>
    <!-- 显示表 -->
    <ul v-if="typeof model == 'object'">
      <li class="black-list-item" v-for="item in model[Object.keys(model)[0]]">
          <section class="icon-DB black-list-title">
            <span class="foldCon">
              <i class="folder"></i>
            </span>
            <span class="black-list-select" v-ellipsis="item.tableName"></span>
            <span class="black-list-check"
              @click="deleteItem(item)">
              <i class="el-icon-circle-close"></i>
            </span>
          </section>
      </li>
    </ul>
  </li>
</template>
<script>
  import Bus from 'utils/bus'
  export default{
    name: 'BlackListSelect',
    props: [ 'model' ],
    methods: {
      deleteItem(model){
        // console.log(model)
        Bus.$emit('deleteItem', model)
      }
    }
  }
</script>
<style lang="less">
.dbList .el-icon-circle-close{
    cursor: pointer;
    color: #ccc;
    font-size: 16px;
    background: none !important;
  }
  .black-select-item{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
    height: 37px;
    line-height: 37px;
    border-bottom: 1px solid #e2e2e2;
    .black-select-title{
      i{
        display: inline-block;
        width: 16px;
        height: 14px;
        margin: 9px 8px 0 0;
      }
    }
  }
  .black-select-item:hover{
    background: #f3f3f5;
  }
  .black-list-title{
    .black-list-select{
      max-width: 235px !important;
    }
  }
  .black-list-right ul{
    padding-left: 15px;
    margin: 0;
  }
</style>