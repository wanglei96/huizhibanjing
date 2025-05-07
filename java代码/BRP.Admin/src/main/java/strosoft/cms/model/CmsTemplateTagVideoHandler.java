package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.cms.data.CmsImageContext;
import strosoft.cms.data.CmsVideoContext;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsContent;

public class CmsTemplateTagVideoHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;
	private String SiteUrl = "";

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {
		SiteUrl = context.getSiteUrl();

		StringBuffer content = new StringBuffer();
		Node currentNode = context.getCurrentNode();
		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);
		CmsVideoContext vidContext = new CmsVideoContext(attributeCondition);
		// 获取没有解析的属性值
		String originalAttribute = getOriginalAttribute(attributeCondition);

		String AllAttribute = "";// height width
		AllAttribute = FunctionAllAttribute(vidContext);
		String VidAttribute = FunctionVidAttribute(vidContext);
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {

			if (null != context.getCurrentShowType()
					&& context.getCurrentShowType().equalsIgnoreCase("content")) {

				String sqlv = "select * from view_cms_content where id = "
						+ context.getCurrentContentId();
				ViewCmsContent viewCmsContent = sqlSession.selectOne(
						"ViewCmsContent.selectEntityBysql", sqlv);
				if (viewCmsContent.getVideoFilePath() != null) {
					String video = "<video  " + AllAttribute + "  src="
							+ context.getSiteUrl() + "'"
							+ viewCmsContent.getVideoFilePath() + "'"
							+ originalAttribute + " ></video>";
					System.out.println(video);
					content.append(video);
					return content.toString();
				}
			} else {
				if (vidContext.getPlayUrl() != null) {
					String video = "<video  " + AllAttribute
							+ originalAttribute + "  ></video>";
					System.out.println(video);
					content.append(video);
				}
				return content.toString();
			}

		} catch (Exception e) {
			return "";
		}
		return "";
	}

	private String FunctionVidAttribute(CmsVideoContext vidContext) {
		// TODO Auto-generated method stub
		String result = "";
		if (vidContext.getPlayUrl() != null) {
			result += " url='" + SiteUrl + vidContext.getPlayUrl() + "' ";
		}
		if (vidContext.getImageUrl() != null) {
			result += " poster='" + vidContext.getImageUrl() + "' ";
		}
		return result;
	}

	private String FunctionAllAttribute(CmsVideoContext vidContext) {
		String result = "";
		Integer width = null;
		Integer height = null;
		// width="520" height="440"
		try {
			width = Integer.parseInt(vidContext.getWidth());

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			height = Integer.parseInt(vidContext.getHeight());

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (height != null && height > 0) {
			result = "height='" + height + "' ";
		}
		if (width != null && width > 0) {
			result += " width='" + width + "'";
		}
		if (vidContext.getImageUrl() != null) {
			result += " poster='" + vidContext.getImageUrl() + "' ";
		}
		if (!vidContext.getIsAutoPlay().equals("false")) {
			result += "  autoplay  ";
		}
		if (!vidContext.getIsControls().equals("false")) {
			result += "  controls  ";

		}

		if (vidContext.getPlayUrl() != null) {
			result += " src='" + SiteUrl + vidContext.getPlayUrl() + "' ";
		}

		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * 根据节点属性名称和值进行返回数据
	 */
	// 获取栏目image查询条件：
	public String getSqlCondition(SqlSession sqlSession,
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
	}

}
