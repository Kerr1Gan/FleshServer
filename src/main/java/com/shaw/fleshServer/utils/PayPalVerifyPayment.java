package com.shaw.fleshServer.utils;

import com.shaw.fleshServer.Stub;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by KerriGan on 2018/2/14.
 */

public class PayPalVerifyPayment {


    private static final String TOKEN_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private static final String PAYMENT_DETAIL = "https://api.sandbox.paypal.com/v1/payments/payment/";
    private static final String clientId = "AS2hguXeqedhAMS9_WQqQezlz_VjhenTS1g7roUA6govlxZ0BghxeApZmQt3OyJSZeqgLDNv7mKUq5TD";
    private static final String secret = "EPFysj4dO94GEGbDFImvmltXd7Gyxu7YimTovmz_Pfr0-PkTC1R-zLhLbNpvMK1v7qPTr55pwiMjsICn";

    static {
        loadJarLib(); // org.json包在生成war包时没有导入
    }

    /**
     * 获取token
     * 了解更多：https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
     *
     * @return
     */
    private String getAccessToken() {
        try {
            URL url = new URL(TOKEN_URL);
            String authorization = clientId + ":" + secret;
            authorization = Base64.encodeBase64String(authorization.getBytes());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 提交模式
            //设置请求头header
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "en_US");
            conn.setRequestProperty("Authorization", "Basic " + authorization);
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            conn.setDoOutput(true);// 是否输入参数
            String params = "grant_type=client_credentials";
            conn.getOutputStream().write(params.getBytes());// 输入参数

            InputStreamReader inStream = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(inStream);
            StringBuilder result = new StringBuilder();
            String lineTxt = null;
            while ((lineTxt = reader.readLine()) != null) {
                result.append(lineTxt);
            }
            reader.close();
            String accessToken = new JSONObject(result.toString()).optString("access_token");
            System.out.println("getAccessToken:" + accessToken);
            return accessToken;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    /**
     * 获取支付详情
     * 了解更多：https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
     *
     * @param paymentId 支付ID，来自于用户客户端
     * @return
     */
    public String getPaymentDetails(String paymentId) {
        try {
            URL url = new URL(PAYMENT_DETAIL + paymentId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");// 提交模式
            //设置请求头header
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            InputStreamReader inStream = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(inStream);
            StringBuilder result = new StringBuilder();
            String lineTxt = null;
            while ((lineTxt = reader.readLine()) != null) {
                result.append(lineTxt);
            }
            reader.close();
            return result.toString();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    /**
     * 获取支付详情
     * 了解更多：https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
     *
     * @param paymentId 支付ID，来自于用户客户端
     * @return 1 is success , -1 is connection error , 0 is fail
     */
    public int verifyPayment(String paymentId, double usd) throws Exception {
        String str = getPaymentDetails(paymentId);
        System.out.println(str);
        if (str == null) {
            return -1;
        }
        JSONObject detail = new JSONObject(str);
        //校验订单是否完成
        if ("approved".equals(detail.optString("state"))) {
            JSONObject transactions = detail.optJSONArray("transactions").optJSONObject(0);
            JSONObject amount = transactions.optJSONObject("amount");
            JSONArray relatedResources = transactions.optJSONArray("related_resources");
            //从数据库查询支付总金额与Paypal校验支付总金额
            double total = usd;
            System.out.println("amount.optDouble('total'):" + amount.optDouble("total"));
            if (total != amount.optDouble("total")) {
                return 0;
            }
            //校验交易货币类型
            String currency = "USD";
            if (!currency.equals(amount.optString("currency"))) {
                return 0;
            }
            //校验每个子订单是否完成
            for (int i = 0, j = relatedResources.length(); i < j; i++) {
                JSONObject sale = relatedResources.optJSONObject(i).optJSONObject("sale");
                if (sale != null) {
                    if (!"completed".equals(sale.optString("state"))) {
                        System.out.println("子订单未完成,订单状态:" + sale.optString("state"));
                    }
                }
            }
            return 1;
        }
        return 0;
    }

    public static void loadJarLib() {
        File jarFile = new File(Stub.class.getResource(".\\").getPath());
        int index = 0;
        while (index++ < 3) {
            jarFile = jarFile.getParentFile();
        }
        File[] jarFiles = jarFile.listFiles();
        if (jarFiles != null) {
            Method method = null;
            boolean accessible = false;
            try {
                // 从URLClassLoader类中获取类所在文件夹的方法
                // 对于jar文件，可以理解为一个存放class文件的文件夹
                method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                accessible = method.isAccessible();     // 获取方法的访问权限
                if (accessible == false) {
                    method.setAccessible(true);     // 设置方法的访问权限
                }
                // 获取系统类加载器
                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                for (File file : jarFiles) {
                    URL url = file.toURI().toURL();
                    if (!url.toString().endsWith(".jar")) {
                        continue;
                    }
                    try {
                        method.invoke(classLoader, url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (method != null) {
                    method.setAccessible(accessible);
                }
            }
        }
    }

    public static void main(String[] args) {
        PayPalVerifyPayment payment = new PayPalVerifyPayment();
        int success = 0;
        try {
            success = payment.verifyPayment("PAY-87X20470C98270520LKB2XBI", 5.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(success == 1 ? "支付完成" : "支付校验失败");
    }
}

