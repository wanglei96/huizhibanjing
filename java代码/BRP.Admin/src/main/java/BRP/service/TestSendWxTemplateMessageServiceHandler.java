package BRP.service;

import BRP.data.Member;
import BRP.data.ViewBookOrder;
import BRP.model.MemberManager;
import BRP.model.MessageManager;
import BRP.model.TianApiUtils;
import BRP.model.ViewBookOrderManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class TestSendWxTemplateMessageServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookOrderId = JsonHelper.getInt(jData, "bookOrderId");
        ViewBookOrder theViewBookOrder = ViewBookOrderManager.getInstance().getEntityById(bookOrderId);
        String result = MessageManager.getInstance().sendWxTemplateMessage(theViewBookOrder);
        this.writeSuccessResponse(response, result);
    }


}
