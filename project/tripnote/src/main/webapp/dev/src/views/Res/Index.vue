<template>
    <div class="userPage">
        <div class="topBar">
            <router-link :to="{path: '/main/route'}" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">账号设置</div>
            </div>

            <div class="optBtns">
                <a href="javascript:;" @click="backHome">
                    <i class="iconfont icon-home"></i>
                </a>
            </div>
        </div>

        <div class="userCon">
            <div class="menu">
                <div class="tit">账号设置</div>
                <ul>
                    <router-link :to="{path: '/setting/info'}" tag="li">
                        <i class="iconfont icon-touxiang"></i>基本信息
                    </router-link>
                    <router-link :to="{path: '/setting/changePwd'}" tag="li">
                        <i class="iconfont icon-xiugaimima"></i>修改密码
                    </router-link>
                </ul>
            </div>
            <router-view></router-view>
        </div>

    </div>
</template>
<script>
import {mapState} from 'vuex'

export default {
    data() {
        return {
            
        }
    },

    computed: {
        // ...mapState({
        //     routeInfo: 'routeInfo',
        //     setDayScheduleActive: 'setDayScheduleActive',
        //     setDayPoiEditActive: 'setDayPoiEditActive',
        //     setRouteLineActive: 'setRouteLineActive'
        // }),
        
    },
    created(){
    },

    watch: {
    },

    methods: {
        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },
        getRouteInfo(){
            this.$http({
                method: 'get',
                url: '/tripnote/doDetail/' + this.routeId,
            }).then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.title = res.data.data.title
                }
            })
        },

        backHome(){
            this.$router.push({path: '/main/route'})
        }
    },

    destroyed(){
       
    }
}
</script>
<style lang="less" scope>
.userPage{
    .topBar{
        height: 60px;
        background: #253744;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #4a5e6d;
        }
        .typeSel{
            float: left;
            margin-top: 14px;
            margin-left: 20px;
            line-height: 32px;
            label{
                float: left;
                font-size: 16px;
            }
            .templateType{
                float: left;
                border: 0;
                .el-input{
                    width: 155px;
                }
                .el-input__inner{
                    border: 0;
                    font-size: 16px;
                }
                .el-icon-arrow-up:before{
                    content: "\E60B"
                }
                .el-input .el-select__caret{
                    transform: none;
                }
            }
            .templateName{
                float: left;
                margin-left: 10px;
                font-size: 16px;
                color: #cde0ed;
            }
        }
        .stepInfo{
            position: absolute;
            width: 480px;
            left: 50%;
            margin-left: -300px;
            margin-top: 10px;
            line-height: 32px;
            li{
                &:first-child{
                    &:before{
                        border-radius: 2px 0 0 2px;
                    }
                }
                &:last-child{
                    &:before{
                        border-radius: 0 2px 2px 0;
                    }
                }
                &.active{
                    &:before{
                        background: #23a16d;
                    }
                    span{
                        background: #23a16d;
                    }
                    p{
                        color: #23a16d;
                    }
                }
                position: relative;
                float: left;
                width: 120px;
                &:before{
                    content: '';
                    position: absolute;
                    left: 0;
                    top: 10px;
                    width: 150px;
                    height: 5px;
                    background: #DAE5E6;
                }
                span{
                    display: block;
                    position: relative;
                    z-index: 1;
                    width: 22px;
                    height: 22px;
                    margin: 0 auto;
                    background: #DAE5E6;
                    border-radius: 100%;
                    line-height: 22px;
                    text-align: center;
                    color: #fff;
                }
                p{
                    position: relative;
                    z-index: 1;
                    line-height: 24px;
                    text-align: center;
                    color: #DAE5E6;
                }
            }
        }
        .optBtns{
            float: right;
            height: 60px;
            padding: 0 35px;
            border-left: 1px solid #DADEE5;
            a{
                display: block;
                line-height: 60px;
                color: #fff;
                &:hover{
                    color: #23a16d;
                }
                i{
                    font-size: 24px;
                }
            }
        }
    }
    .userCon{
        width: 1000px;
        margin: 20px auto;
        .menu{
            float: left;
            width: 250px;
            background: #fff;
            padding: 5px 15px 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            .tit{
                line-height: 40px;
                font-size: 16px;
                color: #23a16d;
                border-bottom: 2px solid #23a16d;
            }
            ul{
                margin-top: 10px;
                li{
                    margin: 5px 0;
                    padding-left: 5px;
                    line-height: 36px;
                    font-size: 14px;
                    cursor: pointer;
                    i{
                        margin-right: 5px;
                        &.icon-touxiang{
                            font-size: 18px;
                        }
                    }
                    &.active{
                        background: #F7F8F9;
                        color: #23a16d;
                    }
                }
            }
        }
        .userBox{
            position: relative;
            margin-left: 270px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            .commonTit{
                border-bottom: 1px solid #EAEDF1;
            }

            .userInfo{
                margin-top: 20px;
                .leftBox{
                    float: left;
                    width: 180px;
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
                    width: 400px;
                    margin-left: 200px;
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
                .changePwdBox{
                    width: 300px;
                    overflow: hidden;
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

            .submitWrap{
                maring: 20px 0;
                padding: 20px 0;
                border-top: 1px solid #EAEDF1;
                text-align: center;
            }

            
        }
        
    }
}
</style>