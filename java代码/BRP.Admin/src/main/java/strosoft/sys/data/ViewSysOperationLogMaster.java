package strosoft.sys.data;
import strosoft.sys.data.ViewSysOperationLog;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewSysOperationLogMaster extends ViewDataMaster 
{
    public  List<ViewSysOperationLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession)    
    {
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogs");
        return list;
    }
    public  List<ViewSysOperationLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogByCondition",condition);
        return list;
    }
    public  List<ViewSysOperationLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewSysOperationLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_sys_operation_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogBySql", sql);
        return list;
    }
    public  List<ViewSysOperationLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogByCondition",sql);
        return list;
    }
    public  List<ViewSysOperationLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysOperationLog> list = null;
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
    public  List<ViewSysOperationLog> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewSysOperationLog> list = sqlSession.selectList("ViewSysOperationLog.selectViewSysOperationLogByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewSysOperationLog.selectCountByCondition",condition);
        return rows;
    }
    public  ViewSysOperationLog createEntity()    
    {
        return new ViewSysOperationLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewSysOperationLog();
    }
}