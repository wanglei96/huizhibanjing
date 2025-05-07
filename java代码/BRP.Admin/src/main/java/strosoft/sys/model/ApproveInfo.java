package strosoft.sys.model;

import org.apache.ibatis.session.SqlSession;

/**
 * 审核信息
 * 
 * @author Administrator
 *
 */
public class ApproveInfo {
	private Integer approverId;
	private String tableName;
	private String primaryKeyFieldName;
	private String primaryKeyValue;
	private String approveStatusCodeFieldName = "approve_status_code";
	private String statusCodeApproved = "Approved";
	private String statusCodeRejected = "Rejected";
	private String statusCodeCancelled = "ApproveCancelled";
	private SqlSession sqlSession;

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public String getApproveContent() {
		return approveContent;
	}

	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKeyFieldName() {
		return primaryKeyFieldName;
	}

	public void setPrimaryKeyFieldName(String primaryKeyFieldName) {
		this.primaryKeyFieldName = primaryKeyFieldName;
	}

	public String getPrimaryKeyValue() {
		return primaryKeyValue;
	}

	public void setPrimaryKeyValue(String primaryKeyValue) {
		this.primaryKeyValue = primaryKeyValue;
	}

	public String getApproveStatusCodeFieldName() {
		return approveStatusCodeFieldName;
	}

	public void setApproveStatusCodeFieldName(String approveStatusCodeFieldName) {
		this.approveStatusCodeFieldName = approveStatusCodeFieldName;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public String getStatusCodeApproved() {
		return statusCodeApproved;
	}

	public void setStatusCodeApproved(String statusCodeApproved) {
		this.statusCodeApproved = statusCodeApproved;
	}

	public String getStatusCodeRejected() {
		return statusCodeRejected;
	}

	public void setStatusCodeRejected(String statusCodeRejected) {
		this.statusCodeRejected = statusCodeRejected;
	}

	public String getStatusCodeCancelled() {
		return statusCodeCancelled;
	}

	public void setStatusCodeCancelled(String statusCodeCancelled) {
		this.statusCodeCancelled = statusCodeCancelled;
	}

	private String approveContent;
	private Boolean approved;
}
