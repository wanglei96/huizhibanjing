package strosoft.wx.common;

public class WxJsCode2SessionResponse {
    /// <summary>
    /// 用户唯一标识
    /// </summary>
    public String openID;
    /// <summary>
    /// 会话密钥
    /// </summary>
    public String sessionKey;
    /// <summary>
    /// 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。
    /// </summary>
    public String unionID;
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getUnionID() {
		return unionID;
	}
	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}
}
