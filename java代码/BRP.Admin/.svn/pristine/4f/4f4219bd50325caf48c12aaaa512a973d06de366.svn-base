package BRP.controller;

import BRP.data.Book;
import BRP.data.ViewBook;
import BRP.model.BarCodeUtils;
import BRP.model.BookManager;
import BRP.model.CompanySettingManager;
import BRP.model.ViewBookManager;
import com.google.zxing.BarcodeFormat;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import strosoft.app.util.StringHelper;
import strosoft.main.boot.JAppApplication;
import strosoft.sys.model.SysSettingManager;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class BarCodeController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    /**
     * @throws IOException
     */
    @RequestMapping("api/BarCode")
    @ResponseBody
    public void service() throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // 允许任何域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "*");
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
            logger.info("user.dir:" + System.getProperty("user.dir"));
            String path = ResourceUtils.getURL("classpath:").getPath();
            logger.info("classpath:" + path);

            String startBookSn = request.getParameter("startBookSn");
            String endBookSn = request.getParameter("endBookSn");
            Integer companyId = Integer.valueOf(request.getParameter("companyId"));
            startBookSn = new String(startBookSn.getBytes("ISO-8859-1"), "UTF-8");
            if (startBookSn == null) {
                startBookSn = "开始编号不可为空";
            }
            if (!StringHelper.isNullOrEmpty(endBookSn)) {
                endBookSn = new String(endBookSn.getBytes("ISO-8859-1"), "UTF-8");
            }
            String condition = String.format(
                    StringHelper.isNullOrEmpty(endBookSn) ? "sn >= '%s' and company_id=" + companyId : "sn >= '%s' and sn <= '%s' company_id=" + companyId,
                    startBookSn, endBookSn);
            List<ViewBook> lstBook = ViewBookManager.getInstance().getList(condition);
            // 设置响应头
            response.setContentType("application/octet-stream");

            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment; filename=\"barcodes.zip\"");

            //获取图片高度
            Integer imageCodeHeight = SysSettingManager.getInstance().getIntSetting("ImageCodeHeight", 200);
            //获取图片宽度
            Integer imageCodeWidth = SysSettingManager.getInstance().getIntSetting("ImageCodeWidth", 300);
            //获取条码高度
            Integer barCodeHeight = SysSettingManager.getInstance().getIntSetting("BarCodeHeight", 70);
            //获取条码宽度
            Integer barCodeWidth = SysSettingManager.getInstance().getIntSetting("BarCodeWidth", 200);
            //文字大小
            Integer BarCodeSize = SysSettingManager.getInstance().getIntSetting("BarCodeSize", 15);
            //水平间距
            Integer horizontalSpacing = SysSettingManager.getInstance().getIntSetting("HorizontalSpacing", 80);
            //垂直间距
            Integer verticalSpacing = SysSettingManager.getInstance().getIntSetting("VerticalSpacing", 80);
            //条码合成图片数量
            Integer imagesPerComposite = SysSettingManager.getInstance().getIntSetting("ImagesPerComposite", 10);
            OutputStream outputStream = response.getOutputStream();
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            List<BufferedImage> barcodeImages = new ArrayList<>();

            List<BufferedImage> compositeImages = new ArrayList<>(); // 用于存储合成后的图片

            for (int i = 0; i < lstBook.size(); i += imagesPerComposite) {
                List<ViewBook> subList = lstBook.subList(i, Math.min(i + imagesPerComposite, lstBook.size()));
                List<BufferedImage> images = new ArrayList<>();

                for (ViewBook book : subList) {
                    String bookSn = book.getSn();
                    String bookcaseName = book.getBookcaseName();
                    String bookSeriesName = book.getBookSeriesName();

                    // 获取条形码图片
                    BufferedImage snImage = BarCodeUtils.getBarCodeImage(bookSn, barCodeWidth, barCodeHeight, BarcodeFormat.CODE_128);
                    //设置文字
                    BufferedImage image = BarCodeUtils.getCodeWithWords(
                            snImage, bookSn, bookcaseName, bookSeriesName, imageCodeWidth,
                            imageCodeHeight, 0,0,5,-3,-5,-3, BarCodeSize);

                    images.add(image);
                }

                BufferedImage compositeImage = createCompositeImage(images, imageCodeWidth, imageCodeHeight, horizontalSpacing, verticalSpacing);
                compositeImages.add(compositeImage);
            }

            for (int i = 0; i < compositeImages.size(); i++) {
                BufferedImage image = compositeImages.get(i);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                baos.flush();

                // 生成图片名称
                int startIdx = i * imagesPerComposite;
                int endIdx = Math.min((i + 1) * imagesPerComposite - 1, lstBook.size() - 1);

                String sBookSn = lstBook.get(startIdx).getSn();
                int dashIndex = sBookSn.lastIndexOf("-");
                String strBookSn = sBookSn.substring(dashIndex + 1);

                String eBookSn = lstBook.get(endIdx).getSn();
                int endDashIndex = eBookSn.lastIndexOf("-");
                String strEndBookSn = eBookSn.substring(endDashIndex + 1);

                String fileName = strBookSn + "-" + strEndBookSn + ".png";

                // 写入压缩文件
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                zipOut.write(baos.toByteArray());
                baos.close();
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage createCompositeImage(List<BufferedImage> images, Integer imageCodeWidth, Integer imageCodeHeight, Integer horizontalSpacing, Integer verticalSpacing) {
        int numColumns = 2; // 每行显示的列数
        int columnWidth = imageCodeWidth + horizontalSpacing; // 列的宽度
        int rowHeight = imageCodeHeight + verticalSpacing; // 行的高度
        int numRows = (int) Math.ceil((double) images.size() / numColumns); // 计算行数

        int newImageWidth = numColumns * columnWidth - horizontalSpacing; // 新图片的宽度
        int newImageHeight = numRows * rowHeight - verticalSpacing; // 新图片的高度
        BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImage.createGraphics();

       /* g2d.setColor(Color.WHITE); // 设置填充颜色为白色，可根据需要自行调整

        // 填充整个图片区域为白色
        g2d.fillRect(0, 0, newImageWidth, newImageHeight);*/

        int x = 0;
        int y = 0;

        for (int i = 0; i < images.size(); i++) {
            BufferedImage image = images.get(i);
            int xOffset = (imageCodeWidth - image.getWidth()) / 2; // 计算水平居中偏移
            int yOffset = (imageCodeHeight - image.getHeight()) / 2; // 计算垂直居中偏移
            g2d.drawImage(image, x + xOffset, y + yOffset, null);
            x += columnWidth; // 增加水平偏移
            if (x >= newImageWidth) {
                x = 0; // 重置水平偏移
                y += rowHeight; // 增加垂直偏移
            }
        }
        g2d.dispose();
        return newImage;
    }
}
