package io.github.wslxm.springbootplus2.starter.baidu.controller;

import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.baidu.constant.TextExtractTypeEnum;
import io.github.wslxm.springbootplus2.starter.baidu.util.BaiDuWeiZiApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangsong
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/baidu")
@Api(value = "BaiDuApiController", tags = "BaiDu  -->  百度API")
public class BaiDuApiController {


    @Autowired
    private BaiDuWeiZiApiUtil baiDuWeiZiApiUtil;


    @SneakyThrows
    @RequestMapping(value = "/textRecognition", method = RequestMethod.POST)
    @ApiOperation(value = "文字识别通用接口", notes = "上传图片,根据类型解析图片中的文字" +
            "\r\n 文档地址：https://ai.baidu.com/ai-doc/OCR/Nkibizxlf " +
            "\r\n 1、通用文字识别 " +
            "\r\n 2、通用文字识别高精度版 " +
            "\r\n 3、通用文字识别（含位置信息版）" +
            "\r\n 4、通用文字识别（含位置信息高精度版） " +
            "\r\n 5、通用文字识别（含生僻字版） " +
            "\r\n 6、网络图片文字识别 " +
            "\r\n 7、身份证识别 (必须使用真实身份证,否则返回数据身份证号码为空)" +
            "\r\n 8、银行卡识别" +
            "\r\n 9、驾驶证识别" +
            "\r\n 10、行驶证识别 " +
            "\r\n 11、车牌识别 " +
            "\r\n 12、营业执照识别 " +
            "\r\n 13、通用票据识别 " +
            "\r\n 14、自定义模板文字识别(不可使用)" +
            "\r\n 15、表格文字识别同步接口 " +
            "\r\n 16、表格文字识别(不可使用) " +
            "\r\n 17、表格识别结果(不可使用) " +
            "\r\n 18、表格识别轮询接口(不可使用) " +
            "\r\n 19、试卷分析与识别 " +
            "\r\n 20、仪器仪表盘读数识别 " +
            "\r\n 21、网络图片文字识别（含位置版） " +
            "")
    public R<String> picTextExtract(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type) {
        if (type.equals(TextExtractTypeEnum.V1.getCode())) {
            return baiDuWeiZiApiUtil.basicGeneral(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V2.getCode())) {
            return baiDuWeiZiApiUtil.basicAccurateGeneral(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V3.getCode())) {
            return baiDuWeiZiApiUtil.general(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V4.getCode())) {
            return baiDuWeiZiApiUtil.accurateGeneral(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V5.getCode())) {
            return baiDuWeiZiApiUtil.enhancedGeneral(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V6.getCode())) {
            return baiDuWeiZiApiUtil.webImage(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V7.getCode())) {
            return baiDuWeiZiApiUtil.idcard(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V8.getCode())) {
            return baiDuWeiZiApiUtil.bankcard(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V9.getCode())) {
            return baiDuWeiZiApiUtil.drivingLicense(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V10.getCode())) {
            return baiDuWeiZiApiUtil.vehicleLicense(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V11.getCode())) {
            return baiDuWeiZiApiUtil.plateLicense(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V12.getCode())) {
            return baiDuWeiZiApiUtil.businessLicense(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V13.getCode())) {
            return baiDuWeiZiApiUtil.receipt(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V14.getCode())) {
            return null;
        } else if (type.equals(TextExtractTypeEnum.V15.getCode())) {
            return baiDuWeiZiApiUtil.form(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V16.getCode())) {
            return null;
        } else if (type.equals(TextExtractTypeEnum.V17.getCode())) {
            return null;
        } else if (type.equals(TextExtractTypeEnum.V18.getCode())) {
            return null;
        } else if (type.equals(TextExtractTypeEnum.V19.getCode())) {
            return baiDuWeiZiApiUtil.docAnalysis(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V20.getCode())) {
            return baiDuWeiZiApiUtil.meter(file.getInputStream());
        } else if (type.equals(TextExtractTypeEnum.V21.getCode())) {
            return baiDuWeiZiApiUtil.webimageLoc(file.getInputStream());
        } else {
            return null;
        }
    }
}




