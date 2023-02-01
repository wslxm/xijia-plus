import Vue from 'vue'
import VueI18n from 'vue-i18n'
import Store from '@/store'
import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'
import avueZhLocale from '@smallwei/avue/lib/locale/lang/zh'

import zhLocale from './zh'
import Avue from '@smallwei/avue'

Vue.use(VueI18n)
const messages = {
    zh: {
        ...zhLocale,
        ...elementZhLocale,
        ...avueZhLocale,
        ...Avue.locale.zh,
    },
}

const i18n = new VueI18n({
    locale: Store.getters.language,
    messages,
});


export default i18n