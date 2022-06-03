# spring-boot-plus2

- QQ群：1037211892
- 微信群: 1720696548 (先加个人号-备注：spring-boot-plus2 或 码云, 在拉入群)
- 加 qq群 或 微信群 免费获取该如下所示全套视频教程(42课->实36课) - (近 7 小时时长)

<div align="center">
<img src="https://images.gitee.com/uploads/images/2022/0119/085201_dda7bbb1_2208600.png"  width="300" height="350">
<img src="https://images.gitee.com/uploads/images/2022/0119/085218_14a67385_2208600.png"  width="300" height="350">
</div>

### 一、项目简述

#### 项目描叙:
这是开发一个后台管理系统快速开发骨架, 采用级简化代码，所有的单表基础crud功能，包括分页查询,id查询, 添加，修改，删除等接口都可以使用代码生成器一键生成，
甚至前端的 vue 的表格展示，添加，编辑，删除等页面及页面功能操作也自动生成出来，并且根据代码生成的模板自动把接口对好, 是不是很强大呢，那就抓紧时间试一试吧

#### 采用技术:

|  后端技术|  
|:--|  
| jdk1.8  |  
| spring-boot2.2.2  |  
| jjwt 0.9.1 |  
| lombok  (实体生成基础方法) | 
| log4j 2.15.0 |  
| cglib 3.2.4 |  
| mybatis  |  
| mybatis-plus 3.3.1  |  
| druid 1.1.22  |  
| swagger 2.9.2  (api文档)|  
| knife4j 2.0.9  (api文档) |  
| hutool 5.4.7 (工具类)|    
| commons-lang3 3.9  (工具类)|   
| poi 3.17  (excel)|   
| fastjson 1.2.61 |    
| redisson- 3.13.6 (redis连接工具,)|   

| 使用服务 |
|:--|
| mysql 5.7+  (数据库) |  
| aly-oss  (文件存储) |  
| redis    (缓存，使用 redisson 3.13.6 连接，已集成支持分布式锁,缓存,分布式编号生成等) |  
| websocket (及时通信) |  



| 前端 |  
|:--|  
| vue2  (需安装 node.js 14 版本)  |  
| element-ui  |  
| avue2.x  (vue2 + ement-ui 二次封装框架) |  
| vue-tinymce  (富文本)|  



#### 项目宗旨：
   - 只为急速开发而生, 主要针对于中小型的项目快速开发
   - 1、快速 (提供基础crud生成)
   - 2、简洁 (对重复使用率高的工具代码进行封装)
   - 3、规范 (对接口命名,方法命名等,请求方式等统一规范,让接口对接更简单)
   - 4、扩展 (提供常有的第三方工具集成,如果 阿里云oss文件管理,短信，微信支付等的集成)

#### 核心功能：

- 项目基础服务： 多环境配置，提供swagger接口文档, 提供 knife4j 接口文档, 可生成离线html，word, pdf 文档
- 系统统一设计： jvm/redis缓存, 数据库字段枚举映射, 全局配置, 全局异常处理，统一返回对象 R规范, 请求响应对象规范 ( DTO/ VO/ Entity), 统一接口规范使用 restful 
- api 服务网关： 登录认证,接口授权,日志持久化,请求参数验证,敏感参数加解密,黑名单,加签,限流
- 引入第三方核心技术: 阿里云oss文件保存,  websocket 消息及时推送
- 代码生成: 生成代码预览, 生成后端crud接口, 并支持生成vue页面并自动对接接口
- 基础功能: 用户/组织机构,角色/菜单/接口/字典管理
- 增强功能: banner,消息管理,请求日志查看,黑名单管理, 全局配置管理等
- 其他: excel 导出/导入,自封装相关工具类(看源码), 微信相关api集成,如:公众号,小程序,支付等,及其他api集成
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


### 七、功能列表展示(覆盖不全,具体参考源码)

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



### 八、项目展示（新-avue版）

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

### 九、开始使用
#### 启动服务端
- 1、拉取代码
- 2、创建数据库[spring-boot-plus2] 编码：**utf8mb4**  排序规则：**utf8mb4_general_ci**
- 3、导入跟目录下的 help/sql 目录下对应的最新版本 sql
- 4、启动demo项目(spring-boot-plus2-demo/ --> SpringBootPlus2DemoServer 或  xj-base/xj-base-admin/ -->  XjBaseAdminServer)

- 修改超管密码: 祥见 Md5Util 工具类的 main 参数方法

#### 启动前端
- 1、在 xj-web/xj-web-avue2 目录下执行` npm install` 或` npm install --registry=https://registry.npm.taobao.org`
- 2、使用 `npm run serve` 启动服务


程序找不到包处理方法：https://blog.csdn.net/weixin_43173021/article/details/108280524

#### 备注说明：
- 最新分支的代码与当前项目测试库的是外网连接，拉取最新分支的最新代码在本地可直接使用当前项目的测试服数据库来启动
- 切勿修改测试库数据，否则到时测试地址无法使用，谢谢

### 十、提交 lssues 

欢迎大家提交各种 lssues, 一定将尽力处理系统的各种问题,让系统运行更加稳定，快捷
- 1、bug （系统的各种问题修复）
- 2、优化项 (系统的各种操作体验 和 代码可读性等进行优化)
- 3、新功能项 (系统的未来更新方向,将系统功能更完善, 注意：一定是通用功能, 该系统基础骨架不做偏向于某一类系统的功能)

