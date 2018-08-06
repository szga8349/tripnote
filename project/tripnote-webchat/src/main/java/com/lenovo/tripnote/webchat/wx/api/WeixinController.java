package com.lenovo.tripnote.webchat.wx.api;

import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.wx.utils.IpUtils;
import com.lenovo.tripnote.webchat.wx.utils.StringUtils;
import com.lenovo.tripnote.webchat.wx.utils.weixin.PayUtil;
import com.lenovo.tripnote.webchat.wx.utils.weixin.vo.OAuthJsToken;
import com.lenovo.tripnote.webchat.wx.utils.weixin.vo.WxUnifiedOrder;
import com.lenovo.tripnote.webchat.wx.vo.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: 本示例仅供参考，请根据自己的使用情景进行修改
 * @Date: 2018/08/02
 * @Author: dujz1
 */
@RequestMapping("/weixin")
@RestController
@PropertySource(value="classpath:conf/sms.properties",encoding="UTF-8")
public class WeixinController extends WeixinSupport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String grant_type = "authorization_code";

	@Value("${webchat.appid}")
	private String appid;

	@Value("${webchat.secret}")
	private String secret;

	@Value("${webchat.pay_secret}")
	private String pay_secret;

	@Value("${webchat.mch_id}")
	private String mch_id;

	@Value("${webchat.notify_url}")
	private String notify_url;

	@Value("${webchat.SIGNTYPE}")
	private String SIGNTYPE;

	@Value("${webchat.TRADETYPE}")
	private String TRADETYPE;
	
	@Value("${webchat.pay_url}")
	private String pay_url;

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     *
     * @param code
     * @return openid
     * @throws WeixinException
     * @throws IOException
     * @since Weixin4J 1.0.0
     */
    @RequestMapping("login")
    public ResultVo login(String code, HttpServletRequest request) throws WeixinException, IOException {
		ResultVo vo = new ResultVo();

        if (code == null || code.equals("")) {
            throw new WeixinException("invalid null, code is null.");
        }
        //拼接参数
        String param = "?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret + "&js_code=" + code;

        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/jscode2session" + param);
        
        //根据请求结果判定，是否验证成功
        net.sf.json.JSONObject jsonObj = res.asJSONObject();
//        OAuthJsToken token = new ObjectMapper().readValue(res.asString(), OAuthJsToken.class);

        if (jsonObj != null) {
            Object errcode = jsonObj.get("errcode");
            if (errcode != null) {
                //返回异常信息
                throw new WeixinException(getCause(Integer.parseInt(errcode.toString())));
            }

            ObjectMapper mapper = new ObjectMapper();
            
            OAuthJsToken oauthJsToken = mapper.readValue(res.asString(),OAuthJsToken.class);

            logger.info("openid=" + oauthJsToken.getOpenid());

			vo.setCode(Result.SUCESSFUL);
			vo.setData(oauthJsToken.getOpenid());
        }else{
        	vo.setCode(Result.FAUL);
        	vo.setData("获取失败！");
		}
        return vo;
    }

    @RequestMapping("wxPay")
    public ResultVo wxPay(@RequestBody WxUnifiedOrder wxUnifiedOrder, HttpServletRequest request){
		ResultVo vo = new ResultVo();

        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);

            //商品名称
            String body = wxUnifiedOrder.getBody();
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);

            String out_trade_no = wxUnifiedOrder.getOut_trade_no();
            String product_id = wxUnifiedOrder.getProduct_id();
            String total_fee = wxUnifiedOrder.getTotal_fee(); //支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            String openid = wxUnifiedOrder.getOpenid();

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", appid);
            packageParams.put("mch_id", mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("product_id", product_id);
            packageParams.put("out_trade_no", out_trade_no);//商户订单号
            packageParams.put("total_fee", total_fee);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", notify_url);
            packageParams.put("trade_type", TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, pay_secret, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
					+ "<mch_id>" + mch_id + "</mch_id>"
					+ "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
                    + "<product_id>" + product_id + "</product_id>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + total_fee + "</total_fee>"
                    + "<trade_type>" + TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(pay_url, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);

            String return_code = (String) map.get("return_code");//返回状态码

            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误

                String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, pay_secret, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：" + paySign + "=====================");

                response.put("paySign", paySign);

                //更新订单信息
                //业务逻辑代码
            }

            response.put("appid", appid);

            vo.setCode(Result.SUCESSFUL);
            vo.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            vo.setCode(Result.FAUL);
            vo.setData("发起失败!");
        }
        return vo;
    }

    /**
     * @Description:微信支付
     * @return
     * @throws Exception
     * @throws WeixinException
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("====================notify========================");
		
    	BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), pay_secret, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/

                /**此处添加自己的业务逻辑代码end**/

                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
