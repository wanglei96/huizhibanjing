package strosoft.app.common;

import org.w3c.dom.Node;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 计划任务
 */
public class ScheduledTask {

    public Node getConfigNode() {
        return configNode;
    }

    public void setConfigNode(Node configNode) {
        this.configNode = configNode;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private int delay = 0;//定时器限时启动时间
    private int period;//定时器周期
    private Node configNode;//配置节点
    private Timer timer;

    /**
     * 启动
     */
    public void start() {
        ScheduledTask self = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                self.process();
            }
        };
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(timerTask, this.getDelay(), this.getPeriod());
    }

    /**
     * 停止
     */
    public void stop(){
        if(this.timer!= null){
            this.timer.cancel();
        }
    }
    /**
     * 处理定时任务
     */
    protected void process() {
    }
}
