package strosoft.app.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Node;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.data.DataEntity;
import strosoft.app.util.JsonHelper;

/*
 * 添加服务处理类
 */
public class BatchAddServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 检查页面输入规则（必填项，唯一项）
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			// 不符合输入规则，终止。
			return;
		}
		String data = request.getParameter("data");
		JSONArray jarrData = JSONArray.fromObject(data);
		// 根据服务处理类参数获取对应的数据库访问类（Mananger类）
		TableDataMaster dataManager = (TableDataMaster) ServiceHandlerHelper
				.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			// 没有获取到数据库访问类，返回错误到页面
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		ArrayList<DataEntity> lstDataEntity = new ArrayList<DataEntity>();
		for (Object currentData : jarrData) {
			JSONObject jData = (JSONObject) currentData;
			// 根据页面参数获取对应实体
			DataEntity newDataEntity = ServiceHandlerHelper.getDataEntity(
					jData, this.getConfigNode(), null, false);
			if (newDataEntity == null) {
				// 没有获取到实体，返回错误到页面。
				this.writeErrorResponse(response, "数据实体配置错误！");
				return;
			}
			lstDataEntity.add(newDataEntity);
		}
		// 通过获取到的数据库访问类执行新增操作，返回新增的记录ID
		ArrayList<Integer> lstNewID = dataManager.add(lstDataEntity);
		// 向页面返回成功响应，并返回新增德记录ID
		this.writeSuccessResponse(response, lstNewID);

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONArray jarrData = JSONArray.fromObject(data);
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.Add);
		Node configNode = this.getConfigNode();
		for (Object currentData : jarrData) {
			JSONObject jData = (JSONObject) currentData;
			// 添加必填字段
			ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator,
					configNode, jData);
			// 添加唯一字段
			ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator,
					configNode, jData);
			// 多主键添加主键唯一验证信息
			ServiceHandlerHelper.addPrimaryKeyItems(theServiceHandlerValidator,
					configNode, jData);
		}
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}

}
