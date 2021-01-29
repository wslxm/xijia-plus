package com.ws.ldy.modules.yw.statistics.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalDeclareCityVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalDeclareCityService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 审报城市统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:19
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsTotalDeclareCity")
@Api(value ="StatisticsTotalDeclareCityController" ,tags = "statistics--总数--审报城市分布")
public class StatisticsTotalDeclareCityController extends BaseController<StatisticsTotalDeclareCityService>  {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "审报城市分布(城市/申报金额)", notes= "")
    public R<StatisticsTotalDeclareCityVO> findStatistics(  ) {
        return R.successFind(baseService. findStatistics());
    }
}
