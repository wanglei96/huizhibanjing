package strosoft.sys.data;
import strosoft.sys.data.SysBasicTableField;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysBasicTableFieldMaster extends TableDataMaster 
{
    public  int add(SysBasicTableField entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysBasicTableField entity)    
    {
        int rows = sqlSession.insert("SysBasicTableField.insertSysBasicTableField",entity);
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
        return add(sqlSession,(SysBasicTableField)dataEntity);
    }
    public  int addList(List<SysBasicTableField> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysBasicTableField> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysBasicTableField.insertSysBasicTableFields",list);
        return rows;
    }
    public  int delete(String tableName,String fieldName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,tableName,fieldName);
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
    public  int delete(SqlSession sqlSession,String tableName,String fieldName)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,tableName,fieldName));
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
        int rows = sqlSession.delete("SysBasicTableField.deleteSysBasicTableField",entity);
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
    public  DataEntity getDataEntity(String tableName,String fieldName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,tableName,fieldName);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,String tableName,String fieldName)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,tableName,fieldName);
         return newDataEntity;
    }
    public  SysBasicTableField getEntity(String tableName,String fieldName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,tableName,fieldName);
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
    public  SysBasicTableField getEntity(SqlSession sqlSession,String tableName,String fieldName)    
    {
        SysBasicTableField entity = createEntity();
        entity.setTableName(tableName);
        entity.setFieldName(fieldName);
        SysBasicTableField newEntity = sqlSession.selectOne("SysBasicTableField.selectSysBasicTableField",entity);
        return newEntity;
    }
    public  List<SysBasicTableField> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession)    
    {
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFields");
        return list;
    }
    public  List<SysBasicTableField> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession,String condition)    
    {
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldByCondition",condition);
        return list;
    }
    public  List<SysBasicTableField> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysBasicTableField> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_basic_table_field limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldBySql", sql);
        return list;
    }
    public  List<SysBasicTableField> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldByCondition",sql);
        return list;
    }
    public  List<SysBasicTableField> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldByCondition",sql);
        return list;
    }
    public  List<SysBasicTableField> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
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
    public  List<SysBasicTableField> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysBasicTableField> list = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysBasicTableField.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysBasicTableField entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysBasicTableField entity)throws IOException    
    {
        int rows = sqlSession.update("SysBasicTableField.updateSysBasicTableField",entity);
        return rows;
    }
    public  SysBasicTableField createEntity()    
    {
        return new SysBasicTableField();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysBasicTableField();
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
        List<SysBasicTableField> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByTableName(String tableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByTableName(sqlSession,tableName);
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
    public  int deleteBySysFieldDataTypeCode(String sysFieldDataTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySysFieldDataTypeCode(sqlSession,sysFieldDataTypeCode);
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
    public  int deleteBySysUiControlCode(String sysUiControlCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySysUiControlCode(sqlSession,sysUiControlCode);
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
    public  int deleteBySysValidationDataTypeCode(String sysValidationDataTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySysValidationDataTypeCode(sqlSession,sysValidationDataTypeCode);
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
    public  int deleteByTableName(SqlSession sqlSession,String tableName)    
    {
        List<SysBasicTableField> list = getListByTableName(sqlSession,tableName);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySysFieldDataTypeCode(SqlSession sqlSession,String sysFieldDataTypeCode)    
    {
        List<SysBasicTableField> list = getListBySysFieldDataTypeCode(sqlSession,sysFieldDataTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySysUiControlCode(SqlSession sqlSession,String sysUiControlCode)    
    {
        List<SysBasicTableField> list = getListBySysUiControlCode(sqlSession,sysUiControlCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySysValidationDataTypeCode(SqlSession sqlSession,String sysValidationDataTypeCode)    
    {
        List<SysBasicTableField> list = getListBySysValidationDataTypeCode(sqlSession,sysValidationDataTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SysBasicTableField> getListByTableName(String tableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> entitys = null;
         try        
        {
            entitys = getListByTableName(sqlSession,tableName);
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
    public  List<SysBasicTableField> getListBySysFieldDataTypeCode(String sysFieldDataTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> entitys = null;
         try        
        {
            entitys = getListBySysFieldDataTypeCode(sqlSession,sysFieldDataTypeCode);
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
    public  List<SysBasicTableField> getListBySysUiControlCode(String sysUiControlCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> entitys = null;
         try        
        {
            entitys = getListBySysUiControlCode(sqlSession,sysUiControlCode);
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
    public  List<SysBasicTableField> getListBySysValidationDataTypeCode(String sysValidationDataTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> entitys = null;
         try        
        {
            entitys = getListBySysValidationDataTypeCode(sqlSession,sysValidationDataTypeCode);
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
    public  List<SysBasicTableField> getListByTableName(SqlSession sqlSession,String tableName)    
    {
        List<SysBasicTableField> entitys = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldByTableName",tableName);
        return entitys;
    }
    public  List<SysBasicTableField> getListBySysFieldDataTypeCode(SqlSession sqlSession,String sysFieldDataTypeCode)    
    {
        List<SysBasicTableField> entitys = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldBySysFieldDataTypeCode",sysFieldDataTypeCode);
        return entitys;
    }
    public  List<SysBasicTableField> getListBySysUiControlCode(SqlSession sqlSession,String sysUiControlCode)    
    {
        List<SysBasicTableField> entitys = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldBySysUiControlCode",sysUiControlCode);
        return entitys;
    }
    public  List<SysBasicTableField> getListBySysValidationDataTypeCode(SqlSession sqlSession,String sysValidationDataTypeCode)    
    {
        List<SysBasicTableField> entitys = sqlSession.selectList("SysBasicTableField.selectSysBasicTableFieldBySysValidationDataTypeCode",sysValidationDataTypeCode);
        return entitys;
    }
    public  SysBasicTableField getEntityByTableName(String tableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField entity = null;
        try        
        {
             entity = getEntityByTableName(sqlSession,tableName);
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
    public  SysBasicTableField getEntityByFieldName(String fieldName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField entity = null;
        try        
        {
             entity = getEntityByFieldName(sqlSession,fieldName);
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
    public  SysBasicTableField getEntityByTableName(SqlSession sqlSession,String tableName)    
    {
        SysBasicTableField entity = sqlSession.selectOne("selectSysBasicTableFieldByCondition","table_name='"+tableName+"'");
        return entity;
    }
    public  SysBasicTableField getEntityByFieldName(SqlSession sqlSession,String fieldName)    
    {
        SysBasicTableField entity = sqlSession.selectOne("selectSysBasicTableFieldByCondition","field_name='"+fieldName+"'");
        return entity;
    }
    public  boolean existsTableName(String tableName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsTableName(sqlSession,tableName);
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
    public  boolean existsFieldName(String fieldName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsFieldName(sqlSession,fieldName);
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
    public  boolean existsTableName(SqlSession sqlSession,String tableName)    
    {
        Integer rows = sqlSession.selectOne("SysBasicTableField.selectIntBySql","select count(*) from sys_basic_table_field where table_name= '"+tableName+"'");
        return rows>0?true:false;
    }
    public  boolean existsFieldName(SqlSession sqlSession,String fieldName)    
    {
        Integer rows = sqlSession.selectOne("SysBasicTableField.selectIntBySql","select count(*) from sys_basic_table_field where field_name= '"+fieldName+"'");
        return rows>0?true:false;
    }
}