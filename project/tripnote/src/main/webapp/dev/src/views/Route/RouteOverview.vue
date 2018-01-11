<template>
    <div>
        <div class="columnWrap routeOverview" v-if="!setRouteLineActive">
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
                    于7月15日00:50从北京国际机场T3航站楼出发，请提前两小时抵达北京国际机场T3航站楼，办理登机及出境事宜。抵达土耳其伊斯坦布尔后在同一航站楼转机，飞机准点到达的情况下转机时间为1小时35分钟，最终于巴黎时间09:45抵达巴黎戴高乐国际机场T1航站楼，飞行时长13小时45分钟，伊斯坦布尔时间比北京晚5个小时（北京时间10:15即伊斯坦布尔时间05:15），巴黎时间比北京晚6个小时（北京时间15:45即巴黎时间09:45），请及时将手机和手表设置成当地时间，以免影响你的行程。

                    抵达巴黎夏尔戴高乐机场后，将在机场地铁站乘坐RER抵达Paris Luxembourg站下车，步行至我们为您预订的安德烈拉丁酒店，具体交通信息可以使用路书app中“每日安排”里面地点导航功能。安德烈拉丁酒店入住时间是14:00，抵达酒店后您可以先在酒店前台办理行李存放手续，然后在酒店大堂稍事休息，然后开始今天的游览计划。由于长达15小时20分钟的飞行及转机时间，所以今天的游览计划安排较为轻松，只给您安排了香
                </div>
            </div>
        </div>

        <div class="columnBox overviewMap" v-if="setRouteLineActive">
            <div class="header">
                <div class="tit">
                    <i class="iconfont icon-rili"></i>
                    行程总览
                </div>
                <div class="opts">
                    <a href="javascript:;" @click="closeMap">
                        <i class="iconfont icon-guanbi"></i>
                    </a>
                </div>
            </div>
            <div class="googleMap" id="map"></div>
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

// import GoogleMapsLoader from '../../assets/libs/googlemap/map.js'


export default {
    data() {
        return {
            showMap: false,
            googleMap: '',
            markerImg: require('../../assets/images/markers.png')
        }
    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive'
        }),
    },
    created(){

    },
    watch: {
        setRouteLineActive(value){
            console.log(value)
            if(value){
                this.initMap()
            }
        }
    },
    mounted(){
        this.$nextTick(() => {
            // this.initMap()
        })
    },

    methods: {
        closeMap(){
            this.$store.dispatch('setRouteLineActive', false)
        },

        initMap(){


            var vm = this


            setTimeout(function(){
                
            
            // GoogleMapsLoader.KEY = 'AIzaSyAmhTSJ4VTpmTf-wX4AE4TXMrZmKzjJ36o';
            // GoogleMapsLoader.LIBRARIES = ['places'];
            // GoogleMapsLoader.LANGUAGE = 'zh-CN';

            // GoogleMapsLoader.load(function(google) {
                var map = new google.maps.Map(document.getElementById('map'), {
                  center: {lat: 30.540450, lng: 104.072974},
                  zoom: 10
                });


                var locations = [
                    ['天赋软件园C12', 30.540450, 104.072974, 100],
                    ['天府广场', 30.657522, 104.065811, 100]
                ];

                var poly;

                // 线条设置
                var polyOptions = {
                    strokeColor: '#FB4949', // 颜色
                    strokeOpacity: 1.0, // 透明度
                    strokeWeight: 2 // 宽度
                }
                poly = new google.maps.Polyline(polyOptions);

                poly.setMap(map); // 装载

                var marker, i, circle;
                var iwarray = [];
                var infoWindow;
                var latlngbounds = new google.maps.LatLngBounds();
                var iconMarker = new google.maps.MarkerImage(vm.markerImg);

                for (i = 0; i < locations.length; i++) {
                    var loc = [];
                    loc.push(locations[i][1]);
                    loc.push(locations[i][2]);
                    var path = poly.getPath(); //获取线条的坐标
                    path.push(new google.maps.LatLng(loc[0], loc[1]));
                    var latlng = new google.maps.LatLng(locations[i][1], locations[i][2]);
                    latlngbounds.extend(latlng);

                    marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        icon: iconMarker
                    });
                    var iw = '<div class="">' + locations[i][0] + '<div>';

                    iwarray[i] = iw;

                    circle = new google.maps.Circle({
                        map: map,
                        radius: 10,
                        fillColor: '#FB4949',
                        fillOpacity: 0.01,
                        strokeWeight: 10,
                        strokeColor: '#FB4949',
                        strokeOpacity: 0.8
                    });
                    circle.bindTo('center', marker, 'position');

                    google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                        return function() {

                            // var boxText = document.createElement("div");
                            // boxText.style.cssText = "border: 1px solid black; margin-top: 8px; background: yellow; padding: 5px;";
                            // boxText.innerHTML = "City Hall, Sechelt<br>British Columbia<br>Canada";
                            // var myOptions = {
                            //      content: boxText
                            //     ,disableAutoPan: false
                            //     ,maxWidth: 0
                            //     ,pixelOffset: new google.maps.Size(-140, -200)
                            //     ,zIndex: null
                            //     ,boxStyle: { 
                            //       background: "url('tipbox.gif') no-repeat"
                            //       ,opacity: 0.75
                            //       ,width: "280px"
                            //      }
                            //     ,closeBoxMargin: "10px 2px 2px 2px"
                            //     ,closeBoxURL: "https://www.google.com/intl/en_us/mapfiles/close.gif"
                            //     ,infoBoxClearance: new google.maps.Size(1, 1)
                            //     ,isHidden: false
                            //     ,pane: "floatPane"
                            //     ,enableEventPropagation: false
                            // };
                            // var ib = new InfoBox(myOptions);

                            // ib.open(map, this);



                            infoWindow = new google.maps.InfoWindow({
                                content: iwarray[i],
                                maxWidth: 200,
                                pixelOffset: new google.maps.Size(0, 0)
                            });




                            // google.maps.event.addListener(infowindow, 'domready', function() {

                            //     var iwOuter = $('.gm-style-iw');

                            //     var iwBackground = iwOuter.prev();

                            //     iwBackground.children(':nth-child(2)').css({
                            //       'background': '#252525'
                            //     });

                            //     var iwmain = iwBackground.children(':nth-child(2)');

                            //     iwBackground.children(':nth-child(4)').css({
                            //       'display': 'none'
                            //     });

                            //     var iwCloseBtn = iwOuter.next();

                            // });

                            infoWindow.open(map, marker);
                        }
                    })(marker, i));

                    google.maps.event.addListener(map, 'click', function() {
                        infoWindow.close();
                    });

                    google.maps.event.addListener(marker, 'mouseout', function() {
                        infoWindow.close();
                    });
                    
                }

            }, 500)


            // });
        },

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
.overviewMap{
    left: 300px;
    right: 20px;
    top: 80px;
    bottom: 20px;
    .header{
        .opts{
            height: 54px;
            margin-right: -15px;
            border-left: 1px solid #EAEDF1;
            &:hover{
                background: #f1f1f1;
            }
            a{
                display: block;
                width: 54px;
                line-height: 54px;
                text-align: center;
                i{
                    font-size: 20px;
                    font-weight: bold;
                    color: #A1ACB3;
                }
            }
            
        }
    }
    .googleMap{
        position: absolute;
        top: 55px;
        left: 0;
        right: 0;
        bottom: 0;
    }
}
</style>