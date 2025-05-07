package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.ViewMemberLikeClubActivity;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberLikeClubActivityMaster extends ViewDataMaster 
{
    public  List<ViewMemberLikeClubActivity> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession)    
    {
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivitys");
        return list;
    }
    public  List<ViewMemberLikeClubActivity> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivityByCondition",condition);
        return list;
    }
    public  List<ViewMemberLikeClubActivity> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivityByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberLikeClubActivity> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_like_club_activity limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivityBySql", sql);
        return list;
    }
    public  List<ViewMemberLikeClubActivity> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivityByCondition",sql);
        return list;
    }
    public  List<ViewMemberLikeClubActivity> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeClubActivity> list = null;
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
    public  List<ViewMemberLikeClubActivity> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewMemberLikeClubActivity> list = sqlSession.selectList("ViewMemberLikeClubActivity.selectViewMemberLikeClubActivityByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberLikeClubActivity.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberLikeClubActivity createEntity()    
    {
        return new ViewMemberLikeClubActivity();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberLikeClubActivity();
    }
}