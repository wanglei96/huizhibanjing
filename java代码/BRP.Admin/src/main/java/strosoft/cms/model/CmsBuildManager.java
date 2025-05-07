package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.data.ViewCmsContentInfo;

/**
 * 生成管理类
 * 
 * @author Administrator
 * 
 */
public class CmsBuildManager {

	// 任务列表
	private ArrayList<CmsBuildTask> lstCmsBuildTask = new ArrayList<CmsBuildTask>();
	private Boolean building = false;
	private static CmsBuildManager _instance;
	private static CmsBuildTaskMonitorThread cbmt;
	// 栏目缓存，每次接受到页面生产请求时刷新
	private List<ViewCmsChannelInfo> viewCmsChannelInfos;

	public static CmsBuildManager getInstance() {
		if (_instance == null) {
			_instance = new CmsBuildManager();
		}
		return _instance;
	}

	private CmsBuildManager() {
	}

	// 执行生成任务
	public void process() throws Exception {
		while (lstCmsBuildTask.size() > 0) {
			try {
				CmsBuildTask theCmsBuildTask = getFirstCmsBuildTask();
				if (null != theCmsBuildTask) {
					CmsBuildHandler theCmsBuildHandler = CmsBuildHandlerFactory
							.getHandler(theCmsBuildTask.getBuildTaskType());
					CmsBuildContext context = new CmsBuildContext();
					context.setCmsBuildTask(theCmsBuildTask);
					theCmsBuildHandler.process(context);
				}
			} catch (Exception e) {
				// 生成遇到异常时，执行下个生成任务
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取生成列表中的第一项，若生成列表中个数为0，返回null
	 * 
	 * @return
	 */
	private synchronized CmsBuildTask getFirstCmsBuildTask() {
		if (lstCmsBuildTask.size() == 0) {
			return null;
		}
		CmsBuildTask theCmsBuildTask = lstCmsBuildTask.get(0);
		lstCmsBuildTask.remove(0);
		return theCmsBuildTask;
	}

	/**
	 * 向lstCmsBuildTask中添加CmsBuildTask
	 */
	public void addCmsBuildTask(CmsBuildTask cmsBuildTask) {
		if (cbmt == null) {
			System.out.println("开启CmsBuildTaskManagerThread...........................");
			cbmt = new CmsBuildTaskMonitorThread();
			Thread t1 = new Thread(cbmt);
			t1.start();
		}
		// 检查是否已存在
		if (!checkIsContainsInLstCmsBuildTask(cmsBuildTask)) {
			lstCmsBuildTask.add(cmsBuildTask);
			System.out.println("不存在");
		} else {
			System.out.println("已存在");
		}

	}

	// 判断页面生成队中是否已存在正在添加的任务
	private boolean checkIsContainsInLstCmsBuildTask(CmsBuildTask cmsBuildTask) {
		for (int i = 0; i < lstCmsBuildTask.size(); i++) {
			if ((lstCmsBuildTask.get(i).getChannelId() == cmsBuildTask.getChannelId())
					&& (lstCmsBuildTask.get(i).getContentId() == cmsBuildTask.getContentId())
					&& (lstCmsBuildTask.get(i).getTemplateId() == cmsBuildTask.getTemplateId())

					&& equalsCmsLanguageCode(lstCmsBuildTask.get(i).getCmsLanguageCode(),
							cmsBuildTask.getCmsLanguageCode())) {
				return true;
			}
		}
		return false;

	}

	// 按照站点查询所选类型的所有模板ID
	public List<Integer> selectTemplateIdListBySiteIdAndType(int siteId, String templateType) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String sql = "select id from cms_template where cms_template_type_code='" + templateType
					+ "' and cms_site_id=" + siteId;
			List<Integer> cmsTemplateIdList = sqlSession.selectList("CmsTemplate.selectIntBySql", sql);
			sqlSession.commit();
			return cmsTemplateIdList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 按照站点查询所选类型的所有栏目ID
	public List<Integer> selectChannelIdListBySiteIdAndType(int siteId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String sql = "select id from cms_Channel where cms_site_id=" + siteId;
			List<Integer> cmsChannelIdList = sqlSession.selectList("CmsChannel.selectIntBySql", sql);
			sqlSession.commit();
			return cmsChannelIdList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 按照站点查询所选类型的所有内容ID
	public List<Integer> selectContentIdListBySiteIdAndType(int siteId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			String sql = "select id from cms_Chontent where cms_site_id=" + siteId;
			List<Integer> cmsChannelIdList = sqlSession.selectList(sql);
			sqlSession.commit();
			return cmsChannelIdList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<CmsBuildTask> getLstCmsBuildTask() {
		return lstCmsBuildTask;
	}

	public void setLstCmsBuildTask(ArrayList<CmsBuildTask> lstCmsBuildTask) {
		this.lstCmsBuildTask = lstCmsBuildTask;
	}

	public Boolean getBuilding() {
		return building;
	}

	public void setBuilding(Boolean building) {
		this.building = building;
	}

	// 格式化html
	public String formatHtml(String html) {
		String newHtml = "";
		Document doc = Jsoup.parse(html);
		newHtml = doc.html();
		newHtml = newHtml.replace("&lt;#text&gt;", "");
		return newHtml;
	}

	public List<ViewCmsChannelInfo> getViewCmsChannelInfos() {
		return viewCmsChannelInfos;
	}

	public void setViewCmsChannelInfos(List<ViewCmsChannelInfo> viewCmsChannelInfos) {
		this.viewCmsChannelInfos = viewCmsChannelInfos;
	}

	/*
	 * public List<ViewCmsContentInfo> getViewCmsContentInfos() { return
	 * viewCmsContentInfos; }
	 * 
	 * public void setViewCmsContentInfos(List<ViewCmsContentInfo>
	 * viewCmsContentInfos) { this.viewCmsContentInfos = viewCmsContentInfos; }
	 */

	public Boolean equalsCmsLanguageCode(String code1, String code2) {
		if (null == code1 && null == code2) {
			return true;
		}
		if (null == code1 || null == code2) {
			return false;
		}
		return code1.equals(code2);
	}

	/**
	 * 通过栏目ID和语言编码获取对应多语言栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelId
	 * @param cmsLanguageCode
	 * @return
	 */
	public ViewCmsChannelInfo getViewCmsChannelInfoByCmsChannelIdAndCode(Integer cmsChannelId, String cmsLanguageCode) {
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer currentCmsChannelId = currentViewCmsChannelInfo.getId();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelId.equals(cmsChannelId)
					&& (equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode))) {
				return currentViewCmsChannelInfo;
			}
		}
		return null;
	}

	/**
	 * 通过栏目Code、语言编码和子栏目名称获取对应的第一个多语言子栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelCode
	 * @param cmsLanguageCode
	 * @return
	 */
	public ViewCmsChannelInfo getSubViewCmsChannelInfoByCmsChannelCodeAndCode(String cmsChannelCode, String name,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		ViewCmsChannelInfo subViewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsChannelCode = currentViewCmsChannelInfo.getCode();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelCode.equals(cmsChannelCode)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		if (null == viewCmsChannelInfo) {
			return null;
		}
		Integer cmsChannelId = viewCmsChannelInfo.getId();
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer currentCmsChannelParentId = currentViewCmsChannelInfo.getParentId();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			String currentCmsLanguageName = currentViewCmsChannelInfo.getName();
			if (currentCmsLanguageName.equals(name) && currentCmsChannelParentId.equals(cmsChannelId)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				subViewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		return subViewCmsChannelInfo;
	}

	/**
	 * 通过栏目Code、语言编码和子栏目名称获取对应的多语言子栏目列表(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelId
	 * @param cmsLanguageCode
	 * @return
	 */
	public List<ViewCmsChannelInfo> getSubViewCmsChannelInfoListByCmsChannelCodeAndCode(String cmsChannelCode,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		List<ViewCmsChannelInfo> subViewCmsChannelInfos = new ArrayList<ViewCmsChannelInfo>();
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsChannelCode = currentViewCmsChannelInfo.getCode();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelCode.equals(cmsChannelCode)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		if (null == viewCmsChannelInfo) {
			return null;
		}
		Integer cmsChannelId = viewCmsChannelInfo.getId();
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer currentCmsChannelParentId = currentViewCmsChannelInfo.getParentId();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelParentId.equals(cmsChannelId)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				subViewCmsChannelInfos.add(currentViewCmsChannelInfo);
			}
		}
		return subViewCmsChannelInfos;
	}

	/**
	 * 通过栏目Id、语言编码和子栏目名称获取对应的多语言子栏目列表(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelId
	 * @param cmsLanguageCode
	 * @return
	 */
	public List<ViewCmsChannelInfo> getSubViewCmsChannelInfoListByCmsChannelIdAndCode(Integer cmsChannelId,
			Integer level, String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer currentCmsChannelId = currentViewCmsChannelInfo.getId();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelId.equals(cmsChannelId)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		if (null == viewCmsChannelInfo) {
			return null;
		}
		List<ViewCmsChannelInfo> viewCmsChannelInfoList = new ArrayList<ViewCmsChannelInfo>();
		viewCmsChannelInfoList.add(viewCmsChannelInfo);
		return getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(viewCmsChannelInfoList, level, cmsLanguageCode);
	}

	/**
	 * 通过栏目列表和level获取子栏目列表
	 * 
	 * @param viewCmsChannelInfoList
	 * @param cmsLanguageCode
	 * @return
	 */
	public List<ViewCmsChannelInfo> getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(
			List<ViewCmsChannelInfo> viewCmsChannelInfoList, Integer level, String cmsLanguageCode) {
		List<ViewCmsChannelInfo> subViewCmsChannelInfos = new ArrayList<ViewCmsChannelInfo>();
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentSubViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer cunrrnParentId = currentSubViewCmsChannelInfo.getParentId();
			for (int j = 0; j < viewCmsChannelInfoList.size(); j++) {
				ViewCmsChannelInfo viewCmsChannelInfo = viewCmsChannelInfoList.get(j);
				Integer id = viewCmsChannelInfo.getId();
				String currentCmsLanguageCode = currentSubViewCmsChannelInfo.getCmsLanguageCode();

				if (cunrrnParentId.equals(id) && equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
					subViewCmsChannelInfos.add(currentSubViewCmsChannelInfo);
				}
			}
		}
		level--;
		if (level > 0 && subViewCmsChannelInfos.size() > 0) {
			subViewCmsChannelInfos = getSubViewCmsChannelInfoListByCmsChannelIdAndLevel(subViewCmsChannelInfos, level,
					cmsLanguageCode);
		}
		return subViewCmsChannelInfos;
	}

	/**
	 * 通过栏目Code和语言编码获取对应多语言栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelCode
	 * @param cmsLanguageCode
	 * @return
	 */
	public ViewCmsChannelInfo getViewCmsChannelInfoByCmsChannelCodeAndCode(String cmsChannelCode,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsChannelCode = currentViewCmsChannelInfo.getCode();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelCode.equals(cmsChannelCode)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		return viewCmsChannelInfo;
	}

	/**
	 * 通过栏目Name和语言编码获取对应多语言栏目(包括cmsLanguageCode为null),返回第一个
	 * 
	 * @param cmsChannelName
	 * @param cmsLanguageCode
	 * @return
	 */
	public ViewCmsChannelInfo getViewCmsChannelInfoByCmsChannelNameAndCode(String cmsChannelName,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsChannelName = currentViewCmsChannelInfo.getName();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsChannelName != null && currentCmsChannelName.equals(cmsChannelName)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		return viewCmsChannelInfo;
	}

	/**
	 * 通过栏目Id、语言编码和上级获取对应多语言栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelId
	 * @param level
	 * @return
	 */
	public ViewCmsChannelInfo getViewCmsChannelInfoByLevelAndId(Integer cmsChannelId, Integer level,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			Integer currentCmsId = currentViewCmsChannelInfo.getId();
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			if (currentCmsId.equals(cmsChannelId) && equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		level--;
		Integer parentId = viewCmsChannelInfo.getParentId();
		if (level > -1 && parentId != 0) {
			viewCmsChannelInfo = getViewCmsChannelInfoByLevelAndId(parentId, level, cmsLanguageCode);
		}
		if (level > -1 && parentId == 0) {
			return null;
		}
		return viewCmsChannelInfo;
	}

	/**
	 * 通过栏目Code、语言编码和上级获取对应多语言栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsChannelId
	 * @param level
	 * @return
	 */
	public ViewCmsChannelInfo getViewCmsChannelInfoByLevelAndId(String cmsChannelCode, Integer level,
			String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			String currentViewCmsChannelCode = currentViewCmsChannelInfo.getCode();
			if (currentViewCmsChannelCode.equals(cmsChannelCode)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		Integer id = viewCmsChannelInfo.getId();
		viewCmsChannelInfo = getViewCmsChannelInfoByLevelAndId(id, level, cmsLanguageCode);
		return viewCmsChannelInfo;
	}

	/**
	 * 通过站点ID、语言编码获取多语言根栏目(包括cmsLanguageCode为null)
	 * 
	 * @param cmsSiteId
	 * @param cmsLanguageCode
	 * @return
	 */
	public ViewCmsChannelInfo getRootViewCmsChannelInfoByCmsSiteId(Integer cmsSiteId, String cmsLanguageCode) {
		ViewCmsChannelInfo viewCmsChannelInfo = null;
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo currentViewCmsChannelInfo = viewCmsChannelInfos.get(i);
			String currentCmsLanguageCode = currentViewCmsChannelInfo.getCmsLanguageCode();
			Integer currentCmsSiteId = currentViewCmsChannelInfo.getCmsSiteId();
			Integer currentParentId = currentViewCmsChannelInfo.getParentId();
			if (currentParentId.equals(0) && currentCmsSiteId.equals(cmsSiteId)
					&& equalsCmsLanguageCode(cmsLanguageCode, currentCmsLanguageCode)) {
				viewCmsChannelInfo = currentViewCmsChannelInfo;
				break;
			}
		}
		return viewCmsChannelInfo;
	}

	/**
	 * 通过查询条件HashMap获取栏目列表
	 * 
	 * @param conditionMap
	 * @param cmsLanguageCode
	 * @return
	 */
	public List<ViewCmsChannelInfo> getViewCmsChannelInfoListByConditionMap(HashMap<String, Object> conditionMap,
			String cmsLanguageCode) {
		List<ViewCmsChannelInfo> viewCmsChannelInfoList = new ArrayList<ViewCmsChannelInfo>();
		for (int i = 0; i < viewCmsChannelInfos.size(); i++) {
			ViewCmsChannelInfo viewCmsChannelInfo = viewCmsChannelInfos.get(i);
			if (conditionMap.containsKey("idList")) {
				Integer currentId = viewCmsChannelInfo.getId();
				List<Integer> idList = (List<Integer>) conditionMap.get("idList");
				if (!compareByIntegerList(currentId, idList)) {
					continue;
				}
			}
			if (conditionMap.containsKey("nameList")) {
				String currentName = viewCmsChannelInfo.getName();
				List<String> nameList = (List<String>) conditionMap.get("nameList");
				if (!compareByStringList(currentName, nameList)) {
					continue;
				}
			}
			if (conditionMap.containsKey("codeList")) {
				String currentCode = viewCmsChannelInfo.getCode();
				List<String> codeList = (List<String>) conditionMap.get("codeList");
				if (!compareByStringList(currentCode, codeList)) {
					continue;
				}
			}
			if (conditionMap.containsKey("idAreaList")) {
				Integer currentId = viewCmsChannelInfo.getId();
				List<Integer> idAreaList = (List<Integer>) conditionMap.get("idAreaList");
				if (!compareByIntAreaList(currentId, idAreaList)) {
					continue;
				}
			}
			if (null != cmsLanguageCode) {
				String currentCmsLanguageCode = viewCmsChannelInfo.getCmsLanguageCode();
				if (!cmsLanguageCode.equals(currentCmsLanguageCode)) {
					continue;
				}
			}
			viewCmsChannelInfoList.add(viewCmsChannelInfo);
		}
		return viewCmsChannelInfoList;
	}

	public Boolean compareByIntAreaList(Integer compareInt, List<Integer> intArearList) {
		if (null == compareInt) {
			return false;
		}
		if (null == intArearList) {
			return true;
		}
		return intArearList.contains(compareInt);
	}

	public Boolean compareByStringList(String compareStr, List<String> strList) {
		if (null == compareStr) {
			return false;
		}
		for (int i = 0; i < strList.size(); i++) {
			String currentStr = strList.get(i);
			if (null == currentStr) {
				return false;
			}
			if (!compareStr.equals(currentStr)) {
				return false;
			}
		}
		return true;
	}

	public Boolean compareByIntegerList(Integer compareInt, List<Integer> intList) {
		if (null == compareInt) {
			return false;
		}
		for (int i = 0; i < intList.size(); i++) {
			Integer currentInt = intList.get(i);
			if (null == currentInt) {
				return false;
			}
			if (!compareInt.equals(currentInt)) {
				return false;
			}
		}
		return true;
	}

	public List<Integer> getCmsChannelLanguageIdsInfoIdsBySql(SqlSession sqlSession, String cmsLanguageCode,
			String condition, String orderBy, String limit) {
		String tableName = null;
		String cmsLanguageCondition = "";
		String orderByCondition = "";
		String limitCondition = "";
		if (null == cmsLanguageCode) {
			tableName = " view_cms_channel ";
		} else {
			tableName = " view_cms_channel_language ";
			cmsLanguageCondition = " and cms_language_code='" + cmsLanguageCode + "' ";
		}
		if (null != orderBy && !orderBy.equals("")) {
			orderByCondition = "order by " + orderBy;
		}
		if (null != limit) {
			limitCondition = limit;
		}
		String sql = "select id from " + tableName + condition + cmsLanguageCondition + orderByCondition
				+ limitCondition;
		return sqlSession.selectList("ViewCmsChannel.selectIdListBysql", sql);
	}

	/**
	 * 通过内容ID和语言编码获取对应多语言内容(包括cmsLanguageCode为null)
	 * 
	 * @param sqlSession
	 * @param cmsBuildTask
	 * @param cmsContentId
	 * @param cmsLanguageCode
	 * @return
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public ViewCmsContentInfo getViewCmsContentInfoByCmsContentIdAndCode(SqlSession sqlSession, Integer cmsSiteId,
			Integer cmsContentId, String cmsLanguageCode)
			throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, SecurityException, NoSuchMethodException {
		ViewCmsContentInfo viewCmsContentInfo = null;
		viewCmsContentInfo = ViewCmsContentInfoManager.getInstance().getViewCmsContentInfoByCmsContentId(sqlSession,
				cmsSiteId, cmsContentId, cmsLanguageCode);
		return viewCmsContentInfo;
	}

}
