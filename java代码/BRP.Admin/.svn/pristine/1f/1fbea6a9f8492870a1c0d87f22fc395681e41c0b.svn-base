package strosoft.cms.model;

import java.util.HashMap;
import org.w3c.dom.Node;

import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsIncludeContext;

public class CmsTemplateTagIncludeHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
		
	StringBuffer content = new StringBuffer();
	Node currentNode = context.getCurrentNode();
	// 获取节点属性
	attributeCondition =getAttributeCondition(currentNode);
	// 获取channel生成属性参数
	CmsIncludeContext includeContext = new CmsIncludeContext(
			attributeCondition);
	content = gethtmlWidthFile(includeContext, context);
	return content.toString();
	}
	
	// file属性
	public StringBuffer gethtmlWidthFile(CmsIncludeContext includeContext,CmsHtmlGenerateContext context) {
		StringBuffer value = new StringBuffer();
		if (!(includeContext.getFile() == null)) {
			String filePath = "";
			String filePathStr = includeContext.getFile();
			String firstStr = filePathStr.substring(0,1);
			if(firstStr.equals("~")){
				//从系统根目录下
				filePath = SysConfig.getRootPath()+filePathStr.substring(2);
			}else if(firstStr.equals("@")){
				//从站点根目录下
				filePath = SysConfig.getRootPath()+(context.getSiteDir()==""?filePathStr.substring(2):context.getSiteDir()+filePathStr.substring(1));
			}else if(firstStr.equals("/")){
				//从站点根目录下
				filePath = SysConfig.getRootPath()+(context.getSiteDir()==""?filePathStr.substring(1):context.getSiteDir()+filePathStr);
			}else{
				//相对路径
				return new StringBuffer("<p>请使用相对系统地址以'~'开头，或使用相对站点地址以'@'开头</p>");
			}
			//获取文件内容
			value.append(getIncludeFileContent(filePath, context));
		}
		return value;
	}
	public String getIncludeFileContent(String includeFilePath,CmsHtmlGenerateContext context){
		String content = "";
		try{
			content = FileHelper.readAllText(includeFilePath,
					context.getEncoding());
			if(content == null){
				throw new Exception("包含文件读取失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			content = "<p>解析包含文件错误，请检查地址是否正确，包含文件指向的地址为：'"+includeFilePath+"'</p>";
		}
		return content;
	}
	
}
