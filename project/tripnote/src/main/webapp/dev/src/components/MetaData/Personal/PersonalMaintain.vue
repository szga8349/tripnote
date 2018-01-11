<template>
  <div class="boxCommon owner-table">
    <div class="table-title">
      <div class="table-title__text">
        <span>待维护的表</span>
      </div>
      <div class="table-title__btns">
        <Search
        :placeholder="$t('content.validator.qsrbm')"
        emitName="ownerSearch"
        @ownerSearch="fetchData"></Search>
      </div>
    </div>
    <table class="table table-bordered tableList">
        <table-header
            :tableHeader = "tableHeader"
            :sort = "sort"
            :order = "order"
            @tableSort="tableSort"
          >
            <th>操作</th>
          </table-header>
          <tbody>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td>
                <a href="javascript:;">设置业务负责人</a>
              </td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td>
                <a href="javascript:;">去详情页编辑</a>
              </td>
            </tr>
          </tbody>
    </table>
    <div class="tablePages">
        <el-pagination background
        layout="total, prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="totalRows"
        >
        </el-pagination>
      </div>
  </div>
</template>
<script>
  export default{
    name: 'PersonalMaintain',
    data(){
      return{
        tableHeader: [
          {
              name: '表名',
          },
          {
              name: '表类型',
          },
          {
              name: '所属项目',
          },
          {
              name: '创建时间',
          },
          {
              name: '生命周期',
          },
          {
              name: '存储大小',
          },
          {
              name: '业务负责人',
          },
        ],
        currentPage: 1,
        pageSize: 10,
        totalRows: 1,
      }
    },
    methods: {

    }
  }
</script>
<style>
</style>
