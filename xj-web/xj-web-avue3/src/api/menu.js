import request from '@/router/axios';
import { baseUrl } from '@/config/env';

/**
 * 获取左菜单
 * @author wangsong 
 * @date 2021/9/17 0017 22:36
 * @return 
 * @version 1.0.0
 */
export const getMenu = (type = 0) => request({
    url: baseUrl + ' /api/admin/menu/findTree',
    method: 'get',
    data: {
        type
    }
});

/**
  * 获取顶部菜单
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/9/17 0017 22:36 
  * @version 1.0.0      
  */
export const getTopMenu = () => request({
    url: baseUrl + '/user/getTopMenu',
    method: 'get'
});
