package BRP.model;

import BRP.data.Bookcase;
import BRP.data.BookcaseMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BookcaseManager extends BookcaseMaster {
    private static BookcaseManager _instance;

    private BookcaseManager() {
        this.setTableName("bookcase");
        this.setTypeName("Bookcase");
    }

    public static BookcaseManager getInstance() {
        if (_instance == null) {
            _instance = new BookcaseManager();
        }
        return _instance;
    }

    public Bookcase getEntityByName(SqlSession sqlSession, String bookcaseName, Integer companyId) {
        String condition = String.format("name='%s' and company_id=%s", bookcaseName, companyId);
        List<Bookcase> lstBookcase =  this.getList(sqlSession,condition);
        if(lstBookcase!=null && lstBookcase.size()>0){
            return lstBookcase.get(0);
        }
        return null;
    }

    public void combineBookcase(Integer keepBookcaseId, List<Integer> deleteBookcaseIds) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String strDeleteBookcaseIds = deleteBookcaseIds.stream().map(Object::toString).collect(Collectors.joining(","));
            String sql = String.format("update book set bookcase_id = %d where bookcase_id in (%s)", keepBookcaseId, strDeleteBookcaseIds);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            this.batchDelete(sqlSession, deleteBookcaseIds);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}