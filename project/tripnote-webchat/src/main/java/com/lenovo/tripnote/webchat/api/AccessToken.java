package com.lenovo.tripnote.webchat.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**微信接口token对象
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class AccessToken {
    /**
     * 令牌
     */
    private String access_token;
    /**
     * 有效期  单位秒
     */
    private Integer expires_in;
}
