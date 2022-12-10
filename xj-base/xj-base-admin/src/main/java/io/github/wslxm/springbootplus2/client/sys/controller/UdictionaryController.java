package io.github.wslxm.springbootplus2.client.sys.controller;

import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DictionaryQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DictionaryService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * yh--base--字典管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_CLIENT + "/sys/dictionary")
public class UdictionaryController extends BaseController<DictionaryService> {


    /**
     * 查询所有-code分组
     *
     * <p>
     * 1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
     * "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
     * "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
     * "\r\n 4、添加/更新/删除/修改排序后端都会更新版本号,重新拉取直接获取最新数据" +
     * "\r\n 5、不包括禁用数据" +
     * "\r\n 建议: 打开首页时调用此方法,刷新缓存数据, 刷新首页时在此刷新缓存
     * </P>
     *
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.Map < java.lang.String, io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup>>
     * @author wangsong
     * @date 2022/12/10 0010 14:07
     * @version 1.0.0
     */
    @RequestMapping(value = "/findCodeGroup", method = RequestMethod.GET)
    public Result<Map<String, DictionaryCodeGroup>> findCodeGroup() {
        return Result.successFind(baseService.findCodeGroup());
    }


    /**
     * Code查询(Tree)
     * <P>
     *     不能传递字符串数字Code
     * </P>
     * @author wangsong
     * @param code 父级code, 不传查询code，传递了只查询指定code下数据
     * @param isTree isTree 是否返回树结构数据 tree 是 false 否(返回过滤后的 list列表)
     * @date 2022/12/10 0010 14:07
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List<io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO>>
     * @version 1.0.0
     */
    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    public Result<List<DictionaryVO>> findByCode(@RequestParam(required = false) String code,
                                                 @RequestParam(required = false) Boolean isTree) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(code);
        query.setIsTree(isTree);
        query.setIsDisable(false);
        query.setIsBottomLayer(false);
        List<DictionaryVO> dictVO = baseService.list(query);
        return Result.success(dictVO);
    }
}
