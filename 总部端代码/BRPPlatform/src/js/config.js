let testing = false;
let debug = false;

//后台根地址
let adminRootUrl = "http://admin.brp.rome8.com";
if (testing) {
    //adminRootUrl = 'http://admin.brp.4404.net'
    adminRootUrl = 'https://admin.fu7.net/BRPAdmin'
}
if (debug) {
    adminRootUrl = "http://localhost:8080";
}
var isCompany = false;

var applicationId = "BRPPlatform";//应用ID
//服务接口地址
var serviceUrl = adminRootUrl + "/api/Service";
//上传文件服务地址
var uploadFileServiceUrl = serviceUrl + "?serviceName=UploadFile";
//文档预览地址
var docPreviewUrl = "https://view.officeapps.live.com/op/view.aspx?src=";
//充值通知地址
var rechargeOrderNotifyUrl = adminRootUrl + "/api/WXMemberWapNotifyUrl.ashx";
//订单通知地址
// var salesOrderNotifyUrl = adminRootUrl + "/api/WXSalesOrderNotify.ashx";
//订单通知地址
var salesOrderNotifyUrl = "https://ws2.strosoft.com/NZWAdmin/api/WxPayNotify";
//logo地址
var logoUrl = adminRootUrl + "/img/logo.png";
//用服务器图片地址
var imageWXAppImageUrl = adminRootUrl + "/WXAppImage/";

//是否启用充值功能
var rechargeEnabled = false;
//是否启用手机注册功能
var mobilePhoneRegisterEnabled = false;
//版本
var version = "1.0.1";
//最后修改时间
var lastUpdateTime = "2023-05-25";
//最后修改时间
var copyright = "NZW";
//系统名称
var systemName = "（总部）慧至半径童书馆管理系统";
//系统全名
var fullSystemName = "（总部）慧至半径童书馆管理系统";
//小程序ID
// var appId = "wx187605989a266417";// TCJ
// var appId = "wx6c3ae89c6515a4ce";/NZW
var appId = "wx7adecf259571c888"; //BOP
//订阅模板TemplateID
var templateID = ['nz2PgAQ0l1dvb1vlRU8kXu_EtUWGo-yaIEiGTM_yoRU'];

//距离计算APIKey
// var distanceCalculation = "5QABZ-LTEWB-IYDUR-J52IT-POMD7-7GBEO"; //zrj
// var distanceCalculation = "Y3DBZ-G5WWQ-BJL5Q-BOLMU-OKOJ3-YQBDA"; //ws
// var distanceCalculation = "F24BZ-DTBWG-ENLQ3-QV2XR-HCU4O-NCBJ3"; //zhs
// var distanceCalculation = "AGXBZ-ENYCV-SCJPG-57UMS-QV3IV-WEFRC"; //qy

var editorConfig = {
    //serverUrl: process.env.VUE_APP_BASE_API + "/config"',
    //**更正代码231109**
    serverUrl: adminRootUrl + "/config",
    UEDITOR_HOME_URL: '/UEditor/',
    //**代码补充231218**
    UEDITOR_CORS_URL: '/UEditor/',
    zIndex: 99999 //设置z轴顺序值，避免工具栏下拉组件被遮挡
};
export default {
    debug: debug,
    testing: testing,
    serviceUrl: serviceUrl,
    adminRootUrl: adminRootUrl,
    logoUrl: logoUrl,
    version: version,
    lastUpdateTime: lastUpdateTime,
    copyright: copyright,
    docPreviewUrl: docPreviewUrl,
    systemName: systemName,
    fullSystemName: fullSystemName,
    appId: appId,
    imageWXAppImageUrl: imageWXAppImageUrl,
    salesOrderNotifyUrl: salesOrderNotifyUrl,
    isCompany: isCompany,
    uploadFileServiceUrl: uploadFileServiceUrl,
    editorConfig: editorConfig,
    applicationId:applicationId
}