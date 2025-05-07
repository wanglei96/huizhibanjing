package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SynchronizationAddCompanyBookSkuServiceHandler extends ServiceHandler {

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
            String condition = "company_id is null";
            List<BookSku> lstBookSku = BookSkuManager.getInstance().getList(sqlSession, condition);
            //获取所有系列
            List<BookSeries> lstBookSeries = BookSeriesManager.getInstance().getList(sqlSession, "company_id is null");
            List<ViewBookSkuBookTag> lstBookSkuBookTag = ViewBookSkuBookTagManager.getInstance().getList(sqlSession);

            List<BookSkuAgeGroup> lstBookSkuAgeGroup = BookSkuAgeGroupManager.getInstance().getList(sqlSession);
            //获取门店sku
            List<BookSku> lstCompanyBookSku = BookSkuManager.getInstance().getList(
                    sqlSession, "company_id=" + defaultCompany.getId());

            List<BookSkuBookTag> newlstBookSkuBookTag = new ArrayList<>();
            List<BookSkuAgeGroup> newlstBookSkuAgeGroup = new ArrayList<>();
            for (BookSku bookSku : lstBookSku) {
                Integer bookSkuId = bookSku.getId();

                Optional<BookSku> duplicateItem = lstCompanyBookSku.stream()
                        .filter(item -> item.getISBN().equals(bookSku.getISBN()) &&
                                item.getBookName().equals(bookSku.getBookName()) &&
                                item.getAuthor().equals(bookSku.getAuthor()))
                        .findFirst();

                List<ViewBookSkuBookTag> bookSkuBookTag = lstBookSkuBookTag.stream()
                        .filter(bookTag -> bookTag.getBookSkuId().equals(bookSku.getId()))
                        .collect(Collectors.toList());

                if (duplicateItem.isPresent()) {
                    // 找到了一个重复的项
                    BookSku foundDuplicate = duplicateItem.get();
                    // 进行进一步处理
                    foundDuplicate.setImageFileId(bookSku.getImageFileId());
                    foundDuplicate.setIntroduction(bookSku.getIntroduction());
                    foundDuplicate.setPress(bookSku.getPress());
                    foundDuplicate.setPlatformBookSkuId(bookSku.getId());
                    if (bookSku.getBookSeriesId() != null) {
                        BookSeries bookSeries = lstBookSeries.stream()
                                .filter(item -> item.getId().equals(bookSku.getBookSeriesId()))
                                .findFirst().orElse(null);
                        if (bookSeries != null) {
                            //根据名字获取系列
                            Integer bookSeriesId = BookSeriesManager.getInstance().getBookSeriesIdByName(
                                    sqlSession, bookSeries.getName(), defaultCompany.getId());
                            foundDuplicate.setBookSeriesId(bookSeriesId);
                        }
                    }
                    BookSkuManager.getInstance().update(sqlSession, foundDuplicate);
                    if (bookSkuBookTag.size() > 0) {
                        BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession, foundDuplicate.getId());
                        for (ViewBookSkuBookTag bookTag : bookSkuBookTag) {
                            Integer bookTagId = BookTagManager.getInstance().getBookTagByNameCompanyId(sqlSession, defaultCompany.getId(), bookTag.getBookTagName());
                            BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                            newBookSkuBookTag.setBookSkuId(foundDuplicate.getId());
                            newBookSkuBookTag.setBookTagId(bookTagId);
                            newlstBookSkuBookTag.add(newBookSkuBookTag);
                        }
                    }
                } else {
                    List<BookSkuAgeGroup> bookSkuAgeGroup = lstBookSkuAgeGroup.stream()
                            .filter(item -> item.getBookSkuId().equals(bookSku.getId()))
                            .collect(Collectors.toList());
                    // 没有找到重复的项，执行接下来的逻辑
                    BookSku newBookSku = bookSku.copy();
                    newBookSku.setId(null);
                    newBookSku.setPlatformBookSkuId(bookSkuId);
                    newBookSku.setCompanyId(defaultCompany.getId());
                    if (bookSku.getBookSeriesId() != null) {
                        BookSeries bookSeries = lstBookSeries.stream()
                                .filter(item -> item.getId().equals(bookSku.getBookSeriesId()))
                                .findFirst().orElse(null);
                        if (bookSeries != null) {
                            //根据名字获取系列
                            Integer bookSeriesId = BookSeriesManager.getInstance().getBookSeriesIdByName(
                                    sqlSession, bookSeries.getName(), defaultCompany.getId());
                            newBookSku.setBookSeriesId(bookSeriesId);
                        }
                    }
                    Integer newBookSkuId = BookSkuManager.getInstance().add(sqlSession, newBookSku);
                    if (bookSkuBookTag.size() > 0) {
                        for (ViewBookSkuBookTag bookTag : bookSkuBookTag) {
                            Integer bookTagId = BookTagManager.getInstance().getBookTagByNameCompanyId(sqlSession, defaultCompany.getId(), bookTag.getBookTagName());
                            BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                            newBookSkuBookTag.setBookSkuId(newBookSkuId);
                            newBookSkuBookTag.setBookTagId(bookTagId);
                            newlstBookSkuBookTag.add(newBookSkuBookTag);
                        }
                    }
                    if (bookSkuAgeGroup.size() > 0) {
                        for (BookSkuAgeGroup ageGroup : bookSkuAgeGroup) {
                            BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                            newBookSkuAgeGroup.setBookSkuId(newBookSkuId);
                            newBookSkuAgeGroup.setAgeGroupId(ageGroup.getAgeGroupId());
                            newlstBookSkuAgeGroup.add(newBookSkuAgeGroup);
                        }
                    }
                }
            }
            BookSkuAgeGroupManager.getInstance().addList(sqlSession, newlstBookSkuAgeGroup);
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
