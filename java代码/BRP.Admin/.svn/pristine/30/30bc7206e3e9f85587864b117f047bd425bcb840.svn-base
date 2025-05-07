package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.data.ViewCmsChannelLanguage;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewCmsChannelLanguageMaster extends ViewDataMaster 
{
    public  List<ViewCmsChannelLanguage> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession)    
    {
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguages");
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",condition);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_cms_channel_language limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageBySql", sql);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",sql);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewCmsChannelLanguage.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCmsChannelLanguage createEntity()    
    {
        return new ViewCmsChannelLanguage();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCmsChannelLanguage();
    }
}