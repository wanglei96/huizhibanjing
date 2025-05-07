var managementContext = new Object();
managementContext.pageIndex = 0;
managementContext.pageSize = config.defaultPageSize;
managementContext.tableName = $.query.get("tableName");
managementContext.title = $.query.get("title");
managementContext.primaryKeyName = "id";
managementContext.gridType = "List";
managementContext.isBasicTable = false;
managementContext.allowPaging = true;
managementContext.operationColumnWidth = 120;
managementContext.detailPageMode = "Dialog";
managementContext.orderBy = "";
managementContext.bindDataOnLoad = true; //页面加载时是否绑定数据

// 当页面加载时
$(function() {
	// 加载自定义组件
	bindCustomControls();
	// 设置环境参数
	setContext();
	// 绑定工具栏权限
	setToolBarPermission();
	// 绑定控件
	bindControls();
	// 绑定数据
	if (managementContext.bindDataOnLoad) {
		bindData();
	}

});

// 加载自定义组件
function bindCustomControls() {
	// 解析全页面
	$.parser.parse();
}

/*
 * 设置环境参数
 */
function setContext() {
	//表名
	if (!managementContext.tableName) {
		if (managementContext.isBasicTable) {
			managementContext.tableName = getQuery("tableName");
		}
	}
	//数据标题
	if (getQuery("dataTitle")) {
		managementContext.dataTitle = getQuery("dataTitle");
	}
	//数据名称
	if (!managementContext.dataName) {
		managementContext.dataName = toPascal(managementContext.tableName);
	}
	// 取得列表
	if (managementContext.getListServiceName == null) {
		managementContext.getListServiceName = "Get"
			+ managementContext.dataName + "List";
	}
	// 取得实体
	if (managementContext.getServiceName == null) {
		managementContext.getServiceName = "Get" + managementContext.dataName;
	}
	// 新增
	if (managementContext.addServiceName == null) {
		managementContext.addServiceName = "Add" + managementContext.dataName;
	}
	// 修改
	if (managementContext.updateServiceName == null) {
		managementContext.updateServiceName = "Update"
		+ managementContext.dataName;
	}
	// 删除
	if (managementContext.deleteServiceName == null) {
		managementContext.deleteServiceName = "Delete"
		+ managementContext.dataName;
	}
	// 批量删除
	if (managementContext.batchDeleteServiceName == null) {
		managementContext.batchDeleteServiceName = "BatchDelete"
		+ managementContext.dataName;
	}
	// 设置默认值
	if (managementContext.setDefaultServiceName == null) {
		managementContext.setDefaultServiceName = "SetDefault"
		+ managementContext.dataName;
	}
	// 设置分页
	if (managementContext.gridType == "Tree") {
		managementContext.allowPaging = false;
	}
	if (!managementContext.allowPaging) {
		managementContext.pageSize = 10000;
	}
	if (managementContext.detailPageName == null) {
		managementContext.detailPageName = managementContext.dataName
			+ "Detail.htm";
	}
	// 设置权限参数
	setPermissionContext();
}
// 设置权限参数
function setPermissionContext() {
	if (managementContext.managementPermission == null) {
		managementContext.managementPermission = managementContext.dataName + "Management";
	}
}
// 绑定divToolbar操作权限
function setToolBarPermission() {
	var permissions = JSON.parse(localStorage.permissions);
	var tableGridPermissions = $("#divToolbar [id^='btn']");
	for (var i = 0; i < tableGridPermissions.length; i++) {
		var id = tableGridPermissions[i].id;
		var permission = managementContext.managementPermission + id.split("_")[0].substr(3);
		if (!checkIsFunctionPermission(permissions, permission)) {
			$("#" + id).hide();
		}
	}
}
// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
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
 * 取得请求参数
 */
function getParameters() {
	var parameters = getParametersBase();
	return parameters;
}
/*
 * 取得参数
 */
function getParametersBase() {
	var parameters = new Object();
	// 当前页
	parameters.pageIndex = managementContext.pageIndex;
	// 分页大小
	if (managementContext.pageSize != null) {
		parameters.pageSize = managementContext.pageSize;
	}
	// 是否基础表
	if (managementContext.isBasicTable) {
		parameters.tableName = managementContext.tableName;
	}
	// 站点id
	if (managementContext.isCms) {
		parameters.cmsSiteId = getCookie("cmsSiteId");
	}
	if (managementContext.orderBy) {
		parameters.orderBy = managementContext.orderBy;
	}
	$("#divSearchPanel input[id^=txtSearch]").each(
		function() {
			if ($(this).val() != "") {
				var searchControlId = $(this).attr("id");
				var parameterName = getParameterName(searchControlId,
					"txtSearch".length);
				parameters[parameterName] = $(this).val();
			}
		});
	$("#divSearchPanel input[id^=cmbSearch]").each(
		function() {
			if ($(this).combobox("getValue") != ""
				&& $(this).combobox("getValue") != "0") {
				var searchControlId = $(this).attr("id");
				var parameterName = getParameterName(searchControlId,
					"cmbSearch".length);
				parameters[parameterName] = $(this).combobox("getValue");
			}
		});
	$("#divSearchPanel input[id^=ctSearch]").each(
		function() {
			if ($(this).combotree("getValue") != ""
				&& $(this).combotree("getValue") != "0") {
				var searchControlId = $(this).attr("id");
				var parameterName = getParameterName(searchControlId,
					"ctSearch".length);
				parameters[parameterName] = $(this).combotree("getValue");
			}
		});
	return parameters;
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
function bindControlsBase() {
}
/*
 * 绑定控件
 */
function bindControls() {
	bindControlsBase();
}

/*
 * 绑定数据
 */
function bindData() {
	var requestParameters = getParameters();
	// alert(JSON.stringify(requestParameters));

	call(managementContext.getListServiceName, requestParameters, function(
		result) {
		outputData(result.data.dataList);
		// 设置功能权限
		if (!managementContext.isBasicTable) {
			bindTableGridFunctionPermission();
		}
		// 设置分页
		if (managementContext.allowPaging) {
			setPager(result.data);
		}
	});
}

/*
 * 设置分页
 */
function setPager(data) {
	// alert(JSON.stringify(data));
	// 分页
	var pager = $('#tableGrid').datagrid('getPager');
	pager.pagination('refresh', {
		total : data.recordCount,
		pageSize : data.pageSize,
		pageNumber : managementContext.pageIndex + 1,
		onSelectPage : function(pageNumber, pageSize) {
			managementContext.pageIndex = pageNumber - 1;
			managementContext.pageSize = pageSize;
			$(this).pagination('loading');
			bindData();
			$(this).pagination('loaded');
		}
	});
}

/*
 * 设置功能权限
 */
function bindTableGridFunctionPermission() {
	var permissions = JSON.parse(localStorage.permissions);
	var container = $("#tableGrid").parents(".datagrid");
	var tableGridPermissions = container.find("a[id^=btn]");
	for (var i = 0; i < tableGridPermissions.length; i++) {
		var id = tableGridPermissions[i].id;
		var permission = managementContext.managementPermission + id.split("_")[0].substr(3);
		if (!checkIsFunctionPermission(permissions, permission)) {
			$("#" + id).hide();
		}
	}
}
;

/*
 * 检查该功能权限是否存在
 */
function checkIsFunctionPermission(permissions, permission) {
	for (var i = 0; i < permissions.length; i++) {
		var currentPermission = permissions[i];
		if (currentPermission == permission) {
			return true;
		}
	}
	return false;
}

/*
 * 输出数据
 */
function outputData(dataList) {
	if (managementContext.gridType == "List") {
		outputListGrid(dataList);
	} else if (managementContext.gridType == "Tree") {
		outputTreeGrid(dataList);
	}
	//输出数据后处理
	postOutputData(dataList);
}
/*
 * 输出数据后处理
 */
function postOutputData() {
}
/*
 * 输出列表
 */
function outputListGrid(dataList, gridControlId, getGridRowCallback,
	getTableColumnsCallback) {
	if (gridControlId == null) {
		gridControlId = "tableGrid";
	}
	// 设置列
	try {
		var tableColumns = getTableColumns();
		if (getTableColumnsCallback != null) {
			tableColumns = getTableColumnsCallback();
		}
		// alert(JSON.stringify(tableColumns));
		$('#' + gridControlId).datagrid({
			columns : tableColumns,
			pagination : true,
			fitColumns : true
		})
	} catch (e) {
		console.log(e);
	}
	// 清空datagrid数据
	$('#' + gridControlId).datagrid('loadData', {
		total : 0,
		rows : []
	});
	for (var i = 0; i < dataList.length; i++) {
		var dataItem = dataList[i];
		outputListItem(dataItem, gridControlId, getGridRowCallback);
	}
}
/*
 * 取得数据表列
 */
function getTableColumns() {
	var tableColumns = getTableColumnsBase();
	return tableColumns;
}
/*
 * 取得数据表列
 */
function getTableColumnsBase() {
	var tableColumns = [ [ {
		field : 'ck',
		checkbox : true,
		title : 'Code',
		width : 30
	}, {
		field : 'operation',
		title : '操作',
		fixed : true,
		width : managementContext.operationColumnWidth,
		align : 'center'
	} ] ];
	if (managementContext.customTableColumns != null) {
		addTableColumns(tableColumns, managementContext.customTableColumns)
	}
	return tableColumns;
}
/*
 * 添加数据列
 */
function addTableColumn(tableColumns, tableColumn) {
	tableColumns[0].push(tableColumn);
}
/*
 * 添加数据列
 */
function addTableColumns(tableColumns, customTableColumns) {
	if (customTableColumns == null) {
		return;
	}
	for (var i = 0; i < customTableColumns.length; i++) {
		tableColumns[0].push(customTableColumns[i]);
	}
}
/*
 * 输出树型列表
 */
function outputTreeGrid(dataList) {
	var treeData = getGridTreeDataBase(dataList);
	var treeColumns = getTreeColumns(dataList);
	// alert(JSON.stringify(treeData));
	// alert(JSON.stringify(treeColumns));
	$('#tableGrid').treegrid({
		idField : managementContext.primaryKeyName,
		treeField : 'name',
		checkbox : true,
		fitColumns : true,
		showFooter : true,
		pagination : false,
		columns : treeColumns
	});
	$('#tableGrid').treegrid("loadData", treeData);
}

/*
 * 输出数据项
 */
function outputListItem(dataItem, gridControlId, getGridRowCallback) {
	if (gridControlId == null) {
		gridControlId = "tableGrid";
	}
	var gridRow = null;
	try {
		if (getGridRowCallback == null) {
			gridRow = getGridRow(dataItem);
		} else {
			gridRow = getGridRowCallback(dataItem);
		}
	} catch (e) {
		console.log(e);
	}
	$('#' + gridControlId).datagrid('insertRow', {
		// index: dataItem.id, // index start with 0
		row : gridRow
	});
}
/*
 * 取得Gird行
 */
function getGridRow(dataItem) {
	var operationHtml = getOperationHtml(dataItem);
	var gridRow = dataItem;
	gridRow.operation = operationHtml;
	return gridRow;
}

/*
 * 取得操作Html
 */
function getOperationHtml(dataItem) {
	var arrOperationButtonHtml = getOperationButtonHtmlArray(dataItem);
	var operationHtml = "<div class='grid_commands'>";
	for (var i = 0; i < arrOperationButtonHtml.length; i++) {
		operationHtml += arrOperationButtonHtml[i];
	}
	operationHtml += "</div>";
	return operationHtml;
}
/*
 * 取得操作按钮列表
 */
function getOperationButtonHtmlArray(dataItem) {
	var arrOpeartionButtonHtml = getOperationButtonHtmlArrayBase(dataItem);
	return arrOpeartionButtonHtml;
}
/*
 * 取得操作按钮列表，默认为查看，修改，删除三个按钮
 */
function getOperationButtonHtmlArrayBase(dataItem) {
	var arrOpeartionButtonHtml = new Array();
	var lnkViewHtml = getGridViewButtonHtml(dataItem);
	var lnkEditHtml = getGridEditButtonHtml(dataItem);
	var lnkDeleteHtml = getGridDeleteButtonHtml(dataItem);
	arrOpeartionButtonHtml.push(lnkViewHtml);
	arrOpeartionButtonHtml.push(lnkEditHtml);
	arrOpeartionButtonHtml.push(lnkDeleteHtml);
	return arrOpeartionButtonHtml;
}
/*
 * 保存
 */
function save() {
	var isValid = this.checkInput();
	if (!isValid) {
		return;
	}
	var data = getEntity();
	if (isEditMode()) {
		call(managementContext.updateServiceName, data, function(result) {
			closeDetailDialog();
			refreshControls();
			bindData();
			try {
				onUpdated(result);
			} catch (e) {
				console.log(e);
			}
		});
	} else {
		call(managementContext.addServiceName, data, function(result) {
			closeDetailDialog();
			refreshControls();
			bindData();
			try {
				onAdded(result);
			} catch (e) {
				console.log(e);
			}
		});
	}
}

/**
 * 检查输入
 */
function checkInput(containerId) {
	var isValid = checkInputBase(containerId);
	return isValid;
}
/**
 * 检查输入
 */
function checkInputBase(containerId) {
	let isValid;
	if (containerId == null) {
		containerId = "formDetail";
	}
	isValid = checkRequired(containerId);
	if (!isValid) {
		return false;
	}
	isValid = checkFormat(containerId);
	if (!isValid) {
		return false;
	}
	isValid = checkTextLength(containerId);
	if (!isValid) {
		return false;
	}
	isValid = checkRange(containerId);
	if (!isValid) {
		return false;
	}
	//检查最小值
	isValid = checkMin(containerId);
	if (!isValid) {
		return false;
	}
	//检查最大值
	isValid = checkMax(containerId);
	if (!isValid) {
		return false;
	}
	return true;
}

/**
 * 检查格式
 */
function checkFormat(containerId) {
	let flg = true;
	$("#" + containerId + " input[id^=txt]").each(function() {
		const format = $(this).attr("format");
		var caption = $(this).attr("caption");
		if (format === "mobilePhone") {
			const value = $(this).val();
			const reg_tel = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; //11位手机号码正则
			if (!reg_tel.test(value)) {
				showWarning(caption + "必须为手机号！");
				$(this).focus();
				flg = false;
				return false;
			}
		} else if (format === "decimal") {
			const value = $(this).val();
			const reg = /^(0\.(?!0+$)\d{1,4}|^[1-9][0-9]{0,11}(\.\d{0,4})?)$/;
			if (!reg.test(value)) {
				showWarning(caption + "必须为数字！");
				$(this).focus();
				flg = false;
				return false;
			}
		} else if (format === "datetime") {
			const value = $(this).val();
			const reg = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})$/;
			if (!reg.test(value)) {
				showWarning(caption + "必须为日期格式！");
				$(this).focus();
				flg = false;
				return false;
			}
		} else if (format === "int") {
			const value = $(this).val();
			const reg = /^-?[1-9]\d*$/;
			if (!reg.test(value)) {
				showWarning(caption + "必须为整数！");
				$(this).focus();
				flg = false;
				return false;
			}
		} else if (format === "regularExpression") {
			const regularExpression = $(this).attr("regularExpression");
			const value = $(this).val();
			if (!regularExpression.test(value)) {
				showWarning(caption + "格式不正确！");
				$(this).focus();
				flg = false;
				return false;
			}
		}
	});
	return flg;
}

/*
 * 检查必填项
 */
function checkRequired(containerId) {
	let flg = true;
	// $("#" + containerId + " input[id^=txt]").each(function () {
	$("#" + containerId + " input[notNull='true']").each(function() {
		const value = $(this).val();
		if (value === "") {
			const caption = $(this).attr("caption");
			const message = caption + "不能为空！";
			showWarning(message);
			$(this).focus();
			flg = false;
			return false;
		}
	});
	$("#" + containerId + " textarea[notNull='true']").each(function() {
		const value = $(this).val();
		if (value === "") {
			const caption = $(this).attr("caption");
			const message = caption + "不能为空！";
			showWarning(message);
			$(this).focus();
			flg = false;
			return false;
		}
	});
	$("#" + containerId + " select[notNull='true']").each(function() {
		const value = $(this).val();
		if (value === "") {
			const caption = $(this).attr("caption");
			const message = caption + "不能为空！";
			showWarning(message);
			$(this).focus();
			flg = false;
			return false;
		}
	});
	return flg;
}

//检查文本框值得长度
function checkTextLength(containerId) {
	let flg = true;
	$("#" + containerId + " input[id^=txt]").each(function() {
		const range = $(this).attr("range");
		if (range === "true") {
			let data = $(this).val();
			let maxLength = $(this).attr("maxLength");
			let minLength = $(this).attr("minLength");
			if (data.length < maxLength && data.length > minLength) {
				const caption = $(this).attr("caption");
				const message = caption + "不能为空！";
				showWarning(message);
				$(this).focus();
				flg = false;
				return false;
			}
		}
	});
	return flg;
}

//检查取值是否在范围内
function checkRange(containerId) {
	let flg = true;
	$("#" + containerId + " input[id^=txt]").each(function() {
		const range = $(this).attr("range");
		if (range === "true") {
			let value = $(this).val();
			const minValue = $(this).attr("minValue");
			const maxValue = $(this).attr("maxValue");
			if (value > maxValue && value < minValue) {
				const caption = $(this).attr("caption");
				const message = caption + "不能为空！";
				showWarning(message);
				$(this).focus();
				flg = false;
				return false;
			}
		}
	});
	return flg;
}

//检查是否大于等于最小值
function checkMin(containerId) {
	let flg = true
	$("#" + containerId + " input[id^=txt]").each(function() {
		const minValue = $(this).attr("minValue");
		if (minValue !== null && minValue !== "") {
			let value = $(this).val();
			if (value < minValue) {
				const caption = $(this).attr("caption");
				const message = caption + "不能为空！";
				showWarning(message);
				$(this).focus();
				flg = false;
				return false;
			}
		}
	});
	return flg;
}

//检查取值小于等于最大值
function checkMax(containerId) {
	let flg = true
	$("#" + containerId + " input[id^=txt]").each(function() {
		const maxValue = $(this).attr("maxValue");
		if (maxValue !== "") {
			let value = $(this).val();
			if (value > maxValue) {
				const caption = $(this).attr("caption");
				const message = caption + "不能为空！";
				showWarning(message);
				$(this).focus();
				flg = false;
				return false;
			}
		}
	});
	return flg;
}

function onUpdated() {
}
function onAdded() {
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
	if (managementContext.primaryKeyName == "id") {
		data.id = primaryKey;
	} else if (managementContext.primaryKeyName == "code") {
		data.code = primaryKey;
	}
	if (managementContext.isCms) {
		data.cmsSiteId = getCookie("cmsSiteId");
	}
	data.tableName = managementContext.tableName;
	call(managementContext.getServiceName, data, function(result) {
		outputEntity(result.data);
	});
}

/*
 * 删除
 */
function deleteDataItem(primaryKey) {
	if (!confirm("确定要删除吗?")) {
		return;
	}

	var data = new Object();
	data.primaryKey = primaryKey;
	if (managementContext.tableName != null) {
		data.tableName = managementContext.tableName;
	}
	call(managementContext.deleteServiceName, data, function(result) {
		refreshControls();
		bindData();
	});
	return false;
}

/*
 * 是否添加模式
 */
function isAddMode() {
	return managementContext.DialogMode == "add";
}
/*
 * 是否查看模式
 */
function isViewMode() {
	return managementContext.DialogMode == "view";
}

/*
 * 是否查看模式
 */
function isEditMode() {
	return managementContext.DialogMode == "edit";
}
/*
 * 是否查看模式
 */
function isCopyMode() {
	return managementContext.DialogMode == "copy";
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
	managementContext.DialogMode = "view";
	setReadonly();
	// 隐藏保存按钮
	$("#btnSave").hide();
}
/*
 * 设置只读
 */
function setReadonly() {
	$("#divDetailDialog input[id^=txt]").each(function() {
		$(this).textbox("readonly", true);
	});

	$("#divDetailDialog textarea[id^=txt]").each(function() {
		$(this).textbox("readonly", true);
	});
	$("#divDetailDialog input[id^=cmb]").each(function() {
		$(this).combobox("readonly", true);
	});
	$("#divDetailDialog input[id^=ct]").each(function() {
		$(this).combotree("readonly", true);
	});
	$("#divDetailDialog input[id^=chk]").each(function() {
		$(this).attr("disabled", "disabled");
	});
	$("#divDetailDialog input[id^=num]").each(function() {
		$(this).numberbox("readonly", true);
	});
	$("#divDetailDialog input[id^=dt]").each(function() {
		$(this).textbox("readonly", true);
	});
	$("#divDetailDialog input[id^=date]").each(function() {
		$(this).textbox("readonly", true);
	});
}
/*
 * 取消只读
 */
function removeReadonly() {
	$("#divDetailDialog input[id^=txt]").each(function() {
		$(this).textbox("readonly", false);
	});
	$("#divDetailDialog textarea[id^=txt]").each(function() {
		$(this).textbox("readonly", false);
	});
	$("#divDetailDialog input[id^=cmb]").each(function() {
		$(this).combobox("readonly", false);
	});
	$("#divDetailDialog input[id^=ct]").each(function() {
		$(this).combotree("readonly", false);
	});
	$("#divDetailDialog input[id^=chk]").each(function() {
		$(this).removeAttr("disabled");
	});
	$("#divDetailDialog input[id^=dt]").each(function() {
		$(this).datetimebox("readonly", false);
	});
	$("#divDetailDialog input[id^=num]").each(function() {
		$(this).numberbox("readonly", false);
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
	managementContext.DialogMode = "edit";
	// 取消只读
	removeReadonly();
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
	managementContext.DialogMode = "add";
	$('#formDetail').form('clear');
	// 取消只读
	removeReadonly();
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
	managementContext.DialogMode = "copy";
	// 取消只读
	removeReadonly();
	// 显示保存按钮
	$("#btnSave").show();
}
// 条件查询
function query() {
	managementContext.pageIndex = 0;

	bindData();
}

/*
 * 关闭明细对话框
 */
function closeDetailDialog() {
	$('#divDetailDialog').dialog('close');
}

/*
 * 弹出新增对话框
 */
function openAddDialog() {
	// 设置新增模式
	setAddMode();
	if (managementContext.detailPageMode == "Dialog") {
		openDialog("divDetailDialog", '新增' + managementContext.title);
	} else if (managementContext.detailPageMode == "Page") {
		window.location.href = managementContext.detailPageName + "?mode=add"
		+ "&r=" + Math.random();
	}
	event.stopPropagation();
}
/*
 * 打开修改对话框
 */
function openEditDialog(primaryKey) {
	// 设置修改模式
	setEditMode();
	// 绑定实体
	bindEntity(primaryKey);

	// 打开对话框
	if (managementContext.detailPageMode == "Dialog") {
		openDialog("divDetailDialog", '修改' + managementContext.title);
	} else if (managementContext.detailPageMode == "Page") {
		window.location.href = managementContext.detailPageName + "?mode=edit&"
		+ managementContext.primaryKeyName + "=" + primaryKey + "&r="
		+ Math.random();
	}
	event.stopPropagation();

}
/*
 * 打开复制对话框
 */
function openCopyDialog(primaryKey) {
	// 设置复制模式
	setCopyMode();
	// 绑定实体
	bindEntity(primaryKey);
	// 打开对话框
	if (managementContext.detailPageMode == "Dialog") {
		openDialog("divDetailDialog", '复制' + managementContext.title);
	} else if (managementContext.detailPageMode == "Page") {
		window.location.href = managementContext.detailPageName + "?mode=copy&"
		+ managementContext.primaryKeyName + "=" + primaryKey + "&r="
		+ Math.random();
	}
	event.stopPropagation();

}

/*
 * 打开查看对话框
 */
function openViewDialog(primaryKey) {
	// 设置查看模式
	setViewMode();
	// 绑定数据实体
	bindEntity(primaryKey);
	// 显示

	if (managementContext.detailPageMode == "Dialog") {
		openDialog("divDetailDialog", '查看' + managementContext.title);
	} else if (managementContext.detailPageMode == "Page") {
		window.location.href = managementContext.detailPageName + "?mode=view&"
		+ managementContext.primaryKeyName + "=" + primaryKey + "&r="
		+ Math.random();
	}
	event.stopPropagation();
}

/*
 * 取得按钮Html
 */
function getGridButtonHtml(dataItem, functionName, buttonText, buttonType) {
	// alert(JSON.stringify(dataItem));
	var primaryKey = getPrimaryKey(dataItem);
	var buttonId = "btn" + buttonType + "_" + primaryKey;
	var buttonIdHtml = "";
	if (typeof (buttonType) != "undefined") {
		buttonIdHtml = "id='" + buttonId + "'";
	}
	var lnkViewHtml = "<a " + buttonIdHtml + " class='easyui-linkbutton grid_button' href='#' onclick=\"return "
		+ functionName + "('" + primaryKey + "')\">" + buttonText + "</a>";
	return lnkViewHtml;
}
/*
 * 取得查看按钮Html
 */
function getGridViewButtonHtml(dataItem) {
	var buttonType = "View";
	return getGridButtonHtml(dataItem, "openViewDialog", "查看", buttonType);
}
/*
 * 取得修改按钮Html
 */
function getGridEditButtonHtml(dataItem) {
	var buttonType = "Update";
	return getGridButtonHtml(dataItem, "openEditDialog", "修改", buttonType);
}

/*
 * 取得删除按钮Html
 */
function getGridDeleteButtonHtml(dataItem) {
	// debugger
	var buttonType = "Delete";
	return getGridButtonHtml(dataItem, "deleteDataItem", "删除", buttonType);
}

/*
 * 取得复制按钮Html
 */
function getGridCopyButtonHtml(dataItem) {
	var buttonType = "Copy";
	return getGridButtonHtml(dataItem, "openCopyDialog", "复制", buttonType);
}

/*
 * 取得上移按钮Html
 */
function getGridMoveUpButtonHtml(dataItem) {
	var primaryKey = getPrimaryKey(dataItem);
	var parentValue = getParentValue(dataItem);
	var buttonId = "btnMoveUp_" + primaryKey;
	return "<a id='" + buttonId + "' class='easyui-linkbutton grid_button' href='#' onclick=\"return moveUp('"
		+ primaryKey + "','" + parentValue + "')\">上移</a>";
}

/*
 * 取得下移按钮Html
 */
function getGridMoveDownButtonHtml(dataItem) {
	var primaryKey = getPrimaryKey(dataItem);
	var parentValue = getParentValue(dataItem);
	var buttonId = "btnMoveDown_" + primaryKey;
	return "<a id='" + buttonId + "' class='easyui-linkbutton grid_button' href='#' onclick=\"return moveDown('"
		+ primaryKey + "','" + parentValue + "')\">下移</a>";
}
/*
 * 取得数据项父节点值
 */
function getParentValue(dataItem) {
	var parentValue = "";
	if (managementContext.primaryKeyName == "id") {
		parentValue = dataItem.parent_id;
	} else {
		parentValue = dataItem.parent_code;
	}
	return parentValue;
}
/*
 * 上移
 */
function moveUp(primaryKey, parentValue) {
	move("MoveUp", primaryKey, parentValue);
}
/*
 * 下移
 */
function moveDown(primaryKey, parentValue) {
	move("MoveDown", primaryKey, parentValue);
}

/*
 * 移动数据
 */
function move(moveDirection, primaryKey, parentValue) {
	var data = new Object();
	data.primaryKey = primaryKey;
	data.primaryKeyName = managementContext.primaryKeyName;
	data.tableName = managementContext.tableName;
	data.moveDirection = moveDirection;
	// 如果是树型基础表，则增加父节点条件
	if (managementContext.gridType == "Tree") {
		if (managementContext.primaryKeyName == "id") {
			if (!parentValue || parentValue == "null") {
				parentValue = 0;
			}
			data.condition = "ifnull(parent_id,0)=" + parentValue + "";
		} else {
			if (!parentValue || parentValue == "null") {
				parentValue = ''
			}
			data.condition = "ifnull(parent_code,'')='" + parentValue + "'";
		}
	}
	// 如果是Cms基础表，则增加站点id条件
	if (managementContext.isCms) {
		data.isCms = true;
		data.cmsSiteId = getCookie("cmsSiteId");
		if (data.condition != null && data.condition != "") {
			data.condition += " and "
		} else {
			data.condition = "";
		}
		var cmsSiteId = getCookie("cmsSiteId");
		data.condition += "cms_site_id=" + cmsSiteId;
	}
	call("Move", data, function(result) {
		refreshControls();
		bindData();
	});
	event.stopPropagation();
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
	// 设置主键
	if (managementContext.primaryKeyName == "id") {
		managementContext.primaryKey = dataItem.id;
	} else if (managementContext.primaryKeyName == "code") {
		managementContext.primaryKey = dataItem.code;
	}
	// 给控件赋值
	for (var key in dataItem) {
		var value = dataItem[key];

		var textboxControlId = getTextBoxControlId(key);
		if ($("#" + textboxControlId).size() > 0) {
			$("#" + textboxControlId).textbox("setValue", value);
			continue;
		}
		var numberboxControlId = getNumberboxControlId(key);
		if ($("#" + numberboxControlId).size() > 0) {
			$("#" + numberboxControlId).numberbox("setValue", value);
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
		var checkBoxControlId = getCheckBoxControlId(key);
		if ($("#" + checkBoxControlId).size() > 0) {
			value = value + "";
			if (value == "1" || value.toLowerCase() == "on"
				|| value.toLowerCase() == "true"
				|| value.toLowerCase() == "yes") {
				$("#" + checkBoxControlId).prop("checked", true);
			} else {
				$("#" + checkBoxControlId).prop("checked", false);
			}
			continue;
		}

		// 日期时间框
		var dateTimeBoxControlId = getDateTimeBoxControlId(key);
		if ($("#" + dateTimeBoxControlId).size() > 0) {
			if (value != "" && value != null) {
				$("#" + dateTimeBoxControlId).datetimebox('setValue', new Date(value.time).format("yyyy-MM-dd hh:mm:ss"))
			}
			continue;
		}
		// 日期框
		var dateBoxControlId = getDateBoxControlId(key);
		if ($("#" + dateBoxControlId).size() > 0) {
			if (value != "" && value != null) {
				$("#" + dateBoxControlId).datebox('setValue', new Date(value.time).format("yyyy-MM-dd"))
			}
			continue;
		}
	}
}

/*
 * 取得文本框控件id
 */
function getTextBoxControlId(key) {
	return "txt" + key.substr(0, 1).toUpperCase() + key.substr(1);
}
/*
 * 取得数字框控件id
 */
function getNumberboxControlId(key) {
	return "num" + key.substr(0, 1).toUpperCase() + key.substr(1);
}
/*
 * 取得复选框控件id
 */
function getCheckBoxControlId(key) {
	return "chk" + key.substr(0, 1).toUpperCase() + key.substr(1);
}
/*
 * 取得隐藏域控件id
 */
function getHiddenFieldControlId(key) {
	return "hf" + key.substr(0, 1).toUpperCase() + key.substr(1);
}
/*
 * 取得日期时间框控件id
 */
function getDateTimeBoxControlId(key) {
	return "dt" + key.substr(0, 1).toUpperCase() + key.substr(1);
}
/*
 * 取得日期控件id
 */
function getDateBoxControlId(key) {
	return "date" + key.substr(0, 1).toUpperCase() + key.substr(1);
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
	});
	$("#" + containerId + " input[id^=num]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 3);
		newEntity[propertyName] = $(this).val();
		if (newEntity[propertyName] == "") {
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=hf]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).val();
		if (newEntity[propertyName] == "") {
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=cmb]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 3);
		newEntity[propertyName] = $(this).combobox("getValue");
		if (newEntity[propertyName] == "") {
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=ct]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).combotree("getValue");
		if (newEntity[propertyName] == "") {
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
	$("#" + containerId + " input[id^=dt]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).datetimebox("getValue");
		if (newEntity[propertyName] == "") {
			newEntity[propertyName] = null;
		}
	});
	$("#" + containerId + " input[id^=date]").each(function() {
		var controlId = $(this).attr("id");
		var propertyName = getPropertyName(controlId, 2);
		newEntity[propertyName] = $(this).datebox("getValue");
		if (newEntity[propertyName] == "") {
			newEntity[propertyName] = null;
		}
	});
	if (isEditMode()) {
		if (managementContext.primaryKeyName == "id") {
			newEntity.id = managementContext.primaryKey;
		} else if (managementContext.primaryKeyName == "code") {
			newEntity.code = managementContext.primaryKey;
		}
	}
	if (managementContext.tableName != null && managementContext.isBasicTable) {
		newEntity.tableName = managementContext.tableName;
	}
	if (managementContext.isCms) {
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

/*
 * 批量删除
 */
function deleteDataItems() {
	var selectedRows = [];
	if (managementContext.gridType == "List") {
		selectedRows = $('#tableGrid').datagrid('getSelections');

	} else if (managementContext.gridType == "Tree") {
		selectedRows = $('#tableGrid').treegrid('getCheckedNodes');
	} else {
		return;
	}
	if (selectedRows.length == 0) {
		alert("请至少选择一行数据!");
		return false;
	}
	var primaryKeys = [];
	for (var i = 0; i < selectedRows.length; i++) {
		if (managementContext.primaryKeyName == "id") {
			primaryKeys.push(selectedRows[i].id);
		} else if (managementContext.primaryKeyName == "code") {
			primaryKeys.push(selectedRows[i].code);
		}
	}
	// alert(primaryKeys);
	var data = new Object();
	data.tableName = managementContext.tableName;
	data.primaryKeys = primaryKeys;
	if (managementContext.isCms) {
		data.cmsSiteId = getCookie("cmsSiteId");
	}
	if (confirm("确定要删除吗?")) {
		call(managementContext.batchDeleteServiceName, data, function(result) {
			refreshControls();
			bindData();
		});
	}
}
/*
 * 刷新控件
 */
function refreshControls() {
}

/*
 * 取得树数据
 */
function getGridTreeDataBase(lstData) {
	// alert(JSON.stringify(lstData));
	if (managementContext.primaryKeyName == "id") {
		var treeData = getGridTreeData(lstData, 0, "id", "parent_id");
		// alert(JSON.stringify(treeData));
		return treeData;
	} else if (managementContext.primaryKeyName == "code") {
		var treeData = getGridTreeData(lstData, "", "code", "parent_code");
		// alert(JSON.stringify(treeData));
		return treeData;
	}
}

/*
 * 取得树数据
 */
function getGridTreeData(lstData, parentValue, primaryKeyFieldName,
	parentFieldName) {
	var treeData = new Array();
	var lstSubData = getSubDataList(lstData, parentValue, parentFieldName);
	// console.log(lstSubData);
	for (var i = 0; i < lstSubData.length; i++) {
		var dataItem = lstSubData[i];
		dataItem.index = i;
		var treeNode = getTreeNode(dataItem);
		treeData.push(treeNode);
		var primaryKey = dataItem[primaryKeyFieldName];
		// console.log(primaryKey + ":" + dataItem[primaryKeyFieldName]);
		var childrenTreeData = getGridTreeData(lstData, primaryKey,
			primaryKeyFieldName, parentFieldName);
		if (childrenTreeData.length > 0) {
			treeNode.children = new Array();
			for (var j = 0; j < childrenTreeData.length; j++) {
				treeNode.children.push(childrenTreeData[j]);
			}
			treeNode.state = "closed";
		}
	}
	return treeData;
}
/*
 * 取得树节点
 */
function getTreeNode(dataItem) {
	var treeNode = dataItem;
	var lnkViewHtml = getGridViewButtonHtml(dataItem);
	var lnkEditHtml = getGridEditButtonHtml(dataItem);
	var lnkDeleteHtml = getGridDeleteButtonHtml(dataItem);
	var lnkMoveUpHtml = getGridMoveUpButtonHtml(dataItem);
	var lnkMoveDownHtml = getGridMoveDownButtonHtml(dataItem);
	var operation = "<div class='grid_commands'>" + lnkViewHtml + lnkEditHtml
		+ lnkDeleteHtml + lnkMoveUpHtml + lnkMoveDownHtml + "</div>";
	treeNode.operation = operation;
	return treeNode;
}

/*
 * 取得主键
 */
function getPrimaryKey(dataItem) {
	return dataItem[managementContext.primaryKeyName];
}