
/*
 * 绑定Cms权限树型表
 */
function bindCmsComboTreeWidthPermission(controlId, serviceName, parentValue, valueFieldName,
		textFieldName, parentFieldName, multiple, condition) {
	var data = new Object();
	data.orderBy = "display_order";
	data.condition = condition;
	data.cmsSiteId =  $.cookie("cmsSiteId");
	data.cmsLanguageCode = currentCmsLanguageCode;
	data.pageSize = 10000;
	data.operatorId = $.cookie("sysUserId");
	call(serviceName, data, function(result) {
		 //console.log(result);
		var comboTreeData = getComboTreeData(result.data.dataList, parentValue,
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
		for ( var i = 0; i < result.data.length; i++) {
			var dataItem = result.data[i];
			if (dataItem.is_default) {
				$("#" + controlId).combotree("setValue",
						dataItem[valueFieldName]);
			}
		}
	});
}
/*
 * 绑定Cms权限树
 */
function bindCmsTreeWidthPermission(controlId, serviceName, valueFieldName, textFieldName,
		parentFieldName, rootText, rootValue, orderBy, checkbox, clickCallback) {
	var data = new Object();
	data.operatorId = $.cookie("sysUserId");
	data.cmsSiteId =  $.cookie("cmsSiteId");
	data.condition = "cms_site_id =" + $.cookie("cmsSiteId");
	data.cmsLanguageCode = currentCmsLanguageCode;
	data.pageSize = 10000;
	call(serviceName, data, function(result) {
		// alert(JSON.stringify(result));
		outputTree(controlId, result.data.dataList, valueFieldName, textFieldName,
				parentFieldName, rootText, rootValue, checkbox, clickCallback);
	});
}
/*
 * 绑定Cms权限树并赋值
 */
function bindCmsComboTreeWithValueAndPermission(controlId, serviceName, parentValue,
		valueFieldName, textFieldName, parentFieldName, multiple, condition,
		value) {
	var data = new Object();
	data.orderBy = "id";
	data.cmsSiteId =  $.cookie("cmsSiteId");
	data.condition = condition;
	data.pageSize = 10000;
	data.cmsLanguageCode = currentCmsLanguageCode;
	data.operatorId = $.cookie("sysUserId");
	call(serviceName, data, function(result) {
		// alert(JSON.stringify(result));
		// console.log(result);
		cmsChannelParentId = new Array();
		cmsChannelParentId.push(value);
		var comboTreeData = getComboTreeData(result.data.dataList, parentValue,
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
		$("#" + controlId).combotree("setValue", value);
	});
}
/*
 * 绑定Cms权限树作为父节点并赋值
 */
function bindCmsComboTreeWithValueAndPermissionAsParentNode(controlId, serviceName, parentValue,
		valueFieldName, textFieldName, parentFieldName, multiple, condition,
		value,id) {
	var data = new Object();
	data.orderBy = "display_order";
	data.condition = condition;
	data.cmsSiteId =  $.cookie("cmsSiteId");
	data.pageSize = 10000;
	data.cmsLanguageCode = currentCmsLanguageCode;
	data.operatorId = $.cookie("sysUserId");
	call(serviceName, data, function(result) {
		// alert(JSON.stringify(result));
		cmsChannelParentId = new Array();
		cmsChannelParentId.push(value);
		var comboTreeData = getCmsChannelComboTreeDataWithValueAndPermission(result.data.dataList, parentValue,
				valueFieldName, textFieldName, parentFieldName,value,id);

		var firstItem = new Object();
		firstItem.id = parentValue;
		firstItem.text = "空";
		comboTreeData.insert(0, firstItem);
		// alert(JSON.stringify(comboTreeData));
		$("#" + controlId).combotree({
			multiple : multiple,
			data : comboTreeData
		});
		$("#" + controlId).combotree("setValue", value);
	});
}
/*
 * 取得树数据
 */
function getCmsChannelComboTreeDataWithValueAndPermission(lstData, parentValue, valueFieldName, textFieldName,
		parentFieldName,value,id) {
	var treeData = new Array();
	var lstSubData = getCmsChannelSubDataListWithValueAndPermission(lstData, parentValue, parentFieldName);
	// console.log(lstSubData);
	for ( var i = 0; i < lstSubData.length; i++) {
		var dataItem = lstSubData[i];
		var treeNode = new Object();
		treeNode.id = dataItem[valueFieldName];
		treeNode.text = dataItem[textFieldName];
		treeData.push(treeNode);
		var primaryKey = dataItem[valueFieldName];
		// console.log(primaryKey + ":" + dataItem[valueFieldName]);
		var childrenTreeData = getCmsChannelComboTreeDataWithValueAndPermission(lstData, primaryKey,
				valueFieldName, textFieldName, parentFieldName,value,id);
		if (childrenTreeData.length > 0) {
			treeNode.children = new Array();
			for ( var j = 0; j < childrenTreeData.length; j++) {
				treeNode.children.push(childrenTreeData[j]);
			}
		}
	}
	return treeData;
}
/*
 * 取得子数据列表
 */
var cmsChannelParentId = new Array();
function getCmsChannelSubDataListWithValueAndPermission(lstData, parentValue, parentFieldName) {
	if (parentValue == null) {
		parentValue = "";
	}
	var lstSubData = new Array();
	for ( var i = 0; i < lstData.length; i++) {
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
		if ((dataItemParentValue == parentValue) && (dataItem["id"] != channelDetial.id)) {
				lstSubData.push(dataItem);
		}
	}
	return lstSubData;
}
var isPermittedCmsChannelIds;
$(function(){
	var data = new Object();
	data.operatorId = $.cookie("sysUserId");
	data.cmsSiteId = $.cookie("cmsSiteId");
	data.pageSize = 10000;
	call("GetCmsChannelIdListWidthPromission", data, function(result) {
		//alert(JSON.stringify(result));
		isPermittedCmsChannelIds = result.data.dataList;
	})	
})

function isPermitted(id){
	if(id == 0){
		return false;
	}
	for( var i = 0; i < isPermittedCmsChannelIds.length; i++){
		if(isPermittedCmsChannelIds[i] == id){
			return true;
		}
	}
	return false;
}
function isPermittedList(idList){
	for( var i = 0; i < idList.length; i++){
		if(!isPermitted(idList[i])){
			return false;
		}
	}
	return true;
}
//获取CMS默认语言Code
var currentCmsLanguageCode = getDefaultCmsLanguageCode();
function getDefaultCmsLanguageCode(){
	return getCookie("cmsLanguage");
}

$(function(){
	//点击查找按钮，显示多语言字段
	$(".copy_item").click(function(){
		var type = $(this).attr("id").replace("copy_item_","");
		var title = $(this).attr("title");
		outputCopyItemDiv(type,title);
	})
	
})

function outputCopyItemDiv(type,title){
	var viewLanguages = dataInfo.dataList;
	var html = "<div id=\"divCopyItemDivDialog\" title=\""+title+"\" class=\"easyui-dialog\" style=\"width:800px;min-height:500px\">\n" +
					"	<div id=\"divFieldTabs\" class=\"easyui-tabs\"\n" +
					"		style=\"width:780px;height:500px;\" closed=\"true\">\n"+
					"	<span class=\"copyMessage\">复制成功</span>\n"+
					" 	<a href=\"#\" class=\"easyui-linkbutton copyIcon\" iconCls=\"icon-cut\" plain=\"true\"	onclick=\"copyContent()\">复制到剪切板</a>";
	for (var val in viewLanguages) {
		if(val != "Default" && val != currentCmsLanguageCode){
			var item = viewLanguages[val];
			html += "		<div title=\""+item.cmsLanguageName+"\" style=\"display:none;padding:20px\">"+item[type]+"</div>\n";
		}
	}
	html += 	"	</div>\n" +
	 		"</div>";
	$("#divCopyItemDivDialog").parent().next().remove();
	$("#divCopyItemDivDialog").parent().remove();
	$("#copyItemDiv").append(html);
	$.parser.parse("#copyItemDiv");
}
//点击复制按钮，复制对应语言字段到剪切板
function copyContent(){
	var html = $("#divCopyItemDivDialog").find(".panel :visible").html();
	if(html == ""){
		return;
	}
    var oInput = document.createElement('input');
	oInput.value = html;
    document.body.appendChild(oInput);
    oInput.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    oInput.className = 'oInput';
    oInput.style.display='none';
    $(".copyMessage").fadeIn(1000);
    $(".copyMessage").fadeOut(1000);
}