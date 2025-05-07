package strosoft.wx.common;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.util.HttpHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.Tracer;

public class WxClient {
    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
    }

    public String getAppSecret() {
        return AppSecret;
    }

    public void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    /**
     * 微信APPID
     */
    public String AppID;
    /**
     * 微信应用密钥
     */
    public String AppSecret;
    /**
     * 访问令牌
     */
    public String AccessToken;

    public WxClient() {
    }

    public WxClient(String AppID, String AppSecret) {
        this.AppID = AppID;
        this.AppSecret = AppSecret;
    }

    // / <summary>
    // / 取得访问令牌
    // / </summary>
    // / <returns></returns>
    public WxAccessToken GetAccessToken() throws Exception {
        return GetAccessToken(this.AppID, this.AppSecret);
    }

    // / <summary>
    // / 取得访问令牌
    // / </summary>
    // / <param name="AppID">第三方用户唯一凭证</param>
    // / <param name="AppSecret">第三方用户唯一凭证密钥</param>
    // / <returns></returns>
    public WxAccessToken GetAccessToken(String AppID, String AppSecret) throws Exception {
        String url = String.format(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", AppID,
                AppSecret);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        WxAccessToken theGetAccessTokenResponse = new WxAccessToken();
        theGetAccessTokenResponse.setAccessToken(JsonHelper.getString(jResult, "access_token"));
        theGetAccessTokenResponse.setExpiresIn(JsonHelper.getInt(jResult, "expires_in"));
        setAccessToken(theGetAccessTokenResponse.getAccessToken());
        return theGetAccessTokenResponse;
    }

    // / <summary>
    // / 获取微信服务器IP地址
    // / </summary>
    // / <returns></returns>
    public ArrayList<String> GetCallbackIP() throws Exception {
        return GetCallbackIP(this.AccessToken);
    }

    // / <summary>
    // / 获取微信服务器IP地址
    // / </summary>
    // / <param name="accessToken"></param>
    // / <returns></returns>
    public ArrayList<String> GetCallbackIP(String accessToken) throws Exception {
        if (accessToken == null || accessToken.equals("")) {
            throw new Exception("accessToken参数不能为空！");
        }
        String url = String.format("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s", accessToken);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        JSONArray jarrIP = jResult.getJSONArray("ip_list");
        ArrayList<String> lstIP = new ArrayList<String>();
        for (Object jIP : jarrIP) {
            String IP = (String) jIP;
            lstIP.add(IP);
        }
        return lstIP;
    }

    /**
     * 取得AccessToken
     *
     * @param code
     * @return
     * @throws Exception
     */
    public WxAccessToken GetAccessToken(String code) throws Exception {

        String url = String.format(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                this.AppID, this.AppSecret, code);
        String result = HttpHelper.get(url);
        Tracer.writeLine("result: " + result);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        WxAccessToken newWXResultGetAccessToken = new WxAccessToken();
        newWXResultGetAccessToken.setAccessToken(jResult.getString("access_token"));
        newWXResultGetAccessToken.setOpenID(jResult.getString("openid"));
        newWXResultGetAccessToken.setRefreshToken(jResult.getString("refresh_token"));
        newWXResultGetAccessToken.setScope(jResult.getString("scope"));
        newWXResultGetAccessToken.setExpiresIn(jResult.getInt("expires_in"));
        newWXResultGetAccessToken.setUnionID(jResult.getString("unionid"));
        return newWXResultGetAccessToken;
    }

    /**
     * 取得用户信息
     *
     * @param accessToken
     * @param openID
     * @return
     * @throws Exception
     */
    public WxUser GetUserInfo(String accessToken, String openID) throws Exception {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", accessToken,
                openID);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        WxUser newWXUser = new WxUser();
        // 普通用户的标识，对当前开发者账号唯一
        newWXUser.setOpenID(jResult.getString("openid"));
        // 普通用户昵称
        newWXUser.setNickname(jResult.getString("nickname"));
        // 性别
        newWXUser.setSex(jResult.getString("sex"));
        // 省
        newWXUser.setProvince(jResult.getString("province"));
        // 市
        newWXUser.setCity(jResult.getString("city"));
        // 普通用户昵称
        newWXUser.setCountry(jResult.getString("country"));
        // 头像地址
        newWXUser.setHeadImgUrl(jResult.getString("headimgurl"));
        // 普通用户昵称
        if (jResult.containsKey("privilege")) {
            newWXUser.Privileges = new ArrayList<String>();
            JSONArray jarrPrivilege = jResult.getJSONArray("privilege");
            for (Object objPrivilege : jarrPrivilege) {
                String strPrivilege = (String) objPrivilege;
                newWXUser.getPrivileges().add(strPrivilege);
            }
        }
        return newWXUser;
    }

    /**
     * 检查结果
     *
     * @param jResult
     * @throws Exception
     */
    public void CheckResponse(JSONObject jResult) throws Exception {
        String errorCode = JsonHelper.getString(jResult, "errcode");
        if (errorCode != null && !errorCode.equals("") && !errorCode.equals("0")) {
            String errorMessage = JsonHelper.getString(jResult, "errmsg");
            Tracer.writeLine("errorMessage: " + errorMessage);
            throw new Exception(errorMessage);
        }
    }

    /**
     * 设置行业
     *
     * @param accessToken
     * @param industryID1
     * @param industryID2
     * @throws Exception
     */
    public void SetIndustry(String accessToken, String industryID1, String industryID2) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s",
                accessToken);
        JSONObject jParameters = new JSONObject();
        jParameters.put("industry_id1", industryID1);
        jParameters.put("industry_id2", industryID2);
        String jsonParameters = jParameters.toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
    }

    /**
     * 取得行业信息
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    public WxIndustryInfo GetIndustry(String accessToken) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s",
                accessToken);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        WxIndustryInfo newWXIndustryInfo = new WxIndustryInfo();
        JSONObject jPrimaryIndustry = jResult.getJSONObject("primary_industry");
        newWXIndustryInfo.PrimaryIndustry = this.GetIndustry(jPrimaryIndustry);
        JSONObject jSecondaryIndustry = jResult.getJSONObject("secondary_industry");
        newWXIndustryInfo.SecondaryIndustry = this.GetIndustry(jSecondaryIndustry);
        return newWXIndustryInfo;
    }

    /**
     * 获取行业
     *
     * @param jWXIndustry
     * @return
     */
    private WxIndustry GetIndustry(JSONObject jWXIndustry) {
        WxIndustry newWXIndustry = new WxIndustry();
        newWXIndustry.FirstClass = (String) jWXIndustry.getString("first_class");
        newWXIndustry.SecondClass = (String) jWXIndustry.getString("second_class");
        return newWXIndustry;
    }

    /**
     * 添加模板
     *
     * @param accessToken
     * @param templateNo  模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @return
     * @throws Exception
     */
    public String AddTemplate(String accessToken, String templateNo) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s",
                accessToken);
        JSONObject jParameters = new JSONObject();
        jParameters.put("template_id_short", templateNo);
        String jsonParameters = jParameters.toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        String templateID = jResult.getString("template_id");
        return templateID;
    }

    // / <summary>
    // / 取得模板列表
    // / </summary>
    // / <param name="accessToken"></param>
    // / <returns></returns>
    public ArrayList<WxTemplate> GetTemplateList(String accessToken) throws Exception {
        String url = String.format(
                "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s", accessToken);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        ArrayList<WxTemplate> lstTemplate = new ArrayList<WxTemplate>();
        JSONArray jarrTemplate = jResult.getJSONArray("template_list");
        for (Object objTemplate : jarrTemplate) {
            JSONObject jTemplate = (JSONObject) objTemplate;
            WxTemplate newWXTemplate = new WxTemplate();
            // 模板ID
            newWXTemplate.setTemplateID(jTemplate.getString("template_id"));
            // 模板标题
            newWXTemplate.setTitle(jTemplate.getString("title"));
            // 模板所属行业的一级行业
            newWXTemplate.setPrimaryIndustry(jTemplate.getString("primary_industry"));
            // 模板所属行业的二级行业
            newWXTemplate.setDeputyIndustry(jTemplate.getString("deputy_industry"));
            // 模板内容
            newWXTemplate.setContent(jTemplate.getString("content"));
            // 模板示例
            newWXTemplate.setExample(jTemplate.getString("example"));
            lstTemplate.add(newWXTemplate);
        }
        return lstTemplate;
    }

    /**
     * 删除模板
     *
     * @param accessToken
     * @param templateID
     * @throws Exception
     */
    public void DeleteTemplate(String accessToken, String templateID) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s",
                accessToken);
        JSONObject jParameters = new JSONObject();
        jParameters.put("template_id", templateID);
        String jsonParameters = jParameters.toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
    }

    /**
     * 发送模板消息
     *
     * @param accessToken
     * @param message
     * @return
     * @throws Exception
     */
    public String SendTempateMessage(String accessToken, WxTemplateMessage message) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",
                accessToken);
        String jsonParameters = message.ToJsonObject().toString();
        String result = HttpHelper.post(url, jsonParameters);
        Tracer.writeLine("消息结果: " + result);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        String messageID = jResult.getString("msgid");
        return messageID;
    }

    /**
     * 发送客服消息
     *
     * @param accessToken
     * @param message
     * @throws Exception
     */
    public void SendCustomerServiceMessage(String accessToken, WxCustomerServiceMessage message) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s",
                accessToken);
        String jsonParameters = message.ToJsonObject().toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
    }

    /**
     * 发送订阅消息
     *
     * @param accessToken
     * @param message
     * @throws Exception
     */
    public void SendSubscribeMessage(String accessToken, WxSubscribeMessage message) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s",
                accessToken);
        String jsonParameters = message.ToJsonObject().toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
    }

    /**
     * 发送订阅消息
     *
     * @param accessToken
     * @param message
     * @return
     * @throws Exception
     */
    public JSONObject SendWxaTempateMessage(String accessToken, WxaSubscribeMessage message) throws Exception {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s",
                accessToken);
        String jsonParameters = message.ToJsonObject().toString();
        String result = HttpHelper.post(url, jsonParameters);
        JSONObject jResult = JSONObject.fromObject(result);
        return jResult;
        //this.CheckResponse(jResult);
    }

    /**
     * code 换取 session_key
     *
     * @param code
     * @return
     * @throws Exception
     */
    public WxJsCode2SessionResponse JsCode2Session(String code) throws Exception {
        return JsCode2Session(this.AppID, this.AppSecret, code);
    }

    /**
     * code 换取 session_key
     *
     * @param appID
     * @param appSecret
     * @param code
     * @return
     * @throws Exception
     */
    public WxJsCode2SessionResponse JsCode2Session(String appID, String appSecret, String code) throws Exception {
        String url = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                appID, appSecret, code);
        String result = HttpHelper.get(url);
        JSONObject jResult = JSONObject.fromObject(result);
        this.CheckResponse(jResult);
        WxJsCode2SessionResponse newWXJsCode2SessionResponse = new WxJsCode2SessionResponse();
        newWXJsCode2SessionResponse.setOpenID(JsonHelper.getString(jResult, "openid"));
        newWXJsCode2SessionResponse.setSessionKey(JsonHelper.getString(jResult, "session_key"));
        newWXJsCode2SessionResponse.setUnionID(JsonHelper.getString(jResult, "unionid"));
        return newWXJsCode2SessionResponse;
    }

}
