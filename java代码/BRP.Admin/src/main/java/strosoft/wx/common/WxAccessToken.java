package strosoft.wx.common;

public class WxAccessToken {
    /// <summary>
    /// 获取到的凭证
    /// </summary>
    private  String AccessToken;
    public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public int getExpiresIn() {
		return ExpiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		ExpiresIn = expiresIn;
	}
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
	public String getScope() {
		return Scope;
	}
	public void setScope(String scope) {
		Scope = scope;
	}
	public String getRefreshToken() {
		return RefreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}
	/// <summary>
    /// 凭证有效时间，单位：秒
    /// </summary>
    private int ExpiresIn;
    /// <summary>
    /// 授权用户唯一标识
    /// </summary>
    private String OpenID;
    /// <summary>
    /// 用户授权的作用域，使用逗号（,）分
    /// </summary>
    private String Scope;
    /// <summary>
    /// 用户刷新access_token
    /// </summary>
    private String RefreshToken;

	private String UnionID;

	public String getUnionID() {
		return UnionID;
	}

	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
}
