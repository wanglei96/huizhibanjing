/**
 * jQuery.uploader - Uploader abstractions for jQuery Written by lood Licensed
 * under the WTFPL (http://sam.zoy.org/wtfpl/). Date: 2015/4/24
 * 
 * @author lood
 * @version 1.1
 * 
 */
jQuery
		.extend({
			uploader : {
				options : {
					maxSize : 5 * 1024 * 1024,
					fileUploadID : null,
					isImage : false,
					isPreviewImage : true, // 是否显示预览图片
					previewImageID : null,
					autoUpload : true,
					multiple : false,
					serviceUrl : null
				},
				/*
				 * 上传文件
				 */
				uploadFile : function(fileUploadID, options) {
					// alert("uploadFile");
					var isValid = jQuery.uploader.checkFile(fileUploadID,
							options);
					if (!isValid) {
						return;
					}
					var fileCount = $("#" + fileUploadID)[0].files.length;
					var successCount = 0;
					var arrResult = new Array();
					for ( var i = 0; i < fileCount; i++) {
						var file = $("#" + fileUploadID)[0].files[i];
						var requestData = new FormData();
						requestData.append("file", file);
						// var slice = file.slice(0, file.size);
						// requestData.append("file", slice);
						var serviceUrl = jQuery.uploader.getServiceUrl(options);
						$
								.ajax({
									url : serviceUrl,
									type : "POST",
									dataType : "json",
									data : requestData,
									processData : false,
									contentType : false,
									success : function(result) {
										if (options.multiple) {
											successCount++;
											arrResult.push(result);
											// 如果成功数量等于上传文件数量，则成功返回
											if (successCount == fileCount) {
												if (options.success != null
														&& options.success != undefined) {
													options.success(arrResult);
												}
											}
										} else {
											if (options.success != null
													&& options.success != undefined) {
												options.success(result);
											}
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										if (options.error != null
												&& options.error != undefined) {
											options.error(XMLHttpRequest,
													textStatus, errorThrown);
										}
									}
								});
						if (options.uploading != null) {
							options.uploading();
						}
					}
				},
				/*
				 * 取得服务地址
				 */
				getServiceUrl : function(options) {
					var serviceUrl = null;
					if (config != null && config.serviceUrl != null
							&& config.serviceUrl != "") {
						serviceUrl = config.serviceUrl;
					}
					if (options.serviceUrl != null
							&& options.serviceUrl != undefined
							&& options.serviceUrl != "") {
						serviceUrl = options.serviceUrl;
					}
					if (window.location.href.toLowerCase().indexOf("/admin/") >= 0) {
						serviceUrl = "/admin/api/service";
					}
					if (window.location.href.toLowerCase().indexOf("/web/") >= 0) {
						serviceUrl = "/web/api/service";
					}
					if (serviceUrl == null) {
						serviceUrl = "/api/service";
					}
					serviceUrl = serviceUrl + "?service_name=UploadFile&r="
							+ Math.random();
					return serviceUrl;
				},
				// 获取数据的URL地址
				createObjectURL : function(blob) {
					if (window.URL) {
						return window.URL.createObjectURL(blob);
					} else if (window.webkitURL) {
						return window.webkitURL.createObjectURL(blob);
					} else {
						return null;
					}
				},
				previewImage : function(fileUploadID, options) {
					// alert("previewImage");
					var $fileUpload = $("#" + fileUploadID);
					var options = $.extend({}, options);
					// 获取文件
					var file = $fileUpload[0].files[0];
					// 预览图片
					var imageUrl = jQuery.uploader.createObjectURL(file);
					if (options.isImage && options.isPreviewImage) {
						if (options.previewImageID != null) {
							$("#" + options.previewImageID).attr("src",
									imageUrl);
						}
					}
				},
				checkFile : function(fileUploadID, options) {
					var $fileUpload = $("#" + fileUploadID);
					var options = $.extend({}, options);
					// 获取文件
					var file = $fileUpload[0].files[0];
					// 文件为空判断
					if (file === null || file === undefined) {
						alert("请选择您要上传的文件！");
						return false;
					}

					// 检测文件类型
					if (options.isImage) {
						if (file.type.indexOf('image') === -1) {
							alert("请选择图片文件！");
							return false;
						}
					}
					// 检查计算文件大小
					if (options.maxSize != null && options.maxSize != undefined) {
						var size = Math.floor(file.size / 1024);
						if (size > options.maxSize) {
							alert("上传文件不得超过" + options.maxSize + "KB!");
							return false;
						}
						;
					}
					return true;
				},
				uploadify : function(element, options) {
					// alert("uploadify");
					var options = $
							.extend({}, jQuery.uploader.options, options);
					element
							.each(function() {
								// 设置预览图片ID
								if (options.isImage && options.isPreviewImage) {
									if (options.previewImageID == null) {
										if ($(this)[0].tagName.toLowerCase() == "img") {
											options.previewImageID = $(this)
													.attr("ID");
										}
									}
								}
								// 监听点击事件
								$(this)
										.click(
												function() {
													// alert("click");
													var fileUploadID = options.fileUploadID;
													if (options.fileUploadID == ""
															|| options.fileUploadID == null
															|| options.fileUploadID == undefined) {
														var fileUploadID = $(
																this)
																.attr("ID")
																+ "FileUpload";
													}
													// 如果文件上传控件不存在，则添加
													if ($("#" + fileUploadID)
															.size() == 0) {
														$("body")
																.append(
																		"<input id=\""
																				+ fileUploadID
																				+ "\" type=\"file\" style=\"display: none;\" />");
													}
													$("#" + fileUploadID)
															.unbind("change");
													$("#" + fileUploadID)
															.change(
																	function() {
																		// alert("change");
																		// 检查文件
																		var isValid = jQuery.uploader
																				.checkFile(
																						fileUploadID,
																						options);
																		if (!isValid) {
																			return;
																		}
																		// 预览图片
																		jQuery.uploader
																				.previewImage(
																						fileUploadID,
																						options);
																		// 上传文件
																		if (options.autoUpload) {
																			jQuery.uploader
																					.uploadFile(
																							fileUploadID,
																							options);
																		}
																	});
													// 点击文件上传控件
													$("#" + fileUploadID)
															.click();
												});
							});
					return element;
				}
			}
		});
jQuery.fn.extend({
	uploadify : function(options) {
		return jQuery.uploader.uploadify(this, options);
	}
});
$(function(){
	var uploadFiletimes = 20;
	$(".photo").on('error',function () {
			if(uploadFiletimes>0){
				$(this).attr('src', '../../img/errorPic.png');
				uploadFiletimes -- ;
			}
	  });
})
