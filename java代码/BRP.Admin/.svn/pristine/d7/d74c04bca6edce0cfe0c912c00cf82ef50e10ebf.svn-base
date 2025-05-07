package strosoft.app.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.web.multipart.MultipartFile;
import strosoft.app.util.FileHelper;

/// <summary>
/// 上传帮助类
/// </summary>
public class UploadHelper {

    /*
     * 上传文件
     */
    public static String uploadFile(FileItem theFileItem) throws Exception {
        // 上传文件目录
        String realUploadDirectory = SysConfig.getAbsoluteUploadDirectory();
        // 获取文件名
        String fileExtention = getFileExtention(theFileItem);
        if (fileExtention.startsWith(".")) {
            fileExtention = fileExtention.substring(1);
        }
        String fileName = UUID.randomUUID().toString() + "." + fileExtention;
        File realFilePath = new File(realUploadDirectory, fileName);
        // 判断目标文件所在的目录是否存在
        if (!realFilePath.getParentFile().exists()) {
            realFilePath.getParentFile().mkdirs();
        }
        theFileItem.write(realFilePath);
        // 相对路径
        String relativeFilePath = SysConfig.getUploadDirectory() + "/" + fileName;
        return relativeFilePath;
        // 输出信息,前端页面获取,这里用的json格式
        /*
         * PrintWriter writer = response.getWriter();
         *
         * writer.print("{");
         * writer.print("msg:\"文件大小:"+item.getSize()+",文件名:"+filename+"\"");
         * writer.print("}");
         *
         * writer.close();
         */

    }

    /*
     * 上传文件
     */
    public static String uploadBase64File(String base64Data, String originalFilename) throws Exception {
        // 上传文件目录
        String realUploadDirectory = SysConfig.getAbsoluteUploadDirectory();
        // 获取文件名
        String fileExtention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + fileExtention;
        FileHelper.base64ToFile(base64Data, fileName, realUploadDirectory);
        // 相对路径
        String relativeFilePath = SysConfig.getUploadDirectory() + "/" + fileName;
        return relativeFilePath;
    }

    /*
     * 取得上传文件项
     */
    public static FileItem getFileItem(HttpServletRequest request)
            throws FileUploadException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ServletFileUpload theServletFileUpload = getServletFileUpload(request);
        try {
            List<FileItem> list = theServletFileUpload.parseRequest(request);
            FileItem theFileItem = getUploadFileItem(list);
            return theFileItem;
        } catch (Exception ex) {
            return null;
        }
    }

    /*
     * 取得上传文件对象
     */
    private static ServletFileUpload getServletFileUpload(HttpServletRequest request) {
        // 上传文件目录
        String realUploadDirectory = request.getSession().getServletContext()
                .getRealPath(SysConfig.getUploadDirectory());
        // 上传文件临时目录
        String tempUploadDirectory = realUploadDirectory + "/tmp";

        // 获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 如果没以下两行设置的话，上传大的文件会占用很多内存，
        // 设置暂时存放的存储室 ,这个存储室,可以和最终存储文件的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tmp 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
        File temp = new File(tempUploadDirectory);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        factory.setRepository(temp);
        // 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        ServletFileUpload theServletFileUpload = new ServletFileUpload(factory);
        return theServletFileUpload;
    }

    private static FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if (!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }

    /*
     * 取得文件扩展名
     */
    private static String getFileExtention(FileItem item) {
        // 获取路径名
        String fileName = item.getName();
        return FileHelper.getFileExtention(fileName);
    }

    /*
     * 取得上传文件名
     */
    public static String getUploadFileName(HttpServletRequest request) throws Exception {
        ServletFileUpload theServletFileUpload = getServletFileUpload(request);
        List<FileItem> lstFileItem = theServletFileUpload.parseRequest(request);
        if (lstFileItem.size() == 0) {
            throw new Exception("没有上传文件！");
        }
        FileItem theFileItem = lstFileItem.get(0);
        return theFileItem.getName();
    }

    /**
     * @param file     文件
     * @param filePath 新的随机文件名
     */
    public static void upload(MultipartFile file, String filePath) {
        File dest = new File(filePath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存MultipartFile
     * @param multipartFile
     * @return
     */
    public static String saveMultipartFile(MultipartFile multipartFile) {
        String path = SysConfig.getUploadDirectory() + "/" + FileHelper.generateRandomFileName((multipartFile.getOriginalFilename()));
        String absolutePath = SysConfig.getRootPath() + "/" + path;
        upload(multipartFile, absolutePath);
        return path;
    }
}
