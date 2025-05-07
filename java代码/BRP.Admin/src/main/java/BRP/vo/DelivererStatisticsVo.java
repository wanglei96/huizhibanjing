package BRP.vo;

import java.util.List;
import java.util.Map;

public class DelivererStatisticsVo {
    // 总配送单
    private Integer totalOrder;
    // 年度配送单
    private Integer yearOrder;
    // 月度配送单
    private Integer monthOrder;
    // 日配送单 趋势
    private Map<String, List<Integer>> dayOrderData;
    // 月配送单 趋势
    private Map<String, List<Integer>> monthOrderData;

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Integer getYearOrder() {
        return yearOrder;
    }

    public void setYearOrder(Integer yearOrder) {
        this.yearOrder = yearOrder;
    }

    public Integer getMonthOrder() {
        return monthOrder;
    }

    public void setMonthOrder(Integer monthOrder) {
        this.monthOrder = monthOrder;
    }

    public Map<String, List<Integer>> getDayOrderData() {
        return dayOrderData;
    }

    public void setDayOrderData(Map<String, List<Integer>> dayOrderData) {
        this.dayOrderData = dayOrderData;
    }

    public Map<String, List<Integer>> getMonthOrderData() {
        return monthOrderData;
    }

    public void setMonthOrderData(Map<String, List<Integer>> monthOrderData) {
        this.monthOrderData = monthOrderData;
    }
}
