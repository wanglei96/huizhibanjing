package strosoft.app.model;

import java.util.Date;
import java.util.LinkedHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServiceMonitor {
	private LinkedHashMap<String, ServiceStatus> hmServiceStatus = new LinkedHashMap<String, ServiceStatus>();
	private Long currentServiceStatusItemID = 0L;

	private static ServiceMonitor _instance;

	public static ServiceMonitor getInstance() {
		if (_instance == null) {
			_instance = new ServiceMonitor();
		}
		return _instance;
	}

	private ServiceMonitor() {
	}

	/**
	 * 添加服务开始
	 * 
	 * @param serviceName
	 */
	public Long addServiceBegin(String serviceName) {
		ServiceStatus serviceStatus;
		if (this.hmServiceStatus.containsKey(serviceName)) {
			serviceStatus = this.hmServiceStatus.get(serviceName);
		} else {
			serviceStatus = new ServiceStatus(serviceName);
			this.hmServiceStatus.put(serviceName, serviceStatus);
		}
		ServiceStatusItem newServiceStatusItem = new ServiceStatusItem();
		newServiceStatusItem.setStartTime(new Date());
		this.currentServiceStatusItemID++;
		serviceStatus.getServiceStatusItems().put(this.currentServiceStatusItemID, newServiceStatusItem);
		return this.currentServiceStatusItemID;
	}

	/**
	 * 添加服务结束
	 * 
	 * @param serviceName
	 */
	public void addServiceEnd(Long serviceStatusItemID, String serviceName) {
		ServiceStatus theServiceStatus = this.hmServiceStatus.get(serviceName);
		ServiceStatusItem theServiceStatusItem = theServiceStatus.getServiceStatusItems().get(serviceStatusItemID);
		// 成功
		theServiceStatusItem.setSuccess(true);
		// 设置结束时间
		theServiceStatusItem.setEndTime(new Date());
		// 设置执行时间
		long processTime = theServiceStatusItem.getEndTime().getTime() - theServiceStatusItem.getStartTime().getTime();
		theServiceStatusItem.setProcessTime(processTime);
		// 设置最大执行时间
		long maxProcessTime = theServiceStatus.getMaxProcessTime();
		if (maxProcessTime < processTime) {
			theServiceStatus.setMaxProcessTime(processTime);
		}
		// 设置最小执行时间
		long minProcessTime = theServiceStatus.getMinProcessTime();
		if (minProcessTime > processTime) {
			theServiceStatus.setMinProcessTime(processTime);
		}
		// 设置总执行时间
		long totalProcessTime = theServiceStatus.getTotalProcessTime() + processTime;
		theServiceStatus.setTotalProcessTime(totalProcessTime);
		// 设置平均执行时间
		double avgProcessTime = totalProcessTime / theServiceStatus.getServiceStatusItems().size();
		theServiceStatus.setAvgProcessTime(avgProcessTime);
		// 成功次数
		long successCount = theServiceStatus.getSuccessCount();
		theServiceStatus.setSuccessCount(successCount + 1);

	}

	/**
	 * 添加服务错误
	 * 
	 * @param serviceName
	 * @param e
	 */
	public void addServiceError(Long serviceStatusItemID, String serviceName, Exception e) {
		ServiceStatus theServiceStatus = this.hmServiceStatus.get(serviceName);
		ServiceStatusItem theServiceStatusItem = theServiceStatus.getServiceStatusItems().get(serviceStatusItemID);
		// 失败
		theServiceStatusItem.setSuccess(false);
		// 设置结束时间
		theServiceStatusItem.setEndTime(new Date());
		// 设置执行时间
		long processTime = theServiceStatusItem.getEndTime().getTime() - theServiceStatusItem.getStartTime().getTime();
		theServiceStatusItem.setProcessTime(processTime);
		// 设置最大执行时间
		long maxProcessTime = theServiceStatus.getMaxProcessTime();
		if (maxProcessTime < processTime) {
			theServiceStatus.setMaxProcessTime(processTime);
		}
		// 设置最小执行时间
		long minProcessTime = theServiceStatus.getMinProcessTime();
		if (minProcessTime > processTime) {
			theServiceStatus.setMinProcessTime(processTime);
		}
		// 设置总执行时间
		long totalProcessTime = theServiceStatus.getTotalProcessTime() + processTime;
		theServiceStatus.setTotalProcessTime(totalProcessTime);
		// 设置平均执行时间
		double avgProcessTime = totalProcessTime / theServiceStatus.getServiceStatusItems().size();
		theServiceStatus.setAvgProcessTime(avgProcessTime);
		// 失败次数
		long errorCount = theServiceStatus.getErrorCount();
		theServiceStatus.setSuccessCount(errorCount + 1);

	}

	/**
	 * 取得服务状态列表
	 * 
	 * @return
	 */
	public JSONArray getServiceStatusList() {
		JSONArray jarrServiceStatus = new JSONArray();
		for (String serviceName : this.hmServiceStatus.keySet()) {
			ServiceStatus theServiceStatus = this.hmServiceStatus.get(serviceName);
			JSONObject jServiceStatus = theServiceStatus.toJSONObject();
			jarrServiceStatus.add(jServiceStatus);
		}
		return jarrServiceStatus;
	}

	/**
	 * 清空服务状态
	 */
	public void clearServiceStatus() {
		this.hmServiceStatus = new LinkedHashMap<String, ServiceStatus>();
	}
}
