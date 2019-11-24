package com.ws.ldy.adminconsole.service;


import com.ws.ldy.adminconsole.entity.DictionaryAdmin;
import com.ws.ldy.adminconsole.service.base.BaseAdminConsoleService;

import java.util.List;

/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
public interface DictionaryAdminService extends BaseAdminConsoleService<DictionaryAdmin, Integer> {
    List<DictionaryAdmin> findByType(String name);
}
