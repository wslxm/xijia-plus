package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.controller.base.BaseAdminConsoleController;
import com.ws.ldy.adminconsole.entity.DictionaryAdmin;

import com.ws.ldy.admincore.controller.vo.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Controller
@RequestMapping("/dictionaryAdmin")
public class DictionaryAdminController extends BaseAdminConsoleController {


    /**
     * TODO  分页查询
     *
     * @param type 字典类型
     * @return Map<String       ,               Object>
     */
    @RequestMapping("/findByType")
    @ResponseBody
    public Map<String, Object> findByType(String type) {
        List<DictionaryAdmin> dictionarys = service.dictionaryAdminServiceImpl.findByType(type);
        for (DictionaryAdmin dictionary : dictionarys) {
            dictionary.setName(dictionary.getValue());
        }
        return new Data(dictionarys).getResData();
    }

    /**
     * TODO  分页查询
     *
     * @param page   页数
     * @param limit  记录数
     * @return Map<String , Object>
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll(Integer page, Integer limit, String type) {
        Map<Integer, Map<String, Object>> param = new HashMap<>(2);
        param.put(1, new HashMap<String, Object>(1) {{
            put("type", type);
        }});
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<DictionaryAdmin> dictionaryAdmins = service.dictionaryAdminServiceImpl.fingPage(dao.dictionaryAdminDao, page, limit, param, sort);
        return new Data(dictionaryAdmins.getContent(), dictionaryAdmins.getTotalPages()).getResData();
    }


    /**
     * TODO  添加/修改
     *
     * @param type            t=1 添加，=2修改
     * @param dictionaryAdmin 对象数据
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(@PathVariable Integer type, DictionaryAdmin dictionaryAdmin) {
        if (type == 1) {
            service.dictionaryAdminServiceImpl.save(dao.dictionaryAdminDao, dictionaryAdmin);
        } else {
            service.dictionaryAdminServiceImpl.save(dao.dictionaryAdminDao, dictionaryAdmin);
        }
        return "success";
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer[] ids) {
        service.dictionaryAdminServiceImpl.deleteByIds(dao.dictionaryAdminDao, ids);
        return "success";
    }
}
