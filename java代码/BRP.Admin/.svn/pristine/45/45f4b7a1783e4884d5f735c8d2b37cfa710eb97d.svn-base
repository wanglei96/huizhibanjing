package strosoft.cg.model;

import java.util.ArrayList;
import java.util.List;

import strosoft.cg.common.ServiceHandlerInfo;
import strosoft.cg.data.CgTablePrimaryKey;
import strosoft.cg.data.ViewCodeGenerateContext;
import strosoft.cg.data.CgViewColumn;

public class ViewServiceHandlerBuilder {
	private ViewCodeGenerateContext context;

	public ViewCodeGenerateContext getContext() {
		return context;
	}

	public void setContext(ViewCodeGenerateContext context) {
		this.context = context;
	}

	public List<ServiceHandlerInfo> build() {
		List<ServiceHandlerInfo> serviceHandlerInfos = new ArrayList<ServiceHandlerInfo>();
		serviceHandlerInfos.add(getEntityServiceHandler());
		serviceHandlerInfos.add(getListServiceHandler());
		return serviceHandlerInfos;
	}

	public ServiceHandlerInfo getEntityServiceHandler() {
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Get" + context.getFormattedViewName());
		serviceHandlerInfo.setType("strosoft.app.service.GetServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument() + ".model." + context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument() + ".data." + context.getFormattedViewName());
		serviceHandlerInfo.setTableName(context.getViewName());
		List<CgViewColumn> lstViewColumn = context.getColumns();
		for (CgViewColumn currentViewColumn : lstViewColumn) {
			if (currentViewColumn.getColumnName().equals("id")) {
				List<CgTablePrimaryKey> lstPrimaryKeys = new ArrayList<CgTablePrimaryKey>();
				CgTablePrimaryKey newTablePrimaryKey = new CgTablePrimaryKey();
				newTablePrimaryKey.setCaption("id");
				newTablePrimaryKey.setColumnName("id");
				newTablePrimaryKey.setParameterName("id");
				lstPrimaryKeys.add(newTablePrimaryKey);
				serviceHandlerInfo.setPrimaryKeys(lstPrimaryKeys);
				break;
			}
		}

		return serviceHandlerInfo;
	}

	public ServiceHandlerInfo getListServiceHandler() {
		ServiceHandlerInfo serviceHandlerInfo = new ServiceHandlerInfo();
		serviceHandlerInfo.setName("Get" + context.getFormattedViewName() + "List");
		serviceHandlerInfo.setType("strosoft.app.service.GetListServiceHandler");
		serviceHandlerInfo.setDataManagerType(context.getModuleDocument() + ".model." + context.getManagerName());
		serviceHandlerInfo.setDataEntityType(context.getModuleDocument() + ".data." + context.getFormattedViewName());
		serviceHandlerInfo.setTableName(context.getViewName());
		serviceHandlerInfo.setSearchItems(context.getSearchFileds());
		return serviceHandlerInfo;
	}
}
