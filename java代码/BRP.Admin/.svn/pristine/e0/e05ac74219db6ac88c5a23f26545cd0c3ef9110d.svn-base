package strosoft.app.common;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.data.DataEntity;

public class DataMaster {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	private String dataName;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	/**
	 * 取得数据实体列表
	 * 
	 * @param condition
	 * @return
	 * @throws IOException
	 */
	public List<DataEntity> getDataEntityList(String condition) throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			List<DataEntity> dataEntityList = getDataEntityList(sqlSession, condition);
			return dataEntityList;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 取得数据实体列表
	 * 
	 * @param sqlSession
	 * @param condition
	 * @return
	 * @throws IOException
	 */
	public List<DataEntity> getDataEntityList(SqlSession sqlSession, String condition) throws IOException {
		String typeName = this.getTypeName();
		String sqlCommandID = typeName + ".select" + typeName + "ByCondition";
		List<DataEntity> dataEntityList = sqlSession.selectList(sqlCommandID, condition);
		return dataEntityList;
	}
}
