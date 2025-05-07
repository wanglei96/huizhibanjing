package strosoft.app.util;

import strosoft.app.common.SysConfig;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * 这将从 .sql 运行脚本到 CREATE 新模式和 IMPORT 结构
 *
 * @author Administrator
 */
public class MySqlImporter {
    private static final String ROOTPATH = SysConfig.getRootPath();
    private static final String DBHOST = SysConfig.getDatabaseHost();
    private static final String DBPORT = SysConfig.getDatabasePort();
    private static final String DBUSER = SysConfig.getDatabaseUser();
    private static final String DBPASSWORD = SysConfig.getDatabasePassword();
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final Logger logger = Logger.getLogger(MySqlImporter.class.getName());

    /**
     * 这将执行 CREATE 和 IMPORT 结构.sql
     *
     * @param schemaName      新数据库名称
     * @param srcDumpFilePath 导出的sql文件的来源
     * @return boolean        成功：TRUE，失败：FALSE
     * @throws Exception
     */
    public static CompletableFuture<Boolean> doCreateImport(final String schemaName, java.nio.file.Path srcDumpFilePath)
            throws Exception {

        String SQL_FILE_PATH = srcDumpFilePath.toAbsolutePath().toString();
        String SCRIPT_PATH = Paths.get(ROOTPATH).toAbsolutePath().toString();

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        if (OS.startsWith("win")) {
            SCRIPT_PATH += "/scripts/create_import.cmd";
            process = runtime.exec(new String[]{SCRIPT_PATH, DBHOST, DBPORT, DBUSER, DBPASSWORD, schemaName, SQL_FILE_PATH});

        } else {
            SCRIPT_PATH += "/scripts/create_import.sh";
            process = runtime.exec(new String[]{"/bin/bash", SCRIPT_PATH, DBHOST, DBPORT, DBUSER, DBPASSWORD, schemaName,
                    SQL_FILE_PATH});
        }

        // 您可以在此处附加 JDK Logger，
        CommandLinePrinter printer1 = new CommandLinePrinter(process.getInputStream(), logger::info);
        CommandLinePrinter printer2 = new CommandLinePrinter(process.getErrorStream(), logger::warning);

        CompletableFuture.runAsync(printer1);
        CompletableFuture.runAsync(printer2);

        if (process.waitFor() == 0) {
            // ALL IS OK!!
            process.destroy();
            logger.info("======== FINISHED SUCCESSFULLY!===========");
            return CompletableFuture.completedFuture(true);
        } else {
            logger.warning("======== FAILED TO EXECUTE SCRIPT!===========");
            return CompletableFuture.completedFuture(false);
        }

    }
}
