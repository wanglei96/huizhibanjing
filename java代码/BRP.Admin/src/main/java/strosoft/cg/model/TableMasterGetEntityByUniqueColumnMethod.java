package strosoft.cg.model;

import strosoft.cg.common.CodeGenerateUtil;
import strosoft.cg.common.MethodInfo;
import strosoft.cg.common.MethodParameter;
import strosoft.cg.common.UniqueFieldInfo;

import java.util.ArrayList;
import java.util.List;

public class TableMasterGetEntityByUniqueColumnMethod extends MethodInfo {
    private UniqueFieldInfo uniqueField;

    public UniqueFieldInfo getUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(UniqueFieldInfo uniqueField) {
        this.uniqueField = uniqueField;
    }

    public String getMethodBody() {
        StringBuffer sb = new StringBuffer();
        sb.append("SqlSession sqlSession = MyBatisManager.getInstance().openSession();");
        sb.append(uniqueField.getFormattedTableName());
        sb.append(" entity = null;");
        sb.append("try{ entity = getEntityBy");
        sb.append(CodeGenerateUtil.upperCase(uniqueField.getDataName()));
        sb.append("(sqlSession,");
        sb.append(uniqueField.getDataName());
        sb.append(");}catch(Exception  e){");
        sb.append("e.printStackTrace();sqlSession.rollback();throw e;} finally {sqlSession.close();}return entity;");
        return sb.toString();
    }

    public void setMethodParameters() {
        this.setMethodName("getEntityBy" + CodeGenerateUtil.upperCase(uniqueField.getDataName()));
        this.setReturnType(uniqueField.getFormattedTableName());
        this.setVisibility("public");
        List<String> exceptions = new ArrayList<String>();
        exceptions.add("Exception");
        this.setExceptions(exceptions);
        List<MethodParameter> parameters = new ArrayList<MethodParameter>();
        MethodParameter parameter = new MethodParameter();
        parameter.setName(uniqueField.getDataName());
        parameter.setType(CodeGenerateUtil.getTypeString(uniqueField.getTypeName()));
        parameters.add(parameter);
        this.setParameters(parameters);

    }


}
