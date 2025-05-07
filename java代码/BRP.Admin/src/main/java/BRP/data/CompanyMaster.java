package BRP.data;
import BRP.model.ClubActivityManager;
import BRP.model.CompanyProductStocksManager;
import BRP.model.HomeNewBookManager;
import org.apache.ibatis.session.SqlSession;
import BRP.model.ActivityServiceTimeManager;
import BRP.model.HomeRecommendManager;
import BRP.model.BookSkuManager;
import BRP.model.HomeRandomBookManager;
import BRP.model.BookTagManager;
import strosoft.app.common.MyBatisManager;
import BRP.model.ActivityManager;
import BRP.model.BookManager;
import java.io.IOException;
import BRP.model.CompanyImageManager;
import strosoft.app.common.IdDataManager;
import strosoft.app.data.DataEntity;
import BRP.model.BookDamageManager;
import BRP.model.RetailOrderManager;
import BRP.model.SalesOrderManager;
import BRP.model.BookOrderManager;
import BRP.model.RequiredBookCategoryManager;
import BRP.model.MemberCardTypeManager;
import BRP.model.CompanyRoleManager;
import BRP.model.DeliverAreaManager;
import BRP.model.HomeNavigationManager;
import BRP.model.ActivityRecommendManager;
import BRP.model.CompanySettlementManager;
import BRP.model.CompanyChannelManager;
import java.util.*;
import BRP.model.CompanyUserManager;
import BRP.model.MemberCardBatchManager;
import BRP.model.CompanySettingManager;
import BRP.model.MemberManager;
import BRP.data.Company;
import BRP.model.BookcaseManager;
import BRP.model.RefundOrderManager;
import BRP.model.SettlementOrderManager;
import BRP.model.MemberCardManager;
import java.math.*;
public class CompanyMaster extends IdDataManager 
{
    public  int add(Company entity)throws Exception    
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
    public  int add(SqlSession sqlSession,Company entity)    
    {
        int rows = sqlSession.insert("Company.insertCompany",entity);
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
        return add(sqlSession,(Company)dataEntity);
    }
    public  int addList(List<Company> list)throws Exception    
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
    public  int addList(SqlSession sqlSession,List<Company> list)    
    {
        if(null == list || list.size() == 0)        
        {
            return 0;
        }
        int rows = sqlSession.insert("Company.insertCompanys",list);
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
        ActivityManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        ActivityRecommendManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        ActivityServiceTimeManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookDamageManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookOrderManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookSkuManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookTagManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        BookcaseManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        ClubActivityManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanyChannelManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanyImageManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanyProductStocksManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanyRoleManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanySettingManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanySettlementManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        CompanyUserManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        DeliverAreaManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        HomeNavigationManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        HomeNewBookManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        HomeRandomBookManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        HomeRecommendManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        MemberManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        MemberCardManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        MemberCardBatchManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        MemberCardTypeManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        RefundOrderManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        RequiredBookCategoryManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        RetailOrderManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        SalesOrderManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        SettlementOrderManager.getInstance().deleteByCompanyId(sqlSession,((Company)entity).getId());
        int rows = sqlSession.delete("Company.deleteCompany",entity);
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
    public  Company getEntity(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Company newEntity = null;
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
    public  Company getEntity(SqlSession sqlSession,Integer id)    
    {
        Company entity = createEntity();
        entity.setId(id);
        Company newEntity = sqlSession.selectOne("Company.selectCompany",entity);
        return newEntity;
    }
    public  List<Company> getList()throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession)    
    {
        List<Company> list = sqlSession.selectList("Company.selectCompanys");
        return list;
    }
    public  List<Company> getList(String condition)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession,String condition)    
    {
        List<Company> list = sqlSession.selectList("Company.selectCompanyByCondition",condition);
        return list;
    }
    public  List<Company> getList(String condition,String orderBy)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession,String condition,String orderBy)    
    {
        String conditionAndOrderBy = condition +" order by "+orderBy;
        List<Company> list = sqlSession.selectList("Company.selectCompanyByCondition",conditionAndOrderBy);
        return list;
    }
    public  List<Company> getList(int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession,int pageIndex,int pageSize)    
    {
        String sql = String.format("select * from company limit %s, %s", pageSize * pageIndex, pageSize);
        List<Company> list = sqlSession.selectList("Company.selectCompanyBySql", sql);
        return list;
    }
    public  List<Company> getListPage(String condition,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession,String condition,int pageIndex,int pageSize)    
    {
        String sql = condition + " limit " + pageSize * pageIndex + "," + pageSize;
        List<Company> list = sqlSession.selectList("Company.selectCompanyByCondition",sql);
        return list;
    }
    public  List<Company> getList(String condition,String orderBy,int pageIndex,int pageSize)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getList(SqlSession sqlSession,String condition,String orderBy,int pageIndex,int pageSize)    
    {
        String sql = condition +" order by " + orderBy + " limit " + pageSize * pageIndex + " , " + pageSize;
        List<Company> list = sqlSession.selectList("Company.selectCompanyByCondition",sql);
        return list;
    }
    public  List<Company> getListBySql(String sql)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> list = null;
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
    public  List<Company> getListBySql(SqlSession sqlSession,String sql)    
    {
        List<Company> list = sqlSession.selectList("Company.selectCompanyBySql",sql);
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
        Integer rows = sqlSession.selectOne("Company.selectCountByCondition",condition);
        return rows;
    }
    public  int update(Company entity)throws Exception    
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
    public  int update(SqlSession sqlSession,Company entity)throws IOException    
    {
        int rows = sqlSession.update("Company.updateCompany",entity);
        return rows;
    }
    public  Company createEntity()    
    {
        return new Company();
    }
    public  DataEntity createDataEntity()    
    {
        return new Company();
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
        List<Company> list = getList(sqlSession);
        for(int i=0;i<list.size();i++)        
        {
            rows += delete(sqlSession,list.get(i));
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
    public  int deleteByLicenseImageFileId(SqlSession sqlSession,Integer licenseImageFileId)    
    {
        List<Company> list = getListByLicenseImageFileId(sqlSession,licenseImageFileId);
        int rows = batchDeleteByEntity(sqlSession,list);
        return rows;
    }
    public  List<Company> getListByLicenseImageFileId(Integer licenseImageFileId)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<Company> entitys = null;
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
    public  List<Company> getListByLicenseImageFileId(SqlSession sqlSession,Integer licenseImageFileId)    
    {
        List<Company> entitys = sqlSession.selectList("Company.selectCompanyByLicenseImageFileId",licenseImageFileId);
        return entitys;
    }
    public  Company getEntityById(Integer id)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Company entity = null;
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
    public  Company getEntityByCompanyName(String companyName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Company entity = null;
        try        
        {
             entity = getEntityByCompanyName(sqlSession,companyName);
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
    public  Company getEntityByCompanyNo(String companyNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Company entity = null;
        try        
        {
             entity = getEntityByCompanyNo(sqlSession,companyNo);
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
    public  Company getEntityByMobilePhone(String mobilePhone)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Company entity = null;
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
    public  Company getEntityById(SqlSession sqlSession,Integer id)    
    {
        Company entity = sqlSession.selectOne("selectCompanyByCondition","id='"+id+"'");
        return entity;
    }
    public  Company getEntityByCompanyName(SqlSession sqlSession,String companyName)    
    {
        Company entity = sqlSession.selectOne("selectCompanyByCondition","company_name='"+companyName+"'");
        return entity;
    }
    public  Company getEntityByCompanyNo(SqlSession sqlSession,String companyNo)    
    {
        Company entity = sqlSession.selectOne("selectCompanyByCondition","company_no='"+companyNo+"'");
        return entity;
    }
    public  Company getEntityByMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        Company entity = sqlSession.selectOne("selectCompanyByCondition","mobile_phone='"+mobilePhone+"'");
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
    public  boolean existsCompanyName(String companyName)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCompanyName(sqlSession,companyName);
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
    public  boolean existsCompanyNo(String companyNo)throws Exception    
    {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try        
        {
            flag = existsCompanyNo(sqlSession,companyNo);
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
        Integer rows = sqlSession.selectOne("Company.selectIntBySql","select count(*) from company where id= '"+id+"'");
        return rows>0?true:false;
    }
    public  boolean existsCompanyName(SqlSession sqlSession,String companyName)    
    {
        Integer rows = sqlSession.selectOne("Company.selectIntBySql","select count(*) from company where company_name= '"+companyName+"'");
        return rows>0?true:false;
    }
    public  boolean existsCompanyNo(SqlSession sqlSession,String companyNo)    
    {
        Integer rows = sqlSession.selectOne("Company.selectIntBySql","select count(*) from company where company_no= '"+companyNo+"'");
        return rows>0?true:false;
    }
    public  boolean existsMobilePhone(SqlSession sqlSession,String mobilePhone)    
    {
        Integer rows = sqlSession.selectOne("Company.selectIntBySql","select count(*) from company where mobile_phone= '"+mobilePhone+"'");
        return rows>0?true:false;
    }
}