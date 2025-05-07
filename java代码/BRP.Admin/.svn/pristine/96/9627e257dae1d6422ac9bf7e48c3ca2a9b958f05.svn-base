package strosoft.app.util;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHelper {
	/**
	 * 字符串转Bcrypt
	 * 
	 * @param plainText
	 * @return
	 */
	public static String toBcrypt(String plainText) {
		String salt = getSalt();
		return toBcrypt(plainText, salt);
	}

	public static String toBcrypt(String plainText, String salt) {
		String hashedText = BCrypt.hashpw(plainText + salt, BCrypt.gensalt());
		return hashedText;
	}

	public static Boolean matches(String plainText, String hashedText) {
		return BCrypt.checkpw(plainText, hashedText);
	}

	public static Boolean matches(String plainText, String hashedText, String salt) {
		return BCrypt.checkpw(plainText + salt, hashedText);
	}

	public static String getSalt(int rounds) {
		return BCrypt.gensalt(rounds, new SecureRandom());
	}

	public static String getSalt() {
		return BCrypt.gensalt();
	}

	public static void main(String args[]) {
		// java自带工具包MessageDigest
		String plainText = "aaa";
		String hashedText = toBcrypt(plainText, "abc");
		System.out.println(hashedText);
		Boolean match = matches(plainText, hashedText, "abc");
		System.out.println(match);
		match = matches(plainText, hashedText, "abcd");
		System.out.println(match);
		
		// 81dc9bdb52d04dc20036dbd8313ed055

	}
}
