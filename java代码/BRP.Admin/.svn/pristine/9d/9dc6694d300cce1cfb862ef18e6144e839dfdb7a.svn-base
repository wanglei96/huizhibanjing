package BRP.data;
import BRP.model.ActivityRecommendLogManager;
import org.apache.ibatis.session.SqlSession;
import BRP.model.ActivityServiceTimeManager;
import BRP.data.MemberCard;
import BRP.model.ActivityRecommendManager;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.model.ActivityRecommendMemberManager;
import BRP.model.MemberCardDisableLogManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class MemberCardMaster extends IdDataManager 
{
    public  int add(MemberCard entity)throws Exception    
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
    public  int add(SqlSession sqlSession,MemberCard entity)    
    {
        int rows = sqlSession.insert("MemberCard.insertMemberCard",entity);
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
        return add(sqlSession,(MemberCard)dataEntity);
    }
    public  int addList(List<MemberCard> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<MemberCard> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("MemberCard.insertMemberCards",list);
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
        ActivityRecommendManager.getInstance().deleteByNewMemberCardId(sqlSession,((MemberCard)entity).getId());
        ActivityRecommendManager.getInstance().deleteByReferrerCardId(sqlSession,((MemberCard)entity).getId());
        ActivityRecommendLogManager.getInstance().deleteByMemberCardId(sqlSession,((MemberCard)entity).getId());
        ActivityRecommendMemberManager.getInstance().deleteByNewMemberCardId(sqlSession,((MemberCard)entity).getId());
        ActivityRecommendMemberManager.getInstance().deleteByReferrerCardId(sqlSession,((MemberCard)entity).getId());
        ActivityServiceTimeManager.getInstance().deleteByMemberCardId(sqlSession,((MemberCard)entity).getId());
        MemberCardDisableLogManager.getInstance().deleteByMemberCardId(sqlSession,((MemberCard)entity).getId());
        int rows = sqlSession.delete("MemberCard.deleteMemberCard",entity);
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
    public  MemberCard getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCard newEntity = null;
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
    public  MemberCard getEntity(SqlSession sqlSession,Integer id)    
    {
        MemberCard entity = createEntity();
        entity.setId(id);
        MemberCard newEntity = sqlSession.selectOne("MemberCard.selectMemberCard",entity);
        return newEntity;
    }
    public  List<MemberCard> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession)    
    {
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCards");
        return list;
    }
    public  List<MemberCard> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession,String condition)    
    {
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardByCondition",condition);
        return list;
    }
    public  List<MemberCard> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<MemberCard> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from member_card limit %s, %s", pageSize * pageIndex, pageSize);
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardBySql", sql);
        return list;
    }
    public  List<MemberCard> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardByCondition",sql);
        return list;
    }
    public  List<MemberCard> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardByCondition",sql);
        return list;
    }
    public  List<MemberCard> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> list = null;
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
    public  List<MemberCard> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<MemberCard> list = sqlSession.selectList("MemberCard.selectMemberCardBySql",sql);
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
        Integer rows = sqlSession.selectOne("MemberCard.selectCountByCondition",condition);
        return rows;
    }
    public  int update(MemberCard entity)throws Exception    
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
    public  int update(SqlSession sqlSession,MemberCard entity)throws IOException    
    {
        int rows = sqlSession.update("MemberCard.updateMemberCard",entity);
        return rows;
    }
    public  MemberCard createEntity()    
    {
        return new MemberCard();
    }
    public  DataEntity createDataEntity()    
    {
        return new MemberCard();
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
        List<MemberCard> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCompanyId(sqlSession,companyId);
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
    public  int deleteByEmployeeId(Integer employeeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByEmployeeId(sqlSession,employeeId);
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
    public  int deleteByMemberCardBatchId(Integer memberCardBatchId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardBatchId(sqlSession,memberCardBatchId);
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
    public  int deleteByMemberCardStatusCode(String memberCardStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardStatusCode(sqlSession,memberCardStatusCode);
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
    public  int deleteByMemberCardTypeId(Integer memberCardTypeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberCardTypeId(sqlSession,memberCardTypeId);
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
    public  int deleteByReferrerId(Integer referrerId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByReferrerId(sqlSession,referrerId);
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
    public  int deleteByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<MemberCard> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByEmployeeId(SqlSession sqlSession,Integer employeeId)    
    {
        List<MemberCard> list = getListByEmployeeId(sqlSession,employeeId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCard> list = getListByMemberId(sqlSession,memberId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardBatchId(SqlSession sqlSession,Integer memberCardBatchId)    
    {
        List<MemberCard> list = getListByMemberCardBatchId(sqlSession,memberCardBatchId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardStatusCode(SqlSession sqlSession,String memberCardStatusCode)    
    {
        List<MemberCard> list = getListByMemberCardStatusCode(sqlSession,memberCardStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberCardTypeId(SqlSession sqlSession,Integer memberCardTypeId)    
    {
        List<MemberCard> list = getListByMemberCardTypeId(sqlSession,memberCardTypeId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<MemberCard> list = getListByPaymentMethodCode(sqlSession,paymentMethodCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByReferrerId(SqlSession sqlSession,Integer referrerId)    
    {
        List<MemberCard> list = getListByReferrerId(sqlSession,referrerId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<MemberCard> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByCompanyId(sqlSession,companyId);
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
    public  List<MemberCard> getListByEmployeeId(Integer employeeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByEmployeeId(sqlSession,employeeId);
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
    public  List<MemberCard> getListByMemberId(Integer memberId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
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
    public  List<MemberCard> getListByMemberCardBatchId(Integer memberCardBatchId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByMemberCardBatchId(sqlSession,memberCardBatchId);
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
    public  List<MemberCard> getListByMemberCardStatusCode(String memberCardStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByMemberCardStatusCode(sqlSession,memberCardStatusCode);
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
    public  List<MemberCard> getListByMemberCardTypeId(Integer memberCardTypeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByMemberCardTypeId(sqlSession,memberCardTypeId);
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
    public  List<MemberCard> getListByPaymentMethodCode(String paymentMethodCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
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
    public  List<MemberCard> getListByReferrerId(Integer referrerId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberCard> entitys = null;
         try        
        {
            entitys = getListByReferrerId(sqlSession,referrerId);
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
    public  List<MemberCard> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByCompanyId",companyId);
        return entitys;
    }
    public  List<MemberCard> getListByEmployeeId(SqlSession sqlSession,Integer employeeId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByEmployeeId",employeeId);
        return entitys;
    }
    public  List<MemberCard> getListByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByMemberId",memberId);
        return entitys;
    }
    public  List<MemberCard> getListByMemberCardBatchId(SqlSession sqlSession,Integer memberCardBatchId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByMemberCardBatchId",memberCardBatchId);
        return entitys;
    }
    public  List<MemberCard> getListByMemberCardStatusCode(SqlSession sqlSession,String memberCardStatusCode)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByMemberCardStatusCode",memberCardStatusCode);
        return entitys;
    }
    public  List<MemberCard> getListByMemberCardTypeId(SqlSession sqlSession,Integer memberCardTypeId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByMemberCardTypeId",memberCardTypeId);
        return entitys;
    }
    public  List<MemberCard> getListByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByPaymentMethodCode",paymentMethodCode);
        return entitys;
    }
    public  List<MemberCard> getListByReferrerId(SqlSession sqlSession,Integer referrerId)    
    {
        List<MemberCard> entitys = sqlSession.selectList("MemberCard.selectMemberCardByReferrerId",referrerId);
        return entitys;
    }
    public  MemberCard getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        MemberCard entity = null;
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
    public  MemberCard getEntityById(SqlSession sqlSession,Integer id)    
    {
        MemberCard entity = sqlSession.selectOne("selectMemberCardByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("MemberCard.selectIntBySql","select count(*) from member_card where id= '"+id+"'");
        return rows>0?true:false;
    }
}