package strosoft.cms.model;

import java.util.ArrayList;
import java.util.List;

// 页面生成检测线程
public class CmsBuildTaskMonitorThread implements Runnable {
	public static List<Thread> cmsBuildTaskThreadlList = new ArrayList<Thread>();
	@Override
	public void run() {
		try {
			CmsBuildManager cmsBuildManager =  CmsBuildManager.getInstance();
			while (true) {
				ArrayList<CmsBuildTask> lstCmsBuildTask = cmsBuildManager.getLstCmsBuildTask();
				//当有任务且线程列表中没有线程时，创建线程、执行任务
				while (lstCmsBuildTask.size() > 0 && cmsBuildTaskThreadlList.size() == 0) {
					try {
						//将生成标志设为真
						cmsBuildManager.setBuilding(true);
						//创建多线程
						//int availProcessors = Runtime.getRuntime().availableProcessors();
						createCmsBuildTaskManagerThreadList(5);
						//启动多线程
						runCmsBuildTaskThreadList();
					} catch (Exception e) {
						//生成遇到异常时
						e.printStackTrace();
					}
				}
				//线程列表个数为0，说明没有生成任务
				if(cmsBuildTaskThreadlList.size() == 0){
					cmsBuildManager.setViewCmsChannelInfos(null);
					cmsBuildManager.setBuilding(false);
				}
				// 线程休眠1秒
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createCmsBuildTaskManagerThreadList(Integer threadNum){
		//延时100ms创建线程，等待任务全部添加
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer length = CmsBuildManager.getInstance().getLstCmsBuildTask().size();
		threadNum = threadNum>length?length:threadNum;
		for(int i=0;i<threadNum;i++){
			CmsBuildTaskThread cmsBuildTaskManagerThread = new CmsBuildTaskThread();
			Thread thead = new Thread(cmsBuildTaskManagerThread);
			cmsBuildTaskThreadlList.add(thead);
		}
	}
	
	public void runCmsBuildTaskThreadList(){
		for(int i=0;i<cmsBuildTaskThreadlList.size();i++){
			Thread t =cmsBuildTaskThreadlList.get(i);
			t.start();
		}
	}
}
