package com.ws.ldy.baseadmin.controller;

import com.ws.ldy.baseadmin.entity.DictionaryAdmin;
import com.ws.ldy.baseadmin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.admincore.common.annotation.LdyAuthority;
import com.ws.ldy.admincore.common.query.QueryCriteria;
import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
 * TODO  字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Controller
@RequestMapping("/dictionaryAdmin")
@LdyAuthority(value = {"dictionary", "字典表"})
public class DictionaryAdminController extends BaseController {

    @Autowired
    private DictionaryAdminServiceImpl dictionaryAdminServiceImpl;

    /**
     * TODO  根据类型查询Type
     *
     * @param type 字典类型
     * @return Map<String, Object>
     */
    @RequestMapping("/findByType")
    @ResponseBody
//    @LdyAuthority(value = {"dictionary:findByType", "根据类型查询Type"})
    public ResponseData findByType(String type) {
        List<DictionaryAdmin> dictionarys = dictionaryAdminServiceImpl.findByType(type);
        for (DictionaryAdmin dictionary : dictionarys) {
            dictionary.setName(dictionary.getValue());
        }
        return ResponseData.success(dictionarys);
    }

    /**
     * TODO  分页查询
     *
     * @param page  页数
     * @param limit 记录数
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll")
    @ResponseBody
 //   @LdyAuthority(value = {"dictionary:findAll", "分页查询"})
    public ResponseData findAll(Integer page, Integer limit, String type) {
        Map<String, Map<String, Object>> param = new HashMap<>(2);
        QueryCriteria.equal(param, "type", type);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<DictionaryAdmin> dictionaryAdmins = dictionaryAdminServiceImpl.fingPage(page, limit, param, sort);
        return ResponseData.success(dictionaryAdmins.getContent(), dictionaryAdmins.getTotalPages());
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
  //  @LdyAuthority(value = {"dictionary:save", "添加/修改"})
    public String save(@PathVariable Integer type, DictionaryAdmin dictionaryAdmin) {
        if (type == 1) {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
        } else {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
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
  //  @LdyAuthority(value = {"dictionary:delete", "删除"})
    public String delete(Integer[] ids) {
        dictionaryAdminServiceImpl.deleteByIds(ids);
        return "success";
    }
}
