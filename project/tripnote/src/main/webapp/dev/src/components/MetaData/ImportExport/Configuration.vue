<template>
  <div class="boxCommon">
    <!-- <h4 class="dataset-detail-crumbs export-inport-header">
      <span>元数据自动导入</span>
      <span class="path">
          <span>
              <span> &gt; </span>
              <a href="javascript:;" @click="$router.push({name: 'ConfigurationList'})">导入任务管理</a>
          </span>
      </span>
      <span class="path" v-if="taskName" style="border: 1px solid blue;">
        <span> &gt; </span>
        <span>{{taskName}}</span>
      </span>
    </h4> -->
    <header class="configuration-step">
      <div
        v-for="(item, index) in stepList"
        class="configurtion-step-item"
        :class="{ active: activeTab == item}">
        <i>{{++index}}</i>
        {{item}}
      </div>
    </header>
    <keep-alive>
      <component :is="activeTab == $t('content.dataset.importExport.rwxx')?'CreateTask':'BlackList'"></component>
    </keep-alive>
  </div>
</template>
<script>
  import Bus from 'utils/bus';
  import CreateTask from './CreateTask'
  import BlackList from './BlackList'
  export default{
    data(){
      return {
        stepList: [this.$t('content.dataset.importExport.rwxx'), this.$t('content.dataset.importExport.hqdkbhmdsz')],
        activeTab: this.$t('content.dataset.importExport.rwxx'),
        pathShow: [this.$t('content.dataset.importExport.drrwgl')],
        taskName: '',
        sourchChange: false,
      }
    },
    components: {
      CreateTask,
      BlackList
    },
    watch: {
      activeTab(val){
        if(val == this.$t('content.dataset.importExport.rwxx')){
          this.sourchChange = false
        }
      }
    },
    created(){
      Bus.$on('taskName',(name)=>{
        this.taskName = name
      })
    },
    computed: {
      firstStepData(){
        return this.$store.state.createTask
      }
    },
  }

</script>
<style lang="less">
  .configuration-step{
    border-bottom: 1px solid #cdcdcd;
    display: flex;
    justify-content: space-around;
    margin-top: 25px;
    line-height: 30px;
    .configurtion-step-item{
      padding: 0 18px;
      color: #888;
      i{
        display: inline-block;
        width: 22px;
        height: 22px;
        line-height: 22px;
        text-align: center;
        font-style: normal;
        border-radius: 100%;
        color: #fff;
        background: #cdcdcd;
      }
    }
    .configurtion-step-item.active{
      border-bottom: 1px solid #014d70;
      color: #014d70;
      i{
        background: #014d70;

      }
    }
  }
  .export-inport-header{
    font-size: 16px;
  }
  .export-inport-header .path{
    font-size: 12px;
    color: #a5aaac;
  }
</style>