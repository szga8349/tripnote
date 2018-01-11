<template>
  <ul class="home-topic-tree">
    <li v-for="(modelItem, index) in model">
      <item :folder="modelItem">
      </item>
    </li>
  </ul>
</template>
<script>
  export default{
    name: 'TopicTree',
    props: [ 'model' ],
    beforeCreate: function () {
      this.$options.components.item = require("./TopicTreeItem.vue")
    },
  }
</script>
<style lang="less">
.home-topic-tree{
    padding-left: 20px;
    background: #fff;
    z-index: 1;
    li{
      a{
        padding-left: 2020px !important;
        margin-left: -2020px !important;
        display: inline-block;
        height: 100%;
        width: 2500px;
        text-decoration: none;
      }
      a:hover{
        background: #eef6f6;
      }
    }
    ul{
      padding-left: 20px;
    }
  }
</style>

