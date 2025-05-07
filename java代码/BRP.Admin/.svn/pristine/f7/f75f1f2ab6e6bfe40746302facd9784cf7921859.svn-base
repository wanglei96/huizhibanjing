package strosoft.app.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class HashMapHelper {
	/*
	 * 取得属性值（可多个)
	 */
	public static String getString(HashMap hmData, String... keys) {
		for (Object key : keys) {
			if (hmData.containsKey(key)) {
				Object objValue = hmData.get(key);
				if (objValue == null) {
					return null;
				}
				return objValue.toString();
			}
		}
		return null;
	}

	/*
	 * 取得属性值（可多个)
	 */
	public static int getInt(HashMap hmData, String... keys) {
		for (String key : keys) {
			if (hmData.containsKey(key)) {
				Object objValue = hmData.get(key);
				if (objValue == null) {
					return 0;
				}
				return Integer.parseInt(objValue.toString());
			}
		}
		return 0;
	}

	public static Date getDatetime(HashMap<String, Object> hmData, String... keys) {
		for (String key : keys) {
			if (hmData.containsKey(key)) {
				Object objValue = hmData.get(key);
				if (objValue == null) {
					return null;
				}
				Date dateValue = DateHelper.toDateTime(objValue.toString());
				return dateValue;
			}
		}
		return null;
	}

	/*
	 * 取得Boolean属性值（可多个)
	 */
	public static Boolean getBoolean(HashMap hmData, String... keys) {
		for (String key : keys) {
			if (hmData.containsKey(key)) {
				Object objValue = hmData.get(key);
				if (objValue == null) {
					return null;
				}
				String strValue = objValue.toString().toLowerCase();
				if (strValue.equals("1") || strValue.equals("yes") || strValue.equals("y") || strValue.equals("true")
						|| strValue.equals("on") || strValue.equals("男") || strValue.equals("是")) {
					return true;
				} else if (strValue.equals("0") || strValue.equals("no") || strValue.equals("n")
						|| strValue.equals("false") || strValue.equals("off") || strValue.equals("女")
						|| strValue.equals("否")) {
					return false;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/*
	 * 取得Decimal属性值（可多个)
	 */
	public static BigDecimal getDecimal(HashMap hmData, String... keys) {
		for (String key : keys) {
			if (hmData.containsKey(key)) {
				Object objValue = hmData.get(key);
				if (objValue == null) {
					return null;
				}
				try {
					BigDecimal decValue = new BigDecimal(objValue.toString());
					return decValue;
				} catch (Exception e) {
					return null;
				}

			}
		}
		return null;
	}
}
