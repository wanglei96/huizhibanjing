package BRP.service;

import BRP.data.CompanyUser;
import BRP.model.*;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配送单待配送的数量和拣书单待拣数量
 */
public class GetDeliverOrPickNumberServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        CompanyUser theCompanyUser = CompanyUserManager.getInstance().getEntity(delivererId);
        // 获取今天日期
        String today = DateHelper.getDateString();
        // 获取明天日期
        String tomorrow = DateHelper.toDateString(DateHelper.addDays(new Date(), 1));
        // 配送单待配送的数量和拣书单待拣数量

        String condition = JsonHelper.getString(jData, "condition");
        String strReturnButNotBorrowed = "";
        String returnButNotBorrowed = CompanySettingManager.getInstance().getCompanySettingValueByCode(theCompanyUser.getCompanyId(), "ReturnButNotBorrowed");
        if (!StringHelper.isNullOrEmpty(returnButNotBorrowed) && returnButNotBorrowed.equals("false")) {
            strReturnButNotBorrowed = " and EXISTS (SELECT 1 FROM book_order_item i WHERE i.book_order_id = book_order.id AND book_order_type_code = 'Borrow')";
        }

        int borrowNumber = BookOrderManager.getInstance().getCount("deliverer_id = " + delivererId + " and book_order_borrow_status_code = 'Delivering' and " + condition + strReturnButNotBorrowed);
        int returnNumber = MyBatisManager.getInstance().executeInt("select count(DISTINCT book_order_id) from view_book_out_order where bookpicker_id = " + delivererId + " and book_out_order_status_code = 'NotFound' and " + condition);
        int tomorrowReturnNumber = MyBatisManager.getInstance().executeInt("select count(DISTINCT book_order_id) from view_book_out_order where bookpicker_id = " + delivererId + " and book_out_order_status_code = 'NotFound' and DATE(deliver_time) = DATE(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) ");
        int returnTotalNumber = MyBatisManager.getInstance().executeInt("select count(DISTINCT book_order_id) from view_book_out_order where bookpicker_id = " + delivererId + " and book_out_order_status_code = 'NotFound'");
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("borrowNumber", borrowNumber);
        map.put("returnNumber", returnNumber);
        map.put("todayReturnNumber", returnTotalNumber);
        map.put("tomorrowReturnNumber", tomorrowReturnNumber);
        this.writeSuccessResponse(response, map);
    }
}
