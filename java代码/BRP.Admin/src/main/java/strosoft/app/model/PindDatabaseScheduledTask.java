package strosoft.app.model;

import org.slf4j.LoggerFactory;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.ScheduledTask;
import strosoft.main.boot.JAppApplication;

public class PindDatabaseScheduledTask extends ScheduledTask {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        String sql = "SELECT 1 from dual";
        try {
            MyBatisManager.getInstance().executeInt(sql);
            logger.info("database ping!");
            System.out.println("database ping!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
