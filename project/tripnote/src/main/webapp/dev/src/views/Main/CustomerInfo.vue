<template>
    <div>
        <div class="commonBox">
            <div class="commonTit">
                <h2>
                    <router-link tag="a" :to="{path: '/main/customer'}">客户管理库</router-link>
                    >> {{pageTit}}
                </h2>
                <div class="rightOpts">
                    <el-button type="primary" icon="el-icon-plus" @click="saveCustomer('form')">保存</el-button>
                </div>
            </div>

            <div class="commonCon customerInfoBox">
                <div class="leftBox">
                    <div class="avatar">
                        <div class="pic" :style="{backgroundImage: `url(${imgFormat(form.portraiturl)})`}"></div>
                    </div>
                    <el-upload
                        class="uploadCustomerPic"
                        action="/tripnote/common/upload/customer/image"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        >
                        <el-button size="small" type="primary">上传头像<i class="el-icon-edit-outline el-icon--right"></i></el-button>
                    </el-upload>
                </div>
                <div class="rightBox">
                    <el-form ref="form" :model="form" :rules="rules" label-width="120px" class="customerForm">
                        <el-form-item label="姓名:" required prop="name">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <!-- <el-form-item label="分组:">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item> -->
                        <el-form-item label="性别:">
                            <el-radio v-model="form.sex" label="1">男</el-radio>
                            <el-radio v-model="form.sex" label="0">女</el-radio>
                        </el-form-item>
                        <el-form-item label="手机号:" required prop="phone1">
                            <el-input v-model="form.phone1"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱地址:">
                            <el-input v-model="form.email"></el-input>
                        </el-form-item>
                        <el-form-item label="生日:">
                            <el-date-picker
                                v-model="form.birthdayEdit"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="星座:">
                            <el-input v-model="form.constellation" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号:">
                            <el-input v-model="form.idcard"></el-input>
                        </el-form-item>
                        <el-form-item label="血型:">
                            <el-select v-model="form.bloodType" placeholder="请选择">
                                <el-option label="A型" value="1"></el-option>
                                <el-option label="B型" value="2"></el-option>
                                <el-option label="AB型" value="3"></el-option>
                                <el-option label="O型" value="4"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="职业:">
                            <el-input v-model="form.occupation"></el-input>
                        </el-form-item>
                        <!-- <el-form-item label="家庭成员:">
                            <el-input v-model="form.family"></el-input>
                        </el-form-item> -->
                        <el-form-item label="所在城市:">
                            <el-input v-model="form.address"></el-input>
                        </el-form-item>
                        <el-form-item label="可接受价格:">
                            <el-select v-model="form.acceptprice" placeholder="请选择">
                                <el-option label="1000元-8000元" value="1"></el-option>
                                <el-option label="8000元-15000元" value="2"></el-option>
                                <el-option label="15000元-30000元" value="3"></el-option>
                                <el-option label="30000元及以上" value="4"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="最感兴趣的地方:">
                            <el-input v-model="form.interestSpace"></el-input>
                        </el-form-item>
                        <el-form-item label="喜欢旅游的时段:">
                            <el-select v-model="form.interestTime" placeholder="请选择">
                                <el-option label="元旦期间" value="1"></el-option>
                                <el-option label="春节期间" value="2"></el-option>
                                <el-option label="五一黄金周" value="3"></el-option>
                                <el-option label="十一黄金周" value="4"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="备注:">
                            <el-input type="textarea" :rows="3" v-model="form.remark" ></el-input>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

        </div>

        <el-dialog title="提示" :visible.sync="dialogDelTip" width="450px">
            <div class="delTipCon">
                <p>您确定要删除该分组吗？</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="dialogDelTip = false">取消</el-button>
                <el-button type="primary" @click.native="delGroup">确定</el-button>
            </div>
        </el-dialog>

    </div>
</template>
<script>
import moment from 'moment'
import { FormatTime } from 'mixins/common'

export default {
    mixins: [ FormatTime ],
    components: {
    },
    watch: {
        'form.birthdayEdit'(val){
            this.form.birthday = moment(val).format('YYYY-MM-DD')
            var day = moment(val).format('MM-DD').split('-')

            function getAstro(m,d){
              return "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯".substr(m*2-(d<"102223444433".charAt(m-1)- -19)*2,2);
            }
            this.form.constellation = getAstro(day[0],day[1]) + '座'
        }
    },

    data() {
        return {
            pageTit: '新建用户',
            dialogDelTip: false,
            delGroupId: '',
            addGroupActive: false,
            letterList: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
            groupList: [],
            addGroupName: '',
            username: '',
            tableData: [],
            tableDataLoading: true,
            pageNo: 1,
            pageSize: 5,
            total: 0,
            toggleGroupOpen: true,

            form: {
                // "id": '',
                "name": '',
                "importance": '',
                "status": '',
                "sex": '1',
                "address": '',
                "birthday": '',
                "idcard": '',
                "constellation": '',
                "phone1": '',
                "phone2": '',
                "phone3": '',
                "bloodType": '',
                "occupation": '',
                "email": '',
                "qq": '',
                "acceptprice": '',
                "arriveSpace": '',
                "interestSpace": '',
                "weixin": '',
                "catogryId": '',
                "portraiturl": '',
                "remark": '',
                "createUserId": '',
                "family": '',
                interestTime: ''
            },
            rules: {
              name: [
                { required: true, message: '请输入姓名', trigger: 'blur' },
              ],
              phone1: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
              ]
            },

        }
    },
    created(){
        this.username = this.getCookie('username')
        if(this.customerId){
            this.pageTit = '编辑用户'
            this.getCustomerDetail()
        }
        // this.getGroupList()
    },
    computed: {
        customerId(){
            return this.$route.params.customerId
        },
        groupId(){
            return this.$route.params.customerId
        },
    },
    methods: {
         // 获取行程列表
        getGroupList(){
            this.$http({
                method: 'POST',
                url: '/customer/catogry/doSearch',
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    var _groupList = res.data.data
                    this.$http({
                        method: 'POST',
                        url: '/customer/catogry/doStatistic',
                    })
                    .then((res)=>{
                        if(res.data.code == -1){
                            this.$message({
                                message: res.data.message,
                                type: 'error',
                                duration: 2000
                            });
                        }else{
                            var _idNum = {}
                            for (var i = 0; i < res.data.data.length; i++) {
                                _idNum[res.data.data[i].id] = res.data.data[i].count
                            }
                            var _countAll = 0
                            for (var i = 0; i < _groupList.length; i++) {
                                _groupList[i]['editable'] = 'done'
                                _groupList[i]['nameEdit'] = _groupList[i].name
                                if(_idNum[_groupList[i].id]){
                                    _groupList[i]['count'] = _idNum[_groupList[i].id]
                                    _countAll += _idNum[_groupList[i].id]
                                }
                            }
                            this.groupList = _groupList
                        }
                    })
                }
            })
        },


        handleAvatarSuccess(res, file) {
            this.form.portraiturl = res.link
        },

        // 获取cookie
        getCookie: function (cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1);
                if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
            }
            return "";
        },


        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        // 保存
        getCustomerDetail(){
            this.$http({
                method: 'POST',
                url: '/customer/doDetail/' + this.customerId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    Object.assign(this.form, res.data.data)
                    console.log(this.form)
                }
            })
        },

        // 保存
        saveCustomer(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var vm = this
                    var _url = '/customer/doAdd'
                    if(this.customerId){
                        // this.form['id'] = this.customerId
                        _url = '/customer/doUpdate/' + this.customerId
                    }
                    this.$http({
                        method: 'POST',
                        url: _url,
                        data: this.form
                    })
                    .then((res)=>{
                        vm.tableDataLoading = false
                        if(res.data.code == -1){
                            this.$message({
                                message: res.data.message,
                                type: 'error',
                                duration: 2000
                            });
                        }else{
                            this.$message({
                                message: this.customerId ? '修改成功！' : '添加成功！',
                                type: 'success',
                                duration: 2000
                            });
                            this.$router.push({path: '/main/customer'})
                        }
                    })
                }
            });
        },

        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/icon_avatar_default.png')
            }
        },

    },
}
</script>
<style lang="less">
.customerInfoBox{
    position: relative;
    margin-top: 20px;
    .leftBox{
        position: absolute;
        left: 0;
        top: 0;
        width: 220px;
        padding: 5px 10px;
        .avatar{
            width: 180px;
            padding: 10px;
            margin: 0 auto;
            box-shadow: 0 0 8px rgba(0,0,0,.15);
            .pic{
                width: 160px;
                height: 160px;
                background-size: cover;
                background-position: center;
            }
        }
        .uploadCustomerPic{
            text-align: center;
            margin-top: 15px;
            .el-button{
                background: none;
                border: 0;
                color: #a0abb3;
            }
        }
    }
    .rightBox{
        overflow: hidden;
        width: 600px;
        margin-left: 220px;
        .optBtns{
            margin-bottom: 10px;
            overflow: hidden;
            .btns{
                float: left;
            }
            .searchBox{
                float: right;
                width: 210px;
                .el-input__inner{
                    border-radius: 16px;
                }
            }
        }
        .customerForm{
            .el-form-item__label{
                color: #a0abb3;
            }
        }
        
    }
}
</style>