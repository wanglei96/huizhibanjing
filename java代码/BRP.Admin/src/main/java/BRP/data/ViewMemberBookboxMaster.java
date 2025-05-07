package BRP.data;
import BRP.data.ViewMemberBookbox;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewMemberBookboxMaster extends ViewDataMaster 
{
    public  List<ViewMemberBookbox> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession)    
    {
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxs");
        return list;
    }
    public  List<ViewMemberBookbox> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxByCondition",condition);
        return list;
    }
    public  List<ViewMemberBookbox> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberBookbox> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_bookbox limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxBySql", sql);
        return list;
    }
    public  List<ViewMemberBookbox> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxByCondition",sql);
        return list;
    }
    public  List<ViewMemberBookbox> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberBookbox> list = null;
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
    public  List<ViewMemberBookbox> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewMemberBookbox> list = sqlSession.selectList("ViewMemberBookbox.selectViewMemberBookboxByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberBookbox.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberBookbox createEntity()    
    {
        return new ViewMemberBookbox();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberBookbox();
    }
}