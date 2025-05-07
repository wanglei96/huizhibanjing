package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.CompanyRolePermission;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import java.math.*;
public class CompanyRolePermissionMaster extends TableDataMaster 
{
    public  int add(CompanyRolePermission entity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = add(sqlSession,entity);
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
        return rows;
    }
    public  int add(SqlSession sqlSession,CompanyRolePermission entity)    
    {
        int rows = sqlSession.insert("CompanyRolePermission.insertCompanyRolePermission",entity);
        return rows;
    }
    public  int add(DataEntity dataEntity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Integer num = null;
        try        
        {
            num  = add(sqlSession,dataEntity);
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
        return num;
    }
    public  int add(SqlSession sqlSession,DataEntity dataEntity)    
    {
        return add(sqlSession,(CompanyRolePermission)dataEntity);
    }
    public  int addList(List<CompanyRolePermission> list)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = addList(sqlSession,list);
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
        return rows;
    }
    public  int addList(SqlSession sqlSession,List<CompanyRolePermission> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CompanyRolePermission.insertCompanyRolePermissions",list);
        return rows;
    }
    public  int delete(Integer companyRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,companyRoleId,permission);
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
        return rows;
    }
    public  int delete(SqlSession sqlSession,Integer companyRoleId,String permission)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,companyRoleId,permission));
        return rows;
    }
    public  int delete(DataEntity entity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,entity);
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
        return rows;
    }
    public  int delete(SqlSession sqlSession,DataEntity entity)    
    {
        int rows = sqlSession.delete("CompanyRolePermission.deleteCompanyRolePermission",entity);
        return rows;
    }
    public  int batchDeleteByEntity(SqlSession sqlSession,List<? extends DataEntity> list)    
    {
        int rows = 0;
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  DataEntity getDataEntity(Integer companyRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,companyRoleId,permission);
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
        return newDataEntity;
    }
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer companyRoleId,String permission)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,companyRoleId,permission);
         return newDataEntity;
    }
    public  CompanyRolePermission getEntity(Integer companyRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyRolePermission newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,companyRoleId,permission);
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
        return newEntity;
    }
    public  CompanyRolePermission getEntity(SqlSession sqlSession,Integer companyRoleId,String permission)    
    {
        CompanyRolePermission entity = createEntity();
        entity.setCompanyRoleId(companyRoleId);
        entity.setPermission(permission);
        CompanyRolePermission newEntity = sqlSession.selectOne("CompanyRolePermission.selectCompanyRolePermission",entity);
        return newEntity;
    }
    public  List<CompanyRolePermission> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession)    
    {
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissions");
        return list;
    }
    public  List<CompanyRolePermission> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession,String condition)    
    {
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionByCondition",condition);
        return list;
    }
    public  List<CompanyRolePermission> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CompanyRolePermission> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from company_role_permission limit %s, %s", pageSize * pageIndex, pageSize);
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionBySql", sql);
        return list;
    }
    public  List<CompanyRolePermission> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionByCondition",sql);
        return list;
    }
    public  List<CompanyRolePermission> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
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
    public  List<CompanyRolePermission> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionByCondition",sql);
        return list;
    }
    public  List<CompanyRolePermission> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyRolePermission> list = null;
        try        
        {
            list = getListBySql(sqlSession,sql);
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
    public  List<CompanyRolePermission> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CompanyRolePermission> list = sqlSession.selectList("CompanyRolePermission.selectCompanyRolePermissionBySql",sql);
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
        Integer rows = sqlSession.selectOne("CompanyRolePermission.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CompanyRolePermission entity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = update(sqlSession,entity);
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
        return rows;
    }
    public  int update(SqlSession sqlSession,CompanyRolePermission entity)throws IOException    
    {
        int rows = sqlSession.update("CompanyRolePermission.updateCompanyRolePermission",entity);
        return rows;
    }
    public  CompanyRolePermission createEntity()    
    {
        return new CompanyRolePermission();
    }
    public  DataEntity createDataEntity()    
    {
        return new CompanyRolePermission();
    }
    public  int clear()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = clear(sqlSession);
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
        return rows;
    }
    public  int clear(SqlSession sqlSession)    
    {
        int rows = 0;
        List<CompanyRolePermission> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CompanyRolePermission getEntityByCompanyRoleId(Integer companyRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyRolePermission entity = null;
        try        
        {
             entity = getEntityByCompanyRoleId(sqlSession,companyRoleId);
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
        return entity;
    }
    public  CompanyRolePermission getEntityByPermission(String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyRolePermission entity = null;
        try        
        {
             entity = getEntityByPermission(sqlSession,permission);
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
        return entity;
    }
    public  CompanyRolePermission getEntityByCompanyRoleId(SqlSession sqlSession,Integer companyRoleId)    
    {
        CompanyRolePermission entity = sqlSession.selectOne("selectCompanyRolePermissionByCondition","company_role_id='"+companyRoleId+"'");
        return entity;
    }
    public  CompanyRolePermission getEntityByPermission(SqlSession sqlSession,String permission)    
    {
        CompanyRolePermission entity = sqlSession.selectOne("selectCompanyRolePermissionByCondition","permission='"+permission+"'");
        return entity;
    }
    public  boolean existsCompanyRoleId(Integer companyRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCompanyRoleId(sqlSession,companyRoleId);
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
        return flag;
    }
    public  boolean existsPermission(String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsPermission(sqlSession,permission);
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
        return flag;
    }
    public  boolean existsCompanyRoleId(SqlSession sqlSession,Integer companyRoleId)    
    {
        Integer rows = sqlSession.selectOne("CompanyRolePermission.selectIntBySql","select count(*) from company_role_permission where company_role_id= '"+companyRoleId+"'");
        return rows>0?true:false;
    }
    public  boolean existsPermission(SqlSession sqlSession,String permission)    
    {
        Integer rows = sqlSession.selectOne("CompanyRolePermission.selectIntBySql","select count(*) from company_role_permission where permission= '"+permission+"'");
        return rows>0?true:false;
    }
}