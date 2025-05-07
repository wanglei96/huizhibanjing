package strosoft.cg.common;

import java.util.List;

public class ServiceHanldelerFileInfo {
	private String fileName;
	private List<ServiceHandlerInfo> serviceHandlerInfos;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<ServiceHandlerInfo> getServiceHandlerInfos() {
		return serviceHandlerInfos;
	}
	public void setServiceHandlerInfos(List<ServiceHandlerInfo> serviceHandlerInfos) {
		this.serviceHandlerInfos = serviceHandlerInfos;
	}
	
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ServiceHandlers>");
		sb.append(getServiceHandlerInfosString());
		sb.append("</ServiceHandlers>");
		return sb.toString();
	}
	public String getServiceHandlerInfosString(){
		if(null == serviceHandlerInfos || serviceHandlerInfos.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<serviceHandlerInfos.size();i++){
			sb.append(serviceHandlerInfos.get(i).toCode());
		}
		return sb.toString();
	}

}
