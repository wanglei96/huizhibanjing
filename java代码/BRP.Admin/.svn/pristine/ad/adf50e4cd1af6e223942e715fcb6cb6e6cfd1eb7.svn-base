package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import strosoft.sys.data.SysRolePermission;
import java.math.*;
public class SysRolePermissionMaster extends TableDataMaster 
{
    public  int add(SysRolePermission entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysRolePermission entity)    
    {
        int rows = sqlSession.insert("SysRolePermission.insertSysRolePermission",entity);
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
        return add(sqlSession,(SysRolePermission)dataEntity);
    }
    public  int addList(List<SysRolePermission> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysRolePermission> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysRolePermission.insertSysRolePermissions",list);
        return rows;
    }
    public  int delete(Integer sysRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,sysRoleId,permission);
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
    public  int delete(SqlSession sqlSession,Integer sysRoleId,String permission)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,sysRoleId,permission));
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
        int rows = sqlSession.delete("SysRolePermission.deleteSysRolePermission",entity);
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
    public  DataEntity getDataEntity(Integer sysRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,sysRoleId,permission);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer sysRoleId,String permission)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,sysRoleId,permission);
         return newDataEntity;
    }
    public  SysRolePermission getEntity(Integer sysRoleId,String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysRolePermission newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,sysRoleId,permission);
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
    public  SysRolePermission getEntity(SqlSession sqlSession,Integer sysRoleId,String permission)    
    {
        SysRolePermission entity = createEntity();
        entity.setSysRoleId(sysRoleId);
        entity.setPermission(permission);
        SysRolePermission newEntity = sqlSession.selectOne("SysRolePermission.selectSysRolePermission",entity);
        return newEntity;
    }
    public  List<SysRolePermission> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession)    
    {
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissions");
        return list;
    }
    public  List<SysRolePermission> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession,String condition)    
    {
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionByCondition",condition);
        return list;
    }
    public  List<SysRolePermission> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysRolePermission> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_role_permission limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionBySql", sql);
        return list;
    }
    public  List<SysRolePermission> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionByCondition",sql);
        return list;
    }
    public  List<SysRolePermission> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionByCondition",sql);
        return list;
    }
    public  List<SysRolePermission> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> list = null;
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
    public  List<SysRolePermission> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysRolePermission> list = sqlSession.selectList("SysRolePermission.selectSysRolePermissionBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysRolePermission.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysRolePermission entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysRolePermission entity)throws IOException    
    {
        int rows = sqlSession.update("SysRolePermission.updateSysRolePermission",entity);
        return rows;
    }
    public  SysRolePermission createEntity()    
    {
        return new SysRolePermission();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysRolePermission();
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
        List<SysRolePermission> list = getList(sqlSession);
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
    public  int deleteBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysRolePermission> list = getListBySysRoleId(sqlSession,sysRoleId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SysRolePermission> getListBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRolePermission> entitys = null;
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
    public  List<SysRolePermission> getListBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysRolePermission> entitys = sqlSession.selectList("SysRolePermission.selectSysRolePermissionBySysRoleId",sysRoleId);
        return entitys;
    }
    public  SysRolePermission getEntityBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysRolePermission entity = null;
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
    public  SysRolePermission getEntityByPermission(String permission)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysRolePermission entity = null;
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
    public  SysRolePermission getEntityBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        SysRolePermission entity = sqlSession.selectOne("selectSysRolePermissionByCondition","sys_role_id='"+sysRoleId+"'");
        return entity;
    }
    public  SysRolePermission getEntityByPermission(SqlSession sqlSession,String permission)    
    {
        SysRolePermission entity = sqlSession.selectOne("selectSysRolePermissionByCondition","permission='"+permission+"'");
        return entity;
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
    public  boolean existsSysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        Integer rows = sqlSession.selectOne("SysRolePermission.selectIntBySql","select count(*) from sys_role_permission where sys_role_id= '"+sysRoleId+"'");
        return rows>0?true:false;
    }
    public  boolean existsPermission(SqlSession sqlSession,String permission)    
    {
        Integer rows = sqlSession.selectOne("SysRolePermission.selectIntBySql","select count(*) from sys_role_permission where permission= '"+permission+"'");
        return rows>0?true:false;
    }
}