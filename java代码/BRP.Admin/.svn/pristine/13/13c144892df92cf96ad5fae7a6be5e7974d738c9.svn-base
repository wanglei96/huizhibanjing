package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.data.DataEntity;

public class ServiceContext {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DataEntity dataEntity;
	public SqlSession session;
	public int newId;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public int getNewId() {
		return newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public DataEntity getDataEntity() {
		return dataEntity;
	}

	public void setDataEntity(DataEntity dataEntity) {
		this.dataEntity = dataEntity;
	}
}
