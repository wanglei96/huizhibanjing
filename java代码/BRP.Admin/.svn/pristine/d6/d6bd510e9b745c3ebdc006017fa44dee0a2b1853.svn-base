package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import BRP.data.BookSkuReviseItem;
import java.math.*;
public class BookSkuReviseItemMaster extends IdDataManager 
{
    public  int add(BookSkuReviseItem entity)throws Exception    
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
    public  int add(SqlSession sqlSession,BookSkuReviseItem entity)    
    {
        int rows = sqlSession.insert("BookSkuReviseItem.insertBookSkuReviseItem",entity);
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
        return add(sqlSession,(BookSkuReviseItem)dataEntity);
    }
    public  int addList(List<BookSkuReviseItem> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<BookSkuReviseItem> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("BookSkuReviseItem.insertBookSkuReviseItems",list);
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
        int rows = sqlSession.delete("BookSkuReviseItem.deleteBookSkuReviseItem",entity);
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
    public  BookSkuReviseItem getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        BookSkuReviseItem newEntity = null;
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
    public  BookSkuReviseItem getEntity(SqlSession sqlSession,Integer id)    
    {
        BookSkuReviseItem entity = createEntity();
        entity.setId(id);
        BookSkuReviseItem newEntity = sqlSession.selectOne("BookSkuReviseItem.selectBookSkuReviseItem",entity);
        return newEntity;
    }
    public  List<BookSkuReviseItem> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession)    
    {
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItems");
        return list;
    }
    public  List<BookSkuReviseItem> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession,String condition)    
    {
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByCondition",condition);
        return list;
    }
    public  List<BookSkuReviseItem> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<BookSkuReviseItem> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from book_sku_revise_item limit %s, %s", pageSize * pageIndex, pageSize);
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemBySql", sql);
        return list;
    }
    public  List<BookSkuReviseItem> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByCondition",sql);
        return list;
    }
    public  List<BookSkuReviseItem> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByCondition",sql);
        return list;
    }
    public  List<BookSkuReviseItem> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> list = null;
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
    public  List<BookSkuReviseItem> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<BookSkuReviseItem> list = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemBySql",sql);
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
        Integer rows = sqlSession.selectOne("BookSkuReviseItem.selectCountByCondition",condition);
        return rows;
    }
    public  int update(BookSkuReviseItem entity)throws Exception    
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
    public  int update(SqlSession sqlSession,BookSkuReviseItem entity)throws IOException    
    {
        int rows = sqlSession.update("BookSkuReviseItem.updateBookSkuReviseItem",entity);
        return rows;
    }
    public  BookSkuReviseItem createEntity()    
    {
        return new BookSkuReviseItem();
    }
    public  DataEntity createDataEntity()    
    {
        return new BookSkuReviseItem();
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
        List<BookSkuReviseItem> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
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
    public  int deleteByBookSkuFieldCode(String bookSkuFieldCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByBookSkuFieldCode(sqlSession,bookSkuFieldCode);
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
    public  int deleteByBookSkuReviseId(Integer bookSkuReviseId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByBookSkuReviseId(sqlSession,bookSkuReviseId);
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
    public  int deleteByApproveStatusCode(SqlSession sqlSession,String approveStatusCode)    
    {
        List<BookSkuReviseItem> list = getListByApproveStatusCode(sqlSession,approveStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByBookSkuFieldCode(SqlSession sqlSession,String bookSkuFieldCode)    
    {
        List<BookSkuReviseItem> list = getListByBookSkuFieldCode(sqlSession,bookSkuFieldCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByBookSkuReviseId(SqlSession sqlSession,Integer bookSkuReviseId)    
    {
        List<BookSkuReviseItem> list = getListByBookSkuReviseId(sqlSession,bookSkuReviseId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<BookSkuReviseItem> getListByApproveStatusCode(String approveStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> entitys = null;
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
    public  List<BookSkuReviseItem> getListByBookSkuFieldCode(String bookSkuFieldCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> entitys = null;
         try        
        {
            entitys = getListByBookSkuFieldCode(sqlSession,bookSkuFieldCode);
        }
        catch(Exception  e)        
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
    public  List<BookSkuReviseItem> getListByBookSkuReviseId(Integer bookSkuReviseId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuReviseItem> entitys = null;
         try        
        {
            entitys = getListByBookSkuReviseId(sqlSession,bookSkuReviseId);
        }
        catch(Exception  e)        
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
    public  List<BookSkuReviseItem> getListByApproveStatusCode(SqlSession sqlSession,String approveStatusCode)    
    {
        List<BookSkuReviseItem> entitys = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByApproveStatusCode",approveStatusCode);
        return entitys;
    }
    public  List<BookSkuReviseItem> getListByBookSkuFieldCode(SqlSession sqlSession,String bookSkuFieldCode)    
    {
        List<BookSkuReviseItem> entitys = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByBookSkuFieldCode",bookSkuFieldCode);
        return entitys;
    }
    public  List<BookSkuReviseItem> getListByBookSkuReviseId(SqlSession sqlSession,Integer bookSkuReviseId)    
    {
        List<BookSkuReviseItem> entitys = sqlSession.selectList("BookSkuReviseItem.selectBookSkuReviseItemByBookSkuReviseId",bookSkuReviseId);
        return entitys;
    }
    public  BookSkuReviseItem getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        BookSkuReviseItem entity = null;
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
    public  BookSkuReviseItem getEntityById(SqlSession sqlSession,Integer id)    
    {
        BookSkuReviseItem entity = sqlSession.selectOne("selectBookSkuReviseItemByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("BookSkuReviseItem.selectIntBySql","select count(*) from book_sku_revise_item where id= '"+id+"'");
        return rows>0?true:false;
    }
}