package strosoft.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
    /**
     * 字符串转MD5
     *
     * @param plainText plain text
     * @return
     */
    public static String toMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : secretBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        // java自带工具包MessageDigest
        String resultString = MD5Helper.toMD5("123456");
        System.out.println(resultString);
        // e10adc3949ba59abbe56e057f20f883e
        String resultString1 = MD5Helper.toMD5("1234");
        System.out.println(resultString1);
        // 81dc9bdb52d04dc20036dbd8313ed055


    }
}
