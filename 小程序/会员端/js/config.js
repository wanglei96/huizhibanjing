const testing = false;
const debug = true ;//true

//后台根地址
var adminRootUrl = "https://admin.8630.net/WYB";
if (testing) {
	 adminRootUrl = "https://qq.strosoft.com/GJYAdmin/";
}
if (debug) {
	adminRootUrl = "https://admin.brp.rome8.com";    //https://admin.brp.rome8.com 正式地址     https://admin.brp.4404.net  https://admin.fu7.net/BRPAdmin  测试地址
}
//服务接口地址
var serviceUrl = adminRootUrl + "/api/Service";
var docPreviewUrl = "https://view.officeapps.live.com/op/view.aspx?src=";
// var imageRootUrl = adminRootUrl + "/";
var imageRootUrl = "https://admin.brp.rome8.com/static/";   //服务器后台路径：D:\Software\Tomcat8.5\webapps\BRPAdmin
//充值通知地址
var rechargeOrderNotifyUrl = adminRootUrl + "/api/WXMemberWapNotifyUrl.ashx";
//订单通知地址
var salesOrderNotifyUrl = adminRootUrl + "/api/WXSalesOrderNotify.ashx";
//logo地址
var logoUrl = adminRootUrl + "/img/logo.png";
//是否启用充值功能
var rechargeEnabled = false;
//是否启用手机注册功能
var mobilePhoneRegisterEnabled = false;
//版本
var version = "1.0.0";
//最后修改时间
var lastUpdateTime = "2021-11-22";
//最后修改时间
var copyright = "crccy.net";
//小程序ID
var appId = "wxc7d18c6c2b1f4a91";

module.exports = {
	debug: debug,
	testing: testing,
	serviceUrl: serviceUrl,
	adminRootUrl: adminRootUrl,
	imageRootUrl: imageRootUrl,
	logoUrl: logoUrl,
	version: version,
	lastUpdateTime: lastUpdateTime,
	copyright: copyright,
	docPreviewUrl: docPreviewUrl,
	appId: appId,
}
