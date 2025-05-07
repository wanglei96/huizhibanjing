package BRP.model;

import BRP.data.BookSeries;
import BRP.data.BookTag;
import BRP.data.BookTagMaster;
import BRP.data.ViewBookSkuBookTag;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BookTagManager extends BookTagMaster {
    private static BookTagManager _instance;

    private BookTagManager() {
        this.setTableName("book_tag");
        this.setTypeName("BookTag");
    }

    public static BookTagManager getInstance() {
        if (_instance == null) {
            _instance = new BookTagManager();
        }
        return _instance;
    }

    public ArrayList<LinkedHashMap<String, Object>> getAllBookTagByAgeGroupId(Integer companyId, Integer ageGroupId, String languageCode) throws Exception {
        String sql = String.format("SELECT book_tag.*, CASE WHEN bt.book_tag_id IS NOT NULL THEN true ELSE false END AS has_book_tag\n" +
                        "FROM book_tag\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT DISTINCT book_tag_id\n" +
                        "    FROM book_sku_book_tag\n" +
                        "    WHERE book_sku_id IN (\n" +
                        "        SELECT DISTINCT book_sku_id\n" +
                        "        FROM view_book_sku_age_group\n" +
                        "        WHERE age_group_id = %s AND company_id = %s %s \n" +
                        "    )\n" +
                        ") AS bt ON book_tag.id = bt.book_tag_id\n" +
                        "WHERE book_tag.company_id = %s;",
                ageGroupId, companyId,
                StringHelper.isNullOrEmpty(languageCode) ? "" : String.format(" and language_code = '%s'", languageCode)
                , companyId);
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        return lhmData;
    }

    public ArrayList<LinkedHashMap<String, Object>> getBookTagByAgeGroupId(Integer companyId, Integer ageGroupId, String languageCode) throws Exception {
        String sql = String.format("SELECT DISTINCT t.*\n" +
                        "FROM view_book_tag t\n" +
                        "LEFT JOIN book_sku_book_tag st ON t.id = st.book_tag_id\n" +
                        "LEFT JOIN book_sku_age_group ag ON st.book_sku_id = ag.book_sku_id where t.is_visible is true  %s %s %s",
                companyId != null ? String.format(" and t.company_id = %s", companyId) : "and t.company_id=(SELECT id FROM company where is_default is true) ",
                StringHelper.isNullOrEmpty(languageCode) ? "" : String.format(" and t.language_code = '%s'", languageCode),
                ageGroupId != -1 ? String.format(" and ag.age_group_id = %s", ageGroupId) : ""
        );
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        return lhmData;
    }

    public Integer getBookTagByName(SqlSession sqlSession, String strTag) {
        String condition = "company_id is null and name='" + strTag + "'";
        List<BookTag> lstBookTag = this.getList(sqlSession, condition);
        if (lstBookTag != null && lstBookTag.size() > 0) {
            return lstBookTag.get(0).getId();
        } else {
            BookTag newBookTag = new BookTag();
            newBookTag.setName(strTag);
            newBookTag.setFrontName(strTag);
            newBookTag.setIsVisible(true);
            newBookTag.setLanguageCode("Chinese");
            Integer id = this.add(sqlSession, newBookTag);
            return id;
        }
    }

    public Integer getBookTagByNameCompanyId(SqlSession sqlSession, Integer companyId, String strTag) {
        String condition = "company_id=" + companyId + " and name='" + strTag + "'";
        List<BookTag> lstBookTag = this.getList(sqlSession, condition);
        if (lstBookTag != null && lstBookTag.size() > 0) {
            return lstBookTag.get(0).getId();
        } else {
            BookTag newBookTag = new BookTag();
            newBookTag.setName(strTag);
            newBookTag.setFrontName(strTag);
            newBookTag.setIsVisible(true);
            newBookTag.setCompanyId(companyId);
            newBookTag.setLanguageCode("Chinese");
            Integer id = this.add(sqlSession, newBookTag);
            return id;
        }
    }

    public Integer getBookTagByNameCompanyId(SqlSession sqlSession, Integer companyId, ViewBookSkuBookTag viewBookSkuBookTag) {
        String condition = "company_id=" + companyId + " and name='" + viewBookSkuBookTag.getBookTagName() + "'";
        List<BookTag> lstBookTag = this.getList(sqlSession, condition);
        if (lstBookTag != null && lstBookTag.size() > 0) {
            return lstBookTag.get(0).getId();
        } else {
            BookTag newBookTag = new BookTag();
            newBookTag.setName(viewBookSkuBookTag.getBookTagName());
            newBookTag.setFrontName(viewBookSkuBookTag.getBookTagName());
            newBookTag.setIsVisible(true);
            newBookTag.setCompanyId(companyId);
            newBookTag.setDisplayOrder(viewBookSkuBookTag.getDisplayOrder());
            if (!StringHelper.isNullOrEmpty(viewBookSkuBookTag.getLanguageCode())) {
                newBookTag.setLanguageCode(viewBookSkuBookTag.getLanguageCode());
            } else {
                newBookTag.setLanguageCode("Chinese");
            }
            Integer id = this.add(sqlSession, newBookTag);
            return id;
        }
    }

    public void combineBookTag(Integer keepTagId, List<Integer> deleteTagIds) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String strDeleteTagIds = deleteTagIds.stream().map(Object::toString).collect(Collectors.joining(","));
            String sql = String.format("update book_sku_book_tag set book_tag_id = %d where book_tag_id in (%s)", keepTagId, strDeleteTagIds);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            this.batchDelete(sqlSession, deleteTagIds);
            String deleteSql = "DELETE t1\n" +
                    "FROM book_sku_book_tag t1\n" +
                    "JOIN book_sku_book_tag t2 ON t1.book_sku_id = t2.book_sku_id \n" +
                    "    AND t1.book_tag_id = t2.book_tag_id \n" +
                    "    AND t1.id > t2.id;";
            MyBatisManager.getInstance().executeDelete(sqlSession, deleteSql);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    public ArrayList<LinkedHashMap<String, Object>> getNewAllBookTagByAgeGroupId(Integer companyId, Integer ageGroupId, String languageCode) throws Exception {
        String sql = String.format("SELECT book_tag.*, \n" +
                        "CASE WHEN age_group_book_tag.book_tag_id IS NOT NULL THEN true ELSE false END AS has_book_tag\n" +
                        "FROM book_tag\n" +
                        "LEFT JOIN age_group_book_tag ON book_tag.id = age_group_book_tag.book_tag_id AND age_group_id = %d\n" +
                        "WHERE book_tag.company_id = %d %s \n" +
                        "ORDER BY CASE WHEN book_tag.display_order IS NULL THEN 1 ELSE 0 END, book_tag.display_order ASC, book_tag.create_time DESC;",
                ageGroupId, companyId,
                StringHelper.isNullOrEmpty(languageCode) ? "" : String.format(" and language_code = '%s'", languageCode)
                , companyId);
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        return lhmData;
    }
}