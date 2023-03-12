<h1 align="center">
   <a href="http://xijia.plus">xijia-plus v2.1.6  </a>

   <a href='https://gitee.com/wslxm/xijia-plus/stargazers'><img src='https://gitee.com/wslxm/xijia-plus/badge/star.svg?theme=white' alt='star'></img></a>
   <a href='https://gitee.com/wslxm/xijia-plus/members'><img src='https://gitee.com/wslxm/xijia-plus/badge/fork.svg?theme=white' alt='fork'></img></a>
</h1>

<h4 align="center"> 基于 springboot2 + vue2 前后端分离的 Java 快速开发脚手架 </h4>
<div align="center"> <a target="_blank" href="https://jq.qq.com/?_wv=1027&k=lmPjMgs3">QQ群：1037211892 </a>  
 &nbsp; 微信群: 1720696548 (加作者微信邀请加入)
</div >



## 一、项目描叙


xijia-plus 是一套完全开源的快速开发平台，毫无保留给个人及企业免费使用。


- 这是一个 网站应用 /App /小程序 等应用的后台快速开发架构/脚手架
- 主要应用于中小型项目的快速开发 (为什么不是大型项目呢? 一般开发大型项目的公司都有自己的架构团队)
- 完善的分布式解决方案： 支持集群，支持模块化开发，分布式缓存，分布式锁，分布式唯一编号
- 完善的基础功能： 已经历数年的优化改进, 让其更简洁易懂易操作及美观 
- 完善的代码生成功能： 提供管理端接口+页面自动生成, 生成后的页面crud功能将直接可以正常使用

----------

- [预览地址：http://manage.xijia.plus/](http://manage.xijia.plus/)    | 账号: `test`  密码: `123456`  ps: 预览地址部署在内网机器上，访问有点慢
- [项目文档: (http://xijia.plus/)](http://xijia.plus/)  |  [原文档地址 wikis (已停止维护)](https://gitee.com/wslxm/xijia-plus/wikis/pages)

- [项目文档-启动项目](http://xijia.plus/%E6%9C%8D%E5%8A%A1%E7%AB%AF/%E5%BF%AB%E9%80%9F%E4%BD%BF%E7%94%A8.html)
- [更新日志](https://gitee.com/wslxm/xijia-plus/blob/2.x/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97.md) 
- [目录结构](https://gitee.com/wslxm/xijia-plus/blob/2.x/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/2.x%20%E7%89%88%E6%9C%AC%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.md)
----------

#### 项目宗旨：
 - 只为急速开发而生, 主要针对于中小型的项目快速开发
 - 1、**快速** (提供基础crud生成, 包括前端页面生成且与接口进行自动匹配)
 - 2、**简洁** (对重复使用率高的工具代码进行封装)
 - 3、**规范** (对接口命名,方法命名等,请求方式等统一规范,让接口对接更简单)

#### 系统功能：
- **接口文档**：swagger + knife4j
- **统一设计**：字典枚举, 全局配置, 全局异常，自定义异常, 统一返回, 统一接口规范
- **网关验证**：登录认证, 接口授权, 验签, 限流, 黑名单, 核心参数加解密, 日志持久化
- **参数处理**：核心参数加解密, 敏感参数脱敏, 验参方案统一
- **基础功能**: 用户/ 角色/ 菜单/ 部门/ 接口管理/ 字典管理
- **增强功能**: banner/ 消息管理/ 请求日志/ 黑名单/ 全局配置
- **快速开发**: 前后端代码生成  (急速开发的关键)
- **技术集成**: 阿里云oss, websocket, redis
- **部署方案**：提供 linux 传统方式部署, docket 部署方案等相关文档
- **其他更多**


## 二、分支说明
- **master**  (主分支, 当前最新版本的代码, 于2.x分支代码同步)
- **v-1.x**   (原前端为layui 的分支版本(已停止维护))
- **v-2.x**   (当前v-2.x.x 最新版本的代码)
- **v-2.x.x**  (每次新开发/优化代码时创建的新分支,完成后同步至v-2.x 以及 master, 该分支可能会删除,删除后可去tab标签中查看之前的版本)

## 三、采用技术:
- `2.x 后端`：springboot2.2.2 + mysql + redis + swagger
- `2.x 前端`：avue2.9.4 + element-ui  
- [更多祥见](https://gitee.com/wslxm/spring-boot-plus2/wikis/%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8/%E9%87%87%E7%94%A8%E6%8A%80%E6%9C%AF
) 

## 四、版本大更新记录


v-0.0.9 后开始记录版本大调整, 当前最新为 2.x 模块

|  版本  |  说明  |
|---|---|
| v-0.0.9 | 版本模块化，模块化目的: 减轻项目大小,按需加载 |
| v-0.1.2 | 对项目模块化进行重新整理, 支持已jar 的方法导入架构代码 |
| v-0.1.3 | layui 的最终版本, 版本完成后正式更名为 1.x 版本 |
| v-1.x | 保留 layui 的最终版本,  对前端进行架构调整, 从layui 调整为 vue + element  |
| v-2.x | 版本移除 layui, 全面使用 vue + element, 并移除相关不常用功能模块 |



## 五、项目展示

|  页面名 |  页面展示 |
|---|---|
| 登录页      | ![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201521_b4b0a90f_2208600.png "屏幕截图.png") |
| 首页        | ![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201610_83f931fa_2208600.png "屏幕截图.png") | 
| 代码生成页   | ![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201654_cc2aa4fe_2208600.png "屏幕截图.png") | 
| 菜单页      | ![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201741_80321125_2208600.png "屏幕截图.png") | 
| 用户管理 <br /> 及 <br />弹出消息通知展示页  | ![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201902_11d194f7_2208600.png "屏幕截图.png") | 
| 其他  | 更多自行访问展示地址 | 


