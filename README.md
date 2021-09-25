# spring-boot-plus2

QQ群：1037211892

### 一、项目简述

#### 项目描叙:
这是开发一个后台管理系统快速开发骨架,采用级简化代码，所有基本crud 操作无需写任何的 dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码

#### 采用技术:
- 后端：jdk1.8 + spring-boot2.2.2 + mybatis-plus3.3.1 + druid 1.1.22 + mysql5.7+ + swagger2.9.2 
- 前端：layui 2.5.6 + js + jquery

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

1.x 版本演示地址： [http://xijia.plus/](http://xijia.plus/) 
账号：10000  
密码：10000


2.x 版本演示地址： 开发中(敬请期待)

### 三、文档地址：

查阅文档： [http://xijia.plus/help](http://xijia.plus/help) 

### 四、版本说明

- 1.x 版本目前将会进行长期维护,不在更新新功能(前端 layui)
- 2.x 版本将进行 ui 替换为 vue + element 或 avue(element二次封装), 目前待确定使用 vue2 还是 vue3 (尽情期待)

---- 
- 项目于 0.0.9+模块化，模块化目的:减轻项目大小,按需加载
- 项目于 v-0.1.2+ 后对项目模块化进行重新整理,支持已jar 的方法导入架构代码(需拉取该项目执行maven打包命令，把jar打入本地仓库)
- 项目于 v-0.1.3 版本后正式更名为 1.x 版本
- 从 layui 2.5.6 替换为 vue + element 或 avue(element二次封装),目前待确定使用 vue2 还是 vue3(尽情期待)


### 五、更新日志

更新日志文档(2021-9-16更新): [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 

### 六、目录结构
1.x 版本(2021-8-18更新)：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.2版本目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.2版本目录结构.md) 


### 七、当前功能列表

#### 基础功能 
- 1、用户管理
- 2、角色管理
- 3、菜单管理
- 4、资源url管理
- 5、动态用户/菜单/角色/资源权限管理 
- 6、权限管理
- 7、字典管理（完全可当枚举字典使用）

#### 增强功能
- 1、代码生成/代码生成在线预览   
- 2、角色/菜单/权限管理 
- 3、操作记录，请求响应完整记录请求响应数据
- 4、黑名单管理, 一键拉黑非法请求
- 5、banner 管理, 快速进行运营推广
- 6、全局配置，让页面数据动起来，不用因为修改页面内容而去改代码的难题
- 7、请求日志持久化

#### 第三方API功能
- 1、集成 springBootAdmn 系统监控
- 2、集成 knife4j 接口文档,可生成离线html，word, pdf 文档， 服务地址：http://localhost/doc.html，
- 3、集成 swagger2 接口文档,地址：http://localhost/swagger-ui.html
- 4、阿里云oss文件管理
- 5、阿里云sms短信服务
- 6、快递100 快递 查询api对接
- 7、顺丰快递 寄件api对接
- 8、websocket 及时通知，和简易在线聊天室
- 9、微信小程序登录
- 10、微信公众号模板消息推送
- 11、微信网页授权
- 12、七牛云oss文件管理
- 13、接入 markdown 编辑器editor，并实现cv上传图片，满足你的文本编辑需求


### 八、项目展示

登录页
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/103734_b430eda8_2208600.png "屏幕截图.png")

系统首页
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/103758_b62b76fa_2208600.png "屏幕截图.png")

代码生成
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/103834_ab76402c_2208600.png "屏幕截图.png")

菜单管理
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/103852_4e742e37_2208600.png "屏幕截图.png")

全局配置
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/103918_2a04aefb_2208600.png "屏幕截图.png")

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