package strosoft.app.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import static java.awt.image.BufferedImage.TYPE_CUSTOM;

public class ImageHelper {
	// 宽度
	private static final int WIDTH = 450;
	// 高度
	private static final int HEIGHT = 400;
	// 双字节字符字体大小
	private static final int DOUBLE_FONT_SIZE = 15;
	// 单字节字符字体大小
	private static final int SINGLE_FONT_SIZE = 8;
	// 行间距
	private static final int LINE_HEIGHT = 30;
	// 水印透明度
	private static float alpha = 1.0f;
	// 水印横向位置
	private static int positionWidth = 53;
	// 水印纵向位置
	private static int positionHeight = 317;
	// 水印文字字体
	private static Font font = new Font("宋体", Font.BOLD, 115);
	// 水印文字颜色
	private static Color color = new Color(230, 230, 230);

	/**
	 * 创建图片对象
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage createImage(int width, int height) {
		return createImage(width, height, null);
	}

	/**
	 * 创建图片对象
	 *
	 * @param width
	 * @param height
	 * @param backgroundColor
	 * @return
	 */
	public static BufferedImage createImage(int width, int height, Color backgroundColor) {
		BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		if (backgroundColor != null) {
			// 2.获取图片画笔
			Graphics graphic = newBufferedImage.getGraphics();
			// 3.设置画笔颜色
			graphic.setColor(backgroundColor);
			// 4.绘制矩形背景
			graphic.fillRect(0, 0, width, height);
		}
		return newBufferedImage;
	}

	/**
	 * 保存图片
	 *
	 * @param filePath
	 * @param image
	 * @throws IOException
	 */
	public static void saveImage(String filePath, BufferedImage image) throws IOException {
		OutputStream os = new FileOutputStream(filePath);
		try {
			ImageIO.write(image, "JPG", os);
		} finally {
			os.close();
		}
	}

	/**
	 * 输出字符串
	 *
	 * @param image
	 * @param text
	 * @param x
	 * @param y
	 * @param color
	 * @param font
	 * @return
	 */
	public static BufferedImage drawString(BufferedImage image, String text, int x, int y, Color color, Font font) {
		Graphics graphic = image.getGraphics();
		graphic.setColor(color);
		graphic.setFont(font);
		graphic.drawString(text, x, y);
		return image;
	}

	/**
	 * 生成图片
	 *
	 * @throws IOException
	 */
	public static void createImage(String[] strList, int[] sizes, String basePath, String targetPath)
			throws IOException {
		OutputStream os = null;
		// 1.创建空白图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.获取图片画笔
		Graphics graphic = image.getGraphics();
		// 3.设置画笔颜色
		graphic.setColor(Color.white);
		// 4.绘制矩形背景
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 5.绘制矩形边框
		graphic.setColor(Color.lightGray);
		graphic.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

		/**
		 * strList：字符串数组，里面存储的是需要显示的文字，每个元素换行显示
		 * sizes：长度数组，对应上面的字符串数组，对于每个字符串，记录标题长度，需要加粗显示
		 */
		int height = 0;// 记录文字行数
		int sumLength = 0;
		String regex = "[^\\x00-\\xff]";// 匹配双字节字符
		Matcher matcher = null;
		Pattern pattern = Pattern.compile(regex);
		boolean rs = false;// 记录当前字符的匹配情况
		boolean flag = false;// 记录前一字符的匹配情况
		for (int j = 0; j < strList.length; j++) {
			height = height + 1;
			sumLength = 0;// 记录当前字符的横坐标位置
			String stroutput = strList[j];// 每个字符串元素
			int size = stroutput.length();// 每个字符串数组元素的长度

			for (int num = 0; num < size; num++) {
				String schar = stroutput.charAt(num) + "";
				matcher = pattern.matcher(schar);
				// 字符串是否与正则表达式相匹配
				rs = matcher.matches();
				if (num < sizes[j]) { // 标题显示格式
					// 设置随机颜色
					graphic.setColor(Color.black);
					// 设置字体大小，标题需要加粗显示
					graphic.setFont(new Font(null, Font.BOLD, DOUBLE_FONT_SIZE));
				} else { // 正文显示格式
					// 设置随机颜色
					graphic.setColor(Color.darkGray);
					// 设置字体大小，正文普通字体显示
					graphic.setFont(new Font(null, Font.PLAIN, DOUBLE_FONT_SIZE));
				}
				/**
				 * 单字节字符，正常情况下应该是占用8个位置的，但是如果前面1个字符是双字节字符的话，
				 * 如果加8的话，会和前面的字符重叠，因为字符本身是要占用空间的，sumLength只是横坐标的位置，
				 * 而并没有考虑占位的问题，所以，如果前面是双字节字符的话，横坐标应该加15； 前面如果是单字节字符，加8就可以了。
				 */
				if (rs) {
					// 双字节字符
					sumLength = sumLength + DOUBLE_FONT_SIZE;
				} else {
					// 单字节字符
					if (flag) {
						// 前一个字符是双字节字符
						sumLength = sumLength + DOUBLE_FONT_SIZE;
					} else {
						sumLength = sumLength + SINGLE_FONT_SIZE;
					}
				}
				flag = rs;
				if (sumLength > WIDTH - DOUBLE_FONT_SIZE * 2) {
					// 每一行的前后都要留有一定空白，横坐标已经超出长度，需要折行
					height = height + 1;
					if (rs) { // 前面是双字节字符，横坐标设置为15
						sumLength = DOUBLE_FONT_SIZE;
					} else { // 前面是单字节字符，横坐标设置为8
						sumLength = SINGLE_FONT_SIZE;
					}
				}
				// 画字符
				graphic.drawString(schar, sumLength, height * LINE_HEIGHT);
			}
		}
		// 6.返回图片
		os = new FileOutputStream(targetPath);
		ImageIO.write(image, "JPG", os);
		os.close();

		System.out.println("返回图片。。。。。。");
	}

	// 添加文字水印
	public static void markImageByText(String logoText, String srcImgPath, String targerPath, Integer degree) {

		InputStream is = null;
		OutputStream os = null;
		try {
			// 1、源图片
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(logoText, positionWidth, positionHeight);
			// 9、释放资源
			g.dispose();
			// 10、生成图片
			os = Files.newOutputStream(Paths.get(targerPath));
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加水印文字");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 添加图片水印
	public static void markImageByIcon(String iconPath, String srcImgPath, String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}

			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);

			// 得到Image对象。
			Image img = imgIcon.getImage();

			// float alpha = 0.1f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 表示水印图片的位置
			g.drawImage(img, positionWidth - 1, positionHeight - 1, null);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();

			os = Files.newOutputStream(Paths.get(targerPath));

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加Icon印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 按给定的百分比量缩小图像
	 *
	 * @param orginalImagePath 输入
	 * @param targetImagePath  输出
	 * @param percent          金额百分比
	 * @throws IOException
	 */
	public static void compress(String orginalImagePath, String targetImagePath, int percent) throws IOException {
		BufferedImage image = ImageIO.read(new File(orginalImagePath));

		File output = new File(String.format(targetImagePath));
		OutputStream out = Files.newOutputStream(output.toPath());

		final String fileExt = targetImagePath.substring(targetImagePath.lastIndexOf(".") + 1);
		ImageOutputStream ios = ImageIO.createImageOutputStream(out);
		ImageWriter writer = ImageIO.getImageWritersByFormatName(fileExt).next();
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();

		if (param.canWriteCompressed()) {
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(percent / 100f);
		}

		writer.write(null, new IIOImage(image, null, null), param);

		out.close();
		ios.close();
		writer.dispose();
		System.out.println("done!");
	}

	/**
	 * 裁剪为正方形
	 *
	 * @param sourceImagePath 原图片地址
	 * @param targetImagePath 新图片地址
	 * @throws IOException
	 */
	public static void cutSquare(String sourceImagePath, String targetImagePath) throws IOException {
		BufferedImage imgSource = ImageIO.read(Files.newInputStream(Paths.get(sourceImagePath)));
		int width = Math.min(imgSource.getWidth(), imgSource.getHeight());
		int height = width;
		int startX = (imgSource.getWidth() - width) / 2;
		int startY = (imgSource.getHeight() - height) / 2;
		// creates output image
		BufferedImage outputImage = imgSource.getSubimage(startX, startY, width, height);

		// extracts extension of output file
		String fileExt = targetImagePath.substring(targetImagePath.lastIndexOf('.') + 1);

		// writes to output file
		OutputStream outStream = Files.newOutputStream(Paths.get(targetImagePath));
		ImageIO.write(outputImage, fileExt, outStream);
	}

	/**
	 * 裁剪成小正方形
	 *
	 * @param sourceImageFilePath
	 * @param targetImageFilePath
	 * @param size
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void cutSquareMiniImage(String sourceImageFilePath, String targetImageFilePath, int size)
			throws IOException, InterruptedException {
		// 转成正方形
		cutSquare(sourceImageFilePath, targetImageFilePath);
		Thread.sleep(1);
		// 生成缩略图
		createMiniImage(targetImageFilePath, targetImageFilePath, size, size);
	}

	/**
	 * 生成缩略图
	 *
	 * @param sourceImagePath 原图片地址
	 * @param targetImagePath 新图片地址
	 * @param targetWidth     缩略图的宽
	 * @param targetHeight    缩略图的高
	 * @throws IOException
	 */
	public static void createMiniImage(String sourceImagePath, String targetImagePath, int targetWidth,
									   int targetHeight) throws IOException {
		BufferedImage imgSource = ImageIO.read(Files.newInputStream(Paths.get(sourceImagePath)));
		double rate = 1d;
		if ((imgSource.getHeight() <= imgSource.getHeight()) && (targetWidth >= targetHeight)) {
			rate = (double) imgSource.getHeight() / (double) targetHeight;
		} else if ((imgSource.getWidth() > imgSource.getHeight()) && (targetWidth < targetHeight)) {
			rate = (double) imgSource.getWidth() / (double) targetWidth;
		} else if ((imgSource.getWidth() <= imgSource.getHeight()) && (targetWidth <= targetHeight)) {
			if (imgSource.getHeight() / targetHeight >= imgSource.getWidth() / targetWidth) {
				rate = (double) imgSource.getWidth() / (double) targetWidth;
			} else {
				rate = (double) imgSource.getHeight() / (double) targetHeight;
			}
		} else {
			if (imgSource.getHeight() / targetHeight >= imgSource.getWidth() / targetWidth) {
				rate = (double) imgSource.getHeight() / (double) targetHeight;
			} else {
				rate = (double) imgSource.getWidth() / (double) targetWidth;
			}
		}

		int scaledWidth = (int) (imgSource.getWidth() / rate);
		int scaledHeight = (int) (imgSource.getHeight() / rate);

		System.out.printf("OUTPUT: width %d, height %d\n", scaledWidth, scaledHeight);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, imgSource.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, scaledWidth, scaledHeight);
		g2d.drawImage(imgSource, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		String fileExt = targetImagePath.substring(targetImagePath.lastIndexOf('.') + 1);

		// writes to output file
		ImageIO.write(outputImage, fileExt, Files.newOutputStream(Paths.get(targetImagePath)));
		System.out.println("done!");
	}

	public static void createSquareMiniImage(String sourceImagePath, String targetImagePath, int size)
			throws IOException, InterruptedException {
		// 生成缩略图
		createMiniImage(sourceImagePath, targetImagePath, size, size);
		Thread.sleep(1);
		// 转成正方形
		toSquare(targetImagePath, targetImagePath);
	}

	/**
	 * 转成正方形图片
	 *
	 * @param sourceImagePath
	 * @param targetImagePath
	 * @throws IOException
	 */
	public static void toSquare(String sourceImagePath, String targetImagePath) throws IOException {
		BufferedImage imgSource = ImageIO.read(Files.newInputStream(Paths.get(sourceImagePath)));
		int width = Math.max(imgSource.getWidth(), imgSource.getHeight());
		int height = width;
		int startX = (width - imgSource.getWidth()) / 2;
		int startY = (height - imgSource.getHeight()) / 2;
		int imageType = imgSource.getType();
		if(imageType == TYPE_CUSTOM){
			imageType = BufferedImage.TYPE_3BYTE_BGR;
		}
		// creates output image
		BufferedImage outputImage = new BufferedImage(width, height, imageType);

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		g2d.drawImage(imgSource, startX, startY, imgSource.getWidth(), imgSource.getHeight(), null);
		g2d.dispose();

		// extracts extension of output file
		String fileExt = targetImagePath.substring(targetImagePath.lastIndexOf('.') + 1);

		// writes to output file
		OutputStream outStream = Files.newOutputStream(Paths.get(targetImagePath));
		ImageIO.write(outputImage, fileExt, outStream);

	}

	/**
	 * 将照片的大小减小到给定的绝对宽度和高度
	 *
	 * @param inputImagePath  输入
	 * @param outputImagePath 输出
	 * @param scaledWidth     缩放宽度
	 * @param scaledHeight    缩放高度
	 * @throws IOException
	 */
	public static void stretch(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
			throws IOException {
		// reads input image
		BufferedImage inputImage = ImageIO.read(Files.newInputStream(Paths.get(inputImagePath)));

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		final String fileExt = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, fileExt, Files.newOutputStream(Paths.get(outputImagePath)));
		System.out.println("done!");
	}

	/**
	 * 使用原始图像的百分比
	 */
	public static void stretch(String inputImagePath, String outputImagePath, int percent) throws IOException {
		BufferedImage inputImage = ImageIO.read(Files.newInputStream(Paths.get(inputImagePath)));
		int scaledWidth = inputImage.getWidth() * percent;
		int scaledHeight = inputImage.getHeight() * percent;
		stretch(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
	}

	/**
	 * 是否图片
	 *
	 * @param fileName
	 * @return
	 */
	public static Boolean isImage(String fileName) {
		String fileExtention = FileHelper.getFileExtention(fileName);
		if (".jpg.png.gif.jpeg".indexOf(fileExtention.toLowerCase()) < 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		ImageHelper.compress("d:\\a.jpg", "d:\\a_50.jpg", 50);
		ImageHelper.compress("d:\\a.jpg", "d:\\a_30.jpg", 30);
		ImageHelper.compress("d:\\a.jpg", "d:\\a_10.jpg", 10);

		ImageHelper.cutSquare("d:\\a.jpg", "d:\\b.jpg");
		ImageHelper.cutSquare("d:\\aaa.jpg", "d:\\bbb.jpg");

		ImageHelper.stretch("d:\\a.jpg", "d:\\a_resize.jpg", 100, 100);
		ImageHelper.createMiniImage("d:\\a.jpg", "d:\\a_mini.jpg", 100, 100);

		ImageHelper.toSquare("d:\\a.jpg", "d:\\a_square.jpg");

//		String[] contents = new String[4];
//		contents[0] = "姓名：张三";
//		contents[1] = "性别：男";
//		contents[2] = "出生年月：1988-01";
//		contents[3] = "个人经历：1995年9月进入XXX小学，2000年9月进入某中学，2003年顺利升入本市高中，2006年考入某一本大学成为一名大学生，2010年考入北京一所大学成为一名研究生，2013年顺利毕业进入职场。";
//		int[] sizes = new int[24];
//		sizes[0] = 3;
//		sizes[1] = 3;
//		sizes[2] = 5;
//		sizes[3] = 5;
//		try {
//			createImage(contents, sizes, "e:/Temp/createImage.jpg", "e:/Temp/createImage.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// markImageByText("测试水印","e:/test/createImage.jpg","e:/test/createImage_text.jpg",90);
//
//		markImageByIcon("e:/Temp/test.jpg", "e:/Temp/createImage.jpg", "e:/Temp/createImage.jpg", 0);
//		String serviceAddress = "http://localhost:8080/StroCMS.Admin/api/Service";
//		ServiceClient newServiceClient = new ServiceClient();
//		newServiceClient.setServiceAddress(serviceAddress);
//		HashMap<String, String> mapParameters = new HashMap<String, String>();
//		JSONObject jData = new JSONObject();
//		jData.put("tableName", "sys_user");
//		String result = newServiceClient.call("GetTable", jData);
//		JSONObject jResult = JSONObject.fromObject(result);
//		System.out.println(jResult.toString());
//
//		BufferedImage imgRFID = ImageHelper.createImage(300, 100, Color.WHITE);
//		Font font = new Font("宋体", Font.BOLD, 20);
//		ImageHelper.drawString(imgRFID, "文档编号：" + "1", 10, 30, Color.BLACK, font);
//		ImageHelper.drawString(imgRFID, "人员编号：" + "3", 10, 60, Color.BLACK, font);
//		ImageHelper.drawString(imgRFID, "姓名：" + "张三", 150, 60, Color.BLACK, font);
//		ImageHelper.saveImage("d:/test.jpg", imgRFID);
//		System.out.println("...");
//
//		while (true) {
//			// print
//			try {
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Thread.sleep(3000);
//		}
	}

	/**
	 * 添加图片时间水印
	 *
	 * @param sourceImagePath
	 * @param targetImagePath
	 * @throws IOException
	 */
	public static void createSquareTimeImage(String sourceImagePath, String targetImagePath) throws IOException {
		BufferedImage imgSource = ImageIO.read(Files.newInputStream(Paths.get(sourceImagePath)));

		System.out.printf("OUTPUT: width %d, height %d\n", imgSource.getWidth(), imgSource.getHeight());

		// creates output image
		BufferedImage outputImage = new BufferedImage(imgSource.getWidth(), imgSource.getHeight(), imgSource.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		// 设置字体和字体颜色
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));

		// 设置字体颜色
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
		g2d.drawImage(imgSource, 0, 0, imgSource.getWidth(), imgSource.getHeight(), null);
		g2d.drawString(DateHelper.getDateTimeString(), 10, 30);
		g2d.dispose();

		// extracts extension of output file
		String fileExt = targetImagePath.substring(targetImagePath.lastIndexOf('.') + 1);

		// writes to output file
		ImageIO.write(outputImage, fileExt, Files.newOutputStream(Paths.get(targetImagePath)));
		System.out.println("done!");

	}

}