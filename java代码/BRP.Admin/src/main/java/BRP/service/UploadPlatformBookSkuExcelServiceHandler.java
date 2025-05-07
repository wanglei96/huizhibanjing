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
import java.util.List;

public class UploadPlatformBookSkuExcelServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer uploadId = JsonHelper.getInt(jData, "uploadId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            SysUploadFile theSysUploadFile = SysUploadFileManager.getInstance().getEntity(sqlSession, uploadId);
            String path = SysConfig.getResourceDirectoryPath() + "/" + theSysUploadFile.getPath();
            this.batchUploadBookSkuExcel(sqlSession, path, companyId);

            this.writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }


    public void batchUploadBookSkuExcel(SqlSession sqlSession, String path, Integer companyId) throws Exception {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = null;
        String[] split = file.getName().split("\\.");
        if ("xls".equals(split[1])) {
            wb = new HSSFWorkbook(fis);
        } else if ("xlsx".equals(split[1])) {
            wb = new XSSFWorkbook(fis);
        }
        List<AgeGroup> ageGroupList = AgeGroupManager.getInstance().getList();
        List<BookSkuAgeGroup> newBookSkuAgeGroupList = new ArrayList<>();
        List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {// 循环Excel文件的i=1行开始
            BookSku newBookSku = new BookSku();
            Row row = sheet.getRow(i);
            if (BookSkuManager.getInstance().isEmptyRow(row)) {
                // 行为空，表示文件结束
                break;
            }
            newBookSku.setIsOnline(true);
            // 书名
            Cell bookName = row.getCell(0);
            String strBookName = BookSkuManager.getInstance().getCellValueByCell(bookName);

            Cell ISBN = row.getCell(7);
            String strISBN = BookSkuManager.getInstance().getCellValueByCell(ISBN);


            if (!StringHelper.isNullOrEmpty(strBookName) && !StringHelper.isNullOrEmpty(strISBN)) {
                String escapedBookName = strBookName.replace("'", "''"); // 将单引号替换为两个连续的单引号
                //判断是否存在
                String condition = String.format("book_name='%s' and ISBN='%s' and company_id is null", escapedBookName, strISBN);
                Integer count = BookSkuManager.getInstance().getCount(sqlSession, condition);
                if (count > 0) {
                    continue;
                }
                newBookSku.setBookName(strBookName);
                newBookSku.setISBN(strISBN);
            }

            // 系列
            Cell bookSeries = row.getCell(1);
            String strBookSeries = BookSkuManager.getInstance().getCellValueByCell(bookSeries);
            //获取系列ID
            if (!StringHelper.isNullOrEmpty(strBookSeries)) {

                Integer bookSeriesId = BookSeriesManager.getInstance().getBookSeriesId(sqlSession, strBookSeries);
                newBookSku.setBookSeriesId(bookSeriesId);
            }
            // 作者
            Cell author = row.getCell(2);
            String strAuthor = BookSkuManager.getInstance().getCellValueByCell(author);
            if (!StringHelper.isNullOrEmpty(strAuthor)) {

                newBookSku.setAuthor(strAuthor);
            }

            // 出版社
            Cell press = row.getCell(3);
            String strPress = BookSkuManager.getInstance().getCellValueByCell(press);
            if (!StringHelper.isNullOrEmpty(strPress)) {
                newBookSku.setPress(strPress);
            }

            //价格
            Cell price = row.getCell(4);
            String strPrice = BookSkuManager.getInstance().getCellValueByCell(price);
            if (!StringHelper.isNullOrEmpty(strPrice)) {
                BigDecimal decPrice = new BigDecimal(strPrice).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                newBookSku.setPrice(decPrice);
            }

            //分类
            Cell language = row.getCell(5);
            String strLanguage = BookSkuManager.getInstance().getCellValueByCell(language);
            if (!StringHelper.isNullOrEmpty(strLanguage)) {
                if ("1".equals(strLanguage)) {
                    newBookSku.setLanguageCode(LanguageCodes.Chinese);
                } else {
                    newBookSku.setLanguageCode(LanguageCodes.English);
                }
            }

            //备注
            Cell bindingType = row.getCell(6);
            String strBindingType = BookSkuManager.getInstance().getCellValueByCell(bindingType);
            if (!StringHelper.isNullOrEmpty(strBindingType)) {
                if ("精装".equals(strBindingType)) {
                    newBookSku.setBindingTypeCode(BindingTypeCodes.Hardcover);
                } else if ("简装".equals(strBindingType)) {
                    newBookSku.setBindingTypeCode(BindingTypeCodes.ConveniencePackage);
                }
            }

            //ISBN

            Integer newBookSkuId = BookSkuManager.getInstance().add(sqlSession, newBookSku);
            //本数
            /*Cell bookCount = row.getCell(8);
            String strBookCount = BookSkuManager.getInstance().getCellValueByCell(bookCount);
            Integer intBookCount = Integer.parseInt(strBookCount);
            newViewBookSkuInfo.setBookCount(intBookCount);
            newViewBookSkuInfo.setCompanyId(companyId);*/

            //0-2
            Cell twoAge = row.getCell(9);
            String strTwoAge = BookSkuManager.getInstance().getCellValueByCell(twoAge);
            if (!StringHelper.isNullOrEmpty(strTwoAge)) {
                AgeGroup theTwoAge = BookSkuManager.getInstance().getAgeGroupByName(ageGroupList, "0-2岁");
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                newBookSkuAgeGroup.setAgeGroupId(theTwoAge.getId());
                newBookSkuAgeGroup.setBookSkuId(newBookSkuId);
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            //3-6
            Cell threeAge = row.getCell(10);
            String strThreeAge = BookSkuManager.getInstance().getCellValueByCell(threeAge);
            if (!StringHelper.isNullOrEmpty(strThreeAge)) {
                AgeGroup theThreeAge = BookSkuManager.getInstance().getAgeGroupByName(ageGroupList, "3-6岁");
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                newBookSkuAgeGroup.setAgeGroupId(theThreeAge.getId());
                newBookSkuAgeGroup.setBookSkuId(newBookSkuId);
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            //7-10
            Cell sevenAge = row.getCell(11);
            String strSevenAge = BookSkuManager.getInstance().getCellValueByCell(sevenAge);
            if (!StringHelper.isNullOrEmpty(strSevenAge)) {
                AgeGroup theSevenAge = BookSkuManager.getInstance().getAgeGroupByName(ageGroupList, "7-10岁");
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                newBookSkuAgeGroup.setAgeGroupId(theSevenAge.getId());
                newBookSkuAgeGroup.setBookSkuId(newBookSkuId);
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            //11-14
            Cell elevenAge = row.getCell(12);
            String strElevenAge = BookSkuManager.getInstance().getCellValueByCell(elevenAge);
            if (!StringHelper.isNullOrEmpty(strElevenAge)) {
                AgeGroup theElevenAge = BookSkuManager.getInstance().getAgeGroupByName(ageGroupList, "11-14岁");
                BookSkuAgeGroup newBookSkuAgeGroup = new BookSkuAgeGroup();
                newBookSkuAgeGroup.setAgeGroupId(theElevenAge.getId());
                newBookSkuAgeGroup.setBookSkuId(newBookSkuId);
                newBookSkuAgeGroupList.add(newBookSkuAgeGroup);
            }
            //标签
            Cell bookTag = row.getCell(13);
            String strbookTag = BookSkuManager.getInstance().getCellValueByCell(bookTag);
            if (!StringHelper.isNullOrEmpty(strbookTag)) {
                String[] strTagList = strbookTag.split("\\s*(,|，)\\s*");
                for (String strTag : strTagList) {
                    //获取标签ID
                    Integer bookTagId = BookTagManager.getInstance().getBookTagByName(sqlSession, strTag);
                    BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                    newBookSkuBookTag.setBookTagId(bookTagId);
                    newBookSkuBookTag.setBookSkuId(newBookSkuId);
                    newBookSkuBookTagList.add(newBookSkuBookTag);
                }
            }
        }
        BookSkuAgeGroupManager.getInstance().addList(sqlSession, newBookSkuAgeGroupList);
        BookSkuBookTagManager.getInstance().addList(sqlSession, newBookSkuBookTagList);
    }
}
