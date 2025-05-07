package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.cms.data.CmsAudioContext;
import strosoft.cms.data.CmsImageContext;
import strosoft.cms.data.CmsStrAContext;
import strosoft.cms.data.CmsVideoContext;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsChannelInfo;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentInfo;

public class CmsTemplateTagAHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;
	private CmsHtmlGenerateContext con;
	private String hreftext = "";
	private Integer id;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
		con = context;
		StringBuffer content = new StringBuffer();
		Node currentNode = context.getCurrentNode();
		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);
		String text = getNodeContext(currentNode, context, cmsBuildContext);
		if(text.equals("")){
			text = currentNode.getTextContent();
		}
		id = context.getCurrentChannelId();
		// 处理attribute
		CmsStrAContext stra = new CmsStrAContext(attributeCondition);
		// 获取没有解析的属性值
		String originalAttribute = getOriginalAttribute(attributeCondition);

		if (null != context.getCurrentShowType()
				&& context.getCurrentShowType().equalsIgnoreCase("content") && null == stra.getChannelCode()&& null == stra.getChannelIndex()&& null == stra.getChannelName()) {
			String hrefok = "";
			String href = "";
			SqlSession sqlSession = cmsBuildContext.getSqlSession();
			try {
				/*ViewCmsContent viewContent = sqlSession.selectOne(
						"selectViewCmsContentById",
						context.getCurrentContentId());
				System.out.println(context.getCurrentContentId());*/
				ViewCmsContentInfo viewCmsContentInfo = CmsBuildManager.getInstance().getViewCmsContentInfoByCmsContentIdAndCode(sqlSession, context.getCmsSiteId(), context.getCurrentContentId(), context.getCmsLanguageCode());
				href = cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
						+ viewCmsContentInfo.getCreateFilePath();
				// String test=viewContent.
				//System.out.println(viewContent.getTitle());
				String contextgetTarget = "";
				if (stra.getTarget() != null ) {
					contextgetTarget = " target='" + stra.getTarget() + "'";
				}
				if (text != null && !text.equals("")) {
					hrefok += " <a href='" + href + "'" + contextgetTarget
							+ originalAttribute + "  >" + text + "</a>";
				} else {
					hrefok += " <a href='" + href + "'" + originalAttribute
							+ ">" + viewCmsContentInfo.getTitle() + "</a>";
				}
				//System.out.println(viewContent);
				content.append(hrefok);
				return content.toString();
			} catch (Exception e) {

			}
		}
		try {
			String AllAttribute=FunctionAllAttribute(stra,con,cmsBuildContext);
			//cmsBuildContext.getCmsBuildTask().getAdminRootUrl()+con.getChannelUrl();
			NodeList nlChildren = currentNode.getChildNodes();
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				//生成子节点html
				String childStr =(createChildNodeHtml(nlChildren, cmsHtmlGenerateContext,cmsBuildContext));
				if(!(null == childStr || childStr.equals(""))){
					hreftext = childStr;
				}
			String href=" <a "+AllAttribute+originalAttribute+">"+hreftext+"</a>";
			//System.out.println(href);
			content.append(href);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	private String getNodeContext(Node currentNode,CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
			String text = "";
		try {
			NodeList nlChildren = currentNode.getChildNodes();
			CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
			// 生成子节点html
			String childStr = (createChildNodeHtml(nlChildren,
					cmsHtmlGenerateContext, cmsBuildContext));
			if (!(null == childStr || childStr.equals(""))) {
				text = childStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	private String FunctionAllAttribute(CmsStrAContext stra,
			CmsHtmlGenerateContext con, CmsBuildContext cmsBuildContext)
			throws IOException {
		String result = "";
		SqlSession session = cmsBuildContext.getSqlSession();
			// index不为空
			if (stra.getChannelIndex() != null && stra.getChannelName() == null
					&& stra.getHref() == null) {
				/*String sql = "SELECT * FROM  view_cms_channel WHERE CODE='"
						+ stra.getChannelIndex() + "'";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelCodeAndCode(stra.getChannelIndex(), con.getCmsLanguageCode());
				result = " href='"
						+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
						+ viewCmsChannelInfo.getCreateFilePath() + "' ";
				if (hreftext.equals("")) {
					hreftext = viewCmsChannelInfo.getName();
				}
				//System.out.println(result);
			}
			if (stra.getChannelCode() != null && stra.getChannelName() == null
					&& stra.getHref() == null) {
			/*	String sql = "SELECT * FROM  view_cms_channel WHERE CODE='"
						+ stra.getChannelCode() + "'";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelCodeAndCode(stra.getChannelCode(), con.getCmsLanguageCode());

				result = " href='"
						+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
						+ viewCmsChannelInfo.getCreateFilePath() + "' ";
				if (hreftext.equals("")) {
					hreftext = viewCmsChannelInfo.getName();
				}
				//System.out.println(result);
			}
			// name不为空
			if (stra.getChannelIndex() == null && stra.getChannelName() != null
					&& stra.getHref() == null) {
				/*String sql = "SELECT * FROM  view_cms_channel WHERE name='"
						+ stra.getChannelName() + "'";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelNameAndCode(stra.getChannelName(),  con.getCmsLanguageCode());
				result = " href='"
						+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
						+ viewCmsChannelInfo.getCreateFilePath() + "' ";
				if (hreftext.equals("")) {
					hreftext = viewCmsChannelInfo.getName();
				}
				//System.out.println(result);
			}
			if (stra.getTarget() != null) {
				result += " target='" + stra.getTarget() + "' ";
			}
			//
			if (stra.getParent().equals("true")
					&& stra.getChannelName() == null
					&& stra.getChannelIndex() == null && stra.getHref() == null) {
				// SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id
				// FROM view_cms_channel WHERE CODE='cm')
				/*String sql = "SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id FROM  view_cms_channel WHERE id='"
						+ id + "')";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByLevelAndId(id, 1, con.getCmsLanguageCode());
				if (viewCmsChannelInfo != null) {
					result = " href='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl()
							+ viewCmsChannelInfo.getCreateFilePath() + "' ";
					//System.out.println(result);
				}
			}
			if (!stra.getParent().equals("true")
					&& stra.getChannelIndex() == null
							&& stra.getChannelCode() == null
					&& stra.getChannelName() == null && stra.getHref() == null) {
				// SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id
				// FROM view_cms_channel WHERE CODE='cm')
				/*String sql = "SELECT * FROM  view_cms_channel WHERE id='" + id
						+ "'";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo viewCmsChannelInfo = CmsBuildManager.getInstance().getViewCmsChannelInfoByCmsChannelIdAndCode(id, con.getCmsLanguageCode());
				if (viewCmsChannelInfo.getCreateFilePath() != null) {
					result = " href='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl()
							+ viewCmsChannelInfo.getCreateFilePath() + "' ";
					//System.out.println(result);
				}
			}
			if (stra.getChannelIndex() != null && stra.getChannelName() != null && stra.getChannelCode() != null
					&& stra.getHref() == null) {
				/*String sql = "SELECT * FROM view_cms_channel  WHERE name='"
						+ stra.getChannelName()
						+ "' AND parent_id= ( SELECT id FROM view_cms_channel WHERE CODE='"
						+ stra.getChannelIndex() + "')";
				ViewCmsChannel viewch = session.selectOne(
						"ViewCmsChannel.selectEntityBysql", sql);*/
				ViewCmsChannelInfo subViewCmsChannelInfo = CmsBuildManager.getInstance().getSubViewCmsChannelInfoByCmsChannelCodeAndCode(stra.getChannelIndex(), stra.getChannelName(), con.getCmsLanguageCode());
				if (subViewCmsChannelInfo.getCreateFilePath() != null) {
					result = " href='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl()
							+ subViewCmsChannelInfo.getCreateFilePath() + "' ";
					//System.out.println(result);
					hreftext = stra.getChannelName();
				}
			}
			if (stra.getHref() != null) {
				result = " href='"
						+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
						+ stra.getHref() + "' ";
			}
			// SELECT * FROM view_cms_channel WHERE CODE='cm' AND parent_id= (
			// SELECT id FROM view_cms_channel WHERE CODE='homepage')

			return result;
	}

	/*
	 * 根据节点属性名称和值进行返回数据
	 */
/*	public String getSqlCondition(SqlSession sqlSession,
			CmsImageContext imgContext, CmsHtmlGenerateContext context) {
		StringBuffer condition = new StringBuffer(" where 1=1");
		// 判断有没有设置栏目模板
		boolean falg = false;
		// 获取栏目id
		// channelCode
		if (!(imgContext.getChannelCode() == null)) {
			condition
					.append(" and code ='" + imgContext.getChannelCode() + "'");
			attributeCondition.remove("channelcode");
			falg = true;
		}
		// channelIndex
		if (!(imgContext.getChannelIndex() == null)) {
			condition.append(" and code ='" + imgContext.getChannelIndex()
					+ "'");
			attributeCondition.remove("channelindex");
			falg = true;
		}

		// channelName
		if (!(imgContext.getChannelName() == null)) {
			condition
					.append(" and name ='" + imgContext.getChannelName() + "'");
			attributeCondition.remove("channelname");
			falg = true;
		}
		// topLevel
		if (!(imgContext.getTopLevel() == null)) {
			System.out.println("topLevel:" + imgContext.getTopLevel());
			Integer level = 0;
			int rootCmsChannelId = CmsChannelManager.getInstance().getIndexId(
					sqlSession, context.getCmsSiteId());
			try {
				level = Integer.parseInt(imgContext.getTopLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			System.out.println("level:" + level);
			List<Integer> ids = CmsChannelManager.getInstance()
					.getSubLevelByIdAndLevel(sqlSession, rootCmsChannelId,
							level);
			if (ids.size() < 1) {
				condition.append(" and 1=2 ");
			} else {
				condition.append(" and id in ("
						+ CmsChannelManager.getInstance().getIdsString(ids)
						+ ")");
			}
			attributeCondition.remove("toplevel");
			falg = true;
		}
		// upLevel
		if (!(imgContext.getUpLevel() == null)
				&& (context.getTemplateType().equalsIgnoreCase("channel") || context
						.getTemplateType().equalsIgnoreCase("content"))) {
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try {
				level = Integer.parseInt(imgContext.getUpLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			cmsChannelId = CmsChannelManager.getInstance()
					.getUpLevelByIdAndLevel(sqlSession, cmsChannelId, level);
			if (null == cmsChannelId) {
				condition.append(" and 1=2 ");
			} else {
				condition.append(" and id =" + cmsChannelId);
			}
			attributeCondition.remove("uplevel");
			falg = true;
		}
		// 没有设置栏目
		if (!falg) {
			condition
					.append(" and id ='" + context.getCurrentChannelId() + "'");
		}
		return condition.toString();
	}*/

}
