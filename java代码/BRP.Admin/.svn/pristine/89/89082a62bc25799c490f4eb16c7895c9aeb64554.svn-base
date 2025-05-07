package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.model.OperationTypeCodes;

public class UpdateServiceHandler extends ServiceHandler {

	@Override
	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 检查页面输入规则（必填项，唯一项）
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			// 不符合输入规则，终止。
			return;
		}
		// 根据服务处理类参数获取对应的数据库访问类（Mananger类）
		TableDataMaster dataManager = ServiceHandlerHelper.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			// 没有获取到数据库访问类，返回错误到页面
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		// 根据页面参数获取对应实体
		DataEntity newDataEntity = ServiceHandlerHelper.getDataEntity(request, this.getConfigNode(), dataManager, true);
		if (newDataEntity == null) {
			// 没有获取到实体，返回错误到页面。
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		// 通过获取到的数据库访问类执行新增操作，返回新增的记录ID
		SqlSession session = MyBatisManager.getInstance().openSession();
		try {
			ServiceContext updateServiceContext = new ServiceContext();
			updateServiceContext.setRequest(request);
			updateServiceContext.setResponse(response);
			updateServiceContext.setSession(session);
			// 处理数据实体
			newDataEntity = this.processDataEntity(updateServiceContext, newDataEntity);
			updateServiceContext.setDataEntity(newDataEntity);
			// 通过获取到的数据库访问类执行修改操作，返回修改记录的id
			int id = dataManager.update(session, newDataEntity);
			// 添加后处理函数
			this.postProcess(updateServiceContext);
			//添加操作日志
			String content = this.getSysOperationLogContent(updateServiceContext);
			ServiceHandlerHelper.addSysOperationLog(session, OperationTypeCodes.Update, request, dataManager, content);
			MyBatisManager.getInstance().commitSession(session);
			// 向页面返回成功响应，并返回新增德记录ID
			this.writeSuccessResponse(response, id);

		} catch (Exception ex) {
			MyBatisManager.getInstance().rollbackSession(session);
			throw ex;
		} finally {
			MyBatisManager.getInstance().closeSession(session);
		}

	}

	/**
	 * 取得操作日志内容
	 * @param updateServiceContext
	 * @return
	 */
	protected String getSysOperationLogContent(ServiceContext updateServiceContext) {
		String content = JSONObject.fromObject(updateServiceContext.getDataEntity()).toString();
		return content;
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Edit);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator, configNode, jData);
		// 添加唯一字段
		ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator, configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}

	/**
	 * 修改前处理数据实体
	 * 
	 * @param request
	 * @param response
	 * @param newDataEntity
	 * @return
	 */
	protected DataEntity processDataEntity(ServiceContext updateServiceContext, DataEntity newDataEntity)
			throws Exception {
		return newDataEntity;
	}

	/**
	 * 修改后处理函数
	 * 
	 * @param updateServiceContext
	 * @throws Exception
	 */
	protected void postProcess(ServiceContext updateServiceContext) throws Exception {
		// TODO Auto-generated method stub

	}
}
