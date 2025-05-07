package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.data.CmsSiteType;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import strosoft.app.common.CodeDataManager;
import java.math.*;
public class CmsSiteTypeMaster extends CodeDataManager 
{
    public  int add(CmsSiteType entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsSiteType entity)    
    {
        int rows = sqlSession.insert("CmsSiteType.insertCmsSiteType",entity);
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
        return add(sqlSession,(CmsSiteType)dataEntity);
    }
    public  int addList(List<CmsSiteType> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsSiteType> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsSiteType.insertCmsSiteTypes",list);
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
        int rows = sqlSession.delete("CmsSiteType.deleteCmsSiteType",entity);
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
    public  CmsSiteType getEntity(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsSiteType newEntity = null;
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
    public  CmsSiteType getEntity(SqlSession sqlSession,String code)    
    {
        CmsSiteType entity = createEntity();
        entity.setCode(code);
        CmsSiteType newEntity = sqlSession.selectOne("CmsSiteType.selectCmsSiteType",entity);
        return newEntity;
    }
    public  List<CmsSiteType> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession)    
    {
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypes");
        return list;
    }
    public  List<CmsSiteType> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeByCondition",condition);
        return list;
    }
    public  List<CmsSiteType> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsSiteType> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_site_type limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeBySql", sql);
        return list;
    }
    public  List<CmsSiteType> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeByCondition",sql);
        return list;
    }
    public  List<CmsSiteType> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeByCondition",sql);
        return list;
    }
    public  List<CmsSiteType> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsSiteType> list = null;
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
    public  List<CmsSiteType> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsSiteType> list = sqlSession.selectList("CmsSiteType.selectCmsSiteTypeBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsSiteType.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsSiteType entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsSiteType entity)throws IOException    
    {
        int rows = sqlSession.update("CmsSiteType.updateCmsSiteType",entity);
        return rows;
    }
    public  CmsSiteType createEntity()    
    {
        return new CmsSiteType();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsSiteType();
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
        List<CmsSiteType> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CmsSiteType getEntityByCode(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsSiteType entity = null;
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
    public  CmsSiteType getEntityByCode(SqlSession sqlSession,String code)    
    {
        CmsSiteType entity = sqlSession.selectOne("selectCmsSiteTypeByCondition","code='"+code+"'");
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
        Integer rows = sqlSession.selectOne("CmsSiteType.selectIntBySql","select count(*) from cms_site_type where code= '"+code+"'");
        return rows>0?true:false;
    }
}