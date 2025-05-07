package strosoft.app.util;


import javax.servlet.http.HttpServletRequest;

/**
 * 客户端IP帮助类
 */
public class ClientIpHelper {
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request == null) {
            return "";
        }
        remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        return remoteAddr;
    }
}