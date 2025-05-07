package strosoft.sys.data;
import strosoft.sys.model.SysBasicTableFieldManager;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.sys.data.SysValidationDataType;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import strosoft.app.common.CodeDataManager;
import java.math.*;
public class SysValidationDataTypeMaster extends CodeDataManager 
{
    public  int add(SysValidationDataType entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysValidationDataType entity)    
    {
        int rows = sqlSession.insert("SysValidationDataType.insertSysValidationDataType",entity);
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
        return add(sqlSession,(SysValidationDataType)dataEntity);
    }
    public  int addList(List<SysValidationDataType> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysValidationDataType> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysValidationDataType.insertSysValidationDataTypes",list);
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
        SysBasicTableFieldManager.getInstance().deleteBySysValidationDataTypeCode(sqlSession,((SysValidationDataType)entity).getCode());
        int rows = sqlSession.delete("SysValidationDataType.deleteSysValidationDataType",entity);
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
    public  SysValidationDataType getEntity(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysValidationDataType newEntity = null;
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
    public  SysValidationDataType getEntity(SqlSession sqlSession,String code)    
    {
        SysValidationDataType entity = createEntity();
        entity.setCode(code);
        SysValidationDataType newEntity = sqlSession.selectOne("SysValidationDataType.selectSysValidationDataType",entity);
        return newEntity;
    }
    public  List<SysValidationDataType> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession)    
    {
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypes");
        return list;
    }
    public  List<SysValidationDataType> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession,String condition)    
    {
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeByCondition",condition);
        return list;
    }
    public  List<SysValidationDataType> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysValidationDataType> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_validation_data_type limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeBySql", sql);
        return list;
    }
    public  List<SysValidationDataType> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeByCondition",sql);
        return list;
    }
    public  List<SysValidationDataType> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeByCondition",sql);
        return list;
    }
    public  List<SysValidationDataType> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysValidationDataType> list = null;
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
    public  List<SysValidationDataType> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysValidationDataType> list = sqlSession.selectList("SysValidationDataType.selectSysValidationDataTypeBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysValidationDataType.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysValidationDataType entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysValidationDataType entity)throws IOException    
    {
        int rows = sqlSession.update("SysValidationDataType.updateSysValidationDataType",entity);
        return rows;
    }
    public  SysValidationDataType createEntity()    
    {
        return new SysValidationDataType();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysValidationDataType();
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
        List<SysValidationDataType> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  SysValidationDataType getEntityByCode(String code)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysValidationDataType entity = null;
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
    public  SysValidationDataType getEntityByCode(SqlSession sqlSession,String code)    
    {
        SysValidationDataType entity = sqlSession.selectOne("selectSysValidationDataTypeByCondition","code='"+code+"'");
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
        Integer rows = sqlSession.selectOne("SysValidationDataType.selectIntBySql","select count(*) from sys_validation_data_type where code= '"+code+"'");
        return rows>0?true:false;
    }
}