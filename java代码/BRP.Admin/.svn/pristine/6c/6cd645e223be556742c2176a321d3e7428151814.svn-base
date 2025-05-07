package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 获取14天借阅人数统计信息
 */
public class GetFourteenBookOrderTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String dateType = JsonHelper.getString(jData, "dateType");
        //获取数据
        ArrayList<LinkedHashMap<String, Object>> alData = this.getData(companyId, dateType);

        JSONArray jResult = JsonHelper.toJSONArray(alData);
        this.writeSuccessResponse(response, jResult);
    }

    private ArrayList<LinkedHashMap<String, Object>> getData(Integer companyId, String dateType) throws Exception {
        String companyIdSql = "";
        if (companyId != null) {
            companyIdSql = " and bo.company_id =" + companyId;
        }
        String sql = "";
        if ("FourteenDay".equals(dateType)) {
            sql = "SELECT\n" +
                    "                    DATE_FORMAT(date_range.date, '%Y-%m-%d') AS order_date,\n" +
                    "                     CASE\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Monday' THEN '星期一'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Tuesday' THEN '星期二'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Wednesday' THEN '星期三'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Thursday' THEN '星期四'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Friday' THEN '星期五'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Saturday' THEN '星期六'\n" +
                    "                            WHEN DATE_FORMAT(date_range.date, '%W') = 'Sunday' THEN '星期日'\n" +
                    "                        END AS week_day,\n" +
                    "                        COALESCE(offline_count, 0) AS offline_count,\n" +
                    "                        COALESCE(online_count, 0) AS online_count\n" +
                    "                    FROM\n" +
                    "                        (\n" +
                    "                            SELECT\n" +
                    "                                CURDATE() - INTERVAL n DAY AS date\n" +
                    "                            FROM\n" +
                    "                                (\n" +
                    "                                    SELECT 0 AS n UNION ALL\n" +
                    "                                    SELECT 1 UNION ALL\n" +
                    "                                    SELECT 2 UNION ALL\n" +
                    "                                    SELECT 3 UNION ALL\n" +
                    "                                    SELECT 4 UNION ALL\n" +
                    "                                    SELECT 5 UNION ALL\n" +
                    "                                    SELECT 6 UNION ALL\n" +
                    "                                    SELECT 7 UNION ALL\n" +
                    "                                    SELECT 8 UNION ALL\n" +
                    "                                    SELECT 9 UNION ALL\n" +
                    "                                    SELECT 10 UNION ALL\n" +
                    "                                    SELECT 11 UNION ALL\n" +
                    "                                    SELECT 12 UNION ALL\n" +
                    "                                    SELECT 13 UNION ALL\n" +
                    "                                    SELECT 14\n" +
                    "                                ) AS numbers\n" +
                    "                        ) AS date_range\n" +
                    "                    LEFT JOIN\n" +
                    "                        (\n" +
                    "                            SELECT\n" +
                    "subquery.create_date,\n" +
                    "SUM(CASE WHEN subquery.is_offline = true THEN 1 ELSE 0 END) AS offline_count,\n" +
                    "SUM(CASE WHEN subquery.is_offline IS NOT true THEN 1 ELSE 0 END) AS online_count\n" +
                    "FROM (\n" +
                    "SELECT\n" +
                    "DATE(bo.create_time) AS create_date,\n" +
                    "bo.member_id,\n" +
                    "bo.is_offline\n" +
                    "FROM\n" +
                    "book_order bo\n" +
                    "JOIN\n" +
                    "book_order_item boi ON bo.id = boi.book_order_id\n" +
                    "WHERE\n" +
                    "bo.create_time >= CURDATE() - INTERVAL 14 DAY " + companyIdSql + "\n" +
                    "AND boi.book_order_type_code = 'Borrow'\n" +
                    "GROUP BY\n" +
                    "DATE(bo.create_time), bo.member_id, bo.is_offline\n" +
                    ") AS subquery\n" +
                    "GROUP BY\n" +
                    "subquery.create_date\n" +
                    "                        ) AS orders ON date_range.date = orders.create_date\n" +
                    "                    ORDER BY\n" +
                    "                        date_range.date;";
        } else if ("Year".equals(dateType)) {
            sql = "SELECT\n" +
                    "\tDATE_FORMAT( date_range.date, '%Y-%m' ) AS order_date,\n" +
                    "\tCOALESCE ( offline_count, 0 ) AS offline_count,\n" +
                    "\tCOALESCE ( online_count, 0 ) AS online_count \n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\tSELECT\n" +
                    "\t\tCURDATE() - INTERVAL n MONTH AS date \n" +
                    "\tFROM\n" +
                    "\t\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t0 AS n UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t1 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t2 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t3 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t4 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t5 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t6 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t7 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t8 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t9 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t10 UNION ALL\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t11 \n" +
                    "\t\t) AS numbers \n" +
                    "\t) AS date_range\n" +
                    "\tLEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tsubquery.create_time_month,\n" +
                    "\t\tSUM( CASE WHEN subquery.is_offline = TRUE THEN 1 ELSE 0 END ) AS offline_count,\n" +
                    "\t\tSUM( CASE WHEN subquery.is_offline IS NOT TRUE THEN 1 ELSE 0 END ) AS online_count \n" +
                    "\tFROM\n" +
                    "\t\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tDATE_FORMAT( bo.create_time, '%Y-%m' ) AS create_time_month,\n" +
                    "\t\t\tbo.member_id,\n" +
                    "\t\t\tbo.is_offline \n" +
                    "\t\tFROM\n" +
                    "\t\t\tbook_order bo\n" +
                    "\t\t\tJOIN book_order_item boi ON bo.id = boi.book_order_id \n" +
                    "\t\tWHERE\n" +
                    "\t\t\tbo.create_time >= CURDATE() - INTERVAL 1 YEAR \n" +
                    "\t\t\tAND boi.book_order_type_code = 'Borrow' " + companyIdSql + " \n" +
                    "\t\tGROUP BY\n" +
                    "\t\t\tDATE_FORMAT( bo.create_time, '%Y-%m' ),\n" +
                    "\t\t\tbo.member_id,\n" +
                    "\t\t\tbo.is_offline \n" +
                    "\t\t) AS subquery \n" +
                    "\tGROUP BY\n" +
                    "\t\tsubquery.create_time_month \n" +
                    "\t) AS orders ON DATE_FORMAT( date_range.date, '%Y-%m' ) = orders.create_time_month \n" +
                    "ORDER BY\n" +
                    "\tdate_range.date;";
        } else if ("Month".equals(dateType)) {
            sql = "SELECT\n" +
                    "                        DATE_FORMAT(CURDATE() - INTERVAL n DAY, '%Y-%m-%d') AS order_date,\n" +
                    "                        COALESCE(offline_count, 0) AS offline_count,\n" +
                    "                        COALESCE(online_count, 0) AS online_count\n" +
                    "                    FROM\n" +
                    "                        (\n" +
                    "                            SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL\n" +
                    "                            SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL\n" +
                    "                            SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL\n" +
                    "                            SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL\n" +
                    "                            SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL\n" +
                    "                            SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL\n" +
                    "                            SELECT 24 UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL\n" +
                    "                            SELECT 28 UNION ALL SELECT 29\n" +
                    "                        ) numbers\n" +
                    "                    LEFT JOIN\n" +
                    "                        (\n" +
                    "                            SELECT\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tsubquery.create_date,\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSUM(CASE WHEN subquery.is_offline = true THEN 1 ELSE 0 END) AS offline_count,\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSUM(CASE WHEN subquery.is_offline IS NOT true THEN 1 ELSE 0 END) AS online_count\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSELECT\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDATE(bo.create_time) AS create_date,\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tbo.member_id,\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tbo.is_offline\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tbook_order bo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tJOIN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tbook_order_item boi ON bo.id = boi.book_order_id\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tbo.create_time >= CURDATE() - INTERVAL 29 DAY " + companyIdSql + "\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND boi.book_order_type_code = 'Borrow'\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tGROUP BY\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDATE(bo.create_time), bo.member_id, bo.is_offline\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) AS subquery\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tGROUP BY\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tsubquery.create_date\n" +
                    "                        ) AS orders ON DATE_FORMAT(CURDATE() - INTERVAL n DAY, '%Y-%m-%d') = orders.create_date\n" +
                    "                    ORDER BY\n" +
                    "                        order_date;";
        }
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        return alData;
    }

}
