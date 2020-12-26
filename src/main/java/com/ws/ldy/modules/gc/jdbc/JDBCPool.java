package com.ws.ldy.modules.gc.jdbc;


import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.error.ErrorException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Jdbc数据源连接工具类
 *
 * @author wangsong
 * @date 2019年6月15日 下午1:27:50
 */
@Slf4j
public class JDBCPool {

    /**
     * 数据源
     *
     * @author wangsong
     * @date 2019年6月15日 下午1:27:25
     * @return
     */
    public static Connection getConn(String url, String username, String password) {
        Connection conn = null;
        try {
            String driverClass = "com.mysql.cj.jdbc.Driver";
            String newUrl = "jdbc:mysql://"+url + "?useUnicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=GMT%2B8";
            newUrl += "&autoReconnect=false"; // 当数据库连接异常中断时，是否自动重新连接，不重连
            String newUsername = username;
            String newPassword = password;
            // 注册驱动
            Class.forName(driverClass);
            conn = DriverManager.getConnection(newUrl, newUsername, newPassword);
        } catch (Exception e) {
            log.info("");
            //e.printStackTrace(); //CommunicationsException ip错误  | SQLNonTransientConnectionException 连接超时
            throw new ErrorException(RType.GENERATE_CODE_JDBC_ERROR);
        }
        return conn;
    }

    /**
     * 连接数据源 - 写入sql
     *
     * @author wangsong
     * @date 2019年6月15日 下午1:26:20
     * @param sql
     * @return
     */
    public static PreparedStatement getPstmt(String url, String username, String password, String sql) {
        Connection conn = getConn(url, username, password);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 添加修改后关闭数据连接
     *
     * @author wangsong
     * @date 2019年6月15日 下午1:26:36
     * @param ps
     */
    public static void closeUpdateRes(PreparedStatement ps) {
        if (ps != null) {
            try {
                Connection conn = ps.getConnection();
                ps.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 查询后关闭连接
     *
     * @author wangsong
     * @date 2019年6月15日 下午1:26:50
     * @param rs
     */
    public static void closeQueryRes(ResultSet rs) {
        if (rs != null) {
            Statement pstmt;
            try {
                pstmt = rs.getStatement();
                if (pstmt != null) {
                    Connection conn = pstmt.getConnection();
                    rs.close();
                    pstmt.close();
                    if (conn != null) {
                        conn.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

