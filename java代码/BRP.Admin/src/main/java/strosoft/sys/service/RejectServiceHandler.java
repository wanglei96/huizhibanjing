package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.ApproveInfo;
import strosoft.sys.model.ApproveManager;

/*
 * 审核未通过服务处理类
 */
public class RejectServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		ApproveInfo newApproveInfo = ApproveManager.getInstance().getApproveInfo(jData);
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		newApproveInfo.setSqlSession(sqlSession);
		try {
			int result = ApproveManager.getInstance().reject(sqlSession, newApproveInfo);
			this.postProcess(newApproveInfo);
			MyBatisManager.getInstance().commitSession(sqlSession);
			this.writeSuccessResponse(response, result);
		} catch (Exception ex) {
			MyBatisManager.getInstance().rollbackSession(sqlSession);
			throw ex;
		} finally {
			MyBatisManager.getInstance().closeSession(sqlSession);
		}
	}

	/**
	 * 审核通过后处理
	 * 
	 * @param newApproveInfo
	 */
	protected void postProcess(ApproveInfo newApproveInfo) {

	}
}
