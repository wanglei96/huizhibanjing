function openProject() {
	var projectId = getSelectedProject();
	$("#moduleList").empty();
	setSelectedModule('');
	bindProjectData(getSelectedProject());
}

function getSelectedProject() {
	var projectId = $('#cmbProjectName').combobox('getValue');
	return projectId;
}
function setSelectedProject(projectId) {
	var projectId = $('#cmbProjectName').combobox('setValue', projectId);
	return projectId;
}

/*
	 * 绑定project
	 */
function bindProjectData(projectId) {
	var data = new Object();
	data.projectId = projectId;
	call("GetCgProject", data, function(result) {
		//alert(JSON.stringify(result.data));
		var modules = result.data.modules;
		var tableColumns = [ [ {
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
			title : "表名",
			field : 'tableName',
			align : 'left',
			fixed : true,
			width : 340
		} ] ];
		var viewColums = [ [ {
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
			title : "表名",
			field : 'viewName',
			align : 'left',
			fixed : true,
			width : 340
		} ] ];
		$(".div_module_tables").remove();
		//绑定输出目录
		bindOutputDirectory(result.data);
		//绑定数据库
		setDatabase(result.data.dataSource);
		for (var i = 0; i < modules.length; i++) {
			addHtml(modules[i].moduleName);
			outputCmsTemplateListGrid(modules[i].tables, modules[i].moduleName + "_tableGrid", tableColumns);
			outputCmsTemplateListGrid(modules[i].views, modules[i].moduleName + "_viewGrid", viewColums);
			setModuleDirectory(modules[i].moduleName, modules[i].moduleDocument);
			$(".datagrid-pager").remove();
			var moduleName = getSelectedModule();
			if (moduleName != modules[i].moduleName) {
				$("#" + modules[i].moduleName + "_tables").hide();
			}
		}
		biandCodeGenerateSelectGrid(modules);
	});
}
function biandCodeGenerateSelectGrid(modules) {
	var tableColumns = [ [ {
		field : 'ck',
		checkbox : true,
		title : 'id',
		width : 30
	}, {
		title : "模块名",
		field : 'moduleName',
		align : 'left',
		fixed : true,
		width : 340
	} ] ];
	outputCmsTemplateListGrid(modules, "codeGenerateSelectGrid", tableColumns);
}
function bindOutputDirectory(dataItem) {
	$("#output_directory").val(dataItem.outputDirectory);
}
function addHtml(addModuleName) {
	//获取当前页面选中模块
	var style = '';
	var moduleClass = 'class="module_item"';
	var moduleName = getSelectedModule();
	if (moduleName == "" || moduleName == addModuleName) {
		setSelectedModule(addModuleName);
		style = '';
		moduleClass = 'class="module_item checked_module_item"';
	}
	var moduleHtml = '<div ' + moduleClass + ' id="moduleItem_' + addModuleName + '">' + addModuleName +
		'<input type="image" src="../../themes/icons/delete_data.png" class="img_module_button" onclick="removeModule(\'' + addModuleName + '\')" id="updateModule_' + addModuleName + '"/> ' +
		'<input type="image" src="../../themes/icons/update_data.png" class="img_module_button" onclick="updateModule(\'' + addModuleName + '\')" id="removeModule_' + addModuleName + '"/> ' +
		'</div>';
	var html = '<div class="div_module_tables" id="' + addModuleName + '_tables" ' + style + '>' +
		'					<div class="divTable table_div" id="' + addModuleName + '_divTable">' +
		'						<div class="table_div_title">表 ' +
		'							<a href="#"class="add_table_button"  onclick="removeTables(\'' + addModuleName + '\',\'Table\')">移除</a>' +
		'							<a href="#"class="add_table_button"  onclick="showDatabaseTables(\'' + addModuleName + '\',\'Table\')">新增</a></div>' +
		'						<table id="' + addModuleName + '_tableGrid" class="table table-bordered table-hover" style="height:auto;">' +
		'						</table>' +
		'					</div>' +
		'					<div class="divView table_div" id="' + addModuleName + '_divView">' +
		'						<div class="table_div_title">视图' +
		'							<a href="#"class="add_table_button" onclick="removeTables(\'' + addModuleName + '\',\'View\')">移除</a>' +
		'							<a href="#"class="add_table_button" onclick="showDatabaseTables(\'' + addModuleName + '\',\'View\')">新增</a></div>' +
		'						<table id="' + addModuleName + '_viewGrid" class="table table-bordered table-hover" style="height:auto;">' +
		'						</table>' +
		'					</div>' +
		'					<div class="divProcedure table_div" id="' + addModuleName + '_divProcedure">' +
		'						<div class="table_div_title">存储过程' +
		'							<a href="#"class="add_table_button"  onclick="removeTables(\'' + addModuleName + '\',\'Procedure\')">移除</a>' +
		'							<a href="#"class="add_table_button"  onclick="showDatabaseTables(\'' + addModuleName + '\',\'Procedure\')">新增</a></div>' +
		'						<table id="' + addModuleName + '_procedureGrid" class="table table-bordered table-hover" style="height:auto;">' +
		'						</table>' +
		'					</div>' +
		'					模块目录：<input readonly="readonly" type="text" class="module_directory" id="' + addModuleName + '_directory"/>' +
		'</div>';
	$("#moduleList").append(moduleHtml);
	$("#tablePanel").append(html);
}

function setModuleDirectory(addModuleName, moduleDocument) {
	$("#" + addModuleName + "_directory").val(moduleDocument);
}

function getSelectedModule() {
	var selectedModule = $("#selectedModuleName").val();
	return selectedModule;
}
function setSelectedModule(moduleName) {
	var selectedModule = $("#selectedModuleName").val(moduleName);
}
function getDatabase() {
	var selectedDatabase = $("#cmbDataBaseName").combobox("getValue");
	return selectedDatabase;
}

$(function() {
	$(document).on("click", ".module_item", function() {
		var id = $(this).attr("id");
		var moduelName = id.substring(11, id.length);
		setSelectedModule(moduelName);
		$(".module_item").removeClass("checked_module_item");
		$(this).addClass("checked_module_item");
		$(".div_module_tables").hide();
		$("#" + moduelName + "_tables").show();
	})
	$(document).on("click", "#addmodule", function() {
		$("#hiddenOriginaltModuleName").val("");
		openDialog("moduleDetail", '新增模块');
	})
})
function setProjectCombobox() {
	var data = new Object();
	data.tableName = "sys_build_project";
	data.pageSize = "9999";
	call("GetList", data, function(result) {
		var selectedProject = getSelectedProject();
		$("#cmbProjectName").combobox("clear", {});
		$("#cmbProjectName").combobox({
			valueField : "id",
			textField : "name",
			data : result.data.dataList
		});
		if (selectedProject != '') {
			setSelectedProject(selectedProject);
		}
	})
}
function setDatabase(databaseName) {
	$("#cmbDataBaseName").combobox("setValues", databaseName);
}
function showDatabaseTables(moduleName, type, searchKey) {
	var dataBase = getDatabase();
	var tableType;
	if (type == "Table") {
		tableType = "BASE TABLE";
	}
	if (type == "View") {
		tableType = "View";
	}
	var condition = "table_schema='" + dataBase + "' AND TABLE_TYPE='" + tableType + "'";
	if (searchKey != "" && typeof (searchKey) != "undefined") {
		condition += " AND TABLE_NAME like '%" + searchKey + "%'";
	} else {
		$("#searchKey").textbox('setValue', "");
	}
	bindTableList("sysTableGrid", "information_schema.tables", type, false, condition);
	openDialog("sysTableDialog", moduleName + ':' + dataBase + ':' + type);
}
function removeTables(module, type) {
	var data = new Object();
	data.moduleName = module;
	data.type = type;
	data.projectId = getSelectedProject();
	data.tableList = [];
	var tableList = $("#" + data.moduleName + "_" + data.type.toLowerCase() + "Grid").datagrid('getSelections');
	for (var i = 0; i < tableList.length; i++) {
		data.tableList.push(eval("tableList[i]." + data.type.toLowerCase() + "Name"));
	}
	if (tableList.length < 1) {
		alert("选择个数不能为0");
	} else {
		if (!confirm("确定要删除吗?")) {
			return;
		}
		call("BatchDeleteCgTable", data, function(result) {
			$("#moduleList").empty();
			bindProjectData(getSelectedProject());
		})
	}
}
function removeTable(moduleName, tableName, type) {
	var data = new Object();
	data.moduleName = moduleName;
	data.type = type;
	data.projectId = getSelectedProject();
	data.tableList = [];
	data.tableList.push(tableName);
	if (!confirm("确定要删除吗?")) {
		return;
	}
	call("BatchDeleteCgTable", data, function(result) {
		$("#moduleList").empty();
		bindProjectData(getSelectedProject());
	})

}

function removeModule(moduleName) {
	var data = new Object();
	data.moduleName = moduleName;
	data.projectId = getSelectedProject();
	if (!confirm("确定要删除吗?")) {
		event.stopPropagation();
		return;
	}
	call("DeleteCgModule", data, function(result) {
		if (getSelectedModule() == moduleName) {
			setSelectedModule("");
		}
		$("#moduleList").empty();
		bindProjectData(getSelectedProject());
	})
	event.stopPropagation();
}

function updateModule(moduleName) {
	var data = new Object();
	var oldModuleDirectory = $("#" + moduleName + "_directory").val();
	$("#hiddenOriginaltModuleName").val(moduleName);
	$("#txtModuleName").textbox("setValue", moduleName);
	$("#txtModuleDocument").textbox("setValue", oldModuleDirectory);
	openDialog("moduleDetail", '修改模块');
	event.stopPropagation();
}

function removeProject() {
	var data = new Object();
	data.projectId = getSelectedProject();
	if (data.projectId == "") {
		alert("请先选择要删除的项目");
	} else {
		var projectName = $('#cmbProjectName').combobox('getText');
		if (!confirm("确定要删除'" + projectName + "'项目吗?")) {
			return;
		}
		call("DeleteCgProject", data, function(result) {
			refreshPage();
		})
	}
}