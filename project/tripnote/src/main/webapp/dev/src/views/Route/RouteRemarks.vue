<template>
    <div>
        <div class="columnWrap notes" v-if="!setRouteLineActive">
            <div class="columnBox">
                <div class="header">
                    <div class="tit"><i class="iconfont icon-wenjian"></i>行程备注</div>
                    <div class="opts">
                        <a href="javascript:;" @click="isEdit=true" v-if="!isEdit" class="actionBtn">
                            <i class="iconfont icon-bianji-blue"></i>
                            <span>编辑</span>
                        </a>

                        <div class="moreBtn" v-if="isEdit">
                            <el-button round size="medium" @click="isEdit=false">取消</el-button>
                            <el-button type="primary" round size="medium" @click="saveOverview">保存</el-button>
                        </div>
                    </div>
                </div>

                <div class="content" v-if="!isEdit" v-html="routeInfo.remarkes"></div>
                <div class="" v-if="isEdit">
                    <froala :tag="'textarea'" :config="config" v-model="routeInfo.remarkes"></froala>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'

import VueFroala from 'vue-froala-wysiwyg';

export default {
    data() {
        return {
            showMap: false,
            googleMap: '',
            markerImg: require('../../assets/images/markers.png'),

            config: {
                events: {
                    'froalaEditor.initialized': function() {
                        
                    },
                    'froalaEditor.image.error': function (e, editor, error, response){
                        console.log(error)
                        // response = JSON.parse(response);
                    },
                },
                heightMin: 300,
                heightMax: 500,
                placeholderText: '请输入内容',
                toolbarButtons: ['bold', 'italic', 'underline', 'fontSize', 'color', '|', 'paragraphFormat', 'align', 'formatOL', 'outdent', 'indent', '|', 'quote', 'insertLink', 'insertImage', 'insertHR'],
                imageUploadURL: '/tripnote/common/upload/schedule/image'
            },

            isEdit: false
        }
    },
    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            setRouteLineActive: 'setRouteLineActive'
        }),

        routeId(){
            if(this.routeInfo.id){
                return this.routeInfo.id
            }else{
                var _path = this.$route.path
                return _path.split('/')[2]
            }
        },
    },
    created(){
    },
    watch: {
        // setRouteLineActive(value){
        //     if(value){
        //         this.initMap()
        //     }
        // }
    },
    mounted(){
        this.$nextTick(() => {
            // this.initMap()
        })
    },

    methods: {
        saveOverview(){
            this.$http({
                method: 'post',
                url: '/tripnote/tripnote/doUpdate/' + this.routeId,
                data: {
                    remarkes: this.routeInfo.remarkes
                }
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.$message({
                        message: '保存成功！',
                        type: 'success',
                        duration: 2000
                    })
                    this.isEdit = false
                }
            })
        },

        // closeMap(){
        //     this.$store.dispatch('setRouteLineActive', false)
        // },

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
