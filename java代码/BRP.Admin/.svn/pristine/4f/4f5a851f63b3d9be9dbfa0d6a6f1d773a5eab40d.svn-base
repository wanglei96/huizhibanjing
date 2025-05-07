package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.data.ViewBookSkuBookTag;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SynchronizationPlatformBookSkuServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer bookSkuId = JsonHelper.getInt(jData, "id");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            BookSku theBookSku = BookSkuManager.getInstance().getEntity(sqlSession, bookSkuId);
            /*String condition = String.format("ISBN='%s' and book_name='%s' and author='%s' and press='%s'",
                    theBookSku.getISBN(), theBookSku.getBookName(), theBookSku.getAuthor(), theBookSku.getPress());
            String sql = String.format("update book_sku " +
                            " set book_name='%s',image_file_id=%d,author='%s',press='%s'," +
                            " price=%s,ISBN='%s',language_code='%s',binding_type_code='%s'," +
                            "introduction='%s',remark='%s' where %s",
                    theBookSku.getBookName(), theBookSku.getImageFileId(), theBookSku.getAuthor(),
                    theBookSku.getPress(), theBookSku.getPrice(), theBookSku.getISBN(),
                    theBookSku.getLanguageCode(), theBookSku.getBindingTypeCode(),
                    theBookSku.getIntroduction(), theBookSku.getRemark(), condition);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);*/
            //获取总部SKU年龄段
            List<BookSkuAgeGroup> lstBookSkuAgeGroup = BookSkuAgeGroupManager.getInstance().getList(sqlSession, String.format("book_sku_id=%d", bookSkuId));
            //获取总部SKU标签
            List<ViewBookSkuBookTag> lstBookSkuBookTag = ViewBookSkuBookTagManager.getInstance().getList(sqlSession, String.format("book_sku_id=%d", bookSkuId));
            List<BookSkuAgeGroup> newBookSkuAgeGroupList = new ArrayList<>();
            List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
            //获取门店sku
            String condition = String.format("platform_book_sku_id = %d", theBookSku.getId());
            List<BookSku> lstBookSku = BookSkuManager.getInstance().getList(sqlSession, condition);
            if (lstBookSku.size() > 0) {
                for (BookSku bookSku : lstBookSku) {
                    bookSku.setPress(theBookSku.getPress());
                    bookSku.setIntroduction(theBookSku.getIntroduction());
                    bookSku.setBindingTypeCode(theBookSku.getBindingTypeCode());
                    bookSku.setImageFileId(theBookSku.getImageFileId());
                    bookSku.setBookName(theBookSku.getBookName());
                    bookSku.setISBN(theBookSku.getISBN());
                    bookSku.setRemark(theBookSku.getRemark());
                    bookSku.setAuthor(theBookSku.getAuthor());
                    bookSku.setLanguageCode(theBookSku.getLanguageCode());
                    bookSku.setPrice(theBookSku.getPrice());
                    BookSkuManager.getInstance().update(sqlSession, bookSku);
                    BookSkuAgeGroupManager.getInstance().deleteByBookSkuId(sqlSession, bookSku.getId());
                    //添加年龄段
                    for (BookSkuAgeGroup bookSkuAgeGroup : lstBookSkuAgeGroup) {
                        bookSkuAgeGroup.setId(null);
                        bookSkuAgeGroup.setBookSkuId(bookSku.getId());
                        newBookSkuAgeGroupList.add(bookSkuAgeGroup);
                    }
                    BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession, bookSku.getId());
                    //添加标签
                    for (ViewBookSkuBookTag bookSkuBookTag : lstBookSkuBookTag) {
                        Integer bookTagId = BookTagManager.getInstance().getBookTagByNameCompanyId(sqlSession, bookSku.getCompanyId(), bookSkuBookTag.getBookTagName());
                        BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                        newBookSkuBookTag.setBookSkuId(bookSku.getId());
                        newBookSkuBookTag.setBookTagId(bookTagId);
                        newBookSkuBookTagList.add(newBookSkuBookTag);
                    }
                }
                BookSkuAgeGroupManager.getInstance().addList(sqlSession, newBookSkuAgeGroupList);
                BookSkuBookTagManager.getInstance().addList(sqlSession, newBookSkuBookTagList);
            }
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
