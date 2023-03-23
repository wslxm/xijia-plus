package io.github.wslxm.springbootplus2.manage.gc.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.GenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImplI;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/gc/generate")
@Api(value = "GenerateController", tags = "base--gc--代码生成")
public class GenerateController extends BaseController {


    @Autowired
    private XjGenerationSeviceImplI xjGenerationSeviceImpl;

    /**
     * 预览代码生成 (查询预览代码，预览代码存放于File/code/src.... 目录下，前端可直接访问)
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成预览代码")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public Result<Map<String, String>> preview(@RequestBody GenerateDto generateDto) {
        return Result.success(xjGenerationSeviceImpl.preview(generateDto));
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
    public Result<Map<String, String>> generateCode(@RequestBody GenerateDto generateDto) {
        return Result.success(xjGenerationSeviceImpl.generateCode(generateDto));
    }


    @ApiOperation("生成Vue代码(将直接下载)")
    @RequestMapping(value = "/generateCodeVue", method = RequestMethod.POST)
    public void generateCodeVue(@RequestBody GenerateDto generateDto) {
        xjGenerationSeviceImpl.generateCodeVue(generateDto);
    }


    @ApiOperation("生成java + vue代码(将直接下载)")
    @RequestMapping(value = "/generateCodeJavaAndVue", method = RequestMethod.POST)
    public void generateCodeJavaAndVue(@RequestBody GenerateDto generateDto) {
        xjGenerationSeviceImpl.generateCodeJavaAndVue(generateDto);
    }


    /**
     * 代码生成路径查询(代码生成时前端确认生成路径无误后再生成代码)
     */
    @ApiOperation("代码生成路径查看")
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    public Result<Map<String, String>> getPath(String tableName, String dataSourceId) {
        return Result.success(xjGenerationSeviceImpl.getPath(tableName, dataSourceId));
    }
}
