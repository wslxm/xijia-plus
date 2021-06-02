

/**
 *  Layui 通用分页配置
 *  Layui 通用分页配置, 数据表格直接使用 page: pageJson,  layPage插件读取每一个参数值
 * @param url 请求地址
 * @param data 要删除的id
 * @param obj 当前行，多行删除不传，多行删除使用重载
 */
pageJson = {
    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] //自定义分页布局
    , curr: 1              // 设定初始在第1页
    , limits: [10, 15, 20, 9999]   // 每页显示条数
    , groups: 5             // 只显示几个连续页码
    // , first: "首页"      // 显示按钮内容（false为不展示,layout 不支持）
    // , last: "尾页"       // 显示按钮内容（false为不展示，layout 不支持）
    , prev: '上一页'        // 上一页按钮内容
    , next: '下一页'        // 下一页按钮内容
};


/**
 *  获取 Layui 当前分页参数 ===>  如：curr=1&limits=10
 *
 *  page 手动指定页数，不传默认使用 pageJson.limits[0]
 *  size 手动指定条数，不传默认使用 pageJson.limits[0]
 */
function getPage(page, size) {
    // 分页页数key
    let pageKey = "current";
    // 分页记录数key
    let sizeKey = "size";

    //获取当前页
    let pageVal = $(".layui-laypage-skip .layui-input").val();
    if (page == null) {
        if (pageVal == null) {
            pageVal = pageJson.curr;
        }
    } else {
        pageVal = page;
    }

    //获取当前页条数
    let sizeVal = $(".layui-laypage-limits").find("option:selected").val();
    if (size == null) {
        if (sizeVal == null) {
            sizeVal = pageJson.limits[0];
        }
    } else {
        sizeVal = size;
    }
    let pageVo = "?" + pageKey + "=" + pageVal + "&" + sizeKey + "=" + sizeVal;
    return pageVo;
}
