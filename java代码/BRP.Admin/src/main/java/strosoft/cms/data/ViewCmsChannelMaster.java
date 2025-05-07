package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewCmsChannelMaster extends ViewDataMaster 
{
    public  List<ViewCmsChannel> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession)    
    {
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannels");
        return list;
    }
    public  List<ViewCmsChannel> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelByCondition",condition);
        return list;
    }
    public  List<ViewCmsChannel> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCmsChannel> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_cms_channel limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelBySql", sql);
        return list;
    }
    public  List<ViewCmsChannel> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelByCondition",sql);
        return list;
    }
    public  List<ViewCmsChannel> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannel> list = null;
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
    public  List<ViewCmsChannel> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsChannel> list = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewCmsChannel.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCmsChannel createEntity()    
    {
        return new ViewCmsChannel();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCmsChannel();
    }
}