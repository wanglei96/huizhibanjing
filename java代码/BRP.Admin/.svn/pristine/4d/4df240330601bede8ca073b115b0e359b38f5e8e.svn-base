package strosoft.app.model;

import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

/**
 * 服务状态
 * 
 * @author Admin
 *
 */
public class ServiceStatus {
	private String serviceName;
	private Double avgProcessTime;
	private long maxProcessTime;
	private long minProcessTime = 0;
	private long totalProcessTime;
	private long successCount;
	private long errorCount;
	private LinkedHashMap<Long, ServiceStatusItem> serviceStatusItems = new LinkedHashMap<Long, ServiceStatusItem>();

	public ServiceStatus(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getAvgProcessTime() {
		return avgProcessTime;
	}

	public void setAvgProcessTime(Double avgProcessTime) {
		this.avgProcessTime = avgProcessTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public LinkedHashMap<Long, ServiceStatusItem> getServiceStatusItems() {
		return serviceStatusItems;
	}

	public void setServiceStatusItems(LinkedHashMap<Long, ServiceStatusItem> serviceStatusItems) {
		this.serviceStatusItems = serviceStatusItems;
	}

	public long getMinProcessTime() {
		return minProcessTime;
	}

	public void setMinProcessTime(long minProcessTime) {
		this.minProcessTime = minProcessTime;
	}

	public long getMaxProcessTime() {
		return maxProcessTime;
	}

	public void setMaxProcessTime(long maxProcessTime) {
		this.maxProcessTime = maxProcessTime;
	}

	public long getTotalProcessTime() {
		return totalProcessTime;
	}

	public void setTotalProcessTime(long totalProcessTime) {
		this.totalProcessTime = totalProcessTime;
	}

	/**
	 * 转成JSON对象
	 * 
	 * @return
	 */
	public JSONObject toJSONObject() {
		JSONObject jResult = new JSONObject();
		jResult.put("serviceName", this.getServiceName());
		jResult.put("avgProcessTime", this.getAvgProcessTime());
		jResult.put("maxProcessTime", this.getMaxProcessTime());
		jResult.put("minProcessTime", this.getMinProcessTime());
		jResult.put("totalProcessTime", this.getTotalProcessTime());
		jResult.put("successCount", this.getSuccessCount());
		jResult.put("errorCount", this.getErrorCount());
		return jResult;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	public long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

}
