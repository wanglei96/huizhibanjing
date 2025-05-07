package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.ViewMemberCardServiceTimeLog;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberCardServiceTimeLogMaster extends ViewDataMaster 
{
    public  List<ViewMemberCardServiceTimeLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession)    
    {
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogs");
        return list;
    }
    public  List<ViewMemberCardServiceTimeLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogByCondition",condition);
        return list;
    }
    public  List<ViewMemberCardServiceTimeLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberCardServiceTimeLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_card_service_time_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogBySql", sql);
        return list;
    }
    public  List<ViewMemberCardServiceTimeLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogByCondition",sql);
        return list;
    }
    public  List<ViewMemberCardServiceTimeLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardServiceTimeLog> list = null;
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
    public  List<ViewMemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewMemberCardServiceTimeLog> list = sqlSession.selectList("ViewMemberCardServiceTimeLog.selectViewMemberCardServiceTimeLogByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberCardServiceTimeLog.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberCardServiceTimeLog createEntity()    
    {
        return new ViewMemberCardServiceTimeLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberCardServiceTimeLog();
    }
}