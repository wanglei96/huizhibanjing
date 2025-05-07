package strosoft.cms.model;

// 页面生成队列线程
public class CmsBuildTaskThread implements Runnable {
	
	@Override
	public void run() {
		try {
			//执行生成任务
			CmsBuildManager.getInstance().process();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//生成结束将此线程从CmsBuildTaskMonitorThread中的cmsBuildTaskThreadlList中移除;
			Thread currentThread = Thread.currentThread();
			CmsBuildTaskMonitorThread.cmsBuildTaskThreadlList.remove(currentThread);
		}
	}

}
