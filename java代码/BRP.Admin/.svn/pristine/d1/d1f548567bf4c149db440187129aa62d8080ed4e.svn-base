package strosoft.app.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

/**
 * Base64 工具类
 */
public class Base64Helper {
	private static final char last2byte = (char) Integer.parseInt("00000011", 2);
	private static final char last4byte = (char) Integer.parseInt("00001111", 2);
	private static final char last6byte = (char) Integer.parseInt("00111111", 2);
	private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
	private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
	private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
	private static final char[] encodeTable = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	public Base64Helper() {

	}

	/**
	 * 编码
	 * 
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String plainText) throws UnsupportedEncodingException {
		return encode(plainText, "utf-8");
	}

	/**
	 * 编码
	 * 
	 * @param plainText
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String plainText, String encoding) throws UnsupportedEncodingException {
		sun.misc.BASE64Encoder en = new sun.misc.BASE64Encoder();
		String encodedText = en.encode(plainText.getBytes(encoding));
		return encodedText;
	}

	/**
	 * 解码
	 * 
	 * @param encodedText
	 * @return
	 * @throws IOException
	 */
	public static String decode(String encodedText) throws IOException {
		return decode(encodedText, "utf-8");
	}

	/**
	 * 解码
	 * 
	 * @param encodedText
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String decode(String encodedText, String encoding) throws IOException {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		byte[] data = dec.decodeBuffer(encodedText);
		String plainText = new String(data, encoding);
		return plainText;
	}

	public static String encode(byte[] from) {
		StringBuilder to = new StringBuilder((int) (from.length * 1.34D) + 3);
		int num = 0;
		char currentByte = 0;

		int i;
		for (i = 0; i < from.length; ++i) {
			for (num %= 8; num < 8; num += 6) {
				switch (num) {
				case 0:
					currentByte = (char) (from[i] & lead6byte);
					currentByte = (char) (currentByte >>> 2);
				case 1:
				case 3:
				case 5:
				default:
					break;
				case 2:
					currentByte = (char) (from[i] & last6byte);
					break;
				case 4:
					currentByte = (char) (from[i] & last4byte);
					currentByte = (char) (currentByte << 2);
					if (i + 1 < from.length) {
						currentByte = (char) (currentByte | (from[i + 1] & lead2byte) >>> 6);
					}
					break;
				case 6:
					currentByte = (char) (from[i] & last2byte);
					currentByte = (char) (currentByte << 4);
					if (i + 1 < from.length) {
						currentByte = (char) (currentByte | (from[i + 1] & lead4byte) >>> 4);
					}
				}

				to.append(encodeTable[currentByte]);
			}
		}

		if (to.length() % 4 != 0) {
			for (i = 4 - to.length() % 4; i > 0; --i) {
				to.append("=");
			}
		}

		return to.toString();
	}

	/**
	 * 通过图片路径将图片文件转化为字符数组
	 * 
	 * @param url 图片路径
	 * @return byte[]
	 */
	public static byte[] imageToBytes(String url) {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(url));
			ImageIO.write(bufferedImage, "jpg", byteOutput);
			return byteOutput.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteOutput != null)
					byteOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将图片字节数组转化为图片，存放到指定路径
	 *
	 * @param bytes 图片字节数组
	 * @param url   存放的路径
	 */
	public static void BytesToImage(byte[] bytes, String url) {
		FileImageOutputStream imageOutput = null;// 打开输入流
		try {
			imageOutput = new FileImageOutputStream(new File(url));
			imageOutput.write(bytes, 0, bytes.length);// 将byte写入硬盘
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (imageOutput != null)
					imageOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String plainText = "aaaaaasdflkasd右夺是苦";
		String encodedText = encode(plainText);
		System.out.println(encodedText);
		String decodedText = decode(encodedText);
		System.out.println(decodedText);

		encodedText = encode(plainText, "GB2312");
		System.out.println(encodedText);
		decodedText = decode(encodedText, "GB2312");
		System.out.println(decodedText);

		byte[] arrData = imageToBytes("D:\\Work\\NZW\\Document\\Images\\2507fef9fad60a5c2bd4789698c1933.jpg");
		String base64Text = encode(arrData);
		System.out.println(base64Text);

	}

}
