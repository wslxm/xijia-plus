package io.github.wslxm.springbootplus2.starter.aliyun.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 阿里云 oss 工具
 *
 * @author wangsong
 * @date 2022/5/26 17:30
 * @return
 */
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"})
public class AliYunOssServer {

	public static void main(String[] args) {
		SpringApplication.run(AliYunOssServer.class, args);
	}
}
