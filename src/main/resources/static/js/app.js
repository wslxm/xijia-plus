//====================================================================================
//====================================================================================
//================================= 添加修改删除通用弹出层 ==============================
//====================================================================================
//====================================================================================

/**
 * Layer 添加修改通用弹出层

 * @param url    请求地址
 * @param width  弹出层宽
 * @param height 弹出层高
 * @param name   弹出层名
 */
function tipsWindown(url, width, height, name) {
    layui.use('layer', function () {
        layer.open({
            type: 2,
            title: [name],
            area: [width, height],  // area: ["600px", "200px"],
            content: [url],         //page/menu_addRoot1
            btn: ['确定', '取消'],
            closeBtn: 0,
            fixed: false,
            shadeClose: true
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                submit.click();
            }
        })
    });
}

/**
 * 通用删除弹出层
 * @param url 请求地址
 * @param ids 要删除id，数组
 * @param obj 当前行，多行删除不传，多行删除使用重载
 */
function tipsDelete(url, ids, obj) {
    layui.use('layer', function () {
        layer.msg('你确定要删除么？', {
            time: 0
            , btn: ['必须删', '不删了']
            , yes: function (index) {
                // 获得要删除菜单及所有子菜单/页面
                var result = ajaxPost(url, ids);
                // 后台操作成功前端直接删除当前行删除
                if (result !== "no" && obj !== null) {
                    obj.del();
                }
                layer.msg('操作成功');
            }
        });
    });
}

//====================================================================================
//====================================================================================
//================================= Layui 所有分页配置 ================================
//====================================================================================
//====================================================================================

var pageJson = {
    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] //自定义分页布局
    //,curr: 5             //设定初始在第 5 页
    , limits: [10, 15, 20]  //每页显示条数
    , groups: 5            //只显示 1 个连续页码
    , first: false         //不显示首页
    , last: false          //不显示尾页
    // , prev: '《'
    // , next: '》'
};

//====================================================================================
//====================================================================================
//=================================  ajax请求 ========================================
//====================================================================================
//====================================================================================
/**
 * TODO  同步请求
 * @author ws
 * @mail  1720696548@qq.com
 * @param null
 * @date  2020/3/30 0030 0:05
 * @return
 */
// TODO   get 请求
function ajaxGet(url) {
    return ajax(url, null, "get", "json", false);
}

// TODO   get 请求带json参数
function ajaxGet(url, data) {
    return ajax(url, data, "get", "json", false);
}

// TODO   post 请求
function ajaxPost(url) {
    return ajax(url, null, "post", "json", false);
}

// TODO   post 请求带json参数
function ajaxPost(url, data) {
    return ajax(url, data, "post", "json", false);
}

// TODO   put 请求
function ajaxPut(url) {
    return ajax(url, null, "put", "json", false);
}

// TODO   put 请求带json参数
function ajaxPut(url, data) {
    return ajax(url, data, "put", "json", false);
}

// TODO   delete请求
function ajaxDelete(url) {
    return ajax(url, null, "delete", "json", false);
}

// TODO  delete 请求带json参数
function ajaxDelete(url, data) {
    return ajax(url, data, "delete", "json", false);
}

/**
 * TODO  异步请求
 * @author ws
 * @mail  1720696548@qq.com
 * @param null
 * @date  2020/3/30 0030 0:05
 * @return
 */
// TODO   get 请求
function ajaxGetAsync(url) {
    return ajax(url, null, "get", "json", true);
}

// TODO   get 请求带json参数
function ajaxGetAsync(url, data) {
    return ajax(url, data, "get", "json", true);
}

// TODO   post 请求
function ajaxPostAsync(url) {
    return ajax(url, null, "post", "json", true);
}

// TODO   post 请求带json参数
function ajaxPostAsync(url, data) {
    return ajax(url, data, "post", "json", true);
}

// TODO   put 请求
function ajaxPutAsync(url) {
    return ajax(url, null, "put", "json", true);
}

// TODO   put 请求带json参数
function ajaxPutAsync(url, data) {
    return ajax(url, data, "put", "json", true);
}

// TODO   delete请求
function ajaxDeleteAsync(url) {
    return ajax(url, null, "delete", "json", true);
}

// TODO  delete 请求带json参数
function ajaxDeleteAsync(url, data) {
    return ajax(url, data, "delete", "json", true);
}


// TODO  1-url  2-数据 3、请求方式 4、返回数据 5、同步false/异步true
function ajax(url, data, type, dataType, async) {
    var result;
    $.ajax({
        type: type,
        dataType: dataType,
        url: url,
        data: data,
        headers: {
            "token": localStorage.getItem('token')
        },
        async: async,      //同步
        traditional: true, //允许传递数组
        success: function (resultText) {
            result = resultText;
        },
        error: function () {
            layer.msg('后台异常！处理失败');
        }
    });
    //错误打印
    if (result.code !== 200) {
        layer.msg(result.msg);
        throw new Error();
    }
    return result;
}

//====================================================================================
//====================================================================================
//=============================== 时间相关 ============================================
//====================================================================================
//====================================================================================

/**
 * 判断时间过去了多少天，（只计算到日期yyyy-MM-dd，未计算小时/分/秒HH:mm:ss）
 *  传入标准时间字符串 yyyy-MM-dd HH:mm:ss || yyyy/MM/dd HH:mm:ss|| yyyy-MM-dd  || yyyy/MM/dd
 */
function judgeTime(data) {
    var date = data.toString();
    //根据索引取到年月日
    var year = date.substring(0, 4);
    var month = date.substring(5, 7);
    var day = date.substring(8, 10);
    var d1 = new Date(year + '/' + month + '/' + day);
    var dd = new Date();
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;
    var d = dd.getDate();
    var d2 = new Date(y + '/' + m + '/' + d);
    var iday = parseInt(d2 - d1) / 1000 / 60 / 60 / 24;
    return iday;
}


/**
 * 获取距离指定时间还有多少天
 * @param {String | Number | Date} dateTime 日期时间
 * @example
 * ```javascript
 *     getDistanceSpecifiedTime('2019/02/02 02:02:00');
 *     getDistanceSpecifiedTime(1549036800000);
 *     getDistanceSpecifiedTime(new Date("2019/2/2 00:00:00"));
 * ```
 */
function getDistanceSpecifiedTime(dateTime) {
    var time = dateTime.replace("-", "/");
    // 指定日期和时间
    var EndTime = new Date(time);
    // 当前系统时间
    var NowTime = new Date();
    var t = EndTime.getTime() - NowTime.getTime();
    var d = Math.floor(t / 1000 / 60 / 60 / 24);
    var h = Math.floor(t / 1000 / 60 / 60 % 24);
    var m = Math.floor(t / 1000 / 60 % 60);
    var s = Math.floor(t / 1000 % 60);
    var html = d + " 天" + h + " 时" + m + " 分" + s + " 秒";
    console.log(html);
    return d;
}


/**
 * 计算n天后的日期
 * initDate：开始日期，默认为当天日期， 格式：yyyymmdd/yyyy-mm-dd
 * days:天数
 * flag：返回值， 年与日之间的分隔符， 默认为xxxx年xx月xx日格式
 */
function getDateAfter_n(initDate, days, flag) {

    if (!days) {
        return initDate;
    }
    initDate = initDate.replace(/-/g, '');
    flag = $.trim(flag);
    var date;
    // 是否设置了起始日期
    if (!$.trim(initDate)) { // 没有设置初始化日期，就默认为当前日期
        date = new Date();
    } else {
        var year = initDate.substring(0, 4);
        var month = initDate.substring(4, 6);
        var day = initDate.substring(6, 8);
        date = new Date(year, month - 1, day); // 月份是从0开始的
    }
    date.setDate(date.getDate() + days);

    var yearStr = date.getFullYear();
    var monthStr = ("0" + (date.getMonth() + 1)).slice(-2, 8); // 拼接2位数月份
    var dayStr = ("0" + date.getDate()).slice(-2, 8); // 拼接2位数日期
    var result = "";
    if (!flag) {
        result = yearStr + "年" + monthStr + "月" + dayStr + "日";
    } else {
        result = yearStr + flag + monthStr + flag + dayStr;
    }
    return result;
}


//====================================================================================
//====================================================================================
//=========================== byte转字符串--字符串转byte ===============================
//====================================================================================
//====================================================================================


/**
 * TODO   byte转字符串
 * @author ws
 * @mail  1720696548@qq.com
 * @param null
 * @date  2020/3/29 0029 23:54
 * @return
 */
function stringToByte(str) {
    var bytes = new Array();
    var len, c;
    len = str.length;
    for (var i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if (c >= 0x010000 && c <= 0x10FFFF) {
            bytes.push(((c >> 18) & 0x07) | 0xF0);
            bytes.push(((c >> 12) & 0x3F) | 0x80);
            bytes.push(((c >> 6) & 0x3F) | 0x80);
            bytes.push((c & 0x3F) | 0x80);
        } else if (c >= 0x000800 && c <= 0x00FFFF) {
            bytes.push(((c >> 12) & 0x0F) | 0xE0);
            bytes.push(((c >> 6) & 0x3F) | 0x80);
            bytes.push((c & 0x3F) | 0x80);
        } else if (c >= 0x000080 && c <= 0x0007FF) {
            bytes.push(((c >> 6) & 0x1F) | 0xC0);
            bytes.push((c & 0x3F) | 0x80);
        } else {
            bytes.push(c & 0xFF);
        }
    }
    return bytes;
}


/**
 * TODO  字符串转byte
 * @author ws
 * @mail  1720696548@qq.com
 * @param null
 * @date  2020/3/29 0029 23:54
 * @return
 */
function byteToString(arr) {
    if (typeof arr === 'string') {
        return arr;
    }
    var str = '',
        _arr = arr;
    for (var i = 0; i < _arr.length; i++) {
        var one = _arr[i].toString(2),
            v = one.match(/^1+?(?=0)/);
        if (v && one.length == 8) {
            var bytesLength = v[0].length;
            var store = _arr[i].toString(2).slice(7 - bytesLength);
            for (var st = 1; st < bytesLength; st++) {
                store += _arr[st + i].toString(2).slice(2);
            }
            str += String.fromCharCode(parseInt(store, 2));
            i += bytesLength - 1;
        } else {
            str += String.fromCharCode(_arr[i]);
        }
    }
    return str;
}





