package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AddScanBookInOrderServiceHandler extends ServiceHandler {

    private ViewBookInOrder theViewBookInOrder;

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String bookSn = JsonHelper.getString(jData, "bookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Boolean isEnabled = JsonHelper.getBoolean(jData, "isEnabled");
        ViewBookInOrder theViewBookInOrder = BookInOrderManager.getInstance().addBookInOrder(companyId, bookSn, isEnabled);
        this.writeSuccessResponse(response, theViewBookInOrder);
    }

}
