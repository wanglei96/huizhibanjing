package strosoft.wx.common;

/**
 * 微信行业信息
 * 
 * @author dl
 * 
 */
public class WxIndustryInfo {
	// / <summary>
	// / 主行业
	// / </summary>
	public WxIndustry PrimaryIndustry;
	// / <summary>
	// / 副行业
	// / </summary>
	public WxIndustry SecondaryIndustry;

	public WxIndustry getPrimaryIndustry() {
		return PrimaryIndustry;
	}

	public void setPrimaryIndustry(WxIndustry primaryIndustry) {
		PrimaryIndustry = primaryIndustry;
	}

	public WxIndustry getSecondaryIndustry() {
		return SecondaryIndustry;
	}

	public void setSecondaryIndustry(WxIndustry secondaryIndustry) {
		SecondaryIndustry = secondaryIndustry;
	}
}
