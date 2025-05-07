package strosoft.app.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysOperationLog;
import strosoft.sys.model.SysOperationLogManager;

/*
 * 添加服务处理类
 */
public class AddServiceHandler extends ServiceHandler {

    @Override
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
        DataEntity newDataEntity = ServiceHandlerHelper.getDataEntity(request, this.getConfigNode(), null, false);
        if (newDataEntity == null) {
            // 没有获取到实体，返回错误到页面。
            this.writeErrorResponse(response, "数据实体配置错误！");
            return;
        }
        // 通过获取到的数据库访问类执行新增操作，返回新增的记录ID
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            ServiceContext newAddServiceContext = new ServiceContext();
            newAddServiceContext.setRequest(request);
            newAddServiceContext.setResponse(response);
            newAddServiceContext.setSession(session);
            // 处理数据实体
            newDataEntity = this.processDataEntity(newAddServiceContext, newDataEntity);
            newAddServiceContext.setDataEntity(newDataEntity);
            int newId = dataManager.add(session, newDataEntity);
            // 添加后处理函数
            newAddServiceContext.setNewId(newId);
            this.postProcess(newAddServiceContext);
            //添加操作日志
            String content = this.getSysOperationLogContent(newAddServiceContext);
            ServiceHandlerHelper.addSysOperationLog(session, OperationTypeCodes.Add, request, dataManager, content);
            //提交事务
            MyBatisManager.getInstance().commitSession(session);
            // 向页面返回成功响应，并返回新增德记录ID
            this.writeSuccessResponse(response, newId);

        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 取得操作日志内容
     * @param addServiceContext
     * @return
     */
    protected String getSysOperationLogContent(ServiceContext addServiceContext) {
        String content = JSONObject.fromObject(addServiceContext.getDataEntity()).toString();
        return content;
    }

    /**
     * 处理数据实体
     *
     * @param newDataEntity
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        return newDataEntity;
    }

    /**
     * 增加后处理函数
     *
     * @param newAddServiceContext
     */
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * 检查输入
     */
    protected Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data = request.getParameter("data");

        JSONObject jData = JSONObject.fromObject(data);

        Node configNode = this.getConfigNode();
        ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Add);
        // 添加必填字段
        ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator, configNode, jData);
        // 添加唯一字段
        ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator, configNode, jData);
        // 多主键添加主键唯一验证信息
        ServiceHandlerHelper.addPrimaryKeyItems(theServiceHandlerValidator, configNode, jData);
        Boolean isValid = theServiceHandlerValidator.check();

        return isValid;
    }

}
