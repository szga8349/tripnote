<template>
  <ul>
    <li v-for="(modelItem, index) in model">
      <item :folder="modelItem"></item>
    </li>
  </ul>
</template>
<script>
  import Item from './BlackListTreeItem'
  export default{
    name: 'BlackListTreeView',
    beforeCreate(){
      this.$options.components.item = require("./BlackListTreeItem.vue")
    },
    props: {
      model: Array
    },
    computed: {
      addTables() {
        return this.$store.state.addTables
      }
    },
    methods: {
      test(){
        console.log('Hello world')
      }
    }
  }
</script>
<style>

</style>

