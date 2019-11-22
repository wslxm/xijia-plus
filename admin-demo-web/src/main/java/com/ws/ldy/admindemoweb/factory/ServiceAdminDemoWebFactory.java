package com.ws.ldy.admindemoweb.factory;

import com.ws.ldy.admindemoweb.service.impl.UserSheepServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceAdminDemoWebFactory  {
    @Resource
    public com.ws.ldy.admindemoweb.service.impl.TestServiceImpl TestServiceImpl;  //此为代码生成

    @Resource
    public UserSheepServiceImpl userSheepServiceImpl;  //此为代码生成


}

























