package strosoft.cg.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import strosoft.app.common.DataMaster;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.FileHelper;
import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.SearchItemInfo;
import strosoft.cg.data.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateCodeManager extends DataMaster {

	private static CreateCodeManager _instance;
	private CgProjectGenerateParameter cgProjectGenerateParameter;

	public static CreateCodeManager getInstance() {
		if (_instance == null) {
			_instance = new CreateCodeManager();
		}
		return _instance;
	}

	public CgProjectGenerateParameter getCgProjectGenerateParameter() {
		return cgProjectGenerateParameter;
	}

	public void setCgProjectGenerateParameter(
			CgProjectGenerateParameter cgProjectGenerateParameter) {
		this.cgProjectGenerateParameter = cgProjectGenerateParameter;
	}

	public CgProjectInfo getProjectConfiguration(String configuration)
			throws ParserConfigurationException {
		// 读取内容
		String removeString = "<#text>";
		configuration = configuration.replace(removeString, "");
		removeString = "</#text>";
		configuration = configuration.replace(removeString, "");
		removeString = "#text";
		configuration = configuration.replace(removeString, "");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		CgProjectInfo project = null;
		Document document;
		try {
			document = db.parse(is);
			project = getProject(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return project;
	}

	public String getConfigurationWhenUpdateProject(CgProjectInfo project,
			String configuration) throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList documentChild = document.getChildNodes();
			List<Node> projects = getNodeListByNodeName(documentChild,
					"Project");
			Element resultProject = (Element) projects.get(0);
			resultProject.setAttribute("Name", project.getProjectName());
			resultProject.setAttribute("OutputDirectory",
					project.getOutputDirectory());
			NodeList projectChild = projects.get(0).getChildNodes();
			Element dataSource = (Element) getNodeListByNodeName(projectChild,
					"DataSource").get(0);
			dataSource.setAttribute("Name", project.getDataSource());
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getProjectJson(CgProjectInfo project) {
		StringBuffer json = new StringBuffer();
		if (null == project) {
			return "";
		}

		return json.toString();
	}

	public CgProjectInfo getProject(Document document) {
		CgProjectInfo project = new CgProjectInfo();
		List<CgModule> modules = new ArrayList<CgModule>();
		NodeList nlRoot = document.getChildNodes();
		if (nlRoot.getLength() > 0) {
			project.setProjectName(getAttributeValue(nlRoot.item(0), "Name"));
			project.setOutputDirectory(getAttributeValue(nlRoot.item(0),
					"OutputDirectory"));
			nlRoot = nlRoot.item(0).getChildNodes();
		}
		List<Node> node = getNodeListByNodeName(nlRoot, "DataSource");
		if (node.size() > 0) {
			project.setDataSource(getAttributeValue(node.get(0), "Name"));
		}
		List<Node> moduleNode = getNodeListByNodeName(nlRoot, "Modules");
		List<Node> moduleNodes = getNodeListByNodeName(moduleNode.get(0)
				.getChildNodes(), "Module");
		for (int i = 0; i < moduleNodes.size(); i++) {
			// List<ProcedureInfo> procedurs = new ArrayList<ProcedureInfo>();
			Node currentNode = moduleNodes.get(i);
			CgModule module = new CgModule();
			module.setModuleName(getAttributeValue(currentNode, "Name"));
			module.setModuleDocument(getAttributeValue(currentNode,
					"ModuleDocument"));
			List<CgTable> tables = getTables(currentNode.getChildNodes(), module);
			List<CgView> views = getViews(currentNode.getChildNodes(), module);
			module.setTables(tables);
			module.setViews(views);
			modules.add(module);
		}
		project.setModules(modules);
		return project;
	}

	public List<CgTable> getTables(NodeList nlRoot, CgModule module) {
		List<CgTable> tables = new ArrayList<CgTable>();
		List<Node> moduleNode = getNodeListByNodeName(nlRoot, "Tables");
		List<Node> tableItems = getNodeListByNodeName(moduleNode.get(0)
				.getChildNodes(), "Table");
		for (int i = 0; i < tableItems.size(); i++) {
			CgTable table = new CgTable();
			Node current = tableItems.get(i);
			table.setTableName(getAttributeValue(current, "Name"));
			table.setType("Table");
			table.setModuleName(module.getModuleName());
			table.setModulePath(module.getModulePath());
			tables.add(table);
		}
		return tables;
	}

	public List<CgView> getViews(NodeList nlRoot, CgModule module) {
		List<CgView> views = new ArrayList<CgView>();
		List<Node> moduleNode = getNodeListByNodeName(nlRoot, "Views");
		List<Node> tableItems = getNodeListByNodeName(moduleNode.get(0)
				.getChildNodes(), "View");
		for (int i = 0; i < tableItems.size(); i++) {
			CgView view = new CgView();
			Node current = tableItems.get(i);
			view.setViewName(getAttributeValue(current, "Name"));
			view.setType("View");
			view.setModuleName(module.getModuleName());
			view.setModulePath(module.getModulePath());
			views.add(view);
		}
		return views;
	}

	public List<Node> getNodeListByNodeName(NodeList nodes, String name) {
		List<Node> results = new ArrayList<Node>();
		for (int i = 0; i < nodes.getLength(); i++) {
			// 获取节点
			Node currentNode = nodes.item(i);
			// 获取节点名
			String nodeName = currentNode.getNodeName();
			if (nodeName.equals(name)) {
				results.add(currentNode);
			}
		}
		return results;
	}

	public List<Node> getNodeListByAttributeNameAndAttributeValue(
			NodeList nodes, String attributeName, String attributeValue) {
		List<Node> results = new ArrayList<Node>();
		for (int i = 0; i < nodes.getLength(); i++) {
			// 获取节点
			Node currentNode = nodes.item(i);
			// 获取节点名
			NamedNodeMap nnmAttribute = currentNode.getAttributes();
			if (nnmAttribute != null) {
				for (int j = 0; j < nnmAttribute.getLength(); j++) {
					Node attribute = nnmAttribute.item(j);
					// System.out.println(currentNode.getNodeName()+":"+attribute.getNodeName()+":"+attribute.getNodeValue());
					if (attribute.getNodeName().equals(attributeName)
							&& attribute.getNodeValue().equals(attributeValue)) {
						results.add(currentNode);
					}
				}
			}
		}
		return results;
	}

	public String getAttributeValue(Node node, String attributeName) {
		String result = null;
		NamedNodeMap nnmAttribute = node.getAttributes();
		if (nnmAttribute != null) {
			for (int j = 0; j < nnmAttribute.getLength(); j++) {
				Node attribute = nnmAttribute.item(j);
				if (attribute.getNodeName().equals(attributeName)) {
					result = attribute.getNodeValue();
				}
			}
		}
		return result;
	}

	public CgTable getColumConfig(String configuration, String moduleName,
			String tableName, String type) throws ParserConfigurationException {
		// 读取内容
		String removeString = "<#text>";
		configuration = configuration.replace(removeString, "");
		removeString = "</#text>";
		configuration = configuration.replace(removeString, "");
		removeString = "#text";
		configuration = configuration.replace(removeString, "");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		CgTable table = null;
		Document document;
		try {
			document = db.parse(is);
			table = getTableInfo(document, moduleName, tableName, type);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return table;
	}

	public CgTable getTableInfo(Document document, String moduleName,
			String tableName, String type) {
		CgTable table = new CgTable();
		NodeList tableNodes = document.getElementsByTagName(type);
		if (null == tableNodes || tableNodes.getLength() < 1) {
			return table;
		}
		List<Node> resultTables = getNodeListByAttributeNameAndAttributeValue(
				tableNodes, "Name", tableName);
		Node resultTable = resultTables.get(0);
		table.setType(type);
		table.setTableName(tableName);
		table.setModuleName(moduleName);
		table.setTableColumns(getTableColums(resultTable));
		return table;
	}

	public List<CgTableColumn> getTableColums(Node tableNode) {
		List<CgTableColumn> tableColumns = new ArrayList<CgTableColumn>();
		NodeList resultTableChildNodes = tableNode.getChildNodes();
		List<Node> fields = getNodeListByNodeName(resultTableChildNodes,
				"Fields");
		List<Node> fieldList = getNodeListByNodeName(fields.get(0)
				.getChildNodes(), "Field");
		for (int i = 0; i < fieldList.size(); i++) {
			Node currentNode = fieldList.get(i);
			CgTableColumn tableColumn = new CgTableColumn();
			tableColumn.setCaption(getAttributeValue(currentNode, "Caption"));
			tableColumn.setColumnName(getAttributeValue(currentNode, "Name"));
			tableColumn.setIsSearchItem(getAttributeValue(currentNode,
					"IsSearchItem"));
			tableColumn.setSearchOperation(getAttributeValue(currentNode,
					"SearchOperation"));
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	public String getConfiguration(CgProjectInfo project) {
		StringBuffer configuration = new StringBuffer();
		configuration.append("<Project Name=\"" + project.getProjectName()
				+ "\" OutputDirectory=\"" + project.getOutputDirectory()
				+ "\">");
		configuration.append("<DataSource Name=\"" + project.getDataSource()
				+ "\"/>");
		configuration.append("<Modules></Modules>");
		configuration.append("</Project>");
		return configuration.toString();
	}

	public String getConfigurationWhenAddModule(CgModule module,
			String configuration) throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList documentChild = document.getChildNodes();
			List<Node> projects = getNodeListByNodeName(documentChild,
					"Project");
			NodeList projectChild = projects.get(0).getChildNodes();
			List<Node> modules = getNodeListByNodeName(projectChild, "Modules");
			Element newChild = document.createElement("Module");
			newChild.setAttribute("Name", module.getModuleName());
			newChild.setAttribute("ModuleDocument", module.getModuleDocument());
			Element newTables = document.createElement("Tables");
			Element newViews = document.createElement("Views");
			Element newProcedures = document.createElement("Procedures");
			newChild.appendChild(newTables);
			newChild.appendChild(newViews);
			newChild.appendChild(newProcedures);
			modules.get(0).appendChild(newChild);
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getConfigurationWhenUpdateModule(CgModule module,
			String originaltModuleName, String configuration)
			throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList documentChild = document.getChildNodes();
			List<Node> projects = getNodeListByNodeName(documentChild,
					"Project");
			NodeList projectChild = projects.get(0).getChildNodes();
			List<Node> modules = getNodeListByNodeName(projectChild, "Modules");
			NodeList modulesChild = modules.get(0).getChildNodes();
			List<Node> resultModule = getNodeListByAttributeNameAndAttributeValue(
					modulesChild, "Name", originaltModuleName);
			Element moduleItem = (Element) resultModule.get(0);
			moduleItem.setAttribute("Name", module.getModuleName());
			moduleItem.setAttribute("ModuleDocument",
					module.getModuleDocument());
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getConfigurationWhenAddTable(CgModule module,
			String configuration, String type)
			throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		List<CgTable> tableList = module.getTables();
		try {
			document = db.parse(is);
			NodeList ModuleNodes = document.getElementsByTagName("Module");
			List<Node> resultModule = getNodeListByAttributeNameAndAttributeValue(
					ModuleNodes, "Name", module.getModuleName());
			NodeList resultModuleChild = resultModule.get(0).getChildNodes();
			List<Node> tables = getNodeListByNodeName(resultModuleChild, type
					+ "s");
			Node resultTable = tables.get(0);
			for (int i = 0; i < tableList.size(); i++) {
				CgTable currentTable = tableList.get(i);
				Element newTable = document.createElement(type);
				newTable.setAttribute("Name", currentTable.getTableName());
				Element newFields = document.createElement("Fields");
				List<CgTableColumn> tableColumns = currentTable.getTableColumns();
				for (int j = 0; j < tableColumns.size(); j++) {
					CgTableColumn currentColumn = tableColumns.get(j);
					Element newField = document.createElement("Field");
					if (null != currentColumn.getCaption()
							&& (!currentColumn.getCaption().equals(""))) {
						newField.setAttribute("Caption",
								currentColumn.getCaption());
					}
					newField.setAttribute("Name", currentColumn.getColumnName());
					newFields.appendChild(newField);
				}
				newTable.appendChild(newFields);
				resultTable.appendChild(newTable);
			}
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	// 检查模板格式是否为XML
	public boolean checkXMLFormat(String content)
			throws ParserConfigurationException {
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

	public String XmlToString(Document document) throws TransformerException,
			UnsupportedEncodingException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty("encoding", "UTF-8");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		t.transform(new DOMSource(document), new StreamResult(bos));
		String xmlStr = bos.toString("UTF-8");
		return xmlStr;
	}

	public CgModule getModuleTablesWhenAddTables(SqlSession sqlSession,
			String database, List<String> tableList, String moduleName,
			String type) throws IOException, Exception {
		CgModule module = new CgModule();
		List<CgTable> tables = new ArrayList<CgTable>();
		for (int i = 0; i < tableList.size(); i++) {
			CgTable table = new CgTable();
			List<CgTableColumn> tableColumns = new ArrayList<CgTableColumn>();
			ArrayList<LinkedHashMap<String, Object>> results = MyBatisManager
					.getInstance().getAllColumns(database, tableList.get(i));
			for (int j = 0; j < results.size(); j++) {
				CgTableColumn tableColumn = new CgTableColumn();
				tableColumn.setColumnName((String) results.get(j).get(
						"COLUMN_NAME"));
				tableColumn.setCaption((String) results.get(j).get(
						"COLUMN_COMMENT"));
				tableColumns.add(tableColumn);
			}
			table.setTableColumns(tableColumns);
			table.setTableName(tableList.get(i));
			table.setType(type);
			tables.add(table);
		}
		module.setModuleName(moduleName);
		module.setTables(tables);
		return module;
	}

	public CgModule getModuleTablesWhenSaveCgField(JSONArray feilList,
			String type, String moduleName, String tableName) {
		CgModule module = new CgModule();
		module.setModuleName(moduleName);
		List<CgTable> tableList = new ArrayList<CgTable>();
		CgTable table = new CgTable();
		List<CgTableColumn> tableColumns = new ArrayList<CgTableColumn>();
		for (int i = 0; i < feilList.size(); i++) {
			JSONObject currentColumn = feilList.getJSONObject(i);
			CgTableColumn tableColumn = new CgTableColumn();
			tableColumn.setColumnName(currentColumn.getString("columnName"));
			tableColumn.setCaption(currentColumn.getString("caption"));
			tableColumn
					.setIsSearchItem(currentColumn.getString("isSearchItem"));
			tableColumn.setSearchOperation(currentColumn
					.getString("searchOperation"));
			tableColumn.setTableName(tableName);
			tableColumn.setTypeName(type);
			tableColumns.add(tableColumn);
		}
		table.setType(type);
		table.setTableName(tableName);
		table.setModuleName(moduleName);
		table.setTableColumns(tableColumns);
		tableList.add(table);
		module.setTables(tableList);
		return module;
	}

	public String getConfigurationWhenSaveCgField(CgModule module,
			String configuration, String type)
			throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		List<CgTable> tableList = module.getTables();
		CgTable table = tableList.get(0);
		List<CgTableColumn> tableColumns = table.getTableColumns();
		try {
			document = db.parse(is);
			NodeList tableNodes = document.getElementsByTagName(type);
			List<Node> resultTables = getNodeListByAttributeNameAndAttributeValue(
					tableNodes, "Name", table.getTableName());
			NodeList resultTableChild = resultTables.get(0).getChildNodes();
			List<Node> fieldsList = getNodeListByNodeName(resultTableChild,
					"Fields");
			// 移除子节点
			Node resultField = fieldsList.get(0);
			NodeList resultFieldChild = resultField.getChildNodes();
			List<Node> fieldList = getNodeListByNodeName(resultFieldChild,
					"Field");
			for (int i = 0; i < fieldList.size(); i++) {
				resultField.removeChild(fieldList.get(i));
			}
			// 添加新节点
			for (int i = 0; i < tableColumns.size(); i++) {
				CgTableColumn current = tableColumns.get(i);
				Element newField = document.createElement("Field");
				newField.setAttribute("Name", current.getColumnName());
				if (null != current.getCaption()
						&& (!current.getCaption().equals("null"))) {
					newField.setAttribute("Caption", current.getCaption());
				}
				if (null != current.getIsSearchItem()
						&& (!current.getIsSearchItem().equals("null"))) {
					newField.setAttribute("IsSearchItem",
							current.getIsSearchItem());
				}
				if (null != current.getSearchOperation()
						&& (!current.getSearchOperation().equals("null"))) {
					newField.setAttribute("SearchOperation",
							current.getSearchOperation());
				}
				resultField.appendChild(newField);
			}
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getConfigurationWhenDeleteTable(List<String> tableList,
			String moduleName, String configuration, String type)
			throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList tableNodes = document.getElementsByTagName(type);
			for (int i = 0; i < tableList.size(); i++) {
				List<Node> resultTable = getNodeListByAttributeNameAndAttributeValue(
						tableNodes, "Name", tableList.get(i));
				Node currentNode = resultTable.get(0);
				currentNode.getParentNode().removeChild(currentNode);
			}
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getConfigurationWhenDeleteModule(String moduleName,
			String configuration) throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList moduleNodes = document.getElementsByTagName("Module");
			List<Node> resultModules = getNodeListByAttributeNameAndAttributeValue(
					moduleNodes, "Name", moduleName);
			Node resultModule = resultModules.get(0);
			resultModule.getParentNode().removeChild(resultModule);
			return XmlToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public CgProjectInfo getProjectByJDataWhenGenenrateCode(JSONObject jData,
			String configuration) throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(configuration);
		InputSource is = new InputSource(sr);
		Document document;

		CgProjectInfo project = new CgProjectInfo();
		List<CgModule> modules = new ArrayList<CgModule>();
		System.out.println(jData);
		project.setDataSource(jData.getString("dataSource"));
		project.setModules(modules);
		project.setOutputDirectory(jData.getString("outputDirectory"));
		project.setProjectName(jData.getString("projectName"));
		project.setProjectId(jData.getInt("projectId"));
		JSONArray moduleList = jData.getJSONArray("moduleList");
		try {
			document = db.parse(is);
			NodeList documentChild = document.getChildNodes();
			List<Node> projects = getNodeListByNodeName(documentChild,
					"Project");
			NodeList projectChild = projects.get(0).getChildNodes();
			List<Node> moduleItems = getNodeListByNodeName(projectChild,
					"Modules");
			NodeList modulesChild = moduleItems.get(0).getChildNodes();
			for (int i = 0; i < moduleList.size(); i++) {
				JSONObject currentModule = moduleList.getJSONObject(i);
				CgModule module = new CgModule();
				module.setModuleName(currentModule.getString("moduleName"));
				module.setModuleDocument(currentModule
						.getString("moduleDocument"));
				module.setModulePath(module.getModuleDocument().replace(".",
						"/"));
				module.setDataSource(project.getDataSource());
				List<Node> resultModule = getNodeListByAttributeNameAndAttributeValue(
						modulesChild, "Name", module.getModuleName());
				NodeList resultModuleChild = resultModule.get(0)
						.getChildNodes();
				List<Node> tables = getNodeListByNodeName(resultModuleChild,
						"Tables");
				List<Node> views = getNodeListByNodeName(resultModuleChild,
						"Views");
				NodeList tablesChild = tables.get(0).getChildNodes();
				NodeList viewsChild = views.get(0).getChildNodes();
				List<Node> tableItems = getNodeListByNodeName(tablesChild,
						"Table");
				List<Node> viewsItems = getNodeListByNodeName(viewsChild,
						"View");
				module.setTables(getTableListByJDataAndXml(tableItems,
						currentModule, module));
				module.setViews(getViewListByJDataAndXml(viewsItems,
						currentModule, module));
				modules.add(module);
			}
			return project;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public List<CgTable> getTableListByJDataAndXml(List<Node> tableNodes,
			JSONObject currentModule, CgModule module) {
		List<CgTable> tableList = new ArrayList<CgTable>();
		JSONArray tables = currentModule.getJSONArray("tableList");
		for (int i = 0; i < tables.size(); i++) {
			CgTable table = new CgTable();
			table.setTableName(tables.getString(i));
			table.setModuleDocument(module.getModuleDocument());
			table.setModulePath(table.getModuleDocument().replace(".", "/"));
			table.setModuleName(module.getModuleName());
			table.setDataSource(module.getDataSource());
			table.setType("Table");
			setTableDetailByName(tableNodes, table);
			tableList.add(table);
		}
		return tableList;
	}

	public List<CgView> getViewListByJDataAndXml(List<Node> viewNodes,
			JSONObject currentModule, CgModule module) {
		List<CgView> viewList = new ArrayList<CgView>();
		JSONArray views = currentModule.getJSONArray("viewList");
		for (int i = 0; i < views.size(); i++) {
			CgView view = new CgView();
			view.setViewName(views.getString(i));
			view.setDataSource(module.getDataSource());
			view.setModuleDocument(module.getModuleDocument());
			view.setModulePath(view.getModuleDocument().replace(".", "/"));
			view.setModuleName(module.getModuleName());
			view.setType("View");
			setViewDetailByName(viewNodes, view);
			viewList.add(view);
		}
		return viewList;
	}

	public void setTableDetailByName(List<Node> nodes, CgTable table) {
		List<SearchItemInfo> searchItemInfos = new ArrayList<SearchItemInfo>();
		List<CgTableColumn> tableColumns = new ArrayList<CgTableColumn>();
		for (int i = 0; i < nodes.size(); i++) {
			Node currentNode = nodes.get(i);
			if (getAttributeValue(currentNode, "Name").equals(
					table.getTableName())) {
				NodeList tableChild = currentNode.getChildNodes();
				List<Node> fields = getNodeListByNodeName(tableChild, "Fields");
				NodeList fieldsChild = fields.get(0).getChildNodes();
				List<Node> fieldItems = getNodeListByNodeName(fieldsChild,
						"Field");
				for (int z = 0; z < fieldItems.size(); z++) {
					CgTableColumn tableColumn = new CgTableColumn();
					Element currentField = (Element) fieldItems.get(z);
					String parameterName = CodeGenerateUtil
							.getParameterName(currentField.getAttribute("Name"));
					tableColumn
							.setCaption(currentField.getAttribute("Caption"));
					tableColumn.setTableName(table.getTableName());
					tableColumn
							.setColumnName(currentField.getAttribute("Name"));
					tableColumn.setIsSearchItem(currentField
							.getAttribute("IsSearchItem"));
					tableColumn.setSearchOperation(currentField
							.getAttribute("SearchOperation"));
					tableColumn.setParameterName(parameterName);
					if (currentField.getAttribute("IsSearchItem")
							.equals("true")) {
						SearchItemInfo searchItemInfo = new SearchItemInfo();
						searchItemInfo.setColumnName(currentField
								.getAttribute("Name"));
						searchItemInfo.setOperation(currentField
								.getAttribute("SearchOperation"));
						searchItemInfo.setCaption(currentField
								.getAttribute("Caption"));
						searchItemInfo.setParameterName(parameterName);
						searchItemInfos.add(searchItemInfo);
					}
					tableColumns.add(tableColumn);
				}

			}
		}
		table.setSearchItems(searchItemInfos);
		table.setTableColumns(tableColumns);
	}

	public void setViewDetailByName(List<Node> nodes, CgView view) {
		List<SearchItemInfo> searchItemInfos = new ArrayList<SearchItemInfo>();
		List<CgViewColumn> viewColumns = new ArrayList<CgViewColumn>();
		for (int i = 0; i < nodes.size(); i++) {
			Node currentNode = nodes.get(i);
			if (getAttributeValue(currentNode, "Name").equals(
					view.getViewName())) {
				NodeList tableChild = currentNode.getChildNodes();
				List<Node> fields = getNodeListByNodeName(tableChild, "Fields");
				NodeList fieldsChild = fields.get(0).getChildNodes();
				List<Node> fieldItems = getNodeListByNodeName(fieldsChild,
						"Field");
				for (int z = 0; z < fieldItems.size(); z++) {
					CgViewColumn viewColumn = new CgViewColumn();
					Element currentField = (Element) fieldItems.get(z);
					String parameterName = CodeGenerateUtil
							.getParameterName(currentField.getAttribute("Name"));
					viewColumn.setCaption(currentField.getAttribute("Caption"));
					viewColumn.setViewName(view.getViewName());
					viewColumn.setColumnName(currentField.getAttribute("Name"));
					viewColumn.setIsSearchItem(currentField
							.getAttribute("IsSearchItem"));
					viewColumn.setSearchOperation(currentField
							.getAttribute("SearchOperation"));
					viewColumn.setParameterName(parameterName);
					if (currentField.getAttribute("IsSearchItem")
							.equals("true")) {
						SearchItemInfo searchItemInfo = new SearchItemInfo();
						searchItemInfo.setColumnName(currentField
								.getAttribute("Name"));
						searchItemInfo.setOperation(currentField
								.getAttribute("SearchOperation"));
						searchItemInfo.setParameterName(parameterName);
						searchItemInfos.add(searchItemInfo);
					}
					viewColumns.add(viewColumn);
				}
			}

		}
		view.setSearchItems(searchItemInfos);
		view.setViewColumns(viewColumns);
	}

	public String getMybatisConfig(String xmlPath, List<String> mapperPaths,
			List<String> packageNames) throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String config = null;
		try {
			File file = new File(xmlPath);
			if (file.exists()) {
				config = FileHelper.readAllText(xmlPath, "utf-8");
			} else {
				System.out.println("找不到配置文件：" + xmlPath);
				config = getBaseConfig();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		StringReader sr = new StringReader(config);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList typeAliases = document.getElementsByTagName("typeAliases");
			NodeList typeAliasesChilds = typeAliases.item(0).getChildNodes();
			for (int i = 0; i < packageNames.size(); i++) {
				Boolean isExistence = isExistence(typeAliasesChilds, "name",
						packageNames.get(i));
				if (!isExistence) {
					Element newMapper = document.createElement("package");
					newMapper.setAttribute("name", packageNames.get(i));
					typeAliases.item(0).appendChild(newMapper);
				}
			}
			NodeList mappers = document.getElementsByTagName("mappers");
			NodeList mapperItems = mappers.item(0).getChildNodes();
			for (int i = 0; i < mapperPaths.size(); i++) {
				Boolean isExistence = isExistence(mapperItems, "resource",
						mapperPaths.get(i));
				if (!isExistence) {
					Element newMapper = document.createElement("mapper");
					newMapper.setAttribute("resource", mapperPaths.get(i));
					mappers.item(0).appendChild(newMapper);
				}
			}
			String oldString = "<configuration>";
			String newString = "\r<!DOCTYPE configuration \r  PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \r  \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\r<configuration>";
			String xmlString = XmlToString(document).replace(oldString,
					newString);
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public String getServiceHandlersXml(String xmlPath,
			List<String> serviceHandlerNames)
			throws ParserConfigurationException {
		// 读取内容
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String config = null;
		try {
			File file = new File(xmlPath);
			if (file.exists()) {
				config = FileHelper.readAllText(xmlPath, "utf-8");
			} else {
				System.out.println("找不到配置文件：" + xmlPath);
				config = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
				config += "<ServiceHandlers Default=\"NoneServiceHandler\"></ServiceHandlers>";
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("找不到配置文件：" + xmlPath);
		}
		StringReader sr = new StringReader(config);
		InputSource is = new InputSource(sr);
		Document document;
		try {
			document = db.parse(is);
			NodeList documentChilds = document.getChildNodes();
			List<Node> serviceHandlers = getNodeListByNodeName(documentChilds,
					"ServiceHandlers");
			NodeList serviceHandlerList = serviceHandlers.get(0)
					.getChildNodes();
			for (int i = 0; i < serviceHandlerNames.size(); i++) {
				Boolean isExistence = isExistence(serviceHandlerList, "src",
						serviceHandlerNames.get(i));
				if (!isExistence) {
					Element newInclude = document.createElement("Include");
					newInclude.setAttribute("src", serviceHandlerNames.get(i));
					serviceHandlers.get(0).appendChild(newInclude);
				}
			}
			String xmlString = XmlToString(document);
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	public Boolean isExistence(NodeList mapperItems, String valueName,
			String value) {
		Boolean flag = false;
		for (int i = 0; i < mapperItems.getLength(); i++) {
			if (!(mapperItems.item(i) instanceof Element)) {
				continue;
			}
			Element current = (Element) mapperItems.item(i);
			if (null != current.getAttribute(valueName)
					&& current.getAttribute(valueName).equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private String getBaseConfig() {
		String baseConfig = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
				+ "<!DOCTYPE configuration \n"
				+ "  PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \n"
				+ "  \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n"
				+ "<configuration>\n"
				+ "	<properties>\n"
				+ "		<property name=\"driver\" value=\"com.mysql.jdbc.Driver\"/>\n"
				+ "		<property name=\"url\" value=\"jdbc:mysql://dev.strosoft.com:3306/stro_cms\"/>\n"
				+ "		<property name=\"username\" value=\"root\"/>\n"
				+ "		<property name=\"password\" value=\"happy0363\"/>\n"
				+ "	</properties>\n"
				+ "	<settings>\n"
				+ "		<setting name=\"callSettersOnNulls\" value=\"true\"/>\n"
				+ "		<!-- 打印查询语句 -->\n"
				+ "        <setting name=\"logImpl\" value=\"STDOUT_LOGGING\"/>\n"
				+ "	</settings>\n"
				+ "	<typeAliases>\n"
				+ "		<typeAlias alias=\"JSONObject\" type=\"net.sf.json.JSONObject\"/>\n"
				+ "		<typeAlias alias=\"LinkedHashMap\" type=\"java.util.LinkedHashMap\"/>\n"
				+ "  </typeAliases>\n"
				+ "	<environments default=\"development\">\n"
				+ "		<environment id=\"development\">\n"
				+ "			<transactionManager type=\"JDBC\"/>\n"
				+ "			<dataSource type=\"POOLED\">\n"
				+ "				<property name=\"driver\" value=\"${driver}\"/>\n"
				+ "				<property name=\"url\" value=\"${url}\"/>\n"
				+ "				<property name=\"username\" value=\"${username}\"/>\n"
				+ "				<property name=\"password\" value=\"${password}\"/>\n"
				+ "				<property name=\"poolPingQuery\" value=\"select now()\"/>\n"
				+ "				<property name=\"poolPingEnabled\" value=\"true\"/>\n"
				+ "			</dataSource>\n"
				+ "		</environment>\n"
				+ "	</environments>\n"
				+ "	<mappers>\n"
				+ "  </mappers>\n"
				+ "</configuration>";
		return baseConfig;
	}
}
