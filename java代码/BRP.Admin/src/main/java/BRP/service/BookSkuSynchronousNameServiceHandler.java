package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BookSkuSynchronousNameServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer id = JsonHelper.getInt(jData, "id");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            BookSku theBookSku = BookSkuManager.getInstance().getEntity(sqlSession, id);
            if (theBookSku.getPlatformBookSkuId() == null) {
                writeErrorResponse(response, "平台sku不存在");
                return;
            }
            BookSku thePlatformBookSku = BookSkuManager.getInstance().getEntity(sqlSession, theBookSku.getPlatformBookSkuId());
            theBookSku.setBookName(thePlatformBookSku.getBookName());
            theBookSku.setBindingTypeCode(thePlatformBookSku.getBindingTypeCode());
            theBookSku.setAuthor(thePlatformBookSku.getAuthor());
            theBookSku.setPress(thePlatformBookSku.getPress());
            theBookSku.setISBN(thePlatformBookSku.getISBN());
            theBookSku.setPrice(thePlatformBookSku.getPrice());
            theBookSku.setIntroduction(thePlatformBookSku.getIntroduction());
            theBookSku.setImageFileId(thePlatformBookSku.getImageFileId());
            theBookSku.setLanguageCode(thePlatformBookSku.getLanguageCode());
            theBookSku.setRemark(thePlatformBookSku.getRemark());

            if(thePlatformBookSku.getBookSeriesId()!=null){
                BookSeries theBookSeries = BookSeriesManager.getInstance().getEntity(sqlSession, thePlatformBookSku.getBookSeriesId());
                Integer bookSeriesId = BookSeriesManager.getInstance().getBookSeriesIdByName(
                        sqlSession, theBookSeries.getName(), theBookSku.getCompanyId());
                theBookSku.setBookSeriesId(bookSeriesId);
            }

            //获取年龄段
            List<BookSkuAgeGroup> lstBookSkuAgeGroup = BookSkuAgeGroupManager.getInstance().getList(sqlSession, "book_sku_id=" + thePlatformBookSku.getId());
            if(lstBookSkuAgeGroup.size() > 0){
                BookSkuAgeGroupManager.getInstance().deleteByBookSkuId(sqlSession, theBookSku.getId());
                for(BookSkuAgeGroup bookSkuAgeGroup : lstBookSkuAgeGroup){
                    BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                    newBookSkuAgeGroup.setBookSkuId(theBookSku.getId());
                    newBookSkuAgeGroup.setAgeGroupId(bookSkuAgeGroup.getAgeGroupId());
                    BookSkuAgeGroupManager.getInstance().add(sqlSession, newBookSkuAgeGroup);
                }
            }


            List<ViewBookSkuBookTag> lstBookSkuBookTag = ViewBookSkuBookTagManager.getInstance().getList(sqlSession, "book_sku_id=" + thePlatformBookSku.getId());
            if(lstBookSkuBookTag.size() > 0){
                BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession, theBookSku.getId());
                //添加标签
                for (ViewBookSkuBookTag bookSkuBookTag : lstBookSkuBookTag) {
                    Integer bookTagId = BookTagManager.getInstance().getBookTagByNameCompanyId(sqlSession, theBookSku.getCompanyId(), bookSkuBookTag);
                    BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                    newBookSkuBookTag.setBookSkuId(theBookSku.getId());
                    newBookSkuBookTag.setBookTagId(bookTagId);
                    BookSkuBookTagManager.getInstance().add(sqlSession, newBookSkuBookTag);
                }
            }
            //获取总部sku信息
            BookSkuManager.getInstance().update(sqlSession,theBookSku);
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
