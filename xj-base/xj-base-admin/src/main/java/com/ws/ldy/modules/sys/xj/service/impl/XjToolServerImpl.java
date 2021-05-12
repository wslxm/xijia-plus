package com.ws.ldy.modules.sys.xj.service.impl;

import cn.hutool.system.SystemUtil;
import com.sun.management.OperatingSystemMXBean;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.sys.xj.model.vo.XjToolJvmInfoVO;
import com.ws.ldy.modules.sys.xj.service.XjToolServer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// 参考： http://blog.csdn.net/zhongweijian/article/details/7619383
// 参考2： https://blog.csdn.net/coolchaobing/article/details/86736190
@Service
public class XjToolServerImpl implements XjToolServer {


    @Override
    public XjToolJvmInfoVO jvmInfo() {
        XjToolJvmInfoVO vo = new XjToolJvmInfoVO();
        vo.setCpu(getCpu());
        vo.setRam(getRam());
        vo.setJvmRam(getJvmRam());
        vo.setServerInformation(getServerInformation());
        vo.setJvmInformation(getJvmInformation());
        vo.setFileInfo(getFileInfo());
        return vo;
    }


    /**
     * 服务cpu 运行情况
     * @return
     */
    private XjToolJvmInfoVO.CpuVO getCpu() {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // cpu核心数
        int availableProcessors = os.getAvailableProcessors();
        // 系统cpu占用
        double systemUtilization = os.getSystemCpuLoad();
        // 用户cpu占用(当前程序)
        double userUtilization = os.getProcessCpuLoad();
        // cpu空闲率
        double usageRat = systemUtilization + userUtilization;
        // vo
        XjToolJvmInfoVO.CpuVO cpuVO = new XjToolJvmInfoVO.CpuVO();
        cpuVO.setNumberOfCores(availableProcessors);
        cpuVO.setSystemUtilization(new BigDecimal(systemUtilization * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        cpuVO.setUserUtilization(new BigDecimal(userUtilization * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        cpuVO.setUsageRate(new BigDecimal(usageRat * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return cpuVO;
    }


    /**
     * 服务器 内存运行情况
     * @return
     */
    @SuppressWarnings("all")
    private XjToolJvmInfoVO.RamVO getRam() {
        double kb = 1024 * 1024 * 1024;
        // 当前服务
        double maxMemory = Runtime.getRuntime().maxMemory() / kb;
        //
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        double totalMemory = (double) os.getTotalPhysicalMemorySize() / kb;     // 总内存
        double remainingMemory = (double) os.getFreePhysicalMemorySize() / kb;  // 剩于内存（需减去当前服务）
        double usedMemory = totalMemory - remainingMemory;                      // 已用内存(总内存-剩余内存)
        double usageRate = usedMemory / totalMemory;                            // 已使用比率 (已用内存/最大内存)
        // vo
        XjToolJvmInfoVO.RamVO ramVO = new XjToolJvmInfoVO.RamVO();
        ramVO.setTotalMemory(new BigDecimal(totalMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setUsedMemory(new BigDecimal(usedMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setRemainingMemory(new BigDecimal(remainingMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setUsageRate(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return ramVO;
    }


    /**
     * jvm 内存运行情况
     * @return
     */
    @SuppressWarnings("all")
    private XjToolJvmInfoVO.JvmRamVO getJvmRam() {
        int kb = 1024 * 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / kb;         // 最大内存(最大可挖取内存)
        long totalMemory = Runtime.getRuntime().totalMemory() / kb;     // 总内存(已挖到的内存)
        long freeMemory = Runtime.getRuntime().freeMemory() / kb;       // 剩余内存(已挖到的但没有使用的内存)
        long usedMemory = totalMemory - freeMemory;                     // 已用内存(总内存-剩余内存)
        long remainingMemory = maxMemory - usedMemory;                  // 总剩余内存(最大内存-已用内存)
        double usageRate = (double) usedMemory / (double) maxMemory;    // 已使用比率 (已用内存/最大内存)
        // vo remainingMemory
        XjToolJvmInfoVO.JvmRamVO jvmRamVO = new XjToolJvmInfoVO.JvmRamVO();
        jvmRamVO.setMaxMemory(new BigDecimal(maxMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setTotalMemory(new BigDecimal(totalMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setUsedMemory(new BigDecimal(usedMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setRemainingMemory(new BigDecimal(remainingMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setUsageRate(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return jvmRamVO;
    }


    /**
     * 获取 服务器相关信息
     * @return
     */
    private XjToolJvmInfoVO.ServerInformationVO getServerInformation() {
        XjToolJvmInfoVO.ServerInformationVO serverInformationVO = new XjToolJvmInfoVO.ServerInformationVO();
        serverInformationVO.setName(SystemUtil.getHostInfo().getName());  // 主机名
        serverInformationVO.setIp(SystemUtil.getHostInfo().getAddress());  // 主机ip
        serverInformationVO.setOperatingSystem(SystemUtil.getOsInfo().getName());  // 系统版本
        serverInformationVO.setSystemStructure(SystemUtil.getOsInfo().getArch());  // 系统架构
        return serverInformationVO;
    }


    /**
     * 获取 jvm/jdk 相关信息
     * @return
     */
    private XjToolJvmInfoVO.JvmInformationVO getJvmInformation() {
        XjToolJvmInfoVO.JvmInformationVO jvmInformationVO = new XjToolJvmInfoVO.JvmInformationVO();
        jvmInformationVO.setJavaName(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.vm.name"));
        jvmInformationVO.setJavaVersion(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.version"));
        jvmInformationVO.setJdkPath(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.home"));
        jvmInformationVO.setPid(SystemUtil.getRuntimeMXBean().getSystemProperties().get("PID"));
        jvmInformationVO.setProjectPath(SystemUtil.getUserInfo().getCurrentDir());
        LocalDateTime startTime = LocalDateTimeUtil.parseTimestamp(SystemUtil.getRuntimeMXBean().getStartTime());
        jvmInformationVO.setStartTime(LocalDateTimeUtil.parse(startTime));
        jvmInformationVO.setRunningTime(LocalDateTimeUtil.betweenTwoTime(startTime, LocalDateTimeUtil.now(), ChronoUnit.SECONDS));
        return jvmInformationVO;
    }


    /**
     * 获取磁盘数据信息
     * @author wangsong
     * @date 2021/5/12 0012 11:44
     * @return com.ws.ldy.modules.sys.xj.model.vo.XjToolJvmInfoVO.FileInfoVO
     * @version 1.0.0
     */
    private XjToolJvmInfoVO.FileInfoVO getFileInfo() {
        // 磁盘信息
        double kb = 1024 * 1024 * 1024;
        File[] files = File.listRoots();
        // 总空间
        double totalFile = 0;
        // 剩余空间
        double freeFile = 0;
        //double unFile = 0L;
        for (File file : files) {
            totalFile += (double) file.getTotalSpace() / kb;
            freeFile += (double) file.getFreeSpace() / kb;
            //  unFile += (double)file.getUsableSpace() / kb;
        }
        // 已使用空间
        double usableFile = totalFile - freeFile;
        // 已使用比率
        double usageRate = usableFile / totalFile;
        XjToolJvmInfoVO.FileInfoVO fileInfoVO = new XjToolJvmInfoVO.FileInfoVO();
        fileInfoVO.setFileSysType("\\");
        fileInfoVO.setTotal(new BigDecimal(totalFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setFree(new BigDecimal(freeFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setUsable(new BigDecimal(usableFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setUsedRatio(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return fileInfoVO;
    }
}
