package com.ws.ldy.xijiaserver.xjservice;


import com.ws.ldy.admincore.controller.vo.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO  图片解析
 * @author 王松
 * @mail  1720696548@qq.com
 * @date  2020/1/31 0031 20:20
 * @return
 */
public interface PicParsingService {

     /**
      * TODO  图片文字提取
      * @author 王松
      * @mail  1720696548@qq.com 
      * @date  2020/1/31 0031 20:19
      * @return com.ws.ldy.admincore.controller.vo.ResponseData
      */
     ResponseData PicTextExtract(HttpServletRequest request);

}
