package strosoft.cms.data;

import java.util.HashMap;

public class CmsContentContext {
	private String ellipsis; // "文字超出部分显示的文字"
	// private String formatString; // "显示的格式"
	// private String isClearTags; // "是否清除HTML标签"
	// private String isDynamic; // "是否动态显示"
	private String isLower; // "是否转换为小写"
	// private String isOriginal; // "如果是引用内容，是否获取所引用内容的值"
	// private String isReturnToBr; // "是否将回车替换为HTML换行标签"
	private String isUpper; // "是否转换为大写"
	private String leftText; // "显示在信息前的文字"
	private String length; // "指定字符长度"
	private String no; // "显示第几项"
	private String replace; // "需要替换的文字，可以是正则表达式"
	private String rightText; // "显示在信息后的文字"
	private String separator; // "显示多项时的分割字符串"
	private String startIndex; // "字符开始位置"
	private String to; // "替换replace的文字信息"
	private String type; // "显示的类型"
	private String wordNum; // "显示字符的数目"
	private String embed;//是否使用flash播放器
	private String itemIndex;

	public CmsContentContext() {

	}

	public CmsContentContext(HashMap<String, String> attributeCondition) {
		this.ellipsis = attributeCondition.get("ellipsis".toLowerCase());
		this.isLower = attributeCondition.get("isLower".toLowerCase());
		this.isUpper = attributeCondition.get("isUpper".toLowerCase());
		this.leftText = attributeCondition.get("leftText".toLowerCase());
		this.length = attributeCondition.get("length".toLowerCase());
		this.no = attributeCondition.get("no".toLowerCase());
		this.replace = attributeCondition.get("replace".toLowerCase());
		this.rightText = attributeCondition.get("rightText".toLowerCase());
		this.separator = attributeCondition.get("separator".toLowerCase());
		this.startIndex = attributeCondition.get("startIndex".toLowerCase());
		this.to = attributeCondition.get("to".toLowerCase());
		this.type = attributeCondition.get("type".toLowerCase());
		this.wordNum = attributeCondition.get("wordNum".toLowerCase());
		this.setEmbed(attributeCondition.get("embed".toLowerCase()));
		this.setItemIndex(attributeCondition.get("itemIndex".toLowerCase()));
	}

	public String getEllipsis() {
		return ellipsis;
	}

	public void setEllipsis(String ellipsis) {
		this.ellipsis = ellipsis;
	}

	public String getIsLower() {
		return isLower;
	}

	public void setIsLower(String isLower) {
		this.isLower = isLower;
	}

	public String getIsUpper() {
		return isUpper;
	}

	public void setIsUpper(String isUpper) {
		this.isUpper = isUpper;
	}

	public String getLeftText() {
		return leftText;
	}

	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public String getRightText() {
		return rightText;
	}

	public void setRightText(String rightText) {
		this.rightText = rightText;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWordNum() {
		return wordNum;
	}

	public void setWordNum(String wordNum) {
		this.wordNum = wordNum;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}

}
