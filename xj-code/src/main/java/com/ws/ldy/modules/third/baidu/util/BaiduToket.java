package com.ws.ldy.modules.third.baidu.util;

import com.ws.ldy.modules.third.baidu.config.BaiDuConfig;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 百度文字提取toket 获取工具类
 */
public class BaiduToket {

    /***
     * authHost ： 百度API，toket 获取地址
     * clientId : 官网获取的 API Key 更新为你注册的
     * clientSecret ： 官网获取的 Secret Key 更新为你注册的
     * tokenTime ：    在jvm的保存tokt有效期，过期后重新获取，toket有效期2小时
     * access_token ： 在jvm的保存toket
     */


    private static long tokenTime = 0L;
    private static String access_token;


    /**
     * 获取token (如果过期了重新调接口获取)
     */
    public static String getToken() {
        if (System.currentTimeMillis() > tokenTime) {
            getAuth();
        }
        return access_token;
    }


    /**
     * 获取权限token, 获取API访问token 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @return 返回示例： { "access_token":
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000 }
     */
    public static void getAuth() {
        // 获取token地址,  1. grant_type为固定参数  2. 官网获取的 API Key 3. 官网获取的 Secret Key
        String getAccessTokenUrl = BaiDuConfig.authHost
                + "grant_type=client_credentials"
                + "&client_id=" + BaiDuConfig.clientId
                + "&client_secret=" + BaiDuConfig.clientSecret;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 请求方式
            connection.setRequestMethod("POST");
            connection.connect();

            // 获取所有响应头字段，并遍历所有的响应头字段
            // Map<String, List<String>> map = connection.getHeaderFields();
            // for (String key : map.keySet()) {
            //     System.err.println(key + "--->" + map.get(key));
            // }

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String expires_in = jsonObject.getString("expires_in");
            // 保存token和toket过期时间
            access_token = jsonObject.getString("access_token");
            tokenTime = System.currentTimeMillis() + Long.valueOf(expires_in);
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
    }

    /**
     * 获取toket 测试
     */
    public static void main(String[] args) {
        String auth = getToken();
        System.out.println(auth);
    }

}
