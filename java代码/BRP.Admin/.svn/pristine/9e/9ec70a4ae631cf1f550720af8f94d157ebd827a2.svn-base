package strosoft.main.controller;

import com.alibaba.fastjson2.JSON;
import com.baidu.ueditor.ActionEnter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import strosoft.app.common.UploadHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UeditorController {

    //配置ueditor后端上传接口的验证
    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, String action, MultipartFile[] upfile) throws Exception {
        // 允许任何域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "*");
        if (action.equals("config")) {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            //注意path不能含有中文路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "config";
            System.out.println("path = " + path);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(new ActionEnter(request, path).exec());
            printWriter.flush();
            printWriter.close();
        } else if (action.equals("uploadimage")) {
            FileItem theFileItem = UploadHelper.getFileItem(request);
            if (theFileItem == null) {
                return null;
            }
            String upload = UploadHelper.uploadFile(theFileItem);
            System.out.println("upload = " + upload);
            System.out.println("upload.sub= " + upload.substring(upload.lastIndexOf("/")));
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
            result.put("original", theFileItem.getName());
            result.put("state", "SUCCESS");
            result.put("url", upload);
            String jStr = JSON.toJSONString(result);
            return jStr;
            /*
            Map<String, Object> result = new HashMap<String, Object>();
            for (MultipartFile multipartFile : upfile) {
                String upload = UploadHelper.saveMultipartFile(multipartFile);
                System.out.println("upload = " + upload);
                System.out.println("upload.sub= " + upload.substring(upload.lastIndexOf("/")));
                result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
                result.put("original", multipartFile.getOriginalFilename());
                result.put("state", "SUCCESS");
                result.put("url", upload);
                String jStr = JSON.toJSONString(result);
                return jStr;
            }
            return null;
             */
        } else if (action.equals("uploadvideo")) {
            FileItem theFileItem = UploadHelper.getFileItem(request);
            if (theFileItem == null) {
                return null;
            }
            String upload = UploadHelper.uploadFile(theFileItem);
            System.out.println("upload = " + upload);
            System.out.println("upload.sub= " + upload.substring(upload.lastIndexOf("/")));
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
            result.put("original", theFileItem.getName());
            result.put("state", "SUCCESS");
            result.put("url", upload);
            String jStr = JSON.toJSONString(result);
            return jStr;
            /*
            Map<String, Object> result = new HashMap<String, Object>();
            for (MultipartFile multipartFile : upfile) {
                String upload = UploadHelper.saveMultipartFile(multipartFile);
                System.out.println("upload = " + upload);
                System.out.println("upload.sub = " + upload.substring(upload.lastIndexOf("/")));
                result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
                result.put("original", multipartFile.getOriginalFilename());
                result.put("state", "SUCCESS");
                result.put("url", upload);
                String jStr = JSON.toJSONString(result);
                return jStr;
            }

             */
        } else {
            FileItem theFileItem = UploadHelper.getFileItem(request);
            if (theFileItem == null) {
                return null;
            }
            String upload = UploadHelper.uploadFile(theFileItem);
            System.out.println("upload = " + upload);
            System.out.println("upload.sub= " + upload.substring(upload.lastIndexOf("/")));
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
            result.put("original", theFileItem.getName());
            result.put("state", "SUCCESS");
            result.put("url", upload);
            String jStr = JSON.toJSONString(result);
            return jStr;
            /*
            Map<String, Object> result = new HashMap<String, Object>();
            for (MultipartFile multipartFile : upfile) {
                String upload = UploadHelper.saveMultipartFile(multipartFile);
                System.out.println("upload = " + upload);
                System.out.println("upload.sub= " + upload.substring(upload.lastIndexOf("/")));
                result.put("title", upload.substring(upload.lastIndexOf("/") + 1));
                result.put("original", multipartFile.getOriginalFilename());
                result.put("state", "SUCCESS");
                result.put("url", upload);
                String jStr = JSON.toJSONString(result);
                return jStr;
            }

             */
        }
        return null;
    }
}
