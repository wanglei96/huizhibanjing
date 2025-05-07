package strosoft.sys.data;

/**
 * 数据库备份
 * @author Admin
 *
 */
public class DatabaseBackup {
	private String id;
	private String name;
	private String time;
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DatabaseBackup(String id, String name, String time,String description) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.description=description;
	}
	public DatabaseBackup(String id) {
		super();
		this.id = id;
	}
	public DatabaseBackup() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "SqlFile [id=" + id + ", name=" + name + ", time=" + time
				+ ", description=" + description + "]";
	}

}
