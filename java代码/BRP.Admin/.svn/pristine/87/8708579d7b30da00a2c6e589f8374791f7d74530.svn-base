package strosoft.wx.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import strosoft.app.util.Logger;
import strosoft.app.util.StreamHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.main.boot.JAppApplication;
import strosoft.wx.pay.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PCWxPayNotifyController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;
    /**
     * @throws IOException
     */
    @RequestMapping("api/PCWxPayNotify")
    @ResponseBody
    public void service() throws IOException {
        Tracer.writeLine("回调开始:");
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
            String path = ResourceUtils.getURL("classpath:").getPath();
            logger.info("classpath:" + path);

            String timeStamp = request.getHeader("Wechatpay-Timestamp");
            String nonce = request.getHeader("Wechatpay-Nonce");
            String body = StreamHelper.readAsString(request.getInputStream());
            String signature = request.getHeader("Wechatpay-Signature");
            Tracer.writeLine("timeStamp:" + timeStamp);
            Tracer.writeLine("nonce:" + nonce);
            Tracer.writeLine("body:" + body);
            Tracer.writeLine("signature:" + signature);

            Boolean isValid = this.checkSignature(timeStamp, nonce, body, signature);
            if (!isValid) {
                Tracer.writeLine("签名验证失败！");
                response.getWriter().write("签名验证失败！");
                return;
            }
            // 解密消息
            if (StringHelper.isNullOrEmpty(body)) {
                response.getWriter().write("请求数据为空！");
                return;
            }

            JSONObject jBody = JSONObject.fromObject(body);

            JSONObject jResouce = jBody.getJSONObject("resource");

            String associatedData = jResouce.getString("associated_data");
            String _nonce = jResouce.getString("nonce");
            String ciphertext = jResouce.getString("ciphertext");
            String message = WxPayHelper.decryptToString(WxPayConfig.getWxApiV3Key(), associatedData, _nonce,
                    ciphertext);
            Tracer.writeLine("notice message:" + message);
            JSONObject jMessage = JSONObject.fromObject(message);
            String tradeState = jMessage.getString("trade_state");
            if (!tradeState.equals("SUCCESS")) {
                response.getWriter().write(tradeState);
                return;
            }

            WxPayNoticeContext context = new WxPayNoticeContext();
            context.setNoticeMessage(jMessage);
            try {
                String handlerTypeName = WxPayConfig.getPCWxNotifyHandlerTypeName();
                Class serviceHandlerClass = Class.forName(handlerTypeName);
                WxPayNotifyHandler serviceHandler = (WxPayNotifyHandler) serviceHandlerClass.newInstance();
                serviceHandler.process(context);
                response.getWriter().write("success");
            } catch (Exception ex) {
                ex.printStackTrace();
                response.getWriter().write(ex.getMessage());
            }

        } catch (Exception ex) {
            JSONArray jarrStackTrace = JSONArray.fromObject(ex.getStackTrace());
            Logger.writeLine(ex.getMessage());
            Logger.writeLine(jarrStackTrace.toString());
            response.getWriter().write(jarrStackTrace.toString());
        }
    }
    /**
     * 验证签名
     *
     * @param timeStamp
     * @param nonce
     * @param body
     * @param signature
     * @return
     * @throws Exception
     */
    private Boolean checkSignature(String timeStamp, String nonce, String body, String signature) throws Exception {
        String text = String.format("%s\n%s\n%s\n", timeStamp, nonce, body);
        WxPayClient theWXPayClient = new WxPayClient();
        Boolean isValid = theWXPayClient.verify(text, signature);
        return isValid;
    }


}
