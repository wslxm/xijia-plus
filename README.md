# spring-boot-plus2

- QQ群：1037211892 
- 微信群: 1720696548  (先加个人号-备注：spring-boot-plus2 或 码云, 在拉入群)
- 加 qq群 或 微信群 免费获取全套视频教程(42课->实36课) - (近 7 小时时长)


### 一、项目简述

#### 项目描叙:
这是开发一个后台管理系统快速开发骨架, 采用级简化代码，所有的单表基础crud功能，包括分页查询,id查询, 添加，修改，删除等接口都可以使用代码生成器一键生成，
甚至前端的 vue 的表格展示，添加，编辑，删除等页面及页面功能操作也自动生成出来，并且根据代码生成的模板自动把接口对好, 是不是很强大呢，那就抓紧时间试一试吧

#### 采用技术:
- 2.x (后端 springboot2.2.2 + mysql + redis   前端：avue2.9.4 + element-ui)
祥见：https://gitee.com/wslxm/spring-boot-plus2/wikis/%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8/%E9%87%87%E7%94%A8%E6%8A%80%E6%9C%AF


#### 项目宗旨：
   - 只为急速开发而生, 主要针对于中小型的项目快速开发
   - 1、快速 (提供基础crud生成, 包括前端页面生成且与接口进行自动匹配)
   - 2、简洁 (对重复使用率高的工具代码进行封装)
   - 3、规范 (对接口命名,方法命名等,请求方式等统一规范,让接口对接更简单)
   - 4、扩展 (默认提供第三方工具集成,如 阿里云oss文件管理,redis,websocket，swagger2)

#### 核心功能：

- 项目基础服务： 多环境配置，提供swagger接口文档, 提供 knife4j 接口文档, 可生成离线html，word, pdf 文档
- 系统统一设计：数据库字段枚举映射, 全局配置, 全局异常处理，统一返回对象 R规范, 请求响应对象规范 (DTO/ VO/ Entity), 统一接口规范使用 restful 
- api 服务网关： 登录认证,接口授权,日志持久化,请求参数验证,敏感参数加解密,黑名单,加签,限流
- 引入第三方核心技术: 阿里云oss文件保存,  websocket 消息及时推送
- 代码生成: 生成代码预览, 生成后端crud接口, 并支持生成vue页面并自动对接接口
- 基础功能: 用户/组织机构,角色/菜单/接口/字典管理
- 增强功能: banner,消息管理, 请求日志查看, 黑名单管理, 全局配置管理等
- redis, 项目默认通过 redis缓存, redis 分布式锁 
- 其他: excel 导出/导入,自封装相关工具类(看源码)
- 项目部署： 提供 linux传统方式部署, docket部署,相关文档

### 二、预览地址

vue 版本演示地址：  [http://vue.xijia.plus/](http://vue.xijia.plus/)

- 测试账号：test  
- 测试密码：123456

### 三、接口文档：

 [http://xijia.plus/swagger-ui.html](http://xijia.plus/swagger-ui.html) 
或
 [http://xijia.plus/doc.html](http://xijia.plus/doc.html) 


### 四、版本大更新记录
v-0.0.9 后开始记录版本大调整, 当前最新为 2.x 模块
- 项目于 v-0.0.9 版本模块化，模块化目的: 减轻项目大小,按需加载
- 项目于 v-0.1.2 对项目模块化进行重新整理,支持已jar 的方法导入架构代码
- 项目于 v-0.1.3 版本完成后正式更名为 1.x 版本
- 项目于 v-1.x 对 前端进行架构调整, 从layui 调整为 vue + element 
- 项目于 v-2.x 版本移除 layui, 全面使用 vue + element,并移除相关不常用功能模块


### 五、更新日志

功能更新日志: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97.md) 

### 六、目录结构

2.x 版本目录结构：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/2.x%20%E7%89%88%E6%9C%AC%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/%E7%9B%B8%E5%85%B3%E6%96%87%E6%A1%A3/2.x%20%E7%89%88%E6%9C%AC%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.md) 


### 七、项目展示（新-avue版）

#### 登录页
![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201521_b4b0a90f_2208600.png "屏幕截图.png")

#### 首页

![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201610_83f931fa_2208600.png "屏幕截图.png")

#### 代码生成页

![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201654_cc2aa4fe_2208600.png "屏幕截图.png")

#### 菜单页

![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201741_80321125_2208600.png "屏幕截图.png")

#### 用户管理 及 弹出消息通知展示页
![输入图片说明](https://images.gitee.com/uploads/images/2021/1208/201902_11d194f7_2208600.png "屏幕截图.png")

更多自行访问展示地址

### 八、开始使用

祥见启动文档: https://gitee.com/wslxm/spring-boot-plus2/wikis/%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8/%E5%90%AF%E5%8A%A8%E9%A1%B9%E7%9B%AE


### 九、提交 lssues 

欢迎大家提交各种 lssues, 一定将尽力处理系统的各种问题,让系统运行更加稳定，快捷
- 1、bug （系统的各种问题修复）
- 2、优化项 (系统的各种操作体验 和 代码可读性等进行优化)
- 3、新功能项 (系统的未来更新方向,将系统功能更完善, 注意：一定是通用功能, 该系统基础骨架不做偏向于某一类系统的功能)

