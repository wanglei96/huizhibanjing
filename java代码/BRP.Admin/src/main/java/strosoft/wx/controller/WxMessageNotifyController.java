package strosoft.wx.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.*;
import strosoft.main.boot.JAppApplication;
import strosoft.wx.common.*;
import strosoft.wx.pay.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WxMessageNotifyController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    /**
     * @throws IOException
     */
    @RequestMapping("api/WxMessageNotify")
    @ResponseBody
    public void service() throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // 允许任何域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "*");

        if (!"GET".equals(request.getMethod())) {
            // 如果不是 GET 请求，则进行消息处理
            try {
                Map<String, String> toPostMessage = this.getXMLFromRequset(request);
                JSONObject jMessage = JSONObject.fromObject(toPostMessage);
                Tracer.writeLine("jMessage: " + jMessage);
                if (jMessage.size() > 0) {
                    String openid = jMessage.getString("FromUserName");
                    Tracer.writeLine("openid: " + openid);
                    // 通过openid获取用户微信
                    if (openid != null && !"".equals(openid)) {
                        WxClient theWXClient = new WxClient();
                        WxAccessToken theGetAccessTokenResponse = theWXClient.GetAccessToken(WxConfig.getWxAppId(), WxConfig.getWxAppSecret());
                        String url = String.format(
                                "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN",
                                theGetAccessTokenResponse.getAccessToken(), openid);
                        String result = HttpHelper.get(url);
                        Tracer.writeLine("result: " + result);
                        JSONObject jResult = JSONObject.fromObject(result);
                        String unionid = JsonHelper.getString(jResult, "unionid");
                        Tracer.writeLine("unionid: " + unionid);
                        if (!StringHelper.isNullOrEmpty(unionid)) {
                            String sql1 = String.format("update member set wx_open_id = null where union_id = '%s'", unionid);
                            MyBatisManager.getInstance().executeUpdate(sql1);
                            String sql2 = String.format("update member set wx_open_id='%s' where union_id='%s'",
                                    openid, unionid);
                            MyBatisManager.getInstance().executeUpdate(sql2);
                        }
                    }
                }

                // 返回响应，这里可以根据处理结果返回相应的响应给用户
                PrintWriter out = response.getWriter();
                out.print("Message received successfully.");
                out.flush();
                out.close();
            } catch (Exception ex) {
                JSONArray jarrStackTrace = JSONArray.fromObject(ex.getStackTrace());
                Logger.writeLine(ex.getMessage());
                Logger.writeLine(jarrStackTrace.toString());
                response.getWriter().write(jarrStackTrace.toString());
            }
            return;
        }


        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
            String path = ResourceUtils.getURL("classpath:").getPath();
            logger.info("classpath:" + path);

            //接收微信服务器发送请求时传递过来的参数
            //signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
            //timestamp 时间戳
            //nonce 随机数
            //echostr 随机字符串
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");

            //校验signature，timestamp，nonce，echostr这四个参数是否字符串为空或为null，是返回true，反之
            if (signature == null || timestamp == null || nonce == null || echostr == null) {
                throw new IllegalArgumentException("请求参数非法，请核实!");
            } else {
                String signatureCheck = getSHA1("brp", timestamp, nonce);
                if (signatureCheck.equals(signature)) {
                    PrintWriter out = response.getWriter();
                    out.print(echostr);
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception ex) {
            JSONArray jarrStackTrace = JSONArray.fromObject(ex.getStackTrace());
            Logger.writeLine(ex.getMessage());
            Logger.writeLine(jarrStackTrace.toString());
            response.getWriter().write(jarrStackTrace.toString());
        }
    }

    //SHA1加密
    public static String getSHA1(String token, String timestamp, String nonce) {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            Arrays.sort(array);

            for (int i = 0; i < 3; ++i) {
                sb.append(array[i]);
            }

            String str = sb.toString();

            //获取加密对象
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //获取字符串字节数组
            md.update(str.getBytes());
            //加密
            byte[] digest = md.digest();
            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";

            //处理加密结果
            for (int i = 0; i < digest.length; ++i) {
                shaHex = Integer.toHexString(digest[i] & 255);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }

                hexstr.append(shaHex);
            }

            return hexstr.toString();
        } catch (Exception var11) {
            var11.printStackTrace();
            return token;
        }
    }

    /**
     * 从请求中获得xml信息。
     *
     * @param request
     * @return
     */
    private Map<String, String> getXMLFromRequset(HttpServletRequest request) {
        try {
            // 将解析结果存储在HashMap中
            Map<String, String> map = new HashMap<String, String>();
            // 从request中取得输入流
            InputStream inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> list = root.elements();
            // 遍历所有子节点
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            inputStream.close();
            inputStream = null;
            return map;
        } catch (Exception e) {
        }
        return null;
    }


}
