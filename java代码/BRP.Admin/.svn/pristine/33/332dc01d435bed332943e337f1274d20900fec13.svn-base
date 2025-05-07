package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuReviseItem;
import BRP.data.SalesOrderItem;
import BRP.data.ViewBookSkuReviseItem;
import BRP.model.BookSkuManager;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateBookSkuReviseServiceHandler extends UpdateServiceHandler {
    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        SqlSession session = updateServiceContext.getSession();
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        List<ViewBookSkuReviseItem> bookSkuReviseItemList = JsonHelper.getJavaList(ViewBookSkuReviseItem.class, jData, "bookSkuReviseItemList");
        if (bookSkuReviseItemList != null && bookSkuReviseItemList.size() > 0) {
            BookSku theBookSku = BookSkuManager.getInstance().getEntity(session, bookSkuId);
            // 构建UPDATE语句
            String updateStatement = "UPDATE book_sku SET " +
                    bookSkuReviseItemList.stream()
                            .map(item -> item.getBookSkuFieldCode() + " = '" + item.getNewValue() + "'")
                            .collect(Collectors.joining(", "));
            //构造查询条件
            /*String condition = String.format("ISBN='%s' and book_name='%s' and author='%s' and press='%s'",
                    theBookSku.getISBN(), theBookSku.getBookName(), theBookSku.getAuthor(), theBookSku.getPress());*/
            String condition = String.format("id=%d or platform_book_sku_id=%d",
                    theBookSku.getPlatformBookSkuId(), theBookSku.getPlatformBookSkuId());
            String updateBookSkuSql = updateStatement + " WHERE " + condition;
            MyBatisManager.getInstance().executeUpdate(session, updateBookSkuSql);

            String ids = bookSkuReviseItemList.stream()
                    .map(ViewBookSkuReviseItem::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            String updateBookSkuReviseItemSql = String.format("UPDATE book_sku_revise_item SET approve_status_code='Approved' WHERE id in (%s)", ids);
            MyBatisManager.getInstance().executeUpdate(session, updateBookSkuReviseItemSql);
        }
    }
}
