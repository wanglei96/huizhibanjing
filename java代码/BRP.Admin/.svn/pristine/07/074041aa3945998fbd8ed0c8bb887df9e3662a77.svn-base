package strosoft.sys.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.sys.data.SysSetting;
import strosoft.sys.model.SysSettingManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SaveSysSettingsServiceHandler extends ServiceHandler {
	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		// 存储List数组实体
		ArrayList<SysSetting> lstSysSetting = new ArrayList<SysSetting>();
		// 获取前传来的数据
		JSONArray jData = JSONArray.fromObject(data);
		// 循环获取数据添加到lstSysSetting中
		for (int i = 0; i < jData.size(); i++) {
			JSONObject jSysSetting = (JSONObject) jData.get(i);
			String code = (String) jSysSetting.get("code");
			SysSetting newSysSetting = SysSettingManager.getInstance()
					.getEntity(code);
			newSysSetting.setValue((String) jSysSetting.get("value"));
			lstSysSetting.add(newSysSetting);
		}
		SysSettingManager.getInstance().update(lstSysSetting);
		this.writeSuccessResponse(response);
	}
}
