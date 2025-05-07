package strosoft.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.CodeDataManager;
import strosoft.app.common.DataMaster;
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
import strosoft.app.util.ClassHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.XmlDocumentHelper;

public class GetServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取request参数字符串
		String data = request.getParameter("data");
		// 将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		DataEntity newDataEntity = new DataEntity();
		// 根据服务处理类参数获取对应的数据库访问类（Mananger类）
		String dataManagerType = XmlDocumentHelper.getAttribute(this.getConfigNode(), "DataManagerType");
		Object dataManager = ClassHelper.newInstance(dataManagerType);
		// 根据服务处理类的父类类型调用对应的获取实体类方法
		if (dataManager instanceof IdNameTreeManager) {
			// id树型表
			IdNameTreeManager theIdNameTreeManager = (IdNameTreeManager) dataManager;
			int id = jData.getInt("id");
			String tableName = jData.getString("tableName");
			newDataEntity = theIdNameTreeManager.getEntity(tableName, id);
		} else if (dataManager instanceof CodeNameTreeManager) {
			// code树型表
			CodeNameTreeManager theCodeNameTreeManager = (CodeNameTreeManager) dataManager;
			String code = jData.getString("code");
			String tableName = jData.getString("tableName");
			newDataEntity = theCodeNameTreeManager.getEntity(tableName, code);
		} else if (dataManager instanceof IdNameTableManager) {
			// id基础表
			IdNameTableManager theIdNameTableManager = (IdNameTableManager) dataManager;
			int id = jData.getInt("id");
			String tableName = jData.getString("tableName");
			newDataEntity = theIdNameTableManager.getEntity(tableName, id);
		} else if (dataManager instanceof CodeNameTableManager) {
			// code基础表
			CodeNameTableManager theCodeNameTableManager = (CodeNameTableManager) dataManager;
			String code = jData.getString("code");
			String tableName = jData.getString("tableName");
			newDataEntity = theCodeNameTableManager.getEntity(tableName, code);
		} else if (dataManager instanceof CmsIdNameTreeManager) {
			// id树型表
			CmsIdNameTreeManager theCmsIdNameTreeManager = (CmsIdNameTreeManager) dataManager;
			int id = jData.getInt("id");
			String tableName = jData.getString("tableName");
			newDataEntity = theCmsIdNameTreeManager.getEntity(tableName, id);
		} else if (dataManager instanceof CmsCodeNameTreeManager) {
			// code树型表
			CmsCodeNameTreeManager theCmsCodeNameTreeManager = (CmsCodeNameTreeManager) dataManager;
			int cmsSiteId = jData.getInt("cmsSiteId");
			String code = jData.getString("code");
			String tableName = jData.getString("tableName");
			newDataEntity = theCmsCodeNameTreeManager.getEntity(cmsSiteId, tableName, code);
		} else if (dataManager instanceof CmsIdNameTableManager) {
			// id基础表
			CmsIdNameTableManager theCmsIdNameTableManager = (CmsIdNameTableManager) dataManager;
			int id = jData.getInt("id");
			String tableName = jData.getString("tableName");
			newDataEntity = theCmsIdNameTableManager.getEntity(tableName, id);
		} else if (dataManager instanceof CmsCodeNameTableManager) {
			// code基础表
			CmsCodeNameTableManager theCmsCodeNameTableManager = (CmsCodeNameTableManager) dataManager;
			int cmsSiteId = jData.getInt("cmsSiteId");
			String code = jData.getString("code");
			String tableName = jData.getString("tableName");
			newDataEntity = theCmsCodeNameTableManager.getEntity(cmsSiteId, tableName, code);
		} else if (dataManager instanceof CodeDataManager) {
			CodeDataManager theCodeDataManager = (CodeDataManager) dataManager;
			String code = jData.getString("code");
			newDataEntity = theCodeDataManager.getDataEntity(code);
		} else if (dataManager instanceof IdDataManager) {
			IdDataManager idDataManager = (IdDataManager) dataManager;
			Integer id = jData.getInt("id");
			newDataEntity = idDataManager.getDataEntity(id);
		} else if (dataManager instanceof DataMaster) {
			DataMaster viewDataMaster = (DataMaster) dataManager;
			String condition = ServiceHandlerHelper.getCompositeKeyConditionWhenGetEntity(this.getConfigNode(), jData);
			List<DataEntity> lstDataEntity = viewDataMaster.getDataEntityList(condition);
			if (lstDataEntity.size() > 0) {
				newDataEntity = lstDataEntity.get(0);
			}
		}
		com.alibaba.fastjson2.JSONObject jResponseData = JsonHelper.toFastJSONObject(newDataEntity);
		this.writeSuccessResponse(response, jResponseData);
	}

}
