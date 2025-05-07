var config = require("@/js/config.js");
var common = require("@/js/common.js");
/**
 * 加载页面
 * @param {*} page 
 */
function loadPage(page) {
	//绑定控件
	if (page.bindControls) {
		page.bindControls();
	}
	//绑定数据
	if (page.bindData) {
		page.bindData();
	} else {
		bindData(page);
	}
}
/**
 * 绑定数据
 */
function bindData(page) {
	page.data.parameters = page.data.parameters || {};
	page.data.parameters.PageIndex = 0;
	var dataListName = page.data.dataListName || page.data.tableName + "List";
	page.setData({
		[dataListName]: []
	})
	loadData(page);
}
/**
 * 加载数据
 * @param {*} page 
 */
function loadData(page) {
	var tableName = page.data.tableName;
	var getListServiceName = page.data.getListServiceName || "Get" + tableName + "List";
	var parameters = page.data.parameters;
	if (page.getParameters) {
		parameters = page.getParameters(parameters);
	}
	common.call(getListServiceName, parameters, function(result) {
		if (page.processResult) {
			page.processResult(result);
		} else {
			var newDataList = result.data.DataList;
			var dataListName = page.data.dataListName || page.data.tableName + "List"
			var dataList = page.data[dataListName] || [];
			for (var i = 0; i < newDataList.length; i++) {
				var dataItem = newDataList[i];
				//处理数据项
				if (page.processDataItem) {
					dataItem = page.processDataItem(dataItem);
				}
				dataList.push(dataItem);
			}
			//处理数据列表
			if (page.processDataList) {
				dataList = page.processDataList(dataList);
			}
			console.log("dataList:");
			console.log(dataList);
			page.setData({
				[dataListName]: dataList
			});
		}
	})
}
module.exports = {
	loadPage: loadPage,
	bindData: bindData,
	loadData: loadData
};
