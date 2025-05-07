package BRP.model;

import BRP.data.BookSeries;
import BRP.data.BookSeriesMaster;
import BRP.data.BookTag;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookSeriesManager extends BookSeriesMaster {
    private static BookSeriesManager _instance;

    private BookSeriesManager() {
        this.setTableName("book_series");
        this.setTypeName("BookSeries");
    }

    public static BookSeriesManager getInstance() {
        if (_instance == null) {
            _instance = new BookSeriesManager();
        }
        return _instance;
    }

    public BookSeries getEntityByName(SqlSession sqlSession, String bookSeriesName) throws Exception {
        List<BookSeries> lstBookSeries = this.getList(sqlSession, "name=" + bookSeriesName);
        if (lstBookSeries != null && lstBookSeries.size() > 0) {
            return lstBookSeries.get(0);
        }
        return null;
    }

    public List<BookSeries> getListByCompanyId(SqlSession sqlSession, Integer companyId) {
        return this.getList(sqlSession, "company_id=" + companyId);
    }

    public Integer getBookSeriesId(SqlSession sqlSession, String strBookSeries) {
        String condition = "company_id is null and name='" + strBookSeries + "'";
        List<BookSeries> lstBookSeries = this.getList(sqlSession, condition);
        if (lstBookSeries != null && lstBookSeries.size() > 0) {
            return lstBookSeries.get(0).getId();
        } else {
            BookSeries newBookSeries = new BookSeries();
            newBookSeries.setName(strBookSeries);
            newBookSeries.setEnabled(true);
            Integer id = this.add(sqlSession, newBookSeries);
            return id;
        }
    }

    public Integer getBookSeriesIdByName(SqlSession sqlSession, String bookSeriesName, Integer companyId) {
        String condition = "company_id=" + companyId + " and name='" + bookSeriesName + "'";
        List<BookSeries> lstBookSeries = this.getList(sqlSession, condition);
        if (lstBookSeries != null && lstBookSeries.size() > 0) {
            return lstBookSeries.get(0).getId();
        } else {
            BookSeries newBookSeries = new BookSeries();
            newBookSeries.setEnabled(true);
            newBookSeries.setCompanyId(companyId);
            newBookSeries.setName(bookSeriesName);
            Integer id = this.add(sqlSession, newBookSeries);
            return id;
        }
    }
}