package BRP.service;

import BRP.data.BookSkuAgeGroup;
import BRP.model.BookSkuAgeGroupManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddBookSkuAgeGroupByBookSkuIdsServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        List<Integer> bookSkuIds = JsonHelper.getIntArray(jData, "bookSkuIds");
        List<BookSkuAgeGroup> bookSkuAgeGroups = new ArrayList<>();
        for(Integer bookSkuId : bookSkuIds){
            BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
            newBookSkuAgeGroup.setBookSkuId(bookSkuId);
            newBookSkuAgeGroup.setAgeGroupId(ageGroupId);
            bookSkuAgeGroups.add(newBookSkuAgeGroup);
        }
        BookSkuAgeGroupManager.getInstance().addList(bookSkuAgeGroups);
        this.writeSuccessResponse(response);
    }
}
