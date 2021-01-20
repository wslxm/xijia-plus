package com.ws.ldy.config.init;


import com.ws.ldy.common.utils.ConsoleColors;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.third.aliyun.oss.config.AliYunOssProperties;
import com.ws.ldy.modules.third.aliyun.sms.util.AliSmsUtil;
import com.ws.ldy.modules.third.kuaidi.kuaidi100.config.KuaiDi100Properties;
import com.ws.ldy.modules.third.kuaidi.sf.config.SFProperties;
import com.ws.ldy.modules.third.qiniu.config.QiNiuOssProperties;
import com.ws.ldy.modules.third.wechat.app.config.WxAppProperties;
import com.ws.ldy.modules.third.wechat.mq.config.WxMqProperties;
import com.ws.ldy.modules.third.wechat.pay.config.WxPayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * 项目完全启动成功后的执行的一些处理操作
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/7/23 0023 9:04
 * @version 1.0.0
 */
@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private AdminAuthorityService adminAuthorityService;

    @Autowired
    private AliYunOssProperties aliYunOssProperties;
    @Autowired
    private AliSmsUtil aliSmsUtil;
    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private WxMqProperties wxMqProperties;

    @Autowired
    private WxAppProperties wxAppProperties;

    @Autowired
    private QiNiuOssProperties qiNiuOssProperties;

    @Autowired
    private KuaiDi100Properties kuaiDi100Properties;

    @Autowired
    private SFProperties sfProperties;

    @Override
    public void run(String... args) {
        // 更新权限表数据
        adminAuthorityService.refreshAuthDB();
        // 更新权限缓存数据
        adminAuthorityService.refreshAuthCache();

        // ========== 启动信息配置参数打印 ========
        ConsoleColors.getSuccessYellowBright();         // 启动成功图
        aliYunOssProperties.println();       // 阿里云oss
        aliSmsUtil.println();                // 阿里云sms
        wxPayProperties.println();           // 微信支付
        wxMqProperties.println();            // 微信公众号
        wxAppProperties.println();           // 微信小程序
        qiNiuOssProperties.println();        // 七牛云oss
        kuaiDi100Properties.println();       // 快递100
        sfProperties.println();              // 快递-顺丰-丰桥
    }
}