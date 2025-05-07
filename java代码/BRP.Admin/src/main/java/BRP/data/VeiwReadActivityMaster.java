package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.VeiwReadActivity;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class VeiwReadActivityMaster extends ViewDataMaster 
{
    public  List<VeiwReadActivity> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession)    
    {
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivitys");
        return list;
    }
    public  List<VeiwReadActivity> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession,String condition)    
    {
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivityByCondition",condition);
        return list;
    }
    public  List<VeiwReadActivity> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivityByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<VeiwReadActivity> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from veiw_read_activity limit %s, %s", pageSize * pageIndex, pageSize);
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivityBySql", sql);
        return list;
    }
    public  List<VeiwReadActivity> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivityByCondition",sql);
        return list;
    }
    public  List<VeiwReadActivity> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<VeiwReadActivity> list = null;
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
    public  List<VeiwReadActivity> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<VeiwReadActivity> list = sqlSession.selectList("VeiwReadActivity.selectVeiwReadActivityByCondition",sql);
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
        Integer rows = sqlSession.selectOne("VeiwReadActivity.selectCountByCondition",condition);
        return rows;
    }
    public  VeiwReadActivity createEntity()    
    {
        return new VeiwReadActivity();
    }
    public  DataEntity createDataEntity()    
    {
        return new VeiwReadActivity();
    }
}