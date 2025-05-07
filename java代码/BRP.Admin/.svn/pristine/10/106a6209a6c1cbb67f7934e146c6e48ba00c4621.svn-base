package strosoft.app.util;

import java.util.HashMap;

public class DataTypeHelper {

	public enum SqlTypeEnum {
		INT, VARCHAR, NVARCHAR, DECIMAL, DATE, MEDIUMTEXT, YEAR, TIMESTAMP, DATETIME, TIME, BOOLEAN, ID, DOUBLE, FLOAT,
		CHAR, TEXT, NTEXT, LONGTEXT, BLOB, INTEGER, BIT, MEDIUMINT, SMALLINT, BIGINT, TINYINT,;
	}

	public static HashMap<String, String> getDataTypeBySqlType(String typeName) {
		SqlTypeEnum typeNameEnum = SqlTypeEnum.valueOf(typeName.toUpperCase());
		HashMap<String, String> dataType = new HashMap<String, String>();
		// java类型
		String javaType = null;
		// 格式化字符串
		String formatString = null;
		// 对其方式
		String sysAlignCode = null;
		// 页面UI控件类型
		String sysUiControlCode = null;
		// 验证类型
		String sysValidationDataTypeCode = null;
		switch (typeNameEnum) {
		case NVARCHAR:
		case VARCHAR:
		case CHAR:
			javaType = "java.lang.String";
			sysAlignCode = "Center";
			sysUiControlCode = "TextBox";
			break;
		case TEXT:
		case NTEXT:
		case MEDIUMTEXT:
		case LONGTEXT:
			javaType = "java.lang.String";
			sysAlignCode = "Left";
			sysUiControlCode = "TextArea";
			break;
		case TINYINT:
		case SMALLINT:
		case INT:
		case INTEGER:
		case MEDIUMINT:
			javaType = "java.lang.Integer";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Integer";
			break;
		case DECIMAL:
			javaType = "java.math.BigDecimal";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Number";
			break;
		case YEAR:
			javaType = "java.util.Date";
			sysAlignCode = "Left";
			sysUiControlCode = "DateBox";
			formatString = "yyyy";
			break;
		case DATE:
			javaType = "java.util.Date";
			sysAlignCode = "Left";
			sysUiControlCode = "DateBox";
			formatString = "MM-dd";
			break;
		case TIME:
			javaType = "java.util.Time";
			sysAlignCode = "Left";
			sysUiControlCode = "DateTimeBox";
			formatString = "yyyy-MM-dd hh:mm:ss";
			sysValidationDataTypeCode = "Date";
			break;
		case DATETIME:
		case TIMESTAMP:
			javaType = "java.sql.Timestamp";
			sysAlignCode = "Left";
			sysUiControlCode = "DateTimeBox";
			formatString = "yyyy-MM-dd hh:mm:ss";
			sysValidationDataTypeCode = "Date";
			break;
		case DOUBLE:
			javaType = "java.lang.Double";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Number";
			break;
		case FLOAT:
			javaType = "java.lang.Float";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Number";
			break;
		case BIT:
		case BOOLEAN:
			javaType = "java.lang.Boolean";
			sysAlignCode = "Left";
			sysUiControlCode = "RadioButtonList";
			break;
		case BIGINT:
			javaType = "java.math.BigInteger";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Number";
			break;
		case ID:
			javaType = "java.lang.Long";
			sysAlignCode = "Left";
			sysUiControlCode = "NumericBox";
			sysValidationDataTypeCode = "Number";
			break;
		case BLOB:
			javaType = "java.util.byte[]";
			sysAlignCode = "Left";
			sysUiControlCode = "TextArea";
			break;
		}
		dataType.put("javaType", javaType);
		dataType.put("formatString", formatString);
		dataType.put("sysAlignCode", sysAlignCode);
		dataType.put("sysUiControlCode", sysUiControlCode);
		dataType.put("sysValidationDataTypeCode", sysValidationDataTypeCode);
		return dataType;
	}

}
