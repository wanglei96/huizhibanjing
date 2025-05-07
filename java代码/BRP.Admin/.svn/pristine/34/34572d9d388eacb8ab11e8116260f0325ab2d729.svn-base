package strosoft.app.api;

import strosoft.app.common.SysConfig;
import strosoft.app.util.FileHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Download extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public Download() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /*
     * 处理请求
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String country = request.getLocale().getCountry();
        response.setContentType("application/octet-stream");
        try {
            String filePath = request.getParameter("filePath");
            String fileName = request.getParameter("fileName");
            String realFilePath = SysConfig.getRootPath() + "/" + filePath;
            if (StringHelper.isNullOrEmpty(fileName)) {
                fileName = FileHelper.getFileName(filePath);
            }

            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // 设置下载文件头及文件名称
            if (country.equals("CN")) {
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream outputStream = response.getOutputStream();

            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(realFilePath))) {
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int length;
                while (-1 != (length = in.read(buffer, 0, bufSize))) {
                    outputStream.write(buffer, 0, length);
                }
            }
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
