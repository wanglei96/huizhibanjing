let testing = false;
let debug =  false;

//后台根地址
let adminRootUrl = 'https://admin.brp.rome8.com'
//门店地址
let companyRootUrl = 'http://company.brp.rome8.com'
if (testing) {
  //adminRootUrl = 'http://admin.brp.4404.net'
  adminRootUrl = 'https://admin.fu7.net/BRPAdmin'
  companyRootUrl = 'http://company.brp.4404.net'
}
if (debug) {
  adminRootUrl = 'http://localhost:8080'
}

var isCompany = true

var applicationId = "BRPCompany";//应用ID

//图片地址
var imageAdminRootUrl = adminRootUrl + '/'

var localImageRootUrl = adminRootUrl + '/img/appImg'
// localImageRootUrl = "https://ws2.strosoft.com/NZWAdmin/img/appImg";
// https://ws2.strosoft.com/NZWAdmin/img/appImg/member/add2.png

//服务接口地址
var serviceUrl = adminRootUrl + '/api/Service'
var docPreviewUrl = 'https://view.officeapps.live.com/op/view.aspx?src='
var imageRootUrl = adminRootUrl + '/'
//充值通知地址
var rechargeOrderNotifyUrl = adminRootUrl + '/api/WXMemberWapNotifyUrl.ashx'
//订单通知地址
// var salesOrderNotifyUrl = adminRootUrl + "/api/WXSalesOrderNotify.ashx";
//订单通知地址
var salesOrderNotifyUrl = adminRootUrl + '/api/PCWxPayNotify'
//logo地址
var logoUrl = adminRootUrl + '/img/logo.png'
//用服务器图片地址
var imageWXAppImageUrl = adminRootUrl + '/WXAppImage/'

//是否启用充值功能
var rechargeEnabled = false
//是否启用手机注册功能
var mobilePhoneRegisterEnabled = false
//版本
var version = '1.0.1'
//最后修改时间
var lastUpdateTime = '2023-05-25'
//最后修改时间
var copyright = 'NZW'
//系统名称
var systemName = '总部管理后台'
//系统全名
var fullSystemName = '图书租赁总部管理后台'


//var companyRootUrl = "http://192.168.110.194:5173/";
//小程序ID
// var appId = "wx187605989a266417";// TCJ
// var appId = "wx6c3ae89c6515a4ce";/NZW
var appId = 'wx7adecf259571c888' //BOP
//订阅模板TemplateID
var templateID = ['nz2PgAQ0l1dvb1vlRU8kXu_EtUWGo-yaIEiGTM_yoRU']

//距离计算APIKey
// var distanceCalculation = "5QABZ-LTEWB-IYDUR-J52IT-POMD7-7GBEO"; //zrj
// var distanceCalculation = "Y3DBZ-G5WWQ-BJL5Q-BOLMU-OKOJ3-YQBDA"; //ws
// var distanceCalculation = "F24BZ-DTBWG-ENLQ3-QV2XR-HCU4O-NCBJ3"; //zhs
// var distanceCalculation = "AGXBZ-ENYCV-SCJPG-57UMS-QV3IV-WEFRC"; //qy

var editorConfig = {
  //serverUrl: process.env.VUE_APP_BASE_API + "/config"',
  //**更正代码231109**
  serverUrl: adminRootUrl + '/config',
  UEDITOR_HOME_URL: '/UEditor/',
  //**代码补充231218**
  UEDITOR_CORS_URL: '/UEditor/',
  zIndex: 99999 //设置z轴顺序值，避免工具栏下拉组件被遮挡
}

export default {
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
  systemName: systemName,
  fullSystemName: fullSystemName,
  appId: appId,
  imageAdminRootUrl: imageAdminRootUrl,
  imageWXAppImageUrl: imageWXAppImageUrl,
  salesOrderNotifyUrl: salesOrderNotifyUrl,
  localImageRootUrl: localImageRootUrl,
  isCompany: isCompany,
  editorConfig: editorConfig,
  companyRootUrl: companyRootUrl,
  applicationId:applicationId
}