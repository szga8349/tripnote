<template>
  <div class="clearfix">
    <div class="collection-search">
      <input v-model.trim="searchInput" type="text" name="" placeholder="请输入表名" />
      <div class="search-icon"></div>
    </div>
    <div>
      <table class="table table-bordered tableList">
        <thead>
          <tr>
            <th>表名</th>
            <th>数据类型</th>
            <th>收藏时间</th>
            <th>生命周期</th>
            <th>收藏人气</th>
            <th>收藏</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in computeListData">
            <td>{{item.datasetName}}</td>
            <td>{{item.datasetType}}</td>
            <td>{{item.createdTime}}</td>
            <td>{{item.lifeDay}}</td>
            <td>{{item.favoriteCount}}</td>
            <td>
              <a href="javascript:;" @click="cancelCollect(item.id)">取消收藏</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="page-style">
      <el-pagination background
        layout="total, prev, pager, next"
        :total="totalPage"
        :page-size="10"
        :current-page="currentPage"
        @current-change="changePage"
      >
      </el-pagination>
    </div>
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle">
      <h3 class="warn-title">确认取消收藏?</h3>
      <span slot="footer" class="dialog-footer">
        <el-button @click.native="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click.native="confirmCancel()">确 认</el-button>
      </span>
    </el-dialog>
    <!-- 提示框 -->
		<message v-if="warnVisible"
			@visible="warnVisible = false">
			<p class="msg" slot="content">{{messageContent}}</p>
		</message>
  </div>
</template>
<script>
import Message from 'components/Message';
import { FormatTime } from 'mixins/common'
export default{
  mixins: [ FormatTime ],
  components: {
		Message
	},
  data(){
    return {
      listData: [],
      listUrl: 'datasets/favorites',
      searchInput: '',
      dialogVisible: false,
      dialogTitle: '提示',
      id: '',

      totalPage: 0,
      currentPage: 1,

      warnVisible: false,
      messageContent: ''
    };
  },
  props: {
    dataList: Array,
  },
  created(){
    this.fetchListData();
  },
  methods: {
    cancelCollect(id){
      this.dialogVisible = true;
      this.id = id;
    },
    confirmCancel(){
      const self = this;
      const cancelUrl = `datasets/${this.id}/favorite`;
      this.dialogVisible = false;

      this.$http.delete(cancelUrl)
      .then(function(res){
        if(res.body.statusCode === 200){
          function waitFetchData(){
            return new Promise((resolve, reject) => {
              self.fetchListData(resolve);
            })
          }
          waitFetchData().then(()=>{
            self.warnVisible = true;
            self.messageContent = '删除成功';
          },()=>{
            self.warnVisible = true;
            self.messageContent = '删除失败';
          })

        }
      })
    },
    fetchListData(resolve){
      const listUrl = this.listUrl;
      this.$http.get(listUrl)
      .then((res)=>{
        if(res.body.statusCode === 200){
          this.listData = res.body.message.records;
          this.totalPage = res.body.message.totalRows;
        }
        if(resolve){
          resolve();
        }
      })
    },
    changePage(val) {
      this.currentPage = val;
    }
  },
  computed: {
    computeListData(){
      let listData = this.listData;
      let searchTxt = this.searchInput;
      let self = this;
      if(listData.length > 0){
        listData.forEach(function(v, k){
          v.createdTime = self.formatTime(v.createdTime);
        })
      }
      let newList = listData.filter((v)=>{
        if(searchTxt === ''){
          return true;
        }else if(v.datasetName){
          let datasetName = v.datasetName.toLowerCase();
          let searchContent = searchTxt.toLowerCase();
          return datasetName.indexOf(searchContent) !== -1;
        }else{
          return;
        }
      })
      this.totalPage = newList.length;
      let currentPage = this.currentPage;
      let currentRows = currentPage * 10;
      if(this.totalPage > currentRows){
        return newList.slice((currentPage-1)*10, currentRows);
      }else{
        return newList.slice((currentPage-1)*10, this.totalRows);
      }
    }
  }
}

</script>
<style>
  .collection-search{
    width: 345px;
    height: 30px;
    border: 1px solid #e2e2e2;
    margin: 20px 0;
  }
  .collection-search input{
    float: left;
    width: 285px;
    height: 100%;
    border: 0;
    padding-left: 5px;
  }
  .search-icon{
    float: right;
    width: 55px;
    height: 100%;
    background: url(../assets/images/icon_search.png) center center no-repeat;
    background-size: 20px 20px;
    background-color: #f3f4f5;
  }
</style>
