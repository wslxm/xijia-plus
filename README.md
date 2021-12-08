# spring-boot-plus2

QQ群：1037211892

### 一、项目简述

#### 项目描叙:
这是开发一个后台管理系统快速开发骨架,采用级简化代码，所有基本crud 操作无需写任何的 dao，service层代码，
包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码

#### 采用技术:
- 后端：jdk1.8 + spring-boot2.2.2 + mybatis-plus3.3.1 + druid 1.1.22 + mysql5.7+ + swagger2.9.2 
- 前端：avue2.x (vue2 + element)

#### 项目宗旨：
   - 只为急速开发而生, 主要针对于中小型的项目快速开发
   - 1、快速 (提供基础crud生成)
   - 2、简洁 (对重复使用率高的工具代码进行封装)
   - 3、规范 (对接口命名,方法命名等,请求方式等统一规范,让接口对接更简单)
   - 4、扩展 (提供常有的第三方工具集成,如果 阿里云oss文件管理,短信，微信支付等的集成)

#### 其他说明: 
支持跨域,支持前后端代码抽离并独立开发(springboot（后端)/layui（前端）, 如有前端人员，也可使用其他开发语言来进行对接，如：vue.js
swagger2 文档, 地址：http://ip:port/swagger-ui.html 


### 二、预览地址

vue 版本演示地址：  [http://vue.xijia.plus/](http://vue.xijia.plus/)
账号：test  
密码：123456

### 三、文档地址：

查阅文档： [http://xijia.plus/help](http://xijia.plus/help) 

### 四、各版本说明
v-0.0.9 后开始记录版本大调整, 当前最新为 2.x 模块
- 项目于 v-0.0.9 版本模块化，模块化目的: 减轻项目大小,按需加载
- 项目于 v-0.1.2 对项目模块化进行重新整理,支持已jar 的方法导入架构代码
- 项目于 v-0.1.3 版本完成后正式更名为 1.x 版本
- 项目于 v-1.x 对 前端进行架构调整, 从layui 调整为 vue + element 
- 项目于 v-2.x 版本移除 layui, 全面使用 vue + element,并移除相关不常用功能模块


### 五、更新日志

功能更新日志: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 

### 六、目录结构

2.x 版本目录结构：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/2.x 版本目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/2.x 版本目录结构.md) 


### 七、当前功能列表

#### 基础功能 
- 1、用户管理
- 2、角色管理
- 3、菜单管理
- 4、资源url管理
- 5、动态用户/菜单/角色/资源权限管理 
- 6、权限管理
- 7、字典管理（完全可当枚举字典使用）
- 8、组织机构管理

#### 增强功能
- 1、代码生成/代码生成在线预览   
- 2、角色/菜单/权限管理 
- 3、操作记录，请求响应完整记录请求响应数据
- 4、黑名单管理, 一键拉黑非法请求
- 5、banner 管理, 快速进行运营推广
- 6、全局配置，让页面数据动起来，不用因为修改页面内容而去改代码的难题
- 7、请求日志持久化
- 8、消息管理,可直接对用户进行消息发送

#### 第三方功能功能
- 1、集成 swagger2 接口文档, 地址：http://ip:端口/swagger-ui.html
- 2、集成 knife4j 接口文档, 可生成离线html，word, pdf 文档， 地址：http://ip:端口/doc.html，
- 3、集成 redis
- 4、集成阿里云oss文件管理
- 5、集成阿里云sms短信服务
- 6、集成 websocket, 可以接收和发送及时通知
- 7、集成微信小程序
- 8、集成微信公众号
- 9、集成微信开发平台
- 10、集成微信支付
- 11、集成百度智能云文字识别



### 八、项目展示1 （新 avue版展示）
![输入图片说明](https://images.gitee.com/uploads/images/2021/1116/120206_346806fb_2208600.png "屏幕截图.png")

### 八、项目展示2

登录页

系统首页

代码生成

菜单管理

全局配置


更多自行访问展示地址

### 九、开始使用

- 1、拉取代码
- 2、创建数据库[spring-boot-plus2]
- 3、导入跟目录下的 help/sql 目录下对应的最新版本 sql
- 4、启动demo项目(spring-boot-plus2-demo/ --> SpringBootPlus2DemoServer 或  xj-base/xj-base-admin/ -->  XjBaseAdminServer)

程序找不到包处理方法：https://blog.csdn.net/weixin_43173021/article/details/108280524

备注说明：
- 最新分支的代码与当前项目测试库的是外网连接，拉取最新分支的最新代码在本地可直接使用当前项目的测试服数据库来启动
- 切勿修改测试库数据，否则到时测试地址无法使用，谢谢

### 十、提交 lssues 

欢迎大家提交各种 lssues, 一定将尽力处理系统的各种问题,让系统运行更加稳定，快捷
- 1、bug （系统的各种问题修复）
- 2、优化项 (系统的各种操作体验 和 代码可读性等进行优化)
- 3、新功能项 (系统的未来更新方向,将系统功能更完善, 注意：一点通用功能, 该系统基础骨架不做偏向于某一类系统的功能)

