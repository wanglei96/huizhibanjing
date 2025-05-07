package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import BRP.data.MemberCardDisableLog;
import java.math.*;
public class MemberCardDisableLogMaster extends IdDataManager 
{
    public  int add(MemberCardDisableLog entity)throws Exception    
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
    public  int add(SqlSession sqlSession,MemberCardDisableLog entity)    
    {
        int rows = sqlSession.insert("MemberCardDisableLog.insertMemberCardDisableLog",entity);
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
        return add(sqlSession,(MemberCardDisableLog)dataEntity);
    }
    public  int addList(List<MemberCardDisableLog> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<MemberCardDisableLog> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("MemberCardDisableLog.insertMemberCardDisableLogs",list);
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
        int rows = sqlSession.delete("MemberCardDisableLog.deleteMemberCardDisableLog",entity);
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
    public  MemberCardDisableLog getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCardDisableLog newEntity = null;
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
    public  MemberCardDisableLog getEntity(SqlSession sqlSession,Integer id)    
    {
        MemberCardDisableLog entity = createEntity();
        entity.setId(id);
        MemberCardDisableLog newEntity = sqlSession.selectOne("MemberCardDisableLog.selectMemberCardDisableLog",entity);
        return newEntity;
    }
    public  List<MemberCardDisableLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession)    
    {
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogs");
        return list;
    }
    public  List<MemberCardDisableLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession,String condition)    
    {
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByCondition",condition);
        return list;
    }
    public  List<MemberCardDisableLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<MemberCardDisableLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from member_card_disable_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogBySql", sql);
        return list;
    }
    public  List<MemberCardDisableLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByCondition",sql);
        return list;
    }
    public  List<MemberCardDisableLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByCondition",sql);
        return list;
    }
    public  List<MemberCardDisableLog> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> list = null;
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
    public  List<MemberCardDisableLog> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<MemberCardDisableLog> list = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogBySql",sql);
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
        Integer rows = sqlSession.selectOne("MemberCardDisableLog.selectCountByCondition",condition);
        return rows;
    }
    public  int update(MemberCardDisableLog entity)throws Exception    
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
    public  int update(SqlSession sqlSession,MemberCardDisableLog entity)throws IOException    
    {
        int rows = sqlSession.update("MemberCardDisableLog.updateMemberCardDisableLog",entity);
        return rows;
    }
    public  MemberCardDisableLog createEntity()    
    {
        return new MemberCardDisableLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new MemberCardDisableLog();
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
        List<MemberCardDisableLog> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByMemberId(Integer memberId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberId(sqlSession,memberId);
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
    public  int deleteByMemberCardId(Integer memberCardId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardId(sqlSession,memberCardId);
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
    public  int deleteBySource(String source)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySource(sqlSession,source);
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
    public  int deleteByStatus(String status)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByStatus(sqlSession,status);
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
    public  int deleteByType(String type)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByType(sqlSession,type);
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
    public  int deleteByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCardDisableLog> list = getListByMemberId(sqlSession,memberId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardId(SqlSession sqlSession,Integer memberCardId)    
    {
        List<MemberCardDisableLog> list = getListByMemberCardId(sqlSession,memberCardId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySource(SqlSession sqlSession,String source)    
    {
        List<MemberCardDisableLog> list = getListBySource(sqlSession,source);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByStatus(SqlSession sqlSession,String status)    
    {
        List<MemberCardDisableLog> list = getListByStatus(sqlSession,status);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByType(SqlSession sqlSession,String type)    
    {
        List<MemberCardDisableLog> list = getListByType(sqlSession,type);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<MemberCardDisableLog> getListByMemberId(Integer memberId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> entitys = null;
         try        
        {
            entitys = getListByMemberId(sqlSession,memberId);
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
    public  List<MemberCardDisableLog> getListByMemberCardId(Integer memberCardId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> entitys = null;
         try        
        {
            entitys = getListByMemberCardId(sqlSession,memberCardId);
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
    public  List<MemberCardDisableLog> getListBySource(String source)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> entitys = null;
         try        
        {
            entitys = getListBySource(sqlSession,source);
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
    public  List<MemberCardDisableLog> getListByStatus(String status)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> entitys = null;
         try        
        {
            entitys = getListByStatus(sqlSession,status);
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
    public  List<MemberCardDisableLog> getListByType(String type)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardDisableLog> entitys = null;
         try        
        {
            entitys = getListByType(sqlSession,type);
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
    public  List<MemberCardDisableLog> getListByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCardDisableLog> entitys = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByMemberId",memberId);
        return entitys;
    }
    public  List<MemberCardDisableLog> getListByMemberCardId(SqlSession sqlSession,Integer memberCardId)    
    {
        List<MemberCardDisableLog> entitys = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByMemberCardId",memberCardId);
        return entitys;
    }
    public  List<MemberCardDisableLog> getListBySource(SqlSession sqlSession,String source)    
    {
        List<MemberCardDisableLog> entitys = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogBySource",source);
        return entitys;
    }
    public  List<MemberCardDisableLog> getListByStatus(SqlSession sqlSession,String status)    
    {
        List<MemberCardDisableLog> entitys = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByStatus",status);
        return entitys;
    }
    public  List<MemberCardDisableLog> getListByType(SqlSession sqlSession,String type)    
    {
        List<MemberCardDisableLog> entitys = sqlSession.selectList("MemberCardDisableLog.selectMemberCardDisableLogByType",type);
        return entitys;
    }
    public  MemberCardDisableLog getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCardDisableLog entity = null;
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
    public  MemberCardDisableLog getEntityById(SqlSession sqlSession,Integer id)    
    {
        MemberCardDisableLog entity = sqlSession.selectOne("selectMemberCardDisableLogByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("MemberCardDisableLog.selectIntBySql","select count(*) from member_card_disable_log where id= '"+id+"'");
        return rows>0?true:false;
    }
}