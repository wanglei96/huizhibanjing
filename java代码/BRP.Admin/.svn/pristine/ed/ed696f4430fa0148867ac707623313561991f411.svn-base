package strosoft.cms.data;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import strosoft.app.common.TableDataMaster;
import java.io.IOException;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.CmsContentTag;
import java.math.*;
public class CmsContentTagMaster extends TableDataMaster 
{
    public  int add(CmsContentTag entity)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = add(sqlSession,entity);
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
    public  int add(SqlSession sqlSession,CmsContentTag entity)    
    {
        int rows = sqlSession.insert("CmsContentTag.insertCmsContentTag",entity);
        return rows;
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
        return add(sqlSession,(CmsContentTag)dataEntity);
    }
    public  int addList(List<CmsContentTag> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CmsContentTag> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CmsContentTag.insertCmsContentTags",list);
        return rows;
    }
    public  int delete(Integer cmsContentId,String tag)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = delete(sqlSession,cmsContentId,tag);
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
    public  int delete(SqlSession sqlSession,Integer cmsContentId,String tag)    
    {
        int rows = delete(sqlSession,getEntity(sqlSession,cmsContentId,tag));
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
        int rows = sqlSession.delete("CmsContentTag.deleteCmsContentTag",entity);
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
    public  DataEntity getDataEntity(Integer cmsContentId,String tag)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try        
        {
            newDataEntity = getDataEntity(sqlSession,cmsContentId,tag);
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
    public  DataEntity getDataEntity(SqlSession sqlSession,Integer cmsContentId,String tag)    
    {
        DataEntity newDataEntity = getEntity(sqlSession,cmsContentId,tag);
         return newDataEntity;
    }
    public  CmsContentTag getEntity(Integer cmsContentId,String tag)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentTag newEntity = null;
        try        
        {
            newEntity = getEntity(sqlSession,cmsContentId,tag);
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
    public  CmsContentTag getEntity(SqlSession sqlSession,Integer cmsContentId,String tag)    
    {
        CmsContentTag entity = createEntity();
        entity.setCmsContentId(cmsContentId);
        entity.setTag(tag);
        CmsContentTag newEntity = sqlSession.selectOne("CmsContentTag.selectCmsContentTag",entity);
        return newEntity;
    }
    public  List<CmsContentTag> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession)    
    {
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTags");
        return list;
    }
    public  List<CmsContentTag> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession,String condition)    
    {
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagByCondition",condition);
        return list;
    }
    public  List<CmsContentTag> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CmsContentTag> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from cms_content_tag limit %s, %s", pageSize * pageIndex, pageSize);
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagBySql", sql);
        return list;
    }
    public  List<CmsContentTag> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagByCondition",sql);
        return list;
    }
    public  List<CmsContentTag> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getList(SqlSession sqlSession,String condition,String orderBy,int pagesize,int pageindex)    
    {
        String sql = condition +" order by "+orderBy+" limit "+pagesize*(pageindex-1)+","+pagesize;
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagByCondition",sql);
        return list;
    }
    public  List<CmsContentTag> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CmsContentTag> list = null;
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
    public  List<CmsContentTag> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CmsContentTag> list = sqlSession.selectList("CmsContentTag.selectCmsContentTagBySql",sql);
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
        Integer rows = sqlSession.selectOne("CmsContentTag.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CmsContentTag entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CmsContentTag entity)throws IOException    
    {
        int rows = sqlSession.update("CmsContentTag.updateCmsContentTag",entity);
        return rows;
    }
    public  CmsContentTag createEntity()    
    {
        return new CmsContentTag();
    }
    public  DataEntity createDataEntity()    
    {
        return new CmsContentTag();
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
        List<CmsContentTag> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  CmsContentTag getEntityByCmsContentId(Integer cmsContentId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentTag entity = null;
        try        
        {
             entity = getEntityByCmsContentId(sqlSession,cmsContentId);
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
    public  CmsContentTag getEntityByTag(String tag)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CmsContentTag entity = null;
        try        
        {
             entity = getEntityByTag(sqlSession,tag);
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
    public  CmsContentTag getEntityByCmsContentId(SqlSession sqlSession,Integer cmsContentId)    
    {
        CmsContentTag entity = sqlSession.selectOne("selectCmsContentTagByCondition","cms_content_id='"+cmsContentId+"'");
        return entity;
    }
    public  CmsContentTag getEntityByTag(SqlSession sqlSession,String tag)    
    {
        CmsContentTag entity = sqlSession.selectOne("selectCmsContentTagByCondition","tag='"+tag+"'");
        return entity;
    }
    public  boolean existsCmsContentId(Integer cmsContentId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCmsContentId(sqlSession,cmsContentId);
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
    public  boolean existsTag(String tag)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsTag(sqlSession,tag);
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
    public  boolean existsCmsContentId(SqlSession sqlSession,Integer cmsContentId)    
    {
        Integer rows = sqlSession.selectOne("CmsContentTag.selectIntBySql","select count(*) from cms_content_tag where cms_content_id= '"+cmsContentId+"'");
        return rows>0?true:false;
    }
    public  boolean existsTag(SqlSession sqlSession,String tag)    
    {
        Integer rows = sqlSession.selectOne("CmsContentTag.selectIntBySql","select count(*) from cms_content_tag where tag= '"+tag+"'");
        return rows>0?true:false;
    }
}