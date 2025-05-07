package BRP.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.data.MemberBorrowTotal;
import java.io.IOException;
import strosoft.app.common.ViewDataMaster;
import strosoft.app.data.DataEntity;
public class MemberBorrowTotalMaster extends ViewDataMaster 
{
    public  List<MemberBorrowTotal> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession)    
    {
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotals");
        return list;
    }
    public  List<MemberBorrowTotal> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession,String condition)    
    {
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotalByCondition",condition);
        return list;
    }
    public  List<MemberBorrowTotal> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotalByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<MemberBorrowTotal> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from member_borrow_total limit %s, %s", pageSize * pageIndex, pageSize);
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotalBySql", sql);
        return list;
    }
    public  List<MemberBorrowTotal> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotalByCondition",sql);
        return list;
    }
    public  List<MemberBorrowTotal> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<MemberBorrowTotal> list = null;
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
    public  List<MemberBorrowTotal> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<MemberBorrowTotal> list = sqlSession.selectList("MemberBorrowTotal.selectMemberBorrowTotalByCondition",sql);
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
        Integer rows = sqlSession.selectOne("MemberBorrowTotal.selectCountByCondition",condition);
        return rows;
    }
    public  MemberBorrowTotal createEntity()    
    {
        return new MemberBorrowTotal();
    }
    public  DataEntity createDataEntity()    
    {
        return new MemberBorrowTotal();
    }
}