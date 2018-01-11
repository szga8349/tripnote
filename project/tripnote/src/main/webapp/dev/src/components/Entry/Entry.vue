<template>
  <div class="content-main">
        <div class="content">
            <div class="user-welcome">欢迎，{{username}}</div>
            <div class="use-project">
                <div class="header-til">
                    <h3>使用中的产品</h3>
                </div>
                <ul class="pro-item clearfix">
                    <li v-for="item in entryNav">
                        <router-link :to="item.href" v-if="item.icon ==='icon-ysj'">
                            <span class="icon" :class="item.icon"></span>
                            <h4>{{item.title}}</h4>
                            <p>{{item.content}}</p>
                        </router-link>
                        <a :href="item.href" v-else>
                            <span class="icon" :class="item.icon"></span>
                            <h4>{{item.title}}</h4>
                            <p>{{item.content}}</p>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="copyright">Copyright:1998-2016 Lenovo Group</div>
        </div>
    </div>
</template>
<script>

  export default{
    name:'Entry',
    data(){
        return{
            entryArr:[{
                    title:'查询分析器',
                    icon:'icon-cxfx',
                    content:'交互式查询工具，支持SQL,R,Python等语言。'
                },{
                    title:'ETL开发',
                    icon:'icon-etl',
                    content:'可视化的数据处理任务开发，支持实时，批量等多种类型的任务调度。'
                },{
                    title:'任务调度',
                    icon:'icon-rwdd',
                    content:'定时数据任务开发，支持任务状态监控及管理任务执行。'
                },{
                    title:'元数据管理',
                    icon:'icon-ysj',
                    content:'对数据链路及质量过程，数据标准及安全多方位展示及追踪，支持数据资产的可视化管控。'
                },{
                    title:'数据集成',
                    icon:'icon-sjjc',
                    content:'数据无缝传输和迁移，支持数据库、本地文件、第三方云平台等多种源类型的数据迁移。'
                }
            ]
        }
    },
    computed: {
      username(){
            return this.$store.state.username || window.sessionStorage.getItem('username')
        },
      headerNav(){
            return this.$store.state.headerNav;
        },
     entryNav(){
        const headerNavMap = new Map(this.headerNav);
        this.entryArr.forEach((val)=>{
            const title = val.title;
            let href = "";
            if(headerNavMap.has(title)){
                href = headerNavMap.get(title)
            }
            this.$set(val,'href',href);
        })
        return this.entryArr;
      }
    },
  }
</script>
<style scoped lang="less">
@import '~assets/less/Entry.less';
</style>
