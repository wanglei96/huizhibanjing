package strosoft.sys.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.SysConfig;
import strosoft.app.service.GetListServiceHandlerContext;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.DocumentHelper;
import strosoft.app.util.FileHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.XmlDocumentHelper;

/**
 * 导出处理类
 * 
 * @author Admin
 *
 */
public class ExportHandler {
	private String exportName;
	private Node configNode;

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public Node getConfigNode() {
		return configNode;
	}

	public void setConfigNode(Node configNode) {
		this.configNode = configNode;
	}

	public void process(ExportContext context) throws Exception {
		// 读取数据
		Object objData = this.getData(context);
		context.setData(objData);
		// 生成Excel文件
		this.createExcel(context);
	}

	protected void createExcel(ExportContext context) throws IOException {
		// 文件名称
		String title = XmlDocumentHelper.getAttribute(this.configNode, "Title");
		context.setFileName(title + ".xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		context.setWorkbook(workbook);
		// 创建sheet页
		XSSFSheet sheet = workbook.createSheet(title);
		context.setSheet(sheet);
		// 导出表头
		this.exportHeader(context);
		// 导出表体
		this.exportBody(context);
		// 保存文件
		this.saveExcel(context);

	}

	protected void exportBody(ExportContext context) {
		ArrayList<LinkedHashMap<String, Object>> lstData = (ArrayList<LinkedHashMap<String, Object>>) context.getData();

		XSSFSheet sheet = context.getSheet();
		NodeList nlColumn = DocumentHelper.selectNodes(this.configNode, "Columns/Column");
		for (int i = 0; i < lstData.size(); i++) {
			LinkedHashMap<String, Object> currentDataItem = lstData.get(i);
			XSSFRow headerRow = sheet.createRow(i + 1);
			for (int columnIndex = 0; columnIndex < nlColumn.getLength(); columnIndex++) {
				Node currentNode = nlColumn.item(columnIndex);
				String columnName = XmlDocumentHelper.getAttribute(currentNode, "SourceColumnName");
				Object objValue = currentDataItem.get(columnName);
				XSSFCell cell = headerRow.createCell(columnIndex);
				if (objValue != null) {
					cell.setCellValue(objValue.toString());
				}
			}
		}
	}

	/**
	 * 保存到Excel
	 * 
	 * @param context
	 * @return
	 * @throws IOException
	 */
	private void saveExcel(ExportContext context) throws IOException {
		String encoding = "UTF-8";
		FileOutputStream fos = null;
		try {
			String fileName = StringHelper.getUUID32() + ".xlsx";
			String absoluteFilePath = SysConfig.getAbsoluteUploadDirectory() + "/" + fileName;
			File file = FileHelper.createFile(absoluteFilePath);
			fos = new FileOutputStream(file);
			XSSFWorkbook workbook = context.getWorkbook();
			workbook.write(fos);
			fos.flush();
			String relativeFilePath = SysConfig.getUploadDirectory() + "/" + fileName;
			context.setFilePath(relativeFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 导出表头
	 * 
	 * @param context
	 */
	private void exportHeader(ExportContext context) {
		XSSFSheet sheet = context.getSheet();
		XSSFRow headerRow = sheet.createRow(0);
		NodeList nlColumn = DocumentHelper.selectNodes(this.configNode, "Columns/Column");
		for (int i = 0; i < nlColumn.getLength(); i++) {
			Node currentNode = nlColumn.item(i);
			String columnName = XmlDocumentHelper.getAttribute(currentNode, "TargetColumnName");
			headerRow.createCell(i).setCellValue(columnName);
		}
	}

	/**
	 * 取得数据
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected Object getData(ExportContext context) throws Exception {
		GetListServiceHandlerContext getListContext = this.getGetListContext(context.getRequest());
		ArrayList<LinkedHashMap<String, Object>> lstData = ServiceHandlerHelper.getDataList(getListContext);
		return lstData;
	}

	/*
	 * 取得参数
	 */
	protected GetListServiceHandlerContext getGetListContext(HttpServletRequest request) throws Exception {
		Node configNode = this.getConfigNode();
		String condition = ServiceHandlerHelper.getGetListCondition(request, configNode);
		GetListServiceHandlerContext context = ServiceHandlerHelper.getGetListServiceHandlerContext(request, configNode,
				condition);
		return context;
	}
}
