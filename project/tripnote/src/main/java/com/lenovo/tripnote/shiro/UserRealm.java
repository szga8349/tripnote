package com.lenovo.tripnote.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.service.BAccountService;

public class UserRealm extends AuthorizingRealm implements InitializingBean {

	@Resource
	private BAccountService bAccountService;

	// 为当前登陆成功的用户授予权限和角色，已经登陆成功了
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal(); // 获取用户名
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		System.out.println(username);
		/*
		 * authorizationInfo.setRoles(userService.getRoles(username));
		 * authorizationInfo.setStringPermissions(userService.getPermissions(
		 * username));
		 */
		return authorizationInfo;
	}

	// 验证当前登录的用户，获取认证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 获取用户名
		BAccount account = bAccountService.getByUsernameOrPhone(username);
		if (account != null) {
			AuthenticationInfo authcInfo = null;
			if (StringUtils.equals(username, account.getLoginName())) {
				authcInfo = new SimpleAuthenticationInfo(account, account.getLoginPassword(), "myRealm");
			} else {
				authcInfo = new SimpleAuthenticationInfo(account, account.getLoginPassword(), "myRealm");
			}
			return authcInfo;
		} else {
			return null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
}
