package strosoft.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.function.Consumer;

/**
 * 从外部命令脚本在控制台中打印输出, Runtime.exec
 * 
 * @author Administrator
 * @version 2022.05.09
 */
public class CommandLinePrinter implements Runnable {
	private final InputStream inputStream;
	private final Consumer<String> consumer;

	public CommandLinePrinter(InputStream inputStream, Consumer<String> consumer) {
		this.inputStream = inputStream;
		this.consumer = consumer;
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("GBK")))) {
			br.lines().forEach(consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}