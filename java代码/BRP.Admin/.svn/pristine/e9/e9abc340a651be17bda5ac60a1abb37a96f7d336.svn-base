package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewDeliverPlace;
public class ViewDeliverPlaceMaster extends ViewDataMaster 
{
    public  List<ViewDeliverPlace> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession)    
    {
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaces");
        return list;
    }
    public  List<ViewDeliverPlace> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaceByCondition",condition);
        return list;
    }
    public  List<ViewDeliverPlace> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaceByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewDeliverPlace> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_deliver_place limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaceBySql", sql);
        return list;
    }
    public  List<ViewDeliverPlace> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaceByCondition",sql);
        return list;
    }
    public  List<ViewDeliverPlace> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewDeliverPlace> list = null;
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
    public  List<ViewDeliverPlace> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewDeliverPlace> list = sqlSession.selectList("ViewDeliverPlace.selectViewDeliverPlaceByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewDeliverPlace.selectCountByCondition",condition);
        return rows;
    }
    public  ViewDeliverPlace createEntity()    
    {
        return new ViewDeliverPlace();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewDeliverPlace();
    }
}