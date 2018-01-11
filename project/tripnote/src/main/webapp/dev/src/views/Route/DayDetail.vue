<template>
    <div>
        <div class="columnBox daySchedule" v-if="!setRouteLineActive">
            <div class="header">
                <div class="tit"><i class="iconfont icon-rili"></i>日程安排</div>
                <div class="opts">
                    <el-button type="primary" round>
                        <i class="iconfont icon-bianji-blue"></i>
                        安排日程
                    </el-button>
                </div>
            </div>

            <div class="content">
                <div class="nullTip">
                    <div class="icon"></div>
                    <p>请先选择这天要游览的城市</p>
                    <el-button size="small" round icon="el-icon-plus">添加地点或产品</el-button>
                </div>
            </div>
        </div>

        <div class="columnWrap dayInfo" v-if="!setRouteLineActive">
            <div class="columnBox">
                <div class="header">
                    <div class="tit"><i class="iconfont icon-wenjian"></i>行程介绍</div>
                    <div class="opts">
                        <a href="">
                            <i class="iconfont icon-bianji-blue"></i>
                        </a>
                    </div>
                </div>

                <div class="content">
                    今天是旅行的第一天，您将全程乘坐土耳其航空，于7月15日00:50从北京国际机场T3航站楼出发，请提前两小时抵达北京国际机场T3航站楼，办理登机及出境事宜。抵达土耳其伊斯坦布尔后在同一航站楼转机，飞机准点到达的情况下转机时间为1小时35分钟，最终于巴黎时间09:45抵达巴黎戴高乐国际机场T1航站楼，飞行时长13小时45分钟，伊斯坦布尔时间比北京晚5个小时（北京时间10:15即伊斯坦布尔时间05:15），巴黎时间比北京晚6个小时（北京时间15:45即巴黎时间09:45），请及时将手机和手表设置成当地时间，以免影响你的行程。

                    抵达巴黎夏尔戴高乐机场后，将在机场地铁站乘坐RER抵达Paris Luxembourg站下车，步行至我们为您预订的安德烈拉丁酒店，具体交通信息可以使用路书app中“每日安排”里面地点导航功能。安德烈拉丁酒店入住时间是14:00，抵达酒店后您可以先在酒店前台办理行李存放手续，然后在酒店大堂稍事休息，然后开始今天的游览计划。由于长达15小时20分钟的飞行及转机时间，所以今天的游览计划安排较为轻松，只给您安排了香
                </div>
            </div>

            <div class="columnBox">
                <div class="header">
                    <div class="tit"><i class="iconfont icon-bianji"></i>定制师笔记</div>
                    <div class="opts">
                        <a href="">
                            <i class="iconfont icon-lnicon34"></i>
                        </a>
                    </div>
                </div>

                <div class="content">
                    今天是旅行的第一天，您将全程乘坐土耳其航空，于7月15日00:50从北京国际机场T3航站楼出发，请提前两小时抵达北京国际机场T3航站楼，办理登机及出境事宜。抵达土耳其伊斯坦布尔后在同一航站楼转机，飞机准点到达的情况下转机时间为1小时35分钟，最终于巴黎时间09:45抵达巴黎戴高乐国际机场T1航站楼，飞行时长13小时45分钟，伊斯坦布尔时间比北京晚5个小时（北京时间10:15即伊斯坦布尔时间05:15），巴黎时间比北京晚6个小时（北京时间15:45即巴黎时间09:45），请及时将手机和手表设置成当地时间，以免影响你的行程。

                    抵达巴黎夏尔戴高乐机场后，将在机场地铁站乘坐RER抵达Paris Luxembourg站下车，步行至我们为您预订的安德烈拉丁酒店，具体交通信息可以使用路书app中“每日安排”里面地点导航功能。安德烈拉丁酒店入住时间是14:00，抵达酒店后您可以先在酒店前台办理行李存放手续，然后在酒店大堂稍事休息，然后开始今天的游览计划。由于长达15小时20分钟的飞行及转机时间，所以今天的游览计划安排较为轻松，只给您安排了香
                </div>
            </div>
        </div>

        <set-city 
            v-if="setRouteLineActive"
        ></set-city>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import SetCity from './DaySetCity'

export default {
    components: {
        SetCity
    },
    data() {
        return {
            setCityVisible: true,
        }
    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive'
        }),
    },
    methods: {
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
.daySchedule{
    width: 300px;
    top: 80px;
    bottom: 20px;
    left: 300px;
    .header{
        .opts{
            margin-top: 12px;
            .el-button.is-round{
                height: 30px;
                padding: 0 12px;
                line-height: 28px;
                font-size: 12px;
                i{
                    float: left;
                    margin-right: 5px;
                    font-size: 12px;
                }
            }
        }
    }
    .nullTip{
        text-align: center;
        .icon{
            width: 75px;
            height: 75px;
            margin: 100px auto 20px;
            background: url(../../assets/images/icon_point.png);
        }
        p{
            margin-bottom: 40px;
            color: #DADFE3;
        }
        .el-button{
            border-color: #23a16d;
            color: #23a16d;
            i{
                font-weight: bold;
            }
        }
    }
}
.dayInfo{
    left: 615px;
    right: 15px;
    top: 75px;
    bottom: 20px;
    .header{
        .opts{
            line-height: 55px;
            i{
                font-size: 12px;
                color: #A1ACB3;
            }
        }
    }
}
</style>