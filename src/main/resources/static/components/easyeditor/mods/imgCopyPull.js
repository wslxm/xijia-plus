/**
 * Md 图片粘贴，qq截图/微信截图/网络复制图片， 拉跩上传（本地文件随便拉取到内容框）
 * @author 王松
 * @mail  1720696548@qq.com
 * @date  2020/10/24 0024 13:25
 * @version 1.0.0
 */
/**
 * 监听粘贴( qq截图/微信截图/网络复制图片，直接 ctrl+v粘贴 或 右键粘贴直接上传图片并处理格式，本地图片不支持)
 * <P>
 *     注意：Markdown编辑器元素的 textarea 的id值必须为 L_content  ，如果不同，替换下方所有 L_content 为当前容器的Id值
 *     参考地址：https://blog.csdn.net/weixin_30617561/article/details/99002642
 * </P>
 *
 * @author wangsong
 * @param null
 * @date 2020/10/24 0024 11:07
 * @return
 * @version 1.0.0
 */
// $("#L_content").on('paste', function (eventObj) {
document.addEventListener('paste', function (event) {
    console.log(event);
    let isChrome = false;
    if (event.clipboardData || event.originalEvent) {
        //某些chrome版本使用的是event.originalEvent
        const clipboardData = (event.clipboardData || event.originalEvent.clipboardData);
        if (clipboardData.items) {
            // for chrome
            let imageRe = new RegExp(/image\/.*/);
            let fileList = $.map(event.clipboardData.items, function (o) {
                if (!imageRe.test(o.type)) {
                    return
                }
                let blob = o.getAsFile();
                return blob;
            });
            if (fileList.length <= 0) {
                return
            }
            upload(fileList);
            //阻止默认行为即不让剪贴板内容在div中显示出来
            event.preventDefault();
        }
    }
});


/**
 * 拉跩上传（本地文件随便拉取到内容框）
 * <P>
 *     注意1：Markdown编辑器元素的 textarea 的id值必须为 L_content  ，如果不同，替换下方所有 L_content 为当前容器的Id值
 *     注意2：依赖 jquery，无jquery无法执行，1、引入jquery  2、可修改为js实现，比较麻烦
 *     参考地址：https://blog.csdn.net/lian772882/article/details/81455063?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase
 * </P>
 * @author wangsong
 * @mail  1720696548@qq.com
 * @date  2020/10/24 0024 11:30
 * @version 1.0.0
 */

// 阻止拖放的图片在新窗口中直接显示，拖放的目标对象此时是document对象
document.ondragover = function (e) {
    e.preventDefault(); //使得ondrop可能触发
};
// 阻止浏览器在新窗口中打开本地图片
document.ondrop = function (e) {
    e.preventDefault();
};
// 为#L_content做释放事件的监听, Markdown编辑器元素的 textarea 的id值必须为L_content
L_content.ondragover = function (e) {
    e.preventDefault();//使得ondrop可能触发
};
// 获取拖动文件上传处理, Markdown编辑器元素的 textarea 的id值必须为L_content
L_content.ondrop = function (e) {
    // 读取浏览器在源对象拖动时在“拖拉机”中保存的数据
    //console.log(e.dataTransfer);
    //console.log(e.dataTransfer.files); //FileList
    let fileList = [];
    //用户拖动进来的文件
    let files = e.dataTransfer.files;
    let imageRe = new RegExp(/image\/.*/);
    for (let i = 0; i < files.length; i++) {
        if (!files[i].type.match(imageRe)) {
            continue
        }
        fileList.push(files[i]);
    }
    if (fileList.length > 0) {
        upload(fileList);
    } else {
        alert("不支持拉拽非图片的文件")
    }
};


/**
 * 文件上传 AJAX 请求上传（需jquery支持）
 * @author wangsong
 * @date 2020/10/24 0024 9:47
 * @return
 * @version 1.0.0
 */
function upload(fileList) {
    // 获取鼠标放置位置
    let cursorPosition = getTxt1CursorPosition();
    for (let i = 0; i < fileList.length; i++) {
        let fd = new FormData();
        fd.append('file', fileList[i]);
        $.ajax({
            url: path + "/aliOssFile/upload?filePath=image/markdown/",
            type: 'POST',
            dataType: 'json',
            data: fd,
            processData: false,
            contentType: false,
            xhrFields: {withCredentials: true},
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            success: function (res) {
                if (res.code === 200) {
                    //let img = document.createElement('img');
                    // 设置上传完图片之后展示的图片
                    let imgUrl = res.data;
                    // md 文本数据处理
                    let value = "![图片未命名](" + imgUrl + ")\n";
                    //========================================
                    // 获取原数据
                    let oldVal = $("#L_content").val();
                    // 获取光标前的数据
                    let oldValFront = oldVal.substring(0, cursorPosition);
                    // 获取光标后的数据
                    let oldValAfter = oldVal.substring(cursorPosition);
                    // 插入数据
                    $("#L_content").val(oldValFront  + value + oldValAfter);
                    // 多图上传更新光标起点
                    cursorPosition += value.length;
                } else {
                    alert(res.msg);
                }
            },
            error: function () {
                alert("上传图片错误");
            }
        });

    }


    // 获取光标的索引位置
    function getTxt1CursorPosition() {
        let oTxt1 = document.getElementById("L_content");
        let cursurPosition = -1;
        if (oTxt1.selectionStart) {//非IE浏览器
            cursurPosition = oTxt1.selectionStart;
        } else {//IE
            let range = document.selection.createRange();
            range.moveStart("character", -oTxt1.value.length);
            cursurPosition = range.text.length;
        }
        return cursurPosition;
        //  alert(cursurPosition);
    }
}