var cy;
var id;
var activeNode;

var _rootData,_rootNode;

var _COLOR = {
    node :   {person: '#FD485E',company:'#4ea2f0',current:'#ff9e00'},
    border : {person: '#FD485E',company:'#128BED',current:'#EF941B'},
    line:    {sellTo: '#fd485e',agent:'#4ea2f0',xxx:'#4ea2f0'}
};
var _currentKeyNo, _companyRadius = 35, _personRadius = 15, _circleMargin = 10, _circleBorder = 3,
    _layoutNode = {}, _isFocus = false;
var _maxChildrenLength = 0;

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

// 去重操作,普通元素
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
            o.data.showStatus = null; 
            o.layout = {}
            o.layout.level = null; 
            o.layout.singleLinkChildren = []; 
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
        var relationships = list[i].graph.relationShips;

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


    setLevel(graph.nodes,graph.links);
    setCategoryColor(graph.nodes,graph.links);

    return graph;
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
function setCategoryColor(nodes, links){
    for(var i = 0; i < links.length; i++){
        var sameLink = {}; 
        sameLink.length = 0;
        sameLink.currentIndex = 0;
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
        if (_currentKeyNo == node.data.obj.nodeId) {
            node.data.color = _COLOR.node.current;
            node.data.strokeColor = _COLOR.border.current;
        } else if (node.data.obj.nodeType == 'ORGANIZATION') {
            node.data.color = _COLOR.node.company;
            node.data.strokeColor = _COLOR.border.company;
        } else {
            node.data.color = _COLOR.node.person;
            node.data.strokeColor = _COLOR.border.person;
        }
    }
}

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

// 根据nodeId获取node 索引
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

// 根据nodeId获取node
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

function drawGraph(elements) {
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
            }
        },
        style: [
            {
                selector: 'node',
                style: {
                    shape: 'ellipse',
                    width: function (ele) {
                        return 60;
                    },
                    height: function (ele) {
                        return 60;
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
                        var label = ele.data("name");
                        var length = label.length;

                        if(length <=5){
                            return label;
                        } else if(length >=5 && length <= 9) {
                            return label.substring(0,length - 5) + '\n' + label.substring(length - 5,length);
                        } else if(length >= 9 && length <= 13){
                            return label.substring(0,4) + '\n' + label.substring(4,9) + '\n' + label.substring(9,13);
                        } else {
                            return label.substring(0,4) + '\n' + label.substring(4,9) + '\n' + label.substring(9,12) + '..';
                        }
                    },
                    'z-index-compare':'manual',
                    'z-index':20,
                    color:"#fff",
                    //'padding-top':0,
                    'padding':function (ele) {
                        if(ele.data("type") == 'ORGANIZATION'){
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
                        if(ele.data("type") == 'ORGANIZATION'){
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
                selector: '.lineFixed',
                style: {
                    'overlay-opacity':0,
                }
            },
        ],
    });

    cy.on('click', 'node', function(evt){
        
    });
    cy.on('mouseover', 'node', function(evt){
        
    });
    cy.on('mouseout', 'node', function(evt){
        
    });
    cy.on('mouseover', 'edge', function(evt){
        if(!_isFocus){
            var edge = evt.target;
            edge.addClass("edgeActive");
        }
    });

    cy.on('click', function(event){
        var evtTarget = event.target;

        if( evtTarget === cy ){
            _isFocus = false;
            activeNode = null;
            cy.collection("node").removeClass("nodeActive");
            $('.tp-detail').fadeOut();
            cancelHighLight();

            //cy.collection("edge").addClass("edgeActive");
        } else {
            //console.log('tap on some element');
        }
    });

    cy.on('zoom',function(){
        if(cy.zoom()<0.5){
            cy.collection("node").addClass("hidetext");
            cy.collection("edge").addClass("hidetext");
        }else{
            cy.collection("node").removeClass("hidetext");
            cy.collection("edge").removeClass("hidetext");
        }

        setTimeout(function () {
            cy.collection("edge").removeClass("lineFixed");
            cy.collection("edge").addClass("lineFixed");
        },200);
    })

    cy.on('pan',function () {
        setTimeout(function () {
            cy.collection("edge").removeClass("lineFixed");
            cy.collection("edge").addClass("lineFixed");
        },200);
    });

    // 定位
    cy.nodes().positions(function( node, i ){
        // 保持居中
        if(node._private.data.keyNo == _currentKeyNo){
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
        cy.collection("edge").addClass("edgeActive");

        setTimeout(function () {
            cy.collection("edge").addClass("lineFixed");
        },400);
    });

    cy.nodes(function (node) {

    });
}
function highLight(nodeIds,cy) {
    cy.collection("node").removeClass("nodeActive");
    cy.collection("node").addClass('dull');
    // cy.collection("edge").addClass('dull');

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
    cy.collection("node").addClass('dull');
    cy.collection("edge").addClass('dull');


    for(var i = 0; i < nodeIds.length; i++){
        var nodeId = nodeIds[i];
        cy.nodes(function (node) {
            var nodeData  = node._private.data;
            if(nodeData.id == nodeId){
                node.removeClass('dull');
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
    // cy.collection("edge").removeClass("edgeActive");
    cy.collection("node").removeClass('dull');
    cy.collection("edge").removeClass('dull');
}


function getD3Position(graph) {
    getLayoutNode(graph);

    function filterLinks1(graph) {
        var layoutLinks = [];
        for(var i = 0; i < graph.links.length; i++){
            var link = graph.links[i];
            var sourceLevel = link.sourceNode.layout.level;
            var targetLevel = link.targetNode.layout.level;
            var sourceNode = link.sourceNode;
            var targetNode = link.targetNode;
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
    if(_maxChildrenLength > 50 && _maxChildrenLength < 100){
        strength = -2000; distanceMax = 500;
    } else if(_maxChildrenLength > 1000 && _maxChildrenLength < 2000) {
        strength = -4000; distanceMax = 1500;
    }

    console.log(graph);
    d3.forceSimulation(graph.nodes)
        .force('charge', d3.forceManyBody().strength(strength).distanceMax(distanceMax).theta(theta))
        .force('link', d3.forceLink(graph.layoutLinks).distance(distance))
        .force('center', d3.forceCenter(width / 2, height / 2))
        .force('collide', d3.forceCollide().radius(function () { return colideRadius;}))
}

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
        if(type == 'SELL_TO'){
            return _COLOR.line.sellTo;
        } else if(type == 'AGENT_FACTORY') {
            return _COLOR.line.agent;
        } else if(type == 'xxx') {
            return _COLOR.line.xxx;
        }
    }
    function getLinkLabel(link) {
        var type = link.data.obj.type;
        if(type == 'SELL_TO'){
            return '销售';
        } else if(type == 'AGENT_FACTORY') {
            return '代理';
        } else if(type == 'xxx') {
            return 'xxx';
        }
    }
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
                nodeId:node.id,
                type:node.data.obj.nodeType,
                keyNo:node.data.obj.nodeId,
                data:node.data,
                id:node.id,
                name:node.data.obj.org_name,
                color:node.data.color,
                borderColor:node.data.strokeColor,
                layout:node.layout,
                d3x:node.x,
                d3y:node.y,
            }
        });
    });

    return els;
}
// 图谱、筛选面板更新
function domUpdate(graphData) {
    getD3Position(graphData);

    setTimeout(function () {
        drawGraph(transformData(graphData));
    },500);
}


//截图2
function downImg(imgdata){
    var type = 'png';
    //将mime-type改为image/octet-stream,强制让浏览器下载
    var fixtype = function (type) {
        type = type.toLocaleLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    }
    imgdata = imgdata.replace(fixtype(type), 'image/octet-stream')
    //将图片保存到本地
    var saveFile = function (data, filename) {
        var link = document.createElement('a');
        link.href = data;
        link.download = filename;
        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        link.dispatchEvent(event);
    }
    var filename = new Date().toLocaleDateString() + '.' + type;
    saveFile(imgdata, filename);
}
//截图2 IE
function downloadimgIE(canvas) {
    function post(URL, PARAMS) {
        var temp = document.createElement("form");
        temp.action = URL;
        temp.enctype = "multipart/form-data";
        temp.method = "post";
        temp.style.display = "none";
        for (var x in PARAMS) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = PARAMS[x];
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }

    var qual = 1;
    if(canvas.width>3000){
        qual = 0.5;
    }else if(canvas.width>5000){
        qual = 0.4;
    }
    //设置保存图片的类型
    var imgdata = canvas.toDataURL('image/jpeg',qual);
    //var filename = '{{$smarty.get.name}}的关联图谱_'+new Date().toLocaleDateString() + '.jpeg';
    var filename = '图谱.png';
    post(INDEX_URL+'cms_downloadimg?filename='+filename, {img:imgdata});
}
//截图1
function canvasImg(imgData){
    var img = new Image();

    img.onload = function(e){

        var canvas = document.createElement('canvas');  //准备空画布
        canvas.width = img.width;
        canvas.height = img.height;
        var context = canvas.getContext('2d');  //取得画布的2d绘图上下文
        context.fillStyle = "#fff";
        context.fillRect(0,0,canvas.width,canvas.height);

        context.drawImage(img, 0, 0);

        if(canvas.width>400){
            var marker = '关联图谱由企查查基于公开信息利用大数据分析引擎独家生成';
            context.font = "14px 微软雅黑";
            context.fillStyle = "#aaaaaa";
            context.fillText(marker, canvas.width/2-context.measureText(marker).width/2, canvas.height-30);
        }

        // var shuiying = new Image();
        // shuiying.src="/material/theme/chacha/cms/v2/images/shuiying.png";
        // if(canvas.width>320){
        //     context.drawImage(shuiying, canvas.width/2-160, canvas.height/2-80,320,160);
        // }else{
        //     context.drawImage(shuiying, canvas.width/2-80, canvas.height/2-40,160,80);
        // }

        // downloadimgIE(canvas);
        downImg(canvas.toDataURL('image/jpeg',1));

        /*if(!!window.ActiveXObject || "ActiveXObject" in window){ // ie
            context.drawImage(shuiying, canvas.width/2-160, canvas.height/2-80,320,160);
            downloadimgIE(canvas);
        } else {
            downImg(canvas.toDataURL('image/jpeg',1));
        }*/
    }

    img.src = imgData;
}


$(function () {
    _currentKeyNo = 24994;
    _rootData = getRootData(data.data);
    domUpdate(_rootData);

    $('#TrSave').click(function () {
        canvasImg(cy.png({full:true,bg:'#0000'}));
    });
});