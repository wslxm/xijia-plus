package com.ws.ldy.modules.yw.statistics.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalPage;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalPageVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalPageService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 关键页访问统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:26
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsTotalPage")
@Api(value ="StatisticsTotalPageController" ,tags = "statistics--总数--关键页访问统计")
public class StatisticsTotalPageController extends BaseController<StatisticsTotalPageService>  {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "总数", notes= "")
    public R<IPage<StatisticsTotalPageVO>> findStatistics( ) {
        Page<StatisticsTotalPage> page = baseService.page(this.getPage(), new LambdaQueryWrapper<StatisticsTotalPage>()
                .orderByDesc(StatisticsTotalPage::getCreateTime)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, StatisticsTotalPageVO.class));
    }
}
