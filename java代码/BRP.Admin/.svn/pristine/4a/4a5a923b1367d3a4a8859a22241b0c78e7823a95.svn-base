package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.data.CmsContentLanguage;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class CmsContentLanguageMaster extends IdDataManager 
{
    public  int add(CmsContentLanguage entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsContentLanguage entity)    
    {
        int rows = sqlSession.insert("CmsContentLanguage.insertCmsContentLanguage",entity);
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
        return add(sqlSession,(CmsContentLanguage)dataEntity);
    }
    public  int addList(List<CmsContentLanguage> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsContentLanguage> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsContentLanguage.insertCmsContentLanguages",list);
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
        int rows = sqlSession.delete("CmsContentLanguage.deleteCmsContentLanguage",entity);
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
    public  CmsContentLanguage getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentLanguage newEntity = null;
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
    public  CmsContentLanguage getEntity(SqlSession sqlSession,Integer id)    
    {
        CmsContentLanguage entity = createEntity();
        entity.setId(id);
        CmsContentLanguage newEntity = sqlSession.selectOne("CmsContentLanguage.selectCmsContentLanguage",entity);
        return newEntity;
    }
    public  List<CmsContentLanguage> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession)    
    {
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguages");
        return list;
    }
    public  List<CmsContentLanguage> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",condition);
        return list;
    }
    public  List<CmsContentLanguage> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsContentLanguage> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_content_language limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageBySql", sql);
        return list;
    }
    public  List<CmsContentLanguage> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",sql);
        return list;
    }
    public  List<CmsContentLanguage> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCondition",sql);
        return list;
    }
    public  List<CmsContentLanguage> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> list = null;
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
    public  List<CmsContentLanguage> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsContentLanguage> list = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsContentLanguage.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsContentLanguage entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsContentLanguage entity)throws IOException    
    {
        int rows = sqlSession.update("CmsContentLanguage.updateCmsContentLanguage",entity);
        return rows;
    }
    public  CmsContentLanguage createEntity()    
    {
        return new CmsContentLanguage();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsContentLanguage();
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
        List<CmsContentLanguage> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByThumbFileId(Integer thumbFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByThumbFileId(sqlSession,thumbFileId);
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
    public  int deleteByAttachmentFileId(Integer attachmentFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByAttachmentFileId(sqlSession,attachmentFileId);
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
    public  int deleteByVideoFileId(Integer videoFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByVideoFileId(sqlSession,videoFileId);
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
    public  int deleteByCmsContentId(Integer cmsContentId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsContentId(sqlSession,cmsContentId);
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
    public  int deleteByCmsLanguageCode(String cmsLanguageCode)throws Exception    
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
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int deleteByThumbFileId(SqlSession sqlSession,Integer thumbFileId)    
    {
        List<CmsContentLanguage> list = getListByThumbFileId(sqlSession,thumbFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByAttachmentFileId(SqlSession sqlSession,Integer attachmentFileId)    
    {
        List<CmsContentLanguage> list = getListByAttachmentFileId(sqlSession,attachmentFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByVideoFileId(SqlSession sqlSession,Integer videoFileId)    
    {
        List<CmsContentLanguage> list = getListByVideoFileId(sqlSession,videoFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsContentId(SqlSession sqlSession,Integer cmsContentId)    
    {
        List<CmsContentLanguage> list = getListByCmsContentId(sqlSession,cmsContentId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsLanguageCode(SqlSession sqlSession,String cmsLanguageCode)    
    {
        List<CmsContentLanguage> list = getListByCmsLanguageCode(sqlSession,cmsLanguageCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<CmsContentLanguage> getListByThumbFileId(Integer thumbFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> entitys = null;
         try        
        {
            entitys = getListByThumbFileId(sqlSession,thumbFileId);
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
    public  List<CmsContentLanguage> getListByAttachmentFileId(Integer attachmentFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> entitys = null;
         try        
        {
            entitys = getListByAttachmentFileId(sqlSession,attachmentFileId);
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
    public  List<CmsContentLanguage> getListByVideoFileId(Integer videoFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> entitys = null;
         try        
        {
            entitys = getListByVideoFileId(sqlSession,videoFileId);
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
    public  List<CmsContentLanguage> getListByCmsContentId(Integer cmsContentId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> entitys = null;
         try        
        {
            entitys = getListByCmsContentId(sqlSession,cmsContentId);
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
    public  List<CmsContentLanguage> getListByCmsLanguageCode(String cmsLanguageCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentLanguage> entitys = null;
         try        
        {
            entitys = getListByCmsLanguageCode(sqlSession,cmsLanguageCode);
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
    public  List<CmsContentLanguage> getListByThumbFileId(SqlSession sqlSession,Integer thumbFileId)    
    {
        List<CmsContentLanguage> entitys = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByThumbFileId",thumbFileId);
        return entitys;
    }
    public  List<CmsContentLanguage> getListByAttachmentFileId(SqlSession sqlSession,Integer attachmentFileId)    
    {
        List<CmsContentLanguage> entitys = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByAttachmentFileId",attachmentFileId);
        return entitys;
    }
    public  List<CmsContentLanguage> getListByVideoFileId(SqlSession sqlSession,Integer videoFileId)    
    {
        List<CmsContentLanguage> entitys = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByVideoFileId",videoFileId);
        return entitys;
    }
    public  List<CmsContentLanguage> getListByCmsContentId(SqlSession sqlSession,Integer cmsContentId)    
    {
        List<CmsContentLanguage> entitys = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCmsContentId",cmsContentId);
        return entitys;
    }
    public  List<CmsContentLanguage> getListByCmsLanguageCode(SqlSession sqlSession,String cmsLanguageCode)    
    {
        List<CmsContentLanguage> entitys = sqlSession.selectList("CmsContentLanguage.selectCmsContentLanguageByCmsLanguageCode",cmsLanguageCode);
        return entitys;
    }
    public  CmsContentLanguage getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentLanguage entity = null;
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
    public  CmsContentLanguage getEntityById(SqlSession sqlSession,Integer id)    
    {
        CmsContentLanguage entity = sqlSession.selectOne("selectCmsContentLanguageByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("CmsContentLanguage.selectIntBySql","select count(*) from cms_content_language where id= '"+id+"'");
        return rows>0?true:false;
    }
}