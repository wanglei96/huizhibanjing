package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewAgeGroupBookTag;
public class ViewAgeGroupBookTagMaster extends ViewDataMaster 
{
    public  List<ViewAgeGroupBookTag> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession)    
    {
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTags");
        return list;
    }
    public  List<ViewAgeGroupBookTag> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTagByCondition",condition);
        return list;
    }
    public  List<ViewAgeGroupBookTag> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTagByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewAgeGroupBookTag> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_age_group_book_tag limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTagBySql", sql);
        return list;
    }
    public  List<ViewAgeGroupBookTag> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTagByCondition",sql);
        return list;
    }
    public  List<ViewAgeGroupBookTag> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewAgeGroupBookTag> list = null;
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
    public  List<ViewAgeGroupBookTag> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewAgeGroupBookTag> list = sqlSession.selectList("ViewAgeGroupBookTag.selectViewAgeGroupBookTagByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewAgeGroupBookTag.selectCountByCondition",condition);
        return rows;
    }
    public  ViewAgeGroupBookTag createEntity()    
    {
        return new ViewAgeGroupBookTag();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewAgeGroupBookTag();
    }
}