package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteBookSkuBookTagByBookSkuIdsServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer bookTagId = JsonHelper.getInt(jData, "bookTagId");
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        String strBookSkuIds = bookSkuIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String sql = "DELETE FROM book_sku_book_tag WHERE book_tag_id = " + bookTagId + " AND book_sku_id IN (" + strBookSkuIds + ")";
        MyBatisManager.getInstance().executeDelete(sql);
        this.writeSuccessResponse(response);
    }
}
