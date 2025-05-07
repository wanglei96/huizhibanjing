package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
import BRP.data.HomeNewBookInfo;
public class HomeNewBookInfoMaster extends ViewDataMaster 
{
    public  List<HomeNewBookInfo> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession)    
    {
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfos");
        return list;
    }
    public  List<HomeNewBookInfo> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession,String condition)    
    {
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfoByCondition",condition);
        return list;
    }
    public  List<HomeNewBookInfo> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfoByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<HomeNewBookInfo> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from home_new_book_info limit %s, %s", pageSize * pageIndex, pageSize);
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfoBySql", sql);
        return list;
    }
    public  List<HomeNewBookInfo> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfoByCondition",sql);
        return list;
    }
    public  List<HomeNewBookInfo> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<HomeNewBookInfo> list = null;
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
    public  List<HomeNewBookInfo> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<HomeNewBookInfo> list = sqlSession.selectList("HomeNewBookInfo.selectHomeNewBookInfoByCondition",sql);
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
        Integer rows = sqlSession.selectOne("HomeNewBookInfo.selectCountByCondition",condition);
        return rows;
    }
    public  HomeNewBookInfo createEntity()    
    {
        return new HomeNewBookInfo();
    }
    public  DataEntity createDataEntity()    
    {
        return new HomeNewBookInfo();
    }
}