package strosoft.cg.model;

import strosoft.cg.common.*;
import strosoft.cg.data.TableCodeGenerateContext;
import strosoft.cg.data.CgTableColumn;
import strosoft.cg.data.CgTableForeignKey;
import strosoft.cg.data.CgTablePrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class TableMapperCodeBuilder {
    private TableCodeGenerateContext context;

    public TableCodeGenerateContext getContext() {
        return context;
    }

    public void setContext(TableCodeGenerateContext context) {
        this.context = context;
    }

    public String generate() {
        MapperInfo mapperInfo = new MapperInfo();
        mapperInfo.setNamespace(context.getFormattedTableName());
        mapperInfo.setResultMap(getResultMap());
        List<SqlCommandInfo> sqlCommands = null;
        if (null == context.getPrimaryKeyType()) {
            sqlCommands = getSqlCommandsWithNonePrimaryKey();
        } else if (context.getPrimaryKeyType().equals("Id")) {
            sqlCommands = getSqlCommandsWithIdPrimaryKey();
        } else if (context.getPrimaryKeyType().equals("Code")) {
            sqlCommands = getSqlCommandsWithCodePrimaryKey();
        } else if (context.getPrimaryKeyType().equals("Composite")) {
            sqlCommands = getSqlCommandsWithCompositePrimaryKey();
        } else {

        }
        //添加批量添加command
        sqlCommands.add(getBatchInsertSqlCommands());
        sqlCommands.addAll(getSqlCommandsWithSql());
        mapperInfo.setSqlCommands(sqlCommands);
        return mapperInfo.toCode();
    }

    public ResultMapInfo getResultMap() {
        ResultMapInfo resultMapInfo = new ResultMapInfo();
        resultMapInfo.setId(context.getFormattedTableName() + "ResultMap");
        resultMapInfo.setType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        List<ResultInfo> resultInfos = new ArrayList<ResultInfo>();
        for (int i = 0; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setColumn(currentColumn.getColumnName());
            resultInfo.setProperty(currentColumn.getParameterName());
            resultInfo.setResultName("result");
            resultInfos.add(resultInfo);
        }
        resultMapInfo.setResults(resultInfos);
        return resultMapInfo;
    }

    public List<SqlCommandInfo> getSqlCommandsWithNonePrimaryKey() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        //一般为关系表，根据每个字段查询；
        for (int i = 0; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            SqlCommandInfo sqlCommand = new SqlCommandInfo();
            StringBuffer idString = new StringBuffer();
            StringBuffer sqlString = new StringBuffer();
            idString.append("select");
            idString.append(context.getFormattedTableName());
            idString.append("sBy");
            idString.append(CodeGenerateUtil.upperCase(currentColumn.getParameterName()));
            sqlCommand.setId(idString.toString());
            sqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
            sqlCommand.setParameterType(currentColumn.getTypeName());
            sqlCommand.setItemType("select");
            sqlString.append("select * from ");
            sqlString.append(context.getTableName());
            sqlString.append(" where ");
            sqlString.append(currentColumn.getColumnName());
            sqlString.append(" = {");
            sqlString.append(currentColumn.getParameterName());
            sqlString.append("}");
            sqlCommand.setSql(sqlString.toString());
            sqlCommands.add(sqlCommand);
        }
        //根据每个字段删除
        for (int i = 0; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            SqlCommandInfo sqlCommand = new SqlCommandInfo();
            StringBuffer sb = new StringBuffer();
            StringBuffer sqlString = new StringBuffer();
            sb.append("delete");
            sb.append(context.getFormattedTableName());
            sb.append("sBy");
            sb.append(CodeGenerateUtil.upperCase(currentColumn.getParameterName()));
            sqlCommand.setId(sb.toString());
            sqlCommand.setParameterType(currentColumn.getTypeName());
            sqlCommand.setItemType("delete");
            sqlString.append("delete  from ");
            sqlString.append(context.getTableName());
            sqlString.append(" where ");
            sqlString.append(currentColumn.getColumnName());
            sqlString.append(" = {");
            sqlString.append(currentColumn.getParameterName());
            sqlString.append("}");
            sqlCommand.setSql(sqlString.toString());
            sqlCommands.add(sqlCommand);
        }
        //新增
        SqlCommandInfo sqlCommand = new SqlCommandInfo();
        sqlCommand.setId("insert" + context.getFormattedTableName());
        sqlCommand.setItemType("insert");
        sqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        sqlCommand.setSql(getInsertSqlString());
        sqlCommands.add(0, sqlCommand);
        return sqlCommands;
    }

    public List<SqlCommandInfo> getSqlCommandsWithIdPrimaryKey() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        //新增
        SqlCommandInfo insertSqlCommand = new SqlCommandInfo();
        insertSqlCommand.setId("insert" + context.getFormattedTableName());
        insertSqlCommand.setItemType("insert");
        insertSqlCommand.setKeyProperty("id");
        insertSqlCommand.setUseGeneratedKeys("true");
        insertSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        insertSqlCommand.setSql(getInsertSqlString());
        sqlCommands.add(insertSqlCommand);
        //根据主键查询
        SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
        selectSqlCommand.setId("select" + context.getFormattedTableName());
        selectSqlCommand.setItemType("select");
        selectSqlCommand.setParameterType("java.lang.Integer");
        selectSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select * from ");
        selectSql.append(context.getTableName());
        selectSql.append(" where id=#{id}");
        selectSqlCommand.setSql(selectSql.toString());
        sqlCommands.add(selectSqlCommand);
        //根据主键修改
        SqlCommandInfo updateSqlCommand = new SqlCommandInfo();
        updateSqlCommand.setId("update" + context.getFormattedTableName());
        updateSqlCommand.setItemType("update");
        updateSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        updateSqlCommand.setSql(getUpdateSqlWithIdPrimaryKey());
        sqlCommands.add(updateSqlCommand);
        //根据主键删除
        SqlCommandInfo deleteSqlCommand = new SqlCommandInfo();
        deleteSqlCommand.setId("delete" + context.getFormattedTableName());
        deleteSqlCommand.setItemType("delete");
        deleteSqlCommand.setParameterType("java.lang.Integer");
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("delete from ");
        deleteSql.append(context.getTableName());
        deleteSql.append(" where id=#{id}");
        deleteSqlCommand.setSql(deleteSql.toString());
        sqlCommands.add(deleteSqlCommand);
        //根据外键查询
        sqlCommands.addAll(getSelectSqlCommandsWithForeignKeys());
        //根据外键删除
        sqlCommands.addAll(getDeleteSqlCommandsWithForeignKeys());
        return sqlCommands;
    }

    public List<SqlCommandInfo> getSqlCommandsWithCodePrimaryKey() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        //新增
        SqlCommandInfo insertSqlCommand = new SqlCommandInfo();
        insertSqlCommand.setId("insert" + context.getFormattedTableName());
        insertSqlCommand.setItemType("insert");
        insertSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        insertSqlCommand.setSql(getInsertSqlString());
        sqlCommands.add(insertSqlCommand);
        //根据主键查询
        SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
        selectSqlCommand.setId("select" + context.getFormattedTableName());
        selectSqlCommand.setItemType("select");
        selectSqlCommand.setParameterType("java.lang.String");
        selectSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select * from ");
        selectSql.append(context.getTableName());
        selectSql.append(" where code=#{code}");
        selectSqlCommand.setSql(selectSql.toString());
        sqlCommands.add(selectSqlCommand);
        //根据主键修改
        SqlCommandInfo updateSqlCommand = new SqlCommandInfo();
        updateSqlCommand.setId("update" + context.getFormattedTableName());
        updateSqlCommand.setItemType("update");
        updateSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        updateSqlCommand.setSql(getUpdateSqlWithCodePrimaryKey());
        sqlCommands.add(updateSqlCommand);
        //根据主键删除
        SqlCommandInfo deleteSqlCommand = new SqlCommandInfo();
        deleteSqlCommand.setId("delete" + context.getFormattedTableName());
        deleteSqlCommand.setItemType("delete");
        deleteSqlCommand.setParameterType("java.lang.String");
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("delete from ");
        deleteSql.append(context.getTableName());
        deleteSql.append(" where code=#{code}");
        deleteSqlCommand.setSql(deleteSql.toString());
        sqlCommands.add(deleteSqlCommand);
        //根据外键查询
        sqlCommands.addAll(getSelectSqlCommandsWithForeignKeys());
        //根据外键删除
        sqlCommands.addAll(getDeleteSqlCommandsWithForeignKeys());
        return sqlCommands;
    }

    public List<SqlCommandInfo> getSqlCommandsWithCompositePrimaryKey() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        //新增
        SqlCommandInfo insertSqlCommand = new SqlCommandInfo();
        insertSqlCommand.setId("insert" + context.getFormattedTableName());
        insertSqlCommand.setItemType("insert");
        insertSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        insertSqlCommand.setSql(getInsertSqlString());
        sqlCommands.add(insertSqlCommand);
        //根据联合主键查询
        SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
        selectSqlCommand.setId("select" + context.getFormattedTableName());
        selectSqlCommand.setItemType("select");
        selectSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        selectSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select * from ");
        selectSql.append(context.getTableName());
        selectSql.append(" where ");
        for (int i = 0; i < context.getPrimaryKeys().size(); i++) {
            CgTablePrimaryKey currentPk = context.getPrimaryKeys().get(i);
            if (i != 0) {
                selectSql.append(" and ");
            }
            selectSql.append(currentPk.getColumnName());
            selectSql.append("=#{");
            selectSql.append(currentPk.getParameterName());
            selectSql.append("}");
        }
        selectSqlCommand.setSql(selectSql.toString());
        sqlCommands.add(selectSqlCommand);
        //根据主键修改
        SqlCommandInfo updateSqlCommand = new SqlCommandInfo();
        updateSqlCommand.setId("update" + context.getFormattedTableName());
        updateSqlCommand.setItemType("update");
        updateSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        updateSqlCommand.setSql(getUpdateSqlWithCodePrimaryKey());
        sqlCommands.add(updateSqlCommand);
        //根据主键删除
        SqlCommandInfo deleteSqlCommand = new SqlCommandInfo();
        deleteSqlCommand.setId("delete" + context.getFormattedTableName());
        deleteSqlCommand.setItemType("delete");
        deleteSqlCommand.setParameterType(context.getModuleDocument() + ".data." + context.getFormattedTableName());
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("delete from ");
        deleteSql.append(context.getTableName());
        deleteSql.append(" where ");
        for (int i = 0; i < context.getPrimaryKeys().size(); i++) {
            CgTablePrimaryKey currentPk = context.getPrimaryKeys().get(i);
            if (i != 0) {
                deleteSql.append(" and ");
            }
            deleteSql.append(currentPk.getColumnName());
            deleteSql.append("=#{");
            deleteSql.append(currentPk.getParameterName());
            deleteSql.append("}");
        }
        deleteSqlCommand.setSql(deleteSql.toString());
        sqlCommands.add(deleteSqlCommand);
        //根据外键查询
        sqlCommands.addAll(getSelectSqlCommandsWithForeignKeys());
        //根据外键删除
        //sqlCommands.addAll(getDeleteSqlCommandsWithForeignKeys());
        return sqlCommands;
    }

    public List<SqlCommandInfo> getSelectSqlCommandsWithForeignKeys() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
            CgTableForeignKey currentKey = context.getReferenceforeignKeys().get(i);
            SqlCommandInfo sqlCommand = new SqlCommandInfo();
            StringBuffer idString = new StringBuffer();
            idString.append("select");
            idString.append(context.getFormattedTableName());
            idString.append("By");
            idString.append(CodeGenerateUtil.upperCase(currentKey.getFormattedReferencesColumnName()));
            StringBuffer sqlString = new StringBuffer();
            sqlString.append("select * from ");
            sqlString.append(context.getTableName());
            sqlString.append(" where ");
            sqlString.append(currentKey.getReferencesColumnName());
            sqlString.append("=#{");
            sqlString.append(currentKey.getFormattedReferencesColumnName());
            sqlString.append("}");
            sqlCommand.setId(idString.toString());
            sqlCommand.setSql(sqlString.toString());
            sqlCommand.setItemType("select");
            sqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
            sqlCommand.setParameterType(currentKey.getTypeName());
            sqlCommands.add(sqlCommand);
        }
        return sqlCommands;
    }

    public List<SqlCommandInfo> getDeleteSqlCommandsWithForeignKeys() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        for (int i = 0; i < context.getReferenceforeignKeys().size(); i++) {
            CgTableForeignKey currentKey = context.getReferenceforeignKeys().get(i);
            SqlCommandInfo sqlCommand = new SqlCommandInfo();
            StringBuffer idString = new StringBuffer();
            idString.append("delete");
            idString.append(context.getFormattedTableName());
            idString.append("By");
            idString.append(CodeGenerateUtil.upperCase(currentKey.getFormattedReferencesColumnName()));
            StringBuffer sqlString = new StringBuffer();
            sqlString.append("delete from ");
            sqlString.append(context.getTableName());
            sqlString.append(" where ");
            sqlString.append(currentKey.getReferencesColumnName());
            sqlString.append("=#{");
            sqlString.append(currentKey.getFormattedReferencesColumnName());
            sqlString.append("}");
            sqlCommand.setId(idString.toString());
            sqlCommand.setSql(sqlString.toString());
            sqlCommand.setItemType("delete");
            sqlCommand.setParameterType(currentKey.getTypeName());
            sqlCommands.add(sqlCommand);
        }
        return sqlCommands;
    }

    public String getUpdateSqlWithCodePrimaryKey() {
        StringBuffer updateSql = new StringBuffer();
        updateSql.append("update ");
        updateSql.append(context.getTableName());
        updateSql.append("\r  <trim prefix=\"set\" suffixOverrides=\",\"> \r");
		/*updateSql.append(context.getColums().get(0).getColumnName());
		updateSql.append("=#{");
		updateSql.append(context.getColums().get(0).getParameterName());
		updateSql.append("}");*/
        for (int i = 0; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            updateSql.append("   <if test=\"");
            updateSql.append(currentColumn.getParameterName());
            updateSql.append(" !=null\">");
            updateSql.append(currentColumn.getColumnName());
            updateSql.append("=#{");
            updateSql.append(currentColumn.getParameterName());
            updateSql.append("},</if>\r");
        }
        updateSql.append("   </trim>\r   <where> \r   ");
        for (int i = 0; i < context.getPrimaryKeys().size(); i++) {
            CgTablePrimaryKey currentPk = context.getPrimaryKeys().get(i);
            if (i != 0) {
                updateSql.append(" and ");
            }
            updateSql.append(currentPk.getColumnName());
            updateSql.append("=#{");
            updateSql.append(currentPk.getParameterName());
            updateSql.append("}");
        }
        updateSql.append("\r  </where>");
        return updateSql.toString();
    }

    public String getUpdateSqlWithIdPrimaryKey() {
        StringBuffer updateSql = new StringBuffer();
        updateSql.append("update ");
        updateSql.append(context.getTableName());
        updateSql.append(" set ");
        updateSql.append(context.getColums().get(0).getColumnName());
        updateSql.append("=#{");
        updateSql.append(context.getColums().get(0).getParameterName());
        updateSql.append("}");
        for (int i = 1; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            updateSql.append(",");
            updateSql.append(currentColumn.getColumnName());
            updateSql.append("=#{");
            updateSql.append(currentColumn.getParameterName());
            updateSql.append("}");
        }
        updateSql.append("\r    where id=#{id}");
        return updateSql.toString();
    }

    public String getInsertSqlString() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("insert into ");
        sqlString.append(context.getTableName());
        sqlString.append("\r    (");
        sqlString.append(context.getColums().get(0).getColumnName());
        for (int i = 1; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            sqlString.append(",");
            sqlString.append(currentColumn.getColumnName());
        }
        sqlString.append(")\r    values\r    (");
        sqlString.append("#{");
        sqlString.append(context.getColums().get(0).getParameterName());
        sqlString.append("}");
        for (int i = 1; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            if (currentColumn.getColumnName().equals("create_time")) {
                //新增时间字段，默认添加
                sqlString.append(",now()");
            } else if (currentColumn.getColumnName().equals("update_time")) {
                //新增时间字段，默认添加
                sqlString.append(",now()");
            } else {
                sqlString.append(",#{");
                sqlString.append(currentColumn.getParameterName());
                sqlString.append("}");
            }
        }
        sqlString.append(")");
        return sqlString.toString();
    }

    public SqlCommandInfo getBatchInsertSqlCommands() {
        SqlCommandInfo batchInsterSqlCommand = new SqlCommandInfo();
        batchInsterSqlCommand.setId("insert" + context.getFormattedTableName() + "s");
        batchInsterSqlCommand.setItemType("insert");
        batchInsterSqlCommand.setParameterType("java.util.List");
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("insert into ");
        sqlString.append(context.getTableName());
        sqlString.append("\r    (");
        sqlString.append(context.getColums().get(0).getColumnName());
        for (int i = 1; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            sqlString.append(",");
            sqlString.append(currentColumn.getColumnName());
        }
        sqlString.append(")\r    values\r   <foreach collection='list' item='item' index='index' separator=','>\r    (");
        sqlString.append("#{item.");
        sqlString.append(context.getColums().get(0).getParameterName());
        sqlString.append("}");
        for (int i = 1; i < context.getColums().size(); i++) {
            CgTableColumn currentColumn = context.getColums().get(i);
            if (currentColumn.getColumnName().equals("create_time")) {
                //新增时间字段，默认添加
                sqlString.append(",now()");
            } else {
                sqlString.append(",#{item.");
                sqlString.append(currentColumn.getParameterName());
                sqlString.append("}");
            }
        }
        sqlString.append(")\r   </foreach>");
        batchInsterSqlCommand.setSql(sqlString.toString());
        return batchInsterSqlCommand;
    }

    //直接传入sql的删、改、查
    public List<SqlCommandInfo> getSqlCommandsWithSql() {
        List<SqlCommandInfo> sqlCommands = new ArrayList<SqlCommandInfo>();
        //根据查询列表
        SqlCommandInfo selectAllSqlCommand = new SqlCommandInfo();
        selectAllSqlCommand.setId("select" + context.getFormattedTableName() + "s");
        selectAllSqlCommand.setItemType("select");
        selectAllSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        selectAllSqlCommand.setSql("select * from " + context.getTableName());
        //根据condition查询
        SqlCommandInfo selectSqlCommand = new SqlCommandInfo();
        selectSqlCommand.setId("select" + context.getFormattedTableName() + "ByCondition");
        selectSqlCommand.setItemType("select");
        selectSqlCommand.setParameterType("java.lang.String");
        selectSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        selectSqlCommand.setSql("select * from " + context.getTableName() + " where ${value}");
        //根据sql查询
        SqlCommandInfo selectBySqlSqlCommand = new SqlCommandInfo();
        selectBySqlSqlCommand.setId("select" + context.getFormattedTableName() + "BySql");
        selectBySqlSqlCommand.setItemType("select");
        selectBySqlSqlCommand.setResultMap(context.getFormattedTableName() + "ResultMap");
        selectBySqlSqlCommand.setParameterType("java.lang.String");
        selectBySqlSqlCommand.setSql("${value}");
        //根据sql修改
        SqlCommandInfo updateSqlCommand = new SqlCommandInfo();
        updateSqlCommand.setId("update" + context.getFormattedTableName() + "BySql");
        updateSqlCommand.setItemType("update");
        updateSqlCommand.setParameterType("java.lang.String");
        updateSqlCommand.setSql("${value}");
        //根据sql删除
        SqlCommandInfo deleteSqlCommand = new SqlCommandInfo();
        deleteSqlCommand.setId("delete" + context.getFormattedTableName() + "BySql");
        deleteSqlCommand.setItemType("delete");
        deleteSqlCommand.setParameterType("java.lang.String");
        deleteSqlCommand.setSql("${value}");
        //根据sql查询int
        SqlCommandInfo selectIntSqlCommand = new SqlCommandInfo();
        selectIntSqlCommand.setId("selectIntBySql");
        selectIntSqlCommand.setItemType("select");
        selectIntSqlCommand.setParameterType("java.lang.String");
        selectIntSqlCommand.setResultType("java.lang.Integer");
        selectIntSqlCommand.setSql("${value}");
        //根据sql查询string
        SqlCommandInfo selectStringSqlCommand = new SqlCommandInfo();
        selectStringSqlCommand.setId("selectStringBySql");
        selectStringSqlCommand.setItemType("select");
        selectStringSqlCommand.setParameterType("java.lang.String");
        selectStringSqlCommand.setResultType("java.lang.String");
        selectStringSqlCommand.setSql("${value}");
        //根据条件查询count
        SqlCommandInfo selectCountSqlCommand = new SqlCommandInfo();
        selectCountSqlCommand.setId("selectCountByCondition");
        selectCountSqlCommand.setItemType("select");
        selectCountSqlCommand.setParameterType("java.lang.String");
        selectCountSqlCommand.setResultType("java.lang.Integer");
        selectCountSqlCommand.setSql("select count(*) from " + context.getTableName() + " where ${value}");
        sqlCommands.add(selectAllSqlCommand);
        sqlCommands.add(selectBySqlSqlCommand);
        sqlCommands.add(deleteSqlCommand);
        sqlCommands.add(updateSqlCommand);
        sqlCommands.add(selectSqlCommand);
        sqlCommands.add(selectCountSqlCommand);
        sqlCommands.add(selectIntSqlCommand);
        sqlCommands.add(selectStringSqlCommand);
        return sqlCommands;
    }
}
