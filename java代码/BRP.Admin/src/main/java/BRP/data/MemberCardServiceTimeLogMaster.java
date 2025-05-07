package BRP.data;
import BRP.data.MemberCardServiceTimeLog;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class MemberCardServiceTimeLogMaster extends IdDataManager 
{
    public  int add(MemberCardServiceTimeLog entity)throws Exception    
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
    public  int add(SqlSession sqlSession,MemberCardServiceTimeLog entity)    
    {
        int rows = sqlSession.insert("MemberCardServiceTimeLog.insertMemberCardServiceTimeLog",entity);
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
        return add(sqlSession,(MemberCardServiceTimeLog)dataEntity);
    }
    public  int addList(List<MemberCardServiceTimeLog> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<MemberCardServiceTimeLog> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("MemberCardServiceTimeLog.insertMemberCardServiceTimeLogs",list);
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
        int rows = sqlSession.delete("MemberCardServiceTimeLog.deleteMemberCardServiceTimeLog",entity);
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
    public  MemberCardServiceTimeLog getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCardServiceTimeLog newEntity = null;
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
    public  MemberCardServiceTimeLog getEntity(SqlSession sqlSession,Integer id)    
    {
        MemberCardServiceTimeLog entity = createEntity();
        entity.setId(id);
        MemberCardServiceTimeLog newEntity = sqlSession.selectOne("MemberCardServiceTimeLog.selectMemberCardServiceTimeLog",entity);
        return newEntity;
    }
    public  List<MemberCardServiceTimeLog> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession)    
    {
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogs");
        return list;
    }
    public  List<MemberCardServiceTimeLog> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition)    
    {
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByCondition",condition);
        return list;
    }
    public  List<MemberCardServiceTimeLog> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<MemberCardServiceTimeLog> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from member_card_service_time_log limit %s, %s", pageSize * pageIndex, pageSize);
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogBySql", sql);
        return list;
    }
    public  List<MemberCardServiceTimeLog> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByCondition",sql);
        return list;
    }
    public  List<MemberCardServiceTimeLog> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByCondition",sql);
        return list;
    }
    public  List<MemberCardServiceTimeLog> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> list = null;
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
    public  List<MemberCardServiceTimeLog> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<MemberCardServiceTimeLog> list = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogBySql",sql);
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
        Integer rows = sqlSession.selectOne("MemberCardServiceTimeLog.selectCountByCondition",condition);
        return rows;
    }
    public  int update(MemberCardServiceTimeLog entity)throws Exception    
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
    public  int update(SqlSession sqlSession,MemberCardServiceTimeLog entity)throws IOException    
    {
        int rows = sqlSession.update("MemberCardServiceTimeLog.updateMemberCardServiceTimeLog",entity);
        return rows;
    }
    public  MemberCardServiceTimeLog createEntity()    
    {
        return new MemberCardServiceTimeLog();
    }
    public  DataEntity createDataEntity()    
    {
        return new MemberCardServiceTimeLog();
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
        List<MemberCardServiceTimeLog> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByActivityId(Integer activityId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByActivityId(sqlSession,activityId);
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
    public  int deleteByMemberCardServiceTimeGiftTypeCode(String memberCardServiceTimeGiftTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardServiceTimeGiftTypeCode(sqlSession,memberCardServiceTimeGiftTypeCode);
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
    public  int deleteByPaymentMethodCode(String paymentMethodCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByPaymentMethodCode(sqlSession,paymentMethodCode);
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
    public  int deleteByMemberCardServiceTimeTypeCode(String memberCardServiceTimeTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardServiceTimeTypeCode(sqlSession,memberCardServiceTimeTypeCode);
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
    public  int deleteByActivityId(SqlSession sqlSession,Integer activityId)    
    {
        List<MemberCardServiceTimeLog> list = getListByActivityId(sqlSession,activityId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardServiceTimeGiftTypeCode(SqlSession sqlSession,String memberCardServiceTimeGiftTypeCode)    
    {
        List<MemberCardServiceTimeLog> list = getListByMemberCardServiceTimeGiftTypeCode(sqlSession,memberCardServiceTimeGiftTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCardServiceTimeLog> list = getListByMemberId(sqlSession,memberId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<MemberCardServiceTimeLog> list = getListByPaymentMethodCode(sqlSession,paymentMethodCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardServiceTimeTypeCode(SqlSession sqlSession,String memberCardServiceTimeTypeCode)    
    {
        List<MemberCardServiceTimeLog> list = getListByMemberCardServiceTimeTypeCode(sqlSession,memberCardServiceTimeTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<MemberCardServiceTimeLog> getListByActivityId(Integer activityId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> entitys = null;
         try        
        {
            entitys = getListByActivityId(sqlSession,activityId);
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
    public  List<MemberCardServiceTimeLog> getListByMemberCardServiceTimeGiftTypeCode(String memberCardServiceTimeGiftTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> entitys = null;
         try        
        {
            entitys = getListByMemberCardServiceTimeGiftTypeCode(sqlSession,memberCardServiceTimeGiftTypeCode);
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
    public  List<MemberCardServiceTimeLog> getListByMemberId(Integer memberId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> entitys = null;
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
    public  List<MemberCardServiceTimeLog> getListByPaymentMethodCode(String paymentMethodCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> entitys = null;
         try        
        {
            entitys = getListByPaymentMethodCode(sqlSession,paymentMethodCode);
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
    public  List<MemberCardServiceTimeLog> getListByMemberCardServiceTimeTypeCode(String memberCardServiceTimeTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCardServiceTimeLog> entitys = null;
         try        
        {
            entitys = getListByMemberCardServiceTimeTypeCode(sqlSession,memberCardServiceTimeTypeCode);
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
    public  List<MemberCardServiceTimeLog> getListByActivityId(SqlSession sqlSession,Integer activityId)    
    {
        List<MemberCardServiceTimeLog> entitys = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByActivityId",activityId);
        return entitys;
    }
    public  List<MemberCardServiceTimeLog> getListByMemberCardServiceTimeGiftTypeCode(SqlSession sqlSession,String memberCardServiceTimeGiftTypeCode)    
    {
        List<MemberCardServiceTimeLog> entitys = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByMemberCardServiceTimeGiftTypeCode",memberCardServiceTimeGiftTypeCode);
        return entitys;
    }
    public  List<MemberCardServiceTimeLog> getListByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCardServiceTimeLog> entitys = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByMemberId",memberId);
        return entitys;
    }
    public  List<MemberCardServiceTimeLog> getListByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<MemberCardServiceTimeLog> entitys = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByPaymentMethodCode",paymentMethodCode);
        return entitys;
    }
    public  List<MemberCardServiceTimeLog> getListByMemberCardServiceTimeTypeCode(SqlSession sqlSession,String memberCardServiceTimeTypeCode)    
    {
        List<MemberCardServiceTimeLog> entitys = sqlSession.selectList("MemberCardServiceTimeLog.selectMemberCardServiceTimeLogByMemberCardServiceTimeTypeCode",memberCardServiceTimeTypeCode);
        return entitys;
    }
    public  MemberCardServiceTimeLog getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCardServiceTimeLog entity = null;
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
    public  MemberCardServiceTimeLog getEntityById(SqlSession sqlSession,Integer id)    
    {
        MemberCardServiceTimeLog entity = sqlSession.selectOne("selectMemberCardServiceTimeLogByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("MemberCardServiceTimeLog.selectIntBySql","select count(*) from member_card_service_time_log where id= '"+id+"'");
        return rows>0?true:false;
    }
}