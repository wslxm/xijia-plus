package io.github.wslxm.springbootplus2.config.init;

import io.github.wslxm.springbootplus2.manage.admin.service.AdminAuthorityService;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
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
 * @version 1.0.1
 */
@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private AdminAuthorityService adminAuthorityService;

    @Override
    public void run(String... args) {
        // ========== 启动信息配置参数打印 ========
        // 启动成功图
        getSuccessYellowBright();
        // 更新权限表数据
        adminAuthorityService.refreshAuthDb();
        // 更新权限缓存数据
        adminAuthorityService.refreshAuthCache();
        // 当前启动环境
        log.info("当前启动环境 spring.profiles.active = {}", SpringContextUtil.getActiveProfile());
        log.info("当前启动端口 server.port = {}", PropUtil.findByKey("server.port"));
        // 日志测试
        log.info("开始测试当前日志级别配置【error-错误日志 | warn-警告日志 | info-普通日志 | debug-调试日志】 最后输出的日志类型为当前日志级别");
        log.error("error 错误日志已正常输出");
        log.warn("warn 警告日志已正常输出");
        log.info("info 普通日志已正常输出");
        log.debug("debug 调试日志已正常输出");
    }


    /**
     *  启动成功图
     */
    public static void getSuccessYellowBright() {
        log.info("\r\n" +
                "         ####                #             #  ##               ##  \r\n" +
                "     #########        ##### ##             #               #   ##  \r\n" +
                "      ##   ##        ####   #####          ####        ######  # ##\r\n" +
                "      #    ##            #########    #######          ############\r\n" +
                "     ########       ####### ## ##     ###   #  #         ## ####  #\r\n" +
                "     ##            #####    #  ##      #    ## ##        ##   ##  #\r\n" +
                "     ##               # #  ##  ##      # ### ###         ###  #   #\r\n" +
                "     ##########      ##### ##  ##      ## ## ###       ####  ##  ##\r\n" +
                "    ## ###  ###     #### ###   #      ##  ## ##       ###   ##   ##\r\n" +
                "    ## ##   ##      ##    ##  ##      ##  # ####            ##   ##\r\n" +
                "   ##  ##  ##            ## ####     ##  ## #  ## #        ##  ### \r\n" +
                "  ##   #######          ##   ##      #   #      ###       #    ### \r\n" +
                "                             #                   ##                ");
    }

}