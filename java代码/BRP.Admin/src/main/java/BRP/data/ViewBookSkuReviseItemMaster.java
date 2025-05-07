package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import BRP.data.ViewBookSkuReviseItem;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewBookSkuReviseItemMaster extends ViewDataMaster 
{
    public  List<ViewBookSkuReviseItem> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession)    
    {
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItems");
        return list;
    }
    public  List<ViewBookSkuReviseItem> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItemByCondition",condition);
        return list;
    }
    public  List<ViewBookSkuReviseItem> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItemByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewBookSkuReviseItem> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_book_sku_revise_item limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItemBySql", sql);
        return list;
    }
    public  List<ViewBookSkuReviseItem> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItemByCondition",sql);
        return list;
    }
    public  List<ViewBookSkuReviseItem> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuReviseItem> list = null;
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
    public  List<ViewBookSkuReviseItem> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewBookSkuReviseItem> list = sqlSession.selectList("ViewBookSkuReviseItem.selectViewBookSkuReviseItemByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewBookSkuReviseItem.selectCountByCondition",condition);
        return rows;
    }
    public  ViewBookSkuReviseItem createEntity()    
    {
        return new ViewBookSkuReviseItem();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewBookSkuReviseItem();
    }
}