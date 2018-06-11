package com.lenovo.tripnote.webchat.utils;

public class RandomUtils {
	private static String randomSource = "0123456789";
	/**
	 * 随机生成6位随机验证码
	 */
	public static String createRandomVcode() {
		int length  = randomSource.length();
		// 验证码
		String vcode = "";
		for (int i = 0; i < 4; i++) {
			vcode = vcode + randomSource.charAt((int)(Math.random() * length));
		}
		return vcode;
	}
}
