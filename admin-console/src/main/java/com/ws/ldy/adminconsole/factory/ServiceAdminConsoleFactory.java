package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.service.impl.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceAdminConsoleFactory {

    @Resource
    public UserAdminServiceImpl userServiceImpl;
    @Resource
    public MenuAdminServiceImpl menuServiceImpl;
    @Resource
    public RoleAdminServiceImpl roleServiceImpl;
    @Resource
    public RoleMenuAdminServiceImpl roleMenuServiceImpl;
    @Resource
    public RoleUserAdminServiceImpl roleUserServiceImpl;
    @Resource
    public DataBaseServiceImpl dataBaseDaoServiceImpl;
    @Resource
    public CodeGenerationImpl codeGenerationImpl;
    @Resource
    public DictionaryAdminServiceImpl dictionaryAdminServiceImpl;  //此为代码生成
    //{code1}

}






















