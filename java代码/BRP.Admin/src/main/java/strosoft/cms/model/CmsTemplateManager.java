package strosoft.cms.model;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.data.CmsTemplateMaster;

public class CmsTemplateManager extends CmsTemplateMaster {
	private static CmsTemplateManager _instance;

	private CmsTemplateManager() {
		this.setTableName("cms_template");
		this.setTypeName("CmsTemplate");
	}

	public static CmsTemplateManager getInstance() {
		if (_instance == null) {
			_instance = new CmsTemplateManager();
		}
		return _instance;
	}

	/*
	 * 取得实体
	 */
	public CmsTemplate getEntity(int cmsTemplateId) throws Exception {
		return (CmsTemplate) super.getDataEntity(cmsTemplateId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CmsTemplate> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	// 单个删除
	public int deleteOne(SqlSession sqlSession, int id) throws Exception {
		// 获取此模板
		CmsTemplate entity = getEntity(sqlSession, id);
		String templateTypeCode = entity.getCmsTemplateTypeCode();
		String fileName = SysConfig.getRootPath() + entity.getPath();
		String sql = "";
		if (templateTypeCode.equals("Channel")) {
			sql = "update cms_channel set cms_channel_template_id = null where cms_channel_template_id = " + id;
		} else if (templateTypeCode.equals("Content")) {
			sql = "update cms_channel set cms_content_template_id = null where cms_content_template_id = " + id;
		}
		if (!sql.equals("")) {
			// 修改模板为此模板的栏目
			CmsChannelManager.getInstance().updateCmsChannelBySql(sqlSession, sql);
		}
		// 删除模板
		int deleteId = super.delete(sqlSession, "cms_template", id);
		delete(fileName);
		return deleteId;

	}

	// 批量删除
	@Override
	public int batchDelete(List<Integer> idList) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			for (int i = 0; i < idList.size(); i++) {
				deleteOne(sqlSession, idList.get(i));
			}
			sqlSession.commit();
			return idList.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 获取实体，传入
	public CmsTemplate getEntity(SqlSession sqlSession, int id) {
		return sqlSession.selectOne("CmsTemplate.selectCmsTemplate", id);
	}

	// 修改时检查是否存在相同地址path
	public boolean checkSamePathWithUpdate(CmsTemplate cmsTemplate) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = sqlSession.selectOne("CmsTemplate.selectSamePathWithUpdate", cmsTemplate);
			if (result > 0) {
				// 存在
				return true;
			} else {
				// 不存在
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 添加时检查是否存在相同地址path
	public boolean checkSamePathWidthAdd(CmsTemplate cmsTemplate) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			int result = sqlSession.selectOne("CmsTemplate.selectSamePathWithAdd", cmsTemplate);
			if (result > 0) {
				// 存在
				return true;
			} else {
				// 不存在
				return false;
			}
		} catch (Exception e) {
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
	 * 
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
	 * @param fileName 要删除的文件的文件名
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

	// 查询默认模板
	public CmsTemplate selectDefaultCmsTemplate(String cmsTemplateType, int cmsSiteId) throws Exception {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		StringBuffer sql = new StringBuffer("");
		sql.append("select * from cms_template where is_system_default_");
		sql.append(cmsTemplateType);
		sql.append("_template = 1 and cms_site_id = ");
		sql.append(cmsSiteId);
		try {
			return sqlSession.selectOne("CmsTemplate.selectEneityBysql", sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	// 检查模板格式是否为XML
	public boolean checkXMLFormat(String content) throws ParserConfigurationException {
		// 读取模板文件内容
		StringReader sr = new StringReader(content);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			Document doc = builder.parse(is);
		} catch (SAXException e) {
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}