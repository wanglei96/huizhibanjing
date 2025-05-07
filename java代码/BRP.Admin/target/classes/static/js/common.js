/*
添加到收藏夹
 */
function addFavorite(url, title) {
	window.external.AddFavorite(url, title);
}
//对Date的扩展,将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符,
//年(y)可以用 1-4 个占位符,毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
				: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
//对Date的扩展,将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符,
//年(y)可以用 1-4 个占位符,毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
				: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
/*
 * 取得文本宽度
 */
function getTextWidth(text, fontSize) {
	var textWidth = 0;
	for (var i = 0; i < text.length; i++) {
		var c = text[i];
		var strCode = text.charCodeAt(i);
		if ((strCode > 65248) || (strCode == 12288)) {
			textWidth += fontSize;
		} else {
			textWidth += fontSize / 2;
		}
	}
	return textWidth;
}
/*
 * 下载Canvas 调用示例：downloadCanvas({ type: 'png', fileName: "传播路径", elementID:
 * "divSpreadPathBox" });
 */
function downloadCanvas(options) {
	var type = options.type;
	// 图片导出为 png 格式
	var imgData = $("#" + options.elementID + " canvas")[0].toDataURL(type);
	console.log(options);
	/**
	 * 获取mimeType
	 * 
	 * @param {String}
	 *            type the old mime-type
	 * @return the new mime-type
	 */
	var _fixType = function(type) {
		type = type.toLowerCase().replace(/jpg/i, 'jpeg');
		var r = type.match(/png|jpeg|bmp|gif/)[0];
		return 'image/' + r;
	};
	// 加工image data,替换mime type
	var mineType = _fixType(type);
	imgData = imgData.replace(mineType, 'image/octet-stream');
	/**
	 * 在本地进行文件保存
	 * 
	 * @param {String}
	 *            data 要保存到本地的图片数据
	 * @param {String}
	 *            filename 文件名
	 */
	var saveFile = function(data, filename) {
		var save_link = document.createElementNS(
			'http://www.w3.org/1999/xhtml', 'a');
		save_link.href = data;
		save_link.download = filename;

		var event = document.createEvent('MouseEvents');
		event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0,
			false, false, false, false, 0, null);
		save_link.dispatchEvent(event);
	};

	// 下载后的问题名
	var filename = options.fileName + (new Date()).getTime() + '.' + type;
	// download
	saveFile(imgData, filename);
}
/*
 * 显示警告消息
 */
function showWarning(msg) {
	$.messager.alert(config.systemName, msg, 'warning');
}

/*
 * 显示消息
 */
function showMessage(msg) {
	$.messager.alert(config.systemName, msg, 'info');
}
/**
 * 调用接口
 * @param {string} serviceName
 * @param {Object} data
 * @param {Function} successCallback
 * @param {Function} [errorCallback]
 */
function call(serviceName, data, successCallback, errorCallback) {
	var requestParameters = new Object();
	requestParameters.serviceName = serviceName;
	requestParameters.data = JSON.stringify(data);
	requestParameters.token = $.cookie("token");
	requestParameters.accountId = $.cookie("sysUserId");
	console.log("serivce request:" + serviceName);
	console.log(requestParameters);
	var serviceUrl = getServiceUrl();
	$.ajax({
		url : serviceUrl,
		type : "POST",
		dataType : "json",
		async : (data == null ? true : data.async),
		data : requestParameters,
		beforeSend : (data == null || data.notLoad) ? null : load(),
		complete : function() {
			disLoad();
		},
		success : function(result) {
			console.log("serivce response:" + serviceName);
			console.log(result);
			// alert(JSON.stringify(result));
			if (result.success) {
				successCallback(result);
			} else {
				if (errorCallback != null && errorCallback != undefined) {
					errorCallback(result);
				} else {
					// showMessage("调用服务【" + serviceName + "】出错:" +
					// result.error_message);
					// alert(result.error_message);
					showWarning(result.errorMessage);
					if (result.messageType == "loginTimeout") {
						window.location.href = "../login.htm";
					}
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(errorThrown);
		}
	});
}
/*
 * 取得服务地址
 */
function getServiceUrl() {
	var serviceUrl = config.serviceUrl + "?r=" + Math.random();
	return serviceUrl;
}

/*
 * 刷新页面
 */
function refreshPage() {
	var url = window.location.href;
	if (url.indexOf("?r=") > 0) {
		url = url.substr(0, url.lastIndexOf("?r=")) + "?r=" + Math.random();
	} else if (url.indexOf("&r=") > 0) {
		url = url.substr(0, url.lastIndexOf("&r=")) + "&r=" + Math.random();
	}
	window.location.href = url;
}

/*
 * 绑定code组合框
 */
function bindCodeNameComboBox(controlId, tableName, condition) {
	bindComboBox(controlId, tableName, "code", "name", "display_order", "　",
		condition);
}
/*
 * 绑定id组合框
 */
function bindIdNameComboBox(controlId, tableName, condition) {
	bindComboBox(controlId, tableName, "id", "name", "display_order", "　",
		condition);
}
/*
 * 绑定组合框
 */
function bindComboBox(controlId, tableName, valueFieldName, textFieldName,
	orderBy, emptyText, conditon) {
	$("#" + controlId).combobox("clear");
	var data = new Object();
	data.tableName = tableName;
	data.orderBy = orderBy;
	data.condition = conditon;
	//console.log(data);
	call("GetTable", data, function(result) {
		//console.log(result.data);
		var comboboxData = result.data;
		var firstItem = new Object();
		firstItem[valueFieldName] = "";
		firstItem[textFieldName] = emptyText;
		comboboxData.insert(0, firstItem);
		$("#" + controlId).combobox({
			valueField : valueFieldName,
			textField : textFieldName,
			data : comboboxData
		});
	});
}

/*
 * 绑定id组合框
 */
function bindIdNameComboBoxWithValue(controlId, tableName, condition, value,
	callback) {
	bindComboBoxWithValue(controlId, tableName, "id", "name", "display_order",
		"　", condition, value, callback);
}
/*
 * 绑定code组合框
 */
function bindCodeNameComboBoxWithValue(controlId, tableName, condition, value,
	callback) {
	bindComboBoxWithValue(controlId, tableName, "code", "name",
		"display_order", "　", condition, value, callback);
}
/*
 * 绑定组合框
 */
function bindComboBoxWithValue(controlId, tableName, valueFieldName,
	textFieldName, orderBy, emptyText, condition, value, callback) {
	var currentValue = value;
	$("#" + controlId).combobox("clear");
	var data = new Object();
	data.tableName = tableName;
	data.orderBy = orderBy;
	data.condition = condition;
	call("GetTable", data, function(result) {
		// console.log(result.data);
		var comboboxData = result.data;
		var firstItem = new Object();
		firstItem.id = "";
		firstItem.name = emptyText;
		comboboxData.insert(0, firstItem);
		$("#" + controlId).combobox({
			valueField : valueFieldName,
			textField : textFieldName,
			data : comboboxData
		});
		$("#" + controlId).combobox("setValue", currentValue);
		if (callback != null) {
			callback(value);
		}
	});
}
/*
 * 绑定id树型表
 */
function bindIdNameComboTree(controlId, tableName, multiple) {
	bindComboTree(controlId, tableName, 0, "id", "name", "parent_id", multiple);
}
/*
 * 绑定code树型表
 */
function bindCodeNameComboTree(controlId, tableName, multiple) {
	bindComboTree(controlId, tableName, "", "code", "name", "parent_code",
		multiple);
}
/*
 * 绑定CmsId树型表
 */
function bindCmsIdNameComboTree(controlId, tableName, multiple) {
	var condition = "cms_site_id=" + getCookie("cmsSiteId");
	bindComboTree(controlId, tableName, 0, "id", "name", "parent_id", multiple,
		condition);
}
/*
 * 绑定CmsCode树型表
 */
function bindCmsCodeNameComboTree(controlId, tableName, multiple) {
	var condition = "cms_site_id=" + getCookie("cmsSiteId");
	bindComboTree(controlId, tableName, "", "code", "name", "parent_code",
		multiple, condition);
}
/*
 * 绑定树型表
 */
function bindComboTree(controlId, tableName, parentValue, valueFieldName,
	textFieldName, parentFieldName, multiple, condition) {
	var data = new Object();
	data.tableName = tableName;
	data.orderBy = "display_order";
	data.condition = condition;
	call("GetTable", data, function(result) {
		// alert(JSON.stringify(result));
		// console.log(result);
		var comboTreeData = getComboTreeData(result.data, parentValue,
			valueFieldName, textFieldName, parentFieldName);

		var firstItem = new Object();
		firstItem.id = parentValue;
		firstItem.text = "空";
		comboTreeData.insert(0, firstItem);
		// alert(JSON.stringify(comboTreeData));
		$("#" + controlId).combotree({
			multiple : multiple,
			data : comboTreeData
		});
		// 选中默认值
		for (var i = 0; i < result.data.length; i++) {
			var dataItem = result.data[i];
			if (dataItem.is_default) {
				$("#" + controlId).combotree("setValue",
					dataItem[valueFieldName]);
			}
		}
	});
}

/*
 * 绑定树型表
 */
function bindCT(options) {
	var data = new Object();
	data.tableName = options.tableName;
	data.orderBy = "display_order";
	if (options.condition) {
		data.condition = options.condition;
	}
	call("GetTable", data, function(result) {
		// alert(JSON.stringify(result));
		// console.log(result);
		var comboTreeData = getComboTreeData(result.data, options.parentValue,
			options.valueFieldName, options.textFieldName, options.parentFieldName);

		var firstItem = new Object();
		firstItem.id = options.parentValue;
		firstItem.text = "空";
		comboTreeData.insert(0, firstItem);
		// alert(JSON.stringify(comboTreeData));
		$("#" + options.controlId).combotree({
			multiple : options.multiple,
			data : comboTreeData
		});
		if (options.value) {
			$("#" + options.controlId).combotree("setValue", options.value);
		} else {
			// 选中默认值
			for (var i = 0; i < result.data.length; i++) {
				var dataItem = result.data[i];
				if (dataItem.is_default) {
					$("#" + options.controlId).combotree("setValue",
						dataItem[options.valueFieldName]);
				}
			}
		}
	});
}
/*
 * 绑定id树型表
 */
function bindIdNameComboTreeWithValue(controlId, tableName, multiple, value) {
	bindComboTreeWithValue(controlId, tableName, 0, "id", "name", "parent_id",
		multiple, "", value);
}
/*
 * 绑定code树型表
 */
function bindCodeNameComboTreeWithValue(controlId, tableName, multiple, value) {
	bindComboTreeWithValue(controlId, tableName, "", "code", "name",
		"parent_code", multiple, "", value);
}
/*
 * 绑定CmsId树型表
 */
function bindCmsIdNameComboTreeWithValue(controlId, tableName, multiple, value) {
	var condition = "cms_site_id=" + getCookie("cmsSiteId");
	bindComboTreeWithValue(controlId, tableName, 0, "id", "name", "parent_id",
		multiple, condition, value);
}
/*
 * 绑定CmsCode树型表
 */
function bindCmsCodeNameComboTreeWithValue(controlId, tableName, multiple,
	value) {
	var condition = "cms_site_id=" + getCookie("cmsSiteId");
	bindComboTreeWithValue(controlId, tableName, "", "code", "name",
		"parent_code", multiple, condition, value);
}
/*
 * 绑定树并赋值
 */
function bindComboTreeWithValue(controlId, tableName, parentValue,
	valueFieldName, textFieldName, parentFieldName, multiple, condition,
	value) {
	bindCT({
		controlId : controlId,
		tableName : tableName,
		parentValue : parentValue,
		valueFieldName : valueFieldName,
		textFieldName : textFieldName,
		parentFieldName : parentFieldName,
		multiple : multiple,
		condition : condition,
		value : value
	})
}
/*
 * 取得树数据
 */
function getComboTreeData(lstData, parentValue, valueFieldName, textFieldName,
	parentFieldName) {
	var treeData = new Array();
	var lstSubData = getSubDataList(lstData, parentValue, parentFieldName);
	for (var i = 0; i < lstSubData.length; i++) {
		var dataItem = lstSubData[i];
		var treeNode = new Object();
		treeNode.id = dataItem[valueFieldName];
		treeNode.text = dataItem[textFieldName];
		treeData.push(treeNode);
		var primaryKey = dataItem[valueFieldName];
		// console.log(primaryKey + ":" + dataItem[valueFieldName]);
		var childrenTreeData = getComboTreeData(lstData, primaryKey,
			valueFieldName, textFieldName, parentFieldName);
		if (childrenTreeData.length > 0) {
			treeNode.children = new Array();
			for (var j = 0; j < childrenTreeData.length; j++) {
				treeNode.children.push(childrenTreeData[j]);
			}
		}
	}
	return treeData;
}

/*
 * 获取idname树
 */
function bindIdNameTree(controlId, tableName, rootText, checkbox, clickCallback) {
	bindTree(controlId, tableName, "id", "name", "parent_id", rootText, 0,
		"display_order", checkbox, clickCallback);
}
/*
 * 获取codename树
 */
function bindCodeNameTree(controlId, tableName, rootText, checkbox,
	clickCallback) {
	bindTree(controlId, tableName, "code", "name", "parent_code", rootText, "",
		"display_order", checkbox, clickCallback);
}
/*
 * 绑定树
 */
function bindTree(controlId, tableName, valueFieldName, textFieldName,
	parentFieldName, rootText, rootValue, orderBy, checkbox, clickCallback) {
	var data = new Object();
	data.orderBy = orderBy;
	data.tableName = tableName;
	call("GetTable", data, function(result) {
		// alert(JSON.stringify(result));
		console.log(result);
		outputTree(controlId, result.data, valueFieldName, textFieldName,
			parentFieldName, rootText, rootValue, checkbox, clickCallback);
	});
}
/*
 * 输出树
 */
function outputTree(controlId, lstData, valueFieldName, textFieldName,
	parentFieldName, rootText, rootValue, checkbox, clickCallback) {
	var childrenTreeData = getTreeData(lstData, rootValue, valueFieldName,
		textFieldName, parentFieldName);

	console.log("childrenTreeData:", JSON.stringify(childrenTreeData));
	var treeData = new Array();
	var rootNode = new Object();
	rootNode.id = rootValue;
	rootNode.text = rootText;
	rootNode.children = childrenTreeData;
	treeData.push(rootNode);
	var treeOptions = {
		checkbox : checkbox,
		data : treeData,
		// cascadeCheck:false,
		onClick : function(node) {
			if (clickCallback != null) {
				clickCallback(node);
			}
		}
	};
	// alert(JSON.stringify(treeOptions));
	$("#" + controlId).tree(treeOptions);
}
/*
 * 取得树数据
 */
function getTreeData(lstData, parentValue, valueFieldName, textFieldName,
	parentFieldName) {
	var treeData = new Array();
	var lstSubDataList = getSubDataList(lstData, parentValue, parentFieldName);
	for (var i = 0; i < lstSubDataList.length; i++) {
		var dataItem = lstSubDataList[i];
		var treeNode = new Object();
		treeNode.id = dataItem[valueFieldName];
		treeNode.text = dataItem[textFieldName];
		treeNode.attributes = dataItem;
		treeData.push(treeNode);
		var childrenTreeData = getTreeData(lstData, dataItem[valueFieldName],
			valueFieldName, textFieldName, parentFieldName);
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
 * 取得子数据列表
 */
function getSubDataList(lstData, parentValue, parentFieldName) {
	if (parentValue == null) {
		parentValue = "";
	}
	var lstSubData = new Array();
	for (var i = 0; i < lstData.length; i++) {
		var dataItem = lstData[i];
		var dataItemParentValue = dataItem[parentFieldName];
		if (dataItemParentValue == "null" || dataItemParentValue == null
			|| dataItemParentValue == "") {
			if (parentFieldName == "parent_code") {
				dataItemParentValue = "";
			} else if (parentFieldName == "parent_id") {
				dataItemParentValue = "0";
			}
		}
		if (dataItemParentValue == parentValue) {
			lstSubData.push(dataItem);
		}
	}
	return lstSubData;
}

/*
 * 取得选中的树节点
 */
function getTreeCheckedValues(controlId, propertyName) {
	var nodes = $('#' + controlId).tree('getChecked',
		[ 'checked', 'indeterminate' ]);
	if (nodes == null) {
		return null;
	}
	// alert(JSON.stringify(nodes));
	var checkedValues = [];
	for (i = 0; i < nodes.length; i++) {
		if (nodes[i].attributes != null) {
			checkedValues.push(nodes[i].attributes[propertyName]);
		}
	}
	return checkedValues;
}

/*
 * 选中树节点
 */
function checkTree(controlId, lstData, dataPropertyName, treePropertyName) {
	uncheckTree(controlId);
	var rootNodes = $("#" + controlId).tree("getRoots");
	checkTreeNodes(controlId, lstData, rootNodes, dataPropertyName,
		treePropertyName);
}
/*
 * 选中树节点
 */
function checkTreeNodes(controlId, lstData, nodes, dataPropertyName,
	treePropertyName) {
	// alert(JSON.stringify(nodes));
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		if (node.attributes != null) {
			var checked = existsTreeValue(lstData,
				node.attributes[treePropertyName], dataPropertyName)
			var treeNode = $("#" + controlId).tree('find', node.id);
			if (checked) {
				$("#" + controlId).tree("check", treeNode.target);
			} else {
				$("#" + controlId).tree("uncheck", treeNode.target);
			}
		}
		var childrenNodes = node.children;
		if (childrenNodes != null && childrenNodes.length > 0) {
			checkTreeNodes(controlId, lstData, childrenNodes, dataPropertyName,
				treePropertyName)
		}
	}
}
/*
 * 指定的值是否在数据列表中存在
 */
function existsTreeValue(lstData, nodeValue, propertyName) {
	var found = false;
	for (var i = 0; i < lstData.length; i++) {
		var dataItem = lstData[i];
		if (dataItem[propertyName] == nodeValue) {
			found = true;
			break;
		}
	}
	return found;
}
/*
 * 清空选中状态
 */
function uncheckTree(controlId) {
	var checkedNodes = $("#" + controlId).tree("getChecked");
	for (var i = 0; i < checkedNodes.length; i++) {
		var node = checkedNodes[i];
		var treeNode = $("#" + controlId).tree("find", node.id);
		$("#" + controlId).tree("uncheck", node.target);
	}
}
// 弹出加载层
function load() {
	$("<div class=\"datagrid-mask\"></div>").css({
		display : "block",
		width : "100%",
		height : $(window).height()
	}).appendTo("body");
	$("<div class=\"datagrid-mask-msg\"></div>").html("处理中,请稍候。。。").appendTo(
		"body").css({
		display : "block",
		left : ($(document.body).outerWidth(true) - 190) / 2,
		top : ($(window).height() - 45) / 2
	});
}
// 取消加载层
function disLoad() {
	$(".datagrid-mask").remove();
	$(".datagrid-mask-msg").remove();
}

/*
 * 返回
 */
function goback() {
	var returnUrl = $.query.get("returnUrl");
	if (returnUrl != "null" && returnUrl != null && returnUrl != "") {
		window.location.href = returnUrl;
	} else {
		window.history.go(-1);
	// window.history.back();
	}
	return false;
}
/*
 * 打开对话框
 */
function openDialog(dialogId, title, onOpenCallback) {
	$('#' + dialogId).dialog({
		title : title,
		onOpen : onOpenCallback,
		closed : false
	});
}

/*
 * 关闭对话框
 */
function closeDialog(dialogId) {
	$('#' + dialogId).dialog('close');
}
/*
 * 输出下拉框
 */
function outputSelect(controlId, data, valueFieldName, textFieldName) {
	$('#' + controlId).empty();
	if (data == null) {
		return;
	}
	for (var i = 0; i < data.length; i++) {
		var dataItem = data[i];
		$('#' + controlId).append(
			"<option value=" + dataItem[valueFieldName] + ">"
			+ dataItem[textFieldName] + "</option>");
	}
}
/*
 * 设置Cookie
 */
function setCookie(name, value) {
	$.cookie(name, value, {
		expires : 9999,
		path : '/'
	});
}
/*
 * 取得Cookie
 */
function getCookie(name) {
	return $.cookie(name);
}
/*
 * 删除Cookie
 */
function removeCookie(name) {
	$.removeCookie(name, {
		path : '/'
	});
}
/**
 * 取得后台绝对地址
 */
function getAdminUrl(relativeUrl) {
	var lastChar = config.adminRootUrl[config.adminRootUrl.length - 1];
	var adminUrl = config.adminRootUrl;
	if (lastChar != "/" && lastChar != "\\") {
		adminUrl += "/";
	}
	adminUrl += relativeUrl;
	return adminUrl;
}

/**
 * 取得当前组织机构ID
 */
function getSysOrganizationId() {
	var sysOrganizationId = getCookie("sysOrganizationId");
	if (sysOrganizationId == null || sysOrganizationId == "" || sysOrganizationId == "0") {
		return "";
	}
	return sysOrganizationId;
}

/**
 * 取得当前用户ID
 */
function getSysUserId() {
	var sysUserId = getCookie("sysUserId");
	if (sysUserId == null || sysUserId == "" || sysUserId == "0") {
		return "";
	}
	return sysUserId;
}
/**
 * 取得二维码地址
 */
function getQRCodeUrl(content) {
	var QRCodeUrl = getAdminUrl("api/QRCode?content=" + escape(content));
	return QRCodeUrl;
}
/**
 * 格式化时间
 */
function formatDateTime(value, row, index) {
	if (null != value) {
		return (new Date(value.time)).format("yyyy-MM-dd hh:mm:ss");
	}
	return value
}

/**
 * 格式化日期
 */
function formatDate(value, row, index) {
	if (null != value) {
		return (new Date(value.time)).format("yyyy-MM-dd");
	}
	return value
}