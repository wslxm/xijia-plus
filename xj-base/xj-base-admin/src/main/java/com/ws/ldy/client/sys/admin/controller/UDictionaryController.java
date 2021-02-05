package com.ws.ldy.client.sys.admin.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/client/admin/dictionary")
@Api(value = "UDictionaryController", tags = "yh--base--字典管理")
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


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询(Tree)", notes = "不能传递字符串数字Code ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "父级code, 不传查询code，传递了只查询指定code下数据", required = false, paramType = "query"),
            @ApiImplicitParam(name = "isTree", value = "isTree 是否返回树结构数据 tree 是 false 否(返回过滤后的 list列表)", required = false, paramType = "query"),
    })
    public R<List<AdminDictionaryVO>> findByCode(@RequestParam(required = false) String code,
                                                 @RequestParam(required = false) Boolean isTree
    ) {
        // 是否查询禁用数据 true 查询  false 不查询
        // 是否需要最后一级数据 true 需要 false 不需要
        boolean isDisable = false;
        boolean isBottomLayer = false;
        List<AdminDictionaryVO> dictVO = baseService.findByCodeFetchDictVO(code, isDisable, isBottomLayer, isTree);
        return R.success(dictVO);
    }
}
