package BRP.model;

import BRP.data.AgeGroupMaster;
import strosoft.app.common.MyBatisManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AgeGroupManager extends AgeGroupMaster {
    private static AgeGroupManager _instance;

    private AgeGroupManager() {
        this.setTableName("age_group");
        this.setTypeName("AgeGroup");
    }

    public static AgeGroupManager getInstance() {
        if (_instance == null) {
            _instance = new AgeGroupManager();
        }
        return _instance;
    }

    public ArrayList<LinkedHashMap<String, Object>> getAgeGroupInfo(Integer companyId) throws Exception {
        String sql = String.format("SELECT\n" +
                "\tag.name AS age_group_name,\n" +
                "\tag.id AS age_group_id,\n" +
                "\tsg.language_code AS language_code,\n" +
                "\tsg.language_name AS language_name,\n" +
                "\tgroup_concat( DISTINCT book_tag.name SEPARATOR ', ' ) AS book_tag_names,\n" +
                "\tcount( DISTINCT sg.book_sku_id ) AS sku_count,\n" +
                "\tcount( DISTINCT b.id ) AS book_count \n" +
                "FROM age_group ag\n" +
                "\t\t\t\t\t\tLEFT JOIN view_book_sku_age_group sg ON \n" +
                "\t\t\t\t\t\t\t\tsg.age_group_id = ag.id and sg.company_id = %s\n" +
                "\t\t\t\t\tLEFT JOIN book b ON \n" +
                "\t\t\t\t\t\t\tsg.book_sku_id = b.book_sku_id \n" +
                "\t\t\t\tLEFT JOIN book_sku_book_tag bt ON \n" +
                "\t\t\t\t\t\tbt.book_sku_id = sg.book_sku_id \n" +
                "\t\t\tLEFT JOIN book_tag ON \n" +
                "\t\t\t\t\tbt.book_tag_id = book_tag.id \n" +
                "GROUP BY\n" +
                "\tag.name,ag.id,sg.language_code,sg.language_name\n" +
                "ORDER BY\n" +
                "\tsg.language_code,ag.display_order", companyId);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        if (alData != null) {
            return alData;
        }
        return null;
    }
}