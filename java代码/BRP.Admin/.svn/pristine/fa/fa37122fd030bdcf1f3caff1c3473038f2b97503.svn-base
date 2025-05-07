package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewReadActivityMember;
public class ViewReadActivityMemberMaster extends ViewDataMaster 
{
    public  List<ViewReadActivityMember> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession)    
    {
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMembers");
        return list;
    }
    public  List<ViewReadActivityMember> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMemberByCondition",condition);
        return list;
    }
    public  List<ViewReadActivityMember> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMemberByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewReadActivityMember> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_read_activity_member limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMemberBySql", sql);
        return list;
    }
    public  List<ViewReadActivityMember> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMemberByCondition",sql);
        return list;
    }
    public  List<ViewReadActivityMember> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewReadActivityMember> list = null;
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
    public  List<ViewReadActivityMember> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewReadActivityMember> list = sqlSession.selectList("ViewReadActivityMember.selectViewReadActivityMemberByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewReadActivityMember.selectCountByCondition",condition);
        return rows;
    }
    public  ViewReadActivityMember createEntity()    
    {
        return new ViewReadActivityMember();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewReadActivityMember();
    }
}