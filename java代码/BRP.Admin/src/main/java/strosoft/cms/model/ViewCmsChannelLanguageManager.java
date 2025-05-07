package strosoft.cms.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.ViewCmsChannelLanguage;

public class ViewCmsChannelLanguageManager  extends IdDataManager 
{
    private static ViewCmsChannelLanguageManager _instance;
    public  static ViewCmsChannelLanguageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCmsChannelLanguageManager();
        }
        return _instance;
    }
    
    
    public ArrayList<ViewCmsChannelLanguage> getListByCmsLanguageCdoe(String cmsLanguageCode) throws IOException{
    	List<ViewCmsChannelLanguage> viewCmsChannelLanguages =  this.getList("cms_language_code = '"+cmsLanguageCode+"'");
    	return super.toList(viewCmsChannelLanguages);
    }
    
    public ArrayList<ViewCmsChannelLanguage> getListBySql(String sqlCondition) throws IOException{
    	SqlSession sqlSession = MyBatisManager.getInstance().openSession();
    	String sql = "SELECT * FROM view_cms_channel_language "+sqlCondition;
    	List<ViewCmsChannelLanguage> viewCmsChannelLanguages = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageBySql", sql);
    	return super.toList(viewCmsChannelLanguages);
    }
    
	/**
	 * 获取所有父栏目直到首页
	 * @param id
	 * @param viewCmsChannels 所有栏目组成的Hashtable
	 * @return
	 */
	public ArrayList<ViewCmsChannelLanguage> getParentsList(int id,Hashtable<Integer, ViewCmsChannelLanguage> viewCmsChannels){
		ArrayList<ViewCmsChannelLanguage> lstParentChannel = new ArrayList<ViewCmsChannelLanguage>();
		ViewCmsChannelLanguage theViewCmsChannel = viewCmsChannels.get(id);
		if (null == theViewCmsChannel || theViewCmsChannel.getParentId() == null ||theViewCmsChannel.getParentId() == 0) {
			return lstParentChannel;
		}
		ViewCmsChannelLanguage theParentChannel =  viewCmsChannels.get(theViewCmsChannel.getParentId());
		if (theParentChannel == null) {
			return lstParentChannel;
		}
		lstParentChannel.add(theParentChannel);
		ArrayList<ViewCmsChannelLanguage> lstParentParentChannel  = this.getParentsList(theViewCmsChannel.getParentId(),viewCmsChannels);	
		if (lstParentParentChannel.size() > 0) {
			lstParentChannel.addAll(lstParentParentChannel);
		}
		return lstParentChannel;
		}
	/**
	 * 将数据库中查出来的list转换为key为id的hashtable
	 * @param viewCmsChannels
	 * @return
	 */
	public Hashtable<Integer, ViewCmsChannelLanguage> getViewChannelHashtable(ArrayList<ViewCmsChannelLanguage> viewCmsChannels){
		Hashtable<Integer, ViewCmsChannelLanguage> viewCmsChannelHashtable = new Hashtable<Integer, ViewCmsChannelLanguage>();
		for(int i=0;i<viewCmsChannels.size();i++){
			ViewCmsChannelLanguage viewCmsChannel = viewCmsChannels.get(i);
			viewCmsChannelHashtable.put(viewCmsChannel.getId(), viewCmsChannel);
		}
		return viewCmsChannelHashtable;
	}
	
	
	
	
	
    public  List<ViewCmsChannelLanguage> getList()throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession)    
    {
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguages");
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition)    
    {
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",condition);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition,String orderBy)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
        try        
        {
            list = getList(sqlSession,pagesize,pageindex);
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,int pagesize,int pageindex)    
    {
        String limt = " limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",limt);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getListPage(String condition,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
        try        
        {
            list = getList(sqlSession,condition,pagesize,pageindex);
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,int pagesize,int pageindex)    
    {
        String sql = condition+" limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",sql);
        return list;
    }
    public  List<ViewCmsChannelLanguage> getList(String condition,String orderBy,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<ViewCmsChannelLanguage> list = null;
        try        
        {
            list = getList(sqlSession,condition,orderBy,pagesize,pageindex);
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
    public  List<ViewCmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limt "+pagesize*(pageindex-1)+","+pagesize;
        List<ViewCmsChannelLanguage> list = sqlSession.selectList("ViewCmsChannelLanguage.selectViewCmsChannelLanguageByCondition",sql);
        return list;
    }
    public  Integer getCount(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Integer count = null;
        try        
        {
            count = getCount(sqlSession,condition);
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
        return count;
    }
    public  Integer getCount(SqlSession sqlSession,String condition)    
    {
        Integer rows = sqlSession.selectOne("ViewCmsChannelLanguage.selectCountByCondition",condition);
        return rows;
    }
    public  ViewCmsChannelLanguage createEntity()    
    {
        return new ViewCmsChannelLanguage();
    }
    public  DataEntity createDataEntity()    
    {
        return new ViewCmsChannelLanguage();
    }
    
}