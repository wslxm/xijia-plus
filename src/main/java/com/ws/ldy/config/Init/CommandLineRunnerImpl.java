package com.ws.ldy.config.Init;


import com.ws.ldy.common.utils.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public void run(String... args) throws Exception {
        // 启动成功图
        ConsoleColors.getSuccessYellowBright();
        // 添加启动相关说明信息或其他
    }
}