package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.ViewCmsContentLanguage;
public class ViewCmsContentLanguageMaster extends ViewDataMaster 
{
    public  List<ViewCmsContentLanguage> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession)    
    {
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguages");
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",condition);
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_cms_content_language limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageBySql", sql);
        return list;
    }
    public  List<ViewCmsContentLanguage> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",sql);
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
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
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewCmsContentLanguage.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCmsContentLanguage createEntity()    
    {
        return new ViewCmsContentLanguage();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCmsContentLanguage();
    }
}