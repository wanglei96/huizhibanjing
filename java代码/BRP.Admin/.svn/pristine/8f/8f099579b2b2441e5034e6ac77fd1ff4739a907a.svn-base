package BRP.service;

import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.model.BookSkuAgeGroupManager;
import BRP.model.BookSkuBookTagManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddBookSkuBookTagByBookSkuIdsServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer bookTagId = JsonHelper.getInt(jData, "bookTagId");
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        List<BookSkuBookTag> lstBookSkuBookTag = new ArrayList<>();
        for(Integer bookSkuId : bookSkuIds){
            BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
            newBookSkuBookTag.setBookSkuId(bookSkuId);
            newBookSkuBookTag.setBookTagId(bookTagId);
            lstBookSkuBookTag.add(newBookSkuBookTag);
        }
        BookSkuBookTagManager.getInstance().addList(lstBookSkuBookTag);
        this.writeSuccessResponse(response);
    }
}
