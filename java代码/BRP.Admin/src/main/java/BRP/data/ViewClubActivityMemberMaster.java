package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewClubActivityMember;
public class ViewClubActivityMemberMaster extends ViewDataMaster 
{
    public  List<ViewClubActivityMember> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession)    
    {
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMembers");
        return list;
    }
    public  List<ViewClubActivityMember> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMemberByCondition",condition);
        return list;
    }
    public  List<ViewClubActivityMember> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMemberByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewClubActivityMember> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_club_activity_member limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMemberBySql", sql);
        return list;
    }
    public  List<ViewClubActivityMember> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMemberByCondition",sql);
        return list;
    }
    public  List<ViewClubActivityMember> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewClubActivityMember> list = null;
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
    public  List<ViewClubActivityMember> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewClubActivityMember> list = sqlSession.selectList("ViewClubActivityMember.selectViewClubActivityMemberByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewClubActivityMember.selectCountByCondition",condition);
        return rows;
    }
    public  ViewClubActivityMember createEntity()    
    {
        return new ViewClubActivityMember();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewClubActivityMember();
    }
}