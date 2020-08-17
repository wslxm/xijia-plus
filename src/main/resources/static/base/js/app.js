/**
 * TODO 后台接口访问地址
 */
path = ""; // 本地 "http://127.0.0.1:9049" ||  线上ip地址： http://47.107.128.84:9049 ||  线上域名地址： http://xijia.plus

/**
 * TODO 系统相关配置
 */
BaseConfig = {
    dev_user_role_id: "2",        // 开发人员角色Id
    token: "TOKEN",               // token 命名
};


/**
 * TODO 枚举字典 key
 */
Enums = {
    // 系统模块
    Admin: {
        MenuRoot: "MENU_ROOT",  // 菜单级别
    },
    // 通用枚举
    Base: {
        Gender: "GENDER",  // 性别
    },
    // 开发模块
    Dev: {
        BaseItem: "BASE_ITEM",  // 项目名称
        BaseState: "BASE_STATE",  // 任务状态
    },
};


/**
 * TODO 枚举字典通用方法
 * @type {{getDict: (function(): any), convertDict: (function(*, *): *), getDictTest: Dict.getDictTest, refreshDict: Dict.refreshDict}}
 */
Dict = {

    /**
     * 刷新字典数据（从后台获取字典数据并缓存到localStorage, 当字典数据没有发生变化时，后台请求的 dictData 数据除了版本信息(version),将没有任何返回数据）
     * <P>
     *   每次打开新的页面调用此方法刷新一下字典数据，如何后台没有更新，则只会查询版本号，不会查询字典数据，节约带宽，大大的提升访问速度
     * </P>
     */
    refreshDict: function () {
        let version = Ajax.get(path + "/admin/adminDictionary/findVersion").data;
        let dictCache = localStorage.getItem('dictCache');
        if (dictCache == null || version !== JSON.parse(dictCache).VERSION.version) {
            //从后台获取字典数据并缓存到localStorage
            let dictData = Ajax.get(path + "/admin/adminDictionary/findCodeGroup");
            localStorage.setItem('dictCache', JSON.stringify(dictData.data));
        }
    },

    /**
     * 枚举转换工具类 --> 接口返回的状态值(数字)转换字典Name值
     */
    convertDict: function (enumKay, code) {
        //获取到指定key下的枚举对象
        let dictKeyCache = Dict.getDict(enumKay);
        if (dictKeyCache != null) {
            return dictKeyCache.dictMap[code];
        } else {
            return null;
        }
    },

    /**
     * 获取指定枚举下的select列表
     */
    getDict: function (enumKay) {
        let dictCache = localStorage.getItem('dictCache');
        if (dictCache != null) {
            let jsonDict = JSON.parse(dictCache);
            return jsonDict[enumKay];
        } else {
            return null;
        }
    },


    /**
     * 测试方法
     *  id        // 数据主键ID
     *  code      // 字典类型
     *  name;     // 字典名称
     *  pid;      // 父Id
     *  desc;     // 描叙
     *  sort;     // 排序
     *  version;  // 版本号
     * disable 禁用（0-否，1-是）
     */
    getDictTest: function () {
        // 测试中的 .name 可以去上方注释中的任意参数
        console.log("项目code=1:  ==>   " + Dict.convertDict(Enums.Dev.BaseItem, 1));
        console.log("项目code=2:   ==>   " + Dict.convertDict(Enums.Dev.BaseItem, 2));
        console.log("项目code=2:   ==>   " + Dict.convertDict(Enums.Dev.BaseItem, 2).name);
        // 打印
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Dev.BaseItem));
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Dev.BaseItem).dictMap[1].name);
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Dev.BaseItem).dictMap[2].name);
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Dev.BaseItem).dictMap[3].name);
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Dev.BaseItem).dictMap[4].name);
    }
};


//TODO Layui 通用弹出层封装
Pop = {
    /**
     *  Layer 添加添加/修改通用弹出层 添加修改删除通用弹出层
     * @param url    请求地址
     * @param width  弹出层宽
     * @param height 弹出层高
     * @param name   弹出层名
     */
    tipsWindown: function (url, width, height, name) {
        layui.use('layer', function () {
            layer.open({
                type: 2,
                title: [name],
                area: [width, height],  // area: ["600px", "200px"],
                content: [url],         //page/menu_addRoot1
                btn: ['确定', '取消'],
                closeBtn: 1,
                fixed: false,
                shadeClose: true,
                crossDomain: true,//跨域，https://www.cnblogs.com/autoXingJY/p/11419860.html
                success: function (layero, index) {
                    // //自适应弹出层
                    // layer.iframeAuto(index);
                }
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                    submit.click();
                }
            })
        });
    },


    /**
     * 通用删除弹出层，单Id删除，参数接url后
     * @param url 请求地址
     * @param obj 当前行，多行删除不传，多行删除使用重载
     */
    tipsDeleteId: function (url, obj) {
        layui.use('layer', function () {
            layer.msg('你确定要删除么？', {
                time: 0
                , btn: ['必须删', '不删了']
                , yes: function (index) {
                    // 获得要删除菜单及所有子菜单/页面
                    let result = Ajax.delete(url);
                    // 后台操作成功前端直接删除当前行删除
                    if (result.code === 200) {
                        // window.location.reload();
                        obj.del();
                    }
                    layer.msg(result.msg);
                }
            });
        });
    },


    /**
     * 通用删除弹出层 多Id删除，参数为 data 集
     * @param url 请求地址
     * @param data 要删除的id
     * @param obj 当前行，多行删除不传，多行删除使用重载
     */
    tipsDeleteIds: function (url, data, obj) {
        layui.use('layer', function () {
            layer.msg('你确定要删除么？', {
                time: 0
                , btn: ['必须删', '不删了']
                , yes: function (index) {
                    // 获得要删除菜单及所有子菜单/页面
                    let result = Ajax.delete(url, data);
                    // 后台操作成功前端直接删除当前行删除
                    if (result.code === 200) {
                        obj.del();
                    }
                    layer.msg(result.msg);
                }
            });
        });
    }
};


/**
 *  TODO Layui 通用分页配置
 *  Layui 通用分页配置, 数据表格直接使用 page: pageJson,  layPage插件读取每一个参数值
 * @param url 请求地址
 * @param data 要删除的id
 * @param obj 当前行，多行删除不传，多行删除使用重载
 */
pageJson = {
    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] //自定义分页布局
    , curr: 1              // 设定初始在第1页
    , limits: [15, 20, 25, 9999]   // 每页显示条数
    , groups: 5            // 只显示几个连续页码
    // , first: "首页"      // 显示按钮内容（false为不展示,layout 不支持）
    // , last: "尾页"       // 显示按钮内容（false为不展示，layout 不支持）
    , prev: '上一页'        // 上一页按钮内容
    , next: '下一页'        // 下一页按钮内容
};


/**
 *  TODO 获取 Layui 当前分页参数 ===>  如：curr=1&limits=10
 */
function getPage() {
    // 分页页数key
    let pageKey = "current";
    // 分页记录数key
    let sizeKey = "size";
    //获取当前页
    let pageVal = $(".layui-laypage-skip .layui-input").val();
    if (pageVal == null) {
        pageVal = pageJson.curr;
    }
    //获取当前页条数
    let sizeVal = $(".layui-laypage-limits").find("option:selected").val();
    if (sizeVal == null) {
        sizeVal = pageJson.limits[0];
    }
    let page = "?" + pageKey + "=" + pageVal + "&" + sizeKey + "=" + sizeVal;
    return page;
}


// TODO AJAX 请求工具封装
// noinspection JSDuplicatedDeclaration
Ajax = {
    /**
     * ajax 请求
     */
// ajax-get 请求
    get: function (url) {
        return Ajax.request(url, null, "get", "json");
    },

//  ajax-get 请求带json参数
    get: function (url, data) {
        return Ajax.request(url, data, "get", "json");
    },

//  ajax-post 请求
    post: function (url) {
        return Ajax.request(url, null, "post", "json");
    },

// ajax-post 请求带json参数
    post: function (url, data) {
        return Ajax.request(url, data, "post", "json");
    },

// ajax-put 请求
    put: function (url) {
        return Ajax.request(url, null, "put", "json");
    },

//  ajax-put 请求带json参数
    put: function (url, data) {
        return Ajax.request(url, data, "put", "json");
    },

//  ajax-delete请求
    delete: function (url) {
        return Ajax.request(url, null, "delete", "json");
    },

//  ajax-delete 请求带json参数
    delete: function (url, data) {
        return Ajax.request(url, data, "delete", "json");
    },


    // ajax-请求(同步请求) --> 1-url  2-数据 3、请求方式 4、返回数据 || -<5、同步false/异步true
    request: function (url, data, type, dataType) {
        let result = null;
        $.ajax({
            type: type,
            dataType: dataType,
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            headers: {
                //"token": localStorage.getItem('token')
                "TOKEN": sessionStorage.getItem(BaseConfig.token),
                //"Accept": "*/*"
            },
            async: false,        // true=异步，false=同步
            //traditional: true, // 允许传递数组
            //请求成功
            success: function (resultData, status, request) {
                result = resultData;
                // token 处理,每次请求后刷新token
                let token = request.getResponseHeader(BaseConfig.token);
                if (token != null) {
                    sessionStorage.setItem(BaseConfig.token, token);
                }
            },
            //请求失败
            error: function (xhr, textStatus, errorThrown) {
                try {
                    if (xhr.responseJSON != null) {
                        result = xhr.responseJSON;
                    } else {
                        layer.msg('AJAX请求失败!');
                    }
                } catch (e) {
                    alert('AJAX请求失败!');
                }
                /*错误信息处理*/
                // alert("进入error---");
                // alert("状态码：" + xhr.status);
                // alert("状态:" + xhr.readyState);//当前状态,0-未初始化，1-正在载入，2-已经载入，3-数据进行交互，4-完成。
                // alert("错误信息:" + xhr.statusText);
                // alert("返回响应信息：" + xhr.responseText);//这里是详细的信息
                // alert("请求状态：" + textStatus);
                // alert(errorThrown);
                // alert("请求失败");
            }
        });
        //错误打印
        if (result.code !== 200) {
            //10003 = 没有token
            if (result.code === 10000) {
                //用户未登陆/或登录过期跳登录页
                location.href = "../login";
            }
            try {
                layer.msg(result.msg);
            } catch (e) {
                //没有加载到layer弹出层
                alert(result.msg);
            }
            throw new Error();
        }
        return result;
    }
};


/**
 * TODO 时间计算工具
 * @type {{getDistanceSpecifiedTime: (function((String|Number|Date)): number), getDateAfter_n: TimeUtil.getDateAfter_n, judgeTime: (function(*): number)}}
 */
TimeUtil = {
    /**
     *  时间计算
     *  判断时间过去了多少天，（只计算到日期yyyy-MM-dd，未计算小时/分/秒HH:mm:ss）
     *  传入标准时间字符串 yyyy-MM-dd HH:mm:ss || yyyy/MM/dd HH:mm:ss|| yyyy-MM-dd  || yyyy/MM/dd
     */

    judgeTime: function (data) {
        let date = data.toString();
        //根据索引取到年月日
        let year = date.substring(0, 4);
        let month = date.substring(5, 7);
        let day = date.substring(8, 10);
        let d1 = new Date(year + '/' + month + '/' + day);
        let dd = new Date();
        let y = dd.getFullYear();
        let m = dd.getMonth() + 1;
        let d = dd.getDate();
        let d2 = new Date(y + '/' + m + '/' + d);
        let iday = parseInt(d2 - d1) / 1000 / 60 / 60 / 24;
        return iday;
    },


    /**
     * 时间计算
     * 获取距离指定时间还有多少天
     * @param {String | Number | Date} dateTime 日期时间
     * @example
     * ```javascript
     *     getDistanceSpecifiedTime('2019/02/02 02:02:00');
     *     getDistanceSpecifiedTime(1549036800000);
     *     getDistanceSpecifiedTime(new Date("2019/2/2 00:00:00"));
     * ```
     */
    getDistanceSpecifiedTime: function (dateTime) {
        let time = dateTime.replace("-", "/");
        // 指定日期和时间
        let EndTime = new Date(time);
        // 当前系统时间
        let NowTime = new Date();
        let t = EndTime.getTime() - NowTime.getTime();
        let d = Math.floor(t / 1000 / 60 / 60 / 24);
        let h = Math.floor(t / 1000 / 60 / 60 % 24);
        let m = Math.floor(t / 1000 / 60 % 60);
        let s = Math.floor(t / 1000 % 60);
        let html = d + " 天" + h + " 时" + m + " 分" + s + " 秒";
        console.log(html);
        return d;
    },


    /**
     * 时间计算
     * 计算n天后的日期
     * initDate：开始日期，默认为当天日期， 格式：yyyymmdd/yyyy-mm-dd
     * days:天数
     * flag：返回值， 年与日之间的分隔符， 默认为xxxx年xx月xx日格式
     */
    getDateAfter_n: function (initDate, days, flag) {
        if (!days) {
            return initDate;
        }
        initDate = initDate.replace(/-/g, '');
        flag = $.trim(flag);
        let date;
        // 是否设置了起始日期
        if (!$.trim(initDate)) { // 没有设置初始化日期，就默认为当前日期
            date = new Date();
        } else {
            let year = initDate.substring(0, 4);
            let month = initDate.substring(4, 6);
            let day = initDate.substring(6, 8);
            date = new Date(year, month - 1, day); // 月份是从0开始的
        }
        date.setDate(date.getDate() + days);

        let yearStr = date.getFullYear();
        let monthStr = ("0" + (date.getMonth() + 1)).slice(-2, 8); // 拼接2位数月份
        let dayStr = ("0" + date.getDate()).slice(-2, 8); // 拼接2位数日期
        let result = "";
        if (!flag) {
            result = yearStr + "年" + monthStr + "月" + dayStr + "日";
        } else {
            result = yearStr + flag + monthStr + flag + dayStr;
        }
        return result;
    }
};


/**
 * TODO 刷新页面滚动条还原工具  记录/还原滚动条位置
 * @type {{onbeforeunload: WindowPos.onbeforeunload, onload: WindowPos.onload}}
 */
WindowPos = {

    /**
     * 记录浏览器滚动条位置
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/4/27 0027 17:42
     * @return
     */
    onbeforeunload: function () {
        let scrollPos;
        if (typeof window.pageYOffset != 'undefined') {
            scrollPos = window.pageYOffset;
        } else if (typeof document.compatMode != 'undefined' &&
            document.compatMode !== 'BackCompat') {
            scrollPos = document.documentElement.scrollTop;
        } else if (typeof document.body != 'undefined') {
            scrollPos = document.body.scrollTop;
        }
        document.cookie = "scrollTop=" + scrollPos; //存储滚动条位置到cookies中
    },


    /**
     * 还原浏览器滚动条位置
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/4/27 0027 17:42
     * @return
     */
    onload: function () {
        if (document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
            let arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
            document.documentElement.scrollTop = parseInt(arr[1]);
            document.body.scrollTop = parseInt(arr[1]);
        }
    }
};

/**
 * TODO 字符串处理工具
 * @type {{byteToString: StringUtils.byteToString, stringToByte: (function(*): any[])}}
 */
StringUtils = {
    /**
     * byte转字符串
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/3/29 0029 23:54
     * @return
     * @param str
     */
    stringToByte: function (str) {
        let bytes = new Array();
        let len, c;
        len = str.length;
        for (let i = 0; i < len; i++) {
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
    },


    /**
     * 字符串转byte
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/3/29 0029 23:54
     * @return
     * @param arr
     */
    byteToString: function (arr) {
        if (typeof arr === 'string') {
            return arr;
        }
        let str = '',
            _arr = arr;
        for (let i = 0; i < _arr.length; i++) {
            let one = _arr[i].toString(2),
                v = one.match(/^1+?(?=0)/);
            if (v && one.length == 8) {
                let bytesLength = v[0].length;
                let store = _arr[i].toString(2).slice(7 - bytesLength);
                for (let st = 1; st < bytesLength; st++) {
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
};


/**
 * TODO base64 加密/解密工具
 * @author 王松
 * @mail  1720696548@qq.com
 * @date  2020/7/11 0011 21:16
 */
Base64 = {
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
    encode: function (e) {
        var t = "";
        var n, r, i, s, o, u, a;
        var f = 0;
        e = Base64._utf8_encode(e);
        while (f < e.length) {
            n = e.charCodeAt(f++);
            r = e.charCodeAt(f++);
            i = e.charCodeAt(f++);
            s = n >> 2;
            o = (n & 3) << 4 | r >> 4;
            u = (r & 15) << 2 | i >> 6;
            a = i & 63;
            if (isNaN(r)) {
                u = a = 64
            } else if (isNaN(i)) {
                a = 64
            }
            t = t + this._keyStr.charAt(s) + this._keyStr.charAt(o) + this._keyStr.charAt(u) + this._keyStr.charAt(a)
        }
        return t
    },
    decode: function (e) {
        var t = "";
        var n, r, i;
        var s, o, u, a;
        var f = 0;
        e = e.replace(/[^A-Za-z0-9+/=]/g, "");
        while (f < e.length) {
            s = this._keyStr.indexOf(e.charAt(f++));
            o = this._keyStr.indexOf(e.charAt(f++));
            u = this._keyStr.indexOf(e.charAt(f++));
            a = this._keyStr.indexOf(e.charAt(f++));
            n = s << 2 | o >> 4;
            r = (o & 15) << 4 | u >> 2;
            i = (u & 3) << 6 | a;
            t = t + String.fromCharCode(n);
            if (u != 64) {
                t = t + String.fromCharCode(r)
            }
            if (a != 64) {
                t = t + String.fromCharCode(i)
            }
        }
        t = Base64._utf8_decode(t);
        return t
    }, _utf8_encode: function (e) {
        e = e.replace(/rn/g, "n");
        var t = "";
        for (var n = 0; n < e.length; n++) {
            var r = e.charCodeAt(n);
            if (r < 128) {
                t += String.fromCharCode(r)
            } else if (r > 127 && r < 2048) {
                t += String.fromCharCode(r >> 6 | 192);
                t += String.fromCharCode(r & 63 | 128)
            } else {
                t += String.fromCharCode(r >> 12 | 224);
                t += String.fromCharCode(r >> 6 & 63 | 128);
                t += String.fromCharCode(r & 63 | 128)
            }
        }
        return t
    }, _utf8_decode: function (e) {
        var t = "";
        var n = 0;
        var r = c1 = c2 = 0;
        while (n < e.length) {
            r = e.charCodeAt(n);
            if (r < 128) {
                t += String.fromCharCode(r);
                n++
            } else if (r > 191 && r < 224) {
                c2 = e.charCodeAt(n + 1);
                t += String.fromCharCode((r & 31) << 6 | c2 & 63);
                n += 2
            } else {
                c2 = e.charCodeAt(n + 1);
                c3 = e.charCodeAt(n + 2);
                t += String.fromCharCode((r & 15) << 12 | (c2 & 63) << 6 | c3 & 63);
                n += 3
            }
        }
        return t
    }
};

