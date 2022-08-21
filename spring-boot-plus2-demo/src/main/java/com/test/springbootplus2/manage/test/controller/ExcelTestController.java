package com.test.springbootplus2.manage.test.controller;

import com.test.springbootplus2.manage.test.model.vo.ExcelTestVO;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
import io.github.wslxm.springbootplus2.core.utils.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangsong
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/excel")
@Api(value = "ExcelTestController", tags = "excel测试")
public class ExcelTestController extends BaseController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("解析excel数据")
    public R<Object> upload(@RequestParam MultipartFile file) {
        try {
            List<Map<String, String>> maps = ExcelUtil.readExcel(file);
            return R.success(maps);
        } catch (Exception e) {
            throw new ErrorException(RType.PARAM_ANALYSIS_ERROR);
        }
    }


    @RequestMapping(value = "/exportExcelDownload", method = RequestMethod.GET)
    @ApiOperation("excel 导出")
    public void exportExcelDownload() {
        List<ExcelTestVO> excelTestVOS = new ArrayList<>();
        ExcelTestVO vo = null;
        for (int i = 0; i < 100; i++) {
            vo = new ExcelTestVO();
            vo.setUserNo("xj-" + i);
            vo.setUsername("小-" + i);
            vo.setUserPhone("1762868" + i);
            excelTestVOS.add(vo);
        }

        ExcelUtil.exportExcelDownload(excelTestVOS, response);
        ExcelUtil.exportExcelDownload(excelTestVOS, 40, 25, false, false, true, response);



    }
}