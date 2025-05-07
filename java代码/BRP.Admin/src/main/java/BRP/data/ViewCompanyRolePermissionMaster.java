package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import BRP.data.ViewCompanyRolePermission;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class ViewCompanyRolePermissionMaster extends ViewDataMaster 
{
    public  List<ViewCompanyRolePermission> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession)    
    {
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissions");
        return list;
    }
    public  List<ViewCompanyRolePermission> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissionByCondition",condition);
        return list;
    }
    public  List<ViewCompanyRolePermission> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissionByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCompanyRolePermission> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from view_company_role_permission limit %s, %s", pageSize * pageIndex, pageSize);
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissionBySql", sql);
        return list;
    }
    public  List<ViewCompanyRolePermission> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissionByCondition",sql);
        return list;
    }
    public  List<ViewCompanyRolePermission> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCompanyRolePermission> list = null;
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
    public  List<ViewCompanyRolePermission> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<ViewCompanyRolePermission> list = sqlSession.selectList("ViewCompanyRolePermission.selectViewCompanyRolePermissionByCondition",sql);
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
        Integer rows = sqlSession.selectOne("ViewCompanyRolePermission.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCompanyRolePermission createEntity()    
    {
        return new ViewCompanyRolePermission();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCompanyRolePermission();
    }
}