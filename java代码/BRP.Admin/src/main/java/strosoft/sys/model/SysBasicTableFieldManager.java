package strosoft.sys.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MoveDirection;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.util.DataTypeHelper;
import strosoft.sys.data.SysBasicTableField;
import strosoft.sys.data.SysBasicTableFieldMaster;

/*
 * 数据层
 */

public class SysBasicTableFieldManager extends SysBasicTableFieldMaster {
    private static SysBasicTableFieldManager _instance;

    public static SysBasicTableFieldManager getInstance() {
        if (_instance == null) {
            _instance = new SysBasicTableFieldManager();
        }
        return _instance;
    }

    private SysBasicTableFieldManager() {
        this.setTableName("sys_basic_table_field");
        this.setTypeName("SysBasicTableField");
    }

    public int add(SysBasicTableField entity) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = add(sqlSession, entity);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int add(SqlSession sqlSession, SysBasicTableField entity) {
        int rows = sqlSession.insert(
                "SysBasicTableField.insertSysBasicTableField", entity);
        return rows;
    }

    public int addList(List<SysBasicTableField> list) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = addList(sqlSession, list);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int addList(SqlSession sqlSession, List<SysBasicTableField> list) {
        int rows = sqlSession.insert(
                "SysBasicTableField.insertSysBasicTableFields", list);
        return rows;
    }

    public int delete(String tableName, String fieldName) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = delete(sqlSession, tableName, fieldName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int delete(SqlSession sqlSession, String tableName, String fieldName) {
        int rows = delete(sqlSession,
                getEntity(sqlSession, tableName, fieldName));
        return rows;
    }

    public int delete(SysBasicTableField entity) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = delete(sqlSession, entity);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int delete(SqlSession sqlSession, SysBasicTableField entity) {
        int rows = sqlSession.delete(
                "SysBasicTableField.deleteSysBasicTableField", entity);
        return rows;
    }
/*	public int batchDeleteByEntity(List<SysBasicTableField> list)
			throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		int rows = 0;
		try {
			rows = batchDeleteByEntity(sqlSession, list);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return rows;
	}*/


    public DataEntity getDataEntity(String tableName, String fieldName)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        DataEntity newDataEntity = null;
        try {
            newDataEntity = getDataEntity(sqlSession, tableName, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return newDataEntity;
    }

    public DataEntity getDataEntity(SqlSession sqlSession, String tableName,
                                    String fieldName) {
        DataEntity newDataEntity = getEntity(sqlSession, tableName, fieldName);
        return newDataEntity;
    }

    public SysBasicTableField getEntity(String tableName, String fieldName)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField newEntity = null;
        try {
            newEntity = getEntity(sqlSession, tableName, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return newEntity;
    }

    public SysBasicTableField getEntity(SqlSession sqlSession,
                                        String tableName, String fieldName) {
        SysBasicTableField entity = createEntity();
        entity.setTableName(tableName);
        entity.setFieldName(fieldName);
        SysBasicTableField newEntity = sqlSession.selectOne(
                "SysBasicTableField.selectSysBasicTableField", entity);
        return newEntity;
    }

    public List<SysBasicTableField> getList() throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getList(sqlSession);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getList(SqlSession sqlSession) {
        List<SysBasicTableField> list = sqlSession
                .selectList("SysBasicTableField.selectSysBasicTableFields");
        return list;
    }

    public List<SysBasicTableField> getList(String condition)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getList(sqlSession, condition);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getList(SqlSession sqlSession,
                                            String condition) {
        List<SysBasicTableField> list = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByCondition",
                condition);
        return list;
    }

    public List<SysBasicTableField> getList(String condition, String orderBy)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getList(sqlSession, condition, orderBy);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getList(SqlSession sqlSession,
                                            String condition, String orderBy) {
        String conditionAndOrderBy = condition + " orderBy " + orderBy;
        List<SysBasicTableField> list = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByCondition",
                conditionAndOrderBy);
        return list;
    }

    public List<SysBasicTableField> getListPage(int pagesize, int pageindex)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getListPage(sqlSession, pagesize, pageindex);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getListPage(SqlSession sqlSession,
                                                int pagesize, int pageindex) {
        String limt = " limt " + pagesize * (pageindex - 1) + "," + pagesize
                * pageindex;
        List<SysBasicTableField> list = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByCondition", limt);
        return list;
    }

    public List<SysBasicTableField> getListPage(String condition, int pagesize,
                                                int pageindex) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getListPage(sqlSession, condition, pagesize, pageindex);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getListPage(SqlSession sqlSession,
                                                String condition, int pagesize, int pageindex) {
        String sql = condition + " limt " + pagesize * (pageindex - 1) + ","
                + pagesize * pageindex;
        List<SysBasicTableField> list = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByCondition", sql);
        return list;
    }

    public List<SysBasicTableField> getListPage(String condition,
                                                String orderBy, int pagesize, int pageindex) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> list = null;
        try {
            list = getListPage(sqlSession, condition, orderBy, pagesize,
                    pageindex);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<SysBasicTableField> getListPage(SqlSession sqlSession,
                                                String condition, String orderBy, int pagesize, int pageindex) {
        String sql = condition + " orderBy " + orderBy + " limt " + pagesize
                * (pageindex - 1) + "," + pagesize * pageindex;
        List<SysBasicTableField> list = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByCondition", sql);
        return list;
    }

    public Integer getCount(String condition) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Integer count = null;
        try {
            count = getCount(sqlSession, condition);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return count;
    }

    public Integer getCount(SqlSession sqlSession, String condition) {
        Integer rows = sqlSession.selectOne(
                "SysBasicTableField.selectCountByCondition", condition);
        return rows;
    }

    public int update(SysBasicTableField entity) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = update(sqlSession, entity);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int update(SqlSession sqlSession, SysBasicTableField entity)
            throws IOException {
        int rows = sqlSession.update(
                "SysBasicTableField.updateSysBasicTableField", entity);
        return rows;
    }

    public SysBasicTableField createEntity() {
        return new SysBasicTableField();
    }

    public DataEntity createDataEntity() {
        return new SysBasicTableField();
    }

    public int clear() throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = clear(sqlSession);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int clear(SqlSession sqlSession) {
        int rows = 0;
        List<SysBasicTableField> list = getList(sqlSession);
        for (int i = 0; i < list.size(); i++) {
            rows += delete(sqlSession, list.get(i));
        }
        return rows;
    }

    public int deleteByTableName(String tableName) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        int rows = 0;
        try {
            rows = deleteByTableName(sqlSession, tableName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return rows;
    }

    public int deleteByTableName(SqlSession sqlSession, String tableName) {
        List<SysBasicTableField> list = getListByTableName(sqlSession,
                tableName);
        int rows = batchDeleteByEntity(sqlSession, list);
        return rows;
    }

    public List<SysBasicTableField> getListByTableName(String tableName)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        List<SysBasicTableField> entitys = null;
        try {
            entitys = getListByTableName(sqlSession, tableName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return entitys;
    }

    public List<SysBasicTableField> getListByTableName(SqlSession sqlSession,
                                                       String tableName) {
        List<SysBasicTableField> entitys = sqlSession.selectList(
                "SysBasicTableField.selectSysBasicTableFieldByTableName",
                tableName);
        return entitys;
    }

    public SysBasicTableField getEntityByTableName(String tableName)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField entity = null;
        try {
            entity = getEntityByTableName(sqlSession, tableName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return entity;
    }

    public SysBasicTableField getEntityByFieldName(String fieldName)
            throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        SysBasicTableField entity = null;
        try {
            entity = getEntityByFieldName(sqlSession, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return entity;
    }

    public SysBasicTableField getEntityByTableName(SqlSession sqlSession,
                                                   String tableName) {
        SysBasicTableField entity = sqlSession.selectOne(
                "selectSysBasicTableFieldByCondition", "table_name=tableName");
        return entity;
    }

    public SysBasicTableField getEntityByFieldName(SqlSession sqlSession,
                                                   String fieldName) {
        SysBasicTableField entity = sqlSession.selectOne(
                "selectSysBasicTableFieldByCondition", "field_name=fieldName");
        return entity;
    }

    public boolean existsTableName(String tableName) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try {
            flag = existsTableName(sqlSession, tableName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return flag;
    }

    public boolean existsFieldName(String fieldName) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        Boolean flag = null;
        try {
            flag = existsFieldName(sqlSession, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return flag;
    }

    public boolean existsTableName(SqlSession sqlSession, String tableName) {
        Integer rows = sqlSession.selectOne(
                "SysBasicTableField.selectIntBySql",
                "select count(id) where table_name= '" + tableName + "'");
        return rows > 0 ? true : false;
    }

    public boolean existsFieldName(SqlSession sqlSession, String fieldName) {
        Integer rows = sqlSession.selectOne(
                "SysBasicTableField.selectIntBySql",
                "select count(id) where field_name= '" + fieldName + "'");
        return rows > 0 ? true : false;
    }

    /**
     * 从表中添加字段到自定义表
     *
     * @param tableName   字段来源表
     * @param tableSchema 字段来源数据库
     * @param customTable 自定义表
     * @throws Exception 从表中添加字段失败
     */
    public void addCustomFieldFromTable(String tableName, String tableSchema,
                                        String customTable) throws Exception {
        SqlSession sqlSession = null;
        try {
            ArrayList<LinkedHashMap<String, Object>> columns = MyBatisManager.getInstance().getAllColumns(tableSchema, tableName);
            ArrayList<LinkedHashMap<String, Object>> refernces = MyBatisManager.getInstance().getReferencedColumns(tableSchema,
                    tableName);/*
			ArrayList<LinkedHashMap<String, Object>> refernced = MyBatisManager.getInstance().getBeReferencedColumns(tableSchema,
			tableName);*/
            ArrayList<LinkedHashMap<String, Object>> uniques = MyBatisManager.getInstance().getUniqueColumns(tableSchema,
                    tableName);
            List<SysBasicTableField> sysBasicTableFieldList = new ArrayList<SysBasicTableField>();
            for (int i = 0; i < columns.size(); i++) {
                LinkedHashMap<String, Object> currentColumnHashMap = columns.get(i);
                String caption = (String) currentColumnHashMap.get("COLUMN_COMMENT");
                String fieldName = (String) currentColumnHashMap.get("COLUMN_NAME");
                String isNullable = (String) currentColumnHashMap.get("IS_NULLABLE");
                String columnKey = (String) currentColumnHashMap.get("COLUMN_KEY");
                String extra = (String) currentColumnHashMap.get("EXTRA");
                String dataType = (String) currentColumnHashMap.get("DATA_TYPE");
                Object characterMaxLength = currentColumnHashMap.get("CHARACTER_MAXIMUM_LENGTH");
                Object numericPrecision = currentColumnHashMap.get("NUMERIC_PRECISION");
                Object numericScale = currentColumnHashMap.get("NUMERIC_SCALE");
                Integer fieldPrecision = (null == numericPrecision) ? null : Integer.parseInt(numericPrecision.toString());
                Integer fieldScale = (null == numericScale) ? null : Integer.parseInt(numericScale.toString());
                Integer maxLength = (null == characterMaxLength) ? null : Integer.parseInt(characterMaxLength.toString());
                Integer isAutoIncrease = extra.equalsIgnoreCase("auto_increment") ? 1 : 0;
                Integer isPrimaryKey = columnKey.equalsIgnoreCase("PRI") ? 1 : 0;
                Integer required = isNullable.equalsIgnoreCase("yes") ? 0 : 1;
                caption = caption.equals("") ? fieldName : caption;
                String maxValue = null;
                String minValue = null;
                if (dataType.equals("int")) {
                    maxValue = "" + Integer.MAX_VALUE;
                    minValue = "" + Integer.MIN_VALUE;
                }
                if (null != fieldPrecision && null != fieldScale && !dataType.equals("int")) {
                    maxValue = getMaxValeByNuberLenth(fieldPrecision, fieldScale);
                    minValue = "-" + maxValue;
                }
                SysBasicTableField sysBasicTableField = new SysBasicTableField();
                sysBasicTableField.setTableName(customTable);
                sysBasicTableField.setFieldName(fieldName);
                sysBasicTableField.setDisplayOrder(i);
                sysBasicTableField.setEnable(1);
                sysBasicTableField.setCaption(caption);
                sysBasicTableField.setRequired(required);
                sysBasicTableField.setIsPrimaryKey(isPrimaryKey);
                sysBasicTableField.setIsAutoIncrease(isAutoIncrease);
                sysBasicTableField.setSysFieldDataTypeCode(dataType);
                sysBasicTableField.setMaxLength(maxLength);
                sysBasicTableField.setFieldPrecision(fieldPrecision);
                sysBasicTableField.setFieldScale(fieldScale);
                sysBasicTableField.setShowInForm(1);
                sysBasicTableField.setShowInGrid(1);
                sysBasicTableField.setColumnSpan(1);
                sysBasicTableFieldList.add(sysBasicTableField);
                setFieldDatasource(sysBasicTableField, refernces);
                setFieldTypeBySqlType(sysBasicTableField);
                //设置是否唯一
                sysBasicTableField.setIsUnique(getIsUnique(sysBasicTableField, uniques));
                sysBasicTableField.setMaxValue(maxValue);
                sysBasicTableField.setMinValue(minValue);
                //如果该字段是自增的ID类型主键，不在列表和详情表单上显示；除文本域、display_order和Id自增主键外都设置为查询字段
                sysBasicTableField.setShowInQuery(1);
                if ((isPrimaryKey - 1 == 0) && (isAutoIncrease - 1 == 0)) {
                    sysBasicTableField.setShowInForm(0);
                    sysBasicTableField.setShowInGrid(0);
                    sysBasicTableField.setShowInQuery(0);
                }
                if (sysBasicTableField.getSysUiControlCode().equals("TextArea")) {
                    sysBasicTableField.setShowInQuery(0);
                    sysBasicTableField.setColumnSpan(2);
                }
                if (sysBasicTableField.getFieldName().equals("display_order")) {
                    sysBasicTableField.setShowInQuery(0);
                }
            }
            sqlSession = MyBatisManager.getInstance().openSession();
            this.addList(sqlSession, sysBasicTableFieldList);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != sqlSession) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }

    /**
     * 添加自定义表的引用字段和表
     *
     * @param sysBasicTableField 字段
     * @param refernces          引用记录列表
     * @throws Exception
     * @throws IOException
     */
    public void setFieldDatasource(SysBasicTableField sysBasicTableField, ArrayList<LinkedHashMap<String, Object>> refernces) throws IOException, Exception {
        String fieldName = sysBasicTableField.getFieldName();
        for (int i = 0; i < refernces.size(); i++) {
            LinkedHashMap<String, Object> currentReferncesHashMap = refernces.get(i);
            String columnName = (String) currentReferncesHashMap.get("COLUMN_NAME");
            Object referncedTableName = currentReferncesHashMap.get("REFERENCED_TABLE_NAME");
            Object referncedColumnName = currentReferncesHashMap.get("REFERENCED_COLUMN_NAME");
            Object referncedTableSchema = currentReferncesHashMap.get("REFERENCED_TABLE_SCHEMA");
            if (columnName.equals(fieldName) && null != referncedTableName) {
                //获取被引用表的字段
                ArrayList<LinkedHashMap<String, Object>> referncedTableColumns = MyBatisManager.getInstance().getAllColumns((String) referncedTableSchema, (String) referncedTableName);
                //被引用表是否为树形结构
                Boolean isTree = false;
                //被引用表是否有dispaly_order排序字段
                Boolean isOrder = false;
                for (int j = 0; j < referncedTableColumns.size(); j++) {
                    LinkedHashMap<String, Object> currentColumns = referncedTableColumns.get(j);
                    String currentColumName = (String) currentColumns.get("COLUMN_NAME");
                    //判断是否有parent_id或parent_code，若有默认为树形结构
                    if (currentColumName.equals("parent_id") || currentColumName.equals("parent_code")) {
                        isTree = true;
                    }
                    //判断是否有有display_order可排序字段
                    if (currentColumName.equals("display_order")) {
                        isOrder = true;
                    }
                }
                //如果是树形结构设置页面控件为树形下拉框，不是树形设置为下拉框
                if (isTree) {
                    sysBasicTableField.setSysUiControlCode("ComboTree");
                } else {
                    sysBasicTableField.setSysUiControlCode("ComboBox");
                }
                //如果有排序字段，设置引用数据源默认排序字段为display_order
                if (isOrder) {
                    sysBasicTableField.setDatasourceOrderBy("display_order");
                }
                sysBasicTableField.setDatasourceTextField(sysBasicTableField.getCaption());
                sysBasicTableField.setDatasourceTableName((String) referncedTableName);
                sysBasicTableField.setDatasourceValueField((String) referncedColumnName);
            }

        }
    }

    /**
     * 根据字段的数据库类型和是否为引用字段设置字段的页面控件类型、默认格式化字符串、默认对齐方式、表单宽高、列表项宽
     *
     * @param sysBasicTableField 字段信息
     */
    public void setFieldTypeBySqlType(SysBasicTableField sysBasicTableField) {
        String dataTypeCode = sysBasicTableField.getSysFieldDataTypeCode();
        HashMap<String, String> dataType = DataTypeHelper.getDataTypeBySqlType(dataTypeCode);
        String formatString = dataType.get("formatString");
        String sysAlignCode = dataType.get("sysAlignCode");
        String sysUiControlCode = dataType.get("sysUiControlCode");
        String sysValidationDataTypeCode = dataType.get("sysValidationDataTypeCode");
        sysBasicTableField.setFormatString(formatString);
        sysBasicTableField.setSysAlignCode(sysAlignCode);
        sysBasicTableField.setSysValidationDataTypeCode(sysValidationDataTypeCode);
        String sourceValueField = sysBasicTableField.getDatasourceValueField();
        //非引用字段，默认页面控件。引用字段在前面已经设置过页面控件。
        if (null == sourceValueField) {
            sysBasicTableField.setSysUiControlCode(sysUiControlCode);
        }
        //表单默认宽度：null;页面会字段设置100%;
        sysBasicTableField.setFormItemWidth(null);
        //文本框表单的默认高度：24px;列表项宽：150px
        sysBasicTableField.setFormItemHeight(30);
        sysBasicTableField.setListItemWidth(150);
        //文本域表单的默认高度：120px;
        if (sysUiControlCode.equals("TextArea")) {
            sysBasicTableField.setFormItemHeight(120);
        }
    }

    /**
     * 从数据库中获取字段的唯一性；唯一：1，不唯一：0
     *
     * @param sysBasicTableField
     * @return
     */
    public int getIsUnique(SysBasicTableField sysBasicTableField, ArrayList<LinkedHashMap<String, Object>> uniques) {
        String fieldName = sysBasicTableField.getFieldName();
        for (int i = 0; i < uniques.size(); i++) {
            String columnName = (String) uniques.get(i).get("Column_name");
            if (columnName.equals(fieldName)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 根据数字长度和小数点位数获取最大数字的字符串
     *
     * @param fieldPrecision 数字长度
     * @param fieldScale     小数点位数
     * @return 最大值
     */
    public String getMaxValeByNuberLenth(Integer fieldPrecision, Integer fieldScale) {
        String maxValue = "";
        for (int i = 0; i < fieldPrecision - fieldScale; i++) {
            maxValue += "9";
        }
        if (fieldScale > 0) {
            maxValue += ".";
        }
        for (int i = 0; i < fieldScale; i++) {
            maxValue += "9";
        }
        return maxValue;
    }

    /*
     * 移动
     */
    public void move(String tableName, String moveDirection, String fieldName) throws Exception {
        String condition = "table_name='" + tableName + "'";
        int exchangeDisplayOrder = this.getExchangeDisplayOrder(tableName, fieldName, moveDirection, condition);
        if (exchangeDisplayOrder == -1) {
            return;
        }
        String exchangeFieldName = this.getFieldNameByDisplayOrder(tableName,
                exchangeDisplayOrder, condition);
        if (exchangeFieldName == null || exchangeFieldName.equals("")) {
            return;
        }
        int displayOrder = this.getDisplayOrderByFieldName(tableName, fieldName);
        String updateExchangeCommandText = String.format(
                "update sys_basic_table_field set display_order=%s where table_name='%s' and field_name='%s'", displayOrder, tableName,
                exchangeFieldName);
        String updateCommandText = String.format(
                "update sys_basic_table_field set display_order=%s where table_name='%s' and field_name='%s'", exchangeDisplayOrder, tableName,
                fieldName);
        String[] sqls = new String[]{updateExchangeCommandText,
                updateCommandText};
        MyBatisManager.getInstance().executeUpdate(sqls);
    }

    /*
     * 根据编码取得显示顺序
     */
    private int getDisplayOrderByFieldName(String tableName, String fieldName) throws Exception {
        String sql = String
                .format("select display_order from sys_basic_table_field where table_name='%s' and field_name='%s'",
                        tableName, fieldName);
        return MyBatisManager.getInstance().executeInt(sql);
    }

    /*
     * 取得ID
     */
    private String getFieldNameByDisplayOrder(String tableName, int displayOrder,
                                              String condition) throws Exception {
        String sql = String.format(
                "select field_name from sys_basic_table_field where display_order=%s and table_name='%s'", displayOrder, tableName);
        String fieldName = MyBatisManager.getInstance().executeString(sql);
        return fieldName;
    }

    /*
     * 取得交换DisplayOrder
     */
    private int getExchangeDisplayOrder(String tableName, String fieldName, String moveDirection,
                                        String condition) throws Exception {
        String sql = "";
        if (moveDirection.equals(MoveDirection.MoveUp)) {
            sql = String
                    .format("select ifnull(max(display_order),-1) from sys_basic_table_field where " + condition + " and display_order<(select display_order from sys_basic_table_field where field_name='%s' and table_name = '%s')",
                            fieldName, tableName);
        } else {
            sql = String
                    .format("select ifnull(min(display_order), -1) from sys_basic_table_field where " + condition + " and display_order>(select display_order from sys_basic_table_field where field_name='%s' and table_name = '%s')",
                            fieldName, tableName);
        }
        int exchangeDisplayOrder = MyBatisManager.getInstance().executeInt(sql);
        return exchangeDisplayOrder;
    }

}