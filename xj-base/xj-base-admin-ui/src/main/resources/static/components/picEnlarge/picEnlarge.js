
// 放大图片
/**
 * 给所有图片添加点击事件
 * @author wangsong
 * @date 2020/12/15 0015 10:02
 * @return
 * @version 1.0.0
 */
function addExpand() {
    var imgs = document.getElementsByTagName("img");
    if(imgs.length > 0){
        imgs[0].focus();
        for (var i = 0; i < imgs.length; i++) {
            imgs[i].onclick = expandPhoto;
            imgs[i].onkeydown = expandPhoto;
        }
    }
}


/**
 * 点击放大/缩小
 * @author wangsong
 * @date 2020/12/15 0015 10:02
 * @return
 * @version 1.0.0
 */
function expandPhoto() {
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "overlay");
    overlay.setAttribute("class", "overlay");
    document.body.appendChild(overlay);

    var img = document.createElement("img");
    img.setAttribute("id", "expand");
    img.setAttribute("class", "overlayimg");
    img.src = this.getAttribute("src");
    document.getElementById("overlay").appendChild(img);

    img.onclick = restore;
}
function restore() {
    document.body.removeChild(document.getElementById("overlay"));
    document.body.removeChild(document.getElementById("expand"));
}

