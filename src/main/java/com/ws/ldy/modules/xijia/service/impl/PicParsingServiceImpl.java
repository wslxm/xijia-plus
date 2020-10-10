package com.ws.ldy.modules.xijia.service.impl;

import com.ws.ldy.common.utils.BaseImg64;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.xijia.openapiutils.OpenBaiDuGeneralBasicApi;
import com.ws.ldy.modules.xijia.service.PicParsingService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;

@Service
public class PicParsingServiceImpl extends BaseIServiceImpl implements PicParsingService {


    @Override
    public String PicTextExtract(MultipartFile file) {
        try {
            //获得上传文件并转为流
            InputStream inputStream = file.getInputStream();
            //baseImg64编码
            String baseImg64Image = BaseImg64.getImageStrFromPath2(inputStream);
            //调用百度图片识别接口
            String data = OpenBaiDuGeneralBasicApi.startAnalysis(baseImg64Image);
            System.out.println(data);
            return data;
        } catch (Exception e) {
            System.err.println(e);
            throw new ErrorException(10001, "图片解析错误");
        }
    }
}
