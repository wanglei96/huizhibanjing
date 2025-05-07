package strosoft.app.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.model.ServiceMonitor;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerFactory;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.sys.model.AuthenticationManager;

public class Service extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Service() {
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
		// 允许任何域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许的请求方法
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String serviceName = request.getParameter("serviceName");
		if (serviceName == null) {
			serviceName = request.getParameter("service_name");
		}
		if (serviceName == null || serviceName.equals("")) {
			ServiceHandlerHelper.writeErrorResponse(response, "服务名称【serviceName】不能为空！");
			return;
		}
		Long serviceStatusItemID = ServiceMonitor.getInstance().addServiceBegin(serviceName);
		try {
			String token = request.getParameter("token");
			String accountId = request.getParameter("accountId");
			String applicationId = AuthenticationManager.getInstance().getParameterApplicationId(request);
			// 是否开启登录状态验证
			Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
			// 是否属于非验证服务
			Boolean isUncheckServiceName = AuthenticationManager.getInstance().isUncheckServiceName(serviceName);
			if (userSessionCheckEnabled && !isUncheckServiceName) {
				// 进行验证
				String loginStatus = AuthenticationManager.getInstance().checkLoginStatus(applicationId, accountId,
						token);
				if (!loginStatus.equals("true")) {
					ServiceHandlerHelper.writeLoginTimeoutResponse(response, loginStatus);
					return;
				}
			}
			ServiceHandler serviceHandler = ServiceHandlerFactory.GetHandler(serviceName);
			if (serviceHandler == null) {
				ServiceHandlerHelper.writeErrorResponse(response, "未找到服务名称为【" + serviceName + "】的服务处理类！");
				return;
			}
			serviceHandler.process(request, response);
			ServiceMonitor.getInstance().addServiceEnd(serviceStatusItemID, serviceName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ServiceHandlerHelper.writeErrorResponse(response, e.getMessage());
			ServiceMonitor.getInstance().addServiceError(serviceStatusItemID, serviceName, e);
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
