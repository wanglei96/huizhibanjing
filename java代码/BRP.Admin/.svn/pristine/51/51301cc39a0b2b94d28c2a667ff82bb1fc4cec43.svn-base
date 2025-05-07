package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BatchAddBookBookcaseIdServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer bookcaseId = JsonHelper.getInt(jData, "bookcaseId");
        List<Integer> bookIds = JsonHelper.getIntArray(jData, "bookIds");
        String bookIdsString = bookIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String sql = String.format("UPDATE book SET bookcase_id=%s WHERE id in (%s)", bookcaseId,
                bookIdsString);
        MyBatisManager.getInstance().executeUpdate(sql);
        this.writeSuccessResponse(response);
    }
}
