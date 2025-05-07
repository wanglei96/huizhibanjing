package BRP.service;

import BRP.model.BookOrderManager;
import BRP.vo.DelivererStatisticsVo;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 配送员小程序 统计
 */
public class GetDeliverStatisticsServiceHandler  extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JSONObject jData = this.getRequestData(request);
        Integer delivererId = JsonHelper.getInt(jData,"delivererId");

        // 总配送单
        Integer totalOrder = BookOrderManager.getInstance().getCount("deliverer_id = " + delivererId);
        // 本年度配送单
        Integer yearOrder = BookOrderManager.getInstance().getCount("YEAR(deliver_time) = YEAR(CURRENT_TIME) and deliverer_id = " + delivererId);
        // 本月配送单
        Integer monthOrder = BookOrderManager.getInstance().getCount("YEAR(deliver_time) = YEAR(CURRENT_TIME) and MONTH(deliver_time) = MONTH(CURRENT_TIME) and deliverer_id = " + delivererId);
        // 日配送单走势
        // 获取当前月份
        List<Integer> daysList = new ArrayList<>();
        int month = new Date().getMonth();
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            for (int i = 1; i <= 31; i++) {
                daysList.add(i);
            }
        }else if (month == 2){
            for (int i = 1; i <= 28; i++) {
                daysList.add(i);
            }
        }else{
            for (int i = 1; i <= 30; i++) {
                daysList.add(i);
            }
        }
        List<Integer> dayListData = new ArrayList<>();
        for(int i = 1; i <= daysList.size(); i++){
            Integer dayCount = BookOrderManager.getInstance().getCount("YEAR(deliver_time) = YEAR(CURRENT_TIME) and MONTH(deliver_time) = MONTH(CURRENT_TIME) and DAY(CURRENT_TIME) = " + i + " and deliverer_id = " + delivererId);
            dayListData.add(dayCount);
        }
        Map<String, List<Integer>> dayOrderMap = new HashMap<>();
        dayOrderMap.put("xData", daysList);
        dayOrderMap.put("yData", dayListData);

        // 月配送单走势
        List<Integer> monthOrderList = new ArrayList<>();
        for(int i = 1; i <= 12; i++){
            Integer monthCount = BookOrderManager.getInstance().getCount("YEAR(deliver_time) = YEAR(CURRENT_TIME) and MONTH(deliver_time) = "+ i +" and deliverer_id = " + delivererId);
            monthOrderList.add(monthCount);
        }
        Map<String, List<Integer>> monthOrderMap = new HashMap<>();
        monthOrderMap.put("xData", new ArrayList<Integer>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);add(10);add(11);add(12);
        }});
        monthOrderMap.put("yData", monthOrderList);
        DelivererStatisticsVo delivererStatisticsVo = new DelivererStatisticsVo();
        delivererStatisticsVo.setTotalOrder(totalOrder);
        delivererStatisticsVo.setYearOrder(yearOrder);
        delivererStatisticsVo.setMonthOrder(monthOrder);
        delivererStatisticsVo.setDayOrderData(dayOrderMap);
        delivererStatisticsVo.setMonthOrderData(monthOrderMap);
        this.writeSuccessResponse(response, delivererStatisticsVo);
    }
}
