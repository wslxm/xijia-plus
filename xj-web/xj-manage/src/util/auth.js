import Cookies from 'js-cookie'
import website from '@/config/website'
import {getStore, setStore} from '@/util/store'

const Authorization = website.Authorization;

export function getToken() {
    return getStore({name: Authorization});
}

export function setToken(token) {
    setStore({name: Authorization, content: token})
}

export function removeToken() {
    setStore({name: Authorization, content: ""})
}