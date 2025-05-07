package strosoft.cg.data;

import strosoft.app.data.DataEntity;

public class CgTableForeignKey  extends DataEntity{
	private String referencesColumnName;
	private String typeName;
	private String referencesColumnType;
	private String formattedReferencesColumnName;
	private String formattedReferencesTableName;
	private String referencedColumnName;
	private String referencedTableName;
	private String referencesTableName;
	private String formattedReferencedTableName;
	private String formattedReferencedColumnName;
	private String referencesModuleDocument;
	public String getReferencesColumnName() {
		return referencesColumnName;
	}
	public void setReferencesColumnName(String referencesColumnName) {
		this.referencesColumnName = referencesColumnName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getReferencesColumnType() {
		return referencesColumnType;
	}
	public void setReferencesColumnType(String referencesColumnType) {
		this.referencesColumnType = referencesColumnType;
	}

	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
	public String getReferencedTableName() {
		return referencedTableName;
	}
	public void setReferencedTableName(String referencedTableName) {
		this.referencedTableName = referencedTableName;
	}
	public String getReferencesTableName() {
		return referencesTableName;
	}
	public void setReferencesTableName(String referencesTableName) {
		this.referencesTableName = referencesTableName;
	}
	public String getFormattedReferencedTableName() {
		return formattedReferencedTableName;
	}
	public void setFormattedReferencedTableName(String formattedReferencedTableName) {
		this.formattedReferencedTableName = formattedReferencedTableName;
	}
	public String getFormattedReferencedColumnName() {
		return formattedReferencedColumnName;
	}
	public void setFormattedReferencedColumnName(
			String formattedReferencedColumnName) {
		this.formattedReferencedColumnName = formattedReferencedColumnName;
	}
	public String getReferencesModuleDocument() {
		return referencesModuleDocument;
	}
	public void setReferencesModuleDocument(String referencesModuleDocument) {
		this.referencesModuleDocument = referencesModuleDocument;
	}
	public String getFormattedReferencesTableName() {
		return formattedReferencesTableName;
	}
	public void setFormattedReferencesTableName(
			String formattedReferencesTableName) {
		this.formattedReferencesTableName = formattedReferencesTableName;
	}
	public String getFormattedReferencesColumnName() {
		return formattedReferencesColumnName;
	}
	public void setFormattedReferencesColumnName(
			String formattedReferencesColumnName) {
		this.formattedReferencesColumnName = formattedReferencesColumnName;
	}
	
	
}
