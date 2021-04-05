package com.ws.ldy.modules.sys.xj.model.vo;


import cn.hutool.system.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * jvm 信息类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/14 0014 11:42
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjToolJvmInfoVO 对象", description = "常用工具文件管理")
public class XjToolJvmInfoVO implements Serializable {

    private static final long serialVersionUID = 6514988039216936158L;
    @ApiModelProperty(notes = "Java Virtual Machine Specification信息", position = 0)
    private JvmSpecInfo jvmSpecInfo;
    @ApiModelProperty(notes = "Java Virtual Machine Implementation信息", position = 1)
    private JvmInfo jvmInfo;
    @ApiModelProperty(notes = "Java Specification信息", position = 2)
    private JavaSpecInfo javaSpecInfo;
    @ApiModelProperty(notes = "Java Implementation信息", position = 3)
    private JavaInfo javaInfo;
    //    @ApiModelProperty(notes = "Java运行时信息,jdk信息", position = 4)
//    private JavaRuntimeInfo javaRuntimeInfo;
    @ApiModelProperty(notes = "系统信息", position = 5)
    private OsInfo osInfo;
    @ApiModelProperty(notes = "计算机信息,项目位置", position = 6)
    private UserInfo userInfo;
    @ApiModelProperty(notes = "主机ip信息", position = 7)
    private HostInfo hostInfo;
    @ApiModelProperty(notes = "运行时JVM信息，包括内存总大小、已用大小、可用大小等", position = 8)
    private RuntimeInfoVO runtimeInfo;
    @ApiModelProperty(notes = "运行时内存信息，包括内存总大小、已用大小、可用大小等", position = 9)
    private OperatingSystemMXBean operatingSystemMXBean;
    @ApiModelProperty(notes = "jvm启动时间信息", position = 9)
    private RuntimeMXBean runtimeMXBean;
    @ApiModelProperty(notes = "磁盘信息", position = 9)
    private FileInfoVO fileInfoVO;

    @Data
    @ToString(callSuper = true)
    public static class RuntimeInfoVO implements Serializable {

        private static final long serialVersionUID = 4776903229464827233L;
        //private String runtime;
        private Long maxMemory;
        private Long totalMemory;
        private Long freeMemory;
        private Long usableMemory;
        private Integer availableProcessors;

    }

    @Data
    @ToString(callSuper = true)
    public static class FileInfoVO implements Serializable {

        private static final long serialVersionUID = 4776903229464827233L;
        private String fileSysType;  // 文件系统类型
        private String fileType;     // 文件类型
        private Long total;  //总
        private Long free;   //剩余
        private Long usable; //已使用

    }
}
