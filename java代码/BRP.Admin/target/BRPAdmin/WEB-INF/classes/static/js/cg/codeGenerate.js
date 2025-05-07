/*
 * 绑定数据库
 */
function bindDatabaseListData() {
	var data = new Object();
	call("GetDatabasesList", data, function(result) {
		$("#cmbDataBaseName").combobox("clear", {});
		$("#cmbDataBaseName").combobox({
			valueField : "databaseName",
			textField : "databaseName",
			data : result.data
		});
		$("#cmbDataBase").combobox("clear", {});
		$("#cmbDataBase").combobox({
			valueField : "databaseName",
			textField : "databaseName",
			data : result.data
		});
	});
}
function bindDatabase() {
	var data = new Object();
	call("GetDatabasesList", data, function(result) {
		$("#cmbDataBase").combobox("clear", {});
		$("#cmbDataBase").combobox({
			valueField : "databaseName",
			textField : "databaseName",
			data : result.data
		});
	});
}
//绑定table
function bindTableList(controlId, tableName, title, haveOperation, condition) {
	var cmsChannelTemplatecondition = condition;
	var cmsChannelTemplateTreeColumns
	if (haveOperation) {
		cmsChannelTemplateTreeColumns = [ [ {
			field : 'ck',
			checkbox : true,
			title : 'id',
			width : 30
		}, {
			title : '操作',
			field : 'operation',
			width : 80,
			fixed : true,
			align : 'left'
		}, {
			title : title,
			field : 'TABLE_NAME',
			align : 'left',
			fixed : true,
			width : 340
		} ] ];
	} else {
		cmsChannelTemplateTreeColumns = [ [ {
			field : 'ck',
			checkbox : true,
			title : 'id',
			width : 30
		}, {
			title : title,
			field : 'TABLE_NAME',
			align : 'left',
			fixed : true,
			width : 340
		} ] ];
	}

	bindCmsTemplateNameTableGrid(controlId, tableName, cmsChannelTemplatecondition, cmsChannelTemplateTreeColumns);
}
function bindFieldTableList(controlId, moduleName, tableName, searchTableName, condition, type) {
	var columConfig = getColumConfig(moduleName, searchTableName, type);
	var tableColumns = columConfig.tableColumns;
	//console.log(columConfig);
	var cmsChannelTemplateTreeColumns = [ [ {
		title : "字段名",
		field : 'COLUMN_NAME',
		align : 'left',
		fixed : true,
		width : 300
	}, {
		title : "中文名",
		field : 'COLUMN_COMMENT',
		editor : 'text',
		align : 'center',
		fixed : true,
		width : 150,
		formatter : function(value, row, index) {
			for (var i = 0; i < tableColumns.length; i++) {
				if (tableColumns[i].columnName == row.COLUMN_NAME && tableColumns[i].caption != "") {
					value = tableColumns[i].caption;
				}
			}
			var html = "<input style='width:140px;' id='fieldCOLUMN_COMMENT_" + row.COLUMN_NAME + "' value='" + value + "'>"
			return html;
		}
	}, {
		title : "查询字段",
		field : 'ISMATCH',
		align : 'center',
		fixed : true,
		width : 80,
		formatter : function(value, row, index) {
			var html = "";
			var isExist = false;
			for (var i = 0; i < tableColumns.length; i++) {
				if (tableColumns[i].columnName == row.COLUMN_NAME) {
					if (tableColumns[i].isSearchItem == "true") {
						isExist = true;
						html += "<input type='checkbox' id='fieldISMATCH_" + row.COLUMN_NAME + "' checked='checked'>";
						break;
					}
				}
			}
			if (!isExist) {
				html += "<input type='checkbox' id='fieldISMATCH_" + row.COLUMN_NAME + "'>";
			}
			return html;
		}
	}, {
		title : "匹配原则",
		field : 'MATCH',
		align : 'center',
		fixed : true,
		width : 80,
		formatter : function(value, row, index) {
			var isExist = false;
			var html = "<select id='fieldMATCH_" + row.COLUMN_NAME + "'>";
			html += "<option value='' selected></option>";
			for (var i = 0; i < tableColumns.length; i++) {
				if (tableColumns[i].columnName == row.COLUMN_NAME) {
					if (tableColumns[i].searchOperation == "like") {
						isExist = true;
						html += "<option value='like' selected>like</option>";
						html += "<option value='equal'>equal</option>";
						html += "<option value='timespan'>timespan</option>";
						break;
					} else if (tableColumns[i].searchOperation == "equal") {
						isExist = true;
						html += "<option value='like'>like</option>";
						html += "<option value='equal' selected>equal</option>";
						html += "<option value='timespan'>timespan</option>";
						break;
					} else if (tableColumns[i].searchOperation == "timespan") {
						isExist = true;
						html += "<option value='like'>like</option>";
						html += "<option value='equal'>equal</option>";
						html += "<option value='timespan' selected>timespan</option>";
						break;
					}
				}
			}
			if (!isExist) {
				html += "<option value='like'>like</option>";
				html += "<option value='equal'>equal</option>";
				html += "<option value='timespan'>timespan</option>";
			}
			html += "</select>";
			return html;
		}
	} ] ];
	bindCmsTemplateNameTableGrid(controlId, tableName, condition, cmsChannelTemplateTreeColumns)
}
;
function bindCmsTemplateNameTableGrid(controlId, tableName, condition, tableColumns) {
	var data = new Object();
	data.tableName = tableName;
	data.condition = condition;
	call("GetTable", data, function(result) {
		outputCmsTemplateListGrid(result.data, controlId, tableColumns);
	});
}
function outputCmsTemplateListGrid(dataList, gridControlId, tableColumns, getGridRowCallback, getTableColumnsCallback) {
	$('#' + gridControlId).datagrid({
		columns : tableColumns,
		checkbox : true,
		pagination : true,
		fitColumns : true
	})
	// 清空datagrid数据
	$('#' + gridControlId).datagrid('loadData', {
		total : 0,
		rows : []
	});

	for (var i = 0; i < dataList.length; i++) {
		var dataItem = dataList[i];
		outputListItem(dataItem, gridControlId, getGridRowCallback);
	}
	$(".datagrid-pager").remove();
}
/*
 * 取得操作按钮列表，默认为查看，修改，删除三个按钮
 */
function getOperationButtonHtmlArrayBase(dataItem) {
	var arrOpeartionButtonHtml = new Array();
	var lnkDetailHtml = getGridDetailButtonHtml(dataItem);
	var lnkRemoveHtml = getGridRemoveButtonHtml(dataItem);
	arrOpeartionButtonHtml.push(lnkDetailHtml);
	arrOpeartionButtonHtml.push(lnkRemoveHtml);
	return arrOpeartionButtonHtml;
}
function getGridDetailButtonHtml(dataItem) {
	var tableName;
	if (dataItem.type == "View") {
		tableName = dataItem.viewName;
	} else {
		tableName = dataItem.tableName;
	}
	return "<a class='easyui-linkbutton grid_button' href='#' onclick=\"return openDetail('" + dataItem.moduleName + "','"
		+ tableName + "','" + dataItem.type + "')\">详情</a>";

}
function getGridRemoveButtonHtml(dataItem) {
	var tableName;
	if (dataItem.type == "View") {
		tableName = dataItem.viewName;
	} else {
		tableName = dataItem.tableName;
	}
	return "<a class='easyui-linkbutton grid_button' href='#' onclick=\"return removeTable('" + dataItem.moduleName + "','"
		+ tableName + "','" + dataItem.type + "')\">移除</a>";

}
/*
 * 打开表详情窗口
 */
function openDetail(moduleName, tableName, type) {
	openDialog("tableDetail", moduleName + ":" + type + ":" + tableName);
	setTableDetail(moduleName, tableName, type);
}
/*
 * 关闭表详情窗口
 */
function closeDetail(){
	closeDialog("tableDetail");
}
function setTableDetail(moduleName, searchTableName, type) {
	var database = getDatabase();
	var condition = "table_schema ='" + database + "'  and table_name = '" + searchTableName + "'";
	bindFieldTableList("tableDetailGrid", moduleName, "information_schema.columns", searchTableName, condition, type);
}
function getColumConfig(moduleName, tableName, type) {
	var resultData;
	var data = new Object();
	data.moduleName = moduleName;
	data.tableName = tableName;
	data.async = false;
	data.type = type;
	data.projectId = getSelectedProject();
	call("GetColumConfig", data, function(result) {
		resultData = result.data;
	})
	return resultData;
}
function openProjectDialog(action) {
	$("#hiddenProjectAction").val(action);
	if (action == "Add") {
		openDialog("projectDetail", "新增项目");
	}
	if (action == "Update") {
		var data = new Object();
		var projectId = getSelectedProject();
		data.projectId = projectId;
		if (projectId == "") {
			alert("请先选择要修改的项目");
		} else {
			call("GetCgProject", data, function(result) {
				//alert(JSON.stringify(result));
				$("#hiddenProjectId").val(projectId);
				$("#txtProjectName").textbox("setValue", result.data.projectName);
				$("#txtOutputDirectory").textbox("setValue", result.data.outputDirectory);
				$("#cmbDataBase").combobox("setValue", result.data.dataSource);
				openDialog("projectDetail", "修改项目");
			})
		}
	}
}
function saveProject() {
	var data = new Object();
	var action = $("#hiddenProjectAction").val();
	data.projectName = $("#txtProjectName").textbox('getValue');
	data.outputDirectory = $("#txtOutputDirectory").textbox('getValue');
	data.dataSource = $("#cmbDataBase").combobox('getValue');
	if (action == "Add") {
		call("AddCgProject", data, function() {
			closeDialog("projectDetail");
			bindDatabase();
			setProjectCombobox();
		})
	}
	if (action == "Update") {
		data.projectId = $("#hiddenProjectId").val();
		call("UpdateCgProject", data, function() {
			refreshPage();
		})
	}
}
function saveModule() {
	var originaltModuleName = $("#hiddenOriginaltModuleName").val();
	var data = new Object();
	data.originaltModuleName = originaltModuleName;
	data.moduleName = $("#txtModuleName").textbox('getValue');
	data.moduleDocument = $("#txtModuleDocument").textbox('getValue');
	data.projectId = getSelectedProject();
	if (originaltModuleName == "") {
		if (isModuleRepeat(data.moduleName)) {
			alert("该模块已存在");
		} else {
			call("AddCgModule", data, function(result) {
				closeDialog("moduleDetail");
				$("#moduleList").empty();
				setSelectedModule('');
				bindProjectData(getSelectedProject());
			})
		}
	} else {
		if ((originaltModuleName != data.moduleName) && isModuleRepeat(data.moduleName)) {
			alert("该模块已存在");
		} else {
			call("UpdateCgModule", data, function(result) {
				closeDialog("moduleDetail");
				$("#moduleList").empty();
				setSelectedModule('');
				bindProjectData(getSelectedProject());
			})
		}
	}

}
function isModuleRepeat(moduleName) {
	var result = false;
	$(".module_item ").each(
		function() {
			var id = $(this).attr("id");
			if (id.substring(11, id.length) == moduleName) {
				result = true;
			}
		});
	return result;
}
function searchTables() {
	var searchKey = $("#searchKey").textbox('getValue');
	var title = $("#sysTableDialog").panel('options').title;
	var moduleName = title.split(":")[0];
	var type = title.split(":")[2];
	showDatabaseTables(moduleName, type, searchKey);
}
function saveTable() {
	var title = $("#sysTableDialog").panel('options').title;
	var moduleName = title.split(":")[0];
	var database = title.split(":")[1];
	var type = title.split(":")[2];
	var data = new Object();
	data.tableList = [];
	data.moduleName = moduleName;
	data.database = database;
	data.type = type;
	data.projectId = getSelectedProject();
	var tableList = $('#sysTableGrid').datagrid('getSelections');
	for (var i = 0; i < tableList.length; i++) {
		data.tableList.push(tableList[i].TABLE_NAME);
	}
	if (data.tableList.length == 0) {
		alert("所选结果不能为空");
	} else if (isAddRepeatTable(data)) {
		alert("已存在：" + isAddRepeatTable(data));
	} else {
		call("AddCgTable", data, function(result) {
			closeDialog("sysTableDialog");
			$("#moduleList").empty();
			bindProjectData(getSelectedProject());
		})
	}
}
//选择表不能重复
function isAddRepeatTable(data) {
	var repeatTable = false;
	var addTableList = data.tableList;
	var oldTableList = [];
	var tableList = $("#" + data.moduleName + "_" + data.type.toLowerCase() + "Grid").datagrid('getRows');
	for (var i = 0; i < tableList.length; i++) {
		oldTableList.push(eval("tableList[i]." + data.type.toLowerCase() + "Name"));
	}
	for (var i = 0; i < oldTableList.length; i++) {
		for (var j = 0; j < addTableList.length; j++) {
			if (oldTableList[i] == addTableList[j]) {
				repeatTable = addTableList[j];
			}
		}
	}
	return repeatTable;
}
function saveFeildDetail() {
	var title = $("#tableDetail").panel('options').title;
	var moduleName = title.split(":")[0];
	var type = title.split(":")[1];
	var tableName = title.split(":")[2];
	var data = new Object();
	data.moduleName = moduleName;
	data.tableName = tableName;
	data.type = type;
	data.projectId = getSelectedProject();
	data.feilList = new Array();
	var feildItemList = $("#tableDetailGrid").datagrid('getRows');
	for (var i = 0; i < feildItemList.length; i++) {
		var columnName = feildItemList[i].COLUMN_NAME;
		var caption = $("#fieldCOLUMN_COMMENT_" + columnName).val() == '' ? null : $("#fieldCOLUMN_COMMENT_" + columnName).val();
		var isSearchItem = $("#fieldISMATCH_" + columnName).is(":checked") ? true : null;
		var searchOperation = $("#fieldMATCH_" + columnName).val() == '' ? null : $("#fieldMATCH_" + columnName).val();
		var feild = {
			columnName : columnName,
			caption : caption,
			isSearchItem : isSearchItem,
			searchOperation : searchOperation
		}
		data.feilList.push(feild);
	}
	call("SaveCgField", data, function(result) {
		setTableDetail(moduleName, tableName, type);
		closeDetail();
	})

}
//清除空字符串
function clearEmpty(data) {
	for (var key in data) {
		if (data[key] != null)
			if (key != 'enslMap')
				if (data[key].trim() == "" || data[key].trim() == 'undefined') {
					data[key] = null;
		}
	}
	return data;
}

//取消字段选中
function unSelectAll() {
	$("#tableDetailGrid").datagrid('unselectAll');
}
/*
 * 关闭明细对话框
 */
function closeDialog(dialogName) {
	$('#' + dialogName).dialog('close');
}

//生成代码
function codeGenerate() {
	openDialog("codeGenerateDialog", "选择生成");
}
//开始生成
function startCodeGenerate() {
	var data = new Object();
	data.projectId = getSelectedProject();
	data.outputDirectory = $("#output_directory").val();
	data.dataSource = $("#cmbDataBaseName").combobox("getValue");
	data.projectName = $('#cmbProjectName').combobox('getText');
	data.moduleList = [];
	var selectedModuleList = $('#codeGenerateSelectGrid').datagrid('getSelections');
	for (var i = 0; i < selectedModuleList.length; i++) {
		var moduleName = selectedModuleList[i].moduleName;
		var moduleDocument = $("#" + moduleName + "_directory").val();
		var tableList = getFieldList($("#" + moduleName + "_tableGrid").datagrid('getSelections'), "tableName");
		var viewList = getFieldList($("#" + moduleName + "_viewGrid").datagrid('getSelections'), "viewName");
		//var procedureList = $("#"+moduleName+"_procedureGrid").datagrid('getSelections');
		var module = {
			moduleName : moduleName,
			moduleDocument : moduleDocument,
			tableList : tableList,
			viewList : viewList
		}
		data.moduleList.push(module);
	}
	call("ProjectCodeGenerate", data, function(result) {
		$("#codeGenerateDialog").dialog('close');
	})
}
//获取TABLE_NAME
function getFieldList(tableList, feildName) {
	var feildeLsit = [];
	for (var i = 0; i < tableList.length; i++) {
		feildeLsit.push(eval("tableList[i]." + feildName));
	}
	return feildeLsit;
}