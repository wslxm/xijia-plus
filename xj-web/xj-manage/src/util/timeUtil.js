const dateTime = new Date()
/**
 *
 */
export default {
    /**
     * Date 时间转 YYYY-MM-DD HH:mm:ss
     *
     * <P>
     *  示例：
     *  import {format} from '@/util/time'
     *  format(new Date())
     * </p>
     * @param dateTime
     * @returns {string}
     */
    format(dateTime) {
        const year = dateTime.getFullYear()
        const month = dateTime.getMonth() + 1 > 9 ? dateTime.getMonth() + 1 : '0' + (dateTime.getMonth() + 1)
        const day = dateTime.getDate() > 9 ? dateTime.getDate() : '0' + dateTime.getDate()

        const hour = dateTime.getHours() > 9 ? dateTime.getHours() : '0' + dateTime.getHours()
        const minute = dateTime.getMinutes() > 9 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()
        const second = dateTime.getSeconds() > 9 ? dateTime.getSeconds() : '0' + dateTime.getSeconds()
        return `${year}-${month}-${day} ${hour}:${minute}:${second}`
    }
}

