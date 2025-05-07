package strosoft.wx.pay;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.WechatPayUploadHttpPost;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wechat.pay.contrib.apache.httpclient.util.RsaCryptoUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WxPayClient {
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    public String getMchSerialNo() {
        return mchSerialNo;
    }

    public void setMchSerialNo(String mchSerialNo) {
        this.mchSerialNo = mchSerialNo;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    private String mchId = WxPayConfig.getWxMchId();// 商户号
    private String apiV3Key = WxPayConfig.getWxApiV3Key();// API V3密钥
    private String mchSerialNo = WxPayConfig.getWxMchSerialNo();// 商户证书序列号
    private String privateKey = WxPayConfig.getWxPrivateKey();// 商户私钥
    private String certificate = null;// 微信支付平台证书

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public WxPayClient() {

    }

    public WxPayClient(String mchId, String apiV3Key) {
        this.setMchId(mchId);
        this.setApiV3Key(apiV3Key);
    }

    public WxPayClient(String mchId, String apiV3Key, String mchSerialNo, String privateKey) {
        this.setMchId(mchId);
        this.setApiV3Key(apiV3Key);
        this.setMchSerialNo(mchSerialNo);
        this.setPrivateKey(privateKey);
    }

    /**
     * 取得证书列表
     *
     * @return
     * @throws Exception
     */
    public String getCertificates() throws Exception {
        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader(ACCEPT, APPLICATION_JSON.toString());
        CloseableHttpClient httpClient = this.createHttpClient();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            String result = WxPayHelper.getResposeText(response);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            response.close();
            httpClient.close();
        }
    }

    /**
     * 取得证书列表
     *
     * @return
     * @throws Exception
     */
    public JSONArray getCertificateArray() throws Exception {
        String result = this.getCertificates();
        JSONObject jResult = JSONObject.fromObject(result);
        JSONArray jarrCertificate = jResult.getJSONArray("data");
        return jarrCertificate;
    }

    /**
     * 取得微信支付平台证书公钥列表
     *
     * @return
     * @throws Exception
     */
    public List<String> getCertificateKeyList() throws Exception {
        JSONArray jarrCertificate = getCertificateArray();
        List<String> lstCertifcate = new ArrayList<String>();
        for (int i = 0; i < jarrCertificate.size(); i++) {
            JSONObject jCertificate = jarrCertificate.getJSONObject(i);
            JSONObject jEncryptCertificate = jCertificate.getJSONObject("encrypt_certificate");
            String associatedData = jEncryptCertificate.getString("associated_data");
            String nonce = jEncryptCertificate.getString("nonce");
            String ciphertext = jEncryptCertificate.getString("ciphertext");
            String certificate = WxPayHelper.decryptToString(this.apiV3Key, associatedData, nonce, ciphertext);
            lstCertifcate.add(certificate);
        }
        return lstCertifcate;
    }

    /**
     * 取得第一个微信支付平台证书公钥
     *
     * @return
     * @throws Exception
     */
    public String getCertificateKey() throws Exception {
        List<String> lstCertificateKey = this.getCertificateKeyList();
        if (lstCertificateKey.size() > 0) {
            return lstCertificateKey.get(0);
        }
        return null;
    }

    /**
     * 取得微信支付平台证书编号列表
     *
     * @return
     * @throws Exception
     */
    public List<String> getCertificateSerialNoList() throws Exception {
        JSONArray jarrCertificate = getCertificateArray();
        List<String> lstSerialNo = new ArrayList<String>();
        for (int i = 0; i < jarrCertificate.size(); i++) {
            JSONObject jCertificate = jarrCertificate.getJSONObject(i);
            String serialNo = jCertificate.getString("serial_no");
            lstSerialNo.add(serialNo);
        }
        return lstSerialNo;
    }

    /**
     * 取得第一个微信支付平台证书编号
     *
     * @return
     * @throws Exception
     */
    public String getCertificateSerialNo() throws Exception {
        List<String> lstCertificateSerialNo = this.getCertificateSerialNoList();
        if (lstCertificateSerialNo.size() > 0) {
            return lstCertificateSerialNo.get(0);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public String uploadFile(String filePath) throws Exception {
        URI uri = new URI("https://api.mch.weixin.qq.com/v3/merchant/media/upload");

        File file = new File(filePath);
        try (FileInputStream fileIs = new FileInputStream(file)) {
            String sha256 = DigestUtils.sha256Hex(fileIs);
            String meta = String.format("{\"filename\":\"%s\",\"sha256\":\"%s\"}", file.getName(), sha256);
            try (InputStream is = new FileInputStream(file)) {
                WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(uri)
                        .withFile(file.getName(), meta, is).build();
                CloseableHttpClient httpClient = this.createHttpClient();
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    String result = WxPayHelper.getResposeText(response);
                    System.out.println(result);
                    return result;
                }
            }
        }
    }

    /**
     * Post请求
     *
     * @param url
     * @param requestBody
     * @return
     * @throws Exception
     */
    public String postRequest(String url, String requestBody) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        String result = doSend(httpPost, new StringEntity(requestBody, APPLICATION_JSON));
        return result;
    }

    /**
     * 发送请求
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    protected String doSend(HttpUriRequest request, HttpEntity entity) throws Exception {
        if (entity != null && request instanceof HttpPost) {
            ((HttpPost) request).setEntity(entity);
        }
        request.addHeader(ACCEPT, APPLICATION_JSON.toString());
        CloseableHttpClient httpClient = this.createWechatPayHttpClient();
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String result = WxPayHelper.getResposeText(response);
            return result;
        }
    }

    /**
     * 创建 HttpClient
     *
     * @return
     * @throws HttpCodeException
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws NotFoundException
     */
    private CloseableHttpClient createHttpClient()
            throws HttpCodeException, IOException, GeneralSecurityException, NotFoundException {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(mchId,
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        Verifier verifier = certificatesManager.getVerifier(mchId);
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey).withValidator(new WechatPay2Validator(verifier))
                .build();
        return httpClient;
    }

    private CloseableHttpClient createWechatPayHttpClient() throws Exception {
        if (certificate == null || certificate == "") {
            certificate = this.getCertificateKey();
        }
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        X509Certificate wechatPayCertificate = PemUtil
                .loadCertificate(new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8)));

        ArrayList<X509Certificate> listCertificates = new ArrayList<>();
        listCertificates.add(wechatPayCertificate);

        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey).withWechatPay(listCertificates).build();
        return httpClient;
    }

    /**
     * 取得预支付交易会话标识
     *
     * @param jParameter
     * @return
     * @throws Exception
     */
    public String getPrepayId(JSONObject jParameter) throws Exception {
        String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
        String requestBody = jParameter.toString();
        String result = postRequest(url, requestBody);
        JSONObject jResult = JSONObject.fromObject(result);
        if (jResult.containsKey("code")) {
            String responseCode = jResult.getString("code");
            if (responseCode.equals("PARAM_ERROR")) {
                throw new Exception(jResult.toString());
            }
        }
        String prepayId = jResult.getString("prepay_id");
        return prepayId;
    }

    /**
     * 退款
     *
     * @param jParameter
     * @return
     * @throws Exception
     */
    public String refund(JSONObject jParameter) throws Exception {
        String url = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
        String requestBody = jParameter.toString();
        String result = postRequest(url, requestBody);
        return result;
    }

    /**
     * 加密字符串
     *
     * @param text
     * @return
     * @throws HttpCodeException
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws NotFoundException
     */
    public String encryptOAEP(String text)
            throws HttpCodeException, IOException, GeneralSecurityException, NotFoundException {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(mchId,
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        Verifier verifier = certificatesManager.getVerifier(mchId);
        String ciphertext = RsaCryptoUtil.encryptOAEP(text, verifier.getValidCertificate());
        return ciphertext;
    }

    /**
     * 取得支付信息
     *
     * @param appId
     * @param prepayId
     * @return
     * @throws HttpCodeException
     * @throws NotFoundException
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public JSONObject getPaymentInfo(String appId, String prepayId)
            throws HttpCodeException, NotFoundException, IOException, GeneralSecurityException {
        // 加密
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = WxPayHelper.getUUID32();
        String _package = "prepay_id=" + prepayId;
        String paymentInfo = String.format("%s\n%s\n%s\n%s\n", appId, timeStamp, nonceStr, _package);
        String paySign = this.sign(paymentInfo);
        JSONObject jPaymentInfo = new JSONObject();
        jPaymentInfo.put("appId", appId);
        jPaymentInfo.put("timeStamp", timeStamp);
        jPaymentInfo.put("nonceStr", nonceStr);
        jPaymentInfo.put("package", _package);
        jPaymentInfo.put("signType", "RSA");
        jPaymentInfo.put("paySign", paySign);
        // String strPaymentInfo = jPaymentInfo.toString();
        return jPaymentInfo;
    }

    /**
     * 签名
     *
     * @param text
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String sign(String text) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        sign.initSign(merchantPrivateKey);
        sign.update(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 验证签名
     *
     * @param text
     * @param signature
     * @return
     * @throws Exception
     */
    public Boolean verify(String text, String signature) throws Exception {
        String certificate = this.getCertificateKey();
        X509Certificate wechatPayCertificate = PemUtil
                .loadCertificate(new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8)));
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(wechatPayCertificate);
        sign.update(text.getBytes(StandardCharsets.UTF_8));
        return sign.verify(Base64.getDecoder().decode(signature));
    }

    public static void main(String[] args) throws Exception {
        /*
         * String mchId = "1228986602"; // 商户号 String mchSerialNo =
         * "21B67E5D3EA2E2B1332464BAB833D182FCAD21F0"; // 商户证书序列号 String apiV3Key =
         * "22dcfef7870b72d3b9fc73fde5c8bb11"; // API V3密钥 String privateKey =
         * "-----BEGIN PRIVATE KEY-----\n" +
         * "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwMajDs+Q0nEoz\n" +
         * "iSScukEisdn/0BF+y9yVE3gk2IIXfQnDTspJsXHb/Lc/ecjQ3PX2nSD817TQCxA1\n" +
         * "9mqPi/rpFObKgipLKPSnMQtSobL/0D8Bm5a2ndlICslzwAX9wcPfqM2RfzIq3zkR\n" +
         * "MoEQALjoySxsjC4mkyY9HqEvrcgU5WxIr8NH/jSpXq8F2CbFkojpSoJrkBTig5al\n" +
         * "YpM53zoT2JrHWIYI5OdOh4eHwENKnarIZ8XQn9tNF9cf4nEDMB8/p5+fJZxNb0Pt\n" +
         * "uQFIjZL39uAcWwsZtm2LJcdVr6FKXnfanW3jYw+m8Y2jgLQ14kOV9qbbmrOwWiP6\n" +
         * "LUAbDw6nAgMBAAECggEAJm3OV1DnFyp3bKIe4CgniL+twoPJLRSduc4TSEYIqcLV\n" +
         * "3ElVCZmwMcD+3tNbH2LyKqW+yibqAu5GzN5AA6sOQMQUSlr1iosWQVkLFSy7c9wh\n" +
         * "weIDjzUCbRodMifRprUaWa/hljhd4sfpFH0ldS4TJoKqM8qnTjaOAoqpsVJ/7z51\n" +
         * "1DAEhToQkC9+6+Jr2bTPfynZei9p2S8S7IqaeJlDfH4mWUs5bH4TK/YS60Om0O8o\n" +
         * "IDOhTVwmb5k0dxN4vqCeuU8LcAHcw6tul25HycJLC9KQv4ft5yVZgFF3NdtBCVJm\n" +
         * "NeeCon3AtXjV+dBN0bqQOuTc12ZvD6Bf4Phn+44dwQKBgQDnFSlhWgHzclPxRniB\n" +
         * "bfhWkQYCfkRc1sDZgX+mt+Z4VRUNfNkVZLSmu9T/uUBrOATj3+wZwXQxu+yb0UyW\n" +
         * "UG1+F+hnjOq7k8RzL/cY+jFQjepXJWdn0KGJolezQwvVAwIWbxV/RjOk3UhhdUNJ\n" +
         * "N2Fr7d6fKjVBEOF06Z09TLR48QKBgQDDMVe7tMWG19VZyYWwzVQKg6gHpTYnuDa4\n" +
         * "NDp71a+cyLpnJ0uPmCBawZknm6lklLp/7QlYlS4aCz9KDzjoF8goRENg7A8qIJKG\n" +
         * "ePgLViri2LOV7fLufJE1C79LI5wd3ViXksIOmb1Y5Fqz2irMa/7tQ+x6hlGYe5c1\n" +
         * "75FkDlNBFwKBgENHhQHEG/dOXSRNWT6NzxUdKjvFmY/SMtng+1KGDoMQeo2vwOCa\n" +
         * "roIxA+XkgN32tG3IP0yMZ5EnXs9LE9pb1lU1DsQ/YgR1FGhZGb9a8Mq8VvNCf6XZ\n" +
         * "N38EFjaqGaLOOmhftUmWHektSCqf4pRJnIK5Ae0jc9fa21mXvuEfGRhxAoGBAKXK\n" +
         * "Vlt/urrCvluKGLzIWhaoJuN/h1ULWabx7a7bHFNOB02wibSIoQqVH7INvD5LOXGI\n" +
         * "8wRDianjXblY3pv7/y18dcJwHp1Il0wSmZR/duPA7iBtwIaiVW+575wxvdt781Ci\n" +
         * "/aMLIFHNe+EBcJK7NAdxRv328n1SlGfieRqENGC3AoGBAJkbMFD7bLG3iAvBuGWI\n" +
         * "1wcjkjQJxAHWdokScfS9DZHMZK7pF2EIpnfFyz3eYYuq53IKkso+9x4gn7lEgITg\n" +
         * "Cp5Rc7JzcuV0KhC4LN7imYRt8d7QNENebU88QpUXA+TkMFOqDweaTDuW3fiq3FS9\n" +
         * "Ugi8+PXtCGETGiczLlynAvm2" + "-----END PRIVATE KEY-----\n"; // 商户API V3私钥
         * String wechatPaySerial = "73742022972DB4956297C2297807166B3228EE34"; //
         * 平台证书序列号 String certificate = "-----BEGIN CERTIFICATE-----\n" +
         * "MIID3DCCAsSgAwIBAgIUc3QgIpcttJVil8IpeAcWazIo7jQwDQYJKoZIhvcNAQEL\n" +
         * "BQAwXjELMAkGA1UEBhMCQ04xEzARBgNVBAoTClRlbnBheS5jb20xHTAbBgNVBAsT\n" +
         * "FFRlbnBheS5jb20gQ0EgQ2VudGVyMRswGQYDVQQDExJUZW5wYXkuY29tIFJvb3Qg\n" +
         * "Q0EwHhcNMjAwNjI3MDk1OTE2WhcNMjUwNjI2MDk1OTE2WjBuMRgwFgYDVQQDDA9U\n" +
         * "ZW5wYXkuY29tIHNpZ24xEzARBgNVBAoMClRlbnBheS5jb20xHTAbBgNVBAsMFFRl\n" +
         * "bnBheS5jb20gQ0EgQ2VudGVyMQswCQYDVQQGDAJDTjERMA8GA1UEBwwIU2hlblpo\n" +
         * "ZW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDCViZ7knsEHN4OLHkg\n" +
         * "QsXJntQ6Zw2qne8w4eSzK9GFz/heKhWmHmtg0EE+VwycuJ3f0gS3a0/1HhwtYbDf\n" +
         * "bgDptismAC1YE1PXKH8v9+e/jeoKZejDMdh18b5svyIEU3LkexY6XAg+T6uT1YVe\n" +
         * "VD7RUXOibUQZqGFR3h8JsPZCh2jAXdb/5HJSClETybFbihREN2+l8zdtw+uYyb6c\n" +
         * "ML5JCNM/V2fxtc6EmAYpedhs9XfOmuOrMzbCjSBZWnvc34Fh3p6PHlaLWcM2cL8z\n" +
         * "19yzwTi6mfk+GXfz4XfK4nJfjwNiCP5SJsLQOcoSdRvcpsf2SNLZNJnPYEbM98YL\n" +
         * "HgdbAgMBAAGjgYEwfzAJBgNVHRMEAjAAMAsGA1UdDwQEAwIE8DBlBgNVHR8EXjBc\n" +
         * "MFqgWKBWhlRodHRwOi8vZXZjYS5pdHJ1cy5jb20uY24vcHVibGljL2l0cnVzY3Js\n" +
         * "P0NBPTFCRDQyMjBFNTBEQkMwNEIwNkFEMzk3NTQ5ODQ2QzAxQzNFOEVCRDIwDQYJ\n" +
         * "KoZIhvcNAQELBQADggEBAALhkqq3nq2BixSRlxvwogcF6gsCPEz7pq0zc5E7LhiQ\n" +
         * "1xbTAEBo7xo+8HXJxa9EeVDT0tTj0Te8xTPqKqHnRBmR6YE33OnmtLFKXGwg7aTz\n" +
         * "53nfL3Hx0wjKlR7HG8FfLqr+/7UjCORCva9axhxq9v+U3XRbizP1jP22Kidc5OKY\n" +
         * "90/yuW785bUm3zz2YfexrYrLy2MB244gDTMIjADm8o/nTt4SIJ3QnN8VZpY2/hfm\n" +
         * "v0VfrsaAziDz4VyiSmOCvHeW5tSE2lCZi6raE4f5/5Vnax/+FsKJj63/MTqEy/6u\n" +
         * "xAxxrQ4fX/3SNOdJU5a3UJwslWVGD4iqnC3JEN6/DOE=" + "-----END CERTIFICATE-----";
         */
        String mchId = "1614905130"; // 商户号
        String mchSerialNo = "1F6C9CB9F3F7A9511517DDFA2E7D359364DBED04"; // 商户证书序列号
        String apiV3Key = "5OGGlDeZv5AibhcqakXol74UjE8MeH7i"; // API V3密钥
        String privateKey = "-----BEGIN PRIVATE KEY-----\r\n"
                + "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkX03647i2mPWP\r\n"
                + "COKUtBs8pZE2Vr2kpQOUeoVZS4w6jt3y1llknkxS0BTPWmGg8qYqZ5ebjMDVsQfl\r\n"
                + "4an/uGdCu22m1Sy5kCqmm/kjtzzzBIwKIdsNK6xP+X2c2sbHM1LaI5P4qP1k611H\r\n"
                + "Z+ToI1pz+8t4y5QcFlv4qgDfKX8N0a++qxrHCZ5/5CTWz5gqEqMPEE2aBSZ0gmFJ\r\n"
                + "S1sIegR5ElY25YXKBOKe8kOXsVCypjp7HtDhITSqL/pjgVh4bqMnpouxLqrg65xG\r\n"
                + "NPYg5Y/hPCneYLtgaL/S3KtwTO0JhkszETWtpMfsjbGndsNrtTWMsvs6mMlvyqaN\r\n"
                + "4pE7MTgrAgMBAAECggEAe3cm8NxaY7XTRWRMJZx/lPdccu+GtYRJgx7gySkJJPcp\r\n"
                + "Wf2N2hlZhR7Ncs8V5Do/fDyh0XJRanH2V8NA2VdfIO3+OEGJvby/fLRN2HkL7BKv\r\n"
                + "SfESQ/tZDB2AL/DZPMmdY0M0I6Rz6XktcLRmacQcbmBk/tAT6WEWJls0GmxmBi8V\r\n"
                + "kdTgggjdTXJJT2GbHXMU1+MFmOkOgz3QkJQkSNsd16Na9ceAS8WPyCj55UkkVMTc\r\n"
                + "E8YvsT2PdIx79hp0IG2H5dGKoBe2FQ0G8dqnh1lV5E8gf+hl+lnCiiemPQ7M1NfS\r\n"
                + "Qi9+UQ/lBU3dZxiuB7F84WBIB0Qk6mnn6sU2rAY50QKBgQDWrEsW272VS7ZbEJeV\r\n"
                + "2jMtWnEhLmGxV0ES4/DxCSGls6/zJNSIhiWCJtFEARnv7fHZEOMTwZDFyr/9GvoH\r\n"
                + "9QBJM3VGHT8ZRFniDGJ51eFp/Dk3J9x+o/pG+lqqYg71DZVwr0hnx8Kl6CemkJYl\r\n"
                + "t7iLVlUmk02x2bIe2lU6E/6rAwKBgQDEBAuvEzlGv7ko/XffXsgKdo5DtqFKur9Z\r\n"
                + "azCGit1V5oKEY4nh8NlUwmhOU3w1OdUKMh/4rmWiqbpiNad3paLHtNrCx4RN8fw3\r\n"
                + "TR97Cir+Zj8bd+IsMeTwDLla6N3ZrZaW+NNqaqtQpz/qX7VjwMr429OmF2sQMD3j\r\n"
                + "VYXleJ7huQKBgEvixfyDmyXuiLzyRZOceaDbqsge/DvTa1aBK0gZgUJxHY6nA1Zr\r\n"
                + "jVDP5gPTG9cb1vVzqcHvF/1DadDQO3ianApEXX12kzqcVeW5mQoWmEN9/L/JoIFH\r\n"
                + "+A4lK+21EjKAJcaav86n59/8DCAuVlVo95yR3THwunNXUEblyWDpdon/AoGBAIws\r\n"
                + "5HogQj6RDwX8NYRc0KuC2vwbl77MS9jTadFdbyll/gJU+J7GUXVmzZbt5w5dPDfd\r\n"
                + "XrXFadNok2x2ODu9DOrKMANJERl4qsiVc6TWwaZKUmj/U5QNXk44mzki/LwVgnKW\r\n"
                + "oQIiJD86S9/878fVcJGFCxdk+eCKz0dKusU+fEipAoGAYVbTlkE1f1TObhLYGpl3\r\n"
                + "mu5ryLK6HnKs3u2iq6Yn+XEmw98eWURiw2QINlW9DsMe1yDPtHKYzXwdLTtsvvP9\r\n"
                + "iwB1LEQcHMv+hTBqcxZc4f3jbALIjiLQM7qd0upJE1XSn8r/GOsuisTqwToF0aSk\r\n"
                + "i6NFcu33PZzaz5VpowXHyxQ=\r\n" + "-----END PRIVATE KEY-----"; // 商户API V3私钥
        String wechatPaySerial = "73742022972DB4956297C2297807166B3228EE34"; // 平台证书序列号
        String certificate = "-----BEGIN CERTIFICATE-----\n"
                + "MIID3DCCAsSgAwIBAgIUc3QgIpcttJVil8IpeAcWazIo7jQwDQYJKoZIhvcNAQEL\n"
                + "BQAwXjELMAkGA1UEBhMCQ04xEzARBgNVBAoTClRlbnBheS5jb20xHTAbBgNVBAsT\n"
                + "FFRlbnBheS5jb20gQ0EgQ2VudGVyMRswGQYDVQQDExJUZW5wYXkuY29tIFJvb3Qg\n"
                + "Q0EwHhcNMjAwNjI3MDk1OTE2WhcNMjUwNjI2MDk1OTE2WjBuMRgwFgYDVQQDDA9U\n"
                + "ZW5wYXkuY29tIHNpZ24xEzARBgNVBAoMClRlbnBheS5jb20xHTAbBgNVBAsMFFRl\n"
                + "bnBheS5jb20gQ0EgQ2VudGVyMQswCQYDVQQGDAJDTjERMA8GA1UEBwwIU2hlblpo\n"
                + "ZW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDCViZ7knsEHN4OLHkg\n"
                + "QsXJntQ6Zw2qne8w4eSzK9GFz/heKhWmHmtg0EE+VwycuJ3f0gS3a0/1HhwtYbDf\n"
                + "bgDptismAC1YE1PXKH8v9+e/jeoKZejDMdh18b5svyIEU3LkexY6XAg+T6uT1YVe\n"
                + "VD7RUXOibUQZqGFR3h8JsPZCh2jAXdb/5HJSClETybFbihREN2+l8zdtw+uYyb6c\n"
                + "ML5JCNM/V2fxtc6EmAYpedhs9XfOmuOrMzbCjSBZWnvc34Fh3p6PHlaLWcM2cL8z\n"
                + "19yzwTi6mfk+GXfz4XfK4nJfjwNiCP5SJsLQOcoSdRvcpsf2SNLZNJnPYEbM98YL\n"
                + "HgdbAgMBAAGjgYEwfzAJBgNVHRMEAjAAMAsGA1UdDwQEAwIE8DBlBgNVHR8EXjBc\n"
                + "MFqgWKBWhlRodHRwOi8vZXZjYS5pdHJ1cy5jb20uY24vcHVibGljL2l0cnVzY3Js\n"
                + "P0NBPTFCRDQyMjBFNTBEQkMwNEIwNkFEMzk3NTQ5ODQ2QzAxQzNFOEVCRDIwDQYJ\n"
                + "KoZIhvcNAQELBQADggEBAALhkqq3nq2BixSRlxvwogcF6gsCPEz7pq0zc5E7LhiQ\n"
                + "1xbTAEBo7xo+8HXJxa9EeVDT0tTj0Te8xTPqKqHnRBmR6YE33OnmtLFKXGwg7aTz\n"
                + "53nfL3Hx0wjKlR7HG8FfLqr+/7UjCORCva9axhxq9v+U3XRbizP1jP22Kidc5OKY\n"
                + "90/yuW785bUm3zz2YfexrYrLy2MB244gDTMIjADm8o/nTt4SIJ3QnN8VZpY2/hfm\n"
                + "v0VfrsaAziDz4VyiSmOCvHeW5tSE2lCZi6raE4f5/5Vnax/+FsKJj63/MTqEy/6u\n"
                + "xAxxrQ4fX/3SNOdJU5a3UJwslWVGD4iqnC3JEN6/DOE=" + "-----END CERTIFICATE-----";
        String appId = "wx3b7992146d96c84d";
        String openId = "oj1kg466Ho6KBeTush_CO8S9uCjw";
        WxPayClient theWxPayClient = new WxPayClient(mchId, apiV3Key, mchSerialNo, privateKey);
        String result = theWxPayClient.getCertificates();
        System.out.println("getCertificate result:" + result);

        List<String> lstCertificateKey = theWxPayClient.getCertificateKeyList();
        System.out.println(lstCertificateKey);

        List<String> lstCertificateSerialNo = theWxPayClient.getCertificateSerialNoList();
        System.out.println(lstCertificateSerialNo);

        String certificateKey = theWxPayClient.getCertificateKey();
        System.out.println(certificateKey);

        String certificateSerialNo = theWxPayClient.getCertificateSerialNo();
        System.out.println(certificateSerialNo);

        // String resultUpload = theWxPayClient.uploadFile("c:/windows/notepad.exe");
        String url = "https://api.mch.weixin.qq.com/v3/marketing/favor/users/oHkLxt_htg84TUEbzvlMwQzVDBqo/coupons";
        String requestBody = "{\n" + "    \"stock_id\": \"9433645\",\n"
                + "    \"stock_creator_mchid\": \"1900006511\",\n" + "    \"out_request_no\": \"20190522_001中文11\",\n"
                + "    \"appid\": \"wxab8acb865bb1637e\"\n" + "}";
        String resultPostRequest = theWxPayClient.postRequest(url, requestBody);
        System.out.println(resultPostRequest);

        // 取得prepayId
        JSONObject jParameter = new JSONObject();
        jParameter.put("mchid", mchId);
        String outTradeNo = String.valueOf(System.currentTimeMillis());
        jParameter.put("out_trade_no", outTradeNo);
        jParameter.put("appid", appId);
        jParameter.put("description", "测试订单" + System.currentTimeMillis());
        jParameter.put("notify_url", "https://qq.strosoft.com/Parking/api/WxPayNotify");
        JSONObject jAmount = new JSONObject();
        jAmount.put("total", 1);
        jAmount.put("currency", "CNY");
        jParameter.put("amount", jAmount);
        JSONObject jPayer = new JSONObject();
        jPayer.put("openid", openId);
        jParameter.put("payer", jPayer);

        String prepayId = theWxPayClient.getPrepayId(jParameter);

        // 获取支付信息
        JSONObject jPaymentInfo = theWxPayClient.getPaymentInfo(appId, prepayId);
        System.out.println(jPaymentInfo);
    }

}
