/**
 * ====================================================================
 * ====================================================================
 * ========================= 左菜单选中状态 ==============================
 * =====================================================================
 * @type {string}
 */
// 选中色和对应的Rgb,不一致无法复制选中色
let color = "#FF6E9C";
let colorRgb = "rgb(255, 110, 156)";


/**
 * 鼠标点击颜色处理 + 数据查询
 */
function getHelp(ths, id) {
    //清除除选中的其他所有
    $(".twoTitle").css("color", "#000000");
    // 设置当前选中
    ths.style.color = color;
    // 查询数据，value = 数据id
    help(id);
    // addUrlPara("id", id);
    history.pushState(null, "", "?id=" + id);
}

/**
 * 鼠标放置样式,不是选中就赋值
 */
function place(ths) {
    if (ths.style.color !== colorRgb) {
        ths.style.color = "#D855FF";
    }
}

/**
 * 鼠标移开样式,不是选中就赋值
 */
function moveAway(ths) {
    if (ths.style.color !== colorRgb) {
        ths.style.color = "#000000";
    }
}

/**
 * 加载动态菜单
 */
function initMenu(resData) {

    let helpMenuHtml = "";
    let data = resData;
    for (let i = 0; i < data.length; i++) {
        let ybHelpTreeVOS = data[i].ybHelpTreeVOS;
        helpMenuHtml += "<div>";
        helpMenuHtml += "<div class='oneTitle' onclick='expandAndContract(" + i + "," + data.length + ")'>" + data[i].name + " <span class='small-numbers'>(" + ybHelpTreeVOS.length + ")</span></div>";
        if (ybHelpTreeVOS.length > 0) {
            for (let j = 0; j < ybHelpTreeVOS.length; j++) {
                helpMenuHtml += "<div id='" + ybHelpTreeVOS[j].id + "' class='twoTitle twoTitle-" + i + "' onclick='getHelp(this,\"" + ybHelpTreeVOS[j].id + "\")' onmouseover='place(this)' onmouseout='moveAway(this)'>" + ybHelpTreeVOS[j].name + "</div>"

            }
        }
        helpMenuHtml += "</div>";
    }
    $("#helpMenu").html(helpMenuHtml);
    // 默认展开第一个
    expandAndContract(0, res.data.length);

    let id = getParam("id");
    if (id !== null && id !== "") {
        help(id);
    } else {
        // 默认展示第一个列表的第一篇文章
        help(data[0].ybHelpTreeVOS[0].id);
        // 设置选中色
        let s = $("#" + data[0].ybHelpTreeVOS[0].id).css("color", color);
    }
}


/**
 * 动态内容菜单点击选中色
 * @param ths
 */
function aClick(ths) {
    let alist = document.getElementsByTagName('a');
    for (let i = 0; i < alist.length; i++) {
        alist[i].style.color = "#1f1e2c";
    }
    ths.style.color = "#ff78e7";
}


/**
 * 展示目录-展开，收缩
 * index = 当前选中
 * len = index 总数
 */
var showIndex;  //当前选中
var showState;  //当前选中是否已展开，已展开true
var showTime = 300;   //展示收缩时间
function expandAndContract(index, len) {
    if (showIndex === index) {
        if (showState) {
            // $(".twoTitle-" + index).hide(showTime);
            // $(".twoTitle-" + index).fadeOut(showTime);
            $(".twoTitle-" + index).slideUp(showTime);
            showState = false;
        } else {
            // $(".twoTitle-" + index).show(showTime);
            // $(".twoTitle-" + index).fadeIn(showTime);
            $(".twoTitle-" + index).slideDown(showTime);
            showState = true;
        }
    } else {
        //上次选中不是当前选中,展示
        // $(".twoTitle-" + index).show(showTime);
        // $(".twoTitle-" + index).fadeIn(showTime);
        $(".twoTitle-" + index).slideDown(showTime);
        showState = true;
    }
    // 关闭当前未选中的其他所有
    for (let i = 0; i < len; i++) {
        if (index !== i) {
            $(".twoTitle-" + i).hide(showTime);
        }
    }
    showIndex = index;
}

/**
 * =====================================================================
 * =====================================================================
 * ========================= 加载内容动态生成的右菜单 ======================
 * =====================================================================
 * @type {string}
 */

//生成目录索引列表
/**
 *  当前html的div id= markdownToHTML
 *  渲染右撤菜单的div id=contentTitle
 */
function generateContentList() {
    var content = '<a name="_labelTop"></a>';
    content += '<div id="navCategory">';
    content += '<p style="font-size:18px"><b>目录</b></p>';
    // 获取html
    var jquery_h1_list = $('#markdownToHTML h2');
    if (jquery_h1_list.length == 0) {
        $('#contentTitle').html(content);
        return;
    }
    // 一级目录 start
    content += '<ul class="first_class_ul">';
    for (var i = 0; i < jquery_h1_list.length; i++) {
        var go_to_top = '<div style="text-align: right"><a onclick="aClick(this)" name="_label' + i + '"></a></div>';
        $(jquery_h1_list[i]).before(go_to_top);

        // 一级目录的一条
        var li_content = '<li><a onclick="aClick(this)" href="#_label' + i + '" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >' + $(jquery_h1_list[i]).text() + '</a></li>';

        var nextH1Index = i + 1;
        if (nextH1Index == jquery_h1_list.length) {
            nextH1Index = 0;
        }
        var jquery_h2_list = $(jquery_h1_list[i]).nextUntil(jquery_h1_list[nextH1Index], "h3");
        // 二级目录 start
        if (jquery_h2_list.length > 0) {
            //li_content +='<ul style="list-style-type:none; text-align: left; margin:2px 2px;">';
            li_content += '<ul class="second_class_ul">';
        }
        for (var j = 0; j < jquery_h2_list.length; j++) {
            var go_to_top2 = '<div style="text-align: right"><a onclick="aClick(this)" name="_lab2_' + i + '_' + j + '"></a></div>';
            $(jquery_h2_list[j]).before(go_to_top2);
            // 二级目录的一条
            li_content += '<li ><a onclick="aClick(this)" href="#_lab2_' + i + '_' + j + '" rel="external nofollow" >' + $(jquery_h2_list[j]).text() + '</a></li>';

            var nextH2Index = j + 1;
            var next;
            if (nextH2Index == jquery_h2_list.length) {
                if (i + 1 == jquery_h1_list.length) {
                    next = jquery_h1_list[0];
                } else {
                    next = jquery_h1_list[i + 1];
                }
            } else {
                next = jquery_h2_list[nextH2Index];
            }
            var jquery_h3_list = $(jquery_h2_list[j]).nextUntil(next, "h4");
            // 三级目录 start
            if (jquery_h3_list.length > 0) {
                li_content += '<ul class="third_class_ul">';
            }
            for (var k = 0; k < jquery_h3_list.length; k++) {
                var go_to_third_Content = '<div style="text-align: right"><a onclick="aClick(this)" name="_label3_' + i + '_' + j + '_' + k + '"></a></div>';
                $(jquery_h3_list[k]).before(go_to_third_Content);
                // 三级目录的一条
                li_content += '<li><a onclick="aClick(this)" href="#_label3_' + i + '_' + j + '_' + k + '" rel="external nofollow" >' + $(jquery_h3_list[k]).text() + '</a></li>';
            }
            if (jquery_h3_list.length > 0) {
                li_content += '</ul>';
            }
            li_content += '</li>';
            // 三级目录 end
        }
        if (jquery_h2_list.length > 0) {
            li_content += '</ul>';
        }
        li_content += '</li>';
        // 二级目录 end
        content += li_content;
    }
    // 一级目录 end
    content += '</ul>';
    content += '</div>';
    // $($('#content')[0]).prepend(content);
    // $($('#contentTitle')[0]).prepend(content);
    $('#contentTitle').html(content);
}

/**
 * 如果为移动设备访问，展示内容，不展示左菜单和右内容菜单
 * @author wangsong
 * @param null
 * @date 2020/12/14 0014 19:46
 * @return
 * @version 1.0.0
 */
function mobileReveal() {
    /* 手机访问单篇文章(不展示左菜单和右内容菜单) */
    if (isMobile()) {
        /* 内容 */
        $("#contentMd").css("width", "100%");
        $("#contentMd").css("height", "100%");
        $("#contentMd").css("left", "0%");
        $("#contentMd").css("top", "0%");
        $("#contentMd").css("z-index", "1");
       // $("#contentMd").css("display", "none"); //隐藏
        /* 菜单宽度设置 */
        $("#helpMenuDiv").css("width", "80%");
        /* 内容菜单 */
        $("#contentTitleDiv").css("width", "0%");
        /* 菜单按钮-展示 */
        $("#isMenuShop").css("display", "block");
    }
}

/**
 * 手机访问点击菜单按钮
 */
function menuShop(){
    let contentMdCss =   $("#contentMd").css("display");
    if(contentMdCss == "block"){
        $("#contentMd").css("display", "none");
    }else{
        $("#contentMd").css("display", "block");
    }
}