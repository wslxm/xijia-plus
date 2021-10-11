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
export const put = (uri, data) => request({
    url: baseUrl + uri,
    method: 'put',
    meta: {
        isSerialize: false
    },
    data: data
})

// id删除
export const del = (uri, id) => request.delete(baseUrl + uri, {
    params: {
        id
    }
})


