package strosoft.sys.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.SysUploadFileManager;

public class DownloadFileServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException, Exception {
			request.setCharacterEncoding("UTF-8");
			String data = request.getParameter("data");
			try{
				JSONObject jData = JSONObject.fromObject(data);
				Integer fileId = jData.getInt("id");
				SysUploadFile file = SysUploadFileManager.getInstance().getEntity(fileId); 
			    //第一步：设置响应类型
				response.setContentType("application/force-download");//应用程序强制下载
			    //第二读取文件
			    String path = SysConfig.getRootPath()+file.getPath();
			    InputStream in = new FileInputStream(path);
			    //设置响应头，对文件进行url编码
			    String name = URLEncoder.encode(file.getName(), "UTF-8");
			    response.setHeader("Content-Disposition", "attachment;filename="+name);   
			    response.setContentLength(in.available());
			    //第三步：老套路，开始copy
			    OutputStream out = response.getOutputStream();
			    byte[] b = new byte[1024];
			    int len = 0;
			    while((len = in.read(b))!=-1){
			      out.write(b, 0, len);
			    }
			    out.flush();
			    out.close();
			    in.close();
			}catch(Exception e){
				e.printStackTrace();
			    String name = URLEncoder.encode("下载失败", "UTF-8");
			    response.setHeader("Content-Disposition", "attachment;filename="+name); 
				this.writeErrorResponse(response, "文件不存在，下载文件失败");
			}
	}
}
