package strosoft.cg.model;

import strosoft.cg.common.ServiceHandlerInfo;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class TableServiceHandlerBuilder {
	private TableCodeGenerateContext context;

	public TableCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(TableCodeGenerateContext context) {
		this.context = context;
	}
	 
	public List<ServiceHandlerInfo> build(){
		List<ServiceHandlerInfo> serviceHandlerInfos = new ArrayList<ServiceHandlerInfo>();
		serviceHandlerInfos.add(addServiceHandler());
		serviceHandlerInfos.add(getEntityServiceHandler());
		serviceHandlerInfos.add(getListServiceHandler());
		serviceHandlerInfos.add(updateServiceHandler());
		serviceHandlerInfos.add(deleteServiceHandler());
		serviceHandlerInfos.add(batchDeleteServiceHandler());
		return serviceHandlerInfos;
	}

	public ServiceHandlerInfo addServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Add"+context.getFormattedTableName());
		serviceHandlerInfo.setActionType("Add");
		serviceHandlerInfo.setType("strosoft.app.service.AddServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setRequiredFields(context.getRequeFileds());
		serviceHandlerInfo.setUniqueFields(context.getUniqueFileds());
		serviceHandlerInfo.setPrimaryKeys(context.getPrimaryKeys());
		serviceHandlerInfo.setTableName(context.getTableName());
		StringBuffer primaryKeySb = new StringBuffer();
		for(int i=0;i<context.getPrimaryKeys().size();i++){
			CgTablePrimaryKey currentKey = context.getPrimaryKeys().get(i);
			if(i!=0){
				primaryKeySb.append(",");
			}
			primaryKeySb.append(currentKey.getColumnName());
		}
		serviceHandlerInfo.setPrimaryKeysCombination(primaryKeySb.toString());
		return serviceHandlerInfo;
	}
	
	public ServiceHandlerInfo getEntityServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Get"+context.getFormattedTableName());
		serviceHandlerInfo.setActionType("Get");
		serviceHandlerInfo.setType("strosoft.app.service.GetServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setTableName(context.getTableName());
		serviceHandlerInfo.setPrimaryKeys(context.getPrimaryKeys());
		
		return serviceHandlerInfo;
	}
	public ServiceHandlerInfo getListServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Get"+context.getFormattedTableName()+"List");
		serviceHandlerInfo.setActionType("GetList");
		serviceHandlerInfo.setType("strosoft.app.service.GetListServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setTableName(context.getTableName());
		serviceHandlerInfo.setSearchItems(context.getSearchFileds());
		return serviceHandlerInfo;
	}
	public ServiceHandlerInfo updateServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Update"+context.getFormattedTableName());
		serviceHandlerInfo.setActionType("Update");
		serviceHandlerInfo.setType("strosoft.app.service.UpdateServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setRequiredFields(context.getRequeFileds());
		serviceHandlerInfo.setUniqueFields(context.getUniqueFileds());
		serviceHandlerInfo.setTableName(context.getTableName());
		StringBuffer primaryKeySb = new StringBuffer();
		for(int i=0;i<context.getPrimaryKeys().size();i++){
			CgTablePrimaryKey currentKey = context.getPrimaryKeys().get(i);
			if(i!=0){
				primaryKeySb.append(",");
			}
			primaryKeySb.append(currentKey.getColumnName());
		}
		serviceHandlerInfo.setPrimaryKeysCombination(primaryKeySb.toString());
		return serviceHandlerInfo;
	}
	public ServiceHandlerInfo deleteServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Delete"+context.getFormattedTableName());
		serviceHandlerInfo.setActionType("Delete");
		serviceHandlerInfo.setType("strosoft.app.service.DeleteServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setTableName(context.getTableName());
		serviceHandlerInfo.setPrimaryKeys(context.getPrimaryKeys());
		return serviceHandlerInfo;
	}
	public ServiceHandlerInfo batchDeleteServiceHandler(){
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("BatchDelete"+context.getFormattedTableName());
		serviceHandlerInfo.setActionType("BatchDelete");
		serviceHandlerInfo.setType("strosoft.app.service.BatchDeleteServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument()+".model."+context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument()+".data."+context.getFormattedTableName());
		serviceHandlerInfo.setTableName(context.getTableName());
		serviceHandlerInfo.setPrimaryKeys(context.getPrimaryKeys());
		return serviceHandlerInfo;
	}
}
