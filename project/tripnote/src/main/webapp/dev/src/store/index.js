import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations';
import actions from './actions';

Vue.use(Vuex);
let view = window.sessionStorage.getItem('viewActive');

const state = {
    loginSilderInit: false, // 登录页面slider初始化
    routeInfo: {}, // 行程详情
    setRouteLineActive: false, // 安排行程激活状态
    setDayScheduleActive: false, // 设置日程激活状态












    username: '', //用户名
    selectPath: '',
    datasetKeyword: '', //存储dataset搜索关键字
    flowKeyword: '', //存储flow搜索关键字
    saveUploadDate: '', //上传文件
    treeItemActive: {
        path: null,
        datasetId: null
    }, // 当前被选中
    defaultSort: '',
    viewData: {
        viewNotTable: [null],
        viewTable: {
            id: "",
            path: ""
        },
        viewActive: view ? view : 'business'
    },
    queryFlow: {
        process: {
            path: ""
        },
        task: {
            path: "",
            id: ""
        },
        isProcess: false
    }, //保存process 请求对象
    selectItem: "", //选择要移动到的项目
    checkList: [],
    isBusiness: true,
    isBusinessTitle: false,
    themeCheckList: [],
    headerNav: [], //header导航
    sourceUserMessage: {},
    sourceTitle: '',
    fetchRouter: {},
    language: '',
    selectItems: [],//权限申请
    exportList: [],//导出元数据表,
    exportDBList: [],//导出元数据库,
    tableName: '',
    businessState: {},
    hasTreeData: false,
    fetchTreeDataEndRequest: false,
    importPrivilege: false,
    dataStatus: 0,
    exportSearh: '',
    treeLoading: false, //树状图loading
    treeBusinessLoading: false, //物理视图loading
    collectCount:0,
    fullLoading: false,
    fullTreeLoading: false,
    saveRouter:'',
    navIsData: true,
    isPrivate: 0,
    propEditPrivilege: false,
    setSourceLink: false,
    createTask: {},
    addTables: [],
    saveBlackList: [],
    getSample: false,
    searchTable: [],
    cronValue: '* * * * * *',
    closeDialog: false,
    showCron: '',
    selectTables: [],
    exportTableSource: {},
    testBlackList: [],
    saveSelectList: [],
    setSearchParams: {},
    tableTypeOpts: '0,1,2', // 左侧菜单过滤条件
    modelOpen: false,
    manageInfo: {},
    userInfo: {},
    ownerActiveTab: {component: 'PersonalOwnerTable', title: '维护的所有表'}
}

export default new Vuex.Store({
    state,
    mutations,
    actions
});
