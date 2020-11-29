package com.ws.ldy.modules.xijia.service;

import java.lang.reflect.InvocationTargetException;

/**
  * java代码运行器
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2020/11/29 0029 10:50
  * @version 1.0.0      
  */
public interface JavaCodeRunService {

    public Object invoke(String javaSrc) ;
}
