package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class BProductOrderCollageFinishVo {
	@IDDeEncryption
	private Integer id;
	   /**
     * b_account.login_token (第三方自动登录标识token)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String loginToken;
    
    
    /**
     * b_product_order_collage.form_id (微信小程序发送通知模板信息中使用的form_id,由预支付后设置)
     * @ibatorgenerated 2018-08-07 15:29:05
     */
    private String formId;
}
