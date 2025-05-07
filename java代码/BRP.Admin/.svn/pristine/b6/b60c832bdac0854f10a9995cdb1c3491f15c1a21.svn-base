package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.model.BookSkuAgeGroupManager;
import BRP.model.BookSkuBookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改SKU
 */
public class UpdateBookSkuServiceHandler extends UpdateServiceHandler {
    /**
     * 增加后处理函数
     *
     * @param context
     */
    @Override
    protected void postProcess(ServiceContext context) throws Exception {
        HttpServletRequest request = context.getRequest();
        JSONObject jData = this.getRequestData(request);
        JSONArray bookSkuAgeGroupList = JsonHelper.getJSONArray(jData, "ageGroupSelectValue");
        BookSku theBookSku = (BookSku) context.getDataEntity();
        SqlSession sqlSession = context.session;

        if (bookSkuAgeGroupList != null) {
            BookSkuAgeGroupManager.getInstance().deleteByBookSkuId(sqlSession, theBookSku.getId());
            List<BookSkuAgeGroup> newBookSkuAgeGroupList = new ArrayList<>();
            for (int i = 0; i < bookSkuAgeGroupList.size(); i++) {
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                Integer id = bookSkuAgeGroupList.getInt(i);
                newBookSkuAgeGroup.setAgeGroupId(id);
                newBookSkuAgeGroup.setBookSkuId(theBookSku.getId());
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            BookSkuAgeGroupManager.getInstance().addList(sqlSession, newBookSkuAgeGroupList);
        }

        JSONArray bookTagSelectValueList = JsonHelper.getJSONArray(jData, "bookTagSelectValue");
        if (bookTagSelectValueList != null) {
            BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession, theBookSku.getId());
            List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
            for (int i = 0; i < bookTagSelectValueList.size(); i++) {
                BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                Integer bookTagId = bookTagSelectValueList.getInt(i);
                newBookSkuBookTag.setBookTagId(bookTagId);
                newBookSkuBookTag.setBookSkuId(theBookSku.getId());
                newBookSkuBookTagList.add(newBookSkuBookTag);
            }
            BookSkuBookTagManager.getInstance().addList(sqlSession, newBookSkuBookTagList);
        }

    }
}
