// jquery
document.write("<script language=javascript src='/base/js/jquery-3.4.1.js'></script>");
document.write("<script language=javascript src='/components/layuiadmin/layui/src/layui.js'></script>");
//
document.write("<script language=javascript src='/base/js/xj-enums.js'></script>");        /* 字典数据，与后端对应，修改了请注意更新字典key */
document.write("<script language=javascript src='/base/js/xj-layui-page.js'></script>");   /* layui分页插件参数统一配置 */
document.write("<script language=javascript src='/base/js/xj-layui-pop.js'></script>");    /* layui 弹层(添加/删除/编辑)统一配置 */
document.write("<script language=javascript src='/base/js/xj-tool.js'></script>");         /* 不好分类的js方法工具 */
//
document.write("<script language=javascript src='/base/js/xj-string-utils.js'></script>"); /* 字符串处理工具 */
document.write("<script language=javascript src='/base/js/xj-time-util.js'></script>");    /* 时间处理工具 */
document.write("<script language=javascript src='/base/js/xj-window-pos.js'></script>");   /* 时间处理工具 */
document.write("<script language=javascript src='/base/js/xj-base64.js'></script>");       /* base64工具类 */
document.write("<script language=javascript src='/base/js/xj-sign-md5.js'></script>");     /* 验签使用的md5工具，与后端md5绑定，不可切换 */
document.write("<script language=javascript src='/base/js/xj-req-sign.js'></script>");     /* 参数加签逻辑, 在xj-req-ajax.js中调用 */
document.write("<script language=javascript src='/base/js/xj-req-ajax.js'></script>");     /* ajax 请求(封装了get/post/put/delete方法并对参数进行了加签) */


/**
 * path: 后台接口访问地址（代码接口中不需要第一层的 /api, 后端使用接口都已 /api 开头, 如：http://127.0.0.1:9049/api）
 * uploadPath：后台文件上传接口
 * <P>
 *   因为当前html代码和接口在同一个启动服务中，直接使用 / 可自动匹配当前服务ip，固指定为：/api
 *   也可指定：本地 "http://127.0.0.1:9049/api" ||  线上ip地址： http://47.107.128.84:9049/api ||  线上域名地址： http://xijia.plus
 * <p>
 */
//var path = "/api";
var path = "http://127.0.0.1:9048/api";
var uploadPath = path + "/open/aliOssFile/upload";


/**
 * 修改配置信息
 * loginPwd:  管理端登录页访问秘钥(如果后台开启)
 * loginPage: 登录页地址(ReqAjax.js, token无效或过期用于跳转到登录页)
 * cacheToken: 接口请求权限命名(token)
 */
var loginPwd = "xijia";
var loginPage = "../login";
var cacheToken = "ADMIN-TOKEN";



/**
 * 枚举字段key（管理端字典列表中生成获取，增加了字典值需维护，不需要手写）
 * @type s
 */
Enums = {
    // 系统枚举(固定值)
    Base: {
        Default : "DEFAULT",  // 默认字典(代码生成默认字典)
        Deleted : "DELETED",  // 逻辑删除
        Disable : "DISABLE",  // 是否禁用
        Gender : "GENDER",  // 性别
        AuthorityState : "AUTHORITY_STATE",  // 权限状态
        AuthorityType : "AUTHORITY_TYPE",  // 权限类型
        MenuRoot : "MENU_ROOT",  // 菜单级别
        BannerIsSkip : "BANNER_IS_SKIP",  // banner是否跳转
        IsRead : "IS_READ",  // 是否已读
        BlacklistType : "BLACKLIST_TYPE",  // 黑/白名单类型
        PayChannel : "PAY_CHANNEL",  // 支付渠道
        PayState : "PAY_STATE",  // 支付状态
        PayType : "PAY_TYPE",  // 支付类型
        WalletType : "WALLET_TYPE",  // 流水类型
    },
    // 系统枚举(动态值)
    Admin: {
        Terminal : "TERMINAL",  // 终端
        Position : "POSITION",  // 部门职位
        BannerPosition : "BANNER_POSITION",  // banner 位置
        MsgType : "MSG_TYPE",  // 及时消息类型
        MsgUserType : "MSG_USER_TYPE",  // 及时消息终端
        PayBusiness : "PAY_BUSINESS",  // 支付业务
    },
};



// 全局请求头,token 的参数获取 (xj-req-ajax.js 中调用)
function getGlobalHeaders() {
    return sessionStorage.getItem(cacheToken) == null ? "" : sessionStorage.getItem(cacheToken);
}

// 全局请求头,token 的参数缓存 (登录时(登录html页) / 或刷新token时（xj-req-ajax.js 中刷新）进行设置)
function setGlobalHeaders(token) {
    sessionStorage.setItem(cacheToken, token)
}

// 文件上传专用 token 获取方法 （layui的文件上传组件中调用）
function getFileGlobalHeaders() {
    return {"TOKEN": getGlobalHeaders()};
}
