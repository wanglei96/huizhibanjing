package strosoft.sys.model;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.sys.data.ViewSysMenuSysFunction;

public class ViewSysMenuSysFunctionManager  extends CodeDataManager
{
    private static ViewSysMenuSysFunctionManager _instance;
    public  static ViewSysMenuSysFunctionManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewSysMenuSysFunctionManager();
        }
        return _instance;
    }
    public  List<ViewSysMenuSysFunction> getList()throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getList(sqlSession);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getList(SqlSession sqlSession)    
    {
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctions");
        return list;
    }
    public  List<ViewSysMenuSysFunction> getList(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getList(sqlSession,condition);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctionByCondition",condition);
        return list;
    }
    public  List<ViewSysMenuSysFunction> getList(String condition,String orderBy)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getList(sqlSession,condition,orderBy);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctionByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getListPage(sqlSession,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(SqlSession sqlSession,int pagesize,int pageindex)    
    {
        String limt = " limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctionByCondition",limt);
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(String condition,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getListPage(sqlSession,condition,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(SqlSession sqlSession,String condition,int pagesize,int pageindex)    
    {
        String sql = condition+" limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctionByCondition",sql);
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(String condition,String orderBy,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewSysMenuSysFunction> list = null;
        try        
        {
            list = getListPage(sqlSession,condition,orderBy,pagesize,pageindex);
        }
        catch(Exception  e)        
        {
            e.printStackTrace();
            sqlSession.rollback();
        }
         finally         
        {
            sqlSession.close();
        }
        return list;
    }
    public  List<ViewSysMenuSysFunction> getListPage(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" orderBy "+orderBy+" limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<ViewSysMenuSysFunction> list = sqlSession.selectList("ViewSysMenuSysFunction.selectViewSysMenuSysFunctionByCondition",sql);
        return list;
    }
    public  ViewSysMenuSysFunction createEntity()    
    {
        return new ViewSysMenuSysFunction();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewSysMenuSysFunction();
    }
    
	/**
	 * 把系统菜单ArrayList转换为Key为id的Hashtable
	 * @param allSysMenu
	 * @return
	 */
	public Hashtable<String,ViewSysMenuSysFunction> getViewSysMenuSysFunctionIdHashtable(List<ViewSysMenuSysFunction> allSysMenuSysFuntion) {
		Hashtable<String,ViewSysMenuSysFunction> sysMenuSysFunctionHashtable = new Hashtable<String, ViewSysMenuSysFunction>();
		for(int i=0;i<allSysMenuSysFuntion.size();i++){
			ViewSysMenuSysFunction sysMenuSysFunction = allSysMenuSysFuntion.get(i);
			sysMenuSysFunctionHashtable.put(sysMenuSysFunction.getId(), sysMenuSysFunction);
		}
		return sysMenuSysFunctionHashtable;
	}
	/**
	 * 把系统菜单ArrayList转换为Key为value的Hashtable
	 * @param allSysMenu
	 * @return
	 */
	public Hashtable<String,ViewSysMenuSysFunction> getViewSysMenuSysFunctionValueHashtable(List<ViewSysMenuSysFunction> allSysMenuSysFuntion) {
		Hashtable<String,ViewSysMenuSysFunction> sysMenuSysFunctionHashtable = new Hashtable<String, ViewSysMenuSysFunction>();
		for(int i=0;i<allSysMenuSysFuntion.size();i++){
			ViewSysMenuSysFunction sysMenuSysFunction = allSysMenuSysFuntion.get(i);
			sysMenuSysFunctionHashtable.put(sysMenuSysFunction.getValue(), sysMenuSysFunction);
		}
		return sysMenuSysFunctionHashtable;
	}
}