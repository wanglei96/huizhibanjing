package strosoft.wx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.WxAccessToken;
import strosoft.wx.common.WxClient;
import strosoft.wx.common.WxConfig;
import strosoft.wx.common.WxUser;

/**
 * 取得微信用户信息
 *
 * @author Admin
 */
public class GetWxUserInfoServiceHandler extends ServiceHandler {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        String code = jData.getString("code");
        Tracer.writeLine("code: " + code);
        WxClient theWXClient = new WxClient(WxConfig.getWxAppId(), WxConfig.getWxAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken(code);
        WxUser theWXUser = theWXClient.GetUserInfo(theWXAccessToken.getAccessToken(), theWXAccessToken.getOpenID());
        theWXUser.setUnionID(theWXAccessToken.getUnionID());
        Tracer.writeLine("Unionid: " + theWXUser.getUnionID());
        Tracer.writeLine("WXOpenID: " + theWXUser.getOpenID());
        this.writeSuccessResponse(response, theWXUser);
    }
}
