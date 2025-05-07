package strosoft.cms.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelLanguage;
import strosoft.cms.data.CmsLanguage;
public class CmsChannelLanguageManager extends IdDataManager
{
    private static CmsChannelLanguageManager _instance;
    private CmsChannelLanguageManager()    
    {
        this.setTableName("cms_channel_language");
        this.setTypeName("CmsChannelLanguage");
    }
    public  static CmsChannelLanguageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsChannelLanguageManager();
        }
        return _instance;
    }
    
    //通过cmsChannel新增
    public int addByCmsChannel(SqlSession sqlSession,CmsChannel cmsChannel,String cmsLanguageCode) throws IOException{
    	//新增语言栏目
		Integer result = 0 ;
		String condition = " cms_site_id = "+cmsChannel.getCmsSiteId();
		List<CmsLanguage> cmsLanguages = CmsLanguageManager.getInstance().getList(sqlSession, condition);
		List<CmsChannelLanguage> cmsChannelLanguages = new ArrayList<CmsChannelLanguage>();
		for(int i=0;i<cmsLanguages.size();i++){
			CmsChannelLanguage cmsChannelLanguage = null;
			CmsLanguage cmsLanguage = cmsLanguages.get(i);
			if(cmsLanguage.getCode().equals(cmsLanguageCode)){
				cmsChannelLanguage = createEntityByCmsChannel(cmsChannel, cmsLanguageCode, null);
			}else{
				cmsChannelLanguage = new CmsChannelLanguage();
				cmsChannelLanguage.setCmsChannelId(cmsChannel.getId());
				cmsChannelLanguage.setCmsLanguageCode(cmsLanguage.getCode());
			}
			cmsChannelLanguages.add(cmsChannelLanguage);
		}
		if(cmsChannelLanguages.size()>0){
			result =  CmsChannelLanguageManager.getInstance().addList(sqlSession, cmsChannelLanguages);
		}
    	return result;
    }
    
    //通过cmsChannel修改
    public int updateByCmsChannel(SqlSession sqlSession,CmsChannel cmsChannel,String cmsLanguageCode,Integer cmsChannelLanguageId) throws IOException{
    	CmsChannelLanguage channelLanguage = createEntityByCmsChannel(cmsChannel, cmsLanguageCode, cmsChannelLanguageId);
    	return update(sqlSession,channelLanguage);
    }
    
    //传入cmsChannel、语言编码、多语言栏目ID创建多语言栏目实体
    public CmsChannelLanguage createEntityByCmsChannel(CmsChannel cmsChannel,String cmsLanguageCode,Integer cmsChannelLanguageId){
    	CmsChannelLanguage channelLanguage = new CmsChannelLanguage();
    	channelLanguage.setId(cmsChannelLanguageId);
    	channelLanguage.setCmsLanguageCode(cmsLanguageCode);
    	channelLanguage.setDescription(cmsChannel.getDescription());
    	channelLanguage.setCmsChannelId(cmsChannel.getId());
    	channelLanguage.setImageFileId(cmsChannel.getImageFileId());
    	channelLanguage.setKeyword(cmsChannel.getKeyword());
    	channelLanguage.setName(cmsChannel.getName());
    	channelLanguage.setText(cmsChannel.getText());
    	channelLanguage.setTitle(cmsChannel.getTitle());
    	channelLanguage.setUrl(cmsChannel.getUrl());
    	channelLanguage.setChannelFilePathRule(cmsChannel.getChannelFilePathRule());
    	return channelLanguage;
    }
    
    public  int add(CmsChannelLanguage entity)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return result;
    }
    public  int add(SqlSession sqlSession,CmsChannelLanguage entity)    
    {
        int rows = sqlSession.insert("CmsChannelLanguage.insertCmsChannelLanguage",entity);
        int newId = entity.getId();
        if (newId > 0)         
        {
            return newId;
        }
         else         
        {
            return rows;
        }
    }
    public  int addList(List<CmsChannelLanguage> list)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int addList(SqlSession sqlSession,List<CmsChannelLanguage> list)    
    {
        int rows = sqlSession.insert("CmsChannelLanguage.insertCmsChannelLanguages",list);
        return rows;
    }
    public  int delete(Integer id)throws IOException    
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
    public  int delete(CmsChannelLanguage entity)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int delete(SqlSession sqlSession,CmsChannelLanguage entity)    
    {
        int rows = sqlSession.delete("CmsChannelLanguage.deleteCmsChannelLanguage",entity);
        return rows;
    }
    public  int batchDelete(List<Integer> list)throws IOException    
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
    public  int batchDeleteByEntity(SqlSession sqlSession,List<CmsChannelLanguage> list)    
    {
        int rows = 0;
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  DataEntity getDataEntity(Integer id)throws IOException    
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
    public  CmsChannelLanguage getEntity(Integer id)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsChannelLanguage newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,id);
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
        return newEntity;
    }
    public  CmsChannelLanguage getEntity(SqlSession sqlSession,Integer id)    
    {
        CmsChannelLanguage entity = createEntity();
        entity.setId(id);
        CmsChannelLanguage newEntity = sqlSession.selectOne("CmsChannelLanguage.selectCmsChannelLanguage",entity);
        return newEntity;
    }
    public  List<CmsChannelLanguage> getList()throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession)    
    {
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguages");
        return list;
    }
    public  List<CmsChannelLanguage> getList(String condition)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCondition",condition);
        return list;
    }
    public  List<CmsChannelLanguage> getList(String condition,String orderBy)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" orderBy "+orderBy;
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsChannelLanguage> getList(int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession,int pagesize,int pageindex)    
    {
        String limt = " limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCondition",limt);
        return list;
    }
    public  List<CmsChannelLanguage> getListPage(String condition,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession,String condition,int pagesize,int pageindex)    
    {
        String sql = condition+" limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCondition",sql);
        return list;
    }
    public  List<CmsChannelLanguage> getList(String condition,String orderBy,int pagesize,int pageindex)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> list = null;
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
    public  List<CmsChannelLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" orderBy "+orderBy+" limt "+pagesize*(pageindex-1)+","+pagesize*pageindex;
        List<CmsChannelLanguage> list = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCondition",sql);
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
        Integer rows = sqlSession.selectOne("CmsChannelLanguage.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsChannelLanguage entity)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int update(SqlSession sqlSession,CmsChannelLanguage entity)throws IOException    
    {
        int rows = sqlSession.update("CmsChannelLanguage.updateCmsChannelLanguage",entity);
        return rows;
    }
    public  CmsChannelLanguage createEntity()    
    {
        return new CmsChannelLanguage();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsChannelLanguage();
    }
    public  int clear()throws IOException    
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
        List<CmsChannelLanguage> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByImageFileId(Integer imageFileId)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int deleteByCmsChannelId(Integer cmsChannelId)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsChannelId(sqlSession,cmsChannelId);
            sqlSession.commit();
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
        return rows;
    }
    public  int deleteByCmsLanguageCode(String cmsLanguageCode)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsLanguageCode(sqlSession,cmsLanguageCode);
            sqlSession.commit();
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
        return rows;
    }
    public  int deleteByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsChannelLanguage> list = getListByImageFileId(sqlSession,imageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<CmsChannelLanguage> list = getListByCmsChannelId(sqlSession,cmsChannelId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsLanguageCode(SqlSession sqlSession,String cmsLanguageCode)    
    {
        List<CmsChannelLanguage> list = getListByCmsLanguageCode(sqlSession,cmsLanguageCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<CmsChannelLanguage> getListByImageFileId(Integer imageFileId)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> entitys = null;
         try        
        {
            entitys = getListByImageFileId(sqlSession,imageFileId);
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
        return entitys;
    }
    public  List<CmsChannelLanguage> getListByCmsChannelId(Integer cmsChannelId)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> entitys = null;
         try        
        {
            entitys = getListByCmsChannelId(sqlSession,cmsChannelId);
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
        return entitys;
    }
    public  List<CmsChannelLanguage> getListByCmsLanguageCode(String cmsLanguageCode)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannelLanguage> entitys = null;
         try        
        {
            entitys = getListByCmsLanguageCode(sqlSession,cmsLanguageCode);
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
        return entitys;
    }
    public  List<CmsChannelLanguage> getListByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsChannelLanguage> entitys = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByImageFileId",imageFileId);
        return entitys;
    }
    public  List<CmsChannelLanguage> getListByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<CmsChannelLanguage> entitys = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCmsChannelId",cmsChannelId);
        return entitys;
    }
    public  List<CmsChannelLanguage> getListByCmsLanguageCode(SqlSession sqlSession,String cmsLanguageCode)    
    {
        List<CmsChannelLanguage> entitys = sqlSession.selectList("CmsChannelLanguage.selectCmsChannelLanguageByCmsLanguageCode",cmsLanguageCode);
        return entitys;
    }
    public  CmsChannelLanguage getEntityById(Integer id)throws IOException    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsChannelLanguage entity = null;
        try        
        {
             entity = getEntityById(sqlSession,id);
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
        return entity;
    }
    public  CmsChannelLanguage getEntityById(SqlSession sqlSession,Integer id)    
    {
        CmsChannelLanguage entity = sqlSession.selectOne("selectCmsChannelLanguageByCondition","id="+id);
        return entity;
    }
    public  boolean existsId(Integer id)throws IOException    
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
        }
         finally         
        {
            sqlSession.close();
        }
        return flag;
    }
    public  boolean existsId(SqlSession sqlSession,Integer id)    
    {
        Integer rows = sqlSession.selectOne("CmsChannelLanguage.selectIntBySql","select count(id) where id= '"+id+"'");
        return rows>0?true:false;
    }
}