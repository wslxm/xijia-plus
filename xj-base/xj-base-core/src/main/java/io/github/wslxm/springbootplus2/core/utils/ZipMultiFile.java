package io.github.wslxm.springbootplus2.core.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 本地文件压缩
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/7/26 16:11
 */
public class ZipMultiFile {

	public static void main(String[] args) {
		// 压缩的文件源
		File[] srcFiles = {
				new File("F:\\tmp\\a.png"),
				new File("F:\\tmp\\b.txt"),
				new File("F:\\tmp\\c.docx")};
		// 存放压缩文件的临时文件
		File zipFile = new File("F:\\tmp\\ZipFile" + System.currentTimeMillis() + ".zip");
		// 调用压缩方法
		zipFiles(srcFiles, zipFile);
	}


	/*File path = null;
        try {
		path = new File(ResourceUtils.getURL("classpath:").getPath());
	} catch (
	FileNotFoundException e) {
		e.printStackTrace();
	}
        if (!path.exists()) {
		path = new File("");
	}
	//压缩包根目录
	File zipFile = new File(path.getAbsolutePath(), "static/uploaZip/");
        if (!zipFile.exists()) {
		zipFile.mkdirs();
	}
	File zipFile2 = new File(path.getAbsolutePath()+ "/static/uploaZip/"+ "aa.zip");

	// 调用压缩方法
        ZipMultiFile.zipFiles(srcFiles, zipFile2);
        System.out.println("====");*/


	public static void zipFiles(File[] srcFiles, File zipFile) {
		// 判断压缩后的文件存在不，不存在则创建
		if (!zipFile.exists()) {
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 创建 FileOutputStream 对象
		FileOutputStream fileOutputStream = null;
		// 创建 ZipOutputStream
		ZipOutputStream zipOutputStream = null;
		// 创建 FileInputStream 对象
		FileInputStream fileInputStream = null;

		try {
			// 实例化 FileOutputStream 对象
			fileOutputStream = new FileOutputStream(zipFile);
			// 实例化 ZipOutputStream 对象
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			// 创建 ZipEntry 对象
			ZipEntry zipEntry = null;
			// 遍历源文件数组
			for (int i = 0; i < srcFiles.length; i++) {
				// 将源文件数组中的当前文件读入 FileInputStream 流中
				fileInputStream = new FileInputStream(srcFiles[i]);
				// 实例化 ZipEntry 对象，源文件数组中的当前文件
				zipEntry = new ZipEntry(srcFiles[i].getName());
				zipOutputStream.putNextEntry(zipEntry);
				// 该变量记录每次真正读的字节个数
				int len;
				// 定义每次读取的字节数组
				byte[] buffer = new byte[1024];
				while ((len = fileInputStream.read(buffer)) > 0) {
					zipOutputStream.write(buffer, 0, len);
				}
			}
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileInputStream.close();
			fileOutputStream.close();
			System.out.println("压缩成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
