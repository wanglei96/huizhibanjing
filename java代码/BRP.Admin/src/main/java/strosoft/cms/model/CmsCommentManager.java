package strosoft.cms.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import strosoft.app.common.IdDataManager;
import strosoft.cms.data.CmsComment;
import strosoft.cms.data.CmsContent;

public class CmsCommentManager extends IdDataManager{

	private static CmsCommentManager _instance;

	public static CmsCommentManager getInstance() {
		if (_instance == null) {
			_instance = new CmsCommentManager();
		}
		return _instance;
	}

	private CmsCommentManager() {
		this.setTableName("cms_comment");
		this.setTypeName("CmsComment");
	}

	/*
	 * 取得实体
	 */
	public CmsComment getEntity(int cmsCommentId) throws Exception {
		return (CmsComment)super.getDataEntity(cmsCommentId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CmsComment> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}


	
}
