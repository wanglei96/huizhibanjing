package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 配送单拣书单走势统计
 */
public class GetBookOutOrderStatisticsServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        if(delivererId == null){
            this.writeErrorResponse(response, "delivererId不允许为空");
            return;
        }
        // 声明十二个月的数据集合
        List<Integer> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }

        // 声明每个月天数的数据集合
        List<Integer> dayList = new ArrayList<>();
        // 获取当前月天数
        Calendar calendar = Calendar.getInstance(); // 创建当前时间对象
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInMonth; i++) {
            dayList.add(i);
        }
        List<Integer> bookOutDayNumberList = new ArrayList<>();
        List<Integer> bookOutMonthNumberList = new ArrayList<>();

        for (int i = 0; i < dayList.size(); i++) {
            int number = MyBatisManager.getInstance().executeInt("SELECT count(DISTINCT book_order_id) count FROM `view_book_out_order` where bookpicker_id = " + delivererId
                    + " and book_out_order_status_code = 'Found' and year(now()) = year(deliver_time)"
                    + " and month(now()) = month(deliver_time) and day(deliver_time) = " + dayList.get(i) + " group by day(deliver_time)");
            bookOutDayNumberList.add(number);
        }
        for (int i = 0; i < monthList.size(); i++) {
            int number = MyBatisManager.getInstance().executeInt("SELECT count(DISTINCT book_order_id) count FROM `view_book_out_order` where bookpicker_id = " + delivererId
                    + " and book_out_order_status_code = 'Found' and year(now()) = year(deliver_time) and month(deliver_time) = " + monthList.get(i) + " group by month(deliver_time)");
            bookOutMonthNumberList.add(number);
        }
        int jianshuMonth = MyBatisManager.getInstance().executeInt("SELECT count(DISTINCT book_order_id) count FROM `view_book_out_order` where bookpicker_id = " + delivererId
                + " and book_out_order_status_code = 'Found' and year(now()) = year(deliver_time) and month(deliver_time) = month(now())");

        int jianshuYear = MyBatisManager.getInstance().executeInt("SELECT count(DISTINCT book_order_id) count FROM `view_book_out_order` where bookpicker_id = " + delivererId
                + " and book_out_order_status_code = 'Found' and year(now()) = year(deliver_time)");

        int jianshuSum = MyBatisManager.getInstance().executeInt("SELECT count(DISTINCT book_order_id) count FROM `view_book_out_order` where bookpicker_id = " + delivererId
                + " and book_out_order_status_code = 'Found'");

        Map<String, List<Integer>> dayNumberMap = new HashMap();
        dayNumberMap.put("xData", dayList);
        dayNumberMap.put("yData", bookOutDayNumberList);

        Map<String, List<Integer>> monthNumberMap = new HashMap();
        monthNumberMap.put("xData", monthList);
        monthNumberMap.put("yData", bookOutMonthNumberList);

        HashMap<String, Object> jianshumap = new HashMap<>();
        jianshumap.put("dayData",dayNumberMap);
        jianshumap.put("monthData",monthNumberMap);
        jianshumap.put("jianshuYear",jianshuYear);
        jianshumap.put("jianshuMonth",jianshuMonth);
        jianshumap.put("jianshuSum",jianshuSum);

        List<Integer> bookInDayNumberList = new ArrayList<>();
        List<Integer> bookInMonthNumberList = new ArrayList<>();
        for (int i = 0; i < dayList.size(); i++) {
            int number = MyBatisManager.getInstance().executeInt("SELECT count(1) count FROM view_book_order where deliverer_id = " + delivererId
                    + " and book_order_borrow_status_code in ('Received','ErrorProcessing') and year(now()) = year(deliver_time) and month(now()) = month(deliver_time) and day(deliver_time) = " + dayList.get(i));
            bookInDayNumberList.add(number);
        }
        for (int i = 0; i < monthList.size(); i++) {
            int number = MyBatisManager.getInstance().executeInt("SELECT count(1) count FROM view_book_order where deliverer_id = " + delivererId
                    + " and book_order_borrow_status_code in ('Received','ErrorProcessing') and year(now()) = year(deliver_time) and month(deliver_time) = " + monthList.get(i));
            bookInMonthNumberList.add(number);
        }

        int peisongYear = MyBatisManager.getInstance().executeInt("SELECT count(1) count FROM view_book_order where deliverer_id = " + delivererId
                + " and book_order_borrow_status_code in ('Received','ErrorProcessing') and year(now()) = year(deliver_time)");

        int peisongMonth = MyBatisManager.getInstance().executeInt("SELECT count(1) count FROM view_book_order where deliverer_id = " + delivererId
                + " and book_order_borrow_status_code in ('Received','ErrorProcessing') and year(deliver_time) = year(now()) and month(deliver_time) = month(now())");

        int peisongSum = MyBatisManager.getInstance().executeInt("SELECT count(1) count FROM view_book_order where deliverer_id = " + delivererId + " and book_order_borrow_status_code in ('Received','ErrorProcessing')");

        Map<String, List<Integer>> dayNumberMap1 = new HashMap();
        dayNumberMap1.put("xData", dayList);
        dayNumberMap1.put("yData", bookInDayNumberList);

        Map<String, List<Integer>> monthNumberMap1 = new HashMap();
        monthNumberMap1.put("xData", monthList);
        monthNumberMap1.put("yData", bookInMonthNumberList);

        HashMap<String, Object> peisongmap = new HashMap<>();
        peisongmap.put("dayData",dayNumberMap1);
        peisongmap.put("monthData",monthNumberMap1);
        peisongmap.put("peisongYear",peisongYear);
        peisongmap.put("peisongMonth",peisongMonth);
        peisongmap.put("peisongSum",peisongSum);

        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("peisongData",peisongmap);
        map1.put("jianshuData",jianshumap);
        this.writeSuccessResponse(response, map1);
    }
}
