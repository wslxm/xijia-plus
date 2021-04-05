package com.ws.ldy.modules.sys.xj.controller;

import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.dto.XjJavaCodeDTO;
import com.ws.ldy.modules.sys.xj.model.vo.XjToolJvmInfoVO;
import com.ws.ldy.modules.sys.xj.util.JavaCodeRunV2Util;
import com.ws.ldy.modules.sys.xj.util.TransformUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/tool/")
@Api(value = "XjToolController", tags = "base-plus--系统小工具")
public class XjToolController {


    @ApiOperation("1、符号转文字工具")
    @RequestMapping(value = "/fhConvert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public R<String> fhConvert(@RequestParam String content, @RequestParam Integer narrow, @RequestParam String fill) {
        String transform = null;
        if (StringUtils.isNotBlank(content)) {
            transform = TransformUtil.transform(content, narrow, fill);
        }
        return R.success(transform);
    }


    @RequestMapping(value = "/javaCodeRun", method = RequestMethod.POST)
    @ApiOperation(value = "2、java代码运行器", notes = "")
    public R<String> upd(@RequestBody XjJavaCodeDTO dto) {
        return R.success(JavaCodeRunV2Util.run(dto.getJavaCode()));
    }


    @SneakyThrows
    @RequestMapping(value = "/jvmInfo", method = RequestMethod.GET)
    @ApiOperation(value = "3、系统的jvm信息", notes = "")
    public R<XjToolJvmInfoVO> jvmInfo() {
        XjToolJvmInfoVO vo = new XjToolJvmInfoVO();
        vo.setJvmSpecInfo(SystemUtil.getJvmSpecInfo());
        vo.setJvmInfo(SystemUtil.getJvmInfo());
        vo.setJavaSpecInfo(SystemUtil.getJavaSpecInfo());
        vo.setJavaInfo(SystemUtil.getJavaInfo());
        //vo.setJavaRuntimeInfo(SystemUtil.getJavaRuntimeInfo());
        vo.setOsInfo(SystemUtil.getOsInfo());
        vo.setUserInfo(SystemUtil.getUserInfo());
        vo.setHostInfo(SystemUtil.getHostInfo());
        vo.setOperatingSystemMXBean(SystemUtil.getOperatingSystemMXBean());
        vo.setRuntimeMXBean(SystemUtil.getRuntimeMXBean());
        // jvm 运行时信息
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        XjToolJvmInfoVO.RuntimeInfoVO runtimeInfoVO = new XjToolJvmInfoVO.RuntimeInfoVO();
        //runtimeInfoVO.setRuntime(runtimeInfo.getRuntime());
        runtimeInfoVO.setMaxMemory(runtimeInfo.getMaxMemory());
        runtimeInfoVO.setTotalMemory(runtimeInfo.getTotalMemory());
        runtimeInfoVO.setFreeMemory(runtimeInfo.getFreeMemory());
        runtimeInfoVO.setUsableMemory(runtimeInfo.getUsableMemory());
        runtimeInfoVO.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        vo.setRuntimeInfo(runtimeInfoVO);
        // 磁盘信息
        File[] files = File.listRoots();
        Long totalFile = 0L;
        Long freeFile = 0L;
        Long unFile = 0L;
        for (File file : files) {
            totalFile += file.getTotalSpace();
            freeFile += file.getFreeSpace();
            unFile += file.getUsableSpace();
        }
        XjToolJvmInfoVO.FileInfoVO fileInfoVO = new XjToolJvmInfoVO.FileInfoVO();
        fileInfoVO.setTotal(totalFile);
        fileInfoVO.setFree(freeFile);
        fileInfoVO.setUsable(unFile);
        vo.setFileInfoVO(fileInfoVO);
        return R.success(vo);
    }
}
