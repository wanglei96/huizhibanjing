package strosoft.cg.model;

import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DataTypeHelper;
import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.ServiceHandlerInfo;
import strosoft.cg.data.CgView;
import strosoft.cg.data.ViewCodeGenerateContext;
import strosoft.cg.data.CgViewColumn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewCodeBulider {
	private CgView view;
	private ViewCodeGenerateContext context = null;
	
	public String getViewEntityCode(){
		ViewEntityClassCodeBuilder viewEntityClassCodeBuilder = new ViewEntityClassCodeBuilder();
		viewEntityClassCodeBuilder.setContext(context);
		return viewEntityClassCodeBuilder.generate();
		
	}
	
	public String getViewMasterCode(){
		ViewMasterClassCodeBuilder builder = new ViewMasterClassCodeBuilder();
		builder.setContext(context);
		return builder.generate();
		
	}
	
	public String getViewManagerCode(){
		ViewManagerClassCodeBuilder builder = new ViewManagerClassCodeBuilder();
		builder.setContext(context);
		return builder.generate();
	}
	
	public String getViewMapperCode(){
		ViewMapperCodeBuilder viewMapperCodeBuilder = new ViewMapperCodeBuilder();
		viewMapperCodeBuilder.setContext(context);
		return viewMapperCodeBuilder.generate();
		
	}
	
	public List<ServiceHandlerInfo> getViewServiceHandlerLsit(){
		ViewServiceHandlerBuilder viewServiceHandlerBuilder = new ViewServiceHandlerBuilder();
		viewServiceHandlerBuilder.setContext(context);
		return viewServiceHandlerBuilder.build();
	}
	
	public void getContext() throws IOException, Exception{
		ViewCodeGenerateContext context = new ViewCodeGenerateContext();
		ArrayList<LinkedHashMap<String, Object>> columns = MyBatisManager.getInstance().getAllColumns(view.getDataSource(), view.getViewName());
		context.setColumns(loadColumns(columns));
		context.setViewName(this.view.getViewName());
		context.setModuleName(this.view.getModuleName());
		context.setModuleDocument(this.view.getModuleDocument());
		context.setModulePath(this.view.getModulePath());
		context.setSearchFileds(this.view.getSearchItems());
		context.setFormattedViewName(this.view.getFormattedViewName());
		context.setMasterName(this.view.getFormattedViewName()+"Master");
		context.setManagerName(this.view.getFormattedViewName()+"Manager");
		this.context = context;
	}
	
	public List<CgViewColumn> loadColumns(ArrayList<LinkedHashMap<String, Object>> columns){
		List<CgViewColumn> viewColumns = new ArrayList<CgViewColumn>();
		List<CgViewColumn> xmlViewColumns = view.getViewColumns();
		for(int i=0;i<columns.size();i++){
			CgViewColumn viewColumn = null;
			Integer isContainIndex = -1;
			for(int j=0;j<xmlViewColumns.size();j++){
				String viewColumndItem = CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME"));
				String xmlColumndItem = xmlViewColumns.get(j).getParameterName();
				if(viewColumndItem.equals(xmlColumndItem)){
					isContainIndex = j;
					break;
				}
			}
			if(isContainIndex>-1){
				viewColumn = xmlViewColumns.get(isContainIndex);
			}else{
				viewColumn = new CgViewColumn();
				viewColumn.setColumnName((String) columns.get(i).get("COLUMN_NAME"));
				viewColumn.setViewName(view.getViewName());
				viewColumn.setParameterName(CodeGenerateUtil.getParameterName((String)columns.get(i).get("COLUMN_NAME")));
			}
			//设置java类型
			viewColumn.setTypeName(DataTypeHelper.getDataTypeBySqlType((String) columns.get(i).get("DATA_TYPE")).get("javaType"));
			viewColumns.add(viewColumn);
		}
		return viewColumns;
	}

	public CgView getView() {
		return view;
	}

	public void setView(CgView view) {
		this.view = view;
	}
	
	
}
