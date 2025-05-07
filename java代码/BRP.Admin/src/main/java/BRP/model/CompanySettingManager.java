package BRP.model;

import BRP.data.CompanySetting;
import BRP.data.CompanySettingMaster;
import BRP.data.CompanySettingValueInfo;
import BRP.data.Holiday;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.sys.data.SysSetting;
import strosoft.sys.model.SysSettingManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CompanySettingManager extends CompanySettingMaster {
    private static CompanySettingManager _instance;

    private CompanySettingManager() {
        this.setTableName("company_setting");
        this.setTypeName("CompanySetting");
    }

    public static CompanySettingManager getInstance() {
        if (_instance == null) {
            _instance = new CompanySettingManager();
        }
        return _instance;
    }

    /**
     * 补充所有门店系统参数
     */
    public void completionCompanySetting(SqlSession sqlSession) throws Exception {
        //获取所有“商户”类型的系统参数
        String getCompanyCategorySettingListSql = "SELECT * from sys_setting where sys_setting_category_code='Company'";
        ArrayList<LinkedHashMap<String, Object>> companyCategorySettingList = MyBatisManager.getInstance().executeHashMapList(sqlSession, getCompanyCategorySettingListSql);
        //循环补充每个商户“商户”类型的系统参数
        if (!companyCategorySettingList.isEmpty()) {
            for (int i = 0; i < companyCategorySettingList.size(); i++) {
                LinkedHashMap<String, Object> sysSetting = companyCategorySettingList.get(i);
                String sql = "INSERT INTO company_setting(company_id,sys_setting_code,value) SELECT id,'" + sysSetting.get("code") + "','" + sysSetting.get("value") + "' from company where id not in (SELECT company_id from company_setting where sys_setting_code='" + sysSetting.get("code") + "')";
                MyBatisManager.getInstance().executeInsert(sqlSession, sql);
            }
        }
    }

    public void updateByCompanyAndCode(SqlSession sqlSession, Integer companyId, String sysSettingCode, String value) throws IOException {
        String sql = "UPDATE company_setting SET value='" + value + "' where company_id=" + companyId + " and sys_setting_code='" + sysSettingCode + "'";
        MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
    }

    /**
     * 检查门店是否休息
     *
     * @param session
     * @param companyId
     * @return
     */
    public Boolean checkTodayRest(SqlSession session, Integer companyId, CompanySettingValueInfo companySettingValueInfo) throws Exception {
        if (StringHelper.isNullOrEmpty(companySettingValueInfo.getSilentBorrowing()) ||
                companySettingValueInfo.getSilentBorrowing().equals("true")) {
            return true;
        }
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
        if (pauseMode.contains("Holiday")) {
            //判断当前日期是否是节假日
            this.checkHoliday(session, companySettingValueInfo);
        }
        if (pauseMode.contains("Temp")) {
            //判断是否是临时休息
            this.checkTemp(session, companySettingValueInfo, calendar);
        }
        if (pauseMode.contains("Weekly")) {
            //判断今日是否休息
            this.checkWeekly(session, companySettingValueInfo, calendar);
        }
        return true;
    }

    /**
     * 检擦是否休息
     *
     * @param session
     * @param companySettingValueInfo
     * @param calendar
     */
    private void checkWeekly(SqlSession session, CompanySettingValueInfo companySettingValueInfo, Calendar calendar) throws Exception {
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
                throw new Exception(companySettingValueInfo.getBorrowingPrompt());
            }
        }
    }

    /**
     * 检查是否存在临时闭店时间段
     *
     * @param session
     * @param companySettingValueInfo
     */
    private void checkTemp(SqlSession session, CompanySettingValueInfo companySettingValueInfo, Calendar calendar) throws Exception {
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
                throw new Exception(companySettingValueInfo.getTempBorrowingPrompt());
            }
        }
    }

    /**
     * 检查今日是否是节假日
     *
     * @param session
     * @param companySettingValueInfo
     */
    private void checkHoliday(SqlSession session, CompanySettingValueInfo companySettingValueInfo) throws Exception {
        //获取法定节假日列表
        List<Holiday> lstHoliday = HolidayManager.getInstance().getList(session);

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
            throw new Exception(companySettingValueInfo.getHolidayBorrowingPrompt());
        }
    }

    /**
     * 构造门店参数
     *
     * @param companySettingList
     * @return
     */
    public CompanySettingValueInfo getCompanySettingValueInfo(List<CompanySetting> companySettingList) {
        CompanySettingValueInfo companySettingValueInfo = new CompanySettingValueInfo();
        for (CompanySetting companySetting : companySettingList) {
            switch (companySetting.getSysSettingCode()) {
                case "LongDistanceRrderDeliveryTime":
                    companySettingValueInfo.setLongDistanceRrderDeliveryTime(companySetting.getValue());
                    break;
                case "ReturnButNotBorrowed":
                    companySettingValueInfo.setReturnButNotBorrowed(companySetting.getValue());
                    break;
                case "PauseMode":
                    companySettingValueInfo.setPauseMode(companySetting.getValue());
                    break;
                case "PauseDayArr":
                    companySettingValueInfo.setPauseDayArr(companySetting.getValue());
                    break;
                case "PauseDateRangeArr":
                    companySettingValueInfo.setPauseDateRangeArr(companySetting.getValue());
                    break;
                case "BorrowingPrompt":
                    companySettingValueInfo.setBorrowingPrompt(companySetting.getValue());
                    break;
                case "SilentBorrowing":
                    companySettingValueInfo.setSilentBorrowing(companySetting.getValue());
                    break;
                case "HolidayBorrowingPrompt":
                    companySettingValueInfo.setHolidayBorrowingPrompt(companySetting.getValue());
                    break;
                case "TempBorrowingPrompt":
                    companySettingValueInfo.setTempBorrowingPrompt(companySetting.getValue());
                    break;
            }
        }
        return companySettingValueInfo;
    }

    public String getCompanySettingValueByCode(SqlSession session, Integer companyId, String borrowingPrompt) {
        String condition = "company_id=" + companyId + " and sys_setting_code='" + borrowingPrompt + "'";
        List<CompanySetting> companySettingList = getList(session, condition);
        if (companySettingList.size() > 0) {
            return companySettingList.get(0).getValue();
        } else {
            return null;
        }
    }

    public String getCompanySettingValueByCode(Integer companyId, String borrowingPrompt) throws Exception {
        String condition = "company_id=" + companyId + " and sys_setting_code='" + borrowingPrompt + "'";
        List<CompanySetting> companySettingList = getList(condition);
        if (companySettingList.size() > 0) {
            return companySettingList.get(0).getValue();
        } else {
            return null;
        }
    }
}