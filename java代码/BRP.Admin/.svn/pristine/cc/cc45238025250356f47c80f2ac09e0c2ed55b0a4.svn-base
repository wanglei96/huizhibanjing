package BRP.service;

import BRP.data.CompanySetting;
import BRP.data.CompanySettingValueInfo;
import BRP.data.Holiday;
import BRP.model.BookTagManager;
import BRP.model.CompanySettingManager;
import BRP.model.HolidayManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GetCompanyOpenStatusServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Boolean isRest = this.getCompanyOpenStatus(companyId);
        this.writeSuccessResponse(response, isRest);
    }

    private Boolean getCompanyOpenStatus(Integer companyId) throws Exception {
        String condition = "company_id=" + companyId;
        List<CompanySetting> companySettingList = CompanySettingManager.getInstance().getList(condition);
        CompanySettingValueInfo companySettingValueInfo = CompanySettingManager.getInstance().getCompanySettingValueInfo(companySettingList);
        //获取暂停模式
        String pauseMode = companySettingValueInfo.getPauseMode();
        if (StringHelper.isNullOrEmpty(pauseMode)) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        TimeZone chinaTimeZone = TimeZone.getTimeZone("Asia/Shanghai"); // 代表中国/上海时区
        calendar.setTimeZone(chinaTimeZone);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(chinaTimeZone);
        if (pauseMode.contains("Weekly")) {
            //判断今日是否休息
            if (!this.checkWeekly(companySettingValueInfo, calendar)) {
                return false;
            }
        }
        if (pauseMode.contains("Temp")) {
            //判断是否是临时休息
            if (!this.checkTemp(companySettingValueInfo, calendar)) {
                return false;
            }
        }
        if (pauseMode.contains("Holiday")) {
            //判断当前日期是否是节假日
            if (!this.checkHoliday(companySettingValueInfo)) {
                return false;
            }
        }


        return true;
    }

    /**
     * 检擦是否休息
     *
     * @param companySettingValueInfo
     * @param calendar
     */
    private Boolean checkWeekly(CompanySettingValueInfo companySettingValueInfo, Calendar calendar) throws Exception {
        String pauseDayArr = companySettingValueInfo.getPauseDayArr();
        if (!StringHelper.isNullOrEmpty(pauseDayArr)) {
            String[] pauseDayArrs = pauseDayArr.split("[,，]");
            // 获取当前日期的星期几
            int currentWeekday = calendar.get(Calendar.DAY_OF_WEEK);
            // 判断当前星期是否在数组里
            boolean isRestDay = false;
            for (String day : pauseDayArrs) {
                if (Integer.parseInt(day) == currentWeekday) {
                    isRestDay = true;
                    break;
                }
            }
            if (isRestDay) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查是否存在临时闭店时间段
     *
     * @param companySettingValueInfo
     */
    private Boolean checkTemp(CompanySettingValueInfo companySettingValueInfo, Calendar calendar) throws Exception {
        String pauseDateRangeArr = companySettingValueInfo.getPauseDateRangeArr();
        if (!StringHelper.isNullOrEmpty(pauseDateRangeArr)) {
            String[] pauseDateRange = pauseDateRangeArr.split("[,，]");
            // 获取当前日期
            Date currentDateTime = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateString = sdf.format(currentDateTime);
            Date currentDate = sdf.parse(currentDateString);
            // 判断当前日期是否在休息日期范围内
            Date startDate = sdf.parse(pauseDateRange[0]);
            Date endDate = sdf.parse(pauseDateRange[1]);
            boolean isRestDay = currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0;
            if (isRestDay) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查今日是否是节假日
     *
     * @param companySettingValueInfo
     */
    private Boolean checkHoliday(CompanySettingValueInfo companySettingValueInfo) throws Exception {
        //获取法定节假日列表
        List<Holiday> lstHoliday = HolidayManager.getInstance().getList();

        // 创建一个存放所有 vacation 的列表
        ArrayList<String> vacationList = new ArrayList<>();

        // 遍历节假日列表，获取每个节假日的 vacation 并添加到列表中
        for (Holiday holiday : lstHoliday) {
            String vacation = holiday.getVacation();
            String[] vacations = vacation.split("\\|");
            Collections.addAll(vacationList, vacations);
        }
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        // 判断当前日期是否为节假日
        if (vacationList.contains(formattedDate)) {
            return false;
        }
        return true;
    }

}
