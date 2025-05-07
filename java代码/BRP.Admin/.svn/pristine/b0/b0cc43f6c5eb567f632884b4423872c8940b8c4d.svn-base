package strosoft.wx.pay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;

public class WxPayHelper {

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
	public static String decryptToString(String aesKey, String associatedData, String nonce, String ciphertext)
			throws GeneralSecurityException, IOException {
		return decryptToString(aesKey.getBytes(), associatedData.getBytes(), nonce.getBytes(), ciphertext);
	}

	/**
	 * 取得响应文本
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static String getResposeText(CloseableHttpResponse response) throws Exception {
		/*
		 * if (SC_OK != response.getStatusLine().getStatusCode()) { throw new
		 * Exception("请求失败！响应状态编码：" + response.getStatusLine().getStatusCode()); }
		 */
		HttpEntity entity = response.getEntity();
		InputStream theInputStream = entity.getContent();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int count;
		byte[] buffer = new byte[4096];
		while ((count = theInputStream.read(buffer, 0, buffer.length)) > 0) {
			output.write(buffer, 0, count);
		}
		String charset = "utf-8";
		String result = output.toString(charset.toLowerCase());
		System.out.println(result);
		output.close();
		theInputStream.close();
		// and ensure it is fully consumed
		EntityUtils.consume(entity);
		return result;
	}

	/**
	 * 取得32位字符串
	 * 
	 * @return
	 */
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString();
		return uuid = uuid.replace("-", "");
	}

	public static void main(String[] args)
			throws HttpCodeException, NotFoundException, IOException, GeneralSecurityException {
		String mchId = "1228986602"; // 商户号
		String mchSerialNo = "21B67E5D3EA2E2B1332464BAB833D182FCAD21F0"; // 商户证书序列号
		String apiV3Key = "22dcfef7870b72d3b9fc73fde5c8bb11"; // API V3密钥
		String privateKey = "-----BEGIN PRIVATE KEY-----\r\n"
				+ "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkX03647i2mPWP\r\n"
				+ "COKUtBs8pZE2Vr2kpQOUeoVZS4w6jt3y1llknkxS0BTPWmGg8qYqZ5ebjMDVsQfl\r\n"
				+ "4an/uGdCu22m1Sy5kCqmm/kjtzzzBIwKIdsNK6xP+X2c2sbHM1LaI5P4qP1k611H\r\n"
				+ "Z+ToI1pz+8t4y5QcFlv4qgDfKX8N0a++qxrHCZ5/5CTWz5gqEqMPEE2aBSZ0gmFJ\r\n"
				+ "S1sIegR5ElY25YXKBOKe8kOXsVCypjp7HtDhITSqL/pjgVh4bqMnpouxLqrg65xG\r\n"
				+ "NPYg5Y/hPCneYLtgaL/S3KtwTO0JhkszETWtpMfsjbGndsNrtTWMsvs6mMlvyqaN\r\n"
				+ "4pE7MTgrAgMBAAECggEAe3cm8NxaY7XTRWRMJZx/lPdccu+GtYRJgx7gySkJJPcp\r\n"
				+ "Wf2N2hlZhR7Ncs8V5Do/fDyh0XJRanH2V8NA2VdfIO3+OEGJvby/fLRN2HkL7BKv\r\n"
				+ "SfESQ/tZDB2AL/DZPMmdY0M0I6Rz6XktcLRmacQcbmBk/tAT6WEWJls0GmxmBi8V\r\n"
				+ "kdTgggjdTXJJT2GbHXMU1+MFmOkOgz3QkJQkSNsd16Na9ceAS8WPyCj55UkkVMTc\r\n"
				+ "E8YvsT2PdIx79hp0IG2H5dGKoBe2FQ0G8dqnh1lV5E8gf+hl+lnCiiemPQ7M1NfS\r\n"
				+ "Qi9+UQ/lBU3dZxiuB7F84WBIB0Qk6mnn6sU2rAY50QKBgQDWrEsW272VS7ZbEJeV\r\n"
				+ "2jMtWnEhLmGxV0ES4/DxCSGls6/zJNSIhiWCJtFEARnv7fHZEOMTwZDFyr/9GvoH\r\n"
				+ "9QBJM3VGHT8ZRFniDGJ51eFp/Dk3J9x+o/pG+lqqYg71DZVwr0hnx8Kl6CemkJYl\r\n"
				+ "t7iLVlUmk02x2bIe2lU6E/6rAwKBgQDEBAuvEzlGv7ko/XffXsgKdo5DtqFKur9Z\r\n"
				+ "azCGit1V5oKEY4nh8NlUwmhOU3w1OdUKMh/4rmWiqbpiNad3paLHtNrCx4RN8fw3\r\n"
				+ "TR97Cir+Zj8bd+IsMeTwDLla6N3ZrZaW+NNqaqtQpz/qX7VjwMr429OmF2sQMD3j\r\n"
				+ "VYXleJ7huQKBgEvixfyDmyXuiLzyRZOceaDbqsge/DvTa1aBK0gZgUJxHY6nA1Zr\r\n"
				+ "jVDP5gPTG9cb1vVzqcHvF/1DadDQO3ianApEXX12kzqcVeW5mQoWmEN9/L/JoIFH\r\n"
				+ "+A4lK+21EjKAJcaav86n59/8DCAuVlVo95yR3THwunNXUEblyWDpdon/AoGBAIws\r\n"
				+ "5HogQj6RDwX8NYRc0KuC2vwbl77MS9jTadFdbyll/gJU+J7GUXVmzZbt5w5dPDfd\r\n"
				+ "XrXFadNok2x2ODu9DOrKMANJERl4qsiVc6TWwaZKUmj/U5QNXk44mzki/LwVgnKW\r\n"
				+ "oQIiJD86S9/878fVcJGFCxdk+eCKz0dKusU+fEipAoGAYVbTlkE1f1TObhLYGpl3\r\n"
				+ "mu5ryLK6HnKs3u2iq6Yn+XEmw98eWURiw2QINlW9DsMe1yDPtHKYzXwdLTtsvvP9\r\n"
				+ "iwB1LEQcHMv+hTBqcxZc4f3jbALIjiLQM7qd0upJE1XSn8r/GOsuisTqwToF0aSk\r\n"
				+ "i6NFcu33PZzaz5VpowXHyxQ=\r\n" + "-----END PRIVATE KEY-----"; // 商户API V3私钥
		String wechatPaySerial = "73742022972DB4956297C2297807166B3228EE34"; // 平台证书序列号

	}

}
