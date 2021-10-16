

/**
 * 添加/编辑/删除通用弹层
 */
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
     * @version 1.0.1
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
