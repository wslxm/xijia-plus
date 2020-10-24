layui.define(["jquery", "layer", "form", "element", "upload", "code", "face"], function (a) {
    var b = layui.jquery, c = layui.layer, d = layui.form, e = layui.element, f = layui.upload, g = layui.face, h = layui.device();
    let i = new marked.Renderer;
    i.code = function (a) {
        return "<pre>" + a.replace(/\n/g, "<br>") + "</pre>"
    }, marked.setOptions({renderer: i, tables: !0, breaks: !0}), layui.focusInsert = function (a, b) {
        var c, d = a.value;
        a.focus(), document.selection ? (c = document.selection.createRange(), document.selection.empty(), c.text = b) : (c = [d.substring(0, a.selectionStart), b, d.substr(a.selectionEnd)], a.focus(), a.value = c.join(""))
    };
    let j = {
        init: function (a) {
            var g = ["<div class=\"layui-unselect fly-edit " + ("fangge" === a.style ? "easyeditor-fangge" : "") + "\" >", "<span type=\"face\" title=\"\u63D2\u5165\u8868\u60C5\"><i class=\"iconfont chengliangyun-md-icon-biaoqing\" style=\"top: 1px;\"></i></span>", "<span type=\"picture\" title=\"\u63D2\u5165\u56FE\u7247\uFF1Aimg[src]\"><i class=\"iconfont chengliangyun-md-icon-tupian\"></i></span>", "<span type=\"href\" title=\"\u8D85\u94FE\u63A5\u683C\u5F0F\uFF1Aa(href)[text]\"><i class=\"iconfont chengliangyun-md-icon-chaolianjie\"></i></span>", "<span type=\"code\" title=\"\u63D2\u5165\u4EE3\u7801\"><i class=\"iconfont chengliangyun-md-icon-daima\" style=\"top: 1px;\"></i></span>", "<span type=\"yinyong\" title=\"\u5F15\u7528\"><i class=\"iconfont chengliangyun-md-icon-blockquote\"></i></span>", "<span type=\"ul\" title=\"\u65E0\u5E8F\u5217\u8868\"><i class=\"iconfont chengliangyun-md-icon-wuxuliebiao\"></i></span>", "<span type=\"ol\" title=\"\u6709\u5E8F\u5217\u8868\"><i class=\"iconfont chengliangyun-md-icon-youxuliebiao\"></i></span>", "<span type=\"table\" title=\"\u8868\u683C\"><i class=\"iconfont chengliangyun-md-icon-biaoge\"></i></span>", "<span type=\"video\" title=\"\u89C6\u9891\"><i class=\"iconfont chengliangyun-md-icon-shipin\"></i></span>", "<span type=\"hr\" title=\"\u5206\u5272\u7EBF\">hr</span>", "<div class=\"fly-right\">", "<span type=\"yulan\"  title=\"\u9884\u89C8\"><i class=\"iconfont chengliangyun-md-icon-yulanyulan\"></i></span>", "<span type=\"fullScreen\"  title=\"\u5168\u5C4F\"><i class=\"iconfont chengliangyun-md-icon-quanping\"></i></span>", "</div>"].join(""),
                k = {
                    face: function (a, d) {
                        var e = "", f = j.faces;
                        for (var g in f) e += "<li title=\"" + g + "\"><img src=\"" + f[g] + "\"></li>";
                        e = "<ul id=\"LAY-editface\" class=\"layui-clear\">" + e + "</ul>", c.tips(e, d, {tips: 3, time: 0, skin: "layui-edit-face"}), b(document).on("click", function () {
                            c.closeAll("tips")
                        }), b("#LAY-editface li").on("click", function () {
                            var c = b(this).attr("title") + " ";
                            layui.focusInsert(a[0], "face" + c), a.trigger("keyup")
                        })
                    }, picture: function (e) {
                        a = a || {}, c.open({
                            type: 1,
                            id: "fly-jie-upload",
                            title: "\u63D2\u5165\u56FE\u7247",
                            area: "auto",
                            shade: !1,
                            area: "465px",
                            fixed: !1,
                            offset: [e.offset().top - b(window).scrollTop() + "px", e.offset().left + "px"],
                            skin: "layui-layer-border",
                            content: "<ul class=\"layui-form layui-form-pane\" style=\"margin: 20px;\"><li class=\"layui-form-item\"><label class=\"layui-form-label\">URL</label><div class=\"layui-input-inline\"><input required name=\"image\" placeholder=\"\u652F\u6301\u76F4\u63A5\u7C98\u8D34\u8FDC\u7A0B\u56FE\u7247\u5730\u5740\" value=\"\" class=\"layui-input\"></div><button type=\"button\" class=\"layui-btn layui-btn-primary\" id=\"uploadImg\"><i class=\"iconfont chengliangyun-md-icon-shangchuan\"></i>\u4E0A\u4F20\u56FE\u7247</button></li><li class=\"layui-form-item\" style=\"text-align: center;\"><button type=\"button\" lay-submit lay-filter=\"uploadImages\" class=\"layui-btn\">\u786E\u8BA4</button></li></ul>",
                            success: function (b, g) {
                                var h = b.find("input[name=\"image\"]");
                                (null == a.uploadUrl || "" == a.uploadUrl) && c.msg("\u672A\u914D\u7F6E\u56FE\u7247\u4E0A\u4F20\u8DEF\u5F84,\u56FE\u7247\u65E0\u6CD5\u4FDD\u5B58", {icon: 5}), f.render({
                                    elem: "#uploadImg", url: a.uploadUrl, size: a.uploadSize || 1024, done: function (a) {
                                        200 == a.code ? h.val(a.data) : c.msg(a.msg, {icon: 5})
                                    }
                                }), d.on("submit(uploadImages)", function (a) {
                                    var b = a.field;
                                    return b.image ? void (layui.focusInsert(e[0], "![\u56FE\u7247\u672A\u547D\u540D](" + b.image + ")\n"), c.close(g), e.trigger("keyup")) : h.focus()
                                })
                            }
                        })
                    }, href: function (a) {
                        c.prompt({title: "\u8BF7\u8F93\u5165\u5408\u6CD5\u94FE\u63A5", shade: !1, fixed: !1, id: "LAY_flyedit_href", offset: [a.offset().top - b(window).scrollTop() + "px", a.offset().left + "px"]}, function (b, d, e) {
                            return /^http(s*):\/\/[\S]/.test(b) ? void (layui.focusInsert(a[0], " [" + b + "](" + b + ")"), c.close(d), a.trigger("keyup")) : void c.tips("\u8FD9\u6839\u672C\u4E0D\u662F\u4E2A\u94FE\u63A5\uFF0C\u4E0D\u8981\u9A97\u6211\u3002", e, {tips: 1})
                        })
                    }, code: function (a) {
                        c.prompt({title: "\u8BF7\u8D34\u5165\u4EE3\u7801", formType: 2, maxlength: 1e4, shade: !1, id: "LAY_flyedit_code", area: ["800px", "360px"]}, function (b, d) {
                            layui.focusInsert(a[0], "\n~~~\n" + b + "\n~~~\n"), c.close(d), a.trigger("keyup")
                        })
                    }, yinyong: function (a) {
                        c.prompt({title: "\u8BF7\u8D34\u5165\u5F15\u7528\u5185\u5BB9", formType: 2, maxlength: 1e4, shade: !1, id: "LAY_flyedit_code", area: ["800px", "360px"]}, function (b, d) {
                            layui.focusInsert(a[0], "> " + b + "\n"), c.close(d), a.trigger("keyup")
                        })
                    }, hr: function (a) {
                        layui.focusInsert(a[0], "-----\n"), a.trigger("keyup")
                    }, ul: function (a) {
                        layui.focusInsert(a[0], "\n-  \n-  \n-  \n"), a.trigger("keyup")
                    }, ol: function (a) {
                        layui.focusInsert(a[0], "\n1. \n2. \n3. \n"), a.trigger("keyup")
                    }, table: function (a) {
                        layui.focusInsert(a[0], "\n\u8868\u5934|\u8868\u5934|\u8868\u5934\n:---:|:--:|:---:\n\u5185\u5BB9|\u5185\u5BB9|\u5185\u5BB9 \n"), a.trigger("keyup")
                    }, video: function (g) {
                        c.open({
                            type: 1,
                            id: "fly-jie-upload",
                            title: "\u63D2\u5165\u89C6\u9891",
                            shade: !1,
                            area: "465px",
                            fixed: !1,
                            offset: [g.offset().top - b(window).scrollTop() + "px", g.offset().left + "px"],
                            skin: "layui-layer-border",
                            content: "<ul class=\"layui-form layui-form-pane\" style=\"margin: 20px;\"><li class=\"layui-form-item\"><label class=\"layui-form-label\">\u5C01\u9762\u56FE</label><div class=\"layui-input-inline\"><input required name=\"image\" placeholder=\"\u652F\u6301\u8FDC\u7A0B\u56FE\u7247\u5730\u5740\" value=\"\" class=\"layui-input\"></div><button type=\"button\" class=\"layui-btn layui-btn-primary\" id=\"uploadImg\"><i class=\"iconfont chengliangyun-md-icon-shangchuan\"></i>\u4E0A\u4F20\u5C01\u9762</button></li><li class=\"layui-form-item\"><label class=\"layui-form-label\">\u89C6\u9891</label><div class=\"layui-input-inline\"><input required name=\"video\" placeholder=\"\u652F\u6301\u8FDC\u7A0B\u89C6\u9891\u5730\u5740\" value=\"\" class=\"layui-input\"></div><button type=\"button\" class=\"layui-btn layui-btn-primary\" id=\"uploadVideo\"><i class=\"iconfont chengliangyun-md-icon-shangchuan\"></i>\u4E0A\u4F20\u89C6\u9891</button><div class=\"layui-progress\" lay-filter=\"progress\" id=\"progress\" style=\"margin-right: 6px;margin-top: 4px;visibility:hidden;\" lay-showpercent=\"true\"><div class=\"layui-progress-bar\" lay-percent=\"20%\"></div></div></li><li class=\"layui-form-item\" style=\"text-align: center;\"><button type=\"button\" lay-submit lay-filter=\"uploadFile\" class=\"layui-btn\">\u786E\u8BA4</button></li></ul>",
                            success: function (b, h) {
                                var i = b.find("input[name=\"image\"]"), j = b.find("input[name=\"video\"]"), k = b.find("#progress");
                                (null == a.uploadUrl || "" == a.uploadUrl) && c.msg("\u672A\u914D\u7F6E\u56FE\u7247\u4E0A\u4F20\u8DEF\u5F84,\u56FE\u7247\u65E0\u6CD5\u4FDD\u5B58", {icon: 5}), (null == a.videoUploadUrl || "" == a.videoUploadUrl) && c.msg("\u672A\u914D\u7F6E\u89C6\u9891\u4E0A\u4F20\u8DEF\u5F84,\u89C6\u9891\u65E0\u6CD5\u4FDD\u5B58", {icon: 5}), f.render({
                                    elem: "#uploadImg",
                                    url: a.uploadUrl,
                                    size: a.uploadSize || 1024,
                                    done: function (a) {
                                        0 == a.code ? i.val(a.url) : c.msg(a.msg, {icon: 5})
                                    }
                                }), f.render({
                                    elem: "#uploadVideo", accept: "video", url: a.videoUploadUrl, size: a.videoUploadSize || 10240, done: function (a) {
                                        0 == a.code ? (k.css("visibility", "hidden"), i.val(a.url)) : c.msg(a.msg, {icon: 5})
                                    }, progress: function (a) {
                                        0 < a && k.css("visibility", "visible");
                                        e.progress("progress", a + "%"), 100 <= a
                                    }
                                }), d.on("submit(uploadFile)", function (a) {
                                    var b = a.field;
                                    return b.image ? b.video ? void (layui.focusInsert(g[0], "video(" + b.image + ")[" + b.video + "]\n"), c.close(h), g.trigger("keyup")) : j.focus() : i.focus()
                                })
                            }
                        })
                    }, fullScreen: function (c, d) {
                        b(window).resize(function () {
                            var c = 0;
                            window.innerHeight ? c = window.innerHeight : document.body && document.body.clientHeight && (c = document.body.clientHeight), document.documentElement && document.documentElement.clientHeight && (c = document.documentElement.clientHeight), b(a.elem).css("height", c - 40 + "px"), b(window).unbind("resize")
                        });
                        var e = b(d);
                        e.attr("type", "exitScreen"), e.attr("title", "\u9000\u51FA\u5168\u5C4F"), e.html("<i class=\"iconfont chengliangyun-md-icon-tuichuquanping\"></i>");
                        var f = document.documentElement, g = f.requestFullScreen || f.webkitRequestFullScreen || f.mozRequestFullScreen || f.msRequestFullscreen;
                        "undefined" != typeof g && g && g.call(f)
                    }, exitScreen: function (c, d) {
                        var e = b(d);
                        e.attr("type", "fullScreen"), e.attr("title", "\u5168\u5C4F"), e.html("<i class=\"iconfont chengliangyun-md-icon-quanping\"></i>");
                        document.documentElement;
                        document.exitFullscreen ? document.exitFullscreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitCancelFullScreen ? document.webkitCancelFullScreen() : document.msExitFullscreen && document.msExitFullscreen(), b(a.elem).css("height", "270px")
                    }, yulan: function (d, e) {
                        var f = b(e), g = function () {
                            var a = d.val();
                            return /^\{html\}/.test(a) ? a.replace(/^\{html\}/, "") : j.content(a)
                        }, i = h.ios || h.android;
                        return k.yulan.isOpen ? c.close(k.yulan.index) : void (k.yulan.index = c.open({
                            type: 1,
                            title: "\u9884\u89C8",
                            shade: !1,
                            offset: "r",
                            id: "LAY_flyedit_yulan",
                            area: [i ? "100%" : "50%", "100%"],
                            scrollbar: !i,
                            anim: -1,
                            isOutAnim: !1,
                            content: "<div class=\"detail-body layui-text easyeditor-content\" style=\"margin:20px;\">" + g() + "</div>",
                            success: function (b) {
                                let c = "layuiCode" === a.codeStyle;
                                c ? j.codeContent({elem: b.find("pre")}) : "", d.on("keyup", function () {
                                    b.find(".detail-body").html(g()), c ? j.codeContent({elem: b.find("pre")}) : ""
                                }), k.yulan.isOpen = !0, f.addClass("layui-this")
                            },
                            end: function () {
                                delete k.yulan.isOpen, f.removeClass("layui-this")
                            }
                        }))
                    }
                };
            layui.use("face", function (c) {
                a = a || {}, j.faces = c, b(a.elem).each(function () {
                    var a = this, c = b(a), d = c.parent();
                    d.prepend(g), d.find(".fly-edit span").on("click", function (d) {
                        var e = b(this).attr("type");
                        k[e].call(a, c, this), "face" === e && d.stopPropagation()
                    })
                });
                let d = a.buttonColor, e = a.hoverColor, f = a.hoverBgColor;
                b(".fly-edit span").css("color", d ? d : ""), b(".fly-edit span").hover(function () {
                    b(this).css("color", e ? e : "").css("background-color", f ? f : "")
                }, function () {
                    b(this).css("color", d ? d : "").css("background-color", "")
                }), "layuiCode" === a.codeStyle ? i.code = function (a) {
                    return "<pre>" + a + "</pre>"
                } : ""
            })
        }, codeContent: function (a) {
            let c = {elem: a.elem, title: "code", about: !1};
            "notepad" === a.codeSkin && (c.skin = "notepad"), b("pre").css("white-space", "pre-wrap"), layui.code(c)
        }, escape: function (a) {
            return ((a || "") + "").replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")
        }, content: function (a) {
            return marked(j.escape(a || "").replace(/  \n/g, "<br>").replace(/video\([\s\S]+?\)\[[\s\S]*?\]/g, function (a) {
                var b = (a.match(/video\(([\s\S]+?)\)\[/) || [])[1], c = (a.match(/\)\[([\s\S]*?)\]/) || [])[1];
                return c ? ["<video id=\"video\" controls=\"\" preload=\"none\" poster=\"" + b + "\">", "<source id=\"mp4\" src=\"" + c + "\" type=\"video/mp4\">", "</video>"].join("") : a
            })).replace(/<a href="\S.+">/g, function (a) {
                var b = (a.match(/<a href="([\s\S]+?)">/) || [])[1], c = /^(http(s)*:\/\/)\b(?!(\w+\.)*(chengliangyun.com|www.chengliangyun.com))\b/.test(b.replace(/\s/g, ""));
                return "<a href=\"" + b + "\" target=\"_blank\"" + (c ? " rel=\"nofollow\"" : "") + ">"
            }).replace(/<table/g, "<table class='layui-table' ").replace(/<blockquote/g, "<blockquote class='layui-elem-quote layui-text'").replace(/face\[([^\s\[\]]+?)\]/g, function (a) {
                let b = a.replace(/^face/g, "");
                return "<img class=\"face\" alt=\"" + b + "\" title=\"" + b + "\" src=\"" + j.faces[b] + "\">"
            })
        }, render: function (a) {
            a = a || {};
            var d = function () {
                750 < b(window).width() ? c.photos({photos: a.elem, img: "img:not(.face)", zIndex: 9999999999, anim: -1}) : b("body").on("click", a.elem + " img:not(.face)", function () {
                    window.open(this.src)
                })
            };
            b(a.elem).each(function () {
                let a = b(this), c = a.text();
                a.html(j.content(c))
            }), d()
        }, cvUpdate: function () {

        }
    };
    j.faces || (j.faces = g), a("easyeditor", j)
});

