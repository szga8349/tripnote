package com.lenovo.tripnote.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**发送短信验证码信息
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsCodeVo {
          /**
         * 随机验证码
         */
        private String smsCode;
        
        /**
         * 发送时间
         */
        private long sendTime;
        
        
        /**
         * 有效期多长时间  默认10分钟 10*60*1000
         */
        private long validity = 10*60*1000;
        
        
}
