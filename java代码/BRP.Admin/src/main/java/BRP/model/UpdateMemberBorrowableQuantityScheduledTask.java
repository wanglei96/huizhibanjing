package BRP.model;

import BRP.data.MemberBorrowQuantityLog;
import org.slf4j.LoggerFactory;
import strosoft.app.common.ScheduledTask;
import strosoft.main.boot.JAppApplication;

import java.time.LocalTime;

public class UpdateMemberBorrowableQuantityScheduledTask extends ScheduledTask {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        try {
            //修改会员可借书数量
            MemberBorrowQuantityLogManager.getInstance().updateMemberBorrowableQuantity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
