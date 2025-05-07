package strosoft.wx.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.HttpHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.WxAccessToken;
import strosoft.wx.common.WxClient;
import strosoft.wx.common.WxConfig;

/**
 * 获取小程序二维码
 *
 * @author Admin
 */
public class GetWxQRCodeServiceHandler extends ServiceHandler {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        WxClient newWXClient = new WxClient();
        String scene = JsonHelper.getString(jData, "scene");
        String QRCodeWidth = JsonHelper.getString(jData, "QRCodeWidth");
        WxAccessToken theWXAccessToken = newWXClient.GetAccessToken(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
        String url = String.format("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s",
                theWXAccessToken.getAccessToken());
        JSONObject jDataParameter = new JSONObject();
        jDataParameter.put("scene", scene);
        jDataParameter.put("width", QRCodeWidth);
        String parameters = jDataParameter.toString();
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String relativePath = SysConfig.getUploadDirectory() + "/" + fileName;
        String absoluteFilePath = SysConfig.getAbsoluteUploadDirectory() + "/" + fileName;
        HttpHelper.downloadFilePost(url, absoluteFilePath, parameters);
        String qrcodeUrl = SysConfig.getAdminRootUrl() + relativePath;
        this.writeSuccessResponse(response, qrcodeUrl);
    }
}
