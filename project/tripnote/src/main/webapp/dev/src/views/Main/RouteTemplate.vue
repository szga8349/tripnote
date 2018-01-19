<template>
    <div class="routeTemplate">
        <div class="header">
            <a href="javascript:;" class="backBtn" @click="sendToParentCancel"></a>
            <div class="typeSel">
                <label>选择模板：</label>
                <el-select v-model="templateTypeSel" placeholder="请选择" class="templateType">
                    <el-option
                        v-for="item in tempateTypeOpts"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                <div class="templateName">{{templateName}}</div>
            </div>

            <div class="btns">
                <el-button @click="sendToParentCancel">取 消</el-button>
                <el-button type="primary" @click="sendToParentSure">确 定</el-button>
            </div>
        </div>

        <div class="detailBox">
            <div class="detailHeader">
                <div class="tit">
                    我定制过的行程
                </div>
                <div class="filterBox">
                    <ul>
                        <li>
                            <label>行程天数：</label>
                            <el-select v-model="filterDayType" placeholder="请选择" class="selType">
                                <el-option
                                    v-for="item in filterDayTypeOpts"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </li>

                        <li>
                            <div class="filterKeywords">
                                <input type="text" placeholder="搜索关键字">
                                <a href="javascript:;" class="btnSubmit"></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="detailCon">
                <div class="routeNav">
                    <ul>
                        <li>
                            <div class="tit">新疆自驾古城之旅（一）</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">新疆自驾古城之旅（二）</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">浪漫大道之海德堡及城堡、宫殿</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">英国伦敦4天3夜行程安排博物馆、购物必去目的地攻略</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">阿布扎比（Abu Dhabi）一日游：令人激动的中途停留日记</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">新疆自驾古城之旅（二）</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">浪漫大道之海德堡及城堡、宫殿</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">英国伦敦4天3夜行程安排博物馆、购物必去目的地攻略</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">阿布扎比（Abu Dhabi）一日游：令人激动的中途停留日记</div>
                            <div class="checkboxC"></div>
                        </li><li>
                            <div class="tit">新疆自驾古城之旅（二）</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">浪漫大道之海德堡及城堡、宫殿</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">英国伦敦4天3夜行程安排博物馆、购物必去目的地攻略</div>
                            <div class="checkboxC"></div>
                        </li>
                        <li>
                            <div class="tit">阿布扎比（Abu Dhabi）一日游：令人激动的中途停留日记</div>
                            <div class="checkboxC"></div>
                        </li>
                    </ul>
                </div>
                <div class="routeDetail">
                    <div class="routeDetailHeader">
                        <div class="tit">选择导出内容</div>
                        <div class="checkbox">
                            <label>全选</label>
                            <div class="checkboxC"></div>
                        </div>
                    </div>

                    <div class="dayList">
                        <ul>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">行程总览</div>
                                <div class="txt">
                                    浪漫大道上有许多很值得参观的宫殿和城堡，本次行程就以,[object bject],以及宫殿为主，带领游客充分体验西方具有历史气息的景点，例如,[object Object],，也会参观,[object Object],和,[object Object],。
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D1 古城之旅</div>
                                <div class="txt">
                                    行程第一天，清早从法兰克福出发，穿过施佩萨尔特山脉沿着浪漫大道向维尔茨堡前行，直至抵达陶伯河上的罗腾堡，这一被誉为“中世纪宝藏”的德国城市。您可领略老城区小街巷的风貌，或是参雅各布教堂里的圣血祭坛，那是提尔曼·里门施奈德的大作。夜间您也可以在守夜者或是当地导游的带领下夜游小
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D2 古城之旅</div>
                                <div class="txt">
                                    行程第一天，清早从法兰克福出发，穿过施佩萨尔特山脉沿着浪漫大道向维尔茨堡前行，直至抵达陶伯河上的罗腾堡，这一被誉为“中世纪宝藏”的德国城市。您可领略老城区小街巷的风貌，或是参雅各布教堂里的圣血祭坛，那是提尔曼·里门施奈德的大作。夜间您也可以在守夜者或是当地导游的带领下夜游小
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D3 古城之旅</div>
                                <div class="txt">
                                    浪漫大道上有许多很值得参观的宫殿和城堡，本次行程就以,[object bject],以及宫殿为主，带领游客充分体验西方具有历史气息的景点，例如,[object Object],，也会参观,[object Object],和,[object Object],。
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D4 古城之旅</div>
                                <div class="txt">
                                    行程第一天，清早从法兰克福出发，穿过施佩萨尔特山脉沿着浪漫大道向维尔茨堡前行，直至抵达陶伯河上的罗腾堡，这一被誉为“中世纪宝藏”的德国城市。您可领略老城区小街巷的风貌，或是参雅各布教堂里的圣血祭坛，那是提尔曼·里门施奈德的大作。夜间您也可以在守夜者或是当地导游的带领下夜游小
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D5 古城之旅</div>
                                <div class="txt">
                                    行程第一天，清早从法兰克福出发，穿过施佩萨尔特山脉沿着浪漫大道向维尔茨堡前行，直至抵达陶伯河上的罗腾堡，这一被誉为“中世纪宝藏”的德国城市。您可领略老城区小街巷的风貌，或是参雅各布教堂里的圣血祭坛，那是提尔曼·里门施奈德的大作。夜间您也可以在守夜者或是当地导游的带领下夜游小
                                </div>
                            </li>
                            <li>
                                <div class="checkboxC"></div>
                                <div class="tit">D6 古城之旅</div>
                                <div class="txt">
                                    浪漫大道上有许多很值得参观的宫殿和城堡，本次行程就以,[object bject],以及宫殿为主，带领游客充分体验西方具有历史气息的景点，例如,[object Object],，也会参观,[object Object],和,[object Object],。
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        templateType: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            tempateTypeOpts: [{
                value: 1,
                label: '我的模板'
            },{
                value: 2,
                label: '我定制过的行程'
            },{
                value: 3,
                label: '系统模板'
            }],

            filterDayTypeOpts: [{
                value: 1,
                label: '全部'
            }, {
                value: 2,
                label: '1-3天'
            }, {
                value: 3,
                label: '4-8天'
            }, {
                value: 4,
                label: '9-12天'
            }, {
                value: 5,
                label: '13-19天'
            }, {
                value: 6,
                label: '20天及以上'
            }],
            filterDayType: 1,

            checked: false,
            
            dialogAddVisible: false,
            templateName: '请点击下面选择模板',
            templateTypeSel: ''
        }
    },
    created(){
        this.templateTypeSel = this.templateType
    },
    // computed: {
    //     templateTypeSel(){
    //         return this.templateType
    //     }
    // },
    methods: {
        sendToParentSure(){
            this.$emit('callbackSure', {
                templateType: this.templateTypeSel,
                templateName: this.templateName
            })
        },
        sendToParentCancel(){
            this.$emit('callbackCancel')
        }
    },
}
</script>
<style lang="less" scope>
.routeTemplate{
    z-index: 2001;
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: #F2F4F8;

    .header{
        height: 60px;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.04);
        .backBtn{
            float: left;
            width: 95px;
            height: 24px;
            margin-top: 18px;
            background: url(../../assets/images/icon_back.png) center center no-repeat;
            border-right: 1px solid #DADEE5;
        }
        .typeSel{
            float: left;
            margin-top: 14px;
            margin-left: 20px;
            line-height: 32px;
            label{
                float: left;
                font-size: 16px;
            }
            .templateType{
                float: left;
                border: 0;
                .el-input{
                    width: 155px;
                }
                .el-input__inner{
                    border: 0;
                    font-size: 16px;
                }
                .el-icon-arrow-up:before{
                    content: "\E60B"
                }
                .el-input .el-select__caret{
                    transform: none;
                }
            }
            .templateName{
                float: left;
                margin-left: 10px;
                font-size: 16px;
            }
        }
        .btns{
            float: right;
            margin: 12px 20px;
            .el-button{
                height: 36px;
                padding: 5px 35px;
            }
        }
    }

    .detailBox{
        // width: 1200px;
        margin: 20px;
        background: #fff;
        box-shadow: 0 0 10px rgba(0,0,0,.1);
    }
    .detailHeader{
        height: 54px;
        border-bottom: 1px solid #eaedf1;
        .tit{
            float: left;
            margin-left: 20px;
            padding-left: 22px;
            background: url(../../assets/images/icon_map_point.png) left center no-repeat;
            line-height: 56px;
            font-size: 16px;
        }
        .filterBox{
            float: right;
            margin: 10px 5px 0 0;
            li{
                float: left;
                padding: 0 15px;
                border-left: 1px solid #EBEEF2;
                font-size: 14px;

                .selType{
                    border: 0;
                    .el-input{
                        width: 100px;
                    }
                    .el-input__inner{
                        border: 0;
                        font-size: 14px;
                    }
                    .el-icon-arrow-up:before{
                        content: "\E60B"
                    }
                    .el-input .el-select__caret{
                        transform: none;
                    }
                }
                .filterKeywords{
                    width: 215px;
                    height: 34px;
                    border-radius: 34px;
                    background: #EDF1F2;
                    input{
                        height: 34px;
                        padding-left: 17px;
                        line-height: 34px;
                        background: none;
                        border: 0;
                    }
                    .btnSubmit{
                        float: right;
                        width: 45px;
                        height: 34px;
                        background: url(../../assets/images/icon_search.png) center center no-repeat;
                    }
                    &:active,
                    &:hover{
                        background: #eee;
                    }
                }
            }
        }
    }
    .detailCon{
        background: #fff;
    }
    .routeNav{
        position: absolute;
        top: 134px;
        bottom: 20px;
        width: 320px;
        overflow-y: auto;
        background: #fff;
        box-shadow: 0 3px 10px rgba(0,0,0,.1);
        li{
            position: relative;
            height: 100px;
            padding: 12px 40px 12px 20px;
            border-bottom: 1px solid #EBEEF2;
            .tit{
                font-size: 16px;
            }
            .checkboxC{
                display: none;
                position: absolute;
                right: 16px;
                top: 16px;
            }
            &.active{
                background: #F7F8F9;
            }
        }
    }
    .routeDetail{
        position: absolute;
        padding: 0 20px;
        top: 134px;
        bottom: 20px;
        left: 341px;
        right: 20px;
        overflow-y: auto;
        background: #fff;
        border-left: 1px solid #EBEEF2;
        box-shadow: 0 3px 10px rgba(0,0,0,.1);
        .routeDetailHeader{
            height: 50px;
            border-bottom: 1px solid #EBEEF2;
            line-height: 50px;
            .tit{
                float: left;
                color: #bac3c9;
                font-size: 14px;
            }
            .checkbox{
                float: right;
                margin-right: 15px;
                margin-top: -2px;
                label{
                    display: inline-block;
                    margin-right: 10px;
                    font-size: 16px;
                }
                .checkboxC{
                    display: inline-block;
                    vertical-align: middle;
                }
            }
        }
        .dayList{
            li{
                position: relative;
                padding: 10px 65px 15px 0;
                border-bottom: 1px solid #EBEEF2;
                .checkboxC{
                    position: absolute;
                    right: 15px;
                    top: 15px;
                }
                .tit{
                    font-size: 16px;
                    span{
                        margin-left: 5px;
                        font-size: 14px;
                    }
                }
                .txt{
                    margin-top: 10px;
                    line-height: 22px;
                    font-size: 13px;
                    color: #828d94;
                }
            }
        }
    }

}
</style>