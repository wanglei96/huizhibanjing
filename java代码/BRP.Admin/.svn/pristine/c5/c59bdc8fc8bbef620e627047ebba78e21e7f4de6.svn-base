package strosoft.cg.common;

import strosoft.app.util.StringHelper;
import strosoft.cg.data.CgTablePrimaryKey;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CodeGenerateUtil {

	public static String getParameterName(String columnName) {
		String[] list = columnName.split("_");
		StringBuffer result = new StringBuffer(list[0]);
		for (int i = 1; i < list.length; i++) {
			result.append(upperCase(list[i]));
		}
		return result.toString();
	}

	/**
	 * 首字母大写的驼峰格式字符串转换为下划线格式字符串
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		char underline = '_';
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					sb.append(underline);
				}
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String getFormattedTableName(String tableName) {
		String[] list = tableName.split("_");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			result.append(upperCase(list[i]));
		}
		return result.toString();
	}

	public static String upperCase(String str) {
		if (StringHelper.isNullOrEmpty(str)) {
			return "";
		}
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	public static String getTypeString(String typeEntityName) {
		String[] list = typeEntityName.split("\\.");
		return list[list.length - 1];
	}

	public static String getAttributeString(String name, String value) {
		if (null == value) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		sb.append("=\"");
		sb.append(value);
		sb.append("\" ");
		return sb.toString();
	}

	// 执行window命令
	public static void exc(String cmd) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");

		// kick off stderr
		errorGobbler.start();

		StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
		// kick off stdout
		outGobbler.start();

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 执行window命令
	public static void exc(String cmd, String[] envp, File dir) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd, envp, dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");

		// kick off stderr
		errorGobbler.start();

		StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
		// kick off stdout
		outGobbler.start();

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 判断主键是否为uuid类型
	public static Boolean isUUIDPrimaryKey(List<CgTablePrimaryKey> primaryKeys) {
		if (null == primaryKeys || primaryKeys.size() != 1) {
			return false;
		}
		if (!primaryKeys.get(0).getColumnName().equals("uuid")) {
			return false;
		}
		return true;
	}
}
