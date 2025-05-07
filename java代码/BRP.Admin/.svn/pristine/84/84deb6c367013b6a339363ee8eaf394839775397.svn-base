package BRP.model;

import BRP.data.*;
import BRP.data.MemberScoreType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.ScheduledTask;
import strosoft.app.util.DateHelper;
import strosoft.main.boot.JAppApplication;
import strosoft.sys.model.SysSettingManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddSettlementOrderScheduledTask extends ScheduledTask {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        try {
            //添加结算记录
            this.addSettlementOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addSettlementOrder() throws IOException {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            //获取业务参数
            Integer cycle = SysSettingManager.getInstance().getIntSetting(session, "Cycle", 7);
            LocalDate today = LocalDate.now();
            int dayOfMonth = today.getDayOfMonth();
            if (dayOfMonth == cycle) {
                BigDecimal nightStoryRate = SysSettingManager.getInstance().getDecimalSetting(session, "NightStoryRate", new BigDecimal(10));
                BigDecimal storyVideoRate = SysSettingManager.getInstance().getDecimalSetting(session, "StoryVideoRate", new BigDecimal(10));
                BigDecimal clueActivityRate = SysSettingManager.getInstance().getDecimalSetting(session, "ClueActivityRate", new BigDecimal(10));
                //获取所有一周未结算的零售单
                String condition = "DATE(business_date) BETWEEN DATE_SUB(LAST_DAY(CURDATE()), INTERVAL 2 MONTH) + INTERVAL 1 DAY \n" +
                        "AND LAST_DAY(CURDATE() - INTERVAL 1 MONTH) AND is_statistics is not true\n" +
                        "AND retail_order_status_code = 'Paid'";
                List<RetailOrder> lstRetailOrder = RetailOrderManager.getInstance().getList(
                        session, condition, "business_date desc");
                if (lstRetailOrder != null && lstRetailOrder.size() > 0) {
                    Map<Integer, List<RetailOrder>> retailOrderMap = lstRetailOrder.stream()
                            .collect(Collectors.groupingBy(RetailOrder::getCompanyId));
                    List<SettlementOrder> lstSettlementOrder = new ArrayList<>();
                    retailOrderMap.forEach((companyId, item) -> {
                        Comparator<RetailOrder> compareByBusinessDate = Comparator.comparing(RetailOrder::getBusinessDate);
                        item.sort(compareByBusinessDate);
                        BigDecimal allAmount = BigDecimal.ZERO;
                        BigDecimal platformAmount = BigDecimal.ZERO;
                        BigDecimal companyAmount = BigDecimal.ZERO;
                        SettlementOrder newSettlementOrder = new SettlementOrder();
                        StringBuilder idBuilder = new StringBuilder();
                        for (int i = 0; i < item.size(); i++) {
                            RetailOrder retailOrder = item.get(i);
                            // 将 id 拼接到 StringBuilder 中
                            idBuilder.append(retailOrder.getId());
                            // 如果不是最后一个元素，添加逗号分隔符
                            if (i < item.size() - 1) {
                                idBuilder.append(",");
                            }
                            //计算结算金额
                            BigDecimal amount = retailOrder.getAmount();
                            allAmount = allAmount.add(amount);
                            BigDecimal rate = BigDecimal.ZERO;
                            if (retailOrder.getNightStoryDays() != null && retailOrder.getNightStoryDays() > 0) {
                                rate = nightStoryRate.divide(new BigDecimal(100));
                            }
                            if (retailOrder.getStoryVideoId() != null && retailOrder.getStoryVideoId() > 0) {
                                rate = storyVideoRate.divide(new BigDecimal(100));
                            }
                            if (retailOrder.getClubActivityId() != null && retailOrder.getClubActivityId() > 0) {
                                rate = clueActivityRate.divide(new BigDecimal(100));
                            }
                            platformAmount = platformAmount.add(amount.multiply(rate));
                            companyAmount = companyAmount.add(amount.multiply(BigDecimal.ONE.subtract(rate)));
                        }

                        // 获取当前日期
                        LocalDate currentDate = LocalDate.now();

                        // 获取上个月的年份和月份
                        int lastMonthYear = currentDate.minusMonths(1).getYear();
                        int lastMonthMonth = currentDate.minusMonths(1).getMonthValue();

                        // 获取上个月的第一天和最后一天
                        LocalDate firstDayOfLastMonth = LocalDate.of(lastMonthYear, lastMonthMonth, 1);
                        LocalDate lastDayOfLastMonth = YearMonth.of(lastMonthYear, lastMonthMonth).atEndOfMonth();

                        // 转换为Timestamp类型
                        Timestamp firstDayTimestamp = Timestamp.valueOf(firstDayOfLastMonth.atStartOfDay());
                        Timestamp lastDayTimestamp = Timestamp.valueOf(lastDayOfLastMonth.atStartOfDay());


                        newSettlementOrder.setCompanyId(companyId);
                        newSettlementOrder.setAmount(allAmount);
                        newSettlementOrder.setBusinessDate(DateHelper.getCurrentTimestamp());
                        newSettlementOrder.setSettlementOrderStatusCode(SettlementOrderStatusCodes.Settling);
                        newSettlementOrder.setCompanyAmount(companyAmount);
                        newSettlementOrder.setPlatformAmount(platformAmount);
                        newSettlementOrder.setBeginDate(firstDayTimestamp);
                        newSettlementOrder.setEndDate(lastDayTimestamp);
                        newSettlementOrder.setRetailOrderIds(idBuilder.toString());
                        lstSettlementOrder.add(newSettlementOrder);
                    });
                    SettlementOrderManager.getInstance().addList(session, lstSettlementOrder);
                    String strRetailOrderIds = lstRetailOrder.stream().map(RetailOrder::getId).map(String::valueOf).collect(Collectors.joining(","));
                    String sql = "update retail_order set is_statistics=1 where id in (" + strRetailOrderIds + ")";
                    MyBatisManager.getInstance().executeUpdate(session, sql);
                }
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
