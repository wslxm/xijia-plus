package com.ws.ldy.client.sys.admin.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/client/admin/dictionary")
@Api(value = "UDictionaryController", tags = "yh--base--字典管理", consumes = BaseConstant.InterfaceType.PC_USER)
public class UDictionaryController extends BaseController<AdminDictionaryService> {


    @RequestMapping(value = "/findCodeGroup", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有-code分组", notes = "1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
            "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
            "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
            "\r\n 4、添加/更新/删除/修改排序后端都会更新版本号,重新拉取直接获取最新数据" +
            "\r\n 5、不包括禁用数据" +
            "\r\n 建议: 打开首页时调用此方法,刷新缓存数据, 刷新首页时在此刷新缓存"
    )
    public R<Map<String, AdminDictionaryVO.FindCodeGroup>> findCodeGroup() {
        return R.successFind(baseService.findCodeGroup());
    }


//    @RequestMapping(value = "/findVersion", method = RequestMethod.GET)
//    @ApiOperation(value = "获取字典版本", notes = "获取数据字典版本信息, 如果判断到本地版本和线上版本不一致,调用 findCodeGroup 接口刷新字典数据")
//    public R<Integer> findVersion() {
//        return R.success(BaseConstant.Cache.DICT_VERSION);
//    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询(Tree)", notes = "无限层次, 树结构，只能传递字符串Code, 不能传递字符串的数字Code，不包括禁用数据 ")
    public R<AdminDictionaryVO> findByCode(@RequestParam String code) {
        // 不能传递字符串数字来查询
        if (StringUtil.isInteger(code)) {
            throw new ErrorException(RType.PARAM_ERROR);
        }
        AdminDictionaryVO dict = baseService.findByCodeFetchDictVO(code, true);
        return R.success(dict);
    }
}
