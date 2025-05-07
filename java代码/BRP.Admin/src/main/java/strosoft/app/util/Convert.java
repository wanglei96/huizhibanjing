package strosoft.app.util;

/**
 * 转换帮助类
 * 
 * @author dl
 *
 */
public class Convert {

	/**
	 * 长整数转字节数组
	 * 
	 * @param l
	 * @return
	 */
	public static byte[] toBytes(long l) {
		byte[] result = new byte[8];
		for (int i = 7; i >= 0; i--) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}

	/**
	 * 字节数组转长整型
	 * 
	 * @param b
	 * @return
	 */
	public static long toLong(byte[] b) {
		long result = 0;
		for (int i = 0; i < 8; i++) {
			result <<= 8;
			result |= (b[i] & 0xFF);
		}
		return result;
	}

	/**
	 * 整数转字节数组
	 * 
	 * @param l
	 * @return
	 */
	public static byte[] toBytes(int l) {
		byte[] result = new byte[4];
		for (int i = 3; i >= 0; i--) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}

	/**
	 * 字节数组转整型
	 * 
	 * @param b
	 * @return
	 */
	public static long toInt(byte[] b) {
		long result = 0;
		for (int i = 0; i < 4; i++) {
			result <<= 8;
			result |= (b[i] & 0xFF);
		}
		return result;
	}

	/**
	 * 转成ASCII字符串
	 * 
	 * @param longValue
	 * @return
	 */
	public static String toAsciiString(long longValue) {
		StringBuffer sb = new StringBuffer();
		byte[] arrValue = toBytes(longValue);
		for (int i = arrValue.length - 1; i >= 0; i--) {
			sb.append((char) arrValue[i]);
		}
		return sb.toString();
	}
}
