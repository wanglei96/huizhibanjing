package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.data.CmsSite;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.model.CmsChannelManager;
import java.math.*;
import strosoft.cms.model.CmsTemplateManager;
public class CmsSiteMaster extends IdDataManager 
{
    public  int add(CmsSite entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsSite entity)    
    {
        int rows = sqlSession.insert("CmsSite.insertCmsSite",entity);
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
        return add(sqlSession,(CmsSite)dataEntity);
    }
    public  int addList(List<CmsSite> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsSite> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsSite.insertCmsSites",list);
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
        CmsChannelManager.getInstance().deleteByCmsSiteId(sqlSession,((CmsSite)entity).getId());
        CmsTemplateManager.getInstance().deleteByCmsSiteId(sqlSession,((CmsSite)entity).getId());
        SysRoleCmsChannelPermissionManager.getInstance().deleteByCmsSiteId(sqlSession,((CmsSite)entity).getId());
        int rows = sqlSession.delete("CmsSite.deleteCmsSite",entity);
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
    public  CmsSite getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsSite newEntity = null;
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
    public  CmsSite getEntity(SqlSession sqlSession,Integer id)    
    {
        CmsSite entity = createEntity();
        entity.setId(id);
        CmsSite newEntity = sqlSession.selectOne("CmsSite.selectCmsSite",entity);
        return newEntity;
    }
    public  List<CmsSite> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession)    
    {
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSites");
        return list;
    }
    public  List<CmsSite> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteByCondition",condition);
        return list;
    }
    public  List<CmsSite> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsSite> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_site limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteBySql", sql);
        return list;
    }
    public  List<CmsSite> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteByCondition",sql);
        return list;
    }
    public  List<CmsSite> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteByCondition",sql);
        return list;
    }
    public  List<CmsSite> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSite> list = null;
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
    public  List<CmsSite> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsSite> list = sqlSession.selectList("CmsSite.selectCmsSiteBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsSite.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsSite entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsSite entity)throws IOException    
    {
        int rows = sqlSession.update("CmsSite.updateCmsSite",entity);
        return rows;
    }
    public  CmsSite createEntity()    
    {
        return new CmsSite();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsSite();
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
        List<CmsSite> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CmsSite getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsSite entity = null;
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
    public  CmsSite getEntityById(SqlSession sqlSession,Integer id)    
    {
        CmsSite entity = sqlSession.selectOne("selectCmsSiteByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("CmsSite.selectIntBySql","select count(*) from cms_site where id= '"+id+"'");
        return rows>0?true:false;
    }
}