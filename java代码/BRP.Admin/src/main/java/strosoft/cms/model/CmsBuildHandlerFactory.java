package strosoft.cms.model;

/**
 * 生成处理工厂类
 * 
 * @author Administrator
 * 
 */
public class CmsBuildHandlerFactory {

	public static CmsBuildHandler getHandler(String buildHandlerType) {
		if (buildHandlerType.equals("Home")) {
			return new CmsIndexBuildHandler();
		} else if (buildHandlerType.equals("Channel")) {
			return new CmsChannelBuildHandler();
		} else if (buildHandlerType.equals("Content")) {
			return new CmsContentBuildHandler();
		} else {
			return new CmsCustomBuildHandler();
		}
	}
}
