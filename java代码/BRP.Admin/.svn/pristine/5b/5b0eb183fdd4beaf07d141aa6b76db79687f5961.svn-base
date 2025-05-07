package strosoft.tools.sms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import strosoft.app.common.SysConfig;

public class MySQLDatabaseBackup {
	/** 
     * Java代码实现MySQL数据库导出 
     * @author GaoHuanjie 
     * @param hostIP MySQL数据库所在服务器地址IP 
     * @param userName 进入数据库所需要的用户名 
     * @param password 进入数据库所需要的密码 
     * @param savePath 数据库导出文件保存路径 
     * @param fileName 数据库导出文件文件名 
     * @param databaseName 要导出的数据库名 
     * @return 返回true表示导出成功，否则返回false。 
	 * @throws Exception 
     */  
    public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws Exception {  
        File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }
        //System.out.println(SysConfig.getConfig(SysConfig.getRootPath()+"config.properties"));
        
        
        System.out.println();
        
        PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;  
        try {  
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));  
            String mysql="mysqldump -h " + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName;
            Runtime rt = Runtime.getRuntime();  
        	String os=System.getProperty("os.name");  
        	Process child = null;  
        	if(os.toLowerCase().startsWith("win")){  
        		child = rt.exec(getName("mysqlURL")+ mysql);  
        	}else if(os.toLowerCase().startsWith("lin")){  
        		child = rt.exec(new String[] { "sh", "-c", mysql });  
        	}else{  
        		System.out.println("other");  
        	}     
            InputStreamReader inputStreamReader = new InputStreamReader(child.getInputStream(), "utf8");  
            bufferedReader = new BufferedReader(inputStreamReader);  
            String line;  
            while((line = bufferedReader.readLine())!= null){  
                printWriter.println(line);  
            }  
            printWriter.flush();  
            if(child.waitFor() == 0){//0 表示线程正常终止。  
                return true;  
            }  
        }catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
     
    /**
     * @param savePath 数据库备份的脚本路径
     * @param fileName 数据库备份的文件名
     * @param hostIP IP地址
     * @param databaseName 数据库名称
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public static boolean restore(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws Exception {
    	File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }
        PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;  
        OutputStreamWriter outputStreamWriter=null;
        try {     
            String mysql="mysql.exe -h " + hostIP + " -u" + userName + " -p" + password + " --default-character-set=utf8 " +databaseName;
            Runtime rt = Runtime.getRuntime();  
        	String os=System.getProperty("os.name");  
        	Process child = null;  
        	if(os.toLowerCase().startsWith("win")){  
        		child = rt.exec(getName("mysqlURL") + mysql);  
        	}else if(os.toLowerCase().startsWith("lin")){  
        		child = rt.exec(new String[] { "sh", "-c", mysql });  
        	}else{  
        		System.out.println("other");  
        	}  
        	outputStreamWriter=new OutputStreamWriter(child.getOutputStream(), "utf8");
        	printWriter = new PrintWriter(outputStreamWriter); 
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(savePath+fileName),"utf-8"));  
            String line;
            //StringBuffer sb = new StringBuffer();
            while((line = bufferedReader.readLine())!= null){  
                printWriter.println(line);
                //sb.append(line + "\r\n");
            }
//            line = sb.toString();
//            outputStreamWriter.write(line);
//            outputStreamWriter.flush();
//            outputStreamWriter.close();
            if(printWriter!=null){
            	printWriter.close();
            }    
            if(child.waitFor() == 0){//0 表示线程正常终止。  
                return true;  
            }  
            
        }catch (IOException e) {  
            e.printStackTrace();  
        } finally { 
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }
                if(outputStreamWriter!= null){
                	outputStreamWriter.close();
                }
                if (printWriter != null) {  
                    printWriter.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }
    
    public static boolean delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile()){
        	return file.delete();
        }
		return false;    
    }
    public static String getName(String name) throws Exception{
    	String rootPath = SysConfig.getRootPath();
        Properties theProperties = new Properties();
        FileInputStream theFileInputStream = new FileInputStream(rootPath + "/config/config.properties");
        theProperties.load(theFileInputStream);
		return theProperties.getProperty(name);
    }
    public static void main(String[] args) throws Exception{  
        try {  
            if (restore("127.0.0.1", "root", "root", "E:\\backupDatabase\\", "20190617162342842.sql", "stro_cms")) {  
                System.out.println("数据库恢复成功！！！");  
            } else {  
                System.out.println("数据库恢复失败！！！");  
            }  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
    }
}
