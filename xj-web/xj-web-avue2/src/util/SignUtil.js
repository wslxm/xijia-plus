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

import {md5} from '@/util/SignMD5';

/**
 * 加签秘钥
 */
const signConfig = {
    appKey: "xijia",
    secretKey: "xijia@qwer",
};


/**
 * 接口加签方法
 * @author wangsong
 * @mail  1720696548@qq.com
 * @date  2022/9/7 0007 11:21
 * @version 1.0.0
 */
export function signAll(url, params, data, timestamp) {
    // 优先存在query参数加签
    let sign = signQuery(url, params, timestamp);
    // 其次使用body参数(不存在query参数时)
    if (sign == null) {
        sign = signBody(data, timestamp);
    }
    // 没有query参数+body参数, 直接加签
    if (sign == null) {
        let theRequest = {};
        sign = addSing(theRequest, timestamp);
    }
    return sign;
}


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
 * @param url 请求请求
 * @param params
 * @param timestamp 时间戳
 * @returns {string|null}
 */
export function signQuery(url, params, timestamp) {
    if (params == null && url.indexOf("?") === -1) {
        return null;
    }
    // 加签参数, query的所有参数，不为null的
    let theRequest = {};
    for (let k in params) {
        let v = params[k];
        if (v != null) {
            theRequest[k] = v;
        }
    }
    //
    if (url.indexOf("?") !== -1) {
        // // 通过split()分割为数组
        let arr = url.split('?')[1].split('&');
        for (let i = 0; i < arr.length; i++) {
            let kye = arr[i].split("=")[0];
            let value = arr[i].split("=")[1];
            if (value != null) {
                // value不为null给对象赋值，decodeURIComponent目的是为了参数出现 # 等的字符,在请求前进行了encodeURIComponent编码
                theRequest[kye] = decodeURIComponent(value);
            }
        }
    }
    return addSing(theRequest, timestamp);
}


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
export function signBody(data, timestamp) {
    if (data == null) {
        return null;
    }
    // body参数排序
    data = bodyDataSort(data);
    // 转为json
    let bodyJson = JSON.stringify(data);
    // 加签参数
    let theRequest = {};
    theRequest["body"] = bodyJson;
    return addSing(theRequest, timestamp);
}


/**
 * 3、加签实现
 *    3、参数对象根据 key 排序, 只有 body + timestamp 两个参数
 *    4、md5(appKey + 排序后的参数 + secretKey)
 * @returns {*}
 */
function addSing(theRequest, timestamp) {
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
    return md5(signConfig.appKey + dataParams + signConfig.secretKey);
}

/**
 * 4、body 参数排序
 * @returns {*}
 */
function bodyDataSort(data) {
    if (data instanceof Array) {
        /**
         * 数组
         */
        if (data[0] instanceof Object || data[0] instanceof Array) {
            let newArrays = [];
            for (let i = 0; i < data.length; i++) {
                newArrays.push(bodyDataSortNext(data[i]));
            }
            return newArrays;
        } else {
            // 不对参数直接是数组 [0,1,2,3] 数据进行排序, data[0]=0 ,下级不是Object也不是Array
            return data;
        }
    } else if (data instanceof Object) {
        /**
         *  对象
         */
        return bodyDataSortNext(data);
    } else {
        /**
         * 普通参数,直接返回
         */
        return data;
    }
}


/**
 * 4.1、body 参数排序子方法，对象参数排序
 * <P>
 *   // 获取每一个下级->  (对象 {a:'1',b:'2'}  |  a:{a,b,c}  数组：[0,1,2,3] )
 *  </P>
 * @param data 必须是对象参数 //
 * @returns {*}
 */
function bodyDataSortNext(data) {
    // 根据 key 类型排序
    let keys = Object.keys(data).sort();
    // 根据key排序后的数据保存
    let newData = {};
    // 遍历 key/value
    for (let key of keys) {
        // 下级是对象或数组, 继续递归排序，是普通参数,直接放入
        if (data[key] instanceof Object || data[key] instanceof Array) {
            newData[key] = bodyDataSort(data[key]);
        } else {
            if ((data[key] != null)) {
                newData[key] = data[key];
            }
        }
    }
    return newData;
}


