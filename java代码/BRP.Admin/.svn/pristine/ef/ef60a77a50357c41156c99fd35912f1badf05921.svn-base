package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewCompanyProductStocks;
public class ViewCompanyProductStocksMaster extends ViewDataMaster 
{
    public  List<ViewCompanyProductStocks> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession)    
    {
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStockss");
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession,condition);
            sqlSession.commit();
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStocksByCondition",condition);
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession,condition,orderBy);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStocksByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession,pageIndex,pageSize);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
        finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_company_product_stocks limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStocksBySql", sql);
        return list;
    }
    public  List<ViewCompanyProductStocks> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession, condition, pageIndex, pageSize);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStocksByCondition",sql);
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyProductStocks> list = null;
        try        
        {
            list = getList(sqlSession, condition, orderBy, pageIndex, pageSize);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCompanyProductStocks> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewCompanyProductStocks> list = sqlSession.selectList("ViewCompanyProductStocks.selectViewCompanyProductStocksByCondition",sql);
        return list;
    }
    public  Integer getCount(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Integer count = null;
        try        
        {
            count = getCount(sqlSession,condition);
            sqlSession.commit();
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return count;
    }
    public  Integer getCount(SqlSession sqlSession,String condition)    
    {
        Integer rows = sqlSession.selectOne("ViewCompanyProductStocks.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCompanyProductStocks createEntity()    
    {
        return new ViewCompanyProductStocks();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCompanyProductStocks();
    }
}