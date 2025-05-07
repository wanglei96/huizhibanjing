package strosoft.cms.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import strosoft.cms.data.CmsAudioContext;
import strosoft.cms.data.CmsImageContext;

public class CmsTemplateTagAudioHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;

	@Override
	public String process(CmsHtmlGenerateContext context,
			CmsBuildContext cmsBuildContext) {

		StringBuffer content = new StringBuffer();
		Node currentNode = context.getCurrentNode();
		// 获取节点属性
		attributeCondition = getAttributeCondition(currentNode);

		CmsAudioContext audiocondittion = new CmsAudioContext(
				attributeCondition);
		// 获取没有解析的属性值
		String originalAttribute = getOriginalAttribute(attributeCondition);
		// autoplay="autoplay"
		String AudioAttribute = AudioFunction(audiocondittion);
		String AudioString = "<audio controls " + AudioAttribute
				+ "> <source src='" + audiocondittion.getPlayUrl() + "'"
				+ originalAttribute + " > </audio>";

		content.append(AudioString);

		return content.toString();

	}

	private String AudioFunction(CmsAudioContext audiocondittion) {
		// TODO Auto-generated method stub
		String result = "";
		if (audiocondittion.getIsLoop() != null) {
			result += " loop ";
		}
		if (audiocondittion.getIsAutoPlay().equals("true")) {

			result += " autoplay='autoplay' ";
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

}
