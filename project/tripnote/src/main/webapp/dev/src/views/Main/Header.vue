<template>
    <div class="mainHeader">
        <div class="toggle"></div>
        <div class="searchBox">
            <i class="iconMapPoint"></i>
            <i class="el-icon-caret-bottom"></i>
            <input type="text" class="keywords" placeholder="搜索关键字">
            <a href="javascript:;" class="btnSearch"></a>
        </div>
        
        <div class="optsBox">
            <div class="changeLanguage">
                English
                <i class="el-icon-caret-bottom"></i>
            </div>

            <div class="msg">
                <span class="num">3</span>
            </div>

            <div class="user">
                <div class="name">
                    <span class="tit">Danior</span>
                    <i class="el-icon-caret-bottom"></i>
                    <div class="menu">
                        <ul>
                            <li><i class="iconfont icon-zhanghaoshezhi"></i>账号设置</li>
                            <li @click="logout"><i class="iconfont icon-tuichu"></i>退出账号</li>
                        </ul>
                    </div>
                </div>
                <div class="avatar">
                    <img src="../../assets/images/avatar.png" width="42px" height="42px">
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  import Vue from 'vue'
  export default{
    name: 'MainHeader',
    data(){
      return {
        
      }
    },
    computed: {
      
    },
    watch: {
      
    },
    created(){
      // this.testSourceAuthority()
    },
    methods: {
        logout(){
            this.$http({
                method: 'POST',
                url: '/tripnote/login/logout'
            })
            .then((res)=>{
                if(res.data.code != 1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    });
                }else{
                    this.$router.push({name: 'Login'})
                }
            })
        }
      // async testSourceAuthority(){
      //   const res = await this.$http.get('/space/init');
      //   const {statusCode, message} = res.body;
      //   if (statusCode === 200) {
      //     if (message.roleLevel !== 0 || message.projectList.length !== 0) {
      //       this.mainNav.push({
      //         name: this.$t("content.nav.cckj"),
      //         icon: 'icon8',
      //         route: {name: 'Source'}
      //       });
      //       message.roleLevel === 1 && message.projectList.unshift({id: '', name: this.$t('content.header.qb')});
      //       this.$store.dispatch('sourceUserMessage', message);
      //     }
      //   }
      // }
    }
  }
</script>
<style lang="less" scope>
.mainHeader{
    height: 60px;
    background: #fff;
    box-shadow: 0 3px 10px rgba(0,0,0,.04);
    .toggle{
        float: left;
        width: 70px;
        height: 60px;
        background: url(../../assets/images/icon_main_nav_toggle.png) center center no-repeat;
        border-right: 1px solid #ebebeb;
        cursor: pointer;
    }
    .searchBox{
        float: left;
        width: 270px;
        height: 36px;
        margin-top: 12px;
        margin-left: 18px;
        background: #EDF1F2;
        border-radius: 18px;
        .iconMapPoint{
            float: left;
            width: 16px;
            height: 18px;
            margin: 9px 0 0 15px;
            background: url(../../assets/images/icon_map_point.png);
        }
        .el-icon-caret-bottom{
            float: left;
            margin: 12px 0 0 5px;
            color: #9FA5B0;
        }
        .keywords{ 
            float: left;
            width: 170px;
            height: 36px;
            margin-left: 10px;
            background: none;
            border: 0;
            line-height: 36px;
            font-size: 13px;
        }
        .btnSearch{
            float: right;
            width: 42px;
            height: 36px;
            background: url(../../assets/images/icon_search.png) 10px center no-repeat;
        }
    }
    .optsBox{
        float: right;
    }
    .changeLanguage{
        float: left;
        padding: 0 22px;
        line-height: 60px;
    }
    .msg{
        float: left;
        width: 90px;
        height: 60px;
        background: url(../../assets/images/icon_msg.png) center center no-repeat;
        .num{
            float: left;
            height: 16px;
            margin-left: 44px;
            margin-top: 14px;
            padding: 0 6px;
            line-height: 16px;
            background: #F83B3B;
            border-radius: 16px;
            color: #fff;
        }
    }
    .user{
        float: left;
        padding: 0 22px;
        line-height: 60px;
        font-size: 14px;
        .name{
            position: relative;
            float: left;
            .menu{
                display: none;
                position: absolute;
                z-index: 100;
                top: 50px;
                right: 0;
                width: 135px;
                padding: 12px 0;
                background: #fff;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                li{
                    overflow: hidden;
                    line-height: 36px;
                    padding-left: 18px;
                    cursor: pointer;
                    i{
                        display: inline-block;
                        vertical-align: middle;
                        margin-top: -3px;
                        margin-right: 12px;
                    }
                    &:hover{
                        background: #E9F1F1;
                    }
                }
            }
            &:hover{
                .menu{
                    display: block;
                }
            }
        }
        .avatar{
            float: left;
            height: 42px;
            margin-left: 10px;
            margin-top: 9px;
            img{
                display: block;
            }
        }
    }

}
</style>
