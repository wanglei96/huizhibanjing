package strosoft.cg.common;

import strosoft.app.data.DataEntity;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class ServiceHandlerInfo  extends DataEntity{
	private String name;
	private String actionType;
	private String type;
	private String dataManagerType;
	private String dataEntityType;
	private String tableName;
	private String primaryKeysCombination;
	private String encryptFields;
	private String orderBy;
	private List<SearchItemInfo> searchItems;
	private List<CgTablePrimaryKey> primaryKeys = new ArrayList<CgTablePrimaryKey>();
	private List<UniqueFieldInfo> uniqueFields;
	private List<RequiredFieldInfo> requiredFields;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataManagerType() {
		return dataManagerType;
	}
	public List<CgTablePrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(List<CgTablePrimaryKey> list) {
		this.primaryKeys = list;
	}
	public void setDataManagerType(String dataManagerType) {
		this.dataManagerType = dataManagerType;
	}
	public String getDataEntityType() {
		return dataEntityType;
	}
	public void setDataEntityType(String dataEntityType) {
		this.dataEntityType = dataEntityType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getEncryptFields() {
		return encryptFields;
	}
	public void setEncryptFields(String encryptFields) {
		this.encryptFields = encryptFields;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public List<SearchItemInfo> getSearchItems() {
		return searchItems;
	}
	public String getPrimaryKeysCombination() {
		return primaryKeysCombination;
	}
	public void setPrimaryKeysCombination(String primaryKeysCombination) {
		this.primaryKeysCombination = primaryKeysCombination;
	}
	public void setSearchItems(List<SearchItemInfo> searchItems) {
		this.searchItems = searchItems;
	}
	public List<UniqueFieldInfo> getUniqueFields() {
		return uniqueFields;
	}
	public void setUniqueFields(List<UniqueFieldInfo> uniqueFields) {
		this.uniqueFields = uniqueFields;
	}
	public List<RequiredFieldInfo> getRequiredFields() {
		return requiredFields;
	}
	public void setRequiredFields(List<RequiredFieldInfo> requiredFields) {
		this.requiredFields = requiredFields;
	}
	public String toCode(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ServiceHandler ");
		sb.append(CodeGenerateUtil.getAttributeString("Name",this.name));
		sb.append(CodeGenerateUtil.getAttributeString("Type",this.type));
		sb.append(CodeGenerateUtil.getAttributeString("DataManagerType",this.dataManagerType));
		sb.append(CodeGenerateUtil.getAttributeString("DataEntityType",this.dataEntityType));
		sb.append(CodeGenerateUtil.getAttributeString("TableName",this.tableName));
		sb.append(CodeGenerateUtil.getAttributeString("EncryptFields",this.encryptFields));
		sb.append(CodeGenerateUtil.getAttributeString("PrimaryKeys",this.primaryKeysCombination));
		String searchItemInfoString = getSearchItemInfoString();
		String uniqueFieldsString = getUniqueFieldsString();
		String requiredFieldsString = getRequiredFieldsString();
		
		String primaryKeysString = getPrimaryKeysString();
		if(searchItemInfoString.equals("") && uniqueFieldsString.equals("") && requiredFieldsString.equals("") && primaryKeysString.equals("")){
			sb.append("/>");
		}else{
			sb.append(">");
			sb.append(searchItemInfoString);
			sb.append(uniqueFieldsString);
			sb.append(requiredFieldsString);
			sb.append(primaryKeysString);
			sb.append("</ServiceHandler>");
		}
		return sb.toString();
	}
	public String getSearchItemInfoString(){
		if(null == this.searchItems || this.searchItems.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<SearchItems>");
		for(int i=0;i<this.searchItems.size();i++){
			sb.append(this.searchItems.get(i).toCode());
		}
		sb.append("</SearchItems>");
		return sb.toString();	
	}
	public String getUniqueFieldsString(){
		if(null == this.uniqueFields || (this.uniqueFields.size()!=1 && this.uniqueFields.size() == this.primaryKeys.size())){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<UniqueFields>");
		if(this.primaryKeys.size()>1){
			for(int i=0;i<this.uniqueFields.size();i++){
				Boolean isprimaryKey = false;
				//多主键时，不在uiqueFields里添加主键
				for(int j=0;j<this.primaryKeys.size();j++){
					if(uniqueFields.get(i).getFieldName().equals(primaryKeys.get(j).getColumnName())){
						isprimaryKey = true;
					}
				}
				if(!isprimaryKey){
					sb.append(this.uniqueFields.get(i).toCode());
				}
			}
		}else{
			//只有一个主键时，添加唯一验证
			for(int i=0;i<this.uniqueFields.size();i++){
				sb.append(this.uniqueFields.get(i).toCode());
			}
		}
		sb.append("</UniqueFields>");
		return sb.toString();	
	}
	public String getRequiredFieldsString(){
		if(null == this.requiredFields || this.requiredFields.size()<1){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<RequredFields>");
		for(int i=0;i<this.requiredFields.size();i++){
			if(this.actionType.equals("Add") && (this.requiredFields.get(i).getDataName().equals("id") || this.requiredFields.get(i).getDataName().equals("uuid"))){
				continue;
			}
			sb.append(this.requiredFields.get(i).toCode());
		}
		sb.append("</RequredFields>");
		return sb.toString();	
	}
	public String getPrimaryKeysString(){
		if((null == this.primaryKeys || this.primaryKeys.size()<1) && (primaryKeys.size() == 1 && (primaryKeys.get(0).getColumnName().equals("code") || primaryKeys.get(0).getColumnName().equals("id")))){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<PrimaryKeys>");
		for(int i=0;i<this.primaryKeys.size();i++){
			sb.append(this.primaryKeys.get(i).toCode());
		}
		sb.append("</PrimaryKeys>");
		return sb.toString();	
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
