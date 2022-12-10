package io.github.wslxm.springbootplus2.manage.sys.model.vo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * jvm 信息类
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/3/14 0014 11:42
 */
@Data
@ToString(callSuper = true)
public class ToolJvmInfoVO implements Serializable {

    private static final long serialVersionUID = 4827571724805823332L;

    /**
     * cpu使用信息
     */
    private CpuVO cpu;
    /**
     * 系统内存所有情况
     */
    private RamVO ram;
    /**
     * jvm内存使用情况
     */
    private JvmRamVO jvmRam;
    /**
     * 服务器信息
     */
    private ServerInformationVO serverInformation;
    /**
     * JVM 程序信息
     */
    private JvmInformationVO jvmInformation;
    /**
     * 文件信息
     */
    private FileInfoVO fileInfo;


    @Data
    @ToString(callSuper = true)
    public static class CpuVO implements Serializable {
        private static final long serialVersionUID = -6539830698200732287L;
        /**
         * 核心数
         */
        private Integer numberOfCores;
        /**
         * 用户使用率
         */
        private Double userUtilization;
        /**
         * 系统使用率
         */
        private Double systemUtilization;
        /**
         * cpu使用率
         */
        private Double usageRate;
    }

    @Data
    @ToString(callSuper = true)
    public static class RamVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        /**
         * 总内存
         */
        private Double totalMemory;
        /**
         * 已用内存
         */
        private Double usedMemory;
        /**
         * 剩余内存
         */
        private Double remainingMemory;
        /**
         * 使用率
         */
        private Double usageRate;
    }


    @Data
    @ToString(callSuper = true)
    public static class JvmRamVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        /**
         * 最大内存
         */
        private Double maxMemory;
        /**
         * 总内存
         */
        private Double totalMemory;
        /**
         * 已用内存
         */
        private Double usedMemory;
        /**
         * 剩余内存
         */
        private Double remainingMemory;
        /**
         * 使用率
         */
        private Double usageRate;
    }


    @Data
    @ToString(callSuper = true)
    public static class ServerInformationVO implements Serializable {
        private static final long serialVersionUID = 2229572121900780687L;
        /**
         * 名称
         */
        private String name;
        /**
         * ip
         */
        private String ip;
        /**
         * 操作系统
         */
        private String operatingSystem;
        /**
         * 系统架构
         */
        private String systemStructure;
    }


    @Data
    @ToString(callSuper = true)
    public static class JvmInformationVO implements Serializable {
        private static final long serialVersionUID = -2710971801815452828L;
        /**
         * java名称
         */
        private String javaName;
        /**
         * java版本
         */
        private String javaVersion;
        /**
         * jdk路径
         */
        private String jdkPath;
        /**
         * 项目路径
         */
        private String projectPath;
        /**
         * 程序pid
         */
        private String pid;
        /**
         * 启用时间
         */
        private String startTime;
        /**
         * 运行时长(已运行秒数)
         */
        private Long runningTime;
    }


    /**
     * 系统--文件信息(GB)
     */
    @Data
    @ToString(callSuper = true)
    public static class FileInfoVO implements Serializable {
        private static final long serialVersionUID = 4776903229464827233L;
        /**
         * 文件系统类型
         */
        private String fileSysType;
        /**
         * 文件类型
         */
        private String fileType;
        /**
         * 总大小
         */
        private Double total;
        /**
         * 剩余空间
         */
        private Double free;
        /**
         * 已使用空间
         */
        private Double usable;
        /**
         * usedRatio
         */
        private Double usedRatio;
    }
}
