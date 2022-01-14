package com.test.springbootplus2.manage.test.controller;

import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangsong
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/xj/excel")
@Api(value = "ExcelTestController", tags = "excel测试")
public class ExcelTestController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("解析excel数据")
    public R<Object> upload(@RequestParam MultipartFile file) {
        try {
            return R.success(ExcelUtil.readExcel(file));
        } catch (Exception e) {
            throw new ErrorException(RType.PARAM_ANALYSIS_ERROR);
        }
    }
}