import {FormatSize} from 'mixins/common';

const ChartColor = ['#fb7a53', '#ffbf01', '#ffdc16', '#92d14f', '#31c45e', '#50da9e', '#76e9e2', '#26d4ff', '#2cabf4', '#1064fe', '#f0f0f0'];

const getData = async (vm, url)=>{
  const res = await vm.$http.get(url);
  const {statusCode, message} = res.body;
  let recordList = statusCode == 200 ? message.records : [];
  return recordList;
}
/**
 * @desc 使用vue-resource第三方库访问后台服务器，返回封装后的Promise对象
 * @param {string} type HTTP请求方式, 默认GET.
 * @param {string} url 请求的接口地址, 格式: "/xxx..."
 * @param {object} vm vue实例
 * @param {object} data 请求的数据, object对象格式
 *
 */
function httpRequest({type = 'get',
                        url = null,
                        vm = this,
                        data = null}){
  const promise = new Promise((resolve, reject) => {
    vm.$http[type](url, data).then(res => {
      const { message, statusCode } = res.body
      if(statusCode == 200){
        resolve(message)
      }
    })
  })
  return promise
}


/**
 *
 * @param {*} vm
 * @param {*} arr
 * @param {*} params
 * @param {*} bool
 */

const setRankList = (vm, arr, params = new Map(), bool=false)=>{
  arr.map(item=>{
    for(let [key, value] of params){
      if(key=='num'){
        vm.$set(item, key, bool ? FormatSize.methods.formatSize(item[value]):item[value])
      }else{
        vm.$set(item,key, item[value])
      }
    }
  })
  return arr;
}

const strMapToObj = (map)=>{
  let obj = Object.create(null)
  for(let [k, v] of map){
    obj[k] = v
  }
  return obj
}

export { ChartColor, getData, setRankList, strMapToObj, httpRequest}
