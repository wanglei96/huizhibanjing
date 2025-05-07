package BRP.service;

import BRP.data.AgeGroup;
import BRP.data.ViewBookSkuInfo;
import BRP.model.BookManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UpdateBookBookcaseNameServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer bookId = JsonHelper.getInt(jData, "bookId");
        String bookcaseName = JsonHelper.getString(jData, "bookcaseName");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookManager.getInstance().updateBookBookcaseName(bookId, bookcaseName,companyId);
        writeSuccessResponse(response);
    }
}
