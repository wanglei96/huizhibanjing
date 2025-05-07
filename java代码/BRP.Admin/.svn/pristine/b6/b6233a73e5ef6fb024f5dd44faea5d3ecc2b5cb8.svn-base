package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import strosoft.app.model.CmsCodeNameTableManager;
import strosoft.app.model.CmsCodeNameTreeManager;
import strosoft.app.model.CmsIdNameTableManager;
import strosoft.app.model.CmsIdNameTreeManager;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.model.CodeNameTreeManager;
import strosoft.app.model.IdNameTableManager;
import strosoft.app.model.IdNameTreeManager;
import strosoft.app.util.*;

import net.sf.json.*;
import strosoft.sys.data.*;
import strosoft.sys.*;

/*
 * 批量删除服务处理类
 */
public class BatchDeleteServiceHandler extends ServiceHandler {

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取request中的请求参数字符串
		String data = request.getParameter("data");
		//将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		//根据服务处理类参数获取对应的数据库访问类（Mananger类）
		String dataManagerType = XmlDocumentHelper.getAttribute(
				this.getConfigNode(), "DataManagerType");
		TableDataMaster dataManager = (TableDataMaster) ClassHelper
				.newInstance(dataManagerType);
		//根据服务处理类的父类类型调用对应的批量删除方法
		if (dataManager instanceof IdNameTreeManager) {
			// id基础表
			IdNameTreeManager theIdNameTreeManager = (IdNameTreeManager) dataManager;
			List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
					"ids");
			String tableName = jData.getString("tableName");
			theIdNameTreeManager.batchDelete(tableName, lstId);
		} else if (dataManager instanceof CodeNameTreeManager) {
			// code基础表
			CodeNameTreeManager theCodeNameTreeManager = (CodeNameTreeManager) dataManager;
			List<String> lstCode = JsonHelper.getStringArray(jData,
					"primaryKeys", "codes");
			String tableName = jData.getString("tableName");
			theCodeNameTreeManager.batchDelete(tableName, lstCode);
		} else if (dataManager instanceof IdNameTableManager) {
			// id基础表
			IdNameTableManager theIdNameTableManager = (IdNameTableManager) dataManager;
			List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
					"ids");
			String tableName = jData.getString("tableName");
			theIdNameTableManager.batchDelete(tableName, lstId);
		} else if (dataManager instanceof CodeNameTableManager) {
			// code基础表
			CodeNameTableManager theCodeNameTableManager = (CodeNameTableManager) dataManager;
			List<String> lstCode = JsonHelper.getStringArray(jData,
					"primaryKeys", "codes");
			String tableName = jData.getString("tableName");
			theCodeNameTableManager.batchDelete(tableName, lstCode);
		} else if (dataManager instanceof CmsIdNameTableManager) {
			// id基础表
			CmsIdNameTableManager theCmsIdNameTableManager = (CmsIdNameTableManager) dataManager;
			List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
					"ids");
			String tableName = jData.getString("tableName");
			theCmsIdNameTableManager.batchDelete(tableName, lstId);
		} else if (dataManager instanceof CmsCodeNameTableManager) {
			// code基础表
			CmsCodeNameTableManager theCmsCodeNameTableManager = (CmsCodeNameTableManager) dataManager;
			List<String> lstCode = JsonHelper.getStringArray(jData,
					"primaryKeys", "codes");
			int cmsSiteId = jData.getInt("cmsSiteId");
			String tableName = jData.getString("tableName");
			theCmsCodeNameTableManager.batchDelete(cmsSiteId, tableName,
					lstCode);
		} else if (dataManager instanceof CmsIdNameTreeManager) {
			// id基础表
			CmsIdNameTreeManager theCmsIdNameTreeManager = (CmsIdNameTreeManager) dataManager;
			List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
					"ids");
			String tableName = jData.getString("tableName");
			theCmsIdNameTreeManager.batchDelete(tableName, lstId);
		} else if (dataManager instanceof CmsCodeNameTreeManager) {
			// code基础表
			CmsCodeNameTreeManager theCmsCodeNameTreeManager = (CmsCodeNameTreeManager) dataManager;
			List<String> lstCode = JsonHelper.getStringArray(jData,
					"primaryKeys", "codes");
			int cmsSiteId = jData.getInt("cmsSiteId");
			String tableName = jData.getString("tableName");
			theCmsCodeNameTreeManager
					.batchDelete(cmsSiteId, tableName, lstCode);
		} else if (dataManager instanceof IdDataManager) {
			// id数据表
			IdDataManager idDataManager = (IdDataManager) dataManager;
			List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
					"ids");
			idDataManager.batchDeleteByIds(lstId);
		} else if (dataManager instanceof CodeDataManager) {
			// code数据表
			CodeDataManager theCodeDataManager = (CodeDataManager) dataManager;
			List<String> lstCode = JsonHelper.getStringArray(jData,
					"primaryKeys", "codes");
			theCodeDataManager.batchDeleteByCodes(lstCode);
		} else {
			//联合主键批量删除
			String condition = ServiceHandlerHelper.getCompositeKeyConditionWhenBatchDelete(this.getConfigNode(), jData);
			if(null != condition){
				List<DataEntity> list = dataManager.getList(condition);
				dataManager.batchDeleteByEntity(list);
			}
		}
		this.writeSuccessResponse(response);
	}
}
