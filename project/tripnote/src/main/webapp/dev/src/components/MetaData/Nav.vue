<template>
  <div class="nav-container">
    <div class="mainType">
      <ul>
        <router-link tag="li"
            v-for="item in tabList"
            :to="{name: item.router}"
            class="mainType__li"
            :class="{active:tabActive == item.type}"
            @click.native="changeMenu(item.type)">
            <a class="mainType-box">
                <i :class="'icon_'+item.id"></i>{{item.name}}
            </a>
        </router-link>
        <li class="searchBar" v-if="$route.name != 'home'">
          <div class="advance-search-container">
            <div style="position: relative;">
              <div class="searchBar__container">
                <div class="searchBar__opt" style="position:relative">
                  <div class="searchInput" :title="searchField.keywords">
                    <i class="el-icon-search searchBar__img"></i>
                    <input type="text" class="form-control"
                          :placeholder="searchKeywordPlaceholder[searchType]"

                          v-model="searchField.keywords"
                          @focus="historySearch"
                          @keyup="keyupSearch"
                          @keydown.enter="routeSearch"/>
                    <span class="searchInput__delete" v-if="!!searchField.keywords" @click="delectInput"></span>
                    <div class="nav-search-select">
                      <el-select v-model="searchField.source">
                        <el-option
                          v-for="item in selectTypeList"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </div>
                    <div class="searchResultList" v-if="searchVisible">
                      <ul>
                        <li v-for="(item,key) in tableFilterList"
                            v-html="item[0]"
                            @click="searchResult(item[1],item[2])">
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="searchBar__btn" @click="routeSearch">
                  <!-- <span class="searchBar__img"></span> -->
                  {{$t("content.header.ss")}}
                </div>
              </div>
              <home-search v-hidden="{text: '高级搜素'}" :keyObj="searchField" v-if="advanceSearchVisible"></home-search>
            </div>
            <a href="javascript:;" @click="advanceSearchVisible=!advanceSearchVisible">高级搜索</a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {EmphasizeName} from 'mixins/common' //搜索结果高亮
  import Vue from 'vue'
  import Bus from 'utils/bus'
  import HomeSearch from '../Home/HomeSearch'
  export default {
    name: 'Nav',
    mixins: [EmphasizeName],
    components: { HomeSearch },
    created() {
      let path = this.$route.fullPath.split('/');
      this.tabActive = path[3] || 'Dataset';
      this.$store.dispatch('setBusiness', this.tabActive === 'Dataset');
      this.searchField.keywords = this.$route.query.keyword
      const vm = this

      Bus.$on('hidden', param => {
        this.advanceSearchVisible = param
      })

    },
    data() {
      const vm = this
      return {
        advanceSearchVisible: false,
        tabActive: "Dataset",
        tabList: [
          {
            type: 'home',
            name: '主页',
            id: 11,
            router: 'home'
          },
          {
          	type: 'business',
            name: this.$t("content.tree.ywst"),
            id: "11",
            router: 'Dataset'
          },
          {
          	type: 'metro',
            name: this.$t('content.tree.wlst'),
            id: "12",
            router: 'Physical'
          },
          {
          	type: 'Personal',
            name: this.$t('content.personal.gldb'),
            id: "13",
            router: 'Personal'
          },
          {
            type: 'ImportExport',
            name: vm.$t('content.dataset.importExport.drdc'),
            id: '14',
            router: 'ImportExport'
          }
        ],
        tableFilterList: [],
        showDelete: false,
        // 显示搜索框的位置
        searchType: "datasets",//暂时只有数据集
        searchKeywordPlaceholder: {
          all: this.$t("content.header.srbmhlcm"),
          datasets: this.$t("content.header.srbm"),
          flows: this.$t("content.header.srlcm"),
        },
        searchField: {keywords: ''},
        searchTypeObj: {
          all: this.$t("content.header.qb"),
          datasets: this.$t("content.header.sjj"),
          flows: this.$t("content.header.sjgc"),
        },
        //搜索显示结果
        searchVisible: false,
        isSearch: null,
        selectTypeList: [
          { label: '全部', value: 'all' },
          { label: '表名', value: 'name' },
          { label: '别名', value: 'businessName' },
          { label: '数据描述', value: 'description' },
          { label: '业务意义', value: 'bizMeaning' },
          { label: '字段', value: 'fieldName' },
          { label: '字段描述', value: 'fieldComment' },
        ],
        searchField: {
          keywords: '',
          source: 'all',
          searchType: 'datasets'
        }
      }
    },
    computed: {
      path(){
        return this.$route.path;
      },
      language(){
        return this.$store.state.language
      },
    },
    watch: {
      '$route': function (val) {
        const {keyword} = val.query;
        const curView = this.$route.name;
        const actPath = this.path.split("/")[3];
        this.tabActive = actPath;
        curView !== "SearchResult" ? this.delectInput() : keyword ? this.showDelete = true : this.historySearch()
        this.advanceSearchVisible = false
      },
      language(val){
        Vue.config.lang = val
        this.tabList = [
          {type: "Dataset", name: this.$t("content.header.sjj"), id: "0"},
          {type: "Personal", name: this.$t('content.personal.grsj'), id: "10"},
        ]
        this.searchKeywordPlaceholder = {
          all: this.$t("content.header.srbmhlcm"),
          datasets: this.$t("content.header.srbm"),
          flows: this.$t("content.header.srlcm"),
        }
      }
    },
    methods: {
      changeMenu(type){
        this.$store.dispatch('changeTableTypeOpts', '0,1,2')
        this.$store.dispatch('saveTableName', '')
        this.$store.dispatch('saveDatasetKeyword', "")
        this.$store.dispatch('setModelOpen', false)

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
      searchTypeChange(type){
        this.searchType = type;
        this.searchField.keywords = ''
        this.fetchFilterData()
      },
      keyupSearch(e = window.event){
        this.searchField.keywords = e.target.value ? e.target.value : this.searchField.keywords;
        this.searchField.keywords == '' && this.historySearch()
        clearTimeout(this.isSearch)
        if (this.searchField.keywords) {
          this.isSearch = setTimeout(() => this.fetchFilterData(), 300);
        }
      },
      delectInput(){
        this.searchVisible = false
        this.searchField.keywords = ""
        this.showDelete = false
      },
      // 非高级搜索
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


        // const searchKey = this.searchField.keywords;
        // if (!searchKey) {
        //   return;
        // }
        // this.$router.push({
        //   name: 'SearchResult',
        //   query: {category: this.searchType, keyword: this.searchField.keywords, advance: false, t: new Date().getTime()}
        // })
      },
      historySearch(){
        if (this.searchField.keywords != '') {
          this.keyupSearch()
          return;
        }
        const url = "/api/v1/search/recent";
        this.$http.get(url).then((res) => {
          if (res.body.return_code != 200) {
            return
          } else {
            const result = res.body.data;
            const _result = [];
            result.map(val => {
              const arr = [];
              arr.push(val.keyword, val.keyword, val.category);
              _result.push(arr)
            })
            this.searchVisible = true;
            this.tableFilterList = _result;
          }
        }, (response) => {
          // error callback
        });
      },
      searchResult(item, category){
        if (!category) {
          category = this.searchType;
        }
        this.searchField.keywords = item;
        this.searchVisible = false;

        this.$router.push({
          name: 'SearchResult',
          query: {category: category, keyword: item, advance: false, t: new Date().getTime()}
        });
      },
      async fetchFilterData(page = 1){
        if (this.searchField.keywords) {
          let _category = this.searchType;
          let _url = "";
          let searchKeyword = this.searchField.keywords;
          let keyword = encodeURIComponent(searchKeyword);
          _url = `api/v1/autocomplete/search?category=${_category}&keyword=${keyword}&rows=10&page=${page}`;
          const res = await this.$http.get(_url)
          if (res.body.statusCode == 200) {
            const queryData = res.body.message.records;
            let _result = [];
            queryData.map((item) => {
              let _item = this.emphasizeName(item, searchKeyword);
              _result.push([_item, item]);
            });
            this.searchVisible = true;
            this.tableFilterList = _result;
          } else {
            this.searchVisible = false;
            this.tableFilterList = [];
          }
        }
      },
    }
  }
</script>
<style lang="less">
  @import '~assets/less/metaData-nav';
  .nav-container{
    a,
    a:hover {
        text-decoration: none;
    }
    a:focus {
        color: #5a6779;
    }
  }
  .mainType .mainType__li {
      border: none;
  }
  .mainType .mainType__li:hover {
      text-decoration: none;
  }
  .mainType .router-link-active {
      width: 100px;
      background: #FAFAFA;
  }
  .mainType .router-link-active .mainType-box {
      background: #E2E4E9;
  }
  .advance-search-container{
    position: relative;
    display: flex;
    a{
      line-height: 40px;
      margin-left: 20px;
    }
  }
  .nav-search-select{
    width: 80px;
    .el-input input{
      width: 80px;
    }
  }
</style>
