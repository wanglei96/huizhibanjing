package strosoft.sys.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.IdDataManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.*;
import strosoft.sys.model.SysBasicTableManager;
import strosoft.sys.model.SysBasicTableRelationManager;

import net.sf.json.*;
import strosoft.sys.data.*;
import strosoft.sys.*;

/*
 * 批量删除服务处理类
 */
public class BatchDeleteSysBasicTableServiceHandler extends ServiceHandler {

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
				"ids");
		SysBasicTableManager.getInstance().batchDelete(lstId);
		this.writeSuccessResponse(response);
	}
}
