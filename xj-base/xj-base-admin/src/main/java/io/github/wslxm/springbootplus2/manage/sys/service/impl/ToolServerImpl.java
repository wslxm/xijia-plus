package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.system.SystemUtil;
import com.sun.management.OperatingSystemMXBean;
import io.github.wslxm.springbootplus2.core.utils.date.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ToolJvmInfoVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ToolServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *  @author wangsong
 */
@Service
@Slf4j
public class ToolServerImpl implements ToolServer {


    @Override
    public ToolJvmInfoVO jvmInfo() {
        ToolJvmInfoVO vo = new ToolJvmInfoVO();
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
    private ToolJvmInfoVO.CpuVO getCpu() {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // cpu核心数
        int availableProcessors = os.getAvailableProcessors();
        // 系统cpu占用
        double systemUtilization = os.getSystemCpuLoad();
        // 用户cpu占用(当前程序)
        double userUtilization = os.getProcessCpuLoad();
        // cpu总使用率
        double usageRat = systemUtilization + userUtilization;
        // vo
        ToolJvmInfoVO.CpuVO cpuVO = new ToolJvmInfoVO.CpuVO();
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
    private ToolJvmInfoVO.RamVO getRam() {
        double gb = 1024 * 1024 * 1024;
        // linux 获取服务器内存信息目录
        double totalMemory = 0;
        double remainingMemory = 0;
        double usedMemory = 0;
        double usageRate = 0;
        String property = System.getProperty("os.name");
        // if ("Linux".equals(property)) {
        //     // System.out.println("进来了：");
        //     // linux 使用命令获取，如果此次部分linux 系统无法获取，请使用其他方法
        //     //String newCmd = "free -h";
        //     String newCmd = "free -m";
        //     String arr = null;
        //     try {
        //         Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", newCmd});
        //         //Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", newCmd});
        //         arr = loadStream(process.getInputStream());
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        //     log.info("ram：\r\n" + arr);
        //     String[] rams = arr.split("\n");
        //     String ramsValue = rams[1];
        //     ramsValue = ramsValue.replace("Mem:", "");
        //     // 获取后的参数对应索引(单位，GB) 0=total  1=used   2=free  3=shared  4=buff/cache   5=available
        //     List<Double> ramVals = new ArrayList<>();
        //     for (int i = 0; i < ramsValue.length(); i++) {
        //         if (ramsValue.charAt(i) != ' ') {
        //             String val = "";
        //             for (int j = i; j < ramsValue.length(); j++) {
        //                 if (ramsValue.charAt(i) == ' ') {
        //                     break;
        //                 } else {
        //                     val += ramsValue.charAt(i);
        //                     i++;
        //                 }
        //             }
        //             // System.out.println("val=" + val);
        //             // Mi表示（1Mi=1024x1024）,M表示（1M=1000x1000）（其它单位类推， 如Ki/K Gi/G）
        //             //  double valDouble = 0;
        //             //  if (val.indexOf("Gi") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("Gi", ""));
        //             //  } else if (val.indexOf("G") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("G", ""));
        //             //  } else if (val.indexOf("Mi") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("Mi", "")) / 1024;
        //             //  } else if (val.indexOf("M") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("M", "")) / 1000;
        //             //  } else if (val.indexOf("Ki") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("Ki", "")) / 1024 / 1024;
        //             //  } else if (val.indexOf("K") != -1) {
        //             //      valDouble = Double.parseDouble(val.replace("K", "")) / 1000 / 1000;
        //             //  }
        //             double valDouble = Double.parseDouble(val) / 1000;
        //             //System.out.println("valDouble=" + valDouble);
        //             ramVals.add(valDouble);
        //         }
        //     }
        //     //System.out.println("参数：" + JSON.toJSONString(ramVals));
        //     totalMemory = ramVals.get(0);                // 总内存
        //     remainingMemory = ramVals.get(5);            // 剩于内存
        //     usedMemory = totalMemory - remainingMemory;  // 已用内存(总内存-剩余内存)
        //     usageRate = usedMemory / totalMemory;        // 已使用比率 (已用内存/最大内存)
        //
        // } else {
        // 非linux 使用api获取
        double maxMemory = Runtime.getRuntime().maxMemory() / gb;
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        //  available
        totalMemory = (double) os.getTotalPhysicalMemorySize() / gb;     // 总内存
        remainingMemory = (double) os.getFreePhysicalMemorySize() / gb;  // 剩于内存（需减去当前服务）
        usedMemory = totalMemory - remainingMemory;                      // 已用内存(总内存-剩余内存)
        usageRate = usedMemory / totalMemory;                            // 已使用比率 (已用内存/最大内存)
        //     }
        // vo
        ToolJvmInfoVO.RamVO ramVO = new ToolJvmInfoVO.RamVO();
        ramVO.setTotalMemory(new BigDecimal(totalMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setUsedMemory(new BigDecimal(usedMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setRemainingMemory(new BigDecimal(remainingMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        ramVO.setUsageRate(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return ramVO;
    }


    public static String loadStream(InputStream in) throws IOException {
        int ptr = 0;
        in = new BufferedInputStream(in);
        StringBuilder buffer = new StringBuilder();
        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }
        return buffer.toString();
    }


    /**
     * jvm 内存运行情况
     * @return
     */
    @SuppressWarnings("all")
    private ToolJvmInfoVO.JvmRamVO getJvmRam() {
        int kb = 1024 * 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / kb;         // 最大内存(最大可挖取内存)
        long totalMemory = Runtime.getRuntime().totalMemory() / kb;     // 总内存(已挖到的内存)
        long freeMemory = Runtime.getRuntime().freeMemory() / kb;       // 剩余内存(已挖到的但没有使用的内存)
        long usedMemory = totalMemory - freeMemory;                     // 已用内存(总内存-剩余内存)
        long remainingMemory = maxMemory - usedMemory;                  // 总剩余内存(最大内存-已用内存)
        double usageRate = (double) usedMemory / (double) maxMemory;    // 已使用比率 (已用内存/最大内存)
        // vo remainingMemory
        ToolJvmInfoVO.JvmRamVO jvmRamVO = new ToolJvmInfoVO.JvmRamVO();
        jvmRamVO.setMaxMemory(new BigDecimal(maxMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setTotalMemory(new BigDecimal(totalMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setUsedMemory(new BigDecimal(usedMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setRemainingMemory(new BigDecimal(remainingMemory).setScale(2, RoundingMode.HALF_UP).doubleValue());
        jvmRamVO.setUsageRate(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return jvmRamVO;
    }


    /**
     * 获取 服务器相关信息
     * @return ToolJvmInfoVO.ServerInformationVO
     */
    private ToolJvmInfoVO.ServerInformationVO getServerInformation() {
        ToolJvmInfoVO.ServerInformationVO serverInformationVO = new ToolJvmInfoVO.ServerInformationVO();
        // 主机名
        serverInformationVO.setName(SystemUtil.getHostInfo().getName());
        // 主机ip
        serverInformationVO.setIp(SystemUtil.getHostInfo().getAddress());
        // 系统版本
        serverInformationVO.setOperatingSystem(SystemUtil.getOsInfo().getName());
        // 系统架构
        serverInformationVO.setSystemStructure(SystemUtil.getOsInfo().getArch());
        return serverInformationVO;
    }


    /**
     * 获取 jvm/jdk 相关信息
     * @return ToolJvmInfoVO.JvmInformationVO
     */
    private ToolJvmInfoVO.JvmInformationVO getJvmInformation() {
        ToolJvmInfoVO.JvmInformationVO jvmInformationVO = new ToolJvmInfoVO.JvmInformationVO();
        jvmInformationVO.setJavaName(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.vm.name"));
        jvmInformationVO.setJavaVersion(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.version"));
        jvmInformationVO.setJdkPath(SystemUtil.getRuntimeMXBean().getSystemProperties().get("java.home"));
        jvmInformationVO.setPid(SystemUtil.getRuntimeMXBean().getSystemProperties().get("PID"));
        jvmInformationVO.setProjectPath(SystemUtil.getUserInfo().getCurrentDir());
        LocalDateTime startTime = LocalDateTimeUtil.parseTimestamp(SystemUtil.getRuntimeMXBean().getStartTime());
        jvmInformationVO.setStartTime(LocalDateTimeUtil.parse(startTime));
        jvmInformationVO.setRunningTime(LocalDateTimeUtil.betweenTwoTime(startTime, LocalDateTime.now(), ChronoUnit.SECONDS));
        return jvmInformationVO;
    }


    /**
     * 获取磁盘数据信息
     * @author wangsong
     * @date 2021/5/12 0012 11:44
     * @return io.github.wslxm.modules.sys.xj.model.vo.ToolJvmInfoVO.FileInfoVO
     * @version 1.0.1
     */
    private ToolJvmInfoVO.FileInfoVO getFileInfo() {
        // 磁盘信息
        double kb = 1024 * 1024 * 1024;
        File[] files = File.listRoots();
        // 总空间
        double totalFile = 0;
        // 剩余空间
        double freeFile = 0;
        // double unFile = 0L;
        for (File file : files) {
            totalFile += (double) file.getTotalSpace() / kb;
            freeFile += (double) file.getFreeSpace() / kb;
            //  unFile += (double)file.getUsableSpace() / kb;
        }
        // 已使用空间
        double usableFile = totalFile - freeFile;
        // 已使用比率
        double usageRate = 0;
        if(totalFile!=0){
            usageRate =  usableFile / totalFile;
        }
        ToolJvmInfoVO.FileInfoVO fileInfoVO = new ToolJvmInfoVO.FileInfoVO();
        fileInfoVO.setFileSysType("\\");
        fileInfoVO.setTotal(new BigDecimal(totalFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setFree(new BigDecimal(freeFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setUsable(new BigDecimal(usableFile).setScale(2, RoundingMode.HALF_UP).doubleValue());
        fileInfoVO.setUsedRatio(new BigDecimal(usageRate * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return fileInfoVO;
    }
}
