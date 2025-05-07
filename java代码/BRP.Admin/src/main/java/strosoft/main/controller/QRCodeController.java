package strosoft.main.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerFactory;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.QRCodeHelper;
import strosoft.app.util.StringHelper;
import strosoft.main.boot.JAppApplication;
import strosoft.sys.model.AuthenticationManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class QRCodeController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;
    /**
     * @throws IOException
     */
    @RequestMapping("api/QRCode")
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

            String strWidth = request.getParameter("width");
            Integer width = 100;
            if (strWidth != null) {
                width = Integer.parseInt(strWidth);
            }
            String strHeight = request.getParameter("height");
            int height = 100;
            if (strHeight != null) {
                height = Integer.parseInt(strHeight);
            }
            String content = request.getParameter("content");
            content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
            if (content == null) {
                content = "内容不可为空";
            }
            // 生成图片数据
            byte[] arrImageData = QRCodeHelper.createQRCodeBytes(width, height,
                    content);
            // 得到文件名称
            String fileName = StringHelper.getRandomText(20) + ".png";
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // 设置下载文件头及文件名称
            response.setHeader("Content-Disposition", "attachment; filename="
                    + fileName);
            ServletOutputStream outputStream = response.getOutputStream();

            outputStream.write(arrImageData);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
