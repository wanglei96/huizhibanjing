package strosoft.app.service;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// <summary>
/// 服务处理类参数检验类
/// </summary>
public class ServiceHandlerValidator {
	/*
	 * 必填项列表
	 */
	public List<RequiredItem> RequiredItems;
	/*
	 * 格式项列表
	 */
	public List<FormatItem> FormatItems;
	/*
	 * 唯一项列表
	 */
	public List<UniqueItem> UniqueItems;
	/*
	 * 多主键主键唯一项列表
	 */
	public List<PrimaryKeyItem> PrimaryKeyItems;
	/*
	 * 重复项列表
	 */
	public List<RepeatItem> RepeatItems;
	/*
	 * 页面模式
	 */
	public DetailPageMode Mode;
	/*
	 * 范围项列表
	 */
	public List<RangeItem> RangeItems;
	/*
	 * 最大值项列表
	 */
	public List<MaxItem> MaxItems;
	/*
	 * 最小值项列表
	 */
	public List<MinItem> MinItems;
	/*
	 * 文本长度列表
	 */
	public List<TextLengthItem> TextLengthItems;
	/*
	 * 正则表达式检查项列表
	 */
	public List<RegularExpressionItem> RegularExpressionItems;
	private HttpServletResponse response;

	/*
	 * 构造函数
	 */
	public ServiceHandlerValidator(HttpServletResponse response) {
		this.response = response;
		this.RequiredItems = new ArrayList<RequiredItem>();
		this.FormatItems = new ArrayList<FormatItem>();
		this.UniqueItems = new ArrayList<UniqueItem>();
		this.RepeatItems = new ArrayList<RepeatItem>();
		this.PrimaryKeyItems = new ArrayList<PrimaryKeyItem>();
		this.RangeItems = new ArrayList<RangeItem>();
		this.MaxItems = new ArrayList<MaxItem>();
		this.MinItems = new ArrayList<MinItem>();
		this.TextLengthItems = new ArrayList<TextLengthItem>();
		this.RegularExpressionItems = new ArrayList<RegularExpressionItem>();
	}

	/*
	 * 构造函数
	 */
	public ServiceHandlerValidator(HttpServletResponse response,
			DetailPageMode detailPageMode) {
		this(response);
		this.Mode = detailPageMode;
	}

	/*
	 * 添加必填项
	 */
	public void addRequiredItem(JSONObject parameters, String caption,
			String parameter) {
		RequiredItem requiredItem = new RequiredItem(parameters, caption,
				parameter);
		this.RequiredItems.add(requiredItem);
	}

	/*
	 * 添加格式项
	 */
	public void addFormatItem(JSONObject parameters, String caption,
			String parameterName, Type dataType) {
		FormatItem formatItem = new FormatItem(parameters, caption,
				parameterName, dataType);
		this.FormatItems.add(formatItem);
	}

	/*
	 * 添加日期格式项
	 */
	public void addDateTimeFormatItem(JSONObject parameters, String caption,
			String parameterName) {
		this.addFormatItem(parameters, caption, parameterName, Date.class);
	}

	/*
	 * 添加Decimal格式项
	 */
	public void addDecimalFormatItem(JSONObject parameters, String caption,
			String parameterName) {
		this.addFormatItem(parameters, caption, parameterName, BigDecimal.class);
	}

	/*
	 * 添加int格式项
	 */
	public void addIntFormatItem(JSONObject parameters, String caption,
			String parameterName) {
		this.addFormatItem(parameters, caption, parameterName, int.class);
	}

	/*
	 * 添加唯一检查项
	 */
	public void addUniqueItem(JSONObject parameters, String caption,
			String parameterName, String tableName) {
		UniqueItem uniqueItem = new UniqueItem(parameters, caption,
				parameterName, tableName, parameterName);
		this.UniqueItems.add(uniqueItem);
	}

	/*
	 * 添加唯一检查项
	 */
	public void addUniqueItem(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName) {
		UniqueItem uniqueItem = new UniqueItem(parameters, caption,
				parameterName, tableName, fieldName);
		this.UniqueItems.add(uniqueItem);
	}

	/*
	 * 添加唯一项
	 */
	public void addUniqueItem(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName,
			HashMap<String, Object> primaryKeys) {
		UniqueItem uniqueItem = new UniqueItem(parameters, caption,
				parameterName, tableName, fieldName, primaryKeys);
		this.UniqueItems.add(uniqueItem);
	}

	/*
	 * 添加唯一项
	 */
	public void addUniqueItem(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName,
			String condition, HashMap<String, Object> primaryKeys) {
		UniqueItem uniqueItem = new UniqueItem(parameters, caption,
				parameterName, tableName, fieldName, condition, primaryKeys);
		this.UniqueItems.add(uniqueItem);
	}
	
	
	/*
	 * 添加多主键项
	 */
	public void addPrimaryKeyItem(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName) {
		PrimaryKeyItem primaryKeyItem = new PrimaryKeyItem(parameters, caption,
				parameterName, tableName, fieldName);
		this.PrimaryKeyItems.add(primaryKeyItem);
	}

	/*
	 * 添加重复项
	 */
	public void addRepeatItem(JSONObject parameters, String firstParamaterName,
			String secondParamaterName, String caption) {
		RepeatItem repeatItem = new RepeatItem(parameters, firstParamaterName,
				secondParamaterName, caption);
		this.RepeatItems.add(repeatItem);
	}

	/*
	 * 添加范围项
	 */
	public void addRangeItem(JSONObject parameters, String caption,
			String parameterName, BigDecimal minValue, BigDecimal maxValue) {
		this.RangeItems.add(new RangeItem(parameters, caption, parameterName,
				minValue, maxValue, BigDecimal.class));
	}

	/*
	 * 添加范围项
	 */
	public void addRangeItem(JSONObject parameters, String caption,
			String parameterName, int minValue, int maxValue) {
		this.RangeItems.add(new RangeItem(parameters, caption, parameterName,
				minValue, maxValue, Integer.class));
	}

	/*
	 * 添加范围项
	 */
	public void addRangeItem(JSONObject parameters, String caption,
			String parameterName, Date minValue, Date maxValue) {
		this.RangeItems.add(new RangeItem(parameters, caption, parameterName,
				minValue, maxValue, Date.class));
	}

	/*
	 * 最大值项
	 */
	public void addMaxItem(JSONObject parameters, String caption,
			String parameterName, BigDecimal maxValue) {
		this.MaxItems.add(new MaxItem(parameters, caption, parameterName,
				maxValue, BigDecimal.class));
	}

	/*
	 * 最大值项
	 */
	public void addMaxItem(JSONObject parameters, String caption,
			String parameterName, int maxValue) {
		this.MaxItems.add(new MaxItem(parameters, caption, parameterName,
				maxValue, int.class));
	}

	/*
	 * 最大值项
	 */
	public void addMaxItem(JSONObject parameters, String caption,
			String parameterName, Date maxValue) {
		this.MaxItems.add(new MaxItem(parameters, caption, parameterName,
				maxValue, Date.class));
	}

	/*
	 * 最小值项
	 */
	public void addMinItem(JSONObject parameters, String caption,
			String parameterName, BigDecimal minValue) {
		this.MinItems.add(new MinItem(parameters, caption, parameterName,
				minValue, BigDecimal.class));
	}

	/*
	 * 最小值项
	 */
	public void addMinItem(JSONObject parameters, String caption,
			String parameterName, int minValue) {
		this.MinItems.add(new MinItem(parameters, caption, parameterName,
				minValue, int.class));
	}

	/*
	 * 最小值项
	 */
	public void addMinItem(JSONObject parameters, String caption,
			String parameterName, Date minValue) {
		this.MinItems.add(new MinItem(parameters, caption, parameterName,
				minValue, Date.class));
	}

	/*
	 * 添加文本长期检查项
	 */
	public void addTextLengthItem(JSONObject parameters, String caption,
			String parameterName, int minLength, int maxLength) {
		TextLengthItem newTextLengthItem = new TextLengthItem(parameters,
				caption, parameterName, minLength, maxLength);
		this.TextLengthItems.add(newTextLengthItem);

	}

	/*
	 * 添加文本最小长度检查项
	 */
	public void addTextLengthItem(JSONObject parameters, String caption,
			String parameterName, int minLength) {
		this.addTextLengthItem(parameters, caption, parameterName, minLength,
				Integer.MAX_VALUE);
	}

	/*
	 * 添加正则表达式检查项
	 */
	public void addRegularExpressionItem(JSONObject parameters, String caption,
			String parameterName, String regularExpression) {
		RegularExpressionItem newRegularExpressionItem = new RegularExpressionItem();
		newRegularExpressionItem.Parameters = parameters;
		newRegularExpressionItem.Caption = caption;
		newRegularExpressionItem.ParameterName = parameterName;
		newRegularExpressionItem.RegularExpression = regularExpression;
		this.RegularExpressionItems.add(newRegularExpressionItem);
	}

	/*
	 * 添加电话正则表达式检查项
	 */
	public void addPhoneRegularExpressionItem(JSONObject parameters,
			String caption, String parameterName) {
		this.addRegularExpressionItem(
				parameters,
				caption,
				parameterName,
				"((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
	}

	/*
	 * 检查
	 */
	public Boolean check() throws Exception {
		// 检查必填项
		for (RequiredItem currentRequiredItem : this.RequiredItems) {
			Boolean isValid = checkRequired(currentRequiredItem.Parameters,
					currentRequiredItem.ParameterName,
					currentRequiredItem.Caption);
			if (!isValid) {
				return false;
			}
		}
		// 检查格式
		for (FormatItem currentFormatItem : this.FormatItems) {
			if (currentFormatItem.DataType == BigDecimal.class) {
				Boolean isValid = checkDecimalFormat(
						currentFormatItem.Parameters,
						currentFormatItem.Caption,
						currentFormatItem.ParameterName);
				if (!isValid) {
					return false;
				}
			} else if (currentFormatItem.DataType == int.class) {
				Boolean isValid = checkIntFormat(currentFormatItem.Parameters,
						currentFormatItem.Caption,
						currentFormatItem.ParameterName);
				if (!isValid) {
					return false;
				}
			} else if (currentFormatItem.DataType == Date.class) {
				Boolean isValid = checkDateTimeFormat(
						currentFormatItem.Parameters,
						currentFormatItem.Caption,
						currentFormatItem.ParameterName);
				if (!isValid) {
					return false;
				}
			}
		}
		// 检查唯一值
		for (UniqueItem currentUniqueItem : this.UniqueItems) {
			if (this.Mode == DetailPageMode.Add
					|| this.Mode == DetailPageMode.Copy) {
				Boolean isValid = checkUnique(currentUniqueItem.Parameters,
						currentUniqueItem.Caption,
						currentUniqueItem.ParameterName,
						currentUniqueItem.TableName,
						currentUniqueItem.FieldName,
						currentUniqueItem.Condition);
				if (!isValid) {
					return false;
				}
			} else if (this.Mode == DetailPageMode.Edit) {
				Boolean isValid = checkUnique(currentUniqueItem.Parameters,
						currentUniqueItem.Caption,
						currentUniqueItem.ParameterName,
						currentUniqueItem.TableName,
						currentUniqueItem.FieldName,
						currentUniqueItem.Condition,
						currentUniqueItem.PrimaryKeys);
				if (!isValid) {
					return false;
				}
			}
		}
		
		// 检查多主键唯一值
		/*Boolean isPrimaryKeysUnique =  checkPrimaryKeysUnique();
		if(!isPrimaryKeysUnique){
			return false;
		}*/
		
		// 检查重复项
		for (RepeatItem currentRepeatItem : this.RepeatItems) {
			if (currentRepeatItem.FirstParameterName.trim() != currentRepeatItem.SecondParameterName
					.trim()) {
				ServiceHandlerHelper.writeErrorResponse(response,
						String.format("%s不一致!", currentRepeatItem.Caption));
				return false;
			}
		}
		// 检查范围项
		for (RangeItem currentRangeItem : this.RangeItems) {
			Boolean isValid = this.checkRange(currentRangeItem);
			if (!isValid) {
				return false;
			}
		}
		// 检查最小值项
		for (MinItem currentMinItem : this.MinItems) {
			Boolean isValid = this.checkMin(currentMinItem);
			if (!isValid) {
				return false;
			}
		}
		// 检查最大值项
		for (MaxItem currentMaxItem : this.MaxItems) {
			Boolean isValid = this.checkMax(currentMaxItem);
			if (!isValid) {
				return false;
			}
		}
		// 检查文本长度项
		for (TextLengthItem currentTextLengthItem : this.TextLengthItems) {
			Boolean isValid = this.checkTextLength(currentTextLengthItem);
			if (!isValid) {
				return false;
			}
		}
		// 检查正则表达式项
		for (RegularExpressionItem currentRegularExpressionItem : this.RegularExpressionItems) {
			Boolean isValid = this
					.checkRegularExpression(currentRegularExpressionItem);
			if (!isValid) {
				return false;
			}
		}
		return true;
	}

	/*
	 * 检查正则表达式项
	 */
	private Boolean checkRegularExpression(
			RegularExpressionItem currentRegularExpressionItem)
			throws IOException {
		String controlValue = JsonHelper.getString(
				currentRegularExpressionItem.Parameters,
				currentRegularExpressionItem.ParameterName);
		if (controlValue == null || controlValue.equals("")) {
			return true;
		}
		// 编译正则表达式
		Pattern pattern = Pattern.compile(
				currentRegularExpressionItem.RegularExpression,
				Pattern.CASE_INSENSITIVE);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(controlValue);
		// 查找字符串中是否有匹配正则表达式的字符/字符串
		boolean isMatch = matcher.find();
		if (!isMatch) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s格式不正确！", currentRegularExpressionItem.Caption));
			return false;
		}
		return true;
	}

	/*
	 * 检查文本长度
	 */
	private Boolean checkTextLength(TextLengthItem currentTextLengthItem)
			throws IOException {
		String controlValue = JsonHelper.getString(
				currentTextLengthItem.Parameters,
				currentTextLengthItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		if (controlValue.length() < currentTextLengthItem.MinValue) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s长度不足最小值%s！", currentTextLengthItem.Caption,
					currentTextLengthItem.MinValue));
			return false;
		} else if (controlValue.length() > currentTextLengthItem.MaxValue) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s长度超出最大值%s！", currentTextLengthItem.Caption,
					currentTextLengthItem.MaxValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查范围项
	 */
	private Boolean checkRange(RangeItem currentRangeItem) throws IOException {
		if (currentRangeItem.DataType == BigDecimal.class) {
			return checkDecimalRange(currentRangeItem);
		} else if (currentRangeItem.DataType == int.class) {
			return checkIntRange(currentRangeItem);
		} else if (currentRangeItem.DataType == Date.class) {
			return checkDateTimeRange(currentRangeItem);
		}
		return true;
	}

	/*
	 * 检查范围
	 */
	private Boolean checkDecimalRange(RangeItem currentRangeItem)
			throws IOException {
		BigDecimal controlValue = JsonHelper.getDecimal(
				currentRangeItem.Parameters, currentRangeItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		double dblMinValue = Double.parseDouble(currentRangeItem.MinValue
				.toString());
		BigDecimal decMinValue = new BigDecimal(dblMinValue);
		double dblMaxValue = Double.parseDouble(currentRangeItem.MaxValue
				.toString());
		BigDecimal decMaxValue = new BigDecimal(dblMaxValue);
		if (controlValue.compareTo(decMinValue) < 0
				|| controlValue.compareTo(decMaxValue) > 0) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s值超出范围！", currentRangeItem.Caption));
			return false;
		}
		return true;
	}

	/*
	 * 检查范围
	 */
	private Boolean checkIntRange(RangeItem currentRangeItem)
			throws IOException {
		int controlValue = JsonHelper.getInt(currentRangeItem.Parameters,
				currentRangeItem.ParameterName);
		if (controlValue < Integer.parseInt(currentRangeItem.MinValue
				.toString())
				|| controlValue > Integer.parseInt(currentRangeItem.MaxValue
						.toString())) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s值超出范围！", currentRangeItem.Caption));
			return false;
		}
		return true;
	}

	/*
	 * 检查范围
	 */
	private Boolean checkDateTimeRange(RangeItem currentRangeItem)
			throws IOException {
		Date controlValue = JsonHelper.getDateValue(
				currentRangeItem.Parameters, currentRangeItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		Date dateMin = DateHelper.toDate(currentRangeItem.MinValue.toString());
		Date dateMax = DateHelper.toDate(currentRangeItem.MaxValue.toString());
		if (controlValue.compareTo(dateMin) < 0
				|| controlValue.compareTo(dateMax) > 0) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s值超出范围！", currentRangeItem.Caption));
			return false;
		}
		return true;
	}

	/*
	 * 检查范围项
	 */
	private Boolean checkMin(MinItem currentMinItem) throws IOException {
		if (currentMinItem.DataType == BigDecimal.class) {
			return checkDecimalMin(currentMinItem);
		} else if (currentMinItem.DataType == int.class) {
			return checkIntMin(currentMinItem);
		} else if (currentMinItem.DataType == Date.class) {
			return checkDateTimeMin(currentMinItem);
		}
		return true;
	}

	/*
	 * 检查最小值
	 */
	private Boolean checkDecimalMin(MinItem currentMinItem) throws IOException {
		BigDecimal controlValue = JsonHelper.getDecimal(
				currentMinItem.Parameters, currentMinItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		BigDecimal decMinValue = new BigDecimal(
				Double.parseDouble(currentMinItem.MinValue.toString()));
		if (controlValue.compareTo(decMinValue) < 0) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能小于%s！", currentMinItem.Caption,
					currentMinItem.MinValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查最小值
	 */
	private Boolean checkIntMin(MinItem currentMinItem) throws IOException {
		int controlValue = JsonHelper.getInt(currentMinItem.Parameters,
				currentMinItem.ParameterName);
		if (controlValue < Integer.parseInt(currentMinItem.MinValue.toString())) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能小于%s！", currentMinItem.Caption,
					currentMinItem.MinValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查最小值
	 */
	private Boolean checkDateTimeMin(MinItem currentMinItem) throws IOException {
		Date controlValue = JsonHelper.getDateValue(currentMinItem.Parameters,
				currentMinItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		Date dateMinValue = DateHelper.toDate(currentMinItem.MinValue
				.toString());
		if (controlValue.compareTo(dateMinValue) < 0) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能小于%s！", currentMinItem.Caption,
					currentMinItem.MinValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查范围项
	 */
	private Boolean checkMax(MaxItem currentMaxItem) throws IOException {
		if (currentMaxItem.DataType == BigDecimal.class) {
			return checkDecimalMax(currentMaxItem);
		} else if (currentMaxItem.DataType == int.class) {
			return checkIntMax(currentMaxItem);
		} else if (currentMaxItem.DataType == Date.class) {
			return checkDateTimeMax(currentMaxItem);
		}
		return true;
	}

	/*
	 * 检查最大值
	 */
	private Boolean checkDecimalMax(MaxItem currentMaxItem) throws IOException {
		BigDecimal controlValue = JsonHelper.getDecimal(
				currentMaxItem.Parameters, currentMaxItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		BigDecimal decMaxValue = new BigDecimal(
				Double.parseDouble(currentMaxItem.MaxValue.toString()));
		if (controlValue.compareTo(decMaxValue) > 0) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能大于%s！", currentMaxItem.Caption,
					currentMaxItem.MaxValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查最大值
	 */
	private Boolean checkIntMax(MaxItem currentMaxItem) throws IOException {
		int controlValue = JsonHelper.getInt(currentMaxItem.Parameters,
				currentMaxItem.ParameterName);
		if (controlValue > Integer.parseInt(currentMaxItem.MaxValue.toString())) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能大于%s！", currentMaxItem.Caption,
					currentMaxItem.MaxValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查最大值
	 */
	private Boolean checkDateTimeMax(MaxItem currentMaxItem) throws IOException {
		Date controlValue = JsonHelper.getDateValue(currentMaxItem.Parameters,
				currentMaxItem.ParameterName);
		if (controlValue == null) {
			return true;
		}
		Date dateMax = DateHelper.toDate(currentMaxItem.MaxValue.toString());
		if (controlValue.compareTo(dateMax) > 0) {
			ServiceHandlerHelper.writeErrorResponse(response, String.format(
					"%s值不能大于%s！", currentMaxItem.Caption,
					currentMaxItem.MaxValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查必填项
	 */
	public Boolean checkRequired(Object... data) throws IOException {
		for (int i = 0; i < data.length; i += 3) {
			JSONObject parameters = (JSONObject) data[i];
			String parameterName = data[i + 1].toString();
			String caption = data[i + 2].toString();
			String message = caption + "不可为空！";
			if (!parameters.containsKey(parameterName)
					|| parameters.getString(parameterName) == null
					|| parameters.getString(parameterName).equals("null")
					|| parameters.getString(parameterName).equals("")) {
				ServiceHandlerHelper.writeErrorResponse(response, message);
				return false;
			}
		}
		return true;
	}

	/*
	 * 检查日期时间格式
	 */
	public Boolean checkDateTimeFormat(Object... data) throws IOException {
		for (int i = 0; i < data.length; i += 2) {
			JSONObject jData = (JSONObject) data[i];
			String caption = data[i + 1].toString();
			String parameterName = data[i + 2].toString();
			Boolean isValid = checkDateTimeFormat(jData, caption, parameterName);
			if (!isValid) {
				return false;
			}
		}
		return true;
	}

	/*
	 * 检查日期时间格式
	 */
	public Boolean checkDateTimeFormat(JSONObject jData, String caption,
			String parameterName) throws IOException {
		if (!jData.containsKey(parameterName)) {
			return true;
		}
		try {
			Date dt = DateHelper.toDate(jData.getString(parameterName));
		} catch (Exception e) {
			ServiceHandlerHelper.writeErrorResponse(response, caption
					+ "格式不正确！");
			return false;
		}
		return true;
	}

	/*
	 * 检查Decimal格式
	 */
	public Boolean checkDecimalFormat(Object... data) throws IOException {
		for (int i = 0; i < data.length; i += 3) {
			JSONObject jData = (JSONObject) data[i];
			String caption = data[i + 1].toString();
			String parameterName = data[i + 2].toString();
			Boolean isValid = checkDecimalFormat(jData, caption, parameterName);
			if (!isValid) {
				return false;
			}
		}
		return true;
	}

	/*
	 * 检查Decimal格式
	 */
	public Boolean checkDecimalFormat(JSONObject jData, String caption,
			String parameterName) throws IOException {
		if (!jData.containsKey(parameterName)) {
			return true;
		}
		try {
			BigDecimal dec = new BigDecimal(jData.getString(parameterName));

		} catch (Exception e) {

			ServiceHandlerHelper.writeErrorResponse(response, caption
					+ "格式不正确！");
			return false;
		}
		return true;
	}

	/*
	 * 检查日期时间格式
	 */
	public Boolean checkIntFormat(Object... data) throws IOException {
		for (int i = 0; i < data.length; i += 2) {
			JSONObject jData = (JSONObject) data[i];
			String caption = data[i + 1].toString();
			String parameterName = data[i + 2].toString();
			Boolean isValid = checkIntFormat(jData, caption, parameterName);
			if (!isValid) {
				return false;
			}
		}
		return true;
	}

	/*
	 * 检查日期时间格式
	 */
	public Boolean checkIntFormat(JSONObject jData, String caption,
			String parameterName) throws IOException {
		if (jData.getString(parameterName) == null) {
			return true;
		}
		try {
			Integer.parseInt(jData.getString(parameterName));
		} catch (Exception e) {

			ServiceHandlerHelper.writeErrorResponse(response, caption
					+ "格式不正确！");
			return false;
		}
		return true;
	}

	/*
	 * 检查值是否唯一
	 */
	public Boolean checkPrimaryKeys(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName,
			String condition) throws Exception {
		String controlValue = JsonHelper.getString(parameters, parameterName);
		String commandText = String.format(
				"select count(*) from %s where %s='%s'", tableName, fieldName,
				controlValue);
		if (condition != null && !condition.equals("")) {
			commandText += String.format(" and (%s)", condition);
		}
		int count = MyBatisManager.getInstance().executeInt(commandText);
		if (count > 0) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s[%s]已经存在，请重新输入！", caption, controlValue));
			return false;
		}
		return true;
	}
	
	/*
	 * 检查多主键否唯一
	 */
	public Boolean checkUnique(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName,
			String condition) throws Exception {
		String controlValue = JsonHelper.getString(parameters, parameterName);
		String commandText = String.format(
				"select count(*) from %s where %s='%s'", tableName, fieldName,
				controlValue);
		if (condition != null && !condition.equals("")) {
			commandText += String.format(" and (%s)", condition);
		}
		int count = MyBatisManager.getInstance().executeInt(commandText);
		if (count > 0) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s[%s]已经存在，请重新输入！", caption, controlValue));
			return false;
		}
		return true;
	}

	/*
	 * 检查唯一项
	 */
	public Boolean checkUnique(JSONObject parameters, String caption,
			String parameterName, String tableName, String fieldName,
			String condition, HashMap<String, Object> primaryKeys)
			throws Exception {
		String controlValue = JsonHelper.getString(parameters, parameterName);
		String primaryKeyCondition = getPrimaryKeyCondition(primaryKeys);
		String commandText = String
				.format("select count(*) from %s where %s='%s' and (not(%s) and %s is not null)",
						tableName, fieldName, controlValue,
						primaryKeyCondition, fieldName);
		if (condition != null && !condition.equals("")) {
			commandText += String.format(" and (%s)", condition);
		}
		int count = MyBatisManager.getInstance().executeInt(commandText);
		if (count > 0) {
			ServiceHandlerHelper.writeErrorResponse(response,
					String.format("%s[%s]已经存在，请重新输入！", caption, controlValue));
			return false;
		}
		return true;
	}

	/*
	 * 取得主键条件
	 */
	private static String getPrimaryKeyCondition(
			HashMap<String, Object> primaryKeys) {
		StringBuilder sbPrimaryKeyCondition = new StringBuilder();
		for (String key : primaryKeys.keySet()) {
			if (sbPrimaryKeyCondition.length() > 0) {
				sbPrimaryKeyCondition.append(" and ");
			}
			sbPrimaryKeyCondition.append(String.format("%s = '%s'", key,
					primaryKeys.get(key)));
		}
		String primaryKeyCondition = sbPrimaryKeyCondition.toString();
		return primaryKeyCondition;
	}

	/*
	 * 必填项
	 */
	public class RequiredItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;

		public RequiredItem() {
		}

		/*
		 * 构造函数
		 */
		public RequiredItem(JSONObject parameters, String caption,
				String parameterName) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
		}
	}

	/*
	 * 格式项
	 */
	public class FormatItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 数据类型
		 */
		public Type DataType;

		public FormatItem() {
		}

		/*
		 * 构造函数
		 */
		public FormatItem(JSONObject parameters, String caption,
				String parameterName, Type dataType) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.DataType = dataType;
		}
	}

	/*
	 * 唯一项
	 */
	public class UniqueItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 表名
		 */
		public String TableName;
		/*
		 * 列名
		 */
		public String FieldName;
		/*
		 * 条件
		 */
		public String Condition;
		/*
		 * 主键集合
		 */
		public HashMap<String, Object> PrimaryKeys = new HashMap<String, Object>();

		public UniqueItem() {
		}

		/*
		 * 构造函数
		 */
		public UniqueItem(JSONObject parameters, String caption,
				String parameterName, String tableName, String fieldName,
				String primaryKeyColumnName, Object primaryKeyValue) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.TableName = tableName;
			this.FieldName = fieldName;
			HashMap<String, Object> primaryKeys = new HashMap<String, Object>();
			primaryKeys.put(primaryKeyColumnName, primaryKeyValue);
			this.PrimaryKeys = primaryKeys;
		}

		public UniqueItem(JSONObject parameters, String caption,
				String parameterName, String tableName, String fieldName) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.TableName = tableName;
			this.FieldName = fieldName;
		}

		/*
		 * 构造函数
		 */
		public UniqueItem(JSONObject parameters, String caption,
				String parameterName, String tableName, String fieldName,
				HashMap<String, Object> primaryKeys) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.TableName = tableName;
			this.FieldName = fieldName;
			this.PrimaryKeys = primaryKeys;
		}

		/*
		 * 构造函数
		 */
		public UniqueItem(JSONObject parameters, String caption,
				String parameterName, String tableName, String fieldName,
				String condition, HashMap<String, Object> primaryKeys) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.TableName = tableName;
			this.FieldName = fieldName;
			this.Condition = condition;
			this.PrimaryKeys = primaryKeys;
		}
	}
	
	/*
	 * 多主键唯一
	 */
	public class PrimaryKeyItem{
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 表名
		 */
		public String TableName;
		/*
		 * 列名
		 */
		public String FieldName;
		
		public PrimaryKeyItem() {
		}
		/*
		 * 构造函数
		 */
		public PrimaryKeyItem(JSONObject parameters, String caption,
				String parameterName, String tableName, String fieldName) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.TableName = tableName;
			this.FieldName = fieldName;
		}
	}

	/*
	 * 重复项
	 */
	public class RepeatItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 密码数据
		 */
		public String FirstParameterName;
		/*
		 * 重复密码数据
		 */
		public String SecondParameterName;
		/*
		 * 标题
		 */
		public String Caption;

		public RepeatItem() {
		}

		/*
		 * 构造函数
		 */
		public RepeatItem(JSONObject parameters, String firstParamaterName,
				String secondParamaterName, String caption) {
			this.Parameters = parameters;
			this.FirstParameterName = firstParamaterName;
			this.SecondParameterName = secondParamaterName;
			this.Caption = caption;
		}
	}

	/*
	 * 范围项
	 */
	public class RangeItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 最小值
		 */
		public Object MinValue;
		/*
		 * 最大值
		 */
		public Object MaxValue;
		/*
		 * 数据类型
		 */
		public Type DataType;

		public RangeItem() {
		}

		/*
		 * 构造函数
		 */
		public RangeItem(JSONObject parameters, String caption,
				String parameterName, Object minValue, Object maxValue,
				Type dataType) {
			this.Parameters = parameters;
			this.ParameterName = parameterName;
			this.Caption = caption;
			this.MinValue = minValue;
			this.MaxValue = maxValue;
			this.DataType = dataType;
		}
	}

	/*
	 * 最大值项
	 */
	public class MaxItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 最大值
		 */
		public Object MaxValue;
		/*
		 * 数据类型
		 */
		public Type DataType;

		public MaxItem() {
		}

		/*
		 * 构造函数
		 */
		public MaxItem(JSONObject parameters, String caption,
				String parameterName, Object maxValue, Type dataType) {
			this.Parameters = parameters;
			this.ParameterName = parameterName;
			this.Caption = caption;
			this.MaxValue = maxValue;
			this.DataType = dataType;
		}
	}

	/*
	 * 最小值项
	 */
	public class MinItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 最小值
		 */
		public Object MinValue;
		/*
		 * 数据类型
		 */
		public Type DataType;

		public MinItem() {
		}

		/*
		 * 构造函数
		 */
		public MinItem(JSONObject parameters, String caption,
				String parameterName, Object minValue, Type dataType) {
			this.Parameters = parameters;
			this.ParameterName = parameterName;
			this.Caption = caption;
			this.MinValue = minValue;
			this.DataType = dataType;
		}
	}

	/*
	 * 文本长期项
	 */
	public class TextLengthItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 最小值
		 */
		public int MinValue;
		/*
		 * 最大值
		 */
		public int MaxValue;

		public TextLengthItem() {
		}

		/*
		 * 构造函数
		 */
		public TextLengthItem(JSONObject parameters, String caption,
				String parameterName, int minValue, int maxValue) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.MinValue = minValue;
			this.MaxValue = maxValue;
		}
	}

	/*
	 * 正则表达式项
	 */
	public class RegularExpressionItem {
		/*
		 * 数据
		 */
		public JSONObject Parameters;
		/*
		 * 参数名
		 */
		public String ParameterName;
		/*
		 * 标题
		 */
		public String Caption;
		/*
		 * 正则表达式
		 */
		public String RegularExpression;

		/*
		 * 构造函数
		 */
		public RegularExpressionItem() {
		}

		/*
		 * 构造函数
		 */
		public RegularExpressionItem(JSONObject parameters, String caption,
				String parameterName, String regularExpress) {
			this.Parameters = parameters;
			this.Caption = caption;
			this.ParameterName = parameterName;
			this.RegularExpression = regularExpress;
		}
	}
	

	public  Boolean checkPrimaryKeysUnique() throws IOException, Exception{
		if(this.PrimaryKeyItems.size()<1){
			return true;
		}
		String condition = ""; 
		String info = "";
		String tableName = "";
		for(PrimaryKeyItem currentPrimaryKeyItem : this.PrimaryKeyItems){
			String dataName = currentPrimaryKeyItem.ParameterName;
			String fieldName = currentPrimaryKeyItem.FieldName;
			String caption = currentPrimaryKeyItem.Caption;
			String value = currentPrimaryKeyItem.Parameters.getString(dataName);
			condition +=" and "+fieldName+"='"+value+"' ";
			info += caption+"["+value+"]";
			tableName = currentPrimaryKeyItem.TableName;
		}
		String command  = "select count(*) from "+tableName+" where 1=1 "+condition;
		int count = MyBatisManager.getInstance().executeInt(command);
		if(this.Mode == DetailPageMode.Add
				|| this.Mode == DetailPageMode.Copy){
			if(count>0){
				ServiceHandlerHelper.writeErrorResponse(response,
						String.format("%s已存在!，请重新输入！", info));
				return false;
			}
		} else if (this.Mode == DetailPageMode.Edit){
			if (count > 1) {
				ServiceHandlerHelper.writeErrorResponse(response,
						String.format("%s已经存在，请重新输入！",info));
				return false;
			}
		}
		return true;
	}

}
