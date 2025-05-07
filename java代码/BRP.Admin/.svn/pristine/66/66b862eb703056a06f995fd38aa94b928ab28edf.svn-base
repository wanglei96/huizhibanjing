package strosoft.sys.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.SysSetting;
import strosoft.sys.data.SysSettingMaster;

public class SysSettingManager extends SysSettingMaster {
    private static SysSettingManager _instance;

    public static SysSettingManager getInstance() {
        if (_instance == null) {
            _instance = new SysSettingManager();
        }
        return _instance;
    }

    private SysSettingManager() {
        this.setTableName("sys_setting");
        this.setTypeName("SysSetting");
    }

    @SuppressWarnings("unchecked")
    public ArrayList<SysSetting> getList() throws IOException {
        List<Object> lstDataEntity = super.getObjectList();
        return super.toList(lstDataEntity);
    }

    /**
     * 取得字符串参数值
     *
     * @param code
     * @return
     */
    public String getSetting(String code) {
        return getSetting(code, "");
    }

    /**
     * 取得字符串参数值
     *
     * @param defaultValue
     * @return
     */
    public String getSetting(String code, String defaultValue) {
        try {
            SysSetting sysSetting = getEntity(code);
            if (sysSetting == null) {
                return defaultValue;
            }
            String strValue = sysSetting.getValue();
            return strValue;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 取得整数参数值
     *
     * @param defaultValue
     * @return
     */
    public Integer getIntSetting(String code, int defaultValue) {
        try {

            SysSetting sysSetting = getEntity(code);
            if (sysSetting == null) {
                return defaultValue;
            }
            Integer intValue = Integer.parseInt(sysSetting.getValue());
            return intValue;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 取得整数参数值
     *
     * @param defaultValue
     * @return
     */
    public Integer getIntSetting(SqlSession session, String code, int defaultValue) {
        try {

            SysSetting sysSetting = getEntity(session, code);
            if (sysSetting == null) {
                return defaultValue;
            }
            Integer intValue = Integer.parseInt(sysSetting.getValue());
            return intValue;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 取得布尔参数值
     *
     * @param string
     * @param defaultValue
     * @return
     */
    public Boolean getBoolSetting(String string, boolean defaultValue) {
        try {
            SysSetting sysSetting = getEntity(string);
            if (sysSetting == null) {
                return defaultValue;
            }
            Boolean value = Boolean.parseBoolean(sysSetting.getValue());
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 取得BigDecimal参数值
     *
     * @param string
     * @param defaultValue
     * @return
     */
    public BigDecimal getDecimalSetting(String string, BigDecimal defaultValue) {
        try {
            SysSetting theSysSetting = getEntity(string);
            if (theSysSetting == null) {
                return defaultValue;
            }
            BigDecimal value = new BigDecimal(theSysSetting.getValue());
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 取得BigDecimal参数值
     *
     * @param string
     * @param defaultValue
     * @return
     */
    public BigDecimal getDecimalSetting(SqlSession session, String string, BigDecimal defaultValue) {
        try {
            SysSetting theSysSetting = getEntity(session, string);
            if (theSysSetting == null) {
                return defaultValue;
            }
            BigDecimal value = new BigDecimal(theSysSetting.getValue());
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
