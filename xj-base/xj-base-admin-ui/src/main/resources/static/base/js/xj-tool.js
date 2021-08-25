

// ============================================================================================
// ============================================================================================
// ===================== 下方就是一下不好分类和分类和方法教少的 js方法================================
// ============================================================================================
// ============================================================================================

/**
 * 获取url 参数
 * @author wangsong
 * @param name key
 * @date 2020/12/13 0013 0:59
 * @return
 * @version 1.0.0
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}


/**
 * 判断是否为移动设备
 * @author wangsong
 * @date 2020/12/14 0014 19:07
 * @return
 * @version 1.0.0
 */
function isMobile() {
    if (navigator.userAgent.match(/Android/i)
        || navigator.userAgent.match(/webOS/i)
        || navigator.userAgent.match(/iPhone/i)
        || navigator.userAgent.match(/iPad/i)
        || navigator.userAgent.match(/iPod/i)
        || navigator.userAgent.match(/BlackBerry/i)
        || navigator.userAgent.match(/Windows Phone/i)
    ) return true;
    return false;
}



/**
  * 判断是否为https  请求
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/8/24 0024 9:31
  * @version 1.0.0
  */
function isHttps() {
    return 'https:' == document.location.protocol ? true : false;
}