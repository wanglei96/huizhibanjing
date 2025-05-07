package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import BRP.data.CompanyUserSysRole;
import java.math.*;
public class CompanyUserSysRoleMaster extends TableDataMaster 
{
    public  int add(CompanyUserSysRole entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CompanyUserSysRole entity)    
    {
        int rows = sqlSession.insert("CompanyUserSysRole.insertCompanyUserSysRole",entity);
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
        return add(sqlSession,(CompanyUserSysRole)dataEntity);
    }
    public  int addList(List<CompanyUserSysRole> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CompanyUserSysRole> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CompanyUserSysRole.insertCompanyUserSysRoles",list);
        return rows;
    }
    public  int delete(Integer companyUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,companyUserId,sysRoleId);
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
    public  int delete(SqlSession sqlSession,Integer companyUserId,Integer sysRoleId)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,companyUserId,sysRoleId));
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
        int rows = sqlSession.delete("CompanyUserSysRole.deleteCompanyUserSysRole",entity);
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
    public  DataEntity getDataEntity(Integer companyUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,companyUserId,sysRoleId);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer companyUserId,Integer sysRoleId)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,companyUserId,sysRoleId);
         return newDataEntity;
    }
    public  CompanyUserSysRole getEntity(Integer companyUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUserSysRole newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,companyUserId,sysRoleId);
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
    public  CompanyUserSysRole getEntity(SqlSession sqlSession,Integer companyUserId,Integer sysRoleId)    
    {
        CompanyUserSysRole entity = createEntity();
        entity.setCompanyUserId(companyUserId);
        entity.setSysRoleId(sysRoleId);
        CompanyUserSysRole newEntity = sqlSession.selectOne("CompanyUserSysRole.selectCompanyUserSysRole",entity);
        return newEntity;
    }
    public  List<CompanyUserSysRole> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession)    
    {
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoles");
        return list;
    }
    public  List<CompanyUserSysRole> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession,String condition)    
    {
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleByCondition",condition);
        return list;
    }
    public  List<CompanyUserSysRole> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CompanyUserSysRole> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from company_user_sys_role limit %s, %s", pageSize * pageIndex, pageSize);
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleBySql", sql);
        return list;
    }
    public  List<CompanyUserSysRole> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleByCondition",sql);
        return list;
    }
    public  List<CompanyUserSysRole> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleByCondition",sql);
        return list;
    }
    public  List<CompanyUserSysRole> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUserSysRole> list = null;
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
    public  List<CompanyUserSysRole> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CompanyUserSysRole> list = sqlSession.selectList("CompanyUserSysRole.selectCompanyUserSysRoleBySql",sql);
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
        Integer rows = sqlSession.selectOne("CompanyUserSysRole.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CompanyUserSysRole entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CompanyUserSysRole entity)throws IOException    
    {
        int rows = sqlSession.update("CompanyUserSysRole.updateCompanyUserSysRole",entity);
        return rows;
    }
    public  CompanyUserSysRole createEntity()    
    {
        return new CompanyUserSysRole();
    }
    public  DataEntity createDataEntity()    
    {
        return new CompanyUserSysRole();
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
        List<CompanyUserSysRole> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CompanyUserSysRole getEntityByCompanyUserId(Integer companyUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUserSysRole entity = null;
        try        
        {
             entity = getEntityByCompanyUserId(sqlSession,companyUserId);
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
    public  CompanyUserSysRole getEntityBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUserSysRole entity = null;
        try        
        {
             entity = getEntityBySysRoleId(sqlSession,sysRoleId);
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
    public  CompanyUserSysRole getEntityByCompanyUserId(SqlSession sqlSession,Integer companyUserId)    
    {
        CompanyUserSysRole entity = sqlSession.selectOne("selectCompanyUserSysRoleByCondition","company_user_id='"+companyUserId+"'");
        return entity;
    }
    public  CompanyUserSysRole getEntityBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        CompanyUserSysRole entity = sqlSession.selectOne("selectCompanyUserSysRoleByCondition","sys_role_id='"+sysRoleId+"'");
        return entity;
    }
    public  boolean existsCompanyUserId(Integer companyUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCompanyUserId(sqlSession,companyUserId);
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
    public  boolean existsSysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsSysRoleId(sqlSession,sysRoleId);
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
    public  boolean existsCompanyUserId(SqlSession sqlSession,Integer companyUserId)    
    {
        Integer rows = sqlSession.selectOne("CompanyUserSysRole.selectIntBySql","select count(*) from company_user_sys_role where company_user_id= '"+companyUserId+"'");
        return rows>0?true:false;
    }
    public  boolean existsSysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        Integer rows = sqlSession.selectOne("CompanyUserSysRole.selectIntBySql","select count(*) from company_user_sys_role where sys_role_id= '"+sysRoleId+"'");
        return rows>0?true:false;
    }
}