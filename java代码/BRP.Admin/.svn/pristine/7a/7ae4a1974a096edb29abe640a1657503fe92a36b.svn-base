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
import strosoft.app.util.ExceptionHelper;
import strosoft.app.util.Tracer;
import strosoft.main.boot.JAppApplication;
import strosoft.sys.model.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ServiceController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    /**
     * @throws IOException
     */
    @RequestMapping("api/Service")
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
            String serviceName = request.getParameter("serviceName");
            if (serviceName == null) {
                serviceName = request.getParameter("service_name");
            }
            logger.info("serviceName:" + serviceName);
            logger.info("user.dir:" + System.getProperty("user.dir"));
            String path = ResourceUtils.getURL("classpath:").getPath();
            logger.info("classpath:" + path);
            if (serviceName == null || serviceName.isEmpty()) {
                ServiceHandlerHelper.writeErrorResponse(response, "服务名称【serviceName】不能为空！");
                return;
            }
            String token = request.getParameter("token");
            String accountId = request.getParameter("accountId");
            String applicationId = AuthenticationManager.getInstance().getParameterApplicationId(request);
            // 是否开启登录状态验证
            Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
            // 是否属于非验证服务
            Boolean isUncheckServiceName = AuthenticationManager.getInstance().isUncheckServiceName(serviceName);
            if (userSessionCheckEnabled && !isUncheckServiceName && !applicationId.equals("StroCmsAdmin")) {
                // 进行验证
                String loginStatus = AuthenticationManager.getInstance().checkLoginStatus(applicationId, accountId,
                        token);
                if (!loginStatus.equals("true")) {
                    ServiceHandlerHelper.writeLoginTimeoutResponse(response, loginStatus);
                    return;
                }
            }
            ServiceHandler serviceHandler = ServiceHandlerFactory.GetHandler(serviceName);

            if (serviceHandler == null) {
                ServiceHandlerHelper.writeErrorResponse(response, "未找到服务名称为【" + serviceName + "】的服务处理类！");
                return;
            }
            serviceHandler.process(request, response);
        } catch (Exception ex) {
            ExceptionHelper.trace(ex);
            ExceptionHelper.log(ex);
            ServiceHandlerHelper.writeErrorResponse(response, ex.getMessage());
        }
    }

}
