package BRP.service;

import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 更换用户线上所借书籍
 */
public class ChangeMemberBorrowBookServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String memberCardNo = JsonHelper.getString(jData, "memberCardNo");
        String oldBookSn = JsonHelper.getString(jData, "oldBookSn");
        String newBookSn = JsonHelper.getString(jData, "newBookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookOrderItemManager.getInstance().changeMemberBorrowBook(companyId, memberCardNo, oldBookSn, newBookSn);
        this.writeSuccessResponse(response);
    }
}
