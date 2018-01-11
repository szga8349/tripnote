<template>
    <div class="wrap">
        <main-nav></main-nav>
        <div class="mainCon">
            <main-header></main-header>
            <router-view></router-view>
        </div>
    </div>
</template>
<script>
import MainNav from './MainNav'
import MainHeader from './MainHeader'
export default{
    components: {
        MainNav,
        MainHeader
    }
}
</script>
<style lang="less" scope>
.wrap{
    position: absolute;
    width: 100%;
    height: 100%;
    background: url(../../assets/images/bg_main.png) 0 0 repeat-y #f2f4f8;
    .mainCon{
        margin-left: 220px;
    }
}
</style>