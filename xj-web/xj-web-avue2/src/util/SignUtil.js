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
    // 数组长度小于2 或 没有指定排序字段 或 不是json格式数据
    // 判断是数组还是对象
    if (data instanceof Array) {
        /**
         * 数组
         */
        if (data[0] instanceof Object || data[0] instanceof Array) {
            // 数组的下级是对象或者是数组
            let arrays = data;
            let newArrays = [];
            for (let i = 0; i < arrays.length; i++) {
                // 获取每一个下级->  a:{a,b,c}
                let dataTwo = arrays[i];
                // 根据 key 类型排序
                let keysTwo = Object.keys(dataTwo).sort();
                let newDataTwo = {};
                // 遍历 key/value
                for (let keyTwo of keysTwo) {
                    // 下级是对象, 继续递归排序
                    if (dataTwo[keyTwo] instanceof Object) {
                        newDataTwo[keyTwo] = bodyDataSort(dataTwo[keyTwo]);
                    } else {
                        if ((dataTwo[keyTwo] != null)) {
                            newDataTwo[keyTwo] = dataTwo[keyTwo];
                        }
                    }
                }
                newArrays.push(newDataTwo);
            }
            return newArrays;
        } else {
            // 不处理: [0,1,2,3] 数组数据
            return data;
        }
    } else if (data instanceof Object) {
        /**
         *  对象
         */
            // 根据 key 类型排序
        let keys = Object.keys(data).sort();
        let newData = {};
        // 遍历 key/value
        for (let key of keys) {
            if (data[key] instanceof Object) {
                // 下级是对象, 继续递归排序
                newData[key] = bodyDataSort(data[key]);
            } else {
                if (data[key] != null) {
                    newData[key] = data[key];
                }
            }
        }
        return newData;
    } else {
        return data;
    }
}


