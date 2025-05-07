package strosoft.cms.model;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.ViewCmsContentLanguage;

public class ViewCmsContentLanguageManager 
{
    private static ViewCmsContentLanguageManager _instance;
    public  static ViewCmsContentLanguageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCmsContentLanguageManager();
        }
        return _instance;
    }
    public  List<ViewCmsContentLanguage> getList()throws IOException    
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
    public  List<ViewCmsContentLanguage> getList(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
        try        
        {
            list = getList(sqlSession,condition);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
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
    public  List<ViewCmsContentLanguage> getList(String condition,String orderBy)throws IOException    
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
    public  List<ViewCmsContentLanguage> getList(int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
        try        
        {
            list = getList(sqlSession,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,int pagesize,int pageindex)    
    {
        String limt = " limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",limt);
        return list;
    }
    public  List<ViewCmsContentLanguage> getListPage(String condition,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
        try        
        {
            list = getList(sqlSession,condition,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition,int pagesize,int pageindex)    
    {
        String sql = condition+" limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",sql);
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(String condition,String orderBy,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsContentLanguage> list = null;
        try        
        {
            list = getList(sqlSession,condition,orderBy,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewCmsContentLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsContentLanguage> list = sqlSession.selectList("ViewCmsContentLanguage.selectViewCmsContentLanguageByCondition",sql);
        return list;
    }
    public  Integer getCount(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Integer count = null;
        try        
        {
            count = getCount(sqlSession,condition);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
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