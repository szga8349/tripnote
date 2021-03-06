function getCurrentTime() {
  var keep = '';
  var date = new Date();
  var y = date.getFullYear();
  var m = date.getMonth() + 1;
  m = m < 10 ? '0' + m : m;
  var d = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
  var h = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
  var f = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
  var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
  var rand = Math.round(Math.random() * 899 + 100);
  keep = y + '' + m + '' + d + '' + h + '' + f + '' + s;
  return keep; //20160614134947
}

function timestampToTime(timestamp, type) {
  var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
  var h = '',
    m = '',
    s = '';
  if(type){
    h = ' ' + date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
  }
  return Y+M+D + h + m + s
}

function objLength(input) {
  var type = toString(input);
  var length = 0;
  if (type != "[object Object]") {
    //throw "输入必须为对象{}！"
  } else {
    for (var key in input) {
      if (key != "number") {
        length++;
      }

    }
  }
  return length;
}
//验证是否是手机号码
function vailPhone(number) {
  let flag = false;
  let myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
  if (number.length != 11) {
    flag = flag;
  }else if (!myreg.test(number)) {
    flag = flag;
  }else{
    flag = true;
  }
  return flag;
}
//浮点型除法
function div(a, b) {
  var c, d, e = 0,
    f = 0;
  try {
    e = a.toString().split(".")[1].length;
  } catch (g) { }
  try {
    f = b.toString().split(".")[1].length;
  } catch (g) { }
  return c = Number(a.toString().replace(".", "")), d = Number(b.toString().replace(".", "")), mul(c / d, Math.pow(10, f - e));
}
//浮点型加法函数   
function accAdd(arg1, arg2) {
  var r1, r2, m;
  try {
    r1 = arg1.toString().split(".")[1].length;
  } catch (e) {
    r1 = 0;
  }
  try {
    r2 = arg2.toString().split(".")[1].length;
  } catch (e) {
    r2 = 0;
  }
  m = Math.pow(10, Math.max(r1, r2));
  return ((arg1 * m + arg2 * m) / m).toFixed(2);
}
//浮点型乘法
function mul(a, b) {
  var c = 0,
    d = a.toString(),
    e = b.toString();
  try {
    c += d.split(".")[1].length;
  } catch (f) { }
  try {
    c += e.split(".")[1].length;
  } catch (f) { }
  return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
}

// 遍历对象属性和值
function displayProp(obj) {
  var names = "";
  for (var name in obj) {
    names += name + obj[name];
  }
  return names;
}
// 去除字符串所有空格
function sTrim(text) {
  return text.replace(/\s/ig, '')
}
//去除所有:
function replaceMaohao(txt) {
  return txt.replace(/\:/ig, '')
}
//转换星星分数
function convertStarArray(score) {
  //1 全星,0 空星,2半星 
  var arr = []
  for (var i = 1; i <= 5; i++) {
    if (score >= i) {
      arr.push(1)
    } else if (score > i-1 && score < i + 1) {
      arr.push(2)
    } else {
      arr.push(0)
    }
  }
  return arr
}

function leftTimer(leftTime) {
  // var leftTime = (new Date(enddate)) - new Date(); //计算剩余的毫秒数
  // var days = parseInt(leftTime / 60 / 60 / 24, 10); //计算剩余的天数
  var hours = parseInt(leftTime / 60 / 60, 10); //计算剩余的小时
  var minutes = parseInt(leftTime / 60 % 60, 10);//计算剩余的分钟
  var seconds = parseInt(leftTime % 60, 10);//计算剩余的秒数
  // days = checkTime(days);
  hours = checkTime(hours);
  minutes = checkTime(minutes);
  seconds = checkTime(seconds);

  let time = 0
  if (hours >= 0 || minutes >= 0 || seconds >= 0) {
    // time = days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
    time = hours + "小时" + minutes + "分";
  }
  return time
}
function checkTime(i) { //将0-9的数字前面加上0，例1变为01
  if (i < 10) {
    i = "0" + i;
  }
  return i;
}

function replaceEmoji(str){
  var ranges = [
    '\ud83c[\udf00-\udfff]', 
    '\ud83d[\udc00-\ude4f]', 
    '\ud83d[\ude80-\udeff]'
  ]
  str = str.replace(new RegExp(ranges.join('|'), 'g'), '')
  return str
}

function formatHTML(html){
  html = html.split('\n')
  let result = ''
  html.forEach((item) => {
    result += '<p>'+item+'</p>'
  })
  return result
}

module.exports = {
  timestampToTime,
  getCurrentTime,
  objLength,
  displayProp,
  sTrim,
  replaceMaohao,
  vailPhone,
  div,
  mul,
  accAdd,
  convertStarArray,
  leftTimer,
  replaceEmoji,
  formatHTML
}