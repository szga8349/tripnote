<template>
    <div class="wrap">
        <main-nav :user="user"></main-nav>
        <div class="mainCon">
            <main-header :user="user"></main-header>
            <router-view :user="user"></router-view>
        </div>
    </div>
</template>
<script>
import MainNav from './Nav'
import MainHeader from './Header'
export default{
    components: {
        MainNav,
        MainHeader
    },
    data(){
      return {
        user: {}
      }
    },
    created(){
        this.getUserDetail()
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
                this.user = res.data.data
            })
        },
    }
}
</script>
<style lang="less" scope>
.wrap{
    position: absolute;
    width: 100%;
    min-height: 100%;
    background: url(../../assets/images/bg_main.png) 0 0 repeat-y #f2f4f8;
    .mainCon{
        margin-left: 220px;
    }
}
</style>