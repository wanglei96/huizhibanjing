package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuBookTag;
import BRP.model.BookSkuBookTagManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddPlatformBookTagServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer bookTagId = JsonHelper.getInt(jData, "bookTagId");
        Integer newbookTagId = JsonHelper.getInt(jData, "newbookTagId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String condition = String.format("id IN (SELECT book_sku_id FROM book_sku_book_tag WHERE book_tag_id = %d)", bookTagId);
            List<BookSku> lstBookSku = BookSkuManager.getInstance().getList(sqlSession, condition);
            String strlstBookSku = lstBookSku.stream()
                    .map(bookSku -> String.valueOf(bookSku.getId()))
                    .collect(Collectors.joining(","));
            //删除SKU下标签
            String sql = String.format(" delete from book_sku_book_tag where book_sku_id in (%s)", strlstBookSku);
            MyBatisManager.getInstance().executeDelete(sqlSession, sql);
            List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
            for (BookSku bookSku : lstBookSku) {
                BookSkuBookTag bookSkuBookTag = new BookSkuBookTag();
                bookSkuBookTag.setBookSkuId(bookSku.getPlatformBookSkuId());
                bookSkuBookTag.setBookTagId(newbookTagId);
                newBookSkuBookTagList.add(bookSkuBookTag);
            }
            BookSkuBookTagManager.getInstance().addList(sqlSession, newBookSkuBookTagList);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
