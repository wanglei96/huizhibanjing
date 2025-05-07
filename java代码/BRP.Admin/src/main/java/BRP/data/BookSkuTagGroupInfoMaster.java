package BRP.data;
import BRP.data.BookSkuTagGroupInfo;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class BookSkuTagGroupInfoMaster extends ViewDataMaster 
{
    public  List<BookSkuTagGroupInfo> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession)    
    {
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfos");
        return list;
    }
    public  List<BookSkuTagGroupInfo> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession,String condition)    
    {
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfoByCondition",condition);
        return list;
    }
    public  List<BookSkuTagGroupInfo> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfoByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<BookSkuTagGroupInfo> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from book_sku_tag_group_info limit %s, %s", pageSize * pageIndex, pageSize);
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfoBySql", sql);
        return list;
    }
    public  List<BookSkuTagGroupInfo> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfoByCondition",sql);
        return list;
    }
    public  List<BookSkuTagGroupInfo> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<BookSkuTagGroupInfo> list = null;
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
    public  List<BookSkuTagGroupInfo> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<BookSkuTagGroupInfo> list = sqlSession.selectList("BookSkuTagGroupInfo.selectBookSkuTagGroupInfoByCondition",sql);
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
        Integer rows = sqlSession.selectOne("BookSkuTagGroupInfo.selectCountByCondition",condition);
        return rows;
    }
    public  BookSkuTagGroupInfo createEntity()    
    {
        return new BookSkuTagGroupInfo();
    }
    public  DataEntity createDataEntity()    
    {
        return new BookSkuTagGroupInfo();
    }
}