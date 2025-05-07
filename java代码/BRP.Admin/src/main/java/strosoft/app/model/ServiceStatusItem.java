package strosoft.app.model;

import java.util.Date;

/**
 * 服务状态项
 * 
 * @author Admin
 *
 */
public class ServiceStatusItem {
	private Date startTime;
	private Date endTime;
	private Long processTime;
	private Boolean success;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
