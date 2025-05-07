package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.model.BookSkuAgeGroupManager;
import BRP.model.BookSkuBookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UpdateBookAndSkuPriceServiceHandler extends UpdateServiceHandler {
    /**
     * 增加后处理函数
     *
     * @param context
     */
    @Override
    protected void postProcess(ServiceContext context) throws Exception {
        HttpServletRequest request = context.getRequest();
        JSONObject jData = this.getRequestData(request);
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        BigDecimal price = JsonHelper.getDecimal(jData, "price");
        String sql = String.format("update book_sku set price =%s where id =%s", price, bookSkuId);
        MyBatisManager.getInstance().executeUpdate(context.getSession(),sql);
    }
}
