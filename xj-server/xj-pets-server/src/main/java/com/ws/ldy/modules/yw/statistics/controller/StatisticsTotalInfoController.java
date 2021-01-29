package com.ws.ldy.modules.yw.statistics.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalInfo;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalInfoVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalInfoService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 平台基础数据统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:22
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsTotalInfo")
@Api(value = "StatisticsTotalInfoController", tags = "statistics--总数--平台基础数据统计")
public class StatisticsTotalInfoController extends BaseController<StatisticsTotalInfoService> {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "平台基础数据统计(最新总数)", notes = "")
    public R<StatisticsTotalInfoVO> findStatistics() {
        List<StatisticsTotalInfo> list = baseService.list(new LambdaQueryWrapper<StatisticsTotalInfo>()
                .orderByDesc(StatisticsTotalInfo::getTime)
                .last(" limit 0,1")
        );
        if (list.size() <= 0) {
            return null;
        }
        return R.successFind(BeanDtoVoUtil.convert(list.get(0), StatisticsTotalInfoVO.class));
    }
}
