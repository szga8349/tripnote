<template>
    <div>
        <div class="userBox commonBox">
            <div class="commonTit">
                <h2>基本信息</h2> 
            </div>

            <div class="userInfo">
                <div class="leftBox">
                    <div class="avatar">
                        <div class="pic" :style="{backgroundImage: `url(${imgFormat(form.imageurl)})`}"></div>
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
                    <el-form ref="form" :model="form" label-width="120px" class="customerForm">
                        <el-form-item label="姓名:">
                            <el-input v-model="form.realName"></el-input>
                        </el-form-item>
                        <el-form-item label="性别:">
                            <el-radio v-model="form.sex" label="1">男</el-radio>
                            <el-radio v-model="form.sex" label="0">女</el-radio>
                        </el-form-item>
                        <el-form-item label="手机号:">
                            <el-input v-model="form.phone"></el-input>
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
                        <el-form-item label="精通语言:">
                            <el-input v-model="form.constellation"></el-input>
                        </el-form-item>
                        <el-form-item label="职业:">
                            <el-input v-model="form.occupation"></el-input>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

            <div class="submitWrap">
                <el-button type="primary" @click="doUpdate">保 存</el-button>
                <el-button type="default">取 消</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
	data() {
		return {
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
        }
    },
    computed: {
        // ...mapState({
        //     loginSilderInit: 'loginSilderInit',
        // }),
    },

    watch: {
        'form.birthdayEdit'(val){
            this.form.birthday = moment(val).format('YYYY-MM-DD')
        }
    },
    created(){
        // var _lastUser = this.getCookie('lastUser')
        // if(_lastUser){
        //     this.loginPhone = _lastUser
        //     this.loginPwd = this.getCookie('user_' + _lastUser)
        //     this.rememberPwd = true
        // }
        this.getUserDetail()
    },
	mounted() {
        
	},
	methods: {
        getUserDetail(){
            this.$http({
                method: 'post',
                url: '/user/doDetail',
                // data: {
                //     indexdates: JSON.stringify({"indexdates":_data}),
                // }
            })
            .then((res)=>{
                console.log(res.data.data)
                this.form = res.data.data
            })
        },

        // 设置cookie
        setCookie: function (cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toUTCString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
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

        //清除cookie
        clearCookie: function () {
            this.setCookie("username", "", -1);
        },

        checkCookie: function () {
            var user = this.getCookie("username");
            if (user != "") {
                alert("Welcome again " + user);
            } else {
                user = prompt("Please enter your name:", "");
                if (user != "" && user != null) {
                    this.setCookie("username", user, 365);
                }
            }
        },


        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        handleAvatarSuccess(res, file) {
            this.form.imageurl = res.link
        },

        doUpdate(){
            this.$http({
                method: 'POST',
                url: '/user/doUpdate',
                data: this.form
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.$message({
                        message: '修改成功！',
                        type: 'success',
                        duration: 2000
                    });
                }
            })
        }

        
	},
    destroyed(){
    }
}
</script>
