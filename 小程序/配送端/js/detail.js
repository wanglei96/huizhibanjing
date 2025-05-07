var config = require("config.js");
var common = require("common.js");
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
  if (page.data.mode == "edit") {
    if (page.bindData) {
      page.bindData();
    } else {
      bindData(page);
    }
  }
}

/**
 * 绑定数据
 */
function bindData(page) {
  var getServiceName = page.data.getServiceName || "Get" + page.data.tableName;
  var data = {};
  data.ID = page.data.ID;
  common.call(getServiceName, data, function (result) {
    var dataEntity = result.data;
    if (page.processDataEntity) {
      dataEntity = page.processDataEntity(dataEntity);
    }
    page.setData({
      dataEntity: dataEntity
    });
  })
}
/**
 * 保存
 */
function save(options) {
  var page = options.page;
  if (page.data.mode == "edit") {
    //修改
    if (page.updateProduct) {
      page.updateProduct();
      return;
    }
    var data = page.data.dataEntity;
    var updateServiceName = "Update" + page.data.tableName;
    common.call(updateServiceName, data, function (result) {
      if (options.success) {
        options.success(result);
      }
    });
  } else {
    //添加
    if (page.addProduct) {
      page.addProduct();
      return;
    }
    var data = page.data.dataEntity;
    var addServiceName = "Add" + page.data.tableName;
    common.call(addServiceName, data, function (result) {
      if (options.success) {
        options.success(result);
      }
    });
  }
}
module.exports = {
  loadPage: loadPage,
  save: save
};