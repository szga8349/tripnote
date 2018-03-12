

/** 网页当前状态判断 (解决没布局完就切换页面造成点聚集在一起)*/
// var hidden, state, visibilityChange;
// if (typeof document.hidden !== "undefined") {
//     hidden = "hidden";
//     visibilityChange = "visibilitychange";
//     state = "visibilityState";
// } else if (typeof document.mozHidden !== "undefined") {
//     hidden = "mozHidden";
//     visibilityChange = "mozvisibilitychange";
//     state = "mozVisibilityState";
// } else if (typeof document.msHidden !== "undefined") {
//     hidden = "msHidden";
//     visibilityChange = "msvisibilitychange";
//     state = "msVisibilityState";
// } else if (typeof document.webkitHidden !== "undefined") {
//     hidden = "webkitHidden";
//     visibilityChange = "webkitvisibilitychange";
//     state = "webkitVisibilityState";
// }
// 添加监听器，在title里显示状态变化

/** 解决浏览器标签切换排列问题 */
// var _isNeedReload = false;
// var _isGraphLoaded = false;

/** end 网页当前状态判断 */

    //

var cy;
var id;
var activeNode;

var _rootData,_rootNode;

var _COLOR = {
    //node :   {person: '#09ACB2',company:'#128BED',current:'#FD485E'},
    //node :   {person: '#20BDBF',company:'#4EA2F0',current:'#FD485E'},
    node :   {person: '#FD485E',company:'#4ea2f0',current:'#ff9e00'},
    //node :   {person: '#a177bf',company:'#4ea2f0',current:'#FD485E'},
    //node :   {person: '#f2af00',company:'#0085c3',current:'#7ab800'},
    //border : {person: '#09ACB2',company:'#128BED',current:'#FD485E'},
    //border : {person: '#57A6A8',company:'#128BED',current:'#FD485E'},
    border : {person: '#FD485E',company:'#128BED',current:'#EF941B'},
    //border : {person: '#7F5AB8',company:'#128BED',current:'#FD485E'},
    //border : {person: '#f2af00',company:'#0085c3',current:'#7ab800'},
    //line:    {invest:'#128BED',employ:'#FD485E',legal:'#09ACB2'},
    //line:    {invest:'#4EA2F0',employ:'#20BDBF',legal:'#D969FF'}
    line:    {invest:'#fd485e',employ:'#4ea2f0',legal:'#4ea2f0'}
    //line:    {invest:'#e43055',employ:'#a177bf',legal:'#4ea2f0'}
};
var _currentKeyNo,_companyRadius = 35,_personRadius = 15,_circleMargin = 10,_circleBorder = 3,
    _layoutNode = {}, _isFocus = false;
var _maxChildrenLength = 0;


/****** 工具 ******/

//去重操作,元素为对象
/*array = [
    {a:1,b:2,c:3,d:4},
    {a:11,b:22,c:333,d:44},
    {a:111,b:222,c:333,d:444}
];
var arr = uniqeByKeys(array,['a','b']);*/
function uniqeByKeys(array,keys){
    //将对象元素转换成字符串以作比较
    function obj2key(obj, keys){
        var n = keys.length,
            key = [];
        while(n--){
            key.push(obj[keys[n]]);
        }
        return key.join('|');
    }

    var arr = [];
    var hash = {};
    for (var i = 0, j = array.length; i < j; i++) {
        var k = obj2key(array[i], keys);
        if (!(k in hash)) {
            hash[k] = true;
            arr .push(array[i]);
        }
    }
    return arr ;
};
//去重操作,普通元素
Array.prototype.unique = function(){
    var res = [];
    var json = {};
    for(var i = 0; i < this.length; i++){
        if(!json[this[i]]){
            res.push(this[i]);
            json[this[i]] = 1;
        }
    }
    return res;
};

//深复制对象方法
function cloneObj (obj) {
    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val === 'object' ? cloneObj(val): val;
    }
    return newObj;
};

/****** 数据处理 ******/

// 数据处理：将原始数据转换成graph数据
function getRootData(list) {
    var graph = {}
    graph.nodes = [];
    graph.links = [];

    //graph.nodes
    for(var i = 0; i < list.length; i++){
        var nodes = list[i].graph.nodes;
        for(var j = 0; j < nodes.length; j++){
            var node = nodes[j];
            var o = {};
            o.nodeId = node.id;
            o.data = {};
            o.data.obj = node;
            //o.data.showStatus = 'NORMAL'; // NORMAL HIGHLIGHT DULL
            o.data.showStatus = null; // NORMAL HIGHLIGHT DULL
            o.layout = {}
            o.layout.level = null; // 1 当前查询节点
            o.layout.singleLinkChildren = []; // 只连接自己的node
            graph.nodes.push(o);

            // 设置_rootNode
            if (_currentKeyNo == o.data.obj.id){
                _rootNode = o;
            }
        }
    }

    graph.nodes = uniqeByKeys(graph.nodes,['nodeId']);

    //graph.links
    for(var i = 0; i < list.length; i++){
        var relationships = list[i].graph.relationships;

        for(var k = 0; k < relationships.length; k++) {
            var relationship = relationships[k];
            var o = {}
            o['id'] = k
            o.data = {};
            o.data.obj = relationship;
            //o.data.showStatus = 'NORMAL'; // NORMAL HIGHLIGHT DULL
            o.data.showStatus = null; // NORMAL HIGHLIGHT DULL
            o.sourceNode = getGraphNode(relationship.startNode,graph.nodes);
            o.targetNode = getGraphNode(relationship.endNode,graph.nodes);
            o.linkId = relationship.id;
            o.source = getNodesIndex(relationship.startNode,graph.nodes);
            o.target = getNodesIndex(relationship.endNode,graph.nodes);
            graph.links.push(o);
        }
    }
    graph.links = uniqeByKeys(graph.links,['linkId']);

    //emplyRevert(graph.links);
    //mergeLinks(graph.links);
    setLevel(graph.nodes,graph.links);
    setCategoryColor(graph.nodes,graph.links);

    return graph;
}
// 数据处理：董监高箭头翻转
function emplyRevert(links) {
    links.forEach(function (link,i) {
        if(link.data.obj.type == 'EMPLOY'){
            var tmpObj = link.source;
            var tmpObjNode = link.sourceNode;
            link.source = link.target;
            link.sourceNode = link.targetNode;
            link.target = tmpObj;
            link.targetNode = tmpObjNode;
        }
    });
}
// 数据处理：董监高、法人线合并
function mergeLinks(links) {
    links.forEach(function (link,i) {
        if(link.sourceNode.data.obj.labels == 'Person' && link.data.obj.type == 'LEGAL'){
            links.forEach(function (nextLink,j) {
                if(link.linkId != nextLink.linkId &&
                    link.sourceNode.nodeId == nextLink.sourceNode.nodeId &&
                    link.targetNode.nodeId == nextLink.targetNode.nodeId &&
                    nextLink.data.obj.type == 'EMPLOY'){

                    links.splice(j,1);
                }
            });
        }

        if(link.sourceNode.data.obj.labels == 'Person' && link.data.obj.type == 'EMPLOY'){
            links.forEach(function (nextLink,j) {
                if(link.linkId != nextLink.linkId &&
                    link.sourceNode.nodeId == nextLink.sourceNode.nodeId &&
                    link.targetNode.nodeId == nextLink.targetNode.nodeId &&
                    nextLink.data.obj.type == 'LEGAL'){

                    links.splice(j,1);
                }
            });
        }
    });
//        console.log(links);
}
// 数据处理：设置节点层级
function setLevel(svg_nodes,svg_links) {
    function getNextNodes(nodeId,links,parentLevel){
        var nextNodes = [];
        for(var i = 0; i < links.length; i++){
            var link = links[i];
            if(nodeId == link.sourceNode.nodeId && !link.targetNode.layout.level){
                link.targetNode.layout.level = parentLevel;
                nextNodes.push(link.targetNode);
            } else if (nodeId == link.targetNode.nodeId && !link.sourceNode.layout.level) {
                link.sourceNode.layout.level = parentLevel;
                nextNodes.push(link.sourceNode);
            }
        }
        nextNodes = uniqeByKeys(nextNodes,['nodeId']);

        return nextNodes;
    }

    var level = 1;
    var nodes = [];
    nodes.push(_rootNode);
    while(nodes.length){
        var nextNodes = [];
        for(var i = 0; i < nodes.length; i++){
            var node = nodes[i];
            node.layout.level = level;
            nextNodes = nextNodes.concat(getNextNodes(node.nodeId,svg_links,level));
        }
        level++;
        nodes = nextNodes;
    }
}
// 数据处理：设置节点角色
function setCategoryColor(nodes, links){
    for(var i = 0; i < links.length; i++){
        var sameLink = {}; // 两点间连线信息
        sameLink.length = 0; // 两点间连线数量
        sameLink.currentIndex = 0; // 当前线索引
        sameLink.isSetedSameLink = false;
        links[i].sameLink = sameLink;
    }

    /*链接相同两点的线*/
    for(var i = 0; i < links.length; i++){
        var baseLink = links[i];

        if(baseLink.sameLink.isSetedSameLink == false){
            baseLink.sameLink.isSetedSameLink = true;
            var nodeId1 = baseLink.sourceNode.nodeId;
            var nodeId2 = baseLink.targetNode.nodeId;

            var sameLinks = [];
            sameLinks.push(baseLink);
            for(var j = 0; j < links.length; j++){
                var otherLink = links[j];
                if(baseLink.linkId != otherLink.linkId && !otherLink.sameLink.isSetedSameLink){
                    if((otherLink.sourceNode.nodeId == nodeId1 && otherLink.targetNode.nodeId == nodeId2 ) ||
                        (otherLink.sourceNode.nodeId == nodeId2 && otherLink.targetNode.nodeId == nodeId1 ) ){
                        sameLinks.push(otherLink);
                        otherLink.sameLink.isSetedSameLink = true;
                    }
                }
            }

            for(var k = 0; k < sameLinks.length; k++){
                var oneLink = sameLinks[k];
                oneLink.sameLink.length = sameLinks.length; // 两点间连线数量
                oneLink.sameLink.currentIndex = k; // 当前线索引
            }
        }
    }

    for(var i = 0; i < nodes.length; i++) {
        var node = nodes[i];
        if (_currentKeyNo == node.data.obj.id) { // 当前节点
            node.data.color = _COLOR.node.current;
            node.data.strokeColor = _COLOR.border.current;
        } else if (node.data.obj.labels == 'Company') {
            node.data.color = _COLOR.node.company;
            node.data.strokeColor = _COLOR.border.company;
        } else {
            node.data.color = _COLOR.node.person;
            node.data.strokeColor = _COLOR.border.person;
        }
    }
}
// 数据处理：设置唯一孩子
function setSingleLinkNodes(links){
    function isSingleLink (nodeId,links){
        var hasLinks = 0;
        var isSingle = true;
        for(var i = 0; i < links.length; i++){
            var link = links[i];
            if(link.targetNode.nodeId == nodeId || link.sourceNode.nodeId == nodeId){
                hasLinks++;
            }
            if(hasLinks > 1){
                isSingle = false;
                break;
            }
        }

        return isSingle;
    } // isSingleLink

    links.forEach(function (link,i) {
        if(isSingleLink(link.sourceNode.nodeId,links)){
            link.targetNode.layout.singleLinkChildren.push(link.sourceNode);
        }
        if(isSingleLink(link.targetNode.nodeId,links)){
            link.sourceNode.layout.singleLinkChildren.push(link.targetNode);
        }
    });
}
// 数据处理：根据nodeId获取node 索引
function getNodesIndex(nodeId,nodes) {
    var index = 0;
    for(var i = 0; i < nodes.length; i++){
        var node = nodes[i];
        if(nodeId == node.nodeId){
            index = i;
            break;
        }
    }
    return index;
}
// 数据处理：node是否存在
function isNodeExist(nodeId,nodes) {
    var exist = false;
    for(var i = 0; i < nodes.length; i++){
        var node = nodes[i];
        if(nodeId == node.nodeId){
            exist = true;
            break;
        }
    }
    return exist;
}
// 数据处理：根据nodes过滤出相应连线（没有节点的连线删除）
function filterLinksByNodes(nodes,allLinks) {
    function isExists(nodes,nodeId) {
        var exist = false;
        for(var i = 0; i < nodes.length; i++){
            var node = nodes[i];
            if(node.nodeId == nodeId){
                exist = true;
                break;
            }
        }
        return exist;
    }
    var sel_links = [];
    for(var i = 0; i < allLinks.length; i++){
        var link = allLinks[i];
        if(isExists(nodes,link.sourceNode.nodeId) && isExists(nodes,link.targetNode.nodeId)){
            //link.source = getNodesIndex(link.sourceNode.nodeId,nodes);
            //link.target = getNodesIndex(link.targetNode.nodeId,nodes);
            sel_links.push(link);
        }
    }
    return sel_links;
}
// 数据处理：根据links过滤出相应节点(没有连线的节点删除)
function filterNodesByLinks(nodes,links) {
    function isExists(links,nodeId) {
        var exist = false;
        for(var i = 0; i < links.length; i++){
            var link = links[i];
            if(link.sourceNode.nodeId == nodeId || link.targetNode.nodeId == nodeId){
                exist = true;
                break;
            }
        }
        return exist;
    }
    var sel_nodes = [];
    for(var i = 0; i < nodes.length; i++){
        var node = nodes[i];
        if(isExists(links,node.nodeId)){
            sel_nodes.push(node);
        }
    }
    return sel_nodes;
}
// 数据处理：根据nodeId获取node
function getGraphNode(nodeId,nodes) {
    var node = null;
    for(var i = 0; i < nodes.length; i++){
        if(nodes[i].nodeId == nodeId) {
            node = nodes[i];
            break;
        }
    }
    return node;
}
//hilight*/
function drawGraph(elements) {
    /*var printLogoNode = cloneObj(elements.nodes[0]);
    printLogoNode.data.id = 'cat';
    console.log(printLogoNode);
    console.log(elements.nodes[0]);

    elements.nodes.push(printLogoNode);*/

    _currentKeyNo,_companyRadius = 35,_personRadius = 15,_circleMargin = 10,_circleBorder = 3;
    cy = cytoscape({
        container: document.getElementById('MainCy'),
        motionBlur: false,
        textureOnViewport:false,
        wheelSensitivity:0.1,
        elements:elements,
        minZoom:0.4,
        maxZoom:2.5,
        layout: {
            name: 'preset',
            componentSpacing: 40,
            nestingFactor:12,
            padding: 10,
            edgeElasticity:800,
            stop:function (e) {

                //解决浏览器标签切换排列问题
//                 if(document[state] == 'hidden'){
//                     _isNeedReload = true;
// //                        console.log('stop _isNeedReload=true');
//                 } else {
//                     _isNeedReload = false;
//                 }
                // setTimeout(function () {
                //     if(document[state] == 'hidden'){
                //         _isGraphLoaded = false;
                //         console.log('stop _isGraphLoaded=false');
                //     } else {
                //         _isGraphLoaded = true;
                //     }
                // },1000);
            }
        },
        style: [
            {
                selector: 'node',
                style: {
                    shape: 'ellipse',
                    width: function (ele) {
                        if(ele.data("type") == 'Company'){

                            /*if(ele._private.data.layout.level > 2){
                                return 48;
                            }*/
                            return 60;
                        }
                        return 45;
                    },
                    height: function (ele) {
                        if(ele.data("type") == 'Company'){
                            /*if(ele._private.data.layout.level > 2){
                                return 48;
                            }*/
                            return 60;
                        }
                        return 45;
                    },
                    'background-color': function (ele) {

                        return ele.data('color');
                    },

                    'border-color': function (ele) {
                        return ele.data("borderColor");
                    },
                    'border-width': 1,
                    'border-opacity': 1,
                    label: function (ele) {
                        //label = label.replace(/(.{5})(?=.)/g, '$1\n');
                        var label = ele.data("name");
                        var length = label.length;

//                            if(ele._private.data.layout.level > 2){ //
//                                if(length <=3){ // 3 4 3排列
//                                    return label;
//                                } else if(length >=4 && length <= 5) {
//                                    return label.substring(0,length - 2) + '\n' + label.substring(length - 2,length);
//                                } else if(length >= 5 && length <= 9){
//                                    return label.substring(0,3) + '\n' + label.substring(3,7) + '\n' + label.substring(7,10);
//                                } else {
//                                    return label.substring(0,3) + '\n' + label.substring(3,7) + '\n' + label.substring(7,9) + '..';
//                                }
//                            } else {
                        if(length <=5){ // 4 5 4排列
                            return label;
                        } else if(length >=5 && length <= 9) {
                            return label.substring(0,length - 5) + '\n' + label.substring(length - 5,length);
                        } else if(length >= 9 && length <= 13){
                            return label.substring(0,4) + '\n' + label.substring(4,9) + '\n' + label.substring(9,13);
                        } else {
                            return label.substring(0,4) + '\n' + label.substring(4,9) + '\n' + label.substring(9,12) + '..';
                        }
//                            }


                        /*if(length <=4){// 3 4 3 排列
                            return label;
                        } else if(length >=4 && length <= 7) {
                            return label.substring(0,length - 4) + '\n' + label.substring(length - 4,length);
                        } else if(length >= 9 && length <= 11){
                            return label.substring(0,3) + '\n' + label.substring(3,7) + '\n' + label.substring(7,10);
                        } else {
                            return label.substring(0,3) + '\n' + label.substring(3,7) + '\n' + label.substring(7,9) + '..';
                        }*/
                    },
                    'z-index-compare':'manual',
                    'z-index':20,
                    color:"#fff",
                    //'padding-top':0,
                    'padding':function (ele) {
                        if(ele.data("type") == 'Company'){
                            return 3;
                        }
                        return 0;
                    },
                    'font-size':12,
                    //'min-height':'400px',
                    //'ghost':'yes',
                    //'ghost-offset-x':300,
                    //'font-weight':800,
                    //'min-zoomed-font-size':6,
                    'font-family':'microsoft yahei',
                    'text-wrap':'wrap',
                    'text-max-width':60,
                    'text-halign':'center',
                    'text-valign':'center',
                    'overlay-color':'#fff',
                    'overlay-opacity':0,
                    'background-opacity':1,
                    'text-margin-y': function (ele) {
                        if(ele.data("type") == 'Company'){
                            return 4;
                        }
                        return 2;
                    },
                }
            },
            {
                selector: 'edge',
                style: {
                    'line-style':function (ele) {
                        return 'solid';
                        /*if(ele.data('data').obj.type == 'INVEST'){
                            return 'solid';
                        } else {
                            return 'dashed'
                        }*/
                    },
                    'curve-style': 'bezier',
                    'control-point-step-size':20,
                    'target-arrow-shape': 'triangle-backcurve',
                    'target-arrow-color': function (ele) {
                        return ele.data("color");
                    },
                    'arrow-scale':0.5,
                    'line-color': function (ele) {
                        //return '#aaaaaa';
                        return ele.data("color");
                    },
                    label: function (ele) {
                        return '';
                    },
                    'text-opacity':0.8,
                    'font-size':12,
                    'background-color':function (ele) {
                        return '#ccc';
                        return ele.data("color");
                    },
                    'width': 0.3,
                    'overlay-color':'#fff',
                    'overlay-opacity':0,
                    'font-family':'microsoft yahei',
                }
            },
            {
                "selector": ".autorotate",
                "style": {
                    "edge-text-rotation": "autorotate"
                }
            },
            {
                selector:'.nodeActive',
                style:{
                    /*'background-color':function (ele) {
                        if(ele.data("category")==1){
                            return "#5c8ce4"
                        }
                        return "#d97a3a";
                    },*/
                    //'z-index':300,
                    'border-color': function (ele) {
                        return ele.data("color");
                    },
                    'border-width': 10,
                    'border-opacity': 0.5
                }
            },
            {
                selector:'.edgeShow',
                style:{
                    'color':'#999',
                    'text-opacity':1,
                    'font-weight':400,
                    label: function (ele) {
                        return ele.data("label");
                    },
                    'font-size':10,
                }
            },
            {
                selector:'.edgeActive',
                style:{
                    'arrow-scale':0.8,
                    'width': 1.5,
                    'color':'#330',
                    'text-opacity':1,
                    'font-size':12,
                    'text-background-color':'#fff',
                    'text-background-opacity':0.8,
                    'text-background-padding':0,
                    'source-text-margin-y':20,
                    'target-text-margin-y':20,
                    //'text-margin-y':3,
                    'z-index-compare':'manual',
                    'z-index':1,
                    'line-color': function (ele) {
                        return ele.data("color");
                    },
                    'target-arrow-color': function (ele) {
                        return ele.data("color");
                    },
                    label: function (ele) {

                        /*if(ele.data('data').obj.type == 'INVEST'){
                            return 'solid';
                        } else {
                            return 'dashed'
                        }*/
                        return ele.data("label");
                    }
                }

            },
            {
                selector:'.hidetext',
                style:{
                    'text-opacity':0,
                }
            },
            {
                selector:'.dull',
                style:{
                    'z-index':1,
                    opacity:0.2,
                }
            },
            {
                selector: '.nodeHover',
                style: {
                    shape: 'ellipse',
                    'background-opacity':0.9,
                }
            },
            {
                selector: '.edgeLevel1',
                style: {
                    label: function (ele) {
                        return ele.data("label");
                    },
                }
            },
            {
                selector: '.edgeShowText',
                style: {
                    label: function (ele) {
                        return ele.data("label");
                    },
                }
            },
            {
                selector: '.lineFixed',// 加载完成后，加载该类，修复线有锯齿的问题
                style: {
                    'overlay-opacity':0,
                }
            },
        ],
    });

    // 定位
    cy.nodes().positions(function( node, i ){
        // 保持居中
        if(node._private.data.id == _currentKeyNo){
            var position= cy.pan();
            cy.pan({
                x: position.x-node._private.data.d3x,
                y: position.y-node._private.data.d3y
            });
        }

        //
        return {
            x: node._private.data.d3x,
            y: node._private.data.d3y
        };
    });

    cy.ready(function () {

        cy.zoom({
            level: 1.0000095043745896, // the zoom level
        });
        
    });

}


function highLight(nodeIds,cy) {
    cy.collection("node").removeClass("nodeActive");
    cy.collection("edge").removeClass("edgeActive");
    cy.collection("node").addClass('dull');
    cy.collection("edge").addClass('dull');

    for(var i = 0; i < nodeIds.length; i++){
        var nodeId = nodeIds[i];
        cy.nodes(function (node) {
            var nodeData  = node._private.data;
            if(nodeData.id == nodeId){
                node.removeClass('dull');
                //node.addClass('nodeActive');
                node.neighborhood("edge").removeClass("dull");
                node.neighborhood("edge").addClass("edgeActive");
                node.neighborhood("edge").connectedNodes().removeClass("dull");
                //node.neighborhood("edge").connectedNodes().addClass("nodeActive");
            }
        });
    }
}
function highLightFilter(nodeIds,cy) {
    function isInNodeIds(nodeId) {
        for(var i = 0; i < nodeIds.length; i++){
            if(nodeId == nodeIds[i]){
                return true;
                break;
            }
        }
        return false;
    }

    cy.collection("node").removeClass("nodeActive");
    cy.collection("edge").removeClass("edgeActive");
    cy.collection("node").addClass('dull');
    cy.collection("edge").addClass('dull');


    for(var i = 0; i < nodeIds.length; i++){
        var nodeId = nodeIds[i];
        cy.nodes(function (node) {
            var nodeData  = node._private.data;
            if(nodeData.id == nodeId){
                node.removeClass('dull');
                //node.addClass('nodeActive');
                /*node.neighborhood("edge").removeClass("dull");
                node.neighborhood("edge").addClass("edgeActive");
                node.neighborhood("edge").connectedNodes().removeClass("dull");*/
                //node.neighborhood("edge").connectedNodes().addClass("nodeActive");
            }
        });
    }

    cy.edges(function (edge) {
        var data = edge._private.data;
        if(isInNodeIds(data.target) && isInNodeIds(data.source)){
            edge.removeClass('dull');
            edge.addClass('edgeActive');
        }
    });
}
function cancelHighLight() {
    cy.collection("node").removeClass("nodeActive");
    cy.collection("edge").removeClass("edgeActive");
    cy.collection("node").removeClass('dull');
    cy.collection("edge").removeClass('dull');
}

/**其他*/

function getD3Position(graph) {
    // return;
    getLayoutNode(graph);

    function filterLinks1(graph) {
        // 筛选用于布局的links
        var layoutLinks = [];
        for(var i = 0; i < graph.links.length; i++){
            var link = graph.links[i];
            var sourceLevel = link.sourceNode.layout.level;
            var targetLevel = link.targetNode.layout.level;
            var sourceNode = link.sourceNode;
            var targetNode = link.targetNode;
//            sourceNode.layout.isSetLink = false;
//            targetNode.layout.isSetLink = false;


//            if(!sourceNode.layout.isSetLink && !targetNode.layout.isSetLink){
            if((sourceLevel == 1 && targetLevel == 2) || (sourceLevel == 2 && targetLevel == 1) ){
//                    sourceNode.layout.isSetLink = true;
//                    targetNode.layout.isSetLink = true;
                layoutLinks.push(link);
            }
            if((sourceLevel == 2 && targetLevel == 3) || (sourceLevel == 3 && targetLevel == 2) ){
//                    sourceNode.layout.isSetLink = true;
//                    targetNode.layout.isSetLink = true;
                layoutLinks.push(link);
            }
//            }

        }

        layoutLinks.forEach(function (link,i) {

            if(link.targetNode.layout.level == 3){
                layoutLinks.forEach(function (alink,j) {
                    if(alink.linkId != link.linkId &&
                        (alink.targetNode.nodeId == link.targetNode.nodeId || alink.sourceNode.nodeId == link.targetNode.nodeId)){
                        layoutLinks.splice(j,1);
                    }
                })
            }

            if(link.sourceNode.layout.level == 3){
                layoutLinks.forEach(function (alink,j) {
                    if(alink.linkId != link.linkId &&
                        (alink.targetNode.nodeId == link.sourceNode.nodeId || alink.sourceNode.nodeId == link.sourceNode.nodeId)){
                        layoutLinks.splice(j,1);
                    }
                })
            }
        })

        return layoutLinks;
    }

    function filterLinks2(graph) {
        // 筛选用于布局的links
        var layoutLinks = [];
        for(var i = 0; i < graph.links.length; i++){
            var link = graph.links[i];
            var sourceLevel = link.sourceNode.layout.level;
            var targetLevel = link.targetNode.layout.level;
            var sourceNode = link.sourceNode;
            var targetNode = link.targetNode;


            if((sourceLevel == 1 && targetLevel == 2) || (sourceLevel == 2 && targetLevel == 1) ){
                layoutLinks.push(link);
            }
            if((sourceLevel == 2 && targetLevel == 3) || (sourceLevel == 3 && targetLevel == 2) ){
                layoutLinks.push(link);
            }

        }

        return layoutLinks;
    }

    function initD3Data(graph) { //
        function getIndex(val,arr) {
            var index = 0;
            for(var i = 0; i < arr.length; i++){
                var obj = arr[i];
                if(val == obj.nodeId){
                    index = i;
                    break;
                }
            }
            return index;
        }

        /*封装符合d3的数据*/
        for(var i = 0; i < graph.nodes.length; i++){
            var node = graph.nodes[i];
            node.id = node.nodeId;
        }

        for(var i = 0; i < graph.links.length; i++){
            var link = graph.links[i];
            link.source = getIndex(link.sourceNode.nodeId, graph.nodes) ;
            link.target = getIndex(link.targetNode.nodeId, graph.nodes) ;
            link.index = i; //
        }

        graph.layoutLinks = filterLinks1(graph);

        // 围绕节点最大数值
        setSingleLinkNodes(graph.layoutLinks);
        graph.nodes.forEach(function(node,i){
            if(node.layout.singleLinkChildren.length && _maxChildrenLength < node.layout.singleLinkChildren.length){
                _maxChildrenLength = node.layout.singleLinkChildren.length
            }
        })
        //console.log('围绕节点最大数值:' + _maxChildrenLength);
    }

    initD3Data(graph); //

    var width = $("#MainD3 svg").width();
    var height = $("#MainD3 svg").height();

    var strength = -600,distanceMax = 330,theta = 0,distance = 130,colideRadius = 35,distanceMin = 400;
    // 根据节点数量调节
    if(graph.nodes.length < 50 ){
        strength = -800;distanceMax = 400;
    } else if( graph.nodes.length > 50 && graph.nodes.length < 100 ){
        strength = -800;distanceMax = 350;distance = 130;colideRadius = 35;
    } else if(graph.nodes.length > 100 && graph.nodes.length < 150){
        strength = -900;distanceMax = 450;
    } else if (graph.nodes.length > 150 && graph.nodes.length < 200) {
        strength = -1000; distanceMax = 500;
    } else if (graph.nodes.length > 200) {
        strength = -1600; distanceMax = 500;theta = 0.6,distance = 100,colideRadius = 35;
    }
    // 根据围绕数量调节
    if(_maxChildrenLength > 50 && _maxChildrenLength < 100){
        strength = -2000; distanceMax = 500;
    } else if(_maxChildrenLength > 1000 && _maxChildrenLength < 2000) {
        strength = -4000; distanceMax = 1500;
    }

    // console.log(JSON.stringify(graph));
    d3.forceSimulation(graph.nodes)
        .force('charge', d3.forceManyBody().strength(strength).distanceMax(distanceMax).theta(theta))
        .force('link', d3.forceLink(graph.layoutLinks).distance(distance))
        .force('center', d3.forceCenter(width / 2, height / 2))
        .force('collide', d3.forceCollide().radius(function () { return colideRadius;}))

    // console.log(JSON.stringify(graph));

    //.on('tick',ticked);
}

/** d3 svg */
/*var svg = d3.select('svg');
svg.selectAll('g').remove();// 清空
var svg_g = svg.append("g")

// 结点
var svg_nodes = svg_g.selectAll('circle')
    .enter().append('circle')
    .attr('r', function (d) {
        if(d.data.obj.labels[0] == 'Company'){
            return 33;
        } else {
            return 24;
        }
    })
    .attr('fill', function(d, i) {
        return d.data.color;
    })
    .style('opacity',1)*/
/** end d3 svg */


/*function ticked() {
    svg_nodes.attr("cx", function(d) {  return d.x; })
        .attr("cy", function(d) { return d.y; });
}*/

//设置符合Layout的node
function getLayoutNode(graphData) {
    var layoutNode = { current : _rootNode, level1 : [], level2 : [], level3 : [], level4 : [], level5 : [],other:[]};

    graphData.nodes.forEach(function (node,i) {
        switch (node.layout.level) {
            case 1: layoutNode.level1.push(node);break;
            case 2: layoutNode.level2.push(node);break;
            case 3: layoutNode.level3.push(node);break;
            case 4: layoutNode.level4.push(node);break;
            case 5: layoutNode.level5.push(node);break;
            default:layoutNode.other.push(node);break;
        }
    });

    _layoutNode = layoutNode;

    return layoutNode;
}

//将rootData转换成cy图谱框架所需要的数据结构
function transformData(graphData) {
    function getLinkColor(type) {
        if(type == 'INVEST'){
            return _COLOR.line.invest;
        } else if(type == 'EMPLOY') {
            return _COLOR.line.employ;
        } else if(type == 'LEGAL') {
            return _COLOR.line.legal;
        }
    }
    function getLinkLabel(link) {
        var type = link.data.obj.type, role = link.data.obj.properties.role;
        if(type == 'INVEST'){
            return '投资';
        } else if(type == 'EMPLOY') {
            return (role ? role : '任职');
        } else if(type == 'LEGAL') {
            return '法定代表人';
        }
    }
    //getLayoutNode(graphData);

    //
    id = graphData.nodes[0].nodeId;
    var els = {};
    els.nodes = [];
    els.edges = [];

    graphData.links.forEach(function (link,i) {
        var color = getLinkColor(link.data.obj.type);
        var label = getLinkLabel(link);

        els.edges.push({
            data:{
                data:link.data,
                color: color,
                id:link.linkId,
                label:label,
                source:link.sourceNode.nodeId,
                target:link.targetNode.nodeId
            },
            classes:'autorotate'
        });
    });

    graphData.nodes.forEach(function (node) {
        els.nodes.push({
            data:{
                nodeId:node.nodeId,
                type:node.data.obj.labels,
                keyNo:node.data.obj.id,
                data:node.data,
                id:node.nodeId,
                name:node.data.obj.properties.name,
                category:node.data.category,
                color:node.data.color,
                borderColor:node.data.strokeColor,
                layout:node.layout,
                d3x:node.x,
                d3y:node.y,
                //labelLine:1 // 解决文字行距问题，第1行
            }
        });
    });
    return els;
}
// 图谱、筛选面板更新
function domUpdate(graphData) {

    var d1 = JSON.stringify(graphData)
    getD3Position(graphData);

    var d2 = JSON.stringify(graphData)

    // console.log(d1)
    // console.log(d2)

    // return;
    // console.log(d2)

    setTimeout(function () {
        // console.log(transformData(graphData))
        drawGraph(transformData(graphData));
    },500);

    // selPanelUpdateList(graphData.nodes,graphData.links,true);
}



// function getData(keyNo,param){
//     var defaultParam = {
//         keyNo:keyNo,
//     }
//     param = $.extend(defaultParam,param);

//     $("#load_data").show();

//     $.ajax({
//         url:INDEX_URL + '/company_muhouAction',
//         //url:'http://10.0.0.38:9600/api/Graph/GetCompanyGraph',
//         type: 'GET',
//         data:param,
//         dataType: 'JSON',
//         success: function (re){

//             re = re.success;
//             if( !re || re.results == undefined || !re.results[0] || !re.results[0].data.length || re.results[0].data[0].graph.nodes.length == 0){
//                 $("#load_data").hide();
//                 $(".printLogo").hide();
//                 $(".tp-foot").hide();
//                 $("#Main").hide();
//                 $('#no_data').show();
//                 return;
//             } else {
//                 $(".printLogo").show();
//                 $(".tp-foot").show();
//                 $("#Main").show();
//                 $('#no_data').hide();
//             }

//             _rootData = getRootData(re.results[0].data);

//             domUpdate(_rootData);
//         },error:function(data){
//             $("#load_data").hide();
//             $(".printLogo").hide();
//             $(".tp-foot").hide();
//             $("#Main").hide();
//             $('#no_data').show();
//         }
//     });
// }


// window.onresize=function(){
//     resizeScreen();
// }
$(document).ready(function () {
    _currentKeyNo = 1;

    var _rootData = getRootData(data.data);

    domUpdate(_rootData);
});

