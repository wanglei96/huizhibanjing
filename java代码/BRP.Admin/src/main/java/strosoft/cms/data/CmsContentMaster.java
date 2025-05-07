package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.data.CmsContent;
import java.io.IOException;
import strosoft.cms.model.CmsContentLanguageManager;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class CmsContentMaster extends IdDataManager 
{
    public  int add(CmsContent entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsContent entity)    
    {
        int rows = sqlSession.insert("CmsContent.insertCmsContent",entity);
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
        return add(sqlSession,(CmsContent)dataEntity);
    }
    public  int addList(List<CmsContent> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsContent> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsContent.insertCmsContents",list);
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
        CmsContentLanguageManager.getInstance().deleteByCmsContentId(sqlSession,((CmsContent)entity).getId());
        int rows = sqlSession.delete("CmsContent.deleteCmsContent",entity);
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
    public  CmsContent getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContent newEntity = null;
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
    public  CmsContent getEntity(SqlSession sqlSession,Integer id)    
    {
        CmsContent entity = createEntity();
        entity.setId(id);
        CmsContent newEntity = sqlSession.selectOne("CmsContent.selectCmsContent",entity);
        return newEntity;
    }
    public  List<CmsContent> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession)    
    {
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContents");
        return list;
    }
    public  List<CmsContent> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentByCondition",condition);
        return list;
    }
    public  List<CmsContent> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsContent> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_content limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentBySql", sql);
        return list;
    }
    public  List<CmsContent> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentByCondition",sql);
        return list;
    }
    public  List<CmsContent> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentByCondition",sql);
        return list;
    }
    public  List<CmsContent> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> list = null;
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
    public  List<CmsContent> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsContent> list = sqlSession.selectList("CmsContent.selectCmsContentBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsContent.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsContent entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsContent entity)throws IOException    
    {
        int rows = sqlSession.update("CmsContent.updateCmsContent",entity);
        return rows;
    }
    public  CmsContent createEntity()    
    {
        return new CmsContent();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsContent();
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
        List<CmsContent> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
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
    public  int deleteByCmsChannelId(Integer cmsChannelId)throws Exception    
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
            throw e;
        }
         finally         
        {
            sqlSession.close();
        }
        return rows;
    }
    public  int deleteByCmsContentStatusCode(String cmsContentStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsContentStatusCode(sqlSession,cmsContentStatusCode);
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
    public  int deleteByAttachmentFileId(SqlSession sqlSession,Integer attachmentFileId)    
    {
        List<CmsContent> list = getListByAttachmentFileId(sqlSession,attachmentFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<CmsContent> list = getListByCmsChannelId(sqlSession,cmsChannelId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsContentStatusCode(SqlSession sqlSession,String cmsContentStatusCode)    
    {
        List<CmsContent> list = getListByCmsContentStatusCode(sqlSession,cmsContentStatusCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsContent> list = getListByImageFileId(sqlSession,imageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByThumbFileId(SqlSession sqlSession,Integer thumbFileId)    
    {
        List<CmsContent> list = getListByThumbFileId(sqlSession,thumbFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByVideoFileId(SqlSession sqlSession,Integer videoFileId)    
    {
        List<CmsContent> list = getListByVideoFileId(sqlSession,videoFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<CmsContent> getListByAttachmentFileId(Integer attachmentFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
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
    public  List<CmsContent> getListByCmsChannelId(Integer cmsChannelId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
         try        
        {
            entitys = getListByCmsChannelId(sqlSession,cmsChannelId);
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
    public  List<CmsContent> getListByCmsContentStatusCode(String cmsContentStatusCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
         try        
        {
            entitys = getListByCmsContentStatusCode(sqlSession,cmsContentStatusCode);
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
    public  List<CmsContent> getListByImageFileId(Integer imageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
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
    public  List<CmsContent> getListByThumbFileId(Integer thumbFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
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
    public  List<CmsContent> getListByVideoFileId(Integer videoFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContent> entitys = null;
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
    public  List<CmsContent> getListByAttachmentFileId(SqlSession sqlSession,Integer attachmentFileId)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByAttachmentFileId",attachmentFileId);
        return entitys;
    }
    public  List<CmsContent> getListByCmsChannelId(SqlSession sqlSession,Integer cmsChannelId)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByCmsChannelId",cmsChannelId);
        return entitys;
    }
    public  List<CmsContent> getListByCmsContentStatusCode(SqlSession sqlSession,String cmsContentStatusCode)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByCmsContentStatusCode",cmsContentStatusCode);
        return entitys;
    }
    public  List<CmsContent> getListByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByImageFileId",imageFileId);
        return entitys;
    }
    public  List<CmsContent> getListByThumbFileId(SqlSession sqlSession,Integer thumbFileId)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByThumbFileId",thumbFileId);
        return entitys;
    }
    public  List<CmsContent> getListByVideoFileId(SqlSession sqlSession,Integer videoFileId)    
    {
        List<CmsContent> entitys = sqlSession.selectList("CmsContent.selectCmsContentByVideoFileId",videoFileId);
        return entitys;
    }
    public  CmsContent getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContent entity = null;
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
    public  CmsContent getEntityById(SqlSession sqlSession,Integer id)    
    {
        CmsContent entity = sqlSession.selectOne("selectCmsContentByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("CmsContent.selectIntBySql","select count(*) from cms_content where id= '"+id+"'");
        return rows>0?true:false;
    }
}