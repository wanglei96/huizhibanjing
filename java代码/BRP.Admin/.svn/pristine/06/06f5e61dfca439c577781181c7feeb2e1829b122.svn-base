package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.sys.data.SysUserRole;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysUserRoleMaster extends TableDataMaster 
{
    public  int add(SysUserRole entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysUserRole entity)    
    {
        int rows = sqlSession.insert("SysUserRole.insertSysUserRole",entity);
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
        return add(sqlSession,(SysUserRole)dataEntity);
    }
    public  int addList(List<SysUserRole> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysUserRole> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysUserRole.insertSysUserRoles",list);
        return rows;
    }
    public  int delete(Integer sysUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,sysUserId,sysRoleId);
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
    public  int delete(SqlSession sqlSession,Integer sysUserId,Integer sysRoleId)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,sysUserId,sysRoleId));
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
        int rows = sqlSession.delete("SysUserRole.deleteSysUserRole",entity);
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
    public  DataEntity getDataEntity(Integer sysUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,sysUserId,sysRoleId);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer sysUserId,Integer sysRoleId)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,sysUserId,sysRoleId);
         return newDataEntity;
    }
    public  SysUserRole getEntity(Integer sysUserId,Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysUserRole newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,sysUserId,sysRoleId);
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
    public  SysUserRole getEntity(SqlSession sqlSession,Integer sysUserId,Integer sysRoleId)    
    {
        SysUserRole entity = createEntity();
        entity.setSysUserId(sysUserId);
        entity.setSysRoleId(sysRoleId);
        SysUserRole newEntity = sqlSession.selectOne("SysUserRole.selectSysUserRole",entity);
        return newEntity;
    }
    public  List<SysUserRole> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession)    
    {
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoles");
        return list;
    }
    public  List<SysUserRole> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession,String condition)    
    {
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleByCondition",condition);
        return list;
    }
    public  List<SysUserRole> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysUserRole> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_user_role limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleBySql", sql);
        return list;
    }
    public  List<SysUserRole> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleByCondition",sql);
        return list;
    }
    public  List<SysUserRole> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleByCondition",sql);
        return list;
    }
    public  List<SysUserRole> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> list = null;
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
    public  List<SysUserRole> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysUserRole> list = sqlSession.selectList("SysUserRole.selectSysUserRoleBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysUserRole.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysUserRole entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysUserRole entity)throws IOException    
    {
        int rows = sqlSession.update("SysUserRole.updateSysUserRole",entity);
        return rows;
    }
    public  SysUserRole createEntity()    
    {
        return new SysUserRole();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysUserRole();
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
        List<SysUserRole> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySysRoleId(sqlSession,sysRoleId);
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
    public  int deleteBySysUserId(Integer sysUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySysUserId(sqlSession,sysUserId);
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
    public  int deleteBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysUserRole> list = getListBySysRoleId(sqlSession,sysRoleId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySysUserId(SqlSession sqlSession,Integer sysUserId)    
    {
        List<SysUserRole> list = getListBySysUserId(sqlSession,sysUserId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SysUserRole> getListBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> entitys = null;
         try        
        {
            entitys = getListBySysRoleId(sqlSession,sysRoleId);
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
        return entitys;
    }
    public  List<SysUserRole> getListBySysUserId(Integer sysUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUserRole> entitys = null;
         try        
        {
            entitys = getListBySysUserId(sqlSession,sysUserId);
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
        return entitys;
    }
    public  List<SysUserRole> getListBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysUserRole> entitys = sqlSession.selectList("SysUserRole.selectSysUserRoleBySysRoleId",sysRoleId);
        return entitys;
    }
    public  List<SysUserRole> getListBySysUserId(SqlSession sqlSession,Integer sysUserId)    
    {
        List<SysUserRole> entitys = sqlSession.selectList("SysUserRole.selectSysUserRoleBySysUserId",sysUserId);
        return entitys;
    }
    public  SysUserRole getEntityBySysUserId(Integer sysUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysUserRole entity = null;
        try        
        {
             entity = getEntityBySysUserId(sqlSession,sysUserId);
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
    public  SysUserRole getEntityBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysUserRole entity = null;
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
    public  SysUserRole getEntityBySysUserId(SqlSession sqlSession,Integer sysUserId)    
    {
        SysUserRole entity = sqlSession.selectOne("selectSysUserRoleByCondition","sys_user_id='"+sysUserId+"'");
        return entity;
    }
    public  SysUserRole getEntityBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        SysUserRole entity = sqlSession.selectOne("selectSysUserRoleByCondition","sys_role_id='"+sysRoleId+"'");
        return entity;
    }
    public  boolean existsSysUserId(Integer sysUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsSysUserId(sqlSession,sysUserId);
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
    public  boolean existsSysUserId(SqlSession sqlSession,Integer sysUserId)    
    {
        Integer rows = sqlSession.selectOne("SysUserRole.selectIntBySql","select count(*) from sys_user_role where sys_user_id= '"+sysUserId+"'");
        return rows>0?true:false;
    }
    public  boolean existsSysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        Integer rows = sqlSession.selectOne("SysUserRole.selectIntBySql","select count(*) from sys_user_role where sys_role_id= '"+sysRoleId+"'");
        return rows>0?true:false;
    }
}