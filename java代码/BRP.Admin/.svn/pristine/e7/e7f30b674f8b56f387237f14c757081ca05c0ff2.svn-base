package strosoft.sys.model;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;
import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

/*
 * 审核管理类
 */
public class ApproveManager extends IdDataManager {
	private static ApproveManager _instance;

	public static ApproveManager getInstance() {
		if (_instance == null) {
			_instance = new ApproveManager();
		}
		return _instance;
	}

	private ApproveManager() {
	}

	/*
	 * 审核通过
	 */
	public int approve(String tableName, String primaryKeyFieldName, String primaryKeyValue) throws Exception {
		String sql = String.format("update %s set ApproveStatusCode='Approved' where %s='%s'", tableName,
				primaryKeyFieldName, primaryKeyValue);
		return MyBatisManager.getInstance().executeUpdate(sql);
	}

	/**
	 * 审核通过
	 * 
	 * @param newApproveInfo
	 * @return
	 * @throws Exception
	 */
	public int approve(ApproveInfo newApproveInfo) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		newApproveInfo.setSqlSession(sqlSession);
		try {
			int result = approve(sqlSession, newApproveInfo);
			MyBatisManager.getInstance().commitSession(sqlSession);
			return result;
		} catch (Exception ex) {
			MyBatisManager.getInstance().rollbackSession(sqlSession);
			throw ex;
		} finally {
			MyBatisManager.getInstance().closeSession(sqlSession);
		}
	}

	/**
	 * 审核通过
	 * 
	 * @param sqlSession
	 * @param newApproveInfo
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public int approve(SqlSession sqlSession, ApproveInfo newApproveInfo) throws IOException, Exception {
		String sql = String.format(
				"update %s set %s = '%s',approve_time=now(),approver_id=%s,approve_content='%s' where %s = '%s'",
				newApproveInfo.getTableName(), newApproveInfo.getApproveStatusCodeFieldName(),
				newApproveInfo.getStatusCodeApproved(), newApproveInfo.getApproverId(),
				newApproveInfo.getApproveContent(), newApproveInfo.getPrimaryKeyFieldName(),
				newApproveInfo.getPrimaryKeyValue());
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/**
	 * 审核未通过
	 * 
	 * @param newApproveInfo
	 * @return
	 * @throws Exception
	 */
	public int reject(ApproveInfo newApproveInfo) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		newApproveInfo.setSqlSession(sqlSession);
		try {
			int result = reject(sqlSession, newApproveInfo);
			MyBatisManager.getInstance().commitSession(sqlSession);
			return result;
		} catch (Exception ex) {
			MyBatisManager.getInstance().rollbackSession(sqlSession);
			throw ex;
		} finally {
			MyBatisManager.getInstance().closeSession(sqlSession);
		}
	}

	/**
	 * 审核未通过
	 * 
	 * @param sqlSession
	 * @param newApproveInfo
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public int reject(SqlSession sqlSession, ApproveInfo newApproveInfo) throws IOException, Exception {
		String sql = String.format(
				"update %s set %s = '%s',approve_time=now(),approver_id=%s,approve_content='%s' where %s = '%s'",
				newApproveInfo.getTableName(), newApproveInfo.getApproveStatusCodeFieldName(),
				newApproveInfo.getStatusCodeRejected(), newApproveInfo.getApproverId(),
				newApproveInfo.getApproveContent(), newApproveInfo.getPrimaryKeyFieldName(),
				newApproveInfo.getPrimaryKeyValue());
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/**
	 * 取消审核
	 * 
	 * @param newApproveInfo
	 * @return
	 * @throws Exception
	 */
	public int cancelApprove(ApproveInfo newApproveInfo) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		newApproveInfo.setSqlSession(sqlSession);
		try {
			int result = cancelApprove(sqlSession, newApproveInfo);
			MyBatisManager.getInstance().commitSession(sqlSession);
			return result;
		} catch (Exception ex) {
			MyBatisManager.getInstance().rollbackSession(sqlSession);
			throw ex;
		} finally {
			MyBatisManager.getInstance().closeSession(sqlSession);
		}
	}

	/**
	 * 取消审核
	 * 
	 * @param sqlSession
	 * @param newApproveInfo
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public int cancelApprove(SqlSession sqlSession, ApproveInfo newApproveInfo) throws IOException, Exception {
		String sql = String.format(
				"update %s set %s = '%s',approve_time=now(),approver_id=%s,approve_content='%s' where %s = '%s'",
				newApproveInfo.getTableName(), newApproveInfo.getApproveStatusCodeFieldName(),
				newApproveInfo.getStatusCodeCancelled(), newApproveInfo.getApproverId(),
				newApproveInfo.getApproveContent(), newApproveInfo.getPrimaryKeyFieldName(),
				newApproveInfo.getPrimaryKeyValue());
		int result = MyBatisManager.getInstance().executeUpdate(sql);
		return result;
	}

	/**
	 * 取得审核信息
	 * 
	 * @param jData
	 * @return
	 */
	public ApproveInfo getApproveInfo(JSONObject jData) {
		ApproveInfo newApproveInfo = new ApproveInfo();
		// 表名
		String tableName = JsonHelper.getString(jData, "tableName");
		newApproveInfo.setTableName(tableName);
		// 主键字段名称
		String primaryKeyFieldName = JsonHelper.getString(jData, "primaryKeyFieldName");
		if (StringHelper.isNullOrEmpty(primaryKeyFieldName)) {
			primaryKeyFieldName = "id";
		}
		newApproveInfo.setPrimaryKeyFieldName(primaryKeyFieldName);
		// 审核人ID
		newApproveInfo.setApproverId(JsonHelper.getInt(jData, "approverId"));
		// 审核意见
		newApproveInfo.setApproveContent(JsonHelper.getString(jData, "approveContent"));
		// 主键
		String primaryKeyValue = JsonHelper.getString(jData, "primaryKeyValue", "id", "code");
		newApproveInfo.setPrimaryKeyValue(primaryKeyValue);
		// 审核状态字段
		String approveStatusCodeFieldName = JsonHelper.getString(jData, "approveStatusCodeFieldName");

		if (!StringHelper.isNullOrEmpty(approveStatusCodeFieldName)) {
			newApproveInfo.setApproveStatusCodeFieldName(approveStatusCodeFieldName);
		}
		// 审核通过后状态
		String statusCodeApproved = JsonHelper.getString(jData, "statusCodeApproved");
		if (!StringHelper.isNullOrEmpty(statusCodeApproved)) {
			newApproveInfo.setStatusCodeApproved(statusCodeApproved);
		}
		// 审核未通过后状态
		String statusCodeRejected = JsonHelper.getString(jData, "statusCodeRejected");
		if (!StringHelper.isNullOrEmpty(statusCodeRejected)) {
			newApproveInfo.setStatusCodeRejected(statusCodeRejected);
		}
		// 审核通过后状态
		String statusCodeCancelled = JsonHelper.getString(jData, "statusCodeCancelled");
		if (!StringHelper.isNullOrEmpty(statusCodeCancelled)) {
			newApproveInfo.setStatusCodeCancelled(statusCodeCancelled);
		}
		return newApproveInfo;
	}

}
