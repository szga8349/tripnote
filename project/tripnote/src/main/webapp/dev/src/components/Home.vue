<template>
  <div>
    <h1>所有表</h1>
    <ul v-for="table in tableList">
        <li>{{ table }}</li>
    </ul>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        tableList: []
      }
    },
    // computed: {
    //   evenNumbers: function () {
    //     return this.tableList.filter(function (number) {
    //       return number % 2 === 0
    //     })
    //   }
    // }
    created () {
      this.fetchData()
    },
    methods: {
      fetchData () {
        this.$http.get('static/data/tables.json')
        .then(function (ret) {
          // state.timeEntries = ret.data
          let data = ret.body.tables.splice(0, 10);
          console.log(data)

          this.tableList = data;
        })
        .then(function (err) {
          console.log(err)
        })
      }
    }
  }
</script>

<style scoped>
</style>
