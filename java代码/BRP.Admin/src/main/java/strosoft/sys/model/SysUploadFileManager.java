package strosoft.sys.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.app.util.HttpHelper;
import strosoft.app.util.ImageHelper;
import strosoft.sys.data.SysUploadFile;

public class SysUploadFileManager extends IdDataManager {

	private static SysUploadFileManager _instance;

	public static SysUploadFileManager getInstance() {
		if (_instance == null) {
			_instance = new SysUploadFileManager();
		}
		return _instance;
	}

	private SysUploadFileManager() {
		this.setTableName("sys_upload_file");
		this.setTypeName("SysUploadFile");
	}

	/*
	 * 取得实体
	 */
	public SysUploadFile getEntity(int id) throws Exception {
		return (SysUploadFile) super.getDataEntity(id);
	}

	/*
	 * 取得实体
	 */
	public SysUploadFile getEntity(SqlSession sqlSession, int id) throws SQLException, IOException {
		return (SysUploadFile) super.getDataEntity(sqlSession, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysUploadFile> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/**
	 * 下载文件
	 * 
	 * @param fileUrl
	 * @return
	 * @throws Exception
	 */
	public int downloadFile(String fileUrl, String relativeFilePath) throws Exception {
		String fileName = FileHelper.getFileName(relativeFilePath);
		String absoluteFilePath = SysConfig.getRootPath() + "/" + relativeFilePath;
		HttpHelper.downloadFile(fileUrl, absoluteFilePath);
		SysUploadFile newSysUploadFile = new SysUploadFile();
		newSysUploadFile.setName(fileName);
		newSysUploadFile.setPath(relativeFilePath);
		int newSysUploadFileId = this.add(newSysUploadFile);
		return newSysUploadFileId;
	}

	/**
	 * 取得小图片路径
	 * 
	 * @param relativeSourceFilePath
	 * @return
	 * @throws IOException
	 */
	public String getSmallImageFilePath(String relativeSourceFilePath) throws IOException {
		String realUploadDirectory = SysConfig.getAbsoluteUploadDirectory();
		String fileExtention = FileHelper.getFileExtention(relativeSourceFilePath);
		Boolean isImage = ImageHelper.isImage(relativeSourceFilePath);
		if (!isImage) {
			return null;
		}
		String fileName = UUID.randomUUID().toString() + "." + fileExtention;
		File realFilePath = new File(realUploadDirectory, fileName);
		// 判断目标文件所在的目录是否存在
		if (!realFilePath.getParentFile().exists()) {
			realFilePath.getParentFile().mkdirs();
		}
		String absoluteSourceFilePath = SysConfig.getRootPath() + "/" + relativeSourceFilePath;
		String targetFilePath = realUploadDirectory + "/" + fileName;
		ImageHelper.createMiniImage(absoluteSourceFilePath, targetFilePath, 300, 300);
		// 相对路径
		String relativeFilePath = SysConfig.getUploadDirectory() + "/" + fileName;
		return relativeFilePath;

	}

}
