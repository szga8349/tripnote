package com.lenovo.tripnote.webchat.utils;

public final class EncryptionUtils {
	public static String encryption(String rawString) {
		return ACEEncryptionUtils.encrypt(rawString);
	}
	public static String deEncryption(String rawString) {
		return ACEEncryptionUtils.decrypt(rawString);
	}
}
