<template>
    <div class="box">
        <div id="MainCon">
            <div class="personal-list">
                <split-controler
                        :isOpenTree="isOpenTree"
                        :controlerLeft="controlerLeft"
                        @setTreeControler="treeControl"></split-controler>
                <h4>
                    <i class="icon iconData"></i>{{$t('content.personal.shuj')}}
                </h4>
                <ul>
                    <li v-for="(item,index) in personalDataList"
                        :class="{active: selectType == index}"
                        @click="selectItem(index)">
                        <i :class="`personal_icon${item.id}`"></i>
                        {{item.name}}
                        <span class="personal-count" v-if="item.count > -1">
                            <span>({{item.count}})</span>
                        </span>
                    </li>
                </ul>
                <h4><i class="icon iconApply"></i>{{$t('content.personal.shenq')}}</h4>
                <ul>
                    <!-- 设计 -->
                    <li
                        v-for="(item, key) in applyList"
                        @click="selectItem(key)"
                        :class="{active: selectType == key}"
                    >
                        <i :class="`personal_icon${item.id}`"></i>
                        {{item.name}}
                        <span class="personal-count" v-if="item.count > -1">
                            <span>({{item.count}})</span>
                        </span>
                    </li>
                </ul>
                <!-- 元数据变更 -->
                <h4><i class="icon iconChange"></i>{{$t('content.personal.ysjbg')}}</h4>
                <ul>
                    <li
                        v-for="(item, key) in delList"
                        @click="selectItem(key)"
                        :class="{active: selectType == key}">
                        <i :class="`personal_icon${item.id}`"></i>
                        {{item.name}}
                        <span class="personal-count" v-if="item.count > -1">
                            <span>({{item.count}})</span>
                        </span>
                    </li>
                </ul>
            </div>
            <div class="content datasetCon">
                <div class="title">
                    <div class="titShow">
                        <h4 class="search-result-title">
                            {{$t('content.personal.gldb')}}
                            <span class="path">></span>
                            <span class="path">{{showTitle}}</span>
                        </h4>
                    </div>
                </div>
                <div v-if="$route.name == 'Owner'"
                     class="tabs owner-table-top">
                    <ul>
                        <li
                            v-for="item in ownerTabList"
                            :class="{active: item.tab == ownerActiveTab.component}"
                            @click="$store.dispatch('ownerActiveTab', {component: item.tab, title: item.label})">
                            {{item.label}}
                            <i class="count-badge">1</i>
                        </li>
                    </ul>
                    <span class="tip-content" v-show="tipContent">
                        <i class="iconfont icon-bangzhu"></i>
                        {{tipContent}}
                    </span>
                </div>

                <div v-if="$route.name == 'Approvals'"
                     class="tabs owner-table-top">
                    <ul>
                        <li
                            v-for="item in approvalsTabList"
                            :class="{active: item.tab == approvalsActiveTab.component}"
                            @click="approvalsActiveTab = {component: item.tab, title: item.label}">
                            {{item.label}}
                        </li>
                    </ul>
                    <span class="tip-content" v-show="tipContent">
                        <i class="iconfont icon-bangzhu"></i>
                        {{tipContent}}
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
    import {windowSplitter} from 'mixins/common'
    import Bus from 'utils/bus'
    export default{
        components: {
            SplitControler,
        },
        mixins: [windowSplitter],
        data: function () {
            return {
                personalDataList: {
                    'Owner': {name: this.$t('content.personal.gldsj'), id: 2, count: -1},
                    'Authority': {name: this.$t('content.personal.yqxdsj'), id: 3, count: -1},
                    'Collect': {name: this.$t('content.personal.scdsj'), id: 1, count: -1},
                },
                applyList: {
                    'Applys': {name: this.$t('content.personal.sqjl'), id: 4, count: -1},
                    'Approvals': {name: this.$t('content.personal.qxsp'), id: 5, count: -1},
                },
                delList: {
                    'Deleted': { name: this.$t('content.personal.yscdysj'), id: 6, count: -1}
                },
                selectType: 'Owner',
                isOpenTree: true,
                controlerLeft: 310,
                ownerTabList: [
                    { tab: 'PersonalOwnerTable', label: '维护的所有表' },
                    { tab: 'PersonalChargeMaintain', label: '负责人信息待维护' },
                    { tab: 'PersonalMetaMaintain', label: '元信息待维护' },
                ],
                approvalsTabList: [
                    { tab: 'approvalsPending', label: '待审批' },
                    { tab: 'approvalsFinish', label: '已审批' }
                ],
                approvalsActiveTab: {component: 'approvalsPending', title: '待审批'},
            }
        },
        computed: {
            tipContent(){
                let content = ''
                switch(this.ownerActiveTab.title){
                    case '元信息待维护':
                        content = '当前账号下负责的表，业务元数据信息不完善，请点击操作完善信息'
                        break;
                    case '负责人信息待维护':
                        content = '请到操作里设置业务负责人，业务负责人负责该表的元信息维护'
                        break;
                    default:
                        content = ''
                }
                return content
            },
            showTitle(){
                if (this.selectType === 'Applys') {
                    return this.$t('content.personal.sqjl')
                }
                if(this.selectType == 'Approvals'){
                    return this.$t('content.personal.qxsp')
                }
                if(this.selectType === 'Deleted'){
                    return this.$t('content.personal.yscdysj')
                }
                return this.personalDataList[this.selectType].name
            },
            ownerActiveTab(){
                return this.$store.state.ownerActiveTab
            }
        },
        created () {
            // this.$router.push({name: this.selectType})
            Bus.$off('fetchCount').$on('fetchCount', this.fetchCount)
            this.fetchCount()
        },
        mounted(){
            let self = this;
            const splitter = self.treeControlInit();
            $(window).scroll(function () {
                const scrollLeft = $(window).scrollLeft();
                let splitterPosition;
                if (self.isOpenTree) {
                    splitterPosition = 310;
                } else {
                    splitterPosition = 60;
                }
                self.controlerLeft = splitterPosition - scrollLeft;
            });
            //其他页面跳转过来的active
            let _name = self.$route.name;
            if (_name !== "Personal") {
                self.selectType = _name
            }
        },
        watch: {
            // 如果路由有变化，会再次执行该方法
            $route (val) {
                let type = this.$route.fullPath.split('/')[4] || 'Owner';
                this.selectItem(type);
            },
        },
        methods: {
          fetchCount(){
            const vm = this
            this.$http.get('/datasets/getPersonalDataCount').then(res=>{
              const {statusCode, message} = res.body
              const keys = statusCode == 200 ? Object.keys(message) : []
              this.$store.dispatch('setManageInfo', message)
              for(let key of keys){
                if(key == 'Owner' || key == 'Authority' || key == 'Collect'){
                    vm.personalDataList[key].count = message[key]
                }else if(key == 'Applys' || key == 'Approvals'){
                    vm.applyList[key].count = message[key]
                }else{
                    vm.delList[key].count = message[key]
                }
              }
              this.$store.dispatch('saveCollectCount', this.personalDataList.Collect.count)
            })
          },
          selectItem(type){
            this.selectType = type
            this.$router.push({name: this.selectType})
          },
          treeControl(){
            this.isOpenTree = !this.isOpenTree;
            let _left = this.isOpenTree ? "310" : '60';
            $('#MainCon').split().destroy();
            this.controlerLeft = _left - $(window).scrollLeft()
            this.treeControlInit(_left - 60);
          },
          resetPosition(){
            let leftPosition = this.splitter.position();
            this.controlerLeft = leftPosition + 60;
          },
        }

    }
</script>
<style lang="less">
    .personal-count{
        position: absolute;
        right: 27px;
        color: #ccc;
    }
    .data-history{
        li i{
            color: #014b70;
            background: none;
            font-size: 16px;
        }
        .active i{
            color: #fff;
        }
    }
    .tip-content{
        line-height: 40px;
        margin-right: 25px;
        color: #999;
        i{
            vertical-align: central;
        }
    }
    .owner-table-top{
        display: flex;
        justify-content: space-between;
        position: relative;
        li{
            position: relative;
            .count-badge{
                position: absolute;
                right: 3px;
                top: 10px;
                height: 16px;
                line-height: 16px;
                font-style: normal;
                font-size: 12px;
                padding: 0 5px;
                border-radius: 7px;
                background: #fb7775;
                color: #fff;
            }
        }
    }
</style>
