package strosoft.app.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESHelper {

	static final int KEY_LENGTH_BYTE = 32;
	static final int TAG_LENGTH_BIT = 128;

	/**
	 * 解密微信数据
	 * 
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptWXData(String encryptedData, String sessionKey, String iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String result = null;
		byte[] encrypData = Base64.getDecoder().decode(encryptedData);
		byte[] ivData = Base64.getDecoder().decode(iv);
		byte[] sessionKeyB = Base64.getDecoder().decode(sessionKey);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(sessionKeyB, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] doFinal = cipher.doFinal(encrypData);
		result = new String(doFinal);
		return result;
	}

	/**
	 * 加密
	 * 
	 * @param content  需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密证书
	 * 
	 * @param aesKey
	 * @param associatedData
	 * @param nonce
	 * @param ciphertext
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public static String decryptToString(byte[] aesKey, byte[] associatedData, byte[] nonce, String ciphertext)
			throws GeneralSecurityException, IOException {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
		GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		cipher.updateAAD(associatedData);
		return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
	}

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		String content = "test";
		String password = "12345678";
		// 加密
		System.out.println("加密前：" + content);
		byte[] encryptResult = encrypt(content, password);
		// 解密
		byte[] decryptResult = decrypt(encryptResult, password);
		System.out.println("解密后：" + new String(decryptResult));

		byte[] aesKey = "22dcfef7870b72d3b9fc73fde5c8bb11".getBytes();
		byte[] associatedData = "certificate".getBytes();
		byte[] nonce = "6808a0ae3301".getBytes();
		String ciphertext = "r9WqBXKL7Vl26tPPrnKNuRS5FXJuRkyDJNUAEAx4H8qrXrKyKlEKBxrd7j794Ag0/SWNd0GA5n5Pjqf7bk3rrQykAKfbnvqaldNGoRHtEOkP9k9C/9i3qNI19EDZYk4p2OcuCVNrl5bosoZ3UBI00yfOhhPBDslb8ExPbsv+wz0i/1N7VTAUAhF89RQSp/oQsxQ+I2VfAjiuZg+QkqP9JQaSsyslaTksepVN6j5H7HQHX+bzkde7HlOriAZF4isde/pXvVEzwXyliQrM4yK4HCvtlE+N9EoZGXXamhVBHAWKc+1VgDfrt5Fx82GdhYsJd2kKbjy1mvcjYF1J7aSPCqZKJ7w/PIY5NkDp5A1xlWH2xR7oEMJxrrS6lBfd81sJjCq58QVp06PeBJlTomNajL8MgiDd/X5B8DtcUoxTck0u49UpKgUBTu5Oa8p+Slsd1wUmGm8QrGA7GJYpY+A+A3dpsJ5S6dP31t9P14uoC7to1/kQZJErowbif97Xef/YSCNrvKyalXHjTKI3B88XiTsPfxkDbYjI3HJ1dAQm3DN2xwPXQsHe/IdQ/BqQLEq7ac7N46uCuBCP8Gh8FxZL0ctzbTnL/Hbi2dcqAMjO7mFDHWwQ5mv2A1qyE+x9arH07BVKRWINz+ak8j/9dYAOCRyMaje1qErfpqBdpiR+C6294E76LaY20x16bGKdJpJj8UUKVKbN3vz76waAKwpd6AooqDgD66KVat1vyB+hV3toJfPS1PXEtQRjF+H29p91I33TDAsCVX/mq83syyopVNbfJAyajOtDjUkaYH5JwHr/V/aH/EY5GaMSVsWfAidaRPtbecz261UJ9jVQLhKkrNTERQ9EcjP/4b2b6ZHPkCOSOBdbBhjSw6+h5cXEuB84nJoUT7d/R2DprKMXOKAWZ7cyd5hFVweHf/KuY2FAfjxuqTbb5kc/GmCvkEGSfVa0/pXFRWOIUZEmVTMBSR20mZC3pHKjmFGOcv+d1GZd58UbCq84lpiVaETgtjnlfzbDk6/1LpJ3qTXpqfdT7MoA0sZfC3KS+n29/LLEQNN43htAKDs3PNqn6CmL+pDjnDHhU1m7SXSTNGD/xXT6dDpMhamKuvHbOvlhcYIPLKRlh6gYb3CLcGKhCnKy20B+2z7chZg9Jf/UJwIciwpsPE0qcLY/HrnVHAof0IDnZQLxwqgwttJs+ro8rNQIVP4Y9c/FSUqIiOpTp+31ivq1GIr9BntnYkYlHcH3pio7y+lkB0XOgAJtMoxHmGF7XztID0CNs7kocZoo76lQefOOdYRw5CU37mrGMjbHykUDorZBd/E3akDIO4oQI0NO1pJHbeNbziDiBdhU8ul7f3QrfyZuUtGPLycHjKhD3AlxHb1FvZFW2YueGbfmxEotufetvttkRkhlfVKxqMD+GwfP6Lnk58LwmHEflyrxna9JfMvjsGlMcYL+CJWamOZb4z0zjaOdqWQ3eWIfsA9nlNW/4ucZXweiEzoXNJJTuXVvUNz8daCyO0nm3e0Wzx1rblP3JEnUWRvilxXUfywqPK2afl6Eq7NfVijkyemF5Pd+raP6uxTIykY+OTf0e8iusVOW4LAuJXf09JXJuWwFjlYeYUKWOszf7UeN1tXrsBMWBR2DjHTOeiw00zd8+6b2OHDT3U6AMHj8VqzDUTXRVUWXv2aNDMIx+eGduKAA5JcVwPjxXHyYrZg3zHRM3S4oxH7wG9tJxLFUnXKeo0aVtqXgUr8YIyk136GDuaRb9TdVJxq5teWsWaiGzbi5aMRKq1U1659SHkMGXrdws/Gla6lQkPvwn++sxukDed/mDEKPMXy8+2mX2a1R0AghnBgfub8/JxEXBFmN5AfO7vxyoTyeE+X4gPaBW5Kw5w==";
		String certString = decryptToString(aesKey, associatedData, nonce, ciphertext);
		System.out.println(certString);
	}
}
