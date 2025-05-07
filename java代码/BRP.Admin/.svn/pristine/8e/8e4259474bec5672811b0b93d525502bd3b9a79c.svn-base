package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BatchOnOffBookServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> bookIds = JsonHelper.getIntArray(jData, "bookIds");
        String bookStatusCode = JsonHelper.getString(jData, "bookStatusCode");
        String bookIdsString = bookIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String sql = String.format("UPDATE book SET book_status_code='%s' WHERE id in (%s)",
                bookStatusCode, bookIdsString);
        MyBatisManager.getInstance().executeUpdate(sql);
        this.writeSuccessResponse(response);
    }
}
