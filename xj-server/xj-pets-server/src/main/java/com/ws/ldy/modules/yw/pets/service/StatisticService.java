package com.ws.ldy.modules.yw.pets.service;

import com.ws.ldy.client.yw.pets.model.vo.NewLatestAnnouncementVO;

/**
 * 统计相关
 * @author wangsong
 * @date 2020/12/30 0030 10:25
 * @return
 * @version 1.0.0
 */
public interface StatisticService {


    /**
     * app首页--最新公示
     * @author wangsong
     * @date 2020/12/30 0030 10:29
     * @return void
     * @version 1.0.0
     */
    NewLatestAnnouncementVO newLatestAnnouncement();
}
