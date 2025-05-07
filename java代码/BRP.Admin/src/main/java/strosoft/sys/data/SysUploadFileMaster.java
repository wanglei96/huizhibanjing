package strosoft.sys.data;
import BRP.model.ClubActivityManager;
import org.apache.ibatis.session.SqlSession;
import BRP.model.ReadActivityManager;
import strosoft.sys.model.SysMenuManager;
import BRP.model.BookSkuManager;
import strosoft.cms.model.CmsChannelManager;
import BRP.model.CompanyManager;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.model.CompanyUserManager;
import strosoft.cms.model.CmsChannelLanguageManager;
import strosoft.cms.model.CmsContentManager;
import BRP.model.StoryVideoManager;
import java.io.IOException;
import strosoft.sys.data.SysUploadFile;
import strosoft.cms.model.CmsContentLanguageManager;
import BRP.model.CompanyContentManager;
import BRP.model.CompanyImageManager;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import java.math.*;
public class SysUploadFileMaster extends IdDataManager 
{
    public  int add(SysUploadFile entity)throws Exception    
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
    public  int add(SqlSession sqlSession,SysUploadFile entity)    
    {
        int rows = sqlSession.insert("SysUploadFile.insertSysUploadFile",entity);
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
        return add(sqlSession,(SysUploadFile)dataEntity);
    }
    public  int addList(List<SysUploadFile> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<SysUploadFile> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("SysUploadFile.insertSysUploadFiles",list);
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
        BookSkuManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        ClubActivityManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsChannelManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsChannelLanguageManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentManager.getInstance().deleteByAttachmentFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentManager.getInstance().deleteByThumbFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentManager.getInstance().deleteByVideoFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentLanguageManager.getInstance().deleteByThumbFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentLanguageManager.getInstance().deleteByAttachmentFileId(sqlSession,((SysUploadFile)entity).getId());
        CmsContentLanguageManager.getInstance().deleteByVideoFileId(sqlSession,((SysUploadFile)entity).getId());
        CompanyManager.getInstance().deleteByLicenseImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CompanyContentManager.getInstance().deleteByAttachmentFileId(sqlSession,((SysUploadFile)entity).getId());
        CompanyContentManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CompanyImageManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        CompanyUserManager.getInstance().deleteByLicenseImageFileId(sqlSession,((SysUploadFile)entity).getId());
        ReadActivityManager.getInstance().deleteByImageFileId(sqlSession,((SysUploadFile)entity).getId());
        StoryVideoManager.getInstance().deleteByCoverImageFileId(sqlSession,((SysUploadFile)entity).getId());
        StoryVideoManager.getInstance().deleteByStoryVideoFileId(sqlSession,((SysUploadFile)entity).getId());
        SysMenuManager.getInstance().deleteByActivationPictureId(sqlSession,((SysUploadFile)entity).getId());
        SysMenuManager.getInstance().deleteByPictureId(sqlSession,((SysUploadFile)entity).getId());
        int rows = sqlSession.delete("SysUploadFile.deleteSysUploadFile",entity);
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
    public  SysUploadFile getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysUploadFile newEntity = null;
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
    public  SysUploadFile getEntity(SqlSession sqlSession,Integer id)    
    {
        SysUploadFile entity = createEntity();
        entity.setId(id);
        SysUploadFile newEntity = sqlSession.selectOne("SysUploadFile.selectSysUploadFile",entity);
        return newEntity;
    }
    public  List<SysUploadFile> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession)    
    {
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFiles");
        return list;
    }
    public  List<SysUploadFile> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession,String condition)    
    {
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileByCondition",condition);
        return list;
    }
    public  List<SysUploadFile> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<SysUploadFile> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from sys_upload_file limit %s, %s", pageSize * pageIndex, pageSize);
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileBySql", sql);
        return list;
    }
    public  List<SysUploadFile> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileByCondition",sql);
        return list;
    }
    public  List<SysUploadFile> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileByCondition",sql);
        return list;
    }
    public  List<SysUploadFile> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysUploadFile> list = null;
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
    public  List<SysUploadFile> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<SysUploadFile> list = sqlSession.selectList("SysUploadFile.selectSysUploadFileBySql",sql);
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
        Integer rows = sqlSession.selectOne("SysUploadFile.selectCountByCondition",condition);
        return rows;
    }
    public  int update(SysUploadFile entity)throws Exception    
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
    public  int update(SqlSession sqlSession,SysUploadFile entity)throws IOException    
    {
        int rows = sqlSession.update("SysUploadFile.updateSysUploadFile",entity);
        return rows;
    }
    public  SysUploadFile createEntity()    
    {
        return new SysUploadFile();
    }
    public  DataEntity createDataEntity()    
    {
        return new SysUploadFile();
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
        List<SysUploadFile> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  SysUploadFile getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysUploadFile entity = null;
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
    public  SysUploadFile getEntityById(SqlSession sqlSession,Integer id)    
    {
        SysUploadFile entity = sqlSession.selectOne("selectSysUploadFileByCondition","id='"+id+"'");
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
        Integer rows = sqlSession.selectOne("SysUploadFile.selectIntBySql","select count(*) from sys_upload_file where id= '"+id+"'");
        return rows>0?true:false;
    }
}