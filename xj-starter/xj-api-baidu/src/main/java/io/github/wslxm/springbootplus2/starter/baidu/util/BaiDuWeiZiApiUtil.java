package io.github.wslxm.springbootplus2.starter.baidu.util;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.baidu.config.BaiDuProperties;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 文字识别api (图片参数为远程url，图片需先上传到服务器在进行识别操作)
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/2/2 0002 9:58
 * @version 1.0.0
 */
@Component
@Slf4j
public class BaiDuWeiZiApiUtil {


    AipOcr client = null;

    /**
     * 初始化 client
     */
    public BaiDuWeiZiApiUtil(BaiDuProperties baiDuProperties) {
        client = new AipOcr(baiDuProperties.getAppId(), baiDuProperties.apiKey, baiDuProperties.getSecretKey());
        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        // client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //  System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
    }


    /**
     * 1、通用文字识别
     *   用户向服务请求识别某张图中的所有文字
     */
    public R<String> basicGeneral(InputStream in) {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.basicGeneral(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 2、通用文字识别高精度版
     *   用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
     */
    public R<String> basicAccurateGeneral(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.basicAccurateGeneral(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 3、通用文字识别（含位置信息版）
     *   用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。
     */
    public R<String> general(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.general(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     *  4、通用文字识别（含位置信息高精度版）
     *   用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长
     */
    public R<String> accurateGeneral(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("detect_direction", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.accurateGeneral(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 5、通用文字识别（含生僻字版）
     *    某些场景中，图片中的中文不光有常用字，还包含了生僻字，这时用户需要对该图进行文字识别，应使用通用文字识别（含生僻字版）。
     */
    public R<String> enhancedGeneral(InputStream in) {
// 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.enhancedGeneral(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 6、网络图片文字识别
     *   用户向服务请求识别一些网络上背景复杂，特殊字体的文字。
     */
    public R<String> webImage(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_language", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.webImage(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 7、身份证识别
     * 用户向服务请求识别身份证，身份证识别包括正面和背面。
     */
    public R<String> idcard(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "true");
        String idCardSide = "back";
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.idcard(fileByte, idCardSide, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 8、银行卡识别
     * 识别银行卡并返回卡号和发卡行。
     *
     */
    public R<String> bankcard(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.bankcard(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 9、驾驶证识别
     * 对机动车驾驶证所有关键字段进行识别
     *
     */
    public R<String> drivingLicense(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.drivingLicense(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 10、行驶证识别
     * 对机动车行驶证所有关键字段进行识别
     *
     */
    public R<String> vehicleLicense(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("accuracy", "normal");
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.vehicleLicense(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 11、车牌识别
     *识别机动车车牌，并返回签发地和号牌。
     *
     */
    public R<String> plateLicense(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("multi_detect", "true");
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.plateLicense(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 12、营业执照识别
     * 识别营业执照，并返回关键字段的值，包括单位名称、法人、地址、有效期、证件编号、社会信用代码等。
     *
     */
    public R<String> businessLicense(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.businessLicense(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 13、通用票据识别
     * 用户向服务请求识别医疗票据、发票、的士票、保险保单等票据类图片中的所有文字，并返回文字在图中的位置信息。
     *
     */
    public R<String> receipt(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("probability", "true");
        options.put("accuracy", "normal");
        options.put("detect_direction", "true");

        byte[] fileByte = readImageFile(in);
        JSONObject res = client.receipt(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 14、自定义模板文字识别
     *    自定义模板文字识别，是针对百度官方没有推出相应的模板，但是当用户需要对某一类卡证/票据（如房产证、军官证、火车票等）进行结构化的提取内容时，可以使用该产品快速制作模板，进行识别。
     *
     */
//    public R<String> custom(InputStream in) {
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        String templateSign = "Nsdax2424asaAS791823112";
//        byte[] fileByte = readImageFile(in);
//        JSONObject res = client.custom(fileByte, templateSign, options);
//        return R.success(JSON.toJSONString(res));
//    }

    /**
     * 15、表格文字识别同步接口
     *    自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。
     *
     */
    public R<String> form(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.form(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 16、表格文字识别
     *    自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。表格文字识别接口为异步接口，分为两个API：提交请求接口、获取结果接口
     *
     */
//    public R<String> tableRecognitionAsync(InputStream in) {
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//
//
//        // 参数为本地图片路径
//        String image = "test.jpg";
//        JSONObject res = client.tableRecognitionAsync(image, options);
//        log.debug(res.toString(2));
//
//        // 参数为本地图片二进制数组
//        byte[] file = readImageFile(image);
//        res = client.tableRecognitionAsync(file, options);
//        log.debug(res.toString(2));
//        return R.success(JSON.toJSONString(res));
//    }


    /**
     * 17、表格识别结果
     *    获取表格文字识别结果
     *
     */
//    public R<String> tableResultGet(InputStream in) {
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("result_type", "json");
//
//        String requestId = "23454320-23255";
//
//        // 表格识别结果
//        JSONObject res = client.tableResultGet(requestId, options);
//        log.debug(res.toString(2));
//        return R.success(JSON.toJSONString(res));
//    }

    /**
     * 18、表格识别轮询接口
     *    代码示例
     *
     */
//    public R<String> tableRecognizeToExcelUrl(InputStream in) {
//        //异步接口
//
//        //使用封装的同步轮询接口
//        JSONObject jsonres = client.tableRecognizeToJson(file, 20000);
//        log.debug(jsonres.toString(2));
//
//        JSONObject excelres = client.tableRecognizeToExcelUrl(file, 20000);
//        log.debug(excelres.toString(2));
//        return R.success(JSON.toJSONString(res));
//    }


    /**
     * 19、试卷分析与识别
     *    可对文档版面进行分析，输出图、表、标题、文本的位置，并输出分版块内容的OCR识别结果，支持中、英两种语言，手写、印刷体混排多种场景
     *
     */
    public R<String> docAnalysis(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.docAnalysis(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }

    /**
     * 20、仪器仪表盘读数识别
     *    适用于不同品牌、不同型号的仪器仪表盘读数识别，广泛适用于各类血糖仪、血压仪、燃气表、电表等，可识别表盘上的数字、英文、符号，支持液晶屏、字轮表等表型。
     *
     */
    public R<String> meter(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.meter(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 21、网络图片文字识别（含位置版）
     *    支持识别艺术字体或背景复杂的文字内容，除文字信息外，还可返回每行文字的位置信息、行置信度，以及单字符内容和位置等。
     *
     */
    public R<String> webimageLoc(InputStream in) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //
        byte[] fileByte = readImageFile(in);
        JSONObject res = client.webimageLoc(fileByte, options);
        return R.success(JSON.toJSONString(res));
    }


    /**
     * 流转二进制
     * <P>
     *        // 参数为本地图片二进制数组
     *         byte[] fileByte = readImageFile(in);
     *         JSONObject res = client.idcard(fileByte, idCardSide, options);
     *         log.debug(res.toString(2));
     * </P>
     * @author wangsong
     * @param in
     * @date 2021/2/2 0002 10:27
     * @return byte[]
     * @version 1.0.0
     */
    private static byte[] readImageFile(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        try {
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return out.toByteArray();
    }
}
