# spring-boot-plus2

QQ群：1037211892

### 一、项目简述

项目宗旨：只为急速开发而生, 主要针对于中小型的项目快速开发

这是开发一个基于spring-boot + mybatis-plus+ druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码


#### 展示地址
[http://xijia.plus/](http://xijia.plus/) 

账号：10000  
密码：10000

#### 文档地址：
 [http://xijia.plus/help](http://xijia.plus/help) 

#### 最新说明
- 当前主分支为v-0.0.8 的分支最后更新代码,可稳定运行
- 更新文档: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 
- 项目于0.0.9+模块化，模块化目的:减轻项目大小,按需加载
#### 更多

- 支持跨域,支持前后端代码抽离并独立开发(springboot（后端)/layui（前端）, 如有前端人员，也可使用其他开发语言来进行对接，如：vue.js
- swagger2 文档, 地址：http://localhost/swagger-ui.html


### 二、项目结构目录(代码结构未 v-0.0.8 - v-0.0.9)

v-0.0.9+ 版本后端代码目录结构(模块化后结构)
```base
1、模块
spring-boot-plus2
        └──xj-base
               └──xj-base-admin            核心包: 管理系统saever
               └──xj-base-admin-ui         核心包: 管理系统ui
               └──xj-base-pay              整合包: 聚合支付
               └──xj-base-core             核心包: 通用base类层+枚举字典
               └──xj-base-jwt              核心包: 登录认证
               └──xj-base-result           核心包: 统一返回 + 全局异常
        └──xj-server     
               └──xj-test-server           业务代码(个人功能开发/架构测试)
               └──xxx                      开发具体项目业务代码模块
        └──xj-third
               └──xj-api-aliyun-oss        阿里云oss集成(文件存储)
               └──xj-api-aliyun-sms        阿里云sms集成(短信)
               └──xj-api-baidu             百度api集成(图片/身份证/银行卡识别)
               └──xj-api-kuaidi-kuaidi100  快递100集成(查询物流)
               └──xj-api-kuaidi-sf         顺丰-丰桥(顺丰寄件)
               └──xj-api-qiniu-oss         七牛云oss集成(文件存储)
               └──xj-api-wx-app            微信小程序集成(小程序登录/小程序订阅消息)
               └──xj-api-wx-mp             微信公众号集成(公众号网页授权/公众号模块消息)
               └──xj-api-wx-pay            微信支付集成(支付/退款/打款)
               └──xj-doc-knife4j           接口文档ui集成(依赖swagger2：访问地址: ip/doc.html)
               └──xj-doc-swagger2          接口文档集成
               └──xj-doc-swagger2-ui       接口文档ui集成(依赖swagger2：访问地址: ip/swagger-ui.html)
               └──xj-fw-boot-admin         监控中心集成    
               └──xj-fw-redis              redis集成    
               └──xj-fw-websocket          websocket集成    
        └──xj-utils
               └──xj-util-core             核心工具(注意：使用了核心工具类需启动服务需禁数据源)
               └──xj-util-fastjson         fastjson工具
               └──xj-util-poi              excel 工具
               └──xj-util-qrcode           二维码 工具
               └──xi-util-geodesy          通过经纬度距离计算 工具(DistanceUtil)
```


v-0.0.8 - v-0.0.9 版本，后端代码目录结构 
```base

spring-boot-plus2
            └──client  用户端接口 (目录结构同 modules)
            └──common  工具
                 └──function     Lambda8 获取字段名工具
                 └──result       统一返回
                 └──utils        util工具类
            └──config  系统配置
                 └──aspect       aop 接口入口(登录认证/权限/日志/黑名单/幂等拦截处理等)
                 └──auth         jwt登录（获取用户信息工具类 / 登录处理核心逻辑，aop调用） 
                 └──datasource   数据源配置(mybatis-plus/druid)
                 └──datetime     统一时间处理 (yyyy-MM-dd HH:mm:ss)
                 └──error        全局异常
                 └──idempotent   幂等
                 └──init         系统启动存放,初始化相关数据
                 └──mvc          mvc 配置
                 └──swagger      接口文档配置
            └──enums   常量/枚举
            └──modules
                 └──business     业务模块
                        └──xx1         
                        └──xx2   
                 └──sys          系统模块
                        └──admin        系统核心功能
                        └──base         系统核心通用层
                        └──gc           代码生成器
                        └──pay          支付封装
                        └──xj           系统增强功能
                 └──third        第三方服务
                        └──aliyun       阿里云API
                                └──oss           阿里云对象存储(文件)
                                └──sms           阿里云短信
                        └──baidu        百度api
                     
                        └──kuaidi       快递
                                └──kuaidi100     快递100查询物流
                                └──sf            顺丰寄件
                        └──qiniu        七牛云oss
                        └──websocket    及时通知/聊天(注意与xjAdminMsg及时通知表有关联)
                        └──wechat       微信API
                                └──app           小程序登录 
                                └──mq            公众号(网页授权/模板信息推送)
                                └──pay           微信支付
   └──task     定时任务

```


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
- 2、操作记录，请求响应完整记录请求响应数据
- 3、黑名单管理, 一键拉黑非法请求
- 4、banner 管理, 快速进行运营推广
- 5、全局配置，让页面数据动起来，不用因为修改页面内容而去改代码的难题
- 6、帮助中心, 提供文档添加，查看功能

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
- 4、启动项目

注：最新分支的代码与当前项目测试库的是外网连接，拉取最新分支的最新代码在本地可直接使用当前项目的测试服数据库来启动，切勿修改测试库数据，否则到时测试地址无法使用，谢谢