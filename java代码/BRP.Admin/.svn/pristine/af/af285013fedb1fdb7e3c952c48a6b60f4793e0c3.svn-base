package strosoft.app.util;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class LinkedHashMapHelper {
	/**
	 * 取得int值
	 * 
	 * @param lhmData
	 * @param key
	 * @return
	 */
	public static Integer getInt(LinkedHashMap<String, Object> lhmData, String key) {
		if (!lhmData.containsKey(key)) {
			return null;
		}
		Object objValue = lhmData.get(key);
		try {
			Integer intValue = (Integer) objValue;
			return intValue;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 取得字符串值
	 * 
	 * @param lhmData
	 * @param key
	 * @return
	 */
	public static String getString(LinkedHashMap<String, Object> lhmData, String key) {
		if (!lhmData.containsKey(key)) {
			return null;
		}
		Object objValue = lhmData.get(key);
		try {
			String strValue = (String) objValue;
			return strValue;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 取得decimal值
	 * 
	 * @param lhmData
	 * @param key
	 * @return
	 */
	public static BigDecimal getDecimal(LinkedHashMap<String, Object> lhmData, String key) {
		if (!lhmData.containsKey(key)) {
			return null;
		}
		Object objValue = lhmData.get(key);
		if (objValue == null) {
			return null;
		}
		try {
			String strValue = objValue.toString();
			BigDecimal decValue = new BigDecimal(strValue);
			return decValue;
		} catch (Exception ex) {
			return null;
		}
	}
}
