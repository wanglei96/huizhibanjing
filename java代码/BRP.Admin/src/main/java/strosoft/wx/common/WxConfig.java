package strosoft.wx.common;

import java.io.IOException;

import strosoft.app.common.SysConfig;

public class WxConfig {
    // / <summary>
    // / AppID
    // / </summary>
    public static String getWxAppId() throws IOException {
        return SysConfig.getConfig("WxAppId");
    }

    // / <summary>
    // / AppID
    // / </summary>
    public static String getTemplateId() throws IOException {
        return SysConfig.getConfig("WxTemplateId");
    }

    // / <summary>
    // / AppSecret
    // / </summary>
    public static String getWxAppSecret() throws IOException {
        return SysConfig.getConfig("WxAppSecret");
    }

    // / <summary>
    // / 微信小程序ID
    // / </summary>
    public static String getWxaAppId() throws IOException {
        return SysConfig.getConfig("WxaAppId", getWxAppId());
    }

    // / <summary>
    // / 微信小程序密钥
    // / </summary>
    public static String getWxaAppSecret() throws IOException {
        return SysConfig.getConfig("WxaAppSecret", getWxAppSecret());
    }

    // / <summary>
    // / 商户ID
    // / </summary>
    public static String getWxMchId() throws IOException {
        return SysConfig.getConfig("WxMchId", "1228986602");
    }

    // / <summary>
    // / 支付秘钥
    // / </summary>
    public static String getWxMchSecret() throws IOException {
        return SysConfig.getConfig("WxMchSecret",
                "22dcfef7870b72d3b9fc73fde5c8bb9e");
    }

    // / <summary>
    // / 证书路径
    // / </summary>
    public static String getWxCertFilePath() throws IOException {
        return SysConfig
                .getConfig("WxCertFilePath", "cert/apiclient_cert.pem");
    }

    // / <summary>
    // / 微信证书密码
    // / </summary>
    public static String getWxCertPassword() throws IOException {
        return SysConfig.getConfig("WxCertPassword");
    }

    // / <summary>
    // / 小程序类型
    // / </summary>
    public static String getMiniprogramState() throws IOException {
        return SysConfig.getConfig("MiniprogramState");
    }

    // / <summary>
    // / 订阅消息模板ID
    // / </summary>
    public static String getSubscriptMessageTemplateID() throws IOException {
        return SysConfig.getConfig("SubscriptMessageTemplateID");
    }

}
