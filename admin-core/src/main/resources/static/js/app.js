
/**
 * 弹出层方法
 * @constructor
 */
function tipsWindown(name,url){
    var width = "600px";
    var height = "400px";
    tipsWindownTwo(name,url,width,height);
}


function tipsWindownTwo(name,url,width,height){
    layui.use('layer', function () {
        layer.open({
            type: 2,
            content: [url],
            area: [width, height],
            title: [name],
            fixed: false,
            //maxmin: true,
            shadeClose: true,
            end: function () {       //窗口销毁时调用
                location.reload();   //页面刷新
            }
        });
        return index;
    })
}

/**
 * 分页配置
 */
var pageJson = {
    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] //自定义分页布局
    //,curr: 5 //设定初始在第 5 页
    , limits: [1, 10, 20]  //每页显示条数
    , groups: 5 //只显示 1 个连续页码
    , first: false //不显示首页
    , last: false //不显示尾页
    // , prev: '《'
    // , next: '》'
};
	


/**
 * 数据请求 -->  get
 * 参数:  url 
 * @return: json
 */
function ajaxGet(url) {
    var result = "no";
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        async: false, //同步
        success: function (resultText) {
            result = resultText;
        },
        error: function () {
            layer.msg('后台异常！处理失败');
        }
    });
    return result;
}


/**
 * 数据请求 -->  post
 * 参数:  url 
 * @return: text
 */
function ajaxPost(url,data) {
    var result = "no";
    $.ajax({
        type: "post",
        dataType: "text",
        url: url,
        data: data,
        async: false, //同步
        success: function (resultText) {
            result = resultText;
        },
        error: function () {
            layer.msg('后台异常！处理失败');
        }
    });
    return result;
}


/**
 * 判断时间过去了多少天，（只计算到日期yyyy-MM-dd，未计算小时/分/秒HH:mm:ss）
 *  传入标准时间字符串 yyyy-MM-dd HH:mm:ss || yyyy/MM/dd HH:mm:ss|| yyyy-MM-dd  || yyyy/MM/dd
 */
function judgeTime(data){
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
    var time = dateTime.replace("-","/");
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
function getDateAfter_n(initDate, days, flag){

    if(!days){
        return initDate;
    }
    initDate = initDate.replace(/-/g,'');
    flag = $.trim(flag);
    var date;
    // 是否设置了起始日期
    if(!$.trim(initDate)){ // 没有设置初始化日期，就默认为当前日期
        date = new Date();
    }else{
        var year = initDate.substring(0,4);
        var month = initDate.substring(4,6);
        var day = initDate.substring(6,8);
        date = new Date(year, month-1, day); // 月份是从0开始的
    }
    date.setDate(date.getDate() + days);

    var yearStr = date.getFullYear();
    var monthStr = ("0"+(date.getMonth()+1)).slice(-2, 8); // 拼接2位数月份
    var dayStr = ("0"+date.getDate()).slice(-2, 8); // 拼接2位数日期
    var result = "";
    if(!flag){
        result = yearStr+"年"+monthStr+"月"+dayStr+"日";
    }else{
        result = yearStr+flag+monthStr+flag+dayStr;
    }
    return result;
}





