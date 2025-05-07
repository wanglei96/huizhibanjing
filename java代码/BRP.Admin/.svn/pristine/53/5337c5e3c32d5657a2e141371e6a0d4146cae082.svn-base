package BRP.data;
import BRP.model.ReadActivityMemberManager;
import org.apache.ibatis.session.SqlSession;
import BRP.model.MemberCollectStoryVideoManager;
import BRP.model.MemberLikeStoryVideoManager;
import BRP.model.PayOrderManager;
import BRP.model.MemberCardServiceTimeLogManager;
import strosoft.app.common.MyBatisManager;
import BRP.model.MemberBookSubscriptionManager;
import BRP.model.MemberCardDisableLogManager;
import BRP.model.ClubActivityMemberManager;
import BRP.model.MemberCommunicaLogManager;
import BRP.data.Member;
import BRP.model.BookManager;
import BRP.model.NightStoryOrderManager;
import BRP.model.MemberBorrowQuantityLogManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import BRP.model.BookDamageManager;
import BRP.model.RetailOrderManager;
import BRP.model.BookOrderManager;
import BRP.model.BookCompensationManager;
import BRP.model.MemberBookmarkManager;
import java.util.*;
import BRP.model.MemberScoreManager;
import BRP.model.RefundOrderManager;
import BRP.model.MemberBrowsedStoryVideoManager;
import BRP.model.MemberCardManager;
import java.math.*;
public class MemberMaster extends IdDataManager 
{
    public  int add(Member entity)throws Exception    
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
    public  int add(SqlSession sqlSession,Member entity)    
    {
        int rows = sqlSession.insert("Member.insertMember",entity);
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
        return add(sqlSession,(Member)dataEntity);
    }
    public  int addList(List<Member> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<Member> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("Member.insertMembers",list);
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
        BookManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        BookCompensationManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        BookDamageManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        BookOrderManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        ClubActivityMemberManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberBookSubscriptionManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberBookmarkManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberBorrowQuantityLogManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberBrowsedStoryVideoManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberCardManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberCardManager.getInstance().deleteByReferrerId(sqlSession,((Member)entity).getId());
        MemberCardDisableLogManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberCardServiceTimeLogManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberCollectStoryVideoManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberCommunicaLogManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberLikeStoryVideoManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        MemberScoreManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        NightStoryOrderManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        PayOrderManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        ReadActivityMemberManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        RefundOrderManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        RetailOrderManager.getInstance().deleteByMemberId(sqlSession,((Member)entity).getId());
        int rows = sqlSession.delete("Member.deleteMember",entity);
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
    public  Member getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Member newEntity = null;
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
    public  Member getEntity(SqlSession sqlSession,Integer id)    
    {
        Member entity = createEntity();
        entity.setId(id);
        Member newEntity = sqlSession.selectOne("Member.selectMember",entity);
        return newEntity;
    }
    public  List<Member> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession)    
    {
        List<Member> list = sqlSession.selectList("Member.selectMembers");
        return list;
    }
    public  List<Member> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession,String condition)    
    {
        List<Member> list = sqlSession.selectList("Member.selectMemberByCondition",condition);
        return list;
    }
    public  List<Member> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<Member> list = sqlSession.selectList("Member.selectMemberByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<Member> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from member limit %s, %s", pageSize * pageIndex, pageSize);
        List<Member> list = sqlSession.selectList("Member.selectMemberBySql", sql);
        return list;
    }
    public  List<Member> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<Member> list = sqlSession.selectList("Member.selectMemberByCondition",sql);
        return list;
    }
    public  List<Member> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<Member> list = sqlSession.selectList("Member.selectMemberByCondition",sql);
        return list;
    }
    public  List<Member> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> list = null;
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
    public  List<Member> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<Member> list = sqlSession.selectList("Member.selectMemberBySql",sql);
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
        Integer rows = sqlSession.selectOne("Member.selectCountByCondition",condition);
        return rows;
    }
    public  int update(Member entity)throws Exception    
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
    public  int update(SqlSession sqlSession,Member entity)throws IOException    
    {
        int rows = sqlSession.update("Member.updateMember",entity);
        return rows;
    }
    public  Member createEntity()    
    {
        return new Member();
    }
    public  DataEntity createDataEntity()    
    {
        return new Member();
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
        List<Member> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByClassId(Integer classId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByClassId(sqlSession,classId);
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
    public  int deleteByDelivererId(Integer delivererId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDelivererId(sqlSession,delivererId);
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
    public  int deleteByDeliverAreaId(Integer deliverAreaId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDeliverAreaId(sqlSession,deliverAreaId);
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
    public  int deleteByDeliverLineId(Integer deliverLineId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDeliverLineId(sqlSession,deliverLineId);
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
    public  int deleteByDeliverPlaceId(Integer deliverPlaceId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDeliverPlaceId(sqlSession,deliverPlaceId);
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
    public  int deleteByDistanceCode(String distanceCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDistanceCode(sqlSession,distanceCode);
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
    public  int deleteByGradeId(Integer gradeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByGradeId(sqlSession,gradeId);
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
    public  int deleteByMemberBookboxStatusCode(String memberBookboxStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByMemberBookboxStatusCode(sqlSession,memberBookboxStatusCode);
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
    public  int deleteByClassId(SqlSession sqlSession,Integer classId)    
    {
        List<Member> list = getListByClassId(sqlSession,classId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<Member> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDelivererId(SqlSession sqlSession,Integer delivererId)    
    {
        List<Member> list = getListByDelivererId(sqlSession,delivererId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDeliverAreaId(SqlSession sqlSession,Integer deliverAreaId)    
    {
        List<Member> list = getListByDeliverAreaId(sqlSession,deliverAreaId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDeliverLineId(SqlSession sqlSession,Integer deliverLineId)    
    {
        List<Member> list = getListByDeliverLineId(sqlSession,deliverLineId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDeliverPlaceId(SqlSession sqlSession,Integer deliverPlaceId)    
    {
        List<Member> list = getListByDeliverPlaceId(sqlSession,deliverPlaceId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDistanceCode(SqlSession sqlSession,String distanceCode)    
    {
        List<Member> list = getListByDistanceCode(sqlSession,distanceCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByGradeId(SqlSession sqlSession,Integer gradeId)    
    {
        List<Member> list = getListByGradeId(sqlSession,gradeId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberBookboxStatusCode(SqlSession sqlSession,String memberBookboxStatusCode)    
    {
        List<Member> list = getListByMemberBookboxStatusCode(sqlSession,memberBookboxStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<Member> list = getListByPaymentMethodCode(sqlSession,paymentMethodCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<Member> getListByClassId(Integer classId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByClassId(sqlSession,classId);
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
    public  List<Member> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
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
    public  List<Member> getListByDelivererId(Integer delivererId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByDelivererId(sqlSession,delivererId);
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
    public  List<Member> getListByDeliverAreaId(Integer deliverAreaId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByDeliverAreaId(sqlSession,deliverAreaId);
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
    public  List<Member> getListByDeliverLineId(Integer deliverLineId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByDeliverLineId(sqlSession,deliverLineId);
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
    public  List<Member> getListByDeliverPlaceId(Integer deliverPlaceId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByDeliverPlaceId(sqlSession,deliverPlaceId);
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
    public  List<Member> getListByDistanceCode(String distanceCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByDistanceCode(sqlSession,distanceCode);
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
    public  List<Member> getListByGradeId(Integer gradeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByGradeId(sqlSession,gradeId);
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
    public  List<Member> getListByMemberBookboxStatusCode(String memberBookboxStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
         try        
        {
            entitys = getListByMemberBookboxStatusCode(sqlSession,memberBookboxStatusCode);
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
    public  List<Member> getListByPaymentMethodCode(String paymentMethodCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Member> entitys = null;
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
    public  List<Member> getListByClassId(SqlSession sqlSession,Integer classId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByClassId",classId);
        return entitys;
    }
    public  List<Member> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByCompanyId",companyId);
        return entitys;
    }
    public  List<Member> getListByDelivererId(SqlSession sqlSession,Integer delivererId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByDelivererId",delivererId);
        return entitys;
    }
    public  List<Member> getListByDeliverAreaId(SqlSession sqlSession,Integer deliverAreaId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByDeliverAreaId",deliverAreaId);
        return entitys;
    }
    public  List<Member> getListByDeliverLineId(SqlSession sqlSession,Integer deliverLineId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByDeliverLineId",deliverLineId);
        return entitys;
    }
    public  List<Member> getListByDeliverPlaceId(SqlSession sqlSession,Integer deliverPlaceId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByDeliverPlaceId",deliverPlaceId);
        return entitys;
    }
    public  List<Member> getListByDistanceCode(SqlSession sqlSession,String distanceCode)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByDistanceCode",distanceCode);
        return entitys;
    }
    public  List<Member> getListByGradeId(SqlSession sqlSession,Integer gradeId)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByGradeId",gradeId);
        return entitys;
    }
    public  List<Member> getListByMemberBookboxStatusCode(SqlSession sqlSession,String memberBookboxStatusCode)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByMemberBookboxStatusCode",memberBookboxStatusCode);
        return entitys;
    }
    public  List<Member> getListByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<Member> entitys = sqlSession.selectList("Member.selectMemberByPaymentMethodCode",paymentMethodCode);
        return entitys;
    }
    public  Member getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Member entity = null;
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
    public  Member getEntityByWxOpenId(String wxOpenId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Member entity = null;
        try        
        {
             entity = getEntityByWxOpenId(sqlSession,wxOpenId);
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
    public  Member getEntityByWxaOpenId(String wxaOpenId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Member entity = null;
        try        
        {
             entity = getEntityByWxaOpenId(sqlSession,wxaOpenId);
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
    public  Member getEntityByMobilePhone(String mobilePhone)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Member entity = null;
        try        
        {
             entity = getEntityByMobilePhone(sqlSession,mobilePhone);
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
    public  Member getEntityById(SqlSession sqlSession,Integer id)    
    {
        Member entity = sqlSession.selectOne("selectMemberByCondition","id='"+id+"'");
        return entity;
    }
    public  Member getEntityByWxOpenId(SqlSession sqlSession,String wxOpenId)    
    {
        Member entity = sqlSession.selectOne("selectMemberByCondition","wx_open_id='"+wxOpenId+"'");
        return entity;
    }
    public  Member getEntityByWxaOpenId(SqlSession sqlSession,String wxaOpenId)    
    {
        Member entity = sqlSession.selectOne("selectMemberByCondition","wxa_open_id='"+wxaOpenId+"'");
        return entity;
    }
    public  Member getEntityByMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        Member entity = sqlSession.selectOne("selectMemberByCondition","mobile_phone='"+mobilePhone+"'");
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
    public  boolean existsWxOpenId(String wxOpenId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsWxOpenId(sqlSession,wxOpenId);
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
    public  boolean existsWxaOpenId(String wxaOpenId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsWxaOpenId(sqlSession,wxaOpenId);
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
    public  boolean existsMobilePhone(String mobilePhone)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsMobilePhone(sqlSession,mobilePhone);
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
        Integer rows = sqlSession.selectOne("Member.selectIntBySql","select count(*) from member where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsWxOpenId(SqlSession sqlSession,String wxOpenId)    
    {
        Integer rows = sqlSession.selectOne("Member.selectIntBySql","select count(*) from member where wx_open_id= '"+wxOpenId+"'");
        return rows>0?true:false;
    }
    public  boolean existsWxaOpenId(SqlSession sqlSession,String wxaOpenId)    
    {
        Integer rows = sqlSession.selectOne("Member.selectIntBySql","select count(*) from member where wxa_open_id= '"+wxaOpenId+"'");
        return rows>0?true:false;
    }
    public  boolean existsMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        Integer rows = sqlSession.selectOne("Member.selectIntBySql","select count(*) from member where mobile_phone= '"+mobilePhone+"'");
        return rows>0?true:false;
    }
}