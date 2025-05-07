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
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsContent;

public class CmsTemplateTagImageHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {

		StringBuffer content = new StringBuffer();
		Node currentNode = context.getCurrentNode();
		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			CmsImageContext imgContext = new CmsImageContext(attributeCondition);
			// 获取没有解析的属性值
			String originalAttribute = getOriginalAttribute(attributeCondition);
			String heightwidth = HW(imgContext);
			// CurrentShowTypt;//当前显示栏目还是内容 如果是内容
			if (null != context.getCurrentShowType()
					&& context.getCurrentShowType().equalsIgnoreCase("content")
					&& imgContext.getSrc() == null) {
				ViewCmsContent viewContent = sqlSession.selectOne(
						"selectViewCmsContentById",
						context.getCurrentContentId());
				if (viewContent.getThumbFilePath() != null
						&& !viewContent.getThumbFilePath().equals("")) {
					String image = "<img "
							+ heightwidth
							+ "src='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl() + "/"
							+ viewContent.getThumbFilePath() + "'"
							+ originalAttribute + "/>";
					System.out.println(image);
					content.append(image);
					return content.toString();
				}
			} else if (imgContext.getSrc() != null) {
				String src = analysisSpecialCharacter(context, imgContext);
				String image = "<img " + heightwidth + "src='" + src + "'"
						+ originalAttribute + "/>";
				System.out.println(image);
				content.append(image);
				return content.toString();

			} else if (imgContext.getChannelIndex() != null) {
				String sqlv = "select * from view_cms_channel where Code = '"
						+ imgContext.getChannelIndex() + "'";
				ViewCmsChannel viewCmsChannel = sqlSession.selectOne(
						"ViewCmsChannel.selectEntityBysql", sqlv);
				if (viewCmsChannel.getImageFilePath() != null) {
					String image = "<img "
							+ heightwidth
							+ "src='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl() + "/"
							+ viewCmsChannel.getImageFilePath() + "'"
							+ originalAttribute + "/>";
					System.out.println(image);
					content.append(image);
					return content.toString();

				}
			}

			else if (imgContext.getChannelName() != null) {
				String sqlv = "select * from view_cms_channel where Code = '"
						+ imgContext.getChannelName() + "'";
				ViewCmsChannel viewCmsChannel = sqlSession.selectOne(
						"ViewCmsChannel.selectEntityBysql", sqlv);
				if (viewCmsChannel.getImageFilePath() != null) {
					String image = "<img "
							+ heightwidth
							+ "src='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl() + "/"
							+ viewCmsChannel.getImageFilePath() + "'"
							+ originalAttribute + "/>";
					System.out.println(image);
					content.append(image);
					return content.toString();

				}
			}

			else {

				String channelCondition = getSqlCondition(sqlSession,
						imgContext, context);
				String sql = "select id from view_cms_channel "
						+ channelCondition + " and cms_site_id="
						+ context.getCmsSiteId();
				List<Integer> channelIdList = sqlSession.selectList(
						"ViewCmsChannel.selectIdListBysql", sql);
				// if(channelIdList.size()==0){
				// String
				// image="<img "+heightwidth+"src='"+imgContext.getAltSrc()+"'/>";
				// System.out.println(image);
				// content.append(image);
				// }
				//
				// if(channelIdList.size()==0){
				// String
				// image="<img "+heightwidth+"src='"+imgContext.getAltSrc()+"'/>";
				// }
				for (int i = 0; i < channelIdList.size(); i++) {
					int id = channelIdList.get(i);
					sql = "select image_file_path from view_cms_channel where id = "
							+ id;
					ViewCmsChannel theViewCmsChannel = sqlSession.selectOne(
							"ViewCmsChannel.selectEntityBysql", sql);
					if (null == theViewCmsChannel) {
						return "";
					}
					String p = SysConfig.getRootPath() + theViewCmsChannel.getImageFilePath();
					// cmsBuildContext.getCmsBuildTask().getAdminRootUrl()+viewCmsContent.getThumbFilePath();
					// 地址
					System.out.println(SysConfig.getRootPath()
							+ "-----------------------"
							+ theViewCmsChannel.getImageFilePath());
					// StroCMS.Admin/upload/2d6ca423-21fe-4fad-8280-c449242aca18.jpg
					String image = "<img "
							+ heightwidth
							+ "src='"
							+ cmsBuildContext.getCmsBuildTask()
									.getAdminRootUrl() + "/"
							+ theViewCmsChannel.getImageFilePath() + "'" + originalAttribute
							+ "/>";
					System.out.println(image);
					content.append(image);
				}

				return content.toString();
			}

		} catch (Exception e) {
			return "";
		}
		return "";
	}

	private String altSrcFunction(CmsImageContext imgContext, String channelSrc) {
		// TODO Auto-generated method stub
		String altSrc = "";

		File f = new File(channelSrc);
		if (f.exists()) {
			System.out.println("存在");
			altSrc = channelSrc;
		} else {
			altSrc = imgContext.getAltSrc();
		}

		return altSrc;
	}

	private String HW(CmsImageContext imgContext) {

		String result = "";

		Integer width = null;
		Integer height = null;
		try {

			height = Integer.parseInt(imgContext.getHeight());
		} catch (Exception e) {

			// TODO: handle exception
		}
		try {
			width = Integer.parseInt(imgContext.getWidth());

		} catch (Exception e) {

			// TODO: handle exception
		}
		if (height != null && height > 0) {

			result = " height='" + imgContext.getHeight() + "' ";

		}
		if (width != null && height > 0) {
			result += " width='" + imgContext.getWidth() + "' ";

		}

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

	public String analysisSpecialCharacter(CmsHtmlGenerateContext context,
			CmsImageContext imgContext) {
		String src = imgContext.getSrc();
		if (null == src) {
			return "";
		}
		String specialCharacter = src.substring(0, 1);
		if (specialCharacter.equals("~")) {
			src = src.replace(specialCharacter, context.getRootUrl());
		} else if (specialCharacter.equals("@")) {
			src = src.replace(specialCharacter, context.getSiteUrl());
		}
		return src;
	}

}
