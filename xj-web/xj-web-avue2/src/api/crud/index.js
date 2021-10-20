import request from '@/router/axios';
import {baseUrl} from '@/config/env';

// 查询
export const get = (uri, data) => {
    return request({
        url: baseUrl + uri,
        method: 'get',
        meta: {
            isSerialize: true
        },
        params: data
    })
}

// 添加
export const post = (uri, data) => request({
    url: baseUrl + uri,
    method: 'post',
    meta: {
        isSerialize: false
    },
    data: data
})

// 编辑
export const put = (uri, data, params) => request({
    url: baseUrl + uri,
    method: 'put',
    meta: {
        isSerialize: false
    },
    data: data,
    params: params
})


// 删除
export const del = (uri, params) => request.delete(baseUrl + uri, {
    method: 'delete',
    meta: {
        isSerialize: false
    },
    params: params
})

/**
 * 文件上传, 可提过 column 中的信息进行各种验证
 * @author wangsong
 * @mail  1720696548@qq.com
 * @date  2021/10/16 0016 12:58
 * @version 1.0.0
 */
export const upload = (thih, file, column) => {
    // var newFile = new File([file], file.name, {type: file.type});
    // 开始上传
    var formData = new FormData();
    formData.append("file", file);
    request({
        url: baseUrl + "/api/open/aliOssFile/upload?filePath=" + column.path,
        method: 'post',
        headers: {"Content-Type": "multipart/form-data;charset=UTF-8"},
        meta: {
            isSerialize: false
        },
        data: formData
    }).then(res => {
        // 多图逗号分割追加
        if (thih.obj[column.prop] == null || thih.obj[column.prop] == "") {
            thih.obj[column.prop] = res.data.data;
        } else {
            thih.obj[column.prop] += "," + res.data.data;
        }
    })
}


/**
 * 删除行
 * @author wangsong
 * @param thih 调用者（调用者需提供 onLoad 查询方法,删除后调用此方法重新从后台查询数据）
 * @param uri  删除接口地址
 * @param id   删除id
 * @param index  删除索引
 * @date 2021/10/16 0016 9:50
 * @return
 * @version 1.0.0
 */
export const delRow = (thih, uri, id, index) => {
    thih.$confirm(`此操作将永久删除序号【${index + 1}】的数据, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        del(uri + "/" + id).then(() => {
            // 删除后刷新列表数据
            thih.onLoad();
        })
    })
}

/**
 * 分页查询/列表查询
 * @author wangsong
 * @param thih
 * @param isPage 是否分页
 * @param isPage 是否触发回调函数（定义 onLoadCallback 方法）
 * @mail  1720696548@qq.com
 * @date  2021/10/16 0016 10:00
 * @version 1.0.0
 */
export const list = (thih, isPage, isCallback) => {
    // 默认分页
    isPage = isPage == null ? true : isPage
    isCallback = isCallback == null ? false : isCallback
    // 查询参数
    let params = thih.search != null ? thih.search : {};
    // 分页参数
    params.current = thih.page != null ? thih.page.currentPage : 0;
    params.size = thih.page != null ? thih.page.pageSize : 0;
    // 发起请求
    get(thih.uri.infoList, params).then(res => {
        if (isPage) {
            // 分页
            thih.data = res.data.data.records;
            thih.page.total = res.data.data.total;
        } else {
            // 不分页
            thih.data = res.data.data;
        }
        // 添加行编辑默认为false
        // for (let i = 0; i < thih.data.length; i++) {
        //     thih.data[i].$cellEdit = false
        // }
        console.log(thih.data)
        if (isCallback) {
            // 回调
            thih.onLoadCallback(res);
        }
    })
}
