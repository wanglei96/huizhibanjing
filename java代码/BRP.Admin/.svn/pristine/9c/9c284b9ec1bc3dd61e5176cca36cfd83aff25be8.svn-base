package strosoft.app.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * HTTP帮助类
 *
 * @author Admin
 */
public class HttpHelper {

    private static final String CHARSET_ENCODING = "UTF-8";
    private static final String LINE_FEED = "\r\n";

    private static final Logger logger = Logger.getLogger(HttpHelper.class.getName());
    private static String multipartBoundary;
    private static final char[] MULTIPART_CHARS = ("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
            .toCharArray();

    public static String get(String url) throws Exception {
        return getText(url, "GET", null, null);
    }

    /**
     * 发送POST请求
     *
     * @param url   HTTP请求URL
     * @param param 参数
     * @return
     * @throws Exception
     */
    public static String post(String url, String param) throws Exception {
        return getText(url, "POST", param, null);
    }

    /**
     * 发送POST请求
     *
     * @param url        HTTP请求URL
     * @param param      参数
     * @param headersMap 需要携带的HTTP请求头信息
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> headersMap, String param) throws Exception {
        return getText(url, "POST", param, headersMap);
    }

    /**
     * 发送POST请求
     *
     * @param url      HTTP请求URL
     * @param paramMap 需要携带的参数Map
     */
    public static String post(String url, Map<String, String> paramMap) throws Exception {
        return post(url, null, paramMap, null);
    }

    /**
     * 发送POST请求
     *
     * @param url        HTTP请求URL
     * @param headersMap 需要携带的HTTP请求头信息
     * @param paramsMap  需要携带的参数Map
     */
    public static String post(String url, Map<String, String> headersMap, Map<String, String> paramsMap)
            throws Exception {
        return post(url, headersMap, paramsMap, null);
    }

    /**
     * 发送POST请求
     *
     * @param url        HTTP请求URL
     * @param headersMap 需要携带的HTTP请求头信息
     * @param paramsMap  需要携带的参数Map
     * @param filesMap   需要上传的文件
     */
    public static String post(String url, Map<String, String> headersMap, Map<String, String> paramsMap,
                              Map<String, File> filesMap) throws Exception {
        if (filesMap != null && filesMap.size() > 0) {
            return doPostFile(url, headersMap, paramsMap, filesMap);
        } else {
            String parametersText = getParametersText(paramsMap);
            return getText(url, "POST", parametersText, headersMap);
        }
    }

    /**
     * 发送PUT请求
     *
     * @param url   HTTP请求URL
     * @param param 需要携带的参数
     * @return
     * @throws Exception
     */
    public static String put(String url, String param) throws Exception {
        return getText(url, "PUT", param, null);
    }

    /**
     * 发送PUT请求
     *
     * @param url        HTTP请求URL
     * @param param      需要携带的参数
     * @param headersMap 需要携带的HTTP请求头信息
     * @return
     * @throws Exception
     */
    public static String put(String url, Map<String, String> headersMap, String param) throws Exception {
        return getText(url, "PUT", param, headersMap);
    }

    /**
     * @param url       HTTP请求URL
     * @param paramsMap 需要携带的参数Map
     * @return
     * @throws Exception
     */
    public static String put(String url, Map<String, String> paramsMap) throws Exception {
        return put(url, null, paramsMap);
    }

    /**
     * 发送PUT请求
     *
     * @param url        HTTP请求URL
     * @param headersMap 需要携带的HTTP请求头信息
     * @param paramsMap  需要携带的参数Map
     * @return
     * @throws Exception
     */
    public static String put(String url, Map<String, String> headersMap, Map<String, String> paramsMap)
            throws Exception {
        String parametersText = getParametersText(paramsMap);
        return getText(url, "PUT", parametersText, headersMap);
    }

    /**
     * 取得请求参数文本
     */
    public static String getParametersText(Map<String, String> paramsMap) throws UnsupportedEncodingException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return null;
        }
        StringBuilder sbParameters = new StringBuilder();
        for (String parameterName : paramsMap.keySet()) {
            String value = paramsMap.get(parameterName);
            if (sbParameters.length() > 0) {
                sbParameters.append("&");
            }
            sbParameters.append(parameterName + "=" + URLEncoder.encode(String.valueOf(value), "UTF-8"));
        }
        String parametersText = sbParameters.toString();
        return parametersText;
    }

    public static String getText(String url, String method, String params, Map<String, String> headers)
            throws Exception {
        HttpURLConnection connection = getConnection(url, method, params, headers);
        String result = getResponseText(connection);
        return result;
    }

    private static String detectCharset(String input) {
        Pattern pattern = Pattern.compile("charset=\"?([\\w\\d-]+)\"?;?", Pattern.CASE_INSENSITIVE);
        if (input != null && !input.equals("")) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    static HttpURLConnection getConnection(String url, String method, String params, Map<String, String> headers)
            throws Exception {
        HttpURLConnection theHttpURLConnection = (HttpURLConnection) (new URL(url)).openConnection();
        theHttpURLConnection.setRequestMethod(method);

        theHttpURLConnection.setRequestProperty("Accept", "*/*");
        theHttpURLConnection.setRequestProperty("Connection", "keep-alive");
        theHttpURLConnection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36");
        // theHttpURLConnection.setRequestProperty("Accept-Encoding",
        // "gzip,deflate");
        if (headers != null && headers.containsKey("Content-Type")) {
            String contentType = headers.get("Content-Type");
            if (!StringHelper.isNullOrEmpty(contentType)) {
                theHttpURLConnection.setRequestProperty("Content-Type", contentType);
            }
        } else {
            theHttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        }
        String ContentEncoding = null;
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Content-Encoding"))
                    ContentEncoding = entry.getValue();
                theHttpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (Objects.equals(method, "POST") || Objects.equals(method, "PUT")) {
            theHttpURLConnection.setDoOutput(true);
            theHttpURLConnection.setDoInput(true);
            if (params != null && !params.equals("")) {
                OutputStream output = new BufferedOutputStream(theHttpURLConnection.getOutputStream());
                if (ContentEncoding != null) {
                    if (ContentEncoding.indexOf("gzip") > 0) {
                        output = new GZIPOutputStream(output);
                    } else if (ContentEncoding.indexOf("deflate") > 0) {
                        output = new DeflaterOutputStream(output);
                    }
                }
                output.write(params.getBytes(StandardCharsets.UTF_8));
                output.flush();
            }
        }
        theHttpURLConnection.connect();
        return theHttpURLConnection;
    }

    /**
     * 打开上传文件连接
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static HttpURLConnection openPostFileConnection(String url) throws IOException {
        URL theUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) theUrl.openConnection();
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        multipartBoundary = generateMultipartBoundary();
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + multipartBoundary);
        connection.setRequestProperty("User-Agent", "Android Client Agent");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setChunkedStreamingMode(0);
        return connection;
    }

    private static String doPostFile(String url, Map<String, String> headersMap, Map<String, String> paramsMap,
                                     Map<String, File> filesMap) throws Exception {
        HttpURLConnection connection = null;
        BufferedOutputStream dataOutStream = null;
        try {
            connection = openPostFileConnection(url);
            dataOutStream = new BufferedOutputStream(connection.getOutputStream(), 128 * 1024);
            // 向HTTP请求添加头信息
            addHeaders(dataOutStream, headersMap);
            // 添加Post请求参数
            addFormFields(dataOutStream, paramsMap);
            // 向HTTP请求添加上传文件部分
            addFileParts(dataOutStream, filesMap);
            dataOutStream.write(LINE_FEED.getBytes());
            dataOutStream.write(("--" + multipartBoundary + "--").getBytes());
            dataOutStream.write(LINE_FEED.getBytes());
            dataOutStream.flush();

            String result = getResponseText(connection);
            return result;
        } finally {
            if (connection != null)
                connection.disconnect();
            try {
                if (dataOutStream != null)
                    dataOutStream.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 生成HTTP协议中的边界字符串
     *
     * @return 边界字符串
     */
    private static String generateMultipartBoundary() {
        Random rand = ThreadLocalRandom.current();
        char[] chars = new char[rand.nextInt(9) + 12]; // 随机长度(12 - 20个字符)
        for (int i = 0; i < chars.length; i++) {
            chars[i] = MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)];
        }
        return "===Boundary" + new String(chars);
    }

    private static void addHeaders(BufferedOutputStream oStream, Map<String, String> headersMap) throws IOException {
        if (headersMap == null || headersMap.isEmpty())
            return;

        for (Entry<String, String> entry : headersMap.entrySet()) {
            oStream.write((entry.getKey() + ":" + entry.getValue()).getBytes());
            oStream.write(LINE_FEED.getBytes());
        }
    }

    /**
     * 向HTTP报文中添加Form表单域参数
     *
     * @param oStream  HTTP输出流
     * @param paramMap 参数Map
     * @throws IOException
     */
    private static void addFormFields(BufferedOutputStream oStream, Map<String, String> paramMap) throws IOException {
        if (paramMap == null || paramMap.isEmpty())
            return;

        for (Entry<String, String> entry : paramMap.entrySet()) {
            oStream.write(("--" + multipartBoundary).getBytes());
            oStream.write(LINE_FEED.getBytes());

            oStream.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"").getBytes());
            oStream.write(LINE_FEED.getBytes());

            oStream.write(LINE_FEED.getBytes());
            oStream.write(URLEncoder.encode(entry.getValue(), CHARSET_ENCODING).getBytes());
            oStream.write(LINE_FEED.getBytes());
        }
    }

    /**
     * 向HTTP请求添加上传文件部分
     *
     * @param theDataOutputStream 由HTTPURLConnection获取的输出流
     * @param fileMap             文件Map, key为文件域名, value为要上传的文件
     */
    private static void addFileParts(BufferedOutputStream theDataOutputStream, Map<String, File> fileMap)
            throws IOException {
        if (fileMap == null || fileMap.isEmpty())
            return;

        for (Entry<String, File> fileEntry : fileMap.entrySet()) {
            String fileName = fileEntry.getValue().getName();

            theDataOutputStream.write(("--" + multipartBoundary).getBytes());
            theDataOutputStream.write(LINE_FEED.getBytes());

            theDataOutputStream.write(("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                    + "\"; filename=\"" + URLEncoder.encode(fileName, CHARSET_ENCODING) + "\"").getBytes());
            theDataOutputStream.write(LINE_FEED.getBytes());

            theDataOutputStream.write(("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).getBytes());
            theDataOutputStream.write(LINE_FEED.getBytes());
            theDataOutputStream.write(LINE_FEED.getBytes());

            //比使用简单的InputStream和OutputStream更有效
            WritableByteChannel outputChannel = null;
            try (ReadableByteChannel inputChannel = Channels.newChannel(new FileInputStream(fileEntry.getValue()))) {
                outputChannel = Channels.newChannel(theDataOutputStream);

                //类似于byte[]，但适用于Channels。
                ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

                while (inputChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();  //重置位置
                    outputChannel.write(byteBuffer); //将数据写入目标
                    byteBuffer.clear(); //清除缓冲区
                }

                theDataOutputStream.flush();
                theDataOutputStream.write(LINE_FEED.getBytes());
                theDataOutputStream.flush();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * 取得响应文本
     *
     * @param connection
     * @return
     * @throws IOException
     */
    private static String getResponseText(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
            String errorBody = readErrorBody(connection);
            throw new IOException(String.format("请求失败：Status %d, %s", status, errorBody));
        }
        String charset = connection.getHeaderField("Content-Type");
        charset = detectCharset(charset);
        InputStream theInputStream = connection.getInputStream();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int count;
        byte[] buffer = new byte[4096];
        while ((count = theInputStream.read(buffer, 0, buffer.length)) > 0) {
            output.write(buffer, 0, count);
        }
        if (charset == null || charset.equals("")) {
            charset = detectCharset(output.toString());
            if (charset == null || charset.equals("")) {
                charset = "utf-8";
            }
        }
        String result = output.toString(charset.toLowerCase());
        output.close();
        theInputStream.close();
        return result;
    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @param saveFilePath
     * @throws Exception
     */
    public static void downloadFile(String fileUrl, String saveFilePath) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            int contentLength = connection.getContentLength();
            logger.info("文件的大小是:" + contentLength);
            if (contentLength > 32) {
                InputStream is = connection.getInputStream();
                bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                bos = new BufferedOutputStream(fos);
                int b = 0;
                byte[] byArr = new byte[1024];
                while ((b = bis.read(byArr)) != -1) {
                    bos.write(byArr, 0, b);
                }
                logger.info("下载的文件的大小是----------------------------------------------:" + contentLength);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @param saveFilePath
     * @throws Exception
     */
    public static void downloadFilePost(String fileUrl, String saveFilePath, String parameters) throws Exception {
        File file = new File(saveFilePath);
        File directory = new File(file.getParent());
        if(!directory.exists()){
            directory.mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            // 输出参数
            BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
            try {
                byte[] arrData = StringHelper.toByteArray(parameters);
                os.write(arrData);
            } finally {
                os.close();
            }
            // 读取文件流并保存
            int contentLength = connection.getContentLength();
            logger.info("文件的大小是:" + contentLength);
            if (contentLength > 32) {
                InputStream is = connection.getInputStream();
                bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                bos = new BufferedOutputStream(fos);
                int b = 0;
                byte[] byArr = new byte[1024];
                while ((b = bis.read(byArr)) != -1) {
                    bos.write(byArr, 0, b);
                }
                logger.info("下载的文件的大小是----------------------------------------------:" + contentLength);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查连接是否还在
     *
     * @param connection
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean check(HttpURLConnection connection) {
        try {
            connection.getInputStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 取得Multipart ContentType
     *
     * @param split
     * @return
     */
    private String getMultipartContentType(String split) {
        String contentType = "multipart/form-data; boundary=" + split;
        return contentType;
    }

    /**
     * 取得multipart参数体
     *
     * @param hmParams
     * @param split
     * @return
     */
    private String getMultipartParams(HashMap<String, String> hmParams, String split) {
        StringBuilder sb = new StringBuilder();
        // 开始
        sb.append("\r\n--" + split);
        for (Entry<String, String> entry : hmParams.entrySet()) {
            sb.append(String.format("\r\nContent-Disposition: form-data; name=\"%s\"\r\n", entry.getKey()));
            sb.append("\r\n");
            sb.append(entry.getValue() + "\r\n");
            sb.append("--" + split);
        }
        // 结束
        sb.append("--");
        String formDateParams = sb.toString();
        return formDateParams;
    }

    /**
     * 从响应正文读取错误消息
     *
     * @param connection HttpConnection
     * @return 出错信息
     * @throws IOException
     */
    private static String readErrorBody(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) throws Exception {
        String url = "https://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD&rsv_spt=1&rsv_iqid=0x837e7fd500000b1c&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=62095104_26_oem_dg&rsv_enter=1&rsv_dl=tb&rsv_sug3=5&rsv_sug1=3&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&inputT=2819&rsv_sug4=3020";
        String html = HttpHelper.get(url);
        logger.info(html);
    }
}