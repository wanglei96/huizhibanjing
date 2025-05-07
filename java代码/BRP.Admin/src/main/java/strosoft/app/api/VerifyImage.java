package strosoft.app.api;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.ServiceClient;
import strosoft.app.common.SysConstants;
import strosoft.app.util.ImageVerifyCodeGenerator;

/**
 * 图片验证码
 * 
 * @author Administrator
 *
 */
public class VerifyImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public VerifyImage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/*
	 * 处理请求
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("image/png");
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

			// 生成验证码图片
			Map<String, Object> imageVerifyCode = ImageVerifyCodeGenerator.generate();
			BufferedImage bfm = (BufferedImage) imageVerifyCode.get("image");
			String code = String.valueOf(imageVerifyCode.get("code"));
			System.out.println("验证码:" + code);

			String key = UUID.randomUUID().toString();
			JSONObject jRequestData = new JSONObject();
			jRequestData.put("key", key);
			jRequestData.put("code", code);
			ServiceClient.callService("SetVerifyCode", jRequestData);

			String fileName = key + ".png";
			// 创建一个Cookie对象
			Cookie cookie = new Cookie(SysConstants.VerifyCodeKeyCookieName, key);
			cookie.setPath("/");
			cookie.setMaxAge(3600);// 一小时失效
			// 通过响应添加
			response.addCookie(cookie);

			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// 设置下载文件头及文件名称
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

			ServletOutputStream outputStream = response.getOutputStream();
			// 使用 ImageIO工具类将 图片验证码输出到响应流
			ImageIO.write(bfm, "PNG", outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
