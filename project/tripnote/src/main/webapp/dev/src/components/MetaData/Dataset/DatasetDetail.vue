<template>
  <!-- v-if="dataType"  -->
    <div class="datasetCon" v-if="dataType">
        <div class="datasetCon" v-if="!hasTreeData">
            <no-data></no-data>
        </div>
        <div class="datasetCon" v-else>
          <div class="title">
            <div class="titShow" style="padding-top: 10px;">
              <el-row type="flex">
                <el-col :span="15">
                  <h4 class="dataset-detail-crumbs">
                    <span>{{$t("content.header.sjj")}}</span>
                    <span class="path">
                        <span> &gt; </span>
                        <span>{{getIsBusiness}}</span>
                    </span>
                    <span class="path" v-for="(item, index) in pathShow">
                        <span v-if="index != pathShow.length-1 && pathType!='hdfs'">
                            <span> &gt; </span>
                            <a href="javascript:;" @click="link(index)">{{item}} </a>
                        </span>
                        <span v-else class="path">
                            <span> &gt; </span>
                            <span v-ellipsis:220="item"></span>
                        </span>
                    </span>
                  </h4>
                </el-col>
                <el-col :span="9" v-if="dataType == 'table'" id="datasetContent2">
                    <header-operation :queryParams="queryParams"></header-operation>
                </el-col>
              </el-row>
            </div>
          </div>
          <!-- <keep-alive> -->
          <component :is="dataType+'-detail'"></component>
          <!-- </keep-alive> -->
        </div>
    </div>
    <loading v-else :isloading="true" isTree="true"></loading>
</template>

<script>
    import DatatypeDetail from './DatasetDetail/DataTypeDetail'
    import DatabaseDetail from './DatasetDetail/DatabaseDetail'
    import DatasourceDetail from './DatasetDetail/DatasourceDetail'
    import TableDetail from './DatasetDetail/TableDetail'
    import BusinessDetail from './DatasetDetail/BusinessDetail'

    import HeaderOperation from './DatasetDetail/HeaderOperation'
    import {Message} from 'mixins/common'
    import Bus from 'utils/bus';
    import {mapState} from 'vuex'

    export default {
        data(){
            return {
                collectActive: false
            }
        },
        mixins: [Message],
        components: {
            DatatypeDetail,     //数据类型详情
            DatasourceDetail,   //数据源详情
            DatabaseDetail,     //数据库详情
            TableDetail,        //表详情
            BusinessDetail,     //业务详情
            HeaderOperation,
        },
        computed: {
            getIsBusiness(){
                return this.$store.state.isBusinessTitle ? this.$t('content.tree.ywst') : this.$t('content.tree.wlst');
            },
            queryDatasetParams(){
              return this.$store.state.viewData
            },
            dataType(){
              return !this.hasTreeData ? 'business' : this.queryDatasetParams.viewActive;
            },//判断是表、库、源、类型
            queryParams(){
                const path = this.$route.query.path ? this.$route.query.path : this.$store.state.fetchRouter.path
                const id = this.$route.query.id ? this.$route.query.id : this.$store.state.fetchRouter.datasetId
                path && path.replace('://', '/')
                return {
                    path,
                    id
                }
            },
            pathShow(){
                let _path = this.queryParams.path
                let path = "";
                if (_path) {
                    path = _path.split('/')
                }
                return path;
            },
            pathType(){
                return this.pathShow.slice(0, 1);
            },
            ...mapState({
                hasTreeData: "hasTreeData"
            })
        },
        created(){
          if(this.$route.name == 'DatasetDetail'){
            this.$store.dispatch('setBusiness', true)
          }else{
            this.$store.dispatch('setBusiness', false)
          }
        },
        methods: {
            link(index){
                const _index = index + 1;
                const query = this.queryDatasetParams.viewNotTable[_index]
                const {path, view} = query;
                const status = view === 'business'
                this.$store.dispatch('setBusiness', status)
                Bus.$emit('refreshTree', {path, datasetId: 0})
            },
        }
    }
</script>
<style scoped>
    .datasetCon {
        position: relative;
    }
    .datasetCon .nodata-container {
        transform: translate(-50%,-50%);
        position: absolute;
        top: 50%;
        left: 50%;
    }
    .dataset-detail-crumbs{
      /*display: flex;
      align-items: center;*/
    }
    .dataset-detail-crumbs>span span{
      margin: 0 1px;
    }
    .dataset-detail-crumbs a{
      line-height: 30px;
    }
    .dataset-detail-crumbs span{
      line-height: 30px;
    }
</style>
