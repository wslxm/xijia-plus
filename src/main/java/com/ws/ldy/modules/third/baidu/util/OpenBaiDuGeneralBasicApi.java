package com.ws.ldy.modules.third.baidu.util;


import com.ws.ldy.common.utils.BaseImg64;
import com.ws.ldy.modules.third.baidu.config.BaiDuConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URI;

/**
 * 百度图片识别api接口
 * 使用BaseImg64.getImageStrFromPath2(InputStream) 方法，将图片进行 BaseImg64 编码
 * 然后使用 startAnalysis, 传递BaseImg64编码后的图片字符串
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/31 0031 21:39
 */
@SuppressWarnings("all")
public class OpenBaiDuGeneralBasicApi {


    /**
     * 开始解析图片，通过传递参数：url和image进行文字识别，返回识别结果
     */
    public static String startAnalysis(String baseImg64Image) {
        try {
            // 获取HttpClient
            HttpClient httpClient = new DefaultHttpClient();
            // 创建请求
            HttpPost post = new HttpPost();
            // 设置请求头，请求头必须为application/x-www-form-urlencoded，因为是传递一个很长的字符串，不能分段发送
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            // 设置URL
            post.setURI(new URI(BaiDuConfig.PIC_GENERAL_BASIC_URL + "?access_token=" + BaiduToket.getToken()));
            // 添加请求数据
            StringEntity entity = new StringEntity("image=" + baseImg64Image);
            post.setEntity(entity);
            // 响应内容
            HttpResponse response = httpClient.execute(post);
            System.out.println(response.toString());
            if (response.getStatusLine().getStatusCode() == 200) {
                // 读取服务器返回过来的json字符串数据
                String str = EntityUtils.toString(response.getEntity());
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    /**
     * 测试本地图片
     *
     * @param args
     * @author wangsong
     * @date 2019年6月29日 上午10:58:17
     */
    public static void main(String[] args) {

        // long now = System.currentTimeMillis();
        //获取本地文件
        String path = "D:\\test.png";
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        //BaseImg64转码
        String image = BaseImg64.getImageStrFromPath(path);
        // 开始解析
        String picData = startAnalysis(image);
        // System.out.println("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");
        System.out.println(picData);
    }
}
