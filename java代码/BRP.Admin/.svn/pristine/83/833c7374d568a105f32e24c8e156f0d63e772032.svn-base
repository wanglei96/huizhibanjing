package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.ScheduledTask;
import strosoft.main.boot.JAppApplication;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookBorrowedReturnScheduledTask extends ScheduledTask {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        try {
            LocalTime currentTime = LocalTime.now();
            int currentHour = currentTime.getHour();
            int currentMinute = currentTime.getMinute();
            boolean isMidnight = currentHour == 9 && currentMinute == 0;
            if (isMidnight) {
                MessageManager.getInstance().sendBookBorrowedReturnMessage(MessageTypeCodes.FifteenNotReturned);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
