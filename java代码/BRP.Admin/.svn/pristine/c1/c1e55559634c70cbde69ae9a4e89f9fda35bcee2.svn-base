package strosoft.cms.model;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsTemplate;

public class CmsCustomBuildHandler extends CmsBuildHandler {
	@Override
	public void processByCmsLanguage(CmsBuildContext context) throws IOException {
		int cmsTemplateId = context.getCmsBuildTask().getTemplateId();
		SqlSession sqlSession = context.getSqlSession();
		CmsTemplate cmsTemplate =sqlSession.selectOne("CmsTemplate.selectCmsTemplate",cmsTemplateId);
		//拼接生成内容文件路径
		String cmsChannelPath = SysConfig.getRootPath()+cmsTemplate.getUrl()+".htm";
		//获取栏目对应模板路径
		String templatePath = SysConfig.getRootPath()+cmsTemplate.getPath();
		//获取模板编码
		String encoding = cmsTemplate.getEncode();
		//获取模板文件
		String templateContent = FileHelper.readAllText(templatePath,encoding);
	}
}
