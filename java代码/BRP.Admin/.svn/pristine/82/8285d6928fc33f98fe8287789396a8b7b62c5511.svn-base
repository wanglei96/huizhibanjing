package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewBookInOrder;
public class ViewBookInOrderMaster extends ViewDataMaster 
{
    public  List<ViewBookInOrder> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession)    
    {
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrders");
        return list;
    }
    public  List<ViewBookInOrder> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrderByCondition",condition);
        return list;
    }
    public  List<ViewBookInOrder> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrderByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewBookInOrder> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_book_in_order limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrderBySql", sql);
        return list;
    }
    public  List<ViewBookInOrder> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrderByCondition",sql);
        return list;
    }
    public  List<ViewBookInOrder> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookInOrder> list = null;
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
    public  List<ViewBookInOrder> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewBookInOrder> list = sqlSession.selectList("ViewBookInOrder.selectViewBookInOrderByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewBookInOrder.selectCountByCondition",condition);
        return rows;
    }
    public  ViewBookInOrder createEntity()    
    {
        return new ViewBookInOrder();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewBookInOrder();
    }
}