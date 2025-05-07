package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ClassHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookManager extends BookMaster {
    private static BookManager _instance;

    private BookManager() {
        this.setTableName("book");
        this.setTypeName("Book");
    }

    public static BookManager getInstance() {
        if (_instance == null) {
            _instance = new BookManager();
        }
        return _instance;
    }

    public void updateBookBookcaseName(Integer bookId, String bookcaseName, Integer companyId) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            Bookcase theBookcase = BookcaseManager.getInstance().getEntityByName(sqlSession, bookcaseName, companyId);
            Book theBook = this.getEntity(sqlSession, bookId);
            if (theBookcase == null) {
                Bookcase newBookcase = new Bookcase();
                newBookcase.setCompanyId(companyId);
                newBookcase.setName(bookcaseName);
                int bookcaseId = BookcaseManager.getInstance().add(sqlSession, newBookcase);
                theBook.setBookcaseId(bookcaseId);
            } else {
                theBook.setBookcaseId(theBookcase.getId());
            }
            this.update(sqlSession, theBook);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    /**
     * 根据图书编号获取图书信息
     *
     * @param sqlSession
     * @param borrowBookSn
     * @return
     */
    public Book getEntityBySn(SqlSession sqlSession, String borrowBookSn, Integer companyId) {
        String condition = String.format("sn='%s' and company_id = %s", borrowBookSn, companyId);
        List<Book> lstBook = this.getList(sqlSession, condition);
        if (lstBook != null && lstBook.size() > 0) {
            return lstBook.get(0);
        }
        return null;
    }

    public Book getEntityBySn(String borrowBookSn, Integer companyId) throws Exception {
        String condition = String.format("sn='%s' and company_id = %s", borrowBookSn, companyId);
        List<Book> lstBook = this.getList(condition);
        if (lstBook != null && lstBook.size() > 0) {
            return lstBook.get(0);
        }
        return null;
    }

    /**
     * 根据sku添加图书
     *
     * @param companyId
     * @param bookSkuId
     * @param count
     */
    public void addBookByBookSkuId(Integer companyId, Integer bookSkuId, Integer count) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            Company theCompany = CompanyManager.getInstance().getEntity(sqlSession, companyId);
            if (count > 0) {
                List<Book> newBookList = new ArrayList<Book>();
                Long newNo = generateBookSn(sqlSession, theCompany.getCompanyNo());
                for (int i = 0; i < count; i++) {
                    Book newBook = new Book();
                    String bookSn = theCompany.getCompanyNo() + "-" + newNo;
                    newBook.setSn(bookSn);
                    newBook.setIsAvailable(true);
                    newBook.setBookStatusCode(BookStatusCodes.On);
                    newBook.setBorrowStatusCode(BorrowStatusCodes.In);
                    newBook.setBookSkuId(bookSkuId);
                    newBook.setCompanyId(companyId);
                    newBookList.add(newBook);
                    newNo++;
                }
                BookManager.getInstance().addList(sqlSession, newBookList);
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    /**
     * 创建图书编号
     *
     * @return
     */
    public Long generateBookSn(SqlSession sqlSession, String companyNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Date now = new Date();
        String bookNo = sdf.format(now);
        String condition = String.format("sn like '%s%%'", companyNo + "-" + bookNo);
        List<Book> lstBook = this.getList(sqlSession, condition, "sn desc");
        if (lstBook != null && lstBook.size() > 0) {
            String strSn = lstBook.get(0).getSn();
            // 查找 "-" 的索引
            int index = strSn.indexOf("-");
            // 截取字符串
            String strBookSn = strSn.substring(index + 1);
            return Long.parseLong(strBookSn) + 1;
        }
        long longBookSn = Long.parseLong(bookNo + String.format("%04d", 1));
        return longBookSn;
    }

    /**
     * 扫码上架图书
     *
     * @param companyId
     * @param bookSn
     */
    public void scanBookEnabledBook(Integer companyId, String bookSn) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            Book theBook = BookManager.getInstance().getEntityBySn(sqlSession, bookSn, companyId);
            if (theBook == null) {
                throw new Exception("未找到图书");
            }
            String bookStatusCode = theBook.getBookStatusCode();

            if (bookStatusCode == null || bookStatusCode.equals(BookStatusCodes.Off)) {
                theBook.setBookStatusCode(BookStatusCodes.On);
            } else if (bookStatusCode.equals(BookStatusCodes.On)) {
                theBook.setBookStatusCode(BookStatusCodes.Off);
            }
            BookManager.getInstance().update(sqlSession, theBook);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    /**
     * 根据SKU获取可借图书列表
     *
     * @param session
     * @param companyId
     * @param bookSkuId
     * @return
     */
    public List<Book> getListByCompanyIdAndBookSkuId(SqlSession session, Integer companyId, Integer bookSkuId) {
        String condition = String.format("book_sku_id = %s and is_available is true and book_status_code='%s' and company_id=%d",
                bookSkuId, BookStatusCodes.On, companyId);
        return this.getList(session, condition);
    }


}