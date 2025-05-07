package BRP.service;

import BRP.data.Book;
import BRP.model.BarCodeUtils;
import BRP.model.BookManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetBookSnBarCodeServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String startBookSn = JsonHelper.getString(jData, "startBookSn");
        String endBookSn = JsonHelper.getString(jData, "endBookSn");
        String condition = String.format(
                StringHelper.isNullOrEmpty(endBookSn) ? "sn >= '%s'" : "sn >= '%s' and sn <= '%s'",
                startBookSn, endBookSn);
        List<Book> lstBook = BookManager.getInstance().getList(condition);
        try {
            // 设置响应头
            response.setContentType("image/jpeg");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            for (Book book : lstBook) {
                String bookSn = book.getSn();
                /*String filePath = "D:/CC/";
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs(); // 创建目录
                }*/
                // 构建文件名
                String fileName = bookSn + ".png";
                // 获取输出流
                OutputStream outputStream = response.getOutputStream();
                // 生成条码图像
                BufferedImage image = BarCodeUtils.getBarCodeWithWords(bookSn, bookSn);
                // 写入输出流
                ImageIO.write(image, "png", outputStream);
                // 关闭输出流
                outputStream.close();
                //ImageIO.write(image, "jpg", new File(String.format(filePath + "%s.jpg", bookSn)));
            }
            //this.writeSuccessResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
