package com.ws.ldy.modules.yw.statistics.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreasePageVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreasePageService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 页面统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:09
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsIncreasePage")
@Api(value = "StatisticsIncreasePageController", tags = "statistics--增长--页面访问增长统计")
public class StatisticsIncreasePageController extends BaseController<StatisticsIncreasePageService> {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "近n天页面访问统计)--(天/访问量)-- 总折线图", notes = "")
    public R<StatisticsIncreasePageVO> findStatistics(@RequestParam Integer dayNum) {
        StatisticsIncreasePageVO vo = baseService.findStatistics(dayNum);
        return R.successFind(vo);
    }


    @RequestMapping(value = "/findStatisticsByDayOrYear", method = RequestMethod.GET)
    @ApiOperation(value = "页面访问统计条件查询(年月日搜索)--(Map<name,List<天/访问量>> ) -- 每个页面一条折线图)", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "页面名称", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "time", value = "时间( 日:yyyy-MM-dd 月：yyyy-MM   年=yyyy)，根据类型传不同格式", required = true, paramType = "query", example = ""),
            @ApiImplicitParam(name = "type", value = "类型 1=日(小时数据)  2=月(天数据)  3-年(月数据)", required = true, paramType = "query", example = "")
    })
    public R<Map<String, StatisticsIncreasePageVO>> findStatisticsByDayOrYear(@RequestParam(required = false) String name,
                                                                              @RequestParam String time,
                                                                              @RequestParam Integer type) {
        Map<String, StatisticsIncreasePageVO> vo = baseService.findStatisticsByDayOrYear(name, time, type);
        return R.successFind(vo);
    }


    @RequestMapping(value = "/findStatisticsByOfLate", method = RequestMethod.GET)
    @ApiOperation(value = "页面访问统计条件查询(最近搜索)--(Map<name,List<天/访问量>> ) -- 每个页面一条折线图)", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dayNum", value = "最近N天/周", required = true, paramType = "query", example = ""),
            @ApiImplicitParam(name = "type", value = "1-天(小时数据) 2-周(天数据)", required = true, paramType = "query", example = ""),
    })
    public R<Map<String, StatisticsIncreasePageVO>> findStatisticsByOfLate(@RequestParam(required = true) Integer type, @RequestParam(required = true) Integer dayNum
    ) {
        Map<String, StatisticsIncreasePageVO> vo = baseService.findStatisticsByOfLate(type, dayNum);
        return R.successFind(vo);
    }
}
