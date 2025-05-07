// common.js
import config from '@/js/config.js';
/*
远程调用接口
*/
function call(serviceName, data, successCallback, errorCallback) {
	var params = new Object();
	params.data = JSON.stringify(data);
	//console.log("service request:" + serviceName);
	var serviceUrl = config.serviceUrl + "?serviceName=" + serviceName;
	//console.log("serviceUrl:" + serviceUrl);
	//console.log(JSON.stringify(data));
	uni.showLoading();
	uni.request({
		url: serviceUrl, //仅为示例，并非真实的接口地址
		data: params,
		async: true,
		header: {
			'content-type': 'application/x-www-form-urlencoded; charset=UTF-8' // 默认值
		},
		method: "POST",
		success: function(res) {
			uni.hideLoading();
			//console.log("service response:" + serviceName);
			var result = res.data;
			//console.warn(JSON.stringify(result));
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
						var errorMessage = result.errorMessage || result.error_message
						console.error(serviceName, errorMessage);
						showError(errorMessage);
					}
				}
			}
		},
		fail: function(res) {
			console.log("service fail:" + serviceName);
			console.log(JSON.stringify(res));
			if (errorCallback) {
				errorCallback(res);
			}
			uni.hideLoading();
		}
	})
}
/**
 *上传文件
 */
function uploadFile(filePath, successCallback) {
	uni.uploadFile({
		url: config.serviceUrl + "?serviceName=UploadFile",
		filePath: filePath,
		name: 'file',
		success: function(res) {

			//console.log(res);
			var result = JSON.parse(res.data);
			if (result.success) {
				//console.log("upload success...")
				if (successCallback) {
					successCallback(result);
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
		},
		fail: function(res) {
			console.log(res);
		}
	})
}
/**
 * 上传多个文件
 */
function uploadFiles(arrFilePath, successCallback) {
	var uploadedFileCount = 0;
	var arrResult = new Array();
	for (var i = 0; i < arrFilePath.length; i++) {
		var filePath = arrFilePath[i];
		uni.uploadFile({
			url: config.serviceUrl + "?serviceName=UploadFile",
			filePath: filePath,
			name: 'file',
			success: function(res) {
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
		})
	}
}
/**
 * 上传图片
 */
function uploadImage(successCallback) {
	uni.chooseImage({
		success: function(res) {
			console.log(res);
			uploadFiles(res.tempFilePaths, function(arrResult) {

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
			})
		},
	})
}

/**
 * 上传视频
 */
function uploadVideo(successCallback) {
	console.log("chooseVideo...")
	uni.chooseVideo({
		success: function(res) {
			console.log(res);
			uploadFile(res.tempFilePath, function(result) {
				if (successCallback) {
					console.log(result);
					var videoFile = result.data;
					//设置图片路径
					videoFile.VideoUrl = config.adminRootUrl + "/" + videoFile.FilePath;
				}
				successCallback(videoFile);
			})
		},
		fail: function(res) {
			console.log("fail.");
		}
	})
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
		success: function(res) {
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
	var title = title;
	if (title == null || title == "") {
		title = "警告";
	}
	uni.showModal({
		title: title,
		content: content,
		showCancel: false,
		confirmText: "确定"
	});
}
/*
显示错误
*/
function showError(content, title) {
	var title = title;
	if (title == null || title == "") {
		title = "错误";
	}
	uni.showModal({
		title: title,
		content: content,
		showCancel: false,
		confirmText: "确定"
	});
}

/*
确认框
*/
function confirm(content, confirmCallback, title, cancelCallback) {
	var title = title;
	if (title == null || title == "") {
		title = "确认";
	}
	uni.showModal({
		title: title,
		content: content,
		success: function(res) {
			if (res.confirm) {
				if (confirmCallback) {
					confirmCallback();
				}
			} else {
				if (cancelCallback) {
					cancelCallback();
				}
			}
		}
	})
}

/*
准备是否登录状态
*/
function checkLogin(returnUrl) {
	var loginName = uni.getStorageSync('loginName');
	if (loginName == null || loginName == "") {
		var url = '/pages/common/login/login';
		if (returnUrl != null && returnUrl != "") {
			url = '/pages/common/login/login?returnUrl=' + returnUrl;
		}
		uni.navigateTo({
			url: url
		})
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
			url: '/pages/common/getUserInfo/getUserInfo',
		});

	} else {
		uni.setStorage({
			key: 'WXOpenID',
			data: WXOpenID,
		})
	}
}

/*
准备是否登录状态
*/
function isLogin() {
	var memberID = uni.getStorageSync('MemberID');
	if (memberID == null || memberID == "") {
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
	uni.createSelectorQuery().select("#" + containerId).boundingClientRect(function(rect) {
		console.log(rect);
		// 使页面滚动到底部
		uni.pageScrollTo({
			scrollTop: rect.bottom
		})
	}).exec();
}

/*
拔打系统参数配置电话
*/
function callSysSettingPhone(sysSettingCode) {
	var data = new Object();
	data.Code = sysSettingCode;
	call("GetSysSetting", data, function(result) {
		var phone = result.data.Value;
		uni.makePhoneCall({
			phoneNumber: phone,
			success: function() {
				console.log("成功拨打电话");
			}
		})
	})
}
/*
取得头像路径
*/
function getHeadImageFilePath(imageFilePath) {
	if (imageFilePath == null || imageFilePath == "") {
		return "/static/img/common/defaultHeadImage.png" 
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
	data.Code = code;
	call("GetSysSetting", data, function(result) {
		var value = (result.data == null) ? defaultValue : result.data.Value;
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
	data.Code = code;
	call("GetSysSetting", data, function(result) {
		var value = "";
		if (result.data == null || result.data.Value == null) {
			value = defaultValue;
		} else {
			value = result.data.Value;
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

function payByWxa(options) {
	var data = new Object();
	data.openid = options.openid;
	data.body = options.body;
	data.out_trade_no = options.outTradeNo;
	data.total_fee = options.totalFee;
	data.notify_url = options.notifyUrl || config.salesOrderNotifyUrl;
	call("GetWxaPaymentInfo", data, function(result) {
		uni.requestPayment({
			'timeStamp': result.data.timeStamp,
			'nonceStr': result.data.nonceStr,
			'package': result.data.package,
			'signType': result.data.signType,
			'paySign': result.data.paySign,
			'success': function(res) {
				if (options.success) {
					options.success(res);
				}
			},
			'fail': function(res) {
				if (options.fail) {
					options.fail(res);
				}
			}
		})
	})
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
	call("GetTable", data, function(result) {
		var dataList = result.data;
		options.valueFieldName = options.valueFieldName || "id";
		options.textFieldName = options.textFieldName || "name";
		//设置首行
		if (options.headText != null) {
			var firstDataItem = {};
			firstDataItem[options.textFileName] = options.headText;
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
	})
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
	var indexName = options.indexName || tableName + "Index";
	//列表名称
	var dataListName = options.dataListName || tableName + "List";
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
	})
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
function getAdminUrl(dataEntity, propertyName) {
	return config.adminRootUrl + "/" + dataEntity[propertyName];
}


/**
 * 下载指定 URL 的文件
 * @param {String} downloadURL
 */
function downloadFile(downloadURL) {
	uni.showLoading({
		title: "开始下载..."
	})
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
						})
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
	downTask.onProgressUpdate(function(res) {
		uni.showLoading({
			title: `下载 ${res.progress}%`
		});
		if (res.progress === 100) uni.hideLoading()
	});
}


/**
 * 获取当前项目ID
 */
function getCurrentProjectId() {
	return uni.getStorageSync("CURRENT_PROJECT_ID");
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

module.exports.call = call;
module.exports.showMessage = showMessage;
module.exports.showError = showError;
module.exports.showWarning = showWarning;
module.exports.uploadFile = uploadFile;
module.exports.uploadFiles = uploadFiles;
module.exports.uploadImage = uploadImage;
module.exports.uploadVideo = uploadVideo;
module.exports.checkLogin = checkLogin;
module.exports.isLogin = isLogin;
module.exports.confirm = confirm;
module.exports.pageScrollToBottom = pageScrollToBottom;
module.exports.checkWX = checkWX;
module.exports.callSysSettingPhone = callSysSettingPhone;
module.exports.getHeadImageFilePath = getHeadImageFilePath;
module.exports.getImageUrl = getImageUrl;
module.exports.loadSysSetting = loadSysSetting;
module.exports.loadBoolSysSetting = loadBoolSysSetting;
module.exports.payByWxa = payByWxa;
module.exports.bindPicker = bindPicker;
module.exports.bindIDNamePicker = bindIDNamePicker;
module.exports.bindIDNamePickerWithEmptyText = bindIDNamePickerWithEmptyText;
module.exports.bindIDNamePickerWithAllText = bindIDNamePickerWithAllText;
module.exports.bindCodeNamePicker = bindCodeNamePicker;
module.exports.bindCodeNamePickerWithEmptyText = bindCodeNamePickerWithEmptyText;
module.exports.bindCodeNamePickerWithAllText = bindCodeNamePickerWithAllText;
module.exports.setProperty = setProperty;
module.exports.setPickerProperty = setPickerProperty;
module.exports.setDataProperty = setDataProperty;
module.exports.setParameter = setParameter;
module.exports.setPickerParameter = setPickerParameter;
module.exports.getAdminUrl = getAdminUrl;
module.exports.downloadFile = downloadFile;
module.exports.getCurrentProjectId = getCurrentProjectId;
module.exports.isEmpty = isEmpty;
