<template>
    <div>
        <div class="columnBox dayScheduleSet" :class="{cityNull: cityNull}">
            <div class="header">
                <div class="tabs">
                    <ul>
                        <li :class="{active: tabActive=='poi'}" @click="changeTabs('poi')" >
                            <span class="names">
                                <span>POI</span>
                                <i class="el-icon-caret-bottom" @mouseover="poiTypeListVisible=true" @mouseleave="poiTypeListHide"></i>
                            </span>
                            <i class="iconfont icon-filter" v-if="poiType!=0" @click="changePoiType(0)"></i>
                            
                            <div class="list" :class="{show:poiTypeListVisible}" @mouseover="poiTypeListShow" @mouseleave="poiTypeListVisible=false">
                                <div class="item" :class="{active: poiType==0}" @click="changePoiType(0)"><i class="iconfont icon-all"></i>全部</div>
                                <div class="item" :class="{active: poiType==1}" @click="changePoiType(1)"><i class="iconfont icon-canyin"></i>餐饮</div>
                                <div class="item" :class="{active: poiType==2}" @click="changePoiType(2)"><i class="iconfont icon-travel"></i>游览</div>
                                <div class="item" :class="{active: poiType==3}" @click="changePoiType(3)"><i class="iconfont icon-gouwu"></i>购物</div>
                                <div class="item" :class="{active: poiType==4}" @click="changePoiType(4)"><i class="iconfont icon-kuaiyule"></i>娱乐</div>
                            </div>
                        </li>
                        <li :class="{active: tabActive=='product'}" @click="changeTabs('product')">产品</li>
                        <li :class="{active: tabActive=='hotel'}" @click="changeTabs('hotel')">酒店</li>
                        <li :class="{active: tabActive=='traffic'}" @click="changeTabs('traffic')">城际交通</li>
                        <li :class="{active: tabActive=='rent'}" @click="changeTabs('rent')">租车</li>
                    </ul>
                </div>

                <div class="opts">
                    <a href="javascript:;" class="closeBox" @click="closeBox">
                        <i class="iconfont icon-guanbi"></i>
                    </a>
                </div>
            </div>

            <div class="content" :class="{moreWidth: tabActive=='traffic' || tabActive == 'rent'}">
                <div class="detail" v-if="!hotelHas && tabActive!='traffic' && tabActive != 'rent'">
                    <div class="head">
                        <div class="tit">
                            <Select :opts="cityList" :sel="citySel" @changeOpt="changeOpt" nameAlias="nameCn" idAlias="cityId"></Select>
                        </div>
                        <el-input
                            v-model="keywords"
                            class="keywords"
                            :class="{active:keywordsActive}"
                            :placeholder="searchPlaceholder"
                            prefix-icon="el-icon-search"
                            @focus="keywordsActive=true"
                            @change="searchPoi"
                            >
                        </el-input>
                        <a href="javascript:;" class="cancelSearch" @click="cancelSearch" v-if="keywordsActive">
                            <i class="el-icon-close"></i>
                        </a>
                    </div>
                    <div class="poiList" id="PoiList">
                        <ul v-if="poiDataList.length > 0">
                            <li v-for="(item, index) in poiDataList" @click="poiDetailLink(item.id, item.type)">
                                <div class="pic" :style="{backgroundImage: `url(${poiImgFormat(item.imageurl)})`}"></div>
                                <div class="detail">
                                    <h3 :title="item.nameCn"><span v-html="typeFormat(item.type)"></span>{{item.nameCn}}</h3>
                                    <div class="subTit" :title="item.nameEn">{{item.nameEn}}</div>
                                    <div class="price" v-if="item.price != null">￥{{item.price}}</div>
                                </div>
                                <div class="opts">
                                    <span class="add" @click.stop="addToSchedule(item)">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="poiSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>
                        </ul>

                        <div class="loadMore" id="LoadMore" :class="{hidden:!loadMoreAble}">
                            正在加载。。。
                        </div>

                        <div class="noDataTip" v-if="poiDataList.length==0">
                            <i class="iconfont icon-point-out"></i>没有相关数据！
                        </div>
                    </div>
                </div>

                <div class="detail" v-if="hotelHas && tabActive!='traffic' && tabActive != 'rent'">
                    <div class="hotelPic" :style="{backgroundImage: `url(${imgFormat(hotelDetail.imageurl)})`}"></div>
                    <div class="hotelTit">
                        <i class="iconfont icon-chuangwei"></i>{{hotelDetail.nameCn}}
                    </div>
                    <div class="hotelCheckTime">
                        <label>入住时间：</label>{{hotelDetail.checkInTime}}
                        <label>退房时间：</label>{{hotelDetail.checkOuTime}}
                    </div>
                    <div class="hotelRemark">
                        <label>备注：</label>
                        <div class="text">{{hotelDetail.remark}}</div>
                    </div>
                </div>

                <div class="detailTraffic" v-if="tabActive=='traffic'">
                    <div class="head">
                        <div class="tit">
                            <Select :opts="cityList" :sel="citySel" @changeOpt="changeOpt" nameAlias="nameCn" idAlias="cityId"></Select>
                        </div>
                    </div>
                    <div class="filterBox clearfix">
                        <el-select value="1" placeholder="" class="customerSel">
                            <el-option
                                key="1"
                                label="1个乘客"
                                value="1">
                            </el-option>
                            <el-option
                                key="2"
                                label="2个乘客"
                                value="2">
                            </el-option>
                            <el-option
                                key="3"
                                label="3个乘客"
                                value="3">
                            </el-option>
                        </el-select>
<!-- 
                        <el-input value="2018-02-06" placeholder="请输入内容" class="dateSel"></el-input>
                        <el-input value="08:00" placeholder="请输入内容" class="timeSel"></el-input>
 -->

                        <el-date-picker
                                  v-model="sd"
                                  type="date"
                          placeholder="选择日期" class="dateSel">
                        </el-date-picker>

                        <el-time-select  
                            v-model="st"
                            class="timeSel"
                          :picker-options="{
                            start: '08:00',
                            step: '01:00',
                            end: '24:00'
                          }"
                          placeholder="选择时间">
                        </el-time-select>
                        
                        <el-button icon="el-icon-search" round class="searchBtn">查询</el-button>
                    </div>

                    <div class="tabs">
                        <ul>
                            <li>
                                <a href="javascript:;" :class="{active: trafficTabActive==1}" @click="trafficTabChange(1)"><i class="iconfont icon-feiji-big"></i>飞机</a>
                            </li>
                            <li>
                                <a href="javascript:;" :class="{active: trafficTabActive==2}" @click="trafficTabChange(2)"><i class="iconfont icon-huoche"></i>火车</a>
                            </li>
                            <li>
                                <a href="javascript:;" :class="{active: trafficTabActive==3}" @click="trafficTabChange(3)"><i class="iconfont icon-lunchuang"></i>渡船</a>
                            </li>
                            <li>
                                <a href="javascript:;" :class="{active: trafficTabActive==4}" @click="trafficTabChange(4)"><i class="iconfont icon-gongjiaoche"></i>巴士</a>
                            </li>
                        </ul>
                    </div>

                    <div class="trafficTabCon" v-if="trafficTabActive==1">
                        <ul>
                            <li class="airplane clearfix" v-for="(item, index) in trafficDataList">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        {{item.nameCn}}
                                    </div>
                                    <div class="model">
                                        <span>计划机型：{{item.model}}</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>{{formatTime(item.startTime)}}</p>
                                    <p>{{item.startName}}</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>{{formatTime(item.endTime)}}</p>
                                    <p>{{item.endName}}</p>
                                </div>

                                <div class="price">
                                    ¥<strong>{{item.price}}</strong>
                                </div>

                                <!-- <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div> -->
                                <div class="opts">
                                    <span class="add" @click.stop="addTrafficToSchedule(item)">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="poiSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>
                            <!-- <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="airplane clearfix">
                                <div class="airplaneName">
                                    <div class="name">
                                        <i class="iconPlane"></i>
                                        南方航空A3801
                                    </div>
                                    <div class="model">
                                        <span>计划机型：32L(中型)</span>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>首都国际机场</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>东京国际机场</p>
                                </div>

                                <div class="price">
                                    ¥<strong>1600</strong>
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li> -->
                        </ul>
                    </div>

                    <div class="trafficTabCon" v-if="trafficTabActive==2">
                        <ul>
                            <li class="train clearfix" v-for="(item, index) in trafficDataList">
                                <div class="no">
                                    <div class="name">{{item.nameCn}}</div>
                                    <div class="sel">
                                        <div class="selected">
                                            <span>经停站</span>
                                            <i class="el-icon-caret-bottom"></i>
                                        </div>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>{{formatTime(item.startTime)}}</p>
                                    <p>{{item.startName}}</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>{{formatTime(item.startTime)}}</p>
                                    <p>{{item.endName}}</p>
                                </div>

                                <div class="time">
                                    {{item.timeConsume}}
                                </div>

                                <div class="price">
                                    无实时价格
                                </div>

                                <div class="opts">
                                    <span class="add" @click.stop="addTrafficToSchedule(item)">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="poiSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>

                           <!--  <li class="train clearfix">
                                <div class="no">
                                    <div class="name">G5280</div>
                                    <div class="sel">
                                        <div class="selected">
                                            <span>经停站</span>
                                            <i class="el-icon-caret-bottom"></i>
                                        </div>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>乌鲁木齐</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>北京西</p>
                                </div>

                                <div class="time">
                                    27小时30分
                                </div>

                                <div class="price">
                                    无实时价格
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li> -->

                            
                        </ul>
                    </div> 

                    <div class="trafficTabCon" v-if="trafficTabActive==3">
                        <ul>
                            <li class="clearfix" v-for="(item, index) in trafficDataList">
                                <div class="no">
                                    <div class="name">{{item.nameCn}}</div>
                                    <div class="sel">
                                        <div class="selected">
                                            <span>选择仓位</span>
                                            <i class="el-icon-caret-bottom"></i>
                                        </div>
                                        <div class="selOpts">
                                            <div class="selOptHeader">
                                                <div class="col1">仓位</div>
                                                <div class="col2">说明</div>
                                                <div class="col3">余票量</div>
                                                <div class="col4">价格</div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="2"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="3"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="4"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="5"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="6"></el-radio>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>{{formatTime(item.startTime)}}</p>
                                    <p>{{item.startName}}</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>{{formatTime(item.endTime)}}</p>
                                    <p>{{item.endName}}</p>
                                </div>

                                <div class="time">
                                    {{item.timeConsume}}
                                </div>

                                <div class="price">
                                    ¥<strong>{{item.price}}</strong>起
                                </div>

                                <div class="opts">
                                    <span class="add" @click.stop="addTrafficToSchedule(item)">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="poiSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>

                            <!-- <li class="clearfix">
                                <div class="no">
                                    <div class="name">北游</div>
                                    <div class="sel">
                                        <div class="selected">
                                            <span>选择仓位</span>
                                            <i class="el-icon-caret-bottom"></i>
                                        </div>
                                        <div class="selOpts">
                                            <div class="selOptHeader">
                                                <div class="col1">仓位</div>
                                                <div class="col2">说明</div>
                                                <div class="col3">余票量</div>
                                                <div class="col4">价格</div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                            <div class="itemList">
                                                <div class="col1">普通舱A区</div>
                                                <div class="col2">大型告诉客轮、一层船头</div>
                                                <div class="col3">128</div>
                                                <div class="col4">¥180</div>
                                                <div class="col5">
                                                    <el-radio v-model="radio" label="1"></el-radio>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>北海国籍客运港码头</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>涠洲岛西角码头</p>
                                </div>

                                <div class="time">
                                    1时10分
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li> -->

                        </ul>
                    </div>

                    <div class="trafficTabCon" v-if="trafficTabActive==4">
                        <ul>
                            <li class="bus clearfix" v-for="(item, index) in trafficDataList">
                                <div class="startPoint">
                                    <p>{{formatTime(item.startTime)}}</p>
                                    <p>{{item.startName}}</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>{{formatTime(item.endTime)}}</p>
                                    <p>{{item.endName}}</p>
                                </div>

                                <div class="time">
                                    {{item.timeConsume}}
                                </div>

                                <div class="price">
                                    无实时价格
                                </div>

                                <div class="opts">
                                    <span class="add" @click.stop="addTrafficToSchedule(item)">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="poiSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>

                            <!-- <li class="bus clearfix">
                                <div class="startPoint">
                                    <p>10:42</p>
                                    <p>乌鲁木齐城北客运站</p>
                                </div>

                                <div class="arrow">
                                    <i class="iconfont icon-jiantou02"></i>
                                </div>

                                <div class="endPoint">
                                    <p>11:50</p>
                                    <p>北京西客运站</p>
                                </div>

                                <div class="time">
                                    27小时30分
                                </div>

                                <div class="price">
                                    无实时价格
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li> -->
                            
                        </ul>
                    </div>
                </div>

                <div class="detailRent" v-if="tabActive=='rent'">
                    <div class="head">
                        <div class="tit">
                            <Select :opts="cityList" :sel="citySel" @changeOpt="changeOpt" nameAlias="nameCn" idAlias="cityId"></Select>
                        </div>
                    </div>

                    <div class="filterBox clearfix">
                        <div class="searchOpt">
                            <div class="item">
                                <label>取车</label>
                                <el-input placeholder="请输入取车地点" class="pointSel"></el-input>
                                <el-date-picker
                                  v-model="sd"
                                  type="date"
                                  placeholder="选择日期" class="dateSel">
                                </el-date-picker>

                                <el-time-select  
                                    v-model="st"
                                    class="timeSel"
                                  :picker-options="{
                                    start: '08:00',
                                    step: '01:00',
                                    end: '24:00'
                                  }"
                                  placeholder="选择时间">
                                </el-time-select>

                            </div>
                            <div class="item">
                                <label>还车</label>
                                <el-input placeholder="请输入还车地点" class="pointSel"></el-input>
                                <el-date-picker
                                  v-model="ed"
                                  type="date"
                                  placeholder="选择日期" class="dateSel">
                                </el-date-picker>

                                <el-time-select  
                                    v-model="et"
                                    class="timeSel"
                                  :picker-options="{
                                    start: '08:00',
                                    step: '01:00',
                                    end: '24:00'
                                  }"
                                  placeholder="选择时间">
                                </el-time-select>
                            </div>
                        </div>
                        <div class="searchBtnWrap">
                            <p>共3天</p>
                            <el-button icon="el-icon-search" round class="searchBtn">查询</el-button>
                        </div>
                    </div>

                    <div class="filterList">
                        <dl>
                            <dt>车型：</dt>
                            <dd>
                                <a href="javascript:;">全部</a>
                                <a href="javascript:;">小型车</a>
                                <a href="javascript:;" class="active">紧凑型车</a>
                                <a href="javascript:;">中大型车</a>
                                <a href="javascript:;">高级轿车</a>
                                <a href="javascript:;">SUV</a>
                                <a href="javascript:;">MPV</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>拍档：</dt>
                            <dd>
                                <a href="javascript:;">全部</a>
                                <a href="javascript:;" class="active">自动/AT</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>座位数：</dt>
                            <dd>
                                <a href="javascript:;" class="active">全部</a>
                                <a href="javascript:;">5座及以下</a>
                                <a href="javascript:;">6-8座</a>
                                <a href="javascript:;">8座以上</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>支付方式：</dt>
                            <dd>
                                <a href="javascript:;" class="active">全部</a>
                                <a href="javascript:;">预付全款</a>
                                <a href="javascript:;">到店支付</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>公司：</dt>
                            <dd>
                                <a href="javascript:;" class="active">全部</a>
                                <a href="javascript:;">Hertz</a>
                                <a href="javascript:;">Avis</a>
                                <a href="javascript:;">Enterprise</a>
                            </dd>
                        </dl>
                    </div>

                    <div class="rentCon">
                        <ul>
                            <li class="clearfix" v-for="(item, index) in rentDataList">
                                <!-- <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div> -->
                                <div class="pic" :style="{backgroundImage: `url(${carImgFormat(item.imageurl)})`}"></div>

                                <div class="name">
                                    <div class="tit">
                                        {{item.nameCn}}<span>或同组车型</span>
                                    </div>
                                    <p>{{item.model}}&nbsp;&nbsp;|&nbsp;&nbsp;{{item.pedestal}}座&nbsp;&nbsp;|&nbsp;&nbsp;{{item.doors}}门&nbsp;&nbsp;|&nbsp;&nbsp;{{item.suitcases}}行李箱</p>
                                </div>

                                <div class="price">
                                    ¥<strong>{{item.price}}</strong>起
                                </div>

                                <!-- <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div> -->

                                <div class="opts">
                                    <span class="add" @click.stop="addCarToSchedule(item)" v-if="carSel.indexOf(item.id)==-1">
                                        <i class="el-icon-plus"></i>
                                    </span>
                                    <span class="selected" v-if="carSel.indexOf(item.id)>-1">
                                        <i class="el-icon-check"></i>
                                    </span>
                                </div>
                            </li>
                            <!-- <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="pic">
                                    <img src="http://pic.ctrip.com/car_isd/vi/online/123.jpg">
                                </div>

                                <div class="name">
                                    <div class="tit">
                                        斯柯达明锐<span>或同组车型</span>
                                    </div>
                                    <p>自动挡&nbsp;&nbsp;|&nbsp;&nbsp;4座&nbsp;&nbsp;|&nbsp;&nbsp;4门&nbsp;&nbsp;|&nbsp;&nbsp;3行李箱&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">查看更多<i class="el-icon-caret-bottom"></i></a> </p>
                                </div>

                                <div class="price">
                                    ¥<strong>150</strong>起
                                </div>

                                <div class="add">
                                    <a href="javascript:;"><i class="el-icon-plus"></i></a>
                                </div>
                            </li> -->
                        </ul>
                    </div>
                </div>

                <div class="googleMap" id="ScheduleMap"></div>
            </div>
        </div>
        
        <router-view></router-view>

        <el-dialog title="添加住宿" :visible.sync="addHotelDialogVisible" width="600px">
            <div class="addHotelCon">
                <div class="header">
                    <div class="icon">
                        <i class="iconfont icon-chuangwei"></i>
                    </div>
                    <div class="tit">{{hotelNameCn}}</div>
                    <div class="subTit">{{hotelNameEn}}</div>
                </div>

                <div class="daySel">
                    <label>入住</label>
                    <el-select v-model="hotelStartDay">
                        <el-option
                            v-for="(item, index) in routeInfo.dayInfo"
                            :label="'第' + (index+1) + '天'"
                            :disabled="isDisable(item)"
                            :value="index">
                        </el-option>
                    </el-select>

                    <label>退房</label>
                    <el-select v-model="hotelEndDay">
                        <el-option
                            v-for="(item, index) in routeInfo.dayInfo"
                            :label="'第' + (index+1) + '天'"
                            :disabled="isDisable(item)"
                            :value="index">
                        </el-option>
                    </el-select>
                </div>
                <div class="daySel">
                    <label>备注</label>
                    <el-input
                        type="textarea"
                        :rows="3"
                        placeholder="备注"
                        v-model="hotelRemark">
                    </el-input>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click.native="addHotelDialogVisible = false">取 消</el-button>
                &nbsp;
                <el-button type="primary" @click.native="addHotelToSchedule">保 存</el-button>
            </span>
        </el-dialog>

        <el-dialog title="提示" :visible.sync="addToScheduleConfirmVisible" width="450px">
            <div class="delTipCon">
                <p>今天的行程里面已经添加过，您确定要继续添加吗？</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addToScheduleConfirmVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addToScheduleAction">确定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import {mapState} from 'vuex'
import Bus from 'utils/bus'

import { FormatTime, FormatDateDay } from 'mixins/common'

import moment from 'moment'

export default {
    mixins: [ FormatTime, FormatDateDay  ],
    data() {
        return {
            sd: '',
            st: '',
            ed: '',
            et: '',
            map: '',
            tabActive: 'poi',
            poiDataList: [],
            poiCityList: [],

            cityNull: false,

            cityList: [],
            citySel: '',
            citySelId: '',

            poiType: 0,
            poiTypeListVisible: false,
            poiTypeListVisibleTimer: '',

            markerList: [],

            keywords: '',
            keywordsActive: false,
            pageNo: 1,
            pageSize: 8,

            poiSel: [],
            airplaneSel: [],
            carSel: [],
            loadingMore: false,
            loadMoreAble: false,


            chainMarker: [],
            chainPloyline: '',
            cityMarker: [],

            addHotelDialogVisible: false,

            hotelId: '',
            hotelNameCn: '',
            hotelNameEn: '',
            hotelStartDay: '',
            hotelEndDay: '',
            hotelRemark: '',

            hotelHas: false,

            trafficTabActive: 1,

            hotelDetail: {},

            searchPlaceholder: '搜索 POI',

            trafficDataList: [],
            rentDataList: [],
            radio: 1,

            addToScheduleConfirmVisible: false,
            addToScheduleItem: '',

            nowDay: '',
        }
    },
    
    watch: {
        // dayId(val){
        //     for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
        //         if(this.routeInfo.dayInfo[i].id == this.dayId){
        //             this.cityInfo = this.routeInfo.dayInfo[i].citys
        //         }
        //     }
        // },
        dayInfo(val){
            if(val){
                this.cityList = val.citys
                if(this.cityList.length == 0){
                    this.cityNull = true
                }else{
                    this.cityNull = false
                }
                // if(this.citySel == ''){
                    this.citySel = val.citys[0] ? val.citys[0].nameCn : ''
                    this.citySelId = val.citys[0] ? val.citys[0].cityId : ''
                // }
                

                this.pageNo = 1
                this.poiSel = []
                this.carSel = []

                for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
                    this.poiSel.push(this.dayInfo.scheduletrips[i].poiId)
                }
                for (var i = 0; i < this.dayInfo.rents.length; i++) {
                    this.carSel.push(this.dayInfo.rents[i].id)
                }
                
                this.initMap()
                this.getPoiData()

                this.getNowDay()
            }
        },
        tabActive(val){
            if(val == 'poi'){
                this.searchPlaceholder = '搜索 POI'
            }else if(val == 'product'){
                this.searchPlaceholder = '搜索 产品'
            }else if(val == 'hotel'){
                this.searchPlaceholder = '搜索 酒店'
            }else{
                this.searchPlaceholder = '搜索'
            }
        }
    },
    created(){
        if(!this.setDayScheduleActive){
            this.$store.dispatch('setDayScheduleActive', !this.setDayScheduleActive)
        }
        if(this.dayInfo['citys']){
            this.cityList = this.dayInfo.citys

            this.citySel = this.dayInfo.citys[0].nameCn
            this.citySelId = this.dayInfo.citys[0].cityId

            this.poiSel = []
            this.carSel = []

            for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
                this.poiSel.push(this.dayInfo.scheduletrips.poiId)
            }
            for (var i = 0; i < this.dayInfo.rents.length; i++) {
                this.carSel.push(this.dayInfo.rents[i].id)
            }

            this.getPoiData()

            this.getNowDay()

            console.log(this.nowDay)
        }
    },
    mounted(){
        this.$nextTick(() => {
            // this.initMap()

            var vm = this
            $('#PoiList').on('scroll', function(){
                console.log(123)
                if(vm.loadingMore || !vm.loadMoreAble){
                    return
                }
                var _loadTop = $('#LoadMore').offset().top

                var _poiHeight = $('#PoiList').height()
                var _poiScrollTop = $('#PoiList').scrollTop()

                if(_loadTop <= $(window).height() - 20){
                    vm.loadingMore = true
                    vm.pageNo++
                    vm.getPoiData('more')
                }
            })


            // $(document).on('click', function(){
            //     alert(123)
            // })
        })
    },

    computed: {
        ...mapState({
            routeInfo: 'routeInfo',
            dayInfo: 'dayInfo',
            setRouteLineActive: 'setRouteLineActive',
            setDayScheduleActive: 'setDayScheduleActive'
        }),
        dayId(){
            // if(this.routeInfo.daySel){
            //     return this.routeInfo.daySel
            // }else{

                return this.$route.params.dayId
            // }
        },
        dayIndex(){
            for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
                if(this.routeInfo.dayInfo[i].id == this.dayId){
                    return this.routeInfo.dayInfo[i].indexdate
                }
            }
        }
    },
    methods: {
        getNowDay(){
            this.sd = this.formatDateDay(this.routeInfo.startDate, this.dayInfo.indexdate - 1)
            this.ed = this.formatDateDay(this.routeInfo.startDate, this.dayInfo.indexdate - 1)
        },
        trafficTabChange(type){
            this.trafficTabActive = type
            this.getTrafficData()
        },

        isDisable(item){
            if(item.indexdate < this.dayIndex){
                return true
            }else{
                return false
            }
        },
        poiTypeListHide(){
            var vm = this
            this.poiTypeListVisibleTimer = setTimeout(function(){
                vm.poiTypeListVisible = false
            }, 200)
        },
        poiTypeListShow(){
            clearTimeout(this.poiTypeListVisibleTimer)
        },

        typeFormat(type){
            if(type == 1){
                return '<i class="iconfont icon-canyin"></i>'
            }else if(type == 2){
                return '<i class="iconfont icon-travel"></i>'
            }else if(type == 3){
                return '<i class="iconfont icon-gouwu"></i>'
            }else if(type == 4){
                return '<i class="iconfont icon-kuaiyule"></i>'
            }else if(type == 5){
                return '<i class="iconfont icon-flag"></i>'
            }else if(type == 6){
                return '<i class="iconfont icon-chuangwei"></i>'
            }
        },

        poiImgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },
        carImgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        changePoiType(val){
            this.poiTypeListVisible = false
            this.poiType = val

            this.getPoiData()
        },

        poiDetailLink(poiId, type){
            if(type == 5){
                this.$router.push({ path: 'product/' + poiId, append: true});
            }else if(type == 6){
                this.$router.push({ path: 'hotel/' + poiId, append: true});
            }else{
                this.$router.push({ path: 'poi/' + poiId, append: true});
            }
        },

        initMap(){
            var _pointList = []
            var _trafficList = []

            console.log(this.dayInfo.scheduletrips)

            for (var i = 0; i < this.dayInfo.scheduletrips.length; i++) {
                if(this.dayInfo.scheduletrips[i].type != 7){
                    _pointList.push(this.dayInfo.scheduletrips[i])
                }else{
                    _trafficList.push(this.dayInfo.scheduletrips[i])
                }
            }


            if(_pointList.length > 0){
                this.map = new google.maps.Map(document.getElementById('ScheduleMap'), {
                    center: {lat: _pointList[0].lat, lng: _pointList[0].lon},
                    zoom: 10
                })

                this.chainPloyline && this.chainPloyline.setMap(null);

                var flightPlanCoordinates = []

                for (var i = 0; i < _pointList.length; i++) {
                    flightPlanCoordinates.push({
                        lat: _pointList[i].lat,
                        lng: _pointList[i].lon
                    })
                }
                this.chainPloyline = new google.maps.Polyline({
                    path: flightPlanCoordinates,
                    // geodesic: true,
                    strokeColor: '#23a16d',
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                });

                this.chainPloyline.setMap(this.map);

                this.mapPoiMarker(_pointList)
            }

            if(_trafficList.length > 0){
                if(this.map == ''){
                    this.map = new google.maps.Map(document.getElementById('ScheduleMap'), {
                        center: {lat: 39.920000, lng: 116.460000},
                        zoom: 10
                    })
                }

                var lineSymbol = {
                    path: 'M 0,-1 0,1',
                    strokeOpacity: 1,
                    scale: 3
                }
                

                this.chainTrafficPloyline && this.chainTrafficPloyline.setMap(null);

                for (var i = 0; i < _trafficList.length; i++) {
                    var flightPlanCoordinates = []

                    flightPlanCoordinates.push({
                        lat: _trafficList[i].startLat,
                        lng: _trafficList[i].startLon
                    })
                    flightPlanCoordinates.push({
                        lat: _trafficList[i].endLat,
                        lng: _trafficList[i].endLon
                    })
                    this.chainTrafficPloyline = new google.maps.Polyline({
                        path: flightPlanCoordinates,
                        // geodesic: true,
                        strokeColor: '#23a16d',
                        strokeOpacity: 0,
                        strokeWeight: 2,
                        icons: [{
                            icon: lineSymbol,
                            offset: '0',
                            repeat: '20px'
                        }]
                    });

                    this.chainTrafficPloyline.setMap(this.map);
                    this.mapTrafficMarker(_trafficList[i])
                }
                
            }
        },

        mapPoiMarker(data){
            var vm = this
            // for (var i = 0; i < this.cityMarker.length; i++) {
            //     this.cityMarker[i].setMap(null);
            // }
            
            var _txtArr = []

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement('DIV');
                div.innerHTML = '<div class="mapTrafficMarker">' + (i+1) + '</div>';

                var marker = new RichMarker({
                    map: this.map,
                    position: new google.maps.LatLng(data[i].lat, data[i].lon),
                    draggable: false,
                    flat: true,
                    anchor: RichMarkerPosition.MIDDLE,
                    content: div
                });

                var _infobox

                _txtArr.push(data[i].nameCn)

                google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                    return function() {
                        _infobox = new InfoBubble({
                            borderWidth: 0,
                            borderRadius: 0,
                            backgroundColor: '#22A98E',
                            disableAutoPan: true,
                            hideCloseButton: true,
                            arrowSize: 8,
                            minWidth: 180,
                            maxWidth: 180,
                            minHeight: 38,
                            // maxHeight: 38,
                            padding: 0,
                            content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div></div>',
                        })

                        _infobox.open(this.map, marker);
                    }
                })(marker, i));

                google.maps.event.addListener(marker, 'mouseout', function() {
                    _infobox.close()
                })
            }
        },

        trafficFormat(type){
            if(type == 1){
                return '<i class="iconfont icon-feiji-big"></i>'
            }else if(type == 2){
                return '<i class="iconfont icon-huoche"></i>'
            }else if(type == 3){
                return '<i class="iconfont icon-lunchuang"></i>'
            }else if(type == 4){
                return '<i class="iconfont icon-gongjiaoche"></i>'
            }
        },


        mapTrafficMarker(trafficData){
            var vm = this
            // for (var i = 0; i < this.trafficMarker.length; i++) {
            //     this.trafficMarker[i].setMap(null);
            // }
            
            var _txtArr = []

            var data = [{
                nameCn: trafficData.startName,
                lat: trafficData.startLat,
                lon: trafficData.startLon,
                type: trafficData.traffictype
            },{
                nameCn: trafficData.endName,
                lat: trafficData.endLat,
                lon: trafficData.endLon,
                type: trafficData.traffictype
            }]

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement('DIV');
                div.innerHTML = '<div class="mapTrafficLineMarker">' + this.trafficFormat(data[i].type) + '</div>';

                var marker = new RichMarker({
                    map: this.map,
                    position: new google.maps.LatLng(data[i].lat, data[i].lon),
                    draggable: false,
                    flat: true,
                    anchor: RichMarkerPosition.MIDDLE,
                    content: div
                });

                var _infobox

                // _txtArr.push(data[i].nameCn)

                google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                    return function() {
                        _infobox = new InfoBubble({
                            borderWidth: 0,
                            borderRadius: 0,
                            backgroundColor: '#22A98E',
                            disableAutoPan: true,
                            hideCloseButton: true,
                            arrowSize: 8,
                            minWidth: 180,
                            maxWidth: 180,
                            minHeight: 38,
                            // maxHeight: 38,
                            padding: 0,
                            content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div></div>',
                        })

                        _infobox.open(this.map, marker);
                    }
                })(marker, i));

                google.maps.event.addListener(marker, 'mouseout', function() {
                    _infobox.close()
                })
            }
        },

        setMapMarker(type){
            var vm = this
            var data = []
            for (var i = 0; i < this.poiDataList.length; i++) {
                if(this.poiDataList[i].type != 7){
                    data.push(this.poiDataList[i])
                }
            }

            if(type == 'more'){
                if(data.length == 0){
                    return
                }
            }else{
                if(data.length == 0){
                    for (var i = 0; i < this.markerList.length; i++) {
                        this.markerList[i].setMap(null);
                    }
                    return
                }

                this.markerList = []

                if(this.map == ''){
                    this.map = new google.maps.Map(document.getElementById('ScheduleMap'), {
                        center: {lat: data[0].lat, lng: data[0].lon},
                        zoom: 10
                    })
                }
            }

            var infoBubble
            var infoTimer
            var _txtArr = []

            var _infoBubble = {}
            

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement('DIV');
                // var _class = type == "chain" ? "chain" : ""
                div.innerHTML = '<div class="mapSchedulePoiMarker"></div>';

                var marker = new RichMarker({
                    map: this.map,
                    position: new google.maps.LatLng(data[i].lat, data[i].lon),
                    draggable: false,
                    flat: true,
                    anchor: RichMarkerPosition.MIDDLE,
                    content: div
                });

                this.markerList.push(marker)



                // if(type == 'chain'){
                //     this.chainMarker.push(marker)
                // }else{
                //     this.cityMarker.push(marker)
                // }
                _txtArr.push(data[i].nameCn)

                _infoBubble[i] = {
                    infobox: '',
                    infotimer: '',
                    has: false
                }

                var _infoBubble
                var _infoBubbleTimer

                
                    google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function() {
                            window.addToSchedule = function(){
                                vm.addToSchedule(data[i])
                            }

                            if(!_infoBubble[i].has){
                                _infoBubble[i].infobox = new InfoBubble({
                                    borderWidth: 0,
                                    borderRadius: 0,
                                    backgroundColor: '#22A98E',
                                    disableAutoPan: true,
                                    hideCloseButton: true,
                                    arrowSize: 8,
                                    minWidth: 180,
                                    maxWidth: 180,
                                    minHeight: 38,
                                    // maxHeight: 38,
                                    padding: 0,
                                    content: '<div class="mapMarkerTxt"><div class="tit">'+_txtArr[i]+'</div><a class="addBtn" onclick="addToSchedule()"><i class="iconfont icon-lnicon34"></i></a></div>',
                                })

                                _infoBubble[i].infobox.open(this.map, marker);
                                _infoBubble[i].has = true

                            }else{
                                _infoBubble[i].infobox.close()
                                _infoBubble[i].has = false
                            }
                        }
                    })(marker, i));
                // }
            }

            google.maps.event.addListener(this.map, 'click', function() {
                for (var i in _infoBubble) {
                    if(_infoBubble[i].infobox){
                        _infoBubble[i].infobox.close()
                        _infoBubble[i].has = false
                    }
                }
            });
        },

        changeOpt(item){
            this.citySel = item.nameCn
            this.citySelId = item.cityId

            if(this.map == ''){
                this.initMap()
            }else{
                this.map.setCenter({lat: item.lat, lng: item.lon})
                this.map.setZoom(10)
            }

            this.pageNo = 1
            var tab = this.tabActive
            
            if(tab == 'product' || tab == 'poi'){
                this.hotelHas = false
                this.getPoiData()
            }else if(tab == 'hotel'){
                if(this.dayInfo.scheduleHotels.length > 1){
                    this.hotelHas = true
                    this.getHotelData()
                }else{
                    this.hotelHas = false
                    this.getPoiHotelData()
                }
                // this.getPoiHotelData()
            }else if(tab == 'traffic'){
                this.getTrafficData()
            }else if(tab == 'rent'){
                this.getRentData()
            }
        },

        changeTabs(tab) {
            this.pageNo = 1
            this.tabActive = tab

            if(tab == 'product' || tab == 'poi'){
                this.hotelHas = false
                this.getPoiData()
            }else if(tab == 'hotel'){
                if(this.dayInfo.scheduleHotels.length > 1){
                    this.hotelHas = true
                    this.getHotelData()
                }else{
                    this.hotelHas = false
                    this.getPoiHotelData()
                }
                // this.getPoiHotelData()
            }else if(tab == 'traffic'){
                this.getTrafficData()
            }else if(tab == 'rent'){
                this.getRentData()
            }
        },

        getPoiData(type){
            var _data = {
                cityId: this.citySelId,
                pageSize: this.pageSize,
                pageNo: this.pageNo
            }
            if(this.keywords != ''){
                _data['name'] = this.keywords
            }
            if(this.poiType != 0){
                _data['type'] = this.poiType
            }

            if(this.tabActive == 'product'){
                _data['type'] = 5
            }
            if(this.tabActive == 'hotel'){
                _data['type'] = 6
            }
            this.$http({
                method: 'post',
                url: '/poi/doSearch',
                data: _data
            })
            .then((res)=>{
                this.loadingMore = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    if(type == 'more'){
                        this.poiDataList = this.poiDataList.concat(res.data.data.data)
                    }else{
                        this.poiDataList = res.data.data.data
                    }

                    if(Math.ceil(res.data.data.total/this.pageSize) > this.pageNo){
                        this.loadMoreAble = true
                    }else{
                        this.loadMoreAble = false
                    }

                    this.setMapMarker(type)
                }
            })
        },

        getPoiHotelData(type){
            var _data = {
                cityId: this.citySelId,
                pageSize: this.pageSize,
                pageNo: this.pageNo
            }
            if(this.keywords != ''){
                _data['name'] = this.keywords
            }

            this.$http({
                method: 'post',
                url: '/hotel/doSearch',
                data: _data
            })
            .then((res)=>{
                this.loadingMore = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    if(type == 'more'){
                        this.poiDataList = this.poiDataList.concat(res.data.data)
                    }else{
                        this.poiDataList = res.data.data
                    }

                    if(res.data.data.length == this.pageSize){
                        this.loadMoreAble = true
                    }else{
                        this.loadMoreAble = false
                    }

                    this.setMapMarker(type)
                }
            })
        },

        getTrafficData(type){
            // var _data = {
            //     cityId: this.citySelId,
            //     pageSize: this.pageSize,
            //     pageNo: this.pageNo
            // }
            // if(this.keywords != ''){
            //     _data['name'] = this.keywords
            // }

            this.$http({
                method: 'post',
                url: '/traffic/doSearch',
                data: {
                    trafficType: this.trafficTabActive
                }
            })
            .then((res)=>{
                this.loadingMore = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.trafficDataList = res.data.data.data
                    // console.log(res.data)
                    // if(type == 'more'){
                    //     this.poiDataList = this.poiDataList.concat(res.data.data)
                    // }else{
                    //     this.poiDataList = res.data.data
                    // }

                    // if(res.data.data.length == this.pageSize){
                    //     this.loadMoreAble = true
                    // }else{
                    //     this.loadMoreAble = false
                    // }

                    // this.setMapMarker(type)
                }
            })
        },

        getRentData(type){
            // var _data = {
            //     cityId: this.citySelId,
            //     pageSize: this.pageSize,
            //     pageNo: this.pageNo
            // }
            // if(this.keywords != ''){
            //     _data['name'] = this.keywords
            // }

            this.$http({
                method: 'post',
                url: '/rent/doSearch',
                // data: _data
            })
            .then((res)=>{
                this.loadingMore = false
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.rentDataList = res.data.data.data
                    // if(type == 'more'){
                    //     this.poiDataList = this.poiDataList.concat(res.data.data)
                    // }else{
                    //     this.poiDataList = res.data.data
                    // }

                    // if(res.data.data.length == this.pageSize){
                    //     this.loadMoreAble = true
                    // }else{
                    //     this.loadMoreAble = false
                    // }

                    // this.setMapMarker(type)
                }
            })
        },


        getHotelData(){
            var _hotelId = this.dayInfo.scheduleHotels[0].id

            this.$http({
                method: 'post',
                url: '/tripnote/schedulehotel/doDetail/' + _hotelId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.hotelDetail = res.data.data
                }
            })
        },


        addToSchedule(item){
            this.addToScheduleItem = item
            if(this.poiSel.indexOf(item.id)>-1){
                this.addToScheduleConfirmVisible = true
            }else{
                this.addToScheduleAction()
            }
        },

        addToScheduleAction(){
            var item = this.addToScheduleItem
            if(item.type == 6){
                this.hotelId = item.id
                this.hotelNameCn = item.nameCn
                this.hotelNameEn = item.nameEn
                this.addHotelDialogVisible = true
            }else{
                this.$http({
                    method: 'post',
                    url: '/tripnote/scheduletrip/poi/doAdd',
                    data: {
                        scheduleId: this.dayId,
                        sourceId: item.id,
                        sort: this.dayInfo.scheduletrips.length + 1
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
                        this.addToScheduleConfirmVisible = false
                        Bus.$emit('refreshSchedule', true)
                    }
                })
            }
        },

        addTrafficToSchedule(item){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletrip/traffic/doAdd',
                data: {
                    scheduleId: this.dayId,
                    sourceId: item.id,
                    sort: this.dayInfo.scheduletrips.length + 1
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
                    Bus.$emit('refreshSchedule', true)
                }
            })
        },

        addCarToSchedule(item){
            this.$http({
                method: 'post',
                url: '/tripnote/scheduletrip/rent/doAdd',
                data: {
                    scheduleId: this.dayId,
                    sourceId: item.id
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
                    Bus.$emit('refreshSchedule', true)
                }
            })
        },


        addHotelToSchedule(){
            var vm = this
            var _scheduleHotels = []

            for (var i = 0; i < this.routeInfo.dayInfo.length; i++) {
                if(i == this.hotelStartDay){
                    _scheduleHotels.push({
                        "checkInType": 1,
                        "scheduleId": this.routeInfo.dayInfo[i].id,
                        "sourceId": this.hotelId
                    })
                }
                if(i > this.hotelStartDay && i < this.hotelEndDay){
                    _scheduleHotels.push({
                        "checkInType": 0,
                        "scheduleId": this.routeInfo.dayInfo[i].id,
                        "sourceId": this.hotelId
                    },{
                        "checkInType": 1,
                        "scheduleId": this.routeInfo.dayInfo[i].id,
                        "sourceId": this.hotelId
                    })
                }
                if(i == this.hotelEndDay){
                    _scheduleHotels.push({
                        "checkInType": 0,
                        "scheduleId": this.routeInfo.dayInfo[i].id,
                        "sourceId": this.hotelId
                    })
                }
            }

            $.ajax({
                url: '/tripnote/scheduletrip/hotel/doAdd',
                type: 'post',
                data: JSON.stringify({
                    "checkIn": 'D'+ (this.hotelStartDay + 1),
                    "checkOut": 'D'+ (this.hotelEndDay + 1),
                    "scheduleHotels": _scheduleHotels,
                    remark: this.hotelRemark
                }),
                contentType: 'application/json',
                success: function(res){
                    if(res.code == -1){
                        vm.$message({
                            message: res.message,
                            type: 'error',
                            duration: 2000
                        });
                    }else{
                        Bus.$emit('refreshSchedule', true)
                        vm.addHotelDialogVisible = false

                        setTimeout(function(){
                            vm.hotelHas = true
                            vm.getHotelData()
                        }, 500)

                        
                    }
                }
            })


            // this.$http({
            //     method: 'post',
            //     url: '/tripnote/scheduletrip/hotel/doAdd',
            //     data: JSON.stringify({
            //         "scheduleHotels": _scheduleHotels
            //     })
            // })
            // .then((res)=>{
            //     if(res.data.code == -1){
            //         this.$message({
            //             message: res.data.message,
            //             type: 'error',
            //             duration: 2000
            //         })
            //     }else{
            //         Bus.$emit('refreshSchedule', true)
            //     }
            // })
        },

        closeBox(){
            this.$store.dispatch('setDayScheduleActive', false)
            this.$router.push({ path: '/route/' + this.$route.params.routeId + '/day/' + this.$route.params.dayId})
        },

        setSchedule(){
            this.$store.dispatch('setDayScheduleActive', !this.setDayScheduleActive)
        },

        getDayDetail(){
            console.log(2)
            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doDeail/' + this.dayId,
            })
            .then((res)=>{
                if(res.data.code == -1){
                    this.$message({
                        message: res.data.message,
                        type: 'error',
                        duration: 2000
                    })
                }else{
                    this.dayInfo = res.data.data
                }
            })
        },

        imgFormat(imgurl){
            if(imgurl){
                return imgurl
            }else{
                return require('../../assets/images/route_pic_blank.png')
            }
        },

        saveDayIntro(){
            this.$http({
                method: 'post',
                url: '/tripnote/schedule/doUpdate/' + this.dayId,
                data: {
                    introduction: this.dayInfo.introduction
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

        searchPoi(){
            this.pageNo = 1
            this.getPoiData()
        },

        cancelSearch(){
            this.keywords = ''
            this.keywordsActive = false

            this.pageNo = 1
            this.getPoiData()
        }
    },
}
</script>
<style lang="less" scope>
.dayScheduleSet{
    top: 80px;
    bottom: 20px;
    left: 500px;
    right: 20px;
    transition: all .3s ease;
    &.active{
        left: 180px;
    }
    &.cityNull{
        .tabs,
        .detail{
            display: none;
        }
        .googleMap{
            left: 0;
        }
    }
    .header{
        .tabs{
            float: left;
            margin-left: -15px;
            li{
                z-index: 101;
                position: relative;
                float: left;
                margin-right: 10px;
                padding: 0 18px;
                font-size: 16px;
                line-height: 53px;
                cursor: pointer;
                &:hover{
                    color: #23a16d;
                }
                &.active{
                    border-bottom: 2px solid #23a16d;
                    color: #23a16d;
                }

                .list{
                    &.show{
                        display: block;
                    }
                    display: none;
                    position: absolute;
                    top: 53px;
                    left: 0;
                    background: #fff;
                    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
                    border: 1px solid #e2e2e2;
                    .item{
                        width: 100px;
                        padding: 8px 15px;
                        line-height: 22px;
                        cursor: pointer;
                        font-size: 14px;
                        color: #666;
                        i{
                            margin-right: 5px;
                        }
                        
                        &.active{
                            color: #23a16d;
                            background: #E9F1F1;
                            &:hover{
                                background: #E9F1F1;
                            }
                        }
                        &:hover{
                            background: #F5F7FA;
                        }
                    }
                }
            }
        }
        .opts{
            height: 54px;
            margin-right: -15px;
            border-left: 1px solid #EAEDF1;
            &:hover{
                background: #f1f1f1;
            }
            .closeBox{
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
    
    .detail{
        width: 300px;
        .head{
            position: relative;
            height: 50px;
            padding: 8px 10px 8px 5px;
            border-bottom: 1px solid #EAEDF1;
            .tit{
                float: left;
                font-size: 14px;
            }
            .el-select{
                .el-input{
                    float: right;
                    width: 110px;
                    .el-input__inner{
                        border: 0;
                    }
                    .el-input__suffix{
                        float: left;
                        .el-select__caret{
                            &:before{
                                content: '\E60C';
                            }
                        }
                    }
                }
            }
            .keywords{
                position: absolute;
                z-index: 1001;
                right: 10px;
                top: 10px;
                width: 130px;
                height: 30px;
                background: #fff;
                transition: all .2s ease;
                .el-input__inner{
                    height: 30px;
                    padding-left: 25px;
                }
                .el-input__prefix{
                    left: 2px;
                    top: 0;
                    .el-input__icon{
                        line-height: 28px;
                    } 
                }
                
                
                &.active{
                    width: 280px;
                    .el-input__inner{
                        border: 0;
                    }
                }
            }
            .cancelSearch{
                position: absolute;
                z-index: 1002;
                right: 10px;
                top: 10px;
                height: 30px;
                width: 30px;
                text-align: center;
                i{
                    line-height: 30px;
                    font-size: 20px;
                    font-weight: bold;
                    color: #bbb;
                }
                &:hover{
                    i{
                        color: #999;
                    }
                }
            }
        }
        .poiList{
            position: absolute;
            top: 51px;
            bottom: 0;
            left: 0;
            width: 300px;
            overflow-y: auto;
            li{
                position: relative;
                overflow: hidden;
                margin: 15px;
                border: 1px solid #EAEDF1;
                transition: all .2s ease;
                cursor: pointer;

                &:hover{
                    -webkit-box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    -moz-box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    box-shadow: rgba(0,0,0,.1) 0 0 8px;
                    transform: scale(1.03);
                    -ms-transform: scale(1.03);
                    -moz-transform: scale(1.03);
                    -webkit-transform: scale(1.03);
                    -o-transform: scale(1.03);
                }
                .pic{
                    float: left;
                    width: 88px;
                    height: 88px;
                    background-size: cover;
                    background-position: center;
                }
                .detail{
                    float: left;
                    width: 125px;
                    margin-left: 10px;
                    h3{
                        width: 100%;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                        margin-top: 5px;
                        line-height: 20px;
                        font-size: 14px;
                        i{
                            color: #23a16d;
                        }
                        span{
                            margin-right: 3px;
                        }
                    }
                    .subTit{
                        width: 100%;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;

                        height: 36px;
                        overflow: hidden;
                        font-size: 12px;
                        color: #9CA7AF;
                    }
                    .price{
                        font-size: 14px;
                        color: #23a16d;
                    }
                }
                .opts{
                    position: absolute;
                    top: 50%;
                    right: 10px;
                    margin-top: -15px;
                    width: 28px;
                    height: 28px;
                    line-height: 30px;
                    cursor: pointer;
                    span{
                        display: block;
                        width: 28px;
                        height: 28px;
                        // i{
                        //     opacity: 0.8;
                        //     font-size: 28px;
                        //     color: #23a16d;
                        // }
                        // &:hover{
                        //     i{
                        //         opacity: 1;
                        //     }
                        // }
                        &.add{
                            position: absolute;
                            top: 0;
                            left: 0;
                            z-index: 2;
                            height: 28px;
                            width: 28px;
                            border: 2px solid #23a16d;
                            color: #23a16d;
                            line-height: 30px;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 26px;
                            &:hover{
                                background: #23a16d;;
                                color: #fff;
                            }
                            i{
                                font-size: 16px;
                                font-weight: bold;
                            }
                        }
                        &.selected{
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 28px;
                            height: 28px;
                            background: #23a16d;
                            z-index: 3;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 30px;
                            i{
                                font-size: 16px;
                                font-weight: bold;
                                color: #fff;
                            }
                        }
                    }
                    &:hover{
                        span{
                            &.selected{
                                display: none;
                            }
                        }
                    }
                }
            }

            .loadMore{
                line-height: 30px;
                text-align: center;
            }
        }

        .hotelPic{
            width: 300px;
            height: 150px;
            background-size: cover;
            background-position: center;
        }
        .hotelTit{
            padding: 10px 15px;
            line-height: 30px;
            font-size: 18px;
            i{
                margin-right: 8px;
                color: #23a16d;
            }
        }
        .hotelCheckTime{
            margin: 0 15px;
            label{
                color: #a0abb3;
                &:last-child{
                    margin-left: 50px;
                }
            }
        }
        .hotelRemark{
            margin: 15px;
            label{
                float: left;
                color: #a0abb3;
                &:last-child{
                    margin-left: 50px;
                }
            }
            .text{
                margin-left: 60px; 
            }
        }
    }

    .googleMap{
        position: absolute;
        top: 0;
        left: 300px;
        right: 0;
        bottom: 0;
        border-left: 1px solid #EAEDF1;
    }

    .moreWidth{
        .googleMap{
            left: 580px;
        }
    }
    .detailTraffic{
        width: 580px;
        .head{
            position: relative;
            height: 45px;
            padding: 8px 10px 5px 5px;
            .tit{
                float: left;
                font-size: 14px;
            }
            .el-select{
                .el-input{
                    float: right;
                    width: 110px;
                    .el-input__inner{
                        border: 0;
                    }
                    .el-input__suffix{
                        float: left;
                        .el-select__caret{
                            &:before{
                                content: '\E60C';
                            }
                        }
                    }
                }
            }
        }
        .filterBox{
            margin: 0 15px 10px;
            .customerSel{
                float: left;
                width: 110px;
                margin-right: 10px;
            }
            .dateSel{
                float: left;
                width: 220px;
                margin-right: 10px;
            }
            .timeSel{
                float: left;
                width: 110px;
                margin-right: 10px;
                
            }
            .searchBtn{
                float: right;
                height: 32px;
                padding: 0 15px;
                border: 1px solid #23a16d;
                color: #23a16d;
            }
        }
        .tabs{
            border-bottom: 1px solid #EAEDF1;
            height: 35px;

            li{
                float: left;
                width: 25%;
                padding: 0 18px;
                line-height: 35px;
                a{
                    display: block;
                    line-height: 35px;
                    color: #555;
                    text-align: center;
                    font-size: 14px;
                    i{
                        vertical-align: middle;
                        margin-right: 3px;
                    }
                    &.active{
                        height: 35px;
                        border-bottom: 2px solid #23a16d;
                        color: #23a16d;
                    }
                }
            }
        }
        .trafficTabCon{
            position: absolute;
            left: 0;
            bottom: 0;
            width: 580px;
            top: 128px;
            overflow-y: auto;
            ul{
                padding: 0 15px 15px;
            }
            li{
                position: relative;
                margin-top: 20px;
                padding: 10px 0 10px 15px;
                border: 1px solid #EAEDF1;

                .airplaneName{
                    float: left;
                    width: 140px;
                    margin-top: 4px;
                    .name{
                        line-height: 22px;
                        .iconPlane{
                            display: inline-block;
                            vertical-align: middle;
                            width: 16px;
                            height: 16px;
                            margin-top: -2px;
                            background: url(../../assets/images/logo_plane.png);
                            background-position: 0 -192px;
                        }
                    }
                    .model{
                        margin-left: 20px;
                        line-height: 22px;
                        color: #a0abb3;
                    }
                }
                .no{
                    float: left;
                    width: 68px;
                    margin-top: 4px;
                    .name{
                        line-height: 22px;
                    }
                    .sel{
                        position: relative;
                        height: 26px;
                        .selected{
                            line-height: 22px;
                            color: #23a16d; 
                            cursor: pointer;
                            i{
                                font-size: 12px;
                            }
                        }
                        &:hover{
                            .selOpts{
                                display: block;
                            }
                        }
                        .selOpts{
                            display: none;
                            z-index: 1;
                            position: absolute;
                            top: 26px;
                            left: -16px;
                            width: 455px;
                            padding: 10px;
                            background: #fff;
                            box-shadow: 0 5px 15px rgba(0,0,0,.15);
                            border: 1px solid #DDE2E7;

                            &:before{
                                content: '';
                                position: absolute;
                                width: 10px;
                                height: 10px;
                                top: -6px;
                                left: 68px;
                                background: #fff;
                                border-top: 1px solid #DDE2E7;
                                border-right: 1px solid #DDE2E7;
                                transform: rotate(-45deg);
                            }
                            .selOptHeader{
                                overflow: hidden;
                                background: #F6F6F6;
                                line-height: 30px; 
                            }
                            .col1{
                                float: left;
                                width: 105px;
                                padding: 0 5px;
                            }
                            .col2{
                                float: left;
                                width: 165px;
                                padding: 0 5px;
                            }
                            .col3{
                                float: left;
                                width: 70px;
                                padding: 0 5px;
                            }
                            .col4{
                                float: left;
                                width: 60px;
                                padding: 0 5px;
                            }
                            .col5{
                                float: left;
                                width: 30px;
                                padding: 0 5px;
                            }
                            .itemList{
                                line-height: 34px;
                                .col4{
                                    color: #23a16d;
                                }
                            }
                        }
                    }
                }

                .startPoint{
                    float: left;
                    width: 90px;
                    margin-top: 4px;
                    margin-left: 20px;
                    line-height: 22px;
                    text-align: right;
                }
                .arrow{
                    float: left;
                    width: 30px;
                    text-align: center;
                    line-height: 52px;
                    i{
                        font-size: 20px;
                    }
                }
                .endPoint{
                    float: left;
                    width: 110px;
                    margin-left: 5px;
                    margin-top: 4px;
                    line-height: 22px;
                }

                &.airplane{
                    .startPoint,
                    .endPoint{
                        width: 100px;
                    }
                    .startPoint{
                        margin-left: 15px;
                    }
                    .price{
                        margin-left: 0;
                    }
                }
                &.train{
                    .startPoint,
                    .endPoint{
                        width: 80px;
                    }
                    .startPoint{
                        margin-left: 10px;
                    }
                    .price{
                        margin-left: 20px;
                        font-size: 18px;
                        color: #a0abb3;
                    }
                }

                &.bus{
                    // .startPoint,
                    // .endPoint{
                    //     width: 80px;
                    // }
                    .startPoint{
                        margin-left: 0px;
                    }
                    .price{
                        margin-left: 20px;
                        font-size: 18px;
                        color: #a0abb3;
                    }
                }


                .time{
                    float: left;
                    line-height: 52px;
                    color: #a0abb3;
                }
                .price{
                    float: left;
                    margin-left: 15px;
                    line-height: 52px;
                    strong{
                        font-size: 18px;
                        color: #23a16d;
                    }
                }
                .opts{
                    position: absolute;
                    top: 50%;
                    right: 10px;
                    margin-top: -26px;
                    width: 52px;
                    height: 52px;
                    line-height: 30px;
                    cursor: pointer;
                    border-left: 1px solid #eaedf1;
                    span{
                        display: block;
                        width: 28px;
                        height: 28px;
                        margin: 12px 0 0 15px;
                        // i{
                        //     opacity: 0.8;
                        //     font-size: 28px;
                        //     color: #23a16d;
                        // }
                        // &:hover{
                        //     i{
                        //         opacity: 1;
                        //     }
                        // }
                        &.add{
                            position: absolute;
                            top: 0;
                            left: 0;
                            z-index: 2;
                            height: 28px;
                            width: 28px;
                            border: 2px solid #23a16d;
                            color: #23a16d;
                            line-height: 30px;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 26px;
                            &:hover{
                                background: #23a16d;;
                                color: #fff;
                            }
                            i{
                                font-size: 16px;
                                font-weight: bold;
                            }
                        }
                        &.selected{
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 28px;
                            height: 28px;
                            background: #23a16d;
                            z-index: 3;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 30px;
                            i{
                                font-size: 16px;
                                font-weight: bold;
                                color: #fff;
                            }
                        }
                    }
                    &:hover{
                        span{
                            &.selected{
                                display: none;
                            }
                        }
                    }
                }
                // .add{
                //     float: right;
                //     width: 60px;
                //     height: 52px;
                //     line-height: 52px;
                //     border-left: 1px solid #EAEDF1;

                //     a{
                //         display: block;
                //         height: 28px;
                //         width: 28px;
                //         margin: 12px auto;
                //         border: 2px solid #23a16d;
                //         color: #23a16d;
                //         line-height: 30px;
                //         border-radius: 100%;
                //         text-align: center;
                //         line-height: 26px;
                //         &:hover{
                //             background: #23a16d;;
                //             color: #fff;
                //         }
                //         i{
                //             font-size: 16px;
                //             font-weight: bold;
                //         }
                //     }
                // }
            }
        }
    }

    .detailRent{
        width: 580px;
        .head{
            position: relative;
            height: 45px;
            padding: 8px 10px 5px 5px;
            .tit{
                float: left;
                font-size: 14px;
            }
            .el-select{
                .el-input{
                    float: right;
                    width: 110px;
                    .el-input__inner{
                        border: 0;
                    }
                    .el-input__suffix{
                        float: left;
                        .el-select__caret{
                            &:before{
                                content: '\E60C';
                            }
                        }
                    }
                }
            }
        }
        .filterBox{
            margin: 0 15px;
            padding: 10px;
            border: 1px solid #EAEDF1;
            .searchOpt{
                float: left;
                padding-right: 20px;
                border-right: 1px solid #EAEDF1;
                .item{
                    overflow: hidden;
                    margin-bottom: 10px;
                    &:last-child{
                        margin-bottom: 0;
                    }
                    label{
                        float: left;
                        width: 42px;
                        line-height: 32px;
                        font-size: 14px;
                    }
                    .pointSel{
                        float: left;
                        width: 150px;
                    }
                    .dateSel{
                        float: left;
                        width: 130px;
                        margin-left: 10px;
                    }
                    .timeSel{
                        float: left;
                        width: 75px;
                        margin-left: -1px;
                        .el-input__inner{
                            padding: 0 5px !important;
                        }
                        .el-input__prefix{
                            display: none !important;
                        }
                    }
                }
            }

            .searchBtnWrap{
                float: right;
                text-align: center;
                margin-right: 10px;
                p{
                    line-height: 24px;
                }

                .searchBtn{
                    float: right;
                    height: 32px;
                    padding: 0 15px;
                    border: 1px solid #23a16d;
                    color: #23a16d;
                }
            }
        }

        .filterList{
            margin: 15px 15px 15px;
            padding-bottom: 15px;
            border-bottom: 1px solid #EAEDF1;
            dl{
                overflow: hidden;
                margin-top: 10px;
                dt{
                    float: left;
                    width: 60px;
                    color: #a0abb3;
                    line-height: 20px;
                }
                dd{
                    margin-left: 60px;
                    a{
                        float: left;
                        margin-right: 10px;
                        padding: 0 4px;
                        line-height: 20px;
                        color: #555;
                        &.active{
                            background: #23a16d;
                            color: #fff;
                        }
                    }
                }
            }
        }

        .rentCon{
            position: absolute;
            left: 0;
            bottom: 0;
            width: 580px;
            top: 312px;
            overflow-y: auto;
            ul{
                padding: 0 15px 15px;
            }
            li{
                position: relative;
                margin-top: 20px;
                padding: 10px 0 10px 15px;
                border: 1px solid #EAEDF1;
                .pic{
                    float: left;
                    width: 100px;
                    height: 75px;
                    background-size: cover;
                    background-position: center;
                    img{
                        display: block;
                        width: 100px;
                        height: 70px;
                    }
                }
                .name{
                    float: left;
                    width: 260px;
                    margin-top: 5px;
                    margin-left: 15px;
                    .tit{
                        line-height: 32px;
                        font-size: 16px;
                        span{
                            font-size: 12px;
                            margin-left: 10px;
                            color: #a0abb3;
                        }
                    }
                    p{
                        line-height: 22px;
                        a{
                            color: #23a16d;
                        }
                    }
                }
                
                .price{
                    float: left;
                    margin-left: 10px;
                    line-height: 70px;
                    strong{
                        font-size: 18px;
                        color: #23a16d;
                    }
                }
                .opts{
                    position: absolute;
                    top: 50%;
                    right: 10px;
                    margin-top: -37px;
                    width: 60px;
                    height: 75px;
                    line-height: 30px;
                    border-left: 1px solid #eaedf1;
                    span{
                        display: block;
                        width: 28px;
                        height: 28px;
                        margin: 22px 0 0 20px;
                        // i{
                        //     opacity: 0.8;
                        //     font-size: 28px;
                        //     color: #23a16d;
                        // }
                        // &:hover{
                        //     i{
                        //         opacity: 1;
                        //     }
                        // }
                        &.add{
                            position: absolute;
                            top: 0;
                            left: 0;
                            z-index: 2;
                            height: 28px;
                            width: 28px;
                            border: 2px solid #23a16d;
                            color: #23a16d;
                            line-height: 30px;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 26px;
                            cursor: pointer;
                            &:hover{
                                background: #23a16d;;
                                color: #fff;
                            }
                            i{
                                font-size: 16px;
                                font-weight: bold;
                            }
                        }
                        &.selected{
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 28px;
                            height: 28px;
                            background: #23a16d;
                            z-index: 3;
                            border-radius: 100%;
                            text-align: center;
                            line-height: 30px;
                            i{
                                font-size: 16px;
                                font-weight: bold;
                                color: #fff;
                            }
                        }
                    }
                    // &:hover{
                    //     span{
                    //         &.selected{
                    //             display: none;
                    //         }
                    //     }
                    // }
                }
                // .add{
                //     float: right;
                //     width: 60px;
                //     height: 70px;
                //     line-height: 70px;
                //     border-left: 1px solid #EAEDF1;
                //     a{
                //         display: block;
                //         height: 28px;
                //         width: 28px;
                //         margin: 22px auto;
                //         border: 2px solid #23a16d;
                //         color: #23a16d;
                //         line-height: 30px;
                //         border-radius: 100%;
                //         text-align: center;
                //         line-height: 26px;
                //         &:hover{
                //             background: #23a16d;;
                //             color: #fff;
                //         }
                //         i{
                //             font-size: 16px;
                //             font-weight: bold;
                //         }
                //     }
                // }
            }
        }
    }
}

.mapSchedulePoiMarker{
    width: 10px;
    height: 10px;
    background: #777;
    border-radius: 100%;
    opacity: 0.8;
    &:hover{
        width: 12px;
        height: 12px;
        margin: -1px 0 0 -1px;
        opacity: 1;
    }
    cursor: pointer;
}

.addHotelCon{
    .header{
        .icon{
            float: left;
            line-height: 40px;
            i{
                font-size: 20px;
                color: #23a16d;
            }
        }
        .tit{
            margin-left: 40px;
            font-size: 18px;
            font-weight: bold;
        }
        .subTit{
            margin-left: 40px;
            font-size: 12px;
            color: #999;
        }
    }
    .daySel{
        overflow: hidden;
        margin: 15px 0;
        label{
            float: left;
            width: 40px;
            line-height: 32px;
            font-size: 14px;
        }
        .el-select{
            float: left;
            margin-right: 30px;
        }
        .el-textarea{
            float: left;
            width: 414px;
            margin-right: 30px;
        }
    }
}

.mapTrafficLineMarker{
    position: relative;
    z-index: 100;
    width: 28px;
    height: 28px;
    background: #23a16d;
    border-radius: 100%;
    // margin-top: -20px;
    // background: url(../../assets/images/poi_marker.png);
    text-align: center;
    line-height: 28px;
    font-size: 14px;
    color: #fff;
    text-align: center;
}


</style>