package BRP.service;

import BRP.data.BorrowReturnBookInfo;
import BRP.data.HomeNewBook;
import BRP.data.ViewHomeNewBook;
import BRP.model.CompanySettingManager;
import BRP.model.HomeNewBookManager;
import BRP.model.ViewHomeNewBookManager;
import BRP.model.ViewMemberCardManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetHomeNewBookShowListServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        List<ViewHomeNewBook> lstViewHomeNewBook;
        if (companyId == null) {
            //lstViewHomeNewBook = ViewHomeNewBookManager.getInstance().getList();
            String sql = "SELECT id FROM company where is_default is true";
            companyId = MyBatisManager.getInstance().executeInt(sql);
        }
        //根据companyId获取数据
        String homeBookShowModeValue = CompanySettingManager.
                getInstance().getCompanySettingValueByCode(companyId, "HomeBookShowMode");
        if ("Manual".equals(homeBookShowModeValue)) {
            String condition = "company_id = " + companyId;
            lstViewHomeNewBook = ViewHomeNewBookManager.getInstance().getList(condition);
        } else {
            String quantityValue = CompanySettingManager.
                    getInstance().getCompanySettingValueByCode(companyId, "HomeBookShowQuantity");
            String isAvailableValue = CompanySettingManager.
                    getInstance().getCompanySettingValueByCode(companyId, "HomeBookShowIsAvailable");
            //获取最新的上架图书
            String sql = String.format("" +
                            "SELECT\n" +
                            "\tbook_sku_id,book_name as book_sku_book_name,image_file_path,small_image_file_path\n" +
                            "FROM\n" +
                            "\t( SELECT * FROM view_book where company_id=%d  and is_online is true  %s ORDER BY create_time DESC,book_name ) AS sorted_books \n" +
                            "GROUP BY\n" +
                            "\tbook_sku_id,book_sku_book_name,image_file_path,small_image_file_path\n" +
                            "ORDER BY\n" +
                            "\tMAX(create_time) DESC" +
                            "\tLIMIT %d;", companyId,
                    "false".equals(isAvailableValue) ? "and is_available is true  AND book_status_code='On' " : ""
                    , Integer.valueOf(quantityValue));
            ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
            lstViewHomeNewBook = JsonHelper.toJavaList(ViewHomeNewBook.class, alData);

        }
        this.writeSuccessResponse(response, lstViewHomeNewBook);
    }
}
