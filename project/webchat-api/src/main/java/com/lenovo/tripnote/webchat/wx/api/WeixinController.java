package com.lenovo.tripnote.webchat.wx.api;

import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.wx.utils.HttpUtils;
import com.lenovo.tripnote.webchat.wx.utils.IpUtils;
import com.lenovo.tripnote.webchat.wx.utils.StringUtils;
import com.lenovo.tripnote.webchat.wx.utils.PayUtil;
import com.lenovo.tripnote.webchat.wx.vo.GroupSuccess;
import com.lenovo.tripnote.webchat.wx.vo.OAuthJsToken;
import com.lenovo.tripnote.webchat.wx.vo.WxUnifiedOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 微信登录，支付等相关接口
 * @Date: 2018/08/02
 * @Author: dujz1
 */
@RequestMapping("/weixin")
@RestController
@PropertySource(value="classpath:conf/sms.properties",encoding="UTF-8")
public class WeixinController extends WeixinSupport{

//	@Resource
//	private HttpUtils httpUtils;

	// 存储缓存数据
	public static Map<String,Object> cacheData = new HashMap<>();

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

	@Value("${webchat.templateGroupSuccessId}")
	private String templateGroupSuccessId;

	@Value("${webchat.transfers_merchant_url}")
	private String transfers_merchant_url;

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
	 * @Description:商户转账给个人到零钱
	 * @return
	 */
//	@RequestMapping("transferToMerchant")
//	public ResultVo transferToMerchant(HttpServletRequest request){
//		ResultVo vo = new ResultVo();
//
//		try{
//			//生成的随机字符串
//			String nonce_str = StringUtils.getRandomStringByLength(32);
//			String spbill_create_ip = IpUtils.getIpAddr(request);
//
//			Map<String, String> packageParams = new HashMap<String, String>();
//			packageParams.put("mch_appid", appid);
//			packageParams.put("mchid", mch_id);
//			packageParams.put("nonce_str", nonce_str);
//			packageParams.put("partner_trade_no", "abcd123");
//			packageParams.put("openid", "oJxKK5Vf6WUJEE-cPGXHaKG9BlGQ");//商户订单号
//			packageParams.put("check_name", "NO_CHECK");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
//			packageParams.put("spbill_create_ip", spbill_create_ip);
//			packageParams.put("amount", "1");
//			packageParams.put("desc", "付款给dujz");
//
//			// 除去数组中的空值和签名参数
//			packageParams = PayUtil.paraFilter(packageParams);
//			String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
//
//			//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
//			String mysign = PayUtil.sign(prestr, pay_secret, "utf-8").toUpperCase();
//			logger.info("=======================第一次签名：" + mysign + "=====================");
//
//
//			//拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
//			String xml = "<xml>" + "<mch_appid>" + appid + "</mch_appid>"
//					+ "<mchid>" + mch_id + "</mchid>"
//					+ "<nonce_str>" + nonce_str + "</nonce_str>"
//					+ "<notify_url>" + notify_url + "</notify_url>"
//					+ "<partner_trade_no>" + "abcd123" + "</partner_trade_no>"
//					+ "<openid>" + "oJxKK5Vf6WUJEE-cPGXHaKG9BlGQ" + "</openid>"
//					+ "<check_name>" + "NO_CHECK" + "</check_name>"
//					+ "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
//					+ "<amount>" + "1" + "</amount>"
//					+ "<desc>" + "付款给dujz" + "</desc>"
//					+ "<sign>" + mysign + "</sign>"
//					+ "</xml>";
//
//			System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
//
//			//调用统一下单接口，并接受返回的结果
////			String result = PayUtil.httpRequest(transfers_merchant_url, "POST", xml);
//			String result = httpUtils.posts(transfers_merchant_url, xml);
//
//			System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
//
//			// 将解析结果存储在HashMap中
//			Map map = PayUtil.doXMLParse(result);
//
//			String return_code = (String) map.get("return_code");//返回状态码
//
//			//返回给移动端需要的参数
//			Map<String, Object> response = new HashMap<String, Object>();
//			if(return_code == "SUCCESS" || return_code.equals(return_code)){
//				// 业务结果
//				response.put("partner_trade_no", map.get("partner_trade_no"));
//				response.put("payment_no", map.get("payment_no"));
//				response.put("payment_time", map.get("payment_time"));
//			}else{
//				response.put("err_code", map.get("err_code"));
//				response.put("err_code_des", map.get("err_code_des"));
//			}
//
//			vo.setCode(Result.SUCESSFUL);
//			vo.setData(response);
//		}catch(Exception e){
//			e.printStackTrace();
//			vo.setCode(Result.FAUL);
//			vo.setData("发起失败!");
//		}
//		return vo;
//	}

    /**
     * @Description:微信支付回调
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


	/**
	 * 定时获取access_token
	 * @throws SQLException
	 */
	@Scheduled(fixedDelay=7180000)
	public void setAccessToken() throws SQLException {
		logger.info("==============开始获取access_token===============");
		String access_token = null;
		String grant_type = "client_credential";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret;

		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			//System.out.println("JSON字符串："+demoJson);
			access_token = demoJson.getString("access_token");

			System.out.println(access_token);
			is.close();
			logger.info("==============结束获取access_token===============");
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("==============开始写入access_token===============");
//        redisTokenHelper.saveObject("global_token", access_token);
		cacheData.put("token", access_token);
		logger.info("==============写入access_token成功===============");
	}

	@RequestMapping(value = "/getAccessToken")
	@ResponseBody
	public ResultVo getAccessToken(HttpServletRequest request) {
		ResultVo vo = new ResultVo();

		System.out.println(cacheData.get("token"));

		vo.setData(cacheData.get("token"));
		return vo;
	}


	/**
	 *  拼团成功发送模板
	 */
	@RequestMapping(value = "/sentTemplateGroupSuccessMessage")
	public void sentOrderSuccessMessage(@RequestBody GroupSuccess groupSuccess) {
		logger.info("==============接收到formId---"+groupSuccess.getFormId()+"===============");
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+cacheData.get("token");

		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/json;charset=UTF-8");


			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();

			JSONObject jsonObject = new JSONObject();


//			商品名称
//			{{keyword1.DATA}}
//
//			参团人数
//			{{keyword2.DATA}}
//
//			原价
//			{{keyword3.DATA}}
//
//			拼团价格
//			{{keyword4.DATA}}
//
//			温馨提示
//			{{keyword5.DATA}}

			jsonObject.put("touser", groupSuccess.getOpenId());
			jsonObject.put("template_id", templateGroupSuccessId);
			jsonObject.put("form_id", groupSuccess.getFormId());


			JSONObject data = new JSONObject();
			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", groupSuccess.getProductName());
			JSONObject keyword2 = new JSONObject();
			keyword2.put("value", groupSuccess.getGroupNum());
			JSONObject keyword3 = new JSONObject();
			keyword3.put("value", groupSuccess.getRawPrice());
			JSONObject keyword4 = new JSONObject();
			keyword4.put("value", groupSuccess.getGroupPrice());
			JSONObject keyword5 = new JSONObject();
			keyword5.put("value", groupSuccess.getTips());

			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("keyword3", keyword3);
			data.put("keyword4", keyword4);
			data.put("keyword5", keyword5);

			jsonObject.put("data", data);

			DataOutputStream out = new DataOutputStream(
					http.getOutputStream());
			out.write(jsonObject.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);

			is.close();
			logger.info("===发送结果："+message+"===============");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/sentMessage")
	public void sentMessage(@RequestParam String open_id, @RequestParam String title, @RequestParam String pagepath, @RequestParam String thumb_media_id) {
		logger.info("==============openId---"+open_id+"===============");
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+cacheData.get("token");

		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/json;charset=UTF-8");


			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();

			JSONObject jsonObject = new JSONObject();


//			产品
//			{{keyword1.DATA}}
//
//			购买时间
//			{{keyword2.DATA}}
//
//			交易单号
//			{{keyword3.DATA}}
//
//			订单总价
//			{{keyword4.DATA}}
//
//			姓名
//			{{keyword5.DATA}}
//
//			电话
//			{{keyword6.DATA}}

			jsonObject.put("touser", open_id);
			jsonObject.put("msgtype", "miniprogrampage");


			JSONObject data = new JSONObject();
			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", "日本五年多次往返签证111");
			JSONObject keyword2 = new JSONObject();
			keyword2.put("value", "2018年08月06日 15:30");
			JSONObject keyword3 = new JSONObject();
			keyword3.put("value", "101225262522225");
			JSONObject keyword4 = new JSONObject();
			keyword4.put("value", "1");
			JSONObject keyword5 = new JSONObject();
			keyword5.put("value", "杜军芝1");
			JSONObject keyword6 = new JSONObject();
			keyword6.put("value", "15108230211");

			data.put("title", title);
			data.put("pagepath", pagepath);
			data.put("thumb_media_id", thumb_media_id);
//			data.put("keyword4", keyword4);
//			data.put("keyword5", keyword5);
//			data.put("keyword6", keyword6);

			jsonObject.put("miniprogrampage", data);

			DataOutputStream out = new DataOutputStream(
					http.getOutputStream());
			out.write(jsonObject.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);

			is.close();
			logger.info("===发送结果："+message+"===============");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
