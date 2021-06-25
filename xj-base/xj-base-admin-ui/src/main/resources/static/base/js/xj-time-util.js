
/**
 * 时间计算工具
 * @type
 */
TimeUtil = {


    /**
     *  对Date的扩展，将 Date 转化为指定格式的String
     *  月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
     *  年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     *  例子：
     *  getFormatDate("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423
     *  getFormatDate("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18
     */
    getFormatDate: function (fmt) {
        let data = new Date();
        var o = {
            "M+": data.getMonth() + 1, //月份
            "d+": data.getDate(), //日
            "H+": data.getHours(), //小时
            "m+": data.getMinutes(), //分
            "s+": data.getSeconds(), //秒
            "q+": Math.floor((data.getMonth() + 3) / 3), //季度
            "S": data.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (data.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    },

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
        var t = etime - stime;  //两个时间戳相差的毫秒数
        // var days = Math.floor(usedTime / (24 * 3600 * 1000));
        // // 计算出小时数
        // var leave1 = usedTime % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数
        // var hours = Math.floor(leave1 / (3600 * 1000));
        // // 计算相差分钟数
        // var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数
        // var minutes = Math.floor(leave2 / (60 * 1000));
        // // 计算相差分秒数 //计算分后剩余的秒数
        // var second = (leave2 - (minutes * 60 * 1000)) /1000 ;
        // var time = days + "天" + hours + "时" + minutes + "分" + second + "秒";
        let d = Math.floor(t / 1000 / 60 / 60 / 24);
        let h = Math.floor(t / 1000 / 60 / 60 % 24);
        let m = Math.floor(t / 1000 / 60 % 60);
        let s = Math.floor(t / 1000 % 60);
        let html = d + "天 " + h + "时 " + m + "分 " + s + "秒";
        // var time = days;
        return html;
    }
};
