var detailContext = new Object();
detailContext.tableName = $.query.get("tableName");
detailContext.title = $.query.get("title");
detailContext.primaryKeyName = "id";
detailContext.isBasicTable = false;
detailContext.mode = "custom";
// 当页面加载时
$(function() {
	// 设置环境参数
	setContext();
	// 绑定控件
	bindControls();
	// 绑定数据
	bindData();
});

/*
 * 设置环境参数
 */
function setContext() {
	// 取得实体
	if (detailContext.getServiceName == null) {
		detailContext.getServiceName = "Get" + detailContext.dataName;
	}
	// 新增
	if (detailContext.addServiceName == null) {
		detailContext.addServiceName = "Add" + detailContext.dataName;
	}
	// 修改
	if (detailContext.updateServiceName == null) {
		detailContext.updateServiceName = "Update" + detailContext.dataName;
	}
	detailContext.mode = $.query.get("mode");
}
// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
/*
 * 根据查询条件控件id取得参数名称
 */
function getParameterName(searchControlId, prefixLength) {
	var parameterName = searchControlId.substr(prefixLength);
	parameterName = parameterName.substr(0, 1).toLowerCase()
			+ parameterName.substr(1);
	return parameterName;
}
/*
 * 绑定控件
 */
function bindControls() {
}
/*
 * 绑定数据
 */
function bindData(){
	bindDataBase();
}
/*
 * 绑定数据
 */
function bindDataBase() {
	var primaryKey = $.query.get(detailContext.primaryKeyName);
	if (isViewMode()) {
		// 绑定数据实体
		bindEntity(primaryKey);
		// 设置查看模式
		setViewMode();
	} else if (isAddMode()) {
		setAddMode();
	} else if (isEditMode()) {
		// 绑定实体
		bindEntity(primaryKey);
		// 设置修改模式
		setEditMode();

	} else if (isCopyMode()) {
		// 绑定实体
		bindEntity(primaryKey);
		// 设置修改模式
		setCopyMode();

	}
}

/*
 * 绑定数据实体
 */
function bindEntity(primaryKey) {
	bindEntityBase(primaryKey);
}
/*
 * 绑定数据实体
 */
function bindEntityBase(primaryKey) {
	var data = new Object();
	if (detailContext.primaryKeyName == "id") {
		data.id = primaryKey;
	} else if (detailContext.primaryKeyName == "code") {
		data.code = primaryKey;
	}
	data.tableName = detailContext.tableName;

	call(detailContext.getServiceName, data, function(result) {
		outputEntity(result.data);
	});
}
/*
 * 保存
 */
function save() {
	var data = getEntity();
	if (isEditMode()) {
		call(detailContext.updateServiceName, data, function(result) {
			closeDetailDialog();
			refreshControls();
			bindData();
		});
	} else {
		call(detailContext.addServiceName, data, function(result) {
			closeDetailDialog();
			refreshControls();
			bindData();
		});
	}
}

/*
 * 是否添加模式
 */
function isAddMode() {
	return detailContext.mode == "add";
}
/*
 * 是否查看模式
 */
function isViewMode() {
	return detailContext.mode == "view";
}

/*
 * 是否查看模式
 */
function isEditMode() {
	return detailContext.mode == "edit";
}
/*
 * 是否查看模式
 */
function isCopyMode() {
	return detailContext.mode == "copy";
}

/*
 * 设置查看模式
 */
function setViewMode() {
	setViewModeBase();
}
/*
 * 设置查看模式
 */
function setViewModeBase() {
	setReadonly();
	// 隐藏保存按钮
	$("#btnSave").hide();
}
/*
 * 设置只读
 */
function setReadonly() {
	$("#formDetail input[id^=txt]").each(function() {
		$(this).textbox("readonly", true);
	});

	$("#formDetail textarea[id^=txt]").each(function() {
		$(this).textbox("readonly", true);
	});
	$("#formDetail input[id^=cmb]").each(function() {
		$(this).combobox("readonly", true);
	});
	$("#formDetail input[id^=ct]").each(function() {
		$(this).combotree("readonly", true);
	});
	$("#formDetail input[id^=chk]").each(function() {
		$(this).attr("disabled", "disabled");
	});
}
/*
 * 取消只读
 */
function removeReadonly() {
	$("#formDetail input[id^=txt]").each(function() {
		$(this).textbox("readonly", false);
	});

	$("#formDetail textarea[id^=txt]").each(function() {
		$(this).textbox("readonly", false);
	});
	$("#formDetail input[id^=cmb]").each(function() {
		$(this).combobox("readonly", false);
	});
	$("#formDetail input[id^=ct]").each(function() {
		$(this).combotree("readonly", false);
	});
	$("#formDetail input[id^=chk]").each(function() {
		$(this).removeAttr("disabled");
	});
}
/*
 * 设置修改模式
 */
function setEditMode() {
	setEditModeBase();
}
/*
 * 设置修改模式
 */
function setEditModeBase() {
	// 显示保存按钮
	$("#btnSave").show();
}
/*
 * 设置添加模式
 */
function setAddMode() {
	setAddModeBase();
}
/*
 * 设置添加模式
 */
function setAddModeBase() {
	detailContext.mode = "add";
	$('#formDetail').form('clear');
	// 显示保存按钮
	$("#btnSave").show();
}
/*
 * 设置复制模式
 */
function setCopyMode() {
	setCopyModeBase();
}
/*
 * 设置修改模式
 */
function setCopyModeBase() {
	// 显示保存按钮
	$("#btnSave").show();
}

/*
 * 关闭明细对话框
 */
function closeDetailDialog() {
	goback();
}

/*
 * 输出实体信息
 */
function outputEntity(dataItem) {
	outputEntityBase(dataItem);
}
/*
 * 输出实体信息
 */
function outputEntityBase(dataItem) {
	//alert(JSON.stringify(dataItem));
	// 设置主键
	if (detailContext.primaryKeyName == "id") {
		detailContext.primaryKey = dataItem.id;
	} else if (detailContext.primaryKeyName == "code") {
		detailContext.primaryKey = dataItem.code;
	}
	// 给控件赋值
	for ( var key in dataItem) {
		var value = dataItem[key];

		var textboxControlId = getTextboxControlId(key);
		if ($("#" + textboxControlId).size() > 0) {
			$("#" + textboxControlId).textbox("setValue", value);
			continue;
		}
		var hiddenFieldControlId = getHiddenFieldControlId(key);
		if ($("#" + hiddenFieldControlId).size() > 0) {
			$("#" + hiddenFieldControlId).val(value);
			continue;
		}
		var comboBoxControlId = getComboBoxControlId(key);
		if ($("#" + comboBoxControlId).size() > 0) {
			$("#" + comboBoxControlId).combobox("setValue", value);
			continue;
		}
		var comboTreeControlId = getComboTreeControlId(key);
		if ($("#" + comboTreeControlId).size() > 0) {
			$("#" + comboTreeControlId).combotree("setValue", value);
			continue;
		}
	}
}

/*
 * 取得文本框控件id
 */
function getTextboxControlId(key) {
	return "txt" + key.substr(0, 1).toUpperCase() + key.substr(1);
}

/*
 * 取得隐藏域控件id
 */
function getHiddenFieldControlId(key) {
	return "hf" + key.substr(0, 1).toUpperCase() + key.substr(1);
}

/*
 * 取得树型组合框输入框控件id
 */
function getComboTreeControlId(key) {
	var comboBoxControlId = "ct" + key.substr(0, 1).toUpperCase()
			+ key.substr(1);
	return comboBoxControlId;
}
/*
 * 取得组合框控件id
 */
function getComboBoxControlId(key) {
	var comboBoxControlId = "cmb" + key.substr(0, 1).toUpperCase()
			+ key.substr(1);
	return comboBoxControlId;
}

/*
 * 取得实体
 */
function getEntity() {
	var newEntity = getEntityBase();
	return newEntity;
}
/*
 * 取得实体
 */
function getEntityBase(containerId) {
	if (containerId == null) {
		containerId = "formDetail";
	}
	var newEntity = new Object();
	$("#" + containerId + " input[id^=txt]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 3);
		newEntity[propertyName] = $(this).val();
		if(newEntity[propertyName] == ""){
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=hf]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).val();
		if(newEntity[propertyName] == ""){
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=cmb]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 3);
		newEntity[propertyName] = $(this).combobox("getValue");
		if(newEntity[propertyName] == ""){
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=ct]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).combotree("getValue");
		if(newEntity[propertyName] == ""){
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=chk]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 3);
		if ($(this)[0].checked) {
			newEntity[propertyName] = 1;
		} else {
			newEntity[propertyName] = 0;
		}
	});
	if (isEditMode()) {
		if (detailContext.primaryKeyName == "id") {
			newEntity.id = detailContext.primaryKey;
		} else if (detailContext.primaryKeyName == "code") {
			newEntity.code = detailContext.primaryKey;
		}
	}
	if (detailContext.tableName != null && detailContext.isBasicTable) {
		newEntity.tableName = detailContext.tableName;
	}
	if (detailContext.isCms) {
		newEntity.cmsSiteId = getCookie("cmsSiteId");
	}
	return newEntity;
}
/*
 * 根据控件id取得属性名称
 */
function getPropertyName(controlId, prifixLength) {
	var propertyName = controlId.substr(prifixLength);
	propertyName = propertyName.substr(0, 1).toLowerCase()
			+ propertyName.substr(1);
	return propertyName;
}
