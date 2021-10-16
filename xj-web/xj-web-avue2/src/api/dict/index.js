import request from '@/router/axios';
import {baseUrl} from '@/config/env';


// 获取或更新字典数据到localStorage中
export const refreshDict = () => {
    request({
        url: baseUrl + "/api/client/dictionary/findCodeGroup",
        method: 'get',
        meta: {
            isSerialize: true
        },
    }).then(res => {
        localStorage.setItem('dictCache', JSON.stringify(res.data.data));
    })
}


/**
 * 获取指定key的字典列表
 * @author wangsong
 * @param enumKay   字典key
 * @param sort      排序, true= 正序-默认 / false=倒序
 * @param all       是否不需要填充所有-查询需要 (true-不需要-默认 / false-需要 )
 * @param dataType  value值的数据类型（true=数字-默认 / false=字符串）
 * <p>
 *     getDict (enumKay, true, true, true)  = getDict (enumKay)
 * </p>
 * @date 2021/10/10 0010 17:38
 * @return
 * @version 1.0.1
 */
export const getDict = (enumKay, sort, all, dataType) => {
    // 没有值默认 true
    sort = sort == null ? sort = true : sort;
    all = all == null ? all = true : all;
    dataType = dataType == null ? dataType = true : dataType;
    const dictListVO = [];
    // 获取所有字典
    let dictCache = localStorage.getItem('dictCache');
    if (dictCache == null) {
        return dictListVO;
    }
    // 获取指定字典
    let dict = JSON.parse(dictCache)[enumKay];
    if (dict == null) {
        return dictListVO;
    }
    // 获取指定字典下的选项
    let dictMap = dict.dictMap;
    if (dictMap == null) {
        return dictListVO;
    }
    // 处理选项数据{label：  ,value： }
    for (var key in dictMap) {
        let dictVO = {};
        // 类型
        if (dataType) {
            dictVO = {label: dictMap[key].name, value: parseInt(dictMap[key].code)};
        } else {
            dictVO = {label: dictMap[key].name, value: dictMap[key].code};
        }
        // 排序
        if (sort) {
            dictListVO.push(dictVO)
        } else {
            dictListVO.unshift(dictVO)
        }
    }
    // 是否需要所有
    if (!all) {
        let dictVO = {label: "所有", value: ""};
        dictListVO.unshift(dictVO);
    }
    return dictListVO;
}

