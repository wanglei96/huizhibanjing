package strosoft.app.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.ArrayListHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysBasicTableField;
import strosoft.sys.model.SysBasicTableFieldManager;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;


/*
 * Id基础表管理类
 */
@SuppressWarnings("unchecked")
public class CustomTableTableManager extends TableDataMaster {
	private static CustomTableTableManager _instance;

	public static CustomTableTableManager getInstance() {
		if (_instance == null) {
			_instance = new CustomTableTableManager();
		}
		return _instance;
	}

	private CustomTableTableManager() {
	}
	
	//新增自定义表
	public int add(JSONObject jData,List<SysBasicTableField> sysBasicTableFields) throws Exception{
		String tableName = JsonHelper.getString(jData, "tableName");
		StringBuffer fieldSb = new StringBuffer();
		StringBuffer valueSb = new StringBuffer();
		for(int i=0;i<sysBasicTableFields.size();i++){
			SysBasicTableField currentField = sysBasicTableFields.get(i);
			String fieldName = currentField.getFieldName();
			String parameterName = StringHelper.getParameterName(fieldName);
			if(i>0){
				fieldSb.append(",");
			}
			fieldSb.append(fieldName);
			//获取对应值
			Object objValue = JsonHelper.getObject(jData, parameterName);
			if (objValue instanceof JSONNull) {
				objValue = null;
			}
			if(i>0){  
				valueSb.append(",");
			}
			//如果有显示顺序字段display_order,且该字段的值为空，默认自增
			if(fieldName.equals("display_order") && objValue == null){
				String sql = "select ifnull(max(display_order),-1) from "+tableName;
				Integer maxOrder = MyBatisManager.getInstance().executeInt(sql);
				objValue = maxOrder +1;
			}
			if(null != objValue && !objValue.equals("null")){
				valueSb.append("'");
				valueSb.append(objValue);
				valueSb.append("'");
			}else{
				valueSb.append("null");
			}
		}
		String sql = "INSERT INTO "+tableName+" ("+fieldSb.toString()+") VALUES ("+valueSb.toString()+")";
		return MyBatisManager.getInstance().executeInsert(sql);
	}
	//修改自定义表
	public int update(JSONObject jData,List<SysBasicTableField> sysBasicTableFields) throws IOException, Exception{
		String tableName = JsonHelper.getString(jData, "tableName");
		StringBuffer sb = new StringBuffer();
		String condition = "";
		for(int i=0;i<sysBasicTableFields.size();i++){
			SysBasicTableField currentField = sysBasicTableFields.get(i);
			String fieldName = currentField.getFieldName();
			String parameterName = StringHelper.getParameterName(fieldName);
			//判断该字段是否为id自增类型主键
			Boolean isPrimaryKey = false;
			if( null != currentField.getIsPrimaryKey() && (currentField.getIsPrimaryKey()-1==0)){
				//是主键
				isPrimaryKey = true;
			}
			//获取对应值
			Object objValue = JsonHelper.getObject(jData, parameterName);
			//获取不到的属性，不去修改。
			if(null != objValue){
				//属性为JSONNull,设置为null
				if (objValue instanceof JSONNull) {
					objValue = null;
				}
				//不是主键,进行修改
				if(!isPrimaryKey){
					if(!sb.toString().equals("")){
						sb.append(",");
					}
					sb.append(fieldName);
					sb.append("=");
					if(null != objValue){
						sb.append("'");
						sb.append(objValue);
						sb.append("'");
					}else{
						sb.append("null");
					}
				}
			}
			//是主键，作为修改条件
			if(isPrimaryKey){
				condition = fieldName+"= '"+objValue+"'";
			}
		}
		String sql = "UPDATE "+tableName+" SET "+sb.toString()+" where "+condition;
		return MyBatisManager.getInstance().executeUpdate(sql);
	}
	//删除
	public int delete(String tableName,String primaryKey) throws Exception{
		List<String> primaryKeys = new ArrayList<String>();
		primaryKeys.add(primaryKey);
		return batchDelete(tableName, primaryKeys);
	}
	//批量删除
	public int batchDelete(String tableName,List<String> primaryKeys) throws Exception{
		int result = 0;
		//获取所有字段信息
		String condition = "table_name = '"+tableName+"'";
		List<SysBasicTableField> sysBasicTableFields = SysBasicTableFieldManager.getInstance().getList(condition);
		//获取主键
		String primaryKey = null;
		for(int i=0;i<sysBasicTableFields.size();i++){
			SysBasicTableField currentField = sysBasicTableFields.get(i);
			if(null != currentField.getIsPrimaryKey() && (currentField.getIsPrimaryKey()-1==0)){
				primaryKey = currentField.getFieldName();
			}
		}
		String primaryKeysStr = null;
		if(null != primaryKey){
			if(primaryKey.equals("id")){
				primaryKeysStr = ArrayListHelper.stringListToString(primaryKeys, ",");
			}else{
				primaryKeysStr = ArrayListHelper.stringListToString(primaryKeys, ",");
			}
			String deleteSql = "Delete from "+tableName+" where "+primaryKey+" in ("+primaryKeysStr+")";
			result = MyBatisManager.getInstance().executeDelete(deleteSql);
		}
		
		return result;
	}
	//设置检查字段
	public void setCheckField(JSONObject jData,ServiceHandlerValidator theServiceHandlerValidator,List<SysBasicTableField> sysBasicTableFields){
		String tableName = JsonHelper.getString(jData, "tableName");
		//主键列表
		HashMap<String, Object> primaryKeys = new LinkedHashMap<String, Object>();
		for(int i=0;i<sysBasicTableFields.size();i++){
			SysBasicTableField currentField = sysBasicTableFields.get(i);
			String fieldName = currentField.getFieldName();
			String caption = currentField.getCaption();
			String parameter = StringHelper.getParameterName(fieldName);//获取对应值
			Object objValue = JsonHelper.getObject(jData, parameter);
			if (objValue instanceof JSONNull) {
				objValue = null;
			}
			//判断该字段是否为id自增类型主键,若是,则不设置该字段必填
			Boolean isIncreaseIdPrimaryKey = false;
			if(currentField.getFieldName().equals("id")
					&& null != currentField.getIsPrimaryKey() && (currentField.getIsPrimaryKey()-1==0)
					 && null != currentField.getIsAutoIncrease() && (currentField.getIsAutoIncrease()-1==0)){
				//是id自增类型主键
				isIncreaseIdPrimaryKey = true;
				primaryKeys.put(fieldName, objValue);
			}
			//必填字段
			if(null != currentField.getRequired() && (currentField.getRequired()-1==0) && !isIncreaseIdPrimaryKey){
				theServiceHandlerValidator.addRequiredItem(jData, caption, parameter);
			}
			//唯一字段
			if(null != currentField.getIsUnique() && (currentField.getIsUnique()-1==0)){
				theServiceHandlerValidator.addUniqueItem(jData, caption, parameter, tableName,fieldName,primaryKeys);
			}
			//最大值
			String maxValue = currentField.getMaxValue();
			if(null != maxValue){
				theServiceHandlerValidator.addMaxItem(jData, caption, parameter, new BigDecimal(maxValue));
			}
			//最小值
			String minValue = currentField.getMinValue();
			if(null != minValue){
				theServiceHandlerValidator.addMinItem(jData, caption, parameter, new BigDecimal(minValue));
			}//最小长度
			Integer minLength = currentField.getMinLength();
			if(null != minLength){
				theServiceHandlerValidator.addTextLengthItem(jData, caption, parameter, minLength);
			}
			//最大长度和最小长度
			Integer maxLength = currentField.getMaxLength();
			if(null != maxLength){
				minLength = null == minLength?0:minLength;
				theServiceHandlerValidator.addTextLengthItem(jData, caption, parameter, minLength, maxLength);
			}
		}
		
	}
}
