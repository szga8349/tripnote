package com.lenovo.tripnote.vo;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Getter;

@SuppressWarnings("serial")
public class UserToken extends UsernamePasswordToken{
	@Getter
	private String device;
	
	public UserToken(String username, String passwod,String device) {
		super(username, passwod, false, null);
		this.device = device;
	}
}
