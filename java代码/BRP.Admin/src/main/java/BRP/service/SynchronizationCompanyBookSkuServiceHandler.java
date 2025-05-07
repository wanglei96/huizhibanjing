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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SynchronizationCompanyBookSkuServiceHandler extends ServiceHandler {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //获取默认门店
            Company defaultCompany = CompanyManager.getInstance().getDefaultCompanyInfo(sqlSession);
            if (defaultCompany == null) {
                writeErrorResponse(response, "未找到默认门店");
            }
            //获取总部Sku
            String condition = " company_id is null";
            List<BookSku> lstBookSku = BookSkuManager.getInstance().getList(sqlSession, condition);
            List<ViewBookSkuBookTag> lstBookSkuBookTag = ViewBookSkuBookTagManager.getInstance().getList(sqlSession);
            List<BookSkuBookTag> newlstBookSkuBookTag = new ArrayList<>();
            //获取所有系列
            List<BookSeries> lstBookSeries = BookSeriesManager.getInstance().getList(sqlSession, "company_id is null");
            for (BookSku bookSku : lstBookSku) {
                //获取门店Sku
                String companyCondition = String.format(
                        "company_id=%d and ISBN='%s' and author = '%s' and book_name='%s'",
                        defaultCompany.getId(), bookSku.getISBN(), bookSku.getAuthor(), bookSku.getBookName());
                List<BookSku> lstCompanyBookSku = BookSkuManager.getInstance().getList(sqlSession, companyCondition);
                if (lstCompanyBookSku.size() > 0) {
                    List<ViewBookSkuBookTag> bookSkuBookTag = lstBookSkuBookTag.stream()
                            .filter(bookTag -> bookTag.getBookSkuId().equals(bookSku.getId()))
                            .collect(Collectors.toList());
                    BookSku companyBookSku = lstCompanyBookSku.get(0);
                    companyBookSku.setImageFileId(bookSku.getImageFileId());
                    companyBookSku.setIntroduction(bookSku.getIntroduction());
                    companyBookSku.setPress(bookSku.getPress());
                    if (bookSku.getBookSeriesId() != null) {
                        BookSeries bookSeries = lstBookSeries.stream()
                                .filter(item -> item.getId().equals(bookSku.getBookSeriesId()))
                                .findFirst().orElse(null);
                        if (bookSeries != null) {
                            //根据名字获取系列
                            Integer bookSeriesId = BookSeriesManager.getInstance().getBookSeriesIdByName(
                                    sqlSession, bookSeries.getName(), defaultCompany.getId());
                            companyBookSku.setBookSeriesId(bookSeriesId);
                        }
                    }
                    BookSkuManager.getInstance().update(sqlSession, companyBookSku);
                    if (bookSkuBookTag.size() > 0) {
                        BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession, companyBookSku.getId());
                        for (ViewBookSkuBookTag bookTag : bookSkuBookTag) {
                            Integer bookTagId = BookTagManager.getInstance().getBookTagByNameCompanyId(sqlSession, defaultCompany.getId(), bookTag.getBookTagName());
                            BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                            newBookSkuBookTag.setBookSkuId(companyBookSku.getId());
                            newBookSkuBookTag.setBookTagId(bookTagId);
                            newlstBookSkuBookTag.add(newBookSkuBookTag);
                        }
                    }

                }
            }
            BookSkuBookTagManager.getInstance().addList(sqlSession, newlstBookSkuBookTag);
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
