package com.ws.ldy.adminconsole.service;


import com.ws.ldy.adminconsole.entity.DictionaryAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.util.List;

/**
 * TODO  数据字典
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
public interface DictionaryAdminService extends BaseServiceApi<DictionaryAdmin, Integer> {

    List<DictionaryAdmin> findByType(String name);

}
