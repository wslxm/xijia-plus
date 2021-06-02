

/**
 * 页面滚动位置记录/还原， 刷新页面还原浏览位置工具
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
