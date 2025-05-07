package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import BRP.data.ViewMemberLikeStoryVideo;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberLikeStoryVideoMaster extends ViewDataMaster 
{
    public  List<ViewMemberLikeStoryVideo> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession)    
    {
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideos");
        return list;
    }
    public  List<ViewMemberLikeStoryVideo> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideoByCondition",condition);
        return list;
    }
    public  List<ViewMemberLikeStoryVideo> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideoByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberLikeStoryVideo> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_like_story_video limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideoBySql", sql);
        return list;
    }
    public  List<ViewMemberLikeStoryVideo> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideoByCondition",sql);
        return list;
    }
    public  List<ViewMemberLikeStoryVideo> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberLikeStoryVideo> list = null;
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
    public  List<ViewMemberLikeStoryVideo> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewMemberLikeStoryVideo> list = sqlSession.selectList("ViewMemberLikeStoryVideo.selectViewMemberLikeStoryVideoByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberLikeStoryVideo.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberLikeStoryVideo createEntity()    
    {
        return new ViewMemberLikeStoryVideo();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberLikeStoryVideo();
    }
}