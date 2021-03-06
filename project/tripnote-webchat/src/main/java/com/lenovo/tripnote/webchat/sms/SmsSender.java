package com.lenovo.tripnote.webchat.sms;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service
@PropertySource(value="classpath:conf/sms.properties",encoding="UTF-8")
public class SmsSender implements ISmsSender,InitializingBean{
	// 初始化ascClient需要的几个参数
	final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
	final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
	@Value("${sms.accessKeyId}")
	private String accessKeyId;
	@Value("${sms.accessKeySecret}")
	private String accessKeySecret ;
	// 登录验证码模板code
	@Value("${sms.loginTemplateCode}")
	private String loginTemplateCode;
	// 注册验证码模板code
	@Value("${sms.registerTemplateCode}")
	private String registerTemplateCode;
	// 重置验证码模板code
	@Value("${sms.resetTemplateCode}")
	private String resetTemplateCode;
	@Value("${sms.signName}")
	private String signName;

	private IClientProfile profile;
	
	private IAcsClient acsClient;

	@Override
	public boolean sendLoginCode(String phone, String code) throws ServerException, ClientException,IOException {
		return sendCode(phone,code,loginTemplateCode);
	}
	/**
	 * 发送验证码
	 * 
	 * @throws ClientException
	 * @throws ServerException
	 */
	private boolean sendCode(String phone, String code,String loginTemplateCode) throws ServerException, ClientException,IOException {
		/*IAcsClient acsClient = new DefaultAcsClient(profile);*/
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(loginTemplateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"name\":\""+phone+"\", \"code\":\""+code+"\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			System.out.println(sendSmsResponse.getCode());
			return true;
		}
		throw new IOException(sendSmsResponse.getMessage());
	}
	/**
	 * 发送重置验证码
	 * 
	 * @throws ClientException
	 * @throws ServerException
	 */
	@Override
	public boolean sendResetCode(String phone, String code) throws ServerException, ClientException,IOException {
		return this.sendCode(phone, code, resetTemplateCode);
	}
	/**
	 * 发送注册验证码
	 * 
	 * @throws ClientException
	 * @throws ServerException
	 */
	@Override
	public boolean sendRegisterCode(String phone, String code) throws ServerException, ClientException,IOException {
		return this.sendCode(phone, code, this.registerTemplateCode);
	}

	public static void main(String[] args) throws ClientException, IOException {
		SmsSender smsSender = new SmsSender();
		smsSender.sendLoginCode("13730696614", "12321321");
		smsSender.sendResetCode("18109060227", "12wewq");
		smsSender.sendRegisterCode("13730696614", "123232");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
       // 初始化ascClient,暂时不支持多region（请勿修改）
		profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			acsClient = new DefaultAcsClient(profile);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
