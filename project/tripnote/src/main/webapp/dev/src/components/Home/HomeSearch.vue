<template>
  <div
      class="home-search-select-container">
    <div class="search-tips" v-if="tipVisible">
      请输入查询关键字
    </div>
    <h4>其他查询条件：</h4>
    <ul>
      <li>
        <el-row :gutter="10">
          <el-col :span="7">
            <label for="">所属主题:</label>
          </el-col>
          <el-col :span="17" style="position: relative;">
            <el-input @focus="showSelectContanier"
                      placeholder="请选择主题"
                      readonly
                      v-model="params.topicPath"></el-input>
            <div class="theme-select"
              v-if="showSelectVisible">
              <el-input icon="search" v-model="theme" :on-icon-click="handleSearch"></el-input>
              <div class="search-topic-tree-container">
                <div class="boder-cover cover-top"></div>
                <topic-tree
                  style="overflow: hidden;"
                  v-if="topicTrees.length>0"
                  :model="topicTrees"></topic-tree>
                <div class="boder-cover cover-bottom"></div>
              </div>
            </div>
          </el-col>
        </el-row>
      </li>
      <li>
        <el-row :gutter="10">
          <el-col :span="7">
            <label for="">所属来源系统:</label>
          </el-col>
          <el-col :span="17">
            <el-select filterable v-model="params.upstreamSystem">
              <el-option
                v-for="item in upstreams"
                :label="item.upstreamSystem"
                :value="item.upstreamSystem">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
      </li>
      <li>
        <el-row :gutter="10">
          <el-col :span="7">
            <label for="">源表功能模块:</label>
          </el-col>
          <el-col :span="17">
            <el-select filterable v-model="params.sourceTable">
              <el-option
                v-for="item in sourceTables"
                :label="item.sourceTable"
                :value="item.sourceTable">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
      </li>
    </ul>
    <footer>
      <el-button @click="dispatchHighSearch" type="primary">确认</el-button>
      <el-button @click="cancel">取消</el-button>
    </footer>
  </div>
</template>
<script>
  import Bus from 'utils/bus'
  import { httpRequest } from 'utils'
  import TopicTree from './TopicTree'
  export default{
    name: 'HomeSearch',
    data () {
      return {
        showSelectVisible: false,
        theme: '',
        upstreams: [],
        sourceTables: [],
        topicTrees: [],
        tipVisible: false,
        params: {
          upstreamSystem: '',
          sourceTable: '',
          topicPath: ''
        }
      }
    },
    props: {
      keyObj: { type: Object }
    },
    components: {
      TopicTree
    },
    created () {
      this.fetchAllData()
    },
    mounted () {
      Bus.$on('selectTopic', (path)=>{
        this.selectTopic(path)
      })
      Bus.$on('hidden', param => {
        console.log(param)
        this.showSelectVisible = param
      })
    },
    methods: {
      fetchAllData () {
        this.fetchUpstream()
        this.fetchSourceTable()
        this.fetchTopicTree()
      },
      async fetchUpstream () {
        const res = httpRequest({vm: this, url: '/api/v1/upstream'})
        res.then( res => {
          this.upstreams = res
        })
      },
      async fetchSourceTable () {
        const res = httpRequest({vm: this, url: '/api/v1/sourceTable'})
        res.then( res => {
          this.sourceTables = res
        })
      },
      async fetchTopicTree (key=null) {
        const data = {
          level: 0,
          page: 1,
          path: "",
          rows: 10000,
          key
        }
        const res = httpRequest({vm: this,
                                 url: '/api/v1/topicTree',
                                 type: 'post',
                                 data})
        res.then( res => {
          this.topicTrees = res.records
          this.setOpen(this.topicTrees)
        })
      },
      setOpen(topicTrees){
        topicTrees.forEach(tree => {
          if(tree.children){
            this.$set(tree, 'open', true)
            this.setOpen(tree.children.records)
          }else{
            this.$set(tree, 'open', false)
          }
        })
      },
      selectTopic(path){
        this.params.topicPath = path
        this.showSelectVisible = false
      },
      dispatchHighSearch(){
        if(this.keyObj.keywords){
          this.tipVisible = false
          this.$store.dispatch('setSearchParams',
            Object.assign(this.params, {
              source: this.keyObj.source
            })
          )
          this.routeSearch()
        } else {
          this.tipVisible = true
        }
      },
      handleSearch(){
        this.fetchTopicTree(this.theme)
      },
      showSelectContanier(){
        this.showSelectVisible = true
      },
      cancel(){
        Bus.$emit('hidden', false)
      },
      routeSearch(){
        this.$router.push({
          name: 'SearchResult',
          query: {category: this.keyObj.searchType, keyword: this.keyObj.keywords, t: new Date().getTime()}
        })
      }
    }
  }
</script>
<style lang="less">
  .search-topic-tree-container{
    >ul{
      >li{
        border-left: 1px solid #dedede;
        overflow: hidden;
      }
    }
  }
  .boder-cover{
    border-left: 1px solid #ffffff;
    height: 22px;
    position: relative;
    margin-left: 20px;
  }
  .cover-top{
    margin-bottom: -18px;
  }
  .cover-bottom{
    margin-top: -15px;
  }
  .home-search-select-container{
    position: absolute;
    width: 100%;
    z-index: 10;
    background: #fff;
    box-shadow: 0 10px 20px 2px rgba(0,0,0,.15);
    border: 1px solid #E3E8EA;
    padding: 20px;
    .search-tips{
      color: red;
    }
    .el-col-17{
      padding: 0 !important;
    }
    h4{
      color: #999999;
      font-size: 14px;
      margin-bottom: 20px;
    }
    li{
      margin-bottom: 15px;
      label{
        width: 100px;
        text-align: right;
        margin-right: 10px;
        line-height: 30px;
      }
    }
    .el-select{
      width: 100%;
    }
    footer{
      margin-top: 30px;
      text-align: right;
    }
  }
  .home-search-input{
    flex-grow: 1;
    display: flex;
    border: 2px solid #E3E8EA;
    border-right: 0;
    .home-search-select{
      width: 96px;
      line-height: 37px;
      color: #aaaaaa;
      cursor: pointer;
      i{
        margin-left: 5px;
      }
    }
    input{
      border: none;
    }
  }
  .theme-select{
    position: absolute;
    box-shadow: 1px 1px 3px #999999;
    padding: 10px;
    height: 300px;
    width: 100%;
    z-index: 1;
    background: #fff;
    overflow-y: scroll;
    li{
      margin-bottom: 0;
      line-height: 30px;
    }
  }
</style>
