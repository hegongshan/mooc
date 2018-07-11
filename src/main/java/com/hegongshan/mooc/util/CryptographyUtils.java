package com.hegongshan.mooc.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public final class CryptographyUtils {
	public static String encryptByMd5(String source, String salt) {

		return new Md5Hash(source, salt).toString();

	}

	public static String encryptByBase64(String source) {
		return Base64.encodeToString(source.getBytes());
	}

	public static String decryptByBase64(String source) {
		return Base64.decodeToString(source);
	}

	public static void main(String[] args) {
		System.out.println(encryptByMd5("hegongshan", "1923489323@qq.com"));
	}
}
