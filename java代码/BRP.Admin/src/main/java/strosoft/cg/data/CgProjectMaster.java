package strosoft.cg.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
import strosoft.cg.data.CgProject;
public class CgProjectMaster extends IdDataManager 
{
    public  int add(CgProject entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CgProject entity)    
    {
        int rows = sqlSession.insert("CgProject.insertCgProject",entity);
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
        return add(sqlSession,(CgProject)dataEntity);
    }
    public  int addList(List<CgProject> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CgProject> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CgProject.insertCgProjects",list);
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
        int rows = sqlSession.delete("CgProject.deleteCgProject",entity);
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
    public  CgProject getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CgProject newEntity = null;
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
    public  CgProject getEntity(SqlSession sqlSession,Integer id)    
    {
        CgProject entity = createEntity();
        entity.setId(id);
        CgProject newEntity = sqlSession.selectOne("CgProject.selectCgProject",entity);
        return newEntity;
    }
    public  List<CgProject> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession)    
    {
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjects");
        return list;
    }
    public  List<CgProject> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession,String condition)    
    {
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectByCondition",condition);
        return list;
    }
    public  List<CgProject> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CgProject> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cg_project limit %s, %s", pageSize * pageIndex, pageSize);
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectBySql", sql);
        return list;
    }
    public  List<CgProject> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectByCondition",sql);
        return list;
    }
    public  List<CgProject> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectByCondition",sql);
        return list;
    }
    public  List<CgProject> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CgProject> list = null;
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
    public  List<CgProject> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CgProject> list = sqlSession.selectList("CgProject.selectCgProjectBySql",sql);
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
        Integer rows = sqlSession.selectOne("CgProject.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CgProject entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CgProject entity)throws IOException    
    {
        int rows = sqlSession.update("CgProject.updateCgProject",entity);
        return rows;
    }
    public  CgProject createEntity()    
    {
        return new CgProject();
    }
    public  DataEntity createDataEntity()    
    {
        return new CgProject();
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
        List<CgProject> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CgProject getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CgProject entity = null;
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
    public  CgProject getEntityByName(String name)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CgProject entity = null;
        try        
        {
             entity = getEntityByName(sqlSession,name);
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
    public  CgProject getEntityById(SqlSession sqlSession,Integer id)    
    {
        CgProject entity = sqlSession.selectOne("selectCgProjectByCondition","id='"+id+"'");
        return entity;
    }
    public  CgProject getEntityByName(SqlSession sqlSession,String name)    
    {
        CgProject entity = sqlSession.selectOne("selectCgProjectByCondition","name='"+name+"'");
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
    public  boolean existsName(String name)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsName(sqlSession,name);
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
        Integer rows = sqlSession.selectOne("CgProject.selectIntBySql","select count(*) from cg_project where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsName(SqlSession sqlSession,String name)    
    {
        Integer rows = sqlSession.selectOne("CgProject.selectIntBySql","select count(*) from cg_project where name= '"+name+"'");
        return rows>0?true:false;
    }
}