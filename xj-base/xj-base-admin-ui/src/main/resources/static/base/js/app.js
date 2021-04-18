document.write("<script language=javascript src='/base/js/SignMD5.js'></script>");

/**
 * 后台接口访问地址
 * 后台文件上传接口
 * 本地 "http://127.0.0.1:9049" ||  线上ip地址： http://47.107.128.84:9049 ||  线上域名地址： http://xijia.plus
 */
var path = "/api";
var uploadPath = "/api/open/aliOssFile/upload";


// 访问秘钥(如果后台开启)
var loginPwd = "xijia";


/**
 * 系统相关配置
 */
var BaseConfig = {
    token: "TOKEN",               // token 命名
};


var Enums = {
    // 系统模块枚举
    Admin: {
        AuthorityState: "AUTHORITY_STATE",  // 权限状态
        AuthorityType: "AUTHORITY_TYPE",  // 权限类型
        MenuRoot: "MENU_ROOT",  // 菜单级别
        Position: "POSITION",  // 职位
    },
    // 通用枚举
    Base: {
        Deleted: "DELETED",  // 逻辑删除
        Disable: "DISABLE",  // 是否禁用
        Gender: "GENDER",  // 性别
        IsRead: "IS_READ",  // 是否已读
    },
    // 支付枚举
    Pay: {
        PayBusiness: "PAY_BUSINESS",  // 支付业务
        PayChannel: "PAY_CHANNEL",  // 支付渠道
        PayState: "PAY_STATE",  // 支付状态
        PayType: "PAY_TYPE",  // 支付类型
        WalletType: "WALLET_TYPE",  // 流水类型
    },
    // 系统增强功能枚举
    Xj: {
        BannerIsSkip: "BANNER_IS_SKIP",  // banner是否跳转
        BannerPosition: "BANNER_POSITION",  // banner 位置
        BlacklistType: "BLACKLIST_TYPE",  // 黑/白名单类型
        FileType: "FILE_TYPE",  // 文件类型
        HelpCategory: "HELP_CATEGORY",  // 帮助中心类别
        HelpVersion: "HELP_VERSION",  // 帮助中心版本
        MsgType: "MSG_TYPE",  // 及时消息类型
        MsgUserType: "MSG_USER_TYPE",  // 及时消息终端
    },
};


/**
 * 枚举字典通用方法
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
        // let version = Ajax.get(path + "/admin/adminDictionary/findVersion").data;
        // let dictCache = localStorage.getItem('dictCache');
        // if (dictCache == null || version !== JSON.parse(dictCache).VERSION.version) {
        //从后台获取字典数据并缓存到localStorage
        let dictData = Ajax.get(path + "/admin/adminDictionary/findCodeGroup");
        localStorage.setItem('dictCache', JSON.stringify(dictData.data));  //
        // }
    },

    /**
     * 枚举转换工具类 --> 接口返回的状态值(数字)转换字典的Name值
     */
    convert: function (enumKay, code) {
        //获取到指定key下的枚举对象
        let dictKeyCache = Dict.getDict(enumKay);
        if (dictKeyCache == null) {
            return null;
        } else {
            return dictKeyCache.dictMap[code] == null ? "" : dictKeyCache.dictMap[code].name;
        }
    },

    /**
     * 枚举转换工具类 --> 接口返回的状态值(数字)转换字典的对象
     */
    convertDict: function (enumKay, code) {
        //获取到指定key下的枚举对象
        let dictKeyCache = Dict.getDict(enumKay);
        return dictKeyCache == null ? null : dictKeyCache.dictMap[code];
    },


    /**
     * 获取指定枚举下的select,单选，多选列表数据
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
     *  下拉框 ：  拼接 select 的 option 列表
     *  使用示例： $("#gender").html(Dict.getDictSelect(Enums.Base.Gender, null, null, parent.data.gender));          //性别
     *  参数说明：
     *    enumKay      枚举key,对应枚举标题code
     *    code         设置默认参数key
     *    name         设置默认参数value (与key同时存在才生效)
     *    defaultVal   默认选中值,对应字典code,  如果传递null, 默认选中第一条数据 (默认选中,和数据回显使用)
     *
     *  参考示例：
     *     <div class="layui-form-item">
     *          <label class="layui-form-label">是否跳转</label>
     *          <div class="layui-input-inline">
     *          <select id="isSkip" name="isSkip" lay-verify="" lay-filter="required">
     *              不带默认值:  $("#isSkip").html(Dict.getDictSelect(Enums.Admin.BannerIsSkip));
     *              带默认值:    $("#isSkip").html(Dict.getDictSelect(Enums.Admin.BannerIsSkip,-1,"默认值"));
     *          </select>
     *          </div>
     *     </div>
     */
    getDictSelect: function (enumKay, code, name, defaultVal) {
        //不填默认值
        let html = "";
        if (code != null && name != null) {
            if (defaultVal == null) {
                html = "<option value='" + code + "' selected>" + name + "</option>"
            } else {
                html = "<option value='" + code + "'>" + name + "</option>"
            }
        }
        // 获取排序后的字典List列表(数组)
        let dictMap = Dict.dictMapSort(Dict.getDict(enumKay).dictMap);
        //
        for (let i = 0; i < dictMap.length; i++) {
            if (defaultVal == null) {
                if (i === 0 && (code == null && name == null)) {
                    html += "<option value='" + dictMap[i].code + "' selected>" + dictMap[i].name + "</option>";
                } else {
                    html += "<option value='" + dictMap[i].code + "'>" + dictMap[i].name + "</option>";
                }
            } else {
                if (dictMap[i].code === defaultVal) {
                    html += "<option value='" + dictMap[i].code + "' selected>" + dictMap[i].name + "</option>";
                } else {
                    html += "<option value='" + dictMap[i].code + "'>" + dictMap[i].name + "</option>";
                }
            }
        }
        return html;
    },


    /**
     * 单选
     * 使用示例：  $("#position").html(Dict.getDictRadio(Enums.Admin.Position, "position", parent.data.position));
     * 参数说明：
     *        参数1： enumKay 枚举key,对应枚举标题code
     *        参数2： Radio 的 name值，表单提交时的参数名
     *        参数3： defaultVal ,默认选中值,对应字典code,  如果传递null, 默认选中第一条数据 (默认选中,和数据回显使用)
     * 示例DIV：
     * <div class="layui-form-item">
     *    <label class="layui-form-label">职位</label>
     *    <div id="position" class="layui-input-block">
     *         <input type="radio" name="position" value="男" title="男" checked=""><div class="layui-unselect layui-form-radio "><i class="layui-anim layui-icon"></i><div>男</div></div>
     *    </div>
     * </div>
     * @returns {string }  input 动态数据
     */
    getDictRadio: function (enumKay, name, defaultVal) {
        // 获取排序后的字典List列表(数组)
        let dictMap = Dict.dictMapSort(Dict.getDict(enumKay).dictMap);
        // 动态参数值模板
        let templates = "<input type='radio' name='{name}' value='{value}' title='{title}' {checked}>" +
            "<div class='layui-unselect layui-form-radio'><i class='layui-anim layui-icon'></i><div>{title}</div></div>";

        let html = "";
        for (let i = 0; i < dictMap.length; i++) {
            html += templates
                .replace("{name}", name)
                .replace("{value}", dictMap[i].code)
                .replace("{title}", dictMap[i].name);
            // 设置默认选中值
            if (defaultVal == null) {
                if (i == 0) {
                    html = html.replace("{checked}", "checked");
                } else {
                    html = html.replace("{checked}", "")
                }
            } else {
                if (defaultVal == dictMap[i].code) {
                    html = html.replace("{checked}", "checked")
                } else {
                    html = html.replace("{checked}", "")
                }
            }
        }
        return html;
    },


    /**
     *    <div class="layui-form-item" pane="">
     *          <label class="layui-form-label">原始复选框</label>
     *          <div class="layui-input-block">
     *            <input type="checkbox" name="like1[write]" lay-skin="primary" title="写作" checked="">
     *                <div class="layui-unselect layui-form-checkbox layui-form-checked" lay-skin="primary">
     *                    <span>写作</span><i class="layui-icon layui-icon-ok"></i>
     *                </div>
     *
     *
     *            <input type="checkbox" name="like1[read]" lay-skin="primary" title="阅读"><div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>阅读</span><i class="layui-icon layui-icon-ok"></i></div>
     *            <input type="checkbox" name="like1[game]" lay-skin="primary" title="游戏" disabled=""><div class="layui-unselect layui-form-checkbox layui-checkbox-disbaled layui-disabled" lay-skin="primary"><span>游戏</span><i class="layui-icon layui-icon-ok"></i></div>
     *          </div>
     *    </div>
     * @param dictMap
     */
    // getDictCheckbox: function (enumKay) {
    //     // 获取排序后的字典List列表(数组)
    //
    //     let dictMap = Dict.dictMapSort(Dict.getDict(enumKay).dictMap);
    //     //
    //     let checkboxTemplates = "<input type=\"checkbox\" name=\"{name}\" lay-skin=\"primary\" title=\"{title}\" checked=\"\">" +
    //         "<div class=\"layui-unselect layui-form-checkbox layui-form-checked\" lay-skin=\"primary\">";
    //     "<span>{title}</span><i class=\"layui-icon layui-icon-ok\"></i>";
    //     "</div>";
    //
    //     let html = "";
    //     for (let i = 0; i < dictMap.length; i++) {
    //         html += checkboxTemplates
    //             .replace("{name}", dictMap[i].code)
    //             .replace("{title}", dictMap[i].name)
    //     }
    //     return html;
    // },


    /**
     * 根据sort字段排序
     * @param array
     */
    dictMapSort: function (dictMap) {
        let array = [];
        for (let index in dictMap) {
            array.push(dictMap[index]);
        }
        array.sort(function (a, b) {
            return a.sort - b.sort
        });
        return array;
    }
    ,


    /**
     * 测试方法
     *  id        // 数据主键ID
     *  code      // 字典类型
     *  name;     // 字典名称
     *  pid;      // 父Id
     *  desc;     // 描叙
     *  dictMap；  // 下级
     */
    getDictTest: function () {
        // 测试中的 .name 可以获取上方注释中的任意参数
        console.log("项目code=1:   ==>   " + Dict.convert(Enums.Base.Gender, 1));
        console.log("项目code=1:  ==>   " + Dict.convertDict(Enums.Base.Gender, 1));
        console.log("项目code=1:   ==>   " + Dict.convertDict(Enums.Base.Gender, 1).name);

        // 打印
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Base.Gender));
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Base.Gender).dictMap[0].name);
        console.log("所有项目:   ==>   " + Dict.getDict(Enums.Base.Gender).dictMap[1].name);
    }
}
;


Pop = {
    /**
     *
     * 居中弹出 ===>  Layer 添加/修改通用弹出层 添加修改删除通用弹出层
     * @param url    请求地址
     * @param width  弹出层宽
     * @param height 弹出层高
     * @param name   弹出层名
     */
    tipsWindown: function (url, width, height, name, btn) {
        if (btn == null) {
            btn = ['确定', '取消']
        }
        layui.use('layer', function () {
            layer.open({
                type: 2,
                title: [name],
                area: [width, height],  // area: ["600px", "200px"],
                offset: 'auto',        // 弹出位置,参考文档：https://www.layui.com/doc/modules/layer.html#offset
                content: [url],         // page/menu_addRoot1
                btn: btn,
                closeBtn: 1,
                anim: 0,       // 打开动画  ,参考：https://www.layui.com/doc/modules/layer.html#anim
                maxmin: true,  // 开启最大化最小化按钮
                resize: true,  // 是否可以拉伸(右下拉伸)
                fixed: false,  // 是否固定窗口
                moveOut: true, // 是否允许拖拽到窗口外
                zIndex: 1000,  // 层高(拉出位置位于其他底层)
                shadeClose: true, // 是否允许点击任意地方关闭窗口
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
     * 右侧弹出 ===>  Layer 添加/修改通用弹出层 添加修改删除通用弹出层
     * <P>
     *     Pop.tipsWindownRight("",null,null,'名称',[])
     * </P>
     * @author wangsong
     * @date 2021/3/9 0009 17:45
     * @return
     * @version 1.0.0
     */
    tipsWindownRight: function (content, width, height, name, btn) {
        if (btn == null) {
            btn = ['确定', '取消']
        }
        if (height == null) {
            height = '100%'
        }
        if (width == null) {
            width = '300px'
        }
        layui.use('layer', function () {
            layer.open({
                type: 1
                // , id: 'LAY_adminPopupR'
                , anim: -1
                , offset: 'r'
                , shade: 0.1
                , btn: btn
                , closeBtn: 1
                , skin: 'layui-anim layui-anim-rl layui-layer-adminRight'
                , content: content
                , area: [width, height]
                , title: [name]
                , zIndex: 1000,    // 层高(拉出位置位于其他底层)
                shadeClose: true,  // 是否允许点击任意地方关闭窗口
                crossDomain: true, // 跨域，https://www.cnblogs.com/autoXingJY/p/11419860.html
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
        })
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
    }
    ,


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
}
;


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


// AJAX 请求工具封装
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
        // url 空格处理
        url = encodeURI(url);
        // 参数加签
        let timestamp = new Date().getTime();
        let sign = Sign.query(url, timestamp);
        sign = sign != null ? sign : Sign.body(data, timestamp);
        // 发起请求
        $.ajax({
            type: type,
            dataType: dataType,
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",  //"application/x-www-form-urlencoded;charset=utf-8",
            headers: {
                "TOKEN": getGlobalHeaders(),
                "timestamp": timestamp,
                "sign": sign,
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
            }
        });
        //错误打印
        if (result.code !== 200) {
            // 10003 = 没有token
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
 * 加签
 * <P>
 *   验签
 *  <P>
 * account=1720696548&password=123456&timestamp=1578811547552
 * sign,timestamp
 * String mysign = DigestUtils.md5Hex(getContentBytes(preSignStr + APP_KEY, INPUT_CHARSET));
 * @author wangsong
 * @mail  1720696548@qq.com
 * @date  2021/3/29 0029 22:10
 * @version 1.0.0
 */
Sign = {
    /**
     * 加签秘钥
     */
    param: {
        appKey: "xijia",
        secretKey: "xijia@qwer",
    },
    /**
     * 1、query 参数加签
     * <p>
     *     1、获取请求参数, 直接获取url ? 后面的参数
     *
     *     // --- addSing 方法
     *     2、追加 timestamp
     *     3、参数对象根据 key 排序(包括 timestamp)
     *     4、md5(appKey + 排序后的参数 + secretKey)
     *
     *     // --- 请求
     *     5、sign + timestamp 放入 headers 进行请求
     *     其他：空val的对象不加入验签范围
     * </p>
     * @param url 请求的 url
     * @param timestamp 时间戳
     * @returns {string|null}
     */
    query: function (url, timestamp) {
        if (url.indexOf("?") === -1) {
            return null;
        }
        // 通过split()分割为数组
        let arr = url.split('?')[1].split('&');
        // 加签参数, query的所有参数
        let theRequest = {};
        for (let i = 0; i < arr.length; i++) {
            let kye = arr[i].split("=")[0];
            let value = arr[i].split("=")[1];
            if (value != null) {
                // value不为null给对象赋值，decodeURIComponent目的是为了参数出现 # 等的字符,在请求前进行了encodeURIComponent编码
                theRequest[kye] = decodeURIComponent(value);
            }
        }
        return Sign.addSing(theRequest, timestamp);

    },

    /**
     * 2、body 参数加签
     * <p>
     *     1、获取请求参数, 对 body 的参数内的参数进行key排序 (包括所有下级数据排序), 然后转化为json字符串
     *       - 并以 query格式拼接参数，示例: body= JSON(key排序后data)
     *
     *     // --- addSing 方法
     *     2、追加query格式的参数 timestamp
     *     3、参数对象根据 key 排序, 只有 body + timestamp 两个参数
     *     4、md5(appKey + 排序后的参数 + secretKey)
     *
     *     // ---- 请求
     *     5、sign + timestamp 放入 headers 进行请求
     *     其他：空val的对象不加入验签范围
     * </p>
     * @param data body数据
     * @param timestamp 当前数据戳
     * @returns {null}
     */
    body: function (data, timestamp) {
        if (data == null) {
            return null;
        }
        // body参数排序
        data = Sign.bodyDataSort(data);
        // 转为json
        let bodyJson = JSON.stringify(data);
        // 加签参数
        let theRequest = {};
        theRequest["body"] = bodyJson;
        return Sign.addSing(theRequest, timestamp);
    },

    /**
     * 3、加签实现
     *    3、参数对象根据 key 排序, 只有 body + timestamp 两个参数
     *    4、md5(appKey + 排序后的参数 + secretKey)
     * @returns {*}
     */
    addSing: function (theRequest, timestamp) {
        theRequest["timestamp"] = timestamp;
        // 排序并重新封装获得排序后的参数
        let dataParams = "";
        let result = Object.keys(theRequest).sort();
        for (let key of result) {
            dataParams += "&" + key + "=" + theRequest[key];
        }
        dataParams = dataParams.substring(1);
        //TODO 加签参数(部署时请注释)
        console.log("加签参数：" + dataParams);
        // 加签
        return md5(Sign.param.appKey + dataParams + Sign.param.secretKey);
    },

    /**
     * 4、body 参数排序
     * @returns {*}
     */
    bodyDataSort: function (data) {
        // 数组长度小于2 或 没有指定排序字段 或 不是json格式数据
        // 判断是数组还是对象
        if (data instanceof Array) {
            /**
             * 数组
             */
            if (data[0] instanceof Object || data[0] instanceof Array) {
                // 数组的下级是对象或者是数组
                let arrays = data;
                let newArrays = [];
                for (let i = 0; i < arrays.length; i++) {
                    // 获取每一个下级->  a:{a,b,c}
                    let dataTwo = arrays[i];
                    // 根据 key 类型排序
                    let keysTwo = Object.keys(dataTwo).sort();
                    let newDataTwo = {};
                    // 遍历 key/value
                    for (let keyTwo of keysTwo) {
                        // 下级是对象, 继续递归排序
                        if (dataTwo[keyTwo] instanceof Object) {
                            newDataTwo[keyTwo] = this.bodyDataSort(dataTwo[keyTwo]);
                        } else {
                            if ((dataTwo[keyTwo] != null)) {
                                newDataTwo[keyTwo] = dataTwo[keyTwo];
                            }
                        }
                    }
                    newArrays.push(newDataTwo);
                }
                return newArrays;
            } else {
                // 不处理: [0,1,2,3] 数组数据
                return data;
            }
        } else if (data instanceof Object) {
            /**
             *  对象
             */
            // 根据 key 类型排序
            let keys = Object.keys(data).sort();
            let newData = {};
            // 遍历 key/value
            for (let key of keys) {
                if (data[key] instanceof Object) {
                    // 下级是对象, 继续递归排序
                    newData[key] = this.bodyDataSort(data[key]);
                } else {
                    if (data[key] != null) {
                        newData[key] = data[key];
                    }
                }
            }
            return newData;
        } else {
            return data;
        }
    },
};

/**
 * 全局请求头
 * @returns {{TOKEN: string}}
 */
function getGlobalHeaders() {
    return sessionStorage.getItem(BaseConfig.token) == null ? "" : sessionStorage.getItem(BaseConfig.token);
}


/**
 * 时间计算工具
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
    },
    //时间戳转换方法    date:时间戳数字
    formatDate: function (date) {
        var date = new Date(date);
        var YY = date.getFullYear() + '-';
        var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
        var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
        var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
        var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
        return YY + MM + DD + " " + hh + mm + ss;
    },
    /**
     * 获取两个时间的时间戳
     * @param faultDate
     * @param completeTime
     * @returns {number}
     * @constructor
     */
    DateDifference(faultDate, completeTime) {
        // let d1 = new Date(faultDate);
        // let d2 = new Date(completeTime);
        var stime = new Date(faultDate).getTime();
        var etime = new Date(completeTime).getTime();
        var usedTime = etime - stime;  //两个时间戳相差的毫秒数
        var days = Math.floor(usedTime / (24 * 3600 * 1000));
        //计算出小时数
        var leave1 = usedTime % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000));
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000));
        var time = days + "天" + hours + "时" + minutes + "分";
        // var time = days;
        return time;
    }
};


/**
 * 刷新页面滚动条还原工具  记录/还原滚动条位置
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
 * 字符串处理工具
 * @type {{byteToString: StringUtils.byteToString, stringToByte: (function(*): any[])}}
 */
StringUtils = {
    /**
     *字符串 转 byte
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
     * byte转字符串
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
    },
    /**
     * 把想要校验的字符串作为参数传给checkNumber 这个函数，若是纯数字则会返回true，若不是纯数字则会返回false
     * @param String
     * @returns {boolean}
     */
    checkNumber: function (String) {
        let reg = /^[0-9]+.?[0-9]*$/
        if (reg.test(String)) {
            return true
        }
        return false
    }
};


/**
 * base64 加密/解密工具
 * @author 王松
 * @mail  1720696548@qq.com
 * @date  2020/7/11 0011 21:16
 */
Base64 = {
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
    //加码
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
    //解码
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


/**
 * 获取url 参数
 * @author wangsong
 * @param null
 * @date 2020/12/13 0013 0:59
 * @return
 * @version 1.0.0
 */
function getParam(name) {
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


