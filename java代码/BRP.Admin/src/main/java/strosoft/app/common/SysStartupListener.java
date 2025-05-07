package strosoft.app.common;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import strosoft.app.util.Logger;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.app.util.XmlDocumentHelper;
import strosoft.main.boot.JAppApplication;
import strosoft.sys.model.AddApplicationTaskManagerThread;
import strosoft.sys.model.DatabaseBackupManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.*;

@WebListener
public class SysStartupListener implements ServletContextListener {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    private static final List<ScheduledTask> lstScheduledTask = new ArrayList<>();

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        for (ScheduledTask curScheduledTask : lstScheduledTask) {
            try {
                curScheduledTask.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 设置绝对路径
        String rootPath = null;
        try {
            rootPath = ResourceUtils.getURL("classpath:").getPath();
            SysConfig.setRootPath(rootPath);
            // 设置环境路径
            SysConfig.setContextPath(sce.getServletContext().getContextPath());
            // 设置配置信息
            this.setConfig();
            Tracer.writeLine("rootPath:" + rootPath);
            // 系统启动时，向验证服务器添加系统的客户系统的应用信息
            this.sendAddApplicationPost();
            // 启动数据库备份线程
            this.startDatabaseBackup();
            // 每 10 秒唤醒一次数据库
            this.pingDatabase();
            //启动计划任务
            this.startScheduledTasks();
        } catch (Exception e) {
            e.printStackTrace();
            Tracer.writeLine(String.valueOf(e.getStackTrace()));
        }
    }

    /**
     * 启动计划任务
     */
    private void startScheduledTasks() throws Exception {
        //取得计划任务配置节点列表
        List<Node> lstNode = this.getScheduledTaskList();
        if (lstNode == null) {
            return;
        }
        for (Node curNode : lstNode) {
            try {
                ScheduledTask newScheduledTask = this.createScheduledTask(curNode);
                newScheduledTask.setConfigNode(curNode);
                int period = XmlDocumentHelper.getIntAttribute(curNode, "Period");
                System.out.println("period:" + period);
                newScheduledTask.setPeriod(period);
                int delay = XmlDocumentHelper.getIntAttribute(curNode, "Delay", 0);
                System.out.println("delay:" + delay);
                newScheduledTask.setDelay(delay);
                newScheduledTask.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private ScheduledTask createScheduledTask(Node curNode) throws Exception {
        String handlerTypeName = XmlDocumentHelper.getAttribute(
                curNode, "Type");
        Class serviceHandlerClass = Class.forName(handlerTypeName);
        ScheduledTask scheduledTask = (ScheduledTask) serviceHandlerClass
                .newInstance();
        return scheduledTask;
    }

    private List<Node> getScheduledTaskList() throws Exception {
        String relativeConfigFilePath = "/config/ScheduledTasks.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        String configFilePath = SysConfig.getRootPath()
                + relativeConfigFilePath;
        Document document = null;
        try {
            document = db.parse(configFilePath);
        } catch (Exception e) {
            throw new Exception(String.format("加载%s出错：%s", relativeConfigFilePath, e.getMessage()));
        }
        // 取得服务处理类配置节点列表
        List<Node> lstServiceHandlerNode = new ArrayList<Node>();
        // 添加包含文件中节点
        NodeList nlInclude = document.getElementsByTagName("ScheduledTask");
        for (int i = 0; i < nlInclude.getLength(); i++) {
            Node nodeInclude = nlInclude.item(i);
            lstServiceHandlerNode.add(nodeInclude);
        }
        return lstServiceHandlerNode;
    }

    /*
     * 设置配置信息
     */
    private void setConfig() {
        String rootPath = SysConfig.getRootPath();
        Properties theProperties = new Properties();
        FileInputStream theFileInputStream;
        try {
            theFileInputStream = new FileInputStream(rootPath + "/config/config.properties");
            theProperties.load(theFileInputStream);
            // 数据库驱动
            String databaseDriver = theProperties.getProperty("databaseDriver");
            SysConfig.setDatabaseDriver(databaseDriver);
            // 数据库Url
            String databaseUrl = theProperties.getProperty("databaseUrl");
            SysConfig.setDatabaseUrl(databaseUrl);
            // 数据库用户
            String databaseUser = theProperties.getProperty("databaseUser");
            SysConfig.setDatabaseUser(databaseUser);
            // 数据库密码
            String databasePassword = theProperties.getProperty("databasePassword");
            SysConfig.setDatabasePassword(databasePassword);
            // 数据库主机地址
            String databaseHost = theProperties.getProperty("databaseHost");
            SysConfig.setDatabaseHost(databaseHost);
            // 数据库端口
            String databasePort = theProperties.getProperty("databasePort");
            SysConfig.setDatabasePort(databasePort);
            // 数据库名称
            String databaseName = theProperties.getProperty("databaseName");
            SysConfig.setDatabaseName(databaseName);
            // 是否启用数据库备份
            String databaseBackupEnabled = theProperties.getProperty("databaseBackupEnabled");
            SysConfig.setDatabaseBackupEnabled(Boolean.valueOf(databaseBackupEnabled));
            // 数据库备份模式（间隔时长，指定时间）
            String databaseBackupMode = theProperties.getProperty("databaseBackupMode");
            SysConfig.setDatabaseBackupMode(databaseBackupMode);
            // 数据库备份间隔时长
            String databaseBackupInterval = theProperties.getProperty("databaseBackupInterval");
            if (!StringHelper.isNullOrEmpty(databaseBackupInterval)) {
                SysConfig.setDatabaseBackupInterval(Integer.parseInt(databaseBackupInterval));
            }
            // 数据库备份时间
            String databaseBackupTime = theProperties.getProperty("databaseBackupTime");
            SysConfig.setDatabaseBackupTime(databaseBackupTime);
            // 数据库备份路径
            String databaseBackupPath = theProperties.getProperty("databaseBackupPath");
            SysConfig.setDatabaseBackupPath(databaseBackupPath);
            // 系统根地址
            String rootUrl = theProperties.getProperty("rootUrl");
            SysConfig.setRootUrl(rootUrl);
            // Admin根地址
            String adminRootUrl = theProperties.getProperty("adminRootUrl");
            SysConfig.setAdminRootUrl(adminRootUrl);
            // mybatis配置文件路径
            String myBatisConfigFile = rootPath + theProperties.getProperty("myBatisConfigFile");
            SysConfig.setMyBatisConfigFile(myBatisConfigFile);
            // 上传文件目录
            String uploadDirectory = theProperties.getProperty("uploadDirectory");
            SysConfig.setUploadDirectory(uploadDirectory);
            // 上传文件绝对路径
            String resourceDirectoryPath = theProperties.getProperty("resourceDirectoryPath");
            SysConfig.setResourceDirectoryPath(resourceDirectoryPath);
            // 短信提供者名称
            String smsProviderName = theProperties.getProperty("smsProviderName");
            SysConfig.setSmsProviderName(smsProviderName);
            // 微信AppId
            String WxAppId = theProperties.getProperty("WxAppId");
            SysConfig.setWxAppId(WxAppId);
            // 微信AppSecret
            String WxAppSecret = theProperties.getProperty("WxAppSecret");
            SysConfig.setWxAppSecret(WxAppSecret);
            // 微信TemplateID
            String TemplateId = theProperties.getProperty("TemplateId");
            SysConfig.setTemplateID(TemplateId);
            // 翻译提供者名称
            String translateProviderName = theProperties.getProperty("translateProviderName");
            SysConfig.setTranslateProviderName(translateProviderName);
            // 检验服务地址
            String verificationServiceAddress = theProperties.getProperty("verificationServiceAddress");
            SysConfig.setVerificationServiceAddress(verificationServiceAddress);
            // 用户Session检测间隔
            String userSessionCheckInertval = theProperties.getProperty("userSessionCheckInertval");
            SysConfig.setUserSessionCheckInertval(Integer.parseInt(userSessionCheckInertval));
            // 应用程序ID
            String applicationId = theProperties.getProperty("applicationId");
            SysConfig.setApplicationId(applicationId);
            // 服务器地址
            String serviceAddress = theProperties.getProperty("serviceAddress");
            // 百度AI接口Key
            SysConfig.setServiceAddress(serviceAddress);
            String BDAIApiKey = theProperties.getProperty("BDAIApiKey");
            // 百度AI密钥
            SysConfig.setBDAIApiKey(BDAIApiKey);
            String BDAISecretKey = theProperties.getProperty("BDAISecretKey");
            SysConfig.setBDAISecretKey(BDAISecretKey);

            // 是否开启日志
            String strLogEnabled = theProperties.getProperty("logEnabled");
            if (StringHelper.isNullOrEmpty(strLogEnabled)) {
                Logger.setLogEnabled(true);
            } else {
                Boolean logEnabled = Boolean.valueOf(strLogEnabled);
                Logger.setLogEnabled(logEnabled);
            }
            // 日志文件路径
            String logFilePath = theProperties.getProperty("logFilePath");
            if (StringHelper.isNullOrEmpty(logFilePath)) {
                Logger.setLogFilePath(rootPath + "/log.txt");
            } else {
                Logger.setLogFilePath(logFilePath);
            }
            // 是否开始跟踪
            String strTraceEnabled = theProperties.getProperty("traceEnabled");
            if (StringHelper.isNullOrEmpty(strTraceEnabled)) {
                Tracer.setTraceEnabled(false);
            } else {
                Boolean traceEnabled = Boolean.valueOf(strTraceEnabled);
                Tracer.setTraceEnabled(traceEnabled);
            }
            // 跟踪文件路径
            String traceFilePath = theProperties.getProperty("traceFilePath");
            if (StringHelper.isNullOrEmpty(traceFilePath)) {
                Tracer.setTraceFilePath(rootPath + "/trace.txt");
            } else {
                Tracer.setTraceFilePath(traceFilePath);
            }
            // 数据库ping间隔时间
            String strDatabasePingInterval = theProperties.getProperty("databasePingInterval");
            if (!StringHelper.isNullOrEmpty(strDatabasePingInterval)) {
                SysConfig.setUserSessionCheckInertval(Integer.parseInt(strDatabasePingInterval));
            }
            // 百度编辑器图片地址前缀
            String strUeditorImagePrefixes = theProperties.getProperty("ueditorImagePrefixes");
            if (!StringHelper.isNullOrEmpty(strUeditorImagePrefixes)) {
                String[] arrUeditorImagePrefix = strUeditorImagePrefixes.split(";");
                List<String> lstUeditorImagePrefix = new ArrayList<String>();
                for (String currentUeditorImagePrefix : arrUeditorImagePrefix) {
                    if (!StringHelper.isNullOrEmpty(currentUeditorImagePrefix)) {
                        lstUeditorImagePrefix.add(currentUeditorImagePrefix);
                    }
                }
                SysConfig.setUeditorImagePrefixList(lstUeditorImagePrefix);
            }
            // MySql安装目录
            String mysqlPath = theProperties.getProperty("mysqlPath");
            SysConfig.setMysqlPath(mysqlPath);
            Tracer.writeLine("设置配置信息完成。");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Tracer.writeLine("设置配置信息出错：" + e.getStackTrace().toString());
            e.printStackTrace();
        }
    }

    /**
     * 启动发送验证信息线程
     */
    private void sendAddApplicationPost() {
        AddApplicationTaskManagerThread buildTaskManagerThread = new AddApplicationTaskManagerThread();
        Thread t1 = new Thread(buildTaskManagerThread);
        t1.start();
    }

    /**
     * 启动数据库自动备份
     */
    private void startDatabaseBackup() {
        if (!SysConfig.getDatabaseBackupEnabled()) {
            return;
        }
        DatabaseBackupManager.getInstance().start();
    }

    /**
     * 保持数据库连接
     */
    private void pingDatabase() {
        long period = SysConfig.getDatabasePingInterval() * 1000;
        if (period == 0) {
            return;
        }
        TimerTask pingTask = new TimerTask() {
            @Override
            public void run() {
                String sql = "SELECT 1 from dual";
                try {
                    MyBatisManager.getInstance().executeInt(sql);
                    logger.info("database ping!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Timer().scheduleAtFixedRate(pingTask, 1000L, period);
    }

}
