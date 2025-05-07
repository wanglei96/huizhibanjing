package BRP.service;

import BRP.data.*;
import BRP.model.BookManager;
import BRP.model.BookSkuManager;
import BRP.model.CompanyManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BatchAddAllBookServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //获取默认门店
            Company defaultCompany = CompanyManager.getInstance().getDefaultCompanyInfo(sqlSession);
            List<BookSku> lstBookSku = BookSkuManager.getInstance().
                    getList(sqlSession, "company_id="+defaultCompany.getId());
            List<Book> newBookList = new ArrayList<>();
            Long newNo = BookManager.getInstance().generateBookSn(sqlSession, defaultCompany.getCompanyNo());
            for (BookSku bookSku : lstBookSku) {
                Book newBook = new Book();
                String bookSn = defaultCompany.getCompanyNo() + "-" + newNo;
                newBook.setSn(bookSn);
                newBook.setBookSkuId(bookSku.getId());
                newBook.setCompanyId(defaultCompany.getId());
                newBook.setIsAvailable(true);
                newBook.setBookStatusCode(BookStatusCodes.On);
                newBook.setBorrowStatusCode(BorrowStatusCodes.In);
                newBookList.add(newBook);
                newNo++;
            }
            BookManager.getInstance().addList(sqlSession, newBookList);
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
