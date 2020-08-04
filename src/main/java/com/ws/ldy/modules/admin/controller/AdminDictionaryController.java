package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminDictionaryDTO;
import com.ws.ldy.modules.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.admin.service.impl.AdminDictionaryServiceImpl;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping("/admin/adminDictionary")
@Api(value = "AdminDictionaryController", tags = "字典管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminDictionaryController extends BaseController<AdminDictionaryService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "")
    public R<List<AdminDictionaryVO>> findList() {
        List<AdminDictionary> list = baseService.list(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getCode));
        return R.successFind(BeanDtoVoUtil.listVo(list, AdminDictionaryVO.class));
    }


    @RequestMapping(value = "/findCodeGroup", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有-code分组", notes = "1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
            "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
            "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
            "\r\n 4、添加/更新/删除/修改排序后端都会更新版本号,重新拉取直接获取最新数据" +
            "\r\n 建议: 每一次打开一个新页面时调用此方法,刷新缓存数据"
    )
    public R<Map<String, AdminDictionaryVO>> findCodeGroup() {
        return R.successFind(baseService.findCodeGroup());
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询", notes = "无限层次, 树结构，只能传递字符串Code, 不能传递字符串数字Code")
    public R<AdminDictionaryVO> findByCode(@RequestParam String code) {
        // 不能传递字符串数字来查询
        if (StringUtil.isInteger(code)) {
            throw new ErrorException(RType.SYSTEM_PARAMETER_IS_NO);
        }
        AdminDictionaryVO dict = baseService.findByCodeFetchDictVO(code);
        return R.success(dict);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "字符串Code不能重复,  数字类型的Code可以重复")
    public R<Void> insert(@RequestBody AdminDictionaryDTO adminDictionaryDto) {
        adminDictionaryDto.setCode(adminDictionaryDto.getCode().trim());
        if (!StringUtil.isInteger(adminDictionaryDto.getCode())
                && baseService.count(new LambdaQueryWrapper<AdminDictionary>().eq(com.ws.ldy.modules.admin.model.entity.AdminDictionary::getCode, adminDictionaryDto.getCode())) > 0) {
            // 字符串code 为string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.ADMIN_DICT_DUPLICATE);
        }
        baseService.save(adminDictionaryDto.convert(AdminDictionary.class));
        //刷新版本号
        AdminDictionaryServiceImpl.version++;
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code 和数字类型的Code 不受影响")
    public R<Void> upd(@RequestBody AdminDictionaryDTO adminDictionaryDto) {
        adminDictionaryDto.setCode(adminDictionaryDto.getCode().trim());
        //原数据
        AdminDictionary dict = baseService.getById(adminDictionaryDto.getId());
        // 修改了code 判断--> 字符串code 为string时不能重复, 为Integer时可以重复
        if (!dict.getCode().equals(adminDictionaryDto.getCode().trim())) {
            if (!StringUtil.isInteger(adminDictionaryDto.getCode())
                    && baseService.count(new LambdaQueryWrapper<AdminDictionary>().eq(com.ws.ldy.modules.admin.model.entity.AdminDictionary::getCode, adminDictionaryDto.getCode())) > 0) {
                throw new ErrorException(RType.ADMIN_DICT_DUPLICATE);
            }
        }
        baseService.updateById(adminDictionaryDto.convert(AdminDictionary.class));
        //刷新版本号
        AdminDictionaryServiceImpl.version++;
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "删除当前ID数据以及该ID下的所有子层级数据")
    public R<Void> del(@RequestParam String id) {
        List<String> ids = baseService.findByIdFetchIds(id);
        baseService.removeByIds(ids);
        //刷新版本号
        AdminDictionaryServiceImpl.version++;
        return R.successDelete();
    }


    @RequestMapping(value = "/updBySort", method = RequestMethod.PUT)
    @ApiOperation(value = "修改排序", notes = "排序数字越小,越靠前")
    public R<Void> updBySort(@RequestParam String id, @RequestParam Integer sort) {
        AdminDictionary dict = new AdminDictionary();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        //刷新版本号
        AdminDictionaryServiceImpl.version++;
        return R.successUpdate();
    }
}
