package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.CmsChannelChannelGroupManager;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;
import java.io.IOException;
import strosoft.cms.data.CmsChannel;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class CmsChannelMaster extends IdDataManager 
{
    public  int add(CmsChannel entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CmsChannel entity)    
    {
        int rows = sqlSession.insert("CmsChannel.insertCmsChannel",entity);
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
        return add(sqlSession,(CmsChannel)dataEntity);
    }
    public  int addList(List<CmsChannel> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsChannel> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsChannel.insertCmsChannels",list);
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
        CmsChannelChannelGroupManager.getInstance().deleteByCmsChannelId(sqlSession,((CmsChannel)entity).getId());
        CmsChannelLanguageManager.getInstance().deleteByCmsChannelId(sqlSession,((CmsChannel)entity).getId());
        CmsContentManager.getInstance().deleteByCmsChannelId(sqlSession,((CmsChannel)entity).getId());
        SysRoleCmsChannelPermissionManager.getInstance().deleteByCmsChannelId(sqlSession,((CmsChannel)entity).getId());
        int rows = sqlSession.delete("CmsChannel.deleteCmsChannel",entity);
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
    public  CmsChannel getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsChannel newEntity = null;
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
    public  CmsChannel getEntity(SqlSession sqlSession,Integer id)    
    {
        CmsChannel entity = createEntity();
        entity.setId(id);
        CmsChannel newEntity = sqlSession.selectOne("CmsChannel.selectCmsChannel",entity);
        return newEntity;
    }
    public  List<CmsChannel> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession)    
    {
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannels");
        return list;
    }
    public  List<CmsChannel> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelByCondition",condition);
        return list;
    }
    public  List<CmsChannel> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsChannel> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_channel limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelBySql", sql);
        return list;
    }
    public  List<CmsChannel> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelByCondition",sql);
        return list;
    }
    public  List<CmsChannel> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelByCondition",sql);
        return list;
    }
    public  List<CmsChannel> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> list = null;
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
    public  List<CmsChannel> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsChannel> list = sqlSession.selectList("CmsChannel.selectCmsChannelBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsChannel.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsChannel entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsChannel entity)throws IOException    
    {
        int rows = sqlSession.update("CmsChannel.updateCmsChannel",entity);
        return rows;
    }
    public  CmsChannel createEntity()    
    {
        return new CmsChannel();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsChannel();
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
        List<CmsChannel> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByCmsChannelTemplateId(Integer cmsChannelTemplateId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsChannelTemplateId(sqlSession,cmsChannelTemplateId);
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
    public  int deleteByCmsContentTemplateId(Integer cmsContentTemplateId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsContentTemplateId(sqlSession,cmsContentTemplateId);
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
    public  int deleteByCmsLinkTypeCode(String cmsLinkTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsLinkTypeCode(sqlSession,cmsLinkTypeCode);
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
    public  int deleteByCmsSiteId(Integer cmsSiteId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCmsSiteId(sqlSession,cmsSiteId);
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
    public  int deleteByCmsChannelTemplateId(SqlSession sqlSession,Integer cmsChannelTemplateId)    
    {
        List<CmsChannel> list = getListByCmsChannelTemplateId(sqlSession,cmsChannelTemplateId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsContentTemplateId(SqlSession sqlSession,Integer cmsContentTemplateId)    
    {
        List<CmsChannel> list = getListByCmsContentTemplateId(sqlSession,cmsContentTemplateId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsLinkTypeCode(SqlSession sqlSession,String cmsLinkTypeCode)    
    {
        List<CmsChannel> list = getListByCmsLinkTypeCode(sqlSession,cmsLinkTypeCode);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCmsSiteId(SqlSession sqlSession,Integer cmsSiteId)    
    {
        List<CmsChannel> list = getListByCmsSiteId(sqlSession,cmsSiteId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsChannel> list = getListByImageFileId(sqlSession,imageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<CmsChannel> getListByCmsChannelTemplateId(Integer cmsChannelTemplateId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> entitys = null;
         try        
        {
            entitys = getListByCmsChannelTemplateId(sqlSession,cmsChannelTemplateId);
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
    public  List<CmsChannel> getListByCmsContentTemplateId(Integer cmsContentTemplateId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> entitys = null;
         try        
        {
            entitys = getListByCmsContentTemplateId(sqlSession,cmsContentTemplateId);
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
    public  List<CmsChannel> getListByCmsLinkTypeCode(String cmsLinkTypeCode)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> entitys = null;
         try        
        {
            entitys = getListByCmsLinkTypeCode(sqlSession,cmsLinkTypeCode);
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
    public  List<CmsChannel> getListByCmsSiteId(Integer cmsSiteId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> entitys = null;
         try        
        {
            entitys = getListByCmsSiteId(sqlSession,cmsSiteId);
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
    public  List<CmsChannel> getListByImageFileId(Integer imageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsChannel> entitys = null;
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
    public  List<CmsChannel> getListByCmsChannelTemplateId(SqlSession sqlSession,Integer cmsChannelTemplateId)    
    {
        List<CmsChannel> entitys = sqlSession.selectList("CmsChannel.selectCmsChannelByCmsChannelTemplateId",cmsChannelTemplateId);
        return entitys;
    }
    public  List<CmsChannel> getListByCmsContentTemplateId(SqlSession sqlSession,Integer cmsContentTemplateId)    
    {
        List<CmsChannel> entitys = sqlSession.selectList("CmsChannel.selectCmsChannelByCmsContentTemplateId",cmsContentTemplateId);
        return entitys;
    }
    public  List<CmsChannel> getListByCmsLinkTypeCode(SqlSession sqlSession,String cmsLinkTypeCode)    
    {
        List<CmsChannel> entitys = sqlSession.selectList("CmsChannel.selectCmsChannelByCmsLinkTypeCode",cmsLinkTypeCode);
        return entitys;
    }
    public  List<CmsChannel> getListByCmsSiteId(SqlSession sqlSession,Integer cmsSiteId)    
    {
        List<CmsChannel> entitys = sqlSession.selectList("CmsChannel.selectCmsChannelByCmsSiteId",cmsSiteId);
        return entitys;
    }
    public  List<CmsChannel> getListByImageFileId(SqlSession sqlSession,Integer imageFileId)    
    {
        List<CmsChannel> entitys = sqlSession.selectList("CmsChannel.selectCmsChannelByImageFileId",imageFileId);
        return entitys;
    }
    public  CmsChannel getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsChannel entity = null;
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
    public  CmsChannel getEntityById(SqlSession sqlSession,Integer id)    
    {
        CmsChannel entity = sqlSession.selectOne("selectCmsChannelByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("CmsChannel.selectIntBySql","select count(*) from cms_channel where id= '"+id+"'");
        return rows>0?true:false;
    }
}