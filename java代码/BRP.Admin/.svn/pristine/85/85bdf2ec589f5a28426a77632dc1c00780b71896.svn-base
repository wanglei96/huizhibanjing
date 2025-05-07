package BRP.service;

import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.model.BookSkuAgeGroupManager;
import BRP.model.BookSkuBookTagManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddBookSkuServiceHandler extends AddServiceHandler {
    /**
     * 增加后处理函数
     *
     * @param newAddServiceContext
     */
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        JSONArray bookSkuAgeGroupList = JsonHelper.getJSONArray(jData, "ageGroupSelectValue");
        if (bookSkuAgeGroupList != null) {
            List<BookSkuAgeGroup> newBookSkuAgeGroupList = new ArrayList<>();
            for (int i = 0; i < bookSkuAgeGroupList.size(); i++) {
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                Integer id = bookSkuAgeGroupList.getInt(i);
                newBookSkuAgeGroup.setAgeGroupId(id);
                newBookSkuAgeGroup.setBookSkuId(newAddServiceContext.getNewId());
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            BookSkuAgeGroupManager.getInstance().addList(newAddServiceContext.getSession(), newBookSkuAgeGroupList);
        }

        JSONArray bookTagSelectValueList = JsonHelper.getJSONArray(jData, "bookTagSelectValue");
        if (bookTagSelectValueList != null) {
            List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
            for (int i = 0; i < bookTagSelectValueList.size(); i++) {
                BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                Integer bookTagId = bookTagSelectValueList.getInt(i);
                newBookSkuBookTag.setBookTagId(bookTagId);
                newBookSkuBookTag.setBookSkuId(newAddServiceContext.getNewId());
                newBookSkuBookTagList.add(newBookSkuBookTag);
            }
            BookSkuBookTagManager.getInstance().addList(newAddServiceContext.getSession(), newBookSkuBookTagList);
        }

        Integer bookCount = JsonHelper.getInt(jData, "bookCount");
    }
}
