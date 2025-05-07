package strosoft.cg.common;

import strosoft.app.data.DataEntity;

import java.util.List;

public class ResultMapInfo  extends DataEntity{
	private String type;
	private String id;
	private List<ResultInfo> results;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ResultInfo> getResults() {
		return results;
	}
	public void setResults(List<ResultInfo> results) {
		this.results = results;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("\r  <resultMap type='");
		sb.append(this.type);
		sb.append("' id='");
		sb.append(this.id);
		sb.append("' >");
		for(int i=0;i<this.results.size();i++){
			ResultInfo resultInfo = this.results.get(i);
			sb.append(resultInfo.toCode());
		}
		sb.append("\r  </resultMap>");
		return sb.toString();
	}
	
}
