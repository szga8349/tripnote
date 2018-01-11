<template>
  <div class="table-search">
    <div>
      <el-input
        :placeholder="placeholder"
        prefix-icon="el-icon-search"
        v-model="searchContent"
        @keyup.enter.native="searchKey">
      </el-input>
      <span
        v-if="searchContent.length>0"
        class="el-icon-circle-close"
        @click="clearInput"
      ></span>
    </div>
    <el-button @click.native="searchKey">{{$t('content.header.ss')}}</el-button>
  </div>
</template>
<script>
  import Bus from 'utils/bus'
  export default{
    name: 'Search',
    props: {
      placeholder: String,
      emitName: String,
    },
    watch: {
      '$parent.isClear'(){
        if(this.$parent.isClear){
          this.searchContent = this.searchContent.repeat(0);
          this.$parent.isClear = false;
        }
      },
    },
    data(){
      return {
        searchContent: ''
      }
    },
    mounted(){
      Bus.$on('clearSearch', () => {
        this.clearInput()
      })
    },
    methods: {
      clearInput(){
        this.searchContent = ''
        this.searchKey()
      },
      searchKey(){
        const key = this.searchContent.trim();
        const currentPage = 1;
        const params = {
          key,
          currentPage
        }
        this.$emit(this.emitName, params);
      }
    }
  }
</script>
<style>
  .table-search{
    float: right;
    display: flex;
    position: relative;
    margin-top: -3px;
  }
  .table-search > div{
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid #d9d9d9;
  }
  .table-search .el-input__icon{
    right: auto;
  }
  .table-search .el-input{
    width: auto;
  }
  .table-search .el-input__inner{
    width: 200px;
    height: 28px !important;
    padding-left: 30px !important;
    padding-right: 0;
    border-radius: 0;
    border: 0;
  }
  .table-search .el-button{
    line-height: 0;
    height: 30px;
    border-radius: 0;
    background: #f3f3f3;
    border-color: #d9d9d9;
    border-left: 0;
  }
  .table-search .el-button:hover{
    color: #014b70
   }
   .table-search .el-icon-circle-close{
    color: #fd8a8b;
   	margin-right: 5px;
    cursor: pointer;
   }
</style>
