package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewMemberCollectStoryVideo;
public class ViewMemberCollectStoryVideoMaster extends ViewDataMaster 
{
    public  List<ViewMemberCollectStoryVideo> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession)    
    {
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideos");
        return list;
    }
    public  List<ViewMemberCollectStoryVideo> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideoByCondition",condition);
        return list;
    }
    public  List<ViewMemberCollectStoryVideo> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideoByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberCollectStoryVideo> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_collect_story_video limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideoBySql", sql);
        return list;
    }
    public  List<ViewMemberCollectStoryVideo> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideoByCondition",sql);
        return list;
    }
    public  List<ViewMemberCollectStoryVideo> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberCollectStoryVideo> list = null;
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
    public  List<ViewMemberCollectStoryVideo> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewMemberCollectStoryVideo> list = sqlSession.selectList("ViewMemberCollectStoryVideo.selectViewMemberCollectStoryVideoByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberCollectStoryVideo.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberCollectStoryVideo createEntity()    
    {
        return new ViewMemberCollectStoryVideo();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberCollectStoryVideo();
    }
}