package strosoft.cg.common;

import strosoft.app.data.DataEntity;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class MethodInfo  extends DataEntity{
	private String visibility;
	private Boolean isAbstract;
	private Boolean isOverride;
	private Boolean isStatic;
	private String returnType;
	private String methodName;
	private List<String> exceptions;
	private List<MethodParameter> parameters;
	
	public Boolean getIsAbstract() {
		return isAbstract;
	}
	public void setIsAbstract(Boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	public Boolean getIsOverride() {
		return isOverride;
	}
	public void setIsOverride(Boolean isOverride) {
		this.isOverride = isOverride;
	}
	public Boolean getIsStatic() {
		return isStatic;
	}
	public void setIsStatic(Boolean isStatic) {
		this.isStatic = isStatic;
	}
	public List<MethodParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<MethodParameter> parameters) {
		this.parameters = parameters;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String toCode(){
		setMethodParameters();
		StringBuffer code = new StringBuffer();
         //可见性
         String visibilityString = this.visibility;
         //函数名
         String methodName = this.getMethodName();
         //是否虚方法
         String isStatic = (null==this.isStatic || !this.isStatic)? "" : " static";
         //是否虚方法
        // String isVirtual = this.IsVirtual ? " virtual" : string.Empty;
         //是否重载方法
         String isOverride = (null==this.isOverride || !this.isOverride) ? "" : " override";
         //参数
         String parametersCode = this.getParameterString();
         //是否抛出异常
         String exceptionString = this.getExceptionString();
         //返回值类型
         String returnType = this.getReturnType();
         //函数体
         String methodBody = this.getMethodBody();
         code.append(visibilityString);
         code.append(" ");
         code.append(isStatic);
         code.append(" ");
         code.append(returnType);
         code.append(" ");
         code.append(methodName);
         code.append("(");
         code.append(parametersCode);
         code.append(")");
         code.append(exceptionString);
         code.append("{");
         code.append(methodBody);
         code.append("}");
		return code.toString();
	}
	
	public String getParameterString(){
		StringBuffer parameterString = new StringBuffer();
		if(null == this.parameters || this.parameters.size()<1){
			return "";
		}
		parameterString.append(this.parameters.get(0).getType());
		parameterString.append(" ");
		parameterString.append(this.parameters.get(0).getName());
		for(int i=1;i<this.parameters.size();i++){
			parameterString.append(",");
			parameterString.append(this.parameters.get(i).getType());
			parameterString.append(" ");
			parameterString.append(this.parameters.get(i).getName());
		}
		return parameterString.toString();
	}
	
	public String getExceptionString(){
		StringBuffer exceptionString = new StringBuffer();
		if(null == exceptions || exceptions.size()<1){
			return "";
		}
		exceptionString.append("throws");
		for(int i=0;i<exceptions.size();i++){
			exceptionString.append(" ");
			exceptionString.append(exceptions.get(i));
		}
		return exceptionString.toString();
	}
	public String getMethodBody(){
		return "";
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public List<String> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}
	
	public void setMethodParameters(){};
	
	//表类型参数获取
	public List<MethodParameter> getTableMethodParameters(TableCodeGenerateContext context){
		List<MethodParameter> parameters = new ArrayList<MethodParameter>();
		for(int i=0;i<context.getPrimaryKeys().size();i++){
			CgTablePrimaryKey currentKey =  context.getPrimaryKeys().get(i);
			MethodParameter parameter = new MethodParameter();
			parameter.setName(currentKey.getParameterName());
			parameter.setType(CodeGenerateUtil.getTypeString(currentKey.getTypeName()));
			parameters.add(parameter);
		}
		return parameters;
	}
	//表类型参数获取
		public List<MethodParameter> getTableMethodParametersWhitSqlSession(TableCodeGenerateContext context){
			List<MethodParameter> parameters = new ArrayList<MethodParameter>();
			MethodParameter transaction = new MethodParameter();
			transaction.setName("sqlSession");
			transaction.setType("SqlSession");
			parameters.add(transaction);
			for(int i=0;i<context.getPrimaryKeys().size();i++){
				CgTablePrimaryKey currentKey =  context.getPrimaryKeys().get(i);
				MethodParameter parameter = new MethodParameter();
				parameter.setName(currentKey.getParameterName());
				parameter.setType(CodeGenerateUtil.getTypeString(currentKey.getTypeName()));
				parameters.add(parameter);
			}
			return parameters;
		}
	//表类型调用方法参数获取
	public String getTableTypeCallMethodParametersString(TableCodeGenerateContext context){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<context.getPrimaryKeys().size();i++){
			CgTablePrimaryKey currentKey =  context.getPrimaryKeys().get(i);
			sb.append(",");
			sb.append(currentKey.getParameterName());
		}
		return sb.toString();
	}
	//表类型调用方法参数获取
		public String getTableTypeCallMethodParametersStringWhithSqlSession(TableCodeGenerateContext context){
			StringBuffer sb = new StringBuffer();
			sb.append("sqlSession");
			for(int i=0;i<context.getPrimaryKeys().size();i++){
				CgTablePrimaryKey currentKey =  context.getPrimaryKeys().get(i);
				sb.append(",");
				sb.append(currentKey.getParameterName());
			}
			return sb.toString();
		}
}
