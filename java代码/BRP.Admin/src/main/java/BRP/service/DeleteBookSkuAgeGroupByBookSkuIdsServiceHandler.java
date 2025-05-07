package BRP.service;

import BRP.data.BookOrderItem;
import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteBookSkuAgeGroupByBookSkuIdsServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        String strBookSkuIds = bookSkuIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String sql = "DELETE FROM book_sku_age_group WHERE age_group_id = " + ageGroupId + " AND book_sku_id IN (" + strBookSkuIds + ")";
        MyBatisManager.getInstance().executeDelete(sql);
        this.writeSuccessResponse(response);
    }
}
