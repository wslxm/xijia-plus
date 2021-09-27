import request from '@/router/axios';
import {baseUrl} from '@/config/env';

export const loginByUsername = (username, password, code, redomStr) => request({
    url: baseUrl + '/api/admin/user/login',
    method: 'post',
    meta: {
        isToken: false
    },
    params: {
        username,
        password,
        code,
        redomStr
    }
})

export const getUserInfo = () => request({
    url: baseUrl + '/api/admin/user/findUser',
    method: 'get'
});

export const refeshToken = () => request({
    url: baseUrl + '/user/refesh',
    method: 'post'
})

export const getMenu = (pid) => request({
    url: baseUrl + '/api/admin/menu/list',
    method: 'get',
    params: {
        pid: pid,
        disable: 0,
        isTree: true,
        terminal: 2
    }
});

export const getTopMenu = () => request({
    url: baseUrl + '/api/admin/menu/list',
    method: 'get',
    params: {
        disable: 0,
        isTree: true,
        terminal: 2,
        isBottomLayer: false
    }
});

export const sendLogs = (list) => request({
    url: baseUrl + '/user/logout',
    method: 'post',
    data: list
})

export const logout = () => request({
    url: baseUrl + '/user/logout',
    meta: {
        isToken: false
    },
    method: 'get'
})