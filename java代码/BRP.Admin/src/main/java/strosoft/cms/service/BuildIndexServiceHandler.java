package strosoft.cms.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;


public class BuildIndexServiceHandler extends ServiceHandler{
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		
		String templateType = JsonHelper.getString(jData, "name");
		String relatedFileName = JsonHelper.getString(jData, "relatedFileName");
		String createdFileName = JsonHelper.getString(jData, "createdFileName");
		String createdFileExtName = JsonHelper.getString(jData, "createdFileExtName");
		//获取模板文件
/*		String templateFilePath="file:///"+SysConfig.getRootPath()+relatedFileName+createdFileExtName;
		//第一次解析模板文件（把stl:include替换成原始html）
		String html = buildIndexPage(templateFilePath, templateType);
		//创建生成的首页
		File file = FileHelper.createFile(SysConfig.getRootPath()+createdFileName+createdFileExtName);
		FileHelper.WriteContents(file, html, "utf-8");*/

		//第二次进行XML解析
		
		String templateFileBildPath="file:///"+SysConfig.getRootPath()+createdFileName+createdFileExtName;
		buildIndexPage(templateFileBildPath, templateType);
		
	}
	
	
	public static String buildIndexPage(String templateFilePath,String templateType){
		
		String html = "";
		try {
			//html = CmsHtmlGenerator.getInstance().
				//	build(templateFilePath, templateType);
			return html;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		
	}

}
