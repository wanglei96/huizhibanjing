package BRP.model;
import BRP.data.MemberScore;
import BRP.data.RequiredBook;
import BRP.data.RequiredBookMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequiredBookManager extends RequiredBookMaster
{
    private static RequiredBookManager _instance;
    private RequiredBookManager()    
    {
        this.setTableName("required_book");
        this.setTypeName("RequiredBook");
    }
    public  static RequiredBookManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new RequiredBookManager();
        }
        return _instance;
    }

    public void batchAddRequiredBook(List<Integer> bookSkuIds, Integer requiredBookCategoryId) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            List<RequiredBook> newRequiredBookList = new ArrayList<>();
            for (Integer bookSkuId : bookSkuIds) {
                RequiredBook newRequiredBook = new RequiredBook();
                newRequiredBook.setBookSkuId(bookSkuId);
                newRequiredBook.setRequiredBookCategoryId(requiredBookCategoryId);
                newRequiredBookList.add(newRequiredBook);
            }
            RequiredBookManager.getInstance().addList(sqlSession, newRequiredBookList);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}