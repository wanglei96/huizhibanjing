package BRP.data;
import BRP.model.HomeNewBookManager;
import org.apache.ibatis.session.SqlSession;
import BRP.model.HomeRandomBookManager;
import BRP.model.MemberBookmarkManager;
import BRP.model.BookSkuBookTagManager;
import BRP.model.BookSkuAgeGroupManager;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.model.MemberBookSubscriptionManager;
import BRP.model.BookManager;
import BRP.model.BookSkuReviseManager;
import BRP.model.BookStocksLogManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
import BRP.data.BookSku;
public class BookSkuMaster extends IdDataManager 
{
    public  int add(BookSku entity)throws Exception    
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
    public  int add(SqlSession sqlSession,BookSku entity)    
    {
        int rows = sqlSession.insert("BookSku.insertBookSku",entity);
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
        return add(sqlSession,(BookSku)dataEntity);
    }
    public  int addList(List<BookSku> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<BookSku> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("BookSku.insertBookSkus",list);
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
        BookManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        BookSkuAgeGroupManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        BookSkuBookTagManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        BookSkuReviseManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        BookStocksLogManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        HomeNewBookManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        HomeRandomBookManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        MemberBookSubscriptionManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        MemberBookmarkManager.getInstance().deleteByBookSkuId(sqlSession,((BookSku)entity).getId());
        int rows = sqlSession.delete("BookSku.deleteBookSku",entity);
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
    public  BookSku getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        BookSku newEntity = null;
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
    public  BookSku getEntity(SqlSession sqlSession,Integer id)    
    {
        BookSku entity = createEntity();
        entity.setId(id);
        BookSku newEntity = sqlSession.selectOne("BookSku.selectBookSku",entity);
        return newEntity;
    }
    public  List<BookSku> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession)    
    {
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkus");
        return list;
    }
    public  List<BookSku> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession,String condition)    
    {
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuByCondition",condition);
        return list;
    }
    public  List<BookSku> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<BookSku> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from book_sku limit %s, %s", pageSize * pageIndex, pageSize);
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuBySql", sql);
        return list;
    }
    public  List<BookSku> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuByCondition",sql);
        return list;
    }
    public  List<BookSku> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuByCondition",sql);
        return list;
    }
    public  List<BookSku> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> list = null;
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
    public  List<BookSku> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<BookSku> list = sqlSession.selectList("BookSku.selectBookSkuBySql",sql);
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
        Integer rows = sqlSession.selectOne("BookSku.selectCountByCondition",condition);
        return rows;
    }
    public  int update(BookSku entity)throws Exception    
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
    public  int update(SqlSession sqlSession,BookSku entity)throws IOException    
    {
        int rows = sqlSession.update("BookSku.updateBookSku",entity);
        return rows;
    }
    public  BookSku createEntity()    
    {
        return new BookSku();
    }
    public  DataEntity createDataEntity()    
    {
        return new BookSku();
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
        List<BookSku> list = getList(sqlSession);
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
    public  int deleteByApproveStatusCode(String approveStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByApproveStatusCode(sqlSession,approveStatusCode);
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
    public  int deleteByApproverId(Integer approverId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByApproverId(sqlSession,approverId);
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
    public  int deleteByBindingTypeCode(String bindingTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByBindingTypeCode(sqlSession,bindingTypeCode);
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
    public  int deleteByBookSeriesId(Integer bookSeriesId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByBookSeriesId(sqlSession,bookSeriesId);
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
    public  int deleteByImageFileId(Integer imageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByImageFileId(sqlSession,imageFileId);
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
    public  int deleteByLanguageCode(String languageCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByLanguageCode(sqlSession,languageCode);
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
        List<BookSku> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByApproveStatusCode(SqlSession sqlSession,String approveStatusCode)    
    {
        List<BookSku> list = getListByApproveStatusCode(sqlSession,approveStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByApproverId(SqlSession sqlSession,Integer approverId)    
    {
        List<BookSku> list = getListByApproverId(sqlSession,approverId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByBindingTypeCode(SqlSession sqlSession,String bindingTypeCode)    
    {
        List<BookSku> list = getListByBindingTypeCode(sqlSession,bindingTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByBookSeriesId(SqlSession sqlSession,Integer bookSeriesId)    
    {
        List<BookSku> list = getListByBookSeriesId(sqlSession,bookSeriesId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<BookSku> list = getListByImageFileId(sqlSession,imageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByLanguageCode(SqlSession sqlSession,String languageCode)    
    {
        List<BookSku> list = getListByLanguageCode(sqlSession,languageCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<BookSku> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
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
    public  List<BookSku> getListByApproveStatusCode(String approveStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByApproveStatusCode(sqlSession,approveStatusCode);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByApproverId(Integer approverId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByApproverId(sqlSession,approverId);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByBindingTypeCode(String bindingTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByBindingTypeCode(sqlSession,bindingTypeCode);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByBookSeriesId(Integer bookSeriesId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByBookSeriesId(sqlSession,bookSeriesId);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByImageFileId(Integer imageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByImageFileId(sqlSession,imageFileId);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByLanguageCode(String languageCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSku> entitys = null;
         try        
        {
            entitys = getListByLanguageCode(sqlSession,languageCode);
        }
        catch(Exception  e)        
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
    public  List<BookSku> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByCompanyId",companyId);
        return entitys;
    }
    public  List<BookSku> getListByApproveStatusCode(SqlSession sqlSession,String approveStatusCode)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByApproveStatusCode",approveStatusCode);
        return entitys;
    }
    public  List<BookSku> getListByApproverId(SqlSession sqlSession,Integer approverId)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByApproverId",approverId);
        return entitys;
    }
    public  List<BookSku> getListByBindingTypeCode(SqlSession sqlSession,String bindingTypeCode)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByBindingTypeCode",bindingTypeCode);
        return entitys;
    }
    public  List<BookSku> getListByBookSeriesId(SqlSession sqlSession,Integer bookSeriesId)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByBookSeriesId",bookSeriesId);
        return entitys;
    }
    public  List<BookSku> getListByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByImageFileId",imageFileId);
        return entitys;
    }
    public  List<BookSku> getListByLanguageCode(SqlSession sqlSession,String languageCode)    
    {
        List<BookSku> entitys = sqlSession.selectList("BookSku.selectBookSkuByLanguageCode",languageCode);
        return entitys;
    }
    public  BookSku getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        BookSku entity = null;
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
    public  BookSku getEntityById(SqlSession sqlSession,Integer id)    
    {
        BookSku entity = sqlSession.selectOne("selectBookSkuByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("BookSku.selectIntBySql","select count(*) from book_sku where id= '"+id+"'");
        return rows>0?true:false;
    }
}