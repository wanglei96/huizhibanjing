package BRP.service;

import BRP.data.HomeNewBook;
import BRP.data.HomeNewBookMaster;
import BRP.model.HomeNewBookManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AddHomeNewBookServiceHandler extends AddServiceHandler {
    /**
     * 处理数据实体
     *
     * @param request
     * @param response
     * @param newDataEntity
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        JSONObject requestData = getRequestData(newAddServiceContext.getRequest());
        Integer companyId = JsonHelper.getInt(requestData, "companyId");
        HomeNewBook newHomeNewBook = (HomeNewBook) newDataEntity;
        MySqlConditionBuilder mb = new MySqlConditionBuilder();
        mb.addEqualCondition("book_sku_id", newHomeNewBook.getBookSkuId());
        mb.addEqualCondition("company_id", companyId);
        List<HomeNewBook> listByBookSkuId = HomeNewBookManager.getInstance().getList(newAddServiceContext.session, mb.toString());
        if (!listByBookSkuId.isEmpty()) {
            throw new Exception("不能重复添加该图书");
        }

        return newDataEntity;
    }
}
