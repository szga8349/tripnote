<template>
    <div class="columnWrap routeOverview">
        <div class="columnBox">
            <div class="header">
                <div class="tit"><i class="iconfont icon-wenjian"></i>行程备注</div>
                <div class="opts">
                    <a href="">
                        <i class="iconfont icon-bianji-blue"></i>
                    </a>
                </div>
            </div>

            <div class="content">
                今天是旅行的第一天，您将全程乘坐土耳其航空，于7月15日00:50从北京国际机场T3航站楼出发，请提前两小时抵达北京国际机场T3航站楼，办理登机及出境事宜。抵达土耳其伊斯坦布尔后在同一航站楼转机，飞机准点到达的情况下转机时间为1小时35分钟，最终于巴黎时间09:45抵达巴黎戴高乐国际机场T1航站楼，飞行时长13小时45分钟，伊斯坦布尔时间比北京晚5个小时（北京时间10:15即伊斯坦布尔时间05:15），巴黎时间比北京晚6个小时（北京时间15:45即巴黎时今天的游览计划安排较为轻松，只给您安排了香
            </div>
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    data() {
        return {
            
        }
    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
        }),
    },
    methods: {
        editOverview(){
            
        }
        // sendToParentSure(){
        //     this.$emit('callbackSure', {
        //         templateType: this.templateTypeSel,
        //         templateName: this.templateName
        //     })
        // },
        // sendToParentCancel(){
        //     this.$emit('callbackCancel')
        // }
    },
}
</script>
<style lang="less" scope>
.routeOverview{
    position: absolute;
    left: 295px;
    right: 15px;
    top: 75px;
    bottom: 20px;
    .header{
        .opts{
            line-height: 55px;
            i{
                font-size: 14px;
                color: #A1ACB3;
            }
        }
    }
    
    .content{
        padding: 10px 15px;
        line-height: 24px;
        font-size: 14px;
    }
}
</style>