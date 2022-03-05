package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.mapper.XjDataBaseMapper;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjTableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjTableVO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjDataBaseService;
import io.github.wslxm.springbootplus2.manage.gc.util.JdbcPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataBase数据库操作，这里继承 BaseAdminServiceImpl 只是为了使用 mapper.dataBaseDao
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:01
 */
@Service
@Slf4j
public class XjDataBaseServiceImpl extends BaseIServiceImpl implements XjDataBaseService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassWord;

    @Autowired
    private XjDataBaseMapper dataBaseMapper;

    @Autowired
    private XjAdminDatasourceService adminDatasourceService;

    @Override
    public List<XjTableVO> findTable(String dataSourceId) {
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
    public List<XjTableFieldVO> findTableField(GcConfig gcConfig, String table, String dataSourceId) {
        return this.findJdbcTableField(gcConfig,table, dataSourceId);
        // return dataBaseMapper.findTableField(getDbName());
    }


    /**
     * 使用jdbc 查询使用数据表
     * @author wangsong
     * @date 2020/11/4 0004 17:55
     * @return java.util.List<io.github.wslxm.others.generatecode.model.vo.XjTableVO>
     * @version 1.0.1
     */
    private List<XjTableVO> findJdbcTable(String dataSourceId) {
        XjAdminDatasource datasource = adminDatasourceService.getById(dataSourceId);
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
            pstmt = JdbcPool.getPstmt(dbUrl.substring(0, dbUrl.indexOf("?")), dbUserName, dbPassWord, sql);
        } else {
            pstmt = JdbcPool.getPstmt(datasource.getDbUrl(), datasource.getDbUsername(), datasource.getDbPassword(), sql);
        }
        // 4、处理返回sql
        ResultSet rs = null;
        List<XjTableVO> vos = new ArrayList<>();
        try {
            rs = pstmt.executeQuery();
            //游标向下移动
            while (rs.next()) {
                //获得查询出来的数据
                XjTableVO vo = new XjTableVO();
                vo.setName(rs.getString("TABLE_NAME"));
                vo.setComment(rs.getString("TABLE_COMMENT"));
                vos.add(vo);
            }
        } catch (SQLException e) {
            log.debug(e.toString());
        } finally {
            JdbcPool.closeQueryRes(rs);
        }
        return vos;
    }


    /**
     *  使用jdbc  查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    private List<XjTableFieldVO> findJdbcTableField(GcConfig gcConfig, String table, String dataSourceId) {
        XjAdminDatasource datasource = adminDatasourceService.getById(dataSourceId);
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
                " from information_schema.columns " +
                " where " +
                " table_name = '" + table + "'" +
                " and table_schema= '" + dbName + "'" +
                " order by ordinal_position asc";
        // 3、判断使用默认数据源还是动态数据源来执行sql
        PreparedStatement pstmt = null;
        if (StringUtils.isBlank(dataSourceId)) {
            pstmt = JdbcPool.getPstmt(dbUrl.substring(0, dbUrl.indexOf("?")), dbUserName, dbPassWord, sql);
        } else {
            pstmt = JdbcPool.getPstmt(datasource.getDbUrl(), datasource.getDbUsername(), datasource.getDbPassword(), sql);
        }
        ResultSet rs = null;
        List<XjTableFieldVO> vos = new ArrayList<>();
        try {
            rs = pstmt.executeQuery();
            //游标向下移动
            while (rs.next()) {
                // 获得查询出来的数据
                XjTableFieldVO vo = new XjTableFieldVO();
                vo.setName(rs.getString("name"));
                vo.setType(rs.getString("type"));
                vo.setDesc(rs.getString("desc"));
                vo.setTypeDetail(rs.getString("typeDetail"));
                vo.setDefaultVal(rs.getString("defaultVal"));
                vo.setIsNull(rs.getString("isNull"));
                vos.add(vo);
            }
        } catch (SQLException e) {
            log.debug(e.toString());
        } finally {
            JdbcPool.closeQueryRes(rs);
        }
        //
        if (datasource != null && StringUtils.isNotBlank(datasource.getDbGeneralField())) {
            // 使用数据源配置的通用字段 (当使用了其他数据源和配置了通用字段时使用)
            List<String> fields = Arrays.asList(datasource.getDbGeneralField().split(","));
            for (XjTableFieldVO tableFieldVO : vos) {
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
            for (XjTableFieldVO tableFieldVO : vos) {
                // 判断是否为通用字段
                String baseFields = gcConfig.getDefaultTemplateParam("baseFields");
                if (baseFields.contains(tableFieldVO.getName())) {
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
