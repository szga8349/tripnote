<template>
  <div class="home-box-container" :style="{height: `${homeHeight}px`}">
    <div class="home-container">
      <div class="searchCon">
        <h3>元数据管理系统</h3>
        <div class="advance-search-container">
          <div class="home-search-container">
            <div class="home-search">
              <div class="home-search-input">

                <!-- <input type="text" class="form-control"
                            :placeholder="searchKeywordPlaceholder[searchType]"
                            v-model="searchKeyword"
                            @focus="historySearch"
                            @keyup="keyupSearch"
                            @keydown.enter="routeSearch"/> -->

                <el-input
                  v-model="searchField.keywords"
                  @keydown.enter.native="routeSearch"
                  placeholder="请输入关键字"></el-input>

                <div class="home-search-select">
                  <el-select v-model="searchField.source">
                    <el-option
                      v-for="item in selectTypeList"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </div>
              </div>
              <el-button
                @click="routeSearch"
                type="primary"
                icon="el-icon-search">
              </el-button>
            </div>
            <home-search v-hidden="{text: '高级搜素'}" :keyObj="searchField" v-if="searchVisible"></home-search>
          </div>
          <a class="advance-link" href="javascript:;" @click="searchVisible=!searchVisible">高级搜索</a>
        </div>
      </div>

      <section class="home-userinfo">
        <div class="home-count-info">
          <div class="home-user-box">
            <div class="user-avatar">
              <img :src="avatarUrl" alt="" />
            </div>
            <h4>{{userInfo.userName}}</h4>
            <ul>
              <li>
                <label>账号角色:</label>
                <span>{{userRole}}</span>
              </li>
              <li>
                <label>加入项目:</label>
                <!-- <span class="project-list" :title="userInfo.projects">
                </span> -->
                <div class="projectsNum" v-if="userInfo.projects.split(',').length>3">
                  <el-tooltip class="item"
                    effect="dark"
                    :content="userInfo.projects"
                    placement="top-start"
                    >
                    <span class="links">{{`${userInfo.projects.split(',').length}个`}}</span>
                  </el-tooltip>
                </div>
                <div class="projectsNum" v-else>{{userInfo.projects}}</div>
              </li>
            </ul>
          </div>
          <ul class="user-info-right">
            <li v-for="item in userInfoRight"
              @click="pushRouter(item.router)"
              >
              <label>{{item.label}}</label>
              <div class="loading" v-if="item.count == null"></div>
              <template v-else>
                <div><span>{{item.count}}</span> 条</div>
              </template>
            </li>
          </ul>
        </div>
        <div class="info-right">
          <home-user-info v-for="(item, index) in infoList"
                        @click.native="pushRouter(item.router, item.params && item.params)"
                        :userInfo="item"
                        v-if="showInfoList(item.title)"></home-user-info>
        </div>
      </section>
      <section class="home-footer">
        <div style="width: 450px;">
          <router-link tag="div"
            :to="{name: 'Physical'}"
            @click.native="changeMenu('business')"
            class="home-footer-link">
            <i class="home-footer-icon1"></i>
            <div>
              <h4>业务视图：{{topic.datasetCount}}</h4>
              <p>按照业务逻辑归类展示的元数据信息</p>
              <p class="num">
                {{topic.topicCount}}个业务主题，
                {{topic.level1}}个一级主题
              </p>
            </div>
          </router-link>
          <router-link tag="div"
            :to="{name: 'Dataset'}"
            @click.native="changeMenu('metro')"
            class="home-footer-link">
            <i class="home-footer-icon2"></i>
            <div>
              <h4>物理视图：{{physical.tableNum}}</h4>
              <p>按照物理逻辑归类展示的元数据信息</p>
              <p class="num">
                {{physical.datasourceNum}}个数据源，
                {{physical.databaseNum}}个数据库
              </p>
            </div>
          </router-link>
        </div>
        <div class="home-footer-right">
          <home-top-list v-for="item in homeTopList" :item="item"></home-top-list>
        </div>
      </section>
    </div>
  </div>
</template>
<script>

  import HomeUserInfo from './HomeUserInfo'
  import HomeTopList from './HomeTopList'
  import HomeSearch from './HomeSearch'
  import Bus from 'utils/bus'
  import { httpRequest } from 'utils'
  export default{
    data(){
      return {
        user: {
          username: this.$store.state.username && window.sessionStorage.getItem('username')
        },
        homeHeight: 550,
        searchField: {
          keywords: '',
          source: 'all',
          searchType: 'datasets'
        },

        avatarUrl: require('assets/images/leapavartar.png'),
        homeTopList: [
          {
            title: '元数据维护完整性Top10',
            list: [],
            loading: true
          },
          {
            title: '元数据收藏Top10',
            list: [],
            loading: true
          },
          {
            title: '元数据点击Top10',
            list: [],
            loading: true
          }
        ],
        searchVisible: false,
        selectType: 'all',
        selectTypeList: [
          { label: '全部', value: 'all' },
          { label: '表名', value: 'name' },
          { label: '别名', value: 'businessName' },
          { label: '数据描述', value: 'description' },
          { label: '业务意义', value: 'bizMeaning' },
          { label: '字段', value: 'fieldName' },
          { label: '字段描述', value: 'fieldComment' },
        ],
        topic: {
          datasetCount: 0,
          topicCount: 0,
          level1: 0
        },
        physical: {
          tableNum: 0,
          databaseNum: 0,
          datasourceNum: 0
        },
        ownerCount: null,
        metaCount: null,
        authorityCount: null,
        collectCount: null,
        examines: {
          waitExamine: null,
          waitMyExamine: null,
          examined: null,
        },
      }
    },
    components: {
      HomeUserInfo,
      HomeTopList,
      HomeSearch
    },
    computed: {
      userInfo(){
        return this.$store.state.userInfo
      },
      userRole(){
        switch (this.userInfo.roles){
          case 'leapid.admin':
            return '系统管理员'
            break
          case 'leapid.pm':
            return '项目管理员'
            break
          case 'leapid.member':
            return '普通用户'
            break
        }
      },
      infoList() {
        return [
          { title: '我有权限的表', label: '账号下有读写权限的表', count: this.authorityCount, color: '#60c94d', router: 'Authority'},
          { title: '我收藏的表', label: '账号下收藏的所有表', count: this.collectCount, color: '#ff7572', router: 'Collect'},
          { title: '负责人信息待维护', label: '待维护业务负责人', count: this.ownerCount, color: '#ffca53', router: 'Owner', params:{ tab: 'PersonalChargeMaintain', label: '负责人信息待维护' }},
          { title: '元信息待维护', label: '待维护的业务元信息', count: this.metaCount, color: '#b187ff', router: 'Owner', params: {tab: 'PersonalMetaMaintain', label: '元信息待维护'}}
        ]
      },
      userInfoRight() {
        return [
          { label: '申请待审核', count: this.examines.waitExamine, router: 'Applys'},
          { label: '待我审核', count: this.examines.waitMyExamine, router: 'Approvals'},
          { label: '已申请', count: this.examines.examined, router: 'Applys'},
        ]
      },
    },
    created(){
      console.log(this)
      Bus.$on('hidden', param => {
        this.searchVisible = param
      })

      var vm = this;
      this.fetchMaintainTopList();
      this.fetchFavTopList();
      this.fetchClickTopList();
      this.fetchCount()

      this.fetchOwnerCount()
      this.fetchMetaCount()

      this.fetchPersonal()

      this.fetchAuthority()
      this.fetchWaitMyExamined()
      this.fetchWaitExamined()
      //计算window的高度
      this.homeHeight = document.body.offsetHeight - 120

      $(window).resize(function(){
        vm.homeHeight = document.body.offsetHeight - 120
      })
    },
    methods: {
      pushRouter(routerName, params=null){
        if(params){
          this.$store.dispatch('ownerActiveTab', {component: params.tab, title: params.label})
        }
        this.$router.push({name: routerName})
      },
      fetchPersonal(){
        const res = httpRequest({ vm: this, url: '/datasets/getPersonalDataCount'})
        res.then( res => {
          this.collectCount = res.Collect
          this.authorityCount = res.Approvals
        })
      },
      fetchAuthority(){
        const res = httpRequest({ vm: this, url: '/privilege/getPrivilegeApplysCount'})
        res.then( res => {
          this.examines.examined = res
        })
      },
      fetchWaitMyExamined(){
        const res = httpRequest({ vm: this, url: '/privilege/getApprovalListCount'})
        res.then( res => {
          this.examines.waitMyExamine = res
        })
      },
      fetchWaitExamined(){
        const res = httpRequest({ vm: this, url: '/datasets/ownedDatasetWithoutCommonInfoCount'})
        res.then( res => {
          this.examines.waitExamine = res
        })
      },
      fetchMetaCount(){
        const res = httpRequest({ vm: this, url: '/datasets/ownedDatasetWithoutCommonInfoCount'})
        res.then( res => {
          this.metaCount = res
        })
      },
      async fetchMaintainTopList(){
          const res = await this.$http.get(`/datasets/ownedDatasetWithoutCommonInfo?rows=10&page=1`)
          if(res.body.statusCode == 200){
            var _records = res.body.message.records
            var _list = []
            for (var i = 0; i < _records.length; i++) {
              _list.push(_records[i]['datasetName'])
            }
            this.homeTopList[0].list = _list
            this.homeTopList[0].loading = false
          }
      },
      async fetchFavTopList(){
        const res = await this.$http.get(`/datasets/getTopFavoriteDataset`)
        if(res.body.statusCode == 200){
          var _records = res.body.message
          var _list = []
          for (var i = 0; i < _records.length; i++) {
            _list.push(_records[i]['name'])
          }
          this.homeTopList[1].list = _list
          this.homeTopList[1].loading = false
        }
      },
      async fetchClickTopList(){
        const res = await this.$http.get(`/datasets/getTopDatasetAccess`)
        if(res.body.statusCode == 200){
          var _records = res.body.message
          var _list = []
          for (var i = 0; i < _records.length; i++) {
            _list.push(_records[i]['name'])
          }
          this.homeTopList[2].list = _list
          this.homeTopList[2].loading = false
        }
      },
      fetchCount(){
        this.fetchTopicCount()
        this.fetchPhysicalCount()
      },
      fetchOwnerCount(){
        const res = httpRequest({ vm: this, url: '/datasets/ownedDatasetWithoutOwnerCount'})
        res.then( res => {
          this.ownerCount = res
        })
      },
      fetchTopicCount(){
        const res = httpRequest({ vm: this, url: '/topic/treeInfo'})
        res.then( res => {
          res.forEach( val => {
            this.topic.datasetCount += val.datasetCount
            this.topic.topicCount += val.topicCount
            if(val.level == 1){
              this.topic.level1 += val.topicCount
            }
          })
        })
      },
      fetchPhysicalCount(){
        const res = httpRequest({ vm: this, url: '/datasets/treeInfo'})
        res.then( res => {
          let source = 0
          res.forEach( val => {
            this.physical.tableNum += val.tableNum
            this.physical.databaseNum += val.databaseNum
            source++
          })
          this.physical.datasourceNum = source
        })
      },
      changeMenu(type){
        this.$store.dispatch('changeTableTypeOpts', '0,1,2')
        this.$store.dispatch('saveTableName', '')
        this.$store.dispatch('saveDatasetKeyword', "")

        if(type == 'business' || type == 'metro'){
          if(type == 'business'){
            // this.$router.push({name: 'Dataset'})
            this.$store.dispatch('setBusiness', true)
          }else{
            // this.$router.push({name: 'Physical'})
            this.$store.dispatch('setBusiness', false)
          }

          const {path} = this.$route;

          if(this.$store.state.navIsData){
            Bus.$emit('initTree')
          }
          this.$store.dispatch('saveNavData', true);
        }else{
          this.$store.dispatch('saveNavData', false)
        }
      },
      routeSearch(){
        const keyword = this.searchField.keywords
        if (!keyword) {
          return;
        }
        this.$store.dispatch('setSearchParams', {
          source: this.searchField.source
        })
        this.$router.push({
          name: 'SearchResult',
          query: {category: this.searchField.searchType, keyword, t: new Date().getTime()}
        })
      },
      showInfoList(title){
        let visible = true
        const userRole = this.userInfo.roles
        const isAdmin = userRole == 'leapid.admin'
        const isPm = userRole == 'leapid.pm'
        switch (title){
          case '负责人信息待维护':
            visible = isAdmin || isPm ? true : false
            break
          case '元信息待维护':
            visible = isAdmin ? true : false
            break
        }
        return visible
      }
    }
  }
</script>
<style lang="less">
//
  .advance-link{
    font-size: 14px;
    color: #014b70;
  }
  .home-box-container{
    overflow-y: scroll;
  }
  .home-container{
    border: 1px solid transparent;
    padding: 0 40px;
    background: #fff;
    margin: 20px;
    >.searchCon{
      width: 738px;
      margin: 80px auto;
      h3{
        text-align: center;
        margin-bottom: 30px;
        margin-top: 0;
        font-size: 30px;
        color: #444;
        font-weight: 400;
      }
      .home-search{
        display: flex;
        flex-grow: 1;
        .el-icon-search{
          font-weight: bolder;
          font-size: 18px;
        }
        .el-button--primary{
          width: 60px;
        }
      }
      .home-search-container{
        position: relative;
        flex-grow: 1;
        a{
          line-height: 30px;
          margin-left: 20px;
        }
      }
      .home-search-input{
        .el-input{
          input{
            height: 40px !important;
            padding-left: 10px !important;

          }
          input::-webkit-input-placeholder{
              color: #b2b7bf;
              font-size: 14px;
          }
        }
      }
    }
    .home-userinfo{
      display: flex;
      height: 200px;
    }
    .home-count-info{
      display: flex;
      width: 450px;
      border: 1px solid #e2e8eb;
      padding: 0 20px 0 20px;
      flex-grow: 0;
      flex-shrink: 0;
      .home-user-box{
        border-right: 1px solid #e2e8eb;
        margin-right: 20px;
        padding-right: 10px;
        width: 46%;
        .user-avatar{
          width: 80px;
          height: 80px;
          margin: -40px auto 0;
          border-radius: 100%;
          position: relative;
          img{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 10px solid #fff;
            border-radius: 100%;
          }
        }
        h4{
          text-align: center;
          margin-bottom: 20px;
          color: #014b70;
          margin-top: 15px;
        }
        ul{
          width: 230px;
          margin: 0 auto;
          font-size: 14px;
          line-height: 30px;
          label{
            color: #888;
            display: inline-block;
            width: 70px;
          }
          span{
            color: #444;
          }
          .links{
            color: #014b70
          }
          .projectsNum{
            display: inline-block;
            width: 90px;
            margin-top: 5px;
            line-height: 24px;
            color: #444;
            word-break: break-all;
            vertical-align: top;
          }
        }
      }
      .user-info-right{
        font-size: 14px;
        width: 100%;
        .loading{
          width: 16px;
          height: 16px;
          margin-top: 25px;
          background: url(../../assets/images/loading_s.gif);
        }
        li{
          display: flex;
          border-bottom: 1px solid #e2e8eb;
          line-height: 65px;
          justify-content: space-between;
          label{
            color: #666666;
          }
          div{
            color: #014b70;
            font-size: 14px;
            span{
              font-size: 28px;
              font-weight: normal;
              font-family: 'Arial';
            }
          }
        }
        li:last-child{
          border: 0;
        }
      }
    }
    .home-footer{
      margin-bottom: 40px;
      display: flex;
      justify-content: space-between;
      margin-top: 40px;
      .home-footer-link{
        height: 180px;
        display: flex;
        justify-content: center;
        background: #eff4f6;
        margin-bottom: 20px;
        align-items: center;
        color: #8a919e;
        cursor: pointer;
        h4{
          color: #424544;
        }
        p{
          margin-bottom: 10px;
        }
      }
      .home-footer-right{
        display: flex;
        flex-grow: 1;
        margin-left: 20px;
        margin-bottom: 20px;
        border: 1px solid #e2e8eb;
        section{
          position: relative;
          flex-grow: 1;
          width: 33.33%;
          h4{
            margin: 0;
            padding: 0;
            line-height: 55px;
            border-bottom: 1px solid #e2e8eb;
            padding-left: 20px;
            font-size: 16px;
          }
          ul{
            padding: 10px 20px;
            line-height: 30px;
            i{
              display: inline-block;
              vertical-align: top;
              width: 16px;
              height: 16px;
              margin-top: 8px;
              line-height: 16px;
              text-align: center;
              color: #fff;
              background: #a9b0bc;
              font-style: normal;
              font-size: 12px;
            }
            .listNum1{
             background: #f54744;
           }
           .listNum2{
             background: #fe8545;
           }
           .listNum3{
             background: #ffad36;
           }
            span{
              display: inline-block;
              vertical-align: top;
              margin-left: 10px;
              font-size: 14px;
              color: #707070;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }
          }
        }
      }
    }
    .project-list{
      display: inline-block;
      /* margin-left: 80px; */
      font-size: 12px;
      width: 110px;
      margin-top: 5px;
      line-height: 20px;
      word-break: break-all;
      vertical-align: top;
    }
  }
  .info-right{
    flex-grow: 1;
    display: flex;
  }
  .home-search-select{
    input{
      width: 97px;
    }
  }
</style>

