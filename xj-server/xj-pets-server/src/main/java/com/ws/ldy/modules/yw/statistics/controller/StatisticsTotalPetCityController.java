package com.ws.ldy.modules.yw.statistics.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalPetCityVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalPetCityService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 宠物城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:29
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsTotalPetCity")
@Api(value = "StatisticsTotalPetCityController", tags = "statistics--总数--宠物城市分布")
public class StatisticsTotalPetCityController extends BaseController<StatisticsTotalPetCityService> {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "城市宠物统计(城市/宠物数量)", notes = "")
    public R<StatisticsTotalPetCityVO> findStatistics() {
        return R.success(baseService.findStatistics());
    }

}
