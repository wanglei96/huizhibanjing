package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.sys.data.SysBasicTableRelation;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysBasicTableRelationMaster extends TableDataMaster 
{
    public  int add(SysBasicTableRelation entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysBasicTableRelation entity)    
    {
        int rows = sqlSession.insert("SysBasicTableRelation.insertSysBasicTableRelation",entity);
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
        return add(sqlSession,(SysBasicTableRelation)dataEntity);
    }
    public  int addList(List<SysBasicTableRelation> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysBasicTableRelation> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysBasicTableRelation.insertSysBasicTableRelations",list);
        return rows;
    }
    public  int delete(String relatedTableName,String relatedColumnName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,relatedTableName,relatedColumnName);
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
    public  int delete(SqlSession sqlSession,String relatedTableName,String relatedColumnName)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,relatedTableName,relatedColumnName));
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
        int rows = sqlSession.delete("SysBasicTableRelation.deleteSysBasicTableRelation",entity);
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
    public  DataEntity getDataEntity(String relatedTableName,String relatedColumnName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,relatedTableName,relatedColumnName);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,String relatedTableName,String relatedColumnName)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,relatedTableName,relatedColumnName);
         return newDataEntity;
    }
    public  SysBasicTableRelation getEntity(String relatedTableName,String relatedColumnName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableRelation newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,relatedTableName,relatedColumnName);
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
    public  SysBasicTableRelation getEntity(SqlSession sqlSession,String relatedTableName,String relatedColumnName)    
    {
        SysBasicTableRelation entity = createEntity();
        entity.setRelatedTableName(relatedTableName);
        entity.setRelatedColumnName(relatedColumnName);
        SysBasicTableRelation newEntity = sqlSession.selectOne("SysBasicTableRelation.selectSysBasicTableRelation",entity);
        return newEntity;
    }
    public  List<SysBasicTableRelation> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession)    
    {
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelations");
        return list;
    }
    public  List<SysBasicTableRelation> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession,String condition)    
    {
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationByCondition",condition);
        return list;
    }
    public  List<SysBasicTableRelation> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysBasicTableRelation> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_basic_table_relation limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationBySql", sql);
        return list;
    }
    public  List<SysBasicTableRelation> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationByCondition",sql);
        return list;
    }
    public  List<SysBasicTableRelation> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationByCondition",sql);
        return list;
    }
    public  List<SysBasicTableRelation> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> list = null;
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
    public  List<SysBasicTableRelation> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysBasicTableRelation> list = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysBasicTableRelation.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysBasicTableRelation entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysBasicTableRelation entity)throws IOException    
    {
        int rows = sqlSession.update("SysBasicTableRelation.updateSysBasicTableRelation",entity);
        return rows;
    }
    public  SysBasicTableRelation createEntity()    
    {
        return new SysBasicTableRelation();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysBasicTableRelation();
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
        List<SysBasicTableRelation> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByBasicTableName(String basicTableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByBasicTableName(sqlSession,basicTableName);
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
    public  int deleteByBasicTableName(SqlSession sqlSession,String basicTableName)    
    {
        List<SysBasicTableRelation> list = getListByBasicTableName(sqlSession,basicTableName);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SysBasicTableRelation> getListByBasicTableName(String basicTableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableRelation> entitys = null;
         try        
        {
            entitys = getListByBasicTableName(sqlSession,basicTableName);
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
    public  List<SysBasicTableRelation> getListByBasicTableName(SqlSession sqlSession,String basicTableName)    
    {
        List<SysBasicTableRelation> entitys = sqlSession.selectList("SysBasicTableRelation.selectSysBasicTableRelationByBasicTableName",basicTableName);
        return entitys;
    }
    public  SysBasicTableRelation getEntityByRelatedTableName(String relatedTableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableRelation entity = null;
        try        
        {
             entity = getEntityByRelatedTableName(sqlSession,relatedTableName);
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
    public  SysBasicTableRelation getEntityByRelatedColumnName(String relatedColumnName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableRelation entity = null;
        try        
        {
             entity = getEntityByRelatedColumnName(sqlSession,relatedColumnName);
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
    public  SysBasicTableRelation getEntityByRelatedTableName(SqlSession sqlSession,String relatedTableName)    
    {
        SysBasicTableRelation entity = sqlSession.selectOne("selectSysBasicTableRelationByCondition","related_table_name='"+relatedTableName+"'");
        return entity;
    }
    public  SysBasicTableRelation getEntityByRelatedColumnName(SqlSession sqlSession,String relatedColumnName)    
    {
        SysBasicTableRelation entity = sqlSession.selectOne("selectSysBasicTableRelationByCondition","related_column_name='"+relatedColumnName+"'");
        return entity;
    }
    public  boolean existsRelatedTableName(String relatedTableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsRelatedTableName(sqlSession,relatedTableName);
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
    public  boolean existsRelatedColumnName(String relatedColumnName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsRelatedColumnName(sqlSession,relatedColumnName);
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
    public  boolean existsRelatedTableName(SqlSession sqlSession,String relatedTableName)    
    {
        Integer rows = sqlSession.selectOne("SysBasicTableRelation.selectIntBySql","select count(*) from sys_basic_table_relation where related_table_name= '"+relatedTableName+"'");
        return rows>0?true:false;
    }
    public  boolean existsRelatedColumnName(SqlSession sqlSession,String relatedColumnName)    
    {
        Integer rows = sqlSession.selectOne("SysBasicTableRelation.selectIntBySql","select count(*) from sys_basic_table_relation where related_column_name= '"+relatedColumnName+"'");
        return rows>0?true:false;
    }
}