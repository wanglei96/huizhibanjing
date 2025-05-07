package strosoft.sys.model;
/**
 * 发送验证应用信息线程，1秒一次直到成功
 * @author administrator
 *
 */
public class AddApplicationTaskManagerThread implements Runnable  {

	@Override
	public void run() {
		Boolean flag = false;
		while(!flag){
			try {
				String result = AuthenticationManager.getInstance().sendVerificationConfig();
				if(result.indexOf("success")>=0 && result.indexOf("true")>=0){
					flag = true;
				}
				System.out.println(result);
				if(!flag){
					System.out.println("添加验证信息失败");
				}
			} catch (Exception e) {
				System.out.println("发送添加验证信息请求失败");
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
