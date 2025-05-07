package strosoft.app.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import strosoft.app.util.StringHelper;

public class EncryptManager {
	/*
	 * 加密
	 */
	public static String encryptPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// 加密后的字符串
		byte[] arrEncryptedPassword = md5.digest(password.getBytes("utf-8"));
		String strEncryptedPassword = StringHelper.bytesToHexString(arrEncryptedPassword);
		return strEncryptedPassword;
	}

	/*
	 * 检查密码
	 */
	public static Boolean checkPassword(String password, String encryptedPassword)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String tmpEncryptedPassword = encryptPassword(password);
		if (StringHelper.isNullOrEmpty(password) && StringHelper.isNullOrEmpty(encryptedPassword)) {
			return true;
		} else {
			return tmpEncryptedPassword.equals(encryptedPassword);
		}
	}
}
