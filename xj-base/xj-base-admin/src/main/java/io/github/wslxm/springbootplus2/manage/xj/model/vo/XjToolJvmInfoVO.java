package io.github.wslxm.springbootplus2.manage.xj.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * jvm 信息类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/14 0014 11:42
 * @version 1.0.1
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjToolJvmInfoVO 对象", description = "常用工具文件管理")
public class XjToolJvmInfoVO implements Serializable {

    private static final long serialVersionUID = 4827571724805823332L;

    @ApiModelProperty(notes = "cpu使用信息", position = 1)
    private CpuVO cpu;
    @ApiModelProperty(notes = "系统内存所有情况", position = 2)
    private RamVO ram;
    @ApiModelProperty(notes = "jvm内存使用情况", position = 3)
    private JvmRamVO jvmRam;
    @ApiModelProperty(notes = "服务器信息", position = 7)
    private ServerInformationVO serverInformation;
    @ApiModelProperty(notes = "JVM 程序信息", position = 8)
    private JvmInformationVO jvmInformation;
    @ApiModelProperty(notes = "文件信息", position = 9)
    private FileInfoVO fileInfo;


    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "CpuVO 对象", description = "1、系统--cpu使用信息")
    public static class CpuVO implements Serializable {
        private static final long serialVersionUID = -6539830698200732287L;
        @ApiModelProperty(notes = "核心数", position = 1)
        private Integer numberOfCores;
        @ApiModelProperty(notes = "用户使用率", position = 2)
        private Double userUtilization;
        @ApiModelProperty(notes = "系统使用率", position = 3)
        private Double systemUtilization;
        @ApiModelProperty(notes = "cpu使用率", position = 4)
        private Double  usageRate;
    }

    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "RamVO 对象", description = "2、系统--内存使用情况(GB)")
    public static class RamVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        @ApiModelProperty(notes = "总内存", position = 1)
        private Double totalMemory;
        @ApiModelProperty(notes = "已用内存", position = 2)
        private Double usedMemory;
        @ApiModelProperty(notes = "剩余内存", position = 3)
        private Double remainingMemory;
        @ApiModelProperty(notes = "使用率", position = 4)
        private Double usageRate;
    }


    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "JvmRamVO 对象", description = "3、Jvm--内存使用情况(MB)")
    public static class JvmRamVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        @ApiModelProperty(notes = "最大内存", position = 1)
        private Double maxMemory;
        @ApiModelProperty(notes = "总内存", position = 1)
        private Double totalMemory;
        @ApiModelProperty(notes = "已用内存", position = 2)
        private Double usedMemory;
        @ApiModelProperty(notes = "剩余内存", position = 3)
        private Double remainingMemory;
        @ApiModelProperty(notes = "使用率", position = 4)
        private Double usageRate;
    }


    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "ServerInformationVO", description = "4、系统--服务器信息")
    public static class ServerInformationVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        @ApiModelProperty(notes = "名称", position = 1)
        private String name;
        @ApiModelProperty(notes = "ip", position = 2)
        private String ip;
        @ApiModelProperty(notes = "操作系统", position = 3)
        private String operatingSystem;
        @ApiModelProperty(notes = "系统架构", position = 4)
        private String systemStructure;
    }


    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "JvmInformationVO 对象", description = "5、Jvm--程序信息")
    public static class JvmInformationVO implements Serializable {
        private static final long serialVersionUID = -2710971801815452828L;
        @ApiModelProperty(notes = "java名称", position = 1)
        private String javaName;
        @ApiModelProperty(notes = "java版本", position = 2)
        private String javaVersion;
        @ApiModelProperty(notes = "jdk路径", position = 3)
        private String jdkPath;
        @ApiModelProperty(notes = "项目路径", position = 4)
        private String projectPath;
        @ApiModelProperty(notes = "程序pid", position = 4)
        private String pid;
        @ApiModelProperty(notes = "启用时间", position = 4)
        private String startTime;
        @ApiModelProperty(notes = "运行时长(已运行秒数)", position = 4)
        private Long runningTime;
    }


    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "FileInfoVO", description = "6、系统--文件信息(GB)")
    public static class FileInfoVO implements Serializable {
        private static final long serialVersionUID = 4776903229464827233L;
        @ApiModelProperty(notes = "文件系统类型", position = 1)
        private String fileSysType;
//        @ApiModelProperty(notes = "文件类型", position = 2)
//        private String fileType;
        @ApiModelProperty(notes = "总大小", position = 3)
        private Double total;
        @ApiModelProperty(notes = "剩余空间", position = 4)
        private Double free;
        @ApiModelProperty(notes = "已使用空间", position = 5)
        private Double usable;
        @ApiModelProperty(notes = "usedRatio", position = 5)
        private Double usedRatio;
    }
}
