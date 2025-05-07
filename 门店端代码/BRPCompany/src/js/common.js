// common.js
import config from "@/js/config.js";
import util from "@/js/util.js";
import axios from "axios";
import QS from "qs";
import { ElMessage } from "element-plus";
import { refreshToken } from "@/api/userApi";
import { useTokenStore } from "@/stores/token";
import router from "@/router";
// 设置请求超时时间
axios.defaults.timeout = 30000;


const theAxios = axios.create({
  baseURL: config.adminRootUrl
});

/*
请求拦截器
*/
theAxios.interceptors.request.use(
  (config) => {
    config.headers["Content-Type"] = "application/x-www-form-urlencoded;charset=utf8";
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

/*
  响应拦截器
*/
theAxios.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  async (error) => {
    console.log(error);
    if (error.response.status) {
      switch (error.response.status) {
        case 401: {
          console.log("401");
          // 根据refresh_token重新请求刷新token
          const res = await refreshToken({ refresh_token: useTokenStore().token?.refresh_token });
          if (res.success) {
            // 保存最新的token信息
            useTokenStore().saveToken(res.content);
            // 请求回最新的token后，继续没完成的请求
            return theAxios(error.config);
          } else {
            // 刷新token失败
            ElMessage.error("刷新Token失败，需要重新登录！");
            // 跳转到登录页面
            router.replace({
              path: "/login",
              query: {
                redirect: router.currentRoute.fullPath
              }
            });
          }
          break;
        }
        case 403:
          localStorage.removeItem("token");
          setTimeout(() => {
            router.replace({
              path: "/login",
              query: {
                redirect: router.currentRoute.fullPath
              }
            });
          }, 1000);
          break;
        case 404:
          console.log("网络请求不存在");
          break;
        default:
          console.log(error.response.data.message);
      }
      return Promise.reject(error.response);
    }
  }
);

/**
 * 远程调用接口
 * @param {string} serviceName
 * @param {any} data
 * @param {Function} successCallback
 * @param {Function} [errorCallback]
 */
function call(serviceName, data, successCallback, errorCallback) {
  var params = {};
  //系统用户ID
  var sysUserId = getSysUserId();
  if (sysUserId) {
    data.sysUserId = sysUserId;
  }
  //商户用户ID
  var companyUserId = getCompanyUserId();
  if (companyUserId) {
    data.companyUserId = companyUserId;
  }
  params.data = JSON.stringify(data);
  params.token = localStorage.getItem("token");
  params.applicationId = config.applicationId;
  params.accountId = localStorage.getItem("companyUserId");
  //console.log("service request:" + serviceName);
  var serviceUrl = config.serviceUrl + "?serviceName=" + serviceName;
  console.log("serviceUrl:" + serviceUrl);
  console.log(JSON.stringify(data));
  theAxios
    .post(serviceUrl, QS.stringify(params))
    .then((res) => {
      var result = res.data;
      console.log(serviceName + " response:", result);
      if (result.success) {
        if (successCallback) {
          return successCallback(result);
        }
      } else {
        if (errorCallback) {
          return errorCallback(result);
        } else {
          var errorMessage = result.errorMessage || result.error_message;
          //输出错误信息
          if (result.messageType === "warning") {
            showWarning(errorMessage);
          } else if (result.messageType === "loginTimeout") {
            router.replace({
              path: "/login",
              query: {
                redirect: router.currentRoute.fullPath
              }
            });
          } else {
            showError(errorMessage);
          }
        }
      }
    })
    .catch((err) => {
      console.log(serviceName + " error:", err);
    });
}

/**
 *上传文件
 */
async function uploadFile(option, successCallback, errorCallback) {
  const formData = new FormData();
  if (option.data) {
    for (const [key, value] of Object.entries(option.data)) {
      if (Array.isArray(value) && value.length) {
        formData.append(key, ...value);
      } else {
        formData.append(key, value);
      }
    }
  }
  formData.append(option.filename, option.file, option.file.name);
  var serviceUrl = config.serviceUrl + "?serviceName=UploadFile";
  console.log("正在上传文件:" + option);
  await theAxios
    .post(serviceUrl, formData)
    .then((res) => {
      var result = res.data;
      console.log("upload file response:", result);
      if (result.success) {
        if (successCallback) {
          successCallback(result);
        }
      } else {
        if (errorCallback) {
          errorCallback(result);
        } else {
          //输出错误信息
          if (result.messageType == "warning") {
            showWarning(result.warningMessage);
          } else {
            var errorMessage = result.errorMessage || result.error_message;
            console.error("UploadFile", errorMessage);
            showError(errorMessage);
          }
        }
      }
    })
    .catch((err) => {
      console.log("upload file error:", err);
    });
}

/**
 * 上传多个文件
 */
function uploadFiles(arrFilePath, successCallback) {
  var uploadedFileCount = 0;
  var arrResult = [];
  for (var i = 0; i < arrFilePath.length; i++) {
    var filePath = arrFilePath[i];
    uni.uploadFile({
      url: config.serviceUrl + "?serviceName=UploadFile",
      filePath: filePath,
      name: "file",
      success: function (res) {
        //console.log(res);
        var result = JSON.parse(res.data);
        arrResult.push(result);
        if (result.success) {
          if (arrResult.length == arrFilePath.length) {
            if (successCallback) {
              successCallback(arrResult);
            }
          }
        } else {
          //console.log("upload error...")
          //输出错误信息
          if (result.messageType == "warning") {
            showWarning(result.errorMessage);
          } else {
            showError(result.errorMessage);
          }
        }
      }
    });
  }
}

/**
 * 上传图片
 */
function uploadImage(successCallback) {
  uni.chooseImage({
    success: function (res) {
      console.log(res);
      uploadFiles(res.tempFilePaths, function (arrResult) {

        if (successCallback) {
          //console.log(arrResult);
          var imageFiles = new Array();
          for (var i = 0; i < arrResult.length; i++) {
            var result = arrResult[i];
            var imageFile = result.data;
            //设置图片路径
            imageFile.imageUrl = config.adminRootUrl + "/" + imageFile.path;
            imageFiles.push(imageFile);
          }
          console.log(imageFiles);
          successCallback(imageFiles);
        }
      });
    }
  });
}

/**
 * 上传视频
 */
function uploadVideo(successCallback) {
  console.log("chooseVideo...");
  uni.chooseVideo({
    success: function (res) {
      console.log(res);
      uploadFile(res.tempFilePath, function (result) {
        if (successCallback) {
          console.log(result);
          var videoFile = result.data;
          //设置图片路径
          videoFile.VideoUrl = config.adminRootUrl + "/" + videoFile.FilePath;
        }
        successCallback(videoFile);
      });
    },
    fail: function (res) {
      console.log("fail.");
    }
  });
}

/*
显示消息
*/
function showMessage(content, title, confirmCallback) {
  var title = title;
  if (title == null || title == "") {
    title = "信息";
  }
  uni.showModal({
    title: title,
    content: content,
    showCancel: false,
    confirmText: "确定",
    success: function (res) {
      if (confirmCallback) {
        confirmCallback();
      }
    }

  });
}

/*
显示警告
*/
function showWarning(content, title) {
  ElMessage.warning(content);
}

/*
显示错误
*/
function showError(content, title) {
  ElMessage.warning(content);
}

/*
确认框
*/
function confirm(content, confirmCallback, cancelCallback) {
  var title = title;
  if (title == null || title == "") {
    title = "确认";
  }
  var confirmed = ElMessage.confirm(content);
  if (confirmed && confirmCallback) {
    confirmCallback();
    return;
  }
  if (!confirmed && cancelCallback) {
    cancelCallback();
    return;
  }
  return confirmed;
}

/*
准备是否登录状态
*/
function checkLogin(returnUrl) {
  var memberID = uni.getStorageSync("MemberID");
  if (memberID == null || memberID == "") {
    var url = "/pages/common/login";
    if (returnUrl != null && returnUrl != "") {
      url = "/pages/common/login?returnUrl=" + returnUrl;
    }
    uni.navigateTo({
      url: url
    });
    return false;
  }
  return true;
}

/*
准备是否登录状态
*/
function checkWX() {
  var WXOpenID = uni.getStorageSync("WXOpenID");
  console.log("WXOpenID:" + WXOpenID);
  if (WXOpenID == null || WXOpenID == "") {
    uni.reLaunch({
      url: "/pages/common/getUserInfo/getUserInfo"
    });

  } else {
    uni.setStorage({
      key: "WXOpenID",
      data: WXOpenID
    });
  }
}

/*
准备是否登录状态
*/
function isLogin() {
  var memberID = uni.getStorageSync("MemberID");
  if (memberID == null || memberID == "") {
    return false;
  }
  return true;
}

/**
 * 检查是否登录
 */
function checkIsLogin() {
  if (!uni.getStorageSync("MemberID")) {
    uni.showModal({
      content: "你还未登录，是否前往登录？",
      showCancel: true,
      success(res) {
        if (res.confirm) {
          uni.navigateTo({
            url: "/pages/common/login"
          });
        }
      }
    });
    return false;
  }
  return true;
}

/*
滚动到底部
*/
function pageScrollToBottom(options) {
  if (options == null || options.containerId) {
    console.log("pageScrollToBottom");
    uni.pageScrollTo({
      scrollTop: 1000
    });
    return;
  }
  console.log(options.containerId);
  var containerId = options.containerId;
  uni.createSelectorQuery().select("#" + containerId).boundingClientRect(function (rect) {
    console.log(rect);
    // 使页面滚动到底部
    uni.pageScrollTo({
      scrollTop: rect.bottom
    });
  }).exec();
}

/*
拔打系统参数配置电话
*/
function callSysSettingPhone(sysSettingCode) {
  var data = new Object();
  data.code = sysSettingCode;
  call("GetSysSetting", data, function (result) {
    var phone = result.data.value;
    uni.makePhoneCall({
      phoneNumber: phone,
      success: function () {
        console.log("成功拨打电话");
      }
    });
  });
}

/*
取得头像路径
*/
function getHeadImageFilePath(imageFilePath) {
  if (imageFilePath == null || imageFilePath == "") {
    return "/static/img/common/defaultHeadImage.png";
  } else {
    return config.adminRootUrl + "/" + imageFilePath;
  }
}

/*
取得默认图片路径
*/
function getImageUrl(imageFilePath) {
  if (imageFilePath == null || imageFilePath == "") {
    return "";
  } else {
    return config.adminRootUrl + "/" + imageFilePath;
  }
}

/*
取得默认图片路径
*/
function getImageUrl1(imageFilePath) {
  if (imageFilePath == null || imageFilePath == "") {
    return config.adminRootUrl + "/" + imageFilePath;
  } else {
    return config.adminRootUrl + "/" + imageFilePath;
  }
}

/*
加载系统参数
*/
function loadSysSetting(code, defaultValue, callback) {
  var data = {};
  data.code = code;
  call("GetSysSetting", data, function (result) {
    var value = (result.data == null) ? defaultValue : result.data.value;
    if (callback) {
      callback(value);
    }
  });
}

/*
加载布尔类型系统参数
*/
function loadBoolSysSetting(code, defaultValue, callback) {
  var data = {};
  data.code = code;
  call("GetSysSetting", data, function (result) {
    var value = "";
    if (result.data == null || result.data.value == null) {
      value = defaultValue;
    } else {
      value = result.data.value;
      if (value == null || value.toLowerCase() == "false" || value.toLowerCase() == "no" || value ==
        "0" || value.toLowerCase() ==
        "off") {
        value = false;
      } else {
        value = true;
      }
    }
    if (callback) {
      callback(value);
    }
  });
}

/*
加载int类型系统参数
*/
function loadIntSysSetting(code, defaultValue, callback) {
  var data = {};
  data.code = code;
  call("GetSysSetting", data, function (result) {
    var value = "";
    if (result.data == null || result.data.value == null) {
      value = defaultValue;
    } else {
      value = parseInt(result.data.value);
    }
    if (callback) {
      callback(value);
    }
  });
}

function payByWXA(options) {
  var data = new Object();
  data.openid = options.openid;
  data.body = options.body;
  data.out_trade_no = options.outTradeNo;
  data.total_fee = options.totalFee;
  data.notify_url = options.notifyUrl || config.salesOrderNotifyUrl;
  call("PayByWXA", data, function (result) {
    uni.requestPayment({
      "timeStamp": result.data.timeStamp,
      "nonceStr": result.data.nonceStr,
      "package": result.data.package,
      "signType": result.data.signType,
      "paySign": result.data.paySign,
      "success": function (res) {
        if (options.success) {
          options.success(res);
        }
      },
      "fail": function (res) {
        if (options.fail) {
          options.fail(res);
        }
      }
    });
  });
}

/**
 * 绑定选择器
 * @param {*} options
 * page：页面对象
 * valueFieldName：值字段名称，默认值为id
 * textFieldName：文本字段名称，默认值为name
 * headText：第一行文本
 * value：选中值
 * tableName：表名
 * condition：查询条件
 * orderBy：排序
 * indexName：索引名称，默认为表名+Index
 * dataListName：数据列表名称，默认为表名+List
 */
function bindPicker(options) {
  console.log("bindPicker:", options);
  var data = new Object();
  data.tableName = options.tableName;
  data.condition = options.condition;
  data.orderBy = options.orderBy;
  call("GetTable", data, function (result) {
    var dataList = result.data;
    options.valueFieldName = options.valueFieldName || "id";
    options.textFieldName = options.textFieldName || "name";
    //设置首行
    options.headText = options.headText || "请选择";
    if (options.headText != null) {
      var firstDataItem = {};
      firstDataItem[options.textFieldName] = options.headText;
      firstDataItem[options.valueFieldName] = null;
      dataList.splice(0, 0, firstDataItem);
    }
    //设置选中值
    if (dataList.length > 0 && options.value) {
      for (var i = 0; i < dataList.length; i++) {
        var dataItem = dataList[i];
        if (dataItem[options.valueFieldName] == options.value) {
          options.selectedIndex = i;
          break;
        }
      }
    } else {
      options.selectedIndex = 0;
    }
    var pickerIndexName = options.indexName || options.tableName + "Index";

    if (options.page) {
      var dataListName = options.dataListName || options.tableName + "List";
      options.page[dataListName] = dataList;
      options.page[pickerIndexName] = options.selectedIndex;
    }
    //回调函数
    if (options.success) {
      options.success(result);
    } else {
      options.page.$forceUpdate();
    }
  });
}

/**
 * 绑定IDName选择器
 * @param {*} options
 */
function bindIDNamePicker(options) {
  options.valueFieldName = "id";
  options.textFileName = "name";
  bindPicker(options);
}

/**
 * 绑定IDName选择器
 * @param {*} options
 */
function bindIDNamePickerWithEmptyText(options) {
  options.valueFieldName = "id";
  options.textFileName = "name";
  options.headText = "请选择";
  bindPicker(options);
}

/**
 * 绑定IDName选择器
 * @param {*} options
 */
function bindIDNamePickerWithAllText(options) {
  options.valueFieldName = "id";
  options.textFileName = "name";
  options.headText = "全部";
  bindPicker(options);
}

/**
 * 绑定CodeName选择器
 * @param {*} options
 */
function bindCodeNamePicker(options) {
  options.valueFieldName = "code";
  options.textFileName = "name";
  bindPicker(options);
}

/**
 * 绑定CodeName选择器
 * @param {*} options
 */
function bindCodeNamePickerWithEmptyText(options) {
  options.valueFieldName = "code";
  options.textFileName = "name";
  options.headText = "请选择";
  bindPicker(options);
}

/**
 * 绑定CodeName选择器
 * @param {*} options
 */
function bindCodeNamePickerWithAllText(options) {
  options.valueFieldName = "code";
  options.textFileName = "name";
  options.headText = "全部";
  bindPicker(options);
}

/**
 * 设置属性
 * @param {*} page
 * @param {*} e
 */
function setProperty(page, e) {
  var property = e.currentTarget.dataset.property;
  var dataEntity = page.dataEntity || {};
  dataEntity[property] = e.detail.value;
  page.dataEntity = dataEntity;
}

/**
 * 设置选择器属性
 * @param {*} page
 * @param {*} e
 */
function setPickerProperty(options) {
  //数据实体属性名称
  var property = options.e.currentTarget.dataset.property;
  var tableName = options.e.currentTarget.dataset.tablename;
  //值字符名称
  var valueFieldName = options.e.currentTarget.dataset.valuefieldname || "id";
  //选择器索引名称
  var indexName = options.indexName || options.e.currentTarget.dataset.indexname || util.toCameral(tableName) +
    "Index";
  //列表名称
  var dataListName = options.dataListName || options.e.currentTarget.dataset.datalistname || util.toCameral(
    tableName) + "List";
  var dataEntity = options.page.dataEntity || {};
  var dataList = options.page[dataListName];
  var selectedIndex = options.e.detail.value;
  var selectedDataItem = dataList[selectedIndex];
  //设置数据实体属性值
  dataEntity[property] = selectedDataItem[valueFieldName];
  options.page.dataEntity = dataEntity;
  options.page[indexName] = selectedIndex;
  if (options.success) {
    options.success(options);
  }
  //console.warn("dataEntity: ", JSON.stringify(dataEntity));
}

/**
 * 设置查询参数
 * @param {*} page
 * @param {*} e
 */
function setParameter(page, e) {
  var property = e.currentTarget.dataset.property;
  var parameters = page.data.parameters || {};
  parameters[property] = e.detail.value;
  page.setData({
    parameters: parameters
  });
}

/**
 * 设置选择器属性
 * @param {*} page
 * @param {*} e
 */
function setPickerParameter(options) {
  var property = options.e.currentTarget.dataset.property;
  var valueFieldName = options.e.currentTarget.dataset.valuefieldname;
  var parameterName = options.e.currentTarget.dataset.parametername;
  var parameters = options.page.data.parameters || {};
  var dataListName = options.dataListName || property + "List";
  var dataList = options.page.data[dataListName];
  var selectedIndex = options.e.detail.value;
  var selectedDataItem = dataList[selectedIndex];
  //值字符名称
  var valueFieldName = valueFieldName || "id";
  //数据实体属性名称
  var parameterName = parameterName || property + valueFieldName;
  parameters[parameterName] = selectedDataItem[valueFieldName];
  //选择器索引名称
  var indexName = options.indexName || property + "Index";
  options.page.parameters = parameters;
  options.page[indexName] = selectedIndex;
}


/**
 * 设置属性
 * @param {*} page
 * @param {*} e
 */
function setDataProperty(page, e) {
  var property = e.currentTarget.dataset.property;
  console.log(property);
  page[property] = e.detail.value;
}

/**
 * 设置选择器属性
 * @param {*} page
 * @param {*} e
 */
function setPickerDataProperty(options) {
  var property = options.e.currentTarget.dataset.property;
  var dataListName = options.dataListName || property + "List";
  var dataList = options.page.data[dataListName];
  var selectedIndex = options.e.detail.value;
  var selectedDataItem = dataList[selectedIndex];
  //值字符名称
  var valueFieldName = options.valueFieldName || "id";
  //属性名称
  var propertyName = options.propertyName || property + "id";
  //选择器索引名称
  var indexName = options.indexName || property + "Index";
  options.page.setData({
    [propertyName]: selectedDataItem[valueFieldName],
    [indexName]: selectedIndex
  });
}

/**
 * 取得后台地址
 * @param {*} dataEntity
 * @param {*} propertyName
 */
function getAdminUrl(data, propertyName) {
  if (propertyName) {
    return config.adminRootUrl + "/" + data[propertyName];
  } else {
    return config.adminRootUrl + "/" + data;
  }
}


/**
 * 下载指定 URL 的文件
 * @param {String} downloadURL
 */
function downloadFile(downloadURL) {
  uni.showLoading({
    title: "开始下载..."
  });
  const downTask = uni.downloadFile({
    url: downloadURL,
    success: res => {
      if (res.statusCode === 200) {
        uni.showToast({
          title: "OK!",
          icon: "success",
          success() {
            uni.saveFile({
              tempFilePath: res.tempFilePath
            });
          }
        });
      }
    },
    fail: (err) => {
      uni.showToast({
        title: "下载失败",
        icon: "error"
      });
      console.error(JSON.stringify(err));
    },
    complete() {
      uni.hideLoading();
    }
  });

  /* 显示下载进度 */
  downTask.onProgressUpdate(function (res) {
    uni.showLoading({
      title: `下载 ${res.progress}%`
    });
    if (res.progress === 100) uni.hideLoading();
  });
}


/**
 * 获取当前商户ID
 */
function getCompanyId() {
  return getLocalStorage("companyId");
}

/**
 * 获取当前商户ID
 */
function getSysUserId() {
  return getLocalStorage("sysUserId");
}
/**
 * 获取当前商户ID
 */
function getCompanyUserId() {
  return getLocalStorage("companyUserId");
}

/**
 * 如果 JSON 为空则返回 false，否则返回 true
 * @param {Object} object
 */
function isEmpty(object) {
  for (var i in object) {
    return false;
  }
  return true;
}

/**
 * 取得系统参数
 * @param {Object} code
 * @param {Object} defaultValue
 */
function getSysSetting(code, defaultValue) {
  var value;
  var data = {};
  data.code = code;
  data.async = false;
  call("GetSysSetting", data, function (result) {
    value = (result.data == null) ? defaultValue : result.data.value;
  });
  return value;
}

/**
 * 取得bool类型系统参数
 * @param {Object} code
 * @param {Object} defaultValue
 */
function getBoolSysSetting(code, defaultValue) {
  var value;
  var data = {};
  data.code = code;
  data.async = false;
  call("GetSysSetting", data, function (result) {
    var value = "";
    if (result.data == null || result.data.value == null) {
      value = defaultValue;
    } else {
      value = result.data.value;
      if (value == null || value.toLowerCase() == "false" || value.toLowerCase() == "no" || value ==
        "0" || value.toLowerCase() ==
        "off") {
        value = false;
      } else {
        value = true;
      }
    }

  });
  return value;
}


/**
 * 取得int类型系统参数
 * @param {Object} code
 * @param {Object} defaultValue
 */
function getIntSysSetting(code, defaultValue) {
  var value;
  var data = {};
  data.code = code;
  data.async = false;
  call("GetSysSetting", data, function (result) {
    var value = "";
    if (result.data == null || result.data.value == null) {
      value = defaultValue;
    } else {
      value = parseInt(result.data.value);
    }
  });
  return value;
}

/**
 * 付款
 * @param {*} options
 */
function pay(options) {
  var data = new Object();
  data.openId = options.openId || uni.getStorageSync("wxOpenId");
  data.description = options.description || "订单" + options.outTradeNo;
  data.outTradeNo = options.outTradeNo;
  data.amount = options.amount;
  data.notifyUrl = options.notifyUrl || config.salesOrderNotifyUrl;
  call("GetWxaPaymentInfo", data, function (result) {
    console.log("wx pay result.data:", result.data);
    uni.requestPayment({
      provider: "wxpay", // 服务提提供商
      timeStamp: result.data.timeStamp, // 时间戳
      nonceStr: result.data.nonceStr, // 随机字符串
      package: result.data.package,
      signType: result.data.signType, // 签名算法
      paySign: result.data.paySign, // 签名
      success: function (res) {
        console.log("res-success", res);
        if (options.success) {
          options.success(res);
        }
      },
      fail: function (res) {
        console.log("res-fail", res);
        if (options.fail) {
          options.fail(res);
        }
      }
    });
  });
}

/**
 * 处理数据列表图片路径
 * @param {Object} dataList
 * @param {Object} imageFileIdFieldName
 */
function processDataListImageUrl(dataList, imageFilePathFieldName) {
  if (dataList == null) {
    return null;
  }
  var imageFilePathFieldName = imageFilePathFieldName || "image_file_path";
  for (var i = 0; i < dataList.length; i++) {
    var dataItem = dataList[i];
    var imageFilePath = dataItem[imageFilePathFieldName];
    var imageUrlName = util.toCameral(imageFilePathFieldName.substr(0, imageFilePathFieldName.length - "_file_path"
      .length)) + "Url";
    dataItem[imageUrlName] = config.adminRootUrl + "/" + dataItem[imageFilePathFieldName];
  }
  return dataList;
}

/**
 * 检查手机号格式
 * @param {Object} phone
 */
function checkMobilePhone(phone) {
  const reg_tel = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; //11位手机号码正则

  if (!reg_tel.test(phone)) {
    uni.showToast({
      title: "请填写正确的手机号!",
      icon: "none"
    });
    return false;
  }
  return true;
}

/*
 * 获取Storage
 */
function getLocalStorage(name) {
  return localStorage.getItem(name);
}

/*
 * 设置Storage
 */
function setLocalStorage(name, value) {
  localStorage.setItem(name, value);
}

/**
 * 取得树型数据
 * @param {*} options
 * @returns
 */
function getTreeData(options) {
  var dataList = options.dataList;
  options.valueFieldName = options.valueFieldName || "id";
  options.textFieldName = options.valueFieldName || "name";
  options.parentValueFieldName = options.parentValueFieldName || "parent_id";
  options.parentValue = options.parentValue || "0";
  var treeData = getTreeDataList(options, options.parentValue);
  return treeData;
}

/**
 * 递归获取树数据列表
 * @param {} dataList
 * @param {*} primaryKeyName
 * @param {*} parentFieldName
 * @param {*} parentValue
 * @returns
 */
function getTreeDataList(options, parentValue) {
  var dataList = options.dataList;
  var valueFieldName = options.valueFieldName;
  var textFieldName = options.textFieldName;
  var parentValueFieldName = options.parentValueFieldName;
  var treeDataList = getListByParentValue(dataList, parentValueFieldName, parentValue);
  for (var i = 0; i < treeDataList.length; i++) {
    var treeDataItem = treeDataList[i];
    if (treeDataItem[valueFieldName]) {
      treeDataItem[valueFieldName] = String(treeDataItem[valueFieldName]);
      treeDataItem.value = String(treeDataItem[valueFieldName]);
    }
    treeDataItem.label = treeDataItem[textFieldName];
    var childTreeDataList = getTreeDataList(options, treeDataItem.value);
    if (childTreeDataList.length > 0) {
      treeDataItem.children = childTreeDataList;
    }
  }
  return treeDataList;
}

/**
 * 取得子数据列表
 * @param {*} dataList
 * @param {*} parentFieldName
 * @param {*} parentValue
 * @returns
 */
function getListByParentValue(dataList, parentFieldName, parentValue) {
  var treeDataList = [];
  for (var i = 0; i < dataList.length; i++) {
    var dataItem = dataList[i];
    var curParentValue = dataItem[parentFieldName];
    if (curParentValue == parentValue || ((curParentValue == null || curParentValue == "0" || curParentValue == 0 || curParentValue == "") && (parentValue == "0" || parentValue == 0 || parentValue == ""))) {
      treeDataList.push(dataItem);
    }
  }
  return treeDataList;
}

/*
 * 绑定下拉框
 */
function bindSelect(options) {
  options.valueFieldName = options.valueFieldName || "id";
  options.textFieldName = options.textFieldName || "name";
  var data = new Object();
  data.tableName = options.tableName;
  data.orderBy = options.orderBy;
  data.condition = options.condition;
  call("GetTable", data, function (result) {
    // console.log(result.data);
    var dataList = result.data;
    var firstItem = new Object();
    firstItem[options.valueFieldName] = "";
    firstItem[options.textFieldName] = options.emptyText;
    dataList.insert(0, firstItem);
    options.dataList.value = dataList;
  });
}


/**
 * 绑定选择器数据（下拉框，多选框，标签）
 */
function bindSelectsInfo(bindSelectOptions, pickerInfo) {
  bindSelectOptions.forEach((item, index) => {
    let data = {
      tableName: item.tableName,
      pageSize: 99999
    };
    if (item.fields) {
      data.fields = item.fields;
    }
    if (item.orderBy) {
      data.orderBy = item.orderBy;
    }
    if (item.condition) {
      data.condition = item.condition;
    }
    call("GetTable", data, (res) => {
      pickerInfo[util.toCameral(item.tableName) + "List"] = res.data;
    });
  });
}

/**
 *
 * @param {*} options
 */
function bindTreeSelect(options) {
  options.valueFieldName = options.valueFieldName || "id";
  options.textFieldName = options.textFieldName || "name";
  options.parentValueFieldName = options.parentValueFieldName || "parent_id";
  options.emptyText = options.emptyText || "";
  options.emptyValue = options.emptyValue || (options.valueFieldName == "id" ? 0 : "");
  var data = new Object();
  data.tableName = options.tableName;
  data.orderBy = options.orderBy;
  data.condition = options.condition;
  call("GetTable", data, function (result) {
    var dataList = result.data;
    options.dataList = dataList;
    var treeDataList = getTreeData(options);
    var firstItem = new Object();
    firstItem[options.valueFieldName] = options.emptyValue;
    firstItem[options.textFieldName] = options.emptyText;
    treeDataList.insert(0, firstItem);
    options.treeData.value = treeDataList;
  });
}

/**
 *
 * @returns
 */
function getCmsSiteId() {
  var cmsSiteId = getLocalStorage("cmsSiteId");
  return cmsSiteId;
}

//Element Plus Form表单正数校验
function floatValidator(rule, value, callback) {
  if (!/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error("请输入正数"));
  } else {
    // 校验通过
    callback();
  }
}

//Element Plus Form表单正整数校验
function positiveInteger(rule, value, callback) {
  if (!/^[1-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error("请输入正整数"));
  } else {
    // 校验通过
    callback();
  }
}

export default {
  call: call,
  showMessage: showMessage,
  showError: showError,
  showWarning: showWarning,
  uploadFile: uploadFile,
  uploadFiles: uploadFiles,
  uploadImage: uploadImage,
  uploadVideo: uploadVideo,
  checkLogin: checkLogin,
  isLogin: isLogin,
  checkIsLogin: checkIsLogin,
  confirm: confirm,
  pageScrollToBottom: pageScrollToBottom,
  checkWX: checkWX,
  callSysSettingPhone: callSysSettingPhone,
  getHeadImageFilePath: getHeadImageFilePath,
  getImageUrl: getImageUrl,
  loadSysSetting: loadSysSetting,
  loadBoolSysSetting: loadBoolSysSetting,
  loadIntSysSetting: loadIntSysSetting,
  payByWXA: payByWXA,
  bindPicker: bindPicker,
  bindIDNamePicker: bindIDNamePicker,
  bindIDNamePickerWithEmptyText: bindIDNamePickerWithEmptyText,
  bindIDNamePickerWithAllText: bindIDNamePickerWithAllText,
  bindCodeNamePicker: bindCodeNamePicker,
  bindCodeNamePickerWithEmptyText: bindCodeNamePickerWithEmptyText,
  bindCodeNamePickerWithAllText: bindCodeNamePickerWithAllText,
  setProperty: setProperty,
  setPickerProperty: setPickerProperty,
  setDataProperty: setDataProperty,
  setParameter: setParameter,
  setPickerParameter: setPickerParameter,
  getAdminUrl: getAdminUrl,
  downloadFile: downloadFile,
  isEmpty: isEmpty,
  getSysSetting: getSysSetting,
  getBoolSysSetting: getBoolSysSetting,
  getIntSysSetting: getIntSysSetting,
  processDataListImageUrl: processDataListImageUrl,
  pay: pay,
  checkMobilePhone: checkMobilePhone,
  getLocalStorage: getLocalStorage,
  setLocalStorage: setLocalStorage,
  getTreeData: getTreeData,
  bindSelect: bindSelect,
  bindTreeSelect,
  getCmsSiteId,
  getCompanyId,
  getSysUserId,
  getCompanyUserId,
  bindSelectsInfo,
  floatValidator,
  positiveInteger
};