package strosoft.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 跟踪类
 * 
 * @author Admin
 *
 */
public class Tracer {

	private static Boolean traceEnabled = true;
	private static String traceFilePath;

	public static Boolean getTraceEnabled() {
		return traceEnabled;
	}

	public static void setTraceEnabled(Boolean traceEnabled) {
		Tracer.traceEnabled = traceEnabled;
	}

	public static String getTraceFilePath() {
		return traceFilePath;
	}

	public static void setTraceFilePath(String traceFilePath) {
		Tracer.traceFilePath = traceFilePath;
	}

	/**
	 * 输出内容
	 * 
	 * @param content
	 * @throws IOException
	 */
	public static void write(String content) throws IOException {
		if (!traceEnabled) {
			return;
		}
		File file = new File(traceFilePath);
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
	public static void writeLine(String content) {
		if (!traceEnabled) {
			return;
		}
		PrintWriter p = null;
		try {
			File file = new File(traceFilePath);
			String encoding = "utf-8";// 设置文件的编码！！
			OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(file, true), encoding);
			// 利用FileWriter方式构建PrintWriter对象，实现追加
			p = new PrintWriter(outstream);
			p.println(DateHelper.getDateTimeString());
			p.println(content);
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(p != null) {
				p.close();
			}
		}
	}
}
