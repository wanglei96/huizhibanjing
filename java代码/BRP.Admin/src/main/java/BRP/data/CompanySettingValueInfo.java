package BRP.data;

public class CompanySettingValueInfo {
    private Integer CompanyId;//商户ID
    private String ReturnButNotBorrowed;//只还未借是否配送
    private String PauseMode;//暂停模式
    private String PauseDayArr;//暂停时间（每周）
    private String PauseDateRangeArr;//暂停时间（临时）
    private String BorrowingPrompt;//借书时的提示
    private String LongDistanceRrderDeliveryTime;//远距离订单配送时间（每周几)
    private String SilentBorrowing;//暂停期间，静默开启借书
    private String TempBorrowingPrompt;//临时闭馆借书时提示语
    private String HolidayBorrowingPrompt;//法定节假日借书时提示语


    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public String getReturnButNotBorrowed() {
        return ReturnButNotBorrowed;
    }

    public void setReturnButNotBorrowed(String returnButNotBorrowed) {
        ReturnButNotBorrowed = returnButNotBorrowed;
    }

    public String getPauseMode() {
        return PauseMode;
    }

    public void setPauseMode(String pauseMode) {
        PauseMode = pauseMode;
    }

    public String getPauseDayArr() {
        return PauseDayArr;
    }

    public void setPauseDayArr(String pauseDayArr) {
        PauseDayArr = pauseDayArr;
    }

    public String getPauseDateRangeArr() {
        return PauseDateRangeArr;
    }

    public void setPauseDateRangeArr(String pauseDateRangeArr) {
        PauseDateRangeArr = pauseDateRangeArr;
    }

    public String getBorrowingPrompt() {
        return BorrowingPrompt;
    }

    public void setBorrowingPrompt(String borrowingPrompt) {
        BorrowingPrompt = borrowingPrompt;
    }

    public String getLongDistanceRrderDeliveryTime() {
        return LongDistanceRrderDeliveryTime;
    }

    public void setLongDistanceRrderDeliveryTime(String longDistanceRrderDeliveryTime) {
        LongDistanceRrderDeliveryTime = longDistanceRrderDeliveryTime;
    }

    public String getSilentBorrowing() {
        return SilentBorrowing;
    }

    public void setSilentBorrowing(String silentBorrowing) {
        SilentBorrowing = silentBorrowing;
    }

    public String getTempBorrowingPrompt() {
        return TempBorrowingPrompt;
    }

    public void setTempBorrowingPrompt(String tempBorrowingPrompt) {
        TempBorrowingPrompt = tempBorrowingPrompt;
    }

    public String getHolidayBorrowingPrompt() {
        return HolidayBorrowingPrompt;
    }

    public void setHolidayBorrowingPrompt(String holidayBorrowingPrompt) {
        HolidayBorrowingPrompt = holidayBorrowingPrompt;
    }
}
