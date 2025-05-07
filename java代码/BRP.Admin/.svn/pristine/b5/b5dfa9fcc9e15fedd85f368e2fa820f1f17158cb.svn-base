package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ClassHelper;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentInfo;
import strosoft.cms.data.ViewCmsContentLanguage;

public class ViewCmsContentInfoManager extends IdDataManager {

	private static ViewCmsContentInfoManager _instance;

	public static ViewCmsContentInfoManager getInstance() {
		if (_instance == null) {
			_instance = new ViewCmsContentInfoManager();
		}
		return _instance;
	}

	// 获取多语言栏目输出列表；
	public HashMap<String, ViewCmsContentInfo> getViewCmsContentInfoListByCmsContentId(SqlSession sqlSession,
			Integer cmsContentId, Integer cmsSiteId)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		String condition = "id = " + cmsContentId;
		return getViewCmsContentInfoListByCondition(sqlSession, condition, cmsSiteId);
	}

	/**
	 * 获取多语言栏目输出列表
	 * 
	 * @param sqlSession
	 * @param condition
	 * @return
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public HashMap<String, ViewCmsContentInfo> getViewCmsContentInfoListByCondition(SqlSession sqlSession,
			String condition, Integer cmsSiteId) throws IOException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException {
		HashMap<String, ViewCmsContentInfo> viewCmsChannelInfoMap = new HashMap<String, ViewCmsContentInfo>();
		// 判断是否有多语言支持
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if (isOnCmslaunguage) {
			List<ViewCmsContentLanguage> viewCmsContentLanguages = ViewCmsContentLanguageManager.getInstance()
					.getList(sqlSession, condition);
			for (int i = 0; i < viewCmsContentLanguages.size(); i++) {
				ViewCmsContentInfo viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper
						.copyObject(ViewCmsContentInfo.class, viewCmsContentLanguages.get(i));
				viewCmsChannelInfoMap.put(viewCmsContentInfo.getCmsLanguageCode(), viewCmsContentInfo);
			}
		} else {
			List<ViewCmsContent> lstViewCmsContent = ViewCmsContentManager.getInstance().getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsContent.size(); i++) {
				ViewCmsContentInfo viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper
						.copyObject(ViewCmsContentInfo.class, lstViewCmsContent.get(i));
				viewCmsChannelInfoMap.put("Default", viewCmsContentInfo);
			}
		}
		return viewCmsChannelInfoMap;
	}

	public List<ViewCmsContentInfo> getViewCmsContentInfoList(Integer cmsSiteId) throws IOException {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			return getViewCmsContentInfoList(sqlSession, cmsSiteId);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public List<ViewCmsContentInfo> getViewCmsContentInfoList(SqlSession sqlSession, Integer cmsSiteId)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		List<ViewCmsContentInfo> ViewCmsContentInfoList = new ArrayList<ViewCmsContentInfo>();
		String condition = "1=1";
		// 判断是否有多语言支持
		Boolean isOnCmslaunguage = CmsLanguageManager.getInstance().getIsOnCmslaunguage(cmsSiteId);
		if (isOnCmslaunguage) {
			List<ViewCmsContentLanguage> lstViewCmsContentLanguage = ViewCmsContentLanguageManager.getInstance()
					.getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsContentLanguage.size(); i++) {
				ViewCmsContentInfo viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper
						.copyObject(ViewCmsContentInfo.class, lstViewCmsContentLanguage.get(i));
				ViewCmsContentInfoList.add(viewCmsContentInfo);
			}
		} else {
			List<ViewCmsContent> lstViewCmsContent = ViewCmsContentManager.getInstance().getList(sqlSession, condition);
			for (int i = 0; i < lstViewCmsContent.size(); i++) {
				ViewCmsContentInfo viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper
						.copyObject(ViewCmsContentInfo.class, lstViewCmsContent.get(i));
				ViewCmsContentInfoList.add(viewCmsContentInfo);
			}
		}
		return ViewCmsContentInfoList;
	}

	public ViewCmsContentInfo getViewCmsContentInfoByCmsContentId(SqlSession sqlSession, Integer cmsSiteId,
			Integer cmsContentId, String cmsLanguageCode)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		ViewCmsContentInfo viewCmsContentInfo = null;
		if (null != cmsLanguageCode) {
			String condition = "id = " + cmsContentId + " and cms_language_code = '" + cmsLanguageCode + "'";
			List<ViewCmsContentLanguage> viewCmsContentLanguages = ViewCmsContentLanguageManager.getInstance()
					.getList(sqlSession, condition);
			if (viewCmsContentLanguages.size() > 0) {
				viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper.copyObject(viewCmsContentInfo.getClass(),
						viewCmsContentLanguages.get(0));
			}
		} else {
			String condition = "id = " + cmsContentId;
			List<ViewCmsContent> viewCmsContents = ViewCmsContentManager.getInstance().getList(sqlSession, condition);
			if (viewCmsContents.size() > 0) {
				viewCmsContentInfo = (ViewCmsContentInfo) ClassHelper.copyObject(viewCmsContentInfo.getClass(),
						viewCmsContents.get(0));
			}
		}

		return viewCmsContentInfo;
	}

	public ViewCmsContentInfo getViewCmsContentInfoByCmsContentId(Integer cmsSiteId, Integer cmsContentId,
			String cmsLanguageCode) {
		SqlSession sqlSession = null;
		ViewCmsContentInfo viewCmsContentInfo = null;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			viewCmsContentInfo = getViewCmsContentInfoByCmsContentId(sqlSession, cmsSiteId, cmsContentId,
					cmsLanguageCode);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return viewCmsContentInfo;
	}
}
