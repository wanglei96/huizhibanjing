package BRP.service;

import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加会员书袋
 */
public class AddMemberBookPocketServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        if (memberId != null) {
            String sql = String.format("UPDATE member\n" +
                    "SET book_pocket_quantity = IFNULL(book_pocket_quantity, 0) + 1\n" +
                    "WHERE id = %d;", memberId);
            MyBatisManager.getInstance().executeUpdate(sql);
        }
        this.writeSuccessResponse(response);
    }
}
