package strosoft.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;

/**
 * 服务处理类
 * 
 * @author Administrator
 *
 */
public class ServiceHandler {

	private String serviceName;
	private Node configNode;

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Node getConfigNode() {
		return configNode;
	}

	public void setConfigNode(Node configNode) {
		this.configNode = configNode;
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException,
			ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {

	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, String data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, int data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, double data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, short data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, long data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, float data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, char data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, Boolean data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功顺应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, byte data) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出成功响应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response);
	}

	/*
	 * 输出成功响应结果
	 */
	protected void writeSuccessResponseRaw(HttpServletResponse response, String rawData) throws IOException {
		ServiceHandlerHelper.writeSuccessResponseRaw(response, rawData);

	}

	/*
	 * 输出数据响应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, Object data) throws IOException {

		ServiceHandlerHelper.writeSuccessResponse(response, data);
	}

	/*
	 * 输出数据响应结果
	 */
	public void writeSuccessResponse(HttpServletResponse response, ArrayList alData) throws IOException {
		ServiceHandlerHelper.writeSuccessResponse(response, alData);
	}

	/*
	 * 输出错误响应结果
	 */
	public void writeErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
		ServiceHandlerHelper.writeErrorResponse(response, errorMessage);
	}

	/*
	 * 输出登录超时响应结果
	 */
	public void writeLoginTimeoutResponse(HttpServletResponse response, String errorMessage) throws IOException {
		ServiceHandlerHelper.writeLoginTimeoutResponse(response, errorMessage);
	}

	/*
	 * 输出空值响应结果
	 */
	public void writeNullResponse(HttpServletResponse response) throws IOException {
		ServiceHandlerHelper.writeNullResponse(response);
	}

	/*
	 * 输出警告响应结果
	 */
	public void writeWarningResponse(HttpServletResponse response, String warningMessage) throws IOException {
		ServiceHandlerHelper.writeWarningResponse(response, warningMessage);
	}

	/**
	 * 取得请求数据
	 * 
	 * @param request
	 * @return
	 */
	protected JSONObject getRequestData(HttpServletRequest request) {
		return ServiceHandlerHelper.getRequestData(request);
	}
}
