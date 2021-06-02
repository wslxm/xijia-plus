/**
 *  AJAX 请求工具封装
 */
Ajax = {
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
                let token = request.getResponseHeader("TOKEN");
                if (token != null) {
                    setGlobalHeaders(token);
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
                location.href = loginPage;
            }
            try {
                layer.msg(result.msg);
            } catch (e) {
                // 某些页面没有加载到layer弹出层
                alert(result.msg);
            }
            throw new Error();
        }
        return result;
    }
};
