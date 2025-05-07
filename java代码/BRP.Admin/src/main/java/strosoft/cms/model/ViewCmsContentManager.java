package strosoft.cms.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.data.ViewCmsContent;
import strosoft.cms.data.ViewCmsContentMaster;

public class ViewCmsContentManager extends ViewCmsContentMaster {

	private static ViewCmsContentManager _instance;

	public static ViewCmsContentManager getInstance() {
		if (_instance == null) {
			_instance = new ViewCmsContentManager();
		}
		return _instance;
	}

	private ViewCmsContentManager() {
		this.setViewName("view_cms_conent");
		this.setTypeName("ViewCmsContent");
	}

	/**
	 * 取得实体
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ViewCmsContent getEntity(int id) throws Exception {
		String condition = "id=" + id;
		List<ViewCmsContent> lstViewCmsContent = this.getList(condition);
		if(lstViewCmsContent.size()> 0){
			return lstViewCmsContent.get(0);
		}
		return null;
	}
}
