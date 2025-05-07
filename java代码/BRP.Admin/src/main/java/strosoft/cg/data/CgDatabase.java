package strosoft.cg.data;

import java.util.List;

import strosoft.app.data.DataEntity;

public class CgDatabase  extends DataEntity{
	private String database;
	private List<String> tables;
	private List<String> views;
	private List<String> procedures;
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public List<String> getTables() {
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public List<String> getViews() {
		return views;
	}
	public void setViews(List<String> views) {
		this.views = views;
	}
	public List<String> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<String> procedures) {
		this.procedures = procedures;
	}
}
