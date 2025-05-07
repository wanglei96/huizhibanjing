package strosoft.cms.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.IdDataManager;
import strosoft.cms.data.CmsTemplateTag;
import strosoft.sys.data.SysBasicTable;

/*
 * 数据层
 */
public class CmsTemplateTagManager extends IdDataManager{
	private static CmsTemplateTagManager _instance;

	public static CmsTemplateTagManager getInstance() {
		if (_instance == null) {
			_instance = new CmsTemplateTagManager();
		}
		return _instance;
	}

	private CmsTemplateTagManager() {
		this.setTableName("cms_template_tag");
		this.setTypeName("CmsTemplateTag");
	}

	/*
	 * 取得实体
	 */
	public CmsTemplateTag getEntity(int cmsTemplateId) throws Exception {
		return (CmsTemplateTag)super.getDataEntity(cmsTemplateId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CmsTemplateTag> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	
}
