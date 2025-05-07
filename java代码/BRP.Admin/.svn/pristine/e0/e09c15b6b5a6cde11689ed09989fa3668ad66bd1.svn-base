package BRP.service;

import BRP.data.AgeGroup;
import BRP.data.AgeGroupMaster;
import BRP.data.HomeRandomBook;
import BRP.model.AgeGroupManager;
import BRP.model.HomeRandomBookManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class AddHomeAgeGroupAndAllRandomBookServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject requestData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(requestData, "companyId");
        Integer allCount = JsonHelper.getInt(requestData, "allCount");
        Integer ageGroupCount = JsonHelper.getInt(requestData, "ageGroupCount");

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            if (ageGroupCount != null) {
                //所有年龄段集合
                List<AgeGroup> ageGroupList = AgeGroupManager.getInstance().getList(sqlSession);
                //随机生成的各个年龄段下图书集合
                ArrayList<HomeRandomBook> newHomeRandomBookList = new ArrayList<>();
                for (int i = 0; i < ageGroupList.size(); i++) {
                    //随机生成图书ID查询Sql
                    String getBookSkuIdsSql = "SELECT book_sku_id from book_sku_age_group LEFT JOIN book_sku on book_sku_age_group.book_sku_id=book_sku.id " +
                            "where company_id=" + companyId + " and age_group_id=" + ageGroupList.get(i).getId() + "\n" +
                            "\tORDER BY RAND() LIMIT " + ageGroupCount;
                    //随机生成图书集合
                    ArrayList<LinkedHashMap<String, Object>> bookSkuMap = MyBatisManager.getInstance().executeHashMapList(sqlSession, getBookSkuIdsSql);
                    for (LinkedHashMap<String, Object> bookSku : bookSkuMap) {
                        HomeRandomBook newHomeRandomBook = new HomeRandomBook();
                        newHomeRandomBook.setBookSkuId((Integer) bookSku.get("book_sku_id"));
                        newHomeRandomBook.setCompanyId(companyId);
                        newHomeRandomBook.setAgeGroupId(ageGroupList.get(i).getId());
                        newHomeRandomBook.setCreateTime(new Timestamp(new Date().getTime()));
                        newHomeRandomBookList.add(newHomeRandomBook);
                    }
                }
                //添加随机生成的各个年龄段下图书
                HomeRandomBookManager.getInstance().addList(sqlSession, newHomeRandomBookList);
            }
            if (allCount != null) {
                //随机生成“全部”图书
                String sql = "INSERT INTO home_random_book(company_id,book_sku_id,create_time) SELECT company_id,id,NOW() from book_sku where company_id=" + companyId +
                        " ORDER BY RAND() LIMIT " + allCount;
                MyBatisManager.getInstance().executeInsert(sqlSession, sql);
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
            writeSuccessResponse(response);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
