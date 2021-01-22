package com.ws.ldy.modules.third.baidu.service.impl;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.baidu.service.PicParsingService;
import com.ws.ldy.modules.third.baidu.util.BaseImg64;
import com.ws.ldy.modules.third.baidu.util.OpenBaiDuGeneralBasicApi;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class PicParsingServiceImpl  implements PicParsingService {


    @Override
    public R<String> PicTextExtract(MultipartFile file) {
        try {
            //获得上传文件并转为流
            InputStream inputStream = file.getInputStream();
            //baseImg64编码
            String baseImg64Image = BaseImg64.getImageStrFromPath2(inputStream);
            //调用百度图片识别接口
            String data = OpenBaiDuGeneralBasicApi.startAnalysis(baseImg64Image);
            System.out.println(data);
            return R.success(data);
        } catch (Exception e) {
            System.err.println(e);
            return R.error(10001,"图片解析错误");
        }
    }
}
