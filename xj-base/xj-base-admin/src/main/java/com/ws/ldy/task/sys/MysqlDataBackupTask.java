//package com.ws.ldy.task.sys;
//
//import com.ws.ldy.common.utils.LocalDateTimeUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.LinkOption;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.attribute.BasicFileAttributeView;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//
///**
// * MYSQL 数据自动备份
// * <P>
// *  请参考文章： https://blog.csdn.net/qq_41463655/article/details/112628365
// *  注意：
// *    win 的cmd 执行需当前 pc需安装 mysql
// *    服务器需安装mysql 或 单独安装 mysqldump, 单独安装命令(8-版本)： yum -y install holland-mysqldump.noarch
// * </P>
// * @author wangsong
// * @date 2021/1/14 0014 19:12
// * @return
// * @version 1.0.0
// */
//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class MysqlDataBackupTask {
//
//
//    /**
//     * 备份 sql 存放目录(相对路径, 注意可能需要在 MvcConfig 配置访问权限)
//     */
//    String filePath = "File/sql/";
//
//    /**
//     * 备份sql保留天(自动删除历史备份文件,防止服务器资源占用)
//     */
//    int retentionDays = 3;
//
//    /**
//     * mysqldump 安装版本是否为 8.0 + （false=否  true=是）， 8.0+ 需要参数  --column-statistics=0  ， mysql8- 不需要
//     * 查询当前mysql版本sql语句：select version();
//     */
//    boolean isDbVersion8 = false;
//
//    /**
//     * 备份命令
//     * USERNAME   账号
//     * PASSWORD   密码
//     * SERVERPATH 服务器IP/域名
//     * DBNAME     数据库名称
//     * FILEPATH   备份文件存放地址+名称
//     * 说明:
//     * --column-statistics=0     mysql8 添加该参数, 非mysql8 不添加, 否则将出错
//     */
//    String cmdMysql8 = "mysqldump --column-statistics=0  -u{USERNAME} -p{PASSWORD} -h{SERVERPATH} -P3306 --databases {DBNAME}";
//    String cmd = "mysqldump  -u{USERNAME} -p{PASSWORD} -h{SERVERPATH} -P3306 --databases {DBNAME}";
//
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dbUserName;
//
//    @Value("${spring.datasource.password}")
//    private String dbPassWord;
//
//
//    /**
//     * 每天凌晨4点 【  0 0 4 1/1 * ? 】
//     * 测试 20 秒一次【  0/20 * * * * ? 】
//     */
//    @Scheduled(cron = "0 0 4 1/1 * ?")
//    private void configureTasks() {
//        log.info("【备份数据库】--START");
//        // 1、删除历史备份
//        this.delBackupFile();
//
//        // 2、获取数据连接信息 -> 数据库名称+地址+账号+密码
//        String dbUrl2 = dbUrl.replace("jdbc:mysql://", "");
//        String dbName = dbUrl2.substring(dbUrl2.lastIndexOf('/') + 1, dbUrl2.indexOf('?'));
//        String serverPath = dbUrl2.substring(0, dbUrl2.lastIndexOf('/'));
//        String username = dbUserName;
//        String password = dbPassWord;
//
//        // 3、拼接备份文件目录+名称(备份文件存放目录+名称(名称 = 数据库名+时间字符串.sql)
//        String timeStr = LocalDateTimeUtil.parse(LocalDateTimeUtil.now())
//                .replaceAll("-", "_")
//                .replaceAll(" ", "_")
//                .replaceAll(":", "");
//        timeStr = timeStr.substring(0, 15);
//        String pathFileName = filePath + dbName + "_" + timeStr + ".sql";
//        String newCmd = "";
//        if (isDbVersion8) {
//            newCmd = cmdMysql8;
//        } else {
//            newCmd = cmd;
//        }
//        // 4、获取执行命令
//        newCmd = newCmd.replace("{USERNAME}", username)
//                .replace("{PASSWORD}", password)
//                .replace("{SERVERPATH}", serverPath)
//                .replace("{DBNAME}", dbName)
//                .replace("{FILEPATH}", pathFileName);
//        log.info("当前执行命令: {}", newCmd);
//        // 5、开始备份
//        BufferedReader bufferedReader = null;
//        // 创建存放sql的文件
//        existsFile(new File(pathFileName));
//        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathFileName), "utf8"));) {
//            Process process = null;
//            String property = System.getProperty("os.name");
//            log.info("当前系统: {}", property);
//            if ("Linux".equals(property)) {
//                // linux
//                process = Runtime.getRuntime().exec(new String[]{"bash", "-c", newCmd});
//            } else {
//                // win
//                process = Runtime.getRuntime().exec(newCmd);
//            }
//            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
//            bufferedReader = new BufferedReader(inputStreamReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                printWriter.println(line);
//            }
//            // 此除会执行过长时间,直到备份完成
//            printWriter.flush();
//            printWriter.close();
//            //0 表示线程正常终止
//            if (process.waitFor() == 0) {
//                log.info("【备份数据库】SUCCESS，SQL文件：{}", pathFileName);
//            }
//        } catch (Exception e) {
//            log.debug(e.toString());
//            log.info("【备份数据库】FAILURE");
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            } catch (IOException e) {
//                log.debug(e.toString());
//            }
//        }
//        log.info("【备份数据库】--END");
//    }
//
//
//    /**
//     * 判断文件是否存在，不存在创建
//     */
//    private static boolean existsFile(File file) {
//        // 判断文件路径是否存在,不存在新建
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        if (!file.exists()) {
//            try {
//                if (file.createNewFile()) {
//                    log.info("创建文件: {} 成功", file.getName());
//                    return true;
//                } else {
//                    log.info("创建文件: {} 失败", file.getName());
//                }
//            } catch (IOException e) {
//                log.debug(e.toString());
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 删除n天前的备份数据
//     */
//    private void delBackupFile() {
//        LocalDateTime startTime = LocalDateTimeUtil.parse("2021-01-01 00:00:00");
//        LocalDateTime endTime = LocalDateTimeUtil.subtract(LocalDateTime.now(), retentionDays, ChronoUnit.DAYS);
//        this.delete(filePath, LocalDateTimeUtil.parseDate(startTime), LocalDateTimeUtil.parseDate(endTime));
//    }
//
//
//    /**
//     * 删除指定的创建时间范围内的所有文件
//     * @author wangsong
//     * @param dirs   文件夹目录
//     * @param date1  开始时间
//     * @param date2  结束时间
//     * @date 2021/1/25 0025 17:22
//     * @return void
//     * @version 1.0.0
//     */
//    private void delete(String dirs, Date date1, Date date2) {
//        File dir = new File(dirs);
//        File[] list = dir.listFiles();
//        if (list == null || list.length == 0) {
//            return;
//        }
//        for (File file : list) {
//            if (file.isDirectory()) {
//                if (file.list().length > 0) {
//                    delete(file.getAbsolutePath(), date1, date2);
//                }
//            } else {
//                Path p = Paths.get(file.getPath());
//                //通过文件的属性来获取文件的创建时间
//                BasicFileAttributeView basicview = Files.getFileAttributeView(p, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
//                BasicFileAttributes attr;
//                try {
//                    attr = basicview.readAttributes();
//                    Date createTimeDate = new Date(attr.creationTime().toMillis());
//                    //这部分为文件的最后修改时间
//                    if (date1.getTime() <= createTimeDate.getTime() && createTimeDate.getTime() <= date2.getTime()) {
//                        if (file.delete()) {
//                            log.info("删除备份sql文件: {} 成功", file.getName());
//                        } else {
//                            log.info("删除备份sql文件: {} 失败", file.getName());
//                        }
//                    }
//                } catch (IOException e) {
//                    log.debug(e.toString());
//                }
//            }
//        }
//    }
//}
//
//
