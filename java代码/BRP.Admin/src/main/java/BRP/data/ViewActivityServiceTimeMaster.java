package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.ViewActivityServiceTime;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewActivityServiceTimeMaster extends ViewDataMaster 
{
    public  List<ViewActivityServiceTime> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession)    
    {
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimes");
        return list;
    }
    public  List<ViewActivityServiceTime> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimeByCondition",condition);
        return list;
    }
    public  List<ViewActivityServiceTime> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimeByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewActivityServiceTime> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_activity_service_time limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimeBySql", sql);
        return list;
    }
    public  List<ViewActivityServiceTime> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimeByCondition",sql);
        return list;
    }
    public  List<ViewActivityServiceTime> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewActivityServiceTime> list = null;
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
    public  List<ViewActivityServiceTime> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewActivityServiceTime> list = sqlSession.selectList("ViewActivityServiceTime.selectViewActivityServiceTimeByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewActivityServiceTime.selectCountByCondition",condition);
        return rows;
    }
    public  ViewActivityServiceTime createEntity()    
    {
        return new ViewActivityServiceTime();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewActivityServiceTime();
    }
}