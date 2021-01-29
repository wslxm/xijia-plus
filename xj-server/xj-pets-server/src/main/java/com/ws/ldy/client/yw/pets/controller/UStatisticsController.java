package com.ws.ldy.client.yw.pets.controller;

import com.ws.ldy.client.yw.pets.model.vo.NewLatestAnnouncementVO;
import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.service.StatisticService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户端相关统计信息
 * <p>
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/statistics")
@Api(value = "UPetsWalletFlowController", tags = "yh--用户端相关统计信息")
public class UStatisticsController extends BaseController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/newLatestAnnouncement", method = RequestMethod.GET)
    @ApiOperation(value = "app首页--最新公示", notes = "")
    public R<NewLatestAnnouncementVO> newLatestAnnouncement() {
        NewLatestAnnouncementVO vo = statisticService.newLatestAnnouncement();
        return R.success(vo);
    }
}
