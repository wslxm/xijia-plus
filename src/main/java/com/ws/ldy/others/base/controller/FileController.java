package com.ws.ldy.others.base.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.id.UUIDUtil;
import com.ws.ldy.config.error.ErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 通用文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "FileController", tags = "文件管理--文件上传到部署项目的服务器")
@RequestMapping("/file")
public class FileController extends BaseController {


    // 文件保存路径
    private static String FILE_PATH = "File/";
    // 文件路径验证
    private static String UPLOAD_PATH_IMAGE = "image";
    private static String UPLOAD_PATH_MUSIC = "music";
    private static String UPLOAD_PATH_VIDEO = "video";
    private static String UPLOAD_PATH_EXCEL = "excel";

    // 图片上传，需要赋值读写权限
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(image/ -图片, music/ -音乐,video/ -视频,excel/ -表格)", required = true)
    })
    public R uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {
        // 接口名
        String interfaceName = request.getServletPath();
        // 文件名
        String fileName = file.getOriginalFilename();
        // 完整url
        String url = request.getRequestURL().toString();
        // 去调接口后url
        String baseUrl = url.replace(interfaceName, "");
        // 验证文件格式及路径
        fileName = getPath(filePath, fileName);
        // 上传路径,判断,不存在创建
        Path directory = Paths.get(FILE_PATH + filePath);
        try {
            // 获得文件流
            InputStream inputStream = file.getInputStream();
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 判断文件是否存在,存在删除
            if (Files.exists(directory.resolve(fileName))) {
                File newFile = new File(FILE_PATH + filePath + fileName);
                newFile.delete();
            }
            // 拷贝流到上传的目录
            Files.copy(inputStream, directory.resolve(fileName));
            // 绝对路径
            String path = baseUrl + "/" + FILE_PATH + filePath + fileName;
            return R.success(path);
        } catch (Exception e) {
            return R.error(RType.SYS_ERROR_CODE_500);
        }
    }


    /**
     * 上传路径文件格式判断
     *
     * @param filePath 文件上传路径
     * @param fileName 文件名称
     * @return fileName
     */
    public String getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            throw new ErrorException(10002, "路径必须已[/]结尾");
        }
        // 目录开头
        String[] path = filePath.split("/");
        // 后缀名
        String suffixName = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        if (UPLOAD_PATH_IMAGE.equals(path[0])) {
            // 图片
            if (!"jpg".equals(suffixName) && !"png".equals(suffixName)) {
                throw new ErrorException(10002, "图片仅支持-[jpg,png]");
            }
            //修改fileName的引用
            fileName = UUIDUtil.creatUUID() + "-" + fileName;
            // filePath = filePath.replace(suffixName, "") + UUIDUtil.creatUUID() + "-";
        } else if (UPLOAD_PATH_MUSIC.equals(path[0])) {
            // 音乐
            if (!"mp3".equals(suffixName)) {
                throw new ErrorException(10002, "音乐仅支持-[mp3]");
            }
        } else if (UPLOAD_PATH_VIDEO.equals(path[0])) {
            // 视频
            if (!"mp4".equals(suffixName)) {
                throw new ErrorException(10002, "视频仅支持-[mp4]");
            }
        } else if (UPLOAD_PATH_EXCEL.equals(path[0])) {
            //excel
            if (!"xlsx".equals(suffixName) && !"xls".equals(suffixName)) {
                throw new ErrorException(10002, "EXCEL仅支持-[xlxs,xlx]");
            }
        } else {
            throw new ErrorException(10002, "路径错误");
        }
        return fileName;
    }


    /**
     * 使用流将图片输出
     *
     * @param filePage
     * @throws IOException
     */
//    public void getImage(String filePage) throws IOException {
//        response.setContentType("image/jpeg;charset=utf-8");
//        response.setHeader("Content-Disposition", "inline; filename=girls.png");
//        ServletOutputStream outputStream = response.getOutputStream();
//        // Paths.get(跟目录).resolve(文件路径)
//        outputStream.write(Files.readAllBytes(Paths.get("/").resolve(filePage)));
//        outputStream.flush();
//        outputStream.close();
//    }


    /**
     * 文件下载
     *
     * @param filePath 文件路径
     * @throws UnsupportedEncodingException
     */
    @ApiOperation("单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件路径(相对路径||绝对路径)，如: File/image/1.jpg", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void excelxiazan(String filePath) throws UnsupportedEncodingException {
        // 下载文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        //信息头: 会告诉浏览器这个文件的名字和类型（必须设置）
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //Content-Disposition : 指定的类型是文件的扩展名(也就是下载文件的名字)
        //下载名乱码解决  -->  java.net.URLEncoder.encode(fileXsl, "UTF-8")
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @ApiOperation("单文件删除")
    @RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
    @ApiImplicitParam(name = "filePath", value = "文件路径(相对路径||绝对路径), 如: File/image/1.jpg", required = true)
    public R deleteFile(String fileName) {
        java.io.File file = new java.io.File(fileName);
        // 判断目录或文件是否存在
        boolean result = file.exists();
        if (result) {
            // 判断是否为文件
            result = file.isFile();
            if (result) {
                //删除文件
                file.delete();
            }
        }
        return R.success();
    }
}

