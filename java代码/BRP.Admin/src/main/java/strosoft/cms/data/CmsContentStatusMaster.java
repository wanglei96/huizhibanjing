package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.model.CmsContentManager;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import strosoft.app.common.CodeDataManager;
import strosoft.cms.data.CmsContentStatus;
import java.math.*;
public class CmsContentStatusMaster extends CodeDataManager 
{
    public  int add(CmsContentStatus entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsContentStatus entity)    
    {
        int rows = sqlSession.insert("CmsContentStatus.insertCmsContentStatus",entity);
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
        return add(sqlSession,(CmsContentStatus)dataEntity);
    }
    public  int addList(List<CmsContentStatus> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsContentStatus> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsContentStatus.insertCmsContentStatuss",list);
        return rows;
    }
    public  int delete(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,code);
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
    public  int delete(SqlSession sqlSession,String code)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,code));
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
        CmsContentManager.getInstance().deleteByCmsContentStatusCode(sqlSession,((CmsContentStatus)entity).getCode());
        int rows = sqlSession.delete("CmsContentStatus.deleteCmsContentStatus",entity);
        return rows;
    }
    public  int batchDelete(List<String> list)throws Exception    
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
    public  int batchDelete(SqlSession sqlSession,List<String> list)    
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
    public  DataEntity getDataEntity(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,code);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,String code)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,code);
         return newDataEntity;
    }
    public  CmsContentStatus getEntity(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentStatus newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,code);
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
    public  CmsContentStatus getEntity(SqlSession sqlSession,String code)    
    {
        CmsContentStatus entity = createEntity();
        entity.setCode(code);
        CmsContentStatus newEntity = sqlSession.selectOne("CmsContentStatus.selectCmsContentStatus",entity);
        return newEntity;
    }
    public  List<CmsContentStatus> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession)    
    {
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatuss");
        return list;
    }
    public  List<CmsContentStatus> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusByCondition",condition);
        return list;
    }
    public  List<CmsContentStatus> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsContentStatus> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_content_status limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusBySql", sql);
        return list;
    }
    public  List<CmsContentStatus> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusByCondition",sql);
        return list;
    }
    public  List<CmsContentStatus> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusByCondition",sql);
        return list;
    }
    public  List<CmsContentStatus> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentStatus> list = null;
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
    public  List<CmsContentStatus> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsContentStatus> list = sqlSession.selectList("CmsContentStatus.selectCmsContentStatusBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsContentStatus.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsContentStatus entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsContentStatus entity)throws IOException    
    {
        int rows = sqlSession.update("CmsContentStatus.updateCmsContentStatus",entity);
        return rows;
    }
    public  CmsContentStatus createEntity()    
    {
        return new CmsContentStatus();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsContentStatus();
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
        List<CmsContentStatus> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CmsContentStatus getEntityByCode(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentStatus entity = null;
        try        
        {
             entity = getEntityByCode(sqlSession,code);
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
    public  CmsContentStatus getEntityByCode(SqlSession sqlSession,String code)    
    {
        CmsContentStatus entity = sqlSession.selectOne("selectCmsContentStatusByCondition","code='"+code+"'");
        return entity;
    }
    public  boolean existsCode(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCode(sqlSession,code);
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
    public  boolean existsCode(SqlSession sqlSession,String code)    
    {
        Integer rows = sqlSession.selectOne("CmsContentStatus.selectIntBySql","select count(*) from cms_content_status where code= '"+code+"'");
        return rows>0?true:false;
    }
}