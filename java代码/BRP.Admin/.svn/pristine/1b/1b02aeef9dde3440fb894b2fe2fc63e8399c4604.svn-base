package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.sys.data.ViewSysLoginLog;
public class ViewSysLoginLogMaster extends ViewDataMaster 
{
    public  List<ViewSysLoginLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession)    
    {
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogs");
        return list;
    }
    public  List<ViewSysLoginLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogByCondition",condition);
        return list;
    }
    public  List<ViewSysLoginLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewSysLoginLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_sys_login_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogBySql", sql);
        return list;
    }
    public  List<ViewSysLoginLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogByCondition",sql);
        return list;
    }
    public  List<ViewSysLoginLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysLoginLog> list = null;
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
    public  List<ViewSysLoginLog> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewSysLoginLog> list = sqlSession.selectList("ViewSysLoginLog.selectViewSysLoginLogByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewSysLoginLog.selectCountByCondition",condition);
        return rows;
    }
    public  ViewSysLoginLog createEntity()    
    {
        return new ViewSysLoginLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewSysLoginLog();
    }
}