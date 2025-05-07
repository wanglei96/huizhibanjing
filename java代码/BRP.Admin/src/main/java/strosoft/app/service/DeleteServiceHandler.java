package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.CodeDataManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.model.*;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

/*
 * 删除服务处理类
 */
public class DeleteServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取request参数字符串
        String data = request.getParameter("data");
        // 将参数字符串转为JSONObject类型
        JSONObject jData = JSONObject.fromObject(data);
        // 根据服务处理类参数获取对应的数据库访问类（Mananger类）
        String dataManagerType = XmlDocumentHelper.getAttribute(this.getConfigNode(), "DataManagerType");
        // 如果未设置数据管理类，则使用定制删除
        if (dataManagerType == null || dataManagerType.contentEquals("")) {
            this.customDelete(response, jData);
            return;
        }
        TableDataMaster dataManager = (TableDataMaster) ClassHelper.newInstance(dataManagerType);
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            String content = "";
            // 根据服务处理类的父类类型调用对应的删除方法
            if (dataManager instanceof IdNameTreeManager) {
                IdNameTreeManager theIdNameTreeManager = (IdNameTreeManager) dataManager;
                Integer id = JsonHelper.getInt(jData, "primaryKey", "id");
                if (id == null) {
                    throw new Exception("请求参数中缺少主键！");
                }
                String tableName = jData.getString("tableName");
                theIdNameTreeManager.delete(session, tableName, id);
                content = id.toString();
            } else if (dataManager instanceof CodeNameTreeManager) {
                CodeNameTreeManager theCodeNameTreeManager = (CodeNameTreeManager) dataManager;
                String code = JsonHelper.getString(jData, "primaryKey", "code");
                String tableName = jData.getString("tableName");
                theCodeNameTreeManager.delete(session, tableName, code);
                content = code;
            } else if (dataManager instanceof IdNameTableManager) {
                IdNameTableManager theIdNameTableManager = (IdNameTableManager) dataManager;
                Integer id = JsonHelper.getInt(jData, "primaryKey", "id");
                if (id == null) {
                    throw new Exception("请求参数中缺少主键！");
                }
                String tableName = jData.getString("tableName");
                theIdNameTableManager.delete(session, tableName, id);
                content = id.toString();
            } else if (dataManager instanceof CodeNameTableManager) {
                CodeNameTableManager theCodeNameTableManager = (CodeNameTableManager) dataManager;
                String code = JsonHelper.getString(jData, "primaryKey", "code");
                String tableName = jData.getString("tableName");
                theCodeNameTableManager.delete(session, tableName, code);
                content = code;
            } else if (dataManager instanceof CmsIdNameTableManager) {
                CmsIdNameTableManager theCmsIdNameTableManager = (CmsIdNameTableManager) dataManager;
                Integer id = JsonHelper.getInt(jData, "primaryKey", "id");
                if (id == null) {
                    throw new Exception("请求参数中缺少主键！");
                }
                String tableName = jData.getString("tableName");
                theCmsIdNameTableManager.delete(session, tableName, id);
                content = id.toString();
            } else if (dataManager instanceof CmsCodeNameTableManager) {
                CmsCodeNameTableManager theCmsCodeNameTableManager = (CmsCodeNameTableManager) dataManager;
                String code = JsonHelper.getString(jData, "primaryKey", "code");
                String tableName = jData.getString("tableName");
                theCmsCodeNameTableManager.delete(session, tableName, code);
                content = code;
            } else if (dataManager instanceof CmsIdNameTreeManager) {
                CmsIdNameTreeManager theCmsIdNameTreeManager = (CmsIdNameTreeManager) dataManager;
                Integer id = JsonHelper.getInt(jData, "primaryKey", "id");
                if (id == null) {
                    throw new Exception("请求参数中缺少主键！");
                }
                String tableName = jData.getString("tableName");
                theCmsIdNameTreeManager.delete(session, tableName, id);
                content = id.toString();
            } else if (dataManager instanceof CmsCodeNameTreeManager) {
                CmsCodeNameTreeManager theCmsCodeNameTreeManager = (CmsCodeNameTreeManager) dataManager;
                String code = JsonHelper.getString(jData, "primaryKey", "code");
                String tableName = jData.getString("tableName");
                theCmsCodeNameTreeManager.delete(session, tableName, code);
                content = code;
            } else if (dataManager instanceof IdDataManager) {
                // IdDataManager idDataManager = (IdDataManager) dataManager;
                Integer id = JsonHelper.getInt(jData, "primaryKey", "id");
                if (id == null) {
                    throw new Exception("请求参数中缺少主键！");
                }
                Method deleteMethod = dataManager.getClass().getMethod("delete", Class.forName("org.apache.ibatis.session.SqlSession"), Class.forName("java.lang.Integer"));
                deleteMethod.invoke(dataManager, session, id);
                content = id.toString();
            } else if (dataManager instanceof CodeDataManager) {
                //CodeDataManager theCodeDataManager = (CodeDataManager) dataManager;
                String code = JsonHelper.getString(jData, "primaryKey", "code");
                Method deleteMethod = dataManager.getClass().getMethod("delete", Class.forName("org.apache.ibatis.session.SqlSession"), Class.forName("java.lang.String"));
                deleteMethod.invoke(dataManager, session, code);
                content = code;
            } else {
                DataEntity newDataEntity = ServiceHandlerHelper.getDataEntity(request, this.getConfigNode(), dataManager,
                        false);
                dataManager.delete(session, newDataEntity);
            }
            //添加操作日志
            ServiceHandlerHelper.addSysOperationLog(session, OperationTypeCodes.Delete, request, dataManager, content);
            MyBatisManager.getInstance().commitSession(session);
            this.writeSuccessResponse(response);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            this.writeErrorResponse(response, ex.getMessage());
        }

    }

    /**
     * 定制删除
     *
     * @param response
     * @param jData
     * @throws IOException
     * @throws Exception
     */
    private void customDelete(HttpServletResponse response, JSONObject jData) throws IOException, Exception {
        String tableName = JsonHelper.getString(jData, "tableName");
        String condition = JsonHelper.getString(jData, "condition");
        String sql = "delete from " + tableName;
        if (condition != null && !condition.equals("")) {
            sql += " where " + condition;
        }
        int result = MyBatisManager.getInstance().executeDelete(sql);
        this.writeSuccessResponse(response, result);

    }
}
