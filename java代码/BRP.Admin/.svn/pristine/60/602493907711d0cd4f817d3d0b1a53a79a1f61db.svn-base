package BRP.service;

import BRP.data.*;
import BRP.model.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 添加线上还书
 */
public class AddReturnBookOrderServiceHandler extends ServiceHandler {


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String returnBookSn = JsonHelper.getString(jData, "returnBookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        ReturnBookOrderInfo theReturnBookOrderInfo = BookOrderManager.getInstance().addReturnBookOrder(companyId, returnBookSn);
        this.writeSuccessResponse(response, theReturnBookOrderInfo);
    }
}
