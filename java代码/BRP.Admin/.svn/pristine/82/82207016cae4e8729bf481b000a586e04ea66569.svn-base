package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.ViewMemberBrowsedClubActivity;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberBrowsedClubActivityMaster extends ViewDataMaster 
{
    public  List<ViewMemberBrowsedClubActivity> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession)    
    {
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivitys");
        return list;
    }
    public  List<ViewMemberBrowsedClubActivity> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivityByCondition",condition);
        return list;
    }
    public  List<ViewMemberBrowsedClubActivity> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivityByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberBrowsedClubActivity> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_browsed_club_activity limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivityBySql", sql);
        return list;
    }
    public  List<ViewMemberBrowsedClubActivity> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivityByCondition",sql);
        return list;
    }
    public  List<ViewMemberBrowsedClubActivity> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBrowsedClubActivity> list = null;
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
    public  List<ViewMemberBrowsedClubActivity> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewMemberBrowsedClubActivity> list = sqlSession.selectList("ViewMemberBrowsedClubActivity.selectViewMemberBrowsedClubActivityByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberBrowsedClubActivity.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberBrowsedClubActivity createEntity()    
    {
        return new ViewMemberBrowsedClubActivity();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberBrowsedClubActivity();
    }
}