package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.ViewMemberScore;
public class ViewMemberScoreMaster extends ViewDataMaster 
{
    public  List<ViewMemberScore> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession)    
    {
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScores");
        return list;
    }
    public  List<ViewMemberScore> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScoreByCondition",condition);
        return list;
    }
    public  List<ViewMemberScore> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScoreByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewMemberScore> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_member_score limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScoreBySql", sql);
        return list;
    }
    public  List<ViewMemberScore> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScoreByCondition",sql);
        return list;
    }
    public  List<ViewMemberScore> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewMemberScore> list = null;
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
    public  List<ViewMemberScore> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewMemberScore> list = sqlSession.selectList("ViewMemberScore.selectViewMemberScoreByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewMemberScore.selectCountByCondition",condition);
        return rows;
    }
    public  ViewMemberScore createEntity()    
    {
        return new ViewMemberScore();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewMemberScore();
    }
}