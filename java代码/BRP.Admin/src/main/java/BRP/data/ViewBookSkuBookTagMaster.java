package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewBookSkuBookTag;
public class ViewBookSkuBookTagMaster extends ViewDataMaster 
{
    public  List<ViewBookSkuBookTag> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession)    
    {
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTags");
        return list;
    }
    public  List<ViewBookSkuBookTag> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTagByCondition",condition);
        return list;
    }
    public  List<ViewBookSkuBookTag> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTagByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewBookSkuBookTag> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_book_sku_book_tag limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTagBySql", sql);
        return list;
    }
    public  List<ViewBookSkuBookTag> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTagByCondition",sql);
        return list;
    }
    public  List<ViewBookSkuBookTag> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewBookSkuBookTag> list = null;
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
    public  List<ViewBookSkuBookTag> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewBookSkuBookTag> list = sqlSession.selectList("ViewBookSkuBookTag.selectViewBookSkuBookTagByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewBookSkuBookTag.selectCountByCondition",condition);
        return rows;
    }
    public  ViewBookSkuBookTag createEntity()    
    {
        return new ViewBookSkuBookTag();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewBookSkuBookTag();
    }
}