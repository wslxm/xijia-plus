package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.others.generatecode.model.entity.XjDatasource;
import com.ws.ldy.others.generatecode.service.XjDatasourceService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import com.ws.ldy.others.generatecode.jdbc.JDBCPool;
import com.ws.ldy.others.generatecode.mapper.DataBaseMapper;
import com.ws.ldy.others.generatecode.model.vo.TableFieldVO;
import com.ws.ldy.others.generatecode.model.vo.TableVO;
import com.ws.ldy.others.generatecode.service.DataBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *    DataBase数据库操作，这里继承 BaseAdminServiceImpl 只是为了使用 mapper.dataBaseDao
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:01
 */
@Service
public class DataBaseServiceImpl extends BaseIServiceImpl implements DataBaseService {

    @Value("${spring.datasource.dynamic.datasource.db1.url}")
    private String dbUrl;

    @Value("${spring.datasource.dynamic.datasource.db1.username}")
    private String dbUserName;

    @Value("${spring.datasource.dynamic.datasource.db1.password}")
    private String dbPassWord;

    @Autowired
    private DataBaseMapper dataBaseMapper;

    @Autowired
    private XjDatasourceService xjDatasourceService;

    @Override
    public List<TableVO> findTable(String dataSourceId) {
        return this.findJdbcTable(dataSourceId);
        // return dataBaseMapper.findTable(getDbName());
    }

    /**
     *   查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    @Override
    public List<TableFieldVO> findTableField(String table, String dataSourceId) {
        return this.findJdbcTableField(table, dataSourceId);
        // return dataBaseMapper.findTableField(getDbName());
    }


    /**
     * 使用jdbc 查询使用数据表
     * @author wangsong
     * @date 2020/11/4 0004 17:55
     * @return java.util.List<com.ws.ldy.others.generatecode.model.vo.TableVO>
     * @version 1.0.0
     */
    private List<TableVO> findJdbcTable(String dataSourceId) {
        XjDatasource datasource = xjDatasourceService.getById(dataSourceId);
        // 1、判断使用默认数据源还是动态数据源来获取数据库名称
        String dbName = "";
        if (StringUtils.isBlank(dataSourceId)) {
            dbUrl = dbUrl.replace("jdbc:mysql://", "");
            dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.indexOf("?"));
        } else {
            // 使用动态选择的数据源
            dbName = datasource.getDbName();
        }
        // 2、拼接 sql
        String sql = "SELECT TABLE_NAME ,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + dbName + "'";
        // 3、判断使用默认数据源还是动态数据源来执行sql
        PreparedStatement pstmt = null;
        if (StringUtils.isBlank(dataSourceId)) {
            pstmt = JDBCPool.getPstmt(dbUrl.substring(0, dbUrl.indexOf("?")), dbUserName, dbPassWord, sql);
        } else {
            pstmt = JDBCPool.getPstmt(datasource.getDbUrl(), datasource.getDbUsername(), datasource.getDbPassword(), sql);
        }
        // 4、处理返回sql
        ResultSet rs = null;
        List<TableVO> vos = new ArrayList<>();
        try {
            // pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //游标向下移动
            while (rs.next()) {
                //获得查询出来的数据
                TableVO vo = new TableVO();
                vo.setName(rs.getString("TABLE_NAME"));
                vo.setComment(rs.getString("TABLE_COMMENT"));
                vos.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCPool.closeQueryRes(rs);
        }
        return vos;
    }


    /**
     *  使用jdbc  查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    private List<TableFieldVO> findJdbcTableField(String table, String dataSourceId) {
        XjDatasource datasource = xjDatasourceService.getById(dataSourceId);
        // 1、判断使用默认数据源还是动态数据源来获取数据库名称
        String dbName = "";
        if (StringUtils.isBlank(dataSourceId)) {
            dbUrl = dbUrl.replace("jdbc:mysql://", "");
            dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.indexOf("?"));
        } else {
            // 使用动态选择的数据源
            dbName = datasource.getDbName();
        }
        // 2、拼接sql
        String sql = "select " +
                " column_name name," +
                " data_type type," +
                " column_comment `desc`," +
                " column_type typeDetail," +
                " is_nullable as isNull," +
                " column_default as defaultVal " +
                " from  information_schema.columns " +
                " where " +
                " table_name = '" + table + "'" +
                " and table_schema= '" + dbName + "'" +
                " order by ordinal_position asc";
        // 3、判断使用默认数据源还是动态数据源来执行sql
        PreparedStatement pstmt = null;
        if (StringUtils.isBlank(dataSourceId)) {
            pstmt = JDBCPool.getPstmt(dbUrl.substring(0, dbUrl.indexOf("?")), dbUserName, dbPassWord, sql);
        } else {
            pstmt = JDBCPool.getPstmt(datasource.getDbUrl(), datasource.getDbUsername(), datasource.getDbPassword(), sql);
        }
        ResultSet rs = null;
        List<TableFieldVO> vos = new ArrayList<>();
        try {
            rs = pstmt.executeQuery();
            //游标向下移动
            while (rs.next()) {
                // 获得查询出来的数据
                TableFieldVO vo = new TableFieldVO();
                vo.setName(rs.getString("name"));
                vo.setType(rs.getString("type"));
                vo.setDesc(rs.getString("desc"));
                vo.setTypeDetail(rs.getString("typeDetail"));
                vo.setIsNull(rs.getString("isNull"));
                vos.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCPool.closeQueryRes(rs);
        }
        //
        if (datasource != null && StringUtils.isNotBlank(datasource.getDbGeneralField())) {
            // 使用数据源配置的通用字段 (当使用了其他数据源和配置了通用字段时使用)
            List<String> fields = Arrays.asList( datasource.getDbGeneralField().split(","));
            for (TableFieldVO tableFieldVO : vos) {
                // 判断是否为通用字段
                if (fields.contains(tableFieldVO.getName())) {
                    tableFieldVO.setIsChecked(false);
                } else {
                    tableFieldVO.setIsChecked(true);
                }
                // 判断空串
                if ("CURRend_timeSTAMP".equals(tableFieldVO.getDefaultVal())) {
                    tableFieldVO.setDefaultVal("当前时间");
                }
            }
        } else {
            // 使用默认通用字段
            for (TableFieldVO tableFieldVO : vos) {
                // 判断是否为通用字段
                if (GenerateConfig.BASE_FIELDS.contains(tableFieldVO.getName())) {
                    tableFieldVO.setIsChecked(false);
                } else {
                    tableFieldVO.setIsChecked(true);
                }
                // 判断空串
                if ("CURRend_timeSTAMP".equals(tableFieldVO.getDefaultVal())) {
                    tableFieldVO.setDefaultVal("当前时间");
                }
            }
        }
        return vos;
    }
}
