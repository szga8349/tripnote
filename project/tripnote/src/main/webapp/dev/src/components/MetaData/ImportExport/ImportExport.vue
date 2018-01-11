<template>
  <div class="box">
    <div id="MainCon">
      <div class="personal-list">
        <split-controler
          :isOpenTree="isOpenTree"
          :controlerLeft="controlerLeft"
          @setTreeControler="treeControl"></split-controler>

        <div class="item" v-if="this.username == 'leapadmin'">
          <h4>
              <i class="icon iconImport"></i>
              {{$t('content.dataset.importExport.ysjzddr')}}
          </h4>
          <ul style="border-bottom: 1px dashed #d5d5d8">
              <router-link
                v-for="item in importTitleList"
                :to="{name: item.routerName}"
                tag="li"
                :class="{active: item.routerName == 'ConfigurationList' ? selectType.includes('Configuration') : selectType == item.routerName}"
                >
                <i :class="item.icon"></i>
                {{item.title}}
              </router-link>
          </ul>
        </div>

        <div class="item">
          <router-link
            tag="h4"
            :to="{name: 'ImportModule'}"
            v-if="hasPrivilege"
            class="link"
            :class="{active: selectType == 'ImportModule'}">
            <i class="import_export_icon5"></i>
            {{$t('content.dataset.importExport.mbdr')}}
          </router-link>
        </div>

        <div class="item">
          <router-link
            tag="h4"
            :to="{name: 'ExportModule'}"
            class="link"
            :class="{active: selectType == 'ExportModule'}">
            <i class="import_export_icon6"></i>
            {{$t('content.dataset.importExport.ysjdc')}}
          </router-link>
        </div>

      </div>
      <div class="datasetCon">
        <div class="titShow import-export-nav">
          <h4>{{$t('content.dataset.importExport.drdc')}}</h4>
          <span v-if="$route.params.id">
              > {{`${$t('content.dataset.importExport.drrwgl')}&nbsp;>&nbsp;${taskName}`}}
          </span>
          <span v-else-if="!$route.params.id && $route.name=='Configuration'">
              > {{$t('content.dataset.importExport.drrwgl')}}&nbsp;>&nbsp;{{$t('content.dataset.importExport.xjrw')}}
          </span>
          <span v-else>
            <span v-if="firstTitle">> {{$t('content.dataset.importExport.ysjzddr')}}</span>&nbsp;>&nbsp;{{activeRouteName}}
          </span>
        </div>
        <div class="tableDetail">
          <div class="tabCon">
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import SplitControler from 'common/SplitControler'
  import {windowSplitter, testPrivilege} from 'mixins/common'
  import {mapState} from 'vuex'
  import Bus from 'utils/bus'
  export default{
    components: {
      SplitControler,
    },
    mixins: [windowSplitter, testPrivilege],
    data(){
      return {
        taskName: '',
        isOpenTree: true,
        controlerLeft: 310,
        showList: {},
        selectType: this.$route.name,
        activeName: '1',
        routerTitle: this.$t('content.dataset.importExport.drrwgl'),
        // activeRouteName: '',
        importTitleList: [
          {
            routerName: 'ConfigurationList',
            icon: 'import_export_icon2',
            title: this.$t('content.dataset.importExport.drrwgl')
          },
          {
            routerName: 'DataSourceLink',
            icon: 'import_export_icon3',
            title: this.$t('content.dataset.importExport.sjylj')
          },
          {
            routerName: 'TaskList',
            icon: 'import_export_icon4',
            title: this.$t('content.dataset.importExport.rwjl')
          },
        ],
        titleNameList: {
          'ConfigurationList': this.$t('content.dataset.importExport.drrwgl'),
          'DataSourceLink': this.$t('content.dataset.importExport.sjylj'),
          'TaskList': this.$t('content.dataset.importExport.rwjl'),
          'ImportModule': this.$t('content.dataset.importExport.mbdr'),
          'ExportModule': this.$t('content.dataset.importExport.ysjdc')
        },
        firstTitle: this.$t('content.dataset.importExport.ysjzddr')
      }
    },
    computed: {
        ...mapState({
            username: "username"
        }),
        activeRouteName(){
          return this.titleNameList[this.$route.name]
        }
    },
    watch: {
      '$route'(val){

        this.selectType = val.name

        // console.log(this.$route.name)

        if(this.$route.name == 'ImportExport'){
          if(this.username == 'leapadmin'){
            this.activeName = 1
            this.$router.push({name: 'ConfigurationList'})
          }else{
            if(this.hasPrivilege){
              this.$router.push({name: 'ImportModule'})
            }else{
              this.$router.push({name: 'ExportModule'})
            }
          }
        }
        if(this.$route.name == 'DataSourceLink' || this.$route.name == 'ConfigurationList' || this.$route.name == 'TaskList'){
          this.firstTitle = this.$t('content.dataset.importExport.ysjzddr')
        }else{
          this.firstTitle = ''
        }

        // this.activeRouteName = 

      }
    },
    async created(){
      const vm = this
      // /systemImport
      await vm.testPrivilege('/excelMetadataInfo/import')

      if(this.username == 'leapadmin'){
        this.showList = {
          ConfigurationList: {title: this.$t('content.dataset.importExport.drrwgl'),  id: 3},
          ImportModule: {title: vm.$t('content.dataset.importExport.mbdr'), id: 1},
          ExportModule: {title: vm.$t('content.dataset.importExport.ysjdc'), id: 2},
          DataSourceLink: {title: this.$t('content.dataset.importExport.sjylj'), id: 4},
          TaskList: {title: this.$t('content.dataset.importExport.rwjl'), id: 5},
        }
        if(this.$route.name == 'ImportExport'){
          this.$router.push({name: 'ConfigurationList'})
        }
      }else{
        if(this.hasPrivilege){
          this.showList = {
            ImportModule: {title: vm.$t('content.dataset.importExport.mbdr'), id: 1},
            ExportModule: {title: vm.$t('content.dataset.importExport.ysjdc'), id: 2},
          }
          if(this.$route.name == 'ImportExport'){
            this.$router.push({name: 'ImportModule'})
          }
        }else{
          this.showList = {
            ExportModule: {title: vm.$t('content.dataset.importExport.ysjdc'), id: 2},
          }
          if(this.$route.name == 'ImportExport'){
            this.$router.push({name: 'ExportModule'})
          }
        }
      }
    },
    mounted(){
      let self = this;
      const splitter = self.treeControlInit()
      $(window).scroll(function () {
        const scrollLeft = $(window).scrollLeft()
        let splitterPosition;
        if (self.isOpenTree) {
          splitterPosition = 310
        } else {
          splitterPosition = 60;
        }
        self.controlerLeft = splitterPosition - scrollLeft;
      });
      //其他页面跳转过来的active
      let _name = self.$route.name;
      if (_name !== "ImportModule") {
        self.selectType = _name
      }
      //控制title
      Bus.$on('taskName',(name)=>{
        this.taskName = name
      })
    },
    methods: {
      handleChange(){
        this.activeName = 1
      },
      treeControl(){
        this.isOpenTree = !this.isOpenTree
        let _left = this.isOpenTree ? "310" : '60'
        $('#MainCon').split().destroy()
        this.controlerLeft = _left - $(window).scrollLeft()
        this.treeControlInit(_left - 60);
      },
      resetPosition(){
        let leftPosition = this.splitter.position()
        this.controlerLeft = leftPosition + 60;
      },
    }
  }
</script>
<style lang="less">
    .el-collapse{
      border: 0;
    }
    .import-export-title{
      display: flex;
      align-items: center;
    }
    .import-export-nav{
        padding: 5px 0 5px 20px;
        background: #fff;
        h4{
          display: inline-block;
          font-size: 16px;
        }
        span{
          color: #a5aaac;
          margin: 0 3px;
        }
    }
</style>
