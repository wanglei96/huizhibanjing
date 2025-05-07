package strosoft.cg.model;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.FileHelper;
import strosoft.cg.common.CodeFormatter;
import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.ServiceHandlerInfo;
import strosoft.cg.common.ServiceHanldelerFileInfo;
import strosoft.cg.data.CgProjectInfo;
import strosoft.cg.data.CgModule;
import strosoft.cg.data.CgTable;
import strosoft.cg.data.CgView;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerateProject {
	private String configPath = "/src/main/resources/config";
	private String javaCodePath = "/src/main/java/";
	private CgProjectInfo project = null;
	private TableCodeBulider tableCodeBulider = null;
	private ViewCodeBulider viewCodeBulider = null;
	private List<ServiceHanldelerFileInfo> serviceHandlerFiles = new ArrayList<ServiceHanldelerFileInfo>();
	private List<String> serviceHandlerNames = new ArrayList<String>();
	private List<String> packageNames = new ArrayList<String>();
	private List<String> mapperPaths = new ArrayList<String>();
	private CgProjectInfo fullProject = null;

	public CgProjectInfo getProject() {
		return project;
	}

	public void setProject(CgProjectInfo project) {
		this.project = project;
	}

	public void buildProject() {
		generateCode();
	}

	public void generateCode() {
		List<CgModule> modules = this.project.getModules();
		// 获取所有模块和表名，表删除时可用来设置外键所在表的处理类
		this.getFullProject();
		for (int i = 0; i < modules.size(); i++) {
			String pakageName = modules.get(i).getModuleDocument() + ".data";
			packageNames.add(pakageName);
			generateModuleCode(modules.get(i));
		}
		generateMysqlCofigCode();
		saveServiceHandlerFile();
		/* packagedIntoJarFile(); */
	}

	public void generateModuleCode(CgModule module) {
		setServiceHandlerFile(module);
		generateTablesCode(module.getTables());
		generateViewsCode(module.getViews());
	}

	public void generateTablesCode(List<CgTable> tables) {
		for (int i = 0; i < tables.size(); i++) {
			generateTableCode(tables.get(i));
		}
	}

	public void generateTableCode(CgTable table) {
		CreateCodeManager.getInstance().getCgProjectGenerateParameter()
				.setTableName(table.getTableName());
		tableCodeBulider = new TableCodeBulider();
		table.setFormattedTableName(CodeGenerateUtil
				.getFormattedTableName(table.getTableName()));
		tableCodeBulider.setTable(table);
		tableCodeBulider.setFullProject(fullProject);
		try {
			tableCodeBulider.getContext();
			generateTableEntityClassCode(table);
			generateTableMasterClassCode(table);
			generateTableManagerClassCode(table);
			generateTableMapperCode(table);
			generateTableServiceHandlerClassCode(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateTableEntityClassCode(CgTable table) {
		String entityCode = new CodeFormatter().Format(tableCodeBulider
				.getTableEntityCode());
		String filePath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(table.getModulePath()).append("/data/")
				.append(table.getFormattedTableName()).append(".java")
				.toString();
		saveCode(filePath, entityCode);
	}

	public void generateTableMasterClassCode(CgTable table) {
		String masterClassCode = new CodeFormatter().Format(tableCodeBulider
				.getTableMasterCode());
		String masterPath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(table.getModulePath()).append("/data/")
				.append(table.getFormattedTableName()).append("Master.java")
				.toString();
		saveCode(masterPath, masterClassCode);

	}

	public void generateTableManagerClassCode(CgTable table) {
		String managerClassCode = new CodeFormatter().Format(tableCodeBulider
				.getTableManagerCode());
		String managerPath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(table.getModulePath())
				.append("/model/").append(table.getFormattedTableName())
				.append("Manager.java").toString();
		File file = new File(managerPath);
		if (!file.exists()) {
			saveCode(managerPath, managerClassCode);
		}

	}

	public void generateTableMapperCode(CgTable table) {
		String mapperCode = tableCodeBulider.getTableMapperCode();
		String mapperPath = new StringBuffer().append(table.getModulePath())
				.append("/mapper/").append(table.getFormattedTableName())
				.append("Mapper.xml").toString();
		String filePath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(mapperPath).toString();
		saveCode(filePath, mapperCode);
		mapperPaths.add(mapperPath);
	}

	public void generateTableServiceHandlerClassCode(CgTable table) {
		List<ServiceHandlerInfo> serviceHandlerInfos = tableCodeBulider
				.getTableServiceHandlerLsit();
		serviceHandlerFiles.get(serviceHandlerFiles.size() - 1)
				.getServiceHandlerInfos().addAll(serviceHandlerInfos);
	}

	public void generateViewsCode(List<CgView> views) {
		for (int i = 0; i < views.size(); i++) {
			generateViewCode(views.get(i));
		}
	}

	public void generateViewCode(CgView view) {
		CreateCodeManager.getInstance().getCgProjectGenerateParameter()
				.setTableName(view.getViewName());
		viewCodeBulider = new ViewCodeBulider();
		view.setFormattedViewName(CodeGenerateUtil.getFormattedTableName(view
				.getViewName()));
		viewCodeBulider.setView(view);
		try {
			viewCodeBulider.getContext();
			generateViewEntityClassCode(view);
			generateViewMapperCode(view);
			generateViewMasterClassCode(view);
			generateViewManagerCode(view);
			generateViewServiceHandlerClassCode(view);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateViewEntityClassCode(CgView view) {
		String entityCode = new CodeFormatter().Format(viewCodeBulider
				.getViewEntityCode());
		String filePath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(view.getModulePath()).append("/data/")
				.append(view.getFormattedViewName()).append(".java").toString();
		saveCode(filePath, entityCode);
	}

	public void generateViewMapperCode(CgView view) {
		String mapperCode = viewCodeBulider.getViewMapperCode();
		String mapperPath = new StringBuffer().append(view.getModulePath())
				.append("/mapper/").append(view.getFormattedViewName())
				.append("Mapper.xml").toString();
		String filePath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(mapperPath).toString();
		saveCode(filePath, mapperCode);
		mapperPaths.add(mapperPath);
	}

	public void generateViewMasterClassCode(CgView view) {
		String masterClassCode = new CodeFormatter().Format(viewCodeBulider
				.getViewMasterCode());
		String masterPath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(view.getModulePath()).append("/data/")
				.append(view.getFormattedViewName()).append("Master.java")
				.toString();
		saveCode(masterPath, masterClassCode);
	}

	public void generateViewManagerCode(CgView view) {
		String managerClassCode = new CodeFormatter().Format(viewCodeBulider
				.getViewManagerCode());
		String managerPath = new StringBuffer(project.getOutputDirectory())
				.append(javaCodePath).append(view.getModulePath()).append("/model/")
				.append(view.getFormattedViewName()).append("Manager.java")
				.toString();
		File file = new File(managerPath);
		if (!file.exists()) {
			saveCode(managerPath, managerClassCode);
		}

	}

	public void generateViewServiceHandlerClassCode(CgView view) {
		List<ServiceHandlerInfo> serviceHandlerInfos = viewCodeBulider
				.getViewServiceHandlerLsit();
		serviceHandlerFiles.get(serviceHandlerFiles.size() - 1)
				.getServiceHandlerInfos().addAll(serviceHandlerInfos);
	}

	public void generateMysqlCofigCode() {
		// 在MybatisConfig.xml中添加
		String xmlPath = project.getOutputDirectory()
				+ configPath + "/MybatisConfig.xml";
		String code = null;
		try {
			code = CreateCodeManager.getInstance().getMybatisConfig(xmlPath,
					mapperPaths, packageNames);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != code) {
			saveCode(xmlPath, code);
		}
	}

	// 获取整个项目module下的tables和views
	public void getFullProject() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SELECT configuration FROM sys_build_project where id="
					+ project.getProjectId();
			ArrayList<String> projects = MyBatisManager.getInstance()
					.getStringList(sqlSession, sql);
			fullProject = CreateCodeManager.getInstance()
					.getProjectConfiguration(projects.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void prepareOutputDirectory() {

	}

	public void saveServiceHandlerFile() {
		for (int i = 0; i < serviceHandlerFiles.size(); i++) {
			String filePath = serviceHandlerFiles.get(i).getFileName();
			String path = new StringBuffer(project.getOutputDirectory())
					.append(configPath + "/ServiceHandlers/")
					.append(filePath).append(".xml").toString();
			String code = serviceHandlerFiles.get(i).toCode();
			// code = CreateCodeManager.getInstance().getServiceHandlerXml(path,
			// code);
			saveCode(path, code);
			// 创建自定义serviceHndler
			String customCode = "<ServiceHandlers></ServiceHandlers>";
			String customPath = new StringBuffer(project.getOutputDirectory())
					.append(configPath + "/ServiceHandlers/Custom")
					.append(CodeGenerateUtil.upperCase(filePath))
					.append(".xml").toString();
			File file = new File(customPath);
			if (!file.exists()) {
				saveCode(customPath, customCode);
			}
			serviceHandlerNames.add("/config/ServiceHandlers/Custom"
					+ CodeGenerateUtil.upperCase(filePath) + ".xml");
			serviceHandlerNames.add("/config/ServiceHandlers/" + filePath
					+ ".xml");
		}
		insertIntogetServiceHandlersXml();
	}

	// 将生成的ServiceHandler引入ServiceHandlers.xml
	public void insertIntogetServiceHandlersXml() {
		String code = null;
		String xmlPath = new StringBuffer(project.getOutputDirectory()).append(
				configPath + "/ServiceHandlers.xml").toString();
		try {
			code = CreateCodeManager.getInstance().getServiceHandlersXml(
					xmlPath, serviceHandlerNames);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != code) {
			saveCode(xmlPath, code);
		}
	}

	// 根据module设置ServiceHandler
	public void setServiceHandlerFile(CgModule module) {
		ServiceHanldelerFileInfo serviceHandlerFile = new ServiceHanldelerFileInfo();
		serviceHandlerFile.setFileName(module.getModuleName());
		serviceHandlerFile
				.setServiceHandlerInfos(new ArrayList<ServiceHandlerInfo>());
		this.serviceHandlerFiles.add(serviceHandlerFile);
	}

	// 写代码
	public void saveCode(String path, String code) {
		try {
			FileHelper.writeAllText(path, code, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 先编译为.class文件，再打包成jar包
	public void packagedIntoJarFile() {
		String classpath = project.getOutputDirectory() + javaCodePath;
		String extdirs = project.getOutputDirectory()
				+ "\\src\\resources\\lib";
		String[] classpathArray = classpath.split(":");
		String out = classpathArray[0] + ":\\" + project.getProjectName();
		// 创建.classes文件输出目录
		FileHelper.createDir(out);
		for (int i = 0; i < this.project.getModules().size(); i++) {
			CgModule module = this.project.getModules().get(i);
			String path = classpath + "\\"
					+ module.getModuleDocument().replace(".", "\\") + "\\data";
			// 编译
			String cmd = "javac -Xlint:unchecked -encoding UTF-8 -classpath "
					+ classpath + " -extdirs " + extdirs + " -d " + out + " "
					+ path + "\\*.java";
			System.out.println("编译命令：" + cmd);
			CodeGenerateUtil.exc(cmd);
			// 打包
			File file = new File(out);
			String jarPath = extdirs + "\\" + module.getModuleName() + "Cg.jar";
			String classesPath = module.getModuleDocument().replace(".", "\\")
					+ "\\data/*";
			cmd = "jar cvf " + jarPath + " " + classesPath;
			System.out.println("打包命令：" + cmd);
			CodeGenerateUtil.exc(cmd, null, file);
			// 删除原java文件和.class文件
			FileHelper.delete(path);
			// FileHelper.delete(out);
		}
	}
}
