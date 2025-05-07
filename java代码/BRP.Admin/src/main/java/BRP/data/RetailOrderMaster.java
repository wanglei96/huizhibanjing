package BRP.data;
import BRP.data.RetailOrder;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.model.ClubActivityMemberManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class RetailOrderMaster extends IdDataManager 
{
    public  int add(RetailOrder entity)throws Exception    
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
    public  int add(SqlSession sqlSession,RetailOrder entity)    
    {
        int rows = sqlSession.insert("RetailOrder.insertRetailOrder",entity);
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
        return add(sqlSession,(RetailOrder)dataEntity);
    }
    public  int addList(List<RetailOrder> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<RetailOrder> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("RetailOrder.insertRetailOrders",list);
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
        ClubActivityMemberManager.getInstance().deleteByRetailOrderId(sqlSession,((RetailOrder)entity).getId());
        int rows = sqlSession.delete("RetailOrder.deleteRetailOrder",entity);
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
    public  RetailOrder getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        RetailOrder newEntity = null;
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
    public  RetailOrder getEntity(SqlSession sqlSession,Integer id)    
    {
        RetailOrder entity = createEntity();
        entity.setId(id);
        RetailOrder newEntity = sqlSession.selectOne("RetailOrder.selectRetailOrder",entity);
        return newEntity;
    }
    public  List<RetailOrder> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession)    
    {
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrders");
        return list;
    }
    public  List<RetailOrder> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession,String condition)    
    {
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderByCondition",condition);
        return list;
    }
    public  List<RetailOrder> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<RetailOrder> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from retail_order limit %s, %s", pageSize * pageIndex, pageSize);
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderBySql", sql);
        return list;
    }
    public  List<RetailOrder> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderByCondition",sql);
        return list;
    }
    public  List<RetailOrder> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderByCondition",sql);
        return list;
    }
    public  List<RetailOrder> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> list = null;
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
    public  List<RetailOrder> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<RetailOrder> list = sqlSession.selectList("RetailOrder.selectRetailOrderBySql",sql);
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
        Integer rows = sqlSession.selectOne("RetailOrder.selectCountByCondition",condition);
        return rows;
    }
    public  int update(RetailOrder entity)throws Exception    
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
    public  int update(SqlSession sqlSession,RetailOrder entity)throws IOException    
    {
        int rows = sqlSession.update("RetailOrder.updateRetailOrder",entity);
        return rows;
    }
    public  RetailOrder createEntity()    
    {
        return new RetailOrder();
    }
    public  DataEntity createDataEntity()    
    {
        return new RetailOrder();
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
        List<RetailOrder> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByClubActivityId(Integer clubActivityId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByClubActivityId(sqlSession,clubActivityId);
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
    public  int deleteByNightStoryTypeId(Integer nightStoryTypeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByNightStoryTypeId(sqlSession,nightStoryTypeId);
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
    public  int deleteByRetailOrderStatusCode(String retailOrderStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByRetailOrderStatusCode(sqlSession,retailOrderStatusCode);
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
    public  int deleteByRetailOrderTypeCode(String retailOrderTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByRetailOrderTypeCode(sqlSession,retailOrderTypeCode);
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
    public  int deleteByStoryVideoId(Integer storyVideoId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByStoryVideoId(sqlSession,storyVideoId);
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
    public  int deleteByClubActivityId(SqlSession sqlSession,Integer clubActivityId)    
    {
        List<RetailOrder> list = getListByClubActivityId(sqlSession,clubActivityId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<RetailOrder> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<RetailOrder> list = getListByMemberId(sqlSession,memberId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByNightStoryTypeId(SqlSession sqlSession,Integer nightStoryTypeId)    
    {
        List<RetailOrder> list = getListByNightStoryTypeId(sqlSession,nightStoryTypeId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByRetailOrderStatusCode(SqlSession sqlSession,String retailOrderStatusCode)    
    {
        List<RetailOrder> list = getListByRetailOrderStatusCode(sqlSession,retailOrderStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByRetailOrderTypeCode(SqlSession sqlSession,String retailOrderTypeCode)    
    {
        List<RetailOrder> list = getListByRetailOrderTypeCode(sqlSession,retailOrderTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByStoryVideoId(SqlSession sqlSession,Integer storyVideoId)    
    {
        List<RetailOrder> list = getListByStoryVideoId(sqlSession,storyVideoId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<RetailOrder> getListByClubActivityId(Integer clubActivityId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
         try        
        {
            entitys = getListByClubActivityId(sqlSession,clubActivityId);
        }
        catch(Exception  e)        
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
    public  List<RetailOrder> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
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
    public  List<RetailOrder> getListByMemberId(Integer memberId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
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
    public  List<RetailOrder> getListByNightStoryTypeId(Integer nightStoryTypeId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
         try        
        {
            entitys = getListByNightStoryTypeId(sqlSession,nightStoryTypeId);
        }
        catch(Exception  e)        
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
    public  List<RetailOrder> getListByRetailOrderStatusCode(String retailOrderStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
         try        
        {
            entitys = getListByRetailOrderStatusCode(sqlSession,retailOrderStatusCode);
        }
        catch(Exception  e)        
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
    public  List<RetailOrder> getListByRetailOrderTypeCode(String retailOrderTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
         try        
        {
            entitys = getListByRetailOrderTypeCode(sqlSession,retailOrderTypeCode);
        }
        catch(Exception  e)        
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
    public  List<RetailOrder> getListByStoryVideoId(Integer storyVideoId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<RetailOrder> entitys = null;
         try        
        {
            entitys = getListByStoryVideoId(sqlSession,storyVideoId);
        }
        catch(Exception  e)        
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
    public  List<RetailOrder> getListByClubActivityId(SqlSession sqlSession,Integer clubActivityId)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByClubActivityId",clubActivityId);
        return entitys;
    }
    public  List<RetailOrder> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByCompanyId",companyId);
        return entitys;
    }
    public  List<RetailOrder> getListByMemberId(SqlSession sqlSession,Integer memberId)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByMemberId",memberId);
        return entitys;
    }
    public  List<RetailOrder> getListByNightStoryTypeId(SqlSession sqlSession,Integer nightStoryTypeId)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByNightStoryTypeId",nightStoryTypeId);
        return entitys;
    }
    public  List<RetailOrder> getListByRetailOrderStatusCode(SqlSession sqlSession,String retailOrderStatusCode)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByRetailOrderStatusCode",retailOrderStatusCode);
        return entitys;
    }
    public  List<RetailOrder> getListByRetailOrderTypeCode(SqlSession sqlSession,String retailOrderTypeCode)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByRetailOrderTypeCode",retailOrderTypeCode);
        return entitys;
    }
    public  List<RetailOrder> getListByStoryVideoId(SqlSession sqlSession,Integer storyVideoId)    
    {
        List<RetailOrder> entitys = sqlSession.selectList("RetailOrder.selectRetailOrderByStoryVideoId",storyVideoId);
        return entitys;
    }
    public  RetailOrder getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        RetailOrder entity = null;
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
    public  RetailOrder getEntityByRetailOrderNo(String retailOrderNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        RetailOrder entity = null;
        try        
        {
             entity = getEntityByRetailOrderNo(sqlSession,retailOrderNo);
        }
        catch(Exception  e)        
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
    public  RetailOrder getEntityById(SqlSession sqlSession,Integer id)    
    {
        RetailOrder entity = sqlSession.selectOne("selectRetailOrderByCondition","id='"+id+"'");
        return entity;
    }
    public  RetailOrder getEntityByRetailOrderNo(SqlSession sqlSession,String retailOrderNo)    
    {
        RetailOrder entity = sqlSession.selectOne("selectRetailOrderByCondition","retail_order_no='"+retailOrderNo+"'");
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
    public  boolean existsRetailOrderNo(String retailOrderNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsRetailOrderNo(sqlSession,retailOrderNo);
        }
        catch(Exception  e)        
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
        Integer rows = sqlSession.selectOne("RetailOrder.selectIntBySql","select count(*) from retail_order where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsRetailOrderNo(SqlSession sqlSession,String retailOrderNo)    
    {
        Integer rows = sqlSession.selectOne("RetailOrder.selectIntBySql","select count(*) from retail_order where retail_order_no= '"+retailOrderNo+"'");
        return rows>0?true:false;
    }
}