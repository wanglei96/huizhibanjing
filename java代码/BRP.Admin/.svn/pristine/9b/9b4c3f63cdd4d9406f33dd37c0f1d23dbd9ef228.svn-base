package strosoft.app.util;

public class NumberStringHelper {
    /**
     * 两个超长的正整数数字字符串，求两数之和
     * @param str1
     * @param str2
     * @return
     */
    public static String numberStringSum(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }

        // 校验字符串是否就包含数字和'-'
        String regex = "\\d+"; // 只考虑正整数
        if (!str1.matches(regex) || !str2.matches(regex)) {
            return null;
        }

        char[] charStr1 = str1.toCharArray();
        char[] charStr2 = str2.toCharArray();

        // 把两个字符串进行反转,然后两两相加
        char[] ch1 = reverse(charStr1);
        char[] ch2 = reverse(charStr2);

        // 判断两个字符串的长度
        int maxLen = ch1.length >= ch2.length ? ch1.length: ch2.length;
        int minLen = ch1.length <= ch2.length ? ch1.length: ch2.length;

        // 判断两个字符串哪一个长
        boolean isLen = ch1.length >= ch2.length ? true: false;

        int sum = 0; // 默认0
        int inValue = 0; // 默认的进位值
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < maxLen; i++) {
            // 小于最小长度的字符串时,两个数相加
            if (i < minLen) {
                int num1 = Integer.parseInt(String.valueOf(ch1[i]));
                int num2 = Integer.parseInt(String.valueOf(ch2[i]));

                // 两个数相加
                sum = num1 + num2 + inValue;
            } else {
                // 小长度字符串相加完, 计算大长度字符串, 考虑进位值
                int num = 0;
                if (isLen) {
                    num = Integer.parseInt(String.valueOf(ch1[i]));
                } else {
                    num = Integer.parseInt(String.valueOf(ch2[i]));
                }
                sum = num + inValue;
            }
            inValue = sum/10; // 判断是否有进位值

            // 把值存储起来
            builder.append(sum%10);
        }
        // 两个字符串长度一样，但又有进位值的情况
        builder.append(inValue == 1 ? inValue : "");

        // 返回的字符串
        return builder.reverse().toString();
    }

    /**
     * 字符串反转
     * @param chars
     * @return
     */
    public static char[] reverse(char[] chars) {
        char[] result = new char[chars.length];
        int index = 0;
        // 反转
        for (int i = chars.length - 1; i >= 0; i--) {
            result[index++] = chars[i];
        }
        return result;
    }
}
