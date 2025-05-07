package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgeGroupBookTagManager extends AgeGroupBookTagMaster {
    private static AgeGroupBookTagManager _instance;

    private AgeGroupBookTagManager() {
        this.setTableName("age_group_book_tag");
        this.setTypeName("AgeGroupBookTag");
    }

    public static AgeGroupBookTagManager getInstance() {
        if (_instance == null) {
            _instance = new AgeGroupBookTagManager();
        }
        return _instance;
    }

    public void addAgeGroupBookTagByAgeGroupId(Integer ageGroupId, List<Integer> lstbookTagIds,
                                               String languageCode, Integer companyId) throws IOException {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            List<ViewBookSkuAgeGroup> lstBookSkuAgeGroup = ViewBookSkuAgeGroupManager.getInstance().
                    getListByAgeGroupId(session, ageGroupId, languageCode, companyId);
            List<Integer> lstBookSkuId = lstBookSkuAgeGroup.stream().map(ViewBookSkuAgeGroup::getBookSkuId)
                    .distinct().collect(Collectors.toList());
            String bookSkuIds = lstBookSkuId.stream().map(String::valueOf)
                    .collect(Collectors.joining(","));
            String deleteSql = "delete from book_sku_book_tag where book_sku_id in (" + bookSkuIds + ")";
            MyBatisManager.getInstance().executeDelete(session, deleteSql);
            List<BookSkuBookTag> newBookSkuBookTagList = new ArrayList<>();
            for (int i = 0; i < lstbookTagIds.size(); i++) {
                Integer bookTagId = lstbookTagIds.get(i);
                for (Integer bookSkuId : lstBookSkuId) {
                    BookSkuBookTag newBookSkuBookTag = new BookSkuBookTag();
                    newBookSkuBookTag.setBookTagId(bookTagId);
                    newBookSkuBookTag.setBookSkuId(bookSkuId);
                    newBookSkuBookTagList.add(newBookSkuBookTag);
                }
            }
            BookSkuBookTagManager.getInstance().addList(session, newBookSkuBookTagList);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    public void addAgeGroupBookTagByAgeGroupIdNew(Integer ageGroupId, List<Integer> lstbookTagIds,
                                                  String languageCode, Integer companyId) throws IOException {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            //删除年龄段下标签
            List<ViewAgeGroupBookTag> lstAgeGroupBookTag = ViewAgeGroupBookTagManager.getInstance().
                    getListByAgeGroupId(session, ageGroupId, languageCode, companyId);
            if (lstAgeGroupBookTag != null && lstAgeGroupBookTag.size() > 0) {
                String strAgeGroupBookTagId = lstAgeGroupBookTag.stream()
                        .map(item -> String.valueOf(item.getId()))
                        .collect(Collectors.joining(","));
                String deleteSql = "delete from age_group_book_tag where id in (" + strAgeGroupBookTagId + ")";
                MyBatisManager.getInstance().executeDelete(session, deleteSql);
            }
            if (lstbookTagIds != null && lstbookTagIds.size() > 0) {
                List<AgeGroupBookTag> newAgeGroupBookTagList = new ArrayList<>();
                for (int i = 0; i < lstbookTagIds.size(); i++) {
                    Integer bookTagId = lstbookTagIds.get(i);
                    AgeGroupBookTag newAgeGroupBookTag = new AgeGroupBookTag();
                    newAgeGroupBookTag.setAgeGroupId(ageGroupId);
                    newAgeGroupBookTag.setBookTagId(bookTagId);
                    newAgeGroupBookTagList.add(newAgeGroupBookTag);
                }
                AgeGroupBookTagManager.getInstance().addList(session, newAgeGroupBookTagList);
            }
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }
}