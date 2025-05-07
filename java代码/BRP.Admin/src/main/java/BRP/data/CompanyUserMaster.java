package BRP.data;
import org.apache.ibatis.session.SqlSession;
import BRP.model.BookCompensationManager;
import BRP.model.DeliverLineManager;
import BRP.data.CompanyUser;
import strosoft.app.common.MyBatisManager;
import java.util.*;
import BRP.model.MemberManager;
import BRP.model.MemberCommunicaLogManager;
import BRP.model.BookManager;
import BRP.model.BookSkuReviseManager;
import BRP.model.RefundOrderManager;
import java.io.IOException;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import BRP.model.BookDamageManager;
import BRP.model.SalesOrderManager;
import BRP.model.BookOrderManager;
import BRP.model.MemberCardManager;
import java.math.*;
public class CompanyUserMaster extends IdDataManager 
{
    public  int add(CompanyUser entity)throws Exception    
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
    public  int add(SqlSession sqlSession,CompanyUser entity)    
    {
        int rows = sqlSession.insert("CompanyUser.insertCompanyUser",entity);
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
        return add(sqlSession,(CompanyUser)dataEntity);
    }
    public  int addList(List<CompanyUser> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<CompanyUser> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("CompanyUser.insertCompanyUsers",list);
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
        BookManager.getInstance().deleteByOfflineApproverId(sqlSession,((CompanyUser)entity).getId());
        BookCompensationManager.getInstance().deleteByCompanyUserId(sqlSession,((CompanyUser)entity).getId());
        BookDamageManager.getInstance().deleteByCompanyUserId(sqlSession,((CompanyUser)entity).getId());
        BookOrderManager.getInstance().deleteByBookpickerId(sqlSession,((CompanyUser)entity).getId());
        BookOrderManager.getInstance().deleteByDelivererId(sqlSession,((CompanyUser)entity).getId());
        BookSkuReviseManager.getInstance().deleteByCompanyUserId(sqlSession,((CompanyUser)entity).getId());
        DeliverLineManager.getInstance().deleteByDelivererId(sqlSession,((CompanyUser)entity).getId());
        MemberManager.getInstance().deleteByDelivererId(sqlSession,((CompanyUser)entity).getId());
        MemberCardManager.getInstance().deleteByEmployeeId(sqlSession,((CompanyUser)entity).getId());
        MemberCommunicaLogManager.getInstance().deleteByCompanyUserId(sqlSession,((CompanyUser)entity).getId());
        RefundOrderManager.getInstance().deleteByOperatorId(sqlSession,((CompanyUser)entity).getId());
        RefundOrderManager.getInstance().deleteByRefunderId(sqlSession,((CompanyUser)entity).getId());
        SalesOrderManager.getInstance().deleteByCompanyUserId(sqlSession,((CompanyUser)entity).getId());
        int rows = sqlSession.delete("CompanyUser.deleteCompanyUser",entity);
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
    public  CompanyUser getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUser newEntity = null;
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
    public  CompanyUser getEntity(SqlSession sqlSession,Integer id)    
    {
        CompanyUser entity = createEntity();
        entity.setId(id);
        CompanyUser newEntity = sqlSession.selectOne("CompanyUser.selectCompanyUser",entity);
        return newEntity;
    }
    public  List<CompanyUser> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession)    
    {
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUsers");
        return list;
    }
    public  List<CompanyUser> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession,String condition)    
    {
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserByCondition",condition);
        return list;
    }
    public  List<CompanyUser> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<CompanyUser> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from company_user limit %s, %s", pageSize * pageIndex, pageSize);
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserBySql", sql);
        return list;
    }
    public  List<CompanyUser> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserByCondition",sql);
        return list;
    }
    public  List<CompanyUser> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserByCondition",sql);
        return list;
    }
    public  List<CompanyUser> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> list = null;
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
    public  List<CompanyUser> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<CompanyUser> list = sqlSession.selectList("CompanyUser.selectCompanyUserBySql",sql);
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
        Integer rows = sqlSession.selectOne("CompanyUser.selectCountByCondition",condition);
        return rows;
    }
    public  int update(CompanyUser entity)throws Exception    
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
    public  int update(SqlSession sqlSession,CompanyUser entity)throws IOException    
    {
        int rows = sqlSession.update("CompanyUser.updateCompanyUser",entity);
        return rows;
    }
    public  CompanyUser createEntity()    
    {
        return new CompanyUser();
    }
    public  DataEntity createDataEntity()    
    {
        return new CompanyUser();
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
        List<CompanyUser> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
        }
        return rows;
    }
    public  int deleteByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCompanyId(sqlSession,companyId);
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
    public  int deleteByCompanyOrganizationId(Integer companyOrganizationId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByCompanyOrganizationId(sqlSession,companyOrganizationId);
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
    public  int deleteByDeliverAreaId(Integer deliverAreaId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByDeliverAreaId(sqlSession,deliverAreaId);
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
    public  int deleteByLicenseImageFileId(Integer licenseImageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try        
        {
            rows = deleteByLicenseImageFileId(sqlSession,licenseImageFileId);
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
    public  int deleteByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<CompanyUser> list = getListByCompanyId(sqlSession,companyId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByCompanyOrganizationId(SqlSession sqlSession,Integer companyOrganizationId)    
    {
        List<CompanyUser> list = getListByCompanyOrganizationId(sqlSession,companyOrganizationId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByDeliverAreaId(SqlSession sqlSession,Integer deliverAreaId)    
    {
        List<CompanyUser> list = getListByDeliverAreaId(sqlSession,deliverAreaId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  int deleteByLicenseImageFileId(SqlSession sqlSession,Integer licenseImageFileId)    
    {
        List<CompanyUser> list = getListByLicenseImageFileId(sqlSession,licenseImageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<CompanyUser> getListByCompanyId(Integer companyId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> entitys = null;
         try        
        {
            entitys = getListByCompanyId(sqlSession,companyId);
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
    public  List<CompanyUser> getListByCompanyOrganizationId(Integer companyOrganizationId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> entitys = null;
         try        
        {
            entitys = getListByCompanyOrganizationId(sqlSession,companyOrganizationId);
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
    public  List<CompanyUser> getListByDeliverAreaId(Integer deliverAreaId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> entitys = null;
         try        
        {
            entitys = getListByDeliverAreaId(sqlSession,deliverAreaId);
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
    public  List<CompanyUser> getListByLicenseImageFileId(Integer licenseImageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<CompanyUser> entitys = null;
         try        
        {
            entitys = getListByLicenseImageFileId(sqlSession,licenseImageFileId);
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
    public  List<CompanyUser> getListByCompanyId(SqlSession sqlSession,Integer companyId)    
    {
        List<CompanyUser> entitys = sqlSession.selectList("CompanyUser.selectCompanyUserByCompanyId",companyId);
        return entitys;
    }
    public  List<CompanyUser> getListByCompanyOrganizationId(SqlSession sqlSession,Integer companyOrganizationId)    
    {
        List<CompanyUser> entitys = sqlSession.selectList("CompanyUser.selectCompanyUserByCompanyOrganizationId",companyOrganizationId);
        return entitys;
    }
    public  List<CompanyUser> getListByDeliverAreaId(SqlSession sqlSession,Integer deliverAreaId)    
    {
        List<CompanyUser> entitys = sqlSession.selectList("CompanyUser.selectCompanyUserByDeliverAreaId",deliverAreaId);
        return entitys;
    }
    public  List<CompanyUser> getListByLicenseImageFileId(SqlSession sqlSession,Integer licenseImageFileId)    
    {
        List<CompanyUser> entitys = sqlSession.selectList("CompanyUser.selectCompanyUserByLicenseImageFileId",licenseImageFileId);
        return entitys;
    }
    public  CompanyUser getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUser entity = null;
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
    public  CompanyUser getEntityByMobilePhone(String mobilePhone)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        CompanyUser entity = null;
        try        
        {
             entity = getEntityByMobilePhone(sqlSession,mobilePhone);
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
    public  CompanyUser getEntityById(SqlSession sqlSession,Integer id)    
    {
        CompanyUser entity = sqlSession.selectOne("selectCompanyUserByCondition","id='"+id+"'");
        return entity;
    }
    public  CompanyUser getEntityByMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        CompanyUser entity = sqlSession.selectOne("selectCompanyUserByCondition","mobile_phone='"+mobilePhone+"'");
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
    public  boolean existsMobilePhone(String mobilePhone)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsMobilePhone(sqlSession,mobilePhone);
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
        Integer rows = sqlSession.selectOne("CompanyUser.selectIntBySql","select count(*) from company_user where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        Integer rows = sqlSession.selectOne("CompanyUser.selectIntBySql","select count(*) from company_user where mobile_phone= '"+mobilePhone+"'");
        return rows>0?true:false;
    }
}