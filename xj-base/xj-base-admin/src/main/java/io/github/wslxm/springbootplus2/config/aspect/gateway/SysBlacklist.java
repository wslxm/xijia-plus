package io.github.wslxm.springbootplus2.config.aspect.gateway;


import io.github.wslxm.springbootplus2.cache.XjCacheUtil2;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminBlacklistService;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.enums.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 黑名单/白名单验证
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/1/23 0023 9:17
 * @version 1.0.1
 */
@Component
@Slf4j
public class SysBlacklist {


    @Autowired
    private XjAdminBlacklistService xjAdminBlacklistService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 黑名单/白名单验证
     * <p>
     * 认证顺序 ,   黑名单指定ip --> 白名单(*)  --> 黑名单(*)   --> 白名单指定ip
     * 优先级：
     * 1、访问ip 在黑名单ip列表，直接禁止访问
     * 2、设置了白名单（*）, 只要ip不在黑名单列表，直接放行
     * 3、访问ip没有在黑名单中，也没有设置白名单（*）的情况下，设置了黑名单(*)，那么除了白名单中的指定ip能访问接口外，其他ip都不能访问资源
     *
     * 本地 127.0.0.1+ localhost 访问直接放行
     * <p/>
     * @author wangsong
     * @date 2020/11/28 0028 0:01
     * @return io.github.wslxm.common.result.R
     * @version 1.0.1
     */
    @SuppressWarnings("all")
    public R<Void> blacklistAuth() {
        // 获取用户ip
        String ip = getIpAddress(request);

        // 本地启用不处理黑名单/白名单
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            return R.success();
        }

        // 获取 黑/白名单配置
        List<String> baiIps = XjCacheUtil2.listByType(Base.BlacklistType.V1.getValue());
        List<String> heiIps = XjCacheUtil2.listByType(Base.BlacklistType.V2.getValue());
        // 1、没有配置黑+白名单，直接放行
        if (baiIps.isEmpty() && heiIps.isEmpty()) {
            return R.success();
        }
        // 2、没有配置黑名单，直接放行
        if (heiIps == null || heiIps.isEmpty()) {
            return R.success();
        }
        // 3、检查黑名单，如果被列进的指定ip的黑名单，直接拦截
        if (!heiIps.isEmpty()) {
            if (heiIps.contains(ip)) {
                return R.error(RType.AUTHORITY_BLACK_LIST_IP.getValue(), "[" + ip + "] " + RType.AUTHORITY_BLACK_LIST_IP.getMsg());
            }
        }

        // 4、如果配置了白名单( * )直接放行除了黑名单的所有ip
        if (!baiIps.isEmpty()) {
            if (baiIps.contains("*")) {
                return R.success();
            }
        }

        // 5、如果配置了黑名单( * )拦截除了白名单外的所有ip
        if (!baiIps.isEmpty()) {
            if (heiIps.contains("*")) {
                if (baiIps == null || !baiIps.contains(ip)) {
                    return R.error(RType.AUTHORITY_WHITE_LIST_NO_IP.getValue(), "[" + ip + "] " + RType.AUTHORITY_WHITE_LIST_NO_IP.getMsg());
                }
            }
        }
        return R.success();
    }


    /**
     * 查询并缓存黑名单/白名单数据
     * <P>
     *     黑名单/白名单配置信息( key=1(白名单)  key=2(黑名单)) 黑名单优先级高于白名单, list为 ip集合
     * </P>
     * @author wangsong
     * @date 2021/7/31 0031 15:07
     * @return java.util.Map<java.lang.String, java.util.List < java.lang.String>>
     * @version 1.0.1
     */
//    private Map<String, List> findBlacklist() {
//        // 如果没有缓存，就去数据库获取
//
//
//        if (!blacklist.isEmpty()) {
//                // key=1(白名单)  key=2(黑名单)) 黑名单优先级高于白名单, list为 ip集合
//                List<XjAdminBlacklistVO> adminBlacklistVos = BeanDtoVoUtil.listVo(blacklist, XjAdminBlacklistVO.class);
//                Map<Integer, List<XjAdminBlacklistVO>> blacklistGroupByType = adminBlacklistVos.stream().collect(Collectors.groupingBy(XjAdminBlacklistVO::getType));
//                List<XjAdminBlacklistVO> baiMd = blacklistGroupByType.get(Base.BlacklistType.V1.getValue());
//                List<XjAdminBlacklistVO> heiMd = blacklistGroupByType.get(Base.BlacklistType.V2.getValue());
//                if (baiMd != null) {
//                    List<String> baiIps = baiMd.stream().map(XjAdminBlacklistVO::getIp).collect(Collectors.toList());
//                    blacklistCache.put(Base.BlacklistType.V1.getValue() + "", baiIps);
//                }
//                if (heiMd != null) {
//                    List<String> heiIps = heiMd.stream().map(XjAdminBlacklistVO::getIp).collect(Collectors.toList());
//                    blacklistCache.put(Base.BlacklistType.V2.getValue() + "", heiIps);
//                }
//            }
//            XjCacheUtil.set(CacheKey.BLACK_LIST.getKey(), blacklistCache);
//        }
//        return XjCacheUtil.getMap(CacheKey.BLACK_LIST.getKey(), List.class);
//    }


    /**
     * 获取请求地址
     * @author wang-song
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.1
     */
    private String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
