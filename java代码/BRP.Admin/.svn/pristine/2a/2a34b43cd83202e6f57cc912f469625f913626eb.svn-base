package BRP.data;
import BRP.data.ViewMemberCardDisableLog;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberCardDisableLogMaster extends ViewDataMaster 
{
    public  List<ViewMemberCardDisableLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession)    
    {
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogs");
        return list;
    }
    public  List<ViewMemberCardDisableLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogByCondition",condition);
        return list;
    }
    public  List<ViewMemberCardDisableLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberCardDisableLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_card_disable_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogBySql", sql);
        return list;
    }
    public  List<ViewMemberCardDisableLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogByCondition",sql);
        return list;
    }
    public  List<ViewMemberCardDisableLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCardDisableLog> list = null;
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
    public  List<ViewMemberCardDisableLog> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewMemberCardDisableLog> list = sqlSession.selectList("ViewMemberCardDisableLog.selectViewMemberCardDisableLogByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberCardDisableLog.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberCardDisableLog createEntity()    
    {
        return new ViewMemberCardDisableLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberCardDisableLog();
    }
}