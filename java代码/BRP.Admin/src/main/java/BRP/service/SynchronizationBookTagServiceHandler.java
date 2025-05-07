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

public class SynchronizationBookTagServiceHandler extends ServiceHandler {

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

            String strBookSkuId = lstBookSku.stream()
                    .map(bookSku -> String.valueOf(bookSku.getId()))
                    .collect(Collectors.joining(","));

            List<ViewBookSkuBookTag> lstBookSkuBookTag = ViewBookSkuBookTagManager
                    .getInstance().getList(sqlSession, "book_sku_id in(" + strBookSkuId + ")");

            //获取门店sku
            String companyCondition = "company_id=" + defaultCompany.getId();

            List<BookSku> lstCompanyBookSku = BookSkuManager.getInstance().getList(
                    sqlSession, companyCondition);

            List<BookSkuBookTag> newlstBookSkuBookTag = new ArrayList<>();
            for (BookSku bookSku : lstBookSku) {
                List<ViewBookSkuBookTag> bookSkuBookTag = lstBookSkuBookTag.stream()
                        .filter(bookTag -> bookTag.getBookSkuId().equals(bookSku.getId()))
                        .collect(Collectors.toList());
                for (BookSku companyBookSku : lstCompanyBookSku) {
                    if (companyBookSku.getPlatformBookSkuId() != null) {
                        if (bookSku.getId().equals(companyBookSku.getPlatformBookSkuId())) {
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
                            break;
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
