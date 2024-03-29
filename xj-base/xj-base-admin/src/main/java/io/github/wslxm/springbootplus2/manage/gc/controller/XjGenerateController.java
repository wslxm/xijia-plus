package io.github.wslxm.springbootplus2.manage.gc.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 代码生成
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/generate")
@Api(value = "XjGenerateController", tags = "base-gc--代码生成")
public class XjGenerateController extends BaseController {


    @Autowired
    private XjGenerationSeviceImpl xjGenerationSeviceImpl;

    /**
     * 预览代码生成 (查询预览代码，预览代码存放于File/code/src.... 目录下，前端可直接访问)
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成预览代码")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<Map<String, String>> preview(@RequestBody XjGenerateDto generateDto) {
        return R.success(xjGenerationSeviceImpl.preview(generateDto));
    }


    /**
     * 实际代码生成对应路径
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generateCode", method = RequestMethod.POST)
    public R<Map<String, String>> generateCode(@RequestBody XjGenerateDto generateDto) {
        return R.success(xjGenerationSeviceImpl.generateCode(generateDto));
    }


    @ApiOperation("生成Vue代码(将直接下载)")
    @RequestMapping(value = "/generateCodeVue", method = RequestMethod.POST)
    public void generateCodeVue(@RequestBody XjGenerateDto generateDto) {
        xjGenerationSeviceImpl.generateCodeVue(generateDto);
    }


    /**
     * 代码生成路径查询(代码生成时前端确认生成路径无误后再生成代码)
     */
    @ApiOperation("代码生成路径")
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    public R<Map<String, String>> getPath(String tableName) {
        return R.success(xjGenerationSeviceImpl.getPath(tableName));
    }
}
