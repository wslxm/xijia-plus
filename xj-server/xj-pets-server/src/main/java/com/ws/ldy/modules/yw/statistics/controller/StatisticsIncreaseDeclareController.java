package com.ws.ldy.modules.yw.statistics.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseDeclareVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseDeclareService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 申报统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:49:50
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsIncreaseDeclare")
@Api(value = "StatisticsIncreaseDeclareController", tags = "statistics--增长--申报金额增长统计")
public class StatisticsIncreaseDeclareController extends BaseController<StatisticsIncreaseDeclareService> {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "近n天申报金额--(天/金额) ", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dayNum", value = "查询n天内数据", required = true, paramType = "query", example = ""),
            @ApiImplicitParam(name = "type", value = "1-小时数据 2-天数据", required = true, paramType = "query", example = ""),
    })
    public R<StatisticsIncreaseDeclareVO> findStatistics(@RequestParam Integer dayNum,@RequestParam Integer type) {
        StatisticsIncreaseDeclareVO vo = baseService.findStatistics(dayNum,type);
        return R.successFind(vo);
    }
}
