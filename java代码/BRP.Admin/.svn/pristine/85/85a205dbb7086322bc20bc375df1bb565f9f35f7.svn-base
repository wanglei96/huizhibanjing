package strosoft.app.util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/*
 * 字符帮助类
 */
public class StringHelper {
	/*
	 * 字节数组转为16进制字符串
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	/**
	 * 转成十六进制字符串
	 * 
	 * @param text
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String toHexString(String text, String encoding) throws UnsupportedEncodingException {
		byte[] arrData = text.getBytes(encoding);
		return bytesToHexString(arrData);
	}

	/**
	 * 转成十六进制字符串
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String toHexString(String text) throws UnsupportedEncodingException {
		byte[] arrData = text.getBytes("utf-8");
		return bytesToHexString(arrData);
	}

	/**
	 * 十六进制字符串转字符串
	 * 
	 * @param hexString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fromHexString(String hexString) throws UnsupportedEncodingException {
		byte[] arrData = hexStringToBytes(hexString);
		String text = new String(arrData, "utf-8");
		return text;
	}

	/**
	 * 十六进制字符串转字符串
	 * 
	 * @param hexString
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fromHexString(String hexString, String encoding) throws UnsupportedEncodingException {
		byte[] arrData = hexStringToBytes(hexString);
		String text = new String(arrData, encoding);
		return text;
	}

	/*
	 * 16进制字符串转为字符数组
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/*
	 * 字符转字节
	 */
	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/*
	 * 数据库字段名转实体类属性名
	 */
	public static String getParameterName(String columnName) {
		String[] list = columnName.split("_");
		StringBuffer result = new StringBuffer(list[0]);
		for (int i = 1; i < list.length; i++) {
			result.append(upperCase(list[i]));
		}
		return result.toString();
	}

	/*
	 * 数据库表名转实体类名
	 */
	public static String getFormattedTableName(String tableName) {
		String[] list = tableName.split("_");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			result.append(upperCase(list[i]));
		}
		return result.toString();
	}

	public static String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString();
		return uuid = uuid.replace("-", "");
	}

	/**
	 * 取得随机UUID字符串
	 * 
	 * @return
	 */
	public static String getRandomUUID() {
		String randomUUID = UUID.randomUUID().toString();
		return randomUUID;
	}

	/**
	 * 合并列表成字符串
	 * 
	 * @param lstData
	 * @return
	 */
	public static String Join(List<Integer> lstData) {
		return Join(lstData, ",");
	}

	/**
	 * 合并列表成字符串
	 * 
	 * @param lstData
	 * @return
	 */
	public static String Join(List<Integer> lstData, String splitText) {
		StringBuffer sb = new StringBuffer();
		if (null == lstData) {
			return "";
		}
		for (int i = 0; i < lstData.size(); i++) {
			if (i > 0) {
				sb.append(splitText);
			}
			sb.append(lstData.get(i));
		}
		return sb.toString();
	}

	/**
	 * 合并列表成字符串
	 * 
	 * @param lstData
	 * @return
	 */
	public static String JoinStringList(List<String> lstData, String splitText) {
		StringBuffer sb = new StringBuffer();
		if (null == lstData) {
			return "";
		}
		for (int i = 0; i < lstData.size(); i++) {
			if (i > 0) {
				sb.append(splitText);
			}
			sb.append(lstData.get(i));
		}
		return sb.toString();
	}

	/**
	 * 取得随机文本
	 * 
	 * @param seedText
	 * @param length
	 * @return
	 */
	public static String getRandomText(String seedText, int length) {
		Random RANDOM = new SecureRandom();
		char[] arrRandomText = new char[length];
		for (int index = 0; index < arrRandomText.length; ++index) {
			arrRandomText[index] = seedText.charAt(RANDOM.nextInt(seedText.length()));
		}
		return new String(arrRandomText);
	}

	/**
	 * 取得随机文本
	 * 
	 * @param seedText
	 * @param length
	 * @return
	 */
	public static String getRandomText(int length) {
		return getRandomText("abcdefghijilmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", length);
	}

	/*
	 * 根据时间获取唯一编号
	 */
	public static String getTimeStrNo() {
		long currentTime = System.currentTimeMillis();
		Date date = new Date(currentTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStrNo = dateFormat.format(date).toString();
		return timeStrNo;
	}

	/**
	 * 检查字符串是否可以转为Integer类型
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isInteger(String str) {
		if (null == str) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 检查文本是否为空或空串
	 * 
	 * @param text
	 * @return
	 */
	public static Boolean isNullOrEmpty(String text) {
		if (text == null || text.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 转成驼峰格式字符器
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String toCamelCase(String sourceText) {
		if(StringHelper.isNullOrEmpty(sourceText)) {
			return null;
		}
		String[] list = sourceText.split("_");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				result.append(upperCase(list[i]));
			} else {
				result.append(list[i]);
			}
		}
		return result.toString();
	}

	/**
	 * 转成驼峰格式字符器
	 * 
	 * @param sourceText
	 * @return
	 */
	public static String toPascal(String sourceText) {
		if (isNullOrEmpty(sourceText)) {
			return "";
		}
		String[] list = sourceText.split("_");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				result.append(upperCase(list[i]));
			} else {
				result.append(list[i].substring(0, 1).toUpperCase() + list[i].substring(1));
			}
		}
		return result.toString();
	}

	/**
	 * 字符串加一
	 * 
	 * @param text
	 * @return
	 */
	public static String addOne(String text) {
		if (isNullOrEmpty(text)) {
			return "1";
		}
		String[] arrPart = text.split("[^0-9]"); // 根据不是数字的字符拆分字符串
		if (arrPart == null || arrPart.length == 0) {
			return "1";
		}
		String strLastNumber = arrPart[arrPart.length - 1]; // 取出最后一组数字
		if (isNullOrEmpty(strLastNumber)) {
			// 如果最后一组没有数字(也就是不以数字结尾)，则返回字符串拼上1
			String result = text + "1";
			return result;
		}
		// 取出字符串的长度
		int n = strLastNumber.length();
		int resultNumber = Integer.parseInt(strLastNumber) + 1; // 将该数字加一
		String strResultNumber = String.valueOf(resultNumber);
		n = Math.min(n, strResultNumber.length());
		// 拼接字符串
		String result = text.subSequence(0, text.length() - n) + strResultNumber;
		return result;
	}

	/**
	 * escape
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * unescape
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * @disc iso字符串转GB2312字符串
	 * @param src
	 * @return
	 */
	public static String isoToGB(String src) {
		String strRet = null;
		try {
			strRet = new String(src.getBytes("ISO_8859_1"), "GB2312");
		} catch (Exception e) {

		}
		return strRet;
	}

	/**
	 * @disc iso字符串转utf8字符串
	 * @param src
	 * @return
	 */
	public static String isoToUTF8(String src) {
		String strRet = null;
		try {
			strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
		} catch (Exception e) {

		}
		return strRet;
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] toByteArray(String text) throws UnsupportedEncodingException {
		return toByteArray(text, "UTF-8");
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param text
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] toByteArray(String text, String encoding) throws UnsupportedEncodingException {
		if (isNullOrEmpty(text)) {
			byte[] empty = new byte[] {};
			return empty;
		}
		byte[] arrData = text.getBytes(encoding);
		return arrData;
	}

}
