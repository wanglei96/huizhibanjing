package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.SysUploadFileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UploadPlatformBookTagExcelServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer uploadId = JsonHelper.getInt(jData, "uploadId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            SysUploadFile theSysUploadFile = SysUploadFileManager.getInstance().getEntity(sqlSession, uploadId);
            String path = SysConfig.getResourceDirectoryPath() + "/" + theSysUploadFile.getPath();
            this.batchUploadBookTagExcel(sqlSession, path);
            MyBatisManager.getInstance().commitSession(sqlSession);
            this.writeSuccessResponse(response);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }


    public void batchUploadBookTagExcel(SqlSession sqlSession, String path) throws Exception {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = null;
        String[] split = file.getName().split("\\.");
        if ("xls".equals(split[1])) {
            wb = new HSSFWorkbook(fis);
        } else if ("xlsx".equals(split[1])) {
            wb = new XSSFWorkbook(fis);
        }
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {// 循环Excel文件的i=1行开始
            Row row = sheet.getRow(i);
            if (BookSkuManager.getInstance().isEmptyRow(row)) {
                // 行为空，表示文件结束
                break;
            }
            // Id
            Cell id = row.getCell(0);
            String strBookTagId = BookSkuManager.getInstance().getCellValueByCell(id);
            Integer bookTagId = Integer.parseInt(strBookTagId);
            // 标签
            Cell oldBookTagName = row.getCell(1);
            String strOldBookTagName = BookSkuManager.getInstance().getCellValueByCell(oldBookTagName);
            //保留
            Cell retainBookTagName = row.getCell(2);
            String strRetainBookTagName = BookSkuManager.getInstance().getCellValueByCell(retainBookTagName);
            if (!StringHelper.isNullOrEmpty(strRetainBookTagName)) {
                continue;
            }
            //替换
            Cell changeBookTagName = row.getCell(3);
            String strChangeBookTagName = BookSkuManager.getInstance().getCellValueByCell(changeBookTagName);
            if (StringHelper.isNullOrEmpty(strChangeBookTagName)
                    && StringHelper.isNullOrEmpty(strRetainBookTagName)) {
                BookTagManager.getInstance().delete(sqlSession, bookTagId);
                continue;
            }

            // 将书标签按逗号分割后加上单引号
            String[] tagArray = strChangeBookTagName.split("[，,]");
            String strChangeBookTagNameWithQuotes = Arrays.stream(tagArray)
                    .map(tag -> String.format("'%s'", tag.trim()))
                    .collect(Collectors.joining(","));

            List<BookTag> lstBookTag = BookTagManager.getInstance().getList(
                    sqlSession, String.format(" name in (%s) and company_id is null", strChangeBookTagNameWithQuotes));

            //根据ID获取书标签
            List<BookSkuBookTag> lstBookSkuBookTag = BookSkuBookTagManager.getInstance().getList(
                    sqlSession, String.format(" book_tag_id = %s", bookTagId));
            for (BookSkuBookTag bookSkuBookTag : lstBookSkuBookTag) {
                //判断bookSkuBookTag是否存在lstBookTag里的标签
                for (BookTag bookTag : lstBookTag) {
                    String condition = String.format("book_sku_id = %s and book_tag_id = %s",
                            bookSkuBookTag.getBookSkuId(), bookTag.getId());
                    if (BookSkuBookTagManager.getInstance().getCount(sqlSession, condition) == 0) {
                        BookSkuBookTag bookSkuBookTagNew = new BookSkuBookTag();
                        bookSkuBookTagNew.setBookTagId(bookTag.getId());
                        bookSkuBookTagNew.setBookSkuId(bookSkuBookTag.getBookSkuId());
                        BookSkuBookTagManager.getInstance().add(sqlSession, bookSkuBookTagNew);
                    }
                }
            }
            BookTagManager.getInstance().delete(sqlSession, bookTagId);
        }
    }
}
