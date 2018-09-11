import {
  wxRequest
} from '@/utils/wxRequest';

// const host = 'http://10.109.23.223'
const host = 'http://10.109.4.9'


// 获取分享id
const getShareId = (params) => wxRequest(params, host + '/token/product/share/doAdd');

export default {
  getShareId
}
