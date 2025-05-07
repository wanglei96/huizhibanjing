package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.sys.data.SysRoleCmsChannelPermission;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysRoleCmsChannelPermissionMaster extends IdDataManager 
{
    public  int add(SysRoleCmsChannelPermission entity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int result = 0;
        try        
        {
            result = add(sqlSession,entity);
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
        return result;
    }
    public  int add(SqlSession sqlSession,SysRoleCmsChannelPermission entity)    
    {
        int rows = sqlSession.insert("SysRoleCmsChannelPermission.insertSysRoleCmsChannelPermission",entity);
        int newId = Integer.valueOf(entity.getId().toString());
        if (newId > 0)         
        {
            return newId;
        }
         else         
        {
            return rows;
        }
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
        return add(sqlSession,(SysRoleCmsChannelPermission)dataEntity);
    }
    public  int addList(List<SysRoleCmsChannelPermission> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysRoleCmsChannelPermission> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysRoleCmsChannelPermission.insertSysRoleCmsChannelPermissions",list);
        return rows;
    }
    public  int delete(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,id);
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
    public  int delete(SqlSession sqlSession,Integer id)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,id));
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
        int rows = sqlSession.delete("SysRoleCmsChannelPermission.deleteSysRoleCmsChannelPermission",entity);
        return rows;
    }
    public  int batchDelete(List<Integer> list)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            for(int i=0;i<list.size();i++)            
            {
                rows += delete(sqlSession,list.get(i));
            }
            sqlSession.commit();
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
            rows=0;
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int batchDelete(SqlSession sqlSession,List<Integer> list)    
    {
        int rows = 0;
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
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
    public  DataEntity getDataEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,id);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer id)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,id);
         return newDataEntity;
    }
    public  SysRoleCmsChannelPermission getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysRoleCmsChannelPermission newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,id);
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
    public  SysRoleCmsChannelPermission getEntity(SqlSession sqlSession,Integer id)    
    {
        SysRoleCmsChannelPermission entity = createEntity();
        entity.setId(id);
        SysRoleCmsChannelPermission newEntity = sqlSession.selectOne("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermission",entity);
        return newEntity;
    }
    public  List<SysRoleCmsChannelPermission> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession)    
    {
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissions");
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession,String condition)    
    {
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCondition",condition);
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_role_cms_channel_permission limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionBySql", sql);
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCondition",sql);
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCondition",sql);
        return list;
    }
    public  List<SysRoleCmsChannelPermission> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> list = null;
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
    public  List<SysRoleCmsChannelPermission> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysRoleCmsChannelPermission> list = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysRoleCmsChannelPermission.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysRoleCmsChannelPermission entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysRoleCmsChannelPermission entity)throws IOException    
    {
        int rows = sqlSession.update("SysRoleCmsChannelPermission.updateSysRoleCmsChannelPermission",entity);
        return rows;
    }
    public  SysRoleCmsChannelPermission createEntity()    
    {
        return new SysRoleCmsChannelPermission();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysRoleCmsChannelPermission();
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
        List<SysRoleCmsChannelPermission> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByCmsChannelId(Integer cmsChannelId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsChannelId(sqlSession,cmsChannelId);
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
    public  int deleteByCmsSiteId(Integer cmsSiteId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsSiteId(sqlSession,cmsSiteId);
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
    public  int deleteByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<SysRoleCmsChannelPermission> list = getListByCmsChannelId(sqlSession,cmsChannelId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsSiteId(SqlSession sqlSession,Integer cmsSiteId)    
    {
        List<SysRoleCmsChannelPermission> list = getListByCmsSiteId(sqlSession,cmsSiteId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysRoleCmsChannelPermission> list = getListBySysRoleId(sqlSession,sysRoleId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SysRoleCmsChannelPermission> getListByCmsChannelId(Integer cmsChannelId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> entitys = null;
         try        
        {
            entitys = getListByCmsChannelId(sqlSession,cmsChannelId);
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
    public  List<SysRoleCmsChannelPermission> getListByCmsSiteId(Integer cmsSiteId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> entitys = null;
         try        
        {
            entitys = getListByCmsSiteId(sqlSession,cmsSiteId);
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
    public  List<SysRoleCmsChannelPermission> getListBySysRoleId(Integer sysRoleId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysRoleCmsChannelPermission> entitys = null;
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
    public  List<SysRoleCmsChannelPermission> getListByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<SysRoleCmsChannelPermission> entitys = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCmsChannelId",cmsChannelId);
        return entitys;
    }
    public  List<SysRoleCmsChannelPermission> getListByCmsSiteId(SqlSession sqlSession,Integer cmsSiteId)    
    {
        List<SysRoleCmsChannelPermission> entitys = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionByCmsSiteId",cmsSiteId);
        return entitys;
    }
    public  List<SysRoleCmsChannelPermission> getListBySysRoleId(SqlSession sqlSession,Integer sysRoleId)    
    {
        List<SysRoleCmsChannelPermission> entitys = sqlSession.selectList("SysRoleCmsChannelPermission.selectSysRoleCmsChannelPermissionBySysRoleId",sysRoleId);
        return entitys;
    }
    public  SysRoleCmsChannelPermission getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysRoleCmsChannelPermission entity = null;
        try        
        {
             entity = getEntityById(sqlSession,id);
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
    public  SysRoleCmsChannelPermission getEntityById(SqlSession sqlSession,Integer id)    
    {
        SysRoleCmsChannelPermission entity = sqlSession.selectOne("selectSysRoleCmsChannelPermissionByCondition","id='"+id+"'");
        return entity;
    }
    public  boolean existsId(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsId(sqlSession,id);
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
    public  boolean existsId(SqlSession sqlSession,Integer id)    
    {
        Integer rows = sqlSession.selectOne("SysRoleCmsChannelPermission.selectIntBySql","select count(*) from sys_role_cms_channel_permission where id= '"+id+"'");
        return rows>0?true:false;
    }
}