

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
        let dictData = Ajax.get(path + "/client/dictionary/findCodeGroup");
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
     *    order        排序(是否正序，true是，false 否)
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
    getDictSelect: function (enumKay, code, name, defaultVal,order) {
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
        let dictMap = Dict.dictMapSort(Dict.getDict(enumKay).dictMap,order);
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
     *  复选框
     *  使用示例： $("#genderCodess").html(Dict.getDictCheckbox(Enums.Base.Default, "genderCodes", parent.data.genderCodes));
     *  参数说明：
     *  enumKay 枚举key
     *  name       字段名
     *  defaultVal 默认选中
     *
     *  <div class="layui-form-item" pane="">
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
    getDictCheckbox: function (enumKay, name, defaultVal) {
        // 获取排序后的字典List列表(数组)
        let dictMap = Dict.dictMapSort(Dict.getDict(enumKay).dictMap);
        //
        let checkboxTemplates = "\r\n" + "<input type='checkbox' name='{name}' value='{value}' lay-skin='primary' title='{title}' {checked}>" +
            "<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
            "<span>{title}</span><i class='layui-icon layui-icon-ok'></i></div>";
        "</div>";
        //默认值/回显值
        let defaults = [];
        if (defaultVal != null) {
            defaults = defaultVal.split(",");
        }
        let html = "";
        for (let i = 0; i < dictMap.length; i++) {
            if (defaults.includes(dictMap[i].code)) {
                html += checkboxTemplates
                    .replace("{name}", name)
                    .replace("{title}", dictMap[i].name)
                    .replace("{title}", dictMap[i].name)
                    .replace("{value}", dictMap[i].code)
                    .replace("{checked}", "checked");
            } else {
                html += checkboxTemplates
                    .replace("{name}", name)
                    .replace("{title}", dictMap[i].name)
                    .replace("{title}", dictMap[i].name)
                    .replace("{value}", dictMap[i].code)
                    .replace("{checked}", "");
            }
        }
        return html;
    },


    /**
     * 根据sort字段排序,
     * @param dictMap 字典
     * @param order 排序(是否正序，true是，false 否)
     */
    dictMapSort: function (dictMap, order) {
        let array = [];
        for (let index in dictMap) {
            array.push(dictMap[index]);
        }
        if (order == null || order) {
            array.sort(function (a, b) {
                return a.sort - b.sort;
            });
        } else {
            array.sort(function (a, b) {
                return b.sort - a.sort;
            });
        }
        return array;
    },


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
