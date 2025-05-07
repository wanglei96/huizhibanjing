package strosoft.app.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerFactory;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.QRCodeHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.AuthenticationManager;

public class QRCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QRCode() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/*
	 * 处理请求
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		try {
			String strWidth = request.getParameter("width");
			Integer width = 100;
			if (strWidth != null) {
				width = Integer.parseInt(strWidth);
			}
			String strHeight = request.getParameter("height");
			int height = 100;
			if (strHeight != null) {
				height = Integer.parseInt(strHeight);
			}
			String content = request.getParameter("content");
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			if (content == null) {
				content = "内容不可为空";
			}
			// 生成图片数据
			byte[] arrImageData = QRCodeHelper.createQRCodeBytes(width, height,
					content);
			// 得到文件名称
			String fileName = StringHelper.getRandomText(20) + ".png";
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// 设置下载文件头及文件名称
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(arrImageData);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
