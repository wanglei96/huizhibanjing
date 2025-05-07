package strosoft.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Logger {
	private static Boolean logEnabled = true;
	private static String logFilePath;

	/**
	 * 输出内容
	 * 
	 * @param content
	 * @throws IOException
	 */
	public static void write(String content) throws IOException {
		if (!logEnabled) {
			return;
		}
		File file = new File(logFilePath);
		PrintWriter p = null;
		try {

			String encoding = "utf-8";// 设置文件的编码！！
			OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(file, true), encoding);
			// 利用FileWriter方式构建PrintWriter对象，实现追加
			p = new PrintWriter(outstream);
			p.print(content);
			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			p.close();
		}
	}

	/**
	 * 输出换行内容
	 * 
	 * @param content
	 * @throws IOException
	 */
	public static void writeLine(String content){
		if (!logEnabled) {
			return;
		}
		File file = new File(logFilePath);
		PrintWriter p = null;
		try {

			String encoding = "utf-8";// 设置文件的编码！！
			OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(file, true), encoding);
			// 利用FileWriter方式构建PrintWriter对象，实现追加
			p = new PrintWriter(outstream);
			p.println(DateHelper.getDateTimeString());
			p.println(content);
			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			p.close();
		}
	}

	public static String getLogFilePath() {
		return logFilePath;
	}

	public static void setLogFilePath(String logFilePath) {
		Logger.logFilePath = logFilePath;
	}

	public static Boolean getLogEnabled() {
		return logEnabled;
	}

	public static void setLogEnabled(Boolean logEnabled) {
		Logger.logEnabled = logEnabled;
	}
}
