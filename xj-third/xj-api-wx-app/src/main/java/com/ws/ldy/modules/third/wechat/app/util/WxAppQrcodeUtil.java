package com.ws.ldy.modules.third.wechat.app.util;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import com.ws.ldy.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;


/**
 * 生成太阳码
 * @author wangsong
 * @date 2021/3/3 0003 14:17
 * @return
 * @version 1.0.0
 */
@Service
@Slf4j
public class WxAppQrcodeUtil {

    @Autowired
    private WxMaQrcodeService wxMaQrcodeService;

    /**
     * @author wangsong
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam int width,
                                  @RequestParam boolean autoColor,
                                  @RequestParam boolean isHyaline) {

        try {
            WxMaCodeLineColor wxMaCodeLineColor = new WxMaCodeLineColor("0", "0", "0");
            File file = wxMaQrcodeService.createWxaCodeUnlimit(scene, page, width, autoColor, wxMaCodeLineColor, isHyaline);
            // 返回base64
            return R.success(Base64ImgUtils.file2Base64(file));
            //   返回字节流
            //   byte[] buffer = new byte[0];
            //   FileInputStream fis = new FileInputStream(file);
            //   ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
            //   byte[] bytes = new byte[fis.available()];
            //   int temp;
            //   while ((temp = fis.read(bytes)) != -1) {
            //       baos.write(bytes, 0, temp);
            //   }
            //   fis.close();
            //   baos.close();
            //   buffer = baos.toByteArray();
            //   return R.success( new String(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
