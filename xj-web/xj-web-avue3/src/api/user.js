import request from '@/router/axios';
import { baseUrl } from '@/config/env';

/**
  * 账号密码登录
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/9/17 0017 22:50 
  * @version 1.0.0      
  */
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

/**
  * 获取登录人的用户信息
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/9/17 0017 22:51 
  * @version 1.0.0      
  */
export const getUserInfo = () => request({
    url: baseUrl + '/api/admin/user/findUser',
    method: 'get'
});

/**
  * 刷新token
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/9/17 0017 22:51 
  * @version 1.0.0      
  */
export const refeshToken = () => request({
    url: baseUrl + '/user/refesh',
    method: 'post'
})


export const sendLogs = (list) => request({
    url: baseUrl + '/user/logout',
    method: 'post',
    data: list
})

/**
  * 退出登录
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/9/17 0017 22:56
  * @version 1.0.0      
  */
export const logout = () => request({
    url: baseUrl + '/user/logout',
    meta: {
        isToken: false
    },
    method: 'get'
})