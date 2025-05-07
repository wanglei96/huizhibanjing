package BRP.data;

public class BookOrderItemInfo extends ViewBookOrderItem{
    private Long hours;
    private Long minutes;
    private Long seconds;
    private Boolean isCancelled;
    private String createTimeText;

    private String addCreateTimeText;

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public String getCreateTimeText() {
        return createTimeText;
    }

    public void setCreateTimeText(String createTimeText) {
        this.createTimeText = createTimeText;
    }

    public String getAddCreateTimeText() {
        return addCreateTimeText;
    }

    public void setAddCreateTimeText(String addCreateTimeText) {
        this.addCreateTimeText = addCreateTimeText;
    }
}
