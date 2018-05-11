<template>
    <div>
        <div class="userBox commonBox">
            <div class="commonTit">
                <h2>修改密码</h2> 
            </div>

            <div class="userInfo">
                <div class="changePwdBox">
                    <el-form ref="form" :model="form" label-width="120px" class="customerForm">
                        <el-form-item label="当前密码:">
                            <el-input v-model="form.oldPwd" type="password"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码:">
                            <el-input v-model="form.newPwd" type="password"></el-input>
                        </el-form-item>
                        <el-form-item label="确认密码:">
                            <el-input v-model="form.newPwd1" type="password"></el-input>
                        </el-form-item>
                    </el-form>
                    
                </div>

                <div class="submitWrap">
                    <el-button type="primary" @click="doUpdate">保 存</el-button>
                    <el-button type="default">取 消</el-button>
                </div>
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
                oldPwd: '',
                newPwd: '',
                newPwd1: '',
            },
        }
    },
    computed: {
        // ...mapState({
        //     loginSilderInit: 'loginSilderInit',
        // }),
    },

    methods: {
        doUpdate(){
            if(this.form.newPwd != this.form.newPwd1){
                this.$message({
                    message: '两次输入的密码不一致！',
                    type: 'error',
                    duration: 2000
                });
                return
            }
            this.$http({
                method: 'POST',
                url: '/user/doCheckPasswd',
                data: {
                    passwd: this.form.oldPwd
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: '当前密码不正确！',
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.$http({
                        method: 'POST',
                        url: '/user/doUpdate',
                        data: {
                            loginPassword: this.form.newPwd
                        }
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
            })
        }        
    },
    destroyed(){
    }
}
</script>
