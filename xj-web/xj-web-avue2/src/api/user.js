import request from '@/router/axios';
import {baseProxyPathRewrite} from '@/config/env';

export const loginByUsername = (username, password, code, redomStr) => request({
    url: baseProxyPathRewrite + '/api/admin/user/login',
    method: 'post',
    meta: {
        isToken: false
    },
    params: {
        username: username,
        password: password,
        code: code,
        redomStr: redomStr,
    }
});

export const getUserInfo = () => request({
    url: baseProxyPathRewrite + '/api/admin/user/findUser',
    method: 'get'
});

export const refeshToken = () => request({
    url: baseProxyPathRewrite + '/user/refesh',
    method: 'post'
});

export const getMenu = (pid) => request({
    url: baseProxyPathRewrite + '/api/admin/menu/list',
    method: 'get',
    params: {
        pid: pid,
        disable: 0,
        isTree: true,
        isLoginUser: true,
    }
});

export const getTopMenu = () => request({
    url: baseProxyPathRewrite + '/api/admin/menu/list',
    method: 'get',
    params: {
        disable: 0,
        isTree: true,
        isLoginUser: true,
        isBottomLayer: false,
    }
});

export const sendLogs = (list) => request({
    url: baseProxyPathRewrite + '/user/logout',
    method: 'post',
    data: list
});

export const logout = () => request({
    url: baseProxyPathRewrite + '/user/logout',
    meta: {
        isToken: false
    },
    method: 'get'
});