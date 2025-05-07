package strosoft.cms.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsImageContext;
import strosoft.cms.data.CmsLocationContext;
import strosoft.cms.data.ViewCmsChannel;

public class CmsTemplateTagLocaTionHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;
	private CmsHtmlGenerateContext con;
	private String hreftext = "";

	@Override
	public String process(CmsHtmlGenerateContext context, CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		NodeList nlChildren = currentNode.getChildNodes();
		String childStr = "";
		CmsHtmlGenerateContext cmsHtmlGenerateContext;
		try {
			cmsHtmlGenerateContext = context.clone();
			childStr = (createChildNodeHtml(nlChildren, cmsHtmlGenerateContext, cmsBuildContext));
			childStr = (createChildNodeHtml(nlChildren, cmsHtmlGenerateContext, cmsBuildContext))
					.replaceAll("</#text>", "").replaceAll("<#text>", "");
		} catch (CloneNotSupportedException e) {

		}
		// 生成子节点html

		con = context;
		StringBuffer content = new StringBuffer();

		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);
		String text = childStr;
		Integer id = context.getCurrentChannelId();
		// 处理attribute
		CmsLocationContext location = new CmsLocationContext(attributeCondition);
		String result = FunctionAttribute(location, cmsBuildContext, 6, text);
		content.append(result);

		return content.toString();
	}

	private String FunctionAttribute(CmsLocationContext location, CmsBuildContext cmsBuildContext, Integer id,
			String text) {
		// TODO Auto-generated method stub
		// cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
		String result = "";
		String add = "";
		int i = 0;
		try {
			SqlSession session = MyBatisManager.getInstance().openSession();
			while (id != null && id != 0) {
				String href = "";
				String sql = "SELECT * FROM view_cms_channel WHERE id=" + id;
				ViewCmsChannel theViewCmsChannel = session.selectOne("ViewCmsChannel.selectEntityBysql", sql);
				if (location.getWordNum() != null && theViewCmsChannel.getName() != null
						&& theViewCmsChannel.getName().length() > location.getWordNum().length()) {
					theViewCmsChannel.setName(theViewCmsChannel.getName().substring(0, Integer.parseInt(location.getWordNum())) + "...");
				}
				id = theViewCmsChannel.getParentId();
				if (location.getTarget() != null) {
					if (location.getTarget().equals("_black")) {
						add += "target='_black'";
					} else if (location.getTarget().equals("_self")) {
						add += "target='_self'";
					}
				}
				if (location.getLinkClass() != null) {
					add += " class= " + location.getLinkClass();
				}
				if (location.getSeparator() == null && text.equals("")) {
					if (i == 0) {
						href = " <a  " + add + " href='" + cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
								+ theViewCmsChannel.getCreateFilePath() + "'>" + theViewCmsChannel.getName() + "</a> ";
					} else {
						href = " - <a " + add + "  href='" + cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
								+ theViewCmsChannel.getCreateFilePath() + "'>" + theViewCmsChannel.getName() + "</a> ";
					}

				} else if (text != "") {
					if (i == 0) {
						href = " <a " + add + "  href='" + theViewCmsChannel.getCreateFilePath() + "'>" + theViewCmsChannel.getName()
								+ "</a> ";
					} else {
						href = " " + text + " <a  " + add + "  href='"
								+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl() + theViewCmsChannel.getCreateFilePath()
								+ "'>" + theViewCmsChannel.getName() + "</a> ";
					}
				} else if (location.getSeparator() != null) {
					if (i == 0) {
						href = " <a " + add + "  href='" + cmsBuildContext.getCmsBuildTask().getAdminRootUrl()
								+ theViewCmsChannel.getCreateFilePath() + "'>" + theViewCmsChannel.getName() + "</a> ";
					} else {
						href = " " + location.getSeparator() + " <a " + add + "  href='"
								+ cmsBuildContext.getCmsBuildTask().getAdminRootUrl() + theViewCmsChannel.getCreateFilePath()
								+ "'>" + theViewCmsChannel.getName() + "</a> ";
					}
				}

				i++;

				result += href;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * 根据节点属性名称和值进行返回数据
	 */
	// 获取栏目image查询条件：
	public String getSqlCondition(SqlSession sqlSession, CmsImageContext imgContext, CmsHtmlGenerateContext context) {
		StringBuffer condition = new StringBuffer(" where 1=1");
		// 判断有没有设置栏目模板
		boolean falg = false;
		// 获取栏目id
		// channelCode
		if (!(imgContext.getChannelCode() == null)) {
			condition.append(" and code ='" + imgContext.getChannelCode() + "'");
			attributeCondition.remove("channelcode");
			falg = true;
		}
		// channelIndex
		if (!(imgContext.getChannelIndex() == null)) {
			condition.append(" and code ='" + imgContext.getChannelIndex() + "'");
			attributeCondition.remove("channelindex");
			falg = true;
		}

		// channelName
		if (!(imgContext.getChannelName() == null)) {
			condition.append(" and name ='" + imgContext.getChannelName() + "'");
			attributeCondition.remove("channelname");
			falg = true;
		}
		// topLevel
		if (!(imgContext.getTopLevel() == null)) {
			System.out.println("topLevel:" + imgContext.getTopLevel());
			Integer level = 0;
			int rootCmsChannelId = CmsChannelManager.getInstance().getIndexId(sqlSession, context.getCmsSiteId());
			try {
				level = Integer.parseInt(imgContext.getTopLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			System.out.println("level:" + level);
			List<Integer> ids = CmsChannelManager.getInstance().getSubLevelByIdAndLevel(sqlSession, rootCmsChannelId,
					level);
			if (ids.size() < 1) {
				condition.append(" and 1=2 ");
			} else {
				condition.append(" and id in (" + CmsChannelManager.getInstance().getIdsString(ids) + ")");
			}
			attributeCondition.remove("toplevel");
			falg = true;
		}
		// upLevel
		if (!(imgContext.getUpLevel() == null) && (context.getTemplateType().equalsIgnoreCase("channel")
				|| context.getTemplateType().equalsIgnoreCase("content"))) {
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try {
				level = Integer.parseInt(imgContext.getUpLevel());
			} catch (Exception e) {
				level = 0;
				e.printStackTrace();
			}
			cmsChannelId = CmsChannelManager.getInstance().getUpLevelByIdAndLevel(sqlSession, cmsChannelId, level);
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
			condition.append(" and id ='" + context.getCurrentChannelId() + "'");
		}
		return condition.toString();
	}

}
