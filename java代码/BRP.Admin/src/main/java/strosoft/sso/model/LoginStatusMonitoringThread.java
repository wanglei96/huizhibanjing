package strosoft.sso.model;

public class LoginStatusMonitoringThread implements Runnable{
	
	@Override
	public void run() {
		Integer checkInertval = ActiveUserManager.getInstance().getCheckInertval();
		while(true){
			try{
				ActiveUserManager.getInstance().CheckSelf();
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("登录状态循环监测失败");
			}
			try {
				Thread.sleep(checkInertval*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
