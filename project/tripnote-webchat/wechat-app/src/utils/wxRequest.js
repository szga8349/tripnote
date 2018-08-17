import wepy from 'wepy';
import util from './util';
import md5 from './md5';
import tips from './tips'

const API_SECRET_KEY = 'www.mall.cycle.com'
const TIMESTAMP = util.getCurrentTime()
const SIGN = md5.hex_md5((TIMESTAMP + API_SECRET_KEY).toLowerCase())

const wxRequest = async(params = {}, url) => {
    tips.loading();
    let data = params.data || {};
    // data.sign = SIGN;
    data.time = TIMESTAMP;

    console.log('url', url)
    console.log('method', params.method)
    console.log('data', params.data)
    console.log('header', params.header)

    let res = await wepy.request({
        url: url,
        method: params.method || 'GET',
        data: data,
        header: params.header,
    });
    tips.loaded();
    return res;
}

module.exports = {
    wxRequest
}
