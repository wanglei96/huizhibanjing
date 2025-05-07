package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.TableDataMaster;
import strosoft.app.common.IdDataManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.XmlDocumentHelper;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.model.SysBasicTableManager;

/*
 * 删除服务处理类
 */
public class DeleteSysBasicTableServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int id = JsonHelper.getInt(jData, "primaryKey", "id");
		
		SysBasicTable theSysBasicTable = SysBasicTableManager.getInstance().getEntity(id);
		SysBasicTableManager.getInstance().delete(id);
		this.writeSuccessResponse(response);

	}

}
