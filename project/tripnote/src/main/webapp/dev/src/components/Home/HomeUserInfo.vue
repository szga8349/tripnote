<template>
  <div class="home-count-list" :style="{borderTop: `1px solid ${userInfo.color}`}">
      <section class="user-info-header">
        <h4>{{userInfo.title}}</h4>
        <div>{{userInfo.label}}</div>
      </section>
      <section class="user-info-footer">
        <div class="loading" v-if="userInfo.count == null"></div>
        <template v-else>
          <span>{{userInfo.count}}</span>
          条
        </template>
      </section>
  </div>
</template>
<script>
  export default{
    name: 'HomeUserInfo',
    props: {
      userInfo: Object,
      loading: Boolean
    },
  }
</script>
<style lang="less">
  .home-count-list{
    flex-grow: 1;
    margin-left: 20px;
    border: 1px solid #e3e8eb;
    padding: 0 10px;
    text-align: center;
    position: relative;
    margin-left: 20px;
    cursor: pointer;
    .user-info-header{
      h4{
        font-size: 15px;
        margin-top: 30px;
      }
      div{
        color: #999999;
        margin-bottom: 30px;
        border-bottom: 1px solid #e2e8e6;
        padding-bottom: 20px;
      }

    }
    .user-info-footer{
      position: absolute;
      bottom: 40px;
      left: 50%;
      width: 100%;
      transform: translateX(-50%);
      color: #014b70;
      .loading{
        width: 16px;
        height: 16px;
        margin: 45px auto 0;
        background: url(../../assets/images/loading_s.gif);
      }
      span{
        font-size: 30px;
        font-family: 'Arial';
      }
    }
  }
</style>

