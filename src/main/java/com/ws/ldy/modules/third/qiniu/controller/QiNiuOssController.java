package com.ws.ldy.modules.third.qiniu.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.FileUploadUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.third.qiniu.model.vo.TokenAndUrlVO;
import com.ws.ldy.modules.third.qiniu.util.QiNiuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(value = "QiNiuController", tags = "QiNiu  -->  OSS文件管理(七牛云)", consumes = BaseConstant.InterfaceType.PC_BASE)
@RequestMapping(BaseConstant.Sys.API + "/qiNiuOss")
public class QiNiuOssController extends BaseController {


    @Autowired
    private QiNiuUtils qiNiuUtils;

    @RequestMapping(value = "/findTokenAndUrl", method = RequestMethod.GET) //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("前端直传--获取七牛云上传Token和上传Url")
    public R<TokenAndUrlVO> findToken() {
        TokenAndUrlVO token = qiNiuUtils.getUpToken();
        return R.success(token);
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST) //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("后端上传--OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录,当为任意文件时,后台不验证文件格式(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "PDF=pdf/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true, example = "image/")
    })
    public R<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {
        // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
        String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
        try {
            // 上传
            String url = qiNiuUtils.uploadStream(file.getInputStream(), fileName);
            return R.success(url);
        } catch (Exception e) {
            return R.error(RType.FILE_UPLOAD_FAILED);
        }
    }
}
