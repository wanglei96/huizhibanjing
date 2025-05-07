package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.SalesOrder;
import BRP.model.SalesOrderItemManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SalesOrderMaster extends IdDataManager 
{
    public  int add(SalesOrder entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SalesOrder entity)    
    {
        int rows = sqlSession.insert("SalesOrder.insertSalesOrder",entity);
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
        return add(sqlSession,(SalesOrder)dataEntity);
    }
    public  int addList(List<SalesOrder> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SalesOrder> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SalesOrder.insertSalesOrders",list);
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
        SalesOrderItemManager.getInstance().deleteBySalesOrderId(sqlSession,((SalesOrder)entity).getId());
        int rows = sqlSession.delete("SalesOrder.deleteSalesOrder",entity);
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
    public  SalesOrder getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SalesOrder newEntity = null;
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
    public  SalesOrder getEntity(SqlSession sqlSession,Integer id)    
    {
        SalesOrder entity = createEntity();
        entity.setId(id);
        SalesOrder newEntity = sqlSession.selectOne("SalesOrder.selectSalesOrder",entity);
        return newEntity;
    }
    public  List<SalesOrder> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession)    
    {
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrders");
        return list;
    }
    public  List<SalesOrder> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession,String condition)    
    {
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderByCondition",condition);
        return list;
    }
    public  List<SalesOrder> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SalesOrder> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sales_order limit %s, %s", pageSize * pageIndex, pageSize);
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderBySql", sql);
        return list;
    }
    public  List<SalesOrder> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderByCondition",sql);
        return list;
    }
    public  List<SalesOrder> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderByCondition",sql);
        return list;
    }
    public  List<SalesOrder> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> list = null;
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
    public  List<SalesOrder> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SalesOrder> list = sqlSession.selectList("SalesOrder.selectSalesOrderBySql",sql);
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
        Integer rows = sqlSession.selectOne("SalesOrder.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SalesOrder entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SalesOrder entity)throws IOException    
    {
        int rows = sqlSession.update("SalesOrder.updateSalesOrder",entity);
        return rows;
    }
    public  SalesOrder createEntity()    
    {
        return new SalesOrder();
    }
    public  DataEntity createDataEntity()    
    {
        return new SalesOrder();
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
        List<SalesOrder> list = getList(sqlSession);
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
    public  int deleteByCompanyUserId(Integer companyUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCompanyUserId(sqlSession,companyUserId);
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
    public  int deleteBySalesOrderStatusCode(String salesOrderStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySalesOrderStatusCode(sqlSession,salesOrderStatusCode);
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
    public  int deleteBySalesOrderTypeCode(String salesOrderTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteBySalesOrderTypeCode(sqlSession,salesOrderTypeCode);
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
        List<SalesOrder> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCompanyUserId(SqlSession sqlSession,Integer companyUserId)    
    {
        List<SalesOrder> list = getListByCompanyUserId(sqlSession,companyUserId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<SalesOrder> list = getListByPaymentMethodCode(sqlSession,paymentMethodCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySalesOrderStatusCode(SqlSession sqlSession,String salesOrderStatusCode)    
    {
        List<SalesOrder> list = getListBySalesOrderStatusCode(sqlSession,salesOrderStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteBySalesOrderTypeCode(SqlSession sqlSession,String salesOrderTypeCode)    
    {
        List<SalesOrder> list = getListBySalesOrderTypeCode(sqlSession,salesOrderTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<SalesOrder> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> entitys = null;
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
    public  List<SalesOrder> getListByCompanyUserId(Integer companyUserId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> entitys = null;
         try        
        {
            entitys = getListByCompanyUserId(sqlSession,companyUserId);
        }
        catch(Exception  e)        
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
    public  List<SalesOrder> getListByPaymentMethodCode(String paymentMethodCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> entitys = null;
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
    public  List<SalesOrder> getListBySalesOrderStatusCode(String salesOrderStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> entitys = null;
         try        
        {
            entitys = getListBySalesOrderStatusCode(sqlSession,salesOrderStatusCode);
        }
        catch(Exception  e)        
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
    public  List<SalesOrder> getListBySalesOrderTypeCode(String salesOrderTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SalesOrder> entitys = null;
         try        
        {
            entitys = getListBySalesOrderTypeCode(sqlSession,salesOrderTypeCode);
        }
        catch(Exception  e)        
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
    public  List<SalesOrder> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<SalesOrder> entitys = sqlSession.selectList("SalesOrder.selectSalesOrderByCompanyId",companyId);
        return entitys;
    }
    public  List<SalesOrder> getListByCompanyUserId(SqlSession sqlSession,Integer companyUserId)    
    {
        List<SalesOrder> entitys = sqlSession.selectList("SalesOrder.selectSalesOrderByCompanyUserId",companyUserId);
        return entitys;
    }
    public  List<SalesOrder> getListByPaymentMethodCode(SqlSession sqlSession,String paymentMethodCode)    
    {
        List<SalesOrder> entitys = sqlSession.selectList("SalesOrder.selectSalesOrderByPaymentMethodCode",paymentMethodCode);
        return entitys;
    }
    public  List<SalesOrder> getListBySalesOrderStatusCode(SqlSession sqlSession,String salesOrderStatusCode)    
    {
        List<SalesOrder> entitys = sqlSession.selectList("SalesOrder.selectSalesOrderBySalesOrderStatusCode",salesOrderStatusCode);
        return entitys;
    }
    public  List<SalesOrder> getListBySalesOrderTypeCode(SqlSession sqlSession,String salesOrderTypeCode)    
    {
        List<SalesOrder> entitys = sqlSession.selectList("SalesOrder.selectSalesOrderBySalesOrderTypeCode",salesOrderTypeCode);
        return entitys;
    }
    public  SalesOrder getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SalesOrder entity = null;
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
    public  SalesOrder getEntityBySalesOrderNo(String salesOrderNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SalesOrder entity = null;
        try        
        {
             entity = getEntityBySalesOrderNo(sqlSession,salesOrderNo);
        }
        catch(Exception  e)        
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
    public  SalesOrder getEntityById(SqlSession sqlSession,Integer id)    
    {
        SalesOrder entity = sqlSession.selectOne("selectSalesOrderByCondition","id='"+id+"'");
        return entity;
    }
    public  SalesOrder getEntityBySalesOrderNo(SqlSession sqlSession,String salesOrderNo)    
    {
        SalesOrder entity = sqlSession.selectOne("selectSalesOrderByCondition","sales_order_no='"+salesOrderNo+"'");
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
    public  boolean existsSalesOrderNo(String salesOrderNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsSalesOrderNo(sqlSession,salesOrderNo);
        }
        catch(Exception  e)        
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
        Integer rows = sqlSession.selectOne("SalesOrder.selectIntBySql","select count(*) from sales_order where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsSalesOrderNo(SqlSession sqlSession,String salesOrderNo)    
    {
        Integer rows = sqlSession.selectOne("SalesOrder.selectIntBySql","select count(*) from sales_order where sales_order_no= '"+salesOrderNo+"'");
        return rows>0?true:false;
    }
}