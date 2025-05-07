package strosoft.sys.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.sys.data.SysBasicTableFieldType;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysBasicTableFieldTypeMaster extends IdDataManager 
{
    public  int add(SysBasicTableFieldType entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysBasicTableFieldType entity)    
    {
        int rows = sqlSession.insert("SysBasicTableFieldType.insertSysBasicTableFieldType",entity);
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
        return add(sqlSession,(SysBasicTableFieldType)dataEntity);
    }
    public  int addList(List<SysBasicTableFieldType> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysBasicTableFieldType> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysBasicTableFieldType.insertSysBasicTableFieldTypes",list);
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
        int rows = sqlSession.delete("SysBasicTableFieldType.deleteSysBasicTableFieldType",entity);
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
    public  SysBasicTableFieldType getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableFieldType newEntity = null;
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
    public  SysBasicTableFieldType getEntity(SqlSession sqlSession,Integer id)    
    {
        SysBasicTableFieldType entity = createEntity();
        entity.setId(id);
        SysBasicTableFieldType newEntity = sqlSession.selectOne("SysBasicTableFieldType.selectSysBasicTableFieldType",entity);
        return newEntity;
    }
    public  List<SysBasicTableFieldType> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession)    
    {
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypes");
        return list;
    }
    public  List<SysBasicTableFieldType> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession,String condition)    
    {
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeByCondition",condition);
        return list;
    }
    public  List<SysBasicTableFieldType> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysBasicTableFieldType> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_basic_table_field_type limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeBySql", sql);
        return list;
    }
    public  List<SysBasicTableFieldType> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeByCondition",sql);
        return list;
    }
    public  List<SysBasicTableFieldType> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeByCondition",sql);
        return list;
    }
    public  List<SysBasicTableFieldType> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableFieldType> list = null;
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
    public  List<SysBasicTableFieldType> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysBasicTableFieldType> list = sqlSession.selectList("SysBasicTableFieldType.selectSysBasicTableFieldTypeBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysBasicTableFieldType.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysBasicTableFieldType entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysBasicTableFieldType entity)throws IOException    
    {
        int rows = sqlSession.update("SysBasicTableFieldType.updateSysBasicTableFieldType",entity);
        return rows;
    }
    public  SysBasicTableFieldType createEntity()    
    {
        return new SysBasicTableFieldType();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysBasicTableFieldType();
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
        List<SysBasicTableFieldType> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  SysBasicTableFieldType getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableFieldType entity = null;
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
    public  SysBasicTableFieldType getEntityById(SqlSession sqlSession,Integer id)    
    {
        SysBasicTableFieldType entity = sqlSession.selectOne("selectSysBasicTableFieldTypeByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("SysBasicTableFieldType.selectIntBySql","select count(*) from sys_basic_table_field_type where id= '"+id+"'");
        return rows>0?true:false;
    }
}