package BRP.service;

import BRP.model.BookManager;
import BRP.model.CompanyManager;
import BRP.model.MemberManager;
import BRP.vo.DesktopStatisticsVo;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

/**
 * 桌面统计
 */
public class GetDesktopStatisticsListServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JSONObject jData = this.getRequestData(request);
        String type = JsonHelper.getString(jData, "type");

        // 声明近半年的数据集合
        List<Integer> yearList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now(); // 创建当前时间对象
        int currentYear = currentDate.getYear(); // 获取当前年份
        int currentMonth = currentDate.getMonthValue(); // 获取当前月份
        int currentDay = currentDate.getDayOfMonth(); // 获取当前日
        for (int i = 0; i < 6; i++) {
            int year = currentYear - (5 - i); // 计算相应的年份
            yearList.add(year);
        }

        // 声明十二个月的数据集合
        List<Integer> monthList = new ArrayList<>();
        for (int i = 1; i <= currentMonth; i++) {
            monthList.add(i);
        }

        // 声明每个月天数的数据集合
        List<Integer> dayList = new ArrayList<>();
        for (int i = 1; i <= currentDay; i++) {
            dayList.add(i);
        }
        List<Integer> list = new ArrayList<>();
        String condition = "";
        // 会员各年龄段分布
        String memberAgeGroupSql = "";
        // 图书各年龄段分布
        String booksAgeGroupSql = "";
        if (type.equals("year")) {
            list = yearList;
            condition = " and year(create_time) in ";
            memberAgeGroupSql = "SELECT year(birthday) birthday, count(1) count FROM member WHERE birthday IS NOT NULL GROUP BY year(birthday)";
            booksAgeGroupSql = "SELECT year(create_time) birthday, count(1) count FROM book WHERE create_time IS NOT NULL GROUP BY year(create_time)";
        } else if (type.equals("month")) {
            list = monthList;
            condition = " and year(create_time) = year(now()) and month(create_time) in ";
            memberAgeGroupSql = "SELECT month(birthday) birthday, count(1) count FROM member WHERE birthday IS NOT NULL GROUP BY month(birthday)";
            booksAgeGroupSql = "SELECT month(create_time) birthday, count(1) count FROM book WHERE create_time IS NOT NULL GROUP BY month(create_time)";
        } else if (type.equals("day")) {
            list = dayList;
            condition = " and year(create_time) = year(now()) and month(create_time) = month(now()) and day(create_time) in ";
            memberAgeGroupSql = "SELECT day(birthday) birthday, count(1) count FROM member WHERE birthday IS NOT NULL GROUP BY day(birthday)";
            booksAgeGroupSql = "SELECT day(create_time) birthday, count(1) count FROM book WHERE create_time IS NOT NULL GROUP BY day(create_time)";
        }
        List<Integer> companyNumberList = new ArrayList<>();
        List<Integer> memberNumberList = new ArrayList<>();
        List<Integer> addMemberNumberList = new ArrayList<>();
        List<Integer> booksNumberList = new ArrayList<>();
        List<Integer> installedBookboxNumberList = new ArrayList<>();
        List<Integer> inventoryBookboxNumberList = new ArrayList<>();
        String times = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                times += ",";
            }
            times += list.get(i);
            // 门店数量
            Integer companyNumber = CompanyManager.getInstance().getCount("1=1" + condition + "(" + times + ")");
            companyNumberList.add(companyNumber);
            // 会员数量
            Integer memberNumber = MemberManager.getInstance().getCount("1=1" + condition + "(" + times + ")");
            memberNumberList.add(memberNumber);
            // 新增会员数量
            Integer addMemberNumber = MemberManager.getInstance().getCount("1=1" + condition + "(" + list.get(i) + ")");
            addMemberNumberList.add(addMemberNumber);
            // 书本数量
            Integer booksNumber = BookManager.getInstance().getCount("1=1" + condition + "(" + times + ")");
            booksNumberList.add(booksNumber);
            // 已安装书箱
            Integer installedBookboxNumber = MemberManager.getInstance().getCount("member_bookbox_status_code = 'Installed'" + condition + "(" + times + ")");
            installedBookboxNumberList.add(installedBookboxNumber);
            // 库存书箱数量
            //Integer inventoryBookboxNumber = MemberManager.getInstance().getCount("member_bookbox_status_code = 'WaitReady'" + condition + "(" + times + ")");
            String sql = "select ifnull(sum(quantity),0) from view_company_product_stocks where product_code = 'Bookbox'" + condition + "(" + times + ")";
            Integer inventoryBookboxNumber = MyBatisManager.getInstance().executeInt(sql);
            inventoryBookboxNumberList.add(inventoryBookboxNumber);
        }

        DesktopStatisticsVo desktopStatisticsVo = new DesktopStatisticsVo();
        Map<String, List<Integer>> companyNumberMap = new HashMap();
        companyNumberMap.put("xData", list);
        companyNumberMap.put("yData", companyNumberList);
        desktopStatisticsVo.setCompanyNumber(companyNumberMap);

        Map<String, List<Integer>> memberNumberMap = new HashMap();
        memberNumberMap.put("xData", list);
        memberNumberMap.put("yData", memberNumberList);
        desktopStatisticsVo.setMemberNumber(memberNumberMap);

        Map<String, List<Integer>> addMemberNumberMap = new HashMap();
        addMemberNumberMap.put("xData", list);
        addMemberNumberMap.put("yData", addMemberNumberList);
        desktopStatisticsVo.setAddMemberNumber(addMemberNumberMap);

        Map<String, List<Integer>> booksNumberMap = new HashMap();
        booksNumberMap.put("xData", list);
        booksNumberMap.put("yData", booksNumberList);
        desktopStatisticsVo.setBooksNumber(booksNumberMap);

        Map<String, List<Integer>> installedBookboxNumberMap = new HashMap();
        installedBookboxNumberMap.put("xData", list);
        installedBookboxNumberMap.put("yData", installedBookboxNumberList);
        desktopStatisticsVo.setInstalledBookboxNumber(installedBookboxNumberMap);

        Map<String, List<Integer>> inventoryBookboxNumberMap = new HashMap();
        inventoryBookboxNumberMap.put("xData", list);
        inventoryBookboxNumberMap.put("yData", inventoryBookboxNumberList);
        desktopStatisticsVo.setInventoryBookboxNumber(inventoryBookboxNumberMap);

        // 会员各年龄段分布
        ArrayList<LinkedHashMap<String, Object>> memberAgeGroup = MyBatisManager.getInstance().executeHashMapList(memberAgeGroupSql);
        // 图书各年龄段分布
        ArrayList<LinkedHashMap<String, Object>> booksAgeGroup = MyBatisManager.getInstance().executeHashMapList(booksAgeGroupSql);
        // 门店会员数量排行前10统计
        String companyMemberGroupSql = "SELECT company.company_name companyName, \n" +
                "\t\t\t\tcount(1) count\n" +
                "FROM member_card \n" +
                "LEFT JOIN company ON company.id = member_card.company_id\n" +
                "WHERE member_id is not null AND disabled is not true\n" +
                "GROUP BY company_id,company.company_name \n" +
                "order by count desc limit 0,10";
        ArrayList<LinkedHashMap<String, Object>> companyMemberGroup = MyBatisManager.getInstance().executeHashMapList(companyMemberGroupSql);

        List<Map<String, String>> memberAgeGroupList = new ArrayList<>();
        for (Map map : memberAgeGroup) {
            memberAgeGroupList.add(new HashMap<String, String>() {{
                put("birthday", map.get("birthday").toString());
                put("count", map.get("count").toString());
            }});
        }
        desktopStatisticsVo.setMemberAgeGroup(memberAgeGroupList);
        List<Map<String, String>> booksAgeGroupList = new ArrayList<>();
        for (Map map : booksAgeGroup) {
            booksAgeGroupList.add(new HashMap<String, String>() {{
                put("birthday", map.get("birthday").toString());
                put("count", map.get("count").toString());
            }});
        }
        desktopStatisticsVo.setBooksAgeGroup(booksAgeGroupList);
        List<Map<String, String>> companyMemberGroupList = new ArrayList<>();
        for (Map map : companyMemberGroup) {
            String companyName = map.get("companyName") == null ? "" : map.get("companyName").toString();
            companyMemberGroupList.add(new HashMap<String, String>() {{
                put("companyName", companyName);
                put("count", map.get("count").toString());
            }});
        }
        desktopStatisticsVo.setCompanyMemberGroup(companyMemberGroupList);
        // 图书数量排行前十
        String booksNumberTopSql = "SELECT book_sku.book_name bookName, count( 1 ) count FROM book LEFT JOIN book_sku ON book_sku.id = book.book_sku_id GROUP BY book_sku_id order by count desc LIMIT 0, 10";
        ArrayList<LinkedHashMap<String, Object>> booksNumberTop = MyBatisManager.getInstance().executeHashMapList(booksNumberTopSql);
        List<Map<String, String>> booksNumberTopList = new ArrayList<>();
        for (Map map : booksNumberTop) {
            String bookName = map.get("bookName").toString();
            String count = map.get("count").toString();
            if (bookName.length() > 10) {
                bookName = bookName.substring(0, 10) + "...";
            }
            Map<String, String> bookMap = new LinkedHashMap<>();
            bookMap.put("bookName", bookName);
            bookMap.put("count", count);
            booksNumberTopList.add(bookMap);
        }
        desktopStatisticsVo.setBooksNumberTop(booksNumberTopList);
        this.writeSuccessResponse(response, desktopStatisticsVo);
    }
}
