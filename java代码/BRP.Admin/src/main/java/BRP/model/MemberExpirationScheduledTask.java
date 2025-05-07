package BRP.model;

import BRP.data.MessageTypeCodes;
import org.slf4j.LoggerFactory;
import strosoft.app.common.ScheduledTask;
import strosoft.main.boot.JAppApplication;

import java.time.LocalTime;

public class MemberExpirationScheduledTask extends ScheduledTask {
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
                MessageManager.getInstance().sendMemberExpirationMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
