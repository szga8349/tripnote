package com.lenovo.tripnote.util;

public class RandomUtils {
	private static String randomSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 随机生成6位随机验证码
	 */
	public static String createRandomVcode() {
		int length  = randomSource.length();
		// 验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + randomSource.charAt((int)(Math.random() * length));
		}
		return vcode;
	}
}
