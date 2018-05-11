// 日期格式化
function formatDate(val) {
  if (val != null) {
    var date = new Date(val);
    return (
      date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
    )
  }
}

function getQueryString(name) { 
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
  var r = window.location.search.substr(1).match(reg); 
  if (r != null) return unescape(r[2]); 
  return null; 
} 

// 地图城市连接
function showMap(_cityInfo){
  var _map = new google.maps.Map(document.getElementById("SummaryMap"), {
    center: { lat: 30.54045, lng: 104.072974 },
    zoom: 3
  });

  var flightPlanCoordinates = []

  var latlngbounds = new google.maps.LatLngBounds();

  for (var i = 0; i < _cityInfo.length; i++) {
      flightPlanCoordinates.push({
          lat: parseFloat(_cityInfo[i].lat),
          lng: parseFloat(_cityInfo[i].lon)
      })

      var latlng = new google.maps.LatLng(parseFloat(_cityInfo[i].lat), parseFloat(_cityInfo[i].lon));
      latlngbounds.extend(latlng);
  }

  var chainPloyline = new google.maps.Polyline({
      path: flightPlanCoordinates,
      // geodesic: true,
      strokeColor: '#FA574B',
      strokeOpacity: 0.75,
      strokeWeight: 1,
      // icons: [{
      //     icon: {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW},
      //     offset: '100%'
      // }]
  });

  chainPloyline.setMap(_map);

  _map.fitBounds(latlngbounds);
  mapCityMarker(_map, _cityInfo, 'chain')
}


function mapCityMarker(map, data, type){
  for (var i = 0; i < data.length; i++) {
    var marker = new RichMarker({
        map: map,
        position: new google.maps.LatLng(data[i].lat, data[i].lon),
        draggable: false,
        flat: true,
        anchor: RichMarkerPosition.MIDDLE,
        content: '<div class="mapMarker"></div>'
    })
  }
}

function formatDateWeek(timestamp, index) {
  if (!timestamp) {
    return '';
  } else {
    return moment(timestamp).add(index, 'days').format("MM/DD") + ' ' + moment(timestamp).add(index, 'days').format('dddd').replace('星期', '周')
  }
}

function dayInfo(dayList, startDate){
  var _html = ''
  var _linkHtml = ''

  for (let i = 0; i < dayList.length; i++) {
    var _city = []
    for (let j = 0; j < dayList[i]['citys'].length; j++) {
      _city.push(dayList[i]['citys'][j].nameCn)      
    }
    _city = _city.join(' <i class="iconfont icon-jiantou02"></i> ')

    _html += '<li>'
      _html += '<div class="no">D'+ (i+1) +'</div>'
      _html += '<div class="date">'+ formatDateWeek(startDate, i) +'</div>'
      _html += '<div class="citys">' + _city + '</div>'
    _html += '</li>'
    
    
    _linkHtml += '<a href="javascript:;" data-index="'+i+'">D'+(i+1)+'</a>'
  }

  $('#DayList').html(_html)
  $('#DayListLink').html(_linkHtml)
}


function trafficIcon(type){
  if(type == 1){
    return '<i class="iconfont icon-gongjiaoche"></i>'
  }else if(type == 2){
    return '<i class="iconfont icon-qiche"></i>'
  }else if(type == 3){
    return '<i class="iconfont icon-buhang"></i>'
  }else if(type == 4){
    return '<i class="iconfont icon-feiji-big"></i>'
  }else if(type == 5){
    return '<i class="iconfont icon-huoche"></i>'
  }else if(type == 6){
    return '<i class="iconfont icon-lunchuang"></i>'
  }else if(type == 7){
    return '<i class="iconfont icon-gongjiaoche"></i>'
  }else if(type == 8){
    return '<i class="iconfont icon-ditie"></i>'
  }
}

function formatSeconds(value) { 
  var theTime = parseInt(value);// 秒
  var theTime1 = 0;// 分
  var theTime2 = 0;// 小时
  // alert(theTime);
  if (theTime > 60) {
    theTime1 = parseInt(theTime / 60);
    theTime = parseInt(theTime % 60);
    // alert(theTime1+"-"+theTime);
    if (theTime1 > 60) {
      theTime2 = parseInt(theTime1 / 60);
      theTime1 = parseInt(theTime1 % 60);
    }
  }
  var result = "" + parseInt(theTime) + "秒";
  if (theTime1 > 0) {
    result = "" + parseInt(theTime1) + "分" + result;
  }
  if (theTime2 > 0) {
    result = "" + parseInt(theTime2) + "小时" + result;
  }
  return result;
} 

function trafficName(type){
  if(type == 1){
    return '公交方案'
  }else if(type == 2){
    return  '驾车方案'
  }else if(type == 3){
    return  '步行方案'
  }else if(type == 4){
    return  '飞机'
  }else if(type == 5){
    return  '火车'
  }else if(type == 6){
    return  '轮渡'
  }else if(type == 7){
    return  '巴士'
  }else if(type == 8){
    return  '地铁'
  }
}

function trafficInfo(distence, time){
  var _distence = distence + '米'
  if(distence > 1000){
    _distence = distence/1000 + '公里'
  }

  var _time = formatSeconds(time)

  return _distence + ' ' + _time

}

function scheduleInfo(scheduleData) {
  for (let i = 0; i < scheduleData.length; i++) {
    var _city = []

    for (let j = 0; j < scheduleData[i].citys.length; j++) {
      _city.push(scheduleData[i].citys[j].nameCn)
    }

    _city = _city.join(' <i class="iconfont icon-jiantou02"></i> ')

    var _html = '<div class="swiper-slide routeDay">'
    _html +='<div class="dayTitle">'
      _html += '<div class="no">D'+(i+1)+'</div>'
      _html += '<div class="citys">'+_city+'</div>'
    _html += '</div>'
    
    var _ss = scheduleData[i].scheduletrips
    var _tt = scheduleData[i].traffics
    var _hh = scheduleData[i].scheduleHotels

    _html += '<div class="scheduleList">'

    for (let j = 0; j < _hh.length; j++) {
      if(_hh[j].checkInType == 0){
        _html +='<div class="item">'
          _html +='<div class="tit">'
            _html +='<div class="no"><i class="iconfont icon-sun"></i></div>'
            _html +='<div class="name">'+ _hh[j].nameCn +'</div>'
          _html +='</div>'
          _html +=' <div class="baseInfo">'
            _html +='<p><label>入住时间：</label>'+_hh[j].checkInTime+'</p>'
            _html +='<p><label>退房时间</label>'+ _hh[j].checkOuTime +'</p>'
          _html +='</div>'
          _html +='<div class="poiInfo">'
            _html +='<div class="pic"><img src="'+_hh[j].imageurl+'"/></div>'
          _html +='</div>'
        _html +='</div>'
      }
    }
    
    for (let j = 0; j < _ss.length; j++) {

      if(_ss[j].type == 7){
        _html +='<div class="item">'
          _html +='<div class="tit">'
            _html +='<div class="no">'+(j+1)+'</div>'
            _html +='<div class="name">'+ _ss[j].nameCn +'</div>'
          _html +='</div>'
          _html +=' <div class="baseInfo">'
            _html +='<p><label>出发：</label>'+_ss[j].startName+'</p>'
            _html +='</div>'
          _html +=' <div class="baseInfo">'
            _html +='<p><label>航班号：</label>'+_ss[j].model+'</p>'
            _html +='</div>'
          _html +=' <div class="baseInfo">'
            _html +='<p><label>到达：</label>'+ _ss[j].endName +'</p>'
          _html +='</div>'
        _html +='</div>'
        
      }else{
        _html +='<div class="item">'
          _html +='<div class="tit">'
            _html +='<div class="no">'+(j+1)+'</div>'
            _html +='<div class="name">'+ _ss[j].nameCn +'</div>'
          _html +='</div>'
          _html +=' <div class="baseInfo">'
            _html +='<p><label>门票：</label>免费</p>'
            _html +='<p><label>时间：</label>'+ _ss[j].timeReference +'</p>'
          _html +='</div>'

          _html +='<div class="poiInfo">'
          _html +='<div class="pic"><img src="'+_ss[j].imageurl+'"/></div>'
          _html +='<div class="text">'+_ss[j].addressInstrations+'</div>'
          _html +='<ul>'
          _html +='<li>'
          _html +='<label>POI类型</label>'
          _html +='<div class="txt">'
          _html +='<i class="iconfont icon-travel"></i>游览</div>'
          _html +='</li>'
          _html +='<li>'
          _html +='<label>电话</label>'
          _html +='<div class="txt">'+_ss[j].phone+'</div>'
          _html +='</li>'
          _html +='<li>'
          _html +='<label>网址</label>'
          _html +='<div class="txt">'+_ss[j].url+'</div>'
          _html +='</li>'
          _html +='<li>'
          _html +='<label>开放时间</label>'
          _html +='<div class="txt">'+_ss[j].openTime+'</div>'
          _html +='</li>'
          _html +='<li>'
          _html +='<label>消费</label>'
          _html +='<div class="txt">'+_ss[j].timeConsume+'</div>'
          _html +='</li>'
          _html +='<li>'
          _html +='<label>用时参考</label>'
          _html +='<div class="txt">'+_ss[j].timeReference+'</div>'
          _html +='</li>'
          _html +='</ul>'
          _html +='</div>'

        _html +='</div>'
      }

      var _startS = _ss[j].id

      for (let j = 0; j < _tt.length; j++) {
        if(_tt[j].startScheduleTrip == _startS){
          _html += '<div class="triffic">'
            _html += trafficIcon(_tt[j].trafficType)
            _html += '<div class="info">'
              _html += '<p>'+trafficName(_tt[j].trafficType)+'</p>'
              _html += '<p class="detail">'+trafficInfo(_tt[j].distance, _tt[j].spendTime)+'</p>'
            _html += '</div>'
          _html += '</div>'
        }
      }
    }
    _html += '</div>'
    _html += '</div>'
    
    $('#Remark').before(_html)
  }
  swiper = new Swiper(".swiper-container", {
    direction: "vertical"
  })

  var startScroll, touchStart, touchCurrent;
  swiper.slides.on('touchstart', function (e) {
      startScroll = this.scrollTop;
      touchStart = e.targetTouches[0].pageY;
  }, true);
  swiper.slides.on('touchmove', function (e) {
      touchCurrent = e.targetTouches[0].pageY;
      var touchesDiff = touchCurrent - touchStart;
      var slide = this;
      var onlyScrolling =
              ( slide.scrollHeight > slide.offsetHeight ) && //allow only when slide is scrollable
              (
                  ( touchesDiff < 0 && startScroll === 0 ) || //start from top edge to scroll bottom
                  ( touchesDiff > 0 && startScroll === ( slide.scrollHeight - slide.offsetHeight ) ) || //start from bottom edge to scroll top
                  ( startScroll > 0 && startScroll < ( slide.scrollHeight - slide.offsetHeight ) ) //start from the middle
              );
      if (onlyScrolling) {
          e.stopPropagation();
      }
  }, true);
  
}

// 获取行程详情
function getRouteInfo() {
  moment.locale('zh-cn');
  $.ajax({
    url: "/tripnote/doExport/html5/" + getQueryString('id'),
    success: function(data) {
      var _tripData = data.data.tripnote
      var _user = data.data.account;
      if(_tripData.imageurl){
        $("#RouteAvatar").attr("src", _tripData.imageurl);
      }
      $("#RouteTitle").html(_tripData.title);
      $("#RouteDate").html(formatDate(_tripData.startDate) + '-' + formatDate(_tripData.endDate))
      $("#RouteUser").html(_user.realName ? _user.realName : _user.loginName)
      $("#RouteIntro").html(_tripData.introduction)
      $("#RouteRemark").html(_tripData.remarks)

      var _cityList = []
      for (var i = 0; i < _tripData.ttripNoteSchedules.length; i++) {
        _cityList = _cityList.concat(_tripData.ttripNoteSchedules[i].citys)
      }
      showMap(_cityList)

      dayInfo(_tripData.ttripNoteSchedules, _tripData.startDate)

      scheduleInfo(_tripData.ttripNoteSchedules)
    }
  })
}

var swiper
$(function() {
  swiper = new Swiper(".swiper-container", {
    direction: "vertical"
  })
  var startScroll, touchStart, touchCurrent;
  swiper.slides.on('touchstart', function (e) {
      startScroll = this.scrollTop;
      touchStart = e.targetTouches[0].pageY;
  }, true);
  swiper.slides.on('touchmove', function (e) {
      touchCurrent = e.targetTouches[0].pageY;
      var touchesDiff = touchCurrent - touchStart;
      var slide = this;
      var onlyScrolling =
              ( slide.scrollHeight > slide.offsetHeight ) && //allow only when slide is scrollable
              (
                  ( touchesDiff < 0 && startScroll === 0 ) || //start from top edge to scroll bottom
                  ( touchesDiff > 0 && startScroll === ( slide.scrollHeight - slide.offsetHeight ) ) || //start from bottom edge to scroll top
                  ( startScroll > 0 && startScroll < ( slide.scrollHeight - slide.offsetHeight ) ) //start from the middle
              );
      if (onlyScrolling) {
          e.stopPropagation();
      }
  }, true);

  
  $("#MenuControl").click(function() {
    if ($(this).hasClass("show")) {
      $(this).removeClass("show");
      $("#TripMenu").removeClass("show");
    } else {
      $(this).addClass("show");
      $("#TripMenu").addClass("show");
    }
  });

  getRouteInfo()

  $('#LinkCover').click(function(){
    swiper.slideTo(0)
    $('#MenuControl').click()
  })
  $('#LinkDay').click(function(){
    swiper.slideTo(1)
    $('#MenuControl').click()
  })

  $('#DayListLink').on('click', 'a', function(){
    var _index = $(this).data('index')
    swiper.slideTo(_index+3)

    $('#MenuControl').click()
  })
})
