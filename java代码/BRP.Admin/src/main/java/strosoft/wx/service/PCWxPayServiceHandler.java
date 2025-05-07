package strosoft.wx.service;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.WxConfig;
import strosoft.wx.pay.WxPayConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class PCWxPayServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Tracer.writeLine("PC支付开始了:");
        JSONObject jData = this.getRequestData(request);
        String outTradeNo = JsonHelper.getString(jData, "outTradeNo");
        Tracer.writeLine("outTradeNo:" + outTradeNo);
        String description = JsonHelper.getString(jData, "description");
        if (StringHelper.isNullOrEmpty(description)) {
            description = "订单" + outTradeNo;
        }
        String notifyUrl = JsonHelper.getString(jData, "notifyUrl");
        // 金额
        int totalAmount = JsonHelper.getDecimal(jData, "amount").multiply(new BigDecimal(100)).intValue();
        // 使用自动更新平台证书的RSA配置
        // 一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
        String wxPrivateKeyFilePath = WxPayConfig.getWxPrivateKeyFilePath();
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(WxPayConfig.getWxMchId())
                        .privateKeyFromPath(WxPayConfig.getWxPrivateKeyFilePath())
                        .merchantSerialNumber(WxPayConfig.getWxMchSerialNo())
                        .apiV3Key(WxPayConfig.getWxApiV3Key())
                        .build();
        // 构建service
        NativePayService service = new NativePayService.Builder().config(config).build();
        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest prepayRequest = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(totalAmount);
        prepayRequest.setAmount(amount);
        prepayRequest.setAppid(WxConfig.getWxaAppId());
        prepayRequest.setMchid(WxPayConfig.getWxMchId());
        prepayRequest.setDescription(description);
        prepayRequest.setNotifyUrl(notifyUrl);
        prepayRequest.setOutTradeNo(outTradeNo);
        // 调用下单方法，得到应答
        PrepayResponse prepayResponse = service.prepay(prepayRequest);
        Tracer.writeLine("CodeUrl:" + prepayResponse.getCodeUrl());
        // 使用微信扫描 code_url 对应的二维码，即可体验Native支付
        System.out.println(prepayResponse.getCodeUrl());
        this.writeSuccessResponse(response, prepayResponse);
    }
}
