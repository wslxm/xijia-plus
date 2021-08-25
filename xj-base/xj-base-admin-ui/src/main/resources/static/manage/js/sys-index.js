/** 如果index在内窗口，直接跳到最顶层窗口 */
if (window !== top) {
    top.location.href = window.location.href;
}


/**
 * 退出登录
 */
function adminLogout() {
    //清除token
    sessionStorage.removeItem('token');
    //跳转登录页
    location.href = "/login";
}


/**
 * 初始化相关数据
 */
function initIndex() {
    // 加载菜单
    initMenu();
    // 页面地址
    //$("#viewsUrl").attr("href", viewsUrl);   // 模板
    $("#homePage").attr("src", homePage);      // 首页页面地址
    $("#updPwd").attr("lay-href", updPwd);     // 修改密码页面地址

    // 加载用户相关信息
    let user = Ajax.get(findUser).data;        // 获取用户信息
    $("#username").html(user.fullName);        // 展示用户名
    $("#head").attr("src", user.head);         // 展示头像

    // 加载项目名称
    let res = Ajax.get(configOneByCode);       // 获取项目名
    $("#entry_name").html(res.data.content);   // 展示

    // 加载字典
    Dict.refreshDict();  //获取字典数据
    Dict.getDictTest();  //字典数据测试
}


// =======================================================================================
// ==================================== 菜单相关 =============================================
// =======================================================================================

let menuData = null;

function initMenu() {
    menuData = Ajax.get(menuTree).data;    // 查询数据
    treeMenu(0);                  // 加载左边导航默认系统管理
    menuRoot1();                          // 加载顶部系统级菜单加载
    layui.element.render('nav');          // 重新渲染菜单
}


// 系统级菜单展示  -->  root=1
function menuRoot1() {
    let html = "";
    $.each(menuData, function (index) {
        if (index == 0) {
            html += "<li class=\"layui-nav-item layui-this\" onclick=\"menuRoot1Click(" + index + ")\"><a href=\"JavaScript:void(0)\">" + menuData[index].name + "</a></li>";
        } else {
            html += "<li class=\"layui-nav-item\" onclick=\"menuRoot1Click(" + index + ")\"><a href=\"JavaScript:void(0)\">" + menuData[index].name + "</a></li>";
        }

    });
    $("#menuRoot1").html(html);
}

// 系统级菜单点击事件切换导航菜单
function menuRoot1Click(tabIndex) {
    treeMenu(tabIndex);
    //重新渲染菜单
    layui.element.render('nav');
}

//一级目录或页面 -->  root=2
function treeMenu(tabIndex) {
    $("#LAY-system-side-menu").html("");
    let html = "";
    //遍历拼接菜单
    let data = menuData[tabIndex].menus;
    $.each(data, function (index, item) {
        //一级菜单（是否展开）
        // if (menuData[index].open == 1) {
        //     htmlRoot2 += "<li  data-name='home' class='layui-nav-item layui-nav-itemed'>";
        // } else {
        html += "<li  data-name='home' class='layui-nav-item'>";                                  // 一级目录头
        html += "<a href='javascript:;' lay-tips='" + data[index].name + "' lay-direction='2'>";  // 名称
        html += "<i class='layui-icon " + data[index].icon + "'></i>";                            // 图标
        html += "<cite class='xijia-menu-title'>" + data[index].name + "</cite>";                 // 菜单名称
        //html += "<spen class='xijia-menu-title layui-nav-more'><spen>";                         // 图标
        html += " </a>";
        //判断是否存在下级目录
        if (data[index].menus != null && data[index].menus.length > 0) {
            html += "<dl class='layui-nav-child' >";            // 二级目录头  --> class="layui-nav-child"
            html += nextMenuRoot3(data[index].menus);           // 二级菜单/页面 --> 获取
            html += "</dl>";
        }
        html += "</li>";                                        // 一级目录尾
    });
    $("#LAY-system-side-menu").html(html);
}


//二级目录或页面 --> root=3
function nextMenuRoot3(data) {
    let html = "";
    //二级菜单
    $.each(data, function (index, item) {
        //判断存在下级目录， 默认展开-> class='layui-nav-itemed'
        if (data[index].menus != null && data[index].menus.length > 0) {
            html += "<dd  style='margin-left: 10%'>";
            html += "<a href='javascript:;'><i class='layui-icon " + data[index].icon + "'></i>";
            html += "<cite class='xijia-menu-title '>" + data[index].name + "</cite>";  // 菜单名称
            html += " </a>";
            html += "<dl class='layui-nav-child'>";    //layui-nav-child
            html += nextMenuRoot4(data[index].menus);  //三级菜单
            html += "</dl>";
            html += "</dd>";
        } else {
            html += "<dd  style='margin-left: 5%' data-name='console'>";
            html += "<a lay-href='" + data[index].url + "'>";
            html += "<cite class='xijia-menu-title'>" + data[index].name + "</cite>";  // 菜单名称
            html += " </a>";
            html += "</dd>";
        }
    });
    return html;
}


//页面 --> root=4
function nextMenuRoot4(data) {
    let html = "";
    $.each(data, function (index) {
        //
        html += "<dd  style='margin-left: 3%'>" +
            "<a  lay-href='" + data[index].url + "'>" +
            "<div class='xijia-menu-title'>" + data[index].name + "</div>" +
            "</a>" +
            "</dd>";
    });
    return html;
}