package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BatchUpdateBookSkuIsOnlineServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        Integer isOnline = JsonHelper.getInt(jData, "isOnline");
        String bookSkuIdsString = bookSkuIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String sql = String.format("UPDATE book_sku SET is_online=%s WHERE id in (%s)",
                isOnline, bookSkuIdsString);
        MyBatisManager.getInstance().executeUpdate(sql);
        this.writeSuccessResponse(response);
    }
}
