package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.cms.data.CmsInclude;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.data.CmsTemplateTag;
import strosoft.sys.data.SysBasicTable;

/*
 * 数据层
 */
public class CmsIncludeManager extends IdDataManager{
	private static CmsIncludeManager _instance;

	public static CmsIncludeManager getInstance() {
		if (_instance == null) {
			_instance = new CmsIncludeManager();
		}
		return _instance;
	}

	private CmsIncludeManager() {
		this.setTableName("cms_include");
		this.setTypeName("CmsInclude");
	}

	//单个删除
	public int deleteOne(SqlSession sqlSession,int id) throws Exception{
		//获取此包含文件
		CmsInclude entity = getEntity(sqlSession,id);
		String fileName = SysConfig.getRootPath()+entity.getPath();
		//删除包含文件
		int deleteId = super.delete(sqlSession, "cms_include", id);
		delete(fileName);
		return deleteId;
	}
	//批量删除
	public int batchDelete(List<Integer> idList) throws Exception{
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try{
			for(int i=0;i<idList.size();i++){
				deleteOne(sqlSession,idList.get(i));
			}
			sqlSession.commit();
			return idList.size();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	//获取实体，传入
	public CmsInclude getEntity(SqlSession sqlSession,int id){
		return sqlSession.selectOne("CmsInclude.selectCmsInclude",id);
	}
	/*
	 * 取得实体
	 */
	public CmsInclude getEntity(int cmsIncludeId) throws Exception {
		return (CmsInclude)super.getDataEntity(cmsIncludeId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CmsInclude> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
	//修改时检查是否存在相同地址path
	public boolean checkSamePathWithUpdate(CmsInclude cmsInclude) throws Exception{
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try{
			int result = sqlSession.selectOne("CmsInclude.selectSamePathWithUpdate",cmsInclude);
			if(result>0){
				//存在
				return true;
			}else{
				//不存在
				return false;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	//添加时检查是否存在相同地址path
		public boolean checkSamePathWidthAdd(CmsInclude cmsInclude) throws Exception{
			SqlSession sqlSession = MyBatisManager.getInstance().openSession();
			try{
				int result = sqlSession.selectOne("CmsInclude.selectSamePathWithAdd",cmsInclude);
				if(result>0){
					//存在
					return true;
				}else{
					//不存在
					return false;
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		}
		/**
		 * 删除文件
		 * @param fileName
		 * @return
		 */
		public boolean delete(String fileName) {
	        File file = new File(fileName);
	        if (!file.exists()) {
	            System.out.println("删除文件失败:" + fileName + "不存在！");
	            return false;
	        } else {
	            if (file.isFile())
	                return deleteFile(fileName);
	        }
			return false;
	    }
		 /**
	     * 删除单个文件
	     *
	     * @param fileName
	     *            要删除的文件的文件名
	     * @return 单个文件删除成功返回true，否则返回false
	     */
	    public static boolean deleteFile(String fileName) {
	        File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + fileName + "成功！");
	                return true;
	            } else {
	                System.out.println("删除单个文件" + fileName + "失败！");
	                return false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
	            return false;
	        }
	    }
	
}
