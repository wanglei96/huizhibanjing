package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import BRP.data.ViewBookOrder;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewBookOrderMaster extends ViewDataMaster 
{
    public  List<ViewBookOrder> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession)    
    {
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrders");
        return list;
    }
    public  List<ViewBookOrder> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrderByCondition",condition);
        return list;
    }
    public  List<ViewBookOrder> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrderByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewBookOrder> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_book_order limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrderBySql", sql);
        return list;
    }
    public  List<ViewBookOrder> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrderByCondition",sql);
        return list;
    }
    public  List<ViewBookOrder> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookOrder> list = null;
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
    public  List<ViewBookOrder> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewBookOrder> list = sqlSession.selectList("ViewBookOrder.selectViewBookOrderByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewBookOrder.selectCountByCondition",condition);
        return rows;
    }
    public  ViewBookOrder createEntity()    
    {
        return new ViewBookOrder();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewBookOrder();
    }
}