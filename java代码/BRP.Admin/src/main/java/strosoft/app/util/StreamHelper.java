package strosoft.app.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class StreamHelper {

	/**
	 * 读取数据流
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readByteArray(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 读取字符串
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static String readAsString(InputStream inStream) throws Exception {
		return readAsString(inStream, "UTF-8");
	}

	/**
	 * 读取字符串
	 * 
	 * @param inStream
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String readAsString(InputStream inStream, String encoding) throws Exception {
		byte[] arrData = readByteArray(inStream);
		String result = new String(arrData, encoding);
		return result;
	}
}
