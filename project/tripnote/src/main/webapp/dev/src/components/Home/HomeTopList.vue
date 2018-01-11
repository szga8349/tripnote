<template>
  <section>
    <h4>{{item.title}}</h4>
    <div v-if="!item.loading">
      <ul v-if="item.list.length > 0" style="font-size: 0;">
        <li v-for="(title, index) in item.list">
          <i :class="`listNum listNum${++index}`">{{index}}</i>
          <span>{{title}}</span>
        </li>
      </ul>
      <no-data :noDataVisible="item.list.length == 0"></no-data>
    </div>
    <loading :isloading="item.loading"></loading>
  </section>
</template>
<script>
  export default{
    props: {
      item: [Object]
    },
    created () {
    }
  }
</script>
