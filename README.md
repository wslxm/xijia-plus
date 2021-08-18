# spring-boot-plus2

QQ群：1037211892

### 一、项目简述

项目宗旨：只为急速开发而生, 主要针对于中小型的项目快速开发

这是开发一个基于spring-boot2.2.0 + mybatis-plus3.3.1 + druid + mysql8 + layui2.5.6 + swagger2.9.2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码


#### 展示地址
[http://xijia.plus/](http://xijia.plus/) 

账号：10000  
密码：10000

#### 文档地址：
 [http://xijia.plus/help](http://xijia.plus/help) 

#### 最新说明
- 当前主分支为v-0.1.2 的分支最后更新代码,可稳定运行(2021-8-18更新)
- 项目于0.0.9+模块化，模块化目的:减轻项目大小,按需加载
- 更新日志文档(2021-6-24更新): [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 


#### 各版本目录结构

- 0.0.8版本(2021-1-14更新)：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.0.8 版本及之前目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/0.0.8 版本及之前目录结构.md) 
- 0.0.9-0.1.0 版本(2021-3-05更新)：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.0.9- 0.1.0  版本目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84/0.0.9-%200.1.0%20%20%E7%89%88%E6%9C%AC%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.md) 
- 0.1.1 版本(2021-6-24更新)：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.1版本目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.1版本目录结构.md) 
- 0.1.2 版本(2021-8-18更新)：[https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.2版本目录结构.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/目录结构/0.1.2版本目录结构.md) 


#### 更多

- 支持跨域,支持前后端代码抽离并独立开发(springboot（后端)/layui（前端）, 如有前端人员，也可使用其他开发语言来进行对接，如：vue.js
- swagger2 文档, 地址：http://ip:port/swagger-ui.html ,也可访问 knife4j-ui, 地址：http://ip:port/doc.html


### 三、当前功能列表
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



### 四、项目外观展示

![输入图片说明](https://images.gitee.com/uploads/images/2020/1206/114540_8a29dc40_2208600.png "屏幕截图.png")

更多自行访问展示地址



### 五、项目使用

- 1、拉取代码
- 2、创建数据库[spring-boot-plus2]
- 3、导入跟目录下的 sql 目录下对应的最新版本 sql, 注意主分支对应的版本，历史sql版本在old下，非sql/old下的sql,如 sql/file 如当前版本有使用到也一起导入到数据库
- 4、启动demo项目

程序找不到包处理方法：https://blog.csdn.net/weixin_43173021/article/details/108280524

注：最新分支的代码与当前项目测试库的是外网连接，拉取最新分支的最新代码在本地可直接使用当前项目的测试服数据库来启动，切勿修改测试库数据，否则到时测试地址无法使用，谢谢