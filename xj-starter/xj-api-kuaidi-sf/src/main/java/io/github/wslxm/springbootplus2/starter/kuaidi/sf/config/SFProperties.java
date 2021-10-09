package io.github.wslxm.springbootplus2.starter.kuaidi.sf.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "kuaidu.sf")
@Data
@Slf4j
public class SFProperties {


    private String url;                  // 请求地址
    private String customerCode;         // 客户编码
    private String customerCheckWord;    // 客户校检码
    private String customerMonthlyCard;  // 客户月结卡号（测试卡号）


//    public void println() {
//        // 打印加载信息
//        log.info(ConsoleColors.YELLOW_BRIGHT +
//                "\r\n" +
//                "|---     顺丰配置    ---| \r\n" +
//                "|  url: {} \r\n" +
//                "|  customerCode: {} \r\n" +
//                "|  customerCheckWord: {} \r\n" +
//                "|  customerMonthlyCard: {} \r\n" +
//                "| ----------------------------------|"
//                + ConsoleColors.RESET, url, customerCode, customerCheckWord, customerMonthlyCard);
//    }
}
