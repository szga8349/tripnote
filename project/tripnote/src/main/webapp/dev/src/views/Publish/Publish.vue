<template>
    <div class="publishPage">
        <div class="topBar">
            <router-link :to="{path: '/route/' + routeId}" tag="a" class="backBtn">
            </router-link>
            <div class="typeSel">
                <div class="templateName">{{title}}</div>
            </div>

            <div class="optBtns">
                <a href="javascript:;" @click="backHome">
                    <i class="iconfont icon-home"></i>
                </a>
            </div>
        </div>

        <div class="publishCon">
            <div class="title">
                您的《{{title}}》已发布
            </div>
            <div class="tips">您可以通过以下3种方式，将行程分享给客人</div>

            <div class="types">
                <ul>
                    <li>
                        <div class="icon">
                            <img src="../../assets/images/pdf.png">
                            <p>PDF</p>
                        </div>
                        <div class="des">
                            自动生成PDF文档，可以通过打印或用邮件、QQ
                            微信的方式发送给客人。
                        </div>
                    </li>
                    <li @click="dialogHtmlVisible = true">
                        <div class="icon">
                            <img src="../../assets/images/html.png">
                            <p>HTML</p>
                        </div>
                        <div class="des">
                            自动生成HTML5页面，可以通过复制链接，用QQ
                            微信，邮件发送给客人。
                        </div>
                    </li>
                    <li>
                        <div class="icon">
                            <img src="../../assets/images/pdf.png">
                            <p>Word</p>
                        </div>
                        <div class="des">
                            自动生成Word文档，可以通过打印或用邮件、QQ
                            微信的方式发送给客人。
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <el-dialog title="定制行程HTML5成功生成" :visible.sync="dialogHtmlVisible" width="450px">
            <div class="shareUrl">
                <div class="tit">
                    <i class="iconfont icon-link"></i>分享网址
                </div>
                <div class="tip">
                    复制下方链接，发送给您的客人
                </div>

                <el-input v-model="url"></el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click.native="dialogHtmlVisible = false">取 消</el-button>
                &nbsp;
                <el-button type="primary" @click.native="">复 制</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import { FormatDateWeek } from 'mixins/common'
import Bus from 'utils/bus'

export default {
    data() {
        return {
            title: '',
            dialogHtmlVisible: false,
            url: 'http://www.tripnote.com/trip/share/ew342dde1341'
        }
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setDayScheduleActive: 'setDayScheduleActive',
            setDayPoiEditActive: 'setDayPoiEditActive',
            setRouteLineActive: 'setRouteLineActive'
        }),
        
        routeId(){
            return this.$route.params.routeId
        },
    },
    created(){
        this.getRouteInfo()
    },

    watch: {
    },

    methods: {
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
.publishPage{
    .topBar{
        height: 60px;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #DADEE5;
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
                color: #555;
                &:hover{
                    color: #23a16d;
                }
                i{
                    font-size: 24px;
                }
            }
        }
    }

    .publishCon{
        margin: 0 auto;
        .title{
            width: 800px;
            margin: 20px auto;
            border-bottom: 1px solid #D9DFE9;
            line-height: 100px;
            font-size: 24px;
            text-align: center;
        }
        .tips{
            text-align: center;
            font-size: 14px;
            color: #a0abb3;
        }
        .types{
            width: 945px;
            margin: 0 auto;
            li{
                float: left;
                width: 175px;
                margin: 50px 70px;
                cursor: pointer;
                .icon{
                    height: 210px;
                    padding: 10px 11px 0;
                    background: #fff;
                    box-shadow: 0 5px 15px rgba(0,0,0,.08);
                    &:hover{
                        box-shadow: 0 5px 20px rgba(0,0,0,.2);
                    }
                    p{
                        line-height: 44px;
                        text-align: center;
                        font-size: 18px;
                        font-weight: bold;
                    }
                }
                .des{
                    margin-top: 20px;
                    font-size: 14px;
                    color: #a0abb3;
                }
            }
        }
    }
}
.shareUrl{
    .tit{
        line-height: 24px;
        i{
            margin-right: 3px;
            vertical-align: middle;
            font-size: 20px;
        }
    }
    .tip{
        margin-bottom: 5px;
        line-height: 24px;
        color: #a0abb3;
    }
}
</style>