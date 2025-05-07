package strosoft.app.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/*
 * 系统配置
 */
public class SysConfig {

    private static final String configFilePath = "/config/config.properties";
    private static String databaseBackupConfigFilePath = "/config/DatabaseBackup.xml";
    private static String rootPath;
    private static String contextPath;
    private static String databaseDriver;
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;
    private static String databaseHost = "127.0.0.1";
    private static String databasePort = "3306";
    private static String databaseName;
    private static Boolean databaseBackupEnabled;
    private static Integer databaseBackupInterval;
    private static String databaseBackupMode;
    private static String databaseBackupTime;
    private static String databaseBackupPath;
    private static String rootUrl;
    private static String adminRootUrl;
    private static String myBatisConfigFile;
    private static String uploadDirectory;
    private static String resourceDirectoryPath;
    private static String smsProviderName;
    private static String translateProviderName;
    private static String verificationServiceAddress;
    private static Integer userSessionCheckInertval;
    private static String applicationId;
    private static String serviceAddress;
    private static String BDAIApiKey;
    private static String BDAISecretKey;
    private static List<String> ueditorImagePrefixList;
    private static Integer databasePingInterval = 30;
    private static String mysqlPath;
    private static Boolean returnAdminRootUrl = true;

    /* 阿里云存储 OSS */
    private static String ossBucketName;
    private static String ossAccessKeyId;
    private static String ossAccessKeySecret;
    private static String ossEndpoint;
    private static String WxAppId;
    private static String WxAppSecret;
    private static String TemplateID;

    public static String getWxAppId() {
        return WxAppId;
    }

    public static void setWxAppId(String wxAppId) {
        WxAppId = wxAppId;
    }

    public static String getWxAppSecret() {
        return WxAppSecret;
    }

    public static void setWxAppSecret(String wxAppSecret) {
        WxAppSecret = wxAppSecret;
    }

    public static String getTemplateID() {
        return TemplateID;
    }

    public static void setTemplateID(String templateID) {
        TemplateID = templateID;
    }

    /**
     * 取得根路径
     */
    public static String getRootPath() {
        return rootPath;
    }

    /**
     * 设置根目录
     */
    public static void setRootPath(String rootPath) {
        SysConfig.rootPath = rootPath;
    }

    /**
     * 取得数据库驱动类
     */
    public static String getDatabaseDriver() {
        return databaseDriver;
    }

    /**
     * 设置数据库驱动类名
     */
    public static void setDatabaseDriver(String driver) {
        SysConfig.databaseDriver = driver;
    }

    /**
     * 取得数据库Url
     */
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * 设置数据库Url
     */
    public static void setDatabaseUrl(String url) {
        SysConfig.databaseUrl = url;
    }

    /**
     * 取得数据库用户名
     */
    public static String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * 设置数据库用户名
     */
    public static void setDatabaseUser(String user) {
        SysConfig.databaseUser = user;
    }

    /**
     * 取得数据库密码
     */
    public static String getDatabasePassword() {
        return databasePassword;
    }

    /**
     * @param password 设置数据库密码
     */
    public static void setDatabasePassword(String password) {
        SysConfig.databasePassword = password;
    }

    /**
     * @return the adminRootUrl
     */
    public static String getAdminRootUrl() {
        return adminRootUrl;
    }

    /**
     * @param adminRootUrl the adminRootUrl to set 取得管理后台根地址
     */
    public static void setAdminRootUrl(String adminRootUrl) {
        SysConfig.adminRootUrl = adminRootUrl;
    }

    /*
     * 取得MyBatis配置文件路径
     */
    public static String getMyBatisConfigFile() {
        return myBatisConfigFile;
    }

    /*
     * 设置MyBatis配置文件路径
     */
    public static void setMyBatisConfigFile(String myBatisConfigFile) {
        SysConfig.myBatisConfigFile = myBatisConfigFile;
    }

    /*
     * 取得上传文件目录名称
     */
    public static String getUploadDirectory() {
        return uploadDirectory;
    }

    /*
     * 取得上传文件绝对目录名称
     */
    public static String getResourceDirectoryPath() {
        return resourceDirectoryPath;
    }

    /*
     * 取得绝对上传文件目录
     */
    public static String getAbsoluteUploadDirectory() {
//		String realUploadDirectory = getRootPath() + "/" + getUploadDirectory();
        String absoluteUploadDirectory = getResourceDirectoryPath() + "/" + getUploadDirectory();
        return absoluteUploadDirectory;
    }

    /*
     * 设置上传文件目录名称
     */
    public static void setUploadDirectory(String uploadDirectory) {
        SysConfig.uploadDirectory = uploadDirectory;
    }

    /*
     * 设置上传文件目录名称
     */
    public static void setResourceDirectoryPath(String resourceDirectoryPath) {
        SysConfig.resourceDirectoryPath = resourceDirectoryPath;
    }

    /*
     * 取得短信提供者名称
     */
    public static String getSmsProviderName() {
        return smsProviderName;
    }

    /*
     * 设置短信提供者名称
     */
    public static void setSmsProviderName(String smsProviderName) {
        SysConfig.smsProviderName = smsProviderName;
    }

    /*
     * 取得配置文件物理路径
     */
    public static String getRealConfigFilePath() {
        String realConfigFilePath = rootPath + configFilePath;
        return realConfigFilePath;
    }

    /**
     * 取得配置属性
     *
     * @param propertyName
     * @return
     * @throws IOException
     */
    public static String getConfig(String propertyName) throws IOException {
        Properties theProperties = new Properties();
        FileInputStream theFileInputStream;
        theFileInputStream = new FileInputStream(getRealConfigFilePath());
        theProperties.load(theFileInputStream);
        String propertyValue = theProperties.getProperty(propertyName);
        return propertyValue;
    }

    /**
     * 取得带默认值配置属性
     *
     * @param propertyName
     * @param defaultValue
     * @return
     * @throws IOException
     */
    public static String getConfig(String propertyName, String defaultValue) throws IOException {
        String propertyValue = getConfig(propertyName);
        if (propertyValue == null) {
            return defaultValue;
        }
        return propertyValue;
    }

    /**
     * 取得整型配置属性
     *
     * @param propertyName
     * @param defaultValue
     * @return
     * @throws IOException
     */
    public static int getIntConfig(String propertyName, int defaultValue) throws IOException {
        String strPropertyValue = getConfig(propertyName);
        if (strPropertyValue == null) {
            return defaultValue;
        }
        try {
            int intPropertyValue = Integer.parseInt(strPropertyValue);
            return intPropertyValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 取得整型配置属性
     *
     * @param propertyName
     * @param defaultValue
     * @return
     * @throws IOException
     */
    public static Boolean getBoolConfig(String propertyName, Boolean defaultValue) throws IOException {
        String strPropertyValue = getConfig(propertyName);
        if (strPropertyValue == null) {
            return defaultValue;
        }
        try {
            Boolean bPropertyValue = Boolean.parseBoolean(strPropertyValue);
            return bPropertyValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String getVerificationServiceAddress() {
        return verificationServiceAddress;
    }

    public static void setVerificationServiceAddress(String verificationServiceAddress) {
        SysConfig.verificationServiceAddress = verificationServiceAddress;
    }

    public static Integer getUserSessionCheckInertval() {
        return userSessionCheckInertval;
    }

    public static void setUserSessionCheckInertval(Integer userSessionCheckInertval) {
        SysConfig.userSessionCheckInertval = userSessionCheckInertval;
    }

    public static String getApplicationId() {
        return applicationId;
    }

    public static void setApplicationId(String applicationId) {
        SysConfig.applicationId = applicationId;
    }

    public static String getServiceAddress() {
        return serviceAddress;
    }

    public static void setServiceAddress(String serviceAddress) {
        SysConfig.serviceAddress = serviceAddress;
    }

    public static String getBDAIApiKey() {
        return BDAIApiKey;
    }

    public static void setBDAIApiKey(String bDAIApiKey) {
        BDAIApiKey = bDAIApiKey;
    }

    public static String getBDAISecretKey() {
        return BDAISecretKey;
    }

    public static void setBDAISecretKey(String bDAISecretKey) {
        BDAISecretKey = bDAISecretKey;
    }

    /**
     * 取得配置文件路径
     */
    public static String getConfigFilePath() {
        return configFilePath;
    }

    /**
     * 取得绝对上传文件目录
     */
    public static String getRealUploadDirectory() {
        String realUploadDirectory = SysConfig.getRootPath() + "\\" + SysConfig.getUploadDirectory();
        return realUploadDirectory;
    }

    /**
     * 设置环境路径
     *
     * @return
     */
    public static String getContextPath() {
        return contextPath;
    }

    /**
     * 取得环境路径
     *
     * @param contextPath
     */
    public static void setContextPath(String contextPath) {
        SysConfig.contextPath = contextPath;
    }

    public static String getTranslateProviderName() {
        return translateProviderName;
    }

    public static void setTranslateProviderName(String translateProviderName) {
        SysConfig.translateProviderName = translateProviderName;
    }

    public static String getDatabaseBackupConfigFilePath() {
        return databaseBackupConfigFilePath;
    }

    public static void setDatabaseBackupConfigFilePath(String databaseBackupConfigFilePath) {
        SysConfig.databaseBackupConfigFilePath = databaseBackupConfigFilePath;
    }

    public static String getRealDatabaseBackupConfigFilePath() {
        String realDatabaseBackupConfigFilePath = SysConfig.getRootPath() + "/" + databaseBackupConfigFilePath;
        return realDatabaseBackupConfigFilePath;
    }

    public static Boolean getDatabaseBackupEnabled() {
        return databaseBackupEnabled;
    }

    public static void setDatabaseBackupEnabled(Boolean databaseBackupEnabled) {
        SysConfig.databaseBackupEnabled = databaseBackupEnabled;
    }

    public static Integer getDatabaseBackupInterval() {
        return databaseBackupInterval;
    }

    public static void setDatabaseBackupInterval(Integer databaseBackupInterval) {
        SysConfig.databaseBackupInterval = databaseBackupInterval;
    }

    public static String getDatabaseBackupMode() {
        return databaseBackupMode;
    }

    public static void setDatabaseBackupMode(String databaseBackupMode) {
        SysConfig.databaseBackupMode = databaseBackupMode;
    }

    public static String getDatabaseBackupTime() {
        return databaseBackupTime;
    }

    public static void setDatabaseBackupTime(String databaseBackupTime) {
        SysConfig.databaseBackupTime = databaseBackupTime;
    }

    public static String getDatabaseBackupPath() {
        return databaseBackupPath;
    }

    public static String getRealDatabaseBackupPath() {
        String realDatabaseBackupPath = SysConfig.getRootPath() + "/" + databaseBackupPath;
        return realDatabaseBackupPath;
    }

    public static void setDatabaseBackupPath(String databaseBackupPath) {
        SysConfig.databaseBackupPath = databaseBackupPath;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName) {
        SysConfig.databaseName = databaseName;
    }

    public static String getDatabaseHost() {
        return databaseHost;
    }

    public static void setDatabaseHost(String databaseHost) {
        SysConfig.databaseHost = databaseHost;
    }

    public static String getDatabasePort() {
        return databasePort;
    }

    public static void setDatabasePort(String databasePort) {
        SysConfig.databasePort = databasePort;
    }

    public static String getRootUrl() {
        return rootUrl;
    }

    public static void setRootUrl(String rootUrl) {
        SysConfig.rootUrl = rootUrl;
    }

    public static List<String> getUeditorImagePrefixList() {
        return ueditorImagePrefixList;
    }

    public static void setUeditorImagePrefixList(List<String> ueditorImagePrefixList) {
        SysConfig.ueditorImagePrefixList = ueditorImagePrefixList;
    }

    public static Integer getDatabasePingInterval() {
        return databasePingInterval;
    }

    public static void setDatabasePingInterval(Integer databasePingInterval) {
        SysConfig.databasePingInterval = databasePingInterval;
    }

    public static String getMysqlPath() {
        return mysqlPath;
    }

    public static void setMysqlPath(String mysqlPath) {
        SysConfig.mysqlPath = mysqlPath;
    }

    public static Boolean getReturnAdminRootUrl() {
        return returnAdminRootUrl;
    }

    public static void setReturnAdminRootUrl(Boolean returnAdminRootUrl) {
        SysConfig.returnAdminRootUrl = returnAdminRootUrl;
    }

    public static String getOssBucketName() {
        return ossBucketName;
    }

    public static void setOssBucketName(String ossBucketName) {
        SysConfig.ossBucketName = ossBucketName;
    }

    public static String getOssAccessKeyId() {
        return ossAccessKeyId;
    }

    public static void setOssAccessKeyId(String ossAccessKeyId) {
        SysConfig.ossAccessKeyId = ossAccessKeyId;
    }

    public static String getOssAccessKeySecret() {
        return ossAccessKeySecret;
    }

    public static void setOssAccessKeySecret(String ossAccessKeySecret) {
        SysConfig.ossAccessKeySecret = ossAccessKeySecret;
    }

    public static String getOssEndpoint() {
        return ossEndpoint;
    }

    public static void setOssEndpoint(String ossEndpoint) {
        SysConfig.ossEndpoint = ossEndpoint;
    }
}
