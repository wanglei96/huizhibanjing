package strosoft.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.UUID;

import net.sf.json.JSONArray;

/*
 * 往文件中写数据
 */
public class FileHelper {
	/**
	 * 创建文件
	 *
	 * @param destFileName
	 * @return
	 */
	public static File createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			// 存在替换
			System.out.println("创建文件" + destFileName + "失败，目标文件已存在！");
			file.delete();
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建文件" + destFileName + "失败，目标文件不能为目录！");
			return null;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return null;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建文件" + destFileName + "成功！");
				return file;
			} else {
				System.out.println("创建文件" + destFileName + "失败！");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建文件" + destFileName + "失败！" + e.getMessage());
			return null;
		}
	}

	/**
	 * 写入文件内容，如果文件已经存在则删除并新建。
	 *
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void writeAllText(String filePath, String content) throws IOException {
		writeAllText(filePath, content, "UTF-8");
	}

	/**
	 * 写入文件内容，如果文件已经存在则删除并新建。
	 *
	 * @param filePath
	 * @param content
	 * @param encoding
	 * @throws IOException
	 */
	public static void writeAllText(String filePath, String content, String encoding) throws IOException {
		PrintWriter thePrintWriter = null;
		try {

			File file = createFile(filePath);
			OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(file), encoding);
			// 利用FileWriter方式构建PrintWriter对象，实现追加
			thePrintWriter = new PrintWriter(outstream);
			thePrintWriter.print(content);
			thePrintWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (thePrintWriter != null) {
				thePrintWriter.close();
			}

		}
	}

	/**
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readAllText(String filePath) throws IOException {
		return readAllText(filePath, "utf-8");
	}

	/**
	 * @param filePath
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readAllText(String filePath, String encoding) throws IOException {
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuilder html = new StringBuilder();
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			String line;
			while ((line = reader.readLine()) != null) {
				html.append(line);
			}
			reader.close();
			return html.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * 往文件中写内容
	 *
	 * @param file
	 * @param content
	 * @param encode
	 * @return
	 */
	public static boolean writeContents(File file, String content, String encode) {

		PrintWriter p = null;
		try {

			String encoding = encode;// 设置文件的编码！！
			OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(file), encoding);
			// 利用FileWriter方式构建PrintWriter对象，实现追加
			p = new PrintWriter(outstream);

			p.print(content);
			// p.write(templateContent);
			p.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			// 我们来小心翼翼的关闭流，好吧^_^
			p.close();
		}
	}

	/**
	 * 写入文件内容，如果文件已经存在则删除并新建。
	 *
	 * @param filePath
	 * @param jarr
	 * @throws IOException
	 */
	public static void writeJSONArray(String filePath, JSONArray jarr) throws IOException {
		if (jarr == null) {
			return;
		}
		String content = jarr.toString();
		writeAllText(filePath, content, "UTF-8");
	}

	/**
	 * 写入文件内容，如果文件已经存在则删除并新建。
	 *
	 * @param filePath
	 * @param jarr
	 * @param encoding
	 * @throws IOException
	 */
	public static void writeJSONArray(String filePath, JSONArray jarr, String encoding) throws IOException {
		if (jarr == null) {
			return;
		}
		String content = jarr.toString();
		writeAllText(filePath, content, encoding);
	}

	/*
	 * 取得文件扩展名
	 */
	public static String getFileExtention(String filePath) {
		// 索引到最后一个反斜杠
		int start = filePath.lastIndexOf(".");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String fileExtention = filePath.substring(start);
		return fileExtention;
	}

	// 创建文件夹
	public static File createDir(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName 要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName 要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir 要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保存base64数据到文件
	 *
	 * @param base64
	 * @param fileName
	 * @param savePath
	 */
	public static void base64ToFile(String base64, String fileName, String savePath) {
		File file = null;
		// 创建文件目录
		String filePath = savePath;
		File dir = new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			file = new File(filePath + "/" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取文件内容，作为字符串返回
	 */
	public static String readFileAsString(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		}

		if (file.length() > 1024 * 1024 * 1024) {
			throw new IOException("File is too large");
		}

		StringBuilder sb = new StringBuilder((int) (file.length()));
		// 创建字节输入流
		FileInputStream fis = new FileInputStream(filePath);
		try {
			// 创建一个长度为10240的Buffer
			byte[] bbuf = new byte[10240];
			// 用于保存实际读取的字节数
			int hasRead = 0;
			while ((hasRead = fis.read(bbuf)) > 0) {
				sb.append(new String(bbuf, 0, hasRead));
			}
		} finally {
			fis.close();
		}
		return sb.toString();
	}

	/**
	 * 根据文件路径读取byte[] 数组
	 */
	public static byte[] readFileAsBytes(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = null;

		try {
			in = new BufferedInputStream(new FileInputStream(file));
			short bufSize = 1024;
			byte[] buffer = new byte[bufSize];
			int len1;
			while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
				bos.write(buffer, 0, len1);
			}

			byte[] var7 = bos.toByteArray();
			return var7;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException var14) {
				var14.printStackTrace();
			}

			bos.close();

		}
	}

	/**
	 * 取得不带扩展名的文件名
	 *
	 * @param filePath 文件相对路径
	 * @return
	 */
	public static String getFileNameWithoutExtension(String filePath) {
		// 索引到最后一个反斜杠
		int lasterIndexOfSlash = filePath.lastIndexOf("/");
		int lastIndexOfBackslash = filePath.lastIndexOf("\\");
		int start = (lasterIndexOfSlash > lastIndexOfBackslash ? lasterIndexOfSlash : lastIndexOfBackslash) + 1;
		int end = filePath.lastIndexOf(".");
		if (end >= 0) {
			// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
			String fileExtention = filePath.substring(start, end);
			return fileExtention;
		} else {
			String fileExtention = filePath.substring(start);
			return fileExtention;
		}
	}

	/**
	 * 取得文件名
	 *
	 * @param filePath 文件相对路径
	 * @return
	 */
	public static String getFileName(String filePath) {
		// 索引到最后一个反斜杠
		int lasterIndexOfSlash = filePath.lastIndexOf("/");
		int lastIndexOfBackslash = filePath.lastIndexOf("\\");
		int start = (lasterIndexOfSlash > lastIndexOfBackslash ? lasterIndexOfSlash : lastIndexOfBackslash) + 1;
		if (start >= 0) {
			// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
			String fileExtention = filePath.substring(start);
			return fileExtention;
		} else {
			String fileExtention = filePath.substring(0);
			return fileExtention;
		}
	}

	public static void copyDirectory(String srcDir, String destDir) {
		File srcFile = new File(srcDir);
		File destFile = new File(destDir);
		copyDirectory(srcFile, destFile);
	}

	// 复制目录
	public static void copyDirectory(File in, File out) {
		// 获取目标目录绝对路径
		String path = out.getPath();
		// 更改目标路径
		out = new File(path);
		if (in.isDirectory()) {
			// 判断目标目录是否存在
			if (!out.exists()) {
				// 目标目录不存在，创建此目录
				out.mkdir();
			}
		}
		// 获取源目录下的所有目录、文件，并遍历
		File[] inFiles = in.listFiles();
		for (File file : inFiles) {
			// 判断file是否为目录，
			// 如果是目录则复制此目录，如果不是则复制此文件
			if (file.isDirectory()) {
				File outDirectory = new File(out, file.getName());
				copyDirectory(file, outDirectory);
			} else {
				File outFile = new File(out, file.getName());
				copyFile(file, outFile);
			}
		}
	}

	/**
	 * 复制文件
	 *
	 * @param srcFile
	 * @param destFile
	 */
	public static void copyFile(File srcFile, File destFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 创建输入输出流
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			// 复制文件————读取源文件数据，写入到目标文件
			byte[] data = new byte[1024];
			int len;
			while ((len = fis.read(data)) != -1) {
				fos.write(data, 0, len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 复制文件
	 *
	 * @param srcFilePath
	 * @param destFilePath
	 */
	public static void copyFile(String srcFilePath, String destFilePath) {
		File srcFile = new File(srcFilePath);
		File destFile = new File(destFilePath);
		copyFile(srcFile, destFile);
	}

	/**
	 * 生成随机文件名
	 *
	 * @param fileName
	 * @return
	 */
	public static String generateRandomFileName(String fileName) {
		return UUID.randomUUID() + getFileExtention(fileName);
	}

	/**
	 * 是否图片
	 *
	 * @param fileName
	 * @return
	 */
	public static Boolean isImage(String fileName) {
		String fileExtention = FileHelper.getFileExtention(fileName);
		if (".jpg.png.gif.jpeg.bmp".indexOf(fileExtention.toLowerCase()) < 0) {
			return false;
		}
		return true;
	}

	/**
	 * 是否视频
	 *
	 * @param fileName
	 * @return
	 */
	public static Boolean isVideo(String fileName) {
		String fileExtention = FileHelper.getFileExtention(fileName);
		if (".mp4.avi".indexOf(fileExtention.toLowerCase()) < 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String fileName = FileHelper.getFileNameWithoutExtension("d:/aaa/abc.txt");
		System.out.println(fileName);
		String fileName2 = FileHelper.getFileName("d:/aaa/abc.txt");
		System.out.println(fileName2);
		String fileName3 = FileHelper.getFileName("abc.txt");
		System.out.println(fileName3);
//  // 删除单个文件
//  String file = "c:/test/test.txt";
//  DeleteFileUtil.deleteFile(file);
//  System.out.println();
		// 删除一个目录
		String dir = "D:/home/web/upload/upload/files";
		deleteDirectory(dir);
//  System.out.println();
		// 复制目录
		copyDirectory("d:/temp/1", "d:/temp/2");

	}

}
