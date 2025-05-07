package BRP.service;

import BRP.data.Book;
import BRP.model.BookManager;
import BRP.model.BookOrderItemManager;
import BRP.model.BookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckMemberIsBorrowedServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        String borrowBookSn = JsonHelper.getString(jData, "borrowBookSn");
        if (!StringHelper.isNullOrEmpty(borrowBookSn)) {
            Book book = BookManager.getInstance().getEntityBySn(borrowBookSn, companyId);
            if (book == null) {
                throw new Exception("图书编号未找到图书请输入正确编号");
            }
            bookSkuId = book.getBookSkuId();
        }
        String sql = String.format("SELECT count(1)\n" +
                "FROM \n" +
                "    book_order_item\n" +
                "LEFT JOIN \n" +
                "    book_order ON book_order.id = book_order_item.book_order_id\n" +
                "LEFT JOIN \n" +
                "    book ON book.id = book_order_item.book_id\n" +
                "WHERE \n" +
                "    book_order.member_id = %d\n" +
                "    AND book.book_sku_id = %d\n" +
                "\t\tAND book_order_item.book_order_type_code = 'Borrow'", memberId, bookSkuId);
        Integer count = MyBatisManager.getInstance().executeInt(sql);
        if (count > 0) {
            this.writeSuccessResponse(response, true);
        } else {
            this.writeSuccessResponse(response, false);
        }
    }
}
