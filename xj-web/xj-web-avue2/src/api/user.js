import request from '@/router/axios';
import {baseUrl} from '@/config/env';

export const loginByUsername = (username, password, code, redomStr) => request({
    url: baseUrl + '/api/admin/user/login',
    method: 'post',
    meta: {
        isToken: false
    },
    params: {
        username: username,
        password: password,
        code: code,
        redomStr: redomStr,
        // 是否动态增加 终端配置字段 terminal
        isTerminal: true
    }
});

export const getUserInfo = () => request({
    url: baseUrl + '/api/admin/user/findUser',
    method: 'get'
});

export const refeshToken = () => request({
    url: baseUrl + '/user/refesh',
    method: 'post'
});

export const getMenu = (pid) => request({
    url: baseUrl + '/api/admin/menu/list',
    method: 'get',
    params: {
        pid: pid,
        disable: 0,
        isTree: true,
        isLoginUser: true,
        //terminal: 3,
        // 是否动态增加 终端配置字段 terminal
        isTerminal: true
    }
});

export const getTopMenu = () => request({
    url: baseUrl + '/api/admin/menu/list',
    method: 'get',
    params: {
        disable: 0,
        isTree: true,
        isLoginUser: true,
        isBottomLayer: false,
        //terminal: 3,
        // 是否动态增加 终端配置字段 terminal
        isTerminal: true
    }
});

export const sendLogs = (list) => request({
    url: baseUrl + '/user/logout',
    method: 'post',
    data: list
});

export const logout = () => request({
    url: baseUrl + '/user/logout',
    meta: {
        isToken: false
    },
    method: 'get'
});